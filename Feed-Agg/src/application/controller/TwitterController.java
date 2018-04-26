package application.controller;

import application.model.Item;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterController {
	@FXML Button homeButton;
	@FXML TextArea userStatus;
	@FXML TableView<Status> timelineView;
//	@FXML TableColumn<Status, Double> Column; 
	@FXML TableColumn<Status, String> userColumn;
	@FXML TableColumn<Status, String> tweetColumn;
	private static Twitter usertwitter;
	private static TwitterFactory tf;
	
	
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
		//THIS WILL NOT DO ANYTHING. I am keeping my keys secret, thanks.
/*		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("QaUhgYDbP3mOQR8DZLgK9RZfa")
		  .setOAuthConsumerSecret("U58SF3hL5eKoWXoCa9AE7dR0CO25aLH8bDB2KIsUtZuNUu5Mvm")

		this.tf = new TwitterFactory(cb.build());
		this.usertwitter = tf.getInstance();
		try {
			ResponseList<Status> userHome = usertwitter.getHomeTimeline(); // Take this and display it in the status text fields.
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */
	}
}
