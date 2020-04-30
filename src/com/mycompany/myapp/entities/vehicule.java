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
public class vehicule {
    private int id_vehicule,id_chauffeur;
    private String licence,permis,photo,marque;

    public vehicule(int id_vehicule, int id_chauffeur, String licence, String permis, String photo, String marque) {
        this.id_vehicule = id_vehicule;
        this.id_chauffeur = id_chauffeur;
        this.licence = licence;
        this.permis = permis;
        this.photo = photo;
        this.marque = marque;
    }

    public vehicule(int id_chauffeur, String licence, String permis, String photo, String marque) {
        this.id_chauffeur = id_chauffeur;
        this.licence = licence;
        this.permis = permis;
        this.photo = photo;
        this.marque = marque;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public String getLicence() {
        return licence;
    }

    public String getPermis() {
        return permis;
    }

    public String getPhoto() {
        return photo;
    }

    public String getMarque() {
        return marque;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "vehicule{" + "id_vehicule=" + id_vehicule + ", id_chauffeur=" + id_chauffeur + ", licence=" + licence + ", permis=" + permis + ", photo=" + photo + ", marque=" + marque + '}';
    }
    
    
    
}
