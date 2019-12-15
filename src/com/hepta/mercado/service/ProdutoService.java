package com.hepta.mercado.service;

import com.hepta.mercado.entity.Produto;
import com.hepta.mercado.persistence.ProdutoDAO;

import java.util.List;

public class ProdutoService {

    private ProdutoDAO produtoDAO;

    private FabricanteService fabricanteService;

    public ProdutoService(){
        produtoDAO = new ProdutoDAO();
        fabricanteService = new FabricanteService();
    }

    public void salvarProduto(Produto produto) throws Exception {
        fabricanteService.salvarFabricante(produto.getFabricante());
        produtoDAO.save(produto);
    }

    public List<Produto> listarProdutos() throws Exception {
        return produtoDAO.getAll();
    }

}