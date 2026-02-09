package model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import main.Main;

public class ServiceDAO {
	
	// access database to get all services
	public static List<Service> getAllServices(){
		String query = "SELECT * FROM services";
		Main.connect.rs = Main.connect.execQuery(query);
		
		// to store the services
		List<Service> services;
		services = new ArrayList<>();
		
		// loop and get datas from database
		try {
			while(Main.connect.rs.next()) {
				Integer id = Main.connect.rs.getInt("serviceID");
				String name = Main.connect.rs.getString("serviceName");
				String desc = Main.connect.rs.getString("serviceDescription");
				Double price = Main.connect.rs.getDouble("servicePrice");
				Integer duration = Main.connect.rs.getInt("serviceDuration");
				
				// add service to list
				services.add(new Service(id, name, desc, price, duration));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// return list
		return services;
	}
	
	// to add a service to the database
	public static void addService(String name, String description, double price, int duration) {		
		String query = "INSERT INTO services (servicename, servicedescription, serviceprice, serviceduration) VALUES (?, ?, ?, ?)";
		
		PreparedStatement ps = Main.connect.prepareStatement(query);
		
		// run query with our data
		try {
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setDouble(3, price);
			ps.setInt(4, duration);
	
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	// to edit a service in the database
	public static void editService(int serviceID, String name, String description, double price, int duration) {		
		String query = "UPDATE services SET servicename = ?,  servicedescription = ?, serviceprice = ?, serviceduration = ? WHERE serviceid = ?";
		
		PreparedStatement ps = Main.connect.prepareStatement(query);
		
		// run query with our data
		try {
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setDouble(3, price);
			ps.setInt(4, duration);
			ps.setInt(5, serviceID);
	
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	// to delete a service in the database
	public static void deleteService(int serviceID) {
		String query = "DELETE FROM services WHERE serviceid = ?";
		
		PreparedStatement ps = Main.connect.prepareStatement(query);
		
		// run query
		try {
			ps.setInt(1, serviceID);
	
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
}
