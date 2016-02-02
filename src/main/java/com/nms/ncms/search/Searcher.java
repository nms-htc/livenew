/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ncms.search;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author cuong
 */
public interface Searcher extends Serializable {
    public Map<String, Object> updateFilter(Map<String, Object> filter);
}
