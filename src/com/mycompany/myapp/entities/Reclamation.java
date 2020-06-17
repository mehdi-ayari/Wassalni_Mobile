/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Reclamation {
    
    private int id ;
    private String titre;
    private String description;
    private String etat;
    private Date date;
    private String  type ; 

    public Reclamation() {
    }

    public Reclamation(int id, String titre, String description, String etat, Date date, String type) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", etat=" + etat + ", date=" + date + ", type=" + type + '}';
    }

    

    

}

