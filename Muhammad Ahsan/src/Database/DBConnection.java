/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author mhamza0
 */
public class DBConnection {
    private static Connection DBConn;
    //Server name:  52.206.157.109 
                //Database name:  U04fVQ
                //Username:  U04fVQ
                //Password:  53688229162
		// JDBC driver name and database URL
    
      public static void init(){
        System.out.println("Connecting to the database");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            DBConn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U04fVQ", "U04fVQ", "53688229162");
        }catch (ClassNotFoundException ce){
            System.out.println("Cannot find the right class.  Did you remember to add the mysql library to your Run Configuration?");
            ce.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
}
    }
    
 
    public static Connection getConn(){
    
        return DBConn;
    }
    
  
    public static void closeConn(){
        try{
            DBConn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("Connection closed.");
        }
    }
    
}
