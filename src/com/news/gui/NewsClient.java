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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import static com.codename1.ui.TextArea.URL;
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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

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
     
        Container.addAll(Titre, DescrField, List);
        NewsDetail.add(img);
        NewsDetail.add(Container);
               
      /* for (Comment cmts : ServicesComment.getInstance().getAllComment(String.valueOf(c.getId_news()))) {
         Container InfoContainer = new Container(BoxLayout.y());
         
          
            Label username = new Label(cmts.getUserName());
            SpanLabel comment = new SpanLabel(cmts.getText());
        
            InfoContainer.add(username);
            InfoContainer.add(comment);
    
            Container ContainerImage = new Container(BoxLayout.x());

           // placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            String url2 = "http://localhost/myproject/web/images/" + cmts.getUserPhoto();
            ConnectionRequest connection2 = new ConnectionRequest();
            connection2.setUrl(url2);
            URLImage imgurl2 = URLImage.createToStorage(placeHolder, url2, url2);

            ImageViewer img2 = new ImageViewer(imgurl2.scaled(imgurl2.getWidth() * 1, imgurl2.getHeight() * 1));
            ContainerImage.add(img2);
            ContainerImage.add(InfoContainer);
            NewsDetail.add(ContainerImage);
                }

*/
        
        ArrayList<Comment> cmts = ServicesComment.getInstance().getAllComment(String.valueOf(c.getId_news()));
      
        if(cmts.size() !=0) {Label NbrComt = new Label("Comment("+cmts.get(0).getNbrCmt()+")"); add(NbrComt);}
        
        else {Label NbrComt = new Label("Comment(0)"); add(NbrComt);}
       
        
        
         for(int i=0 ; i<cmts.size();i++)
         {
              System.out.println(cmts.get(i));
           EncodedImage enc;
           enc=  EncodedImage.createFromImage(theme.getImage("load.png"), false); 
         
          String urll = "http://localhost/myproject/web/images/" + cmts.get(i).getUserPhoto();
           Image imgg= URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
           ImageViewer   imgv=new ImageViewer(imgg);
           
           cmts.get(i).setIdA(String.valueOf(c.getId_news()));
           
           SpanLabel UserName= new SpanLabel();
           UserName.setText(cmts.get(i).getUserName());
           
           Container c1 = new Container(BoxLayout.x());
           c1.addAll(imgv,UserName);
           
          
           SpanLabel text= new SpanLabel();
           text.setText(cmts.get(i).getText());
           
            Container c2 = new Container(BoxLayout.x());
            SpanLabel dateC= new SpanLabel();
           dateC.setText(cmts.get(i).getDate()); 
           
           c2.addAll(dateC);
           
           Container cn = new Container(BoxLayout.y());
           
           cn.addAll(c1,text,c2);
           
           Label choix = new Label();
           cn.add(choix);
           cn.setLeadComponent(choix);
           NewsDetail.add(cn); // 
           Comment cmt = cmts.get(i);
                 
         }
         
         
                   //  if(User.getId()==)

        
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
                    cm.setId_u(String.valueOf(User.getId()));
                    cm.setIdA(String.valueOf(c.getId_news()));
                    cm.setText(TitreField.getText());
                 

                    if (ServicesComment.getInstance().AddComment(cm)) {
                        Dialog.show("Success", "COMMENT Added", new Command("OK"));
                        new NewsClient(Client.current, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "", new Command("OK"));
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
