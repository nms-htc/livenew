/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.PostCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PostCategoryService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PostCatBean extends AbstractManagedBean<PostCategory> {

    private static final long serialVersionUID = 4723089730520397755L;
    @EJB
    private PostCategoryService postCatService;

    @Override
    protected PostCategory initEntity() {
        return new PostCategory();
    }

    @Override
    protected BaseService<PostCategory> getBaseService() {
        return postCatService;
    }

}
