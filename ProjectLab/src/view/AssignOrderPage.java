package view;

import controller.TransactionController;
import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class AssignOrderPage {

	// to store the transaction id and the user logged in
    private int transactionID;
    private User loggedInUser;

    public AssignOrderPage(int transactionID, User loggedInUser) {
        this.transactionID = transactionID;
        this.loggedInUser = loggedInUser;
    }

    public void show(Stage stage) {
        Label title = new Label("Assign Laundry Staff");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // initialize table
		TableView table;
		table = new TableView<>();
		// initialize table content
		ObservableList<User> staffs;
		// get all laundry staffs
		staffs = FXCollections.observableArrayList(UserController.getUsersByRole("Laundry Staff"));
		
		// make table columns
		TableColumn<User, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
		idCol.setMinWidth(100);
		
		TableColumn<User, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
		nameCol.setMinWidth(100);
		
		// make a column for assign button
		TableColumn<User, Void> assignCol = new TableColumn<>("Assign");
		assignCol.setCellFactory(col -> new TableCell<>() {
		    Button assignBtn = new Button("Assign");
		    {
		    	assignBtn.setOnAction(event -> {
		    		// get the staff data
		            User staff = getTableView().getItems().get(getIndex());
		            // call TransactionController to assign the order to the saff
		            TransactionController.assignOrderToLaundryStaff(transactionID, loggedInUser.getUserID(), staff.getUserID());
		            // return to view pending transaction page
		            ViewPendingTransactionPage page = new ViewPendingTransactionPage(loggedInUser);
		            page.show(stage);
		    	});
		    

		    }
		    	
		        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty) {
		                setGraphic(null);
		            } else {
		                setGraphic(assignBtn);
		            }
		        }
		});
		
		// insert data to table
		table.getColumns().addAll(idCol, nameCol, assignCol);
		table.setItems(staffs);

        VBox root = new VBox(15, title, table);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        stage.setScene(new Scene(root, 450, 300));
        stage.setTitle("Assign Order Page");
        stage.show();
    }
}
