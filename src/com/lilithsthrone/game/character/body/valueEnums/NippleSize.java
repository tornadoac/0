package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum NippleSize {

	ZERO_TINY(0, "tiny", Color.GENERIC_SIZE_ONE),
	ONE_SMALL(1, "small", Color.GENERIC_SIZE_TWO),
	TWO_BIG(2, "big", Color.GENERIC_SIZE_THREE),
	THREE_LARGE(3, "large", Color.GENERIC_SIZE_FOUR),
	FOUR_MASSIVE(4, "massive", Color.GENERIC_SIZE_FIVE);

	private int value;
	private String descriptor;
	private Color color;

	private NippleSize(int value, String descriptor, Color color) {
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

	public static NippleSize getNippleSizeFromInt(int inches) {
		for(NippleSize as : NippleSize.values()) {
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
