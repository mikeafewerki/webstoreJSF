/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545;

import edu.mum.cs545.domain.UserEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class UserBean implements Serializable {

    private String userId;
    private String password;
    private boolean loggedin;

    private List<UserEntity> users;

    @EJB //this annotation causes the container to inject this dependency
    private edu.mum.cs545.db.UserEntityFacade ejbUserFacade;

    @PostConstruct  //this annotation causes this method to run after the constructor completes  
    public void init() { //create a few tea products, place in database, and load into the teaEntities list

        UserEntity user = new UserEntity();
        user.setUserId("mike");
        user.setPassword("mike123");

        ejbUserFacade.create(user);

        List<UserEntity> userEntities = ejbUserFacade.findAll();

        users = new ArrayList<UserEntity>();

        for (UserEntity userEnt : userEntities) {

            users.add(userEnt);

        }

    }

    public UserBean() {
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public String checkLogin() {
        if (authenticate()) {
            return "welcome?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Username / Passowrd",
                    "Please enter correct username / Password"));
            return null;
        }
    }

    public boolean authenticate() {
        for (UserEntity user : users) {
            if (user.getUserId().equalsIgnoreCase(userId) && user.getPassword().equals(password)) {
                this.loggedin = true;
                return loggedin;
            }
        }
        loggedin = false;
        return loggedin;
    }
}
