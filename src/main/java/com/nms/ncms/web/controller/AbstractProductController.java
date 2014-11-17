/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.web.controller;

import com.nms.ncms.entity.Category;
import com.nms.ncms.entity.Product;
import com.nms.ncms.service.ChargingService;
import com.nms.ncms.service.MobileChecker;
import com.nms.ncms.service.entity.ProductService;
import com.nms.ncms.web.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public abstract class AbstractProductController<T extends Product, C extends Category> implements Serializable {

    private static final long serialVersionUID = -1246504599210472022L;
    protected static final Logger LOGGER = Logger.getLogger("AbstractProductController");

    protected T current;
    protected C category;
    protected int page = 0;
    protected boolean hasNext = false;
    protected boolean hasPreview = false;
    protected int count = 0;
    protected String orderField = "createdDate";

    @Inject
    protected ChargingService chargingService;
    @Inject
    protected MobileChecker mobileChecker;

    protected List<T> model;

    protected List<T> listByCat;
    protected List<T> listExcludeCurrent;

    public void chargingProduct() {
        if (mobileChecker.isVnmSubsriber()) {
            try {
                FacesMessage message = chargingService.chargeProduct(current);
                if (message.getSeverity().equals(FacesMessage.SEVERITY_INFO)) {
                    getProductService().increateDownloadCount(current);
                }
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception e) {
                MessageUtil.addGlobalWarnMessage("charging-system-overloading");
                LOGGER.log(Level.SEVERE, "charing-service-error", e);
            }
            
        } else {
            MessageUtil.addGlobalWarnMessage("vnm-subscriber-not-detech");
        }
    }

    public C getCategory() {
        return category;
    }

    public void setCategory(C category) {
        this.category = category;
    }

    protected List<T> hots;
    protected List<T> tops;
    protected List<T> news;
    protected List<T> frees;
    protected List<T> promos;

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public List<T> getListByCat() {
        return listByCat;
    }

    public List<T> getListExcludeCurrent() {
        if (current != null) {
            listExcludeCurrent = getProductService().findExcludeCurrent(0, 4, current);
        }
        return listExcludeCurrent;
    }

    public List<T> getHots() {
        return getProductService().findByCat(0, 5, category, "downloadCount", false);
    }

    public List<T> getTops() {
        if (tops == null) {
            tops = getProductService().findByCat(0, 5, category, "downloadCount", false);
        }
        return tops;
    }

    public List<T> getNews() {
        if (news == null) {
            news = getProductService().findByCat(0, 5, category, "createdDate", false);
        }
        return news;
    }

    public List<T> getFrees() {
        if (frees == null) {
            frees = getProductService().getFrees(0, 5, category, "createdDate", false);
        }
        return frees;
    }

    public List<T> getPromos() {
        if (promos == null) {
            promos = getProductService().getPromotions(0, 5, category, "createdDate", false);
        }
        return promos;
    }

    public List<T> getModel() {
        return model;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public void initData() {
        if (current != null) {
            // increase view count
            getProductService().increaseViewCount(current);
            model = getProductService().findExcludeCurrent(page * 10, 10, current);
            count = getProductService().countByCat(category) - 1;

        } else if (category != null) {
            model = getProductService().findByCat(page * 10, 10, category, orderField, false);
            count = getProductService().countByCat(category);
        }
        if (current != null || category != null) {
            if ((page + 1) * 10 <= count) {
                hasNext = true;
            }
            if (page > 0) {
                hasPreview = true;
            }
        }
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPreview() {
        return hasPreview;
    }

    public void setHasPreview(boolean hasPreview) {
        this.hasPreview = hasPreview;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    protected abstract ProductService<T, C> getProductService();
}
