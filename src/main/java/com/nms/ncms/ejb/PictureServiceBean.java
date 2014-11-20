/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Picture;
import com.nms.ncms.entity.Picture;
import com.nms.ncms.entity.PictureCategory;
import com.nms.ncms.service.entity.PictureService;
import com.nms.ncms.web.util.AppConfig;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

@Stateless
public class PictureServiceBean extends AbstractProductBean<PictureCategory, Picture> implements PictureService {

    private static final long serialVersionUID = -1752966518637682180L;
    private static final Logger LOGGER = Logger.getLogger(PictureServiceBean.class.getName());

    public PictureServiceBean() {
        super(Picture.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected void onAfterPersist(Picture entity) {
        super.onAfterPersist(entity);
        try {
            saveFile(entity.getPictureFile());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when save picture file to the fileStore", ex);
            throw new EJBException("picture.error.pictureFile.storefile");
        }
    }

    @Override
    protected void onBeforeUpdate(Picture entity) {
        super.onBeforeUpdate(entity);
        if (entity.getPictureFile() != null) {
            if (entity.getPictureFile().isHasFile()) {
                try {
                    saveFile(entity.getPictureFile());
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "Error when save picture file to the fileStore", e);
            throw new EJBException("picture.error.pictureFile.storefile");
                }
            } else {
                em.remove(entity.getPictureFile());
                entity.setPictureFile(null);
            }
        }
    }

    @Override
    protected void onBeforeRemove(Picture entity) {
        super.onBeforeRemove(entity);
        if (entity.getPictureFile() != null) {
            FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath() + entity.getPictureFile().getId()));
        }
    }

}
