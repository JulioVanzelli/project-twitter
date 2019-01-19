package br.com.fiap.twitter;

import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.fiap.helper.TwitterHelper;
import br.com.fiap.twitter.model.Tweet;
import br.com.fiap.twitter.model.TweetsPerDay;

public class Main {

	public static void main(String[] args) {
		String mentioned 	= "@michelpf";
		String hashtag 		= "openjdk";
		
		TwitterService twitterService = new TwitterService(new TwitterClient());
		
		ArrayList<TweetsPerDay> dataTweets = twitterService.searchDetailedTweetsData(
				hashtag, 
				LocalDateTime.now(), 
				LocalDateTime.now().minusDays(7)
			);
		
		ArrayList<Tweet> total = new ArrayList<Tweet>();
		
		for(int i=0;i<dataTweets.size();i++){
			System.out.println("==================================================");
			System.out.println(dataTweets.get(i).toString());
			System.out.println("==================================================");
			
			twitterService.updateStatus( getFormattedMessage(mentioned, hashtag, dataTweets.get(i).toString()) );
			
			total.addAll(dataTweets.get(i).getListTweets());
		}
		
		String messageAuthor 	= TwitterHelper.getFormattedMessageAuthor(total);
		String messageDate 		= TwitterHelper.getFormattedMessageDate(total);
		
		System.out.println("==================================================");
		System.out.println( messageAuthor );
	   	System.out.println( messageDate );
		System.out.println("==================================================");
		
		twitterService.updateStatus( getFormattedMessage(mentioned, hashtag, messageAuthor +" / "+ messageDate) );
	}
	
	public static String getFormattedMessage(String mentioned, String hashtag, String message) {
		return mentioned + " (#"+ hashtag + ") : " + message;
	}

}
