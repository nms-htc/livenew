/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.Picture;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PictureService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PictureBean extends AbstractProductBean<Picture> {

    private static final long serialVersionUID = 2219397982611609176L;
    @EJB
    private PictureService pictureService;

    @Override
    protected Picture initEntity() {
        return new Picture();
    }

    @Override
    protected BaseService<Picture> getBaseService() {
        return pictureService;
    }

}
