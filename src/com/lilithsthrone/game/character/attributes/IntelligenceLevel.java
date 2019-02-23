package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.65
 * @version 0.2.1
 * @author Innoxia
 */
public enum IntelligenceLevel {

	ZERO_AIRHEAD("arcane impotence", 0, 5, Color.INTELLIGENCE_STAGE_ZERO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			if(Main.game.isInNewWorld()) {
				return StatusEffect.INTELLIGENCE_PERK_0;
			} else {
				return StatusEffect.INTELLIGENCE_PERK_0_OLD_WORLD;
			}
		}
	},

	ONE_AVERAGE("arcane potential", 5, 15, Color.INTELLIGENCE_STAGE_ONE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_1;
		}
	},

	TWO_SMART("arcane proficiency", 15, 35, Color.INTELLIGENCE_STAGE_TWO) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_2;
		}
	},

	THREE_BRAINY("arcane prowess", 35, 65, Color.INTELLIGENCE_STAGE_THREE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_3;
		}
	},

	FOUR_GENIUS("arcane mastery", 65, 95, Color.INTELLIGENCE_STAGE_FOUR) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_4;
		}
	},

	FIVE_POLYMATH("arcane brilliance", 95, 100, Color.INTELLIGENCE_STAGE_FIVE) {
		@Override
		public StatusEffect getRelatedStatusEffect() {
			return StatusEffect.INTELLIGENCE_PERK_5;
		}
	};

	private String name;
	private int minimumValue, maximumValue;
	private Color color;

	private IntelligenceLevel(String name, int minimumValue, int maximumValue, Color color) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.color = color;
	}

	public abstract StatusEffect getRelatedStatusEffect();

	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public Color getColor() {
		return color;
	}

	public static IntelligenceLevel getIntelligenceLevelFromValue(float value){
		for(IntelligenceLevel al : IntelligenceLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_POLYMATH;
	}

}
