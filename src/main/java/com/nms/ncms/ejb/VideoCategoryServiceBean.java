/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.VideoCategory;
import com.nms.ncms.service.entity.VideoCategoryService;
import javax.ejb.Stateless;

@Stateless
public class VideoCategoryServiceBean extends AbstractFacadeBean<VideoCategory> implements VideoCategoryService {

    private static final long serialVersionUID = -6276138478905188216L;

    public VideoCategoryServiceBean() {
        super(VideoCategory.class);
    }

}
