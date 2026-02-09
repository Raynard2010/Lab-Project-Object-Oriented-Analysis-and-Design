package view;

import controller.NotificationController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Notification;

public class NotificationDetailPage {
	BorderPane bp = new BorderPane();
	
	private void addComponent(Stage stage, Notification notification, int customerID) {
		// initialize labels with the selected notification data
		Label timeLbl = new Label(notification.getDateCreated().toString());
		Label detailLbl = new Label(notification.getMessage());
		
		GridPane gp = new GridPane();
		
		// initialized back button to go back to all notifications page
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> stage.setScene(new ViewAllNotifications(customerID).getScene(stage)));
		
		// initialized delete button to delete the notification
		Button delBtn = new Button("Delete");
		delBtn.setOnAction(e ->{
			// call NotificationController to delete the notification
			NotificationController.deleteNotification(notification.getNotificationID());
			// bring user back to all notifications page after the notification has been deleted
			stage.setScene(new ViewAllNotifications(customerID).getScene(stage));
		});	
		
		gp.add(timeLbl, 0, 0);
		gp.add(detailLbl, 0, 1);
		gp.add(backBtn, 0, 2);
		gp.add(delBtn, 1, 2);

		// set gaps 
		gp.setVgap(15);
		
		// put gridpane inside borderpane
		bp.setCenter(gp);
		
		BorderPane.setAlignment(gp, Pos.CENTER);
		
		stage.setTitle("Notification Detail Page");
	}

    public Scene getScene(Stage stage, Notification notification, int customerID) {
        addComponent(stage, notification, customerID);
        
        return new Scene(bp, 500, 500);
    }
}
