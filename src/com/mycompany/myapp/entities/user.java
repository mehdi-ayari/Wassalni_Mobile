/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author jawha
 */


public class user {
    
    protected int id;
    protected String username;
    protected String lastname;
    protected String firstname;
    protected String image;
    protected String email;
    protected String password;
    protected String roles;


    public user(){
        
    }

    public user(String lastname, String firstname, String email, String roles) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.roles = roles;
  
    }

    public user(String lastname, String firstname, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    
    }           


    public user(String username, String firstname, String lastname, String email, String roles) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.roles = roles;
     
    }

    public user(int id, String username, String lastname, String firstname, String image, String email, String password, String roles) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.image = image;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", username=" + username + ", lastname=" + lastname + ", firstname=" + firstname + ", image=" + image + ", email=" + email + ", password=" + password + ", roles=" + roles + '}';
    }
    
    

   

    

    

}

