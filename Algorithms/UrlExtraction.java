import java.util.*;
import java.util.regex.*;
import java.io.*;

public class UrlExtraction {
    public static void main(String[] args) throws IOException{
    	Scanner input = new Scanner(new BufferedReader(new FileReader(new File("input.txt"))));
    	HashSet<String> urls = new HashSet<String>();
    	String text = "";

    	while(input.hasNext()){
    		text += input.nextLine() + "\n";
    	}

    	Pattern urlFinder = Pattern.compile("(a|link)(.*href=\")([A-Za-z/ 0-9:._-]*)");
    	Matcher urlLocater = urlFinder.matcher(text);

    	while(urlLocater.find()){
    		String url = urlLocater.group(3);
    		if(url.charAt(0) != 'h'){
    			url = "http://www.ossm.edu" + url;
    		}
    		urls.add(url);
    	}
		
	System.out.println();

	Iterator urlPoster = urls.iterator();

	System.out.println("All unique urls in text");

	while(urlPoster.hasNext()){
		System.out.println(urlPoster.next());
	}

    }
}
