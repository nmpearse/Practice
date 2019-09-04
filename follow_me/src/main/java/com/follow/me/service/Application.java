package com.follow.me.service;

import java.util.List;

import com.follow.me.pojo.Tweet;

public class Application {

	public static void main(String[] args) {
		
		
	
		
		TweetService service = new TweetService();
		
		service.addUser("Aakancha");
		
		service.addUser("Sumang");
		
		Long user1Id = service.getUsers().stream().filter(u -> u.getName().equals("Aakancha")).findFirst().get().getId();
		Long user2Id = service.getUsers().stream().filter(u -> u.getName().equals("Sumang")).findFirst().get().getId();
		
		service.startFollowing(user2Id, user1Id);
		
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				service.sendTweet(user1Id, "Hi! I am on twitter");
			}
		});
		th.start();
		
		Thread th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				service.sendTweet(user2Id, "Hi! I am Sumang");
			}
		});
		th1.start();
		
		service.addUser("Pony");
		Long user3Id = service.getUsers().stream().filter(u -> u.getName().equals("Pony")).findFirst().get().getId();
		service.startFollowing(user3Id, user1Id);
		service.startFollowing(user3Id, user2Id);
		List<Tweet> list = service.getHomeTimeline(user2Id);
		
		list.stream().forEach(t -> System.out.println(t.getMessage()));
		
		service.getUserTimeline(user2Id).stream().forEach(t -> System.out.println(t.getMessage()));
		System.out.println("*****************Its Pony Home Timeline");
		service.getHomeTimeline(user3Id).stream().forEach(t -> System.out.println(t.getMessage()));
		
		service.destroy();
		
		//System.exit(0);
	}
}
