/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.entity.Picture;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PictureService;
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
public class PictureBean extends AbstractProductBean<Picture> {

    private static final long serialVersionUID = 2219397982611609176L;
    private static final Logger LOGGER = Logger.getLogger(PictureBean.class.getName());

    @EJB
    private PictureService pictureService;

    @Override
    protected Picture initEntity() {
        return new Picture();
    }

    @Override
    protected BaseService<Picture> getBaseService() {
        return pictureService;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    public void handleContentUpload(FileUploadEvent event) {
        try {
            FileEntry fileEntry = current.getPictureFile();
            fileEntry.setTitle(event.getFile().getFileName());
            fileEntry.setContentType(event.getFile().getContentType());
            fileEntry.setFileSize(event.getFile().getSize());
            fileEntry.setInputStream(event.getFile().getInputstream());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when upload picture file", ex);
            MessageUtil.addGlobalErrorMessage("unknow-error-when-picture-file");
        }
    }
}
