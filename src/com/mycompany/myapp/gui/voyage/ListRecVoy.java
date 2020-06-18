/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.voyage;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.reclamation_voyage;
import com.mycompany.myapp.entities.voyage;
import com.mycompany.myapp.services.voyage.serviceRecVoyage;
import com.mycompany.myapp.services.voyage.serviceVoyage;
import java.util.ArrayList;

/**
 *
 * @author jawha
 */
public class ListRecVoy extends Form{
    Form current;

    public ListRecVoy(Form previous) {
        current=this;
        setTitle("List Reclamation voyage");
                setLayout(BoxLayout.y());

        SpanLabel sp = new SpanLabel();
        ArrayList<reclamation_voyage> ReclVoyages = serviceRecVoyage.getInstance().getAllRecVoy();
        for(int i = 0 ; i < ReclVoyages.size();i++){
            reclamation_voyage RecVoyage = ReclVoyages.get(i);
            Label id = new Label();
            Label idVoy = new Label();
            Label Titre = new Label();
            Label Commentaire = new Label();
            id.setText("id reclamation voyage = "+RecVoyage.getId_reclamation_voyage()+"\n");
            idVoy.setText("id Voyage = "+RecVoyage.getId_voy()+"\n");
            Titre.setText("Titre = "+RecVoyage.getTitre()+"\n");
            Commentaire.setText("Commentaire = "+RecVoyage.getCommentaire()+"\n");
            Button btnEdit = new Button("edit");
            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new modifierRec(current, RecVoyage).show();
                }
            });
            Button btnDel = new Button("Delete");
            btnDel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    try {
                        
                        if( serviceRecVoyage.getInstance().suppReclamationV(RecVoyage.getId_reclamation_voyage()))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
                                        
            });
              Label tre = new Label("________________________________");
        addAll(id,idVoy,Titre,Commentaire,btnEdit,btnDel,tre);
    }
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());

}
    
    
}
