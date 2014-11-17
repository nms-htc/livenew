/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.Game;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.GameService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("gameConverter")
public class GameConverter extends AbstractEntityConverter<Game> {

    @EJB
    private GameService gameService;

    @Override
    protected BaseService<Game> getBaseService() {
        return gameService;
    }

    @Override
    protected Class<Game> getEntityClass() {
        return Game.class;
    }
}
