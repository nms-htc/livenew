/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.entity.validation.Url;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Picture")
@XmlRootElement
public class Picture extends Product {

    private static final long serialVersionUID = 496486137872024515L;
    
    @Url
    @Size(max = 500)
    @Column(name="REVIEW_URL", length = 500)
    protected String reviewUrl;
    
    public Picture() {
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }
    
}
