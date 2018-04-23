package application.model;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class UserTwitter {
	private Twitter twitter;
	private AccessToken access;
	
	public UserTwitter(){
		this.twitter = new TwitterFactory().getInstance();
	}

	/* This needs to be reworked around FXML, primarily getting the pin from a text field. Controller will need its own authentication method to handle the inbetween.
	   This method may need to be overall trashed/overhauled as a result. 
	*/
	public void twitterAuthentication() {
		//TODO: Figure out how we can store keys
/*		try {
			RequestToken reqToken = null;
			reqToken = twitter.getOAuthRequestToken();
			

			System.out.println("Got request token.");
			System.out.println("Request token: " + reqToken.getToken());
			System.out.println("Request token secret: " + reqToken.getTokenSecret());
			AccessToken accessToken = null;
			while (accessToken == null) {
				System.out.println(reqToken.getAuthenticationURL());
				System.out.println("Enter the PIN(If given) and hit enter after access is granted:");
				String pin = ; // TODO: GET PIN FROM FIELD
				try {
					if (pin.length() > 0) {
						accessToken = twitter.getOAuthAccessToken(reqToken, pin);
						this.access = accessToken;
					} else {
						accessToken = twitter.getOAuthAccessToken(reqToken);
						this.access = accessToken;
					}

				} catch (TwitterException te) {
					if (401 == te.getStatusCode()) {
						System.out.println("Could not get access token.");
					} else {
						te.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException ie) {
			if (!twitter.getAuthorization().isEnabled()) {
				System.out.println("OAuth consumer key/secret is not set.");
			}
		} catch (TwitterException te){
			te.printStackTrace();
		} */
		
	}
	
	public void postUserStatus(){
		
	}
}
