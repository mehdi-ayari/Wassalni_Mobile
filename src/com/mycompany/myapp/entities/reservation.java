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
    
    private int id_res,user_id_chauffeur,user_id_client,id_colis;
    private String destination,pointdepart,objet,type_reservation,date_reservation;
   
    private float prix;
//    private obj objet;
//    private typres type_reservation;

    public reservation() {
    }
    
    

    public reservation(int id_res, int user_id_chauffeur, int user_id_client, int id_colis, String destination, String pointdepart, String date_reservation, String objet, String type_reservation) {
        this.id_res = id_res;
        this.user_id_chauffeur = user_id_chauffeur;
        this.user_id_client = user_id_client;
        this.id_colis = id_colis;
        this.destination = destination;
        this.pointdepart = pointdepart;
        this.date_reservation = date_reservation;
        this.objet = objet;
        this.type_reservation = type_reservation;
    }

    public reservation(int user_id_chauffeur, int user_id_client, int id_colis, String destination, String pointdepart, String date_reservation, String objet, String type_reservation) {
        this.user_id_chauffeur = user_id_chauffeur;
        this.user_id_client = user_id_client;
        this.id_colis = id_colis;
        this.destination = destination;
        this.pointdepart = pointdepart;
        this.date_reservation = date_reservation;
        this.objet = objet;
        this.type_reservation = type_reservation;
    }

    public reservation(String destination, String pointdepart, String date_reservation, String objet, String type_reservation) {
        this.destination = destination;
        this.pointdepart = pointdepart;
        this.date_reservation = date_reservation;
        this.objet = objet;
        this.type_reservation = type_reservation;
    }

    public reservation(int user_id_chauffeur, int user_id_client, String destination, String pointdepart, String objet, String type_reservation, String date_reservation, float prix) {
        this.user_id_chauffeur = user_id_chauffeur;
        this.user_id_client = user_id_client;
        this.destination = destination;
        this.pointdepart = pointdepart;
        this.objet = objet;
        this.type_reservation = type_reservation;
        this.date_reservation = date_reservation;
        this.prix = prix;
    }

    public reservation(String destination) {
        this.destination = destination;
    }

    public reservation(int user_id_chauffeur, int user_id_client, String destination, String pointdepart, String objet, String type_reservation, float prix) {
        this.user_id_chauffeur = user_id_chauffeur;
        this.user_id_client = user_id_client;
        this.destination = destination;
        this.pointdepart = pointdepart;
        this.objet = objet;
        this.type_reservation = type_reservation;
        this.prix = prix;
    }

    
    
    
    

    public int getId_res() {
        return id_res;
    }

    public int getUser_id_chauffeur() {
        return user_id_chauffeur;
    }

    public int getUser_id_client() {
        return user_id_client;
    }

    public int getId_colis() {
        return id_colis;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate_reservation() {
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

    public void setUser_id_chauffeur(int user_id_chauffeur) {
        this.user_id_chauffeur = user_id_chauffeur;
    }

    public void setUser_id_client(int user_id_client) {
        this.user_id_client = user_id_client;
    }

    public void setId_colis(int id_colis) {
        this.id_colis = id_colis;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate_reservation(String date_reservation) {
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
