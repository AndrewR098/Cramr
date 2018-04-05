package application.model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class is for persistent storage to store feed settings
 * when the application shuts down.
 * @author Seth Chick
 *
 */
public class FeedPersistent implements Serializable{
    public static final long serialVersionUID = 1L;
    public final String url;
    public final int slotID;
    
    /**
     * Constructs a Serializable form of {@link Feed Feed}
     * @param url url of the feed
     * @param feedSlotID the slot position of the feed
     */
    public FeedPersistent(URL url, int feedSlotID) {
	this.url = url.toString();
	slotID = feedSlotID;
    }
    
    /**
     * 
     * @return slot id of feed
     */
    public int getSlotID() {
	return slotID;
    }
    
    /**
     * URL of feed
     * @return url of feed, null if not valid url
     */
    public URL getURL() {
	try {
	    return new URL(url);
	} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
    }
}
