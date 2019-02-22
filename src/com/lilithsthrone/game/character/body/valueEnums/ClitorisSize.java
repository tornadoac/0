package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * Anything over ZERO_AVERAGE is basically a pseudo penis
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum ClitorisSize {
	
	ZERO_AVERAGE("normal-sized", 0, 1, Color.GENERIC_SIZE_ONE),
	
	ONE_BIG("big", 1, 3, Color.GENERIC_SIZE_TWO),
	
	TWO_LARGE("large", 3, 5, Color.GENERIC_SIZE_THREE),
	
	THREE_HUGE("huge", 5, 11, Color.GENERIC_SIZE_FOUR),
	
	FOUR_MASSIVE("massive", 11, 22, Color.GENERIC_SIZE_FIVE),
	
	FIVE_ENORMOUS("enormous", 22, 30, Color.GENERIC_SIZE_SIX),
	
	SIX_GIGANTIC("gigantic", 30, 38, Color.GENERIC_SIZE_SEVEN),
	
	SEVEN_STALLION("stallion-sized", 38, 50, Color.GENERIC_SIZE_EIGHT);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Color color;

	private ClitorisSize(String descriptor, int minimumValue, int maximumValue, Color color) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.color = color;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return minimumValue + (maximumValue - minimumValue) / 2;
	}

	public static ClitorisSize getClitorisSizeFromInt(int inches) {
		for(ClitorisSize cs : ClitorisSize.values()) {
			if(inches>=cs.getMinimumValue() && inches<cs.getMaximumValue()) {
				return cs;
			}
		}
		return SEVEN_STALLION;
	}

	/**
	 * To fit into a sentence: "Your clitoris is "+getDescriptor()+"." "Your "
	 * getDescriptor()+" cock is dribbling precum."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public Color getColor() {
		return color;
	}
}
