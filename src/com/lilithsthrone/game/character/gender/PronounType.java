package com.lilithsthrone.game.character.gender;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.86
 * @version 0.1.86
 * @author Innoxia
 */
public enum PronounType {
	FEMININE("feminine", Color.FEMININE),
	NEUTRAL("androgynous", Color.ANDROGYNOUS),
	MASCULINE("masculine", Color.MASCULINE);
	
	private String name;
	private Color color;
	
	private PronounType(String name, Color color) {
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
