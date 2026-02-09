package controller;

import java.util.List;

import javafx.scene.control.Alert;
import model.Transaction;

public class TransactionController {

	// validate new order
    public String validateOrder(Double totalWeight, String notes) {
    	// validate if weight is not between 2 and 50 kg, or null
        if (totalWeight < 2 || totalWeight > 50 || totalWeight == null) return "Weight must be between 2 and 50kg!";
        // validate if notes is longer than 250 characters or empty
        if (notes == null || notes.trim().isEmpty() || notes.length() > 250) return "Notes must be less than or equal to 250 characters!";
		// return empty string if all validations were passed
        return "";
    }

    // to add new order
    public void orderLaundryService(int serviceID, int customerID, Double totalWeight, String notes) {
    	// validate inputs
		String notif = validateOrder(totalWeight, notes);
		
        // if validation failed, show error message
		if(!notif.equals("")) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setHeaderText("Add Service Failed");
	        alert.setContentText(notif);
	        alert.show();
	        return;
		}
	        
		// call Transaction to add new Transaction
        Transaction.orderLaundryService(serviceID, customerID, totalWeight, notes);
        
        // show success message
	    Alert success = new Alert(Alert.AlertType.INFORMATION);
	    success.setHeaderText("Add Transaction Success");
	    success.setContentText("New transaction added");
	    success.show();
		return;
    }

    // to assign an order to a laundry staff
    public static void assignOrderToLaundryStaff(int transactionId, int receptionistId, int laundryStaffId) {
    	// call Transaction to assign a laundry staff to and order
        Transaction.assignOrderToLaundryStaff(transactionId, receptionistId, laundryStaffId);
    	
    	// show success message
	    Alert success = new Alert(Alert.AlertType.INFORMATION);
	    success.setHeaderText("Assign Success");
	    success.setContentText("Order assigned");
	    success.show();
    }
    
    // call Transaction to get list of transactions that are assigned to a specific laundry staff
    public List<Transaction> getAssignedOrdersByLaundryStaffID(int laundryStaffID) {
        return Transaction.getAssignedOrdersByLaundryStaffID(laundryStaffID);
    }

    // call Transaction to update the status of a specific transaction
    public void updateTransactionStatus(int transactionID, String status) {
        Transaction.updateTransactionStatus(transactionID, status);
        
    	// show success message
	    Alert success = new Alert(Alert.AlertType.INFORMATION);
	    success.setHeaderText("Marked As Finished Success");
	    success.setContentText("Marked as finished");
	    success.show();
    }
    
    // call Transaction to get list of transactions with a specific status
    public static List<Transaction> getTransactionByStatus(String status){
    	return Transaction.getTransactionByStatus(status);
    }
    
    // call Transaction to get all transactions
    public static List<Transaction> getAllTransactions() {
    	return Transaction.getAllTransactions();
    }
    
    // call Transaction to get list of transactions with a specific customerId
    public static List<Transaction> getTransactionsByCustomerID(int customerID) {
    	return Transaction.getTransactionsByCustomerID(customerID);
    }

}
