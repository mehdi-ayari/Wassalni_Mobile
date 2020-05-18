/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.Reservation;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.colis;
import java.util.ArrayList;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
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
    
    public boolean addResrvation(reservation r,colis c)
    {
        String url = Statics.BASE_URL + "/Reservation/ajouter?destination=" + r.getDestination()+ "&pointdepart=" + r.getPointdepart()
                +"&typeReservation="+r.getType_reservation()+"&prix="+r.getPrix()+"&objet="+r.getObjet()+"&userClient="+r.getUser_id_client()
                +"&userChauffeur="+r.getUser_id_chauffeur().getId()+"&contenu="+c.getContenu()+"&poids="+c.getPoids();
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
                
                Map<String, Object> chauffeurJson = (Map<String, Object>) obj.get("userChauffeur");
                Map<String, Object> date = (Map<String, Object>) obj.get("dateReservation");
                
                float dateres = Float.parseFloat(date.get("timestamp").toString());
                Date datereservation = new Date((long) dateres * 1000);
                
                
                reservation r = new reservation();
                user u = new user();
                
                float idc = Float.parseFloat(chauffeurJson.get("id").toString());
                u.setId((int)idc);
                u.setFirstName(chauffeurJson.get("prenom").toString());
                u.setLastName(chauffeurJson.get("nom").toString());
                
                
                
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId_res((int)id);
                r.setDestination(obj.get("destination").toString());
                r.setPointdepart(obj.get("pointdepart").toString());
                r.setUser_id_chauffeur(u);
                r.setPrix(Float.valueOf(obj.get("prix").toString()));
                r.setType_reservation(obj.get("typeReservation").toString());
                r.setObjet(obj.get("objet").toString());
                r.setDate_reservation(datereservation);
                
                Map<String, Object> colisJson = (Map<String, Object>) obj.get("idColis");

                colis c = new colis();
                if(obj.get("objet").toString()=="colis")
                {
                
                float idcoli = Float.parseFloat(colisJson.get("idColis").toString());
                c.setId_colis((int)idcoli);
                c.setContenu(colisJson.get("contenu").toString());
                c.setPoids(Float.valueOf(colisJson.get("poids").toString()));
                r.setId_colis(c);
                }
                else
                {
                r.setId_colis(c);                
                }
                
                
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
    
    
    public boolean DeleteResrvation(int i)
    {
        String url = Statics.BASE_URL + "/Reservation/supprimerreservation?id=" + i;
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
    
    public void modifierReservation(reservation r) {

        String Url = Statics.BASE_URL + "/Reservation/modifier?id=" + r.getId_res() + "&destination="+r.getDestination()+ "&pointdepart=" + r.getPointdepart()
                +"&typeReservation="+r.getType_reservation()+"&prix="+r.getPrix()+"&objet="+r.getObjet()
                +"&userChauffeur="+r.getUser_id_chauffeur().getId();
        req.setUrl(Url);
             System.out.println(Url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
 
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
}
