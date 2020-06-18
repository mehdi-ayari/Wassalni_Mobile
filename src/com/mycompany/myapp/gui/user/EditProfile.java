/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.user;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
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
public class EditProfile extends Form{
 
    
    public EditProfile(Form previous,user u){
        
        setTitle("Edit Profile");
        setLayout(BoxLayout.y());
        Style loginStyle = getAllStyles();
        loginStyle.setBgColor(0xDCDCDC);
        ImageViewer  Logo = null ;
                try {
          Logo = new ImageViewer(Image.createImage("/logo.png").scaled(500, 500));
        } catch (IOException ex) {
        }
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());
   
        
    
    //*********************** CHNAGE USERNAME

        TextField Username = new TextField("", "enter new username");
        Style userStyle = Username.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle.setBgColor(0xffffff);
        userStyle.setBgTransparency(255);
        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        
        
        Button chusername = new Button("change username");
                Style butStyle1 = chusername.getAllStyles();
                 butStyle1.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
               stroke(borderStroke));
        
        
     butStyle1.setFgColor(0x000000);
       
       
       //******************** CHANGE Email
       TextField email = new TextField("", "enter new email");
        Style emailStyle = email.getAllStyles();
        emailStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        emailStyle.setBgColor(0xffffff);
        emailStyle.setBgTransparency(255);
        emailStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
                
       Button chemail = new Button("change email");
       
     Style butStyle2 = chemail.getAllStyles();        
     
                      butStyle2.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
               stroke(borderStroke));
     
     butStyle2.setFgColor(0x000000);
       
       //********************** CHANGE PASSWORD
        TextField oldPassword = new TextField("", "enter Old Password");
        Style passwordStyle = oldPassword.getAllStyles();
        passwordStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        passwordStyle.setBgColor(0xffffff);
        passwordStyle.setBgTransparency(255);
        passwordStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        oldPassword.setConstraint(TextField.PASSWORD);
        
        TextField newPassword = new TextField("", "enter new Password");
        Style newPasswordStyle = newPassword.getAllStyles();
        newPasswordStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        newPasswordStyle.setBgColor(0xffffff);
        newPasswordStyle.setBgTransparency(255);
        newPasswordStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        newPassword.setConstraint(TextField.PASSWORD);
        
        Button chpw = new Button("change password");
        
             Style butStyle3 = chpw.getAllStyles();        
     
                              butStyle3.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
               stroke(borderStroke));
     butStyle3.setFgColor(0x000000);
        
        //*********************** CHANGE TEL
        
                TextField tel = new TextField("", "enter new phone number");
        Style telStyle = tel.getAllStyles();
        telStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        telStyle.setBgColor(0xffffff);
        telStyle.setBgTransparency(255);
        telStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        
        Button chtel = new Button("Change phone number");
       
            Style butStyle4 = chtel.getAllStyles();        
     
                             butStyle4.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
               stroke(borderStroke));
            
     butStyle4.setFgColor(0x000000);
        
  //************************** ADDING TO FORM     
       
    Container cnt1 = new Container(new FlowLayout(Container.CENTER));
    Container cnt2 = new Container(new FlowLayout(Container.CENTER));
    Container cnt3 = new Container(new FlowLayout(Container.CENTER));
    Container cnt4 = new Container(new FlowLayout(Container.CENTER));
        cnt1.add(Username);
        cnt1.add(chusername);
        cnt2.add(email);
        cnt2.add(chemail);
        cnt3.add(oldPassword);
        cnt3.add(newPassword);
        cnt3.add(chpw);
        cnt4.add(tel);
        cnt4.add(chtel);
        
        add(Logo);
        add(cnt1);
        add(cnt2);
        add(cnt3);
        add(cnt4);
    
        //**********************ACTION USERNAME **************************
     
        chusername.addActionListener((evt) -> {
        
            if (Username.getText().length()==0) {
                Dialog.show("Alert", "Please enter a userame", new Command("OK"));
            }
         else   if(UserService.getInstance().usernameExists(Username.getText())) {
                    Dialog.show("Alert", "This username already exists", new Command("OK"));
                }
          else  if (UserService.getInstance().changeUsername(Username.getText())){
                  Dialog.show("Success", "Username changed succesfully", new Command("OK"));
            }
            
            else {
                  Dialog.show("Error", "Connexion error", new Command("OK"));
            }
                
              
            
            
        });
        
        //******************** ACTION EMAIL *******************************
        
        
        chemail.addActionListener((evt) -> {
            
                        if (email.getText().length()==0) {
                Dialog.show("Alert", "Please enter an email", new Command("OK"));
            }
          else if(UserService.getInstance().emailExists(email.getText())) {
                    Dialog.show("Alert", "This email already exists", new Command("OK"));
                }
         else if (UserService.getInstance().changeEmail(email.getText())){
                  Dialog.show("Success", "Email changed succesfully", new Command("OK"));
            }
            
            else {
                  Dialog.show("Error", "Connexion error", new Command("OK"));
            }
           
            
            
        });
        
        
        //********************* ACTION PASSWORD ***************************
        
chpw.addActionListener((evt) -> {
   
             if (oldPassword.getText().length()==0 || newPassword.getText().length()==0 ) {
                Dialog.show("Alert", "Please enter your old password and your new password", new Command("OK"));
            }
         else if(!Statics.getLoggedPlainPW().equals(oldPassword.getText())) {
             Dialog.show("Alert", "your old password is incorrect", new Command("OK"));
         }
         else if (UserService.getInstance().changePassword(newPassword.getText())){
                  Dialog.show("Success", "Password changed succesfully", new Command("OK"));
            }
            
            else {
                  Dialog.show("Error", "Connexion error", new Command("OK"));
            }
    
});

//********************************* TEL ACTION**************************

chtel.addActionListener((evt) -> {
   
    try {
          if (tel.getText().length()==0) {
                Dialog.show("Alert", "Please enter a phone number", new Command("OK"));
            }
         else if (UserService.getInstance().changeTel(Integer.parseInt(tel.getText()))){
                  Dialog.show("Success", "Phone number changed succesfully", new Command("OK"));
            }
            
            else {
                  Dialog.show("Error", "Connexion error", new Command("OK"));
            }
                              } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "phone number must be a number", new Command("OK"));
                    }
           
    
});
        
    }
    
    

        
    
    
}
