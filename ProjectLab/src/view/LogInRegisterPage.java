package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogInRegisterPage {
	
	public BorderPane bp = new BorderPane();

	public LogInRegisterPage(Stage stage) {
		Label nameLbl = new Label("GoVlash Laundry");
		Button registerBtn = new Button("Register");
		Button loginBtn = new Button("Log in");
		
		// initialize register and log in button
		// register button brings user to register page
		// log in button brings user to log in page
		registerBtn.setOnAction(e -> stage.setScene(new RegisterPage().getScene(stage)));
		loginBtn.setOnAction(e -> stage.setScene(new LogInPage().getScene(stage)));
	
		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(
				nameLbl,
				loginBtn,
				registerBtn
		);
		
		bp.setCenter(vbox);
	
		vbox.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(vbox, Pos.CENTER);	
	}
}
