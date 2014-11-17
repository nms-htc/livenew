/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.entity.validation.Email;
import com.nms.ncms.web.util.StringUtil;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "NCMS_USER")
@XmlRootElement
public class User extends BaseEntity {

    private static final long serialVersionUID = -6288277621340836690L;

    public enum Group {

        Admin, Cp;
    }

    @NotNull
    @Size(max = 75)
    @Column(name = "USERNAME", unique = true, length = 75)
    protected String username;

    @NotNull
    @Size(max = 75)
    @Column(name = "CODE", unique = true, length = 75)
    protected String code;

    @NotNull
    @Column(name = "FULLNAME", length = 100)
    @Size(max = 100)
    protected String fullname;

    @NotNull
    @Size(max = 150)
    @Column(name = "PASSWORD", length = 150)
    protected String password;

    @NotNull
    @Email
    @Size(max = 200)
    @Column(name = "EMAIL", unique = true, length = 200)
    protected String email;

    @ElementCollection(targetClass = Group.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "NCMS_USERGROUP", joinColumns = {
        @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")})
    @Column(name = "GROUPNAME")
    protected List<Group> groups;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", fullname=" + fullname + ", password="
                + password + ", email=" + email + '}';
    }

    @PrePersist
    public void hashPassword() {
        password = StringUtil.digest("SHA-256", password);
    }
}
