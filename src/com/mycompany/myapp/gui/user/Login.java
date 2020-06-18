/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.user;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
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
 * @author Zeineb_yahiaoui
 */
public class Login extends Form {
    
        Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public Login() {
        current = this; //Récupération de l'interface(Form) en cours
          
            

//         System.out.println(UserService.getInstance().logiiin("pi","124"));

//************** LOGOOOOOOO ******************************

         setTitle("Se connecter");
        setLayout(BoxLayout.y());
        Style loginStyle = getAllStyles();
        loginStyle.setBgColor(0xDCDCDC);
        
        Container cnt1 = new Container(new FlowLayout(Container.CENTER));
        Container cnt2 = new Container(new FlowLayout(Container.CENTER));
        Container cnt3 = new Container(new FlowLayout(Container.CENTER));
        Container cnt4 = new Container(new FlowLayout(Container.CENTER));
        Container cnt5 = new Container(new FlowLayout(Container.CENTER));
        Container cnt6 = new Container(new FlowLayout(Container.CENTER));
//ImageViewer  Logo = null ;
//                try {
//          Logo = new ImageViewer(Image.createImage("/logo.png").scaled(500, 500));
//        } catch (IOException ex) {
//        }


        /**
         * ***********************************************************************************************
         */
//        Style logoStyle = Logo.getAllStyles();
        //logoStyle.setMargin(Component.TOP, 20);
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
        

        
        //********************************** TEXTFIELD USERNME ********************************************
        
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
        userStyle.setMargin(Component.BOTTOM, 3);
        userStyle.setMargin(Component.TOP, 10);
        
          //********************************** TEXTFIELD PASSWORD ********************************************
        
           TextField tpassword = new TextField();
        Style passwordStyle = tpassword.getAllStyles();
        passwordStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(50).
                stroke(borderStroke));
        passwordStyle.setBgColor(0xffffff);
        passwordStyle.setBgTransparency(255);

        tpassword.setHint("mot de passe");
        tpassword.setConstraint(TextField.PASSWORD);
        

        
        // **************************** BTN VALIDER ***************************
        
                Button btnval = new Button("Se connecter ");
        Style butStyle = btnval.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        
  
        butStyle.setFgColor(0x474747);

      
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 3);
        butStyle.setMargin(Component.TOP, 10);
        butStyle.setMargin(Component.LEFT, 10);
        butStyle.setMargin(Component.RIGHT, 10);
        
       //*************************** BTN S INSCRIRE *****************************
        
         Button inscrire = new Button("s'inscrire");
        Style butStyle2 = inscrire.getAllStyles();
                      butStyle2.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        
        
     butStyle2.setFgColor(0x474747);
        butStyle2.setBgTransparency(0);
        butStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle2.setMargin(Component.TOP, 0);
        
        //**************************** BTN lost mdp ***************************
        
                Button motOublier = new Button("Mot de passe oublié ?");
        Style butStyle1 = motOublier.getAllStyles();

                butStyle1.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
       
  
        butStyle1.setFgColor(0x000000);
        butStyle1.setBgTransparency(0);
        butStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle1.setMargin(Component.TOP, 10);
        
               // ****************** adding to Form **********************
        //cnt1.add(Logo);
        cnt2.add(Username);
        cnt3.add(tpassword);
        cnt4.add(btnval);
        cnt5.add(inscrire);
        cnt6.add(motOublier);
        
        
        add(cnt1);
        add(cnt2);
        add(cnt3);
        add(cnt4);
        add(cnt5);
        add(cnt6);
        
        //*************************** EXIT APP *****************************
        
                getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });
                
        //************************ LOGIN ACTION ***************************
        
        
        
        btnval.addActionListener((evt) ->
                
        {
            
         
          //  System.out.println(Username.getText()+tpassword.getText());
          if ((Username.getText().length() == 0) || (tpassword.getText().length() == 0)) {
                    Dialog.show("Alert", "vous devez saisir votre username et mot de passe", new Command("OK"));
                }  
   
          ConnectionRequest req;
          req = new ConnectionRequest();
                   String a =Username.getText();
         String b =tpassword.getText();

                             String jsn = "{\n" +
"    \"username\": \""+a+"\",\n" +
"    \"password\": \""+b+"\"\n" +
"}";
                             
                             String url="http://localhost/PI_Webwassalni/web/app_dev.php/Reservation/logg";
         req.setUrl(url);
         req.setPost(true);
         req.setContentType("application/json");
         req.setRequestBody(jsn);
                             
                   req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               String reponseLog =  req.getResponseErrorMessage();
                req.removeResponseListener(this); //Supprimer cet actionListener
              
              
               Statics.setRstLog(reponseLog);
               // System.out.println(Statics.getRstLog());
                

            }
        });
                   NetworkManager.getInstance().addToQueueAndWait(req);
         
            if ("Unauthorized".equals(Statics.getRstLog())) {
                System.out.println("failed Login");
            }
            else {
                System.out.println("Login successful");
                
                Statics.setIdSession(UserService.getInstance().getLoggedId(Username.getText()));
                System.out.println("id session : "+Statics.getIdSession());
                user u = new user();
                u = UserService.getInstance().getLoggedInfos(Username.getText());
                Statics.setLoggedPlainPW(tpassword.getText());
                new Profile(u).show();
                
            }
          
        });
        
        
        inscrire.addActionListener((evt) -> {
        
           new Register(current).show();
            
        });
        
      motOublier.addActionListener((evt) -> {
         
          new MdpOub().show();
          
          
      });
        
        
        
        
    }

   


    }
    

