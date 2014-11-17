/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.MusicCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.MusicCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("musicCatConverter")
public class MusicCatConverter extends AbstractEntityConverter<MusicCategory> {

    @EJB
    private MusicCategoryService musicCatService;

    @Override
    protected BaseService<MusicCategory> getBaseService() {
        return musicCatService;
    }
}
