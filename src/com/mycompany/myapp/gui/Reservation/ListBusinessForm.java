/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reservation;

import com.mycompany.myapp.gui.*;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.reservation_business;
import com.mycompany.myapp.services.Reservation.ServiceBusiness;
import java.util.Date;

/**
 *
 * @author Mahdi
 */
public class ListBusinessForm extends Form {

    public ListBusinessForm(Form previous) {
         setTitle("List Reservations Entreprise");
         SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

         for (reservation_business r : ServiceBusiness.getInstance().getAllReservations())
        {
        
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         
       
        Label destination = new Label ("Destination : " + r.getDestination());
        Label pointdepart = new Label ("Point Depart : " + r.getPoint_depart());
        Label nomlient = new Label ("Nom Client :" + r.getNom_client_entreprise());
        Label prenomclient = new Label ("Prenom Client : " + r.getPrenon_client_entreprise());
        String dateres = formater.format(r.getDate_reservation());
        String datedep = formater.format(r.getDate_depart());
        Label datereservation = new Label ("Date Reservation : " + dateres);
        Label datedepart = new Label ("Date Depart : " + datedep);
        Button supprimer = new Button("Supprimer");
        Button modifier = new Button("Modifier");
        C1.add(pointdepart);
        C1.add(destination);
        C1.add(datereservation);
        C1.add(datedepart);
        C1.add(nomlient);
        C1.add(prenomclient);
        C2.add(supprimer);
        C2.add(modifier);
        C1.add(C2);
        
        add(C1);
        
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceBusiness.getInstance().DeleteResrvation(r.getId_res_business());
                                Dialog.show("Success","Reservation supprimer",new Command("OK"));

                ListBusinessForm f = new ListBusinessForm(previous);
                f.showBack();
            }
        });
        
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                Form edit = new Form("Modifier Reservation");
                 edit.setLayout(BoxLayout.y());
                
                Label des = new Label("Destination");
                Label point = new Label("Point Depart");
                Label nom = new Label("Nom Client");
                Label pren = new Label("Prenom Client");
                Label da = new Label("Date Depart");
                TextField tfdestination = new TextField(r.getDestination());
                TextField tfpointdepart = new TextField(r.getPoint_depart());
                TextField tfnomclient = new TextField(r.getNom_client_entreprise());
                TextField tfprenomclient = new TextField(r.getPrenon_client_entreprise());
                Button btmodifier = new Button("Modifier");                
                Picker datedepart = new Picker();
                datedepart.setType(Display.PICKER_TYPE_DATE_AND_TIME);

                Date depart;
                try {
                    depart = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(r.getDate_depart());
                    datedepart.setDate(depart);
                } catch (ParseException ex) {
                    System.out.println("date erreuuurrrrrrrr");
                    System.out.println(r.getDate_depart());
                }
                
                edit.addAll(des,tfdestination,point,tfpointdepart,nom,tfnomclient,pren,tfprenomclient,da,datedepart,btmodifier);
                
                ListBusinessForm l = new ListBusinessForm(previous);
                edit.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> l.showBack());

                edit.show();
                
                btmodifier.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        reservation_business resbus = new reservation_business();
                        resbus.setId_res_business(r.getId_res_business());
                        resbus.setDestination(tfdestination.getText());
                        resbus.setPoint_depart(tfpointdepart.getText());
                        resbus.setNom_client_entreprise(tfnomclient.getText());
                        resbus.setPrenon_client_entreprise(tfpointdepart.getText());
                        Date d = datedepart.getDate();
                        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String datedepar = formater.format(d);
                        resbus.setDate_depart(datedepar);
                        System.out.println(datedepar);
                        ServiceBusiness.getInstance().modifierReservation(resbus);
                         Dialog.show("Success","Reservation Modifier",new Command("OK"));

                         System.out.println("done"); 
                    }
                });
                
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    
        
    }
    } 
    
}
