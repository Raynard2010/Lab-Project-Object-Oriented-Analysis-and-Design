package view;

import java.util.List;

import controller.TransactionController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Transaction;

public class ViewTransactionHistoryPage {
	
	// store logged in user
	private int customerID;
	
	public ViewTransactionHistoryPage(int customerID) {
		this.customerID = customerID;
	}
	
	public Scene getScene(Stage stage) {
		stage.setTitle("Transaction History");
		
		// initialize table
		TableView<Transaction> table = new TableView<>();
		
		// make table column
		TableColumn<Transaction, Integer> colID = new TableColumn<>("Transaction ID");
		colID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		
		TableColumn<Transaction, Integer> colServiceID = new TableColumn<>("Service ID");
		colServiceID.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
		
		TableColumn<Transaction, String> colDate = new TableColumn<>("Transaction Date");
		colDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTransactionDate().toString()));
		
		TableColumn<Transaction, String> colStatus = new TableColumn<>("Status");
		colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTransactionStatus()));
		
		TableColumn<Transaction, Double> colWeight = new TableColumn<>("Weight (kg)");
		colWeight.setCellValueFactory(new PropertyValueFactory<>("totalWeight"));
		
		TableColumn<Transaction, String> colNotes = new TableColumn<>("Notes");
		colNotes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTransactionNotes()));
		
		table.getColumns().addAll(colID, colServiceID, colDate, colStatus, colWeight, colNotes);
		
		// call TransactionController to get all transactions of a specific customer
		List<Transaction> list = TransactionController.getTransactionsByCustomerID(customerID);
		table.getItems().addAll(list);
		
		// initialize back button to bring user back to customer page
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> stage.setScene(new CustomerPage(customerID).getScene(stage)));
		
		HBox bottom = new HBox(backBtn);
		bottom.setPadding(new Insets(10));
		bottom.setSpacing(10);
		
		BorderPane root = new BorderPane();
		root.setCenter(table);
		root.setBottom(bottom);
		
		return new Scene(root, 900, 500);
	}
}
