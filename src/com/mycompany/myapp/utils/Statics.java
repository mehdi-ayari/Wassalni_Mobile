/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

/**
 *
 * @author jawha
 */
public class Statics {
    public static final String BASE_URL="http://localhost/PI_Webwassalni/web/app_dev.php";
    public static final String BASE_URL2="http://localhost/PI_WebWassalni/web/app_dev.php/";

    
      public static String rstLog ="";
    public static int idSession;
    public static String loggedPlainPW;

    public static String getLoggedPlainPW() {
        return loggedPlainPW;
    }

    public static void setLoggedPlainPW(String loggedPlainPW) {
        Statics.loggedPlainPW = loggedPlainPW;
    }
    
    

    public static int getIdSession() {
        return idSession;
    }

    public static void setIdSession(int idSession) {
        Statics.idSession = idSession;
    }
    
    

    public static String getRstLog() {
        return rstLog;
    }

    public static void setRstLog(String rstLog) {
        Statics.rstLog = rstLog;
    }
  
    
    
}
