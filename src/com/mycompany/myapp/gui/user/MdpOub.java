/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.user;

import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.services.user.UserService;
import java.io.IOException;
import java.util.Random;


/**
 *
 * @author ASUS
 */
public class MdpOub extends Form {
    
    public MdpOub(){
    
        setTitle("Récupérer le mot de passe");
        setLayout(BoxLayout.y());
        Style loginStyle = getAllStyles();
        loginStyle.setBgColor(0xDCDCDC);
      
        //***************************** TXT AREA *******************
        
        String msg ="Vous avez oublié votre mot de passe ? tapez votre email et cliquez sur ENVOYER et votre nouveau mot de passe vous sera envoyé";
        TextArea hint = new TextArea(msg);
        
        //****************************** LOGO ************************
        
//        ImageViewer  Logo = null ;
//                try {
//          Logo = new ImageViewer(Image.createImage("/logo.png").scaled(500, 500));
//        } catch (IOException ex) {
//        }
        
        //*************************** TEXT FIELD EMAIL ***********************
        
                        TextField email = new TextField("", "username");
        Style userStyle = email.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle.setBgColor(0xffffff);
        userStyle.setBgTransparency(255);
        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle.setMargin(Component.BOTTOM, 3);
        userStyle.setMargin(Component.TOP, 7);
                
        //************************** BTN ENV ***********************************

        Button env = new Button("ENVOYER");
        
                Style butStyle1 = env.getAllStyles();       
     

                        Style butStyle5 = env.getAllStyles();
                      butStyle5.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        
        
     butStyle5.setFgColor(0x474747);
        butStyle5.setBgTransparency(0);
        butStyle5.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle5.setMargin(Component.TOP, 7);
        
        //************************** BTN GOLOGIN ****************************
        
                Button goLogin = new Button("Login from here");
        

                        Style butStyle2 = goLogin.getAllStyles();
                      butStyle2.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        
        
     butStyle2.setFgColor(0x474747);
        butStyle2.setBgTransparency(0);
        butStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle2.setMargin(Component.TOP, 7);
        
         //***************** AcTION *************************
         
         env.addActionListener((evt) -> {
         
     //   String randomNum = Integer.toString(ThreadLocalRandom.current().nextInt(100, 10000));

       //      System.out.println(randomNum);  
       
       if (email.getText().length()==0)
           { Dialog.show("Alert", "Please enter your email", new Command("OK"));}
       else {
           if(UserService.getInstance().emailExists(email.getText())==false) {
                    Dialog.show("Alert", "This email doesn't exist", new Command("OK"));
                }
           
           else {
               
                      Random num = new Random();
      int randomNum = num.nextInt();
      
      int nvMdp = randomNum % 10000;
      
      if (nvMdp<0)
      {
      nvMdp = nvMdp + 15000;
      }
           // System.out.println(nvMdp);
           String mdp = String.valueOf(nvMdp);
             System.out.println("mdp est :"+mdp);

    
             UserService.getInstance().getIdByEmail(email.getText());
             
            
 Message m = new Message("Votre nouveau mot de passe est : "+mdp);

Display.getInstance().sendMessage(new String[] {email.getText()}, "pi", m); 
               
       int idRecover = UserService.getInstance().getIdByEmail(email.getText());

       if (UserService.getInstance().recoverPassword(idRecover, mdp)) {
                   Dialog.show("Success", "an email has been sent to you containing your password", new Command("OK"));
       }
       else {
           Dialog.show("Alert", "Connection fail", new Command("OK"));
       }
           }
       }
       

          
            
         });
         
         //*********************** GO LOGIN ACTION *********************
         

                 goLogin.addActionListener((evt) -> {
        
            new Login().show();
            
            });

         //******************* ADDING TO FORM ************************
         
        Container cnt1 = new Container(new FlowLayout(Container.CENTER));
        Container cnt2 = new Container(new FlowLayout(Container.CENTER));
        Container cnt3 = new Container(new FlowLayout(Container.CENTER));
        Container cnt4 = new Container(new FlowLayout(Container.CENTER));
        Container cnt5 = new Container(new FlowLayout(Container.CENTER));
         
                
        
    
    //cnt1.add(Logo);
    cnt2.add(hint);
    cnt3.add(email);
    cnt4.add(env);
    cnt5.add(goLogin);
    
    add(cnt1);
    add(cnt2);
    add(cnt3);
    add(cnt4);
    add(cnt5);
    
}
    
}
