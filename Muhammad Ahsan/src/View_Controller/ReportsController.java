/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Database.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class ReportsController implements Initializable {

     @FXML
    private TextArea AllReports;
      @FXML
    private RadioButton loc;
       @FXML
    private RadioButton mon;
        @FXML
    private RadioButton con;
     
     private ToggleGroup tg;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     tg=new ToggleGroup();
     con.setToggleGroup(tg);
      mon.setToggleGroup(tg);
       loc.setToggleGroup(tg);
       
         try {
            Statement statement =  DBConnection.getConn().createStatement();
            String query = "SELECT appointment.contact, appointment.description,url,  start, end " +
                    "FROM appointment  " +
                    "GROUP BY appointment.contact, MONTH(start), start";
            ResultSet results = statement.executeQuery(query);
            StringBuilder report1 = new StringBuilder();
            report1.append(String.format("%1$-25s %2$-25s %3$-25s %4$-25s %5$s \n", 
                    "Consultant", "Appointment", "Customer", "Start", "End"));
            report1.append("\n--------------------------------------------------------------------------------------------------------------------\n");
       
            while(results.next()) {
                report1.append(String.format("%1$-25s %2$-25s %3$-25s %4$-25s %5$s \n", 
                    results.getString("contact"), results.getString("description"), results.getString("url"),
                    results.getString("start"), results.getString("end")));
            }
           
            AllReports.setText(report1.toString());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
     
    } 
     @FXML
    void Report1(ActionEvent event) {
           try {
            Statement statement =  DBConnection.getConn().createStatement();
            String query = "SELECT appointment.contact, appointment.description,url,  start, end " +
                    "FROM appointment  " +
                    "GROUP BY MONTH(start),appointment.contact, start";
            ResultSet results = statement.executeQuery(query);
            StringBuilder report1 = new StringBuilder();
            report1.append(String.format("%1$-25s %2$-25s %3$-25s %4$-25s %5$s \n", 
                    "Consultant", "Appointment", "Customer", "Start", "End"));
            report1.append("\n--------------------------------------------------------------------------------------------------------------------\n");
       
            while(results.next()) {
                report1.append(String.format("%1$-25s %2$-25s %3$-25s %4$-25s %5$s \n", 
                    results.getString("contact"), results.getString("description"), results.getString("url"),
                    results.getString("start"), results.getString("end")));
            }
           
            AllReports.setText(report1.toString());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    
}
     @FXML
    void Report2(ActionEvent event) {
         try {
            Statement statement =  DBConnection.getConn().createStatement();
            String query = "SELECT description, MONTHNAME(start) as 'Month', COUNT(*) as 'Total' FROM appointment GROUP BY description, MONTH(start)";
            ResultSet results = statement.executeQuery(query);
            StringBuilder reportOneText = new StringBuilder();
            reportOneText.append(String.format("%1$-55s %2$-55s %3$s \n", "Month", "Appointment Type", "Total"));
             reportOneText.append("\n---------------------------------------------------------------------------------------------------------------\n");
            while(results.next()) {
                reportOneText.append(String.format("%1$-55s %2$-60s %3$d \n", 
                    results.getString("Month"), results.getString("description"), results.getInt("Total")));
            }
            statement.close();
            AllReports.setText(reportOneText.toString());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
         
        
    }
    
    @FXML
    void Report3(ActionEvent event) {
         try {
            Statement statement =  DBConnection.getConn().createStatement();
            String query = "SELECT location, COUNT(*) as 'Total' FROM appointment " +
                " GROUP BY location";
            ResultSet results = statement.executeQuery(query);
            StringBuilder reportThreeText = new StringBuilder();
            reportThreeText.append(String.format("%1$-65s %2$-65s \n", "Location", "Total Appointments"));
            reportThreeText.append("\n------------------------------------------------------------------\n");
            while(results.next()) {
                reportThreeText.append(String.format("%1$s %2$65d \n", 
                    results.getString("location"), results.getInt("Total")));
            }
            statement.close();
            AllReports.setText(reportThreeText.toString());
        } catch (SQLException e) {
            System.out.println("SQLExcpetion: " + e.getMessage());
        }
        
    }
    
    @FXML
    void Back(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm ");
            alert.setHeaderText("Are you sure you want to go back to main screen " );
            alert.showAndWait()
            .filter(response -> response == ButtonType.OK)// by using lambda Expression we have to write fewer lines of code for Alert dialog.
            .ifPresent(response -> {
            
               
            
            try {
                //  Parent root=FXMLLoader.load(getClass().getResource("/View_Controller/AppointmentScreenwgu.fxml"));
                
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View_Controller/AppointmentScreen.fxml"));
                Parent tableViewParent = loader.load();
                
                Scene tableViewScene = new Scene(tableViewParent);
                
                
                
                
                
                
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                window.setScene(tableViewScene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
            }


    }
                        );
    
}
}