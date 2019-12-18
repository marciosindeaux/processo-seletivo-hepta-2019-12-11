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

    public void excluirProduto(Integer id) throws Exception {
        produtoDAO.delete(id);
    }

    public void atualizarProduto(Integer id, Produto produto) throws Exception {
        produtoDAO.find(id);
        produto.setId(id);
        fabricanteService.salvarFabricante(produto.getFabricante());
        produtoDAO.update(produto);
    }

    public Produto buscarPorId(Integer id) throws Exception {
        return (Produto) produtoDAO.find(id);
    }

}
