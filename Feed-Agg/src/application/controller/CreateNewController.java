package application.controller;

import java.io.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Create new Cramr accounts.
 * @author Casey Hammond
 *
 */
public class CreateNewController {
	
	@FXML 
	Button homeButton;
	@FXML
	TextField fieldEmailOrUser;
	@FXML
	PasswordField fieldPassword;
	@FXML
	PasswordField fieldConfirmPassword;
	@FXML
	Button buttonCreateNew;
	
	/**
	 * If the user enters information correctly, add it to the users.txt file for later logins.
	 * @param event
	 */
	public void handle(Event event) {
		// if the passwords match
		if ( fieldPassword.getText().equals( fieldConfirmPassword.getText() ) ) {
			// if the fields are not blank
			if (fieldEmailOrUser.getText().isEmpty() || fieldPassword.getText().isEmpty() ) {
				System.out.println("Please fill in all fields.\n");
			} else { //create a new user
				BufferedWriter bw = null;
				FileWriter fw = null;
				try {
					String data = "\n" + fieldEmailOrUser.getText() + "," + fieldPassword.getText();
					
					File file = new File("users.txt");
					
					// if file does not exist, create the file.
					if (!file.exists()) {
						file.createNewFile();
					}
					fw = new FileWriter(file.getAbsoluteFile(), true);
					bw = new BufferedWriter(fw);
					
					bw.write(data);
					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (bw != null) {
							bw.close();
						}
						if (fw != null) {
							fw.close();
						}
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/Splash.fxml"));
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(new Scene(root));
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
			// users passwords didn't match.
			System.out.println("Passwords do not match, please try again.\n");
		}
	}
}
