package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;

import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.?
 * @version 0.2.9
 * @author Pimgd
 */
public enum StartingSkinTone {
	
	VERY_LIGHT(Util.newArrayListOfValues(
			Color.SKIN_PALE,
			Color.SKIN_PINK,
			Color.SKIN_BLUE,
			Color.SKIN_IVORY,
			
			Color.SLIME_BLUE,
			Color.SLIME_PINK,
			
			Color.COVERING_BLEACH_BLONDE,
			Color.COVERING_GINGER,
			Color.COVERING_LILAC,
			Color.COVERING_PINK,
			Color.COVERING_WHITE,
			
			Color.HORN_WHITE,
			Color.ORIFICE_INTERIOR,
			Color.TONGUE,

			Color.COVERING_BROWN,
			Color.COVERING_BLONDE,
			Color.COVERING_BLEACH_BLONDE,
			Color.COVERING_GINGER,
			Color.COVERING_WHITE,
			Color.COVERING_PINK,
			
			Color.COVERING_CLEAR,
			Color.COVERING_NONE,

			Color.EYE_BROWN,
			Color.EYE_BLUE,
			Color.EYE_GREEN,
			Color.EYE_YELLOW,
			Color.EYE_RED,
			Color.EYE_PINK,
			Color.EYE_ORANGE,
			Color.EYE_BLACK)),
			
	LIGHT(Util.newArrayListOfValues(
			Color.SKIN_LIGHT,
			Color.SKIN_ROSY,
			Color.SKIN_PINK,
			Color.SKIN_BLUE,
			Color.SKIN_IVORY,
			Color.SKIN_LILAC,
			
			Color.SLIME_BLUE,
			Color.SLIME_PINK,
			Color.SLIME_GREEN,
			Color.SLIME_RED,
			
			Color.COVERING_WHITE,
			Color.COVERING_BLUE,
			Color.COVERING_LILAC,
			Color.COVERING_PINK,
			Color.COVERING_RED,
			Color.COVERING_GREEN,
			Color.COVERING_YELLOW,
			Color.COVERING_ORANGE,
			Color.COVERING_GINGER,
			Color.COVERING_BLEACH_BLONDE,
			
			Color.HORN_WHITE,
			Color.ORIFICE_INTERIOR,
			Color.TONGUE,

			Color.COVERING_BROWN,
			Color.COVERING_BLONDE,
			Color.COVERING_BLEACH_BLONDE,
			Color.COVERING_GINGER,
			Color.COVERING_RED,
			Color.COVERING_WHITE,
			Color.COVERING_BLUE,
			Color.COVERING_PURPLE,
			Color.COVERING_PINK,
			Color.COVERING_GREEN,
			
			Color.COVERING_CLEAR,
			Color.COVERING_NONE,

			Color.EYE_BROWN,
			Color.EYE_BLUE,
			Color.EYE_GREEN,
			Color.EYE_YELLOW,
			Color.EYE_RED,
			Color.EYE_PINK,
			Color.EYE_ORANGE,
			Color.EYE_BLACK)),
			
	OLIVE(Util.newArrayListOfValues(
			Color.SKIN_OLIVE,
			Color.SKIN_TANNED,
			Color.SKIN_RED,
			Color.SKIN_BROWN,
			Color.SKIN_PINK,
			Color.SKIN_BLUE,
			Color.SKIN_LILAC,
			Color.SKIN_PURPLE,
			Color.SKIN_IVORY,
			
			Color.SLIME_BLUE,
			Color.SLIME_PINK,
			Color.SLIME_GREEN,
			Color.SLIME_RED,
			Color.SLIME_BLACK,

			Color.COVERING_BLACK,
			Color.COVERING_WHITE,
			Color.COVERING_BLUE,
			Color.COVERING_LILAC,
			Color.COVERING_PINK,
			Color.COVERING_RED,
			Color.COVERING_GREEN,
			Color.COVERING_YELLOW,
			Color.COVERING_ORANGE,
			Color.COVERING_GINGER,
			Color.COVERING_BLEACH_BLONDE,
			
			Color.HORN_WHITE,
			Color.ORIFICE_INTERIOR,
			Color.TONGUE,

			Color.COVERING_BROWN,
			Color.COVERING_BROWN_DARK,
			Color.COVERING_BLACK,
			Color.COVERING_BLONDE,
			Color.COVERING_BLEACH_BLONDE,
			Color.COVERING_RED,
			Color.COVERING_WHITE,
			Color.COVERING_BLUE,
			Color.COVERING_PURPLE,
			Color.COVERING_PINK,
			Color.COVERING_GREEN,
			
			Color.COVERING_CLEAR,
			Color.COVERING_NONE,

			Color.EYE_BROWN,
			Color.EYE_BLUE,
			Color.EYE_GREEN,
			Color.EYE_YELLOW,
			Color.EYE_RED,
			Color.EYE_PINK,
			Color.EYE_ORANGE,
			Color.EYE_BLACK)),
			
	DARK(Util.newArrayListOfValues(
			Color.SKIN_DARK,
			Color.SKIN_RED,
			Color.SKIN_BROWN,
			Color.SKIN_LILAC,
			Color.SKIN_PURPLE,
			
			Color.SLIME_BLUE,
			Color.SLIME_GREEN,
			Color.SLIME_RED,
			Color.SLIME_BLACK,

			Color.COVERING_BLACK,
			Color.COVERING_WHITE,
			Color.COVERING_BLUE,
			Color.COVERING_LILAC,
			Color.COVERING_PINK,
			Color.COVERING_RED,
			Color.COVERING_GREEN,
			Color.COVERING_YELLOW,
			Color.COVERING_ORANGE,
			
			Color.HORN_WHITE,
			Color.ORIFICE_INTERIOR,
			Color.TONGUE,

			Color.COVERING_BROWN,
			Color.COVERING_BROWN_DARK,
			Color.COVERING_BLACK,
			Color.COVERING_RED,
			Color.COVERING_WHITE,
			Color.COVERING_BLUE,
			Color.COVERING_PURPLE,
			Color.COVERING_PINK,
			Color.COVERING_GREEN,
			
			Color.COVERING_CLEAR,
			Color.COVERING_NONE,

			Color.EYE_BROWN,
			Color.EYE_YELLOW,
			Color.EYE_RED,
			Color.EYE_PINK,
			Color.EYE_ORANGE,
			Color.EYE_BLACK)),
			
	VERY_DARK(Util.newArrayListOfValues(
			Color.SKIN_EBONY,
			Color.SKIN_CHOCOLATE,
			Color.SKIN_RED,
			Color.SKIN_BROWN,
			Color.SKIN_PURPLE,
			
			Color.SLIME_BLUE,
			Color.SLIME_GREEN,
			Color.SLIME_RED,
			Color.SLIME_BLACK,

			Color.COVERING_BLACK,
			Color.COVERING_WHITE,
			Color.COVERING_BLUE,
			Color.COVERING_RED,
			Color.COVERING_GREEN,
			Color.COVERING_YELLOW,
			Color.COVERING_ORANGE,
			
			Color.HORN_WHITE,
			Color.ORIFICE_INTERIOR,
			Color.TONGUE,

			Color.COVERING_BROWN,
			Color.COVERING_BROWN_DARK,
			Color.COVERING_BLACK,
			Color.COVERING_RED,
			Color.COVERING_WHITE,
			Color.COVERING_BLUE,
			Color.COVERING_PURPLE,
			Color.COVERING_GREEN,
			
			Color.COVERING_CLEAR,
			Color.COVERING_NONE,

			Color.EYE_BROWN,
			Color.EYE_YELLOW,
			Color.EYE_RED,
			Color.EYE_PINK,
			Color.EYE_ORANGE,
			Color.EYE_BLACK));
	
	private List<Color> associatedColors;
	
	private StartingSkinTone(List<Color> associatedColors) {
		this.associatedColors = associatedColors;
	}

	public List<Color> getAssociatedColors() {
		return associatedColors;
	}
	
}
