/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.news.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.news;
import com.mycompany.myapp.entities.user;
import com.news.services.ServicesNews;
import com.news.services.ServicesComment;
import com.mycompany.myapp.entities.Comment;
import java.util.ArrayList;

/**
 *
 * @author Ayadi
 */
public class NewsClient extends Form {
         static Form currentForm;
    private EncodedImage placeHolder;
    String FilenameInserver = "";
    user User = Session.getCurrentSession();
   

  
    public NewsClient(Form previous, Resources theme) {

        currentForm = this;
        currentForm.setTitle("News");
        currentForm.setLayout(BoxLayout.y());

        for (news c : ServicesNews.getInstance().getAllNews()) {
            Container InfoContainer = new Container(BoxLayout.y());
            Label titre = new Label(c.getTitre());
            Label descr = new Label(c.getDescr());
        
             Button ListMembers=new Button("CONSULTER");
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

       
            currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

    
    }
     public Form NewsDetail(news c, Resources theme) {

        Form NewsDetail = new Form(c.getTitre(), BoxLayout.y());

        placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/myproject/web/images/" + c.getImage();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

        Label Titre = new Label();
        Label List = new Label("See Comments on this NEWS");
        TextField DescrField = new TextField(null, "Description");


        Titre.setText(c.getTitre());

        DescrField.setSingleLineTextArea(false);
        DescrField.setRows(4);
        DescrField.setColumns(20);
        DescrField.setText(c.getDescr());

        Container Container = new Container(new FlowLayout(CENTER));
        //
         ArrayList<Comment> cmts = Services.ServiceComment.getInstance().getAllCmts(News.getId());
      
        if(cmts.size() !=0) {Label NbrComt = new Label("Comments("+cmts.get(0).getNbrCmt()+")"); add(NbrComt);}
        
        else {Label NbrComt = new Label("Comments(0)"); add(NbrComt);}
       
        
        
         for(int i=0 ; i<cmts.size();i++)
         {
           URL=Statics.IMG_URL+cmts.get(i).getUserPhoto();
           Image   img= URLImage.createToStorage(enc, URL, URL, URLImage.RESIZE_SCALE);
           ImageViewer   imgv=new ImageViewer(img);
           
           cmts.get(i).setIdA(News.getId());
           
           SpanLabel UserName= new SpanLabel();
           UserName.setText(cmts.get(i).getUserName());
           
           Container c1 = new Container(BoxLayout.x());
           c1.addAll(imgv,UserName);
           
          
           SpanLabel text= new SpanLabel();
           text.setText(cmts.get(i).getText());
           
            Container c2 = new Container(BoxLayout.x());
            SpanLabel dateC= new SpanLabel();
           dateC.setText(cmts.get(i).getDate()); 
           SpanLabel Likes= new SpanLabel();
           Likes.setText("Likes: "+cmts.get(i).getPoint()); 
           SpanLabel NbrReply= new SpanLabel();
           NbrReply.setText("Replies("+cmts.get(i).getNbrReply()+")");
            c2.addAll(Likes,dateC,NbrReply);
           
           Container c = new Container(BoxLayout.y());
           
           c.addAll(c1,text,c2);
           
           Label choix = new Label();
           c.add(choix);
           c.setLeadComponent(choix);
           add(c);
           Comment cmt = cmts.get(i);
         choix.addPointerReleasedListener(e-> new ListReplies( current,cmt, user).show());
         
         }     
      
         //
        Container.addAll(Titre, DescrField, List);
        NewsDetail.add(img);
        NewsDetail.add(Container);
        
        Container ButtonsContainer = new Container(new FlowLayout(CENTER));

       TextField TitreField = new TextField(null, "COMMENT");
        Button Comment = new Button("Comment");
        ButtonsContainer.addAll(TitreField,Comment);

        NewsDetail.add(ButtonsContainer);
        NewsDetail.revalidate();
        Comment.addActionListener(ev -> {
             if ((TitreField.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Comment cm = new Comment();

                    cm.setText(TitreField.getText());
                 

                    if (ServicesComment.getInstance().AddComment(cm)) {
                        Dialog.show("Success", "COMMENT Added", new Command("OK"));
                        new NewsEntreprise(Entreprise.current, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "phone must be a number", new Command("OK"));
                }

            }

        });
    
       
       NewsDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new NewsClient(Client.current, theme).show();
        });


     
        return NewsDetail;
    }
          
    
    public Form AddNews()
    {    
        
        
        Form AddNews = new Form("ADD", new FlowLayout(Component.CENTER, Component.CENTER));
        AddNews.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
             currentForm.showBack();
        });

        return AddNews;
    }
}
