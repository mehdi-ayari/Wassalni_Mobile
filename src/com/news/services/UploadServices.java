/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.news.services;

import java.net.MalformedURLException;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Ayadi
 */
public class UploadServices {

   public String upload(String Path) {
        String fileNameInServer = "";

        FileUploader fu = new FileUploader("localhost/myproject/web");
 
        try {
            //Upload
            fileNameInServer = fu.upload(Path);
        } catch (MalformedURLException ex) {

        } catch (java.io.IOException ex) {
        }
        System.out.println(fileNameInServer);

        return fileNameInServer;

    }

    public static void main(String[] args) {
        UploadServices us = new UploadServices();
        System.out.println(us.upload("C:/Users/Kallel/Desktop/load.png"));

    }

}
