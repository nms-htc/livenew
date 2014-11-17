/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.Music;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.MusicService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MusicBean extends AbstractProductBean<Music> {

    private static final long serialVersionUID = 6621902050457501495L;

    @EJB
    private MusicService musicService;

    @Override
    protected Music initEntity() {
        return new Music();
    }

    @Override
    protected BaseService<Music> getBaseService() {
        return musicService;
    }

}
