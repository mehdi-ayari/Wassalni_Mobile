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
import com.mycompany.myapp.entities.news;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ASUS
 */
public class ServicesNews {
     public ArrayList<news> News;

    public static ServicesNews instance = null;
    public boolean resultOK = false;
    public String result = "";
    private ConnectionRequest req;

    private ServicesNews() {
        req = new ConnectionRequest();
    }

    public static ServicesNews getInstance() {
        if (instance == null) {
            instance = new ServicesNews();
        }
        return instance;
    }
    
     public ArrayList<news> parseNews(String jsonText) {
        try {
            News = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                news n = new news();
                float id = Float.parseFloat(obj.get("id").toString());
                n.setId_news((int) id);
                n.setTitre(obj.get("titre").toString());
                n.setImage(obj.get("image").toString());
                n.setDescr(obj.get("descr").toString());
              

                News.add(n);
            }

        } catch (IOException ex) {

        }

        return News;
    }
      public ArrayList<news> parseNewsStat(String jsonText) {
        try {
            News = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                news n = new news();
                float nombre = Float.parseFloat(obj.get("nombre").toString());
                n.setNombreComment((int) nombre);
                n.setTitre(obj.get("titre").toString());
              

                News.add(n);
            }

        } catch (IOException ex) {

        }

        return News;
    }
       public ArrayList<news> getAllNewsStat() {
        String url = Statics.BASE_URL + "news/StatNewsMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                News = parseNewsStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return News;
    }
     public ArrayList<news> getAllNews() {
        String url = Statics.BASE_URL + "news/ListNewsMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                News = parseNews(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return News;
    }
     
      public boolean AddNews(news c) {
        String url = Statics.BASE_URL + "news/AddNewsMobile/?titre=" + c.getTitre() + "&descr=" + c.getDescr() + "&image=" + c.getImage(); //création de l'URL
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
      public String DeleteNews(news c) {
        String url = Statics.BASE_URL + "news/deleteNewsMobile/?id=" + c.getId_news();
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
        public boolean EditNews(news c) {
        String url = Statics.BASE_URL + "news/EditNewsMobile/?id=" + c.getId_news()+ "&titre=" + c.getTitre()+ "&descr=" + c.getDescr(); //création de l'URL
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
