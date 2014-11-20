/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.VideoCategory;
import com.nms.ncms.service.entity.VideoCategoryService;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class VideoCategoryServiceBean extends AbstractFacadeBean<VideoCategory> implements VideoCategoryService {

    private static final long serialVersionUID = -6276138478905188216L;
    private static final Logger LOGGER = Logger.getLogger(VideoCategoryServiceBean.class.getName());

    public VideoCategoryServiceBean() {
        super(VideoCategory.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
