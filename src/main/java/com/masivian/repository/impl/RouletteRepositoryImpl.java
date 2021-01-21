package com.masivian.repository.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.masivian.model.Roulette;
import com.masivian.repository.IRouletteRepository;

@Repository
public class RouletteRepositoryImpl implements IRouletteRepository {
	private static final String KEY = "ROULETTE";
	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	public boolean saveRoulette(Roulette roulette) {
		redisTemplate.opsForHash().put(KEY, roulette.getId(), roulette);
		return true;
	}
	@Override
	public boolean updateRoulette(Roulette roulette) {
		redisTemplate.opsForHash().put(KEY, roulette.getId(), roulette);
		return true;
	}
	@Override
	public boolean deleteRoulette(String id) {
		redisTemplate.opsForHash().delete(KEY, id);
		return true;
	}
	@Override
	public Roulette rouletteById(String id) {
		Roulette roulette = (Roulette) redisTemplate.opsForHash().get(KEY, id);
		return roulette;
	}

	@Override
	public List<Roulette> AllRoulette() {
		List<Roulette> roulette = redisTemplate.opsForHash().values(KEY);
		return roulette;
	}

}
