/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Story;
import Model.StoryType;
import Model.User;
import View.ViewStory;
import java.util.ArrayList;


/**
 *
 * @author elkin
 */
public class Controller {

    private ViewStory view;
    private Story story;
    private StoryType storyType;
    private User user;
        
    public void setView(ViewStory view) {
        this.view = view;
    }

    public void setStory(Story story) {
        this.story = story;
        
    }

    public void setStoryType(StoryType storyType) {
        this.storyType = storyType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean validateEmptyField(String name, String lastName, String user, char[] password) {
        if(name.isEmpty() || lastName.isEmpty() || user.isEmpty() || password.length == 0){
            return false;
        }
            this.user.setName(name);
            this.user.setLastName(lastName);
            this.user.setUserName(user);
            this.user.setPass(String.valueOf(password));
            return true;
            
        
    }

    public boolean createUser() {
        return this.user.createUser();
    }

    public boolean getByUserName(String userName) {
        return this.user.getByUserName(userName);
    }

    public boolean validatePassword(char[] password) {
        return this.user.getPass().equals(String.valueOf(password));
    }

    public String getLastName() {
        return this.user.getLastName();
    }

    public String getName() {
        return this.user.getName();
    }

    public String getUserName() {
        return this.user.getUserName();
    }

    public String getPass() {
        return this.user.getPass();
    }

    public boolean updateUser(){
        return this.user.updateUser();
    }

    public boolean deleteUser() {
        return this.user.deleteteUser();
    }

    public boolean loadStoryTypes() {
        return storyType.loadStoryTypes();
    }

    public Iterable<String> getStoryTypes() {
        return this.storyType.getStoryTypes();
    }

    public Iterable<String> getStoryTitle() {
       return this.story.getStoryTitles();
    }

    public Iterable<String> getStoryAutor() {
        return this.story.getStoryAuthors();
                
    }

    public boolean loadStoryTitle() {
        return this.story.loadStoryTitle();
    }

    public boolean loadStoryAutor() {
        return this.story.loadStoryAuthor();
    }

    public boolean createQueryStory(String word, Integer type, String title, String autor,Integer id) {
        word = "%"+word+"%";
        return story.createQueryStory(word,type,title,autor,id);
    }

    public ArrayList<Story> getStories() {
        return story.getStories();
    }

    
    public Integer getId() {
        return user.getId();
    }

    public boolean validateEmptyField(String title, String autor, Integer type, String story, String description) {
        if(title.isEmpty() || autor.isEmpty() || type == -1 || story.isEmpty() || description.isEmpty()){
            return false;
        }
            this.story.setTitle(title);
            this.story.setAutor(autor);
            this.story.setStoryTypeId(type);
            this.story.setStory(story);
            this.story.setShortDescription(description);
            this.story.setUserId(this.getId());
            return true;
    }

    public boolean updateUserStory() {
        return this.story.updateUserStory();
    }

    public void setIdStory(Integer id) {
        this.story.setId(id);
    }

    public boolean deleteUserStory() {
        return this.story.deleteteUserStory();
    }

    public boolean createUserStory() {
        return this.story.createUserStory();
    }

    public void setUserId(Integer id) {
        this.story.setUserId(id);
    }

    public Integer getUserId() {
        return this.user.getId();
    }

    
}
