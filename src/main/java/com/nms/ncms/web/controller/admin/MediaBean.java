/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.entity.Media;
import com.nms.ncms.entity.User;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.MediaService;
import com.nms.ncms.web.util.MessageUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author cuong
 */
@Named
@ViewScoped
public class MediaBean extends AbstractManagedBean<Media>{

    private static final long serialVersionUID = -5556013888215767609L;
    private static final Logger LOGGER = Logger.getLogger(MediaBean.class.getName());
    
    @EJB
    private MediaService mediaServiceBean;
    
    @Inject
    protected User currentUser;

    @Override
    protected Media initEntity() {
        return new Media();
    }

    @Override
    protected BaseService<Media> getBaseService() {
        return mediaServiceBean;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected void onBeforePersist() {
        super.onBeforePersist();
        // add current user for createdUser
        ((Media) current).setUser(currentUser);
        // remove empty file entry
    }
    
    public void handleContentUpload(FileUploadEvent event) {
        try {
            FileEntry fileEntry = current.getContentFile();
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
