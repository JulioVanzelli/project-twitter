package twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterClient {
	
	private String apiKey;
	private String apiSecretKey;
	private String accessToken;
	private String accessTokenSecret;
	
	public TwitterClient(String apiKey, String apiSecretKey, String accessToken, String accessTokenSecret) {
		this.apiKey = apiKey;
		this.apiSecretKey = apiSecretKey;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
	}
	
	private AccessToken loadAccessToken() {
		return new AccessToken(this.accessToken, this.accessTokenSecret);
	}

	public Twitter getTwitter() {
		
		try {
			AccessToken accessToken = this.loadAccessToken();
			Twitter twitter = TwitterFactory.getSingleton();
			twitter.setOAuthConsumer(this.apiKey, this.apiSecretKey);
			twitter.setOAuthAccessToken(accessToken);
			
			return twitter;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
}
