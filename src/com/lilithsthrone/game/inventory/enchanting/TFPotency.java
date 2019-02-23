package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.83
 * @version 0.2.11
 * @author Innoxia
 */
public enum TFPotency {

	MAJOR_DRAIN("Major Drain", Color.GENERIC_TERRIBLE, 8, -5),
	DRAIN("Drain", Color.GENERIC_BAD, 4, -3),
	MINOR_DRAIN("Minor Drain", Color.GENERIC_MINOR_BAD, 1, -1),
	MINOR_BOOST("Minor Boost", Color.GENERIC_MINOR_GOOD, 1, 1),
	BOOST("Boost", Color.GENERIC_GOOD, 4, 3),
	MAJOR_BOOST("Major Boost", Color.GENERIC_EXCELLENT, 8, 5);

	private static List<TFPotency> allPotencies = new ArrayList<>();

	static {
		Collections.addAll(allPotencies, TFPotency.values());
	}

	private String name;
	private Color color;
	private int value;
	private int clothingBonusValue;

	private TFPotency(String name, Color color, int value, int clothingBonusValue) {
		this.name = name;
		this.color = color;
		this.value = value;
		this.clothingBonusValue = clothingBonusValue;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public int getValue() {
		return value;
	}

	public int getClothingBonusValue() {
		return clothingBonusValue;
	}

	public boolean isNegative() {
		return this==TFPotency.MINOR_DRAIN || this==TFPotency.DRAIN || this==TFPotency.MAJOR_DRAIN;
	}

	public static List<TFPotency> getAllPotencies() {
		return allPotencies;
	}

	public static TFPotency getRandomWeightedPositivePotency() {
		double rnd = Math.random();

		if(rnd<0.6) {
			return TFPotency.MINOR_BOOST;
		} else if (rnd<0.9) {
			return TFPotency.BOOST;
		} else {
			return TFPotency.MAJOR_BOOST;
		}
	}

	public static TFPotency getRandomWeightedNegativePotency() {
		double rnd = Math.random();

		if(rnd<0.6) {
			return TFPotency.MAJOR_DRAIN;
		} else if (rnd<0.9) {
			return TFPotency.DRAIN;
		} else {
			return TFPotency.MAJOR_DRAIN;
		}
	}

}
