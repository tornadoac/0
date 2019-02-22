package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;

import com.lilithsthrone.utils.BaseColor;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum FluidFlavor {
	
	CUM("cum", BaseColor.WHITE,
			Util.newArrayListOfValues(
					"salty")),
	
	MILK("milk", BaseColor.WHITE,
			Util.newArrayListOfValues(
					"creamy")),
	
	GIRL_CUM("girl-cum", BaseColor.WHITE,
			Util.newArrayListOfValues(
					"sweet")),

	SLIME("slime", BaseColor.GREEN_LIGHT,
			Util.newArrayListOfValues(
					"sweet")),
	
	BEER("beer", BaseColor.TAN,
			Util.newArrayListOfValues(
					"yeasty",
					"beer-flavored")),
	
	VANILLA("vanilla", BaseColor.YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"sweet",
					"vanilla-flavored")),
	
	STRAWBERRY("strawberries", BaseColor.ROSE,
			Util.newArrayListOfValues(
					"sweet",
					"strawberry-flavored")),
	
	CHOCOLATE("chocolate", BaseColor.BROWN,
			Util.newArrayListOfValues(
					"chocolatey",
					"chocolate-flavored")),
	
	PINEAPPLE("pineapple", BaseColor.YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"tart",
					"sour",
					"tangy",
					"pineapple-flavored")),
	
	HONEY("honey", BaseColor.YELLOW,
			Util.newArrayListOfValues(
					"sweet",
					"honey-flavored")),
	
	MINT("mint", BaseColor.GREEN_LIME,
			Util.newArrayListOfValues(
					"minty"));
	
	private String name;
	private BaseColor color;
	private List<String> flavorDescriptors;

	private FluidFlavor(String name, BaseColor color, List<String> flavorDescriptors) {
		this.name = name;
		this.color=color;
		this.flavorDescriptors = flavorDescriptors;
	}
	
	/**
	 * To go into: "You can't get the rich strawberry taste out of your mouth."<br/>
	 * Or: "Strawberry-flavored"
	 */
	public String getName() {
		return name;
	}
	
	public BaseColor getColor() {
		return color;
	}

	public List<String> getFlavorDescriptors() {
		return flavorDescriptors;
	}
	
	public String getRandomFlavorDescriptor() {
		return flavorDescriptors.get(Util.random.nextInt(flavorDescriptors.size()));
	}
}
