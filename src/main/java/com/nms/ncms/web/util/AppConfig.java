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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConfig implements Serializable {

    private static final long serialVersionUID = -8269216861708175445L;
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());
    public static final String FILE_PATH = "com/nms/cofig/config.properties";
    public static final String FILE_STORE_PATH_PROPERTY = "filestore.path";
    public static final String RTMP_URL = "rtmp.url";
    public static final String RTSP_URL = "rtsp.url";
    public static final String HTTP_M3U8_URL = "http.m3u8.url";
    public static final String HTTP_HDS_URL = "http.hds.url";
    
    public static Properties props = null;

    static {
        try {
            loadProperties();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "[AppConfig] Error when try loading the 'config.properties' file.", e);
        }
    }

    private static void loadProperties() throws IOException {
        InputStream is = AppConfig.class.getClassLoader().getResourceAsStream(FILE_PATH);
        props = new Properties();
        props.load(is);
    }

    public static String getFileStorePath() {
        String path = null;

        if (props != null) {
            path = props.getProperty(FILE_STORE_PATH_PROPERTY);
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

}
