package com.kartoflane.superluminal2.components;

public enum Races {
	HUMAN,
	ENGI,
	ENERGY,
	ROCK,
	MANTIS,
	SLUG,
	CRYSTAL,
	ANAEROBIC,
	GHOST,
	RANDOM;

	@Override
	public String toString() {
		switch (this) {
			case ENERGY:
				return "Zoltan";
			case ANAEROBIC:
				return "Lanius";
			default:
				String s = name();
				s = s.substring(0, 0) + s.substring(1).toLowerCase();
				return s;
		}
	}
}
