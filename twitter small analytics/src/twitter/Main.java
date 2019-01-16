package twitter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		TwitterClient twitterClient = new TwitterClient(
				"",
				"",
				"", 
				""
			);

		TwitterService twitterService = new TwitterService(twitterClient);

		// hashtag e tweets da última semana
		ArrayList<TweetsPerDay> dataTweets = twitterService.searchDetailedTweetsData(
				"java", 
				LocalDateTime.now(), 
				LocalDateTime.now().minusDays(7)
			);
		
		ArrayList<Tweet> total = new ArrayList<Tweet>();
		
		for(int i=0;i<dataTweets.size();i++){
			System.out.println("==================================================");
			System.out.println(dataTweets.get(i).toString());
			System.out.println("==================================================");
			
			total.addAll(dataTweets.get(i).getListTweets());
		}

		System.out.println("==================================================");
		String[] aux = TweetHelper.getFirstAndLastAuthor(total);
		System.out.println( aux[1] != null ? aux[0] + " foi o primeiro autor e "+ aux[1] + " último autor." : "Sem autor." );
		
		aux = TweetHelper.getFirstAndLastTweetDate(total);
	   	System.out.println( aux[1] != null ? aux[0] + " foi o primeiro tweet e "+ aux[1] + " último tweet." : "Sem tweet." );
		System.out.println("==================================================");

	}

}
