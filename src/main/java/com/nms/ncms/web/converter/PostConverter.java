/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.Post;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.PostService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("postConverter")
public class PostConverter extends AbstractEntityConverter<Post> {

    @EJB
    private PostService postService;

    @Override
    protected BaseService<Post> getBaseService() {
        return postService;
    }

    @Override
    protected Class<Post> getEntityClass() {
        return Post.class;
    }

}
