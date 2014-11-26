/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 *
 * All rights reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.entity.validation.Url;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "NCMS_SLIDECONTENT")
@XmlRootElement
public class SlideContent extends BaseEntity {

    private static final long serialVersionUID = 3918665632383797138L;

    @NotNull
    @Size(max = 75)
    @Column(name = "TITLE", length = 75)
    protected String title;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected FileEntry image = new FileEntry();

    @Url
    @NotNull
    @Column(name = "URL", length = 100)
    @Size(max = 100)
    protected String url;
    
    @Min(0)
    @Max(100)
    @Column(name = "POSITION")
    protected short position;

    public SlideContent() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FileEntry getImage() {
        return image;
    }

    public void setImage(FileEntry image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public short getPosition() {
        return position;
    }

    public void setPosition(short position) {
        this.position = position;
    }
    
}
