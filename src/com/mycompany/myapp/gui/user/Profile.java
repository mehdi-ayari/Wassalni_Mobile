/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.user;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.gui.Reservation.AddBusinessForm;
import com.mycompany.myapp.gui.Reservation.AddReservationForm;
import com.mycompany.myapp.gui.Reservation.ListBusinessForm;
import com.mycompany.myapp.gui.Reservation.ListReservationForm;
import com.mycompany.myapp.gui.reclamation.AddRec;
import com.mycompany.myapp.gui.reclamation.ShowReclamation;
import com.mycompany.myapp.gui.voyage.AddRecVoyage;
import com.mycompany.myapp.gui.voyage.ListRecVoy;
import com.mycompany.myapp.gui.voyage.ListVoyagesForm;
import com.news.gui.NewsClient;
//import com.mycompany.myapp.gui.StockHomeForm;
//import com.mycompany.myapp.gui.commande.CommandeForm;
//import com.mycompany.myapp.gui.gererentrepot.EntrepotALouerForm;
//import com.mycompany.myapp.gui.gererentrepot.ListEntrepot;
//import com.mycompany.myapp.gui.gererentrepot.LocationListe;
//import com.mycompany.myapp.transporteur.gui.HomeContratFrom;
//import com.mycompany.myapp.transporteur.gui.HomeTransporteurForm;
//import com.mycompany.myapp.transporteur.gui.map;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Profile extends Form {
   
    Form profile;
private Resources theme;
    public Profile (user u){
   
        profile = this;
        setTitle("Profile");
        setLayout(BoxLayout.y());
         Style loginStyle = getAllStyles();
        loginStyle.setBgColor(0xDCDCDC);
        theme = UIManager.initFirstTheme("/theme");
        //***********************  LOGO
        
//        ImageViewer  Logo = null ;
//                try {
//          Logo = new ImageViewer(Image.createImage("/logo.png").scaled(500, 500));
//        } catch (IOException ex) {
//        }
        
        
        
        
        //************************** TOOLBAR ******************************
        
                        getToolbar().addCommandToOverflowMenu("LogOut",
                null, ev -> {
                    new Login().show();
                });
                
                  getToolbar().addCommandToOverflowMenu("Edit Profile",
                null, ev -> {
                    new EditProfile(profile,u).show();
                });     
        //******************** LABELS
         Label nomL = new Label("Nom :  "+u.getFirstName());    
         
         nomL.getAllStyles().setFgColor(0x000000);
         Label prenomL = new Label("Prénom :  "+u.getLastName()); 
         prenomL.getAllStyles().setFgColor(0x000000);
         Label usernameL = new Label("Username :  "+u.getUsername()); 
         usernameL.getAllStyles().setFgColor(0x000000);
         Label emailL = new Label("Email :  "+u.getEmail());  
         emailL.getAllStyles().setFgColor(0x000000);
         
         Label telL = new Label("Numéro de Téléphone :  "+u.getTelephone());
         telL.getAllStyles().setFgColor(0x000000);
         
         addAll(usernameL,nomL,prenomL,emailL,telL);
          
         if(u.getRole_user().equals("client")){
              getToolbar().addCommandToLeftSideMenu("Add Reservation",  null , (evt) -> {
          
           new AddReservationForm(profile).show();
        
       });  
              getToolbar().addCommandToLeftSideMenu("List Reservation",  null , (evt) -> {
          
           new ListReservationForm(profile).show();
        
       });  
              
                getToolbar().addCommandToLeftSideMenu("List Reclamation Voyage",  null , (evt) -> {
          
           new ListRecVoy(profile).show();
        
       });  
                 getToolbar().addCommandToLeftSideMenu("List Voyage",  null , (evt) -> {
          
           new ListVoyagesForm(profile).show();
        
       });  
                   getToolbar().addCommandToLeftSideMenu("Show Reclamation",  null , (evt) -> {
          
           new ShowReclamation(profile).show();
        
       });  
                    getToolbar().addCommandToLeftSideMenu("News",  null , (evt) -> {
          
           new NewsClient(profile, theme).show();
        
       });  
              
         } 
         else if(u.getRole_user().equals("entreprise")){
              getToolbar().addCommandToLeftSideMenu("Add Reservation Business",  null , (evt) -> {
          
           new AddBusinessForm(profile).show();
        
       });  
               getToolbar().addCommandToLeftSideMenu("List Business",  null , (evt) -> {
          
           new ListBusinessForm(profile).show();
        
       });  
        
    }

    }}