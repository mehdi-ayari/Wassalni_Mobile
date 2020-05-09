/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.services.ServiceReservation;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mahdi
 */
public class AddReservationForm extends Form {
        
    private ComboBox cmb;
    ServiceUser seruser = new ServiceUser();
    public AddReservationForm(Form previous) {
        
        setTitle("Ajouter une Reservation");
       setLayout(BoxLayout.y());
        
        TextField tfdestination = new TextField("","destination");
        TextField tfpointdepart = new TextField("","pointdepart");
        ComboBox typereservation = new ComboBox("Taxi","Privée","camion");
        ComboBox objet=new ComboBox("Passager","Colis");
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
        DateFormat.MEDIUM,
        DateFormat.MEDIUM);
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        cmb = new ComboBox<>();
        ArrayList<user> users = new ArrayList<>();
        users.addAll(seruser.getAllUsers());

        for (user object : users) {
            String ch="CHAUFFEUR";
            if(object.getRole_user().indexOf(ch)!=0)
            cmb.addItem(object.getFirstName());
        }
        
        Button btnValider = new Button("Add Reservation");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdestination.getText().length()==0)||(tfpointdepart.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                        reservation r = new reservation();
                        r.setDestination(tfdestination.getText());
                        r.setPointdepart(tfpointdepart.getText());
                        r.setObjet(objet(objet.getSelectedIndex()));
                        r.setType_reservation(typereservation(typereservation.getSelectedIndex()));
                        r.setPrix(20);
                        if( ServiceReservation.getInstance().addResrvation(r))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   
                    
                }
                
                
            }
        });
        
        addAll(tfdestination,tfpointdepart,typereservation,objet,cmb,btnValider);
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
