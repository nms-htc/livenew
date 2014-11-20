/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.entity.validation.Url;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Picture")
@XmlRootElement
public class Picture extends Product {

    private static final long serialVersionUID = 496486137872024515L;
    
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected FileEntry pictureFile = new FileEntry();
    
    public Picture() {
    }

    public FileEntry getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(FileEntry pictureFile) {
        this.pictureFile = pictureFile;
    }

}
