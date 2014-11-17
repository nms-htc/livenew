/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.VideoCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.VideoCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("videoCatConverter")
public class VideoCatConverter extends AbstractEntityConverter<VideoCategory> {

    @EJB
    private VideoCategoryService videoCatService;

    @Override
    protected BaseService<VideoCategory> getBaseService() {
        return videoCatService;
    }

}
