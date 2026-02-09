package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.Main;
import model.User;

public class LogInPage {
	BorderPane bp = new BorderPane();
	
	private void addComponent(Stage stage) {
		// initialize labels
		Label emailLbl = new Label("Email: ");
		Label passwordLbl = new Label("Password: ");
		
		// initialize textfield and passwordfield
		TextField emailtf = new TextField("");
		PasswordField passwordpf = new PasswordField();
		
		GridPane gp = new GridPane();
		
		Button loginBtn = new Button("Log In");
		
		// initialize back button to go back to log in and register page
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> {
			try {
				new Main().start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		Label validationLbl = new Label("");
		
		loginBtn.setOnAction(e -> {
			// call UserController to process log in data and return the user 
		    User user = UserController.login(emailtf.getText(), passwordpf.getText());

		    if(user == null) {
		    	// tell user that the data is wrong
		        validationLbl.setText("Invalid data");
		        return;
		    }
		    
		    // bring user to specific pages based on their role
		    if(user.getUserRole().equals("Customer")) {
		        stage.setScene(new CustomerPage(user.getUserID()).getScene(stage));
		    }
		    else if(user.getUserRole().equals("Admin")) {
		        stage.setScene(new AdminPage().getScene(stage)); 
		    }
		    else if(user.getUserRole().equals("Laundry Staff")) {
		        stage.setScene(new LaundryStaffPage(user).getScene(stage));
		    }
		    else if(user.getUserRole().equals("Receptionist")) {
		    	 stage.setScene(new ReceptionistPage(user).getScene(stage)); 
		           
		    }
		    

		});


		
		
		gp.add(emailLbl, 0, 0);
		gp.add(emailtf, 1, 0);
		
		gp.add(passwordLbl, 0, 1);
		gp.add(passwordpf, 1, 1);
		
		gp.add(validationLbl, 0, 3);
		
		gp.add(backBtn, 0, 4);
		gp.add(loginBtn, 1, 4);
		
		gp.setVgap(15);
		
		bp.setCenter(gp);
		
		stage.setTitle("Log In Page");
	}
	
    public Scene getScene(Stage stage) {
        addComponent(stage);
        
        return new Scene(bp, 500, 500);
    }
}
