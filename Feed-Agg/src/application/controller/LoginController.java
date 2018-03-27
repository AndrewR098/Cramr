package application.controller;


import java.io.IOException;

import application.Main;
import application.UserProfile;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Melina Klein
 * @author Sam Carey
 *
 */
public class LoginController implements EventHandler {

	@FXML
	Button loginButton;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	
	/**
	 * Controller
	*/
	public LoginController() {
		super();
	}
	
	//FIXME make sure this is named "handle(Event event)" or this won't work nor compile
	/**
	 * event handle created
	 * calls authenticate() to determine if creditials match txt file
	 */
	@Override
	public void LoginHandle(Event event) {
		
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		UserProfile user = new UserProfile( username, password );
		//initial launch Splash page->click splashButton -> Login.fxml || click newAccountButton -> NewAccount.fxml
		
		//user goes from Splash to Login: if pw is correct -> Homepage
		//if password is incorrect -> NewAccount.fxml page
		//if matches => opens Recipe.fxml page
		if(user.authenticate( username, password ) == true)
		{
			try {
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( Main.class.getResource("../Homepage.fxml") );
							
						// Load the layout from the FXML and add it to the scene
						AnchorPane layout = (AnchorPane) loader.load();				
						Scene scene = new Scene( layout );
							
						// Set the scene to stage and show the stage to the user
						Main.stage.setScene(scene);
				}catch ( IOException e ) {
					e.printStackTrace();
							
				}		
		}
		//else opens Restricted.fxml page
		else
		{
			try {
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( Main.class.getResource("../NewAccount.fxml") );
							
						// Load the layout from the FXML and add it to the scene
						AnchorPane layout = (AnchorPane) loader.load();				
						Scene scene = new Scene( layout );
							
						// Set the scene to stage and show the stage to the user
						Main.stage.setScene(scene);
				}catch ( IOException e ) {
					e.printStackTrace();
							
				}		
		}
	}//end method
}//end class


