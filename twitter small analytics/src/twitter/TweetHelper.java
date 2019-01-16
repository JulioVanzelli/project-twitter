package twitter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public final class TweetHelper {
	
	public static String[] getFirstAndLastAuthor(ArrayList<Tweet> listTweet) {		
		Collections.sort(listTweet,(t1, t2) -> t1.getAuthor().compareTo(t2.getAuthor()));
		
		String[] authors = new String[2];
		
		if(listTweet.size() < 1) {
			authors[0] = "Sem autor.";
			return authors;
		}
		
		authors[0] = listTweet.get(0).getAuthor();
		authors[1] = listTweet.get(listTweet.size()-1).getAuthor();
		
		return authors;
	}
	
	public static String[] getFirstAndLastTweetDate(ArrayList<Tweet> listTweet) {
		SimpleDateFormat dateHourFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Collections.sort(listTweet,(t1, t2) -> t1.getDate().compareTo(t2.getDate()));
		
		String[] dates = new String[2];
		
		if(listTweet.size() < 1) {
			dates[0] = "Sem tweet.";
			return dates;
		}
		
		dates[0] = dateHourFormat.format(listTweet.get(0).getDate());
		dates[1] = dateHourFormat.format(listTweet.get(listTweet.size()-1).getDate());
		
		return dates;
	}
	
}
