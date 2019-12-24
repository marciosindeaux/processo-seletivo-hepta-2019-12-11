package com.hepta.mercado.persistence;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.entity.Produto;
import com.hepta.mercado.persistence.abstarcts.AbstractDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO extends AbstractDAO {

    private FabricanteDAO fabricanteDAO = new FabricanteDAO();

    private boolean tableCreated = false;

    private String classNamed = Arrays.stream(Produto.class.getName().split("\\.")).reduce((a, b) -> b).orElse(null);

    public void createTableIfNotExisits(){
        if(!tableCreated){
            fabricanteDAO.createTableIfNotExisits();
            String sql = "CREATE TABLE IF NOT EXISTS "+ classNamed+"(" +
                    " id INTEGER NOT NULL AUTO_INCREMENT," +
                    " nome VARCHAR(255) ," +
                    " id_fabricante INTEGER NOT NULL," +
                    " volume DOUBLE," +
                    " unidade VARCHAR(255)," +
                    " estoque INTEGER NOT NULL," +
                    " PRIMARY KEY (id))";
            this.realizeOperation(sql);
            tableCreated = !tableCreated;
        }
    }

    public Optional<List<Produto>> selectAll(){
        createTableIfNotExisits();
        try {
            String sql = "SELECT " +
                    "id, " +
                    "nome, " +
                    "id_fabricante, " +
                    "volume, " +
                    "unidade, " +
                    "estoque " +
                "FROM "+classNamed;
            ResultSet resultSet = this.realizeOperation(sql);
            if(resultSet != null){
                List<Produto> produtos = new ArrayList<>();

                while(resultSet.next()){
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setNome(resultSet.getString("nome"));
                    produto.setUnidade(resultSet.getString("unidade"));
                    produto.setEstoque(resultSet.getInt("estoque"));
                    produto.setVolume(resultSet.getDouble("volume"));
                    produto.setFabricante(fabricanteDAO.selectFromId(resultSet.getInt("id_fabricante")).orElse(null));
                    produtos.add(produto);
                }
                this.closeOperators();
                return Optional.of(produtos);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeOperators();
        return Optional.empty();
    }

    public Optional<Produto> selectFromId(Integer id){
        createTableIfNotExisits();
        try {
            String sql = "SELECT " +
                    "id, " +
                    "nome, " +
                    "id_fabricante, " +
                    "volume, " +
                    "unidade, " +
                    "estoque " +
                    "FROM "+classNamed + " WHERE id = "+id;
            ResultSet resultSet = this.realizeOperation(sql);
            if(resultSet != null){
                resultSet.next();
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setUnidade(resultSet.getString("unidade"));
                produto.setEstoque(resultSet.getInt("estoque"));
                produto.setVolume(resultSet.getDouble("volume"));
                produto.setFabricante(fabricanteDAO.selectFromId(resultSet.getInt("id_fabricante")).orElse(null));

                return Optional.of(produto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void insert(Produto produto){
        createTableIfNotExisits();
            String sql = "INSERT INTO "+classNamed+" (nome, id_fabricante , volume , unidade , estoque )" +
                    " values ('"+produto.getNome()+"',"+produto.getFabricante().getId()+","+produto.getVolume()+",'"+produto.getUnidade()+"',"+produto.getEstoque()+")";
            this.realizeOperation(sql);
    }

    public void delete(Integer id){
        createTableIfNotExisits();
        String sql = "DELETE FROM "+classNamed+" where id = "+id;
        this.realizeOperation(sql);
    }

    public void update(Produto produto){
        createTableIfNotExisits();
        fabricanteDAO.upadte(produto.getFabricante());
        List<String> queries = new ArrayList<>();
        queries.add("UPDATE "+classNamed+" SET nome = '"+produto.getNome()+"' where id = "+produto.getId());
        queries.add("UPDATE "+classNamed+" SET id_fabricante = "+produto.getFabricante().getId()+" where id = "+produto.getId());
        queries.add("UPDATE "+classNamed+" SET volume = "+produto.getVolume()+" where id = "+produto.getId());
        queries.add("UPDATE "+classNamed+" SET unidade = '"+produto.getUnidade()+"' where id = "+produto.getId());
        queries.add("UPDATE "+classNamed+" SET estoque = "+produto.getEstoque()+" where id = "+produto.getId());
        queries.forEach(this::realizeOperation);
    }
}
