package com.masivian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masivian.dto.GenericAnswer;
import com.masivian.dto.RouletteClosingDto;
import com.masivian.dto.RouletteDto;
import com.masivian.dto.RouletteOpeningDto;
import com.masivian.enums.ColorEnum;
import com.masivian.exception.ModeloNotFoundException;
import com.masivian.model.Bet;
import com.masivian.model.Roulette;
import com.masivian.model.User;
import com.masivian.repository.IRouletteRepository;
import com.masivian.repository.IUserRepository;
import com.masivian.service.IRouletteService;
import com.masivian.util.Util;

@Service
public class RouletteServiceImpl implements IRouletteService {

	@Autowired
	private IRouletteRepository rouletteRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public ResponseEntity<RouletteDto> saveRoulette() {
		Roulette roulette = new Roulette();
		RouletteDto rouletteDto = new RouletteDto();
		roulette.setId(UUID.randomUUID().toString());
		roulette.setState(false);
		rouletteDto.setId(roulette.getId());
		rouletteRepository.saveRoulette(roulette);

		return new ResponseEntity<RouletteDto>(rouletteDto, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RouletteOpeningDto> rouletteOpening(RouletteDto rouletteDto) {
		RouletteOpeningDto rouletteOpeningDto = new RouletteOpeningDto();
		Roulette roulette = rouletteRepository.rouletteById(rouletteDto.getId());
		if (roulette == null) {
			throw new ModeloNotFoundException("Denegada = No existe la ruleta: " + rouletteDto.getId());
		} else if (roulette.isState() == true) {
			rouletteOpeningDto.setState(false);
			rouletteOpeningDto.setMessage("Denegada = La ruleta ya se encuentra en estado activa.");
		} else if (roulette.isState() == false) {
			roulette.setState(true);
			rouletteRepository.updateRoulette(roulette);
			rouletteOpeningDto.setState(true);
			rouletteOpeningDto.setMessage("Exitosa = Apertura de la ruleta realizada correctamente.");
		}

		return new ResponseEntity<RouletteOpeningDto>(rouletteOpeningDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> makeBet(String idRoulette, String userId, Bet bet) {
		Roulette roulette = rouletteRepository.rouletteById(idRoulette);
		User user = userRepository.UserById(userId);
		List<String> errors = betValidations(idRoulette, roulette, user, bet);
		if (!errors.isEmpty()) {
			GenericAnswer genericAnswer = new GenericAnswer();
			genericAnswer.setTimestamp(new Date());
			genericAnswer.setMessage("Errores de validación.");
			genericAnswer.setErrors(errors);

			return new ResponseEntity<Object>(genericAnswer, HttpStatus.OK);
		}

		bet.setId(UUID.randomUUID().toString());
		bet.setUser(user);
		roulette.getBets().add(bet);
		rouletteRepository.updateRoulette(roulette);
		return new ResponseEntity<Object>(bet, HttpStatus.CREATED);
	}

	private List<String> betValidations(String idRoulette, Roulette roulette, User user, Bet bet) {
		List<String> errors = new ArrayList<String>();
		if (roulette == null) {
			throw new ModeloNotFoundException("Denegada = No existe la ruleta: " + idRoulette);
		}
		if (roulette.isState() == false) {
			throw new ModeloNotFoundException("Denegada = La ruleta está cerrada: " + idRoulette);
		}
		if (user == null) {
			throw new ModeloNotFoundException("No se ha encontrado el usuario");
		}
		if (!(bet.getNumber() >= 0 && bet.getNumber() <= 36)) {
			errors.add("Los números válidos para apostar son del 0 al 36");
		}
		if (!ColorEnum.validColor(bet.getColor())) {
			errors.add("Los colores válidos son (negro o rojo)");
		}
		if (!(bet.getMoney() >= 1 && bet.getMoney() <= 10000)) {
			errors.add("Cantidad determinada de dinero (máximo 10.000 dólares)");
		}
		if (user.getMoney() < bet.getMoney()) {
			errors.add("El cliente No tiene el crédito necesario para realizar la apuesta.");
		}

		return errors;
	}

	@Override
	public ResponseEntity<RouletteClosingDto> rouletteClosing(RouletteDto rouletteDto) {
		Roulette roulette = rouletteRepository.rouletteById(rouletteDto.getId());
		if (roulette == null) {
			throw new ModeloNotFoundException("Denegada = No existe la ruleta: " + rouletteDto.getId());
		}
		if (roulette.isState() == false) {
			throw new ModeloNotFoundException("Denegada = La ruleta está cerrada: " + rouletteDto.getId());
		}

		RouletteClosingDto rouletteClosingDto = new RouletteClosingDto();
		rouletteWinners(roulette, rouletteClosingDto);
		roulette.setState(false);
		rouletteRepository.updateRoulette(roulette);
		return new ResponseEntity<RouletteClosingDto>(rouletteClosingDto, HttpStatus.CREATED);
	}

	private void rouletteWinners(Roulette roulette, RouletteClosingDto rouletteClosingDto) {
		List<Bet> winners = new ArrayList<>();
		List<Bet> losers = new ArrayList<>();
		Map<String, User> usersMap = new HashMap<String, User>();
		int winningNumber = Util.generateRandomNumber();
		String winningColor = Util.generateRandomColor(winningNumber);
		double discountMoney = 0;
		for (Bet bet : roulette.getBets()) {
			if (bet.getNumber() == winningNumber || bet.getColor().equalsIgnoreCase(winningColor)) {
				winners.add(bet);
				receiveAwardWinners(bet, winningNumber, winningColor);
				usersMap.put(bet.getUser().getId(), bet.getUser());
			} else {
				losers.add(bet);
				discountMoney = discountMoney + bet.getMoney();
				bet.getUser().setMoney(discountMoney);
				usersMap.put(bet.getUser().getId(), bet.getUser());
			}
		}
		rouletteClosingDto.setBets(roulette.getBets());
		rouletteClosingDto.setTotalWinners(winners.size());
		rouletteClosingDto.setTotalLosers(losers.size());
		updateMoneyUsers(usersMap);
	}

	private void receiveAwardWinners(Bet bet, int winningNumber, String winningColor) {
		if (bet.getNumber() == winningNumber) {
			double earnedMoney = bet.getUser().getMoney() * 5;
			bet.getUser().setMoney(earnedMoney);
		}
		if (bet.getColor().equalsIgnoreCase(winningColor)) {
			double earnedMoney = bet.getUser().getMoney() * 1.8;
			bet.getUser().setMoney(earnedMoney);
		}
	}

	private void updateMoneyUsers(Map<String, User> usersMap) {
		for (Entry<String, User> user : usersMap.entrySet()) {
			userRepository.updateUser(user.getValue());
		}
	}

	@Override
	public ResponseEntity<List<Roulette>> allRoulette() {
		List<Roulette> roulette = rouletteRepository.AllRoulette();

		return new ResponseEntity<List<Roulette>>(roulette, HttpStatus.OK);
	}
}
