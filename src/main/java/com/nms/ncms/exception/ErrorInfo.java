/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorInfo {

    public static List<ErrorInfo> errorList = new ArrayList<>();

    public static final ErrorInfo UNKNOW_ERROR = new ErrorInfo("UNKNOW_ERROR", "Unknow error", Serverity.ERROR, Origin.INTERNAL);
    public static final ErrorInfo DIGEST_MESSAGE_ERROR = new ErrorInfo("DIGEST_MESSAGE_ERROR", "Error when digist object", Serverity.ERROR, Origin.INTERNAL);
    public static final ErrorInfo UNIQUE_CONSTRAINT_ERROR = new ErrorInfo("UNIQUE_CONSTRAINT_ERROR", "Has more than one recored in db with same uniqui value", Serverity.ERROR, Origin.SERVICE);

    public enum Serverity {

        DEBUG, INFO, WARNING, ERROR, FATAL;
    }

    public enum Origin {

        CLIENT, SERVICE, INTERNAL;
    }

    protected String errorId = null;
    protected String description = null;
    protected Serverity serverity = Serverity.INFO;
    protected Origin origin = Origin.INTERNAL;

    public ErrorInfo(String errorId, String description, Serverity serverity, Origin origin) {
        this.errorId = errorId;
        this.description = description;
        this.serverity = serverity;
        this.origin = origin;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Serverity getServerity() {
        return serverity;
    }

    public void setServerity(Serverity serverity) {
        this.serverity = serverity;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

}
