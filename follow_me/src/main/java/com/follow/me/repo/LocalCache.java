package com.follow.me.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.follow.me.pojo.Tweet;

public class LocalCache {
		
	private LocalCache() {}
	
	/**
	 * Mapping contains tweets sent by an user corresponding to their user id
	 * */
	private static Map<Long, List<Tweet>> tweetsByUser = new HashMap<>();
	
	/**
	 * Mapping contains tweets sent by the followers of a user
	 * */
	private static Map<Long, List<Tweet>> homeTimeLineTweets = new HashMap<>();
	
	/**
	 * Mapping contains list of users who are follower of an user
	 * */
	private static Map<Long, List<Long>> followers = new HashMap<>();

	public static void fanOutTweet(Tweet t) {
		List<Long> followersList = followers.getOrDefault(t.getUserId(), new ArrayList<>());
		for (Long followerId: followersList){
			List<Tweet> tweets = homeTimeLineTweets.getOrDefault(followerId, new ArrayList<>());
			tweets.add(t);
			homeTimeLineTweets.put(followerId, tweets);
		}
		
	}

	public static Map<Long, List<Tweet>> getTweetsByUser() {
		return tweetsByUser;
	}

	public static Map<Long, List<Tweet>> getHomeTimeLineTweets() {
		return homeTimeLineTweets;
	}
	
	public static Map<Long, List<Long>> getFollowers() {
		return followers;
	}
}
