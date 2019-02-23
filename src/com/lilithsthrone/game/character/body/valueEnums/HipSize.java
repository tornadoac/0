package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Color;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 *
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum HipSize {

	ZERO_NO_HIPS("completely straight", 0, Color.GENERIC_SIZE_ONE),

	ONE_VERY_NARROW("very narrow", 1, Color.GENERIC_SIZE_TWO),

	TWO_NARROW("narrow", 2, Color.GENERIC_SIZE_THREE),

	THREE_GIRLY("girly", 3, Color.GENERIC_SIZE_FOUR),

	FOUR_WOMANLY("womanly", 4, Color.GENERIC_SIZE_FIVE),

	FIVE_VERY_WIDE("very wide", 5, Color.GENERIC_SIZE_SIX),

	SIX_EXTREMELY_WIDE("extremely wide", 6, Color.GENERIC_SIZE_SEVEN),

	SEVEN_ABSURDLY_WIDE("absurdly wide", 7, Color.GENERIC_SIZE_EIGHT);

	private String descriptor;
	private int size;
	private Color color;

	private HipSize(String descriptor, int hipSize, Color color) {
		this.descriptor = descriptor;
		this.size = hipSize;
		this.color=color;
	}

	public static HipSize getHipSizeFromInt(int hipSize) {
		for(HipSize hs : HipSize.values()) {
			if(hipSize == hs.getValue()) {
				return hs;
			}
		}
		return SEVEN_ABSURDLY_WIDE;
	}

	/**
	 * To fit into a sentence: "You have "+getDescriptor()+" hips."
	 */
	public String getDescriptor() {
		if(Main.game.isSillyModeEnabled()) {
			switch(this) {
				case FIVE_VERY_WIDE:
					return "thicc";
				case SIX_EXTREMELY_WIDE:
					return "extremely thicc";
				case SEVEN_ABSURDLY_WIDE:
					return "absurdly thicc";
				case ZERO_NO_HIPS:
				case ONE_VERY_NARROW:
				case TWO_NARROW:
				case THREE_GIRLY:
				case FOUR_WOMANLY:
					break;
			}
		}
		return descriptor;
	}

	public int getValue() {
		return size;
	}

	public Color getColor() {
		return color;
	}
}
