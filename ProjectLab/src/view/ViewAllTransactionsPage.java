package view;

import controller.NotificationController;
import controller.TransactionController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Transaction;

public class ViewAllTransactionsPage {
	
	public void show(Stage stage) {
		// initialize table
		TableView<Transaction> table = new TableView<>();
		
		// make table columns
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
		colDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTransactionDate().toString()));
		
		TableColumn<Transaction, String> colStatus = new TableColumn<>("Status");
		colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTransactionStatus()));
		
		TableColumn<Transaction, Double> colWeight = new TableColumn<>("Weight (kg)");
		colWeight.setCellValueFactory(new PropertyValueFactory<>("totalWeight"));
		
		TableColumn<Transaction, String> colNotes = new TableColumn<>("Notes");
		colNotes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTransactionNotes()));
		
		// make a column for send notification button
		TableColumn<Transaction, Void> sendNotifCol = new TableColumn<>("Send notification");
		sendNotifCol.setCellFactory(col -> new TableCell<>(){
		    Button sendNotifBtn = new Button("Send notification");

		    {
		    	sendNotifBtn.setOnAction(event -> {
		    		// get the transaction data
		    		Transaction transaction = getTableView().getItems().get(getIndex());	
		    		// get the customer id of the transaction
		    		int recipientID = transaction.getCustomerID();
		    		// call NotificationController to send notification to the customer
		            NotificationController.sendNotification(recipientID, "Your order is finished and ready for pickup. Thank you for choosing our service!");
		        });
		    }
		    	
		        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty) {
		                setGraphic(null);
		            } else {
		                setGraphic(sendNotifBtn);
		            }
		        }
		});
		
		table.getColumns().addAll(colID, colServiceID, colCustomerID, colReceptionistID, colStaffID, colDate, colStatus, colWeight, colNotes);
		
		// get the table contents
		ObservableList<Transaction> list = FXCollections.observableArrayList(TransactionController.getAllTransactions());
		table.setItems(list);
		
		// sort the transactions from newest to oldest
		table.getSortOrder().add(colID);
		colID.setSortType(TableColumn.SortType.DESCENDING);

		// initialize back button to go back to admin page
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> stage.setScene(new AdminPage().getScene(stage)));
		
		// initliaze finished button, to only show finished transcations
		Button finishedBtn = new Button("Finished");
		finishedBtn.setOnAction(e -> {
			// get finished transcation data
			ObservableList<Transaction> finishedList = FXCollections.observableArrayList(TransactionController.getTransactionByStatus("Finished"));
			
			// add sendNotifCol when viewing finished transactions
			table.getColumns().addAll(sendNotifCol);
			
			// sort transactions from newest to oldest
			table.setItems(finishedList);
			table.getSortOrder().add(colID);
			colID.setSortType(TableColumn.SortType.DESCENDING);
			
		});
		
		HBox bottom = new HBox(backBtn, finishedBtn);
		bottom.setPadding(new Insets(10));
		bottom.setSpacing(10);
		
		BorderPane root = new BorderPane();
		root.setCenter(table);
		root.setBottom(bottom);
		
		Scene scene = new Scene(root, 900, 500);
		stage.setScene(scene);
		stage.setTitle("All Transactions");
		stage.show();
		
		
	}
}
