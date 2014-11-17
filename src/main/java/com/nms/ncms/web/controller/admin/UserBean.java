/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.User;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.service.entity.UserService;
import com.nms.ncms.web.util.JsfUtil;
import com.nms.ncms.web.util.MessageUtil;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserBean extends AbstractManagedBean<User> {

    private static final long serialVersionUID = 1335928742902659838L;

    @EJB
    private UserService userService;

    @Override
    protected User initEntity() {
        return new User();
    }

    @Override
    protected BaseService<User> getBaseService() {
        return userService;
    }

    public void updatePassword() {
        JsfUtil.processAction(u -> {
            userService.updatePassword(u);
        }, current, MessageUtil.REQUEST_SUCCESS_MESSAGE, MessageUtil.REQUEST_FAIL_MESSAGE);
    }
    
    public String saveSystemUser() {
        try {
            current.setGroups(Arrays.asList(User.Group.Admin));
            userService.persist(current);
            MessageUtil.addGlobalInfoMessage(MessageUtil.REQUEST_SUCCESS_MESSAGE);
            return "/admin/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.handleException(e, MessageUtil.REQUEST_FAIL_MESSAGE);
        }
        return null;
    }

    public User.Group[] getUserGroups() {
        return User.Group.values();
    }
}
