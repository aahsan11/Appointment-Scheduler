/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Database.DBConnection;
import Model.Appointment;
import Model.CustomerInf;

import static View_Controller.LogInScreenController.loginuser;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class AppointmentScreenController implements Initializable {
      ZoneId zid=ZoneId.systemDefault();
    private Stage primaryStage;
     @FXML
    private Parent root;    
    Stage stage;
       @FXML
       
      
    private MenuBar mymenuBar;
     @FXML
    private RadioButton View;
     @FXML
    private RadioButton Week;
     @FXML
    private RadioButton Month;
      
       
     @FXML
    private TableView<Appointment> AppointmentTable;
      
    @FXML
    private TableColumn<Appointment, String> Customer;
    
    
   @FXML
    private TableColumn<Appointment, String> ID;

    @FXML
    private TableColumn<Appointment, String> Contact;
    

      
     @FXML
    private TableColumn<Appointment, String> Locatio;

     @FXML
    private TableColumn<Appointment, String> Title;
     
       @FXML
    private TableColumn<Appointment, String> Type;
       
        @FXML
    private TableColumn<Appointment, String> Start;
          @FXML
    private TableColumn<Appointment, String> End;
           private ToggleGroup radiobutton;
        
        
  private final ZoneId newzid1 = ZoneId.systemDefault();
      
    ObservableList<Appointment> customerList1 = FXCollections.observableArrayList();
      ObservableList<Appointment> customerList1a = FXCollections.observableArrayList();
      ObservableList<Appointment> appointmentEdit=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
  @FXML  
    void ViewAllCustomers() throws IOException {
 
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerView.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        
        Stage stage1 = (Stage) mymenuBar.getScene().getWindow();
       stage1.setScene(home_page_scene);
        stage1.show();

      }
     @FXML  
    void Report() throws IOException {
 
      Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/Reports.fxml"));
        Scene scene = new Scene(root);
       
        Stage stage1 = (Stage) mymenuBar.getScene().getWindow();
       stage1.setScene(scene);
        stage1.show();

      }
    @FXML
    void MonthlyAppointments(ActionEvent event){
        
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        
 
       
        LocalDate begin = LocalDate.now();
        LocalDate end = LocalDate.now().plusMonths(1);
      
              try{
          Statement statement =DBConnection.getConn().createStatement();
            String query = "SELECT * FROM appointment WHERE  " + 
                "start >= '" + begin + "' AND start <= '" + end + "'"; 
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                String ti=rs.getString("title");
                String ur=rs.getString("url");
                Timestamp tsStart = rs.getTimestamp("start");
             
             Timestamp tsEnd = rs.getTimestamp("end");
             String co=rs.getString("contact");
             String de=rs.getString("description");
             String lo=rs.getString("location");
             String id=rs.getString("appointmentId");
             
                
                System.out.println("title" +ti +"name"+ ur + "start" + tsStart);
                
                
             
                ZonedDateTime newzdtStart = tsStart.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime newLocalStart = newzdtStart.withZoneSameInstant(ZoneOffset.systemDefault());
                LocalDateTime ld=newLocalStart.toLocalDateTime();
                
                System.out.println("From db in local time1: " + newLocalStart);
                
                
                 ZonedDateTime newzdtEnd= tsEnd.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime newLocalEnd= newzdtEnd.withZoneSameInstant(ZoneOffset.systemDefault());
                LocalDateTime la=newLocalEnd.toLocalDateTime();
                System.out.print(id);
                
                appointments.add(new Appointment(id, ur, co, lo, ti, de,ld.toString(),la.toString()));
                
            }
  
                   
              
              }  catch (SQLException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }    
                
                
          AppointmentTable.setItems(appointments);
       
            
            ID.setCellValueFactory(cellData -> {
            return cellData.getValue().idProperty();
        });
                    Customer.setCellValueFactory(new PropertyValueFactory("CustomerName"));
          Contact.setCellValueFactory(new PropertyValueFactory("Contact"));
          Locatio.setCellValueFactory(new PropertyValueFactory("Location"));
           Title.setCellValueFactory(new PropertyValueFactory("Title"));
            Type.setCellValueFactory(new PropertyValueFactory("Type"));
            Start.setCellValueFactory(new PropertyValueFactory("Start"));
               End.setCellValueFactory(new PropertyValueFactory("End"));      
              
            
        }
    
    
 
    
    @FXML
    void Weekly(ActionEvent event){
        
        ObservableList<Appointment> appointments1 = FXCollections.observableArrayList();
        
 
       
        LocalDate begin = LocalDate.now();
        LocalDate end = LocalDate.now().plusWeeks(1);
      
              try{
          Statement statement =DBConnection.getConn().createStatement();
            String query = "SELECT * FROM appointment WHERE  " + 
                "start >= '" + begin + "' AND end <= '" + end + "'"; 
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                String ti=rs.getString("title");
                String ur=rs.getString("url");
                Timestamp tsStart = rs.getTimestamp("start");
             
             Timestamp tsEnd = rs.getTimestamp("end");
             String co=rs.getString("contact");
             String de=rs.getString("description");
             String lo=rs.getString("location");
             String id=rs.getString("appointmentId");
             
                
                System.out.println(ti + ur  + tsStart);
                
                
             
                ZonedDateTime newzdtStart = tsStart.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime newLocalStart = newzdtStart.withZoneSameInstant(ZoneOffset.systemDefault());
                LocalDateTime ld=newLocalStart.toLocalDateTime();
                
                System.out.println("From db in local time: " + newLocalStart);
                
                
                 ZonedDateTime newzdtEnd= tsEnd.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime newLocalEnd= newzdtEnd.withZoneSameInstant(ZoneOffset.systemDefault());
                LocalDateTime la=newLocalEnd.toLocalDateTime();
                
                appointments1.add(new Appointment(id, ur, co, lo, ti, de,ld.toString(),la.toString()));
                
            }
  
                   
              
              }  catch (SQLException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }    
                
                
          AppointmentTable.setItems(appointments1);
          
            
            ID.setCellValueFactory(cellData -> {
            return cellData.getValue().idProperty();
        });
            
                    Customer.setCellValueFactory(new PropertyValueFactory("CustomerName"));
          Contact.setCellValueFactory(new PropertyValueFactory("Contact"));
          Locatio.setCellValueFactory(new PropertyValueFactory("Location"));
           Title.setCellValueFactory(new PropertyValueFactory("Title"));
            Type.setCellValueFactory(new PropertyValueFactory("Type"));
            Start.setCellValueFactory(new PropertyValueFactory("Start"));
               End.setCellValueFactory(new PropertyValueFactory("End"));      
              
            
            
        
          
        
    }
    
    
    
    
 @FXML
    void ViewAll(ActionEvent event){
            ObservableList<Appointment> customerList11 = FXCollections.observableArrayList();
     try{            
            
        PreparedStatement statement = DBConnection.getConn().prepareStatement(
        "SELECT appointmentId, title, description, "
                + "start, end, location , url, contact  "
                + "FROM appointment "
               );
            ResultSet rs = statement.executeQuery();
           
            
            while (rs.next()) {
                
             Timestamp tsStart = rs.getTimestamp("start");
             
             Timestamp tsEnd = rs.getTimestamp("end");
             
         
            
             
                ZonedDateTime newzdtStart = tsStart.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime newLocalStart = newzdtStart.withZoneSameInstant(newzid1);
                LocalDateTime ld=newLocalStart.toLocalDateTime();
                
               // System.out.println("From db to local time: " + newLocalStart);
                
                
                 ZonedDateTime newzdtEnd= tsEnd.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime newLocalEnd= newzdtEnd.withZoneSameInstant(ZoneOffset.systemDefault());
                LocalDateTime la=newLocalEnd.toLocalDateTime();
               //  System.out.println("From db to local time : " + newLocalEnd);
                
                  String id = rs.getString("appointmentId");
                String title = rs.getString("title");
                String contac = rs.getString("contact");
                String type = rs.getString("description");
                 String loca = rs.getString("location");
                 //String st=rs.getString("start");
                  String en=rs.getString("end");
                 String url1 = rs.getString("url");
                 System.out.println(title + "||");
                 System.out.println(contac + "||");
                 System.out.println(type + "||");
             
                  customerList11.add(new Appointment(id, url1, contac, loca, title, type,ld.toString(),la.toString()));
                 
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
         
                 
                   AppointmentTable.setItems(customerList11);
                    
                    Customer.setCellValueFactory(new PropertyValueFactory("CustomerName"));
                  
                          
            ID.setCellValueFactory(cellData -> {
            return cellData.getValue().idProperty();
        });
          Contact.setCellValueFactory(new PropertyValueFactory("Contact"));
          Locatio.setCellValueFactory(new PropertyValueFactory("Location"));
           Title.setCellValueFactory(new PropertyValueFactory("Title"));
            Type.setCellValueFactory(new PropertyValueFactory("Type"));
            Start.setCellValueFactory(new PropertyValueFactory("Start"));
               End.setCellValueFactory(new PropertyValueFactory("End"));
        
    
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        radiobutton= new ToggleGroup();
         View.setToggleGroup(radiobutton);
         Month.setToggleGroup(radiobutton);
         Week.setToggleGroup(radiobutton);
              
        
        
         // appointments in next 15 mintues
         
        LocalDateTime ldts = LocalDateTime.now(); //c
        System.out.println("this"+ ldts);
        ZoneId zid1 = ZoneId.systemDefault();
        ZonedDateTime zdt1 = ldts.atZone(zid1);//c
        ZonedDateTime utcDate = zdt1.withZoneSameInstant(ZoneId.of("Asia/Dubai"));
        ldts=utcDate.toLocalDateTime();
          Timestamp ts = Timestamp.valueOf(ldts); 
           
          
        LocalDateTime ldt21 = LocalDateTime.now().plusMinutes(15); //cc
        ZoneId zid2=ZoneId.systemDefault();
        ZonedDateTime zdt2=ldt21.atZone(zid2);
        ZonedDateTime utcDate1 = zdt2.withZoneSameInstant(ZoneId.of("Asia/Dubai"));
         ldt21=utcDate1.toLocalDateTime();
          Timestamp ts1 = Timestamp.valueOf(ldt21); 
         
          System.out.println("usertime" + ldts + "sone"+ ldt21);
          
              LocalDateTime now = LocalDateTime.now();

      
          
        System.out.println("current time"+ ts + "15minutes time"+ ts1);
        String user = loginuser;
        System.out.println("userlogin" + user);
        try {
            
            
             Statement statement = DBConnection.getConn().createStatement();
            String query = "SELECT * FROM appointment WHERE start BETWEEN '" + ts + "' AND '" + ts1 + "' AND " + 
               "contact='" + user + "'";
           
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"); 
                String start=rs.getString("start");
                String description=rs.getString("description");
               LocalDateTime localTime =LocalDateTime.parse(start, df);
                ZoneId zid = ZoneId.systemDefault();
                //ZonedDateTime zdtStart = localTime.atZone(zid);
                ZonedDateTime zdtStart = localTime.atZone(zid);
                
                String name=rs.getString("url");
                
        
          Alert alert1=new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("appointment Alert");
            alert1.setContentText("You have an "+ description+ " appointment with "+ name+  " with in 15 minutes");
            alert1.showAndWait();
           
            }
            else System.out.println ("No appointment for next 15 minutes");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        
        
        
        
        
        
        
        
        try{            
            
        PreparedStatement statement = DBConnection.getConn().prepareStatement(
        "SELECT appointmentId, title, description, "
                + "start, end, location , url, contact  "
                + "FROM appointment "
               );
            ResultSet rs = statement.executeQuery();
           
            
            while (rs.next()) {
                
             Timestamp tsStart = rs.getTimestamp("start");
             
             Timestamp tsEnd = rs.getTimestamp("end");
             
   
            
             
                ZonedDateTime newzdtStart = tsStart.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime newLocalStart = newzdtStart.withZoneSameInstant(ZoneOffset.systemDefault());
                LocalDateTime ld=newLocalStart.toLocalDateTime();
                
                System.out.println("From db in local time: " + newLocalStart);
                
                
                 ZonedDateTime newzdtEnd= tsEnd.toLocalDateTime().atZone(ZoneId.of("UTC"));
        	ZonedDateTime newLocalEnd= newzdtEnd.withZoneSameInstant(ZoneOffset.systemDefault());
                LocalDateTime la=newLocalEnd.toLocalDateTime();
            
                   String id = rs.getString("appointmentId");
                String title = rs.getString("title");
                String contac = rs.getString("contact");
                String type = rs.getString("description");
                 String loca = rs.getString("location");
                 //String st=rs.getString("start");
                  String en=rs.getString("end");
                 String url1 = rs.getString("url");
                 System.out.println(title + "||");
                 System.out.println(contac + "||");
                 System.out.println(type + "||");
                 
                 System.out.println("id" + id);
              
                  customerList1a.add(new Appointment(id, url1, contac, loca, title, type,ld.toString(),la.toString()));
                  
                 
              
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
         
                 
                   AppointmentTable.setItems(customerList1a);
               
                      
            ID.setCellValueFactory(cellData -> {
            return cellData.getValue().idProperty();
        });
                        
                    Customer.setCellValueFactory(new PropertyValueFactory("CustomerName"));
          Contact.setCellValueFactory(new PropertyValueFactory("Contact"));
          Locatio.setCellValueFactory(new PropertyValueFactory("Location"));
           Title.setCellValueFactory(new PropertyValueFactory("Title"));
            Type.setCellValueFactory(new PropertyValueFactory("Type"));
            Start.setCellValueFactory(new PropertyValueFactory("Start"));
               End.setCellValueFactory(new PropertyValueFactory("End"));
        
       
          
    }  
    
    @FXML
     void NewAppointment(ActionEvent event)  throws IOException {

           Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/AppointmentNew.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
        

    }
       @FXML
     void Delete(ActionEvent event)   {
        
      Appointment selected=AppointmentTable.getSelectionModel().getSelectedItem();
      
      
      
      
      if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete " + selected.getTitle() + " scheduled for " + selected.getStart() + "?");
            alert.showAndWait()
            .filter(response -> response == ButtonType.OK) // by using lambda Expression we have to write fewer lines of code for Alert dialog.
            .ifPresent(response -> {
                    AppointmentTable.getItems().remove(selected);
                    delete(selected);
              
                }
      
      );
      
      } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Appointment selected for Deletion");
            alert.setContentText("Please select an Appointment to delete");
            alert.showAndWait();
        }

      
      } 
       public void delete(Appointment app){
         
                 
      
     
      String cus=app.getCustomerName();
      String con=app.getContact();
      String loc=app.getLocation();
      String tit=app.getTitle();
      String typ=app.getType();
 
      
    
        try {
           
            System.out.println("Delete List" + tit + "" + cus + "1  "+ typ + "2" + con + "3" + loc);

 PreparedStatement psc = DBConnection.getConn().prepareStatement("DELETE FROM appointment WHERE appointmentId = ?");
                  
                
                  psc.setString(1, app.getID());
               
                 
                 
                  psc.executeUpdate();
                  
      
      
      
       } catch (SQLException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     } 
   
     
     
         @FXML
    void Edit(ActionEvent event) throws IOException{
        
            if (AppointmentTable.getSelectionModel().isEmpty()){
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No appointment found");
            alert.setHeaderText("No appointment Selected");
            alert.setContentText("please select appointment");
            alert.showAndWait();
       }
       else {
  
        
       
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/AppointmentEdit.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
            
    
             
          AppointmentEditController controller=loader.getController();
              controller.initData(AppointmentTable.getSelectionModel().getSelectedItem());
              
        
        
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
       window.show();

        
        
    }
}
}
     
     
     
       

    

