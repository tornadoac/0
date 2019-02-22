package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public enum AffectionLevelBasic {
	
	/** -100 to -30*/
	DISLIKE("dislikes", -100, -30, Color.AFFECTION_NEGATIVE_TWO),

	/** -30 to 30*/
	NEUTRAL("neutral", -30, 30, Color.AFFECTION_POSITIVE_ONE),

	/** -30 to 100*/
	LIKE("likes", 30, 100, Color.AFFECTION_POSITIVE_FIVE);
	
	private String name;
	private int minimumValue, maximumValue;
	private Color color;

	private AffectionLevelBasic(String name, int minimumValue, int maximumValue, Color color) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public Color getColor() {
		return color;
	}

	public static AffectionLevelBasic getAffectionLevelFromValue(float value){
		for(AffectionLevelBasic al : AffectionLevelBasic.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return LIKE;
	}
}
