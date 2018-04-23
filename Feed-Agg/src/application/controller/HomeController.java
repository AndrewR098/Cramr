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
 * @author Sam Carey
 * @author Melina KleinΩxcv d
 * 
 * 
 * 
 * 

 */

public class HomeController{
	@FXML
	Button facebookButton;
	@FXML
	Button twitterButton;
	@FXML
	Button flickrButton;
	@FXML
	Button addSocialButton;
	@FXML
	Button redditButton;
	
	//@FXML
	//hyperlink for NewAccount
	
	/**
	 * This is the landing page; it will display RSS feeds within columns for all feeds like Flickr or any
	 * other feeds we can stream now
	 * The user can choose to filter feeds based on site, by clicking on an icon.
	 * When a user clicks an icon, we will build a new fxml page that is identical to the homepage.fxml except
	 * it will only display RSS for the selected site
	 * 
	 */
	public HomeController() {
		super();
	}
	
	public void handle(Event event) {
		if(event.getSource() == facebookButton) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/FacebookView.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(event.getSource() == twitterButton) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/TwitterView.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else if(event.getSource() == flickrButton) {
			try {//FIXME: flickr button still exists
				Parent root = FXMLLoader.load(getClass().getResource("/SocialView3.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			
			}
		
	  } else if(event.getSource() == addSocialButton) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/AddSocialAccount.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} else if (event.getSource() == redditButton) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/RedditView.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	}
	
}

	