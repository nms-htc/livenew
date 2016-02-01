/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.Media;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.MediaService;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author cuong
 */
@Named
@ApplicationScoped
public class MediaConverter extends AbstractEntityConverter<Media>{
    
    @EJB
    private MediaService mediaService;

    @Override
    protected BaseService<Media> getBaseService() {
        return mediaService;
    }

    @Override
    protected Class<Media> getEntityClass() {
        return Media.class;
    }
    
}
