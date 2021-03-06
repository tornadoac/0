package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public enum AgeCategory {
	
	// Was Always at least 18, as returned by valueOf()
	YOUNG_CHILD("early childhood", 1, 6, Colour.AGE_CHILD, ContentPreferenceValue.ZERO_NONE),
	
	LATE_CHILD("childhood", 6, 10, Colour.AGE_PRETEENS, ContentPreferenceValue.ZERO_NONE),
	
	TEENS_EARLY("early teens", 10, 16, Colour.AGE_EARLYTEENS, ContentPreferenceValue.ZERO_NONE),
	
	TEENS_LATE("late teens", 16, 20, Colour.AGE_TEENS, ContentPreferenceValue.FOUR_HIGH),
	
	TWENTIES_EARLY("early twenties", 20, 23, Colour.AGE_TWENTIES, ContentPreferenceValue.FIVE_ABUNDANT),
	
	TWENTIES_MIDDLE("mid-twenties", 23, 27, Colour.AGE_TWENTIES, ContentPreferenceValue.FIVE_ABUNDANT),
	
	TWENTIES_LATE("late twenties", 27, 30, Colour.AGE_TWENTIES, ContentPreferenceValue.FOUR_HIGH),
	
	THIRTIES_EARLY("early thirties", 30, 33, Colour.AGE_THIRTIES, ContentPreferenceValue.THREE_AVERAGE),
	
	THIRTIES_MIDDLE("mid-thirties", 33, 37, Colour.AGE_THIRTIES, ContentPreferenceValue.THREE_AVERAGE),
	
	THIRTIES_LATE("late thirties", 37, 40, Colour.AGE_THIRTIES, ContentPreferenceValue.TWO_LOW),
	
	FORTIES_EARLY("early forties", 40, 43, Colour.AGE_FORTIES, ContentPreferenceValue.TWO_LOW),
	
	FORTIES_MIDDLE("mid-forties", 43, 47, Colour.AGE_FORTIES, ContentPreferenceValue.ONE_MINIMAL),
	
	FORTIES_LATE("late forties", 47, 50, Colour.AGE_FORTIES, ContentPreferenceValue.ONE_MINIMAL),
	
	FIFTIES_EARLY("early fifties", 50, 53, Colour.AGE_FIFTIES, ContentPreferenceValue.ONE_MINIMAL),
	
	FIFTIES_MIDDLE("mid-fifties", 53, 57, Colour.AGE_FIFTIES, ContentPreferenceValue.ZERO_NONE),
	
	FIFTIES_LATE("late fifties", 57, 60, Colour.AGE_FIFTIES, ContentPreferenceValue.ZERO_NONE),
	
	SIXTIES_EARLY("early sixties", 60, 63, Colour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),
	
	SIXTIES_MIDDLE("mid-sixties", 63, 67, Colour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),
	
	SIXTIES_LATE("late sixties", 67, 70, Colour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),
	
	SIXTIES_PLUS("past seventy", 70, 100, Colour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE);

	private String name;
	private int minimumAge;
	private int maximumAge;
	private Colour colour;
	private ContentPreferenceValue agePreferenceDefault;

	private AgeCategory(String name, int minimumAge, int maximumAge, Colour colour, ContentPreferenceValue agePreferenceDefault) {
		this.name = name;
		this.minimumAge = minimumAge;
		this.maximumAge = maximumAge;
		this.colour = colour;
		this.agePreferenceDefault = agePreferenceDefault;
	}

	public int getMinimumValue() {
		return minimumAge;
	}

	public int getMaximumValue() {
		return maximumAge;
	}
	
	public int getMedianValue() {
		return minimumAge + (maximumAge - minimumAge) / 2;
	}

	public static AgeCategory valueOf(int age) {
		if(age<YOUNG_CHILD.getMinimumValue()) {
			return YOUNG_CHILD;
		}
		for(AgeCategory f : AgeCategory.values()) {
			if(age>=f.getMinimumValue() && age<f.getMaximumValue()) {
				return f;
			}
		}
		return SIXTIES_PLUS;
	}
	
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}

	public ContentPreferenceValue getAgePreferenceDefault() {
		return agePreferenceDefault;
	}
	
	public static int getAgeFromPreferences(Gender gender) {
		AgeCategory category;
		try {
			category = Util.getRandomObjectFromWeightedMap(Main.getProperties().agePreferencesMap.get(gender.getType()));
		} catch(Exception ex) {
			category = AgeCategory.TWENTIES_MIDDLE;
			//Agemod
		}
		if(category==null) {
			category = AgeCategory.TWENTIES_MIDDLE;
		}
		
		int lowerBound = category.getMinimumValue();
		int upperBound = category.getMaximumValue();
		return lowerBound + Util.random.nextInt(upperBound-lowerBound);
	}
}
