/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author mhamza0
 */
public class User {
     private static Connection connDB;
    private String username;
    private String password;
    private int userID;

    public User(int userID, String username, String password) {
       // this.userID = userID;
        this.username = username;
        this.password = password;
        this.userID=userID;
    }
    
       public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    
     public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername(){
        return username;
}
  
    }  

   
