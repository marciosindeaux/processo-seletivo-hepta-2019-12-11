package com.hepta.mercado.persistence;

import com.hepta.mercado.entity.Produto;

import javax.persistence.EntityManager;

public class ProdutoDAO extends AbstractDAO<Produto>{

	@Override
	protected Object findAbstractEntity(EntityManager em, Integer id) {
		return em.find(Produto.class, id);
	}

	@Override
	protected String nameAbstractEntity() {
		return "Produto";
	}
}
