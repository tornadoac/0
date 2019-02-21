package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * Measured in inches.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum HairLength {
	/** Bald */
	ZERO_BALD("bald", 0, 1, Color.GENERIC_SIZE_ONE),
	/** Very short */
	ONE_VERY_SHORT("very short", 1, 3, Color.GENERIC_SIZE_TWO),
	/** Short */
	TWO_SHORT("short", 3, 6, Color.GENERIC_SIZE_THREE),
	/** above the shoulders */
	THREE_SHOULDER_LENGTH("shoulder-length", 6, 12, Color.GENERIC_SIZE_FOUR),
	/** Reaching down to mid-back */
	FOUR_MID_BACK("long", 12, 25, Color.GENERIC_SIZE_FIVE),
	/** Reaching down to just above the ass */
	FIVE_ABOVE_ASS("very long", 25, 40, Color.GENERIC_SIZE_SIX),
	/** Reaching down to below the ass */
	SIX_BELOW_ASS("incredibly long", 40, 70, Color.GENERIC_SIZE_SEVEN),
	/** Hair so long that it reaches the floor */
	SEVEN_TO_FLOOR("floor-length", 70, 140, Color.GENERIC_SIZE_EIGHT);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Color color;

	private HairLength(String descriptor, int minimumValue, int maximumValue, Color color) {
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
	
	public static HairLength getShorter(int currentHairLength){
		if (currentHairLength <= ZERO_BALD.maximumValue)
			return ZERO_BALD;
		else if (currentHairLength <= ONE_VERY_SHORT.maximumValue)
			return ZERO_BALD;
		else if (currentHairLength <= TWO_SHORT.maximumValue)
			return ONE_VERY_SHORT;
		else if (currentHairLength <= THREE_SHOULDER_LENGTH.maximumValue)
			return TWO_SHORT;
		else if (currentHairLength <= FOUR_MID_BACK.maximumValue)
			return THREE_SHOULDER_LENGTH;
		else if (currentHairLength <= FIVE_ABOVE_ASS.maximumValue)
			return FOUR_MID_BACK;
		else if (currentHairLength <= SIX_BELOW_ASS.maximumValue)
			return FIVE_ABOVE_ASS;
		else
			return SIX_BELOW_ASS;
	}
	
	public static HairLength getLonger(int currentHairLength){
		if (currentHairLength <= ZERO_BALD.maximumValue)
			return ONE_VERY_SHORT;
		else if (currentHairLength <= ONE_VERY_SHORT.maximumValue)
			return TWO_SHORT;
		else if (currentHairLength <= TWO_SHORT.maximumValue)
			return THREE_SHOULDER_LENGTH;
		else if (currentHairLength <= THREE_SHOULDER_LENGTH.maximumValue)
			return FOUR_MID_BACK;
		else if (currentHairLength <= FOUR_MID_BACK.maximumValue)
			return FIVE_ABOVE_ASS;
		else if (currentHairLength <= FIVE_ABOVE_ASS.maximumValue)
			return SIX_BELOW_ASS;
		else if (currentHairLength <= SIX_BELOW_ASS.maximumValue)
			return SEVEN_TO_FLOOR;
		else
			return SEVEN_TO_FLOOR;
	}

	public static HairLength getHairLengthFromInt(int hairLength) {
		for(HairLength hl : HairLength.values()) {
			if(hairLength>=hl.getMinimumValue() && hairLength<hl.getMaximumValue()) {
				return hl;
			}
		}
		return SEVEN_TO_FLOOR;
	}

	/**
	 * An indication of hair length.
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public Color getColor() {
		return color;
	}
}
