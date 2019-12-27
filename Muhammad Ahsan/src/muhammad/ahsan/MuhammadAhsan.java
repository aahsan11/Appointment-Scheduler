/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muhammad.ahsan;

import Database.DBConnection;
import Database.Loggerdb;
import View_Controller.LogInScreenController;

import java.io.IOException;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 *
 * @author mhamza0
 */
public class MuhammadAhsan extends Application {
     private BorderPane menu;
    private static Connection connection;
     Locale locale = Locale.getDefault();
      private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {

         
          FXMLLoader loader = new FXMLLoader();
        
            
            
              loader.setLocation(getClass().getResource("/View_Controller/LogInScreen.fxml"));
        Parent loginScreen = loader.load();
        
        
        
            
    
             
            LogInScreenController controller=loader.getController();
              controller.signInScreen(this);
        
        
       Scene scene = new Scene(loginScreen);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            

      
    }

     
    public static void main(String[] args) {
        
       // Locale.setDefault(new Locale("fr", "FR"));
        //System.out.println(Locale.getDefault());
        
		//ResourceBundle rb = ResourceBundle.getBundle("login");
     DBConnection.init();
        connection = DBConnection.getConn();
        Loggerdb.init();
        launch(args);
        DBConnection.closeConn();
      
    }
    
}
