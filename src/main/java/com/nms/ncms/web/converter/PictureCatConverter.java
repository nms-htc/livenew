/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.PictureCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PictureCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("pictureCatConverter")
public class PictureCatConverter extends AbstractEntityConverter<PictureCategory> {

    @EJB
    private PictureCategoryService pictureCatService;

    @Override
    protected BaseService<PictureCategory> getBaseService() {
        return pictureCatService;
    }
}
