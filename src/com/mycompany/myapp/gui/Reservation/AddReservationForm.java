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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.colis;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.services.Reservation.ServiceReservation;
import com.mycompany.myapp.services.Reservation.ServiceUser;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mahdi
 */
public class AddReservationForm extends Form {
        TextField tfdestination = new TextField("","destination");
        TextField tfpointdepart = new TextField("","pointdepart");
        TextField tfcontenu = new TextField("","contenu");
        TextField tfpoids = new TextField("","poids");
        
        ComboBox typereservation = new ComboBox("Taxi","Privée","camion");
        ComboBox objet=new ComboBox("Passager","Colis");
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
        objet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String s = objet(objet.getSelectedIndex());
                if(s=="colis")
                {
                  tfcontenu.setVisible(true);
                  tfpoids.setVisible(true);
                }
                else
                {
                tfcontenu.setVisible(false);
                tfpoids.setVisible(false);
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
                        r.setPrix(20);
                        if(tfcontenu.getText()!="" || tfpoids.getText()!="")
                        {
                        coli.setContenu(tfcontenu.getText());
                        coli.setPoids(Float.valueOf(tfpoids.getText()));
                        }
                        user c = users.get(cmb.getSelectedIndex());
                        r.setUser_id_chauffeur(c);
                        
                        if( ServiceReservation.getInstance().addResrvation(r,coli))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   
                    
                }
                
                
            }
        });
        
        
        
        this.addAll(tfdestination,tfpointdepart,objet,typereservation,cmb,tfcontenu,tfpoids,btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    public String objet(int index)
    {
       
        if (index==0) 
        return "passager";
        else
            return "colis";
     
    }
    
    public String typereservation(int index)
    {
     
        if (index==0)
            return "Taxi";
        else
            if(index == 1)
                return "Privée";
        else
                return "camion";
        
    }
    
}
