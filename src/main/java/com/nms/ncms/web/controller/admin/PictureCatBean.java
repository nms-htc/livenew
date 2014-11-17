/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.PictureCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PictureCategoryService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PictureCatBean extends AbstractManagedBean<PictureCategory> {

    private static final long serialVersionUID = -3400045537994131927L;
    @EJB
    private PictureCategoryService pictureCatService;

    @Override
    protected PictureCategory initEntity() {
        return new PictureCategory();
    }

    @Override
    protected BaseService<PictureCategory> getBaseService() {
        return pictureCatService;
    }

}
