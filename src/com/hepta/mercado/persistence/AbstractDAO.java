package com.hepta.mercado.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractDAO<T> {

    public void save(Object entity) throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
    }

    public Object update(Object objeto) throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        Object objetoAtualizado = null;
        try {
            em.getTransaction().begin();
            objetoAtualizado = em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
        return objetoAtualizado;
    }

    public void delete(Integer id) throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Object produto = findAbstractEntity(em,id);
            em.remove(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }

    }

    public Object find(Integer id) throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        Object produto = null;
        try {
            produto = findAbstractEntity(em,id);
            if(produto == null){
                throw new NoSuchElementException();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
        return produto;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        List<T> list = new ArrayList<>();
        try {
            Query query = em.createQuery("FROM " + nameAbstractEntity());
            list = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
        return list;
    }

    protected abstract Object findAbstractEntity(EntityManager em, Integer id);

    protected abstract String nameAbstractEntity();
}
