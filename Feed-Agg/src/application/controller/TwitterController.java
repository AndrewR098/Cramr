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
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterController {
	@FXML Button homeButton;
	@FXML TextArea userStatus;
	@FXML Text statusOne, statusTwo, statusThree, statusFour, statusFive, statusSix, statusSeven, statusEight, statusNine, statusTen, statusEleven, statusTwelve, statusThirteen, statusFourteen;
	private static Twitter usertwitter = new TwitterFactory().getInstance();
	
	
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
		String toPost = userStatus.getText();
		userStatus.setText("");
		try {
			Status status = usertwitter.updateStatus(toPost);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Should assume the user is authenticated(?)
	}
	
	public void initialize(){
		try {
			ResponseList<Status> userHome = usertwitter.getHomeTimeline(); // Take this and display it in the status text fields.
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
