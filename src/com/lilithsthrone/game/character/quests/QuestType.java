package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.1
 * @version 0.1.69
 * @author Innoxia
 */
public enum QuestType {
	MAIN(Color.QUEST_MAIN),
	SIDE(Color.QUEST_SIDE),
	RELATIONSHIP(Color.QUEST_RELATIONSHIP);

	private Color color;

	private QuestType(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
