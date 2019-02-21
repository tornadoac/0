package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.2.1
 * @version 0.2.1
 * @author Innoxia
 */
public enum PenisGirth {

	ZERO_SLENDER(0, "slender", Color.GENERIC_SIZE_ONE),
	ONE_THIN(1, "thin", Color.GENERIC_SIZE_TWO),
	TWO_AVERAGE(2, "averagely-girthed", Color.GENERIC_SIZE_THREE),
	THREE_THICK(3, "thick", Color.GENERIC_SIZE_FOUR),
	FOUR_FAT(4, "fat", Color.GENERIC_SIZE_FIVE);
	
	private int value;
	private String descriptor;
	private Color color;

	private PenisGirth(int value, String descriptor, Color color) {
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
	
	public static PenisGirth getPenisGirthFromInt(int size) {
		for(PenisGirth ls : PenisGirth.values()) {
			if(size == ls.getValue()) {
				return ls;
			}
		}
		return ZERO_SLENDER;
	}
	
	public static int getLargest() {
		int largest = ZERO_SLENDER.value;
		for(PenisGirth ls : PenisGirth.values()) {
			largest = Math.max(largest, ls.value);
		}
		return largest;
	}

	public Color getColor() {
		return color;
	}
}
