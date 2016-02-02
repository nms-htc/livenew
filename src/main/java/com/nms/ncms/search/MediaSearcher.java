/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.search;

import com.nms.ncms.entity.MediaCategory;
import com.nms.ncms.web.util.StringUtil;
import java.util.Date;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author cuong
 */
@Named
@ViewScoped
public class MediaSearcher implements Searcher{

    private static final long serialVersionUID = -2157593066339597232L;
    
    private String code;
    private String title;
    private String fileTitle;
    private Date startDate;
    private Date endDate;
    private MediaCategory category;

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

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public MediaCategory getCategory() {
        return category;
    }

    public void setCategory(MediaCategory category) {
        this.category = category;
    }

    @Override
    public Map<String, Object> updateFilter(Map<String, Object> filter) {
        
        if (StringUtil.isNotNull(code)) {
            filter.put("code", code);
        }
        
        if (StringUtil.isNotNull(title)) {
            filter.put("title", filter);
        }
        
        if (StringUtil.isNotNull(fileTitle)) {
            
        }
        
        if (category != null) {
           filter.put("category", category);
        }
        
        if (startDate != null) {
            filter.put("startDate", startDate);
        }
        
        if (endDate != null) {
            filter.put("endDate", endDate);
        }
        
        return filter;
    }
    
}
