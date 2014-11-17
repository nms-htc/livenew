/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.flows;

import java.io.Serializable;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;

//@ApplicationScoped
public class FlowFactory implements Serializable {
    private static final long serialVersionUID = -1661000665063975947L;
    
//    @Produces @FlowDefinition
    public Flow gameFlow(@FlowBuilderParameter FlowBuilder f) {
        String flowId = "gameManagement";
        f.id("", flowId);
        f.viewNode("list", "/admin/contents/game/list.xhtml").markAsStartNode();
        f.viewNode("edit", "/admin/contents/game/edit.xhtml");
        f.returnNode("returnFlow").fromOutcome("/admin/index.xhtml");
        return f.getFlow();
    }

}
