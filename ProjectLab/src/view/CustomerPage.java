package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerPage {

    private VBox root;
    
    // to store the id of the logged in user
    private int customerID;

    public CustomerPage(int customerID) {
        this.customerID = customerID;
        root = new VBox(15);
        root.setPadding(new Insets(20));
    }

    private void addComponent(Stage stage) {
        stage.setTitle("Customer Page");
        
		// initialize orderServiceBtn to go to order service page
        Button orderServiceBtn = new Button("Order Laundry Service");
        
		// initialize notificationsBtn to go to all notifications page
        Button notificationsBtn = new Button("Notifications");
        
        // initialize transactionHistoryBtn to go to transaction history page
        Button transactionHistoryBtn = new Button("View Transaction History");
        
        orderServiceBtn.setOnAction(e -> {
            stage.setScene(new ViewOrderServicePage(customerID).getScene(stage));
        });
        
        notificationsBtn.setOnAction(e -> {
        	stage.setScene(new ViewAllNotifications(customerID).getScene(stage));
        });
        
        transactionHistoryBtn.setOnAction(e -> {
        	stage.setScene(new ViewTransactionHistoryPage(customerID).getScene(stage));
        });
        
        root.getChildren().addAll(orderServiceBtn, notificationsBtn, transactionHistoryBtn);
    }

    public Scene getScene(Stage stage) {
        addComponent(stage);
        return new Scene(root, 500, 500);
    }
}
