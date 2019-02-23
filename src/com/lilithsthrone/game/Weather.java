package com.lilithsthrone.game;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.0
 * @version 0.1.96
 * @author Innoxia
 */
public enum Weather {

	CLOUD("cloudy", Color.GENERIC_GOOD),

	CLEAR("clear", Color.GENERIC_GOOD),

	RAIN("raining", Color.GENERIC_BAD),

	SNOW("snowing", Color.GENERIC_BAD),

	MAGIC_STORM_GATHERING("stormy sky", Color.GENERIC_ARCANE),

	MAGIC_STORM("arcane storm", Color.GENERIC_ARCANE);

	private String name;
	private Color color;

	private Weather(String name, Color color) {
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
