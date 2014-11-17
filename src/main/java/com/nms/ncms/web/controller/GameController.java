/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller;

import com.nms.ncms.entity.Game;
import com.nms.ncms.entity.GameCategory;
import com.nms.ncms.service.entity.GameService;
import com.nms.ncms.service.entity.ProductService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class GameController extends AbstractProductController<Game, GameCategory> {

    private static final long serialVersionUID = 8560603447846518616L;

    @EJB
    private GameService gameService;

    @Override
    protected ProductService<Game, GameCategory> getProductService() {
        return gameService;
    }

}
