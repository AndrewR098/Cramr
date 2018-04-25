package application.controller;
import java.io.File;
import java.io.IOException;

import application.model.UserProfile;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/*
 * The LoginController Class handles Login event, displaying appropriate fxml
 * @author Samuel Carey (ial812)
 */
import javafx.stage.Stage;

public class LoginController implements EventHandler{
	
	@FXML TextField userLogin = new TextField();
	@FXML PasswordField userPass = new PasswordField();
	@FXML Button loginButton = new Button();
	
	public static UserProfile currentUser = null;
	
	/**
	 * Handles login functions. If login is correct, it'll bring you to the homepage.
	 * If not, it will pop up saying it's incorrect.
	 */
	@Override
	public void handle(Event event) {
		//incorrect credentials, needs to do something
		if(UserProfile.authenticate(userLogin.getText(),userPass.getText()) == null){
			System.out.println("INCORRECT LOGIN");
			Alert wrongLoginAlert = new Alert(AlertType.ERROR, "Password or Username is incorrect.", ButtonType.CLOSE);
			wrongLoginAlert.showAndWait();
			//here we deal with bad stuff
		} else {
			
			currentUser = new UserProfile( userLogin.getText(),userPass.getText() );
			String filename = "subreddits" + LoginController.currentUser.user + ".txt";
			File subredditsFile = new File(filename);
			if (!subredditsFile.exists()) {
				try {
					subredditsFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else{
				
			}
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Homepage.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			
			}

		}

	}
}
