package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.86
 * @version 0.2.11
 * @author Innoxia
 */
public enum Height {

	// Need to standardize to 1.5 each category

//	/**6" to 2'*/
//	NEGATIVE_THREE_MIMIMUM("fairy-sized", 15, 61, Color.GENERIC_SIZE_ONE),

	/**2' to 3'6"*/
	NEGATIVE_TWO_MIMIMUM("very tiny", 61, 106, Color.GENERIC_SIZE_ONE),

	/**3'6" to 4'*/
	NEGATIVE_ONE_TINY("tiny", 106, 122, Color.GENERIC_SIZE_ONE),

	/**4' to 5'*/
	ZERO_TINY("small", 122, 152, Color.GENERIC_SIZE_ONE),

	/**5' to 5'6"*/
	ONE_SHORT("short", 152, 166, Color.GENERIC_SIZE_TWO),

	/**5'6" to 6'*/
	TWO_AVERAGE("average height", 166, 183, Color.GENERIC_SIZE_THREE),

	/**6' to 6'6"*/
	THREE_TALL("tall", 183, 198, Color.GENERIC_SIZE_FOUR),

	/**6'6" to 7'*/
	FOUR_VERY_TALL("very tall", 198, 214, Color.GENERIC_SIZE_FIVE),

	/**7' to 7'6"*/
	FIVE_ENORMOUS("towering", 214, 228, Color.GENERIC_SIZE_SIX),

	/**7'6" to 9'"*/
	SIX_GIANT("gigantic", 228, 274, Color.GENERIC_SIZE_SEVEN),

	/**9' to 12'*/
	SEVEN_COLOSSAL("colossal", 274, 366, Color.GENERIC_SIZE_EIGHT);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Color color;

	private Height(String descriptor, int minimumValue, int maximumValue, Color color) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.minimumValue = 61;
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

	/**
	 * If height is less than this value, then that height is short stature.
	 */
	public static int getShortStatureCutOff() {
		return ZERO_TINY.getMinimumValue();
	}

	public static Height getHeightFromInt(int centimeters) {
		for(Height cs : Height.values()) {
			if(centimeters >= cs.getMinimumValue() && centimeters < cs.getMaximumValue()) {
				return cs;
			}
		}
		return SEVEN_COLOSSAL;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public Color getColor() {
		return color;
	}
}
