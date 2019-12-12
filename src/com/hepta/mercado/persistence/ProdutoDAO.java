package com.hepta.mercado.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.mercado.entity.Produto;

public class ProdutoDAO extends AbstractDAO{

	@Override
	protected Object findAbstractEntity(EntityManager em, Integer id) {
		return (Produto) em.find(Produto.class, id);
	}

	@Override
	protected String nameAbstractEntity() {
		return "Produto";
	}
}
