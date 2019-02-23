package com.lilithsthrone.game.character.gender;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.69
 * @version 0.1.69
 * @author Innoxia
 */
public enum AndrogynousIdentification {
	FEMININE("feminine", Color.FEMININE),
	CLOTHING_FEMININE("clothing feminine", Color.ANDROGYNOUS),
	CLOTHING_MASCULINE("clothing masculine", Color.ANDROGYNOUS),
	MASCULINE("masculine", Color.MASCULINE);

	private String name;
	private Color color;

	private AndrogynousIdentification(String name, Color color) {
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
