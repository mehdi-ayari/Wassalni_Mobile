/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahdi
 */
public class ServiceReservation {
    
    public ArrayList<reservation> reservations;
    
    public static ServiceReservation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReservation() {
         req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }
    
    public boolean addResrvation(reservation r)
    {
        String url = Statics.BASE_URL + "/Reservation/ajouter?destination=" + r.getDestination()+ "&pointdepart=" + r.getPointdepart()+"&dateReservation="+r.getDate_reservation()
                +"&typeReservation="+r.getType_reservation()+"&prix="+r.getPrix()+"&objet="+r.getObjet()+"&userClient="+r.getUser_id_client()+"&userChauffeur="+r.getUser_id_chauffeur().getId();
        req.setUrl(url);
        System.out.println(url);
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
    
    public ArrayList<reservation> parseReservations(String jsonText){
        try {
            reservations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ReservationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ReservationListJson.get("root");
            
            for(Map<String,Object> obj : list){
                reservation r = new reservation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId_res((int)id);
                r.setDestination(obj.get("destination").toString());
                r.setPointdepart(obj.get("pointdepart").toString());
                
//                r.setId_colis((int)Float.parseFloat(obj.get("idColis").toString()));
                r.setType_reservation(obj.get("typeReservation").toString());
                r.setObjet(obj.get("objet").toString());
                reservations.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reservations;
    }
    
    
    public ArrayList<reservation> getAllReservations(){
        String url = Statics.BASE_URL+"/Reservation/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
    
}
