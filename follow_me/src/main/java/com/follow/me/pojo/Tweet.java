package com.follow.me.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tweet {
	
	@Id
	@GeneratedValue
	private long id;
	private String message;
	private long userId;
	private Date tweetedOn;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getTweetedOn() {
		return tweetedOn;
	}
	public void setTweetedOn(Date tweetedOn) {
		this.tweetedOn = tweetedOn;
	}
	
	

}
