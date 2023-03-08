package com.initiallyrics.rest.webservices.restful.web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.initiallyrics.rest.webservices.restful.web_services.exception.UserNotFoundException;

@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<>();
	
	private static int id = 0;
	
	static {
		userList.add(new User(++id, "abdul", LocalDate.now().minusYears(24)));
		userList.add(new User(++id, "rohit", LocalDate.now().minusYears(25)));
		userList.add(new User(++id, "virhant", LocalDate.now().minusYears(23)));
	}
	
	public List<User> getAllUser(){
		return userList;
	}
	
	public User getUserById(int id) {
		Predicate<User> predicate = user -> user.getId().equals(id);
		User user = userList.stream().filter(predicate).findFirst().orElse(null);
		if (user == null) {
			throw new UserNotFoundException("User Not Found with id: "+ id);
		}
		return user;
	}
	
	public User createUser(User user) {
		user.setId(++id);
		userList.add(user);
		return user;
	}
	
}
