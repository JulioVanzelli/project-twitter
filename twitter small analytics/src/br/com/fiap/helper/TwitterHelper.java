package br.com.fiap.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import br.com.fiap.twitter.model.Tweet;

public final class TwitterHelper {
	
	private static String[] getFirstAndLastAuthor(ArrayList<Tweet> listTweet) {		
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
	
	private static String[] getFirstAndLastTweetDate(ArrayList<Tweet> listTweet) {
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
	
	public static String getFormattedMessageAuthor (ArrayList<Tweet> listTweet) {
		String[] aux = TwitterHelper.getFirstAndLastAuthor(listTweet);
		
		return aux[1] != null ? aux[0] + " foi o primeiro autor e "+ aux[1] + " último autor." : aux[0];
	}
	
	public static String getFormattedMessageDate (ArrayList<Tweet> listTweet) {
		String[] aux = TwitterHelper.getFirstAndLastTweetDate(listTweet);
		
		return aux[1] != null ? aux[0] + " foi o primeiro autor e "+ aux[1] + " último autor." : aux[0];
	}
	
}
