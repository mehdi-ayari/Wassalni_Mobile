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
public class Comment {
    
    
    private String id;
    private String id_u;   
    private String text;
    private String date;
    private String UserName;
    private String UserPhoto;
    private String NbrCmt;
    private String idA;

    public Comment(){}

    public Comment(String id, String id_u, String text, String date, String UserName, String UserPhoto, String NbrCmt) {
        this.id = id;
        this.id_u = id_u;
        
        this.text = text;
      
        this.date = date;
        this.UserName = UserName;
        this.UserPhoto = UserPhoto;
        this.NbrCmt = NbrCmt;
       
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String UserPhoto) {
        this.UserPhoto = UserPhoto;
    }

    public String getNbrCmt() {
        return NbrCmt;
    }

    public void setNbrCmt(String NbrCmt) {
        this.NbrCmt = NbrCmt;
    }
    
  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_u() {
        return id_u;
    }

    public void setId_u(String id_u) {
        this.id_u = id_u;
    }

    public String getIdA() {
        return idA;
    }

    public void setIdA(String idA) {
        this.idA = idA;
    }

    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   
    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", id_u=" + id_u + ", text=" + text + ", date=" + date + ", UserName=" + UserName + ", UserPhoto=" + UserPhoto + ", NbrCmt=" + NbrCmt + '}';
    }

   
    
    
    
}