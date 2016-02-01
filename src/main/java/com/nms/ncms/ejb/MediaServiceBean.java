/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Media;
import com.nms.ncms.service.entity.MediaService;
import com.nms.ncms.web.util.AppConfig;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author cuong
 */
@Stateless
public class MediaServiceBean extends AbstractFacadeBean<Media> implements MediaService {
    
    private static final Logger LOGGER = Logger.getLogger(MediaServiceBean.class.getName());
    private static final long serialVersionUID = -7335500168503945339L;

    public MediaServiceBean() {
        super(Media.class);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected void onAfterPersist(Media entity) {
        super.onAfterPersist(entity);
        try {
            saveFile(entity.getContentFile());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when save game file to the fileStore", ex);
            throw new EJBException("game.error.gameFile.storefile");
        }
    }

    @Override
    protected void onBeforeUpdate(Media entity) {
        super.onBeforeUpdate(entity);
        if (entity.getContentFile() != null) {
            if (entity.getContentFile().isHasFile()) {
                try {
                    saveFile(entity.getContentFile());
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "Error when save game file to the fileStore", e);
                    throw new EJBException("game.error.gameFile.storefile");
                }
            } else {
                em.remove(entity.getContentFile());
                entity.setContentFile(null);
            }
        }
    }

    @Override
    protected void onBeforeRemove(Media entity) {
        super.onBeforeRemove(entity);
        if (entity.getContentFile()!= null) {
            FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath() + entity.getContentFile().getId()));
        }
    }
}
