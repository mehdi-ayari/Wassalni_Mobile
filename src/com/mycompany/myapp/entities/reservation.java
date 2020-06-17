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


public class reservation {
    
    public enum typres {
  Taxi,
  Privée,
  camion;	
}
   
        public enum obj {
  colis,
  passager;
}
    
    private int id_res;
    private String destination,pointdepart,objet,type_reservation;
    private user user_id_chauffeur,user_id_client;
    private Date date_reservation;
    private colis id_colis;
   
    private float prix;
//    private obj objet;
//    private typres type_reservation;

    public reservation() {
    }
    
    

    
    

    public reservation(String destination, String pointdepart, Date date_reservation, String objet, String type_reservation) {
        this.destination = destination;
        this.pointdepart = pointdepart;
        this.date_reservation = date_reservation;
        this.objet = objet;
        this.type_reservation = type_reservation;
    }

   

    
    

    
    
    
    

    public int getId_res() {
        return id_res;
    }

    public user getUser_id_chauffeur() {
        return user_id_chauffeur;
    }

    public user getUser_id_client() {
        return user_id_client;
    }

    public colis getId_colis() {
        return id_colis;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public String getObjet() {
        return objet;
    }

    public String getType_reservation() {
        return type_reservation;
    }

    public String getPointdepart() {
        return pointdepart;
    }

    public float getPrix() {
        return prix;
    }

    
    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public void setUser_id_chauffeur(user user_id_chauffeur) {
        this.user_id_chauffeur = user_id_chauffeur;
    }

    public void setUser_id_client(user user_id_client) {
        this.user_id_client = user_id_client;
    }

    public void setId_colis(colis id_colis) {
        this.id_colis = id_colis;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setType_reservation(String type_reservation) {
        this.type_reservation = type_reservation;
    }

    public void setPointdepart(String pointdepart) {
        this.pointdepart = pointdepart;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    
    @Override
    public String toString() {
        return "reservation{" + "id_res=" + id_res + ", user_id_chauffeur=" + user_id_chauffeur + ", user_id_client=" + user_id_client + ", id_colis=" + id_colis + ", destination=" + destination + ", pointdepart=" + pointdepart + ", date_reservation=" + date_reservation + ", objet=" + objet + ", type_reservation=" + type_reservation + '}';
    }

    
    
    
    
    
    
}
