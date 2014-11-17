/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Music;
import com.nms.ncms.entity.MusicCategory;
import com.nms.ncms.service.entity.MusicService;
import javax.ejb.Stateless;

@Stateless
public class MusicServiceBean extends AbstractProductBean<MusicCategory, Music> implements MusicService {

    private static final long serialVersionUID = 4317161615998592675L;

    public MusicServiceBean() {
        super(Music.class);
    }

}
