package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
	private int userID;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userGender;
	private LocalDate userDOB;
	private String userRole;
	
	public User(int userID, String userName, String userEmail, String userPassword, String userGender,
			LocalDate userDOB, String userRole) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.userDOB = userDOB;
		this.userRole = userRole;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public LocalDate getUserDOB() {
		return userDOB;
	}

	public void setUserDOB(LocalDate userDOB) {
		this.userDOB = userDOB;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	// call UserDAO to add a user
	public static void addUser(String name, String email, String password, String gender, LocalDate dob, String role) {
		UserDAO.addUser(name, email, password, gender, dob, role);
	}
	
	// call UserDAO to process log in data
	public static User login(String email, String password) {	
	    return UserDAO.login(email, password);
	}
	
	// call UserDAO to get a list of users with a specific role
	public static ArrayList<User> getUsersByRole(String role) {
	    return UserDAO.getUsersByRole(role);
	}
	
	// to get a user with a specific name
	public static User getUserByName(String name) {
		return UserDAO.getUserByName(name);
	}
	
	// to get a user with a specific email
	public static User getUserByEmail(String email) {
		return UserDAO.getUserByEmail(email);
	}

}

