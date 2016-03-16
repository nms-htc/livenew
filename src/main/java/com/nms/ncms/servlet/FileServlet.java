/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 *
 * All rights reserved.
 */
package com.nms.ncms.servlet;

import com.nms.ncms.service.entity.FileService;
import com.nms.ncms.servlet.task.FileDownloadTask;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FileServlet", urlPatterns = {"/file/*"}, asyncSupported = true)
public class FileServlet extends HttpServlet {

    private static final long serialVersionUID = -7386823170154173756L;
    private static final Logger LOGGER = Logger.getLogger(FileServlet.class.getName());

    @EJB
    private FileService fileService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long fileId = null;
        boolean download = false;

        String pathInfor = request.getPathInfo();
        if (pathInfor != null) {
            
            String[] paths = pathInfor.split("/");
            //file endtry id
            String id = new String(Base64.getUrlDecoder().decode(paths[1]), "utf-8");
            // validate fileId
            try {
                fileId = Long.parseLong(id);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            
            if (paths.length > 2) {
                download = true;
            }
           
        } else {
            // file entry id
            String id = request.getParameter("id");
            // action type (1 => download, default => inline)
            String act = request.getParameter("act");

            // validate fileId
            try {
                fileId = Long.parseLong(id);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            // check download param
            if (act != null && "1".equals(act)) {
                download = true;
            }
        }

        AsyncContext asyncContext = request.startAsync();
        FileDownloadTask downloadTask = new FileDownloadTask(fileService, fileId, download, asyncContext);
        asyncContext.start(downloadTask);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
