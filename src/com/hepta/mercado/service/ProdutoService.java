package com.hepta.mercado.service;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.entity.Produto;
import com.hepta.mercado.persistence.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private ProdutoDAO produtoDAO;

    private FabricanteService fabricanteService;

    public ProdutoService(){
        produtoDAO = new ProdutoDAO();
        fabricanteService = new FabricanteService();
    }

    public void salvarProduto(Produto produto){
        fabricanteService.salvarFabricante(produto.getFabricante());
        produto.setFabricante(fabricanteService.listarTodos()
                .stream()
                .filter(a -> a.getNome().equalsIgnoreCase(produto.getFabricante().getNome()))
                .reduce((a,b) -> {
                    if(b!= null){
                        return b;
                    }else{
                        return a;
                    }
                }).orElse(new Fabricante()));
        produtoDAO.insert(produto);
    }

    public List<Produto> listarProdutos(){
        return produtoDAO.selectAll().orElse(new ArrayList<>());
    }

    public void excluirProduto(Integer id){
        produtoDAO.delete(id);
    }

    public void atualizarProduto(Integer id, Produto produto){
        produtoDAO.selectFromId(id);
        produto.setId(id);
        fabricanteService.salvarFabricante(produto.getFabricante());
        fabricanteService.listarTodos().stream()
                .filter(a -> a.getNome().equalsIgnoreCase(produto.getFabricante().getNome()))
                .reduce((a,b) -> {
                    if(b != null){
                        return b;
                    }else{
                        return a;
                    }
                });
        produtoDAO.update(produto);
    }

    public Produto buscarPorId(Integer id) {
        return produtoDAO.selectFromId(id).orElse(null);
    }

}
