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
public class colis {
   private int id_colis;
   private float poids;
   private String contenu;

    public colis(int id_colis, float poids, String contenu) {
        this.id_colis = id_colis;
        this.poids = poids;
        this.contenu = contenu;
    }

    public colis(float poids, String contenu) {
        this.poids = poids;
        this.contenu = contenu;
    }

    public colis() {
    }

    public int getId_colis() {
        return id_colis;
    }

    public float getPoids() {
        return poids;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId_colis(int id_colis) {
        this.id_colis = id_colis;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "colis{" + "id_colis=" + id_colis + ", poids=" + poids + ", contenu=" + contenu + '}';
    }
           
          
    
}
