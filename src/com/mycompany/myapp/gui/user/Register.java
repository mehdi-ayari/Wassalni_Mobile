/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.user;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.services.user.UserService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Register extends Form {
    
   
    
    public Register(Form previous) {
       
        
                 setTitle("S'inscrire");
        setLayout(BoxLayout.y());
        Style registerStyle = getAllStyles();
        registerStyle.setBgColor(0xDCDCDC);
        
        Container cnt0 = new Container(new FlowLayout(Container.CENTER));
        Container cnt1 = new Container(new FlowLayout(Container.CENTER));
        Container cnt2 = new Container(new FlowLayout(Container.CENTER));
        Container cnt3 = new Container(new FlowLayout(Container.CENTER));
        Container cnt4 = new Container(new FlowLayout(Container.CENTER));
        Container cnt5 = new Container(new FlowLayout(Container.CENTER));
        Container cnt6 = new Container(new FlowLayout(Container.CENTER));
        Container cnt7 = new Container(new FlowLayout(Container.CENTER));
        Container cnt8 = new Container(new FlowLayout(Container.CENTER));
        Container cnt9 = new Container(new FlowLayout(Container.CENTER));
        Container cnt10 = new Container(new FlowLayout(Container.CENTER));

        
//        ImageViewer  Logo = null ;
//          try {
//          Logo = new ImageViewer(Image.createImage("/logo.png").scaled(500, 500));
//        } catch (IOException ex) {System.out.println("probleme dans l'image logo"); }
//          
          //********************************************* Username
          
                          TextField Username = new TextField("", "username");
        Style userStyle = Username.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle.setBgColor(0xffffff);
        userStyle.setBgTransparency(255);
        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
     //   userStyle.setMargin(Component.BOTTOM, 3);
       // userStyle.setMargin(Component.TOP, 10);
        
        //*************************************** NOM
        
                        TextField nom = new TextField("", "nom");
        Style nomStyle = nom.getAllStyles();
        nomStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        nomStyle.setBgColor(0xffffff);
        nomStyle.setBgTransparency(255);
        nomStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
      
          
        //************************* prenom  
        
                                TextField prenom = new TextField("", "prenom");
        Style prenomStyle = prenom.getAllStyles();
        prenomStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        prenomStyle.setBgColor(0xffffff);
        prenomStyle.setBgTransparency(255);
        prenomStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
      
        
        //************************* email 
       TextField email = new TextField("", "email");
        Style emailStyle = email.getAllStyles();
        emailStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        emailStyle.setBgColor(0xffffff);
        emailStyle.setBgTransparency(255);
        emailStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
      
        
        //************************* password
        
        TextField password = new TextField("", "Password");
        Style passwordStyle = password.getAllStyles();
        passwordStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle.setBgColor(0xffffff);
        userStyle.setBgTransparency(255);
        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        password.setConstraint(TextField.PASSWORD);
        
        //*************************** Role
                ComboBox role = new ComboBox();
                role.addItem("client");
                role.addItem("entreprise");
               // role.addItem("Transporteur");
                
        Style roleStyle = role.getAllStyles();
        roleStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        roleStyle.setBgColor(0xffffff);
        roleStyle.setBgTransparency(255);
        roleStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
      
      
        //*************************** CIN
        
                TextField cin = new TextField("", "CIN");
        Style cinStyle = cin.getAllStyles();
        cinStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        cinStyle.setBgColor(0xffffff);
        cinStyle.setBgTransparency(255);
        cinStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        
      
        //******************************* TEL
        
        TextField tel = new TextField("", "Num Telephone");
        Style telStyle = tel.getAllStyles();
        telStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        telStyle.setBgColor(0xffffff);
        telStyle.setBgTransparency(255);
        telStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        
        //************************ BTN SINSCRIRE
        
        Button valider = new Button("S'inscrire");
        
         Style butStyle1 = valider.getAllStyles();
                      butStyle1.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        
        
     butStyle1.setFgColor(0x474747);
        butStyle1.setBgTransparency(0);
        butStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle1.setMargin(Component.TOP, 0);
        
        //*********************** BTN ALRDY HAVE ACC
        
        Button deja = new Button("Already have an account LogIn here");
        
         Style butStyle2 = deja.getAllStyles();
                      butStyle2.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        
        
     butStyle2.setFgColor(0x474747);
        butStyle2.setBgTransparency(0);
        butStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle2.setMargin(Component.TOP, 4);
      
      
        //************************ ADDing to Form
        
       // cnt0.add(Logo);
        cnt1.add(Username);
        cnt2.add(nom);
        cnt3.add(prenom);
        cnt4.add(email);
        cnt5.add(password);
        cnt6.add(role);
        cnt7.add(cin);
        cnt8.add(tel);
        cnt9.add(valider);
        cnt10.add(deja);
        
        add(cnt0);
        add(cnt1);
        add(cnt2);
        add(cnt3);
        add(cnt4);
        add(cnt5);
        add(cnt7);
        add(cnt8);
        add(cnt6);
        add(cnt9);
        add(cnt10);

        //****************************** GO TO LOGIN
        
        deja.addActionListener((evt) -> {
        
            new Login().show();
            
            });
        //****************** SINCRIRE ACTION
        
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Username.getText().length()==0)||(nom.getText().length()==0)||(prenom.getText().length()==0)
                        ||(email.getText().length()==0)||(password.getText().length()==0)||
                        (tel.getText().length()==0)||(cin.getText().length()==0)
                        )
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
             else {  if(UserService.getInstance().usernameExists(Username.getText())) {
                    Dialog.show("Alert", "This username already exists", new Command("OK"));
                }
             else    if(UserService.getInstance().emailExists(email.getText())) {
                    Dialog.show("Alert", "This email already exists", new Command("OK"));
                }
                else
                {
                    try {
                   
                        user u = new user();
                        u.setUsername(Username.getText());
                        u.setFirstName(nom.getText());
                        u.setLastName(prenom.getText());
                        u.setEmail(email.getText());
                        u.setPassword(password.getText());
                        System.out.println(role.getSelectedItem().toString());
                        if (role.getSelectedItem().toString()=="Transporteur")
                        {u.setRole_user(role.getSelectedItem().toString()+"Libre");}
                        else
                            {u.setRole_user(role.getSelectedItem().toString());}
                        u.setTelephone(Integer.parseInt(tel.getText()));
                        
                        if( UserService.getInstance().addUser(u)) {
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            Statics.setLoggedPlainPW(password.getText());
                        //  go To profile
                        
                        new Profile(u).show();
                        }
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "CIN and Telephone must be numbers", new Command("OK"));
                    }
                    
                }
                
                }
            }
        });
            
        
        
    }
    
    
}