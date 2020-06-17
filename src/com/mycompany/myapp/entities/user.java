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
    
    private int id,telephone,nbr_emp;
    private String username,username_canonical,
            email,email_canonical,salt,password,confirmation_token,
            firstName,lastName,adresse,role_user;
    protected String lastname;
    protected String firstname;
    protected String image;
    protected String roles;

    public user(int id, int telephone, int nbr_emp, String username, String username_canonical, String email, String email_canonical, String salt, String password, String confirmation_token, String firstName, String lastName, String adresse, String role_user) {
        this.id = id;
        this.telephone = telephone;
        this.nbr_emp = nbr_emp;
   
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

    public user() {
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

    public String getRole_user() {
        return role_user;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
	}
    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", username=" + username + ", lastname=" + lastname + ", firstname=" + firstname + ", image=" + image + ", email=" + email + ", password=" + password + ", roles=" + roles + '}';
    }
    
    

   

    

    

}

