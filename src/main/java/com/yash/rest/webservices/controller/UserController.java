package com.yash.rest.webservices.controller;



import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yash.rest.webservices.entity.User;
import com.yash.rest.webservices.exception.UserNotFoundException;
import com.yash.rest.webservices.services.UserDAOService;

@RestController
public class UserController {

	@Autowired
	private UserDAOService userDAOService;
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return userDAOService.findAll();
	}
		
	@GetMapping("users/{id}")
	public Resource<User> retriveUser(@PathVariable int id){
		User user = userDAOService.findOne(id);
		if(user==null){
			throw new UserNotFoundException("id-"+ id);
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userDAOService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id){
		User user = userDAOService.deleteById(id);
		if(user==null){
			throw new UserNotFoundException("id-"+ id);
		}
//		return user;
	}
}
