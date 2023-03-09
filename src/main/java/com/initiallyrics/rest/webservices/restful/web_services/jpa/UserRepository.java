package com.initiallyrics.rest.webservices.restful.web_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.initiallyrics.rest.webservices.restful.web_services.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
}
