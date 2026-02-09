package model;

import java.time.LocalDate;
import java.util.List;

public class Employee extends User{

	public Employee(int userID, String userName, String userEmail, String userPassword, String userGender,
			LocalDate userDOB, String userRole) {
		super(userID, userName, userEmail, userPassword, userGender, userDOB, userRole);
	}
	
	// call EmployeeDAO to get all employees
	public static List<User> getAllEmployees(){
		return EmployeeDAO.getAllEmployees();
	}
}
