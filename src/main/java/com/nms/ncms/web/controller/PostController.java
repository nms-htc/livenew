/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller;

import com.nms.ncms.entity.Post;
import com.nms.ncms.entity.PostCategory;
import com.nms.ncms.service.entity.PostService;
import com.nms.ncms.service.entity.ProductService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PostController extends AbstractProductController<Post, PostCategory> {

    private static final long serialVersionUID = -4276685476416044041L;

    @EJB
    private PostService postService;

    @Override
    protected ProductService<Post, PostCategory> getProductService() {
        return postService;
    }

}
