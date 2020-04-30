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
public class reclamation_voyage {
    private int id_reclamation_voyage,id_voy;
    private String titre,commentaire;

    public reclamation_voyage(int id_reclamation_voyage, int id_voy, String titre, String commentaire) {
        this.id_reclamation_voyage = id_reclamation_voyage;
        this.id_voy = id_voy;
        this.titre = titre;
        this.commentaire = commentaire;
    }

    public reclamation_voyage(int id_voy, String titre, String commentaire) {
        this.id_voy = id_voy;
        this.titre = titre;
        this.commentaire = commentaire;
    }

    public int getId_reclamation_voyage() {
        return id_reclamation_voyage;
    }

    public int getId_voy() {
        return id_voy;
    }

    public String getTitre() {
        return titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setId_reclamation_voyage(int id_reclamation_voyage) {
        this.id_reclamation_voyage = id_reclamation_voyage;
    }

    public void setId_voy(int id_voy) {
        this.id_voy = id_voy;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "reclamation_voyage{" + "id_reclamation_voyage=" + id_reclamation_voyage + ", id_voy=" + id_voy + ", titre=" + titre + ", commentaire=" + commentaire + '}';
    }
    
    
    
}
