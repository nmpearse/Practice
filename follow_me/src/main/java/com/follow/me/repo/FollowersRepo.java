package com.follow.me.repo;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.follow.me.pojo.Followers;

public interface FollowersRepo extends CrudRepository<Followers, Long>{
	
	
	List<Followers> findByUserId(Long userId);
	
	List<Followers> findByFolloweeId(Long followeeId);


}
