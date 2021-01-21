package com.masivian.repository.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.masivian.model.User;
import com.masivian.repository.IUserRepository;

@Repository
public class UserRepositoryImpl implements IUserRepository {
    private static final String KEY = "USER";
    @Autowired
    private RedisTemplate redisTemplate;
	@Override
	public boolean saveUser(User user) {
		redisTemplate.opsForHash().put(KEY, user.getId(), user);
		return true;
	}
	@Override
	public boolean updateUser(User user) {
		redisTemplate.opsForHash().put(KEY, user.getId(), user);
		return true;
	}
	@Override
	public boolean deleteUser(String id) {
		redisTemplate.opsForHash().delete(KEY, id);
		return true;
	}
	@Override
	public User UserById(String id) {
		User user = (User) redisTemplate.opsForHash().get(KEY, id);
		return user;
	}
	@Override
	public List<User> AllUser() {
		List<User> users = redisTemplate.opsForHash().values(KEY);
		return users;
	}
}
