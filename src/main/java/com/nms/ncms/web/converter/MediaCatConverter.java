/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.ejb.MediaCategoryServiceBean;
import com.nms.ncms.entity.MediaCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.MediaCategoryService;
import com.nms.ncms.web.util.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author cuong
 */
@FacesConverter("mediaCatConverter")
public class MediaCatConverter extends AbstractEntityConverter<MediaCategory>{
    
    private MediaCategoryService mediaCategoryService = EJB.lookup(MediaCategoryServiceBean.class);

    @Override
    protected BaseService<MediaCategory> getBaseService() {
        return mediaCategoryService;
    }

    @Override
    protected Class<MediaCategory> getEntityClass() {
        return MediaCategory.class;
    }
    
}
