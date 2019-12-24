package com.hepta.mercado.persistence.abstarcts;

import com.hepta.mercado.persistence.abstarcts.AbstractOperation;

import java.sql.ResultSet;

public abstract class AbstractDAO {

    AbstractOperation abstractOperation;

    public ResultSet realizeOperation(String query){

        try{
            abstractOperation = new AbstractOperation();
            ResultSet rs =  abstractOperation.whithDefaultDriver().createStatement().whithQuery(query)
                    .executeAndClose();
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void closeOperators(){
        abstractOperation.closeOperators();
    }
}
