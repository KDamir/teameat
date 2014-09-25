/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author damir.keldibekov
 */
public class CommonDao implements Serializable{
    public CommonDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /*InvoiceEntity*/
    public void create(InvoiceEntity invoiceEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(invoiceEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<InvoiceEntity> findInvoiceEntityEntities() {
        return findInvoiceEntityEntities(true, -1, -1);
    }

    public List<InvoiceEntity> findInvoiceEntityEntities(int maxResults, int firstResult) {
        return findInvoiceEntityEntities(false, maxResults, firstResult);
    }

    private List<InvoiceEntity> findInvoiceEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvoiceEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public InvoiceEntity findInvoiceEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvoiceEntity.class, id);
        } finally {
            em.close();
        }
    }
    
    /*MeatCategoryEntity*/
    public void create(MeatCategoryEntity meatCategoryEntity) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(meatCategoryEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMeatCategoryEntity(meatCategoryEntity.getName()) != null) {
                throw new PreexistingEntityException("MeatCategoryEntity " + meatCategoryEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<MeatCategoryEntity> findMeatCategoryEntityEntities() {
        return findMeatCategoryEntityEntities(true, -1, -1);
    }

    public List<MeatCategoryEntity> findMeatCategoryEntityEntities(int maxResults, int firstResult) {
        return findMeatCategoryEntityEntities(false, maxResults, firstResult);
    }

    private List<MeatCategoryEntity> findMeatCategoryEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MeatCategoryEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MeatCategoryEntity findMeatCategoryEntity(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MeatCategoryEntity.class, id);
        } finally {
            em.close();
        }
    }
    /*MeatPartEntity*/
    public void create(MeatPartEntity meatPartEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(meatPartEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<MeatPartEntity> findMeatPartEntityEntities() {
        return findMeatPartEntityEntities(true, -1, -1);
    }

    public List<MeatPartEntity> findMeatPartEntityEntities(int maxResults, int firstResult) {
        return findMeatPartEntityEntities(false, maxResults, firstResult);
    }

    private List<MeatPartEntity> findMeatPartEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MeatPartEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MeatPartEntity findMeatPartEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MeatPartEntity.class, id);
        } finally {
            em.close();
        }
    }
    
    /*MeatTypesEntity*/
    public void create(MeatTypesEntity meatTypesEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(meatTypesEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<MeatTypesEntity> findMeatTypesEntityEntities() {
        return findMeatTypesEntityEntities(true, -1, -1);
    }

    public List<MeatTypesEntity> findMeatTypesEntityEntities(int maxResults, int firstResult) {
        return findMeatTypesEntityEntities(false, maxResults, firstResult);
    }

    private List<MeatTypesEntity> findMeatTypesEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MeatTypesEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MeatTypesEntity findMeatTypesEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MeatTypesEntity.class, id);
        } finally {
            em.close();
        }
    }
    
    /*ReceiverEntity*/
    public void create(ReceiverEntity receiverEntity) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(receiverEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReceiverEntity(receiverEntity.getCompanyName()) != null) {
                throw new PreexistingEntityException("ReceiverEntity " + receiverEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<ReceiverEntity> findReceiverEntityEntities() {
        return findReceiverEntityEntities(true, -1, -1);
    }

    public List<ReceiverEntity> findReceiverEntityEntities(int maxResults, int firstResult) {
        return findReceiverEntityEntities(false, maxResults, firstResult);
    }

    private List<ReceiverEntity> findReceiverEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReceiverEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReceiverEntity findReceiverEntity(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReceiverEntity.class, id);
        } finally {
            em.close();
        }
    }
}
