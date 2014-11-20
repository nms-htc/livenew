/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Video;
import com.nms.ncms.entity.VideoCategory;
import com.nms.ncms.service.entity.VideoService;
import com.nms.ncms.web.util.AppConfig;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

@Stateless
public class VideoServiceBean extends AbstractProductBean<VideoCategory, Video> implements VideoService {

    private static final long serialVersionUID = -1210801531435011082L;
    private static final Logger LOGGER = Logger.getLogger(VideoServiceBean.class.getName());

    public VideoServiceBean() {
        super(Video.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected void onAfterPersist(Video entity) {
        super.onAfterPersist(entity);
        try {
            saveFile(entity.getVideoFile());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when save video file to the fileStore", ex);
            throw new EJBException("video.error.videoFile.storefile");
        }
    }

    @Override
    protected void onBeforeUpdate(Video entity) {
        super.onBeforeUpdate(entity);
        if (entity.getVideoFile() != null) {
            if (entity.getVideoFile().isHasFile()) {
                try {
                    saveFile(entity.getVideoFile());
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "Error when save video file to the fileStore", e);
            throw new EJBException("video.error.videoFile.storefile");
                }
            } else {
                em.remove(entity.getVideoFile());
                entity.setVideoFile(null);
            }
        }
    }

    @Override
    protected void onBeforeRemove(Video entity) {
        super.onBeforeRemove(entity);
        if (entity.getVideoFile() != null) {
            FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath() + entity.getVideoFile().getId()));
        }
    }

}
