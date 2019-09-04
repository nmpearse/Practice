package com.follow.me.repo;

import java.util.List;

import com.follow.me.pojo.Tweet;
import com.follow.me.pojo.User;

public interface IRepo {

	public User addUser(String name);

	public Tweet sendTweet(long userId, String tweetMsg);

	public List<Tweet> getUserTimeline(long userId);

	public List<Tweet> getHomeTimeline(long userId);
	
	public void startFollowing(long userId, long followedId);
	
	public List<User> getUsersList();

}
