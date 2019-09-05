package com.follow.me.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.follow.me.pojo.Tweet;


public interface TweetRepo extends CrudRepository<Tweet, Long> {

	@Query("FROM Tweet WHERE userId = ?1 ORDER BY id desc")
	List<Tweet> findByuserIdOrderById(Long userId);
}
