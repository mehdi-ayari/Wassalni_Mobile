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
public class commentaire {
    private int id_commentaire,id_news,id_user;
    private String text;

    public commentaire(int id_commentaire, int id_news, int id_user, String text) {
        this.id_commentaire = id_commentaire;
        this.id_news = id_news;
        this.id_user = id_user;
        this.text = text;
    }

    public commentaire(int id_news, String text) {
        this.id_news = id_news;
        this.text = text;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public int getId_news() {
        return id_news;
    }

    public int getId_user() {
        return id_user;
    }

    public String getText() {
        return text;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setId_news(int id_news) {
        this.id_news = id_news;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id_commentaire=" + id_commentaire + ", id_news=" + id_news + ", id_user=" + id_user + ", text=" + text + '}';
    }
    
    
    
}
