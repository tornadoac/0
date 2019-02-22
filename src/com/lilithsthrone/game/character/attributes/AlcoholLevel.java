package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public enum AlcoholLevel {
	
	ZERO_SOBER("sober", 0, 0.01f, Color.ALCOHOL_LEVEL_ZERO),
	
	ONE_TIPSY("tipsy", 0.01f, 0.2f, Color.ALCOHOL_LEVEL_ONE),
	
	TWO_MERRY("merry", 0.2f, 0.4f, Color.ALCOHOL_LEVEL_TWO),
	
	THREE_DRUNK("drunk", 0.4f, 0.6f, Color.ALCOHOL_LEVEL_THREE),
	
	FOUR_HAMMERED("hammered", 0.6f, 0.8f, Color.ALCOHOL_LEVEL_FOUR),
	
	FIVE_WASTED("wasted", 0.8f, 1, Color.ALCOHOL_LEVEL_FIVE);
	
	private String name;
	private float minimumValue, maximumValue;
	private Color color;

	private AlcoholLevel(String name, float minimumValue, float maximumValue, Color color) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}

	public float getMinimumValue() {
		return minimumValue;
	}

	public float getMaximumValue() {
		return maximumValue;
	}

	public Color getColor() {
		return color;
	}
	
	public static AlcoholLevel getAlcoholLevelFromValue(float value){
		for(AlcoholLevel al : AlcoholLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_WASTED;
	}
}
