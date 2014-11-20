/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.entity.Music;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.MusicService;
import com.nms.ncms.web.util.MessageUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class MusicBean extends AbstractProductBean<Music> {

    private static final long serialVersionUID = 6621902050457501495L;
    private static final Logger LOGGER = Logger.getLogger(MusicBean.class.getName());

    @EJB
    private MusicService musicService;

    @Override
    protected Music initEntity() {
        return new Music();
    }

    @Override
    protected BaseService<Music> getBaseService() {
        return musicService;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    public void handleContentUpload(FileUploadEvent event) {
        try {
            FileEntry fileEntry = current.getMusicFile();
            fileEntry.setTitle(event.getFile().getFileName());
            fileEntry.setContentType(event.getFile().getContentType());
            fileEntry.setFileSize(event.getFile().getSize());
            fileEntry.setInputStream(event.getFile().getInputstream());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when upload music file", ex);
            MessageUtil.addGlobalErrorMessage("unknow-error-when-music-file");
        }
    }
}
