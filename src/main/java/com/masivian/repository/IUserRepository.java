package com.masivian.repository;

import java.util.List;
import com.masivian.model.User;

public interface IUserRepository {   
	boolean saveUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(String id);
	User UserById(String id);
	List<User> AllUser();
}
