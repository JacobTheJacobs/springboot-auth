package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.payload.SignupRequest;
import com.example.demo.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers() {
		List<User> findAll = userRepository.findAll();
		return findAll;
	}
	
	@PostMapping("/users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void addEmployee(@RequestBody User user) {
		System.out.println(user);
		userRepository.save(user);
	}
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User getSingleUser(@PathVariable Long id) {
		return userRepository.findById(id).get();
	}
	
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updateUser(@RequestBody User user, @PathVariable Long id) {
		User findById = userRepository.findById(id).get();
		System.out.println(findById);
		findById.setUsername(user.getUsername());
		findById.setEmail(user.getEmail());
		String encode = encoder.encode(user.getPassword());
		findById.setPassword(encode);
		userRepository.save(findById);

	}
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
	

}
