package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

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
	
	Feed feed;
	@FXML 
	Button homeButton;
	@FXML
	ListView<String> subreddits;
	
	public void handle(Event event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Homepage.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		File file = new File("subreddits" + LoginController.currentUser.user + ".txt");
		if (!file.exists())
			System.out.println("file doesn't exist");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				final String currentSubreddit = "https://www.reddit.com/r/" + scan.nextLine() + "/.rss";
				
				AsyncTask task = new AsyncTask(){
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
						updateFeed();
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
	private void updateFeed(){
		ObservableList<String> oList = FXCollections.observableArrayList();
		String feedTitle = feed.getEntries().get(0).getTitle();
		oList.add(feedTitle);
		subreddits.getItems().add(feedTitle);
	}
	public void open(MouseEvent event){
		//int selection = event.getPickResult().getIntersectedFace();
		int selection2 = subreddits.getSelectionModel().getSelectedIndex();
		if(selection2>=0)
			Main.openWebpageExternal(feed.getEntries().get(selection2).getLink());
	}
}