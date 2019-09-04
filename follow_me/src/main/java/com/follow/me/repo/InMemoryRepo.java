package com.follow.me.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.follow.me.pojo.Tweet;
import com.follow.me.pojo.User;

public class InMemoryRepo implements IRepo {

	private static Map<Long, User> users = new HashMap<>();
	/**
	 * Mapping contains list of users who an user is following
	 * */
	private static Map<Long, List<Long>> userFollowing = new HashMap<>();
	
	private static List<Tweet> tweets = new ArrayList<>();
		
	private static SequenceGenerator userSeqGenerator = new SequenceGenerator();
	private static SequenceGenerator tweetSeqGenerator = new SequenceGenerator();
	
	public long getNewUserId() {
		return userSeqGenerator.getNext();
	}
	
	public long getNewTweetId() {
		return tweetSeqGenerator.getNext();
	}
	
	public void addUser(String name) {
		User newUser = new User();
		newUser.setId(getNewUserId());
		newUser.setName(name);
		users.put(newUser.getId(), newUser);
		
	}

	public Tweet sendTweet(long userId, String tweetMsg) {
		Tweet tweet = new Tweet();
		tweet.setId(getNewTweetId());
		tweet.setUserId(userId);
		tweet.setMessage(tweetMsg);
		tweet.setTweetedOn(new Date());
		tweets.add(tweet);
		return tweet;
	}

	public List<Tweet> getUserTimeline(long userId) {
		return tweets.stream().filter(t -> t.getUserId()==userId).collect(Collectors.toList());
	}

	public List<Tweet> getHomeTimeline(long userId) {
		List<Long> followeeeList = userFollowing.getOrDefault(userId, new ArrayList<Long>());
		return tweets.stream().filter(t -> followeeeList.contains(t.getUserId())).collect(Collectors.toList());
	}
	
	public void startFollowing(long userId, long followeeId) {
		List<Long> userFollowingList = userFollowing.getOrDefault(userId, new ArrayList<Long>());
		userFollowingList.add(followeeId);
		userFollowing.put(userId, userFollowingList);
	}

	public List<User> getUsersList() {
		return new ArrayList<>(users.values());
	}
}
