package view;

import java.util.List;

import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

import model.Transaction;
import model.User;

public class ViewPendingTransactionPage {
	
	// store the logged in user
    private User loggedInUser;

    public ViewPendingTransactionPage(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void show(Stage stage) {

    	// initialize table
        TableView<Transaction> table = new TableView<>();

        // make table column
        TableColumn<Transaction, Integer> colID = new TableColumn<>("Transaction ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));

        TableColumn<Transaction, Integer> colServiceID = new TableColumn<>("Service ID");
        colServiceID.setCellValueFactory(new PropertyValueFactory<>("serviceID"));


        TableColumn<Transaction, Integer> colCustomerID = new TableColumn<>("Customer ID");
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        
        TableColumn<Transaction, Integer> colReceptionistID = new TableColumn<>("Receptionist ID");
        colReceptionistID.setCellValueFactory(new PropertyValueFactory<>("receptionistID"));

        
        TableColumn<Transaction, Integer> colStaffID = new TableColumn<>("Laundry Staff ID");
        colStaffID.setCellValueFactory(new PropertyValueFactory<>("laundryStaffID"));


        TableColumn<Transaction, String> colDate = new TableColumn<>("Transaction Date");
        colDate.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getTransactionDate().toString())
        );

        TableColumn<Transaction, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getTransactionStatus())
        );

        TableColumn<Transaction, Double> colWeight = new TableColumn<>("Weight (kg)");
        colWeight.setCellValueFactory(new PropertyValueFactory<>("totalWeight"));


        TableColumn<Transaction, String> colNotes = new TableColumn<>("Notes");
        colNotes.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getTransactionNotes())
        );

        table.getColumns().addAll(
            colID, colServiceID, colCustomerID, colReceptionistID, colStaffID,
            colDate, colStatus, colWeight, colNotes
        );

        // call TransactionController to get all pending transactions and add them to the table
        List<Transaction> list = TransactionController.getTransactionByStatus("Pending");
        table.getItems().addAll(list);

        // initialize assign order button
        Button assignBtn = new Button("Assign Order");
        assignBtn.setOnAction(e -> {
            Transaction selectedTransaction = table.getSelectionModel().getSelectedItem();
            if (selectedTransaction == null) {
            	// if no transaction is selected
                System.out.println("Please select a transaction first!");
                return;
            }
            
            // bring user to assign order page
            AssignOrderPage assignPage = new AssignOrderPage(selectedTransaction.getTransactionID(), loggedInUser);
            assignPage.show(stage);
        });

        HBox bottom = new HBox(assignBtn);
        bottom.setPadding(new Insets(10));
        bottom.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(bottom);

        Scene scene = new Scene(root, 900, 500);
        stage.setScene(scene);
        stage.setTitle("Pending Transactions");
        stage.show();
    }
}
