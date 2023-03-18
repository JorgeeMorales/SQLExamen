package com.example.sqlexamen;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConexionSQL {
    Connection conn;

    public Connection open(){
        String url="jdbc:mysql://ugjbzvbktxgvsx6m:Skxgdvkb6KxAtvqCfOy1@bfciqoxrxgnp4aupsken-" +
                "mysql.services.clever-cloud.com:3306/bfciqoxrxgnp4aupsken";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void close(){
        if (conn!=null){
            try{
                conn.close();
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Exception controlada.");
            }
        }
    }

}
