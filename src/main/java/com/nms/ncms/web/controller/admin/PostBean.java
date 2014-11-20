/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.Post;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PostService;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PostBean extends AbstractProductBean<Post> {

    private static final long serialVersionUID = 1234824483877206931L;
    private static final Logger LOGGER = Logger.getLogger(PostBean.class.getName());

    @EJB
    private PostService postService;

    @Override
    protected Post initEntity() {
        return new Post();
    }

    @Override
    protected BaseService<Post> getBaseService() {
        return postService;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
