package application.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddSocialController {

		@FXML 
		Button homeButton;
		@FXML
		TextField twitterUsernameText;
		@FXML
		PasswordField twitterPasswordText;
		@FXML
		Button twitterLoginButton;
		@FXML
		TextField subredditText;
		@FXML
		Button addSubredditButton;
		@FXML
		TextField facebookEmailText;
		@FXML
		PasswordField facebookPasswordText;
		@FXML
		Button facebookLoginButton;
		
		public void handle(Event event) {
			Button pressedButton = (Button) event.getSource();
			if (pressedButton == homeButton) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/Homepage.fxml"));
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(new Scene(root));
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if (pressedButton == twitterLoginButton) {
				if (twitterUsernameText.getText() == null || twitterUsernameText.getText() == "") {
					System.out.println("Username field is blank");
				} else if (twitterPasswordText.getText() == null || twitterPasswordText.getText() == "") {
					System.out.println("Password field is blank");
				} else {
					//create a twitter object for the currentUser
				}
			}
			if (pressedButton == facebookLoginButton) {
				if (facebookEmailText.getText() == null || facebookEmailText.getText() == "") {
					System.out.println("Email field is blank");
				} else if (facebookPasswordText.getText() == null || facebookPasswordText.getText() == "") {
					System.out.println("Password field is blank");
				} else {
					//create a facebook object for the currentUser
				}
			}
			if (pressedButton == addSubredditButton) {
				if (subredditText.getText() == null || subredditText.getText() == "") {
					System.out.println("subreddit field is blank");
				} else {
					//add subreddit to users list of subreddits
					LoginController.currentUser.addSubreddit(subredditText.getText());
				}
			}
		}
	}


