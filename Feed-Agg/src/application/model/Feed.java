package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import com.rometools.rome.io.*;

/**
 * Manages feed from one URL
 * @author Seth Chick, hnn727
 *
 */
public class Feed {

    private URL url;
    private ArrayList<FeedMessage> messages;
    
    
    public Feed(URL url) {
	messages = new ArrayList<FeedMessage>();
	
    }
    
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
