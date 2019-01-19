package br.com.fiap.twitter.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.fiap.helper.TweetHelper;

public class TweetsPerDay {
	
	private Date date;
	private int numberTweets = 0;
	private int numberRetweets = 0;
	private int numberFavors = 0;	
	private ArrayList<Tweet> listTweets;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNumberTweets() {
		return numberTweets;
	}
	public void setNumberTweets(int numberTweets) {
		this.numberTweets = numberTweets;
	}
	public int getNumberRetweets() {
		return numberRetweets;
	}
	public void setNumberRetweets(int numberRetweets) {
		this.numberRetweets = numberRetweets;
	}
	public int getNumberFavors() {
		return numberFavors;
	}
	public void setNumberFavors(int numberFavors) {
		this.numberFavors = numberFavors;
	}
	public ArrayList<Tweet> getListTweets() {
		return listTweets;
	}
	public void setListTweets(ArrayList<Tweet> listTweets) {
		this.listTweets = listTweets;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if (this.getNumberTweets() < 1) {
			return dateFormat.format(this.getDate()) + ": Não teve tweets";
		}
		
		String authorsString 	= TweetHelper.getFormattedMessageAuthor(this.listTweets);
		String datesString 		= TweetHelper.getFormattedMessageDate(this.listTweets);
		
		return dateFormat.format(this.getDate()) +": "+ 
			this.getNumberTweets() + " tweets. / " +
			this.getNumberRetweets() + " retweets. / " +
			this.getNumberFavors() + " favoritações. / " +
			authorsString + " / " + datesString;
	}
}
