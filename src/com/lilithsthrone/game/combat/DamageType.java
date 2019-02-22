package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum DamageType {

	PHYSICAL("physical",
			Color.DAMAGE_TYPE_PHYSICAL,
			"forceful",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.EARTH),

	ICE("ice",
			Color.DAMAGE_TYPE_COLD,
			"freezing",
			Attribute.RESISTANCE_ICE,
			Attribute.DAMAGE_ICE,
			SpellSchool.WATER),

	FIRE("fire",
			Color.DAMAGE_TYPE_FIRE,
			"burning",
			Attribute.RESISTANCE_FIRE,
			Attribute.DAMAGE_FIRE,
			SpellSchool.FIRE),

	POISON("poison",
			Color.DAMAGE_TYPE_POISON,
			"poisoned",
			Attribute.RESISTANCE_POISON,
			Attribute.DAMAGE_POISON,
			SpellSchool.AIR),

	LUST("lust",
			Color.DAMAGE_TYPE_LUST,
			"arousing",
			Attribute.RESISTANCE_LUST,
			Attribute.DAMAGE_LUST,
			SpellSchool.ARCANE),

	MISC("generic",
			Color.DAMAGE_TYPE_PHYSICAL,
			"standard",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.ARCANE);

	private String name;
	private Color color;
	private String weaponDescriptor;
	private Attribute resistAttribute;
	private Attribute multiplierAttribute;
	private SpellSchool spellSchool;

	private DamageType(String name, Color color, String weaponDescriptor, Attribute resistAttribute, Attribute multiplierAttribute, SpellSchool spellSchool) {
		this.name = name;
		this.color = color;
		this.weaponDescriptor = weaponDescriptor;
		this.resistAttribute = resistAttribute;
		this.multiplierAttribute = multiplierAttribute;
		this.spellSchool = spellSchool;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public String getWeaponDescriptor() {
		return weaponDescriptor;
	}

	public Attribute getResistAttribute() {
		return resistAttribute;
	}

	public Attribute getMultiplierAttribute() {
		return multiplierAttribute;
	}

	public SpellSchool getSpellSchool() {
		return spellSchool;
	}
}
