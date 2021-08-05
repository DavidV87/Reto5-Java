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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author elkin
 */
public class Story {
    private Integer id;
    private Integer userId;
    private Integer storyTypeId;
    private String title;
    private String shortDescription;
    private String story;
    private Date timestamp;
    private String autor;
    private Date update;
    private ArrayList<String> storyTitles;
    private ArrayList<String> storyAuthors;
    private ArrayList<Story> stories;
    private Conector conector;
    private Controller controller;
    
    public Story(){
        storyTitles = new ArrayList<>();
        storyAuthors = new ArrayList<>();
        stories = new ArrayList<>();
        
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
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the storyTypeId
     */
    public Integer getStoryTypeId() {
        return storyTypeId;
    }

    /**
     * @param storyTypeId the storyTypeId to set
     */
    public void setStoryTypeId(Integer storyTypeId) {
        this.storyTypeId = storyTypeId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return the story
     */
    public String getStory() {
        return story;
    }

    /**
     * @param story the story to set
     */
    public void setStory(String story) {
        this.story = story;
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

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the update
     */
    public Date getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Date update) {
        this.update = update;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

   
    public boolean loadStoryTitle() {
        conector = new Conector();
        this.storyTitles.clear();
        try(Connection c = conector.connect()){
            String query = "SELECT DISTINCT title "
                    + "FROM story "
                    + "ORDER BY title ASC ";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                this.storyTitles.add(result.getString("title"));
            }
            c.close();
            return true;     
        }catch (Exception e){
            
        }
        return false;
    }
    
     public boolean loadStoryAuthor() {
        conector = new Conector();
        this.storyAuthors.clear();
        try(Connection c = conector.connect()){
            String query = "SELECT DISTINCT autor "
                    + "FROM story "
                    + "ORDER BY autor ASC ";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                this.storyAuthors.add(result.getString("autor"));
            }
            c.close();
            return true;     
        }catch (Exception e){
            System.out.println("No hay elementos");
        }
        return false;
    }


    /**
     * @return the storyTitles
     */
    public ArrayList<String> getStoryTitles() {
        return storyTitles;
    }

    /**
     * @return the storyAuthors
     */
    public ArrayList<String> getStoryAuthors() {
        return storyAuthors;
    }

    public boolean createQueryStory(String word, Integer type, String title, String autor, Integer id) {
        String query = null;
        PreparedStatement statement = null;
        String                  where =  "WHERE (s.story LIKE ? OR s.short_description LIKE ?)";
        if(type == 0)           where += " AND story_type_id <> ?";
        else                    where += " AND story_type_id = ?";
        if (title.equals(""))   where += " AND title <> ?";
        else                    where += " AND title = ?";
        if (autor.equals(""))   where += " AND autor <> ? ";
        else                    where += " AND autor = ? ";
        conector = new Conector();
        stories.clear();
        try(Connection c = conector.connect()){
            if (id == null){
                query ="SELECT s.id, s.user_id, s.story_type_id, s.title, s.short_description, s.story, s.time_stamp, s.autor, s.time_update "
                           +"FROM story AS s "
                           + where +"; ";
                statement = c.prepareStatement(query);
                statement.setString(1, word);
                statement.setString(2, word);
                statement.setInt(3, type);
                statement.setString(4, title);
                statement.setString(5, autor);
                               
            }else{
                query ="SELECT s.id, s.user_id, s.story_type_id, s.title, s.short_description, s.story, s.time_stamp, s.autor, s.time_update " 
                    +  "FROM story AS s " 
                    +  "INNER JOIN user AS u ON s.user_id = u.id " 
                    +  "WHERE s.user_id=?;";
                
                statement = c.prepareStatement(query);
                statement.setInt(1, id);
                
            }
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                Story s = new Story();
                
                s.id = result.getInt(1);
                
                s.userId = result.getInt(2);
                
                s.storyTypeId = result.getInt(3);
                
                s.title = result.getString(4);
                
                s.shortDescription = result.getString(5);
                
                s.story =result.getString(6);
                
                s.timestamp = result.getDate(7);
                
                s.autor = result.getString(8);
                stories.add(s);
            }
            return true;     
        }catch (Exception e){
            System.out.println("Base de datos vacia");
        }
        return false;
    }

    /**
     * @return the stories
     */
    public ArrayList<Story> getStories() {
        return stories;
    }

    public boolean updateUserStory() {
        Integer key = null;
        conector = new Conector();
        try(Connection c = conector.connect()){
            String query =  "UPDATE story SET title=?, autor=?, story_type_id=?, story=?, short_description=?"
                        +   "WHERE id = ?;";
            PreparedStatement statement = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, this.title);
            statement.setString(2, this.autor);
            statement.setInt(3, this.storyTypeId);
            statement.setString(4, this.story);
            statement.setString(5, this.shortDescription);
            statement.setInt(6, this.id);
            int rows = statement.executeUpdate();
            if (rows > 0){
                
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next())
                    key =  generatedKeys.getInt(1);
            }
            c.close();
            return true;
        }catch (Exception e){
            System.out.println("");
        }
        return false;

    }

    public boolean deleteteUserStory() {
        conector = new Conector();
        try(Connection c = conector.connect()){
            String query = "DELETE FROM story WHERE id= ?";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, this.id);
            statement.executeUpdate();
            return true;
        } catch(Exception e){
            System.err.println("No se puede eliminar el registro");
        }
        return false;
    }

    public boolean createUserStory() {
        Integer key = null;
        conector = new Conector();
        try(Connection c = conector.connect()){
            
            String query =  "INSERT INTO story (user_id, story_type_id, title, short_description, story, autor)"
                        +   "VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, this.userId);
            
            statement.setInt(2, this.storyTypeId);
            
            statement.setString(3, this.title);
            
            statement.setString(4, this.shortDescription);
            
            statement.setString(5, this.story);
            
            statement.setString(6, this.autor);
            
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

}
