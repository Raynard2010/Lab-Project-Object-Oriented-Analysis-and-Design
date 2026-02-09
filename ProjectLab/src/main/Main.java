package main;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Connect;
import view.LogInRegisterPage;

public class Main extends Application{
	
	// singleton database
	public static Connect connect = Connect.getInstance();
	
	// to make and show the beginning page, which is log in and register page
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		init();
		
		LogInRegisterPage view = new LogInRegisterPage(primaryStage);
		
		// to prepare and show the page
		Scene scene = new Scene(view.bp, 500, 500);
		primaryStage.setTitle("Log In & Registration Page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
