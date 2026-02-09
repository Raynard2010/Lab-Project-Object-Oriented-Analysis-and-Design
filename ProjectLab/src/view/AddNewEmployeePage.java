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

public class AddNewEmployeePage {
	BorderPane bp = new BorderPane();
	
	private void addComponent(Stage stage) {
		// initialize labels
		Label nameLbl = new Label("Name: ");
		Label emailLbl = new Label("Email: ");
		Label passwordLbl = new Label("Password: ");
		Label passwordConfirmLbl = new Label("Confirm password: ");
		Label genderLbl = new Label("Gender: ");
		Label dobLbl = new Label("DOB: ");
		Label roleLbl = new Label("Role: ");
		
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
		
		// initialize datepicker
		DatePicker dobdp = new DatePicker();
		dobdp.setValue(LocalDate.now());

		gp.add(dobLbl, 0, 5);
		gp.add(dobdp, 1, 5);
		
		// make role radio button
		HBox roleBox = new HBox(20);
        RadioButton adminBtn = new RadioButton("Admin");
        adminBtn.setSelected(true);
        RadioButton staffButton = new RadioButton("Laundry Staff");
        RadioButton recepButton = new RadioButton("Receptionist");
        ToggleGroup roleGroup = new ToggleGroup();
        adminBtn.setToggleGroup(roleGroup);
        staffButton.setToggleGroup(roleGroup);
        recepButton.setToggleGroup(roleGroup);
        roleBox.getChildren().add(adminBtn);
        roleBox.getChildren().add(staffButton);
        roleBox.getChildren().add(recepButton);
		gp.add(roleLbl, 0, 6);
		gp.add(roleBox, 1, 6);
		
		// initialize back button to go back to employee management page
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> stage.setScene(new EmployeeManagementPage().getScene(stage)));
		
		// set the function that will happen when registerBtn is clicked
		registerBtn.setOnAction(e -> {
			// store the datas inputted
			String name = nametf.getText();
			String email = emailtf.getText();
			String password = passwordpf.getText();
			String passwordconfirm = passwordconfirmpf.getText();
			RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
			String gender = selectedGender.getText();
			LocalDate dob = dobdp.getValue();	
			RadioButton selectedRole = (RadioButton) roleGroup.getSelectedToggle();
			String role = selectedRole.getText();
			
			// call UserController to add new employee
			UserController.addEmployee(name, email, password, passwordconfirm, gender, dob, role);
		});

		gp.add(backBtn, 0, 8);
		gp.add(registerBtn, 1, 8);
		
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
