/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;



import Database.Loggerdb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import static java.time.Clock.system;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import muhammad.ahsan.MuhammadAhsan;

/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class LogInScreenController implements Initializable {
    ResourceBundle rb=ResourceBundle.getBundle("Database/login", Locale.getDefault());// default English
    // ResourceBundle rb=ResourceBundle.getBundle("Database/login_fr", Locale.getDefault());// French
    private MuhammadAhsan ah;
   
private final ZoneId zid1 = ZoneId.systemDefault();
    
    
    /**
     * Initializes the controller class.
     */
   public static String loginuser;
      @FXML
    private TextField usernameField;
     
   @FXML
    private PasswordField passwordField;
    @FXML
    private Label SchedulerText;
    @FXML
    private Label UsernameText;
    @FXML
    private Label PasswordText;
    @FXML
      private Label blank;
    @FXML
            private Button signIn;
    @FXML
            private Button cancel;
    
     private final static Logger logger = Logger.getLogger(Loggerdb.class.getName());
    
   Connection connection = null;
    PreparedStatement ps = null;
  
    

     @FXML
    void Cancel(ActionEvent event) {
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText("Are you sure you want to Exit ");
            alert.showAndWait()
            .filter(response -> response == ButtonType.OK) // by using lambda Expression we have to write fewer lines of code for Alert dialog.
            .ifPresent(response -> { 
                     Platform.exit();
              
                }
      
      );

    }
    
    
    
    
  
    
     @FXML
    void signIn(ActionEvent event) throws IOException  {
        
       
      String userName = usernameField.getText();   
    
        if (isValid()){
            
             loginuser=userName;
             ZonedDateTime zid=ZonedDateTime.now();
             System.out.println(zid+ "thisss");
            System.out.println("welcome"+ loginuser);
            logger.log(Level.INFO, userName);
          Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/AppointmentScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
        }
     
          
        
        
    }
    
    /**
     * Initializes LoginScreen
     * @param ah 
     */
   
     public void signInScreen(MuhammadAhsan ah){
         this.ah=ah;
         SchedulerText.setText(rb.getString("title"));
            UsernameText.setText(rb.getString("username"));
            PasswordText.setText(rb.getString("password"));
            signIn.setText(rb.getString("signin"));
            cancel.setText(rb.getString("cancel"));
           // blank.setText(rb.getString("incorrect"));
            
            
            
        }
    boolean isValid(){
         boolean login = false;
        
        Statement statement=null;
        try{
        Connection con=DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U04fVQ", "U04fVQ", "53688229162");
        statement=con.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * FROM user WHERE userName= " + "'" + usernameField.getText() + "'" 
            + "AND password=" + "'" + passwordField.getText() + "'");
        while(rs.next()){
            if (rs.getString("USERNAME") != null && rs.getString("PASSWORD") != null) { 
                     String  username = rs.getString("USERNAME");
                     System.out.println( "USERNAME = " + username );
                     String password = rs.getString("PASSWORD");
                     System.out.println("PASSWORD = " + password);
                     login = true;
                 }  
            }
            rs.close();
        }
       
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        finally{
             String userName = usernameField.getText();  
        String pass = passwordField.getText(); 
             usernameField.clear();
            passwordField.clear();
            blank.setText(rb.getString("incorrect"));
              if(userName.length()==0 || pass.length()==0) { // if empty field
            blank.setText(rb.getString("empty"));
              }
        }
          
            
            return login;
        }
    
    
  



  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
 
    
   

}