package twitter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		TwitterClient twitterClient = new TwitterClient(
				"Y4mDeKYjHHPWC3iurthtAtPIW",
				"4yNdfPmUNaIrGnVN0XJNCffWPfzuC3sDHIlykXtqgUZdbZHB6b",
				"3405826665-cEAJizD5sefVqfkmnf6gg1RN49OmVLsSZAWdSuW", 
				"VrZQuwUAvbV5RMjkUvFLTYUO86gRtadqyGm8wyhLFuD2w"
			);

		TwitterService twitterService = new TwitterService(twitterClient);
		
		//twitterService.updateStatus("@michelpf finalizei a aplicação.");
		
		//twitterService.sendDirectMessage("neverislate", "testando minha aplicação Novamente.");

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
		
		// 08GhostFace foi o primeiro autor e zeroturnaround último autor.
		
		System.out.println("==================================================");
		String[] aux = TweetHelper.getFirstAndLastAuthor(total);
		System.out.println( aux[1] != null ? aux[0] + " foi o primeiro autor e "+ aux[1] + " último autor." : "Sem autor." );
		
		aux = TweetHelper.getFirstAndLastTweetDate(total);
	   	System.out.println( aux[1] != null ? aux[0] + " foi o primeiro tweet e "+ aux[1] + " último tweet." : "Sem tweet." );
		System.out.println("==================================================");
		
		//twitterService.updateStatus("@michelpf finalizei a aplicação.");
	}

}
