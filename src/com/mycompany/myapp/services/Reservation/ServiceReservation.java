/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.Reservation;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
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
import com.codename1.util.MathUtil;

import java.lang.*;





/**
 *
 * @author Mahdi
 */
public class ServiceReservation {
    
    public ArrayList<reservation> reservations;
    
    public static ServiceReservation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private List<Double> loc;
    private double distance;
    
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
    
//    public static double distance(float lat1, float lon1, float lat2, float lon2) {
//        
//                 	
//		if ((lat1 == lat2) && (lon1 == lon2)) {
//			return 0;
//		}
//		else {
//                     double theta = lon1 - lon2;
//		     double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
//		
//			dist = MathUtil.acos(dist);
//			dist = Math.toDegrees(dist);
//			dist = dist * 60 * 1.1515;
//			
//			dist = dist * 1.609344;
//                    
//                   
//			return (dist);
//		}
//	}
    
    
    
//    public static final double R = 6372.8; // In kilometers
//
//    // calculate distance with haversine formula
//    public static double distance(double lat1, double lon1, double lat2, double lon2) {
//        double dLat = Math.toRadians(lat2 - lat1);
//        double dLon = Math.toRadians(lon2 - lon1);
//        lat1 = Math.toRadians(lat1);
//        lat2 = Math.toRadians(lat2);
//
//        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
//        double c = 2 * Math.asin(Math.sqrt(a));
//        return R * c;
//    }
//    
    
//  public double latitude(String adresse)
//    {
//        double l=0 ;
//        if (adresse.equals("centre ville,Tunis"))
//        {
//            l=36.8004904;
//            return l;
////            list.set(0,"36.8004904");
////            list.set(1,"36.8004904");
////            list.add("10.185332118993045");
//           
//        }
//        else
//            if(adresse.equals("ariana"))
//        {
//            l=36.9685735;
////            list.set(1,"10.1219855");
//            return l;
//        }
//        if (adresse.equals("ben arous"))
//        {
//            l=36.7718;
//            return l;
//        }
//        else
//        if (adresse.equals("bardo"))
//        {
//            l=36.8134113;
////            list.set(1,"10.13219109");
//            return l;
//        }
//        else
//        if (adresse.equals("carthage"))
//        {
//            l=36.8577565;
//            return l;
////            list.add("10.32821822");
//        }
//        else
//        if (adresse.equals("mourouj"))
//        {
//            l=36.719825;
//            return l;
////            list.add("10.21923624");
//        }
//        else
//        if (adresse.equals("megrine"))
//        {
//            l=36.77179995;
//            return l;
////            list.add("10.23862035");
//        }
//        
//        return l;
//    }
//
//  public double longitude(String adresse)
//    {
//        double l=0 ;
//        if (adresse.equals("centre ville,Tunis"))
//        {
////            l=36.8004904;
////            list.set(0,"36.8004904");
////            list.set(1,"36.8004904");
//            l=10.185332118993045;
//            return l;
//           
//        }
//        if (adresse.equals("ariana"))
//        {
////            l=36.9685735;
//            l=10.1219855;
//            return l;
//           // return list;
//        }
//        if (adresse.equals("ben arous"))
//        {
////            list.add("36.7718");
//            l=10.2386203;
//            return l;
//        }
//        if (adresse.equals("bardo"))
//        {
////            l=36.8134113;
//            l=10.13219109;
//            return l;
//           // return list;
//        }
//        if (adresse.equals("carthage"))
//        {
////            l=36.8577565;
//            l=10.32821822;
//            return l;
//        }
//        if (adresse.equals("mourouj"))
//        {
////            l=36.719825;
//            l=10.21923624;
//            return l;
//        }
//        if (adresse.equals("megrine"))
//        {
////            l=36.77179995;
//            l=10.23862035;
//        return l;
//        }
//        
//            
//        return l;
//    }

//    public double local() throws IOException
//    {
//    LocationManager locationManager = LocationManager.getLocationManager();
//    Location location = locationManager.getCurrentLocation();
//    Double loc1= location.getLatitude();
//    Double loc2= location.getLongitude();
//    System.out.println("Latitude: "+ loc1);
//    System.out.println("Longitude: "+ loc2);
//    
//    return loc1;
//    }
  
  private List<Double> geocodeParse(String jsonText){
        List<Double> list2 = null;
        JSONParser j = new JSONParser();
        try {
            Map<String,Object> ListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ListJson.get("features");
            
            list2 = (List<Double>)list.get(0).get("center");

            
            return list2;
            


        } catch (IOException ex) {
            
        }
        
        return list2;

        
    }
      
      public List<Double> geocode(String address){
        
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("https://api.mapbox.com/geocoding/v5/mapbox.places/tunisia,"+address+".json?access_token=pk.eyJ1IjoibWVoZGk1MCIsImEiOiJjazh0am9rcWowMHdwM2hvazBsM3Jna3dhIn0.khgMF0AilXy9ja0Y7CRY8Q");
        req.setUserAgent("Opera/8.0 (Windows NT 5.1; U; en)");
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    loc = geocodeParse(new String(req.getResponseData()));             
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return loc;
    }
      
      private double DirectionParse(String jsonText)
    {
         
        JSONParser j = new JSONParser();
        try {
            Map<String,Object> ListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ListJson.get("routes");
            
            
            distance = Float.parseFloat(list.get(0).get("distance").toString());
            System.out.println(distance);
            System.out.println(distance);

            
            return distance;
            


        } catch (IOException ex) {
            
        }
        
        return distance;


        
    }

    public double getDirections(Coord origin, Coord destination) throws IOException {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("https://api.mapbox.com/directions/v5/mapbox/driving/"+origin.getLongitude()+"%2C"+origin.getLatitude()+"%3B"+destination.getLongitude()+"%2C"+destination.getLatitude()+"?alternatives=true&geometries=geojson&steps=true&access_token=pk.eyJ1IjoiamF3aGFyY2giLCJhIjoiY2s2dGVvOGlpMDB3NDNtcHVzcmxhdmR2YyJ9.VHALBLsdspgxm5eIuC270Q");
        req.setUserAgent("Opera/8.0 (Windows NT 5.1; U; en)");
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        JSONParser p = new JSONParser();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    distance = DirectionParse(new String(req.getResponseData()));             
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return distance;
    }

}
