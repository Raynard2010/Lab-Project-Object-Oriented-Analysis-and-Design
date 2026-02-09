package view;

import controller.ServiceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Service;

public class ServiceManagementPage {
	BorderPane bp = new BorderPane();
	
	private void addComponent(Stage stage) {
		// initialize table
		TableView table;
		table = new TableView<>();
		
		// initialize table contents
		ObservableList<Service> services;
		services = FXCollections.observableArrayList(ServiceController.getAllServices());

		// make table columns
		TableColumn<Service, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
		idCol.setMinWidth(100);
		
		TableColumn<Service, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
		nameCol.setMinWidth(100);
		
		TableColumn<Service, String> descCol = new TableColumn<>("Description");
		descCol.setCellValueFactory(new PropertyValueFactory<>("serviceDescription"));
		descCol.setMinWidth(100);
		
		TableColumn<Service, Double> priceCol = new TableColumn<>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
		priceCol.setMinWidth(100);
		
		TableColumn<Service, Integer> durationCol = new TableColumn<>("Duration");
		durationCol.setCellValueFactory(new PropertyValueFactory<>("serviceDuration"));
		durationCol.setMinWidth(100);
		
		// make a column for edit button
		TableColumn<Service, Void> editCol = new TableColumn<>("Edit");
		editCol.setCellFactory(col -> new TableCell<>() {
		    Button editBtn = new Button("Edit");

		    {
		    	editBtn.setOnAction(event -> {
		    		// get the service data
		            Service service = getTableView().getItems().get(getIndex());
		            // bring user to edit service page with the service data
		            stage.setScene(new EditServicePage().getScene(stage, service));
		        });
		    }
		    	
		        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty) {
		                setGraphic(null);
		            } else {
		                setGraphic(editBtn);
		            }
		        }
		});
		
		// make a column for delete button
		TableColumn<Service, Void> deleteCol = new TableColumn<>("Delete");
		deleteCol.setCellFactory(col -> new TableCell<>() {
		    Button deleteBtn = new Button("Delete");

		    {
		    	deleteBtn.setOnAction(event -> {
		    		// get the service data
		            Service service = getTableView().getItems().get(getIndex());
		            // call ServiceController to delete the service
		    		ServiceController.deleteService(service.getServiceID());
		    		
		    		// refresh the table
		    		services.setAll(ServiceController.getAllServices());
		    		table.setItems(services);
		    		
		        });
		    }
		    	
		        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty) {
		                setGraphic(null);
		            } else {
		                setGraphic(deleteBtn);
		            }
		        }
		});
		
		table.getColumns().addAll(idCol, nameCol, descCol, priceCol, durationCol, editCol, deleteCol);
		table.setMaxHeight(500);

		// set the content of the table
		table.setItems(services);
		
		bp.setTop(table);
		
		// initialized addServiceBtn to bring user to add service page
		Button addServiceBtn = new Button("Add New Service");
		addServiceBtn.setOnAction(e -> stage.setScene(new AddServicePage().getScene(stage)));
		
		HBox hbox = new HBox();
		
		hbox.getChildren().add(addServiceBtn);
		
		bp.setCenter(hbox);
		
		stage.setTitle("Service Management Page");
	}
	

    public Scene getScene(Stage stage) {
        addComponent(stage);
        
        return new Scene(bp);
    }
}
