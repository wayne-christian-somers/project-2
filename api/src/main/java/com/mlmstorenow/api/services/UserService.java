package com.mlmstorenow.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlmstorenow.api.customexception.UnsucessfulLoginException;
import com.mlmstorenow.api.customexception.UserNotFoundException;
import com.mlmstorenow.api.models.User;
import com.mlmstorenow.api.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userrepo;

	public User insertUser(User u) {

		return userrepo.save(u);
	}

	public Optional<?> login(String email, String password) {
		try {
			User user = userrepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException());

			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {

				return Optional.of(user);
			} else {
				throw new UnsucessfulLoginException();
			}

		} catch (UserNotFoundException un) {
			Optional<String> s = Optional.of("User not found");
			return s;
		} catch (UnsucessfulLoginException ul) {
			Optional<String> s = Optional.of("Login failed");
			return s;
		}
	}

	public Optional<?> getUserByUsername(String email) {
		try {
			User user = userrepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException());

			return Optional.of(user);
		} catch (UserNotFoundException un) {
			Optional<String> s = Optional.of("User not found");
			return s;
		}
	}
}
