package application.controller;

import application.controller.LoginController;
import java.io.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
		Button removeSubredditButton;
		@FXML
		TextField facebookEmailText;
		@FXML
		PasswordField facebookPasswordText;
		@FXML
		Button facebookLoginButton;
		@FXML
		Text redditActionText;
		public static boolean added, removed;
		
		public void handle(Event event) throws IOException {
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
			if (pressedButton == addSubredditButton) {
				if (subredditText.getText() == null || subredditText.getText() == "") {
					System.out.println("subreddit field is blank");
				} else {
					//add subreddit to users list of subreddits
					LoginController.currentUser.addSubreddit(subredditText.getText());
					subredditText.setText("");
					if (added) {
						redditActionText.setText("Subreddit Added");
					} else {
						redditActionText.setText("");
					}
				}
			}
			if (pressedButton == removeSubredditButton) {
				if (subredditText.getText() == null || subredditText.getText() == "") {
					System.out.println("subreddit field is blank");
				} else {
					LoginController.currentUser.removeSubreddit(subredditText.getText());
					subredditText.setText("");
					if (removed) {
						redditActionText.setText("Subreddit Removed");
					} else {
						redditActionText.setText("");
					}
				}
			}
		}
	}


