/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.l10n.DateFormat;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.reservation_business;
import com.mycompany.myapp.services.ServiceBusiness;
import java.util.Date;


/**
 *
 * @author Mahdi
 */
public class AddBusinessForm extends Form {
  
    public AddBusinessForm(Form previous) {
        
       setTitle("Ajouter une Reservation Entreprise");
       setLayout(BoxLayout.y());
       
        TextField tfdestination = new TextField("","Destination");
        TextField tfpointdepart = new TextField("","Point Depart");
        TextField tfnomclient = new TextField("","Nom Client");
        TextField tfprenomclient = new TextField("","Prenom Client");
        Picker datedepart = new Picker();
        Button btvalider = new Button("Reserver");
        datedepart.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        btvalider.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               if ((tfdestination.getText().length()==0)||(tfpointdepart.getText().length()==0)
                       ||(tfnomclient.getText().length()==0)||(tfprenomclient.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                        reservation_business b = new reservation_business();
                        b.setDestination(tfdestination.getText());
                        b.setPoint_depart(tfpointdepart.getText());
                        b.setNom_client_entreprise(tfnomclient.getText());
                        b.setPrenon_client_entreprise(tfprenomclient.getText());
                        String d = datedepart.getDate().toString();
                        String dd = d.substring(4,d.length());
                        SimpleDateFormat formatter = new SimpleDateFormat();
                        Date s = null;
                   try {
                       s = formatter.parse(dd);
                   } catch (ParseException ex) {
                       System.out.println("erreur");
                   }
                        b.setDate_depart(s);
                        if( ServiceBusiness.getInstance().addResrvation(b))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   
                    
                }
           }
       });
        addAll(tfdestination,tfpointdepart,tfnomclient,tfprenomclient,datedepart,btvalider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    
                
    }
    
    
    
}
