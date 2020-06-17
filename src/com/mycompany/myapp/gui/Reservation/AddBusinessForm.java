/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reservation;


import com.mycompany.myapp.gui.*;
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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.reservation_business;
import com.mycompany.myapp.services.Reservation.ServiceBusiness;
import java.util.Date;


/**
 *
 * @author Mahdi
 */
public class AddBusinessForm extends Form {
          DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          Date date = new Date();
          SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AddBusinessForm(Form previous) {
        
       setTitle("Ajouter une Reservation Entreprise");
       setLayout(BoxLayout.y());
       
        Label destination = new Label("Destination :");
        Label pointdepart = new Label("Point Depart :");
        Label nomclient = new Label("Nom Client :");
        Label prenomclient = new Label("Prenom Client :");
        Label date = new Label("Date Depart :");
       
        TextField tfdestination = new TextField("","Destination");
        TextField tfpointdepart = new TextField("","Point Depart");
        TextField tfnomclient = new TextField("","Nom Client");
        TextField tfprenomclient = new TextField("","Prenom Client");
        Label test;
        Picker datedepart = new Picker();
        Button btvalider = new Button("Reserver");
        datedepart.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        btvalider.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
//                        Date datecontrole = datedepart.getDate();
////                        System.out.println(datedept);
//                        String datesaisie = formater.format(datecontrole);
//                        String heurecontrole = format.format(datesaisie);
//                        String heuresaisie = heurecontrole.substring(11,13);
////                        System.out.println(datedepar);
//
//                        String da = format.format(date);  
//                        String heure = da.substring(11,13);
//
////                        System.out.println(da);
////                        System.out.println(heure);
//                        int hc = Integer.valueOf(heure);
//                        int hs = Integer.valueOf(heuresaisie);
                        
               if (tfdestination.getText().length()==0)
                 Dialog.show("Alert", "SVP entrée une destination", new Command("OK"));
               else if (tfpointdepart.getText().length()==0)
                 Dialog.show("Alert", "SVP entrée une point de depart ", new Command("OK"));
               else if((tfnomclient.getText().length()==0))
                 Dialog.show("Alert", "SVP entrée le nom de votre client", new Command("OK"));
               else if ((tfnomclient.getText().length()==0) && (isValidFloat(tfnomclient.getText())==true))
                 Dialog.show("Alert", " le champ nom client ne doit pas etre numerique ", new Command("OK"));
               else if((tfprenomclient.getText().length()==0))
                 Dialog.show("Alert", "SVP entrée le prenom de votre client", new Command("OK"));
               else if ((tfprenomclient.getText().length()==0) && (isValidFloat(tfprenomclient.getText())==true))
                 Dialog.show("Alert", " le champ prenom client ne doit pas entre numerique ", new Command("OK"));
//               else if ((hc<=hs+1))
//                 Dialog.show("Alert", "la date de reservation doit etre au moins aprés une du dadte actuelle", new Command("OK"));
//               else if ((hc<=hs+1))
//                 Dialog.show("Alert", "la date de reservation doit etre au moins aprés une du dadte actuelle", new Command("OK"));
               else
                {
                    reservation_business b = new reservation_business();
                    
                    
                        Date datedept = datedepart.getDate();
                        System.out.println(datedept);
                        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String datedepar = formater.format(datedept);
                        System.out.println(datedepar);
                        
                        
                        b.setDate_depart(datedepar);
                        b.setDestination(tfdestination.getText());
                        b.setPoint_depart(tfpointdepart.getText());
                        b.setNom_client_entreprise(tfnomclient.getText());
                        b.setPrenon_client_entreprise(tfprenomclient.getText());
                        b.setEtat(0);

                        if( ServiceBusiness.getInstance().addResrvation(b))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   
                    
                }
           }
       });
        addAll(destination,tfdestination,pointdepart,tfpointdepart,nomclient,tfnomclient,prenomclient,tfprenomclient,date,datedepart,btvalider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    
                
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
