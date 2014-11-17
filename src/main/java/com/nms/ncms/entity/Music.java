/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.entity.validation.Url;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Music")
@XmlRootElement
public class Music extends Product {

    private static final long serialVersionUID = 5149591841140789090L;

    @Url
    @Size(max = 500)
    @Column(name = "REVIEW_URL", length = 500)
    protected String musicUrl;

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

    public Music() {
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
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
}
