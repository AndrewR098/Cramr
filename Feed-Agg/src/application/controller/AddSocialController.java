package application.controller;

import application.controller.LoginController;
import java.io.*;

import javax.swing.JOptionPane;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;


public class AddSocialController {

		@FXML 
		Button homeButton;
		@FXML
		TextField twitterUsernameText, pinField;
		@FXML
		Button twitterSubmit;
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
		@FXML
		TextField urlTwitter;
		public static boolean added, removed;
		public static RequestToken requestToken;
		
		public void initialize(){
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey("QaUhgYDbP3mOQR8DZLgK9RZfa")
			  .setOAuthConsumerSecret("U58SF3hL5eKoWXoCa9AE7dR0CO25aLH8bDB2KIsUtZuNUu5Mvm");
			TwitterFactory temp = new TwitterFactory(cb.build());
			TwitterController.setTf(temp);
			TwitterController.setUsertwitter(temp.getInstance());
			try {
				requestToken = TwitterController.getUsertwitter().getOAuthRequestToken();
				System.out.println("Request token: " + requestToken.getToken());
				System.out.println("Request token secret: " + requestToken.getTokenSecret());
				urlTwitter.setText(requestToken.getAuthorizationURL());
			} catch (TwitterException e){
				e.printStackTrace();
			}
			
		}
		
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
		
		public void finishTwitterAuth(){
			String pin = pinField.getText();
			try {
                if (pin.length() > 0) {
    				JOptionPane.showMessageDialog(null, "This Twitter Account has been added to your feeds");
                    TwitterController.setUserToken(TwitterController.getUsertwitter().getOAuthAccessToken(requestToken, pin));
                } else {
                	JOptionPane.showMessageDialog(null, "This Twitter Account has been added to your feeds");
                	TwitterController.setUserToken(TwitterController.getUsertwitter().getOAuthAccessToken(requestToken));
                }
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()) {
                    System.out.println("Unable to get the access token.");
                } else {
                    te.printStackTrace();
                }
            }

			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey("QaUhgYDbP3mOQR8DZLgK9RZfa")
			  .setOAuthConsumerSecret("U58SF3hL5eKoWXoCa9AE7dR0CO25aLH8bDB2KIsUtZuNUu5Mvm")
			  .setOAuthAccessToken(TwitterController.getUserToken().getToken())
			  .setOAuthAccessTokenSecret(TwitterController.getUserToken().getTokenSecret());
			TwitterFactory temp = new TwitterFactory(cb.build());
			TwitterController.setTf(temp);
			TwitterController.setUsertwitter(temp.getInstance());
			try{ TwitterController.setCurTimeline(TwitterController.getUsertwitter().getHomeTimeline()); } catch (TwitterException e) { e.printStackTrace(); }
			HomeController.disTwitter = false;
		}
	}


