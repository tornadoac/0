package com.lilithsthrone.game.character.markings;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class Tattoo extends AbstractCoreItem implements XMLSaving {


	private AbstractTattooType type;

	private Color primaryColor;
	private Color secondaryColor;
	private Color tertiaryColor;

	private boolean glowing;

	private TattooWriting writing;

	private TattooCounter counter;

	protected List<ItemEffect> effects;

	protected Map<Attribute, Integer> attributeModifiers;

	private static Map<Color, String> SVGGlowMap = new HashMap<>();

	public Tattoo(AbstractTattooType type,
			Color primaryColor,
			Color secondaryColor,
			Color tertiaryColor,
			boolean glowing,
			TattooWriting writing,
			TattooCounter counter) {
		super(type.getName(),
				type.getName(),
				type.getPathName(),
				primaryColor,
				Rarity.COMMON,
				null);

		this.type = type;

		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.tertiaryColor = tertiaryColor;

		this.glowing = glowing;

		this.writing = writing;

		this.counter = counter;

		this.effects = new ArrayList<>();

		attributeModifiers = new HashMap<>();
	}

	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof Tattoo)
					&& ((Tattoo)o).getType()==type
					&& ((Tattoo)o).getPrimaryColor()==primaryColor
					&& ((Tattoo)o).getPrimaryColor()==secondaryColor
					&& ((Tattoo)o).getPrimaryColor()==tertiaryColor
					&& ((Tattoo)o).isGlowing()==glowing
					&& ((Tattoo)o).getWriting().equals(this.getWriting())
					&& ((Tattoo)o).getCounter().equals(this.getCounter())
					&& ((Tattoo)o).getEffects().equals(this.getEffects());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + this.getPrimaryColor().hashCode();
		if(this.getSecondaryColor()!=null) {
			result = 31 * result + this.getSecondaryColor().hashCode();
		}
		if(this.getTertiaryColor()!=null) {
			result = 31 * result + this.getTertiaryColor().hashCode();
		}
		result = 31 * result + (this.isGlowing() ? 1 : 0);
		result = 31 * result + this.getWriting().hashCode();
		result = 31 * result + this.getCounter().hashCode();
		result = 31 * result + this.getEffects().hashCode();
		return result;
	}

	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("tattoo");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "id", TattooType.getIdFromTattooType(getType()));

		CharacterUtils.addAttribute(doc, element, "name", this.getName());

		CharacterUtils.addAttribute(doc, element, "primaryColor", this.getPrimaryColor().toString());

		if(this.getSecondaryColor()!=null) {
			CharacterUtils.addAttribute(doc, element, "secondaryColor", this.getSecondaryColor().toString());
		}

		if(this.getTertiaryColor()!=null) {
			CharacterUtils.addAttribute(doc, element, "tertiaryColor", this.getTertiaryColor().toString());
		}

		CharacterUtils.addAttribute(doc, element, "glowing", String.valueOf(this.isGlowing()));

		if(this.getWriting()!=null) {
			this.getWriting().saveAsXML(element, doc);
		}

		if(this.getCounter()!=null) {
			this.getCounter().saveAsXML(element, doc);
		}

		Element innerElement = doc.createElement("effects");
		element.appendChild(innerElement);

		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}

		return element;
	}

	public static Tattoo loadFromXML(Element parentElement, Document doc) {
		try {
			TattooWriting writing = null;
			if(parentElement.getElementsByTagName("tattooWriting").item(0)!=null) {
				writing = TattooWriting.loadFromXML((Element) parentElement.getElementsByTagName("tattooWriting").item(0), doc);
			}

			TattooCounter counter = null;
			if(parentElement.getElementsByTagName("tattooCounter").item(0)!=null) {
				counter = TattooCounter.loadFromXML((Element) parentElement.getElementsByTagName("tattooCounter").item(0), doc);
			}
			Tattoo tat = new Tattoo(
					TattooType.getTattooTypeFromId(parentElement.getAttribute("id")),
					Color.valueOf(parentElement.getAttribute("primaryColor")),
					parentElement.getAttribute("secondaryColor").isEmpty()?null:Color.valueOf(parentElement.getAttribute("secondaryColor")),
					parentElement.getAttribute("tertiaryColor").isEmpty()?null:Color.valueOf(parentElement.getAttribute("tertiaryColor")),
					Boolean.valueOf(parentElement.getAttribute("glowing")),
					writing,
					counter);

			try {
				tat.setName(parentElement.getAttribute("name"));
			} catch(Exception ex) {
			}

			Element element = (Element)parentElement.getElementsByTagName("effects").item(0);
			if(element!=null) {
				NodeList nodeList = element.getElementsByTagName("effect");
				for(int i = 0; i < nodeList.getLength(); i++){
					Element e = ((Element)nodeList.item(i));
					ItemEffect ie = ItemEffect.loadFromXML(e, doc);
					if(ie!=null) {
						tat.addEffect(ie);
					}
				}
			}

			return tat;

		} catch(Exception ex) {
			System.err.println("Warning: An instance of Tattoo was unable to be imported!");
			return null;
		}
	}

	@Override
	public String getSVGString() {
		return getSVGImage(Main.game.getPlayer());
	}

	public String getSVGImage(GameCharacter character) {
		if(this.isGlowing()) {
			return getSVGGlow() + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"+type.getSVGImage(character, primaryColor, secondaryColor, tertiaryColor)+"</div>";

		} else {
			return type.getSVGImage(character, primaryColor, secondaryColor, tertiaryColor);
		}
	}

	private String getSVGGlow() {
		String stringFromMap = SVGGlowMap.get(this.getPrimaryColor());
		if (stringFromMap!=null) {
			return stringFromMap;

		} else {
			try {
				InputStream is;
				String s;

				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/tattoos/glow.svg");
				s = Util.inputStreamToString(is);
				is.close();

				s = SvgUtil.colorReplacement("tattooGlow"+this.getPrimaryColor().toString(), this.getPrimaryColor(), this.getPrimaryColor(), this.getPrimaryColor(), s);

				SVGGlowMap.put(this.getPrimaryColor(), s);

				return s;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "";
	}

	public Rarity getRarity() {
		if(effects.isEmpty()) {
			return Rarity.COMMON;

		} else if(effects.size()<=1) {
			return Rarity.UNCOMMON;

		} else if(effects.size()<=3) {
			return Rarity.RARE;

		} else if(effects.size()<=5) {
			return Rarity.EPIC;

		} else {
			return Rarity.LEGENDARY;
		}
	}

	public String getDisplayName(boolean withRarityColor) {
		return Util.capitalizeSentence(this.getPrimaryColor().getName()) + " "
				+ (withRarityColor
					?" <span style='color: " + this.getRarity().getColor().toWebHexString() + ";'>"
						+ (this.getType()==TattooType.NONE
							?"tattoo"
							:this.getName() + " tattoo")
						+ "</span>"
					: this.getName()+" tattoo")
				+(!this.getEffects().isEmpty()
						? " "+getEnchantmentPostfix(withRarityColor, "b")
						: "");
	}

	public String getEnchantmentPostfix(boolean colored, String tag) {
		if(!this.getEffects().isEmpty()) {
			for(ItemEffect ie : this.getEffects()) {
				if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ENSLAVEMENT) {
					return "of "+(colored?"<"+tag+" style='color:"+TFModifier.CLOTHING_ENSLAVEMENT.getColor().toWebHexString()+";'>enslavement</"+tag+">":"enslavement");

				} else if(ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BEHAVIOR || ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BODY_PART) {
					return "of "+(colored?"<"+tag+" style='color:"+Color.FETISH.toWebHexString()+";'>"+ie.getSecondaryModifier().getDescriptor()+"</"+tag+">":ie.getSecondaryModifier().getDescriptor());

				} else if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE) {
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

	public boolean isBadEnchantment() {
		return this.getEffects().stream().anyMatch(e -> e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE && e.getPotency().isNegative());
	}

	public Map<Attribute, Integer> getAttributeModifiers() {
		attributeModifiers.clear();

		for(ItemEffect ie : getEffects()) {
			if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE) {
				if(attributeModifiers.containsKey(ie.getSecondaryModifier().getAssociatedAttribute())) {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), attributeModifiers.get(ie.getSecondaryModifier().getAssociatedAttribute()) + ie.getPotency().getClothingBonusValue());
				} else {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), ie.getPotency().getClothingBonusValue());
				}
			}
		}

		return attributeModifiers;
	}

	public AbstractTattooType getType() {
		return type;
	}

	public List<ItemEffect> getEffects() {
		return effects;
	}

	public void setEffects(List<ItemEffect> effects) {
		this.effects = effects;
	}

	public void addEffect(ItemEffect effect) {
		effects.add(effect);
	}

	public Color getPrimaryColor() {
		return primaryColor;
	}

	public Color getSecondaryColor() {
		return secondaryColor;
	}

	public Color getTertiaryColor() {
		return tertiaryColor;
	}

	public boolean isGlowing() {
		return glowing;
	}

	public TattooWriting getWriting() {
		return writing;
	}

	/**
	 * For examples.
	 */
	public String getFormattedWritingOutput(String input) {
		return (this.getWriting().getStyles().contains(TattooWritingStyle.BOLD)?"<b>":"")
				+ (this.getWriting().getStyles().contains(TattooWritingStyle.ITALICIZED)?"<i>":"")
				+"<span style='color:"+getWriting().getColor().toWebHexString()+";'>"+(getWriting().isGlow()?UtilText.applyGlow(input, getWriting().getColor()):input)+"</span>"
				+(this.getWriting().getStyles().contains(TattooWritingStyle.BOLD)?"</b>":"")
				+ (this.getWriting().getStyles().contains(TattooWritingStyle.ITALICIZED)?"</i>":"");
	}

	public String getFormattedWritingOutput() {
		return (this.getWriting().getStyles().contains(TattooWritingStyle.BOLD)?"<b>":"")
				+ (this.getWriting().getStyles().contains(TattooWritingStyle.ITALICIZED)?"<i>":"")
				+"<span style='color:"+getWriting().getColor().toWebHexString()+";'>"+(getWriting().isGlow()?UtilText.applyGlow(getWriting().getText(), getWriting().getColor()):getWriting().getText())+"</span>"
				+(this.getWriting().getStyles().contains(TattooWritingStyle.BOLD)?"</b>":"")
				+ (this.getWriting().getStyles().contains(TattooWritingStyle.ITALICIZED)?"</i>":"");
	}

	public TattooCounter getCounter() {
		return counter;
	}

	/**
	 * For examples.
	 */
	public String getFormattedCounterOutput(int input) {
		return "<span style='color:"+getCounter().getColor().toWebHexString()+";'>"+(getCounter().isGlow()?UtilText.applyGlow(getCounter().getCountType().convertInt(input), getCounter().getColor()):getCounter().getCountType().convertInt(input))+"</span>";
	}

	public String getFormattedCounterOutput(GameCharacter equippedToCharacter) {
		String convertedInt = getCounter().getCountType().convertInt(getCounter().getType().getCount(equippedToCharacter));
		return "<span style='color:"+getCounter().getColor().toWebHexString()+";'>"+(getCounter().isGlow()?UtilText.applyGlow(convertedInt, getCounter().getColor()):convertedInt)+"</span>";
	}

	public void setType(AbstractTattooType type) {
		this.type = type;
	}

	public void setPrimaryColor(Color primaryColor) {
		this.primaryColor = primaryColor;
	}

	public void setSecondaryColor(Color secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public void setTertiaryColor(Color tertiaryColor) {
		this.tertiaryColor = tertiaryColor;
	}

	public void setGlowing(boolean glowing) {
		this.glowing = glowing;
	}

	@Override
	public String getDescription() {
		return this.getType().getDescription();
	}

	@Override
	public int getValue() {
		return this.getType().getValue();
	}

	@Override
	public int getEnchantmentLimit() {
		return this.getType().getEnchantmentLimit();
	}

	@Override
	public AbstractItemEffectType getEnchantmentEffect() {
		return ItemEffectType.TATTOO;
	}

	@Override
	public AbstractTattooType getEnchantmentItemType(List<ItemEffect> effects) {
		return this.getType();
	}

	@Override
	public TFEssence getRelatedEssence() {
		return TFEssence.ARCANE;
	}
}
