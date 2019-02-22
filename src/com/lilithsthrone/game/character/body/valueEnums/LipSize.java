package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum LipSize {

	ZERO_THIN(0, "thin", Color.GENERIC_SIZE_ONE),
	ONE_AVERAGE(1, "average-sized", Color.GENERIC_SIZE_TWO),
	TWO_FULL(2, "full", Color.GENERIC_SIZE_THREE),
	THREE_PLUMP(3, "plump", Color.GENERIC_SIZE_FOUR),
	FOUR_HUGE(4, "huge", Color.GENERIC_SIZE_FIVE);
	
	private int value;
	private String descriptor;
	private Color color;

	private LipSize(int value, String descriptor, Color color) {
		this.value = value;
		this.descriptor = descriptor;
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}
	
	public static LipSize getLipSizeFromInt(int inches) {
		for(LipSize ls : LipSize.values()) {
			if(inches == ls.getValue()) {
				return ls;
			}
		}
		return ZERO_THIN;
	}
	
	public static int getLargest() {
		int largest = ZERO_THIN.value;
		for(LipSize ls : LipSize.values()) {
			largest = Math.max(largest, ls.value);
		}
		return largest;
	}

	public Color getColor() {
		return color;
	}
}
