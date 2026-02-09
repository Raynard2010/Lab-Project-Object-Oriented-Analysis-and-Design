package controller;

import java.util.List;

import javafx.scene.control.Alert;
import model.Service;

public class ServiceController {
	
	// call Service to get all services
	public static List<Service> getAllServices(){
		return Service.getAllServices();
	}
	
	// to validate new service input
	public static String validateAddService(String name, String description, double price, int duration) {
		// validate if name is empty or longer than 50 characters
		if(name.isEmpty()) return "Name must not be empty";
	    if(name.length() > 50) return "Name too long";
	    
	    // validate if description is empty or longer than 250 characters
		if(description.isEmpty()) return "Description must not be empty";
	    if(description.length() > 250) return "Name too long";	    
		
	    // validate if price is less than 0
		if(price <= 0) return "Invalid price";
		// validate if duration is shorter than 1 day or longer than 30 days
		if(duration < 1 || duration > 30) return "Invalid duration (Must be between 1 and 30 days)";
		
		// return empty string if all validations were passed
		return "";
	}
	
	// to validate edit service input
	public static String validateEditService(String name, String description, double price, int duration) {
		// validate if name is empty or longer than 50 characters
		if(name.isEmpty()) return "Name must not be empty";
	    if(name.length() > 50) return "Name too long";
	    
	    // validate if description is empty or longer than 250 characters
		if(description.isEmpty()) return "Description must not be empty";
	    if(description.length() > 250) return "Name too long";	    
		
	    // validate if price is less than 0
		if(price <= 0) return "Invalid price";
		// validate if duration is shorter than 1 day or longer than 30 days
		if(duration < 1 || duration > 30) return "Invalid duration (Must be between 1 and 30 days)";
		
		// return empty string if all validations were passed
		return "";
	}
	
	// to add new service
	public static void addService(String name, String description, double price, int duration) {
		// validate inputs
		String notif = validateAddService(name, description, price, duration);
				
        // if validation failed, show error message
		if(!notif.equals("")) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setHeaderText("Add Service Failed");
	        alert.setContentText(notif);
	        alert.show();
	        return;
		}
		
		// if validation passed, show success message and call Service to add new service
		Service.addService(name, description, price, duration);
	    Alert success = new Alert(Alert.AlertType.INFORMATION);
	    success.setHeaderText("Add Service Success");
	    success.setContentText("New service added");
	    success.show();
		return;
	}
	
	// to edit a service
	public static void editService(int serviceID, String name, String description, double price, int duration) { 
		// validate inputs 
		String notif = validateEditService(name, description, price, duration);
		
    	// if validation failed, show error message
		if(!notif.equals("")) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setHeaderText("Edit Service Failed");
	        alert.setContentText(notif);
	        alert.show();
	        return;
		}
		
		// if validation passed, show success message and call Service to edit service
		Service.editService(serviceID, name, description, price, duration);
	    Alert success = new Alert(Alert.AlertType.INFORMATION);
	    success.setHeaderText("Edit Service Success");
	    success.setContentText("Service edited");
	    success.show();
		return;
	}
	
	// tp delete a service
	public static void deleteService(int serviceID) {
		// call Service to delete a specific service
		Service.deleteService(serviceID);
		
		// show success message
	    Alert success = new Alert(Alert.AlertType.INFORMATION);
	    success.setHeaderText("Delete Service Success");
	    success.setContentText("Service deleted");
	    success.show();
		return;
	}
}
