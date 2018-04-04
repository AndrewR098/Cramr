package application.model;
import java.io.*;
import java.util.Scanner;
/*
 * The UserProfile builds a UserProfile with User/Pass combinations entered by the user
 * present in users.txt
 * 
 * @author Samuel Carey (ial812)
 */
public class UserProfile {

	private String user, pass;
	static File userFile = new File("users.txt");
	
	/*
	 * Constructor
	 */
	public UserProfile(){
		this.user = getUser();
		this.pass = getPass();
	}
	/*
	 * @param username String from userLogin 
	 * @param password String from userPass
	 * 
	 * UserProfile either returns null if credentials are invalid
	 * or a UserProifle object with the credentials if they are valid
	 */
	public static UserProfile authenticate(String username, String password){	
		try{
			Scanner scan = new Scanner(userFile);
			
			
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				String split[] = line.split(",");
				String userCheck = split[0]; String passCheck = split[1];
				if(userCheck.equals(username) && passCheck.equals(passCheck)){
					scan.close();	
					UserProfile profile =  new UserProfile();
					profile.setUser(username);
					profile.setPass(password);
					return profile;
				}	
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();	
		}
		return null;
	}
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
