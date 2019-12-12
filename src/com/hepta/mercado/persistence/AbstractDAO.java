package com.hepta.mercado.persistence;

import com.hepta.mercado.entity.Produto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO {

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
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
        return produto;
    }

    @SuppressWarnings("unchecked")
    public List<Produto> getAll() throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        List<Produto> produtos = new ArrayList<>();
        try {
            Query query = em.createQuery("FROM " + nameAbstractEntity());
            produtos = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
        return produtos;
    }

    protected abstract Object findAbstractEntity(EntityManager em, Integer id);

    protected abstract String nameAbstractEntity();
}
