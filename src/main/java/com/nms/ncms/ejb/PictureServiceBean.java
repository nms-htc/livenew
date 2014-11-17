/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Picture;
import com.nms.ncms.entity.PictureCategory;
import com.nms.ncms.service.entity.PictureService;
import javax.ejb.Stateless;

@Stateless
public class PictureServiceBean extends AbstractProductBean<PictureCategory, Picture> implements PictureService {

    private static final long serialVersionUID = -1752966518637682180L;

    public PictureServiceBean() {
        super(Picture.class);
    }

}
