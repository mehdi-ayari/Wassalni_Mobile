/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.reclamation;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.TypeRec;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class ReclamationService {
    
    public ArrayList<Reclamation> reclamations;
    public static ReclamationService instance;
    private final ConnectionRequest req;
    public boolean resultOK;
    public ArrayList<TypeRec> types;
    
    public ReclamationService() {
        req = new ConnectionRequest();
    }
    
    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
            
        }
        return instance;
    }
    
    public ArrayList<Reclamation> getAllrecs() {
        String url = "http://localhost/PI_WebWassalni/web/app_dev.php/Reclamation/allrec/1";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    reclamations = parseRecs(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
    
    public ArrayList<Reclamation> parseRecs(String jsonText) throws ParseException {
        try {
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setTitre(obj.get("titre").toString());
                t.setDescription(obj.get("description").toString());
                
                SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");
                Map<String, Object> prch = (Map<String, Object>) obj.get("date");
                Date dater;
                dater = ((Date)sdf.parse(prch.get("date").toString()));
                t.setDate(dater);
                t.setEtat(obj.get("etat").toString());
                t.setType(obj.get("type").toString());
                //   t.setCategorie((CategorieR) obj.get("Categorie"));
                //Ajouter la tâche extraite de la réponse Json à la liste
                reclamations.add(t);
            }

        } catch (IOException ex) {

        }

        return reclamations;
    }
    
    public ArrayList<TypeRec> parseTypes(){
        types = new ArrayList<>();
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    if ( types.size() > 0) {
                        types.clear();
                  }
                    Log.p("size content" + content.size());
                    for (Map<String, Object> obj : content) {
                        TypeRec t = new TypeRec();
                        t.setId(Integer.parseInt(obj.get("id").toString()));
                        t.setType((String) obj.get("type"));
                        types.add(t);
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
        };
        connectionRequest.setUrl("http://192.168.93.1/rimehService/MalekService/allProd.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
        return types;
    }
    
    
    public ArrayList<TypeRec> getAllTypes() {
        String url = "http://localhost/PI_WebWassalni/web/app_dev.php/Reclamation/listTypes";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    types = parseTypes(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return types;
    }
    
    public ArrayList<TypeRec> parseTypes(String jsonText) throws ParseException {
        try {
            types = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                TypeRec t = new TypeRec();
                        float id = Float.parseFloat(obj.get("id").toString());
                        t.setId((int) id);
                        t.setType((String) obj.get("type"));
                        types.add(t);
                //   t.setCategorie((CategorieR) obj.get("Categorie"));
                //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {

        }

        return types;
    }
    
}
