/******************************************************
 * ALL OF CREDITS ARE FOR @YUSUKE - yusuke [at] mac.com
 * DEPENDENCIES: twitter4j - twitter4j.org
 * This is a compilation for a personal use.
 ************************ ******************************/

package com.API_Twitter;

import twitter4j.conf.*;
import twitter4j.*;
import java.util.Scanner;

/**
 * 
 * @author Velastroll - t.me/velastroll
 */
class SendMD {

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
		System.out.print("\nwho is the  receiver?: ");
		String receiver = getKeyboard.nextLine();
		System.out.print("\nwhat would you like to send?: ");
		String md = getKeyboard.nextLine();
		getKeyboard.close();
		
		sendingMD(receiver, md);
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
	 * @param receiver	{String}	
	 * @param md		[String]
	 */
	public static void sendingMD(String receiver, String md){
		try {
			DirectMessage message = twitter.sendDirectMessage(receiver, md);
			System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to send a direct message: " + te.getMessage());
			System.exit(-1);
		}
	}
}