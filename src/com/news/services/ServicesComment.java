/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.news.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServicesComment {
    public ArrayList<Comment> Comment;

    public static ServicesComment instance = null;
    public boolean resultOK = false;
    public String result = "";
    private ConnectionRequest req;

    private ServicesComment() {
        req = new ConnectionRequest();
    }

    public static ServicesComment getInstance() {
        if (instance == null) {
            instance = new ServicesComment();
        }
        return instance;
    }
    
     public ArrayList<Comment> parseComment(String jsonText) {
        try {
            Comment = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Comment n = new Comment();
                float id = Float.parseFloat(obj.get("id").toString());
                float news_id = Float.parseFloat(obj.get("news_id").toString());
                float user_id = Float.parseFloat(obj.get("user_id").toString());
                String createdAt = obj.get("createdAt").toString();
                String value = createdAt.substring(6, 16);
                n.setCreatedAt(value);
                
                n.setId_comment((int) id);
                 n.setId_news((int) news_id);
                  n.setId_user((int) user_id);
                n.setText(obj.get("text").toString());
                         

                Comment.add(n);
            }

        } catch (IOException ex) {

        }

        return Comment;
    }

      public boolean AddComment(Comment c) {
        String url = Statics.BASE_URL + "news/AddCommentMobile/?text=" + c.getText()  + "&news_id=" + c.getId_news() + "&user_id=" + c.getId_user() ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public ArrayList<Comment> getAllComment() {
        String url = Statics.BASE_URL + "news/ListCommentMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Comment = parseComment(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Comment;
    }
     public String DeleteComment(Comment c) {
        String url = Statics.BASE_URL + "news/deleteCommentMobile/?id=" + c.getId_comment();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
        public boolean EditComment(Comment c) {
        String url = Statics.BASE_URL + "news/EditCommentMobile/?id=" + c.getId_comment() + "text=" + c.getText(); //création de l'URL
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
