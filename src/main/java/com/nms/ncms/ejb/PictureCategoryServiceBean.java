/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.PictureCategory;
import com.nms.ncms.service.entity.PictureCategoryService;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class PictureCategoryServiceBean extends AbstractFacadeBean<PictureCategory> implements PictureCategoryService {

    private static final long serialVersionUID = -2658343686363511314L;
    private static final Logger LOGGER = Logger.getLogger(PictureCategoryServiceBean.class.getName());

    public PictureCategoryServiceBean() {
        super(PictureCategory.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
