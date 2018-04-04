package application.model;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * @author Melina Klein
 *
 */
public class UserProfile {
/**
 * declared variables
 */
	private String username;
	private String password;
	
	/**
	 * Constructor passing two parameters
	 * @param username
	 * @param password
	 */
	public UserProfile( String username, String password ) { 
		
		//2 parameters passed through info read from input txt file
		this.username = username;
		this.password = password;
	}//end constructor
	
	/**
	 * boolean method 
	 * calls authenticate( String username, String password )
	 * @param username
	 * @param password
	 * @return found if true
	 */
	public boolean authenticate( String username, String password ) {
		
		//Scan in text file
		File file;
		Scanner in;
		boolean found = false;
		try {
			
			file = new File( "users.txt" );
			in = new Scanner(file);
			while ( in.hasNextLine()) {
				String line = in.nextLine();
				String [] tokens = line.split( "," );
				System.out.println();

				if (username.equals(tokens[0]) && password.equals(tokens[1]))
					found = true;
			}	
			in.close();
				
			
		} catch (IOException e) { 
			   e.printStackTrace();
	    } catch ( IndexOutOfBoundsException e ) {
			   System.out.println( "Out of Bounds" );
			   e.printStackTrace();
	    }
		return found;
	}//end method
}//end class
