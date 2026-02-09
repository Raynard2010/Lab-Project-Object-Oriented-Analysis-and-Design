package view;

import java.time.LocalDate;

import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Service;
import model.User;

public class EmployeeManagementPage {
	BorderPane bp = new BorderPane();
	
	private void addComponent(Stage stage) {
		// initialize table
		TableView table;
		table = new TableView<>();
		
		// initialize table contents
		ObservableList<User> users;
		users = FXCollections.observableArrayList(UserController.getAllEmployees());
		
		// make table columns
		TableColumn<Service, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
		idCol.setMinWidth(100);
		
		TableColumn<Service, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
		nameCol.setMinWidth(100);
		
		TableColumn<Service, String> emailCol = new TableColumn<>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
		emailCol.setMinWidth(100);
		
		TableColumn<Service, String> passwordCol = new TableColumn<>("Password");
		passwordCol.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
		passwordCol.setMinWidth(100);
		
		TableColumn<Service, String> genderCol = new TableColumn<>("Gender");
		genderCol.setCellValueFactory(new PropertyValueFactory<>("userGender"));
		genderCol.setMinWidth(100);
		
		TableColumn<Service, LocalDate> dobCol = new TableColumn<>("DOB");
		dobCol.setCellValueFactory(new PropertyValueFactory<>("userDOB"));
		dobCol.setMinWidth(100);

		TableColumn<Service, String> roleCol = new TableColumn<>("Role");
		roleCol.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		roleCol.setMinWidth(100);
		
		table.getColumns().addAll(idCol, nameCol, emailCol, passwordCol, genderCol, dobCol, roleCol);
		table.setMaxHeight(500);
		
		// set the content of the table
		table.setItems(users);
		
		bp.setTop(table);
		
		// initialize addEmployeeBtn to go back to service management page
		Button addEmployeeBtn = new Button("Add New Employee");
		addEmployeeBtn.setOnAction(e -> stage.setScene(new AddNewEmployeePage().getScene(stage)));
		
		HBox hbox = new HBox();
		
		// put addEmployeeBtn at the bottom
		hbox.getChildren().add(addEmployeeBtn);
		bp.setCenter(hbox);
		
		stage.setTitle("Employee Management Page");
	}
	

    public Scene getScene(Stage stage) {
        addComponent(stage);
        
        return new Scene(bp);
    }
}
