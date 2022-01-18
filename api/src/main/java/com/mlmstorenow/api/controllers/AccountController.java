package com.mlmstorenow.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mlmstorenow.api.models.User;
import com.mlmstorenow.api.services.UserService;

@RestController
@RequestMapping("/user")
public class AccountController {

	@Autowired
	UserService userv;

	@PostMapping("/register")
	public ResponseEntity<?> registration(User user) { 
		System.out.println("in register controller");
		
		if(userv.login(user.getEmail(), user.getPassword()) == null) {
			userv.insertUser(user);
		return new ResponseEntity<>(null,HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.CONFLICT);
	}

	@PostMapping("/login ")
	public ResponseEntity<?> login(@Valid @RequestBody User user) {

		Optional<?> userlogin = userv.login(user.getEmail(), user.getPassword());
		
		if (userlogin.get().getClass().getName().equals("com.mlmstorenow.models.User")) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else if (userlogin.get().equals("User not found")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

	}

}
