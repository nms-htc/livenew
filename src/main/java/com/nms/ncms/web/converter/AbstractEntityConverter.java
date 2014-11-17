/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 * All rights reserved.
 */
package com.nms.ncms.web.converter;

import com.nms.ncms.entity.BaseEntity;
import com.nms.ncms.service.entity.BaseService;
import com.nms.ncms.web.util.JsfUtil;
import java.lang.reflect.ParameterizedType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public abstract class AbstractEntityConverter<T extends BaseEntity> implements Converter {

    /* Factory method */
    protected abstract BaseService<T> getBaseService();

    protected Long getKey(String keyStr) {
        return Long.parseLong(keyStr);
    }

    protected String getStringKey(T entity) {
        return String.valueOf(((BaseEntity) entity).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        if (JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }

        return getBaseService().find(getKey(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        if (value.getClass().equals(getEntityClass())) {
            T entity = (T) value;
            return getStringKey(entity);
        } else {
            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass()
                    .getName() + "; expected type: " + getEntityClass().getName());
        }
    }

    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
