/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.web.util;

import com.nms.ncms.entity.Product;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.DataModel;

public abstract class PaginationHelper<T extends Product>{

    private final int pageSize;
    private int page;
    private final int itemCount;

    public PaginationHelper(int pageSize, int itemCount) {
        this.pageSize = pageSize;
        this.itemCount = itemCount;
        LOGGER.log(Level.INFO, "PaginationHelper is created with pageSize = {0} and page = {1}", new Object[]{this.pageSize, this.page});
    }
    
    public int getItemCount() {
        return this.itemCount;
    }

    public abstract DataModel createPageDataModel();

    public int getPageFirstItem() {
        LOGGER.log(Level.INFO, "Method getFirstPage() is called with result is {0}", page * pageSize);
        return page * pageSize;
    }

    public int getPageLastItem() {
        int i = getPageFirstItem() + pageSize - 1;
        int count = getItemCount() - 1;
        if (i > count) {
            i = count;
        }

        if (i < 0) {
            i = 0;
        }

        return i;
    }

    public boolean isHasNextPage() {
        LOGGER.info("Method isHasNextpage is called");
        LOGGER.log(Level.INFO, "Current page is: {0}", page);
        LOGGER.log(Level.INFO, "Current pageSize is: {0}", pageSize);
        LOGGER.log(Level.INFO, "Current ItemCount is: {0}", getItemCount());
        LOGGER.log(Level.INFO, "Result is: {0}", (page + 1) * pageSize + 1 <= getItemCount());
        return (page + 1) * pageSize + 1 <= getItemCount();
    }

    public void nextPage() {
        if (isHasNextPage()) {
            page++;
            LOGGER.log(Level.INFO, "Nextpage is called and now page = {0}", page);
        }
    }

    public boolean isHasPreviewPage() {
        LOGGER.log(Level.INFO, "Has preview page is called with page = {0}", page);
        return page > 0;
    }

    public void previewPage() {
        if (isHasPreviewPage()) {
            page--;
            LOGGER.log(Level.INFO, "Previewpage is called and now page = {0}", page);
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public boolean isStartPage() {
        return page == 0;
    }

    public void goToStartPage() {
        if (!isStartPage()) {
            page = 0;
        }
    }

    public boolean isEndPage() {
        return !isHasNextPage();
    }

    public void goToEndPage() {
        if (isHasNextPage()) {
            page = getTotalPage() - 1;
        }
    }

    public int getTotalPage() {
        int totalPage = 0;
        int count = getItemCount();

        if (count > 0) {
            totalPage = count / pageSize;
        }

        if (count % pageSize > 0) {
            totalPage++;
        }

        return totalPage;
    }

    private static final Logger LOGGER = Logger.getLogger(PaginationHelper.class.getName());
}
