package view;

import controller.ServiceController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Service;

public class EditServicePage {
	BorderPane bp = new BorderPane();
	
	private void addComponent(Stage stage, Service service) {
		// initialize labels
		Label nameLbl = new Label("Name: ");
		Label descLbl = new Label("Description: ");
		Label priceLbl = new Label("Price: ");
		Label durationLbl = new Label("Duration: ");
		
		// initialize textfields
		TextField nametf = new TextField("");
		TextField desctf = new TextField("");
		TextField pricetf = new TextField("");
		TextField durationtf = new TextField("");
		
		GridPane gp = new GridPane();
		
		// initilaize button
		Button editBtn = new Button("Edit Service");
		
		// place components in gridpane
		gp.add(nameLbl, 0, 0);
		gp.add(nametf, 1, 0);
		
		gp.add(descLbl, 0, 1);
		gp.add(desctf, 1, 1);
		
		gp.add(priceLbl, 0, 2);
		gp.add(pricetf, 1, 2);
		
		gp.add(durationLbl, 0, 3);
		gp.add(durationtf, 1, 3);
		
		// initialize back button to go back to service management page
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> stage.setScene(new ServiceManagementPage().getScene(stage)));
				
		// set the function that will happen when editBtn is clicked
		editBtn.setOnAction(e -> {
			// store the datas inputted
			String name = nametf.getText();
			String description = desctf.getText();
			
			// validate pricetf to be filled
			if(pricetf.getText().isEmpty()) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setHeaderText("Edit Service Failed");
		        alert.setContentText("Price must not be empty");
		        alert.show();
		        return;
			}
			
			// validate durationtf to be filled
			if(durationtf.getText().isEmpty()) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setHeaderText("Edit Service Failed");
		        alert.setContentText("Duration must not be empty");
		        alert.show();
		        return;
			}
			
			Double price = Double.parseDouble(pricetf.getText());
			Integer duration = Integer.parseInt(durationtf.getText());
			
			// call ServiceController to edit a service
			ServiceController.editService(service.getServiceID() ,name, description, price, duration);
		});

		gp.add(backBtn, 0, 5);
		gp.add(editBtn, 1, 5);
		
		// set gaps 
		gp.setVgap(15);
		
		// put gridpane inside borderpane
		bp.setCenter(gp);
		
		BorderPane.setAlignment(gp, Pos.CENTER);
		
		stage.setTitle("Edit Service Page");
	}
	

    public Scene getScene(Stage stage, Service service) {
        addComponent(stage, service);
        
        return new Scene(bp, 500, 500);
    }
}
