/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controller;
import Database.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author elkin
 */
public class StoryType {
    private Integer id;
    private String name;
    private Controller controller;
    private Conector conector;
    private ArrayList<String> storyTypes;

    public StoryType(){
        storyTypes = new ArrayList<>();
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

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    public boolean loadStoryTypes(){
        conector = new Conector();
        this.storyTypes.clear();
        try(Connection c = conector.connect()){
            String query = "SELECT id, name "
                    + "FROM story_type "
                    + "ORDER BY id ASC ";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                this.storyTypes.add(result.getString("name"));
            }
            c.close();
            return true;     
        }catch (Exception e){
            System.out.println("No hay elementos");
        }
        return false;
    }

    /**
     * @return the storyTypes
     */
    public ArrayList<String> getStoryTypes() {
        return storyTypes;
    }


   
}
