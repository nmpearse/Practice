package com.follow.me.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Followers {
	
	@Id
	@GeneratedValue
	private long id;
	private long userId;
	private long followeeId;
	private Date followedOn;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getFolloweeId() {
		return followeeId;
	}
	public void setFolloweeId(long followeeId) {
		this.followeeId = followeeId;
	}
	public Date getFollowedOn() {
		return followedOn;
	}
	public void setFollowedOn(Date followedOn) {
		this.followedOn = followedOn;
	}
	
	
	
	

}
