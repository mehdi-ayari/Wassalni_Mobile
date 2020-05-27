/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.voyage;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.reclamation_voyage;
import com.mycompany.myapp.services.voyage.serviceRecVoyage;

/**
 *
 * @author jawha
 */
public class AddRecVoyage extends Form {

    public AddRecVoyage(Form previous) {
        setTitle("Add new Reclamation");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField("","Titre");
        TextField tfCommentaire = new TextField("","Commentaire");
        Button btnValiButton = new Button("Add Reclamation");
        btnValiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfTitre.getText().length()==0||tfCommentaire.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else{
                        reclamation_voyage Rec = new reclamation_voyage(tfTitre.getText(), tfCommentaire.getText());
                        if(new serviceRecVoyage().addTask(Rec))
                            Dialog.show("Success", "connection accepted", new Command("OK"));
                        else
                            Dialog.show("Error", "connection denied", new Command("OK"));

            }}
          
        });
        addAll(tfTitre,tfCommentaire,btnValiButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
        
    }
    
    
    
}
