/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Game;
import com.nms.ncms.entity.Music;
import com.nms.ncms.entity.MusicCategory;
import com.nms.ncms.service.entity.MusicService;
import com.nms.ncms.web.util.AppConfig;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

@Stateless
public class MusicServiceBean extends AbstractProductBean<MusicCategory, Music> implements MusicService {

    private static final long serialVersionUID = 4317161615998592675L;
    private static final Logger LOGGER = Logger.getLogger(MusicServiceBean.class.getName());

    public MusicServiceBean() {
        super(Music.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected void onAfterPersist(Music entity) {
        super.onAfterPersist(entity);
        try {
            saveFile(entity.getMusicFile());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when save music file to the fileStore", ex);
            throw new EJBException("music.error.musicFile.storefile");
        }
    }

    @Override
    protected void onBeforeUpdate(Music entity) {
        super.onBeforeUpdate(entity);
        if (entity.getMusicFile() != null) {
            if (entity.getMusicFile().isHasFile()) {
                try {
                    saveFile(entity.getMusicFile());
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "Error when save music file to the fileStore", e);
            throw new EJBException("music.error.musicFile.storefile");
                }
            } else {
                em.remove(entity.getMusicFile());
                entity.setMusicFile(null);
            }
        }
    }

    @Override
    protected void onBeforeRemove(Music entity) {
        super.onBeforeRemove(entity);
        if (entity.getMusicFile() != null) {
            FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath() + entity.getMusicFile().getId()));
        }
    }

}
