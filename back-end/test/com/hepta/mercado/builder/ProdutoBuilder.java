package com.hepta.mercado.builder;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.entity.Produto;

public class ProdutoBuilder {

    private Produto produto;

    private ProdutoBuilder(Produto produto){
        this.produto= produto;
    }

    public static ProdutoBuilder umProduto(){
        return new ProdutoBuilder(new Produto());
    }

    public ProdutoBuilder comId(Integer id){
        this.produto.setId(id);
        return this;
    }

    public ProdutoBuilder comNome(String nome){
        this.produto.setNome(nome);
        return this;
    }

    public ProdutoBuilder comFabricante(Fabricante fab){
        this.produto.setFabricante(fab);
        return this;
    }

    public ProdutoBuilder comVolume(Double volume){
        this.produto.setVolume(volume);
        return this;
    }

    public ProdutoBuilder comUnidade(String unidade){
        this.produto.setUnidade(unidade);
        return this;
    }

    public ProdutoBuilder comEstoque(Integer estoque){
        this.produto.setEstoque(estoque);
        return this;
    }

    public Produto agora(){
        return this.produto;
    }


}
