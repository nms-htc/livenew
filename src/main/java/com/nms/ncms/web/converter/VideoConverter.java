/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.Video;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.VideoService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("videoConverter")
public class VideoConverter extends AbstractEntityConverter<Video> {
    
    @EJB
    private VideoService videoService;
    
    @Override
    protected BaseService<Video> getBaseService() {
        return videoService;
    }

    @Override
    protected Class<Video> getEntityClass() {
        return Video.class;
    }

}
