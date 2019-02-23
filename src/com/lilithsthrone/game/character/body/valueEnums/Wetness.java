package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 *
 * @since 0.1.0
 * @version 0.1.90
 * @author Innoxia
 */
public enum Wetness {

	ZERO_DRY("dry", 0, 101, Color.GENERIC_SIZE_ONE),

	ONE_SLIGHTLY_MOIST("slightly moist", 1, 100, Color.GENERIC_SIZE_TWO),

	TWO_MOIST("moist", 2, 50, Color.GENERIC_SIZE_THREE),

	THREE_WET("wet", 3, 25, Color.GENERIC_SIZE_FOUR),

	FOUR_SLIMY("slimy", 4, 0, Color.GENERIC_SIZE_FIVE),

	FIVE_SLOPPY("sloppy", 5, 0, Color.GENERIC_SIZE_SIX),

	SIX_SOPPING_WET("sopping wet", 6, 0, Color.GENERIC_SIZE_SEVEN),

	SEVEN_DROOLING("drooling", 7, 0, Color.GENERIC_SIZE_EIGHT);

	private int wetness, arousalNeededToGetVaginaWet;
	private String descriptor;
	private Color color;

	private Wetness(String descriptor, int wetness, int arousalNeededToGetVaginaWet, Color color) {
		this.descriptor = descriptor;
		this.wetness = wetness;
		this.arousalNeededToGetVaginaWet = arousalNeededToGetVaginaWet;
		this.color = color;
	}

	public static Wetness valueOf(int wetness) {
		for(Wetness w : Wetness.values()) {
			if(wetness == w.getValue()) {
				return w;
			}
		}
		return SEVEN_DROOLING;
	}

	/**
	 * To fit into a sentence: "Your vagina is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" asshole is stretched wide open."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return wetness;
	}

	public int getArousalNeededToGetVaginaWet() {
		return arousalNeededToGetVaginaWet;
	}

	public Color getColor() {
		return color;
	}
}
