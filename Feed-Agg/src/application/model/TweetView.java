package application.model;

/**
 * This Class lets us put the information we want to display from the Twitter4J Status object more easily into a tableview.
 * @author Andrew Rodriguez, Cristian Cisneros
 */
public class TweetView {

	private String text, user;
	private Integer retweetCount, favoriteCount;
	/**
	 * Constructor for a TweetView
	 * @param inText
	 * @param inUser
	 * @param inRetweetCount
	 * @param inFavoriteCount
	 */
	public TweetView(String inText, String inUser, Integer inRetweetCount, Integer inFavoriteCount){
		this.text = inText;
		this.user = inUser;
		this.retweetCount = inRetweetCount;
		this.favoriteCount = inFavoriteCount;
	}
	
	/**
	 * Gets tweetView text
	 * @return text String: text of Status.
	 */
	public String getText() {
		return text;
	}

	/**
	 * sets status text in the TweetView object.
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Gets tweetView user
	 * @return text String: user of Status.
	 */
	public String getUser() {
		return user;
	}

	/**
	 * sets user name in the TweetView object.
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets tweetView retweets
	 * @return text Integer: retweet count of status.
	 */
	public Integer getRetweetCount() {
		return retweetCount;
	}

	/**
	 * sets retweet count in the TweetView object.
	 * @param retweetCount
	 */
	public void setRetweetCount(Integer retweetCount) {
		this.retweetCount = retweetCount;
	}

	/**
	 * Gets tweetView favorite
	 * @return text Integer: favorite count of status.
	 */
	public Integer getFavoriteCount() {
		return favoriteCount;
	}

	/**
	 * sets favorite count in the TweetView object.
	 * @param favoriteCount
	 */
	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	
}
