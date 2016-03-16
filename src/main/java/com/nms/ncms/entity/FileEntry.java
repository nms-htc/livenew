package com.nms.ncms.entity;

import com.nms.ncms.entity.validation.Url;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Nguyen Trong Cuong
 * @since 09/10/2014
 * @version 1.0
 */
@Entity
@Table(name = "NL_FILE")
@XmlRootElement
public class FileEntry extends BaseEntity {

    private static final long serialVersionUID = -2874867851464343753L;

    @Column(name = "TITLE", length = 100)
    protected String title;

    @Column(name = "CONTENTTYPE", length = 50)
    protected String contentType;

    @Column(name = "FILESIZE")
    protected long fileSize;

    @Column(name = "FILEPATH", length = 250)
    protected String filePath;

    @Url
    @Column(name = "URL", length = 250)
    protected String url;

    @Column(name = "UPLOAD")
    protected boolean upload = true;

    @Transient
    @XmlTransient
    protected InputStream inputStream;
    
    public FileEntry() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @XmlTransient
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @XmlTransient
    public boolean isUpload() {
        return upload;
    }

    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    public String getURL() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (isUpload()) {
            return externalContext.getRequestScheme() + "://"
                    + externalContext.getRequestServerName() + ":"
                    + externalContext.getRequestServerPort()
                    + externalContext.getRequestContextPath() + "/file?id=" + id;
        }
        return url;
    }

    public String getDownloadURL() {
        if (this.isUpload()) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            String downloadUrl = externalContext.getRequestScheme()
                    + "://" + externalContext.getRequestServerName()
                    + ":" + externalContext.getRequestServerPort()
                    + externalContext.getRequestContextPath() 
                    + "/file";
            
            try {
                String encodedId = Base64.getUrlEncoder().encodeToString(String.valueOf(id).getBytes("utf-8"));
                downloadUrl += "/" + encodedId + "/" + UUID.randomUUID() + "/" + UUID.randomUUID();
                downloadUrl += "?sub_aff1=" + Base64.getUrlEncoder().encodeToString(UUID.randomUUID().toString().getBytes("utf-8"));
                downloadUrl += "&sub_aff2=" + Base64.getUrlEncoder().encodeToString(UUID.randomUUID().toString().getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                // Nothing to do.
                downloadUrl += "?id=" + id + "&act=1";
            }
            
            return downloadUrl;
        }
        return null;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean isHasFile() {
        if (upload) {
            return inputStream != null;
        } else {
            return url != null && !"".equals(url.trim());
        }
    }

    @Override
    public String toString() {
        return "File{" + "title=" + title + ", contentType=" + contentType
                + ", fileSize=" + fileSize + ", filePath=" + filePath + '}';
    }

}
