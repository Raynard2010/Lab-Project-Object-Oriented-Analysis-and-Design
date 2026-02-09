package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;

public class LaundryStaffPage {
    BorderPane bp = new BorderPane();
    
    // to store the data of the logged in user
    private User loggedInUser;
    
    public LaundryStaffPage(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    private void addComponent(Stage stage) {
        stage.setTitle("Laundry Staff Page");

        // initialize viewAssignedBtn to go to assigned order page
        Button viewAssignedBtn = new Button("View Assigned Orders");
        viewAssignedBtn.setOnAction(e -> {
            ViewAssignedOrderPage assignedPage = new ViewAssignedOrderPage(loggedInUser);
            assignedPage.show(stage);
        });

        bp.setCenter(viewAssignedBtn);
    }

    public Scene getScene(Stage stage) {
        addComponent(stage);
        return new Scene(bp, 500, 500);
    }
}
