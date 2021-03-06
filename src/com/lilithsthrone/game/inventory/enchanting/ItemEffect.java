package com.lilithsthrone.game.inventory.enchanting;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.8
 * @version 0.2.11
 * @author Innoxia
 */
public class ItemEffect implements XMLSaving {

	public static final int SEALED_COST_MINOR_BOOST = 5;
	public static final int SEALED_COST_MINOR_DRAIN = 25;
	public static final int SEALED_COST_DRAIN = 100;
	public static final int SEALED_COST_MAJOR_DRAIN = 500;
	
	private AbstractItemEffectType itemEffectType;
	private TFModifier primaryModifier, secondaryModifier;
	private TFPotency potency;
	private int limit;
	private ItemEffectTimer timer;
	
	public ItemEffect(AbstractItemEffectType itemEffectType) {
		this.itemEffectType = itemEffectType;
		this.primaryModifier = null;
		this.secondaryModifier = null;
		this.potency = null;
		this.limit = 0;
		this.timer = new ItemEffectTimer();
	}
	
	public ItemEffect(AbstractItemEffectType itemEffectType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit) {
		this.itemEffectType = itemEffectType;
		this.primaryModifier = primaryModifier;
		this.secondaryModifier = secondaryModifier;
		this.potency = potency;
		this.limit = limit;
		this.timer = new ItemEffectTimer();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ItemEffect){
			if((((ItemEffect)o).getItemEffectType()==null && itemEffectType==null
					||((ItemEffect)o).getItemEffectType()!=null && ((ItemEffect)o).getItemEffectType().equals(itemEffectType))
				&& ((ItemEffect)o).getPrimaryModifier() == primaryModifier
				&& ((ItemEffect)o).getSecondaryModifier() == secondaryModifier
				&& ((ItemEffect)o).getPotency() == potency
				&& ((ItemEffect)o).getLimit() == limit){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if(itemEffectType!=null) {
			result = 31 * result + itemEffectType.hashCode();
		}
		if(primaryModifier!=null) {
			result = 31 * result + primaryModifier.hashCode();
		}
		if(secondaryModifier!=null) {
			result = 31 * result + secondaryModifier.hashCode();
		}
		if(potency!=null) {
			result = 31 * result + potency.hashCode();
		}
		result = 31 * result + limit;
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element effect = doc.createElement("effect");
		parentElement.appendChild(effect);

		CharacterUtils.addAttribute(doc, effect, "itemEffectType", ItemEffectType.getIdFromItemEffectType(getItemEffectType()));
		CharacterUtils.addAttribute(doc, effect, "primaryModifier", (getPrimaryModifier()==null?"null":getPrimaryModifier().toString()));
		CharacterUtils.addAttribute(doc, effect, "secondaryModifier", (getSecondaryModifier()==null?"null":getSecondaryModifier().toString()));
		CharacterUtils.addAttribute(doc, effect, "potency", (getPotency()==null?"null":getPotency().toString()));
		CharacterUtils.addAttribute(doc, effect, "limit", String.valueOf(getLimit()));
		CharacterUtils.addAttribute(doc, effect, "timer", String.valueOf(getTimer().getSecondsPassed()));
		
		return effect;
	}
	
	public static ItemEffect loadFromXML(Element parentElement, Document doc) {
		String itemEffectType = parentElement.getAttribute("itemEffectType");
		
		if(itemEffectType.equals("RACE_DEMON")) {
			throw new NullPointerException();
		}
		
		switch(itemEffectType) {
			case "ATTRIBUTE_STRENGTH":
			case "ATTRIBUTE_FITNESS":
				itemEffectType = "ATTRIBUTE_PHYSIQUE";
				break;
			case "ATTRIBUTE_INTELLIGENCE":
				itemEffectType = "ATTRIBUTE_ARCANE";
				break;
		}
		switch(parentElement.getAttribute("primaryModifier")) {
			case "DAMAGE_ATTACK":
			case "RESISTANCE_ATTACK":
				return null;
		}
		String secondaryMod = parentElement.getAttribute("secondaryModifier");
		switch(parentElement.getAttribute("secondaryModifier")) {
			case "TF_MOD_FETISH_SEEDER":
				secondaryMod = "TF_MOD_FETISH_IMPREGNATION";
				break;
			case "TF_MOD_FETISH_BROODMOTHER":
				secondaryMod = "TF_MOD_FETISH_PREGNANCY";
				break;
		}
		
		ItemEffect ie;
		try { // Wrap this in a try, as the TFModifier.valueof might fail, due to removing Broodmother/Seeder fetish modifiers in 0.2.7.5.
			TFModifier primary = (parentElement.getAttribute("primaryModifier").equals("null")?null:TFModifier.valueOf(parentElement.getAttribute("primaryModifier")));
			TFModifier secondary = (secondaryMod.equals("null")?null:TFModifier.valueOf(parentElement.getAttribute("secondaryModifier")));
			
			if(secondary!=null && TFModifier.getWeaponMajorAttributeList().contains(secondary)) {
				primary = TFModifier.CLOTHING_MAJOR_ATTRIBUTE;
			}
			
			ie = new ItemEffect(
					ItemEffectType.getItemEffectTypeFromId(itemEffectType),
					primary,
					secondary,
					(parentElement.getAttribute("potency").equals("null")?null:TFPotency.valueOf(parentElement.getAttribute("potency"))),
					Integer.valueOf(parentElement.getAttribute("limit")));
		} catch(Exception ex) {
			System.err.println("Unable to import ItemEffect (" + parentElement.getAttribute("primaryModifier") +
					", " + parentElement.getAttribute("secondaryModifier") + ") from" + doc.getDocumentURI());
			System.err.println(ex);
			return null;
		}
		
		try {
			int time = Integer.valueOf(parentElement.getAttribute("timer"));
			
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.5")) {// I think this was to align effects to all be on the hour:
				int timer = time/60;
				ie.getTimer().setSecondsPassed(((timer*60) + (int)(Main.game.getMinutesPassed()%60)));
				
			} else if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6")) {
				ie.getTimer().setSecondsPassed(time*60);
				
			} else {
				ie.getTimer().setSecondsPassed(time);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return ie;
	}
	
	public String applyEffect(GameCharacter user, GameCharacter target, int secondsPassed) {
		this.timer.incrementSecondsPassed(secondsPassed);
		if(target!=null) {
			if(target.getRace()==Race.DEMON
					&& (getSecondaryModifier()==TFModifier.TF_TYPE_1
							|| getSecondaryModifier()==TFModifier.TF_TYPE_2
							|| getSecondaryModifier()==TFModifier.TF_TYPE_3
							|| getSecondaryModifier()==TFModifier.TF_TYPE_4
							|| getSecondaryModifier()==TFModifier.TF_TYPE_5
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_ARACHNID
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAIL
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAUR
							|| getSecondaryModifier()==TFModifier.REMOVAL)) {
				return UtilText.parse(target,
						"<p style='text-align:center;'>"
							+ "As [npc.nameIsFull] [style.boldDemon([npc.a_race])], the transformation has [style.boldBad(no effect)]!"
						+ "</p>");
			}
		}
		return getItemEffectType().applyEffect(getPrimaryModifier(), getSecondaryModifier(), getPotency(), getLimit(), user, target, this.timer);
	}
	
	public List<String> getEffectsDescription(GameCharacter user, GameCharacter target) {
		return getItemEffectType().getEffectsDescription(getPrimaryModifier(), getSecondaryModifier(), getPotency(), getLimit(), user, target);
	}
	
	public int getCost() {
		int cost = 1;
		if(getPrimaryModifier()!=null) {
			if(getPrimaryModifier()!=TFModifier.NONE) {
				cost+=getPrimaryModifier().getValue();
			}
		}
		if(getSecondaryModifier()!=null) {
			if(getSecondaryModifier()!=TFModifier.NONE) {
				cost+=getSecondaryModifier().getValue();
			}
		}
		if(potency!=null) {
			cost += potency.getValue();
		}
		if(getLimit() != -1) {
			cost+=1;
		}
		
		return cost;
	}
	
	public AbstractItemEffectType getItemEffectType() {
		return itemEffectType;
	}

	public void setItemEffectType(AbstractItemEffectType itemEffectType) {
		this.itemEffectType = itemEffectType;
	}

	public TFModifier getPrimaryModifier() {
		return primaryModifier;
	}

	public void setPrimaryModifier(TFModifier primaryModifier) {
		this.primaryModifier = primaryModifier;
	}

	public TFModifier getSecondaryModifier() {
		return secondaryModifier;
	}

	public void setSecondaryModifier(TFModifier secondaryModifier) {
		this.secondaryModifier = secondaryModifier;
	}

	public TFPotency getPotency() {
		return potency;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public ItemEffectTimer getTimer() {
		return timer;
	}

	public void setTimer(ItemEffectTimer timer) {
		this.timer = timer;
	}
	
}
