/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.MediaCategory;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.MediaCategoryService;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author cuong
 */
@Named
@ViewScoped
public class MediaCatBean extends AbstractManagedBean<MediaCategory>{

    private static final long serialVersionUID = -8064429763652589370L;
    private static final Logger LOGGER = Logger.getLogger(MediaCatBean.class.getName());
    
    @EJB
    private MediaCategoryService mediaCategoryServiceBean;

    @Override
    protected MediaCategory initEntity() {
        return new MediaCategory();
    }

    @Override
    protected BaseService<MediaCategory> getBaseService() {
        return mediaCategoryServiceBean;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
}
