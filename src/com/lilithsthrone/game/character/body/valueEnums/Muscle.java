package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum Muscle {
	
	ZERO_SOFT("soft", 0, 20, Color.MUSCLE_ZERO),
	ONE_LIGHTLY_MUSCLED("lightly muscled", 20, 40, Color.MUSCLE_ONE),
	TWO_TONED("toned", 40, 60, Color.MUSCLE_TWO),
	THREE_MUSCULAR("muscular", 60, 80, Color.MUSCLE_THREE),
	FOUR_RIPPED("ripped", 80, 100, Color.MUSCLE_FOUR);

	private String name;
	private int minimumMuscle, maximumMuscle;
	private Color color;

	private Muscle(String name, int minimumMuscle, int maximumMuscle, Color color) {
		this.name = name;
		this.minimumMuscle = minimumMuscle;
		this.maximumMuscle = maximumMuscle;
		this.color = color;
	}

	public int getMinimumValue() {
		return minimumMuscle;
	}

	public int getMaximumValue() {
		return maximumMuscle;
	}

	public int getMedianValue() {
		return minimumMuscle + (maximumMuscle - minimumMuscle) / 2;
	}

	public static Muscle valueOf(int muscle) {
		for(Muscle f : Muscle.values()) {
			if(muscle>=f.getMinimumValue() && muscle<f.getMaximumValue()) {
				return f;
			}
		}
		return FOUR_RIPPED;
	}
	
	public String getName(boolean withDeterminer) {
		if(withDeterminer) {
			return UtilText.generateSingularDeterminer(name) + " " + name;
		} else {
			return name;
		}
	}

	public Color getColor() {
		return color;
	}
}
