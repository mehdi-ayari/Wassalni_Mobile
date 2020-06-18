/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.news.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
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
    
    
      public boolean AddComment(Comment c) {
        String url = Statics.BASE_URL + "news/AddCommentMobile/?text=" + c.getText()  + "&news_id=" + c.getIdA()+ "&user_id=" + c.getId_u(); //création de l'URL
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
     
     public String DeleteComment(Comment c) {
        String url = Statics.BASE_URL + "news/deleteCommentMobile/?id=" + c.getId();
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
        String url = Statics.BASE_URL + "news/EditCommentMobile/?id=" + c.getIdA()+ "text=" + c.getText(); //création de l'URL
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
        
         
    public ArrayList<Comment> parseCmts(String jsonText)
    {
        try {
            Comment = new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> actsListJason = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)actsListJason.get("root");
             for(Map<String,Object> obj : list)
            {
                Comment.add(new Comment(obj.get("id").toString().substring(0,obj.get("id").toString().length()-2),obj.get("id_u").toString(),obj.get("text").toString(),
                       obj.get("date").toString(),obj.get("UserName").toString(),obj.get("UserPhoto").toString(),obj.get("NbrCmt").toString() ));
            }
        } catch (IOException ex) {
        }
        return Comment;
    }
    
    
    public ArrayList<Comment> getAllComment(String id)
    {
        String url = Statics.BASE_URL+"news/ListCommentMobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
           
                Comment= parseCmts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Comment;
    }
    
}
