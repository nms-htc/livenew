/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Picture")
@XmlRootElement
public class PictureCategory extends BaseCategory {

    private static final long serialVersionUID = -5809524831295842691L;

}
