/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Database.DBConnection;
import Model.Appointment;
import Model.Customer;
import static View_Controller.LogInScreenController.loginuser;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class AppointmentNewController implements Initializable {
        private final ZoneId zid1 = ZoneId.systemDefault();

    @FXML
    private TableView<Customer> Customers;

    @FXML
    private TableColumn<Customer, String> cus;
    
    @FXML
    private TextField Title;
    
    @FXML
    private TextField Location;
   


    @FXML
    private TextField Name;

    @FXML
    private ComboBox <String> Type;

    @FXML
    public DatePicker Date;

    @FXML
    private ComboBox <String> Start;

    @FXML
    private ComboBox <String> End;
     @FXML
    private RadioButton Monthly;

    @FXML
    private ComboBox <String> Contact;
    
    private final DateTimeFormatter timeformat = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
    
    ObservableList <String> type=FXCollections.observableArrayList("Consultation","Savings", "Offers", "Payments");
    
    ObservableList <String> start=FXCollections.observableArrayList("9:00 AM","9:30 AM", "10:00 AM", "10:30 AM",
             "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM",
             "4:00 PM", "4:30 PM");
    
      ObservableList <String> end=FXCollections.observableArrayList("9:30 AM","10:00 AM", "10:30 AM", "11:00 AM",
             "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM",
             "4:30 PM", "5:00 PM");

    ObservableList<String> contact = FXCollections.observableArrayList("test", "wgu", "ahsan", "admin");
    
    ObservableList<Customer> customerList1=FXCollections.observableArrayList();
    ObservableList<Customer> sel=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Name.setEditable(false);
        Date.setValue(LocalDate.now());
        Contact.setItems(contact);
        Start.setItems(start);
        Type.setItems(type);
        End.setItems(end);
          try {
           
       
        ResultSet rs = null;
          
         
        
        Connection con=DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U04fVQ", "U04fVQ", "53688229162");
       
        
          
          
            

            Statement pst;

                   String sql=  "SELECT customerName "
					+ "FROM customer"
					;
                   pst=con.prepareStatement(sql);
                   rs=pst.executeQuery(sql);

           
                 
               
                while (rs.next()) {
                   
                     String name1=rs.getString("customerName");
                    
                    
                customerList1.add(new Customer( name1));
                Customers.setItems(customerList1);
                 
                
                 
                   System.out.println(Customers);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

         
  
  
       cus.setCellValueFactory(new PropertyValueFactory("cus"));
       
   
       
    }
    @FXML
    void selec(MouseEvent event) {
  Customer cu=Customers.getSelectionModel().getSelectedItem();
     
     Name.setText(cu.getCus());
    }
 @FXML
    void ContactAction(ActionEvent event)  { 
        Location.setEditable(false);
        
        if (Contact.getSelectionModel().getSelectedItem().equals("test")
        )
    {
        Location.setText("Washington DC");
    }
        
         if (Contact.getSelectionModel().getSelectedItem().equals("wgu")
        )
    {
        Location.setText("Los Angles");
    }
            if (Contact.getSelectionModel().getSelectedItem().equals("ahsan")
        )
    {
        Location.setText("Toronto");
    }
           if (Contact.getSelectionModel().getSelectedItem().equals("admin")
        )
    {
        Location.setText("London");
    }
         
    
}
     @FXML
    void AppointmentCancel(ActionEvent event) throws IOException  { 
        Parent root=FXMLLoader.load(getClass().getResource("/View_Controller/AppointmentScreen.fxml"));
        Scene scene=new Scene(root);
        Stage parent=(Stage)((Node) event.getSource()).getScene().getWindow();
        parent.setScene(scene);
        parent.show();
        
    }
    

    
    
    @FXML
    void Save(ActionEvent event) throws IOException  { 
        if (isvalid()){
   
        if (time()){
        int customerId=-1;
          String title=Title.getText();
        String name=Name.getText();
        String type=Type.getSelectionModel().getSelectedItem();
       
        String contact=Contact.getSelectionModel().getSelectedItem();
        String location=Location.getText();
      
        String end=End.getSelectionModel().getSelectedItem();
           String start=Start.getSelectionModel().getSelectedItem();
           
         LocalDate ld=Date.getValue();
        
      
        LocalTime lt=LocalTime.parse(Start.getSelectionModel().getSelectedItem(), timeformat);
        LocalDateTime ldts=LocalDateTime.of(ld, lt);
        
         LocalTime ltg=LocalTime.parse(End.getSelectionModel().getSelectedItem(), timeformat);
        LocalDateTime ldtn=LocalDateTime.of(ld, ltg);
        
        
          ZoneId zid;
         if(location.equals("Washington DC")|| (location.equals("Toronto"))) {
            zid = ZoneId.of("America/New_York");
        } else if(location.equals("Los Angles")) {
            zid = ZoneId.of("America/Phoenix");
        } else {
            zid = ZoneId.of("Europe/London");
        }
         ZonedDateTime zdt = ldts.atZone(zid);
        ZonedDateTime utcDate = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        ldts = utcDate.toLocalDateTime();
        Timestamp ts = Timestamp.valueOf(ldts); 
        System.out.println("start time" + ts);
        
        
        ZonedDateTime zdts = ldtn.atZone(zid);
        ZonedDateTime utcDates = zdts.withZoneSameInstant(ZoneId.of("UTC"));
        ldtn = utcDates.toLocalDateTime();
        Timestamp tsc = Timestamp.valueOf(ldtn); 
        System.out.println("end time" + tsc);
        
        
        try {
          
           PreparedStatement psn = DBConnection.getConn().prepareStatement("INSERT INTO appointment "
                   + "(customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" 
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)");
           
            PreparedStatement psd = DBConnection.getConn().prepareStatement("SELECT customerID FROM customer "
					+ "WHERE customerName = ? "
					);
            psd.setString(1, name);
            ResultSet rs=psd.executeQuery();
            if (rs.next()){
                customerId=rs.getInt("customerId");
            }
          
           
         
         PreparedStatement pst = DBConnection.getConn().prepareStatement(
        "SELECT * FROM appointment WHERE start = ? AND location =? AND customerId=?"
	);
                   
                   pst.setTimestamp(1, ts);
                   pst.setString(2, location);
                   pst.setInt(3, customerId);
                    ResultSet ra=pst.executeQuery();
                    if(ra.next()) {
                        System.out.println("failed");
                        
                         
        
          Alert alert1=new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Appointment overlapped");
            alert1.setHeaderText("Not available");
            alert1.setContentText("please select different time");
            alert1.showAndWait();
           
	}
                    else{
                         psn.setInt(1, customerId );
           psn.setString(2, title);
           psn.setString(3, type);
           psn.setString(4, location);
           psn.setString(5, contact);
           psn.setString(6, name);
           psn.setTimestamp(7, ts);
           
           
           System.out.println("time to be inserted"+ ts);
           psn.setTimestamp(8, tsc);
           psn.setString(9, loginuser);
           psn.setString(10, loginuser);
                   int re=psn.executeUpdate();
                   
                     Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/AppointmentScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
                   
                    }
         
         
     } catch (SQLException ex) {
           Logger.getLogger(AppointmentNewController.class.getName()).log(Level.SEVERE, null, ex);
       }
         
        }   
        }
    }
    
   
        boolean isvalid(){
              
          
           
           String error="";
           
           
        try{
              String contact1=Contact.getValue();
        
      
            String name=Name.getText();
            
         
            
            
               String title=Title.getText();
        
        String type=Type.getValue();
              
              String star=Start.getValue();
               String en=End.getValue();
               
               
                if (star==null || name.length()==0){
                error+="Please select start time. \n";
            }
                 if (en==null || en.length()==0){
                error+="Please select end time. \n";
            }
           if (name==null || name.length()==0){
                error+="Please select customerName. \n";
            }
            if (title==null || title.length()==0){
                error+="Please enter an Appointment title. \n";
            }
            if (type==null  || type.length()==0){
                error+= "please select an Appointment type. \n";
            }
          if(contact1==null){
                error+="please select a contact. \n";
            }
        
          
         
        }catch (Exception e){
          error=error+"All fields must be completed ";
        }
         
         if (error.length() == 0) {
            return true;
        } else {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
           
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields ");
            alert.setContentText(error);

            alert.showAndWait();

            return false;
        }
    }
        
        
        
        
        
        boolean time(){
        
         String error="";
         try{
                 
        
        LocalDate localDate = Date.getValue();
            
               String en=End.getValue();
               String start1=Start.getValue();
	 LocalTime startTime=LocalTime.parse(start1, timeformat);
	LocalTime endTime = LocalTime.parse(en, timeformat);
        
        LocalDateTime startDT = LocalDateTime.of(localDate, startTime);
        LocalDateTime endDT = LocalDateTime.of(localDate, endTime);

        ZonedDateTime startUTC = startDT.atZone(zid1).withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime endUTC = endDT.atZone(zid1).withZoneSameInstant(ZoneId.of("UTC")); 
         if (endUTC.equals(startUTC)|| endUTC.isBefore(startUTC)){
            error+="End time must be after Start time. \n";
                
               }
            
        }
        
         catch (Exception e) {
            error=error+"plz select valid start and end time  ";
            
        }
         
            
             if (error.length() == 0) {
            return true;
        } else {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
           
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields ");
            alert.setContentText(error);

            alert.showAndWait();

            return false;
        }
            
               
              
        }
        
        
       
}

