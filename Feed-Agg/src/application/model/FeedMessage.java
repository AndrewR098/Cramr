package application.model;

/**
 * Individual message from RSS
 * @author Seth Chick
 *
 */
public class FeedMessage {
	private String title;
    private String description;
    private String link;
    private String author;
    private String guid;
    
    /**
     * Construct a feed message
     */
    public FeedMessage(){}

	/**
	 * This might not be used when RSS XML is parsed
	 * @param title
	 * @param description
	 * @param link
	 * @param author
	 * @param guid
	 */
	public FeedMessage(String title, String description, String link, String author, String guid) {
		this.title = title;
		this.description = description;
		this.link = link;
		this.author = author;
		this.guid = guid;
	}

	public FeedMessage parseFeed(String input) {
	    return null;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", description=" + description + ", link=" + link + ", author=" + author
				+ ", guid=" + guid + "]";
	}
	
	
    
    
}
