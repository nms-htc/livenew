/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.User;
import com.nms.ncms.service.entity.UserService;
import com.nms.ncms.web.util.MessageUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Named
@SessionScoped
public class AuthenticateBean implements Serializable {

    private static final long serialVersionUID = 3287793346277920082L;
    private static final Logger LOGGER = Logger.getLogger(AuthenticateBean.class.getName());

    @NotNull
    private String username;
    @NotNull
    private String password;
    private String originalURL;
    private User currentUser;
    
    @EJB
    private UserService service;

    public AuthenticateBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/admin/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    public void login() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            User user = service.findByUsernameAndPassword(username, password);
            externalContext.getSessionMap().put("currentUser", user);
            currentUser = user;
            request.login(username, password);
            externalContext.redirect(originalURL);
        } catch (ServletException e) {
            LOGGER.log(Level.INFO, "Error when login to system with username = {0}, password = {1}, and errorMessage :",
                    new Object[]{
                        username,
                        password,
                        e.toString()
                    });
            MessageUtil.addGlobalErrorMessage("username-or-password-incorrect");
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/admin/login.xhtml");
    }

    /* Getters and Setters */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Produces
    public User getCurrentUser() {
        return currentUser;
    }
}
