package com.follow.me.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.follow.me.pojo.StringDTO;
import com.follow.me.pojo.Tweet;
import com.follow.me.pojo.User;
import com.follow.me.service.TweetService;

@RestController
@RequestMapping("/api/v1")
public class FollowMeController {
  
@Autowired
  private TweetService service;
  /**
   * Get all users list.
   *
   * @return the list
   */
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return service.getUsers();
  }
  
  @GetMapping("/user/timeline/{userId}")
  public List<Tweet> getUserTimeLine(@PathVariable(value = "userId") Long userId) {
	  return service.getUserTimeline(userId);
  }
  
  @GetMapping("/home/timeline/{userId}")
  public List<Tweet> getHomeTimeline(@PathVariable(value = "userId") Long userId) {
	  return service.getHomeTimeline(userId);
  }
  
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody StringDTO message) {
	  return service.addUser(message.getValue());
	}
	
	@PutMapping("/follow/{userId}/{followeeId}")
	public ResponseEntity<String> startFollowing(@PathVariable(value = "userId") Long userId, @PathVariable(value = "followeeId") Long followeeId) {
	  service.startFollowing(userId, followeeId);
	  return ResponseEntity.ok().body("Success");
	}
	
	@PostMapping("/tweet/{userId}")
	public ResponseEntity<String> sendTweet(@PathVariable(value = "userId") Long userId, @Valid @RequestBody StringDTO message) {
	  service.sendTweet(userId, message.getValue());
	  return ResponseEntity.ok().body("Success");
	}
  
//  
//  /**
//   * Gets users by id.
//   *
//   * @param userId the user id
//   * @return the users by id
//   * @throws ResourceNotFoundException the resource not found exception
//   */
//  @GetMapping("/users/{id}")
//  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
//      throws ResourceNotFoundException {
//    User user =
//        userRepository
//            .findById(userId)
//            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
//    return ResponseEntity.ok().body(user);
//  }
//  /**
//   * Create user user.
//   *
//   * @param user the user
//   * @return the user
//   */
//  @PostMapping("/users")
//  public User createUser(@Valid @RequestBody User user) {
//    return userRepository.save(user);
//  }
//  /**
//   * Update user response entity.
//   *
//   * @param userId the user id
//   * @param userDetails the user details
//   * @return the response entity
//   * @throws ResourceNotFoundException the resource not found exception
//   */
//  @PutMapping("/users/{id}")
//  public ResponseEntity<User> updateUser(
//      @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
//      throws ResourceNotFoundException {
//    User user =
//        userRepository
//            .findById(userId)
//            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
//    user.setEmail(userDetails.getEmail());
//    user.setLastName(userDetails.getLastName());
//    user.setFirstName(userDetails.getFirstName());
//    user.setUpdatedAt(new Date());
//    final User updatedUser = userRepository.save(user);
//    return ResponseEntity.ok(updatedUser);
//  }
//  /**
//   * Delete user map.
//   *
//   * @param userId the user id
//   * @return the map
//   * @throws Exception the exception
//   */
//  @DeleteMapping("/user/{id}")
//  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
//    User user =
//        userRepository
//            .findById(userId)
//            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
//    userRepository.delete(user);
//    Map<String, Boolean> response = new HashMap<>();
//    response.put("deleted", Boolean.TRUE);
//    return response;
//  }
}