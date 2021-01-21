package com.masivian.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.masivian.model.User;

public interface IUserService {
	ResponseEntity<User> saveUser(User user);
	ResponseEntity<User> userById(String id);
	ResponseEntity<List<User>> allUser();
}
