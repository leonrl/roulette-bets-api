package com.masivian.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.masivian.model.User;
import com.masivian.repository.IUserRepository;
import com.masivian.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public ResponseEntity<User> saveUser(User user) {
		userRepository.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<User> userById(String id) {
		User user = userRepository.UserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<User>> allUser() {
		List<User> user = userRepository.AllUser();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}
}
