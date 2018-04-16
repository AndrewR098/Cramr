package application.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TwitterController {
	@FXML Button homeButton;
	@FXML TextArea userStatus;
	@FXML Text statusOne, statusTwo, statusThree, statusFour, statusFive, statusSix, statusSeven, statusEight, statusNine, statusTen, statusEleven, statusTwelve, statusThirteen, statusFourteen;
	
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
	
	public void postTweet(){
		userStatus.setText("");
		// Need a Twitter object to post the status.
		// Should assume the user is authenticated(?)
	}
	
	public void initialize(){
		// Get the user timeline! Display the first 14 statuses we generate. Need a twitter object.
	}
	
	public void postStatus(){
		//TODO: Get the text from the textfield. Post it using Twitter4J libraries.
	}
}
