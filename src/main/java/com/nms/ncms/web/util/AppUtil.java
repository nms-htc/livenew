/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Cuong
 */
public class AppUtil {

    public static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
        }

        return null;
    }

    public static String encodeSHA256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest);
    }

    public static String buildStreamingIdentifier(String filePath) {
        String extention = FilenameUtils.getExtension(filePath).toLowerCase();
        String streamingIdentifier = null;
        filePath = filePath.replace('\\','/');

        switch (extention) {
            case "mp4":
                streamingIdentifier = "mp4:" + filePath;
                break;
            case "m4v":
                streamingIdentifier = "mp4:" + filePath;
                break;
            case "mov":
                streamingIdentifier = "mp4:" + filePath;
                break;
            case "f4v":
                streamingIdentifier = "mp4:" + filePath;
                break;
            case "flv":
                streamingIdentifier = "flv:" + filePath;
                break;
            case "mp3":
                streamingIdentifier = "mp3:" + filePath;
                break;
            case "m4a":
                streamingIdentifier = "mp4:" + filePath;
                break;
            case "f4a":
                streamingIdentifier = "mp4:" + filePath;
                break;
            case "aac":
                streamingIdentifier = "mp4:" + filePath;
                break;
        }

        return streamingIdentifier;
    }
}
