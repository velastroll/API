/********************************************************
 * ALL OF CREDITS ARE FOR @YUSUKE - yusuke [at] mac.com *
 * DEPENDENCIES: twitter4j - twitter4j.org				*
 * This is a compilation for a personal use.			*
 ************************ *******************************/

package com.API_Twitter;

import twitter4j.conf.*;
import twitter4j.*;
import java.util.Scanner;

/**
 * @author Velastroll - t.me/velastroll
 */
class SendTweet {

	public static String oAuthConsumerKey = "";
	public static String oAuthConsumerSecret = "";
	public static String oAuthAccessToken = "";
	public static String oAuthAccessTokenSecret = "";
	public static ConfigurationBuilder cb;
	public static TwitterFactory tf;
	public static Twitter twitter;


	public static void main(String[] args) {

		setConfig();
		
		tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();

		Scanner getKeyboard = new Scanner(System.in);
		System.out.print("\nwhat would you like to tweet?: ");
		String tweet = getKeyboard.nextLine();
		getKeyboard.close();
		
		sendingTweet(tweet);
	}
	
	
	public static void setConfig(){
		cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(oAuthConsumerKey);
		cb.setOAuthConsumerSecret(oAuthConsumerSecret);
		cb.setOAuthAccessToken(oAuthAccessToken);
		cb.setOAuthAccessTokenSecret(oAuthAccessTokenSecret);
	}
	
	/**
	 * 
	 * @param tweet [String] Must be less than 140 characters.
	 */
	public static void sendingTweet(String tweet){
		try {
			twitter.updateStatus(tweet);
		}catch (TwitterException te) {
			System.out.println("Failed to send message: " + te.getMessage());
			System.exit(-1);
		}
	}
}