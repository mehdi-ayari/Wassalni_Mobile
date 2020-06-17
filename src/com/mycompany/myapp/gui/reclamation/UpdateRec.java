/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.reclamation;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.TypeRec;
import com.mycompany.myapp.services.reclamation.ReclamationService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class UpdateRec extends Form{
     
     
     Form me ;
     ComboBox combo;
      Form previous = Display.getInstance().getCurrent();
        int indext = 0;
        
       public UpdateRec(Reclamation r, ArrayList<TypeRec> types) throws IOException {
        ArrayList<String> stringsArray;
        stringsArray = new ArrayList<String>();
        ReclamationService myService = new ReclamationService();
           //*********************
        me=this;
        setTitle("Modifier reclamation");
        setLayout(BoxLayout.y());
        
       Button btnValider = new Button("Modifier Reclamation");
       TextField titre= new TextField("", "Titre",0,TextField.NUMERIC);
       titre.setText(r.getTitre());
       TextField description= new TextField("", "Description",0,TextField.NUMERIC);
       description.setText(r.getDescription());
       Label titreLabel = new Label("Titre");
       Label descriptionLabel = new Label("Description");
      
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
       
       //******************
       Label typeLabel = new Label("selctionner type");
         Picker stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        //stringPicker.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
        //"A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
        //stringPicker.setSelectedString("A Game of Thrones");
        me.add(titreLabel);
        me.add(titre);
        me.add(descriptionLabel);
        me.add(description);
        me.add(typeLabel);
        me.add(stringPicker);
        me.getStyle().setBgColor(ColorUtil.WHITE);
        me.getStyle().setBgTransparency(255);
        stringsArray.clear();
        if (types.size() > 0) {
            for (TypeRec t : types) {
            //stringPicker.setPropertyValue(String.valueOf(p.getIdp()), p.getNom());
            stringsArray.add(t.getType());
            }
            String[] strings = new String[types.size()];
            stringsArray.toArray(strings);
            stringPicker.setStrings(strings);
        } else {
            Log.p("noo val");
        }
        //stringPicker.setSelectedString(rec.getProduit().getNom());
        me.add(btnValider);
       //******************
       btnValider.addActionListener((ActionListener) (ActionEvent evt) -> {
           int indexT = indext;
           if( stringPicker.getSelectedStringIndex() >= 0 ) {
               indexT = stringPicker.getSelectedStringIndex();
           }
           int idt = types.get(indexT).getId();
           if ((titre.getText().length()==0) || (description.getText().length()==0)) 
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
           else
           {
               int identifiant = r.getId();
               ConnectionRequest con = new ConnectionRequest();
               con.setPost(false);
               String newTit = titre.getText();
               String newDesc = description.getText();
               con.setUrl("http://localhost/PI_WebWassalni/web/app_dev.php/Reclamation/updRec/"+identifiant+"/"+idt+"/"+newTit+"/"+newDesc);
               con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        System.out.println("done comment!");
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        Log.p("response : " + s);
                        if ( s.equals("\"success\"")) {
                            Dialog.show("Confirmation", "modified successfully", "Ok", null);
                            ShowReclamation l = new ShowReclamation(me);
                    l.show();
                            //commandeaff2 c = new commandeaff2(me);
                            //c.show();
                        }
                        else {
                            Dialog.show("Error", "not modified", "Not Ok", null);
                        }
                    }
                });
               NetworkManager.getInstance().addToQueue(con);
            }
        });
      }

    
}

    

