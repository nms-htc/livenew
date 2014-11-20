/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.GameCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.GameCategoryService;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class GameCatBean extends AbstractManagedBean<GameCategory> {

    private static final long serialVersionUID = -5858193832490589251L;
    private static final Logger LOGGER = Logger.getLogger(GameCatBean.class.getName());

    @EJB
    private GameCategoryService gameCatService;

    @Override
    protected GameCategory initEntity() {
        return new GameCategory();
    }

    @Override
    protected BaseService<GameCategory> getBaseService() {
        return gameCatService;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
