package com.lilithsthrone.game.occupantManagement;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.1.87
 * @version 0.3.1
 * @author Innoxia
 */
public enum SlaveJobSetting {
	
	SEX_ORAL(Color.GENERIC_SEX, "Allow Oral", "Oral", "Allow this slave to perform oral on others."),
	SEX_VAGINAL(Color.GENERIC_SEX, "Allow Vaginal", "Vaginal", "Allow this slave to receive vaginal sex (if they have a vagina)."),
	SEX_ANAL(Color.GENERIC_SEX, "Allow Anal", "Anal", "Allow this slave to receive anal sex."),
	SEX_NIPPLES(Color.GENERIC_SEX, "Allow Nipple Penetration", "Nipples", "Allow this slave to receive penetrative nipple sex (if they have penetrable nipples)."),

	MILKING_NO_PREFERENCE(Color.BASE_GREY, "No Preference", "NP", "Set this slave to work in any available milking room."),
	MILKING_INDUSTRIAL(Color.GENERIC_MINOR_BAD, "Industrial Milking", "IN", "Set this slave to work in a milking room with industrial milkers."),
	MILKING_REGULAR(Color.BASE_YELLOW_LIGHT, "Regular Milking", "RG", "Set this slave to work in a milking room with regular milkers."),
	MILKING_ARTISAN(Color.GENERIC_MINOR_GOOD, "Artisan Milking", "AR", "Set this slave to work in a milking room with artisan milkers."),
	MILKING_MILK_DISABLE(Color.BASE_YELLOW_LIGHT, "Forbid Milk", "DM", "Do not allow this slave's milk to be collected."),
	MILKING_MILK_CROTCH_DISABLE(Color.BASE_YELLOW_LIGHT, "Forbid Udder-milk", "DU", "Do not allow this slave's udders to be milked."),
	MILKING_CUM_DISABLE(Color.BASE_BLUE_LIGHT, "Forbid Cum", "DC", "Do not allow this slave's cum to be collected."),
	MILKING_GIRLCUM_DISABLE(Color.BASE_PINK_LIGHT, "Forbid Girlcum", "DG", "Do not allow this slave's girlcum to be collected."),
	
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE(Color.FEMININE, "Feminine Transformations", "TF (F)", "Allow Lilaya to perform feminine transformations on this slave."),
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE(Color.MASCULINE, "Masculine Transformations", "TF (M)", "Allow Lilaya to perform masculine transformations on this slave.");
	
	private Color color;
	private String name;
	private String tag;
	private String description;
	
	private SlaveJobSetting(Color color, String name, String tag, String description) {
		this.color = color;
		this.name = name;
		this.tag = tag;
		this.description = description;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}
	
	public String getTag() {
		return tag;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDailyEffectsDescription() {
		return null;
	}
	
	public String applyDailyEffects() {
		return null;
	}
	
}
