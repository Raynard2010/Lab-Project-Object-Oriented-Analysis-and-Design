package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import main.Main;

public class UserDAO {
	
	// add new user to database
	public static void addUser(String name, String email, String password, String gender, LocalDate dob, String role) {
		String query = "INSERT INTO users (username, useremail, userpassword, usergender, userdob, userrole) VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = Main.connect.prepareStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, gender);
			ps.setDate(5, java.sql.Date.valueOf(dob));
			ps.setString(6, role);
	
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	// add new employee to database
	public static void addEmployee(String name, String email, String password, String gender, LocalDate dob, String role) {
		String query = "INSERT INTO users (username, useremail, userpassword, usergender, userdob, userrole) VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = Main.connect.prepareStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, gender);
			ps.setDate(5, java.sql.Date.valueOf(dob));
			ps.setString(6, role);
	
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	// to process log in data
	public static User login(String email, String password) {
		// get a user with the email and password input
	    String query = "SELECT * FROM users WHERE userEmail = ? AND userPassword = ? LIMIT 1";

	    try (PreparedStatement ps = Main.connect.prepareStatement(query)) {
	        ps.setString(1, email);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();
	        
	        // return the user that is logging in
	        if (rs.next()) {
	            return new User(
	                rs.getInt("userID"),
	                rs.getString("userName"),
	                rs.getString("userEmail"),
	                rs.getString("userPassword"),
	                rs.getString("userGender"),
	                rs.getDate("userDOB").toLocalDate(),
	                rs.getString("userRole")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null; // login failed		
	}
	
	// access database to get a list of users with a specific role
	public static ArrayList<User> getUsersByRole(String role) {
		// to store the users
		ArrayList<User> users = new ArrayList<>();

	    String query = "SELECT * FROM users WHERE userRole = ?";

	    try (PreparedStatement ps = Main.connect.prepareStatement(query)) {
	        ps.setString(1, role);
	        ResultSet rs = ps.executeQuery();

	        // loop, get data, and add to list
	        while (rs.next()) {
	            users.add(new User(
	                rs.getInt("userID"),
	                rs.getString("userName"),
	                rs.getString("userEmail"),
	                rs.getString("userPassword"),
	                rs.getString("userGender"),
	                rs.getDate("userDOB").toLocalDate(),
	                rs.getString("userRole")
	            ));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // return list
	    return users;
	}
	
	// to get a user with a specific name
	public static User getUserByName(String name) {
	    String query = "SELECT * FROM users WHERE username = ?";

	    try (PreparedStatement ps = Main.connect.prepareStatement(query)) {
	        ps.setString(1, name);

	        ResultSet rs = ps.executeQuery();
	        
	        // if user is found, return the user data
	        if (rs.next()) {
	            return new User(
	                rs.getInt("userID"),
	                rs.getString("userName"),
	                rs.getString("userEmail"),
	                rs.getString("userPassword"),
	                rs.getString("userGender"),
	                rs.getDate("userDOB").toLocalDate(),
	                rs.getString("userRole")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null; // no user with the name found
	}
	
	// to get a user with a specific email
	public static User getUserByEmail(String email) {
	    String query = "SELECT * FROM users WHERE useremail = ?";

	    try (PreparedStatement ps = Main.connect.prepareStatement(query)) {
	        ps.setString(1, email);

	        ResultSet rs = ps.executeQuery();

	        // if user is found, return the user data
	        if (rs.next()) {
	            return new User(
	                rs.getInt("userID"),
	                rs.getString("userName"),
	                rs.getString("userEmail"),
	                rs.getString("userPassword"),
	                rs.getString("userGender"),
	                rs.getDate("userDOB").toLocalDate(),
	                rs.getString("userRole")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; // no user with the email found
	}

}
