package com.hepta.mercado.persistence.abstarcts;

import java.sql.*;

public class AbstractOperation {

    // JDBC driver name and database URL
    private static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mercado?useTimezone=true&serverTimezone=UTC&autoReconnect=true&createDatabaseIfNotExist=true";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    private Connection conn = null;
    private Statement stmt = null;

    private String query ;

    public static AbstractOperation createOperation(){
        return new AbstractOperation();
    }

    public AbstractOperation whithDefaultDriver() throws ClassNotFoundException {
        Class.forName(DEFAULT_DRIVER);
        return this;
    }

    public AbstractOperation createStatement() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        return this;
    }

    public AbstractOperation whithQuery(String query){
        this.query = query;
        return this;
    }

    public ResultSet executeAndClose() throws SQLException {

        ResultSet resultSet;
        System.out.println(query);
        if(query.split(" ")[0].equalsIgnoreCase("select")){
            resultSet = stmt.executeQuery(query);
        }else{
            stmt.executeUpdate(query);
            resultSet = null;
        }
        return resultSet;
    }

    public void closeOperators(){
        try{
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
