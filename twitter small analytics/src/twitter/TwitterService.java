package twitter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterService {
	
	private Twitter twitter;
	
	public TwitterService(TwitterClient twitterClient) {
		this.twitter = twitterClient.getTwitter();
	}
	
	public List<Status> getHomeTimeline() {
		try {
			return this.twitter.getHomeTimeline();			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Status updateStatus(String message) {
		try {
			Status status = this.twitter.updateStatus(message);	
			
			return status;
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public DirectMessage sendDirectMessage(String user, String message) {
		try {
			DirectMessage directMessage = this.twitter.sendDirectMessage(user, message);	
			
			return directMessage;
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<TweetsPerDay> searchDetailedTweetsData(
			String word, 
			LocalDateTime initialDate,
			LocalDateTime finalDate
	) {
		
		GregorianCalendar since = new GregorianCalendar(
			finalDate.getYear(),
			(finalDate.getMonthValue() - 1), 
			finalDate.getDayOfMonth()		
		);
			
		GregorianCalendar until = new GregorianCalendar(
			initialDate.getYear(),
			(initialDate.getMonthValue() - 1), 
			initialDate.getDayOfMonth()
		);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		ArrayList<TweetsPerDay> listTweetsPerDay = new ArrayList<TweetsPerDay>();
			
		try {

			while (since.before(until)){
				
				Query query = new Query("#" + word); 
				
				GregorianCalendar previousDate = new GregorianCalendar(); 
				
				previousDate.setTime(since.getTime());
				previousDate.add(GregorianCalendar.DAY_OF_MONTH, -1);
				
				query.setSince(dateFormat.format(previousDate.getTime()));
				query.setUntil(dateFormat.format(since.getTime()));
				
				QueryResult result; 
				
				ArrayList<Tweet> listTweets = new ArrayList<Tweet>();
				int tweetsCounter=0;
				int retweetsCounter=0;
				int favorCounter=0;
			   
				result = this.twitter.search(query);
			   
				while (result.hasNext()) {
					query = result.nextQuery(); 
				   
					for (Status status : result.getTweets()) {
						tweetsCounter++;
						retweetsCounter += status.getRetweetCount();
						favorCounter += status.getFavoriteCount();
					   
						Tweet tweet = new Tweet();
					   
						tweet.setDate(status.getCreatedAt());
						tweet.setAuthor(status.getUser().getScreenName());
						tweet.setFavors(status.getFavoriteCount());
						tweet.setRetweets(status.getRetweetCount());
					   
						listTweets.add(tweet);
					}
				   
					result = this.twitter.search(query);
				}
				
				TweetsPerDay TweetsPerDay = new TweetsPerDay();
				
				TweetsPerDay.setDate(since.getTime());
				TweetsPerDay.setNumberTweets(tweetsCounter);
				TweetsPerDay.setNumberRetweets(retweetsCounter);
				TweetsPerDay.setNumberFavors(favorCounter);
				TweetsPerDay.setListTweets(listTweets);
				
				listTweetsPerDay.add(TweetsPerDay);
				
				since.add(GregorianCalendar.DAY_OF_MONTH, 1); 
			}
			
			return listTweetsPerDay;
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
