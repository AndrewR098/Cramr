package application.model;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import application.controller.LoginController;
/*
 * The UserProfile builds a UserProfile with User/Pass combinations entered by the user
 * present in users.txt
 * 
 * @author Samuel Carey (ial812)
 */
public class UserProfile {

	private static String user;
	private static String pass;
	static File userFile = new File("users.txt");
	static File subredditsFile = new File("subreddits" + user);
	
	//TESTING
	private ArrayList<String> subreddits = new ArrayList<String>();
	// TODO: add twitter object
	// TODO: add facebook object
	
	/*
	 * Constructor
	 */
	public UserProfile(String a, String b){
		this.user = a;
		this.pass = b;
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
					UserProfile profile = new UserProfile(username,password);
					return profile;
				}	
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();	
		}
		return null;
	}
	
	public void addSubreddit(String str) {
		//update the arraylist with the new subreddit
		this.subreddits.add(str);
		
		//update file which holds all subreddits for this user
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			// if file does not exist, create the file.
			if (!subredditsFile.exists()) {
				subredditsFile.createNewFile();
			}
			fw = new FileWriter(subredditsFile.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			
			bw.write(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getSubreddits() {
		return subreddits;
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
