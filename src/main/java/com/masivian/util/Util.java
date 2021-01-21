package com.masivian.util;

import com.masivian.enums.ColorEnum;

public class Util {
	public static String generateRandomColor(int winningNumber) {
		String Color;
		if (winningNumber % 2 == 0) {
			Color = ColorEnum.ROJO.getNombreColor();
		} else {
			Color = ColorEnum.NEGRO.getNombreColor();
		}
		return Color;
	}

	public static int generateRandomNumber() {
		int max = 36;
		int min = 0;
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}

}
