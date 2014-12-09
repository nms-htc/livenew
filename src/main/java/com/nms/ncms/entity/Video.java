/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.web.util.AppConfig;
import com.nms.ncms.web.util.AppUtil;
import java.text.Format;
import java.text.MessageFormat;
import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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
    
    @Transient
    public String getRtmpUrl() {
        if (videoFile != null && videoFile.filePath != null) {
            String si = AppUtil.buildStreamingIdentifier(videoFile.getFilePath());
            return MessageFormat.format(AppConfig.getConfig(AppConfig.RTMP_URL), si);
        }
        return null;
    }
    
    @Transient
    public String getM3u8Url() {
        if (videoFile != null && videoFile.filePath != null) {
            String si = AppUtil.buildStreamingIdentifier(videoFile.getFilePath());
            return MessageFormat.format(AppConfig.getConfig(AppConfig.HTTP_M3U8_URL), si);
        }
        return null;
    }
    
    @Transient
    public String getRtspUrl() {
        if (videoFile != null && videoFile.filePath != null) {
            String si = AppUtil.buildStreamingIdentifier(videoFile.getFilePath());
            return MessageFormat.format(AppConfig.getConfig(AppConfig.RTSP_URL), si);
        }
        return null;
    }
}
