/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.PostCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PostCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("postCatConverter")
public class PostCatConverter extends AbstractEntityConverter<PostCategory> {

    @EJB
    private PostCategoryService postCatService;

    @Override
    protected BaseService<PostCategory> getBaseService() {
        return postCatService;
    }

}
