/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.voyage;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.location.GeofenceManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.location.LocationListener;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.LinesLayer;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Graphics;
import com.mycompany.myapp.entities.voyage;
import com.mycompany.myapp.utils.Statics;
import com.sun.prism.image.Coords;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;



/**
 *
 * @author jawha
 */
public class map extends Form{
    
    private Form main;
    private Coord lastLocation;
    private Coord MyLocation;
    private List<Double> loc;
    private List<List<Double>> Direc;
    public static float distance;

    public map(Form previous) {
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Button b = new Button("Where am I?");
        this.addComponent(b);
        main=previous;



        
    
    
    

                }
    
        public void showMeOnMap(voyage v) {
        Form maps = new Form("Map");
        maps.setLayout(new BorderLayout());
        maps.setScrollable(false);
        final MapComponent mc = new MapComponent();
        putMeOnMap(mc,v);
        mc.zoomToLayers();
            
        maps.addComponent(BorderLayout.CENTER, mc);
        maps.getToolbar().addCommandToLeftBar(new map.BackCommand());
        maps.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ZOOM_IN, e->mc.zoomIn());
        maps.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ZOOM_OUT, e->mc.zoomOut());
        maps.setBackCommand(new map.BackCommand());
        maps.show();

    }

    private void putMeOnMap(MapComponent map,voyage v) {
        double lat = 0;
        double lng = 0;
        
                 try {
                   if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
                    if (Display.getInstance().getLocationManager().isGPSEnabled()) {
                        InfiniteProgress ip = new InfiniteProgress();
                        final Dialog ipDlg = ip.showInifiniteBlocking();
                        //Cancel after 20 seconds
                        Location loc = LocationManager.getLocationManager().getCurrentLocationSync(20000);
                        ipDlg.dispose();
                        if (loc != null) {
                             lat = loc.getLatitude();
                             lng = loc.getLongitude();
                        } else {
                            Dialog.show("GPS error", "Your location could not be found, please try going outside for a better GPS signal", "Ok", null);
                        }
                    } else {
                        Dialog.show("GPS disabled", "AppName needs access to GPS. Please enable GPS", "Ok", null);
                    }
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog ipDlg = ip.showInifiniteBlocking();
                    //Cancel after 20 seconds
                    Location loc = LocationManager.getLocationManager().getCurrentLocationSync(20000);
                    ipDlg.dispose();
                    if (loc != null) {
                         lat = loc.getLatitude();
                         lng = loc.getLongitude();

                    } else {
                        Dialog.show("GPS error", "Your location could not be found, please try going outside for a better GPS signal", "Ok", null);
                    }
                   }

              /* double latti = Double.parseDouble(s[1]);
               double longi = Double.parseDouble(s[0]);
                     System.out.println(latti +" " + longi);*/
             Coord firstPlace;
            MyLocation = new Coord(36.89771,10.18962);
            lastLocation=MyLocation;
                     System.out.println(v.getDestination());
            Coord Direction = new Coord(geocode(v.getDestination()).get(1),geocode(v.getDestination()).get(0));
            List<List<Double>> vec = getDirections(MyLocation, Direction);
            PointsLayer p5 = new PointsLayer();
            PointsLayer p4 = new PointsLayer();
                        Double latDir = (Double) Direction.getLatitude();
                        Double lngDir = (Double) Direction.getLongitude();
                        PointLayer pointC = new PointLayer(new Coord(vec.get(0).get(1), vec.get(0).get(0)),(String) "here", null);
                        PointLayer pointN = new PointLayer(new Coord(vec.get(0).get(1), vec.get(0).get(0)),(String) "next", null);
                        p5.addPoint(pointC);
                        LinesLayer line = new LinesLayer();
                        int j =1;
                        while (j<vec.size()) {
                            pointC=pointN;
                            pointN= new PointLayer(new Coord(vec.get(j).get(1), vec.get(j).get(0)),(String) "here", null);
                            line.addLineSegment(new Coord[]{pointC,pointN});
                            j++;
                        }
                        map.addLayer(line);
                                                                   
                    
            Image i = Image.createImage("/blue_pin.png");
            Image i2 = Image.createImage("/red_pin.png");
            PointsLayer pl = new PointsLayer();
            PointsLayer p3 = new PointsLayer();
            pl.setPointIcon(i);
            p3.setPointIcon(i2);
            PointLayer p = new PointLayer(lastLocation, "You Are Here", i);
            PointLayer p2 = new PointLayer(Direction, "Direction", i2);
            p.setDisplayName(true);
            pl.addPoint(p);
            p3.addPoint(p2);
            LinesLayer line2 = new LinesLayer();
            line2.addLineSegment(new Coord[]{p,p2});
            
                    Button mosconeButton = new Button("");
        mosconeButton.setUIID("Label");
        FontImage.setMaterialIcon(mosconeButton, FontImage.MATERIAL_PLACE);
        map.zoomTo(Direction, 5);
            pl.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    PointLayer p = (PointLayer) evt.getSource();
                    System.out.println("pressed " + p);

                    Dialog.show("Details", "You Are Here" + "\n" + p.getLatitude() + "|" + p.getLongitude(), "Ok", null);
                }
            });
            map.addLayer(pl);
            map.addLayer(p3);
            v.setDistance(distance);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
        class BackCommand extends Command {

        public BackCommand() {
            super("");
            FontImage img = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));
            setIcon(img);
        }

        public void actionPerformed(ActionEvent evt) {
            main.showBack();
        }
    }

    private ArrayList decodePoly(String encoded) {
        ArrayList poly = new ArrayList();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            Coord p = new Coord(lat / 1E5, lng / 1E5);
            poly.add(p);
        }

        return poly;
    }
    
    private List<List<Double>> DirectionParse(String jsonText)
    {
          List<List<Double>> list2 = null;
        JSONParser j = new JSONParser();
        try {
            Map<String,Object> ListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ListJson.get("routes");
            Map<String,Object> list3;
            list3 = (Map<String,Object>)list.get(0).get("geometry");
            distance = Float.parseFloat(list.get(0).get("distance").toString());
            System.out.println(distance);
            list2 = (List<List<Double>>)list3.get("coordinates");
            System.out.println(list2);

            
            return list2;
            


        } catch (IOException ex) {
            
        }
        
        return list2;


        
    }

    public List<List<Double>> getDirections(Coord origin, Coord destination) throws IOException {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("https://api.mapbox.com/directions/v5/mapbox/driving/"+origin.getLongitude()+"%2C"+origin.getLatitude()+"%3B"+destination.getLongitude()+"%2C"+destination.getLatitude()+"?alternatives=true&geometries=geojson&steps=true&access_token=pk.eyJ1IjoiamF3aGFyY2giLCJhIjoiY2s2dGVvOGlpMDB3NDNtcHVzcmxhdmR2YyJ9.VHALBLsdspgxm5eIuC270Q");
        req.setUserAgent("Opera/8.0 (Windows NT 5.1; U; en)");
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        JSONParser p = new JSONParser();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    Direc = DirectionParse(new String(req.getResponseData()));             
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Direc;
    }
    
  
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
        req.setUrl("https://api.mapbox.com/geocoding/v5/mapbox.places/tunisia,"+address+".json?access_token=pk.eyJ1IjoiamF3aGFyY2giLCJhIjoiY2s2dGVvOGlpMDB3NDNtcHVzcmxhdmR2YyJ9.VHALBLsdspgxm5eIuC270Q");
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

      



}