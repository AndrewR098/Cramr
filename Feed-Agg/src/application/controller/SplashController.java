package application.controller;

import java.io.IOException;
import java.io.File;
import application.Main;
import application.model.UserProfile;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * Display the splash page.
 * If the user selects LOGIN, take them to the login page.
 * If the user selects CREATE NEW, take them to the create new page.
 * 
 */
public class SplashController implements EventHandler {
	@FXML
	Button signUpButton;
	@FXML
	Button splashButton, rssTestButton;
	@FXML
	private TextField usernameField;

	/**
	 * Handle the event that the user chooses to go log in.
	 * @param event Event: button pressed
	 */
	@Override
	public void handle(Event event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	/**
	 * Handle the event that the user needs to sign up
	 * @param event Event: button pressed
	 */
	public void signUpHandle(Event event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/CreateNew.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * deprecated function.
	 * @param event
	 */
	public void handleTest(Event event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/RSSTest.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
