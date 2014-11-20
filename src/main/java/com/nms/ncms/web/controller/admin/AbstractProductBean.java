/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.entity.Product;
import com.nms.ncms.entity.User;
import com.nms.ncms.web.util.MessageUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;

public abstract class AbstractProductBean<T extends Product> extends AbstractManagedBean<T> {

    private static final long serialVersionUID = -8389159376645995691L;
    
    @Inject
    protected User currentUser;

    @Override
    protected void onBeforePersist() {
        super.onBeforePersist();
        // add current user for createdUser
        ((Product) current).setUser(currentUser);
        // remove empty file entry
    }

    @Override
    protected void onBeforeUpdate() {
        super.onBeforeUpdate();
    }

    @Override
    protected void alterModelFilters(Map<String, Object> filters) {
        super.alterModelFilters(filters);
        boolean hasAdminRole = currentUser.getGroups().contains(User.Group.Admin);
        if (!hasAdminRole) {
            if (filters == null) filters = new HashMap<>();
            filters.put("cpCode", currentUser.getCode());
        }
    }
    
    public void handleThumbnailUpload(FileUploadEvent event) {
        try {
            FileEntry fileEntry = current.getThumbFile();
            fileEntry.setTitle(event.getFile().getFileName());
            fileEntry.setContentType(event.getFile().getContentType());
            fileEntry.setFileSize(event.getFile().getSize());
            fileEntry.setInputStream(event.getFile().getInputstream());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when upload thumbnail file", ex);
            MessageUtil.addGlobalErrorMessage("unknow-error-when-upload-thumbnail-file");
        }
    }
}
