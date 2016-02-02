/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 *
 * All rights reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.SlideContent;
import com.nms.ncms.service.entity.SlideContentService;
import com.nms.ncms.web.util.AppConfig;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import org.apache.commons.io.FileUtils;

public class SlideContentServiceBean extends AbstractFacadeBean<SlideContent> implements SlideContentService {

    private static final long serialVersionUID = -367600908111391886L;
    private static final Logger LOGGER = Logger.getLogger(SlideContentServiceBean.class.getName());

    public SlideContentServiceBean() {
        super(SlideContent.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void onAfterPersist(SlideContent entity) {
        super.onAfterPersist(entity);
        try {
            saveFile(entity.getImage());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when save image file to the fileStore", ex);
            throw new EJBException("slide-content.error.image.storefile");
        }
    }

    @Override
    protected void onBeforeUpdate(SlideContent entity) {
        super.onBeforeUpdate(entity);
        if (entity.getImage() != null) {
            if (entity.getImage().isHasFile()) {
                try {
                    saveFile(entity.getImage());
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "Error when save image file to the fileStore", e);
                    throw new EJBException("slide-content.error.image.storefile");
                }
            } else {
                em.remove(entity.getImage());
                entity.setImage(null);
            }
        }
    }

    @Override
    protected void onBeforeRemove(SlideContent entity) {
        super.onBeforeRemove(entity);
        if (entity.getImage() != null) {
            FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath() + entity.getImage().getId()));
        }
    }

}
