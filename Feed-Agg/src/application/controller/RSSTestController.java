package application.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.rometools.rome.io.FeedException;
import com.victorlaerte.asynctask.AsyncTask;

import application.model.Feed;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class RSSTestController implements Initializable {

	Feed feed;
	@FXML
	ImageView image;
	@FXML
	ListView<String> list;
	
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
			}
		};
		task.execute();
	}

}
