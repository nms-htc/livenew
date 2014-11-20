/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.entity.Video;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.VideoService;
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
public class VideoBean extends AbstractProductBean<Video> {

    private static final long serialVersionUID = -1514605817248554643L;
    private static final Logger LOGGER = Logger.getLogger(VideoBean.class.getName());

    @EJB
    private VideoService videoService;

    @Override
    protected Video initEntity() {
        return new Video();
    }

    @Override
    protected BaseService<Video> getBaseService() {
        return videoService;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    public void handleContentUpload(FileUploadEvent event) {
        try {
            FileEntry fileEntry = current.getVideoFile();
            fileEntry.setTitle(event.getFile().getFileName());
            fileEntry.setContentType(event.getFile().getContentType());
            fileEntry.setFileSize(event.getFile().getSize());
            fileEntry.setInputStream(event.getFile().getInputstream());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when upload video file", ex);
            MessageUtil.addGlobalErrorMessage("unknow-error-when-video-file");
        }
    }
}
