package com.masivian.repository;

import java.util.List;
import com.masivian.model.Roulette;

public interface IRouletteRepository {
	boolean saveRoulette(Roulette roulette);
	boolean updateRoulette(Roulette roulette);
	boolean deleteRoulette(String id);
	Roulette rouletteById(String id);
	List<Roulette> AllRoulette();
}
