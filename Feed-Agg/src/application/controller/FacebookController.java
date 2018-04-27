package application.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Facebook was not implemented for this project.
 * @author Casey Hammond
 *
 */
public class FacebookController {
	@FXML 
	Button homeButton;
	
	/**
	 * If the user clicks the HOME button, go to the home page.
	 * @param event
	 */
	public void handle(Event event) {
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
