/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.voyage;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
public class modifierRec extends Form{
            public modifierRec(Form previous, reclamation_voyage ent) {
    
setTitle("Modifier Reclamation d'un voyage");
        setLayout(BoxLayout.y());
        
        TextField titre= new TextField("", "titre");
        titre.setText(ent.getTitre());
        int id = ent.getId_reclamation_voyage();
            System.err.println(id);
        TextField comment= new TextField("", "commentaire");
        comment.setText(ent.getCommentaire());
        Button btnValider = new Button("modifier reclamation");
        
        btnValider.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent evt) {
          if ((titre.getText().length()==0)||(comment.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                       reclamation_voyage a = new reclamation_voyage(titre.getText(), comment.getText());
                        if( serviceRecVoyage.getInstance().modifierReclamationV(id, a))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
    }
        });
        
        addAll(titre,comment,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
            }    
    
}
