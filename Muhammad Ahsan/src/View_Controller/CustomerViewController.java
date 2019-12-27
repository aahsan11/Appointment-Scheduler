


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Database.DBConnection;


import Model.CustomerInf;
import static Model.CustomerInf.deleteCustomer;
import static View_Controller.LogInScreenController.loginuser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class CustomerViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private TextField Name1;

    @FXML
    private TextField Address1;
     @FXML
    private TextField Address3;

    @FXML
    private TextField PostalCode1;

    @FXML
    private TextField Phone1;

    @FXML
    private ComboBox <String> City1;

    @FXML
    private TextField Country1;
   
     @FXML
    private Button Save2;

    @FXML
    private Button Edit;
    
      @FXML
    private Button Save;

    @FXML
    private Button Delete;

    @FXML
    private TableView<CustomerInf> Table;



  @FXML
    private TableColumn<CustomerInf, String> Cus;

    @FXML
    private TableColumn<CustomerInf, String> Address;

    @FXML
    private TableColumn<CustomerInf, String> Address2;

    @FXML
    private TableColumn<CustomerInf, String> PostalCode;

    @FXML
    private TableColumn<CustomerInf, String> Phone;

    @FXML
    private TableColumn<CustomerInf, String> City;

    @FXML
    private TableColumn<CustomerInf, String> Country;
    
 
    private DBConnection db;

      ObservableList<CustomerInf> customerList = FXCollections.observableArrayList();
      ObservableList<CustomerInf> insertCustomer=FXCollections.observableArrayList();
         ObservableList<CustomerInf> customerEdit = FXCollections.observableArrayList();
      
      
          public ObservableList<String> city1=FXCollections.observableArrayList("LosAngles", "Washington DC", "Toronto", "London");

    
          boolean isValid(){
              String error="";
              String Name=Name1.getText();
              String Address=Address1.getText();
              String PostalCode3=PostalCode1.getText();
              String Phone3=Phone1.getText();
              String City3=City1.getValue();
              if (Name==null || Name.length()==0){
                  error+="Please enter cutomerName. \n ";
              }
              if (Address==null || Address.length()==0){
                  error=error+ "Please enter address. \n";
              }
              if (PostalCode3==null || PostalCode3.length()==0 ){
                  error+="Please enter postalCode. \n";
              }
              if (Phone3==null || Phone3.length()==0){
                  error+="Please enter Phone. \n";
              }
              if (City3==null){
                  error+="Please select city. \n";
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
    
    @FXML
    void citycombo() {
    
    if (City1.getSelectionModel().getSelectedItem().equals("LosAngles")
        ||City1.getSelectionModel().getSelectedItem().equals("Washington DC"))
    {
        Country1.setText("USA");
    }
    else if (City1.getSelectionModel().getSelectedItem().equals("London")){
          Country1.setText("UK");
    }
              else {
            Country1.setText("Canada");       
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
       Country1.setEditable(false);
        City1.setItems(city1);
        Save2.setVisible(false);
        
    
      try {
           
       
        ResultSet rs = null;
          
         
        
        Connection con=DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U04fVQ", "U04fVQ", "53688229162");
       
        
          
          
            

            Statement pst;

                   String sql=  "SELECT customer.addressId, customer.customerId, customerName, address, address2, postalCode, phone, city, country "
					+ "FROM customer , address , city , country "
					+ "WHERE customer.addressId = address.addressId "
					+ "AND address.cityId = city.cityId "
					+ "AND city.countryId = country.countryId";
                   pst=con.prepareStatement(sql);
                   rs=pst.executeQuery(sql);

           
                 System.out.println(rs + "address");
               
                while (rs.next()) {
                    String name=rs.getString("customerName");
                    String address=rs.getString("address");
                    String city=rs.getString("city");
                    String country=rs.getString("country");
                    String address2=rs.getString("address2");
                    String phone=rs.getString("phone");
                    String postalCode=rs.getString("postalCode");
                     System.out.println(name + "||| "+address +"||| "+address2 +"|||"+postalCode+   "|||" +phone +"|||"
                             +city+ "|||"+country+"|||"
                             );
                    
                   customerList.add(new CustomerInf( name,address,address2,postalCode, phone, city, country));
                 
                   Table.setItems(customerList);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

         
    
       Cus.setCellValueFactory(new PropertyValueFactory("cus"));
        Address.setCellValueFactory(new PropertyValueFactory("address"));
  
          
        Address2.setCellValueFactory(new PropertyValueFactory("address2"));
        PostalCode.setCellValueFactory(new PropertyValueFactory("postalCode"));
          Phone.setCellValueFactory(new PropertyValueFactory("phone"));
        City.setCellValueFactory(new PropertyValueFactory("city"));
        Country.setCellValueFactory(new PropertyValueFactory("country"));
      
          
       
        
    }
    
       @FXML
    void Save(ActionEvent event)  {
       
                if (isValid()){       
         String name=Name1.getText();
        String address=Address1.getText();
        String address2=Address3.getText();
        String city=City1.getSelectionModel().getSelectedItem();
        String postalcode=PostalCode1.getText();
        String phonenumber=Phone1.getText();
        String country=Country1.getText();
   
        
         try{
          
          String query=("INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) "
                        + "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)");
  
   PreparedStatement ps = DBConnection.getConn().prepareStatement(query,java.sql.Statement.RETURN_GENERATED_KEYS);
   ps.setString(1, address);
     ps.setString(2, address2);    
     if  (City1.getSelectionModel().getSelectedItem().equals("Washington DC"))
             {
         ps.setInt(3, 1);
     }
     else if  (City1.getSelectionModel().getSelectedItem().equals("LosAngles")){
         ps.setInt(3, 2);
     }
     else if  (City1.getSelectionModel().getSelectedItem().equals("London")){
         ps.setInt(3, 4);
     }
     else {
         ps.setInt(3, 3);
     }
     
     
    
     ps.setString(4, postalcode);
     ps.setString(5, phonenumber);
     ps.setString(6, loginuser);// test
     ps.setString(7, loginuser);// test
     int newAddressId = -1;
     
      boolean res = ps.execute();
       
                ResultSet rs = ps.getGeneratedKeys();
                
                
                if(rs.next()){
                    newAddressId = rs.getInt(1);
                    System.out.println("Generated AddressId: "+ newAddressId);
                }
               
                 PreparedStatement psc = DBConnection.getConn().prepareStatement("INSERT INTO customer "
                + "(customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)"
                + "VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)");
         psc.setString(1, name);
         psc.setInt(2, newAddressId);
         psc.setInt(3, 1);
         psc.setString(4, loginuser); // test
         psc.setString(5, loginuser); // test
         
         int re=psc.executeUpdate();
   
         
         
         int countryId = -1;

		
         customerList.add(new CustomerInf(name, address, address2,postalcode, phonenumber,city,country ));
         
         Table.setItems(customerList);
     
         Name1.clear();
        Address1.setText("");
           Address3.setText("");
         
                    PostalCode1.setText("");
                       Phone1.setText("");
         
           
          
    }catch (SQLException ex) {
            ex.printStackTrace();
            }
         
         
         }
             
    }
    
       @FXML
    void Save2(ActionEvent event)  {
      if (isValid()){
          
        Save2.setVisible(false);
        Save.setVisible(true);
        
       int customerId = -1;
    try{
         PreparedStatement psg = DBConnection.getConn().prepareStatement("SELECT customerId FROM customer "
					+ "WHERE customerName = ? "
					//+ "AND addressId = ? "
					);   
         
           CustomerInf selectedItem = Table.getSelectionModel().getSelectedItem();
       
       String cu=selectedItem.getCus();
       String ad=selectedItem.getAddress();
       String ad2=selectedItem.getAddress2();
       String po=selectedItem.getPostalCode();
       String ph=selectedItem.getPhone();
       String ci=selectedItem.getCity();
       String co=selectedItem.getCountry();
   
       
         String as=Name1.getText();
         
        String ne=Address1.getText();
        System.out.println(cu + "this is name");
        String as2=Address3.getText();
        String pn=Phone1.getText();
        String pe=PostalCode1.getText();
        String cit=City1.getSelectionModel().getSelectedItem();
        String country=Country1.getText();
       // String cu=Name1.getText();
          Table.getItems().remove(selectedItem);
       
         psg.setString(1, cu);
       
   
             ResultSet result = psg.executeQuery();

			if(result.next()) {
				customerId = result.getInt("customerId");
                                System.out.println("customerId" + customerId);
			}
                        
                        
                         PreparedStatement ps = DBConnection.getConn().prepareStatement("UPDATE address, customer, city, country "
                        + "SET address = ?, address2 = ?, address.cityId = ?, postalCode = ?, phone = ?, address.lastUpdate = CURRENT_TIMESTAMP, address.lastUpdateBy = ?"
                        + "WHERE customer.customerId = ? AND customer.addressId = address.addressId AND address.cityId = city.cityId AND city.countryId = country.countryId");
                          ps.setString(1, ne);
                ps.setString(2, as2);
               
                if  (City1.getSelectionModel().getSelectedItem().equals("Washington DC"))
             {
         ps.setInt(3, 1);
     }
     if  (City1.getSelectionModel().getSelectedItem().equals("LosAngles")){
         ps.setInt(3, 2);
     }
      if  (City1.getSelectionModel().getSelectedItem().equals("London")){
         ps.setInt(3, 4);
     }
     else {
         ps.setInt(3, 3);
     }
                
                
                ps.setString(4, pe);
                ps.setString(5, pn);
               ps.setString(6, loginuser);
                ps.setInt(7, customerId);
                
                int resu = ps.executeUpdate();
                
                 PreparedStatement psc = DBConnection.getConn().prepareStatement("UPDATE customer, address, city "
                + "SET customerName = ?, customer.lastUpdate = CURRENT_TIMESTAMP, customer.lastUpdateBy = ? "
                + "WHERE customer.customerId = ? AND customer.addressId = address.addressId AND address.cityId = city.cityId");
            
                psc.setString(1, as);
                psc.setString(2, loginuser);
                psc.setInt(3, customerId);
                int results = psc.executeUpdate();
                
          customerList.add(new CustomerInf(as, ne, as2,pe, pn,cit,country ));
         
         Table.setItems(customerList);
         
           Name1.clear();
        Address1.setText("");
           Address3.setText("");
         
                    PostalCode1.setText("");
                       Phone1.setText("");
      }catch (SQLException ex) {
            ex.printStackTrace();
            }
      }
      }
   
       @FXML
    void Back(ActionEvent event)throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm ");
            alert.setHeaderText("Are you sure you want to go back to main screen " );
            alert.showAndWait()
            .filter(response -> response == ButtonType.OK) // by using lambda Expression we have to write fewer lines of code for Alert dialog.
            .ifPresent(response -> {
            
               
            
            try {
               
                
                
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

    
   
    @FXML
    void Delet(ActionEvent event){
         
        CustomerInf selectedItem = Table.getSelectionModel().getSelectedItem();
     
    if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete " + selectedItem.getCus());
            alert.showAndWait()
            .filter(response -> response == ButtonType.OK) // by using lambda Expression we have to write fewer lines of code for Alert dialog.
            .ifPresent(response -> { 
                     Table.getItems().remove(selectedItem);
                    delete(selectedItem);
              
                }
      
      );
      
      } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Record selected for Deletion");
            alert.setContentText("Please select a Record to delete");
            alert.showAndWait();
        }

         
     }      
            
        
    
    public void delete(CustomerInf cus){
        
          
       String cu=cus.getCus();
       String ad=cus.getAddress();
       String ad2=cus.getAddress2();
       String po=cus.getPostalCode();
       String ph=cus.getPhone();
   
        try {
             ResultSet rs = null;
            Connection con=DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U04fVQ", "U04fVQ", "53688229162");
              Statement pst;
       

 PreparedStatement psc = DBConnection.getConn().prepareStatement("DELETE FROM address WHERE address=?");
                  
                
                  psc.setString(1, cus.getAddress());
                
                 
                  psc.executeUpdate();
                  
                   PreparedStatement pso = DBConnection.getConn().prepareStatement("DELETE FROM customer WHERE customerName=?");
                    pso.setString(1, cus.getCus());
                    pso.executeUpdate();
                    
                    PreparedStatement psp = DBConnection.getConn().prepareStatement("DELETE FROM address WHERE address2=?");
                     psp.setString(1, cus.getAddress2());
                    psp.executeUpdate();
                    
                    PreparedStatement psl = DBConnection.getConn().prepareStatement("DELETE FROM address WHERE postalCode=?");
                     psl.setString(1, cus.getPostalCode());
                    psl.executeUpdate();
                    
                     PreparedStatement psu = DBConnection.getConn().prepareStatement("DELETE FROM address WHERE phone=?");
                     psu.setString(1, cus.getPhone());
                    psu.executeUpdate();
                    
                    
        } catch (SQLException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
          
          
            

      
    
        
        
    }
    
        @FXML
    void EditRecord(ActionEvent event) {
        CustomerInf cus=Table.getSelectionModel().getSelectedItem();
        if(Table.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
           
            alert.setTitle("No Selection");
            alert.setHeaderText("No Record Selected ");
            alert.setContentText("Please select a record to Edit");
           

            alert.showAndWait();
        }
        else{
            Save2.setVisible(true);
            Save.setVisible(false);
        String as=cus.getAddress();
        String ne=cus.getCus();
        String as2=cus.getAddress2();
        String cy=cus.getCity();
        String pn=cus.getPhone();
        String pe=cus.getPostalCode();
        String cy1=cus.getCountry();
        
        Name1.setText(ne);
        Address1.setText(as);
        Address3.setText(as2);
        Phone1.setText(pn);
        PostalCode1.setText(pe);
        City1.getSelectionModel().select(cy);
        Country1.setText(cy1);
                }
    }
    
}
