/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.Video;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.VideoService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoBean extends AbstractProductBean<Video> {

    private static final long serialVersionUID = -1514605817248554643L;
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

}
