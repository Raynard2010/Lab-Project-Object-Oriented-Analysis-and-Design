package view;

import controller.NotificationController;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Notification;

public class ViewAllNotifications {
BorderPane bp = new BorderPane();
	
// store logged in user's id
int customerID;


		public ViewAllNotifications(int customerID) {
		    this.customerID = customerID;
		}
	
	public void addComponent(Stage stage) {
		// initialized back button to go back to admin page
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> stage.setScene(new AdminPage().getScene(stage)));
		
		// initialized table
		TableView<Notification> table = new TableView<Notification>();
		// initialized table content
		ObservableList<Notification> notifications;
		notifications = FXCollections.observableArrayList(NotificationController.getNotificationsByRecipientID(customerID));
		
		// make table columns
		TableColumn<Notification, Number> notificationIdCol = new TableColumn<>("Notification Id");
		notificationIdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNotificationID()));
		
		TableColumn<Notification, Number> recipientIdCol = new TableColumn<>("Recipient Id");
		recipientIdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getRecipientID()));
		
		TableColumn<Notification, String> messageCol = new TableColumn<>("Message");
		messageCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMessage()));
		
		TableColumn<Notification, Boolean> isReadCol = new TableColumn<>("Is Read?");
		isReadCol.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().getIsRead()));
		
		// make a column for open button
		TableColumn<Notification, Void> openCol = new TableColumn<>("Open");
		openCol.setCellFactory(col -> new TableCell<>() {
		    Button openBtn = new Button("Open");

		    {
		    	openBtn.setOnAction(event -> {
		    		// get the notification data
		            Notification notification = getTableView().getItems().get(getIndex());
		            // get the id of the notification
		            int notificationID = notification.getNotificationID();
		            // go to the notification detail page of the chosen notification
		            stage.setScene(new NotificationDetailPage().getScene(stage, NotificationController.getNotificationByID(notificationID), customerID));
		        });
		    }
		    	
		        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty) {
		                setGraphic(null);
		            } else {
		                setGraphic(openBtn);
		            }
		        }
		});
		
		table.getColumns().addAll(notificationIdCol, recipientIdCol, messageCol, isReadCol, openCol);
		
		table.setItems(notifications);
		
		bp.setCenter(new VBox(table));
	}
	
	public Scene getScene(Stage stage) {
        addComponent(stage);
        
        return new Scene(bp, 500, 500);
    }
}
