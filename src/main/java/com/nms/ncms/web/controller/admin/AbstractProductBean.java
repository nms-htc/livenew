/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.Product;
import com.nms.ncms.entity.User;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public abstract class AbstractProductBean<T extends Product> extends AbstractManagedBean<T> {

    private static final long serialVersionUID = -8389159376645995691L;

    @Inject
    protected User currentUser;

    @Override
    protected void onBeforePersist() {
        super.onBeforePersist();
        // add current user for createdUser
        ((Product) current).setUser(currentUser);
    }

    @Override
    protected void alterModelFilters(Map<String, Object> filters) {
        super.alterModelFilters(filters);
        boolean hasAdminRole = currentUser.getGroups().contains(User.Group.Admin);
        if (!hasAdminRole) {
            if (filters == null) filters = new HashMap<>();
            filters.put("cpCode", currentUser.getCode());
        }
    }
}
