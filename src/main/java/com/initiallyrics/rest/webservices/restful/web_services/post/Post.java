package com.initiallyrics.rest.webservices.restful.web_services.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.initiallyrics.rest.webservices.restful.web_services.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min=10, message = "Description should be of minimun 10 characters long!")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore //so that when fetching post, we can't get user details.
	private User user;

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

}
