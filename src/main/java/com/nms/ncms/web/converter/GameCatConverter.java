/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.GameCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.GameCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "gameCatConverter")
public class GameCatConverter extends AbstractEntityConverter<GameCategory> {
    
    @EJB
    private GameCategoryService gameCategoryService;

    @Override
    protected BaseService<GameCategory> getBaseService() {
        return gameCategoryService;
    }

    @Override
    protected Class<GameCategory> getEntityClass() {
        return GameCategory.class;
    }
}
