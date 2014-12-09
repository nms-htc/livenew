/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 *
 * All rights reserved.
 */
package com.nms.ncms.web.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConfig implements Serializable {

    private static final long serialVersionUID = -8269216861708175445L;
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());
    private static final String BASE_NAME = "com.nms.config.Ncms";
    public static final String FILE_STORE_PATH_PROPERTY = "filestore.path";
    public static final String RTMP_URL = "rtmp.url";
    public static final String RTSP_URL = "rtsp.url";
    public static final String HTTP_M3U8_URL = "http.m3u8.url";
    public static final String HTTP_HDS_URL = "http.hds.url";
    
    public static ResourceBundle bundle = null;

    static {
        try {
            loadBundle();
        } catch (MissingResourceException | NullPointerException e) {
            LOGGER.log(Level.WARNING, "[AppConfig] Error when try loading the '{0}' bundle.", BASE_NAME);
        }
    }

    public static void loadBundle() {
        bundle = ResourceBundle.getBundle(BASE_NAME);
    }

    public static String getFileStorePath() {
        String path = null;

        if (bundle != null) {
            path = bundle.getString(FILE_STORE_PATH_PROPERTY);
        }

        if (path == null) {
            path = getDefaultPath();
        }

        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }

        return path;
    }

    private static String getDefaultPath() {
        StringBuilder sb = new StringBuilder(System.getProperty("user.home"));
        sb.append(File.separator);
        sb.append(".ncms").append(File.separator);
        return sb.toString();
    }
    
    public static String getConfig(String key) {
        return bundle.getString(key);
    }

}
