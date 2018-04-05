package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.*;

/**
 * Manages feed from one URL
 * @author Seth Chick, hnn727
 *
 */
public class Feed {

    private URL url;
    private ArrayList<FeedMessage> messages;
    private SyndFeed feed;
    
    
    
    
    
    public Feed(URL url) throws IllegalArgumentException, FeedException, IOException {
	messages = new ArrayList<FeedMessage>();
	this.url=url;
	SyndFeedInput feedInput = new SyndFeedInput();
	feed = feedInput.build(new XmlReader(url));
	
    }
    
    /**
     * @deprecated
     * @param url
     * @throws IOException
     */
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
    
    
    
    
}
