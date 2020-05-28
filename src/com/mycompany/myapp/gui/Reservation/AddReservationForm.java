/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reservation;

import com.mycompany.myapp.gui.*;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.colis;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.services.Reservation.ServiceReservation;
import com.mycompany.myapp.services.Reservation.ServiceUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 *
 * @author Mahdi
 */
public class AddReservationForm extends Form {
    
        Label destination = new Label("Destination :");
        Label pointdepart = new Label("Point Depart :");
        Label objetres = new Label("Objet Reservation :");
        Label type = new Label("Type Reservation :");
        Label Chauffeur = new Label("List Chauffeur :");
        Label contenu = new Label("Contenu Colis :");
        Label poids = new Label("Poids Colis :");
    
        TextField tfdestination = new TextField("","destination");
        TextField tfpointdepart = new TextField("","pointdepart");
        TextField tfcontenu = new TextField("","contenu");
        TextField tfpoids = new TextField("","poids");
        
        ComboBox typereservation = new ComboBox("Choisir Type","Taxi","Privée","camion");
        ComboBox objet=new ComboBox("Choisir objet","Passager","Colis");
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
        DateFormat.MEDIUM,
        DateFormat.MEDIUM);
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Button btnValider = new Button("Add Reservation");
       
    private ComboBox cmb;
    ServiceUser seruser = new ServiceUser();
    public AddReservationForm(Form previous) {
        
        setTitle("Ajouter une Reservation");
       setLayout(BoxLayout.y());
        
         cmb = new ComboBox<>();
        ArrayList<user> users = new ArrayList<>();
        users.addAll(ServiceUser.getInstance().getAllUsers());

        for (user object : users) {
            cmb.addItem(object.getFirstName()+" "+object.getLastName());
        }
         cmb.addActionListener((evt) -> {
            user c = users.get(cmb.getSelectedIndex());
            System.out.println(c);
            System.out.println(cmb.getSelectedItem());
        });
        
        tfcontenu.setVisible(false);
        tfpoids.setVisible(false);
        contenu.setVisible(false);
        poids.setVisible(false);
        objet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String s = objet(objet.getSelectedIndex());
                if(s=="colis")
                {
                  tfcontenu.setVisible(true);
                  tfpoids.setVisible(true);
                  contenu.setVisible(true);
                  poids.setVisible(true);
                }
                else
                {
                tfcontenu.setVisible(false);
                tfpoids.setVisible(false);
                contenu.setVisible(false);
                poids.setVisible(false);
                }
            }
        });
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdestination.getText().length()==0)||(tfpointdepart.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                        reservation r = new reservation();
                        colis coli = new colis();
                        r.setDestination(tfdestination.getText());
                        r.setPointdepart(tfpointdepart.getText());
                        r.setObjet(objet(objet.getSelectedIndex()));
                        r.setType_reservation(typereservation(typereservation.getSelectedIndex()));
                        
                        
//                        ArrayList<String> adressedep = new ArrayList<>();
//                        adressedep = ServiceReservation.getInstance().latlong(tfpointdepart.getText());
//                        float latdep = Float.valueOf(adressedep.get(0)) ;
//                        float longtdep = Float.valueOf(adressedep.get(1)) ;
//                        ArrayList<String> adressedest = new ArrayList<>();
//                        adressedest = ServiceReservation.getInstance().latlong(tfdestination.getText());
//                        float latdes = Float.valueOf(adressedest.get(0)) ;
//                        float longtdes = Float.valueOf(adressedest.get(1)) ;
                        
                        
                        
                        
                        double distance = ServiceReservation.getInstance().distance((float)ServiceReservation.getInstance().latitude(tfdestination.getText()),
                                (float)ServiceReservation.getInstance().longitude(tfdestination.getText()),
                                (float)ServiceReservation.getInstance().latitude(tfpointdepart.getText()),
                                (float)ServiceReservation.getInstance().longitude(tfpointdepart.getText()));
                        
                        
                        System.out.println(tfdestination.getText());
                        System.out.println(tfpointdepart.getText());
                        System.out.println(ServiceReservation.getInstance().longitude(tfdestination.getText()));
                        System.out.println(ServiceReservation.getInstance().latitude(tfdestination.getText()));
                        System.out.println(ServiceReservation.getInstance().longitude(tfpointdepart.getText()));
                        System.out.println(ServiceReservation.getInstance().latitude(tfpointdepart.getText()));
                        System.out.println(distance);

                        
                        String da = format.format(date);
                        String heure = da.substring(11,13);
                        
                        System.out.println(da);
                        System.out.println(heure);
                        int h = Integer.valueOf(heure);
                        
                        float prix = prix(typereservation(typereservation.getSelectedIndex()), distance, h);
                        System.out.println(prix);        
                                
                                
                        r.setPrix(prix);
                        if(tfcontenu.getText()!="" || tfpoids.getText()!="")
                        {
                        coli.setContenu(tfcontenu.getText());
                        coli.setPoids(Float.valueOf(tfpoids.getText()));
                        }
                        user c = users.get(cmb.getSelectedIndex());
                        r.setUser_id_chauffeur(c);
                        
                        Dialog dlg = new Dialog("Prix et Distance");
                        Label d = new Label("Distance : " + String.valueOf(distance) + " Km ");
                        Label p = new Label("Prix : " + String.valueOf(prix)+ " DT  ");
                        Button valider = new Button("Valider");
                        Button annuller = new Button("Annuler");
                        dlg.add(d);
                        dlg.add(p);
                        dlg.add(valider);
                        dlg.add(annuller);
                        
                        annuller.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                dlg.dispose();
                            }
                        });
                        valider.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                 if( ServiceReservation.getInstance().addResrvation(r,coli))
                                 {
                                 Dialog.show("Success","Connection accepted",new Command("OK"));
                                 dlg.dispose();}
                        else
                                 {
                                 Dialog.show("ERROR", "Server error", new Command("OK"));
                                 dlg.dispose();
                                 }
                            }
                        });
                        dlg.showDialog();
                        

                }
                
                
            }
        });
        
        
        
        this.addAll(destination,tfdestination,pointdepart,tfpointdepart,objetres,objet,type,typereservation,Chauffeur,cmb,contenu,tfcontenu,poids,tfpoids,btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    public String objet(int index)
    {
       
        if (index==1) 
        return "passager";
        else
            return "colis";
     
    }
    
    public String typereservation(int index)
    {
     
        if (index == 1)
            return "Taxi";
        else
            if(index == 2)
                return "Privée";
        else
                return "camion";
        
    }
    
    
    
    
    
    public float prix(String type,double distance,int date)
    {
        float p=0 ;
        if (type.equals("Taxi") && date<21 )
        {
            p= 450+(400*(float)distance);
            return p;
        }
        if (type.equals("Taxi") && date>21 )
        {
            p= 700+(500*(float)distance);
            return p;
        }
        if (type.equals("Privée") && date<21)
        {
           p= 1000+(600*(float)distance);
           return p;
        }
        if (type.equals("Privée") && date>21)
        {
            p= 1500+(650*(float)distance);
            return p;
        }
        if (type.equals("camion") && date>21)
        {
            p= 4500+(2100*(float)distance);
            return p;
        }
        if (type.equals("camion") && date<21)
        {
            p= 3500+(1800*(float)distance);
            return p;
        }
        return p;
    
    }
    
    
}
