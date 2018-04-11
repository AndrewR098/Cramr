package application.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.*;

/**
 * Manages feed from one URL. This also stores the slot on the UI tabs on which position it's at.
 * YOU MUST HANDLE THESE EXCEPTIONS IN THE CONTROLLER ITSELF SINCE THIS
 * CLASS CANNOT RETURN THE ERROR ITSELF!
 * @author Seth Chick, hnn727
 *
 */
public class Feed {

	private URL url;
	private SyndFeed feed;
	private int slotID;




	/**
	 * Creates and parses an online feed
	 * @param url URL of the RSS feed
	 * @param slotID the position of the feed on the UI
	 * @throws IllegalArgumentException not a valid feed parser, you don't have to worry about this, I think
	 * @throws FeedException Something went wrong with parsing the feed
	 * @throws IOException Something went wrong retrieving the feed
	 */
	public Feed(URL url, int slotID) throws IllegalArgumentException, FeedException, IOException {
		this.url=url;
		SyndFeedInput feedInput = new SyndFeedInput();
		feed = feedInput.build(new XmlReader(url));
		this.slotID = slotID;
	}

	/**
	 * @deprecated
	 * @param url
	 * @throws IOException
	 */
	@Deprecated
	public void readRSS(URL url) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String line;
		while((line=reader.readLine())!=null) {
			String source = "";
			final String startItem = "<item>";
			final String endItem = "</item>";
			if(line.contains(startItem)) {
				int firstPos = line.indexOf(startItem);
				source += line.substring(firstPos)+"\n";
				boolean endFound = false;
				int lastPos = 0;
				while (!endFound) {
					//TODO finish this https://www.youtube.com/watch?v=xiK-DH74oJg&ab_channel=FranciscoIacobelli
				}

			}
		}

		reader.close();
	}

	/**
	 * 
	 * @return get Feed itself
	 */
	public SyndFeed getFeed() {
		return feed;
	}

	/**
	 * 
	 * @return retrieve a list of every entry
	 */
	public List<SyndEntry> getEntries() {
		return feed.getEntries();
	}

	/**
	 * 
	 * @return url of the feed
	 */
	public URL getURL() {
		return url;
	}

	/**
	 * Retrieves the title of the feed
	 * @return title of feed, null if none
	 */
	public String getTitle() {
		return feed.getTitle();
	}

	/**
	 * 
	 * @return slot id of feed
	 */
	public int getSlotID() {
		return slotID;
	}

	/**
	 * 
	 * @param id slot id of feed
	 */
	public void setSlotID(int id) {
		slotID=id;
	}

	/**
	 * Gets the savable version of feed to be stored to persistent storage,
	 * you should call this when the user decides to log out and exit and
	 * store this object to memory
	 * @return savable feed
	 * @see FeedPersistent
	 */
	public FeedPersistent getSerializable() {
		return new FeedPersistent(url,slotID);
	}

	/**
	 * Constructs a previous feed from a file
	 * @param file file to read from
	 * @return Feed if read successful, null if something went wrong
	 * @see FeedPersistent
	 */
	public static Feed parseSerializableFeed(String file){
		Object ob = null;
		try {
			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream obInput = new ObjectInputStream(fileInput);

			ob = obInput.readObject();

			obInput.close();
			fileInput.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
			System.err.print("Something went wrong finding this feed.");
			return null;
		}catch(IOException e) {
			e.printStackTrace();
			System.err.println("Something went wrong reading the file");
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("This isn't a FeedPersistent");
			return null;

		}
		if(ob instanceof FeedPersistent) {
			FeedPersistent fp = (FeedPersistent)ob;
			Feed thisFeed = null;
			try {
				thisFeed = new Feed(fp.getURL(),fp.getSlotID());
			}catch(MalformedURLException e) {
				System.err.println("Something went wrong parsing for URL");
				e.printStackTrace();
				return null;
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (FeedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return thisFeed;
		}else {
			System.err.println("This isn't a persistent feed settings.");
			return null;
		}
	}
}
