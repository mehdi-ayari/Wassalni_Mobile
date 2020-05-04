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
import com.mycompany.myapp.services.ServiceReservation;
import java.util.Date;

/**
 *
 * @author Mahdi
 */
public class AddReservationForm extends Form {

    public AddReservationForm(Form previous) {
        
        setTitle("Ajouter une Reserver");
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
        
        Button btnValider = new Button("Add Reservation");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdestination.getText().length()==0)||(tfpointdepart.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        reservation r = new reservation(tfdestination.getText(),tfpointdepart.getText(),date
                                ,objet(objet.getSelectedIndex()),typereservation(typereservation.getSelectedIndex()));
                        if( ServiceReservation.getInstance().addResrvation(r))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfdestination,tfpointdepart,typereservation,objet,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    public String objet(int index)
    {
       
        if (index==1) 
        return "Passager";
        else
            return "Colis";
     
    }
    
    public String typereservation(int index)
    {
     
        if (index==1)
            return "Taxi";
        else
            if(index == 2)
                return "Privée";
        else
                return "camion";
        
    }
    
}
