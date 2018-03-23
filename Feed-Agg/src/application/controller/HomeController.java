package application.controller;

import java.awt.Event;
import java.io.IOException;

import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
/**
 * @author Sam Carey
 * @author Melina KleinΩxcv d
 * 
 * 
 * 
 * 

 */

public class HomeController implements EventHandler{
	@FXML
	Button splashButton;
	
	//@FXML
	//hyperlink for NewAccount
	
	/**
	 * This is the landing page; it will display RSS feeds within columns for all feeds like Flickr or any
	 * other feeds we can stream now
	 * 
	 * The user can choose to filter feeds based on site, by clicking on an icon.
	 * When a user clicks an icon, we will build a new fxml page that is identical to the homepage.fxml except
	 * it will only display RSS for the selected site
	 * 
	 */
	public HomeController() {
		super();
	}
	
	public void Homehandle(Event event) {
		//if (user clicks facebook icon to set up that on their cramer feed)
		//{
		try {
			// Load the FXML document (we created with SceneBuilder)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("../FacebookLogin.fxml") );
						
					// Load the layout from the FXML and add it to the scene
					AnchorPane layout = (AnchorPane) loader.load();				
					Scene scene = new Scene( layout );
						
					// Set the scene to stage and show the stage to the user
					Main.stage.setScene(scene);
			}catch ( IOException e ) {
				e.printStackTrace();
						
			}		
	}
	
}
/** what is this? 
	@Override
	public void handle(javafx.event.Event event) {
		// TODO Auto-generated method stub
		
	}
**/
	