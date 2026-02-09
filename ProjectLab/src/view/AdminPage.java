package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminPage {
	BorderPane bp = new BorderPane();
	
	private void addComponent(Stage stage) {
		GridPane gp = new GridPane();
		
		// initialize serviceBtn to go to service management page
		Button serviceBtn = new Button("Service Management");
		serviceBtn.setOnAction(e -> stage.setScene(new ServiceManagementPage().getScene(stage)));
		
		// initialize employeeBtn to go to employee management page
		Button employeeBtn = new Button("Employee Management");
		employeeBtn.setOnAction(e -> stage.setScene(new EmployeeManagementPage().getScene(stage)));
		
		// initialize viewAllTransactionsBtn to go to view all transactions page
		Button viewAllTransactionsBtn = new Button("View All Transactions");
		viewAllTransactionsBtn.setOnAction(e -> {
			ViewAllTransactionsPage page = new ViewAllTransactionsPage();
			page.show(stage);
		});
				
		gp.add(serviceBtn, 0, 0);
		gp.add(employeeBtn, 0, 1);
		gp.add(viewAllTransactionsBtn, 0, 2);
		
		// set gaps 
		gp.setVgap(15);
		
		// put gridpane inside borderpane
		bp.setCenter(gp);
		
		stage.setTitle("Admin Page");
	}
	

    public Scene getScene(Stage stage) {
        addComponent(stage);
        
        return new Scene(bp, 500, 500);
    }
}
