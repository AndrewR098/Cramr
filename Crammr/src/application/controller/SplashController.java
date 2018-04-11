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
 * @author melinaklein
 *
 */

public class SplashController implements EventHandler{
	@FXML
	Button splashButton;
	
	//@FXML
	//hyperlink for NewAccount
	public SplashController() {
		super();
	}
	
	public void handle(Event event) {
		//if (user clicks login button)
		//{
		try {
			// Load the FXML document (we created with SceneBuilder)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("../Login.fxml") );
						
					// Load the layout from the FXML and add it to the scene
					AnchorPane layout = (AnchorPane) loader.load();				
					Scene scene = new Scene( layout );
						
					// Set the scene to stage and show the stage to the user
					Main.stage.setScene(scene);
			}catch ( IOException e ) {
				e.printStackTrace();
						
			}		
	}
	/**else user clicks newAccount Hyperlink
	 * try { 
	 * 	//load FXML document (for NewAccount.fxml)
	 * }catch ( IOException e ) {
			e.printStackTrace();
							
		}		
	}
	 */
	
}
/**
	@Override
	public void handle(javafx.event.Event event) {
		// TODO Auto-generated method stub
		
	}
**/
	