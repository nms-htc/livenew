/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 * All rights reserved.
 */
package com.nms.ncms.web.controller.admin;

import com.nms.ncms.entity.BaseEntity;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.web.model.AbstractLazyDataModel;
import com.nms.ncms.web.util.JsfUtil;
import com.nms.ncms.web.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;
import org.primefaces.model.LazyDataModel;


/**
 * Abtrast class that implement all basic functionalities managed bean for entities.
 *
 * @author Nguyen Trong Cuong (cuongnt1987@gmail.com)
 * @since 16/09/2014
 * @version 1.0
 * @param <T> Entity Class Type
 */
public abstract class AbstractManagedBean<T extends BaseEntity> implements Serializable {

    private static final long serialVersionUID = 8024568564171342875L;

    protected T current;
    protected LazyDataModel<T> model;
    private SelectItem[] selectItems;
    private List<T> all;

    public void resetEntity() {
        current = null;
    }

    public void prepareEntity(T entity) {
        current = entity;
    }

    /**
     * Call back method persist action
     */
    protected void onBeforePersist() {
    }

    /**
     * Call back method persist action
     */
    protected void onPersistSuccess() {
        current = initEntity();
    }

    /**
     * Persist entity to db
     */
    public void persist() {
        JsfUtil.processAction(e -> {
            onBeforePersist();
            getBaseService().persist(e);
            onPersistSuccess();
        }, current, MessageUtil.REQUEST_SUCCESS_MESSAGE, 
        MessageUtil.REQUEST_FAIL_MESSAGE);
    }

    /**
     * Call back method update action
     */
    protected void onBeforeUpdate() {
    }

    /**
     * Call back method update action
     */
    protected void onUpdateSuccess() {
    }

    /**
     * Update entity and save to db
     */
    public void update() {
        JsfUtil.processAction(e -> {
            onBeforeUpdate();
            getBaseService().update(e);
            onUpdateSuccess();
        }, current, MessageUtil.REQUEST_SUCCESS_MESSAGE, MessageUtil.REQUEST_FAIL_MESSAGE);
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onBeforeRemove(T entity) {
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onRemoveSuccess(T entity) {
    }

    /**
     * Remove entity from db
     *
     * @param entity entity instance for removing
     */
    public void remove(T entity) {
        JsfUtil.processAction(e -> {
            onBeforeRemove(e);
            getBaseService().remove(e);
            onRemoveSuccess(e);
        }, entity, MessageUtil.REQUEST_SUCCESS_MESSAGE, MessageUtil.REQUEST_FAIL_MESSAGE);
    }

    /**
     * Initilize a new instance of the entity;
     *
     * @return new instanse of entity
     */
    protected abstract T initEntity();
    
    /**
     * Factory method for initilize a new instance of LazyDataModel (Primefaces)
     *
     * @return new instance of LazyDataModel for the entity.
     */
    protected LazyDataModel<T> initDataModel() {
        return new AbstractLazyDataModel<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected BaseService<T> getService() {
                return getBaseService();
            }

            @Override
            protected void modifyModelFilters(Map<String, Object> filters) {
                super.modifyModelFilters(filters);
                alterModelFilters(filters);
            }
            
        };
    }

    /**
     * Factoty method for BasicService EJB
     *
     * @return BasicSerivce instanse
     */
    protected abstract BaseService<T> getBaseService();

    /* getters and setters */
    public T getCurrent() {

        if (current == null) {
            current = initEntity();
        }

        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public LazyDataModel<T> getModel() {
        if (model == null) {
            model = initDataModel();
        }
        return model;
    }

    public void setModel(LazyDataModel<T> model) {
        this.model = model;
    }

    public SelectItem[] getSelectItems() {
        if (selectItems == null) {
            selectItems = JsfUtil.getSelectItems(getBaseService().findAll(), false);
        }
        return selectItems;
    }

    public void setSelectItems(SelectItem[] selectItems) {  
        this.selectItems = selectItems;
    }
    
    public List<T> getAll() {
        return getBaseService().findAll();
    }
    
    // pre-set criteria for filtering datatable.
    protected void alterModelFilters(Map<String, Object> filters) {
    }
    
}
