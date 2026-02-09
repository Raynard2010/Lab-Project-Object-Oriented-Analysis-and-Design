package view;

import java.time.LocalDate;

import controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Main;

public class RegisterPage {
	BorderPane bp = new BorderPane();
	
	private void addComponent(Stage stage) {
		// initialize labels
		Label nameLbl = new Label("Name: ");
		Label emailLbl = new Label("Email: ");
		Label passwordLbl = new Label("Password: ");
		Label passwordConfirmLbl = new Label("Confirm password: ");
		Label genderLbl = new Label("Gender: ");
		Label dobLbl = new Label("DOB: ");
		
		// initialize textfields
		TextField nametf = new TextField("");
		TextField emailtf = new TextField("");
		
		// initialize passwordfields for passwords
		PasswordField passwordpf = new PasswordField();
		PasswordField passwordconfirmpf = new PasswordField();
		
		GridPane gp = new GridPane();
		
		// initilaize button
		Button registerBtn = new Button("Register");
		
		// place components in gridpane
		gp.add(nameLbl, 0, 0);
		gp.add(nametf, 1, 0);
		
		gp.add(emailLbl, 0, 1);
		gp.add(emailtf, 1, 1);
		
		gp.add(passwordLbl, 0, 2);
		gp.add(passwordpf, 1, 2);
		
		gp.add(passwordConfirmLbl, 0, 3);
		gp.add(passwordconfirmpf, 1, 3);
		
		// make gender radio button
		HBox genderBox = new HBox(20);
        RadioButton maleBtn = new RadioButton("Male");
        maleBtn.setSelected(true);
        RadioButton femaleBtn = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleBtn.setToggleGroup(genderGroup);
        femaleBtn.setToggleGroup(genderGroup);
		genderBox.getChildren().add(maleBtn);
		genderBox.getChildren().add(femaleBtn);
		gp.add(genderLbl, 0, 4);
		gp.add(genderBox, 1, 4);
		
		DatePicker dobdp = new DatePicker();
		dobdp.setValue(LocalDate.now());

		// initialize datepicker
		gp.add(dobLbl, 0, 5);
		gp.add(dobdp, 1, 5);
		
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
		
		// set the function that will happen when registerBtn is clicked
		registerBtn.setOnAction(e -> {
			String name = nametf.getText();
			String email = emailtf.getText();
			String password = passwordpf.getText();
			String passwordconfirm = passwordconfirmpf.getText();
			RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
			String gender = selectedGender.getText();
			LocalDate dob = dobdp.getValue();	
			
			// call UserController to add new employee
			UserController.addUser(name, email, password, passwordconfirm, gender, dob, "Customer");
		});

		gp.add(backBtn, 0, 7);
		gp.add(registerBtn, 1, 7);		

		// set gaps 
		gp.setVgap(15);
		
		// put gridpane inside borderpane
		bp.setCenter(gp);
		
		BorderPane.setAlignment(gp, Pos.CENTER);
		
		stage.setTitle("Register Page");
	}
	
    public Scene getScene(Stage stage) {
        addComponent(stage);
        
        return new Scene(bp, 500, 500);
    }
}
