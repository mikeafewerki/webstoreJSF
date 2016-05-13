/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545;

import edu.mum.cs545.domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author 984986
 */
@Named
@SessionScoped
public class UserBean implements Serializable{
    private String userId;
    private String password;
    private boolean loggedin;
    
    private List<User> users;
    
    public UserBean() {
        users = new ArrayList<User>();
        
        users.add(new User("mike","mike123"));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }
    
    public String checkLogin(){
        if(authenticate())
            return "welcome?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return null;
        }
    }
    
    public boolean authenticate(){
        for(User user : users)
            if(user.getUserId().equalsIgnoreCase(userId) && user.getPassword().equals(password)){
                this.loggedin = true;
                return loggedin;
            }
        loggedin = false;
        return loggedin;
    }
}
