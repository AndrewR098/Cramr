package application.controller;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.UserProfile;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
/*
 * The LoginController Class handles Login event, displaying appropriate fxml
 * @author Samuel Carey (ial812)
 */
import javafx.stage.Stage;

public class LoginController{	
	@FXML TextField userLogin = new TextField();
	@FXML PasswordField userPass = new PasswordField();
	@FXML Button loginButton = new Button();
	public void handle(Event event) {
		//incorrect credentials, display Restricted
		if(UserProfile.authenticate(userLogin.getText(),userPass.getText()) == null){
			System.out.println("aye boi");
		/*	
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Restricted.fxml"));
				Scene scene = new Scene(root,1000,700);
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
			*/
		} else {
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