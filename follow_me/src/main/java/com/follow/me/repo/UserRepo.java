package com.follow.me.repo;


import org.springframework.data.repository.CrudRepository;

import com.follow.me.pojo.User;

public interface UserRepo extends CrudRepository<User, Long>{


}
