/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 * All rights reserved.
 */
package com.nms.ncms.service.entity;

import com.nms.ncms.entity.User;


public interface UserService extends BaseService<User>{
    public boolean hasAdminUser();
    
    public void updatePassword(User user);
    
    public User findByCode(String code);
    
    public User findByEmail(String email);
    
    public User findByUsername(String username);
    
    public User findByUsernameAndPassword(String username, String password);
}
