package application.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.rometools.rome.io.FeedException;
import com.victorlaerte.asynctask.AsyncTask;

import application.Main;
import application.model.Feed;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Example class for RSS
 * @author Seth Chick
 *
 */
public class RSSTestController implements Initializable {

	Feed feed;
	@FXML
	ImageView image;
	@FXML
	ListView<String> list;
	@FXML
	ProgressBar bar;
	@FXML
	ProgressIndicator progress;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		AsyncTask task = new AsyncTask(){
			public void doInBackground(){
				try{
					feed = new Feed(new URL("http://rss.cnn.com/rss/cnn_topstories.rss"),0);
				}
				catch(MalformedURLException e){
					e.printStackTrace();
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FeedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
				bar.setProgress(1);
				progress.setProgress(1);
			}
		};
		task.execute();
	}
	
	private void updateFeed(){
		ObservableList<String> oList = FXCollections.observableArrayList();
		String feedTitle = feed.getEntries().get(0).getTitle();
		oList.add(feedTitle);
		list.getItems().add(feedTitle);
		
	}
	
	public void open(MouseEvent event){
		//int selection = event.getPickResult().getIntersectedFace();
		int selection2 = list.getSelectionModel().getSelectedIndex();
		if(selection2>=0)
			Main.openWebpageExternal(feed.getEntries().get(selection2).getLink());
	}

}
