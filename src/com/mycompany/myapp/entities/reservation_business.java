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
public class reservation_business {
    private int id_res_business,user_id_entreprise,etat;
    private Date date_reservation;
    private String destination,nom_client_entreprise,prenon_client_entreprise,point_depart,date_depart;

    public reservation_business(int id_res_business, int user_id_entreprise, String date_depart, Date date_reservation, String destination, String nom_client_entreprise, String prenon_client_entreprise, String point_depart) {
        this.id_res_business = id_res_business;
        this.user_id_entreprise = user_id_entreprise;
        this.date_depart = date_depart;
        this.date_reservation = date_reservation;
        this.destination = destination;
        this.nom_client_entreprise = nom_client_entreprise;
        this.prenon_client_entreprise = prenon_client_entreprise;
        this.point_depart = point_depart;
    }

    public reservation_business(int user_id_entreprise, String date_depart, Date date_reservation, String destination, String nom_client_entreprise, String prenon_client_entreprise, String point_depart) {
        this.user_id_entreprise = user_id_entreprise;
        this.date_depart = date_depart;
        this.date_reservation = date_reservation;
        this.destination = destination;
        this.nom_client_entreprise = nom_client_entreprise;
        this.prenon_client_entreprise = prenon_client_entreprise;
        this.point_depart = point_depart;
    }

    public reservation_business() {
    }
    

    public int getId_res_business() {
        return id_res_business;
    }

    public int getUser_id_entreprise() {
        return user_id_entreprise;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public String getDestination() {
        return destination;
    }

    public String getNom_client_entreprise() {
        return nom_client_entreprise;
    }

    public String getPrenon_client_entreprise() {
        return prenon_client_entreprise;
    }

    public String getPoint_depart() {
        return point_depart;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    

    public void setId_res_business(int id_res_business) {
        this.id_res_business = id_res_business;
    }

    public void setUser_id_entreprise(int user_id_entreprise) {
        this.user_id_entreprise = user_id_entreprise;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNom_client_entreprise(String nom_client_entreprise) {
        this.nom_client_entreprise = nom_client_entreprise;
    }

    public void setPrenon_client_entreprise(String prenon_client_entreprise) {
        this.prenon_client_entreprise = prenon_client_entreprise;
    }

    public void setPoint_depart(String point_depart) {
        this.point_depart = point_depart;
    }

    @Override
    public String toString() {
        return "reservation_business{" + "id_res_business=" + id_res_business + ", user_id_entreprise=" + user_id_entreprise + ", date_depart=" + date_depart + ", date_reservation=" + date_reservation + ", destination=" + destination + ", nom_client_entreprise=" + nom_client_entreprise + ", prenon_client_entreprise=" + prenon_client_entreprise + ", point_depart=" + point_depart + '}';
    }
    
    
    
}
