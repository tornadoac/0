package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.ColorListPresets;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum BodyCoveringType {

	// Skin shades go light->dark

	HUMAN(BodyCoveringTemplateFactory.createTopSkin(
			Util.newArrayListOfValues(CoveringPattern.NONE, CoveringPattern.FRECKLED),
			Color.humanSkinColors)),
	
	FOX_FUR("a layer of",
			false,
			"fur",
			"fur",
			Util.newArrayListOfValues(
					CoveringModifier.FLUFFY,
					CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MARKED),
			CoveringPattern.allHairCoveringPatterns,
			Color.naturalFurColors,
			Color.allCoveringColors,
			Color.naturalFurColors,
			Color.allCoveringColors),
	
	ANGEL(BodyCoveringTemplateFactory.createTopSkin(
			Util.newArrayListOfValues(CoveringPattern.NONE),
			Color.humanSkinColors)),

	ANGEL_FEATHER("a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Util.newArrayListOfValues(Color.COVERING_WHITE),
			Util.mergeLists(Color.dyeFeatherColors, Color.naturalFeatherColors),
			Util.newArrayListOfValues(Color.COVERING_WHITE),
			Util.mergeLists(Color.dyeFeatherColors, Color.naturalFeatherColors)),
	
	DEMON_COMMON(BodyCoveringTemplateFactory.createTopSkin(
			Util.newArrayListOfValues(CoveringPattern.NONE),
			Color.demonSkinColors)),

	DEMON_FEATHER("a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Util.newArrayListOfValues(Color.COVERING_BLACK),
			Util.mergeLists(Color.dyeFeatherColors, Color.naturalFeatherColors),
			Util.newArrayListOfValues(Color.COVERING_BLACK),
			Util.mergeLists(Color.dyeFeatherColors, Color.naturalFeatherColors)),
	
	BAT_SKIN(BodyCoveringTemplateFactory.createBottomSkin(Color.humanSkinColors)),
	
	BAT_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(CoveringModifier.SHORT),
			Util.newArrayListOfValues(CoveringPattern.NONE))),
	
	CANINE_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(
					CoveringModifier.FLUFFY,
					CoveringModifier.SHORT,
					CoveringModifier.SHAGGY),
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MARKED,
					CoveringPattern.SPOTTED))),
	
	LYCAN_FUR(BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SHAGGY), null)),

	FELINE_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.SHORT,
					CoveringModifier.FLUFFY),
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MOTTLED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MARKED,
					CoveringPattern.STRIPED,
					CoveringPattern.HIGHLIGHTS))),

	SQUIRREL_FUR(BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH), null)),
	
	RAT_SKIN(BodyCoveringTemplateFactory.createBottomSkin(Color.ratSkinColors)),
	
	RAT_FUR(BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH), null)),

	RABBIT_FUR(BodyCoveringTemplateFactory.createFurSkin(Util.newArrayListOfValues(CoveringModifier.SMOOTH), null)),
	
	HORSE_HAIR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(CoveringModifier.SHORT),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MOTTLED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MARKED,
					CoveringPattern.STRIPED),
			CoveringPattern.allHairCoveringPatterns,
			Color.naturalFurColors,
			Color.allCoveringColors,
			Color.naturalFurColors,
			Color.allCoveringColors),
	
	REINDEER_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			Util.newArrayListOfValues(CoveringPattern.NONE))),
	
	BOVINE_FUR(BodyCoveringTemplateFactory.createFurSkin(
			Util.newArrayListOfValues(
					CoveringModifier.SHORT,
					CoveringModifier.SMOOTH),
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MOTTLED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MARKED))),
	
	DILDO("a layer of", // This color is set in GameCharacter's getCovering method, based on the color of the dildo equipped.
			false,
			"silicone",
			"silicone",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			null,
			ColorListPresets.ALL.getPresetColorList(),
			null,
			ColorListPresets.ALL.getPresetColorList(),
			null),
	
	PENIS(BodyCoveringTemplateFactory.createPenisSkin()),

	ANUS(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_ANUS)),
	
	MOUTH(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_MOUTH)),
	
	NIPPLES(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_NIPPLE)),
	
	NIPPLES_CROTCH(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_NIPPLE)),
	
	VAGINA(BodyCoveringTemplateFactory.createOrificeSkin(CoveringPattern.ORIFICE_VAGINA)),
	

	FIRE(BodyCoveringTemplateFactory.createElemental("flames", CoveringModifier.BLAZING, 
					Color.COVERING_ORANGE,
					Color.COVERING_BLUE_LIGHT)),
	
	FIRE_HAIR(BodyCoveringTemplateFactory.createElemental("flames", CoveringModifier.BLAZING, 
			Color.COVERING_ORANGE,
			Color.COVERING_BLUE_LIGHT)),
	
	WATER(BodyCoveringTemplateFactory.createElemental("water", CoveringModifier.SHIMMERING, 
			Color.COVERING_BLUE,
			Color.COVERING_BLUE_LIGHT)),
	
	WATER_HAIR(BodyCoveringTemplateFactory.createElemental("water", CoveringModifier.SHIMMERING, 
			Color.COVERING_BLUE,
			Color.COVERING_BLUE_LIGHT)),

	ICE(BodyCoveringTemplateFactory.createElemental("ice", CoveringModifier.SHIMMERING, Color.COVERING_BLUE_LIGHT)),
	
	ICE_HAIR(BodyCoveringTemplateFactory.createElemental("ice", CoveringModifier.SHIMMERING, Color.COVERING_BLUE_LIGHT)),

	AIR(BodyCoveringTemplateFactory.createElemental("vapours", CoveringModifier.SWIRLING, Color.COVERING_BLUE_LIGHT)),
	
	AIR_HAIR(BodyCoveringTemplateFactory.createElemental("vapours", CoveringModifier.SWIRLING, Color.COVERING_BLUE_LIGHT)),

	STONE(BodyCoveringTemplateFactory.createElemental("stone", CoveringModifier.MATTE, Color.COVERING_GREY)),
	
	STONE_HAIR(BodyCoveringTemplateFactory.createElemental("stone", CoveringModifier.MATTE, Color.COVERING_GREY)),

	RUBBER(BodyCoveringTemplateFactory.createElemental("rubber", CoveringModifier.GLOSSY, Color.COVERING_BLACK)),
	
	RUBBER_HAIR(BodyCoveringTemplateFactory.createElemental("rubber", CoveringModifier.GLOSSY, Color.COVERING_BLACK)),

	ARCANE(BodyCoveringTemplateFactory.createElemental("energy", CoveringModifier.SWIRLING, Color.COVERING_PINK)),
	
	ARCANE_HAIR(BodyCoveringTemplateFactory.createElemental("energy", CoveringModifier.SWIRLING, Color.COVERING_PINK)),
	
	SLIME(BodyCoveringTemplateFactory.createSlime(CoveringPattern.NONE, CoveringPattern.allStandardCoveringPatterns)),

	SLIME_EYE(BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_IRISES,
			Util.newArrayListOfValues(CoveringPattern.EYE_IRISES_HETEROCHROMATIC))),
	
	SLIME_PUPILS(BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_PUPILS,
			Util.newArrayListOfValues(CoveringPattern.EYE_PUPILS_HETEROCHROMATIC))),
	
	SLIME_SCLERA(BodyCoveringTemplateFactory.createSlime(CoveringPattern.EYE_SCLERA,
			Util.newArrayListOfValues(CoveringPattern.EYE_SCLERA_HETEROCHROMATIC))),
	
	SLIME_HAIR(BodyCoveringTemplateFactory.createSlime(CoveringPattern.NONE, CoveringPattern.allHairCoveringPatterns)),
	
	SLIME_ANUS(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_ANUS, null)),
	
	SLIME_MOUTH(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_MOUTH, null)),
	
	SLIME_NIPPLES(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_NIPPLE, null)),
	
	SLIME_NIPPLES_CROTCH(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_NIPPLE, null)),
	
	SLIME_VAGINA(BodyCoveringTemplateFactory.createSlime(CoveringPattern.ORIFICE_VAGINA, null)),
	
	SLIME_PENIS(BodyCoveringTemplateFactory.createSlime(CoveringPattern.NONE, CoveringPattern.allStandardCoveringPatterns)),

	FEATHERS("a layer of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.MOTTLED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MARKED,
					CoveringPattern.STRIPED),
			CoveringPattern.allHairCoveringPatterns,
			Color.naturalFeatherColors,
			Color.dyeFeatherColors,
			Color.naturalFeatherColors,
			Color.dyeFeatherColors),

	ALLIGATOR_SCALES("a layer of",
			true,
			"scales",
			"scale",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			CoveringPattern.allScalesCoveringPatterns,
			Color.naturalScaleColors,
			Color.allCoveringColors,
			Color.naturalScaleColors,
			Color.allCoveringColors),

	// MISC:
	
	HORN("a layer of",
			false,
			"keratin",
			"keratin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			Color.hornColors,
			Color.dyeHornColors,
			Color.hornColors,
			Color.dyeHornColors),

	ANTLER_REINDEER("a layer of",
			false,
			"velvet",
			"velvet",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allScalesCoveringPatterns,
			Color.antlerColors,
			Color.dyeAntlerColors,
			Color.antlerColors,
			Color.dyeAntlerColors),

	TONGUE("a layer of",
			false,
			"skin",
			"skin",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE,
					CoveringPattern.HIGHLIGHTS,
					CoveringPattern.STRIPED,
					CoveringPattern.SPOTTED,
					CoveringPattern.MOTTLED,
					CoveringPattern.MARKED),
			Util.newArrayListOfValues(Color.ORIFICE_INTERIOR),
			Color.allSkinColors,
			Util.newArrayListOfValues(Color.ORIFICE_INTERIOR),
			Color.allSkinColors),

	// HAIR:

	HAIR_HUMAN(BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SMOOTH)),
	
	HAIR_ANGEL(BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SILKEN)),
	
	HAIR_FOX_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.NONE),
			CoveringPattern.allHairCoveringPatterns,
			Color.naturalHairColors,
			Color.allCoveringColors,
			Color.naturalHairColors,
			Color.allCoveringColors),

	HAIR_DEMON(BodyCoveringTemplateFactory.createHeadHair(CoveringModifier.SILKEN)),

	HAIR_CANINE_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_LYCAN_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_FELINE_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_HORSE_HAIR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)),

	HAIR_REINDEER_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)),

	HAIR_BOVINE_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)),

	HAIR_SQUIRREL_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_RAT_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),

	HAIR_RABBIT_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),
	
	HAIR_BAT_FUR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.FURRY)),
	
	HAIR_HARPY("a plume of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			CoveringPattern.allHairCoveringPatterns,
			null,
			Color.naturalFeatherColors,
			Color.dyeFeatherColors,
			Color.naturalFeatherColors,
			Color.dyeFeatherColors),
	
	HAIR_SCALES_ALLIGATOR(BodyCoveringTemplateFactory.createFurHeadHair(CoveringModifier.COARSE)), //Why do alligators have hair?!
	
	// BODY HAIR:
	
	BODY_HAIR_HUMAN(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)),

	BODY_HAIR_ANGEL(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.SILKEN)),

	BODY_HAIR_DEMON(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.SILKEN)),

	BODY_HAIR_CANINE_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_LYCAN_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_FOX_FUR("a layer of",
			false,
			"hair",
			"hair",
			Util.newArrayListOfValues(
					CoveringModifier.FURRY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Color.naturalHairColors,
			Color.allCoveringColors,
			Color.naturalHairColors,
			Color.allCoveringColors),

	BODY_HAIR_FELINE_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_HORSE_HAIR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)),

	BODY_HAIR_REINDEER_HAIR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)),

	BODY_HAIR_BOVINE_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.COARSE)),

	BODY_HAIR_SQUIRREL_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_RAT_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),

	BODY_HAIR_RABBIT_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),
	
	BODY_HAIR_BAT_FUR(BodyCoveringTemplateFactory.createBodyHair(CoveringModifier.FURRY)),
	
	BODY_HAIR_HARPY("a plume of",
			true,
			"feathers",
			"feather",
			Util.newArrayListOfValues(CoveringModifier.FLUFFY),
			null,
			null,
			CoveringPattern.allHairCoveringPatterns,
			Color.naturalFeatherColors,
			Color.dyeFeatherColors,
			Color.naturalFeatherColors,
			Color.dyeFeatherColors),

	BODY_HAIR_SCALES_ALLIGATOR("a crest of",
			false,
			"scales",
			"scale",
			Util.newArrayListOfValues(CoveringModifier.SMOOTH),
			null,
			null,
			CoveringPattern.allStandardCoveringPatterns,
			Color.naturalScaleColors,
			Color.allCoveringColors,
			Color.naturalScaleColors,
			Color.allCoveringColors),

	// EYES:
	
	EYE_HUMAN(BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()),

	EYE_ANGEL(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_DEMON_COMMON(BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			Color.naturalDemonIrisColors, Color.dyeDemonIrisColors, true)),

	EYE_DOG_MORPH(BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()),

	EYE_LYCAN(BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			Color.naturalPredatorIrisColors, Color.dyePredatorIrisColors, true)),

	EYE_FOX_MORPH("a pair of",
			true,
			"eyes",
			"eye",
			Util.newArrayListOfValues(
					CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES),
			Util.newArrayListOfValues(
					CoveringPattern.EYE_IRISES_HETEROCHROMATIC),
			Color.naturalPredatorIrisColors,
			Color.dyePredatorIrisColors,
			Color.naturalPredatorIrisColors,
			Color.dyePredatorIrisColors),

	EYE_FELINE(BodyCoveringTemplateFactory.createEyeIrisesWithCustomColors(
			Color.naturalPredatorIrisColors, Color.dyePredatorIrisColors, true)),

	EYE_SQUIRREL(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_RAT(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_RABBIT(BodyCoveringTemplateFactory.createEyeIrises()),
	
	EYE_BAT(BodyCoveringTemplateFactory.createEyeIrises()),
	
	EYE_ALLIGATOR_MORPH(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_HORSE_MORPH(BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()),

	EYE_REINDEER_MORPH(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_COW_MORPH(BodyCoveringTemplateFactory.createEyeIrisesHeterochromiaNaturallyOccurring()),

	EYE_HARPY(BodyCoveringTemplateFactory.createEyeIrises()),

	EYE_PUPILS("a pair of",
			true,
			"pupils",
			"pupil",
			Util.newArrayListOfValues(CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(CoveringPattern.EYE_PUPILS),
			Util.newArrayListOfValues(CoveringPattern.EYE_PUPILS_HETEROCHROMATIC),
			Color.naturalPupilColors,
			Color.dyePupilColors,
			Color.naturalPupilColors,
			Color.dyePupilColors),

	EYE_SCLERA("a pair of",
			true,
			"sclerae",
			"sclera",
			Util.newArrayListOfValues(CoveringModifier.EYE),
			null,
			Util.newArrayListOfValues(CoveringPattern.EYE_SCLERA),
			Util.newArrayListOfValues(CoveringPattern.EYE_SCLERA_HETEROCHROMATIC),
			Color.naturalScleraColors,
			Color.dyeScleraColors,
			Color.naturalScleraColors,
			Color.dyeScleraColors),
	
	// Fluids:
	
	CUM("",
			false,
			"cum",
			"cum",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newArrayListOfValues(CoveringPattern.FLUID),
			null,
			Util.newArrayListOfValues(Color.COVERING_WHITE),
			Util.newArrayListOfValues(
					Color.COVERING_CLEAR,
					Color.COVERING_BROWN,
					Color.COVERING_BLACK,
					Color.COVERING_RED,
					Color.COVERING_BLUE,
					Color.COVERING_PURPLE,
					Color.COVERING_GREEN),
			null,
			null),
	
	GIRL_CUM("",
			false,
			"girlcum",
			"girlcum",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newArrayListOfValues(CoveringPattern.FLUID),
			null,
			Util.newArrayListOfValues(Color.COVERING_CLEAR),
			Util.newArrayListOfValues(
					Color.COVERING_WHITE,
					Color.COVERING_BROWN,
					Color.COVERING_BLACK,
					Color.COVERING_RED,
					Color.COVERING_BLUE,
					Color.COVERING_PURPLE,
					Color.COVERING_GREEN),
			null,
			null),
	
	MILK("",
			false,
			"milk",
			"milk",
			Util.newArrayListOfValues(CoveringModifier.FLUID),
			null,
			Util.newArrayListOfValues(CoveringPattern.FLUID),
			null,
			Util.newArrayListOfValues(Color.COVERING_WHITE),
			Util.newArrayListOfValues(
					Color.COVERING_CLEAR,
					Color.COVERING_BROWN,
					Color.COVERING_BLACK,
					Color.COVERING_RED,
					Color.COVERING_BLUE,
					Color.COVERING_PURPLE,
					Color.COVERING_GREEN),
			null,
			null),
	
	// Makeup:
	
	MAKEUP_BLUSHER("a layer of",
			false,
			"blusher",
			"blusher",
			Util.newArrayListOfValues(CoveringModifier.MAKEUP),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(Color.COVERING_NONE),
			Color.allMakeupColors,
			null,
			null),
	
	MAKEUP_EYE_LINER("a layer of",
			false,
			"eye liner",
			"eye liner",
			Util.newArrayListOfValues(CoveringModifier.MAKEUP),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(Color.COVERING_NONE),
			Color.allMakeupColors,
			null,
			null),
	
	MAKEUP_EYE_SHADOW("a layer of",
			false,
			"eye shadow",
			"eye shadow",
			Util.newArrayListOfValues(
					CoveringModifier.MATTE,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			null,
			Util.newArrayListOfValues(Color.COVERING_NONE),
			Color.allMakeupColors,
			null,
			null),

	MAKEUP_LIPSTICK("a layer of",
			false,
			"lipstick",
			"lipstick",
			Util.newArrayListOfValues(
					CoveringModifier.GLOSSY,
					CoveringModifier.MATTE,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			Util.newArrayListOfValues(
					CoveringPattern.SPOTTED,
					CoveringPattern.STRIPED),
			Util.newArrayListOfValues(Color.COVERING_NONE),
			Color.allMakeupColors,
			null,
			Color.allMakeupColors),
	
	MAKEUP_NAIL_POLISH_HANDS("a layer of",
			false,
			"nail polish",
			"nail polish",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			Util.newArrayListOfValues(
					CoveringPattern.SPOTTED,
					CoveringPattern.STRIPED),
			Util.newArrayListOfValues(Color.COVERING_NONE),
			Color.allMakeupColors,
			null,
			Color.allMakeupColors),
	
	MAKEUP_NAIL_POLISH_FEET("a layer of",
			false,
			"nail polish",
			"nail polish",
			Util.newArrayListOfValues(
					CoveringModifier.SMOOTH,
					CoveringModifier.SPARKLY,
					CoveringModifier.METALLIC),
			null,
			Util.newArrayListOfValues(CoveringPattern.NONE),
			Util.newArrayListOfValues(
					CoveringPattern.SPOTTED,
					CoveringPattern.STRIPED),
			Util.newArrayListOfValues(Color.COVERING_NONE),
			Color.allMakeupColors,
			null,
			Color.allMakeupColors);
	
	private String determiner; 
	private String namePlural;
	private String nameSingular;
	private List<CoveringModifier> naturalModifiers;
	private List<CoveringModifier> extraModifiers;
	private List<Color> naturalColorsPrimary;
	private List<Color> dyeColorsPrimary;
	private List<Color> naturalColorsSecondary;
	private List<Color> dyeColorsSecondary;
	private List<Color> allColors;
	private List<Color> allPrimaryColors;
	private List<Color> allSecondaryColors;
	private List<CoveringPattern> naturalPatterns;
	private List<CoveringPattern> dyePatterns;
	private List<CoveringPattern> allPatterns;
	private boolean isDefaultPlural;
	
	private BodyCoveringType(BodyCoveringTemplate template) {
		determiner = template.determiner;
		namePlural = template.namePlural;
		nameSingular = template.nameSingular;
		naturalModifiers = template.naturalModifiers;
		extraModifiers = template.extraModifiers;
		naturalColorsPrimary = template.naturalColorsPrimary;
		dyeColorsPrimary = template.dyeColorsPrimary;
		naturalColorsSecondary = template.naturalColorsSecondary;
		dyeColorsSecondary = template.dyeColorsSecondary;
		naturalPatterns = template.naturalPatterns;
		dyePatterns = template.dyePatterns;
		isDefaultPlural = template.isDefaultPlural;
		
		allPatterns = new ArrayList<>();
		allPatterns.addAll(naturalPatterns);
		allPatterns.addAll(dyePatterns);
		
//		allColors = new ArrayList<>();
//		allColors.addAll(naturalColorsPrimary);
//		allColors.addAll(dyeColorsPrimary);
//		allColors.addAll(naturalColorsSecondary);
//		allColors.addAll(dyeColorsSecondary);
//		
//		allPrimaryColors = new ArrayList<>();
//		allPrimaryColors.addAll(naturalColorsPrimary);
//		allPrimaryColors.addAll(dyeColorsPrimary);
//		
//		allSecondaryColors = new ArrayList<>();
//		allSecondaryColors.addAll(naturalColorsSecondary);
//		allSecondaryColors.addAll(dyeColorsSecondary);
		
		allColors = new ArrayList<>();
		allPrimaryColors = new ArrayList<>();
		allSecondaryColors = new ArrayList<>();
		for(Color c : this.naturalColorsPrimary) {
			allColors.add(c);
			allPrimaryColors.add(c);
		}
		for(Color c : this.dyeColorsPrimary) {
			if(!allColors.contains(c)) {
				allColors.add(c);
			}
			if(!allPrimaryColors.contains(c)) {
				allPrimaryColors.add(c);
			}
		}
		for(Color c : this.naturalColorsSecondary) {
			allColors.add(c);
			allSecondaryColors.add(c);
		}
		for(Color c : this.dyeColorsSecondary) {
			if(!allColors.contains(c)) {
				allColors.add(c);
			}
			if(!allSecondaryColors.contains(c)) {
				allSecondaryColors.add(c);
			}
		}
	}

	private BodyCoveringType(
			String determiner,
			boolean isDefaultPlural,
			String namePlural,
			String nameSingular,
			List<CoveringModifier> naturalModifiers,
			List<CoveringModifier> extraModifiers,
			List<CoveringPattern> naturalPatterns,
			List<CoveringPattern> dyePatterns,
			List<Color> naturalColorsPrimary,
			List<Color> dyeColorsPrimary,
			List<Color> naturalColorsSecondary,
			List<Color> dyeColorsSecondary) {
		
		this.determiner = determiner;
		this.namePlural = namePlural;
		this.nameSingular=nameSingular;
		this.isDefaultPlural = isDefaultPlural;
		
		if(naturalModifiers == null) {
			this.naturalModifiers = new ArrayList<>();
		} else {
			this.naturalModifiers = naturalModifiers;
		}
		
		if(extraModifiers == null) {
			this.extraModifiers = new ArrayList<>();
		} else {
			this.extraModifiers = extraModifiers;
		}
		
		if(naturalPatterns == null) {
			this.naturalPatterns = new ArrayList<>();
			this.naturalPatterns.add(CoveringPattern.NONE);
		} else {
			this.naturalPatterns = naturalPatterns;
		}

		this.dyePatterns = new ArrayList<>();
		if(dyePatterns != null) {
			this.dyePatterns.addAll(dyePatterns);
			this.dyePatterns.removeAll(this.naturalPatterns);
		}
		
		allPatterns = new ArrayList<>();
		if(naturalPatterns == null) {
			allPatterns.add(CoveringPattern.NONE);
		} else {
			allPatterns.addAll(this.naturalPatterns);
		}
		
		if(dyePatterns != null) {
			allPatterns.addAll(this.dyePatterns);
		}
		
		if(naturalColorsPrimary == null) {
			this.naturalColorsPrimary = new ArrayList<>();
		} else {
			this.naturalColorsPrimary = naturalColorsPrimary;
		}
		if(dyeColorsPrimary == null) {
			this.dyeColorsPrimary = new ArrayList<>();
		} else {
			this.dyeColorsPrimary = dyeColorsPrimary;
		}
		
		if(naturalColorsSecondary == null) {
			this.naturalColorsSecondary = new ArrayList<>();
		} else {
			this.naturalColorsSecondary = naturalColorsSecondary;
		}
		if(dyeColorsSecondary == null) {
			this.dyeColorsSecondary = new ArrayList<>();
		} else {
			this.dyeColorsSecondary = dyeColorsSecondary;
		}
		
		allColors = new ArrayList<>();
		allPrimaryColors = new ArrayList<>();
		allSecondaryColors = new ArrayList<>();
		for(Color c : this.naturalColorsPrimary) {
			allColors.add(c);
			allPrimaryColors.add(c);
		}
		for(Color c : this.dyeColorsPrimary) {
			if(!allColors.contains(c)) {
				allColors.add(c);
			}
			if(!allPrimaryColors.contains(c)) {
				allPrimaryColors.add(c);
			}
		}
		for(Color c : this.naturalColorsSecondary) {
			allColors.add(c);
			allSecondaryColors.add(c);
		}
		for(Color c : this.dyeColorsSecondary) {
			if(!allColors.contains(c)) {
				allColors.add(c);
			}
			if(!allSecondaryColors.contains(c)) {
				allSecondaryColors.add(c);
			}
		}
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static BodyCoveringType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
			
		} else if(value.equals("HAIR_IMP")) {
			value = "HAIR_DEMON";
			
		} else if(value.equals("BODY_HAIR_IMP")) {
			value = "BODY_HAIR_DEMON";
			
		} else if(value.equals("EYE_IMP")) {
			value = "EYE_DEMON_COMMON";
		}
		return valueOf(value);
	}
	
	public String getDeterminer(GameCharacter gc) {
		return determiner;
	}

	public boolean isDefaultPlural() {
		return isDefaultPlural;
	}
	
	public String getNameSingular(GameCharacter gc) {
		return nameSingular;
	}
	
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
	}
	
	public String getName(GameCharacter gc){
		if(isDefaultPlural()) {
			return getNamePlural(gc);
		} else {
			return getNameSingular(gc);
		}
	}
	
	public List<CoveringPattern> getNaturalPatterns() {
		return naturalPatterns;
	}

	public List<CoveringPattern> getDyePatterns() {
		return dyePatterns;
	}

	public List<CoveringPattern> getAllPatterns() {
		return allPatterns;
	}
	
	public List<Color> getNaturalColorsPrimary() {
		return naturalColorsPrimary;
	}

	public List<Color> getDyeColorsPrimary() {
		return dyeColorsPrimary;
	}

	public List<Color> getNaturalColorsSecondary() {
		return naturalColorsSecondary;
	}

	public List<Color> getDyeColorsSecondary() {
		return dyeColorsSecondary;
	}

	public List<Color> getAllColors() {
		return allColors;
	}
	
	public List<Color> getAllPrimaryColors() {
		return allPrimaryColors;
	}
	
	public List<Color> getAllSecondaryColors() {
		return allSecondaryColors;
	}

	public BodyCoveringType getBodyCoveringType() {
		return this;
	}

	public List<CoveringModifier> getNaturalModifiers() {
		return naturalModifiers;
	}

	public List<CoveringModifier> getExtraModifiers() {
		return extraModifiers;
	}
}