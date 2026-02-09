package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;

public class ReceptionistPage {

    private BorderPane bp = new BorderPane();
    
    // store the logged in user
    private User loggedInUser; 

    public ReceptionistPage(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    private void addComponent(Stage stage) {
        stage.setTitle("Receptionist Page");
        
        // initialized openPendingTransactionBtn that will bring user to pending transaction page
        Button openPendingTransactionBtn = new Button("Open Pending Transactions");
        openPendingTransactionBtn.setOnAction(e -> {
            ViewPendingTransactionPage page = new ViewPendingTransactionPage(loggedInUser);
            page.show(stage);
        });

        bp.setCenter(openPendingTransactionBtn);
    }

    public Scene getScene(Stage stage) {
        addComponent(stage);
        return new Scene(bp, 500, 500);
    }
}
