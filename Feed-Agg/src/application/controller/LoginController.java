package application.controller;
import application.model.UserProfile;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/*
 * The LoginController Class handles Login event, displaying appropriate fxml
 * @author Samuel Carey (ial812)
 */
import javafx.stage.Stage;

public class LoginController implements EventHandler{
	
	@FXML TextField userLogin = new TextField();
	@FXML PasswordField userPass = new PasswordField();
	@FXML Button loginButton = new Button();
	
	public static UserProfile currentUser = null;
	
	@Override
	public void handle(Event event) {
		//incorrect credentials, needs to do something
		if(UserProfile.authenticate(userLogin.getText(),userPass.getText()) == null){
			System.out.println("INCORRECT LOGIN");
			//here we deal with bad stuff
		} else {
			
			currentUser = new UserProfile( userLogin.getText(),userPass.getText() );
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Homepage.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			
			}

		}

	}
}
