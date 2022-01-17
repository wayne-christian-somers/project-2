package com.mlmstorenow.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mlmstorenow.models.User;
import com.mlmstorenow.services.UserService;

@RestController
@RequestMapping("/user")
public class AccountController {

	@Autowired
	UserService userv;

	@PostMapping("/register")
	public User registration(User user) {
		return userv.insertUser(user);
	}

	@PostMapping("/login ")
	public ResponseEntity<?> login(@Valid @RequestBody User user) {

		Optional<?> userlogin = userv.login(user.getEmail(), user.getPassword());
		if (userlogin.get().getClass().getName().equals("com.mlmstorenow.models.User")) {
			return ResponseEntity.ok(userv.insertUser(user));
		} else if (userlogin.get() == null) {
			return ResponseEntity.of(Optional.empty());
		} else {
			return ResponseEntity.ok(userlogin.get());
		}

	}

}
