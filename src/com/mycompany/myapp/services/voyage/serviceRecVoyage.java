/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.voyage;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.reclamation_voyage;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author jawha
 */
public class serviceRecVoyage {
    
    public ArrayList<reclamation_voyage> RecVoy;
    public static serviceRecVoyage instance;
    public boolean resultOK;
    private ConnectionRequest req;

    public serviceRecVoyage() {
        req = new ConnectionRequest();
    }
    
    public static serviceRecVoyage getInstance(){
        if(instance == null)
            instance = new serviceRecVoyage();
        return instance;
    }
    
    public ArrayList<reclamation_voyage> parseRecVoyage(String jsonText){
        try {
            RecVoy=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> RecVoyListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)RecVoyListJson.get("root");
            for(Map<String,Object> obj : list){
                reclamation_voyage RecV = new reclamation_voyage();
                float id = Float.parseFloat(obj.get("idReclamationVoyage").toString());
                RecV.setId_reclamation_voyage((int) id);
                RecV.setTitre(obj.get("titre").toString());
                RecV.setCommentaire(obj.get("commentaire").toString());
                Map<String,Object> Voy = (Map<String,Object>) obj.get("idVoy");
                RecV.setId_voy((int)Float.parseFloat(Voy.get("idVoyage").toString()));
                RecVoy.add(RecV);
            }
            
            
        } catch (IOException e) {
        }
        return RecVoy;
    }
    
    public ArrayList<reclamation_voyage> getAllRecVoy(){
        String url = Statics.BASE_URL2+"Voyage/reclamationvoyage/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                RecVoy = parseRecVoyage(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return RecVoy;
    }
    
    
    
    public boolean addTask(reclamation_voyage t,int id){
        String url = Statics.BASE_URL2+"Voyage/reclamationvoyage/"+id+"/add?titre="+t.getTitre()+"&commentaire="+t.getCommentaire();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK= req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    
    }
    
    public boolean modifierReclamationV(int id, reclamation_voyage e) {
    String url = Statics.BASE_URL2 + "Voyage/reclamationvoyage/"+id+"/modif?titre="+e.getTitre()+"&commentaire="+e.getCommentaire()    ;
      req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean suppReclamationV(int id) {
    String url = Statics.BASE_URL2 + "Voyage/reclamationvoyage/"+id+"/del"    ;
      req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
