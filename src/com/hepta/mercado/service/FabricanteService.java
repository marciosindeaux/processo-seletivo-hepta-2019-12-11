package com.hepta.mercado.service;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.persistence.FabricanteDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class FabricanteService {

    private FabricanteDAO fabricanteDAO;

    FabricanteService() {
        this.fabricanteDAO = new FabricanteDAO();
    }

    void salvarFabricante(Fabricante fabricante) throws Exception {
        if(fabricante!= null){
            List<Fabricante> fabricantes = new ArrayList<Fabricante>(fabricanteDAO.getAll());

            for(Fabricante item : fabricantes){
                if(item.getNome().equalsIgnoreCase(fabricante.getNome())){
                    fabricante.setId(item.getId());
                    return;
                }
            }

            fabricanteDAO.save(fabricante);
        }else{
            throw new RuntimeException();
        }
    }
}