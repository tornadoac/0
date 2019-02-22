package com.lilithsthrone.game.inventory.clothing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.2.5
 * @author Innoxia
 */
public abstract class AbstractClothing extends AbstractCoreItem implements XMLSaving {


	private AbstractClothingType clothingType;
	protected List<ItemEffect> effects;

	private Color secondaryColor;
	private Color tertiaryColor;
	private boolean cummedIn, enchantmentKnown;
	private List<DisplacementType> displacedList;

	private String pattern; // name of the pattern.
	private Color patternColor;
	private Color patternSecondaryColor;
	private Color patternTertiaryColor;

	public AbstractClothing(AbstractClothingType clothingType, Color color, Color secondaryColor, Color tertiaryColor, boolean allowRandomEnchantment) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				clothingType.getAllAvailablePrimaryColors().contains(color) ? color : clothingType.getAllAvailablePrimaryColors().get(Util.random.nextInt(clothingType.getAllAvailablePrimaryColors().size())),
				clothingType.getRarity(),
				null);

		this.itemTags = new HashSet<>(clothingType.getItemTags());

		this.clothingType = clothingType;
		if(clothingType.getEffects()==null) {
			this.effects = new ArrayList<>();
		} else {
			this.effects = new ArrayList<>(clothingType.getEffects());
		}

		cummedIn = false;
		enchantmentKnown = true;

		this.secondaryColor = secondaryColor;
		this.tertiaryColor = tertiaryColor;

		patternColor = Color.CLOTHING_BLACK;
		patternSecondaryColor = Color.CLOTHING_BLACK;
		patternTertiaryColor = Color.CLOTHING_BLACK;

		displacedList = new ArrayList<>();

		if (effects.isEmpty() && allowRandomEnchantment && getClothingType().getRarity() == Rarity.COMMON) {
			int chance = Util.random.nextInt(100) + 1;

			List<TFModifier> attributeMods = new ArrayList<>(TFModifier.getClothingAttributeList());

			TFModifier rndMod = attributeMods.get(Util.random.nextInt(attributeMods.size()));
			attributeMods.remove(rndMod);
			TFModifier rndMod2 = attributeMods.get(Util.random.nextInt(attributeMods.size()));

			if (chance <= 25) { // Jinxed:

				effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.MINOR_BOOST, 0));
				effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedNegativePotency(), 0));
				if(chance <10) {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod2, TFPotency.getRandomWeightedNegativePotency(), 0));
				}

				enchantmentKnown = false;

			} else if (chance >= 75) { // Enchanted:
				effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedPositivePotency(), 0));
				if(chance > 90) {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod2, TFPotency.getRandomWeightedPositivePotency(), 0));
				}
				enchantmentKnown = false;
			}

		}
	}

	public AbstractClothing(AbstractClothingType clothingType, Color color, Color secondaryColor, Color tertiaryColor, List<ItemEffect> effects) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				clothingType.getAllAvailablePrimaryColors().contains(color) ? color : clothingType.getAllAvailablePrimaryColors().get(Util.random.nextInt(clothingType.getAllAvailablePrimaryColors().size())),
				clothingType.getRarity(),
				null);

		this.itemTags = new HashSet<>(clothingType.getItemTags());

		this.clothingType = clothingType;

		cummedIn = false;
		enchantmentKnown = true;

		this.secondaryColor = secondaryColor;
		this.tertiaryColor = tertiaryColor;

		patternColor = Color.CLOTHING_BLACK;
		patternSecondaryColor = Color.CLOTHING_BLACK;
		patternTertiaryColor = Color.CLOTHING_BLACK;

		displacedList = new ArrayList<>();
		if(effects!=null) {
			this.effects = new ArrayList<>(effects);
		} else {
			this.effects = new ArrayList<>();
		}

		enchantmentKnown = false;
	}

	@Override
	public boolean equals(Object o) {
		if(super.equals(o)){
			if(o instanceof AbstractClothing){
				if(((AbstractClothing)o).getClothingType().equals(getClothingType())
						&& ((AbstractClothing)o).getSecondaryColor()==secondaryColor
						&& ((AbstractClothing)o).getTertiaryColor()==tertiaryColor
						&& ((AbstractClothing)o).getPattern().equals(getPattern())
						&& ((AbstractClothing)o).isSealed()==this.isSealed()
						&& ((AbstractClothing)o).isDirty()==cummedIn
						&& ((AbstractClothing)o).isEnchantmentKnown()==enchantmentKnown
						&& ((AbstractClothing)o).isBadEnchantment()==this.isBadEnchantment()
						&& ((AbstractClothing)o).getEffects().equals(this.getEffects())
						){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getClothingType().hashCode();
		if(getSecondaryColor()!=null) {
			result = 31 * result + getSecondaryColor().hashCode();
		}
		if(getTertiaryColor()!=null) {
			result = 31 * result + getTertiaryColor().hashCode();
		}
		result = 31 * result + getPattern().hashCode();
		result = 31 * result + (this.isSealed() ? 1 : 0);
		result = 31 * result + (cummedIn ? 1 : 0);
		result = 31 * result + (enchantmentKnown ? 1 : 0);
		result = 31 * result + (this.isBadEnchantment() ? 1 : 0);
		result = 31 * result + this.getEffects().hashCode();
		return result;
	}

	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("clothing");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "id", this.getClothingType().getId());
		CharacterUtils.addAttribute(doc, element, "name", name);
		CharacterUtils.addAttribute(doc, element, "color", this.getColor().toString());
		CharacterUtils.addAttribute(doc, element, "colorSecondary", this.getSecondaryColor().toString());
		CharacterUtils.addAttribute(doc, element, "colorTertiary", this.getTertiaryColor().toString());
		CharacterUtils.addAttribute(doc, element, "patternColor", this.getPatternColor().toString());
		CharacterUtils.addAttribute(doc, element, "patternColorSecondary", this.getPatternSecondaryColor().toString());
		CharacterUtils.addAttribute(doc, element, "patternColorTertiary", this.getPatternTertiaryColor().toString());
		CharacterUtils.addAttribute(doc, element, "pattern", this.getPattern());
		CharacterUtils.addAttribute(doc, element, "isDirty", String.valueOf(this.isDirty()));
		CharacterUtils.addAttribute(doc, element, "enchantmentKnown", String.valueOf(this.isEnchantmentKnown()));

		Element innerElement = doc.createElement("effects");
		element.appendChild(innerElement);

		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}

		innerElement = doc.createElement("displacedList");
		element.appendChild(innerElement);
		for(DisplacementType dt : this.getDisplacedList()) {
			Element displacementType = doc.createElement("displacementType");
			innerElement.appendChild(displacementType);
			CharacterUtils.addAttribute(doc, displacementType, "value", dt.toString());
		}

		return element;
	}

	public static AbstractClothing loadFromXML(Element parentElement, Document doc) {
		AbstractClothing clothing = null;

		try {
			clothing = AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId(parentElement.getAttribute("id")), false);
		} catch(Exception ex) {
			System.err.println("Warning: An instance of AbstractClothing was unable to be imported. ("+parentElement.getAttribute("id")+")");
			return null;
		}

		if(clothing==null) {
			System.err.println("Warning: An instance of AbstractClothing was unable to be imported. ("+parentElement.getAttribute("id")+")");
			return null;
		}


		if(!parentElement.getAttribute("name").isEmpty()) {
			clothing.setName(parentElement.getAttribute("name"));
		}

		// Try to load color:
		try {
			if(clothing.getClothingType().getId()=="BDSM_CHOKER" && Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.6")) {
				clothing.setColor(Color.valueOf(parentElement.getAttribute("colorSecondary")));
				clothing.setSecondaryColor(Color.valueOf(parentElement.getAttribute("color")));
			} else {
				clothing.setColor(Color.valueOf(parentElement.getAttribute("color")));
				if(!parentElement.getAttribute("colorSecondary").isEmpty()) {
					Color secColor = Color.valueOf(parentElement.getAttribute("colorSecondary"));
					if(clothing.clothingType.getAllAvailableSecondaryColors().contains(secColor)) {
						clothing.setSecondaryColor(secColor);
					}
				}
			}
			if(!parentElement.getAttribute("colorTertiary").isEmpty()) {
				Color terColor = Color.valueOf(parentElement.getAttribute("colorTertiary"));
				if(clothing.clothingType.getAllAvailableTertiaryColors().contains(terColor)) {
					clothing.setTertiaryColor(terColor);
				}
			}

			if(!parentElement.getAttribute("pattern").isEmpty()) {
				String pat = parentElement.getAttribute("pattern");
				clothing.setPattern(pat);
			}

			if(!parentElement.getAttribute("patternColor").isEmpty()) {
				Color color = Color.valueOf(parentElement.getAttribute("patternColor"));
				clothing.setPatternColor(color);
			}
			if(!parentElement.getAttribute("patternColorSecondary").isEmpty()) {
				Color secColor = Color.valueOf(parentElement.getAttribute("patternColorSecondary"));
				clothing.setPatternSecondaryColor(secColor);
			}
			if(!parentElement.getAttribute("patternColorTertiary").isEmpty()) {
				Color terColor = Color.valueOf(parentElement.getAttribute("patternColorTertiary"));
				clothing.setPatternTertiaryColor(terColor);
			}
		} catch(Exception ex) {
		}

		// Try to load core features:
		try {
			if(!parentElement.getAttribute("sealed").isEmpty()) {
				clothing.setSealed(Boolean.valueOf(parentElement.getAttribute("sealed")));
			}
			clothing.setDirty(Boolean.valueOf(parentElement.getAttribute("isDirty")));
			clothing.setEnchantmentKnown(Boolean.valueOf(parentElement.getAttribute("enchantmentKnown")));
		} catch(Exception ex) {
		}

		// Try to load attributes:
		if(!Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.5") || !clothing.getClothingType().isCondom()) { // Do not load condom effects from versions prior to 0.3.0.5
			if(parentElement.getElementsByTagName("attributeModifiers")!=null && parentElement.getElementsByTagName("attributeModifiers").getLength()>0) {
				if(clothing.getClothingType().getClothingSet()==null) {
					clothing.getEffects().clear();

					Element element = (Element)parentElement.getElementsByTagName("attributeModifiers").item(0);
					NodeList modifierElements = element.getElementsByTagName("modifier");
					for(int i = 0; i < modifierElements.getLength(); i++){
						Element e = ((Element)modifierElements.item(i));
						try {
							Attribute att = Attribute.getAttributeFromId(e.getAttribute("attribute"));
							int value = Integer.valueOf(e.getAttribute("value"));

							TFPotency pot = TFPotency.BOOST;
							if(value <= -5) {
								pot = TFPotency.MAJOR_DRAIN;
							} else if(value <= -3) {
								pot = TFPotency.DRAIN;
							} else if(value <= -1) {
								pot = TFPotency.MINOR_DRAIN;
							} else if(value <= 1) {
								pot = TFPotency.MINOR_BOOST;
							} else if(value <= 3) {
								pot = TFPotency.BOOST;
							} else {
								pot = TFPotency.MAJOR_BOOST;
							}

							for(TFModifier mod : TFModifier.getClothingAttributeList()) {
								if(mod.getAssociatedAttribute()==att) {
									clothing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, mod, pot, 0));
									break;
								}
							}

							for(TFModifier mod : TFModifier.getClothingMajorAttributeList()) {
								if(mod.getAssociatedAttribute()==att) {
									clothing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, mod, pot, 0));
									break;
								}
							}

						} catch(Exception ex) {
						}
					}
				}

			} else {
				try {
					clothing.getEffects().clear();

					Element element = (Element)parentElement.getElementsByTagName("effects").item(0);
					NodeList effectElements = element.getElementsByTagName("effect");
					for(int i=0; i<effectElements.getLength(); i++){
						Element e = ((Element)effectElements.item(i));
						ItemEffect ie = ItemEffect.loadFromXML(e, doc);
						if(ie!=null) {
							clothing.addEffect(ie);
						}
					}
				} catch(Exception ex) {
				}
			}
		} else {
			clothing.setEnchantmentKnown(true);
		}

		// Try to load displacements:
		try {
			clothing.displacedList = new ArrayList<>();
			Element displacementElement = (Element)parentElement.getElementsByTagName("displacedList").item(0);
			NodeList displacementTypeElements = displacementElement.getElementsByTagName("displacementType");
			for(int i = 0; i < displacementTypeElements.getLength(); i++){
				Element e = ((Element)displacementTypeElements.item(i));

				DisplacementType dt = DisplacementType.valueOf(e.getAttribute("value"));
				boolean displacementTypeFound = false;
				for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList(null)) {
					if (bp.displacementType == dt) {
						displacementTypeFound = true;
					}
				}
				if(displacementTypeFound) {
					clothing.displacedList.add(dt);
				} else {
					System.err.println("Warning: Invalid displacement");
				}
			}
		} catch(Exception ex) {
		}

		return clothing;
	}

	public Color getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(Color secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public Color getTertiaryColor() {
		return tertiaryColor;
	}

	public void setTertiaryColor(Color tertiaryColor) {
		this.tertiaryColor = tertiaryColor;
	}

	/**
	 * Returns the name of a pattern that the clothing has.
	 * @return
	 */
	public String getPattern() {
		if(pattern == null) {
			return "none";
		}
		return pattern;
	}

	/**
	 * Changes pattern to specified one. Will not render that pattern if it doesn't exist or the item doesn't support it anyway.
	 * @param pattern
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Color getPatternColor() {
		return patternColor;
	}

	public Color getPatternSecondaryColor() {
		return patternSecondaryColor;
	}

	public Color getPatternTertiaryColor() {
		return patternTertiaryColor;
	}

	public void setPatternColor(Color patternColor) {
		this.patternColor = patternColor;
	}

	public void setPatternSecondaryColor(Color patternSecondaryColor) {
		this.patternSecondaryColor = patternSecondaryColor;
	}

	public void setPatternTertiaryColor(Color patternTertiaryColor) {
		this.patternTertiaryColor = patternTertiaryColor;
	}

	/**
	 * @return the name of a css class to use as a displayed rarity in inventory screens
	 */
	@Override
	public String getDisplayRarity() {
		if (!enchantmentKnown) {
			return "unknown";
		}
		return super.getDisplayRarity();
	}

	private static StringBuilder descriptionSB = new StringBuilder();

	public String getTypeDescription() {
		return this.getClothingType().getDescription();
	}

	@Override
	public String getDescription() {
		descriptionSB.setLength(0);

		descriptionSB.append(
				"<p>"
					+ getTypeDescription()
				+ "</p>");

		// Physical resistance
		descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They" : "It") + " provide" + (getClothingType().isPlural() ? "" : "s") + " <b>" + getClothingType().getPhysicalResistance() + "</b> <b style='color: "
				+ Attribute.RESISTANCE_PHYSICAL.getColor().toWebHexString() + ";'> " + Attribute.RESISTANCE_PHYSICAL.getName() + "</b>.</p>");

		if (enchantmentKnown) {
			if (!this.getEffects().isEmpty()) {
				descriptionSB.append("<p>Effects:");
				for (ItemEffect e : this.getEffects()) {
					if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE
							&& e.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
						for(String s : e.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
							descriptionSB.append("<br/>"+ s);
						}
					}
				}
				for(Entry<Attribute, Integer> entry : this.getAttributeModifiers().entrySet()) {
					descriptionSB.append("<br/>"+
							(entry.getValue()<0
									?"[style.boldBad("+entry.getValue()+")] "
									:"[style.boldGood(+"+entry.getValue()+")] ")
							+ "<b style='color:"+entry.getKey().getColor().toWebHexString()+";'>"+Util.capitalizeSentence(entry.getKey().getName())+"</b>");
				}
				descriptionSB.append("</p>");
			}

			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They have" : "It has") + " a value of " + UtilText.formatAsMoney(getValue()) + ".");
		} else {
			descriptionSB.append("<br/>" + (getClothingType().isPlural() ? "They have" : "It has") + " an <b>unknown value</b>!");
		}

		descriptionSB.append("</p>");

		if (getClothingType().getClothingSet() != null) {
			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They are" : "It is") + " part of the <b style='color:" + Color.RARITY_EPIC.toWebHexString() + ";'>"
					+ getClothingType().getClothingSet().getName() + "</b> set." + "</p>");
		}

		return descriptionSB.toString();
	}

	public AbstractClothingType getClothingType() {
		return clothingType;
	}

	public boolean isCanBeEquipped(GameCharacter clothingOwner) {
		return this.getClothingType().isCanBeEquipped(clothingOwner);
	}

	public String getCannotBeEquippedText(GameCharacter clothingOwner) {
		return UtilText.parse(clothingOwner, this.getClothingType().getCannotBeEquippedText(clothingOwner));
	}

	@Override
	public Rarity getRarity() {

		if(this.isCondom()) {
			if(this.getEffects().get(0).getPotency().isNegative()) {
				return Rarity.JINXED;
			} else {
				return rarity;
			}
		}

		if(rarity==Rarity.LEGENDARY || rarity==Rarity.QUEST) {
			return rarity;
		}
		if(this.getClothingType().getClothingSet()!=null || rarity==Rarity.EPIC) {
			return Rarity.EPIC;
		}

		if(this.isSealed() || this.isBadEnchantment()) {
			return Rarity.JINXED;
		}
		if(rarity==Rarity.COMMON) {
			if(this.getEffects().size()>1) {
				return Rarity.RARE;
			}
			if(!this.getEffects().isEmpty()) {
				return Rarity.UNCOMMON;
			}

			return Rarity.COMMON;
		}

		return rarity;
	}

	@Override
	public int getValue() {
		float runningTotal = this.getClothingType().getBaseValue();

		if (colorShade == Color.CLOTHING_PLATINUM) {
			runningTotal *= 2f;

		} else if (colorShade == Color.CLOTHING_GOLD) {
			runningTotal *= 1.75f;

		} else if (colorShade == Color.CLOTHING_ROSE_GOLD) {
			runningTotal *= 1.5f;

		} else if (colorShade == Color.CLOTHING_SILVER) {
			runningTotal *= 1.25f;
		}

		if(rarity==Rarity.JINXED) {
			runningTotal *= 0.5;
		}

		float attributeBonuses = 0;//getModifiedDropoffValue
		if (attributeModifiers != null) {
			for (Integer i : attributeModifiers.values()) {
				attributeBonuses += i * 15;
			}
		}

		if (getClothingType().getClothingSet() != null) {
			if (getClothingType().getClothingSet().getAssociatedStatusEffect().getAttributeModifiers(Main.game.getPlayer()) != null) {
				for (Float f : getClothingType().getClothingSet().getAssociatedStatusEffect().getAttributeModifiers(Main.game.getPlayer()).values()) {
					attributeBonuses += f * 15;
				}
			}
		}

		attributeBonuses = Util.getModifiedDropoffValue(attributeBonuses, 500);

		runningTotal += Math.max(0, attributeBonuses);

		if (runningTotal < 1) {
			runningTotal = 1;
		}

		return (int) runningTotal;
	}

	@Override
	public int getPrice(float modifier) {
		if (!enchantmentKnown) {
			return 50;
		}
		return super.getPrice(modifier);
	}

	@Override
	public String getName() {
		return this.getEffects().isEmpty()?this.getClothingType().getName():name;
	}

	/**
	 * @param withDeterminer
	 *			True if you want the determiner to prefix the name
	 * @return A string in the format "blue shirt" or "a blue shirt"
	 */
	public String getName(boolean withDeterminer) {
		return (withDeterminer ? (getClothingType().isPlural() ? getClothingType().getDeterminer() + " " : (Util.isVowel(getColor().getName().charAt(0)) ? "an " : "a ")) : "") + getColor().getName() + " " + getName();
	}

	public String getName(boolean withDeterminer, boolean withRarityColor) {
		if (!enchantmentKnown) {
			return (withDeterminer
						? (getClothingType().isPlural()
								? getClothingType().getDeterminer() + " "
								: (Util.isVowel(getColor().getName().charAt(0)) ? "an " : "a "))
						: "")
					+ getColor().getName()
					+ (withRarityColor
							? (" <span style='color: " + Color.RARITY_UNKNOWN.toWebHexString() + ";'>" + getName() + "</span>")
							: " "+getName());
		} else {
			return (withDeterminer
					? (getClothingType().isPlural()
							? getClothingType().getDeterminer() + " "
							: (Util.isVowel(getColor().getName().charAt(0)) ? "an " : "a "))
					: "")
					+ getColor().getName()
					+ (withRarityColor
							? (" <span style='color: " + this.getRarity().getColor().toWebHexString() + ";'>" + getName() + "</span>")
							: " "+getName());
		}
	}

	/**
	 * @param withRarityColor
	 *			If true, the name will be colored to its rarity.
	 * @return A string in the format "Blue cap of frostbite" or
	 *		 "Gold circlet of anti-magic"
	 */
	public String getDisplayName(boolean withRarityColor) {

		if(!this.getName().replaceAll("\u00A0"," ").equalsIgnoreCase(this.getClothingType().getName().replaceAll("\u00A0"," "))) { // If this item has a custom name, just display that:
//			for(int i=0;i<this.getName().toCharArray().length;i++) {
//				System.out.print("["+Character.codePointAt(this.getName().toCharArray(), i)+"]");
//			}
//			System.out.println();
//			for(int i=0;i<this.getClothingType().getName().toCharArray().length;i++) {
//				System.out.print("["+Character.codePointAt(this.getClothingType().getName().toCharArray(), i)+"]");
//			}
//			System.out.println();
//			System.out.println();

			return (withRarityColor
					? (" <span style='color: " + (!this.isEnchantmentKnown()?Color.RARITY_UNKNOWN:this.getRarity().getColor()).toWebHexString() + ";'>" + getName() + "</span>")
					: getName());
		}

		return Util.capitalizeSentence(getColor().getName()) + " "
				+ (!this.getPattern().equalsIgnoreCase("none")?Pattern.getPattern(this.getPattern()).getNiceName():"")
				+ (withRarityColor
					? (" <span style='color: " + (!this.isEnchantmentKnown()?Color.RARITY_UNKNOWN:this.getRarity().getColor()).toWebHexString() + ";'>" + getName() + "</span>")
					: getName())
				+(!this.getEffects().isEmpty() && this.isEnchantmentKnown() && this.getRarity()!=Rarity.QUEST && this.getRarity()!=Rarity.LEGENDARY && this.getRarity()!=Rarity.EPIC
						? " "+getEnchantmentPostfix(withRarityColor, "b")
						: "");
	}

	@Override
	public String getSVGString() {
		return getClothingType().getSVGImage(colorShade, secondaryColor, tertiaryColor, pattern, patternColor, patternSecondaryColor, patternTertiaryColor);
	}

	public String getSVGEquippedString(GameCharacter character) {
		return getClothingType().getSVGEquippedImage(character, colorShade, secondaryColor, tertiaryColor, pattern, patternColor, patternSecondaryColor, patternTertiaryColor);
	}

	/**
	 * Applies any extra effects this clothing causes when being equipped.
	 *
	 * @return A description of this clothing being equipped.
	 */
	public String onEquipApplyEffects(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		if (!enchantmentKnown) {
			enchantmentKnown = true;

			pointlessSB.setLength(0);
				if (this.isBadEnchantment()) {
					clothingOwner.incrementAttribute(Attribute.MAJOR_CORRUPTION, 1);
					pointlessSB.append(
							getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, true)
							+ "<p style='text-align:center;'>"
									+ "<b style='color:" + Color.GENERIC_BAD.toWebHexString() + ";'>Jinx revealed:</b> "+getDisplayName(true));

					for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
						pointlessSB.append("<br/><b>(" + att.getValue()+"</b> <b style='color:"+att.getKey().getColor().toWebHexString()+";'>"+ Util.capitalizeSentence(att.getKey().getName()) + "</b><b>)</b>");
					}

					pointlessSB.append("<br/>"
							+ "<b>"+(clothingOwner.isPlayer()?"You gain":UtilText.parse(clothingOwner, "[npc.Name] gains"))
									+" +1</b> <b style='color:" + Color.GENERIC_TERRIBLE.toWebHexString()+ ";'>core</b> <b style='color:" + Color.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>corruption</b> <b>from discovering their jinx...</b>"
							+ "</p>");

				} else {
					pointlessSB.append(
							getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, true)
							+ "<p style='text-align:center;'>"
									+ "<b style='color:" + Color.GENERIC_GOOD.toWebHexString() + ";'>Enchantment revealed:</b> "+getDisplayName(true));

					for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
						pointlessSB.append("<br/><b>(+" + att.getValue()+"</b> <b style='color:"+att.getKey().getColor().toWebHexString()+";'>"+ Util.capitalizeSentence(att.getKey().getName()) + "</b><b>)</b>");
					}

					pointlessSB.append("</p>");
				}

			return pointlessSB.toString();

		} else {
			return getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, true);
		}
	}

	/**
	 * @return A description of this clothing being equipped.
	 */
	public String onEquipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, false);
	}

	/**
	 * Applies any extra effects this clothing causes when being unequipped.
	 *
	 * @return A description of this clothing being unequipped.
	 */
	public String onUnequipApplyEffects(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().unequipText(clothingOwner, clothingEquipper, rough, this, true);
	}

	/**
	 * @return A description of this clothing being unequipped.
	 */
	public String onUnequipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().unequipText(clothingOwner, clothingEquipper, rough, this, false);
	}

	private static List<String> incompatibleClothing = new ArrayList<>();

	/**
	 * Returns a formatted description of if this clothing is sealed, cummedIn, too feminine/masculine and what slots it is blocking.
	 */
	public String clothingExtraInformation(GameCharacter equippedToCharacter) {
		StringBuilder extraInformationSB = new StringBuilder();

		if (equippedToCharacter == null) { // The clothing is not currently equipped by anyone:

			incompatibleClothing.clear();
			if (!getClothingType().getIncompatibleSlots(null).isEmpty()) {
				for (InventorySlot invSlot : getClothingType().getIncompatibleSlots(null))
					if (Main.game.getPlayer().getClothingInSlot(invSlot) != null)
						incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
			}
			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
				for (InventorySlot invSlot : c.getClothingType().getIncompatibleSlots(null))
					if (getClothingType().getSlot() == invSlot)
						incompatibleClothing.add(c.getClothingType().getName());

			if(!getClothingType().getIncompatibleSlots(null).isEmpty()) {
				extraInformationSB.append("Equipping "+(getClothingType().isPlural()?"them":"it")+" will [style.boldBad(block)] your "+ Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots(null))+".<br/>");
			}

			if(Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot())!=null && Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot()).getClothingType().isDiscardedOnUnequip()) {
				extraInformationSB.append("[style.boldBad(Equipping this will cause the "+Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot()).getName()+" you're already wearing to be discarded!)]<br/>");
			}

			if(this.isSealed() && enchantmentKnown) {
				extraInformationSB.append((getClothingType().isPlural() ? "They" : "It") + " will [style.boldJinx(jinx)] " + (getClothingType().isPlural() ? "themselves" : "itself") + " onto you!<br/>");
			}

			if(!enchantmentKnown) {
				extraInformationSB.append("You can either take " + (getClothingType().isPlural() ? "them" : "it") + " to a suitable vendor, or equip " + (getClothingType().isPlural() ? "them" : "it") + " now to identify the"
						+ " <b style='color: "+ Color.RARITY_UNKNOWN.toWebHexString() + ";'>unknown enchantment</b>!<br/>");
			}

			if(cummedIn) {
				extraInformationSB.append((getClothingType().isPlural() ? "They have" : "It has") + " been <b style='color: " + Color.CUM.toWebHexString() + ";'>covered in sexual fluids</b>!<br/>");
			}

			for(ItemTag tag : this.getItemTags()) {
				if(tag.getClothingTooltipAdditions()!=null) {
					for(String description : tag.getClothingTooltipAdditions()) {
						extraInformationSB.append(description+"<br/>");
					}
				}
			}

//			if(this.getClothingType().isMufflesSpeech()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They [style.boldBad(muffle" : "It [style.boldBad(muffles") + " the wearer's speech)].<br/>");
//			}
//
//			if(this.getClothingType().isHindersLegMovement()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They [style.boldTerrible(block" : "It [style.boldTerrible(blocks") + " the wearer's escape in combat)] (if they are unable to fly).<br/>");
//			}
//
//			if(this.getClothingType().isHindersArmMovement()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They [style.boldTerrible(block" : "It [style.boldTerrible(blocks") + " flight from arm-wings)].<br/>");
//			}

			if(getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Color.MASCULINE.toWebHexString() + ";'>too masculine</b> for you.<br/>");
			}

			if(getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Color.FEMININE.toWebHexString() + ";'>too feminine</b> for you.<br/>");
			}

			if(!incompatibleClothing.isEmpty()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color:" + Color.GENERIC_BAD.toWebHexString() + ";'>incompatible</b> with your "
						+ Util.stringsToStringList(incompatibleClothing, false) + ".<br/>");
			}

			if(extraInformationSB.length()==0) {
				return "";
			}
			return "<p>"+extraInformationSB.toString().substring(0, extraInformationSB.length()-5)+"</p>";

		} else {

			if(!getClothingType().getIncompatibleSlots(equippedToCharacter).isEmpty()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldBad(blocking)] [npc.her] "
						+ Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots(equippedToCharacter)) + "!<br/>");
			}

			if(this.isSealed()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldCorruption(jinxed)] and can't be removed!<br/>");

			} else if(this.getClothingType().isDiscardedOnUnequip()) {
				extraInformationSB.append("[style.boldBad(Removing [npc.namePos] "+this.getName()+" will cause "+(getClothingType().isPlural() ? "them" : "it")+" to be discarded!)]<br/>");
			}

			if(cummedIn) {
				extraInformationSB.append((getClothingType().isPlural() ? "They have" : "It has") + " been <b style='color: " + Color.CUM.toWebHexString() + ";'>covered in sexual fluids</b>!<br/>");
			}

			for(ItemTag tag : this.getItemTags()) {
				if(tag.getClothingTooltipAdditions()!=null) {
					for(String description : tag.getClothingTooltipAdditions()) {
						extraInformationSB.append(description+"<br/>");
					}
				}
			}

//			if(this.getClothingType().isMufflesSpeech()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldBad(muffling [npc.her] speech)].<br/>");
//			}
//
//			if(this.getClothingType().isHindersLegMovement() && !equippedToCharacter.isAbleToFly()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldTerrible(blocking [npc.her] escape in combat)].<br/>");
//			}
//
//			if(this.getClothingType().isHindersArmMovement() && equippedToCharacter.isAbleToFlyFromArms()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are " : "It is") + " [style.boldTerrible(blocking flight from [npc.her] arm-wings)].<br/>");
//			}

			if(getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Color.MASCULINE.toWebHexString() + ";'>too masculine</b> for [npc.herHim].<br/>");
			}

			if(getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Color.FEMININE.toWebHexString() + ";'>too feminine</b> for [npc.herHim].<br/>");
			}

			if(!displacedList.isEmpty()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They have been" : "It has been")
						+ " <b style='color: " + Color.GENERIC_BAD.toWebHexString() + ";'>"+ Util.displacementTypesToStringList(displacedList) + "</b>!<br/>");
			}

			if(extraInformationSB.length()==0) {
				return "";
			}
			return "<p>"+UtilText.parse(equippedToCharacter, extraInformationSB.toString().substring(0, extraInformationSB.length()-5))+"</p>";
		}

	}

	public String getDisplacementBlockingDescriptions(GameCharacter equippedToCharacter){
		descriptionSB = new StringBuilder("<p><b>Displacement types:</b>");
		for(BlockedParts bp : getClothingType().getBlockedPartsList(equippedToCharacter)){
			descriptionSB.append("<br/><b>"+Util.capitalizeSentence(bp.displacementType.getDescription())+":</b> ");
			if(equippedToCharacter.isAbleToBeDisplaced(this, bp.displacementType, false, false, equippedToCharacter))
				descriptionSB.append("<b style='color:"+Color.GENERIC_GOOD.toWebHexString()+";'>Available</b>");
			else
				descriptionSB.append("<b style='color:"+Color.GENERIC_BAD.toWebHexString()+";'>Blocked</b> by "+equippedToCharacter.getBlockingClothing().getName()+"");
		}
		descriptionSB.append("</p>");

		return descriptionSB.toString();
	}

	public List<String> getExtraDescriptions(GameCharacter equippedToCharacter) {
		List<String> descriptionsList = new ArrayList<>();

		for(ItemTag tag : this.getItemTags()) {
			if(tag.getClothingTooltipAdditions()!=null) {
				for(String description : tag.getClothingTooltipAdditions()) {
					descriptionsList.add(description);
				}
			}
		}

		if (equippedToCharacter == null) { // The clothing is not currently equipped by anyone:
			incompatibleClothing.clear();
			if (!getClothingType().getIncompatibleSlots(null).isEmpty()) {
				for (InventorySlot invSlot : getClothingType().getIncompatibleSlots(null))
					if (Main.game.getPlayer().getClothingInSlot(invSlot) != null)
						incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
			}
			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
				for (InventorySlot invSlot : c.getClothingType().getIncompatibleSlots(null))
					if (getClothingType().getSlot() == invSlot)
						incompatibleClothing.add(c.getClothingType().getName());

			if (!getClothingType().getIncompatibleSlots(null).isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Color.GENERIC_BAD.toWebHexString()+";'>Equipping
				// blocks</b>");
				for (InventorySlot slot : getClothingType().getIncompatibleSlots(null)) {
					descriptionsList.add("<b style='color: " + Color.GENERIC_BAD.toWebHexString() + ";'>Blocks " + Util.capitalizeSentence(slot.getName()) + "</b>");
				}
			}

			if (this.isSealed() && enchantmentKnown) {
				descriptionsList.add("<b style='color: " + Color.GENERIC_ARCANE.toWebHexString() + ";'>Jinxed</b>");
			}

			if (cummedIn) {
				descriptionsList.add("<b style='color: " + Color.CUM.toWebHexString() + ";'>Dirty</b>");
			}

			if (getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Color.MASCULINE.toWebHexString() + ";'>Too masculine</b>");
			}

			if (getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Color.FEMININE.toWebHexString() + ";'>Too feminine</b>");
			}

			if (!incompatibleClothing.isEmpty()) {
				descriptionsList.add("<b style='color: " + Color.GENERIC_BAD.toWebHexString() + ";'>Incompatible with:</b>");
				descriptionsList.addAll(incompatibleClothing);
			}

		} else { // Being worn:

			if (!getClothingType().getIncompatibleSlots(equippedToCharacter).isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Color.GENERIC_BAD.toWebHexString()+";'>Blocking</b>");
				for (InventorySlot slot : getClothingType().getIncompatibleSlots(equippedToCharacter))
					descriptionsList.add("<b style='color: " + Color.GENERIC_BAD.toWebHexString() + ";'>Blocking " + Util.capitalizeSentence(slot.getName()) + "</b>");
			}

			if (this.isSealed() && enchantmentKnown) {
				descriptionsList.add("<b style='color: " + Color.GENERIC_ARCANE.toWebHexString() + ";'>Jinxed</b>");
			}

			if (cummedIn) {
				descriptionsList.add("<b style='color: " + Color.CUM.toWebHexString() + ";'>Dirty</b>");
			}

			if (getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Color.MASCULINE.toWebHexString() + ";'>Too masculine</b>");
			}

			if (getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Color.FEMININE.toWebHexString() + ";'>Too feminine</b>");
			}

			if (!displacedList.isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Color.GENERIC_BAD.toWebHexString()+";'>Displaced</b>");
				for (DisplacementType dt : displacedList)
					descriptionsList.add("<b style='color: " + Color.GENERIC_BAD.toWebHexString() + ";'>" + Util.capitalizeSentence(dt.getDescriptionPast()) + "</b>");
			}

		}

		return descriptionsList;
	}

	/**
	 * @return A list of blocked body parts. e.g. "Penis, Anus and Vagina" or "Nipples"
	 */
	public String getClothingBlockingDescription(DisplacementType dt, GameCharacter owner, String preFix, String postFix) {
		Set<CoverableArea> coveredAreas = new HashSet<>();// EnumSet.noneOf(CoverableArea.class);

		if (dt == null) {
			for (BlockedParts bp : this.getClothingType().getBlockedPartsList(owner)) {
				if (!this.getDisplacedList().contains(bp.displacementType)) {
					coveredAreas.addAll(bp.blockedBodyParts);
				}
			}
		} else {
			for (BlockedParts bp : this.getClothingType().getBlockedPartsList(owner)) {
				if (bp.displacementType == dt) {
					coveredAreas.addAll(bp.blockedBodyParts);
				}
			}
		}

		if(owner!=null) {
			if (owner.getVaginaType() == VaginaType.NONE)
				coveredAreas.remove(CoverableArea.VAGINA);
			if (owner.getPenisType() == PenisType.NONE)
				coveredAreas.remove(CoverableArea.PENIS);
		}

		if (!coveredAreas.isEmpty())
			return preFix + Util.setToStringListCoverableArea(coveredAreas) + postFix;
		else
			return "";
	}

	public void removeBadEnchantments() {
		this.getEffects().removeIf(e -> (e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || e.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) && e.getPotency().isNegative());
	}

	public boolean isSealed() {
		for(ItemEffect effect : this.getEffects()) {
			if(effect!=null && effect.getPrimaryModifier()==TFModifier.CLOTHING_SEALING) {
				return true;
			} else if(effect==null) {
				System.err.println("AbstractClothing.isSealed() for "+this.getName()+" is encountering a null ItemEffect!");
			}
		}
		return false;
	}

	public void setSealed(boolean sealed) {
		if(sealed) {
			this.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.NONE, TFPotency.MINOR_BOOST, 0));
		} else {
			this.getEffects().removeIf(e -> e.getPrimaryModifier() == TFModifier.CLOTHING_SEALING);
		}
	}

	public int getJinxRemovalCost() {
		for(ItemEffect effect : this.getEffects()) {
			if(effect.getPrimaryModifier()==TFModifier.CLOTHING_SEALING) {
				switch(effect.getPotency()) {
					case BOOST:
						break;
					case DRAIN:
						return ItemEffect.SEALED_COST_DRAIN;
					case MAJOR_BOOST:
						break;
					case MAJOR_DRAIN:
						return ItemEffect.SEALED_COST_MAJOR_DRAIN;
					case MINOR_BOOST:
						return ItemEffect.SEALED_COST_MINOR_BOOST;
					case MINOR_DRAIN:
						return ItemEffect.SEALED_COST_MINOR_DRAIN;
				}
			}
		}
		return ItemEffect.SEALED_COST_MINOR_BOOST;
	}

	public boolean isDirty() {
		return cummedIn;
	}

	public void setDirty(boolean cummedIn) {
		this.cummedIn = cummedIn;
		if(Main.game.getPlayer()!=null) {
			if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(this)) {
				Main.game.getPlayer().updateInventoryListeners();
			}
		}
	}

	public List<DisplacementType> getDisplacedList() {
		return displacedList;
	}

	public void clearDisplacementList() {
		displacedList.clear();
	}

	public boolean isEnchantmentKnown() {
		return enchantmentKnown;
	}

	private StringBuilder pointlessSB = new StringBuilder();
	public String setEnchantmentKnown(boolean enchantmentKnown) {
		pointlessSB.setLength(0);
		this.enchantmentKnown = enchantmentKnown;

		if(enchantmentKnown && !attributeModifiers.isEmpty()){
			if (isBadEnchantment()) {
				pointlessSB.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + Color.GENERIC_BAD.toWebHexString() + ";'>Jinx revealed:</b> "+getDisplayName(true));

				for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
					pointlessSB.append("<br/><b>(" + att.getValue()+"</b> <b style='color:"+att.getKey().getColor().toWebHexString()+";'>"+ Util.capitalizeSentence(att.getKey().getName()) + "</b><b>)</b>");
				}

				pointlessSB.append("</p>");

			} else {
				pointlessSB.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + Color.GENERIC_GOOD.toWebHexString() + ";'>Enchantment revealed:</b> "+getDisplayName(true));

				for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
					pointlessSB.append("<br/><b>(+" + att.getValue()+"</b> <b style='color:"+att.getKey().getColor().toWebHexString()+";'>"+ Util.capitalizeSentence(att.getKey().getName()) + "</b><b>)</b>");
				}

				pointlessSB.append("</p>");
			}

		} else {
			return "";
		}

		return pointlessSB.toString();
	}

	public Attribute getCoreEnchantment() {
		Attribute att = Attribute.MAJOR_PHYSIQUE;
		int max = 0;
		for(Entry<Attribute, Integer> entry : getAttributeModifiers().entrySet()) {
			if(entry.getValue() > max) {
				att = entry.getKey();
				max = entry.getValue();
			}
		}

		return att;
	}

	public String getEnchantmentPostfix(boolean colored, String tag) {
		if(!this.getEffects().isEmpty() && !this.isCondom()) {
			for(ItemEffect ie : this.getEffects()) {
				if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ENSLAVEMENT) {
					return "of "+(colored?"<"+tag+" style='color:"+TFModifier.CLOTHING_ENSLAVEMENT.getColor().toWebHexString()+";'>enslavement</"+tag+">":"enslavement");

				} else if(ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BEHAVIOR || ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BODY_PART) {
					return "of "+(colored?"<"+tag+" style='color:"+Color.FETISH.toWebHexString()+";'>"+ie.getSecondaryModifier().getDescriptor()+"</"+tag+">":ie.getSecondaryModifier().getDescriptor());

				} else if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || ie.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
					String name = (this.isBadEnchantment()?this.getCoreEnchantment().getNegativeEnchantment():this.getCoreEnchantment().getPositiveEnchantment());
					return "of "+(colored?"<"+tag+" style='color:"+this.getCoreEnchantment().getColor().toWebHexString()+";'>"+name+"</"+tag+">":name);

				} else if(ie.getPrimaryModifier() == TFModifier.CLOTHING_SEALING) {
					return "of "+(colored?"<"+tag+" style='color:"+Color.SEALED.toWebHexString()+";'>sealing</"+tag+">":"sealing");

				} else {
					return "of "+(colored?"<"+tag+" style='color:"+Color.TRANSFORMATION_GENERIC.toWebHexString()+";'>transformation</"+tag+">":"transformation");
				}
			}
		}
		return "";
	}

	public boolean isBadEnchantment() {
		return this.getEffects().stream().anyMatch(e -> (e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || e.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) && e.getPotency().isNegative());
	}

	public boolean isEnslavementClothing() {
		return this.getEffects().stream().anyMatch(e -> e.getPrimaryModifier() == TFModifier.CLOTHING_ENSLAVEMENT);
	}

	@Override
	public List<ItemEffect> getEffects() {
		return effects;
	}

	public void addEffect(ItemEffect effect) {
		effects.add(effect);
	}

	public void removeEffect(ItemEffect effect) {
		effects.remove(effect);
	}

	@Override
	public Map<Attribute, Integer> getAttributeModifiers() {
		attributeModifiers.clear();

		for(ItemEffect ie : getEffects()) {
			if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || ie.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				if(attributeModifiers.containsKey(ie.getSecondaryModifier().getAssociatedAttribute())) {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), attributeModifiers.get(ie.getSecondaryModifier().getAssociatedAttribute()) + ie.getPotency().getClothingBonusValue());
				} else {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), ie.getPotency().getClothingBonusValue());
				}

			}
		}

		return attributeModifiers;
	}

	@Override
	public int getEnchantmentLimit() {
		return clothingType.getEnchantmentLimit();
	}

	@Override
	public AbstractItemEffectType getEnchantmentEffect() {
		return clothingType.getEnchantmentEffect();
	}

	@Override
	public AbstractCoreType getEnchantmentItemType(List<ItemEffect> effects) {
		return clothingType.getEnchantmentItemType(effects);
	}

	@Override
	public TFEssence getRelatedEssence() {
		return clothingType.getRelatedEssence();
	}

	public boolean isCondom() {
		return this.getClothingType().getItemTags().contains(ItemTag.CONDOM);
	}

	public ItemEffect getCondomEffect() {
		for(ItemEffect ie : this.getEffects()) {
			if(ie.getPrimaryModifier()==TFModifier.CLOTHING_CONDOM) {
				return ie;
			}
		}
		return null;
	}
}
