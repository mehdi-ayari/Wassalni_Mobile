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
import com.mycompany.myapp.entities.voyage;
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
public class serviceVoyage {
    public ArrayList<voyage> voyages;
    public boolean resultOK;
    private ConnectionRequest req;
    public static serviceVoyage instance;

    private serviceVoyage() {
        req = new ConnectionRequest();
    }
    
    public static serviceVoyage getInstance()
    {
        if(instance == null){
            instance = new serviceVoyage();
        }
        return instance;
    }
    
    public ArrayList<voyage> parseVoyages(String jsonText)
    {
        try {
            
            voyages = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> VoyagesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)VoyagesListJson.get("root");
            
            for(Map<String,Object> obj : list ){
                
                voyage v = new voyage();
                float id = Float.parseFloat(obj.get("idVoyage").toString());
                v.setId_voyage((int)id);
                //float distance = Float.parseFloat(obj.get("distance").toString());
                try{
                float distance = Float.parseFloat(obj.get("distance").toString());
                v.setDistance((float) distance); 
                }
                catch(NullPointerException e){
                }
                
                Map<String,Object> R = (Map<String,Object>) obj.get("reservationRes");
                Map<String,Object> D = (Map<String,Object>) obj.get("dateVoyage");
                float timestamp = Float.parseFloat(D.get("timestamp").toString());
                float idRes = Float.parseFloat(R.get("id").toString());
                String Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date((int)timestamp * 1000L));
                Date dateVoyage= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Date);
                String destination = R.get("destination").toString();
                v.setDate_voyage(dateVoyage);
                v.setReservation_id_res((int) idRes);
                v.setDestination(destination);
                voyages.add(v);
            }
            
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
        return voyages;
    }
    
    public ArrayList<voyage> getVoyagesDone(){
        String url2 = Statics.BASE_URL2+"Voyage/voyage/findDone";
        req.setUrl(url2);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                voyages = parseVoyages(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return voyages;
    }
    
    public ArrayList<voyage> getVoyages(){
        String url2 = Statics.BASE_URL2+"Voyage/voyage/find";
        req.setUrl(url2);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                voyages = parseVoyages(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return voyages;
    }
    
    
    
}
