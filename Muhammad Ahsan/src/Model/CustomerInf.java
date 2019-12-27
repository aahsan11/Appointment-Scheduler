


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mhamza0
 */
public class CustomerInf {
     private static ObservableList<CustomerInf>customers=FXCollections.observableArrayList();
  
  //  StringProperty customerName;
    StringProperty postalCode;
    StringProperty phone;
    StringProperty address2;
    StringProperty address;
    StringProperty country;
    StringProperty city;
    StringProperty cus;
    
    public CustomerInf(String cus,String address, String address2,String postalCode, String phone, String city,
             String country){
       
       
       this.postalCode=new SimpleStringProperty(postalCode);
       this.phone=new SimpleStringProperty(phone);
       this.address=new SimpleStringProperty(address);
       this.address2=new SimpleStringProperty(address2);
       this.country=new SimpleStringProperty(country);
       this.city=new SimpleStringProperty(city);
       this.cus=new SimpleStringProperty(cus);
    }
     
   
   
        public void setCus(String cus){
      this.cus= new SimpleStringProperty(cus);  
    }
   
    public void setAddress(String address){
        this.address=new SimpleStringProperty(address);
    }
    public void SetAddress2(String address2){
        this.address2=new SimpleStringProperty(address2);
    }
    public void SetPhone(String phone){
        this.phone=new SimpleStringProperty(phone);
    } 
    public void SetPostalCode(String postalCode){
        this.postalCode=new SimpleStringProperty(postalCode);
    }
      public void SetCity(String City){
        this.city=new SimpleStringProperty(City);
    }
    public void SetCountry(String country){
        this.country=new SimpleStringProperty(country);
    }
   
   
    public String getCus(){
        return cus.get();
    }
   
    public String getAddress(){
        return address.get();
    }
    public String getAddress2(){
        return address2.get();
    }
    public String getPhone(){
        return phone.get();
    }
    public String getPostalCode(){
        return postalCode.get();
    }
    public String getCountry(){
        return country.get();
    }
    public String getCity(){
        return city.get();
    }
     
   
    public StringProperty cusProperty(){
        return cus;
    }
   
    public StringProperty addressProperty(){
        return address;
    }
     public StringProperty address2Property(){
        return address2;
    }
      public StringProperty phoneProperty(){
        return phone;
    }
       public StringProperty city(){
        return city;
    }
        public StringProperty countryProperty(){
        return country;
    }
         public StringProperty postalProperty(){
        return postalCode;
    }
         public static void deleteCustomer(CustomerInf customer){
             customers.remove(customer);
             
         }
        
    
}
