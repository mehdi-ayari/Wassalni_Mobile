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
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.gui.Reservation.AddReservationForm;
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
    public Profile (user u){
   
        profile = this;
        setTitle("Profile");
        setLayout(BoxLayout.y());
         Style loginStyle = getAllStyles();
        loginStyle.setBgColor(0xDCDCDC);
        
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
              getToolbar().addCommandToLeftSideMenu("commande",  null , (evt) -> {
          
           new AddReservationForm(profile).show();
        
       });  
        
    }

    }}