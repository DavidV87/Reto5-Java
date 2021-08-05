/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Controller.Controller;
import Database.Conector;
import Model.Story;
import Model.StoryType;
import Model.User;
import View.ViewStory;

/**
 *
 * @author elkin
 */
public class Principal {
    
    private static Conector conector;
    private static Controller controller;
    private static ViewStory view;
    private static Story story;
    private static StoryType storyType;
    private static User user;
    
    public static void main(String[] args) {
        conector = new Conector();
        controller = new Controller();
        view = new ViewStory();
        story = new Story();
        storyType = new StoryType();
        user = new User();
        
        controller.setView(view);
        controller.setStory(story);
        controller.setStoryType(storyType);
        controller.setUser(user);
        
        view.setController(controller);
        story.setController(controller);
        storyType.setController(controller);
        user.setController(controller);
        
        view.setVisible(true);
        
        
    }
       
    
}
