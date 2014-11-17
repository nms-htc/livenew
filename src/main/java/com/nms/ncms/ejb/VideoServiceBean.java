/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Video;
import com.nms.ncms.entity.VideoCategory;
import com.nms.ncms.service.entity.VideoService;
import javax.ejb.Stateless;

@Stateless
public class VideoServiceBean extends AbstractProductBean<VideoCategory, Video> implements VideoService {

    private static final long serialVersionUID = -1210801531435011082L;

    public VideoServiceBean() {
        super(Video.class);
    }

}
