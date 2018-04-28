package application.controller;



import application.model.TweetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
/**
 * The twitter controller handles all interactions between the user and the twitter model. It utilizes the Twitter4J library to interact with Twitter's API effectively and easily.
 * @author Andrew Rodriguez, Cristian Cisneros 
 *
 */
public class TwitterController {
	@FXML Button homeButton;
	@FXML TextArea userStatus;
	@FXML Label tooLong;
	@FXML TableView<TweetView> timelineView;
	@FXML TableColumn<TweetView, Integer> retweetColumn;
	@FXML TableColumn<TweetView, Integer> likeColumn; 
	@FXML TableColumn<TweetView, String> userColumn;
	@FXML TableColumn<TweetView, String> tweetColumn;
	@FXML Button retweet1, like1;
	static Twitter usertwitter;
	private static TwitterFactory tf;
	static ResponseList<Status> curTimeline;
	private static AccessToken userToken;
	
	/**
	 * Setup the twitter page with all the info needed to display to the user.
	 */
	public void initialize(){
		try {
			ResponseList<Status> userHome = usertwitter.getHomeTimeline(); // Take this and display it in the status text fields.
			this.curTimeline = userHome;
			retweetColumn.setCellValueFactory(new PropertyValueFactory<TweetView, Integer>("retweetCount"));
			likeColumn.setCellValueFactory(new PropertyValueFactory<TweetView, Integer>("favoriteCount"));
			tweetColumn.setCellValueFactory(new PropertyValueFactory<TweetView, String>("text"));
			userColumn.setCellValueFactory(new PropertyValueFactory<TweetView, String>("user"));
			timelineView.setItems(getTweets(userHome));
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	/**
	 * If user clicks HOME, take them to the home page.
	 * @param event
	 */
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
	
	/**
	 * If user clicks TWEET, post whatever they have typed to twitter, as long as it is not too long.
	 */
	public void postTweet(){
		String toPost = userStatus.getText();
		if(toPost.length() > 240){
			tooLong.setVisible(true);
		} else{
			userStatus.setText("");
			tooLong.setVisible(false);
			try {
				Status status = usertwitter.updateStatus(toPost);
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Not sure how to undo Retweets yet, but we CAN retweet statuses.
	 * @param event
	 */
	public void retweetStatus(ActionEvent event){
		if (event.getSource().equals(retweet1)){
			try{
			if (!curTimeline.get(0).isRetweetedByMe()){
					usertwitter.retweetStatus(curTimeline.get(0).getId());
			} else{
					usertwitter.destroyStatus(curTimeline.get(0).getCurrentUserRetweetId());
			}
			} catch(TwitterException e){
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Not sure how to undo likes yet without too many API requests, but we can like it.
	 * @param event
	 */
	public void likeStatus(ActionEvent event){
		if (event.getSource().equals(like1)){
			try{
			if (!curTimeline.get(0).isFavorited()){
				usertwitter.createFavorite(curTimeline.get(0).getId());
			} else {
				usertwitter.destroyFavorite(curTimeline.get(0).getId());
			} } catch (TwitterException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Get tweets from the users feed.
	 * @param userHome
	 * @return tweets: List of tweets from user's home timeline
	 */
	public ObservableList<TweetView> getTweets(ResponseList<Status> userHome){
		ObservableList<TweetView> tweets = FXCollections.observableArrayList();
		TweetView toAdd;
		for(Status value : userHome){
			toAdd = new TweetView(value.getText(), value.getUser().getScreenName(), value.getRetweetCount(), value.getFavoriteCount());
			tweets.add(toAdd);
		}
		return tweets;
	}
	
	/**
	 * Refresh the page to show new tweets.
	 */
	public void refresh(){
		try {
			ResponseList<Status> userHome = usertwitter.getHomeTimeline(); // Take this and display it in the status text fields.
			retweetColumn.setCellValueFactory(new PropertyValueFactory<TweetView, Integer>("retweetCount"));
			likeColumn.setCellValueFactory(new PropertyValueFactory<TweetView, Integer>("favoriteCount"));
			tweetColumn.setCellValueFactory(new PropertyValueFactory<TweetView, String>("text"));
			userColumn.setCellValueFactory(new PropertyValueFactory<TweetView, String>("user"));
			timelineView.setItems(getTweets(userHome));
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	/**
	 * get user access token
	 * @return
	 */
	public static AccessToken getUserToken() {
		return userToken;
	}

	/**
	 * set user access token
	 * @param userToken
	 */
	public static void setUserToken(AccessToken userToken) {
		TwitterController.userToken = userToken;
	}

	/**
	 * get user twitter instance
	 * @return
	 */
	public static Twitter getUsertwitter() {
		return usertwitter;
	}

	/** 
	 * set user twitter instance
	 * @param usertwitter
	 */
	public static void setUsertwitter(Twitter usertwitter) {
		TwitterController.usertwitter = usertwitter;
	}

	/**
	 * Get last loaded timeline. Useful to avoid making excessive calls to the API.
	 * @return
	 */
	public static ResponseList<Status> getCurTimeline() {
		return curTimeline;
	}

	/**
	 * Set timeline.
	 * @param curTimeline
	 */
	public static void setCurTimeline(ResponseList<Status> curTimeline) {
		TwitterController.curTimeline = curTimeline;
	}

	/**
	 * twitter factory, necessary to set twitter instance when we add our account.
	 * @return
	 */
	public static TwitterFactory getTf() {
		return tf;
	}

	/**
	 * set the twitter factory.
	 * @param tf
	 */
	public static void setTf(TwitterFactory tf) {
		TwitterController.tf = tf;
	}
}
