/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.MusicCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.MusicCategoryService;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MusicCatBean extends AbstractManagedBean<MusicCategory> {

    private static final long serialVersionUID = -8467672057280121204L;
    private static final Logger LOGGER = Logger.getLogger(MusicCatBean.class.getName());

    @EJB
    MusicCategoryService musicCatService;

    @Override
    protected MusicCategory initEntity() {
        return new MusicCategory();
    }

    @Override
    protected BaseService<MusicCategory> getBaseService() {
        return musicCatService;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
