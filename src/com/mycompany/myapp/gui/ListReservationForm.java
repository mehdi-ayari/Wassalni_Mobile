/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.services.ServiceReservation;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author Mahdi
 */
public class ListReservationForm extends Form {
private ComboBox cmb;

    public ListReservationForm() {
    }

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
        Label chauffeur = new Label ("Chauffeur : " + r.getUser_id_chauffeur().getLastName() +" "+r.getUser_id_chauffeur().getFirstName());
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
        
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceReservation.getInstance().DeleteResrvation(r.getId_res());
                Dialog.show("Success","Reservation supprimer",new Command("OK"));
                ListReservationForm f = new ListReservationForm(previous);
                f.showBack();
            }
        });
        
        modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
              
                 Form edit = new Form("Modifier Reservation");
                 edit.setLayout(BoxLayout.y());
                    
                TextField tfdestination = new TextField(r.getDestination());
                TextField tfpointdepart = new TextField(r.getPointdepart());
//                TextField tfcontenu = new TextField("",r.getId_colis().getContenu());
//                TextField tfpoids = new TextField("",String.valueOf(r.getId_colis().getPoids()));
                Button btmodifier = new Button("Modifier");
                ArrayList<String> type = choix(r.getType_reservation());
                System.out.println(r.getType_reservation());
                ComboBox typereservation = new ComboBox(r.getType_reservation(),type.get(1),type.get(2));
                
                if (r.getObjet()=="passager")
                {
                ComboBox objet=new ComboBox("Colis","Passager");
                }
                if (r.getObjet()=="colis")
                {
                ComboBox objet=new ComboBox("Passager","Colis");
                }
                cmb = new ComboBox<>();
                ArrayList<user> users = new ArrayList<>();
                users.addAll(ServiceUser.getInstance().getAllUsers());

                for (user object : users) {
                    cmb.addItem(object.getFirstName()+" "+object.getLastName());
                }
                
//                edit.addAll(tfdestination,tfpointdepart,objet,typereservation,cmb,btmodifier);
                edit.add(tfdestination);
                edit.add(tfpointdepart);
                edit.add(typereservation);
                ListReservationForm l = new ListReservationForm(previous);
                edit.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> l.showBack());

                edit.show();
                
                }
            });
        
        }
        
        
        
        
        }

        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
    public ArrayList<String> choix(String c)
    {
        ArrayList<String> choix = new ArrayList<>();
//        choix.add(c);
        if(c!="Privée")
        {
            choix.add("Privée");
        }
        if (c!="Taxi")
        {
            choix.add("Taxi");
        }
        if(c!="camion")
        {
            choix.add("camion");
        }
        return choix;  
    
    }
    
    
    
}
