/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.BaseEntity_;
import com.nms.ncms.entity.BaseCategory;
import com.nms.ncms.entity.Product;
import com.nms.ncms.entity.Product_;
import com.nms.ncms.service.MobileChecker;
import com.nms.ncms.service.entity.FileService;
import com.nms.ncms.service.entity.ProductService;
import com.nms.ncms.web.util.AppConfig;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.io.FileUtils;

public abstract class AbstractProductBean<C extends BaseCategory, P extends Product> extends AbstractFacadeBean<P> implements ProductService<P, C> {

    private static final long serialVersionUID = -2603765016508854535L;
    
    @EJB
    private FileService fileService;
    
    public AbstractProductBean(Class<P> entityClass) {
        super(entityClass);
    }
    
    @Override
    public List<P> findByCat(int start, int range, C category, String orderField, boolean asc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<P> cq = cb.createQuery(entityClass);
        Root<P> root = cq.from(entityClass);
        cq.select(root);
        
        if (category != null) {
            cq.where(cb.equal(root.get("category"), (BaseCategory) category));
        }
        
        if (orderField != null && !orderField.trim().isEmpty()) {
            if (asc) {
                cq.orderBy(cb.asc(root.get(orderField)));
            } else {
                cq.orderBy(cb.desc(root.get(orderField)));
            }
        }
        
        TypedQuery<P> q = em.createQuery(cq);
        q.setFirstResult(start);
        q.setMaxResults(range);
        return q.getResultList();
    }
    
    @Override
    public List<P> findExcludeCurrent(int start, int range, P product) {
        if (product != null) {
            BaseCategory category = product.getCategory();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<P> cq = cb.createQuery(entityClass);
            Root<P> root = cq.from(entityClass);
            if (category != null) {
                C cat = (C) category;
                cq.where(new Predicate[]{
                    cb.equal(root.get("category"), cat),
                    cb.notEqual(root.get("id"), ((Product) product).getId())
                });
            }
            TypedQuery<P> q = em.createQuery(cq);
            q.setFirstResult(start);
            q.setMaxResults(range);
            return q.getResultList();
        }
        return null;
    }
    
    @Override
    public List<P> getPromotions(int start, int range, C category, String orderField, boolean asc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<P> cq = cb.createQuery(entityClass);
        Root<P> root = cq.from(entityClass);
        cq.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.greaterThan(root.get("promoPrice"), Double.valueOf(0)));
        if (category != null) {
            predicates.add(cb.equal(root.get("category"), category));
        }
        
        cq.where(predicates.toArray(new Predicate[]{}));
        
        if (orderField != null && !orderField.trim().isEmpty()) {
            if (asc) {
                cq.orderBy(cb.asc(root.get(orderField)));
            } else {
                cq.orderBy(cb.desc(root.get(orderField)));
            }
        }
        
        TypedQuery<P> q = em.createQuery(cq);
        q.setFirstResult(start);
        q.setMaxResults(range);
        return q.getResultList();
    }
    
    @Override
    public List<P> getFrees(int start, int range, C category, String orderField, boolean asc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<P> cq = cb.createQuery(entityClass);
        Root<P> root = cq.from(entityClass);
        cq.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("price"), 0));
        if (category != null) {
            predicates.add(cb.equal(root.get("category"), category));
        }
        
        cq.where(predicates.toArray(new Predicate[]{}));
        
        if (orderField != null && !orderField.trim().isEmpty()) {
            if (asc) {
                cq.orderBy(cb.asc(root.get(orderField)));
            } else {
                cq.orderBy(cb.desc(root.get(orderField)));
            }
        }
        
        TypedQuery<P> q = em.createQuery(cq);
        q.setFirstResult(start);
        q.setMaxResults(range);
        return q.getResultList();
    }
    
    @Override
    public int countFrees(C category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<P> root = cq.from(entityClass);
        cq.select(cb.count(root));
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("price"), 0));
        if (category != null) {
            predicates.add(cb.equal(root.get("category"), category));
        }
        
        cq.where(predicates.toArray(new Predicate[]{}));
        
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }
    
    @Override
    public int countPromotions(C category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<P> root = cq.from(entityClass);
        cq.select(cb.count(root));
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.greaterThan(root.get("promoPrice"), Double.valueOf(0)));
        if (category != null) {
            predicates.add(cb.equal(root.get("category"), category));
        }
        
        cq.where(predicates.toArray(new Predicate[]{}));
        
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }
    
    @Override
    public int countByCat(C category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<P> root = cq.from(entityClass);
        cq.select(cb.count(root));
        
        if (category != null) {
            cq.where(cb.equal(root.get("category"), category));
        }
        
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }
    
    @Override
    public void increaseViewCount(P product) {
        int count = product.getViewCount() + 1;
        product.setViewCount(count);
        em.merge(product);
    }
    
    @Override
    public void increateDownloadCount(P product) {
        int count = product.getDownloadCount() + 1;
        product.setDownloadCount(count);
        em.merge(product);
    }
    
    @Override
    protected void onBeforePersist(P entity) {
        super.onBeforePersist(entity);
        // validate
        validate(entity);
    }
    
    @Override
    protected void onAfterPersist(P entity) {
        super.onAfterPersist(entity);
        try {
            saveFile(entity.getThumbFile());
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Error when save file to the fileStore", ex);
            throw new EJBException("product.error.thumbnail.storefile");
        }
    }
    
    @Override
    protected void onBeforeUpdate(P entity) {
        super.onBeforeUpdate(entity);

        if (entity.getThumbFile() != null) {
            if (entity.getThumbFile().isHasFile()) {
                try {
                    saveFile(entity.getThumbFile());
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "Error when save file to the fileStore", e);
                    throw new EJBException("product.error.thumbnail.storefile");
                }
            }
        }
    }
    
    @Override
    protected void onBeforeRemove(P entity) {
        super.onBeforeRemove(entity);
        if (entity.getThumbFile() != null) {
            if (entity.getThumbFile().isUpload()) {
                FileUtils.deleteQuietly(new File(AppConfig.getFileStorePath() + entity.getThumbFile().getId()));
            }
        }
    }
    
    protected void validate(P product) {
        Product testEntity = null;
        try { // check duplicate code of concreate product
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<P> cq = cb.createQuery(entityClass);
            Root<P> root = cq.from(entityClass);
            cq.select(root);
            cq.where(cb.equal(root.get("code"), product.getCode()));
            TypedQuery<P> q = em.createQuery(cq);
            testEntity = q.getSingleResult();
        } catch (Exception e) {
            getLogger().log(Level.OFF, "Check dupplicating product code, excetion for correcting", e);
        }
        
        if (testEntity != null) {
            getLogger().log(Level.SEVERE, "Duplicate product code error - \"{0}\"", product.getCode());
            throw new EJBException("product.code.error.duplicate");
        }
    }
    
    @Override
    public List<P> search(String keywords, C category, MobileChecker mobileChecker, String orderField, boolean asc, int start, int range) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<P> cq = cb.createQuery(entityClass);
        Root<P> root = cq.from(entityClass);
        cq.select(root);

        List<Predicate> predicates = buildPredicates(cb, root, category, keywords);
        List<Predicate> mobilePredicates = buildPredicates(cb, root, mobileChecker);

        predicates.addAll(mobilePredicates);

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        if (orderField != null && !orderField.trim().isEmpty()) {
            if (asc) {
                cq.orderBy(cb.asc(root.get(orderField)));
            } else {
                cq.orderBy(cb.desc(root.get(orderField)));
            }
        }

        TypedQuery<P> q = em.createQuery(cq);
        q.setFirstResult(start);
        q.setMaxResults(range);
        return q.getResultList();
    }

    @Override
    public int count(String keywords, C category, MobileChecker mobileChecker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Build predicates for search
     *
     * @param cb
     * @param root
     * @param category
     * @param keywords
     * @return
     */
    protected List<Predicate> buildPredicates(CriteriaBuilder cb, Root<P> root, C category, String keywords) {
        List<Predicate> predicates = new LinkedList<>();
        
        //predicates.add(cb.isTrue(root.get(Product_.enable)));

        if (category != null) {
            predicates.add(cb.equal(root.get(Product_.category), category));
        }

        if (keywords != null && !keywords.trim().isEmpty()) {
           predicates.add(cb.like(cb.upper(root.get(Product_.title)), '%' + keywords.trim().toUpperCase() + '%'));
        }

        return predicates;
    }
    
    /**
     * Build predicats for search on Wap
     *
     * @param cd
     * @param root
     * @param mobileChecker
     * @return
     */
    protected List<Predicate> buildPredicates(CriteriaBuilder cd, Root<P> root, MobileChecker mobileChecker) {
        return new LinkedList<>();
    }

}
