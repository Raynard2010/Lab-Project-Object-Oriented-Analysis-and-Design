package model;

import util.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import main.Main;

public class TransactionDAO {
   
	// to add a new order to database
    public static void orderLaundryService(int serviceID, int customerID, Double totalWeight, String notes) {
        try {
            PreparedStatement ps = Connect.getInstance().prepareStatement(
                "INSERT INTO transactions (serviceID, customerID, receptionistID, laundryStaffID, transactionDate, transactionStatus, totalWeight, transactionNotes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setInt(1, serviceID);
            ps.setInt(2, customerID);
            // null because no receptionist id and laundry staff id at creation
            ps.setObject(3, null); 
            ps.setObject(4, null);  
            // get current date
            ps.setDate(5, Date.valueOf(LocalDate.now()));
            // order is created as pending
            ps.setString(6, "Pending");
            ps.setDouble(7, totalWeight);
            ps.setString(8, notes);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    // to add receptionist id and laundry staff id to a specific order in the database
    public static void assignOrderToLaundryStaff(int transactionId, Integer receptionistId, Integer laundryStaffId) {
        String sql = "UPDATE transactions SET receptionistID=?, laundryStaffID=? WHERE transactionID=?";
        
        try {
            PreparedStatement ps = Connect.getInstance().prepareStatement(sql);
            ps.setObject(1, receptionistId);   
            ps.setObject(2, laundryStaffId);   
            ps.setInt(3, transactionId);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    // to get a list of transactions with a specific status from the database
    public static List<Transaction> getTransactionByStatus(String status) {
    	// to store the transactions
		List<Transaction> transactions;
		transactions = new ArrayList<>();
		
		try {
	        String query = "SELECT * FROM transactions WHERE transactionStatus = ?";
	        PreparedStatement ps = Main.connect.prepareStatement(query);

	        ps.setString(1, status);

	        ResultSet rs = ps.executeQuery();
	        
	        // loop and get data
			while(rs.next()) {
				Integer id = rs.getObject("transactionID", Integer.class);
				Integer serviceid = rs.getObject("serviceID", Integer.class);
				Integer customerid = rs.getObject("customerID", Integer.class);

				Integer receptionistID = rs.getObject("receptionistID", Integer.class);
				Integer laundryStaffID = rs.getObject("laundryStaffID", Integer.class);

				LocalDate date = rs.getDate("transactionDate").toLocalDate();
				String transactionStatus = rs.getString("transactionStatus");
				Double weight = rs.getDouble("totalWeight");
				String notes = rs.getString("transactionNotes");
				
				// add transaction to list
				transactions.add(new Transaction(id, serviceid, customerid, receptionistID, laundryStaffID, date, transactionStatus, weight, notes));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// return list
		return transactions;
    }

    // to get a list of transactions with a specific laundry staff id from the database
    public static ArrayList<Transaction> getAssignedOrdersByLaundryStaffID(int laundryStaffID) {
    	// to store the transactions
        ArrayList<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE laundryStaffID = ? AND transactionStatus != 'Finished'";

        try (PreparedStatement ps = Connect.getInstance().prepareStatement(sql)) {
            ps.setInt(1, laundryStaffID);
            ResultSet rs = ps.executeQuery();
            // loop, get data, and add them to list
            while (rs.next()) {
                list.add(new Transaction(
                    rs.getInt("transactionID"),
                    rs.getInt("serviceID"),
                    rs.getInt("customerID"),
                    (Integer) rs.getObject("receptionistID"),
                    (Integer) rs.getObject("laundryStaffID"),
                    rs.getDate("transactionDate").toLocalDate(),
                    rs.getString("transactionStatus"),
                    rs.getDouble("totalWeight"),
                    rs.getString("transactionNotes")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return list;
    }

    // to update the status of a specific transaction in the database
    public static void updateTransactionStatus(int transactionID, String status) {
        String sql = "UPDATE transactions SET transactionStatus = ? WHERE transactionID=?";
        try (PreparedStatement ps = Connect.getInstance().prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, transactionID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // to get all transactions from the database
    public static List<Transaction> getAllTransactions() {
    	// to store the transactions
    	List<Transaction> transactions = new ArrayList<>();
    	
    	try {
    		// Simple SELECT * - get everything
    		String query = "SELECT * FROM transactions";
    		PreparedStatement ps = Main.connect.prepareStatement(query);
    		ResultSet rs = ps.executeQuery();
    		
    		// Loop through all rows and build Transaction objects
    		while(rs.next()) {
    			Integer id = rs.getObject("transactionID", Integer.class);
    			Integer serviceid = rs.getObject("serviceID", Integer.class);
    			Integer customerid = rs.getObject("customerID", Integer.class);
    			Integer receptionistID = rs.getObject("receptionistID", Integer.class);
    			Integer laundryStaffID = rs.getObject("laundryStaffID", Integer.class);
    			LocalDate date = rs.getDate("transactionDate").toLocalDate();
    			String transactionStatus = rs.getString("transactionStatus");
    			Double weight = rs.getDouble("totalWeight");
    			String notes = rs.getString("transactionNotes");
    			
    			transactions.add(new Transaction(id, serviceid, customerid, receptionistID, laundryStaffID, date, transactionStatus, weight, notes));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return transactions;
    }
    
    // Gets all transaction for a specific customer by ID
    public static List<Transaction> getTransactionsByCustomerID(int customerID) {
    	List<Transaction> transactions = new ArrayList<>();
    	
    	try {
    		// Query with WHERE clause filtering by customer
    		String query = "SELECT * FROM transactions WHERE customerID = ?";
    		PreparedStatement ps = Main.connect.prepareStatement(query);
    		ps.setInt(1, customerID);
    		ResultSet rs = ps.executeQuery();
    		
    		// Build list of customer's transactions
    		while(rs.next()) {
    			Integer id = rs.getObject("transactionID", Integer.class);
    			Integer serviceid = rs.getObject("serviceID", Integer.class);
    			Integer customerid = rs.getObject("customerID", Integer.class);
    			Integer receptionistID = rs.getObject("receptionistID", Integer.class);
    			Integer laundryStaffID = rs.getObject("laundryStaffID", Integer.class);
    			LocalDate date = rs.getDate("transactionDate").toLocalDate();
    			String transactionStatus = rs.getString("transactionStatus");
    			Double weight = rs.getDouble("totalWeight");
    			String notes = rs.getString("transactionNotes");
    			
    			transactions.add(new Transaction(id, serviceid, customerid, receptionistID, laundryStaffID, date, transactionStatus, weight, notes));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return transactions;
    }

}
