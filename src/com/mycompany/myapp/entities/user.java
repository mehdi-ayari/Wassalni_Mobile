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

    public user() {
    }
    public enum roleus {
  client,
  administrateur,
  chauffeur,
  entreprise;	
}
    
    private int id,telephone,nbr_emp;
    private String username,username_canonical,
            email,email_canonical,salt,password,confirmation_token,
            firstName,lastName,adresse;
    
    private roleus role_user;

    public user(int id, int telephone, int nbr_emp, String username, String username_canonical, String email, String email_canonical, String salt, String password, String confirmation_token, String firstName, String lastName, String adresse, roleus role_user) {
        this.id = id;
        this.telephone = telephone;
        this.nbr_emp = nbr_emp;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.salt = salt;
        this.password = password;
        this.confirmation_token = confirmation_token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresse = adresse;
        this.role_user = role_user;
    }

    public user(int telephone, int nbr_emp, String username, String username_canonical, String email, String email_canonical, String salt, String password, String confirmation_token, String firstName, String lastName, String adresse, roleus role_user) {
        this.telephone = telephone;
        this.nbr_emp = nbr_emp;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.salt = salt;
        this.password = password;
        this.confirmation_token = confirmation_token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresse = adresse;
        this.role_user = role_user;
    }

    public int getId() {
        return id;
    }

    public int getTelephone() {
        return telephone;
    }

    public int getNbr_emp() {
        return nbr_emp;
    }

    public String getUsername() {
        return username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdresse() {
        return adresse;
    }

    public roleus getRole_user() {
        return role_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setNbr_emp(int nbr_emp) {
        this.nbr_emp = nbr_emp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRole_user(roleus role_user) {
        this.role_user = role_user;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", telephone=" + telephone + ", nbr_emp=" + nbr_emp + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", salt=" + salt + ", password=" + password + ", confirmation_token=" + confirmation_token + ", firstName=" + firstName + ", lastName=" + lastName + ", adresse=" + adresse + ", role_user=" + role_user + '}';
    }
    
    
    
    
}
