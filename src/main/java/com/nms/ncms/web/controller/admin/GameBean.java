/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.Game;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.GameService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class GameBean extends AbstractProductBean<Game> {

    private static final long serialVersionUID = -3525845741604139824L;

    @EJB
    private GameService gameService;

    @Override
    protected Game initEntity() {
        return new Game();
    }

    @Override
    protected BaseService<Game> getBaseService() {
        return gameService;
    }

    public Game.Flatform[] getFlatforms() {
        return Game.Flatform.values();
    }
}
