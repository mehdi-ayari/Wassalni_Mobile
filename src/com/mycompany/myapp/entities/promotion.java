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
public class promotion {
    private int id_promotion,user_id,reservation_id,pourcentage;
    private String nom,description;
    private Date date_debut,date_fin;

    public promotion(int id_promotion, int user_id, int reservation_id, int pourcentage, String nom, String description, Date date_debut, Date date_fin) {
        this.id_promotion = id_promotion;
        this.user_id = user_id;
        this.reservation_id = reservation_id;
        this.pourcentage = pourcentage;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public promotion(int user_id, int reservation_id, int pourcentage, String nom, String description, Date date_debut, Date date_fin) {
        this.user_id = user_id;
        this.reservation_id = reservation_id;
        this.pourcentage = pourcentage;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "promotion{" + "id_promotion=" + id_promotion + ", user_id=" + user_id + ", reservation_id=" + reservation_id + ", pourcentage=" + pourcentage + ", nom=" + nom + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    
    
    
}
