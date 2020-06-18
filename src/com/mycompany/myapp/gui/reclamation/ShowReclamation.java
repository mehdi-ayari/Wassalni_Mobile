/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.reclamation;


import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.TypeRec;
import com.mycompany.myapp.services.reclamation.ReclamationService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ShowReclamation  extends Form{

    SpanLabel lb;
    Form saif;
    String[] commandes = {"Modifier", "Details", "Supprimer"};
       ReclamationService myService = new ReclamationService();
        ArrayList<Reclamation> list2 = myService.getAllrecs();
        ArrayList<Reclamation> list = list2;
        ArrayList<TypeRec> types = new ArrayList<>() ;
    public ShowReclamation(Form previous) {
        if ( types.size() > 0 ) {
        } else {
           types = myService.getAllTypes();
        }
        saif=this;
        setTitle("les Reclamations");
        setLayout(BoxLayout.y());
        
        Toolbar.setGlobalToolbar(true);
/*
accueilf.getToolbar().addSearchCommand(e -> {
});*/           
                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MENU, s);
                TextField searchField = new TextField("", "Search..."); 
                searchField.getHintLabel().setUIID("Title");
                searchField.setUIID("Title");
                searchField.getAllStyles().setAlignment(Component.LEFT);
                saif.getToolbar().setTitleComponent(searchField);
                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
                saif.getStyle().setBgColor(ColorUtil.WHITE);
                saif.getStyle().setBgTransparency(255);
 if (list.isEmpty()) {
            SpanLabel lb = new SpanLabel(" aucune reclamation !");
           saif.add(lb);
        } else {

            for (Reclamation r : list) {
                MultiButton b = new MultiButton("");
                /*
                int size = Display.getInstance().convertToPixels(5, true);
                EncodedImage placeholder = EncodedImage.createFromImage(icon, focusScrolling).createFromImage(Image.createImage(50, 200 / 5, 0xffff0000), true);
                URLImage background = URLImage.createToStorage(placeholder, "400px-AGameOfThrones.jpg",
        "http://awoiaf.westeros.org/images/thumb/9/93/AGameOfThrones.jpg/400px-AGameOfThrones.jpg");
                b.setIcon(background);
                */
                /*
                FontImage fi = FontImage.createFixed("" + FontImage.MATERIAL_PERSON, FontImage.getMaterialDesignFont(), 0xff, size, size);
                b.setIcon(fi);*/
                b.getStyle().setBgColor(ColorUtil.WHITE);
                b.getStyle().setBgTransparency(255);
                b.getUnselectedStyle().setBorder(Border.createGrooveBorder(1, ColorUtil.GRAY));
                //String etat = "";
                b.setTextLine2("Désignation \n:" + r.getTitre());
                b.setTextLine3("Etat  " + r.getEtat());
                  saif.add(b);
                  
                  
                  
                   b.addActionListener(e -> {
                       if (r.getEtat()== "ok") {
                           DetailsRec k = new DetailsRec(r);
                           k.show();
                       } else {
                    Dialog d = new Dialog();
                    d.setLayout(BoxLayout.y());
                    d.getContentPane().setScrollableY(true);
                    for (String cmd : commandes) {
                        MultiButton mb = new MultiButton(cmd);
                        d.add(mb);
                        mb.addActionListener(ee -> {

                            switch (cmd) {

                                case "Modifier":
                                UpdateRec  c = null;
                            {
                                try {
                                    c = new UpdateRec(r, types);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                                c.show();
                                    
                                    break;
                                case "Supprimer":   
                                    delete(r.getId(), d);  
                                    break;
                                default:

                            
                                   
                                 DetailsRec k = new DetailsRec(r);
                                   k.show();
                            
                                   
                                    
                                    break;
                            }

                            
                        });
                    }
                    d.showPopupDialog(b);
                   }
                });
                 /* b.addActionListener(e->{
                    
 DetailsC d = new DetailsC(r);
 d.show();
 });        
            }*/
          
 }}
        // **********************************
        searchField.addDataChangeListener((i1, i2) -> { 
                    String t = searchField.getText();
                    if(t.length() < 1) {
                        saif.removeAll();
                        list = list2;
                        refreshForm();
                    } else {
                        t = t.toLowerCase();
                        saif.removeAll();
                        for (Reclamation r : list) {
                            if ( r.getTitre().indexOf(t) > -1 ) {
                           MultiButton b = new MultiButton("");
                            b.getStyle().setBgColor(ColorUtil.WHITE);
                            b.getStyle().setBgTransparency(255);
                            b.getUnselectedStyle().setBorder(Border.createGrooveBorder(1, ColorUtil.GRAY));
                            b.setTextLine2("Désignation \n:" + r.getTitre());
                            b.setTextLine3("Traité  " + r.getEtat());
                            saif.add(b);
                  
                  
                  
                   b.addActionListener(e -> {
                    Dialog d = new Dialog();
                    d.setLayout(BoxLayout.y());
                    d.getContentPane().setScrollableY(true);
                    for (String cmd : commandes) {
                        MultiButton mb = new MultiButton(cmd);
                        d.add(mb);
                        mb.addActionListener(ee -> {

                            switch (cmd) {

                                case "Modifier":
                                    UpdateRec  c = null;
                            // c = new ReclamationUpdate(r.getIdm(), r.getDate(), r.getQuantite(), r.getSociete(), r.getProduit(), products, societies);
                             c.show();
                                    
                                    break;
                                default:

                            
                                   
                                 DetailsRec k = new DetailsRec(r);
                                   k.show();
                            
                                   
                                    
                                    break;
                            }

                            
                        });
                    }
                    d.showPopupDialog(b);

                });
                            }
                        }}
        });
        // **********************************
                

       
        
        //accueilf.add(new InfiniteProgress());
        getToolbar().addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
            try {
                new AddRec(saif, types).show();
            } catch (IOException ex) {
                System.out.println("err");            }
        });
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.show());
   
    }
    public void refreshForm() {
        for (Reclamation r : list) {
                           MultiButton b = new MultiButton("");
                           b.getStyle().setBgColor(ColorUtil.WHITE);
                            b.getStyle().setBgTransparency(255);
                            b.getUnselectedStyle().setBorder(Border.createGrooveBorder(1, ColorUtil.GRAY));
                            b.setTextLine2("Désignation \n:" + r.getTitre());
                            b.setTextLine3("Traité  " + r.getEtat());
                            saif.add(b);
                  
                  
                  
                   b.addActionListener(e -> {
                    Dialog d = new Dialog();
                    d.setLayout(BoxLayout.y());
                    d.getContentPane().setScrollableY(true);
                    for (String cmd : commandes) {
                        MultiButton mb = new MultiButton(cmd);
                        d.add(mb);
                        mb.addActionListener(ee -> {

                            switch (cmd) {

                                case "Modifier":
                                UpdateRec  u = null;
                            {
                                try {
                                    u = new UpdateRec(r, types);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                                u.show();
                                    
                                    break;
                                default:

                            
                                   
                                 DetailsRec k = new DetailsRec(r);
                                   k.show();
                            
                                   
                                    
                                    break;
                            }

                            
                        });
                    }
                    d.showPopupDialog(b);

                });
                            
                        }
    }
    
    public void delete(int id, Dialog d) {
            Log.p("clicked too");
                    ConnectionRequest con = new ConnectionRequest();
                    con.setPost(false);
                    con.setUrl("http://localhost/PI_WebWassalni/web/app_dev.php/Reclamation/delRec/"+id);
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                    System.out.println("done comment!");
                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println("response : " + s);
                    System.out.println("response : " + evt.getMetaData().equals("true"));
                    if ( s.equals("\"success\"")) {
                        reloadForm();
                        Dialog.show("Confirmation", "deleted successfulyy", "Ok", null);
                        d.dispose();
                    }
                    else {
                    Dialog.show("Error", "not deleted", "Not Ok", null);
                    }
                    }
                    });
                    NetworkManager.getInstance().addToQueue(con);
                    
    }
    
     public void reloadForm() {
         list2.clear();
         list2 = myService.getAllrecs();
         saif.removeAll();
         list.clear();
         list = list2;
         if (list.isEmpty()) {
            SpanLabel lb = new SpanLabel(" acune reclamation !");
           saif.add(lb);
        } else {

            for (Reclamation r : list) {
                           MultiButton b = new MultiButton("");
                           b.getStyle().setBgColor(ColorUtil.WHITE);
                            b.getStyle().setBgTransparency(255);
                            b.getUnselectedStyle().setBorder(Border.createGrooveBorder(1, ColorUtil.GRAY));
                            b.setTextLine2("Désignation \n:" + r.getTitre());
                            b.setTextLine3("Traité  " + r.getEtat());
                            saif.add(b);
                  
                  
                  
                   b.addActionListener(e -> {
                    Dialog d = new Dialog();
                    d.setLayout(BoxLayout.y());
                    d.getContentPane().setScrollableY(true);
                    for (String cmd : commandes) {
                        MultiButton mb = new MultiButton(cmd);
                        d.add(mb);
                        mb.addActionListener(ee -> {

                            switch (cmd) {

                                case "Modifier":
                                UpdateRec  u = null;
                            {
                                try {
                                    u = new UpdateRec(r, types);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                                u.show();
                                    
                                    break;
                                default:

                            
                                   
                                 DetailsRec k = new DetailsRec(r);
                                   k.show();
                            
                                   
                                    
                                    break;
                            }

                            
                        });
                    }
                    d.showPopupDialog(b);

                });
                            
                        }
          
 }}    
}
        



