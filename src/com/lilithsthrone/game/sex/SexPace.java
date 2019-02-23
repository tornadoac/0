package com.lilithsthrone.game.sex;

import com.lilithsthrone.utils.BaseColor;

/**
 * @since 0.1.69.9
 * @version 0.3.1
 * @author Innoxia
 */
public enum SexPace {

	SUB_RESISTING(false, "resisting", BaseColor.CRIMSON),
	SUB_NORMAL(false, "normal", BaseColor.PINK),
	SUB_EAGER(false, "eager", BaseColor.PINK_DEEP),

	DOM_GENTLE(true, "gentle", BaseColor.PINK_LIGHT),
	DOM_NORMAL(true, "normal", BaseColor.PINK),
	DOM_ROUGH(true, "rough", BaseColor.CRIMSON);

	private boolean isDom;
	private String name;
	private BaseColor color;

	private SexPace(boolean isDom, String name, BaseColor color) {
		this.isDom = isDom;
		this.name = name;
		this.color = color;
	}

	public SexPace getOppositeDomEquivalent() {
		switch(this) {
			case DOM_GENTLE:
				return SUB_NORMAL;
			case DOM_NORMAL:
				return SUB_NORMAL;
			case DOM_ROUGH:
				return SUB_EAGER;
			case SUB_EAGER:
				return DOM_ROUGH;
			case SUB_NORMAL:
				return DOM_NORMAL;
			case SUB_RESISTING:
				return DOM_GENTLE;
		}
		return SUB_NORMAL;
	}

	public boolean isDom() {
		return isDom;
	}

	public String getName() {
		return name;
	}

	public BaseColor getColor() {
		return color;
	}
}
