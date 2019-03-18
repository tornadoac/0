package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public class Akari extends NPC {

	private List<AbstractClothing> specials;

	
	

	
	public Akari() {
		this(false);
	}
	
	public Akari(boolean isImported) {
		super(isImported, new NameTriplet("Akari"), "",
				"Akari owns and operates the Bargain Store in the Arcade. She's bubbly all the time, and rarely seen without a smile.",
				400, Month.JANUARY, 17,
				10, Gender.F_V_B_FEMALE,
				Subspecies.FOX_MORPH, RaceStage.LESSER, new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_AKARIS_SHOP, true);
		
		
		specials = new ArrayList<>();
		dailyReset();
	}
	
	private Map<String, List<AbstractClothing>> getAllClothingListsMap() {
		return Util.newHashMapOfValues(
				
				new Value<>("specials", specials));
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		for(Entry<String, List<AbstractClothing>> entry : getAllClothingListsMap().entrySet()) {
			Element clothingElement = doc.createElement(entry.getKey());
			properties.appendChild(clothingElement);
			for(AbstractClothing c : entry.getValue()) {
				try {
					c.saveAsXML(clothingElement, doc);
				} catch(Exception ex) {
				}
			}
		}
		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(this.getSexCount(Main.game.getPlayer().getId()).getTotalTimesHadSex()==0) {
			this.setVaginaVirgin(true);
		}
		
		for(Entry<String, List<AbstractClothing>> entry : this.getAllClothingListsMap().entrySet()) {
			Element npcSpecificElement = (Element) parentElement.getElementsByTagName(entry.getKey()).item(0);
			if(npcSpecificElement!=null) {
				entry.getValue().clear();
				
				NodeList nodeList = npcSpecificElement.getElementsByTagName("clothing");
				for(int i=0; i < nodeList.getLength(); i++){
					Element e = (Element) nodeList.item(i);
					try {
						entry.getValue().add(AbstractClothing.loadFromXML(e, doc));
					} catch(Exception ex) {
					}
				}
				
			}
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 25);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50);
	
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.LOW)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_CLOTHING_STORE_OWNER);
	
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
		
		// Body:

		// Core:
		this.setHeight(165);
		this.setFemininity(85);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_BLUE));
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, Colour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_FELINE_FUR, Colour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED_LIGHT));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.ONE_SMALL.getValue());
		this.setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// 
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {

		this.unequipAllClothingIntoVoid(true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MINI_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TRAINER_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false), true, this);

	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void dailyReset() {
		clearNonEquippedInventory();

		int requiredRoomForMiscItems = ItemType.getEssences().size()+SpellSchool.values().length+availableSpellBooks.size()+10;
		
		List<AbstractCoreType> types = new ArrayList<>();
		
		for(AbstractWeaponType wt : WeaponType.getAllweapons()) {
			if(wt.getItemTags().contains(ItemTag.SOLD_BY_VICKY)) {
				types.add(wt);
//				for(int i=0; i<1+Util.random.nextInt(3); i++){
//					this.addWeapon(AbstractWeaponType.generateWeapon(wt), false);
//				}
			}
		}
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_VICKY)) {
				types.add(item);
//				this.addItem(AbstractItemType.generateItem(item), false);
			}
		}
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			try {
				if(clothing!=null && clothing.getItemTags().contains(ItemTag.SOLD_BY_VICKY)) {
					types.add(clothing);
//					this.addClothing(AbstractClothingType.generateClothing(clothing, false), false);
				} 
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		Collections.shuffle(types);
		int count=0;
		for(AbstractCoreType type : types) {
			if(type instanceof AbstractWeaponType) {
				for(int i=0; i<1+Util.random.nextInt(3); i++){
					this.addWeapon(AbstractWeaponType.generateWeapon((AbstractWeaponType) type), false);
				}
				
			} else if(type instanceof AbstractItemType) {
				this.addItem(AbstractItemType.generateItem((AbstractItemType) type), false);
				
			} else if(type instanceof AbstractClothingType) {
				this.addClothing(AbstractClothingType.generateClothing((AbstractClothingType) type, false), false);
			}
			count++;
			if(count>=this.getMaximumInventorySpace()-requiredRoomForMiscItems) {
				break;
			}
		}
		
		
		AbstractItem ingredient = AbstractItemType.generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
		TFModifier primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		for(int i=0; i<10;i++) {
			if(ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0, Main.game.getPlayer(), Main.game.getPlayer())!=null) {
				AbstractItem potion = EnchantingUtils.craftItem(ingredient, Util.newArrayListOfValues(new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0)));
				this.addItem(potion, false);
				potion.setName(EnchantingUtils.getPotionName(ingredient, potion.getEffects()));
			}
			
			ingredient = AbstractItemType.generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
			primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		}
		
		for(AbstractItemType itemType : availableSpellBooks) {
			this.addItem(AbstractItemType.generateItem(itemType), false);
		}
		
		for(SpellSchool school : SpellSchool.values()) {
			for(int i=0; i<10+Util.random.nextInt(20); i++) {
				this.addItem(AbstractItemType.generateItem(ItemType.getSpellScrollType(school)), false);
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			for(AbstractItemType itemType : ItemType.getEssences()) {
				for(int i=0;i<20+Util.random.nextInt(11);i++) {
					this.addItem(AbstractItemType.generateItem(itemType), false);
				}
			}
		}
	}

	
	/**
	 * Adds three uncommon clothing items to the list, and one rare item.
	 */
	
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public String getTraderDescription() {
		return "<p>"
					+ "Nyan nervously leafs through her little notebook, before guiding you over to some shelves that stock what you're looking for, "
					+ "[nyan.speech(E-erm, j-just remember, I get new stock in every day! S-so if you don't like what I've got today, y-you can come back again tomorrow! I-if you want to, that is...)]"
				+ "</p>";
	}

	@Override
	public boolean isTrader() {
		return true;
	}
	
	public List<AbstractClothing> getSpecials() {
		Collections.shuffle(specials);
		return specials;
	}


}