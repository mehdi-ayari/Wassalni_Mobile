/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.user;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Data;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class UserService {
    
        public ArrayList<user> users;
    
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    String reponseLog;
    boolean resultUN;
    boolean resultEmail;
    boolean resultPW;
    boolean resultTel;
    boolean resultMdpOub;
    
    private UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    public ArrayList<user> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des users et récupération de leurs données
                user u = new user();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                u.setFirstName(obj.get("nom").toString());
                u.setLastName(obj.get("prenom").toString());
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                
                float tell = Float.parseFloat(obj.get("telephone").toString());
                u.setTelephone((int)tell);
                u.setRole_user(obj.get("role").toString());
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */

        return users;
    }
    
     public ArrayList<user> getAllUsers(){
        String url = Statics.BASE_URL+"/Reservation/allmobile";
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
     
     public int getLoggedId(String username){
         int x = 0 ;
         ArrayList<user> tester = getAllUsers();
                  for (int k=0;k<tester.size();k++){
         if (tester.get(k).getUsername().equals(username))
         {
             x = tester.get(k).getId();
           
         }
 
         }

         return x;
         
     }
     
          public int getIdByEmail(String email){
         int Iddd = 0 ;
         ArrayList<user> tester = getAllUsers();
                  for (int k=0;k<tester.size();k++){
         if (tester.get(k).getEmail().equals(email))
         {
             Iddd = tester.get(k).getId();
           
         }
 
         }

         return Iddd;
         
     }
     
     
     
     public user getLoggedInfos(String username){
         user u = new user();
                  ArrayList<user> tester = getAllUsers();
                  for (int k=0;k<users.size();k++){
         if (users.get(k).getUsername().equals(username))
         {
             u = users.get(k);
         }
             
         }
          return u;
          
     }
     

     public String logiiin(String username,String password){
         
         String a ="wedev";
         String b ="123";

                             String jsn = "{\n" +
"    \"username\": \""+a+"\",\n" +
"    \"password\": \""+b+"\"\n" +
"}";       

                         try {
         String url="http://localhost/PI_Webwassalni/web/app_dev.php/Reservation/logg";
         req.setUrl(url);
         req.setPost(true);
         req.setContentType("application/json");
         req.setRequestBody(jsn);
          
             
         
          req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reponseLog =  req.getResponseErrorMessage();
                req.removeResponseListener(this); //Supprimer cet actionListener
              
                
            }
        });
       }
          
          catch (Exception e) {
              System.out.println("hemo");
              
          }
       //  System.out.println(reponse);
       NetworkManager.getInstance().addToQueueAndWait(req);
         return reponseLog;
     }
     
         public boolean addUser(user u) {
        String url = "http://localhost/PI_Webwassalni/web/app_dev.php/Reservation/newmobile?username="+u.getUsername()+"&nom="+u.getFirstName()+"&prenom="+u.getLastName()+"&email="+u.getEmail()+"&password="+u.getPassword()+"&telephoe="+u.getTelephone()+"&role="+u.getRole_user();
                
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         
         public boolean usernameExists(String username){
             boolean result=false;
                               ArrayList<user> tester = getAllUsers();
                  for (int k=0;k<tester.size();k++){
         if (tester.get(k).getUsername().equals(username))
         {
             result=true;
         }
             
         }
             
             return result;
         }
         
                  public boolean emailExists(String email){
             boolean result=false;
                               ArrayList<user> tester = getAllUsers();
                  for (int k=0;k<tester.size();k++){
         if (tester.get(k).getEmail().equals(email))
         {
             result=true;
         }
             
         }
             
             return result;
         }
                  
             public boolean changeUsername(String username){
                 
                 
        String url = "http://localhost/PI_webwassalni/web/app_dev.php/Reservation/changeUsername?id="+Statics.getIdSession()+"&username="+username;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultUN = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultUN;
}
             
              public boolean changeEmail(String email){

                          String url = "http://localhost/PI_Webwassalni/web/app_dev.php/Reservation/changeEmail?id="+Statics.getIdSession()+"&email="+email;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultEmail = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultEmail;
                  
                  
}
         public boolean changePassword(String password){
                 
                 
        String url = "http://localhost/PI_Webwassalni/web/app_dev.php/Reservation/plainPW?id="+Statics.getIdSession()+"&password="+password;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultPW = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultPW;
}
         
                  public boolean recoverPassword(int id ,String password){
                 
                 
        String url = "http://localhost/PI_Webwassalni/web/app_dev.php/Reservation/plainPW?id="+id+"&password="+password;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultMdpOub = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultMdpOub;
}
         
         
        public boolean changeTel(int tel){
                 
                 
        String url = "http://localhost/PI_Webwassalni/web/app_dev.php/Reservation/changeTel?id="+Statics.getIdSession()+"&tel="+tel;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultTel = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultTel;
}
              
             
             
             
             
             
}
