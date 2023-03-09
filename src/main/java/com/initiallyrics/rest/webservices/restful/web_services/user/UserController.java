package com.initiallyrics.rest.webservices.restful.web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private UserDaoService service;

	//this constructor will automatically autowire service property, no need for @Annotation
	public UserController(UserDaoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> getAllUser() {
		List<User> userList = service.getAllUser();
		return userList;
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id) {
		User user = service.getUserById(id);
		return user;
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //create link based on current httpReq.
				.path("/{id}")  //appending a path variable
				.buildAndExpand(savedUser.getId())//replacing that variable with created userId
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
}
