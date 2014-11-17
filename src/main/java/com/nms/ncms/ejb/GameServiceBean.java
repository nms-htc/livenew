/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Game;
import com.nms.ncms.entity.GameCategory;
import com.nms.ncms.service.entity.GameService;
import javax.ejb.Stateless;

@Stateless
public class GameServiceBean extends AbstractProductBean<GameCategory, Game> implements GameService {

    private static final long serialVersionUID = 4734878632483200574L;

    public GameServiceBean() {
        super(Game.class);
    }

}
