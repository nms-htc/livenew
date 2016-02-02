/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.GameCategory;
import com.nms.ncms.service.entity.GameCategoryService;
import java.util.logging.Logger;

public class GameCategoryServiceBean extends AbstractFacadeBean<GameCategory> implements GameCategoryService {

    private static final long serialVersionUID = -407380901258123522L;
    private static final Logger LOGGER = Logger.getLogger(GameCategoryServiceBean.class.getName());

    public GameCategoryServiceBean() {
        super(GameCategory.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
}
