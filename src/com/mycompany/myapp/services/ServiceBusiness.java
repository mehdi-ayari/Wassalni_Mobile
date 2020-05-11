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
import com.mycompany.myapp.entities.reservation_business;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;


/**
 *
 * @author Mahdi
 */
public class ServiceBusiness {
    public ArrayList<reservation_business> business;
    
    public static ServiceBusiness instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceBusiness() {
         req = new ConnectionRequest();
    }


    public static ServiceBusiness getInstance() {
        if (instance == null) {
            instance = new ServiceBusiness();
        }
        return instance;
    }
    
    public boolean addResrvation(reservation_business b)
    {
        String url = Statics.BASE_URL + "/Reservation/ajouterbusiness?destination=" + b.getDestination()+ "&pointDepart=" + b.getPoint_depart()+"&dateDepart="+b.getDate_depart()
                +"&prenonClientEntreprise="+b.getPrenon_client_entreprise()+"&nomClientEntreprise="+b.getNom_client_entreprise();
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
    
    public ArrayList<reservation_business> parseReservations(String jsonText){
        try {
            business=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> BusinessListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)BusinessListJson.get("root");
            
            for(Map<String,Object> obj : list){
                reservation_business b = new reservation_business();
                float id = Float.parseFloat(obj.get("idResBusiness").toString());
                b.setId_res_business((int)id);
                b.setDestination(obj.get("destination").toString());
                b.setPoint_depart(obj.get("pointDepart").toString());
                
//                r.setId_colis((int)Float.parseFloat(obj.get("idColis").toString()));
                b.setNom_client_entreprise(obj.get("nomClientEntreprise").toString());
                b.setPrenon_client_entreprise(obj.get("prenonClientEntreprise").toString());
//                b.setDate_depart((Date) obj.get("dateDepart"));
                business.add(b);
            }
            
            
        } catch (IOException ex) {
            
        }
        return business;
    }
    
    
    public ArrayList<reservation_business> getAllReservations(){
        String url = Statics.BASE_URL+"/Reservation/allbusiness";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                business = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return business;
    }
    
}
