/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.services.ServiceReservation;

/**
 *
 * @author Mahdi
 */
public class ListReservationForm extends Form {

    public ListReservationForm(Form previous) {
        
        setTitle("List Reservations");
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (reservation r : ServiceReservation.getInstance().getAllReservations())
        {
        
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         
        String obj = r.getObjet();
        if(obj=="passager")
        {
        Label destination = new Label ("Destination : " + r.getDestination());
        Label pointdepart = new Label ("Point Depart : " + r.getPointdepart());
        Label objet = new Label ("Objet :" + r.getObjet());
        Label typereservation = new Label ("Type Reservation : " + r.getType_reservation());
//        String date = formater.format(r.getDate_reservation());
        Label datereservation = new Label ("Date Reservation : " + r.getDate_reservation());
        Label prix = new Label ("Prix : " + r.getPrix());
        Label chauffeur = new Label ("Chauffeur : " + r.getUser_id_chauffeur().getLastName());
        Label contenu = new Label ("Contenu : " );
        Label poids = new Label ("Poids : " );
        Button supprimer = new Button("Supprimer");
        Button modifier = new Button("Modifier");
        C1.add(destination);
        C1.add(pointdepart);
        C1.add(objet);
        C1.add(typereservation);
        C1.add(datereservation);
        C1.add(prix);
        C1.add(chauffeur);
        C1.add(contenu);
        C1.add(poids);
        C2.add(supprimer);
        C2.add(modifier);
        C1.add(C2);
        
        add(C1);
        }
        else
        {
            Label destination = new Label ("Destination : " + r.getDestination());
        Label pointdepart = new Label ("Point Depart : " + r.getPointdepart());
        Label objet = new Label ("Objet :" + r.getObjet());
        Label typereservation = new Label ("Type Reservation : " + r.getType_reservation());
        String date = formater.format(r.getDate_reservation());
        Label datereservation = new Label ("Date Reservation : " + date);
        Label prix = new Label ("Prix : " + r.getPrix());
        Label chauffeur = new Label ("Chauffeur : " + r.getUser_id_chauffeur().getLastName());
        Label contenu = new Label ("Contenu : " + r.getId_colis() );
        Label poids = new Label ("Poids : " + r.getId_colis() );

        Button supprimer = new Button("Supprimer");
        Button modifier = new Button("Modifier");
        C1.add(destination);
        C1.add(pointdepart);
        C1.add(objet);
        C1.add(typereservation);
        C1.add(datereservation);
        C1.add(prix);
        C1.add(chauffeur);
        C1.add(contenu);
        C1.add(poids);
        
        C2.add(supprimer);
        C2.add(modifier);
        C1.add(C2);
        add(C1);
        
        }
        
        
        
        
        }
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceReservation.getInstance().getAllReservations().toString());
//        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
    
    
}
