package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Main launcher class, also handles opening web browser
 * @author Seth Chick
 *
 */
public class Main extends Application {
	private static Application app;
	
	/**
	 * Open the system default web browser with the
	 * {@link java.lang.String String} URI.
	 * @param uri link to open
	 * @see https://stackoverflow.com/questions/16604341/how-can-i-open-the-default-system-browser-from-a-java-fx-application
	 */
	public static void openWebpageExternal(String uri){
		app.getHostServices().showDocument(uri);
	}
	
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			// Load the FXML file for the game board
			Parent root = FXMLLoader.load(getClass().getResource("../Splash.fxml"));
			
			// Set the scene onto the stage
			primaryStage.setScene(new Scene(root, 700, 700));
			primaryStage.setTitle("Cramr");
			
			// Display the board to the user
			primaryStage.show();
			Main.stage=primaryStage;
			app = this;
						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

/*class Launcher extends Application{
	@Override
	public void start(Stage primaryStage) {
		try {
		    // Load the FXML file for the game board
			Parent root = FXMLLoader.load(getClass().getResource("Splash.fxml"));
			
			// Set the scene onto the stage
			primaryStage.setScene(new Scene(root, 700, 700));
			
			// Display the board to the user
			primaryStage.show();
			Main.stage=primaryStage;
						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}*/