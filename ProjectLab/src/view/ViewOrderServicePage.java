package view;

import controller.ServiceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Service;

public class ViewOrderServicePage {

    private int customerID;

    public ViewOrderServicePage(int customerID) {
        this.customerID = customerID;
    }

    public Scene getScene(Stage stage) {
        stage.setTitle("Order Service Page");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        root.getChildren().add(new Label("Available Laundry Services"));
        
        // initialize table
		TableView table;
		table = new TableView<>();
		
		// initialize table content
		ObservableList<Service> services;
		services = FXCollections.observableArrayList(ServiceController.getAllServices());
		
		// make table column
		TableColumn<Service, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
		idCol.setMinWidth(100);
		
		TableColumn<Service, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
		nameCol.setMinWidth(100);
		
		TableColumn<Service, String> descriptionCol = new TableColumn<>("Description");
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("serviceDescription"));
		descriptionCol.setMinWidth(100);
		
		TableColumn<Service, Double> priceCol = new TableColumn<>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
		priceCol.setMinWidth(100);
		
		TableColumn<Service, Integer> durationCol = new TableColumn<>("Duration");
		durationCol.setCellValueFactory(new PropertyValueFactory<>("serviceDuration"));
		durationCol.setMinWidth(100);
		
		// make column for order button
		TableColumn<Service, Void> orderCol = new TableColumn<>("Order");
		orderCol.setCellFactory(col -> new TableCell<>() {
		    Button orderBtn = new Button("Order");

		    {
		    	orderBtn.setOnAction(event -> {
		    		// get selected service
		            Service service = getTableView().getItems().get(getIndex());
		            // bring user to order form page
		            stage.setScene(new ViewOrderFormPage(service, customerID).getScene(stage));
		        });
		    }
		    	
		        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty) {
		                setGraphic(null);
		            } else {
		                setGraphic(orderBtn);
		            }
		        }
		});
	
		
		table.getColumns().addAll(idCol, nameCol, descriptionCol, priceCol, durationCol, orderCol);
		table.setMaxHeight(500);
		
		// set the contents of the table
		table.setItems(services);
		
		root.setTop(table);

        return new Scene(root, 600, 500);
    }
}
