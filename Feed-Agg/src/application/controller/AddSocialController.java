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

/**
 * Add social feeds to the user profile.
 * @author Casey Hammond, Andrew Rodriguez, Cristian Cisneros
 * 
 */
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
		public static boolean updateHome;
		
		/**
		 * Twitter login setup
		 */
		public void initialize(){
			updateHome = false;
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey("m11rTAwm0FvUNqa0byhh9Z4fa")
			  .setOAuthConsumerSecret("BdfxxBhzfROJ4k3BJHAGQw0tg1sLz0OxJPa1evjEDbofq4q4c3");
			TwitterFactory temp = new TwitterFactory(cb.build());
			TwitterController.setTf(temp);
			TwitterController.setUsertwitter(temp.getInstance());
			try {
				requestToken = TwitterController.getUsertwitter().getOAuthRequestToken();
	//			System.out.println(requestToken.getToken() + "\n" + requestToken.getTokenSecret());
				urlTwitter.setText(requestToken.getAuthorizationURL());
			} catch (TwitterException e){
				e.printStackTrace();
			}
			
		}
		
		/**
		 * If user clicks HOME, go to the home page.
		 * If user clicks ADD REDDIT, add subreddit to the user profile.
		 * If user clicks REMOVE REDDIT, remove the subreddit from the user profile.
		 * 
		 * @param event
		 * @throws IOException
		 */
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
		
		/**
		 * Authenticate twitter logins after the user submits the pin.
		 */
		public void finishTwitterAuth(){
			String pin = pinField.getText();
			pin.trim();
			try {
                if (pin.length() > 0) {
                	updateHome= true;
    				JOptionPane.showMessageDialog(null, "This Twitter Account has been added to your feeds");
                    TwitterController.setUserToken(TwitterController.getUsertwitter().getOAuthAccessToken(requestToken, pin));
                } else {
                	updateHome= true;
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

	//		System.out.println(TwitterController.getUserToken().getToken() + "\n" + TwitterController.getUserToken().getTokenSecret());
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey("m11rTAwm0FvUNqa0byhh9Z4fa")
			  .setOAuthConsumerSecret("BdfxxBhzfROJ4k3BJHAGQw0tg1sLz0OxJPa1evjEDbofq4q4c3")
			  .setOAuthAccessToken(TwitterController.getUserToken().getToken())
			  .setOAuthAccessTokenSecret(TwitterController.getUserToken().getTokenSecret());
			TwitterFactory temp = new TwitterFactory(cb.build());
			TwitterController.setTf(temp);
			TwitterController.setUsertwitter(temp.getInstance());
			try{ TwitterController.setCurTimeline(TwitterController.getUsertwitter().getHomeTimeline()); } catch (TwitterException e) { e.printStackTrace(); }
			HomeController.disTwitter = false;
		}
	}


