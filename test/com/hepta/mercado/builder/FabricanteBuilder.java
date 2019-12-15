package com.hepta.mercado.builder;

import com.hepta.mercado.entity.Fabricante;

public class FabricanteBuilder {

    public static FabricanteBuilder umFabricante(){
        return new FabricanteBuilder(new Fabricante());
    }

    private  Fabricante fabricante;

    private FabricanteBuilder(Fabricante fabricante){
        this.fabricante = fabricante;
    }

    public FabricanteBuilder comNome(String nome){
        fabricante.setNome(nome);
        return this;
    }

    public FabricanteBuilder comId(Integer id){
        fabricante.setId(id);
        return this;
    }

    public Fabricante agora(){
        return this.fabricante;
    }
}
