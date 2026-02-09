package view;

import java.util.List;
import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Transaction;
import model.User;

public class ViewAssignedOrderPage {

	// store logged in user
    private User loggedInUser;

    public ViewAssignedOrderPage(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void show(Stage stage) {
    	// initialize table
        TableView<Transaction> table = new TableView<>();

        // make table column
        TableColumn<Transaction, Number> colID = new TableColumn<>("Transaction ID");
        colID.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getTransactionID()));

        TableColumn<Transaction, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTransactionStatus()));

        TableColumn<Transaction, Number> colWeight = new TableColumn<>("Weight (kg)");
        colWeight.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTotalWeight()));

        table.getColumns().addAll(colID, colStatus, colWeight);

        TransactionController controller = new TransactionController();
        // call TransactionController to get orders assigned to a laundry staff
        List<Transaction> list = controller.getAssignedOrdersByLaundryStaffID(loggedInUser.getUserID());
        table.getItems().addAll(list);

        // make column for mark as finished button
        Button finishBtn = new Button("Mark as Finished");
        finishBtn.setOnAction(e -> {
        	// store the selected transaction
            Transaction selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            
            // call TransactionController to update the status of the transaction to finished
            controller.updateTransactionStatus(selected.getTransactionID(), "Finished");

            table.getItems().remove(selected);
        });

        HBox bottom = new HBox(finishBtn);
        bottom.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(bottom);

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Assigned Orders");
        stage.show();
    }
}
