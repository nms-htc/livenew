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
@DiscriminatorValue("Video")
@XmlRootElement
public class Video extends Product {

    private static final long serialVersionUID = -1171685629743520442L;

    @Url
    @Size(max = 500)
    @Column(name = "REVIEW_URL", length = 500)
    protected String videoUrl;

    public Video() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}
