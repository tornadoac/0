package com.lilithsthrone.game.character.body.types;

import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Pimgd
 */
public class BodyCoveringTemplateFactory {

	public static BodyCoveringTemplate createSkin(List<CoveringPattern> coverPatterns, List<Color> naturalPrimaryColors, List<Color> naturalSecondaryColors, List<Color> dyeColors) {
		return new BodyCoveringTemplate("a layer of",
				false,
				"skin",
				"skin",
				Util.newArrayListOfValues(CoveringModifier.SMOOTH),
				null,
				coverPatterns,
				CoveringPattern.allStandardCoveringPatterns,
				naturalPrimaryColors,
				dyeColors,
				naturalSecondaryColors,
				dyeColors);
	}

	public static BodyCoveringTemplate createTopSkin(List<CoveringPattern> coverPatterns, List<Color> skinColors) {
		return createSkin(coverPatterns, skinColors, skinColors, null);
	}

	public static BodyCoveringTemplate createBottomSkin(List<Color> skinColors) {
		return createSkin(Util.newArrayListOfValues(CoveringPattern.NONE), skinColors, null, Color.allSkinColors);
	}

	public static BodyCoveringTemplate createSlime(CoveringPattern basePattern, List<CoveringPattern> coverPatterns) {
		return new BodyCoveringTemplate("a layer of",
				false,
				"slime",
				"slime",
				Util.newArrayListOfValues(CoveringModifier.GOOEY),
				null,
				Util.newArrayListOfValues(basePattern),
				coverPatterns,
				Color.naturalSlimeColors,
				Color.dyeSlimeColors,
				Color.naturalSlimeColors,
				Color.dyeSlimeColors);
	}

	public static BodyCoveringTemplate createFurSkin(List<CoveringModifier> modifiers, List<CoveringPattern> patterns) {
		return createFur("a layer of", "fur", modifiers, patterns);
	}

	private static BodyCoveringTemplate createFur(String determiner, String name, List<CoveringModifier> modifiers, List<CoveringPattern> patterns) {
		return new BodyCoveringTemplate(determiner,
				false,
				name,
				name,
				modifiers,
				null,
				patterns,
				CoveringPattern.allStandardCoveringPatterns,
				Color.naturalFurColors,
				Color.allCoveringColors,
				Color.naturalFurColors,
				Color.allCoveringColors);
	}

	private static BodyCoveringTemplate createHair(String determiner, String name, List<CoveringModifier> modifiers, List<CoveringPattern> patterns) {
		return new BodyCoveringTemplate(determiner,
				false,
				name,
				name,
				modifiers,
				null,
				patterns,
				CoveringPattern.allHairCoveringPatterns,
				Color.naturalHairColors,
				Color.allCoveringColors,
				Color.naturalHairColors,
				Color.allCoveringColors);
	}

	private static BodyCoveringTemplate createHairWithoutPatterns(String determiner, String name, CoveringModifier modifier) {
		return createHair(determiner, name, Util.newArrayListOfValues(modifier), Util.newArrayListOfValues(CoveringPattern.NONE));
	}

	public static BodyCoveringTemplate createHeadHair(CoveringModifier modifier) {
		return createHairWithoutPatterns("a head of", "hair", modifier);
	}

	public static BodyCoveringTemplate createFurHeadHair(CoveringModifier modifier) {
		return createHairWithoutPatterns("a layer of", "hair", modifier);
	}

	public static BodyCoveringTemplate createBodyHair(CoveringModifier modifier) {
		return createHairWithoutPatterns("a layer of", "hair", modifier);
	}

	public static BodyCoveringTemplate createElemental(String name, CoveringModifier modifier, Color... naturalHairColors) {
		return new BodyCoveringTemplate("",
				false,
				name,
				name,
				Util.newArrayListOfValues(modifier),
				null,
				Util.newArrayListOfValues(CoveringPattern.NONE),
				null,
				Arrays.asList(naturalHairColors),
				null,
				null,
				null);
	}

	public static BodyCoveringTemplate createOrificeSkin(CoveringPattern pattern) {
		return new BodyCoveringTemplate("a layer of",
				false,
				"skin",
				"skin",
				Util.newArrayListOfValues(CoveringModifier.SMOOTH),
				null,
				pattern == null ? null : Util.newArrayListOfValues(pattern),
				null,
				Color.allSkinColors,
				null,
				Util.newArrayListOfValues(Color.ORIFICE_INTERIOR),
				Color.allSkinColors);
	}

	public static BodyCoveringTemplate createPenisSkin() {
		return new BodyCoveringTemplate("a layer of",
				false,
				"skin",
				"skin",
				Util.newArrayListOfValues(CoveringModifier.SMOOTH),
				null,
				Util.newArrayListOfValues(
						CoveringPattern.NONE),
				Util.newArrayListOfValues(
						CoveringPattern.MARKED,
						CoveringPattern.MOTTLED,
						CoveringPattern.SPOTTED,
						CoveringPattern.STRIPED),
				Color.allSkinColors,
				null,
				Util.newArrayListOfValues(Color.ORIFICE_INTERIOR),
				Color.allSkinColors);
	}

	public static BodyCoveringTemplate createEyeIrisesWithCustomColors(List<Color> naturalIrisColors, List<Color> dyeIrisColors, boolean heteroIsExtra) {
		List<CoveringPattern> natural = Util.newArrayListOfValues(CoveringPattern.EYE_IRISES, CoveringPattern.EYE_IRISES_HETEROCHROMATIC);
		List<CoveringPattern> extra = null;
		if (heteroIsExtra) {
			natural = Util.newArrayListOfValues(CoveringPattern.EYE_IRISES);
			extra = Util.newArrayListOfValues(CoveringPattern.EYE_IRISES_HETEROCHROMATIC);
		}
		return new BodyCoveringTemplate("a pair of",
				true,
				"eyes",
				"eye",
				Util.newArrayListOfValues(CoveringModifier.EYE),
				null,
				natural,
				extra,
				naturalIrisColors,
				dyeIrisColors,
				naturalIrisColors,
				dyeIrisColors);
	}

	public static BodyCoveringTemplate createEyeIrisesWithCustomColors(List<Color> naturalIrisColors, List<Color> dyeIrisColors) {
		return createEyeIrisesWithCustomColors(naturalIrisColors, dyeIrisColors, true);
	}

	public static BodyCoveringTemplate createEyeIrises() {
		return createEyeIrisesWithCustomColors(Color.naturalIrisColors, Color.dyeIrisColors, true);
	}

	public static BodyCoveringTemplate createEyeIrisesHeterochromiaNaturallyOccurring() {
		return createEyeIrisesWithCustomColors(Color.naturalIrisColors, Color.dyeIrisColors, false);
	}
}
