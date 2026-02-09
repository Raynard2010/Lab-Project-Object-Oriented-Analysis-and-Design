package controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javafx.scene.control.Alert;
import model.Employee;
import model.User;

public class UserController {
	
	// call User to get a user with a specific name
	public static User getUserByName(String name) {
		return User.getUserByName(name);
	}
	
	// call User to get a user with a specific email
	public static User getUserByEmail(String email) {
		return User.getUserByEmail(email);
	}
	
	// to validate new user input
	public static String validateAddCustomer(String name, String email, String password, String confirmPassword, String gender, LocalDate dob) {
		// validate name not empty
		if(name.isEmpty()) return "Name must not be empty";
		
		// to check whether the name input exists in the database or not
		User user = getUserByName(name);	    
	    if(user != null)  return "A user with that username exists";
	    
	    // validate email not empty and ends with @email.com
		if(email.isEmpty()) return "Email must not be empty";
		if(!email.endsWith("@email.com")) return "Email must end with @email.com";
		
		// to check whether the email input exists in the database or not
		user = getUserByEmail(email);	    
	    if(user != null)  return "A user with that email exists";    
		
	    // validate password longer than 6 characters
		if(password.length() < 6) return "Password is too short";
		// validate password confirmation is the same as password
		if(!confirmPassword.equals(password)) return "Password confirmation is not the same as password";
		
		// validate a gender is selected
		if(gender.isEmpty()) return "Please select a gender";
		
		// to validate if the user is older than 12 years old by counting the age
		LocalDate now = LocalDate.now();
        int age = Period.between(dob, now).getYears();
		if(age < 12) return "You must be at least 12 years old";
		
		// return empty string if all validation passed
		return "";
	}
	
	// to validate new employee input
	public static String validateAddEmployee(String name, String email, String password, String confirmPassword, String gender, LocalDate dob, String role) {
		// validate name not empty
		if(name.isEmpty()) return "Name must not be empty";
		
		// to check whether the name input exists in the database or not
		User user = getUserByName(name);	    
	    if(user != null)  return "A user with that username exists";
	    
	    // validate email not empty and ends with @govlash.com
		if(email.isEmpty()) return "Email must not be empty";
		if(!email.endsWith("@govlash.com")) return "Email must end with @govlash.com";
		
		// to check whether the email input exists in the database or not
		user = getUserByEmail(email);	    
	    if(user != null)  return "A user with that email exists";	    
		
	    // validate password longer than 6 characters
		if(password.length() < 6) return "Password is too short";
		// validate password confirmation is the same as password
		if(!confirmPassword.equals(password)) return "Password confirmation is not the same as password";
		
		// to validate if the employee is older than 17 years old by counting the age
		LocalDate now = LocalDate.now();
        int age = Period.between(dob, now).getYears();
		if(age < 17) return "You must be at least 17 years old";
		
		// return empty string if all validation passed
		return "";
	}
	
	// to add new user
	public static void addUser(String name, String email, String password, String confirmPassword, String gender, LocalDate dob, String role) {
		// to store validation messages
		String notif;
		
			notif = validateAddCustomer(name, email, password, confirmPassword, gender, dob);
	        // If validation failed, show error message
			if(!notif.equals("")) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setHeaderText("Registration Failed");
		        alert.setContentText(notif);
		        alert.show();
		        return;
			}
			
			// call User to add new user and display success message
			User.addUser(name, email, password, gender, dob, role);
		    Alert success = new Alert(Alert.AlertType.INFORMATION);
		    success.setHeaderText("Registration Success");
		    success.setContentText("New customer added");
		    success.show();
			return;

	}
	
	// to add new employee
	public static void addEmployee(String name, String email, String password, String confirmPassword, String gender, LocalDate dob, String role) {
		// to store validation messages
		String notif;

			notif = validateAddEmployee(name, email, password, confirmPassword, gender, dob, role);
	        // If validation failed, show error message
			if(!notif.equals("")) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setHeaderText("Add Employee Failed");
		        alert.setContentText(notif);
		        alert.show();
		        return;
			}
			
			// call User to add new employee and display success message
			User.addUser(name, email, password, gender, dob, role);
		    Alert success = new Alert(Alert.AlertType.INFORMATION);
		    success.setHeaderText("Add Employee Success");
		    success.setContentText("New employee added");
		    success.show();
			return;
		
	}
	
	// call User to process login data
	public static User login(String email, String password) {
		return User.login(email, password);
	}
	
	// call Employee to get all employees
	public static List<User> getAllEmployees(){
		return Employee.getAllEmployees();
	}
	
	// call User to get all users with a specific role
	public static List<User> getUsersByRole(String role) {
	    return User.getUsersByRole(role);
	}

}
