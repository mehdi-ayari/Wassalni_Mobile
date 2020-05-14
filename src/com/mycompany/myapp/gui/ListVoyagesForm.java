/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.maps.MapComponent;
import com.codename1.maps.providers.OpenStreetMapProvider;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.mycompany.myapp.entities.voyage;
import com.mycompany.myapp.services.serviceVoyage;
import java.util.ArrayList;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jawha
 */
public class ListVoyagesForm extends Form{
Form current;
    public ListVoyagesForm(Form previous) {
         Date date = new Date(); 
         current=this;
         
         
         

        setTitle("List Voyages");
        setLayout(BoxLayout.y());
        SpanLabel s= new SpanLabel();
        s.setText(serviceVoyage.getInstance().getVoyagesDone().toString());
        ArrayList<voyage> Voyages = serviceVoyage.getInstance().getVoyagesDone();
        for(int i = 0 ; i < Voyages.size();i++){
            voyage Voyage = serviceVoyage.getInstance().getVoyagesDone().get(i);
            Label id = new Label();
            Label idRes = new Label();
            Label dateVoyage = new Label();
            Label distance = new Label();
            Label destination = new Label();
            id.setText("idVoyage = "+Voyage.getId_voyage()+"\n");
            distance.setText("distance = "+Voyage.getDistance()+"\n");
            dateVoyage.setText("date voyage = "+Voyage.getDate_voyage()+"\n");
            idRes.setText("id reservation = "+Voyage.getReservation_id_res()+"\n");
            destination.setText("destination = "+Voyage.getDestination());
              Label tre = new Label("________________________________");
                
                Button Reclamer = new Button("Reclamer");
                Button Map = new Button("Map");
                
                addAll(id,destination,idRes,dateVoyage,distance,Reclamer,Map,tre);
           
        }
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
    }
    
    
    
}
