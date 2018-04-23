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

	public String user;
	private String pass;
	static File userFile = new File("users.txt");
	
	//TESTING
	//private ArrayList<String> subreddits = new ArrayList<String>();
	// NO SUBREDDITS OBJECT, JUST A .TXT FILE FOR THE GIVEN USER
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
				if(userCheck.equals(username) && passCheck.equals(password)){
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
		String filename = "subreddits" + LoginController.currentUser.user + ".txt";
		
		//update file which holds all subreddits for this user
		BufferedWriter bw = null;
		FileWriter fw = null;
		int fileSize = 0;
		int fileCounter = 0;
		
		try {
			File subredditsFile = new File(filename);
			Scanner scan = new Scanner(subredditsFile);
			// if file does not exist, create the file.
			if (!subredditsFile.exists()) {
				try {
					subredditsFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fw = new FileWriter(subredditsFile.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			
			while ( scan.hasNextLine() ) {
				fileSize++;
				scan.nextLine();
			}
			scan.close();
			scan = new Scanner(subredditsFile);
			//scan.reset();
			while ( scan.hasNextLine() ) {
				fileCounter++;
				if ( scan.nextLine().equals(str) ) {
					System.out.println("Subreddit already added to this user profile.");
					break;
				} 
				if ( fileCounter == fileSize ) {
					bw.write(str);
					bw.newLine();
				}
			}
			scan.close();
			
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
	
	/*public ArrayList<String> getSubreddits() {
		return subreddits;
	}*/
	
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
