/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.entity.validation.Url;
import com.nms.ncms.web.util.AppConfig;
import com.nms.ncms.web.util.AppUtil;
import java.text.MessageFormat;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Music")
@XmlRootElement
public class Music extends Product {

    private static final long serialVersionUID = 5149591841140789090L;

    @Size(max = 150)
    @Column(name = "SINGER", length = 150)
    protected String singer;

    @Size(max = 150)
    @Column(name = "CREATOR", length = 150)
    protected String creator;

    @Size(max = 150)
    @Column(name = "ALBUM", length = 150)
    protected String album;

    @Url
    @Size(max = 500)
    @Column(name = "ALBUM_THUMB_URL", length = 500)
    protected String albumThumbUrl;

    @Lob
    @Column(name = "LYRIC")
    protected String lyric;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected FileEntry musicFile = new FileEntry();

    public Music() {
    }

    public FileEntry getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(FileEntry musicFile) {
        this.musicFile = musicFile;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumThumbUrl() {
        return albumThumbUrl;
    }

    public void setAlbumThumbUrl(String albumThumbUrl) {
        this.albumThumbUrl = albumThumbUrl;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
    
    @Transient
    public String getRtmpUrl() {
        if (musicFile != null && musicFile.filePath != null) {
            String si = AppUtil.buildStreamingIdentifier(musicFile.getFilePath());
            return MessageFormat.format(AppConfig.getConfig(AppConfig.RTMP_URL), si);
        }
        return null;
    }
    
    @Transient
    public String getM3u8Url() {
        if (musicFile != null && musicFile.filePath != null) {
            String si = AppUtil.buildStreamingIdentifier(musicFile.getFilePath());
            return MessageFormat.format(AppConfig.getConfig(AppConfig.HTTP_M3U8_URL), si);
        }
        return null;
    }
    
    @Transient
    public String getRtspUrl() {
        if (musicFile != null && musicFile.filePath != null) {
            String si = AppUtil.buildStreamingIdentifier(musicFile.getFilePath());
            return MessageFormat.format(AppConfig.getConfig(AppConfig.RTSP_URL), si);
        }
        return null;
    }
}
