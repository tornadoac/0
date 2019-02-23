package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;

public class BodyCoveringSkinToneColorHelper {
	private static final List<BodyCoveringType> NOT_FOR_THESE_BCTS =
			Util.newArrayListOfValues(BodyCoveringType.MAKEUP_BLUSHER,
					BodyCoveringType.MAKEUP_EYE_LINER,
					BodyCoveringType.MAKEUP_EYE_SHADOW,
					BodyCoveringType.MAKEUP_LIPSTICK,
					BodyCoveringType.MAKEUP_NAIL_POLISH_FEET,
					BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS);

	private static class FilteredColors {
		private List<Color> primary;
		private List<Color> secondary;

		public FilteredColors(List<Color> primary, List<Color> secondary) {
			this.primary = primary;
			this.secondary = secondary;
		}

		public List<Color> getPrimary() {
			return primary;
		}

		public List<Color> getSecondary() {
			return secondary;
		}

	}

	private static Map<StartingSkinTone, Map<BodyCoveringType, FilteredColors>> filteredColors = new EnumMap<>(StartingSkinTone.class);

	private BodyCoveringSkinToneColorHelper() {
		//singleton via statics
	}

	public static List<Color> getAcceptableColorsForPrimary(StartingSkinTone tone, BodyCoveringType bct) {
		return getOrCreateFilteredColorsForCombination(tone, bct).getPrimary();
	}

	public static List<Color> getAcceptableColorsForSecondary(StartingSkinTone tone, BodyCoveringType bct) {
		return getOrCreateFilteredColorsForCombination(tone, bct).getSecondary();
	}

	private static FilteredColors getOrCreateFilteredColorsForCombination(StartingSkinTone tone, BodyCoveringType bct) {
		if (NOT_FOR_THESE_BCTS.contains(bct)) {
			return new FilteredColors(new ArrayList<>(), new ArrayList<>());
		}
		return filteredColors.computeIfAbsent(tone, ignored -> new EnumMap<>(BodyCoveringType.class)).computeIfAbsent(bct, ignored -> {
			Set<Color> colorApplicationListPrimary = new HashSet<>();

			colorApplicationListPrimary.addAll(bct.getNaturalColorsPrimary());
			colorApplicationListPrimary.retainAll(tone.getAssociatedColors());
			if(colorApplicationListPrimary.isEmpty()) {
				colorApplicationListPrimary.addAll(bct.getNaturalColorsPrimary());
			}

			Set<Color> colorApplicationListSecondary = new HashSet<>();

			colorApplicationListSecondary.addAll(bct.getNaturalColorsSecondary());
			colorApplicationListSecondary.retainAll(tone.getAssociatedColors());
			if(colorApplicationListSecondary.isEmpty()) {
				colorApplicationListSecondary.addAll(bct.getNaturalColorsSecondary());
			}
			return new FilteredColors(new ArrayList<>(colorApplicationListPrimary), new ArrayList<>(colorApplicationListSecondary));
		});
	}
}
