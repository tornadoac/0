package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.90
 * @author Innoxia
 */
public enum AssSize {
	
	ZERO_FLAT("flat", 0, Color.GENERIC_SIZE_ONE),
	
	ONE_TINY("tiny", 1, Color.GENERIC_SIZE_TWO),
	
	TWO_SMALL("small", 2, Color.GENERIC_SIZE_THREE),
	
	THREE_NORMAL("round", 3, Color.GENERIC_SIZE_FOUR),
	
	FOUR_LARGE("large", 4, Color.GENERIC_SIZE_FIVE),
	
	FIVE_HUGE("huge", 5, Color.GENERIC_SIZE_SIX),
	
	SIX_MASSIVE("massive", 6, Color.GENERIC_SIZE_SEVEN),
	
	SEVEN_GIGANTIC("gigantic", 7, Color.GENERIC_SIZE_EIGHT);

	private String descriptor;
	private int size;
	private Color color;

	private AssSize(String descriptor, int assSize, Color color) {
		this.descriptor = descriptor;
		this.size = assSize;
		this.color = color;
	}

	public static AssSize getAssSizeFromInt(int assSize) {
		for(AssSize as : AssSize.values()) {
			if(as.getValue() == assSize) {
				return as;
			}
		}
		return SEVEN_GIGANTIC;
	}

	/**
	 * To fit into a sentence: "You have a "+getDescriptor()+" ass."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return size;
	}

	public Color getColor() {
		return color;
	}
}
