package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum AreolaeSize {

	ZERO_TINY(0, "tiny", Color.GENERIC_SIZE_ONE),
	ONE_SMALL(1, "small", Color.GENERIC_SIZE_TWO),
	TWO_BIG(2, "average-sized", Color.GENERIC_SIZE_THREE),
	THREE_LARGE(3, "large", Color.GENERIC_SIZE_FOUR),
	FOUR_MASSIVE(4, "massive", Color.GENERIC_SIZE_FIVE);

	private int value;
	private String descriptor;
	private Color color;

	private AreolaeSize(int value, String descriptor, Color color) {
		this.value = value;
		this.descriptor = descriptor;
		this.color=color;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}

	public static AreolaeSize getAreolaeSizeFromInt(int inches) {
		for(AreolaeSize as : AreolaeSize.values()) {
			if(inches == as.getValue()) {
				return as;
			}
		}
		return ZERO_TINY;
	}

	public Color getColor() {
		return color;
	}
}
