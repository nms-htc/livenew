/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 *
 * All rights reserved.
 */
package com.nms.ncms.web.controller;

import com.nms.ncms.entity.SlideContent;
import com.nms.ncms.service.entity.SlideContentService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Provide slide content for each request.
 *
 * @author Cuong
 * @since 26-11-2014
 */
@Named
@ViewScoped
public class SlideController implements Serializable {

    private static final long serialVersionUID = 6300545196768366986L;

    @EJB
    protected SlideContentService service;
    protected List<SlideContent> slideContents;

    public SlideController() {
    }

    public List<SlideContent> getSlideContents() {
        if (slideContents == null) {
            slideContents = service.findAll();
        }
        return slideContents;
    }
}
