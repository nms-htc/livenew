/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.service;

import com.nms.ncms.entity.Product;
import java.io.Serializable;
import javax.faces.application.FacesMessage;

public interface ChargingService extends Serializable {

    public FacesMessage chargeProduct(Product product);
}
