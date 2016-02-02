/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Game;
import com.nms.ncms.entity.GameCategory;
import com.nms.ncms.service.entity.GameService;
import com.nms.ncms.web.util.AppConfig;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import org.apache.commons.io.FileUtils;

public class GameServiceBean extends AbstractProductBean<GameCategory, Game> implements GameService {

    private static final long serialVersionUID = 4734878632483200574L;
    private static final Logger LOGGER = Logger.getLogger(GameServiceBean.class.getName());

    public GameServiceBean() {
        super(Game.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void onAfterPersist(Game entity) {
        super.onAfterPersist(entity);
        try {
            saveFile(entity.getGameFile());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when save game file to the fileStore", ex);
            throw new EJBException("game.error.gameFile.storefile");
        }
    }

    @Override
    protected void onBeforeUpdate(Game entity) {
        super.onBeforeUpdate(entity);
        if (entity.getGameFile() != null) {
            if (entity.getGameFile().isHasFile()) {
                try {
                    saveFile(entity.getGameFile());
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "Error when save game file to the fileStore", e);
                    throw new EJBException("game.error.gameFile.storefile");
                }
            } else {
                em.remove(entity.getGameFile());
                entity.setGameFile(null);
            }
        }
    }

    @Override
    protected void onBeforeRemove(Game entity) {
        super.onBeforeRemove(entity);
        if (entity.getGameFile() != null) {
            FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath() + entity.getGameFile().getId()));
        }
    }

}
