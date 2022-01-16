package com.mlmstorenow.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlmstorenow.customexception.UnsucessfulLoginException;
import com.mlmstorenow.customexception.UserNotFoundException;
import com.mlmstorenow.models.User;
import com.mlmstorenow.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userrepo;
	
	public User insertUser(User u) {
		
		return userrepo.save(u);
	}
	
	public Optional<?> login(String email, String password) {
		try {
		User user= userrepo.findByEmail(email).orElseThrow(()->new UserNotFoundException());
			
		if(user.getEmail()==email && user.getPassword()== password) {
			
			return Optional.of(user);
		}else {
			throw new UnsucessfulLoginException();
		}
			
		}catch(UserNotFoundException un) {
			Optional<String> s = Optional.of("User not found");
			return s;
		}catch(UnsucessfulLoginException ul) {
			Optional<String> s = Optional.of("Login failed");
			return s;
		}
	}
}
