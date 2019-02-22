package com.lilithsthrone.game.character.race;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum Disposition {

	CIVILIZED("civilized", Color.CLOTHING_GREEN),
	NEUTRAL("neutral", Color.CLOTHING_BLUE),
	UNPREDICTABLE("unpredictable", Color.CLOTHING_ORANGE),
	SAVAGE("savage", Color.CLOTHING_RED);

	private String name;
	private Color color;

	private Disposition(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}
}
