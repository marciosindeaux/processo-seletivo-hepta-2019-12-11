package com.hepta.mercado.service;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.persistence.FabricanteDAO;

import java.util.ArrayList;
import java.util.List;

class FabricanteService {

    private FabricanteDAO fabricanteDAO;

    FabricanteService() {
        this.fabricanteDAO = new FabricanteDAO();
    }

    void salvarFabricante(Fabricante fabricante) throws Exception {
        if(fabricante!= null && !fabricante.getNome().isEmpty()){
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
