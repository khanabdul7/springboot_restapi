package com.initiallyrics.rest.webservices.restful.web_services.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.initiallyrics.rest.webservices.restful.web_services.post.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name="user_details")
public class User {

	protected User() { //default constructor is req to JPA
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=4, message="Name should have atleast 4 characters!")
	//@JsonProperty("user_name") //Customize field names in response, instead of name in response field shown as user_name.
	private String name;
	
	@Past(message="Birth Date should be from past!")
	//@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy="user") // one user can have many posts, mapped by means-> owner of posts will be user, user's id is used as FK in post table
	@JsonIgnore
	private List<Post> posts;

	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
