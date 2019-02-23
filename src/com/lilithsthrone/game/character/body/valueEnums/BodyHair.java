package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.86
 * @author Innoxia
 */
public enum BodyHair {

	ZERO_NONE(0, "none", Color.GENERIC_SIZE_ONE),
	ONE_STUBBLE(1, "stubble", Color.GENERIC_SIZE_TWO),
	TWO_MANICURED(2, "manicured", Color.GENERIC_SIZE_THREE),
	THREE_TRIMMED(3, "trimmed", Color.GENERIC_SIZE_FOUR),
	FOUR_NATURAL(4, "natural", Color.GENERIC_SIZE_FIVE),
	FIVE_UNKEMPT(6, "unkempt", Color.GENERIC_SIZE_SIX),
	SIX_BUSHY(7, "bushy", Color.GENERIC_SIZE_SEVEN),
	SEVEN_WILD(8, "wild", Color.GENERIC_SIZE_EIGHT);

	private int value;
	private String descriptor;
	private Color color;

	private BodyHair(int value, String descriptor, Color color) {
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

	public Color getColor() {
		return color;
	}

	public static BodyHair getRandomBodyHair() {
		return BodyHair.values()[Util.random.nextInt(BodyHair.values().length)];
	}

	public static BodyHair getBodyHairFromValue(int value) {
		for(BodyHair bh : BodyHair.values()) {
			if(bh.getValue() == value) {
				return bh;
			}
		}
		return ZERO_NONE;
	}
}
