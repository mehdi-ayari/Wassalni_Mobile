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
    private int id_comment,id_news,id_user;
    private String text;
    private String CreatedAt;
    private String UserPhoto;
    private String NbrCmt;
     private String UserName;

    public Comment(int id_comment, int id_news, int id_user, String text, String CreatedAt, String UserPhoto, String NbrCmt, String UserName) {
        this.id_comment = id_comment;
        this.id_news = id_news;
        this.id_user = id_user;
        this.text = text;
        this.CreatedAt = CreatedAt;
        this.UserPhoto = UserPhoto;
        this.NbrCmt = NbrCmt;
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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public Comment(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public Comment() {
    }

  
    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public int getId_news() {
        return id_news;
    }

    public void setId_news(int id_news) {
        this.id_news = id_news;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" + "id_comment=" + id_comment + ", id_news=" + id_news + ", id_user=" + id_user + ", text=" + text + ", CreatedAt=" + CreatedAt + ", UserPhoto=" + UserPhoto + ", NbrCmt=" + NbrCmt + ", UserName=" + UserName + '}';
    }
    
    
}
