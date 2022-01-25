package com.mlmstorenow.api.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mlmstorenow.api.models.User;
import com.mlmstorenow.api.repositories.UserRepository;
import com.mlmstorenow.api.services.EmailService;
import com.mlmstorenow.api.services.JwtService;
import com.mlmstorenow.api.services.PaymentService;
import com.mlmstorenow.api.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

	@Autowired
	UserService userv;
	@Autowired
	JwtService jws;
	@Autowired
	EmailService eserv;
	@Autowired
	PaymentService payserv;
	@Autowired
	UserRepository urepo;

	@PostMapping("/register")
	public ResponseEntity<?> registration(@RequestBody User user) {
		String userEmail = user.getEmail();
		if (userv.login(user.getEmail(), user.getPassword()) != null) {
			User u = userv.insertUser(user);

			if(!u.equals(null)) {
			String authorization = jws.tokenGenerator(userEmail).serialize();
			
			return ResponseEntity.ok().body(authorization);
			}
			// return ResponseEntity.ok().body(null);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody User user, HttpServletResponse response) {

		Optional<?> userlogin = userv.login(user.getEmail(), user.getPassword());
		if (userlogin.get().getClass().getName().equals("com.mlmstorenow.api.models.User")) {

			String authorization = jws.tokenGenerator(user.getEmail()).serialize();

			return ResponseEntity.ok().body(authorization);
		} else if (userlogin.get().equals("User not found")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

	}

	@PatchMapping("/login")
	public ResponseEntity<?> login2(@Valid @RequestBody User user, HttpServletResponse response) {

		Optional<?> userlogin = userv.login(user.getEmail(), user.getPassword());
		if (userlogin.get().getClass().getName().equals("com.mlmstorenow.api.models.User")) {
			String authorization = jws.tokenGenerator(user.getEmail()).serialize();
			return new ResponseEntity<>(authorization, HttpStatus.I_AM_A_TEAPOT);
		} else if (userlogin.get().equals("User not found")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

	}

}
