/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.service;

import java.io.Serializable;

/**
 *
 * @author CuongNT
 */
public interface MobileChecker extends Serializable {

    public boolean isVnmSubsriber();
    
    public String getPhoneNumber();
    
    public boolean isMobile();
    
    public boolean isIos();
    
    public boolean isAndroid();
    
    public boolean isWindowPhone();
    
    public int getOsCode();
    
}
