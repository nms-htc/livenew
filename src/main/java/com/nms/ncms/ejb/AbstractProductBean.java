/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.ejb;

import com.nms.ncms.entity.Category;
import com.nms.ncms.entity.Product;
import com.nms.ncms.service.entity.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstractProductBean<C extends Category, P extends Product> extends AbstractFacadeBean<P> implements ProductService<P, C> {

    private static final long serialVersionUID = -2603765016508854535L;

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
            cq.where(cb.equal(root.get("category"), (Category) category));
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
            Category category = product.getCategory();
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
        int count = product.getDownloadCount()+ 1;
        product.setDownloadCount(count);
        em.merge(product);
    }

    @Override
    protected void onBeforePersist(P entity) {
        super.onBeforePersist(entity);
        Product testEntity = null; 
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> root = cq.from(Product.class);
            cq.select(root);
            cq.where(cb.equal(root.get("code"), entity.getCode()));
            TypedQuery<Product> q = em.createQuery(cq);
            testEntity = q.getSingleResult();
        } catch (Exception e) {
            // valid ok?
        }
        if (testEntity != null) {
            throw new EJBException("product.code.error.duplicate");
        }
    }
    
}
