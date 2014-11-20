/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.entity;

import com.nms.ncms.web.util.MessageUtil;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected FileEntry gameFile = new FileEntry();

    @Size(max = 2000)
    @Column(name = "DEVICES_SUPPORT", length = 2000)
    private String devicesSupport;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "FLATFORM")
    private Flatform flatform;

    public Game() {
    }

    public String getDevicesSupport() {
        return devicesSupport;
    }

    public void setDevicesSupport(String devicesSupport) {
        this.devicesSupport = devicesSupport;
    }

    public Flatform getFlatform() {
        return flatform;
    }

    public void setFlatform(Flatform flatforms) {
        this.flatform = flatforms;
    }

    public FileEntry getGameFile() {
        return gameFile;
    }

    public void setGameFile(FileEntry gameFile) {
        this.gameFile = gameFile;
    }

}
