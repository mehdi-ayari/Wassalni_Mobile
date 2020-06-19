/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.reclamation;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author Lenovo
 */
public class Accueil extends Form{
    Form accueill;

    public Accueil(Resources res) {
        accueill=this;
        setTitle("Page d'Accueil");
        setLayout(BoxLayout.y());
        Button reclamation = new Button("Gestion Reclamation ");


        
        reclamation.addActionListener(e->new ShowReclamation(accueill).show());
        
        addAll(reclamation);

             
        Button btnClose = new Button("Quiter");
        btnClose.setIcon(
            FontImage.createMaterial(
                FontImage.MATERIAL_EXIT_TO_APP,
                UIManager.getInstance().getComponentStyle("Button")
            )            
        );
        btnClose.addActionListener
                ((ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    
                    Display.getInstance().exitApplication();
                    
        });

  accueill.add(btnClose);
       
        
        
    
    
}

    public Accueil() {
    }

    Accueil(Form saif) {
        accueill=this;
        setTitle("Accueil");
        setLayout(BoxLayout.y());
        Button reclamation = new Button("Gestion Reclamation ");


        
        reclamation.addActionListener(e->new ShowReclamation(accueill).show());
        
        addAll(reclamation);

             
        Button btnClose = new Button("Quiter");
        btnClose.setIcon(
            FontImage.createMaterial(
                FontImage.MATERIAL_EXIT_TO_APP,
                UIManager.getInstance().getComponentStyle("Button")
            )            
        );
        btnClose.addActionListener
                ((ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    
                    Display.getInstance().exitApplication();
                    
        });

  accueill.add(btnClose);
    }
}

