package com.lilithsthrone.utils;

import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.main.Main;

import javafx.scene.paint.Color;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public enum Color {
	
	// This class and BaseColor are beyond saving x_x
	
	BASE_WHITE(false, BaseColor.WHITE, "white", Util.newArrayListOfValues("white")),
	BASE_GREY(false, BaseColor.GREY, "grey", Util.newArrayListOfValues("grey")),
	BASE_GREY_DARK(false, BaseColor.GREY_DARK, "dark grey", Util.newArrayListOfValues("darkGrey")),
	
	BASE_ROSE(false, BaseColor.ROSE, "rose", Util.newArrayListOfValues("rose")),
	BASE_LILAC(false, BaseColor.LILAC, "lilac", Util.newArrayListOfValues("lilac")),
	BASE_LILAC_LIGHT(false, BaseColor.LILAC_LIGHT, "light lilac", Util.newArrayListOfValues("lightLilac")),
	BASE_PURPLE_DARK(false, BaseColor.PURPLE_DARK, "dark purple", Util.newArrayListOfValues("darkPurple")),
	BASE_PURPLE(false, BaseColor.PURPLE, "purple", Util.newArrayListOfValues("purple")),
	BASE_PURPLE_LIGHT(false, BaseColor.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("lightPurple")),
	
	BASE_PINK_DEEP(false, BaseColor.PINK_DEEP, "deep pink", Util.newArrayListOfValues("deepPink", "darkPink")),
	BASE_VIOLET(false, BaseColor.VIOLET, "violet", Util.newArrayListOfValues("violet")),
	BASE_PINK(false, BaseColor.PINK, "pink", Util.newArrayListOfValues("pink")),
	BASE_PINK_LIGHT(false, BaseColor.PINK_LIGHT, "light pink", Util.newArrayListOfValues("lightPink")),
		
	BASE_MAGENTA(false, BaseColor.MAGENTA, "magenta", Util.newArrayListOfValues("magenta")),
	BASE_CRIMSON(false, BaseColor.CRIMSON, "crimson", Util.newArrayListOfValues("crimson")),
	BASE_RED(false, BaseColor.RED, "red", Util.newArrayListOfValues("red")),
	BASE_RED_LIGHT(false, BaseColor.RED_LIGHT, "light red", Util.newArrayListOfValues("lightRed")),
	
	BASE_TAN(false, BaseColor.TAN, "tan", Util.newArrayListOfValues("tan")),
	BASE_BROWN(false, BaseColor.BROWN, "brown", Util.newArrayListOfValues("brown")),
	BASE_BROWN_DARK(false, BaseColor.BROWN_DARK, "dark brown", Util.newArrayListOfValues("darkBrown")),
	BASE_ORANGE(false, BaseColor.ORANGE, "orange", Util.newArrayListOfValues("orange")),
	BASE_GINGER(false, BaseColor.GINGER, "ginger", Util.newArrayListOfValues("ginger")),
	
	BASE_GOLD(false, BaseColor.GOLD, "gold", Util.newArrayListOfValues("gold")),
	BASE_YELLOW(false, BaseColor.YELLOW, "yellow", Util.newArrayListOfValues("yellow")),
	BASE_YELLOW_LIGHT(false, BaseColor.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("lightYellow")),
	
	BASE_GREEN_LIME(false, BaseColor.GREEN_LIME, "lime green", Util.newArrayListOfValues("limeGreen")),
	BASE_GREEN_LIGHT(false, BaseColor.GREEN_LIGHT, "light green", Util.newArrayListOfValues("lightGreen")),
	BASE_GREEN(false, BaseColor.GREEN, "green", Util.newArrayListOfValues("green")),
	BASE_GREEN_DARK(false, BaseColor.GREEN_DARK, "dark green", Util.newArrayListOfValues("darkGreen")),
	
	BASE_AQUA(false, BaseColor.AQUA, "aqua", Util.newArrayListOfValues("aqua")),
	BASE_TEAL(false, BaseColor.TEAL, "teal", Util.newArrayListOfValues("teal")),
	BASE_PERIWINKLE(false, BaseColor.PERIWINKLE, "periwinkle", Util.newArrayListOfValues("periwinkle")),
	BASE_BLUE_DARK(false, BaseColor.BLUE_DARK, "dark blue", Util.newArrayListOfValues("darkBlue")),
	BASE_BLUE_LIGHT(false, BaseColor.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("lightBlue")),
	BASE_BLUE(false, BaseColor.BLUE, "blue", Util.newArrayListOfValues("blue")),
	BASE_BLUE_STEEL(false, BaseColor.BLUE_STEEL, "steely blue", Util.newArrayListOfValues("steelyBlue")),
	
	BASE_BLACK(false, BaseColor.BLACK, "black", Util.newArrayListOfValues("black")),
	BASE_PITCH_BLACK(false, BaseColor.PITCH_BLACK, "black", Util.newArrayListOfValues("black")),
	
	// Game colors:
	BACKGROUND(false, Util.newColor(0x222222), Util.newColor(0xcccccc), "grey"),
	BACKGROUND_ALT(false, Util.newColor(0x292929), Util.newColor(0xbbbbbb), "grey"),
	
	MAP_BACKGROUND_UNEXPLORED(false, Util.newColor(0x111), Util.newColor(0x111), "black"),
	MAP_BACKGROUND_PINK(false, Util.newColor(0xb2a4bb), Util.newColor(0xb2a4bb), "pink"),
	MAP_BACKGROUND(false, Util.newColor(0xbbbbbb), Util.newColor(0xbbbbbb), "grey"),
	MAP_BACKGROUND_DARK(false, Util.newColor(0x888888), Util.newColor(0x8f8f8f), "dark grey"),
	MAP_BACKGROUND_BLUE(false, BaseColor.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("lightBlue")),
	
	GENERIC_SEX(false, BaseColor.PINK_LIGHT, "pink", Util.newArrayListOfValues("sex")),
	GENERIC_COMBAT(false, BaseColor.CRIMSON, "crimson"),
	GENERIC_ARCANE(false, BaseColor.PINK, "pink", Util.newArrayListOfValues("arcane")),
	GENERIC_TERRIBLE(false, BaseColor.CRIMSON, "crimson", Util.newArrayListOfValues("terrible")),
	GENERIC_MINOR_BAD(false, BaseColor.RED_LIGHT, "red", Util.newArrayListOfValues("minorBad", "badMinor")),
	GENERIC_MINOR_GOOD(false, BaseColor.GREEN_LIGHT, "light green", Util.newArrayListOfValues("minorGood", "goodMinor")),
	GENERIC_BAD(false, BaseColor.RED, "red", Util.newArrayListOfValues("bad")),
	GENERIC_GOOD(false, BaseColor.GREEN, "green", Util.newArrayListOfValues("good")),
	GENERIC_EXCELLENT(false, BaseColor.GOLD, "gold", Util.newArrayListOfValues("excellent")),
	GENERIC_ATTRIBUTE(false, BaseColor.MAGENTA, "magenta"),
	GENERIC_EXPERIENCE(false, BaseColor.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("experience", "xp")),
	COOLDOWN(false, BaseColor.CRIMSON, "crimson", Util.newArrayListOfValues("cooldown")),

	SCAR(false, BaseColor.TAN, "tan"),
	TATTOO(false, BaseColor.GREY, "grey"),
	
	PERK(false, BaseColor.AQUA, "aqua", Util.newArrayListOfValues("perk")),
	TRAIT(false, BaseColor.GREEN_LIGHT, "green", Util.newArrayListOfValues("trait")),
	FETISH(false, BaseColor.PINK_LIGHT, "light pink", Util.newArrayListOfValues("fetish")),
	STATUS_EFFECT(false, BaseColor.YELLOW, "yellow"),
	SPECIAL_ATTACK(false, BaseColor.CRIMSON, "crimson"),
	STATUS_EFFECT_TIME_OVERFLOW(false, BaseColor.BLUE, "aqua"),
	STATUS_EFFECT_TIME_HIGH(false, BaseColor.GREEN_LIGHT, "green"),
	STATUS_EFFECT_TIME_MEDIUM(false, BaseColor.ORANGE, "orange"),
	STATUS_EFFECT_TIME_LOW(false, BaseColor.RED, "red"),

	RACE_BESTIAL(false, BaseColor.TAN, "tan", Util.newArrayListOfValues("bestial", "animal", "feral")),
	RACE_UNKNOWN(false, BaseColor.BLACK, "black", Util.newArrayListOfValues("unknown")),
	RACE_HUMAN(false, BaseColor.BLUE_STEEL, "pale blue", Util.newArrayListOfValues("human")),
	RACE_DEMON(false, BaseColor.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("demon")),
	RACE_LILIN(false, BaseColor.PURPLE, "purple", Util.newArrayListOfValues("Lilin")),
	RACE_IMP(false, BaseColor.PURPLE, "purple", Util.newArrayListOfValues("imp")),
	RACE_ANGEL(false, BaseColor.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("angel")),
	RACE_DOG_MORPH(false, BaseColor.BROWN, "brown", Util.newArrayListOfValues("dogMorph", "dog")),
	RACE_CAT_MORPH(false, BaseColor.VIOLET, "violet", Util.newArrayListOfValues("catMorph", "cat")),
	RACE_CAT_MORPH_CARACAL(false, BaseColor.VIOLET, "violet", Util.newArrayListOfValues("ocelotMorph", "ocelot")),
	RACE_CAT_MORPH_LION(false, BaseColor.YELLOW, "yellow", Util.newArrayListOfValues("lionMorph", "lion")),
	RACE_CAT_MORPH_LEOPARD(false, BaseColor.GINGER, "ginger", Util.newArrayListOfValues("leopardMorph", "leopard")),
	RACE_CAT_MORPH_TIGER(false, BaseColor.GINGER, "ginger", Util.newArrayListOfValues("tigerMorph", "tiger")),
	RACE_CAT_MORPH_CHEETAH(false, BaseColor.YELLOW, "yellow", Util.newArrayListOfValues("cheetahMorph", "cheetah")),
	RACE_CAT_MORPH_LYNX(false, BaseColor.SILVER, "silver", Util.newArrayListOfValues("lynxMorph", "lynx")),
	RACE_CAT_MORPH_LEOPARD_SNOW(false, BaseColor.SILVER, "silver", Util.newArrayListOfValues("leopardSnowMorph", "snowLeopard", "snep", "leopardSnow", "snowLeopardMorph")),
	RACE_COW_MORPH(false, BaseColor.TAN, "tan", Util.newArrayListOfValues("cowMorph", "cow")),
	RACE_HORSE_MORPH(false, BaseColor.ORANGE, "orange", Util.newArrayListOfValues("horseMorph", "horse")),
	RACE_PEGASUS(false, BaseColor.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("pegasusMorph", "pegasus")),
	RACE_UNICORN(false, BaseColor.WHITE, "white", Util.newArrayListOfValues("unicornMorph", "unicorn")),
	RACE_ALICORN(false, BaseColor.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("alicornMorph", "alicorn")),
	RACE_CENTAUR(false, BaseColor.BROWN_DARK, "dark brown", Util.newArrayListOfValues("centaur")),
	RACE_PEGATAUR(false, BaseColor.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("pegataur")),
	RACE_REINDEER_MORPH(false, BaseColor.BROWN_DARK, "dark brown", Util.newArrayListOfValues("reindeerMorph", "reindeer")),
	RACE_WOLF_MORPH(false, BaseColor.BLACK, "black", Util.newArrayListOfValues("wolfMorph", "wolf")),
	RACE_FOX_MORPH(false, BaseColor.GINGER, "ginger", Util.newArrayListOfValues("foxMorph", "fox")),
	RACE_HARPY(false, BaseColor.PINK_LIGHT, "light pink", Util.newArrayListOfValues("harpy")),
	RACE_SLIME(false, BaseColor.GREEN_LIGHT, "light green", Util.newArrayListOfValues("slime")),
	RACE_SQUIRREL_MORPH(false, BaseColor.GINGER, "ginger", Util.newArrayListOfValues("squirrelMorph", "squirrel")),
	RACE_RAT_MORPH(false, BaseColor.BROWN_DARK, "dark brown", Util.newArrayListOfValues("ratMorph", "rat")),
	RACE_RABBIT_MORPH(false, BaseColor.BROWN_DARK, "dark brown", Util.newArrayListOfValues("rabbitMorph", "rabbit")),
	RACE_BAT_MORPH(false, BaseColor.BLACK, "black", Util.newArrayListOfValues("batMorph", "bat")),
	RACE_ALLIGATOR_MORPH(false, BaseColor.GREEN_DARK, "dark green", Util.newArrayListOfValues("alligatorMorph", "alligator", "gatorMorph", "gator")),
	
	QUEST_MAIN(false, BaseColor.PINK, "pink"),
	QUEST_SIDE(false, BaseColor.BLUE, "blue"),
	QUEST_RELATIONSHIP(false, BaseColor.PINK_LIGHT, "pink"),

	MAP_MARKER(false, Util.newColor(0x6163DB), Util.newColor(0x6163DB), "blue"),

	ATTRIBUTE_HEALTH(false, BaseColor.CRIMSON, "crimson", Util.newArrayListOfValues("health", "hp", "energy")),
	ATTRIBUTE_MANA(false, BaseColor.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("willpower", "wp", "mana", "aura")),
//	ATTRIBUTE_STAMINA(BaseColor.LILAC, "lilac", Util.newArrayListOfValues("stamina", "sp", "energy")),

	ATTRIBUTE_PHYSIQUE(false, BaseColor.MAGENTA, "magenta", Util.newArrayListOfValues("physique", "phys", "strength", "str")),
	ATTRIBUTE_ARCANE(false, BaseColor.PURPLE, "purple", Util.newArrayListOfValues("intelligence", "int")),
//	ATTRIBUTE_FITNESS(BaseColor.LILAC, "light purple", Util.newArrayListOfValues("fitness", "fit")),
	ATTRIBUTE_CORRUPTION(false, BaseColor.PINK_DEEP, "pink", Util.newArrayListOfValues("corruption", "cor", "corr")),

	ATTRIBUTE_AROUSAL(false, BaseColor.PINK_DEEP, "pink", Util.newArrayListOfValues("arousal", "ars")),
	ATTRIBUTE_LUST(false, BaseColor.MAGENTA, "magenta", Util.newArrayListOfValues("lust", "lst", "seduction")),

	//TODO
	PHYSIQUE_STAGE_ZERO(false, BaseColor.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_ONE(false, BaseColor.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_TWO(false, BaseColor.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_THREE(false, BaseColor.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_FOUR(false, BaseColor.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_FIVE(false, BaseColor.GOLD, "gold"),
	
	//TODO
	INTELLIGENCE_STAGE_ZERO(false, BaseColor.PURPLE, "purple"),
	INTELLIGENCE_STAGE_ONE(false, BaseColor.PURPLE, "purple"),
	INTELLIGENCE_STAGE_TWO(false, BaseColor.PURPLE, "purple"),
	INTELLIGENCE_STAGE_THREE(false, BaseColor.PURPLE, "purple"),
	INTELLIGENCE_STAGE_FOUR(false, BaseColor.PURPLE, "purple"),
	INTELLIGENCE_STAGE_FIVE(false, BaseColor.GOLD, "gold"),
	
	//TODO
	FITNESS_STAGE_ZERO(false, BaseColor.LILAC, "light purple"),
	FITNESS_STAGE_ONE(false, BaseColor.LILAC, "light purple"),
	FITNESS_STAGE_TWO(false, BaseColor.LILAC, "light purple"),
	FITNESS_STAGE_THREE(false, BaseColor.LILAC, "light purple"),
	FITNESS_STAGE_FOUR(false, BaseColor.LILAC, "light purple"),
	FITNESS_STAGE_FIVE(false, BaseColor.GOLD, "gold"),
	
	CORRUPTION_STAGE_ZERO(false, Util.newColor(0xffdf80), Util.newColor(0xffdf80), "gold"),
	CORRUPTION_STAGE_ONE(false, Util.newColor(0xff80bf), Util.newColor(0xff80bf), "pink"),
	CORRUPTION_STAGE_TWO(false, Util.newColor(0xff1a8c), Util.newColor(0xff1a8c), "pink"),
	CORRUPTION_STAGE_THREE(false, Util.newColor(0xe600ac), Util.newColor(0xe600ac), "pink"),
	CORRUPTION_STAGE_FOUR(false, Util.newColor(0xd411d4), Util.newColor(0xd411d4), "pink"),
	CORRUPTION_STAGE_FIVE(false, Util.newColor(0xbf00ff), Util.newColor(0xbf00ff), "pink"),
	
	AROUSAL_STAGE_ZERO(false, Util.newColor(0xfee6ff), Util.newColor(0xfee6ff), "pink"),
	AROUSAL_STAGE_ONE(false, Util.newColor(0xfcb3ff), Util.newColor(0xfcb3ff), "pink"),
	AROUSAL_STAGE_TWO(false, Util.newColor(0xfb80ff), Util.newColor(0xfb80ff), "pink"),
	AROUSAL_STAGE_THREE(false, Util.newColor(0xf94dff), Util.newColor(0xf94dff), "pink"),
	AROUSAL_STAGE_FOUR(false, Util.newColor(0xf824ff), Util.newColor(0xf824ff), "pink"),
	AROUSAL_STAGE_FIVE(false, Util.newColor(0xf700ff), Util.newColor(0xf700ff), "pink"),
	
	LUST_STAGE_ZERO(false, Util.newColor(0x80CAFF), Util.newColor(0xfee6ff), "blue"),
	LUST_STAGE_ONE(false, Util.newColor(0xB699FF), Util.newColor(0xfcb3ff), "purple"),
	LUST_STAGE_TWO(false, Util.newColor(0xFF99D1), Util.newColor(0xfb80ff), "pink"),
	LUST_STAGE_THREE(false, Util.newColor(0xFF61AB), Util.newColor(0xf94dff), "pink"),
	LUST_STAGE_FOUR(false, Util.newColor(0xFF3377), Util.newColor(0xf824ff), "dark pink"),
	LUST_STAGE_FIVE(false, Util.newColor(0xFF1A66), Util.newColor(0xf824ff), "dark pink"),

	DESIRE_STAGE_ZERO(false, Util.newColor(0xB699FF), Util.newColor(0xfcb3ff), "purple"),
	DESIRE_STAGE_ONE(false, Util.newColor(0xFF99D1), Util.newColor(0xfb80ff), "pink"),
	DESIRE_STAGE_TWO(false, Util.newColor(0xFF61AB), Util.newColor(0xf94dff), "pink"),
	DESIRE_STAGE_THREE(false, Util.newColor(0xFF3377), Util.newColor(0xf824ff), "dark pink"),
	DESIRE_STAGE_FOUR(false, Util.newColor(0xffdf80), Util.newColor(0xffdf80), "gold"),


	COMPANION(false, BaseColor.GREEN_LIGHT,  "light green", Util.newArrayListOfValues("companion", "companions")),
	
	AFFECTION(false, BaseColor.PINK_LIGHT,  "light pink", Util.newArrayListOfValues("affection")),
	OBEDIENCE(false, BaseColor.PURPLE_LIGHT,  "light purple", Util.newArrayListOfValues("obedience")),
	
	AFFECTION_NEGATIVE_FIVE(false, Util.newColor(0xff0066), Util.newColor(0x8e011e), "magenta"),
	AFFECTION_NEGATIVE_FOUR(false, Util.newColor(0xff2a7f), Util.newColor(0xa40123), "magenta"),
	AFFECTION_NEGATIVE_THREE(false, Util.newColor(0xff5599), Util.newColor(0xb21e44), "pink"),
	AFFECTION_NEGATIVE_TWO(false, Util.newColor(0xff80b2), Util.newColor(0xbc325a), "pink"),
	AFFECTION_NEGATIVE_ONE(false, Util.newColor(0xffaacc), Util.newColor(0xc44670), "pink"),
	AFFECTION_NEUTRAL(false, Util.newColor(0xe3dedb), Util.newColor(0xcd5986), "grey"),
	AFFECTION_POSITIVE_ONE(false, Util.newColor(0xffeeaa), Util.newColor(0xd66e9d), "yellow"),
	AFFECTION_POSITIVE_TWO(false, Util.newColor(0xffe680), Util.newColor(0xe082b3), "yellow"),
	AFFECTION_POSITIVE_THREE(false, Util.newColor(0xffdd55), Util.newColor(0xe996c9), "yellow"),
	AFFECTION_POSITIVE_FOUR(false, Util.newColor(0xffd42a), Util.newColor(0xf2aadf), "gold"),
	AFFECTION_POSITIVE_FIVE(false, Util.newColor(0xffcc00), Util.newColor(0xfbbcf4), "gold"),

	MASCULINE_PLUS(false, Util.newColor(0x4D9DFF), Util.newColor(0x4D9DFF), "dark blue", Util.newArrayListOfValues("masculineStrong", "masStr", "masculinePlus")),
	MASCULINE(false, Util.newColor(0x8ABEFF), Util.newColor(0x8ABEFF), "blue", Util.newArrayListOfValues("masculine", "mas")),
	ANDROGYNOUS(false, Util.newColor(0xB39EFF), Util.newColor(0xB39EFF), "purple", Util.newArrayListOfValues("androgynous", "andro")),
	FEMININE(false, Util.newColor(0xFFBDFF), Util.newColor(0xFFFBDFF), "pink", Util.newArrayListOfValues("feminine", "fem")),
	FEMININE_PLUS(false, Util.newColor(0xFF85FF), Util.newColor(0xFF85FF), "pink", Util.newArrayListOfValues("feminineStrong", "femStr", "femininePlus")),
	
	BODY_SIZE_ZERO(false, Util.newColor(0xFFEBD6), Util.newColor(0x241D00), "tan", Util.newArrayListOfValues("bodySizeZero")),
	BODY_SIZE_ONE(false, Util.newColor(0xFFE0BD), Util.newColor(0x3D3100), "tan", Util.newArrayListOfValues("bodySizeOne")),
	BODY_SIZE_TWO(false, Util.newColor(0xFFC88A), Util.newColor(0x5C4900), "tan", Util.newArrayListOfValues("bodySizeTwo")),
	BODY_SIZE_THREE(false, Util.newColor(0xFFAB57), Util.newColor(0x806600), "tan", Util.newArrayListOfValues("bodySizeThree")),
	BODY_SIZE_FOUR(false, Util.newColor(0xFF9124), Util.newColor(0x9E7E00), "tan", Util.newArrayListOfValues("bodySizeFour")),

	MUSCLE_ZERO(false, Util.newColor(0xDBFFF6), Util.newColor(0x001F17), "teal", Util.newArrayListOfValues("muscleZero")),
	MUSCLE_ONE(false, Util.newColor(0xBDFFED), Util.newColor(0x00382B), "teal", Util.newArrayListOfValues("muscleOne")),
	MUSCLE_TWO(false, Util.newColor(0x8AFFE0), Util.newColor(0x00523F), "teal", Util.newArrayListOfValues("muscleTwo")),
	MUSCLE_THREE(false, Util.newColor(0x57FFD2), Util.newColor(0x006B52), "teal", Util.newArrayListOfValues("muscleThree")),
	MUSCLE_FOUR(false, Util.newColor(0x24FFC5), Util.newColor(0x008566), "teal", Util.newArrayListOfValues("muscleFour")),

	AGE_LOLI(false, Util.newColor(0xAFE9B3), Util.newColor(0xc44670), "green", Util.newArrayListOfValues("ageLoli")), //I wanted to use my own color here originally, but it doesn't work Util.newColor(0xE6EFED), Util.newColor(0x009E27)
	
	AGE_CHILD(false, Util.newColor(0xE1F0C1), Util.newColor(0x73A112), "green", Util.newArrayListOfValues("ageChild")),
	AGE_PRETEENS(false, Util.newColor(0xE1F0C1), Util.newColor(0x73A112), "green", Util.newArrayListOfValues("agePreTeens")),
	AGE_EARLYTEENS(false, Util.newColor(0xE1F0C1), Util.newColor(0x73A112), "green", Util.newArrayListOfValues("ageEarlyTeens")),
	AGE_TEENS(false, Util.newColor(0xE1F0C1), Util.newColor(0x73A112), "green", Util.newArrayListOfValues("ageTeens")),
	AGE_TWENTIES(false, Util.newColor(0xCCE698), Util.newColor(0x638A0F), "green", Util.newArrayListOfValues("ageTwenties")),
	AGE_THIRTIES(false, Util.newColor(0xB8DC6F), Util.newColor(0x52730D), "green", Util.newArrayListOfValues("ageThirties")),
	AGE_FORTIES(false, Util.newColor(0xA4D246), Util.newColor(0x41590D), "green", Util.newArrayListOfValues("ageForties")),
	AGE_FIFTIES(false, Util.newColor(0x8AB92D), Util.newColor(0x334408), "green", Util.newArrayListOfValues("ageFifties")),
	AGE_SIXTIES(false, Util.newColor(0x6B9023), Util.newColor(0x232E05), "green", Util.newArrayListOfValues("ageSixties")),
	
	ALCOHOL(false, BaseColor.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("alcohol")),
	ALCOHOL_LEVEL_ZERO(false, Util.newColor(0xF2E8C0), Util.newColor(0x967F22), "light yellow"),
	ALCOHOL_LEVEL_ONE(false,  Util.newColor(0xEDDFAB), Util.newColor(0x967F22), "light yellow"),
	ALCOHOL_LEVEL_TWO(false,  Util.newColor(0xE8D696), Util.newColor(0x967F22), "yellow"),
	ALCOHOL_LEVEL_THREE(false,  Util.newColor(0xE3CE82), Util.newColor(0x967F22), "yellow"),
	ALCOHOL_LEVEL_FOUR(false,  Util.newColor(0xDEC66E), Util.newColor(0x967F22), "yellow"),
	ALCOHOL_LEVEL_FIVE(false, Util.newColor(0xD9BD59), Util.newColor(0x967F22), "gold"),
	
	PSYCHOACTIVE(false, BaseColor.MAGENTA, "magenta", Util.newArrayListOfValues("psychoactive")),

	TRANSFORMATION_SHRINK(false, BaseColor.RED, "red", Util.newArrayListOfValues("tfShrink", "shrink", "tfShrunk", "shrunk", "tfShrinking", "shrinking")),
	TRANSFORMATION_GROW(false, BaseColor.GREEN, "green", Util.newArrayListOfValues("tfGrow", "grow", "tfGrown", "grown", "tfGrowth", "growth")),

	GENERIC_SIZE_ONE(false, Util.newColor(0xAFE9B3), Util.newColor(0xc44670), "green"),
	GENERIC_SIZE_TWO(false, Util.newColor(0xA0E4A3), Util.newColor(0xbc325a), "green"),
	GENERIC_SIZE_THREE(false, Util.newColor(0x8FE096), Util.newColor(0xb21e44), "green"),
	GENERIC_SIZE_FOUR(false, Util.newColor(0x77DA7F), Util.newColor(0xa40123), "green"),
	GENERIC_SIZE_FIVE(false, Util.newColor(0x67D570), Util.newColor(0x8e011e), "green"),
	GENERIC_SIZE_SIX(false, Util.newColor(0x57D161), Util.newColor(0x8e011e), "green"),
	GENERIC_SIZE_SEVEN(false, Util.newColor(0x47CD52), Util.newColor(0x8e011e), "green"),
	GENERIC_SIZE_EIGHT(false, Util.newColor(0x37C843), Util.newColor(0x8e011e), "green"),
	
	WETNESS(false, BaseColor.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("wetness", "wet", "tfWetness", "tfWet")),
	PLASTICITY(false, BaseColor.LILAC, "lilac", Util.newArrayListOfValues("plasticity", "tfPlasticity")),
	ELASTICITY(false, BaseColor.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("elasticity", "tfElasticity")),
	TRANSFORMATION_GENERIC(false, BaseColor.GREEN_LIME, "lime", Util.newArrayListOfValues("tfGeneric", "tfBase", "genericTF")),
	TRANSFORMATION_SEXUAL(false, BaseColor.PINK_LIGHT, "pink", Util.newArrayListOfValues("tfSex", "tfSexual", "sexualTF")),
	TRANSFORMATION_HUMAN(false, BaseColor.BLUE_STEEL, "pale blue", Util.newArrayListOfValues("tfHuman")),
	TRANSFORMATION_PARTIAL(false, Util.newColor(0xff80bf), Util.newColor(0xff80bf), "purple", Util.newArrayListOfValues("tfPartial")),
	TRANSFORMATION_PARTIAL_FULL(false, Util.newColor(0xff1a8c), Util.newColor(0xff1a8c), "purple", Util.newArrayListOfValues("tfMinor")),
	TRANSFORMATION_LESSER(false, Util.newColor(0xe600ac), Util.newColor(0xe600ac), "purple", Util.newArrayListOfValues("tfLesser")),
	TRANSFORMATION_GREATER(false, Util.newColor(0xd411d4), Util.newColor(0xd411d4), "purple-pink", Util.newArrayListOfValues("tfGreater")),

	// Speech colors:
	MASCULINE_PLUS_NPC(false, BaseColor.BLUE, "blue"),
	MASCULINE_NPC(false, BaseColor.BLUE_LIGHT, "blue"),
	ANDROGYNOUS_NPC(false, BaseColor.LILAC_LIGHT, "purple"),
	FEMININE_NPC(false, BaseColor.ROSE, "pink"),
	FEMININE_PLUS_NPC(false, BaseColor.PINK, "pink"),

	// Combat colors:
	DAMAGE_TYPE_PHYSICAL(false, Util.newColor(0xFF428E), Util.newColor(0xFF428E), "red", Util.newArrayListOfValues("dmgPhysical", "resPhysical", "physical")),
	DAMAGE_TYPE_MANA(false, BaseColor.PURPLE_LIGHT, "purple", Util.newArrayListOfValues("dmgMana", "resMana")),
	DAMAGE_TYPE_LUST(false, BaseColor.MAGENTA, "magenta", Util.newArrayListOfValues("dmgLust", "resLust")),
	DAMAGE_TYPE_SPELL(false, Util.newColor(0xFF6BDA), Util.newColor(0xFF6BDA), "pink", Util.newArrayListOfValues("dmgSpell", "resSpell", "spell")),
	DAMAGE_TYPE_FIRE(false, Util.newColor(0xff9955), Util.newColor(0xff9955), "orange", Util.newArrayListOfValues("dmgFire", "resFire", "fire")),
	DAMAGE_TYPE_COLD(false, Util.newColor(0x85C6FF), Util.newColor(0x85C6FF), "blue", Util.newArrayListOfValues("dmgCold", "resCold", "cold", "ice")),
	DAMAGE_TYPE_POISON(false, Util.newColor(0x85FF8B), Util.newColor(0x85FF8B), "green", Util.newArrayListOfValues("dmgPoison", "resPoison", "poison")),
	DAMAGE_TYPE_PURE(false, Util.newColor(0xFFCC00), Util.newColor(0xFFCC00), "gold", Util.newArrayListOfValues("dmgPure", "resPure", "pure")),

	SPELL_SCHOOL_FIRE(false, BaseColor.ORANGE, "orange", Util.newArrayListOfValues("spellFire", "schoolFire")),
	SPELL_SCHOOL_WATER(false, BaseColor.AQUA, "aqua", Util.newArrayListOfValues("water", "spellWater", "schoolWater")),
	SPELL_SCHOOL_EARTH(false, BaseColor.BROWN, "brown", Util.newArrayListOfValues("earth", "spellEarth", "schoolEarth")),
	SPELL_SCHOOL_AIR(false, BaseColor.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("air", "spellAir", "schoolAir")),
	SPELL_SCHOOL_ARCANE(false, BaseColor.PINK, "pink", Util.newArrayListOfValues("spellArcane", "schoolArcane")),
	
	// Rarity colors:
	RARITY_UNKNOWN(false, BaseColor.BLACK, "grey"),
	RARITY_JINXED(false, BaseColor.RED, "red", Util.newArrayListOfValues("jinx", "jinxed")),
	RARITY_COMMON(false, Util.newColor(0xf2f2f2), Util.newColor(0xf2f2f2), "white", Util.newArrayListOfValues("common")),
	RARITY_UNCOMMON(false, Util.newColor(0x1de547), Util.newColor(0x108228), "green", Util.newArrayListOfValues("uncommon")),
	RARITY_RARE(false, Util.newColor(0x47C2FF), Util.newColor(0x47C2FF), "blue", Util.newArrayListOfValues("rare")),
	RARITY_EPIC(false, Util.newColor(0xFF4DFC), Util.newColor(0xFF4DFC), "purple", Util.newArrayListOfValues("epic")),
	RARITY_LEGENDARY(false, Util.newColor(0xffcc00), Util.newColor(0xffcc00), "gold", Util.newArrayListOfValues("legendary")),
	RARITY_QUEST(false, BaseColor.TEAL, "teal", Util.newArrayListOfValues("teal")),

	// Inventory colors:
	CURRENCY_GOLD(true, BaseColor.GOLD, "gold"),
	CURRENCY_SILVER(true, BaseColor.SILVER, "gold"),
	CURRENCY_COPPER(true, BaseColor.COPPER, "gold"),

	MILK(false, BaseColor.YELLOW_LIGHT,  "light yellow", Util.newArrayListOfValues("milk", "lactation")),
	CUM(false, BaseColor.BLUE_LIGHT,  "light blue", Util.newArrayListOfValues("cum", "cummed", "dirty")),
	GIRLCUM(false, BaseColor.PINK_LIGHT,  "light pink", Util.newArrayListOfValues("girlcum", "gcum")),
	
	SEALED(false, BaseColor.PINK_DEEP, "pink"),
	DISPLACED(false, BaseColor.CRIMSON, "crimson"),

	// Text colors:
	TEXT(false, Util.newColor(0xDDDDDD), Util.newColor(0x262626), "grey", Util.newArrayListOfValues("text")),
	TEXT_HALF_GREY(false, Util.newColor(0xBBBBBB), Util.newColor(0x444444), "grey", Util.newArrayListOfValues("halfDisabled")),
	TEXT_GREY(false, Util.newColor(0x777777), Util.newColor(0x777777), "grey", Util.newArrayListOfValues("disabled")),
	TEXT_GREY_DARK(false, Util.newColor(0x444444), Util.newColor(0xcccccc), "grey", Util.newArrayListOfValues("disabledDark")),

	// Standard colors used for clothing:
	CLOTHING_WHITE(false, Util.newColor(0xdddddd), Util.newColor(0xdddddd), "white"),
	CLOTHING_GREY(false, Util.newColor(0x777777), Util.newColor(0x777777), "grey"),
	CLOTHING_BLACK(false, Util.newColor(0x333333), Util.newColor(0x333333), "black"),
	
	CLOTHING_RED_VERY_DARK(false, Util.newColor(0x660016), Util.newColor(0x660016), "midnight red"),
	CLOTHING_RED_DARK(false, Util.newColor(0x900020), Util.newColor(0x900020), "burgundy"),
	CLOTHING_RED_BRIGHT(false, Util.newColor(0xFA2424), Util.newColor(0xFA2424), "bright red"),
	CLOTHING_RED(false, Util.newColor(0xD84646), Util.newColor(0xD84646), "red"),
	CLOTHING_BROWN(false, Util.newColor(0xC87137), Util.newColor(0xC87137), "brown"),
	CLOTHING_BROWN_DARK(false, Util.newColor(0x63391C), Util.newColor(0x63391C), "dark brown"),
	CLOTHING_BROWN_VERY_DARK(false, Util.newColor(0x3C2211), Util.newColor(0x3C2211), "midnight brown"),
	CLOTHING_ORANGE(false, Util.newColor(0xE79F6F), Util.newColor(0xE79F6F), "orange"),
	CLOTHING_ORANGE_BRIGHT(false, Util.newColor(0xFF7900), Util.newColor(0xFF7900), "bright orange"),
	CLOTHING_ORANGE_DARK(false, Util.newColor(0xE56D00), Util.newColor(0xE56D00), "dark orange"),
	CLOTHING_TAN(false, BaseColor.TAN, "tan"),
	CLOTHING_OLIVE(false, Util.newColor(0x5f4a2a), Util.newColor(0x5f4a2a), "olive"),//0x887509 0x5f4a2a
	CLOTHING_YELLOW(false, Util.newColor(0xE2C360), Util.newColor(0xE2C360), "yellow"),
	CLOTHING_YELLOW_DARK(false, Util.newColor(0x7F691A), Util.newColor(0x7F691A), "mustard yellow"),
	CLOTHING_GREEN_LIME(false, Util.newColor(0xD0E37D), Util.newColor(0xD0E37D), "lime green"),
	CLOTHING_GREEN(false, Util.newColor(0x74AA74), Util.newColor(0x74AA74), "green"),
	CLOTHING_GREEN_DRAB(false, Util.newColor(0x4C5D4C), Util.newColor(0x4C5D4C), "drab green"),
	CLOTHING_GREEN_DARK(false, Util.newColor(0x3B6F3D), Util.newColor(0x3B6F3D), "dark green"),
	CLOTHING_GREEN_VERY_DARK(false, Util.newColor(0x112211), Util.newColor(0x112211), "midnight green"),
	CLOTHING_TURQUOISE(false, Util.newColor(0x6EC4B3), Util.newColor(0x6EC4B3), "turquoise"),
	CLOTHING_BLUE_LIGHT(false, Util.newColor(0x72CFE3), Util.newColor(0x72CFE3), "light blue"),
	CLOTHING_BLUE(false, Util.newColor(0x3971C6), Util.newColor(0x3971C6), "blue"),
	CLOTHING_BLUE_DARK(false, Util.newColor(0x003C89), Util.newColor(0x003C89), "dark blue"),
	CLOTHING_BLUE_VERY_DARK(false, Util.newColor(0x002C66), Util.newColor(0x002C66), "midnight blue"),
	CLOTHING_PURPLE_VERY_DARK(false, Util.newColor(0x322145), Util.newColor(0x322145), "midnight purple"),
	CLOTHING_PURPLE_DARK(false, Util.newColor(0x674A95), Util.newColor(0x674A95), "dark purple"),
	CLOTHING_PURPLE(false, Util.newColor(0xA382D3), Util.newColor(0xA382D3), "purple"),
	CLOTHING_PURPLE_LIGHT(false, Util.newColor(0xC58ED7), Util.newColor(0xC58ED7), "violet"),
	CLOTHING_PERIWINKLE(false, BaseColor.PERIWINKLE, "periwinkle"),
	CLOTHING_PINK_LIGHT(false, Util.newColor(0xF4B3F4), Util.newColor(0xF4B3F4), "light pink"),
	CLOTHING_PINK(false, Util.newColor(0xD75086), Util.newColor(0xD75086), "pink"),
	CLOTHING_PINK_DARK(false, Util.newColor(0xFF1493), Util.newColor(0xFF1493), "deep pink"),
	CLOTHING_PINK_HOT(false, Util.newColor(0xff69b4), Util.newColor(0xff69b4), "hot pink"),
	
	CLOTHING_BLACK_STEEL(true, Util.newColor(0x333333), Util.newColor(0x333333), "black steel"),
	CLOTHING_STEEL(true, Util.newColor(0x969696), Util.newColor(0x969696), "steel"),
	CLOTHING_BRASS(true, Util.newColor(0xab8317), Util.newColor(0xab8317), "brass"),
	CLOTHING_COPPER(true, Util.newColor(0xD46F2B), Util.newColor(0xD46F2B), "copper", Util.newArrayListOfValues("copper")),
	CLOTHING_SILVER(true, Util.newColor(0xC4C4C4), Util.newColor(0xC4C4C4), "silver", Util.newArrayListOfValues("silver")),
	CLOTHING_GOLD(true, Util.newColor(0xEBC633), Util.newColor(0xEBC633), "gold"),
	CLOTHING_ROSE_GOLD(true, BaseColor.ROSE_GOLD, "rose gold"),
	CLOTHING_PLATINUM(true, BaseColor.PLATINUM, "platinum"),
	
	// For special use with rainbow clothing:
	CLOTHING_MULTICOLORED(false, Util.newColor(0xff3030), Util.newColor(0xccffff), "multicolored"),

	// Body parts:

	// Skin (Human and Demon):
	SKIN_PALE(false, Util.newColor(0xFBF4E9), Util.newColor(0x534946), "pale"),
	SKIN_LIGHT(false, Util.newColor(0xEFDBD7), Util.newColor(0x534946), "light"),
	SKIN_PORCELAIN(false, Util.newColor(0xDBCDB9), Util.newColor(0xDBCDB9), "porcelain"),
	SKIN_ROSY(false, Util.newColor(0xDDAA93), Util.newColor(0xDDAA93), "rosy"),
	SKIN_OLIVE(false, BaseColor.TAN, "olive"),
	SKIN_TANNED(false, Util.newColor(0xC39D6B), Util.newColor(0xC39D6B), "tanned"),
	SKIN_DARK(false, BaseColor.BROWN_DARK, "dark"),
	SKIN_CHOCOLATE(false, Util.newColor(0x59372D), Util.newColor(0x59372D), "chocolate"),
	SKIN_EBONY(false, BaseColor.BLACK, "ebony"),
	
	SKIN_RED(false, BaseColor.CRIMSON, "scarlet"),
	SKIN_RED_DARK(false, BaseColor.RED_DARK, "dark red"),
	SKIN_BROWN(false, BaseColor.BROWN, "brown"),
	SKIN_YELLOW(false, BaseColor.YELLOW, "yellow"),
	SKIN_AMBER(false, BaseColor.AMBER, "amber"),
	SKIN_PINK(false, BaseColor.PINK, "pink"),
	SKIN_PINK_LIGHT(false, BaseColor.PINK_LIGHT, "light pink"),
	SKIN_GREEN(false, BaseColor.GREEN, "green"),
	SKIN_GREEN_DARK(false, BaseColor.GREEN_DARK, "dark green"),
	SKIN_BLUE_LIGHT(false, BaseColor.BLUE_LIGHT, "light blue"),
	SKIN_BLUE(false, BaseColor.BLUE, "blue"),
	SKIN_BLUE_DARK(false, BaseColor.BLUE_DARK, "dark blue"),
	SKIN_PERIWINKLE(false, BaseColor.PERIWINKLE, "periwinkle"),
	SKIN_LILAC(false, BaseColor.LILAC, "lilac"),
	SKIN_PURPLE(false, BaseColor.PURPLE, "purple"),
	SKIN_PURPLE_DARK(false, BaseColor.PURPLE_DARK, "dark purple"),
	SKIN_IVORY(false, BaseColor.WHITE, "ivory"),
	SKIN_GREY(false, BaseColor.GREY, "grey"),

	// Slime types:

	SLIME_RED(false, BaseColor.RED, "translucent red"),
	SLIME_RED_DARK(false, BaseColor.RED_DARK, "translucent dark red"),
	SLIME_BROWN_DARK(false, BaseColor.BROWN_DARK, "translucent dark brown"),
	SLIME_BROWN(false, BaseColor.BROWN, "translucent brown"),
	SLIME_ORANGE(false, BaseColor.ORANGE, "translucent orange"),
	SLIME_TAN(false, BaseColor.TAN, "translucent tan"),
	SLIME_YELLOW(false, BaseColor.YELLOW, "translucent yellow"),
	SLIME_AMBER(false, BaseColor.AMBER, "translucent amber"),
	SLIME_PINK_DARK(false, BaseColor.PINK_DEEP, "translucent dark pink"),
	SLIME_PINK(false, BaseColor.PINK, "translucent pink"),
	SLIME_PINK_LIGHT(false, BaseColor.PINK_LIGHT, "translucent light pink"),
	SLIME_GREEN(false, BaseColor.GREEN, "translucent green"),
	SLIME_GREEN_DARK(false, BaseColor.GREEN_DARK, "translucent dark green"),
	SLIME_BLUE_LIGHT(false, BaseColor.BLUE_LIGHT, "translucent light blue"),
	SLIME_BLUE(false, BaseColor.BLUE, "translucent blue"),
	SLIME_BLUE_DARK(false, BaseColor.BLUE_DARK, "translucent dark blue"),
	SLIME_PERIWINKLE(false, BaseColor.PERIWINKLE, "translucent periwinkle"),
	SLIME_LILAC(false, BaseColor.LILAC, "translucent lilac"),
	SLIME_PURPLE(false, BaseColor.PURPLE, "translucent purple"),
	SLIME_PURPLE_DARK(false, BaseColor.PURPLE_DARK, "translucent dark purple"),
	SLIME_CLEAR(false, BaseColor.WHITE, "clear"),
	SLIME_GREY(false, BaseColor.GREY, "translucent grey"),
	SLIME_BLACK(false, BaseColor.BLACK, "translucent black"),
	SLIME_WHITE(false, BaseColor.WHITE, "translucent white"),
	SLIME_RAINBOW(false, BaseColor.PINK,
			"translucent "
			+ "<span style='color:#E64C4C;'>r</span>"
			+ "<span style='color:#E6854C;'>a</span>"
			+ "<span style='color:#E6C74C;'>i</span>"
			+ "<span style='color:#6EE64C;'>n</span>"
			+ "<span style='color:#4CB2E6;'>b</span>"
			+ "<span style='color:#AD4CE6;'>o</span>"
			+ "<span style='color:#E64CA8;'>w</span>") {
		public boolean isRainbow() {
			return true;
		}
	},

	// Feathers:
//	FEATHERS_RED(false, BaseColor.RED, "red"),
//	FEATHERS_RED_DARK(false, BaseColor.RED_DARK, "dark red"),
//	FEATHERS_BROWN_DARK(false, BaseColor.BROWN_DARK, "dark brown"),
//	FEATHERS_BROWN(false, BaseColor.BROWN, "brown"),
//	FEATHERS_TAN(false, BaseColor.TAN, "tan"),
//	FEATHERS_ORANGE(false, BaseColor.ORANGE, "orange"),
//	FEATHERS_GINGER(false, BaseColor.GINGER, "ginger"),
//	FEATHERS_BLEACH_BLONDE(false, BaseColor.YELLOW_LIGHT, "bleach-blonde"),
//	FEATHERS_AMBER(false, BaseColor.AMBER, "amber"),
//	FEATHERS_PINK(false, BaseColor.PINK, "pink"),
//	FEATHERS_GREEN(false, BaseColor.GREEN, "green"),
//	FEATHERS_GREEN_DARK(false, BaseColor.GREEN_DARK, "dark green"),
//	FEATHERS_BLUE_LIGHT(false, BaseColor.BLUE_LIGHT, "light blue"),
//	FEATHERS_BLUE(false, BaseColor.BLUE, "blue"),
//	FEATHERS_BLUE_DARK(false, BaseColor.BLUE_DARK, "dark blue"),
//	FEATHERS_PERIWINKLE(false, BaseColor.PERIWINKLE, "periwinkle"),
//	FEATHERS_PURPLE(false, BaseColor.PURPLE, "purple"),
//	FEATHERS_PURPLE_DARK(false, BaseColor.PURPLE_DARK, "dark purple"),
//	FEATHERS_GREY(false, BaseColor.GREY, "grey"),
//	FEATHERS_BLACK(false, BaseColor.BLACK, "black"),
//	FEATHERS_WHITE(false, BaseColor.WHITE, "white"),

	// Horns:
	HORN_WHITE(false, BaseColor.WHITE, "ivory"),
	HORN_GREY(false, BaseColor.GREY, "grey"),
	HORN_DARK_GREY(false, BaseColor.GREY_DARK, "dark-grey"),
	HORN_BLACK(false, BaseColor.BLACK, "black"),
	
	HORN_RED(false, BaseColor.RED, "red"),
	HORN_SCARLET(false, BaseColor.CRIMSON, "scarlet"),
	HORN_BROWN(false, BaseColor.BROWN, "brown"),
	HORN_DARK_BROWN(false, BaseColor.BROWN_DARK, "dark brown"),
	HORN_AMBER(false, BaseColor.AMBER, "amber"),
	HORN_PINK(false, BaseColor.PINK_LIGHT, "light pink"),
	HORN_GREEN(false, BaseColor.GREEN, "green"),
	HORN_BLUE(false, BaseColor.BLUE_LIGHT, "light blue"),
	HORN_LILAC(false, BaseColor.LILAC, "lilac"),
	HORN_PURPLE(false, BaseColor.PURPLE, "purple"),

	// Antlers:
	ANTLER_WHITE(false, BaseColor.WHITE, "ivory"),
	ANTLER_TAN(false, BaseColor.TAN, "tan"),
	ANTLER_BROWN(false, BaseColor.BROWN, "brown"),
	ANTLER_DARK_BROWN(false, BaseColor.BROWN_DARK, "dark brown"),
	ANTLER_GREY(false, BaseColor.GREY, "grey"),
	ANTLER_DARK_GREY(false, BaseColor.GREY_DARK, "dark-grey"),
	ANTLER_BLACK(false, BaseColor.BLACK, "black"),
	
	ANTLER_RED(false, BaseColor.RED, "red"),
	ANTLER_SCARLET(false, BaseColor.CRIMSON, "scarlet"),
	ANTLER_AMBER(false, BaseColor.AMBER, "amber"),
	ANTLER_PINK(false, BaseColor.PINK_LIGHT, "light pink"),
	ANTLER_GREEN(false, BaseColor.GREEN, "green"),
	ANTLER_BLUE(false, BaseColor.BLUE_LIGHT, "light blue"),
	ANTLER_LILAC(false, BaseColor.LILAC, "lilac"),
	ANTLER_PURPLE(false, BaseColor.PURPLE, "purple"),

	// Orifices:
	ORIFICE_INTERIOR(false, BaseColor.ROSE, "fleshy-pink"),

	// Misc:
	TONGUE(false, BaseColor.ROSE, "pink"),

	// Generic colors:
	COVERING_SILVER(true, BaseColor.GREY, "metallic silver"),
	COVERING_GOLD(true, BaseColor.GOLD, "metallic gold"),
	COVERING_PLATINUM(true, BaseColor.PLATINUM, "metallic platinum"),
	COVERING_ROSE_GOLD(true, BaseColor.ROSE_GOLD, "metallic rose gold"),
	COVERING_COPPER(true, BaseColor.COPPER, "metallic copper"),
	COVERING_STEEL(true, Util.newColor(0x969696), Util.newColor(0x969696), "metallic steel"),
	COVERING_TAN(false, BaseColor.TAN, "tan"),
	COVERING_BROWN(false, BaseColor.BROWN, "brown"),
	COVERING_BROWN_DARK(false, BaseColor.BROWN_DARK, "dark brown"),
	COVERING_BLACK(false, BaseColor.BLACK, "black"),
	COVERING_GREY(false, BaseColor.GREY, "grey"),
	COVERING_DIRTY_BLONDE(false, BaseColor.TAN, "dirty-blonde"),
	COVERING_BLONDE(false, BaseColor.YELLOW, "blonde"),
	COVERING_BLEACH_BLONDE(false, BaseColor.YELLOW_LIGHT, "bleach-blonde"),
	COVERING_YELLOW(false, BaseColor.YELLOW, "yellow"),
	COVERING_GINGER(false, BaseColor.GINGER, "ginger"),
	COVERING_ORANGE(false, BaseColor.ORANGE, "orange"),
	COVERING_AMBER(false, BaseColor.AMBER, "amber"),
	COVERING_RED_LIGHT(false, BaseColor.RED_LIGHT, "red"),
	COVERING_RED(false, BaseColor.RED, "red"),
	COVERING_RED_DARK(false, BaseColor.RED_DARK, "dark red"),
	COVERING_AUBURN(false, BaseColor.AUBURN, "auburn"),
	COVERING_WHITE(false, BaseColor.WHITE, "white"),
	COVERING_BLUE_LIGHT(false, BaseColor.BLUE_LIGHT, "light blue"),
	COVERING_BLUE(false, BaseColor.BLUE, "blue"),
	COVERING_BLUE_DARK(false, BaseColor.BLUE_DARK, "dark blue"),
	COVERING_PERIWINKLE(false, BaseColor.PERIWINKLE, "periwinkle"),
	COVERING_LILAC(false, BaseColor.LILAC, "lilac"),
	COVERING_PURPLE_LIGHT(false, BaseColor.PURPLE_LIGHT, "light purple"),
	COVERING_PURPLE(false, BaseColor.PURPLE, "purple"),
	COVERING_PURPLE_DARK(false, BaseColor.PURPLE_DARK, "dark purple"),
	COVERING_PINK_LIGHT(false, BaseColor.PINK_LIGHT, "light pink"),
	COVERING_PINK(false, BaseColor.PINK, "pink"),
	COVERING_PINK_DARK(false, BaseColor.PINK_DEEP, "dark pink"),
	COVERING_GREEN(false, BaseColor.GREEN, "green"),
	COVERING_GREEN_DARK(false, BaseColor.GREEN_DARK, "dark green"),
	
	// Specials:
	COVERING_CLEAR(false, BaseColor.WHITE, "clear"), // For nail-polish
	COVERING_RAINBOW(false, BaseColor.BLUE,
			"<span style='color:#E64C4C;'>r</span>"
			+ "<span style='color:#E6854C;'>a</span>"
			+ "<span style='color:#E6C74C;'>i</span>"
			+ "<span style='color:#6EE64C;'>n</span>"
			+ "<span style='color:#4CB2E6;'>b</span>"
			+ "<span style='color:#AD4CE6;'>o</span>"
			+ "<span style='color:#E64CA8;'>w</span>") {
		public boolean isRainbow() {
			return true;
		}
	},
	COVERING_NONE(false, BaseColor.GREY, "none"),

	// Eye colors:
	EYE_WHITE(false, BaseColor.WHITE, "white"),
	
	EYE_BROWN(false, BaseColor.BROWN, "brown"),
	EYE_BLUE(false, BaseColor.BLUE_LIGHT, "blue"),
	EYE_HAZEL(false, BaseColor.TAN, "hazel"),
	EYE_AQUA(false, BaseColor.AQUA, "aqua"),
	EYE_GREEN(false, BaseColor.GREEN, "green"),
	EYE_GREY(false, BaseColor.GREY, "grey"),

	EYE_PERIWINKLE(false, BaseColor.PERIWINKLE, "periwinkle"),
	EYE_LILAC(false, BaseColor.LILAC, "lilac"),
	EYE_PURPLE(false, BaseColor.PURPLE, "purple"),
	EYE_VIOLET(false, BaseColor.VIOLET, "violet"),
	EYE_CRIMSON(false, BaseColor.CRIMSON, "crimson"),
	EYE_GOLD(false, BaseColor.GOLD, "golden"),
	EYE_SILVER(false, BaseColor.SILVER, "silver"),
	
	EYE_YELLOW(false, BaseColor.YELLOW, "yellow"),
	EYE_AMBER(false, BaseColor.AMBER, "amber"),
	EYE_RED(false, BaseColor.RED, "red"),
	EYE_PINK(false, BaseColor.PINK, "pink"),
	EYE_ORANGE(false, BaseColor.ORANGE, "orange"),
	EYE_BLACK(false, BaseColor.BLACK, "black");
	
	// Skin/fur/body part groups:
	
	public static List<Color> humanSkinColors = Util.newArrayListOfValues(
			Color.SKIN_PALE,
			Color.SKIN_LIGHT,
			Color.SKIN_PORCELAIN,
			Color.SKIN_ROSY,
			Color.SKIN_OLIVE,
			Color.SKIN_TANNED,
			Color.SKIN_DARK,
			Color.SKIN_CHOCOLATE,
			Color.SKIN_EBONY);

	public static List<Color> ratSkinColors = Util.newArrayListOfValues(
			Color.SKIN_PINK_LIGHT);
	
	public static List<Color> demonSkinColors = Util.newArrayListOfValues(
			Color.SKIN_PALE,
			Color.SKIN_LIGHT,
			Color.SKIN_PORCELAIN,
			Color.SKIN_ROSY,
			Color.SKIN_OLIVE,
			Color.SKIN_TANNED,
			Color.SKIN_DARK,
			Color.SKIN_CHOCOLATE,
			Color.SKIN_EBONY,
			Color.SKIN_IVORY,
			Color.SKIN_GREY,
			Color.SKIN_RED,
			Color.SKIN_RED_DARK,
			Color.SKIN_BROWN,
			Color.SKIN_AMBER,
			Color.SKIN_YELLOW,
			Color.SKIN_GREEN,
			Color.SKIN_GREEN_DARK,
			Color.SKIN_BLUE_LIGHT,
			Color.SKIN_BLUE,
			Color.SKIN_BLUE_DARK,
			Color.SKIN_PERIWINKLE,
			Color.SKIN_LILAC,
			Color.SKIN_PURPLE,
			Color.SKIN_PURPLE_DARK,
			Color.SKIN_PINK_LIGHT,
			Color.SKIN_PINK);

	public static List<Color> allSkinColors = Util.newArrayListOfValues(
			Color.SKIN_PALE,
			Color.SKIN_LIGHT,
			Color.SKIN_PORCELAIN,
			Color.SKIN_ROSY,
			Color.SKIN_OLIVE,
			Color.SKIN_TANNED,
			Color.SKIN_DARK,
			Color.SKIN_CHOCOLATE,
			Color.SKIN_EBONY,
			Color.SKIN_IVORY,
			Color.SKIN_GREY,
			Color.SKIN_RED,
			Color.SKIN_RED_DARK,
			Color.SKIN_BROWN,
			Color.SKIN_AMBER,
			Color.SKIN_YELLOW,
			Color.SKIN_GREEN,
			Color.SKIN_GREEN_DARK,
			Color.SKIN_BLUE_LIGHT,
			Color.SKIN_BLUE,
			Color.SKIN_BLUE_DARK,
			Color.SKIN_PERIWINKLE,
			Color.SKIN_LILAC,
			Color.SKIN_PURPLE,
			Color.SKIN_PURPLE_DARK,
			Color.SKIN_PINK_LIGHT,
			Color.SKIN_PINK,
			Color.COVERING_RAINBOW);


	public static List<Color> naturalSlimeColors = Util.newArrayListOfValues(
			Color.SLIME_CLEAR,
			Color.SLIME_WHITE,
			Color.SLIME_GREY,
			Color.SLIME_BLACK,
			Color.SLIME_RED,
			Color.SLIME_RED_DARK,
			Color.SLIME_BROWN_DARK,
			Color.SLIME_BROWN,
			Color.SLIME_TAN,
			Color.SLIME_YELLOW,
			Color.SLIME_AMBER,
			Color.SLIME_GREEN,
			Color.SLIME_GREEN_DARK,
			Color.SLIME_BLUE_LIGHT,
			Color.SLIME_BLUE,
			Color.SLIME_BLUE_DARK,
			Color.SLIME_PERIWINKLE,
			Color.SLIME_LILAC,
			Color.SLIME_PURPLE,
			Color.SLIME_PURPLE_DARK,
			Color.SLIME_PINK_DARK,
			Color.SLIME_PINK,
			Color.SLIME_PINK_LIGHT
			);
	
	public static List<Color> dyeSlimeColors = Util.newArrayListOfValues(
			Color.SLIME_RAINBOW
	);
	
	public static List<Color> naturalFeatherColors = Util.newArrayListOfValues(
			Color.COVERING_WHITE,
			Color.COVERING_GREY,
			Color.COVERING_BLACK,
			Color.COVERING_RED_LIGHT,
			Color.COVERING_RED,
			Color.COVERING_RED_DARK,
			Color.COVERING_BROWN_DARK,
			Color.COVERING_BROWN,
			Color.COVERING_TAN,
			Color.COVERING_ORANGE,
			Color.COVERING_GINGER,
			Color.COVERING_BLEACH_BLONDE,
			Color.COVERING_YELLOW,
			Color.COVERING_AMBER,
			Color.COVERING_GREEN,
			Color.COVERING_GREEN_DARK,
			Color.COVERING_BLUE_LIGHT,
			Color.COVERING_BLUE,
			Color.COVERING_BLUE_DARK,
			Color.COVERING_PERIWINKLE,
			Color.COVERING_LILAC,
			Color.COVERING_PURPLE_LIGHT,
			Color.COVERING_PURPLE,
			Color.COVERING_PURPLE_DARK,
			Color.COVERING_PINK,
			Color.COVERING_PINK_LIGHT
			);
			
	public static List<Color> dyeFeatherColors = Util.newArrayListOfValues(
			Color.COVERING_PLATINUM,
			Color.COVERING_GOLD,
			Color.COVERING_SILVER,
			Color.COVERING_COPPER,
			Color.COVERING_STEEL,
			Color.COVERING_ROSE_GOLD,
			Color.COVERING_RAINBOW
			);
			
	public static List<Color> naturalFurColors = Util.newArrayListOfValues(
			Color.COVERING_WHITE,
			Color.COVERING_SILVER,
			Color.COVERING_BLONDE,
			Color.COVERING_GINGER,
			Color.COVERING_BROWN,
			Color.COVERING_TAN,
			Color.COVERING_BROWN_DARK,
			Color.COVERING_GREY,
			Color.COVERING_BLACK);

	public static List<Color> allCoveringColors = Util.newArrayListOfValues(
			Color.COVERING_PLATINUM,
			Color.COVERING_GOLD,
			Color.COVERING_SILVER,
			Color.COVERING_COPPER,
			Color.COVERING_STEEL,
			Color.COVERING_ROSE_GOLD,
			
			Color.COVERING_WHITE,
			Color.COVERING_GREY,
			Color.COVERING_BLACK,
			Color.COVERING_RED_LIGHT,
			Color.COVERING_RED,
			Color.COVERING_RED_DARK,
			Color.COVERING_BROWN_DARK,
			Color.COVERING_BROWN,
			Color.COVERING_TAN,
			Color.COVERING_ORANGE,
			Color.COVERING_GINGER,
			Color.COVERING_BLEACH_BLONDE,
			Color.COVERING_BLONDE,
			Color.COVERING_YELLOW,
			Color.COVERING_AMBER,
			Color.COVERING_GREEN,
			Color.COVERING_GREEN_DARK,
			Color.COVERING_BLUE_LIGHT,
			Color.COVERING_BLUE,
			Color.COVERING_BLUE_DARK,
			Color.COVERING_PERIWINKLE,
			Color.COVERING_LILAC,
			Color.COVERING_PURPLE_LIGHT,
			Color.COVERING_PURPLE,
			Color.COVERING_PURPLE_DARK,
			Color.COVERING_PINK,
			Color.COVERING_PINK_LIGHT,
			Color.COVERING_RAINBOW
			);
	
	public static List<Color> allMakeupColors = Util.mergeLists(Util.newArrayListOfValues(Color.COVERING_CLEAR), allCoveringColors);

	public static List<Color> naturalScaleColors = Util.newArrayListOfValues(
			Color.COVERING_WHITE,
			Color.COVERING_BROWN,
			Color.COVERING_TAN,
			Color.COVERING_BROWN_DARK,
			Color.COVERING_BLACK);

	public static List<Color> hornColors = Util.newArrayListOfValues(
			Color.HORN_WHITE,
			Color.HORN_GREY,
			Color.HORN_DARK_GREY,
			Color.HORN_BLACK);
	
	public static List<Color> dyeHornColors = Util.newArrayListOfValues(
			Color.HORN_RED,
			Color.HORN_SCARLET,
			Color.HORN_BROWN,
			Color.HORN_DARK_BROWN,
			Color.HORN_AMBER,
			Color.HORN_PINK,
			Color.HORN_GREEN,
			Color.HORN_BLUE,
			Color.HORN_LILAC,
			Color.HORN_PURPLE,
			Color.COVERING_RAINBOW);

	// Antlers:
	public static List<Color> antlerColors = Util.newArrayListOfValues(
			Color.ANTLER_WHITE,
			Color.ANTLER_TAN,
			Color.ANTLER_BROWN,
			Color.ANTLER_DARK_BROWN,
			Color.ANTLER_GREY,
			Color.ANTLER_DARK_GREY,
			Color.ANTLER_BLACK);
	

	public static List<Color> dyeAntlerColors = Util.newArrayListOfValues(
			Color.ANTLER_RED,
			Color.ANTLER_SCARLET,
			Color.ANTLER_AMBER,
			Color.ANTLER_PINK,
			Color.ANTLER_GREEN,
			Color.ANTLER_BLUE,
			Color.ANTLER_LILAC,
			Color.ANTLER_PURPLE,
			Color.COVERING_RAINBOW);
	
	// Hair:
	
	public static List<Color> naturalHairColors = Util.newArrayListOfValues(
			Color.COVERING_WHITE,
			Color.COVERING_BLONDE,
			Color.COVERING_DIRTY_BLONDE,
			Color.COVERING_GINGER,
			Color.COVERING_BROWN,
			Color.COVERING_BROWN_DARK,
			Color.COVERING_AUBURN,
			Color.COVERING_GREY,
			Color.COVERING_BLACK);
	
	// Eyes:
	
	public static List<Color> naturalIrisColors = Util.newArrayListOfValues(
			Color.EYE_BROWN,
			Color.EYE_AMBER,
			Color.EYE_HAZEL,
			Color.EYE_BLUE,
			Color.EYE_AQUA,
			Color.EYE_GREEN,
			Color.EYE_GREY);
	
	public static List<Color> dyeIrisColors = Util.newArrayListOfValues(
			Color.EYE_SILVER,
			Color.EYE_YELLOW,
			Color.EYE_GOLD,
			Color.EYE_RED,
			Color.EYE_CRIMSON,
			Color.EYE_ORANGE,
			Color.EYE_PINK,
			Color.EYE_VIOLET,
			Color.EYE_PERIWINKLE,
			Color.EYE_LILAC,
			Color.EYE_PURPLE,
			Color.EYE_BLACK,
			Color.COVERING_RAINBOW);

	public static List<Color> naturalDemonIrisColors = Util.newArrayListOfValues(
			Color.EYE_BROWN,
			Color.EYE_AMBER,
			Color.EYE_HAZEL,
			Color.EYE_BLUE,
			Color.EYE_AQUA,
			Color.EYE_GREEN,
			Color.EYE_GREY,
			Color.EYE_RED,
			Color.EYE_CRIMSON,
			Color.EYE_ORANGE,
			Color.EYE_YELLOW,
			Color.EYE_PINK,
			Color.EYE_VIOLET,
			Color.EYE_PERIWINKLE,
			Color.EYE_LILAC,
			Color.EYE_PURPLE,
			Color.EYE_BLACK);
	
	public static List<Color> dyeDemonIrisColors = Util.newArrayListOfValues(
			Color.EYE_SILVER,
			Color.EYE_GOLD,
			Color.COVERING_RAINBOW);
	
	public static List<Color> naturalPredatorIrisColors = Util.newArrayListOfValues(
			Color.EYE_BROWN,
			Color.EYE_AMBER,
			Color.EYE_YELLOW,
			Color.EYE_BLUE,
			Color.EYE_AQUA,
			Color.EYE_GREEN,
			Color.EYE_GREY);
	
	public static List<Color> dyePredatorIrisColors = Util.newArrayListOfValues(
			Color.EYE_SILVER,
			Color.EYE_GOLD,
			Color.EYE_RED,
			Color.EYE_CRIMSON,
			Color.EYE_ORANGE,
			Color.EYE_PINK,
			Color.EYE_VIOLET,
			Color.EYE_LILAC,
			Color.EYE_PURPLE,
			Color.EYE_BLACK,
			Color.COVERING_RAINBOW);
	
	public static List<Color> naturalPupilColors = Util.newArrayListOfValues(
			Color.EYE_BLACK);
	
	public static List<Color> dyePupilColors = Util.newArrayListOfValues(
			Color.EYE_WHITE,
			Color.EYE_SILVER,
			Color.EYE_BROWN,
			Color.EYE_BLUE,
			Color.EYE_AQUA,
			Color.EYE_GREEN,
			Color.EYE_GREY,
			Color.EYE_YELLOW,
			Color.EYE_GOLD,
			Color.EYE_RED,
			Color.EYE_CRIMSON,
			Color.EYE_ORANGE,
			Color.EYE_AMBER,
			Color.EYE_PINK,
			Color.EYE_VIOLET,
			Color.EYE_PERIWINKLE,
			Color.EYE_LILAC,
			Color.EYE_PURPLE,
			Color.COVERING_RAINBOW);
	
	public static List<Color> naturalScleraColors = Util.newArrayListOfValues(
			Color.EYE_WHITE);
	
	public static List<Color> dyeScleraColors = Util.newArrayListOfValues(
			Color.EYE_BLACK,
			Color.EYE_SILVER,
			Color.EYE_BROWN,
			Color.EYE_BLUE,
			Color.EYE_AQUA,
			Color.EYE_GREEN,
			Color.EYE_GREY,
			Color.EYE_YELLOW,
			Color.EYE_GOLD,
			Color.EYE_RED,
			Color.EYE_CRIMSON,
			Color.EYE_ORANGE,
			Color.EYE_AMBER,
			Color.EYE_PINK,
			Color.EYE_VIOLET,
			Color.EYE_PERIWINKLE,
			Color.EYE_LILAC,
			Color.EYE_PURPLE,
			Color.COVERING_RAINBOW);
	
	private Color color;
	private Color lightColor;
	private boolean metallic;
	private String name;
	private List<String> formattingNames;
	
	private Color(boolean metallic, Color color, Color lightColor, String name) {
		this.metallic = metallic;
		this.color = color;
		this.lightColor = lightColor;
		this.name = name;
	}
	
	private Color(boolean metallic, BaseColor color, String name) {
		this.metallic = metallic;
		this.color = color.getColor();
		this.lightColor = color.getLightColor();
		this.name = name;
	}
	
	// Constructors with formatting names:
	private Color(boolean metallic, Color color, Color lightColor, String name, List<String> formattingNames) {
		this.metallic = metallic;
		this.color = color;
		this.lightColor = lightColor;
		this.name = name;
		this.formattingNames=formattingNames;
	}
	
	private Color(boolean metallic, BaseColor color, String name, List<String> formattingNames) {
		this.metallic = metallic;
		this.color = color.getColor();
		this.lightColor = color.getLightColor();
		this.name = name;
		this.formattingNames=formattingNames;
	}

	/**
	 * Returns a String in the format RRGGBB
	 * 
	 * @return
	 */
	public String toWebHexString() {
		return "#"+getColor().toString().substring(2, 8);
	}

	public Color getColor() {
		if(Main.getProperties()!=null) {
			if(Main.getProperties().hasValue(PropertyValue.lightTheme))
				return lightColor;
			else
				return color;
			
		} else {
			return color;
		}
	}

	public boolean isMetallic() {
		return metallic;
	}

	public boolean isRainbow() {
		return false;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return An array of length 5, with [0] being darkest, [4] being lightest.
	 */
	public String[] getShades() {
		return getShades(5);
	}
	
	public String[] getShades(int shadesCount) {
		return getShades(shadesCount, false, 1);
	}

	public String[] getShadesRgbaFormat(float opacity) {
		return getShades(5, true, opacity);
	}
	
	/**
	 * @param shadesCount Number of shades to calculate.
	 * @return Array of Strings, with each one being in the format:<br/>
	 * {@code rgba(63,107,169, opacity)}
	 */
	public String[] getShadesRgbaFormat(int shadesCount, float opacity) {
		return getShades(shadesCount, true, opacity);
	}
	
	private String[] getShades(int shadesCount, boolean rgba, float opacity) {
		String[] shadesString = new String[shadesCount];
		float luminosity = -0.5f;
		float increment = (Math.abs(luminosity)*2)/(shadesCount-1);
		int red = Integer.parseInt(color.toString().substring(2, 4), 16);
		int gre = Integer.parseInt(color.toString().substring(4, 6), 16);
		int blu = Integer.parseInt(color.toString().substring(6, 8), 16);
		int r, g, b;

		for (int i = 0; i < shadesCount; i++) {
			r = red + (int)(red * (i * increment + luminosity));
			r = Math.max(Math.min(r, 255), 0);

			g = gre + (int)(gre * (i * increment + luminosity));
			g = Math.max(Math.min(g, 255), 0);

			b = blu + (int)(blu * (i * increment + luminosity));
			b = Math.max(Math.min(b, 255), 0);
			
			if(rgba) {
				shadesString[i] = "rgba("+r+","+g+","+b+", "+opacity+")";
			} else {
				shadesString[i] = String.format("#%02X%02X%02X", r, g, b);
			}
		}

		return shadesString;
	}

	public List<String> getFormattingNames() {
		return formattingNames;
	}

}
