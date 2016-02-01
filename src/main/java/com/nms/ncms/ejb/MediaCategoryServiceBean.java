/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.MediaCategory;
import com.nms.ncms.service.entity.MediaCategoryService;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author cuong
 */
@Stateless
public class MediaCategoryServiceBean extends AbstractFacadeBean<MediaCategory> implements MediaCategoryService{

    private static final long serialVersionUID = 2642477250775362701L;
    private static final Logger LOGGER = Logger.getLogger(MediaCategoryServiceBean.class.getName());

    public MediaCategoryServiceBean() {
        super(MediaCategory.class);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
}
