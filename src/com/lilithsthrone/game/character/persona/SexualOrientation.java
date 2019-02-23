package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.79
 * @version 0.2.7
 * @author Innoxia
 */
public enum SexualOrientation {
	ANDROPHILIC("androphilic", Color.MASCULINE, SexualOrientationPreference.THREE_AVERAGE),

	GYNEPHILIC("gynephilic",Color.FEMININE, SexualOrientationPreference.THREE_AVERAGE),

	AMBIPHILIC("ambiphilic", Color.ANDROGYNOUS, SexualOrientationPreference.THREE_AVERAGE);

	private String name;
	private Color color;
	private SexualOrientationPreference orientationPreferenceDefault;

	private SexualOrientation(String name, Color color, SexualOrientationPreference orientationPreferenceDefault) {
		this.name = name;
		this.color = color;
		this.orientationPreferenceDefault = orientationPreferenceDefault;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public SexualOrientationPreference getOrientationPreferenceDefault() {
		return orientationPreferenceDefault;
	}
}
