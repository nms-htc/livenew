/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.model;

import com.nms.ncms.entity.BaseEntity;
import com.nms.ncms.service.entity.BaseService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public abstract class AbstractLazyDataModel<T extends BaseEntity> extends LazyDataModel<T> {

    private static final long serialVersionUID = -1137464869996262401L;

    protected abstract BaseService<T> getService();

    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public T getRowData(String rowKey) {
        return getService().find(parserRowKey(rowKey));
    }

    @Override
    public Object getRowKey(T object) {
        return ((BaseEntity) object).getId();
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        // modifing filter cirterias.
        modifyModelFilters(filters);

        boolean asc = false;
        if (sortOrder != null && sortOrder == SortOrder.ASCENDING) {
            asc = true;
        }
        this.setRowCount(getService().count(filters));
        return getService().search(first, pageSize, sortField, asc, filters);
    }

    /**
     * Override this method to add default predicate for query data model.
     *
     * @param filters
     */
    protected void modifyModelFilters(Map<String, Object> filters) {
        /* nothing to say. */
    }

}
