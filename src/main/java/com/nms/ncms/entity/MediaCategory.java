/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cuong
 */
@Entity
@DiscriminatorValue("Media")
@XmlRootElement
public class MediaCategory extends BaseCategory {
    
    private static final long serialVersionUID = 1067101326152831801L;
    
    
}
