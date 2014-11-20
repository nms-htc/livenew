/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Video")
@XmlRootElement
public class Video extends Product {

    private static final long serialVersionUID = -1171685629743520442L;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected FileEntry videoFile = new FileEntry();

    public Video() {
    }

    public FileEntry getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(FileEntry videoFile) {
        this.videoFile = videoFile;
    }

}
