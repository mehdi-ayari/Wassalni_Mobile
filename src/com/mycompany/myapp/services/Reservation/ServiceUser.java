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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


/**
 *
 * @author Mahdi
 */
public class ServiceUser {

    private ConnectionRequest req;
    public ArrayList<user> users;
    public static ServiceUser instance=null;
    public boolean resultOK;

    
    public ServiceUser() {
        req = new ConnectionRequest();
    }
     public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
     
     public ArrayList<user> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)userListJson.get("root");
            for(Map<String,Object> obj : list){
                user u = new user();
                u.setId((int)Float.parseFloat(obj.get("id").toString()));
                u.setFirstName(obj.get("nom").toString());
                u.setLastName(obj.get("prenom").toString());
                u.setEmail(obj.get("mail").toString());
                u.setTelephone((int)Float.parseFloat(obj.get("telephone").toString()));
                u.setAdresse(obj.get("adresse").toString());
               
//                Map<String, Object> role = (Map<String, Object>) obj.get("roles");
//                String roles= role.toString();
//                u.setRole_user(roles);
                
//                Map<String, Object> CategorieJson = (Map<String, Object>) obj.get("fkCategorie");
//                Categorie c = new Categorie();
//                float idc = Float.parseFloat(CategorieJson.get("idCategorie").toString());
//                c.setId((int)idc);
//                c.setNom(CategorieJson.get("nom").toString());
                
                users.add(u);
            }
            
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
     
    
    public ArrayList<user> getAllUsers(){
        String url = Statics.BASE_URL+"/Reservation/allusers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
    
}
