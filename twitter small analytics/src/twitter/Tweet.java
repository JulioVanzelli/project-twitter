package twitter;

import java.util.Date;

public class Tweet {
	
	private Date date;
	private String author;
	private int retweets;
	private int favors;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getRetweets() {
		return retweets;
	}
	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}
	public int getFavors() {
		return favors;
	}
	public void setFavors(int favors) {
		this.favors = favors;
	}

}
