package com.lilithsthrone.game.inventory;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum Rarity {

	COMMON("common", Color.RARITY_COMMON),
	UNCOMMON("uncommon", Color.RARITY_UNCOMMON),
	RARE("rare", Color.RARITY_RARE),
	EPIC("epic", Color.RARITY_EPIC),
	LEGENDARY("legendary", Color.RARITY_LEGENDARY),
	QUEST("quest", Color.RARITY_QUEST),

	JINXED("jinxed", Color.RARITY_JINXED);

	private String name;
	private Color color;

	private Rarity(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}
}
