package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.75
 * @version 0.2.6
 * @author Innoxia
 */
public class EnchantingUtils {

	public static final int FLAME_COST_MODIFER = 500;

	public static AbstractItem craftItem(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractItem craftedItem = null;

		craftedItem = AbstractItemType.generateItem((AbstractItemType) ingredient.getEnchantmentItemType(effects));

		List<ItemEffect> effectsToBeAdded = new ArrayList<>();
		effectsToBeAdded.addAll(effects);

		craftedItem.setItemEffects(effectsToBeAdded);

		craftedItem.setName(EnchantmentDialogue.getOutputName());
		craftedItem.setColor(ingredient.getEnchantmentEffect().getColor());
		craftedItem.setSVGString(getSVGString(ingredient, effectsToBeAdded));

		return craftedItem;
	}

	public static AbstractClothing craftClothing(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractClothing craftedClothing = null;

		List<ItemEffect> effectsToBeAdded = new ArrayList<>(effects);

		craftedClothing = AbstractClothingType.generateClothing(
				(AbstractClothingType) ingredient.getEnchantmentItemType(effects),
				((AbstractClothing)ingredient).getColor(),
				((AbstractClothing)ingredient).getSecondaryColor(),
				((AbstractClothing)ingredient).getTertiaryColor(),
				effectsToBeAdded);

		craftedClothing.setPattern(((AbstractClothing)ingredient).getPattern());
		craftedClothing.setPatternColor(((AbstractClothing)ingredient).getPatternColor());
		craftedClothing.setPatternSecondaryColor(((AbstractClothing)ingredient).getPatternSecondaryColor());
		craftedClothing.setPatternTertiaryColor(((AbstractClothing)ingredient).getPatternTertiaryColor());

		craftedClothing.setName(EnchantmentDialogue.getOutputName());

		craftedClothing.setEnchantmentKnown(true);

		return craftedClothing;
	}

	public static Tattoo craftTattoo(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		List<ItemEffect> effectsToBeAdded = new ArrayList<>(effects);
		((Tattoo)ingredient).setEffects(effectsToBeAdded);
		((Tattoo)ingredient).setName(EnchantmentDialogue.getOutputName());
		return (Tattoo) ingredient;
	}

	public static AbstractWeapon craftWeapon(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractWeapon craftedWeapon = null;

		List<ItemEffect> effectsToBeAdded = new ArrayList<>();
		effectsToBeAdded.addAll(effects);

		craftedWeapon = AbstractWeaponType.generateWeapon(
				(AbstractWeaponType) ingredient.getEnchantmentItemType(effects),
				((AbstractWeapon) ingredient).getDamageType(),
				((AbstractWeapon)ingredient).getPrimaryColor(),
				((AbstractWeapon)ingredient).getSecondaryColor());

		craftedWeapon.setEffects(effectsToBeAdded);

		craftedWeapon.setName(EnchantmentDialogue.getOutputName());

		return craftedWeapon;
	}

	public static String getPotionName(AbstractCoreItem ingredient, List<ItemEffect> effects) {

		if(ingredient.getEnchantmentItemType(effects) instanceof AbstractClothingType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractTattooType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractWeaponType) {
			return Util.capitalizeSentence(ingredient.getName());
		}

		if(((AbstractItem)ingredient).getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId())) {
			if(effects.isEmpty() || effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return "Hypno-Watch";
			}
			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return "Gynephilic Hypno-Watch";
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return "Ambiphilic Hypno-Watch";
			} else {
				return "Androphilic Hypno-Watch";
			}
		}

		String potionName = ((AbstractItemType) ingredient.getEnchantmentItemType(effects)).getName(false);
		String potionDescriptor = "";
		String potionSuffix = "";
		String potionPreSuffix = ""; // it was either PreSuffix or PrefixSuffix...

		if(ingredient!=null) {
			try {
				potionDescriptor = ingredient.getEffects().get(0).getItemEffectType().getPotionDescriptor();
			} catch(Exception ex) {
				// :3
				// Cat-face comments aren't helpful damn it!
				System.err.println("EnchantingUtils: getPotionName() error 1.");
			}
		}

		String finalPotionName = ((potionDescriptor==null || potionDescriptor.isEmpty())?"":Util.capitalizeSentence(potionDescriptor)+" ") + potionName;

		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != null && ie.getPrimaryModifier() != TFModifier.NONE) {
				potionSuffix = ie.getPrimaryModifier().getDescriptor();

				if(ie.getSecondaryModifier() != TFModifier.NONE) {
					potionPreSuffix = ie.getSecondaryModifier().getDescriptor();
				}

				if(potionSuffix!="") {
					if(potionPreSuffix!="") {
						if(ie.getSecondaryModifier().isSoloDescriptor())
							finalPotionName += " of "+potionPreSuffix;
						else
							finalPotionName += " of "+potionPreSuffix+" "+potionSuffix;
					} else {
						finalPotionName += " of "+potionSuffix;
					}
				}

				break;
			}
		}

		return Util.capitalizeSentence(finalPotionName);
	}

	private static Set<TFModifier> freePrimaryModifiers = Util.newHashSetOfValues(TFModifier.TF_MOD_WETNESS, TFModifier.TF_MILK, TFModifier.TF_MILK_CROTCH, TFModifier.TF_CUM, TFModifier.TF_GIRLCUM);
	private static Set<TFModifier> freeSecondaryModifiers = Util.newHashSetOfValues(TFModifier.TF_MOD_WETNESS, TFModifier.TF_MOD_REGENERATION, TFModifier.TF_MOD_CUM_EXPULSION);

	private static boolean isEffectFreeForWaterSchool(ItemEffect effect) {
		return freePrimaryModifiers.contains(effect.getPrimaryModifier())
				|| freeSecondaryModifiers.contains(effect.getSecondaryModifier());
	}

	private static int applyDiscountsForPerksAndFetishes(AbstractCoreItem ingredient, int cost) {
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && ingredient instanceof AbstractItem) {
			cost/=2;
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.CLOTHING_ENCHANTER) && ingredient instanceof AbstractClothing) {
			cost/=2;
		}
		return cost;
	}

	public static int getModifierEffectCost(AbstractCoreItem ingredient, ItemEffect effect) {
		if(!(ingredient instanceof Tattoo)
				&& Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
				&& isEffectFreeForWaterSchool(effect)) {
			return 0;
		}

		return applyDiscountsForPerksAndFetishes(ingredient, effect.getCost());
	}

	public static int getCost(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		Map<ItemEffect, Integer> effectCount = new HashMap<>();
		for(ItemEffect ie : effects) {
			effectCount.merge(ie, 1, Integer::sum);
		}
		for(ItemEffect ie : ingredient.getEffects()) {
			if(effects.contains(ie)) {
				effectCount.merge(ie, -1, Integer::sum);
			} else {
				effectCount.merge(ie, 1, Integer::sum);
			}
		}

		if (!(ingredient instanceof Tattoo) && Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)) {
			effectCount.keySet().removeIf(EnchantingUtils::isEffectFreeForWaterSchool);
		}

		int cost = 0;
		for(Entry<ItemEffect, Integer> entry : effectCount.entrySet()) {
			int costIncrement = entry.getKey().getCost() * Math.abs(entry.getValue());

			if(entry.getKey().getPrimaryModifier()==TFModifier.CLOTHING_SEALING) {
				switch(entry.getKey().getPotency()) {
					case MAJOR_BOOST:
						costIncrement*=4;
						break;
					case BOOST:
						costIncrement*=2;
						break;
					default:
						break;
				}
			}

			cost += costIncrement;
		}

		return applyDiscountsForPerksAndFetishes(ingredient, cost);
	}

	public static String getSVGString(AbstractCoreItem ingredient, List<ItemEffect> effects) {

		if(ingredient.getEnchantmentItemType(effects) instanceof AbstractClothingType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractTattooType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractWeaponType) {
			return ingredient.getSVGString();
		}

		if(((AbstractItem)ingredient).getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId())) {
			if(effects.isEmpty() || effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchBase();
			}

			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchGynephilic();

			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAmbiphilic();

			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAndrophilic();
			}
		}

		StringBuilder SVGImageSB = new StringBuilder();

		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(ingredient.getEnchantmentEffect().getColor())+"</div>");

		String s = ((AbstractItemType) ingredient.getEnchantmentItemType(effects)).getSVGString();

		Color color = Color.CLOTHING_BLUE_LIGHT;

		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != null && ie.getPrimaryModifier() != TFModifier.NONE) {
				color = ie.getPrimaryModifier().getColor();

				break;
			}
		}

		s = SvgUtil.colorReplacement(((AbstractItem)ingredient).getItemType().getId(), color, null, null, s);

		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+s+"</div>");

		for(ItemEffect ie : effects) {
			if(ie.getSecondaryModifier() != null && ie.getSecondaryModifier() != TFModifier.NONE) {
				SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRefinedSwirlsMap().get(ie.getSecondaryModifier().getColor())+"</div>");

				break;
			}
		}

		return SVGImageSB.toString();
	}

	public static String getImportedSVGString(AbstractCoreItem item, Color importedColor, List<ItemEffect> effects) {

		if(((AbstractItem)item).getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId())) {
			if(effects.isEmpty() || effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchBase();
			}

			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchGynephilic();

			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAmbiphilic();

			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAndrophilic();
			}
		}

		StringBuilder SVGImageSB = new StringBuilder();

		String importedColorString = SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(importedColor);
		if(importedColorString==null || importedColorString.isEmpty() || importedColorString.equals("null")) {
			importedColorString = SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(effects.get(0).getItemEffectType().getColor());
		}

		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+importedColorString+"</div>");

		String s = item.getSVGString();

		Color color = Color.CLOTHING_BLUE_LIGHT;

		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != null && ie.getPrimaryModifier() != TFModifier.NONE) {
				color = ie.getPrimaryModifier().getColor();

				break;
			}
		}

		s = s.replaceAll("#ff2a2a", color.getShades()[0]);
		s = s.replaceAll("#ff5555|#f55", color.getShades()[1]);
		s = s.replaceAll("#ff8080", color.getShades()[2]);
		s = s.replaceAll("#ffaaaa|#faa", color.getShades()[3]);
		s = s.replaceAll("#ffd5d5", color.getShades()[4]);
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+s+"</div>");

		for(ItemEffect ie : effects) {
			if(ie.getSecondaryModifier() != null && ie.getSecondaryModifier() != TFModifier.NONE) {
				SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRefinedSwirlsMap().get(ie.getSecondaryModifier().getColor())+"</div>");

				break;
			}
		}

		return SVGImageSB.toString();
	}
}
