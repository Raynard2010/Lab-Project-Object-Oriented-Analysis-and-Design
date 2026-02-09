package view;

import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Service;

public class ViewOrderFormPage {

	// store the service type and the logged in user
    private Service service;
    private int customerID;

    public ViewOrderFormPage(Service service, int customerID) {
        this.service = service;
        this.customerID = customerID;
    }

    public Scene getScene(Stage stage) {
        stage.setTitle("Order Form Page");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label(
                "Order Laundry Service (Service name: " + service.getServiceName() + ")" 
        );

        // initialize textfields
        TextField weightField = new TextField();
        weightField.setPromptText("Enter laundry weight (kg)");

        TextArea notesField = new TextArea();
        notesField.setPromptText("Additional notes");

        // initialize submit button
        Button submitBtn = new Button("Submit Order");
        
        // initialize back button to go back to order service page
        Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> stage.setScene(new ViewOrderServicePage(customerID).getScene(stage)));

        submitBtn.setOnAction(e -> {
            try {
            	// store input data
                double totalWeight = Double.parseDouble(weightField.getText());
                String notes = notesField.getText();

                // call TransactionController to add new order
                TransactionController controller = new TransactionController();
                controller.orderLaundryService(service.getServiceID(), customerID, totalWeight, notes);


            } catch (Exception ex) {
            	// show message if input is invalid
                new Alert(Alert.AlertType.ERROR, "Invalid input!").show();
            }

        });

        // place components
        root.getChildren().addAll(
                title,
                new Label("Laundry Weight"), weightField,
                new Label("Notes"), notesField,
                submitBtn,
                backBtn
        );

        return new Scene(root, 600, 400);
    }
}
