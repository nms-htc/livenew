/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.web.util.MessageUtil;
import java.util.Collection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Game")
@XmlRootElement
public class Game extends Product {

    private static final long serialVersionUID = -2110867128144045606L;

    public enum Flatform {

        Android, Ios, Java, Window_Phone;

        @Override
        public String toString() {
            String result = "";
            switch (this) {
                case Android:
                    result = MessageUtil.getBundleMessage("android");
                    break;
                case Ios:
                    result = MessageUtil.getBundleMessage("ios");
                    break;
                case Java:
                    result = MessageUtil.getBundleMessage("java");
                    break;
                case Window_Phone:
                    result = MessageUtil.getBundleMessage("windowphone");
                    break;
            }
            return result;
        }
    }

    @Size(max = 2000)
    @Column(name = "DEVICES_SUPPORT", length = 2000)
    private String devicesSupport;

    @ElementCollection(targetClass = Flatform.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "NCMS_FLATFORM", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @Column(name = "FLATFORM")
    private Collection<Flatform> flatforms;

    public String getDevicesSupport() {
        return devicesSupport;
    }

    public void setDevicesSupport(String devicesSupport) {
        this.devicesSupport = devicesSupport;
    }

    public Collection<Flatform> getFlatforms() {
        return flatforms;
    }

    public void setFlatforms(Collection<Flatform> flatforms) {
        this.flatforms = flatforms;
    }
}
