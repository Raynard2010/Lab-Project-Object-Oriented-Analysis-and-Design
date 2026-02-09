package model;

import java.time.LocalDate;
import java.util.List;

public class Transaction {

    private Integer transactionID;
    private Integer serviceID;
    private Integer customerID;
    private Integer receptionistID;
    private Integer laundryStaffID;
    private LocalDate transactionDate;
    private String transactionStatus;
    private double totalWeight;
    private String transactionNotes;

    public Transaction(Integer transactionID, Integer serviceID, Integer customerID,
                       Integer receptionistID, Integer laundryStaffID,
                       LocalDate transactionDate, String transactionStatus,
                       double totalWeight, String transactionNotes) {
        this.transactionID = transactionID;
        this.serviceID = serviceID;
        this.customerID = customerID;
        this.receptionistID = receptionistID;
        this.laundryStaffID = laundryStaffID;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.totalWeight = totalWeight;
        this.transactionNotes = transactionNotes;
    }
    

    public int getTransactionID() { return transactionID; }
    public int getServiceID() { return serviceID; }
    public int getCustomerID() { return customerID; }
    public String getTransactionStatus() { return transactionStatus; }
    public double getTotalWeight() { return totalWeight; }
    public String getTransactionNotes() { return transactionNotes; }
    public Integer getReceptionistID() { return receptionistID; }
    public Integer getLaundryStaffID() { return laundryStaffID; }
    public LocalDate getTransactionDate() { return transactionDate; }

    // call TransactionDAO to add a new order
    public static void orderLaundryService(int serviceID, int customerID, Double totalWeight, String notes) {
        TransactionDAO.orderLaundryService(serviceID, customerID, totalWeight, notes);
    }

    // call TransactionDAO to assign order to a laundry staff
    public static void assignOrderToLaundryStaff(int transactionID, int receptionistId, int laundryStaffId) {
        TransactionDAO.assignOrderToLaundryStaff(transactionID, receptionistId, laundryStaffId);
    }
    
    // call TransactionDAO to get a list of transactions with a specific laundry staff id
    public static List<Transaction> getAssignedOrdersByLaundryStaffID(int laundryStaffID) {
        return TransactionDAO.getAssignedOrdersByLaundryStaffID(laundryStaffID);
    }
    
    // call TransactionDAO to update the status of a specific transaction
    public static void updateTransactionStatus(int transactionID, String status) {
        TransactionDAO.updateTransactionStatus(transactionID, status);
    }
    
    // call TransactionDAO to get a list of transactions with a specific status    
    public static List<Transaction> getTransactionByStatus(String status){
    	return TransactionDAO.getTransactionByStatus(status);
    }
    
    // call TransactionDAO to get all transactions
    public static List<Transaction> getAllTransactions() {
    	return TransactionDAO.getAllTransactions();
    }
    
    // call TransactionDAO to get a list of transactions with a specific customer id
    public static List<Transaction> getTransactionsByCustomerID(int customerID) {
    	return TransactionDAO.getTransactionsByCustomerID(customerID);
    }

}
