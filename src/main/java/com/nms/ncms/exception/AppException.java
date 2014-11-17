/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.exception;

/**
 *
 * @author Cuong
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -4808588009136179451L;
    protected ErrorInfo errorInfo;

    public AppException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public AppException(ErrorInfo errorInfo, String message) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public AppException(ErrorInfo errorInfo, Throwable cause) {
        super(cause);
        this.errorInfo = errorInfo;
    }

    public AppException(ErrorInfo errorInfo, String message, Throwable cause) {
        super(message, cause);
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
