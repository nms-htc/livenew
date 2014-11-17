/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.Picture;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PictureService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("pictureConverter")
public class PictureConverter extends AbstractEntityConverter<Picture> {

    @EJB
    private PictureService pictureService;

    @Override
    protected BaseService<Picture> getBaseService() {
        return pictureService;
    }

    @Override
    protected Class<Picture> getEntityClass() {
        return Picture.class;
    }
    
}
