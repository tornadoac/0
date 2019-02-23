package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.3
 * @author Innoxia
 */
public abstract class AbstractItemType extends AbstractCoreType {

	private String determiner, name, namePlural, description, pathName;
	private boolean plural;
	private Color colorPrimary;
	private Color colorSecondary;
	private Color colorTertiary;
	private int value;
	private Rarity rarity;
	protected String SVGString;
	private TFEssence relatedEssence;
	protected List<ItemEffect> effects;
	protected Set<ItemTag> itemTags;

	public AbstractItemType(
			int value,
			String determiner,
			boolean plural,
			String name,
			String namePlural,
			String description,
			String pathName,
			Color colorPrimary,
			Color colorSecondary,
			Color colorTertiary,
			Rarity rarity,
			TFEssence relatedEssence,
			List<ItemEffect> effects,
			List<ItemTag> itemTags) {

		this.determiner = determiner;
		this.plural = plural;
		this.name = name;
		this.namePlural = namePlural;
		this.description = description;
		this.pathName = pathName;

		this.value = value;
		this.rarity = rarity;

		this.relatedEssence = relatedEssence;

		this.itemTags = new HashSet<>();
		if(itemTags!=null) {
			this.itemTags.addAll(itemTags);
		}

		if(effects==null) {
			this.effects = new ArrayList<>();
		} else {
			this.effects=effects;
		}

		if (colorPrimary == null) {
			this.colorPrimary = Color.CLOTHING_BLACK;
		} else {
			this.colorPrimary = colorPrimary;
		}
		if (colorSecondary == null) {
			this.colorSecondary = Color.CLOTHING_BLACK;
		} else {
			this.colorSecondary = colorSecondary;
		}
		if (colorTertiary == null) {
			this.colorTertiary = Color.CLOTHING_BLACK;
		} else {
			this.colorTertiary = colorTertiary;
		}

		// Set this item's file image:
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! AbstractItemType icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			String s = Util.inputStreamToString(is);

			SVGString = colorReplacement(this.getColorPrimary(), this.getColorSecondary(), this.getColorTertiary(), s);

			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private String colorReplacement(Color color, Color colorSecondary, Color colorTertiary, String inputString) {
		return SvgUtil.colorReplacement(Integer.toString(this.hashCode()), color, colorSecondary, colorTertiary, inputString);
	}

	@Override
	public boolean equals(Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractItemType){
				if(((AbstractItemType)o).getName(false).equals(getName(false))
						&& ((AbstractItemType)o).getPathName().equals(getPathName())
						&& ((AbstractItemType)o).getRarity() == getRarity()
						&& ((AbstractItemType)o).getRelatedEssence() == getRelatedEssence()
						&& ((AbstractItemType)o).getEffects().equals(getEffects())
						){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() { // I know it doesn't include everything, but this should be enough to check for equality.
		int result = super.hashCode();
		result = 31 * result + getName(false).hashCode();
		result = 31 * result + getPathName().hashCode();
		result = 31 * result + getRarity().hashCode();
		if(getRelatedEssence() != null)
			result = 31 * result + getRelatedEssence().hashCode();
		result = 31 * result + getEffects().hashCode();
		return result;
	}

	public static AbstractItem generateItem(AbstractItemType itemType) {
		return new AbstractItem(itemType) {};
	}

	public static AbstractItem generateFilledCondom(Color color, GameCharacter character, FluidCum cum, int milliliters) {
		return new AbstractFilledCondom(ItemType.CONDOM_USED, color, character, cum, milliliters) {};
	}

	public static AbstractItem generateFilledBreastPump(Color color, GameCharacter character, FluidMilk milk, int quantity) {
		return new AbstractFilledBreastPump(ItemType.MOO_MILKER_FULL, color, character, milk, quantity) {};
	}

	public String getId() {
		return ItemType.getItemToIdMap().get(this);
	}

	public List<ItemEffect> getEffects() {
		return effects;
	}

	public boolean isAbleToBeSold() {
		return getRarity()!=Rarity.QUEST;
	}

	public boolean isAbleToBeDropped() {
		return getRarity()!=Rarity.QUEST;
	}

	// Enchantments:

	public int getEnchantmentLimit() {
		return 100;
	}

	public AbstractItemEffectType getEnchantmentEffect() {
		return null;
	}

	public TFEssence getRelatedEssence() {
		return relatedEssence;
	}

	public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
		return null;
	}

	// Getters & setters:

	public String getDeterminer() {
		return (determiner!=null?determiner:"");
	}

	public boolean isPlural() {
		return plural;
	}

	public String getName(boolean displayName) {
		if(displayName) {
			return Util.capitalizeSentence((determiner!=null?determiner:"") + " <span style='color: " + rarity.getColor().toWebHexString() + ";'>" + (this.isPlural()?namePlural:name) + "</span>");
		} else {
			return (this.isPlural()?namePlural:name);
		}
	}

	public String getNamePlural(boolean displayName) {
		if(displayName) {
			return Util.capitalizeSentence((determiner!=null?determiner:"") + " <span style='color: " + rarity.getColor().toWebHexString() + ";'>" + namePlural + "</span>");
		} else {
			return namePlural;
		}
	}

	public String getDescription() {
		return description;
	}

	public String getDisplayName(boolean withRarityColor) {
		return Util.capitalizeSentence((determiner!=null?determiner:"") + (withRarityColor
				? (" <span style='color: " + rarity.getColor().toWebHexString() + ";'>" + (this.isPlural()?getNamePlural(false):getName(false)) + "</span>")
				: (this.isPlural()?getNamePlural(false):getName(false))));
	}

	public String getPathName() {
		return pathName;
	}

	public Color getColorPrimary() {
		return colorPrimary;
	}

	public Color getColorSecondary() {
		return colorSecondary;
	}

	public Color getColorTertiary() {
		return colorTertiary;
	}

	public int getValue() {
		return value;
	}

	public String getSVGString() {
		return SVGString;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public String getUseName() {
		return "use";
	}

	public String getUseDescription(GameCharacter user, GameCharacter target) {
		return "<p>"
					+ "You use the item."
				+ "</p>";
	}

	public boolean isAbleToBeUsedFromInventory() {
		return true;
	}

	public boolean isAbleToBeUsed(GameCharacter target) {
		return !Main.game.isInCombat() || target.isPlayer();
	}

	public boolean isAbleToBeUsedInSex() {
		return true;
	}

	public boolean isAbleToBeUsedInCombat() {
		return true;
	}

	public boolean isConsumedOnUse() {
		return true;
	}

	public boolean isTransformative() {
		return false;
	}

	public boolean isGift() {
		return false;
	}

	public boolean isFetishGiving() {
		return false;
	}

	public String getUnableToBeUsedFromInventoryDescription() {
		return "This item cannot be used in this way!";
	}

	public String getUnableToBeUsedDescription(GameCharacter target) {
		return "This item cannot be used in this way!";
	}

	public String getDyeBrushEffects(AbstractClothing clothing, Color color) {
		return "<p>"
					+ "As you take hold of the Dye-brush, you see the purple glow around the tip growing in strength."
					+ " The closer you move it to your " + clothing.getName() + ", the brighter the glow becomes, until suddenly, images of different colors start flashing through your mind."
					+ " As you touch the bristles to the " + clothing.getName() + "'s surface, the Dye-brush instantly evaporates!"
					+ " You see that the arcane enchantment has dyed the " + clothing.getName() + " " + color.getName() + "."
				+ "</p>";
	}

	public String getDyeBrushEffects(AbstractWeapon weapon, Color color) {
		return "<p>"
					+ "As you take hold of the Dye-brush, you see the purple glow around the tip growing in strength."
					+ " The closer you move it to your " + weapon.getName() + ", the brighter the glow becomes, until suddenly, images of different colors start flashing through your mind."
					+ " As you touch the bristles to the " + weapon.getName() + "'s surface, the Dye-brush instantly evaporates!"
					+ " You see that the arcane enchantment has dyed the " + weapon.getName() + " " + color.getName() + "."
				+ "</p>";
	}

	public Set<ItemTag> getItemTags() {
		return itemTags;
	}
}
