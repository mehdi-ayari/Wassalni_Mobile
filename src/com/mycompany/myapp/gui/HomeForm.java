/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author jawha
 */
public class HomeForm extends Form{
    
Form current;
    public HomeForm() 
    {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnListVoyage = new Button("List Voyage");
        btnListVoyage.addActionListener(e-> new ListVoyagesForm(current).show());
        addAll(btnListVoyage);


    }
    
    
    
    
    
}
