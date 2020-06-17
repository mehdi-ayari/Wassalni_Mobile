/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.news.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.charts.NewsPieChart;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.news;
import com.mycompany.myapp.entities.user;
import com.news.services.ServicesNews;
import com.news.services.UploadServices;


/**
 *
 * @author Ayadi
 */
public class NewsEntreprise extends Form {

    static Form currentForm;
    private EncodedImage placeHolder;
    UploadServices us = new UploadServices();
    String FilenameInserver = "";
    user User = Session.getCurrentSession();

    public NewsEntreprise(Form previous, Resources theme) {

        currentForm = this;
        currentForm.setTitle("News OF THE DAY");
        currentForm.setLayout(BoxLayout.y());
              
        for (news c : ServicesNews.getInstance().getAllNews()) {
            Container InfoContainer = new Container(BoxLayout.y());
            Label titre = new Label(c.getTitre());
            Label descr = new Label(c.getDescr());

            Button ListMembers = new Button("CONSULTER");
            InfoContainer.add(titre);
            InfoContainer.add(descr);

            InfoContainer.add(ListMembers);
            Container Container = new Container(BoxLayout.x());

            placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            String url = "http://localhost/myproject/web/images/" + c.getImage();
            ConnectionRequest connection = new ConnectionRequest();
            connection.setUrl(url);
            URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

            ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));
            Container.add(img);
            Container.add(InfoContainer);
            currentForm.add(Container);

            img.addPointerReleasedListener(ev -> {
                NewsDetail(c, theme).show();
            });

        }
        currentForm.getToolbar().addCommandToOverflowMenu("Add News", null, ev -> {
            AddNews(theme).show();

        });
        currentForm.getToolbar().addCommandToOverflowMenu("Stat News", null, ev -> {
            StatNews(theme).show();
        });

        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

    }

    public Form AddNews(Resources theme) {

        Form AddNews = new Form("ADD", BoxLayout.y());

        Label Titre = new Label("Titre");
        Label Descr = new Label("DESCRIPTION");

        Button testImage = new Button("Browse Images");

        TextField TitreField = new TextField(null, "Titre");
        TextField DescrField = new TextField(null, "DESCRIPTION");

        TextField ImageField = new TextField(null, "Image");

        Button Save = new Button("Save");

        Save.addActionListener(ev -> {

            if ((TitreField.getText().length() == 0) || (DescrField.getText().length() == 0) || FilenameInserver.equals("")) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    news c = new news();

                    c.setTitre(TitreField.getText());
                    c.setDescr(DescrField.getText());
                    c.setImage(FilenameInserver);

                    if (ServicesNews.getInstance().AddNews(c)) {
                        Dialog.show("Success", "NEWS Added", new Command("OK"));
                        new NewsEntreprise(Entreprise.current, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "", new Command("OK"));
                }

            }

        });

        testImage.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {

                public void actionPerformed(final ActionEvent evt) {
                    //if a user cancels the gallery the evt will be null
                    if (evt == null) {
                        ToastBar.Status s = ToastBar.getInstance().createStatus();
                        s.setMessage(" Cancelled Gallery");
                        s.setMessageUIID("Title");
                        Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                        s.setIcon(i);
                        s.setExpires(2000);
                        s.show();
                        return;
                    }
                    String file = (String) evt.getSource();
                    System.out.println("pathhhh:" + file);
                    String path = file.substring(7);
                    System.out.println(path);

                    FilenameInserver = us.upload(path);

                }
            }, Display.GALLERY_IMAGE);

        });
        AddNews.addAll(Titre, TitreField, testImage, Descr, DescrField, Save);

        AddNews.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new NewsEntreprise(Entreprise.current, theme).show();
        });

        return AddNews;
    }

    public Form NewsDetail(news c, Resources theme) {

        Form NewsDetail = new Form(c.getTitre(), BoxLayout.y());

        placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/myproject/web/images/" + c.getImage();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

       TextField Titre = new TextField(null, "Titre");
       TextField DescrField = new TextField(null, "Description");


        Titre.setText(c.getTitre());

        DescrField.setSingleLineTextArea(false);
        DescrField.setRows(4);
        DescrField.setColumns(20);
        DescrField.setText(c.getDescr());

        Container Container = new Container(new FlowLayout(CENTER));
        Container.addAll(Titre, DescrField);
        NewsDetail.add(img);
        NewsDetail.add(Container);
        
        Container ButtonsContainer = new Container(new FlowLayout(CENTER));

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        ButtonsContainer.addAll(Edit, Delete);

        NewsDetail.add(ButtonsContainer);
        NewsDetail.revalidate();
        Delete.addActionListener(ev -> {
            
            String result = ServicesNews.getInstance().DeleteNews(c);
            if (!result.equals("Error")) {
                Dialog.show("Success", result, new Command("OK"));
                new NewsEntreprise(Entreprise.current, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
              
        });

        Edit.addActionListener(ev -> {
           
                c.setDescr(DescrField.getText());
                c.setTitre(Titre.getText());
                
                if (ServicesNews.getInstance().EditNews(c)) {
                    Dialog.show("Success", "NEWS Edited", new Command("OK"));
                    NewsDetail.revalidate();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            
        });

            NewsDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new NewsEntreprise(Entreprise.current, theme).show();
        });

        return NewsDetail;
    }

    public Form AddNews() {

        Form AddNews = new Form("ADD", new FlowLayout(Component.CENTER, Component.CENTER));
        AddNews.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            currentForm.showBack();
        });

        return AddNews;
    }

    public Form StatNews(Resources theme) {

        NewsPieChart a = new NewsPieChart();
        Form stats_Form = a.execute();

        stats_Form.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new NewsEntreprise(Entreprise.current, theme).show();
        });

        return stats_Form;
    }

}
