package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
/**
 * 
 * @author Melina Klein
 * @author Sam Carey
 *
 */

public class Main extends Application {

	public static Stage stage; //access it between classes

	/**
	 * Override to set primaryStage
	 * loads Login.fxml page 
	 */
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {
			// Load the FXML document (we created with SceneBuilder)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("../Splash.fxml") );
			
			// Load the layout from the FXML and add it to the scene
			AnchorPane layout = (AnchorPane) loader.load();				
			Scene scene = new Scene( layout );
			
			// Set the scene to stage and show the stage to the user
			primaryStage.setScene(scene);
			//add skinning
			//scene.getStylesheets().add
			//  (Main.class.getResource("application.css").toExternalForm());
		
			String css = this.getClass().getResource("../application/application.css").toExternalForm(); 
			scene.getStylesheets().add(css);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Take in args to launch app
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
