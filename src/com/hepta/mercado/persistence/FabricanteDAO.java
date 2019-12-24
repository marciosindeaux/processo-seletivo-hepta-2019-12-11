package com.hepta.mercado.persistence;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.persistence.abstarcts.AbstractDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FabricanteDAO extends AbstractDAO {

    private boolean tableCreated = false;

    private String classNamed = Arrays.stream(Fabricante.class.getName().split("\\.")).reduce((a,b) -> b).orElse(null);

    public void createTableIfNotExisits(){
        if(!tableCreated){

            String sql = "CREATE TABLE IF NOT EXISTS "+classNamed+"( " +
                    "id INTEGER NOT NULL AUTO_INCREMENT," +
                    " nome VARCHAR(255)," +
                    " PRIMARY KEY(id))";
            tableCreated = true;
            this.realizeOperation(sql);
        }
    }

    public Optional<List<Fabricante>> selectAll(){
        createTableIfNotExisits();
        createTableIfNotExisits();
        String sql = "SELECT id, nome FROM "+classNamed;
        ResultSet rs = this.realizeOperation(sql);
        if(rs != null){
            List<Fabricante> fabricantes = new ArrayList<>();
            try{
                while(rs.next()){
                    Fabricante fabricante = new Fabricante();
                    fabricante.setId(rs.getInt("id"));
                    fabricante.setNome(rs.getString("nome"));
                    fabricantes.add(fabricante);
                }
                return Optional.of(fabricantes);
            }catch ( Exception e){
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    public Optional<Fabricante> selectFromId(Integer id){
        createTableIfNotExisits();
        try{
            String sql = "SELECT id, nome FROM "+classNamed+" WHERE id = "+id;
            ResultSet rs = this.realizeOperation(sql);

            rs.next();
            Fabricante fabricante = new Fabricante();
            fabricante.setNome(rs.getString("nome"));
            fabricante.setId(rs.getInt("id"));

            return Optional.of(fabricante);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void insert(Fabricante fabricante){
        createTableIfNotExisits();
        String sql = "INSERT INTO "+classNamed+" (nome) VALUES ('"+fabricante.getNome()+"')";
        this.realizeOperation(sql) ;
    }

    public void upadte(Fabricante fabricante){
        createTableIfNotExisits();
        List<String> queries = new ArrayList<>();
        queries.add("UPDATE "+classNamed+" SET nome = '"+fabricante.getNome()+"' WHERE id = "+ fabricante.getId());
        queries.forEach(this::realizeOperation);
    }
}
