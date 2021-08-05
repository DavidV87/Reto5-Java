/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elkin
 */
public class Conector {
    private String driver = "com.mysql.jdbc.Driver";
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3307/story_db";
    
    public Conector(){
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            System.out.println("Problemas con el driver");
        }
   }
    public Connection connect(){
        Connection c = null;
        try{
            c = DriverManager.getConnection(this.url, this.user, this.password);
        }catch (SQLException e){
            System.out.println("No hay conexion con la base de datos");
        }
        return c;
    }
}

    
