package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Application launcher
 * @author Seth Chick
 *
 */
public class Main extends Application {
    
    public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			stage = primaryStage;
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


//Maven References
//------------------------------------
//http://www.vogella.com/tutorials/EclipseMaven/article.html
//https://cwiki.apache.org//confluence/display/MAVEN/MojoFailureException
//http://webcache.googleusercontent.com/search?q=cache:http://roufid.com/no-compiler-is-provided-in-this-environment/
//http://roufid.com/no-compiler-is-provided-in-this-environment/