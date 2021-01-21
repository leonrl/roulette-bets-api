package com.masivian.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.masivian.dto.RouletteClosingDto;
import com.masivian.dto.RouletteDto;
import com.masivian.dto.RouletteOpeningDto;
import com.masivian.model.Bet;
import com.masivian.model.Roulette;

public interface IRouletteService {
	ResponseEntity<RouletteDto> saveRoulette();
	ResponseEntity<RouletteOpeningDto> rouletteOpening(RouletteDto rouletteDto);
	ResponseEntity<Object> makeBet(String idRoulette, String userId, Bet bet);
	ResponseEntity<RouletteClosingDto> rouletteClosing(RouletteDto rouletteDto);
	ResponseEntity<List<Roulette>> allRoulette();
}
