/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author cuong
 */
@Entity
@Table(name = "NL_MEDIA")
public class Media extends BaseEntity{
    
    private static final long serialVersionUID = -1109842547759059006L;
    
    @NotNull
    @Size(max = 150)
    @Column(name = "CODE", length = 150, nullable = false)
    protected String code;
    
    @NotNull
    @Size(max = 250)
    @Column(name = "TITLE", length = 250, nullable = false)
    protected String title;
    
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected FileEntry contentFile = new FileEntry();
    
    @Column(name = "DOWNLOAD_COUNT")
    @Min(0)
    protected int downloadCount;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected User user;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected BaseCategory category;

    public Media() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FileEntry getContentFile() {
        return contentFile;
    }

    public void setContentFile(FileEntry contentFile) {
        this.contentFile = contentFile;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BaseCategory getCategory() {
        return category;
    }

    public void setCategory(BaseCategory category) {
        this.category = category;
    }    
}
