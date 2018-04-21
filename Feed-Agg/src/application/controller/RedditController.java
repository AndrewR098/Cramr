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
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.victorlaerte.asynctask.AsyncTask;

import application.Main;
import application.model.Feed;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RedditController implements Initializable{
	
	ArrayList<Feed> feeds;
	List<SyndEntry> messages;
	ArrayList<String> messageContent;
	@FXML 
	Button homeButton;
	@FXML
	ListView<String> subreddits;
	
	
	
	public void handle(Event event1) {
		Button pressedButton = (Button) event1.getSource();
		if (pressedButton == homeButton) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Homepage.fxml"));
				Stage stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		feeds = new ArrayList<Feed>();
		messages = new ArrayList<SyndEntry>();
		messageContent= new ArrayList<String>();
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
		
		
		/*if (LoginController.currentUser.getSubreddits().size() == 0) {
			output.add("Add a subreddit on the \"Add Social\" page to view subreddits.");
			Collections.reverse(output);
			ObservableList<String> stuff = FXCollections.observableArrayList(output);
			subreddits.setItems(stuff);
		}*/
		
	}
	
	/**
	 * Adds to the feed display
	 * @param feed current feed to pass
	 */
	private synchronized void updateFeed(Feed feed){
		//ObservableList<String> oList = FXCollections.observableArrayList();
		//String feedTitle = feed.getEntries().get(0).getTitle();
		//oList.add(feedTitle);
		//subreddits.getItems().add(feedTitle);
	    List<SyndEntry> mess = feed.getEntries(); //get local entries from feed
	    List<String> content = new ArrayList<String>(); //make String list for ListView
	    
	    feeds.add(feed);
	    for(int i = 0; i<mess.size(); i++) {
		messages.add(mess.get(i));
		content.add(mess.get(i).getTitle());
		messageContent.add(mess.get(i).getTitle());
	    }
	    
	    subreddits.getItems().setAll(messageContent);
	    
	}
	public void open(MouseEvent event){
		//int selection = event.getPickResult().getIntersectedFace();
		int selection2 = subreddits.getSelectionModel().getSelectedIndex();
		if(selection2>=0)
			Main.openWebpageExternal(messages.get(selection2).getLink());
	}
}