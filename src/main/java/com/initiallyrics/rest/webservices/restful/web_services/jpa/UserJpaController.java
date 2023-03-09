package com.initiallyrics.rest.webservices.restful.web_services.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.initiallyrics.rest.webservices.restful.web_services.exception.UserNotFoundException;
import com.initiallyrics.rest.webservices.restful.web_services.post.Post;
import com.initiallyrics.rest.webservices.restful.web_services.post.PostRepository;
import com.initiallyrics.rest.webservices.restful.web_services.user.User;

import jakarta.validation.Valid;

@RestController
public class UserJpaController {

	private UserRepository repository;
	
	private PostRepository postRepository;

	// this constructor will automatically autowire service property, no need for
	// @Annotation
	public UserJpaController(UserRepository repository, PostRepository postRepository) {
		super();
		this.repository = repository;
		this.postRepository = postRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> getAllUser() {
		List<User> userList = repository.findAll();
		return userList;
	}

	@GetMapping("/jpa/user/{id}")
	public User getUserById(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found with id: " + id);
		}

		return user.get();
	}

	@PostMapping("/jpa/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // create link based on current httpReq.
				.path("/{id}") // appending a path variable
				.buildAndExpand(savedUser.getId())// replacing that variable with created userId
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/user/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/jpa/user/{id}/posts")
	public List<Post> getAllPostsOfUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id); // finding user
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found with id: " + id);
		}
		
		return user.get().getPosts(); //getting and returning posts of that user
	}
	
	@PostMapping("/jpa/user/{id}/post")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		
		Optional<User> user = repository.findById(id); // finding user using id
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found with id: " + id);
		}
		
		post.setUser(user.get());  // setting that user to post that needs to create, so that post should belong to specific user.
		
		Post savedPost = postRepository.save(post); // saving post 
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // create link based on current httpReq.
				.path("/{id}") // appending a path variable
				.buildAndExpand(savedPost.getId())// replacing that variable with created postId
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
