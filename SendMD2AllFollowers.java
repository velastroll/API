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
class SendMD2AllFollowers {
	
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
		System.out.print("\nwhat would you like to send to all your followers?: ");
		String md = getKeyboard.nextLine();
		getKeyboard.close();

		sendMD2all(md);

	}
	
	/**
	 * @param md [String]
	 */
	public static void sendMD2all(String md){
        try {
            long cursor = -1;
            IDs ids;
            System.out.println("Listing followers's ids.");
            do {
                ids = twitter.getFollowersIDs(md, cursor);
                for (long id : ids.getIDs()) {
                    sendingMD(id, md);
                }
            } while ((cursor = ids.getNextCursor()) != 0);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get followers' ids: " + te.getMessage());
            System.exit(-1);
        }
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
	 * @param receiver	{long} ID's receiver.	
	 * @param md		[String]
	 */
	private static void sendingMD(long receiver, String md){
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