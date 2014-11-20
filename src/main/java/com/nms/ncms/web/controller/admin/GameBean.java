/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.entity.Game;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.GameService;
import com.nms.ncms.web.util.MessageUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class GameBean extends AbstractProductBean<Game> {

    private static final long serialVersionUID = -3525845741604139824L;
    private static final Logger LOGGER = Logger.getLogger(GameBean.class.getName());

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

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    public void handleContentUpload(FileUploadEvent event) {
        try {
            FileEntry fileEntry = current.getGameFile();
            fileEntry.setTitle(event.getFile().getFileName());
            fileEntry.setContentType(event.getFile().getContentType());
            fileEntry.setFileSize(event.getFile().getSize());
            fileEntry.setInputStream(event.getFile().getInputstream());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when upload game file", ex);
            MessageUtil.addGlobalErrorMessage("unknow-error-when-upload-game-file");
        }
    }
    
}
