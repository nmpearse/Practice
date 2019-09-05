package com.follow.me.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.follow.me.pojo.Followers;
import com.follow.me.pojo.Tweet;
import com.follow.me.pojo.User;

@Repository
@Primary
public class H2Repo implements IRepo {

	@Autowired
	FollowersRepo followerRepo;

	@Autowired
	TweetRepo tweetRepo;
	
	@Autowired
	UserRepo userRepo;	
	
	@Override
	public User addUser(String name) {
		User newUser = new User();
		newUser.setName(name);
		userRepo.save(newUser);
		return newUser;
	}

	@Override
	public Tweet sendTweet(long userId, String tweetMsg) {
		Tweet tweet = new Tweet();
		tweet.setUserId(userId);
		tweet.setMessage(tweetMsg);
		tweet.setTweetedOn(new Date());
		tweetRepo.save(tweet);
		return tweet;
	}

	@Override
	public List<Tweet> getUserTimeline(long userId) {
		List<Followers> followeeList = followerRepo.findByUserId(userId);
		List<Tweet> tweetList = new ArrayList<>();
		followeeList.stream().forEach(follower -> 
			tweetList.addAll(tweetRepo.findByuserIdOrderById(follower.getFolloweeId()))
		);
		return tweetList;
	}

	@Override
	public List<Tweet> getHomeTimeline(long userId) {
		return tweetRepo.findByuserIdOrderById(userId);
	}

	@Override
	public void startFollowing(long userId, long followedId) {
		Followers follower = new Followers();
		follower.setUserId(userId);
		follower.setFolloweeId(followedId);
		follower.setFollowedOn(new Date());
		followerRepo.save(follower);
	}

	@Override
	public List<User> getUsersList() {
		List<User> userList = new ArrayList<>();
		userRepo.findAll().forEach(userList::add);
		return userList;
	}

}
