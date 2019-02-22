package com.lilithsthrone.game.character.effects;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.53
 * @version 0.2.11
 * @author Innoxia
 */
public enum PerkCategory {
	
	JOB(Color.BASE_BROWN),
	
	PHYSICAL(Color.ATTRIBUTE_PHYSIQUE),
	
	BOTH(Color.ATTRIBUTE_CORRUPTION),
	
	ARCANE(Color.ATTRIBUTE_ARCANE),
	
	// Just for coloring:
	PHYSICAL_EARTH(Color.SPELL_SCHOOL_EARTH),
	PHYSICAL_WATER(Color.SPELL_SCHOOL_WATER),
	ARCANE_FIRE(Color.SPELL_SCHOOL_FIRE),
	ARCANE_AIR(Color.SPELL_SCHOOL_AIR);

	private Color color;

	private PerkCategory(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
