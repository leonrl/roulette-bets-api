package com.masivian.enums;

public enum ColorEnum {
	NEGRO {
		@Override
		public String getNombreColor() {
			return "negro";
		}
	},
	ROJO {
		@Override
		public String getNombreColor() {
			return "rojo";
		}
	};

	abstract public String getNombreColor();

	public static boolean validColor(String color) {
		for (ColorEnum e : ColorEnum.values()) {
			if (e.getNombreColor().equalsIgnoreCase(color)) {
				return true;
			}
		}

		return false;
	}

}
