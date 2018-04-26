package application.model;

public class TweetView {

	private String text, user;
	private Integer retweetCount, favoriteCount;
	
	public TweetView(String inText, String inUser, Integer inRetweetCount, Integer inFavoriteCount){
		this.text = inText;
		this.user = inUser;
		this.retweetCount = inRetweetCount;
		this.favoriteCount = inFavoriteCount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(Integer retweetCount) {
		this.retweetCount = retweetCount;
	}

	public Integer getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	
}
