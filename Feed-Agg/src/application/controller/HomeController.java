package application.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import com.victorlaerte.asynctask.AsyncTask;

import application.Main;
import application.model.Feed;
import application.model.TweetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
/**
 * @author Sam Carey
 * @author Melina KleinΩxcv d
 * @author Seth Chick
 * 
 * This is the landing page; it will display RSS feeds within columns for all feeds like Flickr or any
 * other feeds we can stream now
 * The user can choose to filter feeds based on site, by clicking on an icon.
 * When a user clicks an icon, we will build a new fxml page that is identical to the homepage.fxml except
 * it will only display RSS for the selected site
 * 

 */

public class HomeController implements Initializable{
	@FXML
	Button facebookButton;
	@FXML
	Button twitterButton;
	@FXML
	Button addSocialButton;
	@FXML
	Button redditButton;
	
	////////////TWITTER//////////////////////////
	@FXML
	ListView<String> twFeed;
	ObservableList<String> hpTweets=  FXCollections.observableArrayList();
	@FXML
	Button refreshTwitter,newTweet;
	/////////////////REDDIT//////////////////////
	@FXML
	ListView<String> rdtFeed;
	@FXML
	Button rdtRefreshButton, rdtSubs;
	
	ArrayList<Feed> rFeeds;
	List<SyndEntry> rMessages;
	ArrayList<String> rMessageContent;
	public static boolean disTwitter = true;
	
	////////////////REDDIT: END//////////////////
	
	//@FXML
	//hyperlink for NewAccount
	
	/**
	 * This is the landing page; it will display RSS feeds within columns for all feeds like Flickr or any
	 * other feeds we can stream now
	 * The user can choose to filter feeds based on site, by clicking on an icon.
	 * When a user clicks an icon, we will build a new fxml page that is identical to the homepage.fxml except
	 * it will only display RSS for the selected site
	 * 
	 */
	public HomeController() {
		super();
	}
	/**
	 * Handles button presses.
	 * @param event
	 */
	public void handle(Event event) {
		if(event.getSource() == facebookButton) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/FacebookView.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(event.getSource() == twitterButton) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/TwitterView.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}

		}  else if(event.getSource() == addSocialButton) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/AddSocialAccount.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (event.getSource() == redditButton) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/RedditView.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(event.getSource() == rdtRefreshButton){
			refreshReddit();
		}else if(event.getSource() == rdtSubs){
			redditSubscriptions();
		}

	}
	
	/**
	 * If the user has setup reddit and/or twitter feeds, display them to the user.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//Reddit/////////////////////////////////////
		refreshReddit();
		twitterButton.setDisable(disTwitter);
		try {
			if(TwitterController.usertwitter==null){
				hpTweets.add("No Twitter account associated with Cramr...\nSign in to see your feed!");
				twFeed.setItems(hpTweets);
				return;
			}
			
			ResponseList<Status> userHome = TwitterController.usertwitter.getHomeTimeline();

			TwitterController.curTimeline = userHome;
			TweetView toAdd;
			
			for(Status value: userHome){
				String str="";
				str = value.getUser().getScreenName() +"\n\n"+value.getText();
				hpTweets.add(str);
				twFeed.setItems(hpTweets);
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//////////////////REDDIT: END///////////////////////////
		
	}
	//////////////////////////REDDIT START///////////////////////
	/**
	 * Adds to the Reddit feed display
	 * @param feed current feed to pass
	 */
	private synchronized void updateFeed(Feed feed){
		//ObservableList<String> oList = FXCollections.observableArrayList();
		//String feedTitle = feed.getEntries().get(0).getTitle();
		//oList.add(feedTitle);
		//subreddits.getItems().add(feedTitle);
		assert feed!=null: "Feed is null";
		if(feed!=null){
			List<SyndEntry> mess = feed.getEntries(); //get local entries from feed
			List<String> content = new ArrayList<String>(); //make String list for ListView

			rFeeds.add(feed);
			for(int i = 0; i<mess.size(); i++) {
				rMessages.add(mess.get(i));
				content.add(mess.get(i).getTitle());
				rMessageContent.add(mess.get(i).getTitle());
			}

			if(!rMessageContent.isEmpty())
				rdtFeed.getItems().setAll(rMessageContent);
			else
				rdtFeed.getItems().setAll("No subscriptions");
		}
	    
	}
	/**
	 * Opens clicked reddit feed
	 * @param event unused
	 */
	public void onRedditClicked(MouseEvent event){
		if(!rFeeds.isEmpty()){
			int selection2 = rdtFeed.getSelectionModel().getSelectedIndex();
			if(selection2>=0)
				Main.openWebpageExternal(rMessages.get(selection2).getLink());
		}
	}
	/**
	 * Refresh Reddit feed. 
	 */
	public void refreshReddit(){
		rFeeds = new ArrayList<Feed>();
		rMessages = new ArrayList<SyndEntry>();
		rMessageContent= new ArrayList<String>();
		File file = new File("subreddits" + LoginController.currentUser.user + ".txt");
		
		if (!file.exists())
			System.out.println("file doesn't exist");
		
		Scanner scan;
		
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				final String currentSubreddit = "https://www.reddit.com/r/" + scan.nextLine() + "/.rss";
				AsyncTask task = new AsyncTask(){
				    Feed feed;
					public void doInBackground(){
						
						try{
							feed = new Feed(new URL(currentSubreddit),0);
						} catch(MalformedURLException e){
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (FeedException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					public void progressCallback(Object... params){
						
					}
					public void onPreExecute(){
						
					}
					public void onPostExecute(){
						//TODO: update views
						updateFeed(feed);
					}
				};
				task.execute();
			}
			scan.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Show reddit subs
	 */
	public void redditSubscriptions(){
		rFeeds.clear();
		rMessageContent.clear();
		rMessages.clear();
		ArrayList<String> subList = new ArrayList<String>();
		File file = new File("subreddits" + LoginController.currentUser.user + ".txt");
		if(file.exists()){
			try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()){
					subList.add(scan.nextLine());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(subList.isEmpty()){
			subList.add("No Subscriptions.");
		}
		
		rdtFeed.getItems().setAll(subList);
	}
	///////////////////REDDIT END////////////////////////////////
}

	