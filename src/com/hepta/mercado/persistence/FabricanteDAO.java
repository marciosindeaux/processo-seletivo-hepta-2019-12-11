package com.hepta.mercado.persistence;

import com.hepta.mercado.entity.Fabricante;

import javax.persistence.EntityManager;

public class FabricanteDAO extends AbstractDAO<Fabricante> {


    @Override
    protected Object findAbstractEntity(EntityManager em, Integer id) {
        return em.find(Fabricante.class, id);
    }

    @Override
    protected String nameAbstractEntity() {
        return "Fabricante";
    }
}
