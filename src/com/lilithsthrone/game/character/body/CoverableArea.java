package com.lilithsthrone.game.character.body;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public enum CoverableArea {

	NONE("none"), // Utility value

	HANDS("hands"),

	ASS("ass"),
	ANUS("asshole"),

	STOMACH("stomach"),
	LEGS("legs"),
	FEET("feet"),
	BACK("back"),
	THIGHS("thighs"),

	VAGINA("pussy"),
	MOUND("mound"),
	PENIS("cock"),
	TESTICLES("balls"),

	BREASTS("breasts"),
	NIPPLES("nipples"),

	BREASTS_CROTCH("crotch-breasts"),
	NIPPLES_CROTCH("crotch-nipples"),

	HAIR("hair"),
	MOUTH("mouth");

	private String name;

	private CoverableArea(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
