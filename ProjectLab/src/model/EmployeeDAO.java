package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.Main;

public class EmployeeDAO {
	
	// access database to get all employees
	public static List<User> getAllEmployees(){
		// get all users with employee roles
		String query = "SELECT * FROM users WHERE userrole = 'Admin' OR userrole ='Laundry Staff' OR userrole = 'Receptionist'";
		Main.connect.rs = Main.connect.execQuery(query);
		
		// list to store all the employees
		List<User> users;
		users = new ArrayList<>();
		
		// loop and get data
		try {
			while(Main.connect.rs.next()) {
				Integer id = Main.connect.rs.getInt("userid");
				String name = Main.connect.rs.getString("userName");
				String email = Main.connect.rs.getString("userEmail");
				String password = Main.connect.rs.getString("userPassword");
				String gender = Main.connect.rs.getString("userGender");
				LocalDate dob = Main.connect.rs.getDate("userDOB").toLocalDate();
				String role = Main.connect.rs.getString("userRole");
				
				// add data to list
				users.add(new User(id, name, email, password, gender, dob, role));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// return list
		return users;
	}
}
