/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.service.entity;

import com.nms.ncms.entity.BaseCategory;
import com.nms.ncms.entity.Product;
import com.nms.ncms.service.MobileChecker;
import java.util.List;

public interface ProductService<E extends Product, C extends BaseCategory> extends BaseService<E> {

    public List<E> findByCat(int start, int range, C category, String orderField, boolean asc);

    public List<E> findExcludeCurrent(int start, int range, E product);
    
    public List<E> getPromotions(int start, int range,C category, String orderField, boolean asc);
    
    public List<E> getFrees(int start, int range,C category, String orderField, boolean asc);
    
    public int countPromotions(C category);
    
    public int countFrees(C category);

    public int countByCat(C category);
    
    public void increaseViewCount(E product);
    
    public void increateDownloadCount(E product);
    
    public List<E> search(String keywords, C category, MobileChecker mobileChecker, 
            String orderField, boolean asc, int start, int ranger);
    
    public int count(String keywords, C category, MobileChecker mobileChecker);
}
