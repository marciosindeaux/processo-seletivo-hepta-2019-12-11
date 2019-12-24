package com.hepta.mercado.service;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.persistence.FabricanteDAO;

import java.util.ArrayList;
import java.util.List;

class FabricanteService {

    FabricanteDAO fabricanteDAO;

    FabricanteService() {
        fabricanteDAO = new FabricanteDAO();
    }

    void salvarFabricante(Fabricante fabricante){
        if(fabricante!= null && !fabricante.getNome().isEmpty()){
            List<Fabricante> fabricantes = fabricanteDAO.selectAll().orElse(new ArrayList<>());

            for(Fabricante item : fabricantes){
                if(item.getNome().equalsIgnoreCase(fabricante.getNome())){
                    fabricante.setId(item.getId());
                    return;
                }
            }

            fabricanteDAO.insert(fabricante);
        }else{
            throw new RuntimeException();
        }
    }

    List<Fabricante> listarTodos(){
        return fabricanteDAO.selectAll().orElse(new ArrayList<>());
    }
}
