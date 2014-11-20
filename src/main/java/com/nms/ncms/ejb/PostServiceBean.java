/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Post;
import com.nms.ncms.entity.PostCategory;
import com.nms.ncms.service.entity.PostService;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class PostServiceBean extends AbstractProductBean<PostCategory, Post> implements PostService {

    private static final long serialVersionUID = -857383942536246354L;
    private static final Logger LOGGER = Logger.getLogger(PostServiceBean.class.getName());

    public PostServiceBean() {
        super(Post.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
