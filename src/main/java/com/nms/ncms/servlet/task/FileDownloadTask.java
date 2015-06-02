/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 *
 * All rights reserved.
 */
package com.nms.ncms.servlet.task;

import com.nms.ncms.entity.FileEntry;
import com.nms.ncms.service.entity.FileService;
import com.nms.ncms.web.util.AppConfig;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

public class FileDownloadTask implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(FileDownloadTask.class.getName());

    private final FileService fileService;
    private final Long fileId;
    private final AsyncContext asyncContext;
    private final boolean download;

    public FileDownloadTask(FileService fileService, Long fileId, boolean download, AsyncContext asyncContext) {
        this.fileService = fileService;
        this.fileId = fileId;
        this.asyncContext = asyncContext;
        this.download = download;
    }

    public void sendResponseError(HttpServletResponse response, int error) {
        try {
            response.sendError(error);
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Error when send response error to client: errorCode = " + error, ex);
        }
    }

    @Override
    public void run() {
        final HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

        // get file information
        FileEntry fileEntry = null;
        try {
            fileEntry = fileService.find(fileId);
        } catch (Exception e) { // throw 400
            LOGGER.log(Level.SEVERE, "Error when get file metadata from db: fileId = " + fileId, e);
            sendResponseError(response, HttpServletResponse.SC_BAD_REQUEST);
        }

        if (fileEntry != null) {
            if (fileEntry.isUpload() && fileEntry.getFilePath() != null && !""
                    .equals(fileEntry.getFilePath().trim())) { // validate file entry has file.

                // write file to response
                File file = new File(AppConfig.getFileStorePath() + fileEntry.getFilePath());
                try (OutputStream out = response.getOutputStream()) {
                    if (file.exists()) {
                        // prepare response information
                        response.setContentLength((int) fileEntry.getFileSize());
                        response.setContentType(fileEntry.getContentType());
                        response.setHeader("Content-Disposition", (download ? "attachment" : "inline")
                                + ";filename=\"" + fileEntry.getTitle() + "\"");
                        FileUtils.copyFile(file, out);
                        out.flush();
                    } else {
                        sendResponseError(response, HttpServletResponse.SC_BAD_REQUEST);
                    }
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error when download file: fileId =" + fileId, e);
                    sendResponseError(response, HttpServletResponse.SC_BAD_REQUEST);
                }

            } else { // throw 400
                LOGGER.log(Level.SEVERE, "File entry cannot has attack file: fileId = {0}", fileId);
                sendResponseError(response, HttpServletResponse.SC_BAD_REQUEST);
            }
        } else { // throw 404
            sendResponseError(response, HttpServletResponse.SC_NOT_FOUND);
        }
        // complete async context
        asyncContext.complete();
    }
}
