/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.reclamation;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.TypeRec;
import com.mycompany.myapp.services.reclamation.ReclamationService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class AddRec extends Form{
Form me ;
     ComboBox combo;
       //ArrayList<TypeRec> types = new ArrayList<>() ;
       ArrayList<TypeRec> list = new ArrayList<>() ;
       
      public AddRec(Form previous, ArrayList<TypeRec> types) throws IOException {
        ArrayList<String> stringsArray;
        ReclamationService myService = new ReclamationService();
        stringsArray = new ArrayList<String>();
        if ( types.size() > 0 ) {
        } else {
            list = myService.getAllTypes();
        }
        me=this;
        setTitle("Add  a new reclamation");
        setLayout(BoxLayout.y());
        Label typeLabel = new Label("selctionner Type");
        TextField titre= new TextField("", "titre");
        TextField description = new TextField("", "Description", 20, TextArea.ANY);
        description.setMaxSize(300);

         Picker stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        //stringPicker.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
        //"A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
        //stringPicker.setSelectedString("A Game of Thrones");
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
            Log.p("strings = " + strings);
            stringPicker.setStrings(strings);
        } else {
            Log.p("noo val");
        }
        
        
                  

       Button btnValider = new Button("Add Reclamation");
        addAll(titre,description,btnValider);
       btnValider.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int idt = 20;
                if ((titre.getText().length()==0) || (description.getText().length()==0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                }
                else
                {
                    idt = types.get(stringPicker.getSelectedStringIndex()).getId();
                    ConnectionRequest con = new ConnectionRequest();
                    con.setPost(false);
                    String tit = titre.getText();
                    String desc = description.getText();
                    int userId = 1;
                    con.setUrl("http://localhost/PI_WebWassalni/web/app_dev.php/Reclamation/addRec/"+idt+"/"+userId+"/"+tit+"/"+desc);
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                    System.out.println("done comment!");
                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println("response : " + s);
                    System.out.println("response : " + evt.getMetaData().equals("true"));
                    if ( s.equals("\"success\"")) {
                    Dialog.show("Confirmation", "ajout ok", "Ok", null);
                    //Mail m = new Mail();
                    // previous.showBack();
                    Mail m = new Mail();
                    ShowReclamation l = new ShowReclamation(me);
                    l.show();
                    // to do c.refreshTheme();
                    }
                    else {
                    Dialog.show("Error", "ajout not ok", "Not Ok", null);
                    }
                    }
                    });
                    NetworkManager.getInstance().addToQueue(con);
                }
            }
        });
               /*
           CommandeF t = new CommandeF(date.getDate(),Integer.parseInt(soc.getText()));
        
                       if( ServiceCommandef.getInstance().addcomm2(String.valueOf(date.getDate()), quantite.getText(), soc.getText(), prod.getText() ))
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                       else
                           Dialog.show("ERROR", "Server error", new Command("OK"));
                       
                    } catch (NumberFormatException e) {
                       Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                   }
                    
           }
                
                });*/
      
         
    
       
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> {
                previous.showBack();
                stringsArray.clear();
                //stringsArray2.clear();
                        });
            
           
           }
       private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
}