package com.hepta.mercado.persistence;

import com.hepta.mercado.entity.Fabricante;

import javax.persistence.EntityManager;
import javax.print.DocFlavor;

public class FabricanteDAO extends AbstractDAO {


    @Override
    protected Object findAbstractEntity(EntityManager em, Integer id) {
        return  (Fabricante) em.find(Fabricante.class, id);
    }

    @Override
    protected String nameAbstractEntity() {
        return "Fabricante";
    }
}
