/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 *
 * All rights reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.service.entity.FileService;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class FileServiceBean extends AbstractFacadeBean<FileEntry> implements FileService {

    private static final long serialVersionUID = -3338951037097650629L;
    private static final Logger LOGGER = Logger.getLogger(FileServiceBean.class.getName());

    public FileServiceBean() {
        super(FileEntry.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
