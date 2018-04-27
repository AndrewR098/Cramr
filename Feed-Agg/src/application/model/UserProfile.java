package application.model;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import application.controller.AddSocialController;
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
	/**
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
		if (str == null || str.isEmpty() || str.equals("") || str.contains(" ")) {
			System.out.println("not a valid subreddit entry.");
			AddSocialController.added = false;
			return;
		}
		//update file which holds all subreddits for this user
		BufferedWriter bw = null;
		FileWriter fw = null;
		int fileSize = 0;
		int fileCounter = 0;
		boolean dont = true;
		
		try {
			File subredditsFile = new File(filename);
			Scanner scan = new Scanner(subredditsFile);
			// if file does not exist, create the file.
			
			fw = new FileWriter(subredditsFile.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			
			while ( scan.hasNextLine() ) {
				fileSize++;
				scan.nextLine();
			}
			scan.close();
			scan = new Scanner(subredditsFile);
			//scan.reset();
			while (scan.hasNextLine() ) {
				fileCounter++;
				if ( scan.nextLine().equals(str) ) {
					System.out.println("Subreddit already added to this user profile.");
					dont = false;
					AddSocialController.added = false;
					break;
				} 
				if ( fileCounter == fileSize ) {
					bw.write(str);
					bw.newLine();
					dont = false;
					System.out.println("subreddit added");
					AddSocialController.added = true;
				}
			}
			if( fileCounter == fileSize && dont) {
				bw.write(str);
				bw.newLine();
				System.out.println("subreddit added");
				AddSocialController.added = true;
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
	
	public void removeSubreddit(String subreddit) throws IOException{
		if (subreddit == null || subreddit.isEmpty() || subreddit.equals("") || subreddit.contains(" ")) {
			System.out.println("not a valid subreddit entry.");
			AddSocialController.removed = false;
			return;
		}
		
		String filename = "subreddits" + LoginController.currentUser.user + ".txt";
        File file = new File(filename);
        ArrayList<String> keep = new ArrayList<String>();
        boolean exists = false;
 
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(subreddit)) {
            	exists = true;
            }
            if(!trimmedLine.equals(subreddit)) {
            	keep.add(trimmedLine);
            }
        }
        reader.close();
        file.delete();
        
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String value : keep) {
            writer.write(value);
            writer.newLine();
        }
        writer.close();
        if (!exists) {
        	System.out.println("subreddit isn't in your subscriptions to remove");
        	AddSocialController.removed = false;
        } else {
        	System.out.println("subreddit removed");
        	AddSocialController.removed = true;
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
