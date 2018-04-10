package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			// Load the FXML file for the game board
			Parent root = FXMLLoader.load(getClass().getResource("../Splash.fxml"));
			
			// Set the scene onto the stage
			primaryStage.setScene(new Scene(root, 700, 700));
			
			// Display the board to the user
			primaryStage.show();
			Main.stage=primaryStage;
						
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