/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.news.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.user;


/**
 *
 * @author Ayadi
 */
public class Client extends Form {

    static Form current;
    user User=Session.getCurrentSession();
    public Client(Form previous, Resources theme) {
        current = this; //Récupérsation de l'interface(Form) en cours

        setTitle("Client");
        setLayout(BoxLayout.y());

        getToolbar().addCommandToSideMenu("Home", null, ev -> {

        }
        );
        getToolbar().addCommandToSideMenu("News", null, ev -> {
            new NewsClient(current, theme).showBack();

        });

        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, ev -> {
            try {
                Session.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
            previous.showBack();
        });

    }
}
