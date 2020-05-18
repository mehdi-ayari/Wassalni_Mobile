/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reservation;

import com.mycompany.myapp.gui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Mahdi
 */
public class HomeForm extends Form {
    Form current;
    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddReservation = new Button("Add Reservation");
        Button btnListReservation = new Button("List Reservations");
        Button btnAddBusiness = new Button("Add Reservation Entreprise");
        Button btnListBusiness = new Button("List Reservation Entreprise");
        
        
        btnAddReservation.addActionListener(e-> new AddReservationForm(current).show());
        btnListReservation.addActionListener(e-> new ListReservationForm(current).show());
        btnAddBusiness.addActionListener(e-> new AddBusinessForm(current).show());
        btnListBusiness.addActionListener(e-> new ListBusinessForm(current).show());
       
        addAll(btnAddReservation,btnListReservation,btnAddBusiness,btnListBusiness);
        
        
    }
    
    
}
