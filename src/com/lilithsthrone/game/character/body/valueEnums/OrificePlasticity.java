package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Color;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 *
 * @since 0.1.83
 * @version 0.1.90
 * @author Innoxia
 */
public enum OrificePlasticity {

	/*-------MOLDABLE:---------*/

	/**Instantly returns to starting value after sex.*/
	ZERO_RUBBERY("rubbery", 0, 0, Color.GENERIC_SIZE_ONE),

	/**Quickly returns to starting value after sex.*/
	ONE_SPRINGY("springy", 1, 0, Color.GENERIC_SIZE_TWO),

	/**Returns to starting value after sex.*/
	TWO_TENSILE("tensile", 2, 0, Color.GENERIC_SIZE_THREE),

	/*-------NORMAL:---------*/

	/**Normal value, along with FOUR_ACCOMMODATING. Will slowly return to starting value after sex.*/
	THREE_RESILIENT("resilient", 3, 0, Color.GENERIC_SIZE_FOUR),

	/**Normal value, along with THREE_RESILIENT. Will very slowly return to starting value after sex.*/
	FOUR_ACCOMMODATING("accommodating", 4, 0, Color.GENERIC_SIZE_FIVE),

	/*-------STRETCHY:---------*/

	/**Will recover about 80% of its original size after sex.*/
	FIVE_YIELDING("yielding", 5, 0.2f, Color.GENERIC_SIZE_SIX),

	/**Will only recover about 40% of its original size after sex.*/
	SIX_MALLEABLE("malleable", 6, 0.6f, Color.GENERIC_SIZE_SEVEN),

	/**Will recover none of its original size after sex.*/
	SEVEN_MOLDABLE("moldable", 7, 0.75f, Color.GENERIC_SIZE_EIGHT);

	private String descriptor;
	private int value;
	private float stretchModifier, capacityIncreaseModifier;
	private Color color;

	private OrificePlasticity(String descriptor, int value, float capacityIncreaseModifier, Color color) {
		this.descriptor = descriptor;
		this.value = value;
		this.capacityIncreaseModifier = capacityIncreaseModifier;
		this.color = color;
	}

	public static OrificePlasticity getElasticityFromInt(int value) {
		for(OrificePlasticity oe : OrificePlasticity.values()) {
			if(value == oe.getValue()) {
				return oe;
			}
		}
		return SEVEN_MOLDABLE;
	}

	/**
	 * To fit into a sentence: "You have a "+getDescriptor()+" ass."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return value;
	}

	public float getStretchModifier() {
		return stretchModifier;
	}

	public float getCapacityIncreaseModifier() {
		return capacityIncreaseModifier;
	}

	public Color getColor() {
		return color;
	}
}
