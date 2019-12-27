/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Appointment {
    
    
    StringProperty CustomerName;
    StringProperty appointmentid;
    
    StringProperty Title;
    StringProperty Type;
    StringProperty Contact;
    StringProperty Location;
    StringProperty Star;
      StringProperty En;
  
    
    
    
    public Appointment( String appointmentid, String CustomerName, String Contact, String Location, String Title, String Type, String Start, String End){
        this.appointmentid=new SimpleStringProperty(appointmentid);
        this.CustomerName= new SimpleStringProperty(CustomerName);
        this.Contact=new SimpleStringProperty(Contact);
        this.Location=new SimpleStringProperty(Location);
         this.Title=new SimpleStringProperty(Title);
         this.Type=new SimpleStringProperty(Type);
          this.Star=new SimpleStringProperty(Start);
              this.En=new SimpleStringProperty(End);
       
    }
      public void setId(String appointmentid){
        this.appointmentid=new SimpleStringProperty(appointmentid);
    }
    
    
    public void setCustomerName(String CustomerName){
        this.CustomerName=new SimpleStringProperty(CustomerName);
    }
    public void setStartTime(String Start){
     this.Star=new SimpleStringProperty(Start);
    }
    
       
    public void setEndTime(String End){
    this.En=new SimpleStringProperty(End);
    }
    public void setTitle(String Title){
        this.Title=new SimpleStringProperty(Title);
    }
    public void setType(String Type){
        this.Type=new SimpleStringProperty(Type);
    }
    
    public void setContact(String Contact){
        this.Contact=new SimpleStringProperty(Contact);
    }
    
    
   
    
    public void setLocation(String Location){
       this.Location=new SimpleStringProperty(Location);
    }
     public String getID(){
        return appointmentid.get();
    }
     
    
    public String getCustomerName(){
        return CustomerName.get();
    }
    
    public String getStartTime(){
        return Star.get();
    }
   public String getEndTime(){
        return En.get();
    }
    public String getTitle(){
        return Title.get();
    }
    public String getType(){
        return Type.get();
    }
    public String getContact(){
        return Contact.get();
    }
    public String getLocation(){
        return Location.get();
    }
    public String getStart() {
            return Star.get();
        }
     public String getEnd() {
            return En.get();
        }
     
     public StringProperty idProperty(){
         return appointmentid;
     }
     
     public StringProperty cusProperty(){
        return CustomerName;
    }
   
    //public StringProperty startProperty(){
      //  return Start;
    //}
     
      public StringProperty titleProperty(){
        return Title;
    }
       public StringProperty contactProperty(){
        return Contact;
    }
        public StringProperty typeProperty(){
        return Type;
    }
         public StringProperty locationProperty(){
        return Location;
    }
         
        
          
}
