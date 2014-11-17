/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller;

import com.nms.ncms.entity.Picture;
import com.nms.ncms.entity.PictureCategory;
import com.nms.ncms.service.entity.PictureService;
import com.nms.ncms.service.entity.ProductService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PictureController extends AbstractProductController<Picture, PictureCategory> {

    private static final long serialVersionUID = 6716553296353367211L;

    @EJB
    private PictureService pictureService;

    @Override
    protected ProductService<Picture, PictureCategory> getProductService() {
        return pictureService;
    }

}
