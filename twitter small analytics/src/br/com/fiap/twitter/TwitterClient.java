package br.com.fiap.twitter;

import br.com.fiap.config.Config;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterClient {
	
	private String apiKey;
	private String apiSecretKey;
	private String accessToken;
	private String accessTokenSecret;
	
	public TwitterClient() {
		Config config = new Config("config.properties");
		
		this.apiKey = config.getProperty("twitter_api_key");
		this.apiSecretKey = config.getProperty("twitter_api_secret_key");
		this.accessToken = config.getProperty("twitter_access_token");
		this.accessTokenSecret = config.getProperty("twitter_access_token_secret");
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
