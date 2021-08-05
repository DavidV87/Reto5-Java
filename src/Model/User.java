/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controller;
import Database.Conector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author elkin
 */
public class User {
    private Integer id;
    private String name;
    private String lastName;
    private String userName;
    private String pass;
    private Date timestamp;
    private Controller controller;
    private Conector conector;

    public User() {
    
    }
    
    
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the useName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param useName the useName to set
     */
    public void setUserName(String useName) {
        this.userName = useName;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    public boolean getByUserName(String userName){
        conector = new Conector();
        try(Connection c = conector.connect()){
            String query = "SELECT id, name, last_name, user_name, pass, time_stamp "
                    + "FROM user "
                    + "WHERE user_name = ?;";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, userName);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                this.id = result.getInt(1);
                this.name = result.getString(2);
                this.lastName= result.getString(3);
                this.userName = result.getString(4);
                this.pass = result.getString(5);
                this.timestamp = result.getDate(6);
                c.close();
                return true; 
            }
                 
        }catch (Exception e){
            System.out.println("esta es la excepcion" +userName);
        }
        return false;
    }

    public boolean createUser() {
        Integer key = null;
        conector = new Conector();
        try(Connection c = conector.connect()){
            String query =  "INSERT INTO user (name, last_name, user_name, pass)"
                        +   "VALUES (?,?,?,?);";
            PreparedStatement statement = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, this.name);
            statement.setString(2, this.lastName);
            statement.setString(3, this.userName);
            statement.setString(4, this.pass);
            int rows = statement.executeUpdate();
            if (rows > 0){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next())
                    key =  generatedKeys.getInt(1);
            }
            c.close();
            return true;
        }catch (Exception e){
            System.out.println("esta es la excepcion");
        }
        return false;

    }

    public boolean updateUser() {
        Integer key = null;
        conector = new Conector();
        try(Connection c = conector.connect()){
            String query =  "UPDATE user SET name=?, last_name=?, pass=?"
                        +   "WHERE user_name = ?;";
            PreparedStatement statement = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, this.name);
            statement.setString(2, this.lastName);
            statement.setString(3, this.pass);
            statement.setString(4, this.userName);
            int rows = statement.executeUpdate();
            if (rows > 0){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next())
                    key =  generatedKeys.getInt(1);
            }
            c.close();
            return true;
        }catch (Exception e){
            System.out.println("esta es la excepcion");
        }
        return false;

    }

    public boolean deleteteUser() {
        conector = new Conector();
        try(Connection c = conector.connect()){
            String query = "DELETE FROM user WHERE user_name= ?";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, this.getUserName());
            statement.executeUpdate();
            return true;
        } catch(Exception e){
            System.err.println("No se puede eliminar el registro");
        }
        return false;
    }

  
    
}
