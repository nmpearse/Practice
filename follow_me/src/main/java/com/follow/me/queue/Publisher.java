package com.follow.me.queue;

import java.util.concurrent.BlockingQueue;

import com.follow.me.pojo.Tweet;

public class Publisher extends Thread{
	BlockingQueue<Tweet> queue;
	boolean stop = false;
	public Publisher(BlockingQueue<Tweet> queue) {
		super("Producer");
		this.queue = queue;
	}

	@Override
	public void run() {
		while(!this.stop) {
			// Run the Publisher
		}
	}
	
	public void sendMessage(Tweet t) {
		queue.add(t);
	}
	
	public void stopThread() {
		this.stop = true;
	}
}
