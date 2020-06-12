/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reservation;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


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
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Display;



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
        String accountSID = "AC16fce352cd301d805ce075ba4b7969d0";
        String authToken = "90023e188332683906bd451a7a227703";
        String fromPhone = "+14157544698";
        public static final String ACCOUNT_SID = "AC16fce352cd301d805ce075ba4b7969d0";
    public static final String AUTH_TOKEN = "90023e188332683906bd451a7a227703";
    
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
                    Dialog.show("Alert", "SVP entrée une destination et une point depart", new Command("OK"));
                else if ((typereservation.getSelectedItem().toString().equals("Choisir Type")))
                    Dialog.show("Alert", "SVP selectionner un type de reservation", new Command("OK"));
                else if ((objet.getSelectedItem().toString().equals("Choisir objet")))
                    Dialog.show("Alert", "SVP selectionner un objet ", new Command("OK"));
                else if ((objet(objet.getSelectedIndex()).equals("colis")) &&(tfcontenu.getText().length()==0))
                    Dialog.show("Alert", "SVP entré un contenu ", new Command("OK"));
                else if ((objet(objet.getSelectedIndex()).equals("colis")) && (tfpoids.getText().length()==0))
                    Dialog.show("Alert", "SVP entré un poids ", new Command("OK"));
                else if ((objet(objet.getSelectedIndex()).equals("colis")) && (isValidFloat(tfpoids.getText())==false))
                    Dialog.show("Alert", "Le poids doit numerique ", new Command("OK"));
                else
                {
                    try {
                        //                    Location position = LocationManager.getLocationManager().getLastKnownLocation();
//                    double la = position.getLatitude();
//                    double lo = position.getLongitude();
//


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

List<Double> latlongdest = ServiceReservation.getInstance().geocode(tfdestination.getText());
List<Double> latlongdepar = ServiceReservation.getInstance().geocode(tfpointdepart.getText());
Coord origin = new Coord(latlongdepar.get(1),latlongdepar.get(0));
Coord direction = new Coord(latlongdest.get(1),latlongdest.get(0));
double distance;

distance = ServiceReservation.getInstance().getDirections(origin, direction)/1000;
System.out.println(distance);



//                        double distance = ServiceReservation.getInstance().distance((float)ServiceReservation.getInstance().latitude(tfdestination.getText()),
//                                (float)ServiceReservation.getInstance().longitude(tfdestination.getText()),
//                                (float)ServiceReservation.getInstance().latitude(tfpointdepart.getText()),
//                                (float)ServiceReservation.getInstance().longitude(tfpointdepart.getText()));
//

System.out.println(tfdestination.getText());
System.out.println(tfpointdepart.getText());



String da = format.format(date);  
String heure = da.substring(11,13);

System.out.println(da);
System.out.println(heure);
int h = Integer.valueOf(heure);

float prix = prix(typereservation(typereservation.getSelectedIndex()), distance, h);
System.out.println(prix);
//                        System.out.println(la);
//                        System.out.println(lo);


//                    try {   
//                        System.out.println(ServiceReservation.getInstance().local());
//                    } catch (IOException ex) {
//                            System.out.println("zabbbbbbbbb");
//                                  
//                    }



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
            dlg.dispose();
        ListReservationForm f = new ListReservationForm(previous);
                f.showBack();
                com.codename1.messaging.Message m;
            m = new com.codename1.messaging.Message("Merci pour votre confiance votre reservation est ajoutée avec succée");
               Display.getInstance().sendMessage(new String[] {"mehdi.ayari@esprit.tn"}, "Ajout Reservation", m);
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+14157544698"),
//                new com.twilio.type.PhoneNumber("+15017122661"),
//                "votre reservation est ajouter avec succée")
//            .create();
//
//        System.out.println(message.getSid());
//        
        }
        else
        {
            Dialog.show("ERROR", "Server error", new Command("OK"));
            dlg.dispose();
        }
    }
});
dlg.showDialog();
                    } catch (IOException ex) {
                        System.out.println("distance");
                    }
                        

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
    
    public static boolean isValidFloat(String str) {
		boolean isValid = false;
		try {
			Integer newInput = Integer.valueOf(str);
			float i = newInput.floatValue();
			isValid = true;
		} finally {
			return isValid;
		}
	}
    
    
}
