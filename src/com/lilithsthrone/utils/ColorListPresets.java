package com.lilithsthrone.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.2.4
 * @version 0.2.11
 * @author Innoxia
 */
public enum ColorListPresets {

	NONE(new ArrayList<>()),

	JUST_WHITE(Util.newArrayListOfValues(
			Color.CLOTHING_WHITE)),

	JUST_BLACK(Util.newArrayListOfValues(
			Color.CLOTHING_BLACK)),

	JUST_DARK_RED(Util.newArrayListOfValues(
			Color.CLOTHING_RED_DARK)),

	JUST_RED(Util.newArrayListOfValues(
			Color.CLOTHING_RED)),

	JUST_TAN(Util.newArrayListOfValues(
			Color.CLOTHING_TAN)),

	JUST_BROWN(Util.newArrayListOfValues(
			Color.CLOTHING_BROWN)),

	JUST_DARK_BROWN(Util.newArrayListOfValues(
			Color.CLOTHING_BROWN_DARK)),

	JUST_ORANGE(Util.newArrayListOfValues(
			Color.CLOTHING_ORANGE)),

	JUST_YELLOW(Util.newArrayListOfValues(
			Color.CLOTHING_YELLOW)),

	JUST_PINK(Util.newArrayListOfValues(
			Color.CLOTHING_PINK)),

	JUST_GREY(Util.newArrayListOfValues(
			Color.CLOTHING_GREY)),

	JUST_GOLD(Util.newArrayListOfValues(
			Color.CLOTHING_GOLD)),

	JUST_ROSE_GOLD(Util.newArrayListOfValues(
			Color.CLOTHING_ROSE_GOLD)),

	JUST_STEEL(Util.newArrayListOfValues(
			Color.CLOTHING_STEEL)),

	JUST_SILVER(Util.newArrayListOfValues(
			Color.CLOTHING_SILVER)),

	JUST_COPPER(Util.newArrayListOfValues(
			Color.CLOTHING_COPPER)),

	BLACK_OR_WHITE(Util.newArrayListOfValues(
			Color.CLOTHING_BLACK,
			Color.CLOTHING_WHITE)),

	DENIM(Util.newArrayListOfValues(
			Color.CLOTHING_BLUE_LIGHT,
			Color.CLOTHING_BLUE,
			Color.CLOTHING_PINK_LIGHT,
			Color.CLOTHING_PINK,
			Color.CLOTHING_WHITE,
			Color.CLOTHING_GREY,
			Color.CLOTHING_BLACK)),

	KIMONO(Util.newArrayListOfValues(
			Color.CLOTHING_BLUE_LIGHT,
			Color.CLOTHING_PINK_LIGHT,
			Color.CLOTHING_PINK,
			Color.CLOTHING_PURPLE,
			Color.CLOTHING_PURPLE_LIGHT,
			Color.CLOTHING_RED,
			Color.CLOTHING_TURQUOISE,
			Color.CLOTHING_WHITE,
			Color.CLOTHING_YELLOW)),

	MAID(Util.newArrayListOfValues(
			Color.CLOTHING_PINK_LIGHT,
			Color.CLOTHING_PINK,
			Color.CLOTHING_BLACK)),

	MILK_MAID(Util.newArrayListOfValues(
			Color.CLOTHING_PINK_LIGHT,
			Color.CLOTHING_BLUE_LIGHT,
			Color.CLOTHING_PURPLE_LIGHT,
			Color.CLOTHING_GREEN,
			Color.CLOTHING_TAN,
			Color.CLOTHING_BROWN,
			Color.CLOTHING_BLACK)),

	LEATHER(Util.newArrayListOfValues(
			Color.CLOTHING_WHITE,
			Color.CLOTHING_BLACK,
			Color.CLOTHING_GREY,
			Color.CLOTHING_BROWN,
			Color.CLOTHING_BROWN_DARK,
			Color.CLOTHING_BROWN_VERY_DARK,
			Color.CLOTHING_TAN)),

	LINGERIE(Util.newArrayListOfValues(
			Color.CLOTHING_WHITE,
			Color.CLOTHING_BLACK,
			Color.CLOTHING_GREY,
			Color.CLOTHING_RED_VERY_DARK,
			Color.CLOTHING_RED_DARK,
			Color.CLOTHING_RED,
			Color.CLOTHING_RED_BRIGHT,
			Color.CLOTHING_ORANGE,
			Color.CLOTHING_ORANGE_BRIGHT,
			Color.CLOTHING_ORANGE_DARK,
			Color.CLOTHING_BROWN,
			Color.CLOTHING_BROWN_DARK,
			Color.CLOTHING_BROWN_VERY_DARK,
			Color.CLOTHING_TAN,
			Color.CLOTHING_OLIVE,
			Color.CLOTHING_YELLOW,
			Color.CLOTHING_YELLOW_DARK,
			Color.CLOTHING_GREEN_LIME,
			Color.CLOTHING_GREEN,
			Color.CLOTHING_GREEN_DRAB,
			Color.CLOTHING_GREEN_DARK,
			Color.CLOTHING_GREEN_VERY_DARK,
			Color.CLOTHING_TURQUOISE,
			Color.CLOTHING_BLUE_LIGHT,
			Color.CLOTHING_BLUE,
			Color.CLOTHING_BLUE_DARK,
			Color.CLOTHING_BLUE_VERY_DARK,
			Color.CLOTHING_PURPLE_VERY_DARK,
			Color.CLOTHING_PURPLE_DARK,
			Color.CLOTHING_PURPLE,
			Color.CLOTHING_PURPLE_LIGHT,
			Color.CLOTHING_PERIWINKLE,
			Color.CLOTHING_PINK_LIGHT,
			Color.CLOTHING_PINK,
			Color.CLOTHING_PINK_HOT,
			Color.CLOTHING_PINK_DARK)),

	ALL_METAL(Util.newArrayListOfValues(
			Color.CLOTHING_BLACK_STEEL,
			Color.CLOTHING_STEEL,
			Color.CLOTHING_BRASS,
			Color.CLOTHING_COPPER,
			Color.CLOTHING_SILVER,
			Color.CLOTHING_ROSE_GOLD,
			Color.CLOTHING_GOLD,
			Color.CLOTHING_PLATINUM)),

	NOT_WHITE(Util.newArrayListOfValues(
			Color.CLOTHING_BLACK,
			Color.CLOTHING_GREY,
			Color.CLOTHING_RED_VERY_DARK,
			Color.CLOTHING_RED_DARK,
			Color.CLOTHING_RED,
			Color.CLOTHING_RED_BRIGHT,
			Color.CLOTHING_ORANGE,
			Color.CLOTHING_ORANGE_BRIGHT,
			Color.CLOTHING_ORANGE_DARK,
			Color.CLOTHING_BROWN,
			Color.CLOTHING_BROWN_DARK,
			Color.CLOTHING_BROWN_VERY_DARK,
			Color.CLOTHING_TAN,
			Color.CLOTHING_OLIVE,
			Color.CLOTHING_YELLOW,
			Color.CLOTHING_YELLOW_DARK,
			Color.CLOTHING_GREEN_LIME,
			Color.CLOTHING_GREEN,
			Color.CLOTHING_GREEN_DRAB,
			Color.CLOTHING_GREEN_DARK,
			Color.CLOTHING_GREEN_VERY_DARK,
			Color.CLOTHING_TURQUOISE,
			Color.CLOTHING_BLUE_LIGHT,
			Color.CLOTHING_BLUE,
			Color.CLOTHING_BLUE_DARK,
			Color.CLOTHING_BLUE_VERY_DARK,
			Color.CLOTHING_PURPLE_VERY_DARK,
			Color.CLOTHING_PURPLE_DARK,
			Color.CLOTHING_PURPLE,
			Color.CLOTHING_PURPLE_LIGHT,
			Color.CLOTHING_PERIWINKLE,
			Color.CLOTHING_PINK_LIGHT,
			Color.CLOTHING_PINK,
			Color.CLOTHING_PINK_HOT,
			Color.CLOTHING_PINK_DARK)),

	ALL(Util.newArrayListOfValues(
			Color.CLOTHING_WHITE,
			Color.CLOTHING_BLACK,
			Color.CLOTHING_GREY,
			Color.CLOTHING_RED_VERY_DARK,
			Color.CLOTHING_RED_DARK,
			Color.CLOTHING_RED,
			Color.CLOTHING_RED_BRIGHT,
			Color.CLOTHING_ORANGE,
			Color.CLOTHING_ORANGE_BRIGHT,
			Color.CLOTHING_ORANGE_DARK,
			Color.CLOTHING_BROWN,
			Color.CLOTHING_BROWN_DARK,
			Color.CLOTHING_BROWN_VERY_DARK,
			Color.CLOTHING_TAN,
			Color.CLOTHING_OLIVE,
			Color.CLOTHING_YELLOW,
			Color.CLOTHING_YELLOW_DARK,
			Color.CLOTHING_GREEN_LIME,
			Color.CLOTHING_GREEN,
			Color.CLOTHING_GREEN_DRAB,
			Color.CLOTHING_GREEN_DARK,
			Color.CLOTHING_GREEN_VERY_DARK,
			Color.CLOTHING_TURQUOISE,
			Color.CLOTHING_BLUE_LIGHT,
			Color.CLOTHING_BLUE,
			Color.CLOTHING_BLUE_DARK,
			Color.CLOTHING_BLUE_VERY_DARK,
			Color.CLOTHING_PURPLE_VERY_DARK,
			Color.CLOTHING_PURPLE_DARK,
			Color.CLOTHING_PURPLE,
			Color.CLOTHING_PURPLE_LIGHT,
			Color.CLOTHING_PERIWINKLE,
			Color.CLOTHING_PINK_LIGHT,
			Color.CLOTHING_PINK,
			Color.CLOTHING_PINK_HOT,
			Color.CLOTHING_PINK_DARK)),

	ALL_WITH_METALS(Util.newArrayListOfValues(
			Color.CLOTHING_WHITE,
			Color.CLOTHING_BLACK,
			Color.CLOTHING_GREY,
			Color.CLOTHING_RED_VERY_DARK,
			Color.CLOTHING_RED_DARK,
			Color.CLOTHING_RED,
			Color.CLOTHING_RED_BRIGHT,
			Color.CLOTHING_ORANGE,
			Color.CLOTHING_ORANGE_BRIGHT,
			Color.CLOTHING_ORANGE_DARK,
			Color.CLOTHING_BROWN,
			Color.CLOTHING_BROWN_DARK,
			Color.CLOTHING_BROWN_VERY_DARK,
			Color.CLOTHING_TAN,
			Color.CLOTHING_OLIVE,
			Color.CLOTHING_YELLOW_DARK,
			Color.CLOTHING_YELLOW,
			Color.CLOTHING_GREEN_LIME,
			Color.CLOTHING_GREEN,
			Color.CLOTHING_GREEN_DRAB,
			Color.CLOTHING_GREEN_DARK,
			Color.CLOTHING_GREEN_VERY_DARK,
			Color.CLOTHING_TURQUOISE,
			Color.CLOTHING_BLUE_LIGHT,
			Color.CLOTHING_BLUE,
			Color.CLOTHING_BLUE_DARK,
			Color.CLOTHING_BLUE_VERY_DARK,
			Color.CLOTHING_PURPLE_VERY_DARK,
			Color.CLOTHING_PURPLE_DARK,
			Color.CLOTHING_PURPLE,
			Color.CLOTHING_PURPLE_LIGHT,
			Color.CLOTHING_PERIWINKLE,
			Color.CLOTHING_PINK_LIGHT,
			Color.CLOTHING_PINK,
			Color.CLOTHING_PINK_HOT,
			Color.CLOTHING_PINK_DARK,
			Color.CLOTHING_BLACK_STEEL,
			Color.CLOTHING_STEEL,
			Color.CLOTHING_BRASS,
			Color.CLOTHING_COPPER,
			Color.CLOTHING_SILVER,
			Color.CLOTHING_ROSE_GOLD,
			Color.CLOTHING_GOLD,
			Color.CLOTHING_PLATINUM));

	private List<Color> presetColorList;

	public List<Color> getPresetColorList() {
		return presetColorList;
	}

	private ColorListPresets(List<Color> presetColorList) {
		this.presetColorList = presetColorList;
	}

}
