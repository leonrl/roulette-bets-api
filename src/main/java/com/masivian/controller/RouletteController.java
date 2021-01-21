package com.masivian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.dto.RouletteClosingDto;
import com.masivian.dto.RouletteDto;
import com.masivian.dto.RouletteOpeningDto;
import com.masivian.model.Bet;
import com.masivian.model.Roulette;
import com.masivian.service.IRouletteService;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

	@Autowired
	private IRouletteService rouletteService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RouletteDto> createRoulette() {
		return rouletteService.saveRoulette();
	}

	@PutMapping(value = "/opening", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RouletteOpeningDto> rouletteOpening(@RequestBody RouletteDto rouletteDto) {
		return rouletteService.rouletteOpening(rouletteDto);
	}

	@PostMapping(value = "/{idRoulette:.*}/bet", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> makeBet(@RequestHeader(value = "userId") String userId,
			@PathVariable(value = "idRoulette") String idRoulette, @RequestBody Bet bet) {
		return rouletteService.makeBet(idRoulette, userId, bet);
	}

	@PutMapping(value = "/closing", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RouletteClosingDto> rouletteClosing(@RequestBody RouletteDto rouletteDto) {
		return rouletteService.rouletteClosing(rouletteDto);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Roulette>> allRoulette() {
		return rouletteService.allRoulette();
	}

}
