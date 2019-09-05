package com.follow.me.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.follow.me.pojo.Tweet;
import com.follow.me.pojo.User;
import com.follow.me.queue.Consumer;
import com.follow.me.queue.Publisher;
import com.follow.me.repo.IRepo;
import com.follow.me.repo.LocalCache;

@Service
public class TweetService {
	
	@Autowired
	IRepo repo;
	
	private static BlockingQueue<Tweet> queue = new LinkedBlockingQueue<>();
	private static Publisher publisher = new Publisher(queue);
	private static Consumer consumer = new Consumer(queue); 
	
	static {
		publisher.start();
		consumer.start();
	}
	
	public User addUser(String name) {
		return repo.addUser(name);
	}
	
	public void sendTweet(long userId, String tweetMsg) {
		Tweet t = repo.sendTweet(userId, tweetMsg);
		List<Tweet> userTweets = LocalCache.getTweetsByUser().getOrDefault(userId, new ArrayList<>());
		userTweets.add(t);
		LocalCache.getTweetsByUser().put(userId,userTweets);
		publisher.sendMessage(t);
	}
	
	public List<Tweet> getUserTimeline(long userId) {
		if(LocalCache.getTweetsByUser().containsKey(userId)) {
			return LocalCache.getTweetsByUser().get(userId);
		}
		return repo.getUserTimeline(userId);
	}
	
	public List<Tweet> getHomeTimeline(long userId) {
		if(LocalCache.getHomeTimeLineTweets().containsKey(userId)) {
			return LocalCache.getHomeTimeLineTweets().get(userId);
		}
		return repo.getHomeTimeline(userId);
	}
	
	public void startFollowing(long userId, long followeeId) {
		repo.startFollowing(userId, followeeId);
		List<Long> followersList = LocalCache.getFollowers().getOrDefault(followeeId, new ArrayList<>());
		followersList.add(userId);
		LocalCache.getFollowers().put(followeeId, followersList);
	}
	
	public List<User> getUsers(){
		return repo.getUsersList();
	}

	public void destroy() {
		publisher.stopThread();
		consumer.stopThread();
		publisher.interrupt();
		consumer.interrupt();
	}
}
