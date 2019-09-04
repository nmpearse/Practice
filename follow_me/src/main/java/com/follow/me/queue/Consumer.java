package com.follow.me.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.follow.me.pojo.Tweet;
import com.follow.me.repo.LocalCache;

public class Consumer extends Thread {
	BlockingQueue<Tweet> queue;
	boolean stop = false;
	public Consumer(BlockingQueue<Tweet> queue) {
		super("Consumer");
		this.queue = queue;
	}

	@Override
	public void run() {
		while(!this.stop) {
			try {
				Tweet t = queue.poll(1000, TimeUnit.MILLISECONDS);
				LocalCache.fanOutTweet(t);
			} catch (Exception e) {
				// Do nothing
			}
		}
	}
	
	public void stopThread() {
		this.stop = true;
	}
}