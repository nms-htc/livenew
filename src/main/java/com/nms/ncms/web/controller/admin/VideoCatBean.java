/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.VideoCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.VideoCategoryService;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoCatBean extends AbstractManagedBean<VideoCategory> {

    private static final long serialVersionUID = -4446171873229250459L;
    private static final Logger LOGGER = Logger.getLogger(VideoCatBean.class.getName());

    @EJB
    private VideoCategoryService videoCatService;

    @Override
    protected VideoCategory initEntity() {
        return new VideoCategory();
    }

    @Override
    protected BaseService<VideoCategory> getBaseService() {
        return videoCatService;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
