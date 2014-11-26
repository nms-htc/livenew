/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 *
 * All rights reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.entity.SlideContent;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.SlideContentService;
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
public class SlideContentBean extends AbstractManagedBean<SlideContent> {

    private static final long serialVersionUID = -8617374789252659295L;
    private static final Logger LOGGER = Logger.getLogger(SlideContentBean.class.getName());

    @EJB
    private SlideContentService service;

    @Override
    protected SlideContent initEntity() {
        return new SlideContent();
    }

    @Override
    protected BaseService<SlideContent> getBaseService() {
        return service;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    public void handleImageUpload(FileUploadEvent event) {
        try {
            FileEntry fileEntry = current.getImage();
            fileEntry.setTitle(event.getFile().getFileName());
            fileEntry.setContentType(event.getFile().getContentType());
            fileEntry.setFileSize(event.getFile().getSize());
            fileEntry.setInputStream(event.getFile().getInputstream());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error when upload Image file", ex);
            MessageUtil.addGlobalErrorMessage("unknow-error-when-upload-image-file");
        }
    }
}
