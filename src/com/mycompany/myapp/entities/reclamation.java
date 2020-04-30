/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author jawha
 */
public class reclamation {
    private int id_reclamtion,user_id;
    private String titre,description;
    private Date date_reclamtion;

    public reclamation(int id_reclamtion, int user_id, String titre, String description, Date date_reclamtion) {
        this.id_reclamtion = id_reclamtion;
        this.user_id = user_id;
        this.titre = titre;
        this.description = description;
        this.date_reclamtion = date_reclamtion;
    }

    public reclamation(int user_id, String titre, String description, Date date_reclamtion) {
        this.user_id = user_id;
        this.titre = titre;
        this.description = description;
        this.date_reclamtion = date_reclamtion;
    }

    public int getId_reclamtion() {
        return id_reclamtion;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_reclamtion() {
        return date_reclamtion;
    }

    public void setId_reclamtion(int id_reclamtion) {
        this.id_reclamtion = id_reclamtion;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_reclamtion(Date date_reclamtion) {
        this.date_reclamtion = date_reclamtion;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id_reclamtion=" + id_reclamtion + ", user_id=" + user_id + ", titre=" + titre + ", description=" + description + ", date_reclamtion=" + date_reclamtion + '}';
    }
    
    
    
}
