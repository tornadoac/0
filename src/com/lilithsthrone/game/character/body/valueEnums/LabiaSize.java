package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.86
 * @version 0.1.86
 * @author Innoxia
 */
public enum LabiaSize {

	ZERO_TINY(0, "tiny", Color.GENERIC_SIZE_ONE),
	ONE_SMALL(1, "small", Color.GENERIC_SIZE_TWO),
	TWO_AVERAGE(2, "average-sized", Color.GENERIC_SIZE_THREE),
	THREE_LARGE(3, "large", Color.GENERIC_SIZE_FOUR),
	FOUR_MASSIVE(4, "massive", Color.GENERIC_SIZE_FIVE);

	private int value;
	private String descriptor;
	private Color color;

	private LabiaSize(int value, String descriptor, Color color) {
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

	public static LabiaSize getRandomLabiaSize() {
		return LabiaSize.values()[Util.random.nextInt(LabiaSize.values().length)];
	}

	public static LabiaSize getLabiaSizeFromInt(int size) {
		for(LabiaSize as : LabiaSize.values()) {
			if(size == as.getValue()) {
				return as;
			}
		}
		return ZERO_TINY;
	}

	public Color getColor() {
		return color;
	}
}
