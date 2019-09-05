package com.follow.me.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"com.follow.me.pojo"}) 
@EnableJpaRepositories(basePackages={"com.follow.me.repo"}) 
@ComponentScan(basePackages={"com.follow.me.controller", "com.follow.me.service", "com.follow.me.repo"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		/*TweetService service = new TweetService();

		service.addUser("Aakancha");

		service.addUser("Sumang");

		Long user1Id = service.getUsers().stream().filter(u -> u.getName().equals("Aakancha")).findFirst().get()
				.getId();
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

		System.exit(0);*/
	}
}
