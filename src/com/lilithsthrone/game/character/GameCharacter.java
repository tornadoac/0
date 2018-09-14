package com.lilithsthrone.game.character;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.Dildo;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.FootStructure;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.NippleType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.TongueType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodyShape;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Alexa;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.DominionSuccubusAttacker;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyNestsAttacker;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.npc.submission.SubmissionAttacker;
import com.lilithsthrone.game.character.persona.MoralityValue;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryAttributeChange;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingSet;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.game.occupantManagement.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.SlavePermission;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.settings.DifficultyLevel;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.PregnancyDescriptor;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.CachedImage;
import com.lilithsthrone.rendering.ImageCache;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * The class for all the game's characters. I think this is the biggest class in the game.
 * 
 * @since 0.1.0
 * @version 0.2.10
 * @author Innoxia
 */
public abstract class GameCharacter implements XMLSaving {

	/** Calculations description as used in getAttributeValue() */
	public static final String HEALTH_CALCULATION = "10 + 2*level + Physique*2 + Bonus Energy";
	public static final String MANA_CALCULATION = "10 + 2*level + Arcane*2 + Bonus Aura";
	public static final String RESTING_LUST_CALCULATION = "Corruption/2";

	public static final int LEVEL_CAP = 50;
	public static final int MAX_TRAITS = 6;
	
	
	// Core variables:
	protected String id;
	protected NameTriplet nameTriplet;
	protected String surname;
	protected String genericName;
	protected boolean playerKnowsName;
	protected boolean raceConcealed;
	protected String playerPetName = "";
	protected String description;
	protected int level;
	protected LocalDateTime birthday;
	protected int ageAppearanceDifference;
	
	protected Occupation history;
	protected Map<PersonalityTrait, PersonalityWeight> personality;
	protected SexualOrientation sexualOrientation;
	private float obedience;

	private int experience;
	private int perkPoints;

	protected List<Artwork> artworkList;
	private int artworkIndex = -1;
	private String artworkFolderName = "";
	
	
	// Location:
	protected WorldType worldLocation;
	protected WorldType homeWorldLocation;
	protected Vector2i location;
	protected Vector2i homeLocation;
	
	
	// Body:
	protected Body body;
	protected Gender genderIdentity; // What gender this character prefers to be. Used to determine NPC demonic transformations (i.e. a demon who identifies as a female will transform back into a female whenever possible.)
	protected Map<CoverableArea, Set<String>> areasKnownByCharactersMap;
	protected Map<SexAreaOrifice, List<FluidStored>> fluidsStoredMap;
	
	
	// Inventory:
	protected CharacterInventory inventory;
	
	private Map<InventorySlot, Scar> scars;
	private Map<InventorySlot, Tattoo> tattoos;

	
	// Attributes, perks & status effects:
	protected Map<Attribute, Float> attributes;
	protected Map<Attribute, Float> bonusAttributes;
	protected Map<Attribute, Float> potionAttributes;
	protected List<Perk> traits;
	protected Map<Integer, Set<Perk>> perks;
	protected Set<Fetish> fetishes;
	protected Map<Fetish, FetishDesire> fetishDesireMap;
	protected Map<Fetish, Integer> clothingFetishDesireModifiersMap;
	protected List<Fetish> fetishesFromClothing;
	protected Map<Fetish, Integer> fetishExperienceMap;
	protected Map<StatusEffect, Integer> statusEffects;
	protected Map<StatusEffect, String> statusEffectDescriptions;
	
	
	// Relationship stats:
	/** String is character ID*/
	private Map<String, Float> affectionMap;
	
	
	// Pregnancy:
	protected List<String> pregnancyReactions;
	protected long timeProgressedToFinalPregnancyStage;
	protected List<PregnancyPossibility> potentialPartnersAsMother;
	protected List<PregnancyPossibility> potentialPartnersAsFather;
	protected Litter pregnantLitter;
	protected List<Litter> littersBirthed;
	protected List<Litter> littersFathered;
	
	
	// Family:
	protected String motherId;
	protected String fatherId;
	protected LocalDateTime conceptionDate;

	
	// Slavery:
	protected List<String> slavesOwned;
	protected String owner;
	protected DialogueNodeOld enslavementDialogue;
	protected AbstractClothing enslavementClothing;
	
	protected SlaveJob slaveJob;
	protected List<SlaveJobSetting> slaveJobSettings;
	protected Map<SlavePermission, Set<SlavePermissionSetting>> slavePermissionSettings;
	
	protected boolean[] workHours;
	
	//Companion
	private String elementalID;
	private List<String> companions;
	String partyLeader;
	
	private int maxCompanions;
	
	
	// Combat:
	protected Set<SpecialAttack> specialAttacks;
	protected List<Spell> spells;
	protected Set<SpellUpgrade> spellUpgrades;
	protected Map<SpellSchool, Integer> spellUpgradePoints;
	protected float health;
	protected float mana;

	
	// Sex:
	private int totalOrgasmCount;
	private int daysOrgasmCount;
	private int daysOrgasmCountRecord;
	
	
	// Stats:
	// Combat stats:
	private int foughtPlayerCount;
	private int lostCombatCount;
	private int wonCombatCount;
	
	
	// Sex stats:
	private Map<String, SexCount> sexCount; // Character ID to count
	private Map<SexType, Integer> sexCountMap;
	private Map<SexType, Integer> cumCountMap;
	/** Entry Strings are: Character ID who took virginity, virginity loss description.*/
	private Map<SexType, Entry<String, String>> virginityLossMap;
	/** String is partner ID*/
	private Map<String, Map<SexType, Integer>> sexPartnerMap;
	
	// Fluids:
	private float alcoholLevel = 0f;
	private List<Addiction> addictions;
	private Set<FluidType> psychoactiveFluidsIngested;
	
	
	// Misc.:
	protected static List<CharacterChangeEventListener> playerAttributeChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> NPCAttributeChangeEventListeners = new ArrayList<>();

	protected static List<CharacterChangeEventListener> NPCLocationChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> playerLocationChangeEventListeners = new ArrayList<>();

	protected static List<CharacterChangeEventListener> NPCInventoryChangeEventListeners = new ArrayList<>();
	protected static List<CharacterChangeEventListener> playerInventoryChangeEventListeners = new ArrayList<>();

	protected GameCharacter(NameTriplet nameTriplet,
			String description,
			int level,
			LocalDateTime birthday,
			Gender startingGender,
			Subspecies startingSubspecies,
			RaceStage stage,
			CharacterInventory inventory,
			WorldType worldLocation,
			PlaceType startingPlace) {
		
		id = "NOT_SET"; // id gets set in Game's addNPC method, so it doesn't matter if this is unique or not... Right?
		
		surname = "";
		genericName = "";
		playerKnowsName = true;
		raceConcealed = false;
		this.description = description;
		this.level = level;
		
		RacialBody startingRace = RacialBody.valueOfRace(startingSubspecies.getRace());
		
		if(birthday==null) {
			setBirthday(Main.game.getDateNow());
		} else {
			this.setBirthday(birthday);
		}
		ageAppearanceDifference = 0;
		
		this.worldLocation = worldLocation;
		this.homeWorldLocation = worldLocation;
		location = new Vector2i(0, 0);
		
		this.setWorldLocation(worldLocation);
		this.setLocation(Main.game.getWorlds().get(worldLocation).getCell(startingPlace).getLocation());
		homeLocation = location;
		
		// Set up personality:
		personality = new HashMap<>();
		for(Entry<PersonalityTrait, PersonalityWeight> entry : startingRace.getPersonality().entrySet()) {
			double rnd = Math.random();
			if(rnd<0.7) {
				personality.put(entry.getKey(), entry.getValue());
			} else if(rnd<0.95) {
				if((Math.random()<0.5f && entry.getValue().getValue()>PersonalityWeight.LOW.getValue()) || entry.getValue().getValue()==PersonalityWeight.HIGH.getValue()) {
					personality.put(entry.getKey(), PersonalityWeight.getPersonalityWeightFromInt(entry.getValue().getValue()-1));
				} else {
					personality.put(entry.getKey(), PersonalityWeight.getPersonalityWeightFromInt(entry.getValue().getValue()+1));
				}
				
			} else {
				if((Math.random()<0.5f && entry.getValue().getValue()>PersonalityWeight.LOW.getValue()) || entry.getValue().getValue()==PersonalityWeight.HIGH.getValue()) {
					personality.put(entry.getKey(), PersonalityWeight.getPersonalityWeightFromInt(entry.getValue().getValue()-2));
				} else {
					personality.put(entry.getKey(), PersonalityWeight.getPersonalityWeightFromInt(entry.getValue().getValue()+2));
				}
			}
		}
		
		sexualOrientation = startingRace.getSexualOrientation(startingGender);

		affectionMap = new HashMap<>();
		
		obedience = 0;
		
		slavesOwned = new ArrayList<>();
		owner = "";
		enslavementDialogue = null;
		
		slaveJob = SlaveJob.IDLE;
		slaveJobSettings = new ArrayList<>();
		slavePermissionSettings = new HashMap<>();
		for(SlavePermission permission : SlavePermission.values()) {
			slavePermissionSettings.put(permission, new HashSet<>());
			for(SlavePermissionSetting setting : permission.getSettings()) {
				if(setting.isDefaultValue()) {
					slavePermissionSettings.get(permission).add(setting);
				}
			}
		}
		
		workHours = new boolean[24];
		
		motherId = "";
		fatherId = "";
		conceptionDate = this.birthday.minusDays(280);
		
		perkPoints = 0;
		experience = 0;

		if (inventory == null) {
			this.inventory = new CharacterInventory(0);
		} else {
			this.inventory = inventory;
		}
		
		scars = new HashMap<>();
		tattoos = new HashMap<>();
		
		attributes = new EnumMap<>(Attribute.class);
		bonusAttributes = new EnumMap<>(Attribute.class);
		traits = new ArrayList<>();
		perks = new HashMap<>();
		fetishes = new HashSet<>();
		fetishDesireMap = new HashMap<>();
		clothingFetishDesireModifiersMap = new HashMap<>();
		fetishesFromClothing = new ArrayList<>();
		fetishExperienceMap = new HashMap<>();
		statusEffectDescriptions = new EnumMap<>(StatusEffect.class);
		statusEffects = new EnumMap<>(StatusEffect.class);
		
		potionAttributes = new EnumMap<>(Attribute.class);

		specialAttacks = EnumSet.noneOf(SpecialAttack.class);
		spells = new ArrayList<>();
		spellUpgrades = EnumSet.noneOf(SpellUpgrade.class);
		spellUpgradePoints = new HashMap<>();

		totalOrgasmCount = 0;
		daysOrgasmCount = 0;
		daysOrgasmCountRecord = 0;
		
		// Coverable area knowledge:
		areasKnownByCharactersMap = new HashMap<>();
		for(CoverableArea area : CoverableArea.values()) {
			areasKnownByCharactersMap.put(area, new HashSet<>());
		}
		
		fluidsStoredMap = new HashMap<>();
		
		pregnancyReactions = new ArrayList<>();
		
		timeProgressedToFinalPregnancyStage = 1;
		pregnantLitter = null;
		littersBirthed = new ArrayList<>();
		littersFathered = new ArrayList<>();
		potentialPartnersAsMother = new ArrayList<>();
		potentialPartnersAsFather = new ArrayList<>();
		
		// Stats:
		foughtPlayerCount=0;
		lostCombatCount=0;
		wonCombatCount=0;
		
		// Sex Stats:
		sexCount = new HashMap<>();
		
		sexPartnerMap = new HashMap<>();
		
		sexCountMap = new HashMap<>();
		cumCountMap = new HashMap<>();
		virginityLossMap = new HashMap<>();
		
		// Addictions:
		addictions = new ArrayList<>();
		psychoactiveFluidsIngested = new HashSet<>();
		
		// Start all attributes and bonus attributes at 0:
		for (Attribute a : Attribute.values()) {
			attributes.put(a, (float) a.getBaseValue());
			bonusAttributes.put(a, 0f);
		}
		
		setHistory(Occupation.UNEMPLOYED);
		// Set starting attributes based on the character's race
		for (Attribute a : startingRace.getAttributeModifiers().keySet()) {
			attributes.put(a, startingRace.getAttributeModifiers().get(a).getMinimum() + startingRace.getAttributeModifiers().get(a).getRandomVariance());
		}

		// Set the character's starting body based on their gender and race:
		setBody(startingGender, startingSubspecies, stage);
		genderIdentity = startingGender;
		
		if(nameTriplet==null) {
			this.nameTriplet = Name.getRandomTriplet(this.getRace());
		} else {
			this.nameTriplet = nameTriplet;
		}
		
		health = getAttributeValue(Attribute.HEALTH_MAXIMUM);
		mana = getAttributeValue(Attribute.MANA_MAXIMUM);
		setLust(getRestingLust());
		
		//Companion initialization
		elementalID = "";
		companions = new ArrayList<>();
		setMaxCompanions(1);
		
		calculateStatusEffects(0);

		artworkList = new ArrayList<>();
		if (isUnique())
			loadImages();
		
		PerkManager.initialisePerks(this);
	}
	


	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = doc.createElement("character");
		parentElement.appendChild(properties);
		
		// ************** Core information **************//
		
		Element characterCoreInfo = doc.createElement("core");
		Comment comment = doc.createComment("If you want to edit any of these values, just be warned that it might break the game...");
		properties.appendChild(characterCoreInfo);
		
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "id", this.getId());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "pathName", this.getClass().getCanonicalName());
		
		Element name = doc.createElement("name");
		characterCoreInfo.appendChild(name);
		CharacterUtils.addAttribute(doc, name, "nameFeminine", this.getNameTriplet().getFeminine());
		CharacterUtils.addAttribute(doc, name, "nameAndrogynous", this.getNameTriplet().getAndrogynous());
		CharacterUtils.addAttribute(doc, name, "nameMasculine", this.getNameTriplet().getMasculine());
		
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "surname", this.getSurname());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "genericName", this.getGenericName());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "description", this.getDescription());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "playerPetName", playerPetName);
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "playerKnowsName", String.valueOf(this.isPlayerKnowsName()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "raceConcealed", String.valueOf(this.isRaceConcealed()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "level", String.valueOf(this.getTrueLevel()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "ageAppearanceDifference", String.valueOf(this.getAgeAppearanceDifference()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "yearOfBirth", String.valueOf(this.getBirthday().getYear()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "monthOfBirth", this.getBirthMonth().toString());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "dayOfBirth", String.valueOf(this.getDayOfBirth()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "version", Main.VERSION_NUMBER);
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "history", this.getHistory().toString());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "elemental", this.getElementalID());
		
		
//		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "personality", this.getPersonality().toString());
		Element personalityElement = doc.createElement("personality");
		characterCoreInfo.appendChild(personalityElement);
		for(Entry<PersonalityTrait, PersonalityWeight> entry: getPersonality().entrySet()){
			Element element = doc.createElement("personalityEntry");
			personalityElement.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "trait", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, element, "weight", entry.getValue().toString());
		}
		
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "sexualOrientation", this.getSexualOrientation().toString());
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "obedience", String.valueOf(this.getObedienceValue()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "genderIdentity", String.valueOf(this.getGenderIdentity()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "foughtPlayerCount", String.valueOf(this.getFoughtPlayerCount()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "lostCombatCount", String.valueOf(this.getLostCombatCount()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "wonCombatCount", String.valueOf(this.getWonCombatCount()));
		

		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "experience", String.valueOf(this.getExperience()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "perkPoints", String.valueOf(this.getPerkPoints()));

		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "health", String.valueOf(this.getHealth()));
		CharacterUtils.createXMLElementWithValue(doc, characterCoreInfo, "mana", String.valueOf(this.getMana()));
		
		// Knows area map:
		Element areasKnownByCharactersElement = doc.createElement("areasKnownByCharacters");
		characterCoreInfo.appendChild(areasKnownByCharactersElement);
		for(CoverableArea area: CoverableArea.values()){
			if(!this.areasKnownByCharactersMap.get(area).isEmpty()) {
				Element element = doc.createElement("area");
				areasKnownByCharactersElement.appendChild(element);
				CharacterUtils.addAttribute(doc, element, "type", area.toString());
				
				for(String id : areasKnownByCharactersMap.get(area)) {
					Element elementId = doc.createElement("character");
					element.appendChild(elementId);
					CharacterUtils.addAttribute(doc, elementId, "id", id);
				}
			}
		}
		
		characterCoreInfo.getParentNode().insertBefore(comment, characterCoreInfo);
		

		// ************** Location Information **************//
		
		Element locationInformation = doc.createElement("locationInformation");
		properties.appendChild(locationInformation);
		CharacterUtils.createXMLElementWithValue(doc, locationInformation, "worldLocation", this.getWorldLocation().toString());
		CharacterUtils.createXMLElementWithValue(doc, locationInformation, "homeWorldLocation", this.getHomeWorldLocation().toString());
		Element location = doc.createElement("location");
		locationInformation.appendChild(location);
		CharacterUtils.addAttribute(doc, location, "x", String.valueOf(this.getLocation().getX()));
		CharacterUtils.addAttribute(doc, location, "y", String.valueOf(this.getLocation().getY()));
		location = doc.createElement("homeLocation");
		locationInformation.appendChild(location);
		CharacterUtils.addAttribute(doc, location, "x", String.valueOf(this.getHomeLocation().getX()));
		CharacterUtils.addAttribute(doc, location, "y", String.valueOf(this.getHomeLocation().getY()));
		
		

		// ************** Body **************//
		
		Element characterBody = doc.createElement("body");
		properties.appendChild(characterBody);
		
		this.body.saveAsXML(characterBody, doc);
		
		
		
		// ************** Inventory **************//
		
		this.inventory.saveAsXML(properties, doc);
		
		

		// ************** Markings **************//
		
		Element scarsElement = doc.createElement("scars");
		properties.appendChild(scarsElement);
		for(Entry<InventorySlot, Scar> scar : this.scars.entrySet()) {
			Element element = doc.createElement("scarEntry");
			scarsElement.appendChild(element);

			CharacterUtils.addAttribute(doc, element, "slot", scar.getKey().toString());
			scar.getValue().saveAsXML(element, doc);
		}
		
		Element tattooElement = doc.createElement("tattoos");
		properties.appendChild(tattooElement);
		for(Entry<InventorySlot, Tattoo> tattoo : this.tattoos.entrySet()) {
			Element element = doc.createElement("tattooEntry");
			tattooElement.appendChild(element);

			CharacterUtils.addAttribute(doc, element, "slot", tattoo.getKey().toString());
			tattoo.getValue().saveAsXML(element, doc);
		}
		
		// ************** Attributes **************//
		
		// Attributes:
		Element characterCoreAttributes = doc.createElement("attributes");
		properties.appendChild(characterCoreAttributes);
		for(Attribute att : Attribute.values()){
			if(this.getBaseAttributeValue(att) != att.getBaseValue()) {
				Element element = doc.createElement("attribute");
				characterCoreAttributes.appendChild(element);
				
				CharacterUtils.addAttribute(doc, element, "type", att.toString());
				CharacterUtils.addAttribute(doc, element, "value", String.valueOf(this.getBaseAttributeValue(att)));
			}
		}
		
		Element characterPotionAttributes = doc.createElement("potionAttributes");
		properties.appendChild(characterPotionAttributes);
		for(Entry<Attribute, Float> entry : getPotionAttributes().entrySet()){
			Element element = doc.createElement("attribute");
			characterPotionAttributes.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(entry.getValue()));
		}
		
		// Perks:
		
		Element characterEquippedPerks = doc.createElement("traits");
		properties.appendChild(characterEquippedPerks);
		for(Perk p : this.getTraits()){
			Element element = doc.createElement("perk");
			characterEquippedPerks.appendChild(element);
			CharacterUtils.addAttribute(doc, element, "type", p.toString());
		}
		
		Element characterPerks = doc.createElement("perks");
		properties.appendChild(characterPerks);
		for(Entry<Integer, Set<Perk>> p : this.getPerksMap().entrySet()){
			for(Perk perk : p.getValue()) {
				Element element = doc.createElement("perk");
				characterPerks.appendChild(element);
	
				CharacterUtils.addAttribute(doc, element, "row", p.getKey().toString());
				CharacterUtils.addAttribute(doc, element, "type", perk.toString());
			}
		}
		
		// Spells:
		Element characterSpells = doc.createElement("knownSpells");
		properties.appendChild(characterSpells);
		for(Spell spell : this.getSpells()) {
			Element element = doc.createElement("spell");
			characterSpells.appendChild(element);
			CharacterUtils.addAttribute(doc, element, "type", spell.toString());
		}
		
		Element characterSpellUpgrades = doc.createElement("spellUpgrades");
		properties.appendChild(characterSpellUpgrades);
		for(SpellUpgrade upgrade : this.getSpellUpgrades()) {
			Element element = doc.createElement("upgrade");
			characterSpellUpgrades.appendChild(element);
			CharacterUtils.addAttribute(doc, element, "type", upgrade.toString());
		}

		Element characterSpellUpgradePoints = doc.createElement("spellUpgradePoints");
		properties.appendChild(characterSpellUpgradePoints);
		for(SpellSchool school : SpellSchool.values()) {
			Element element = doc.createElement("upgradeEntry");
			characterSpellUpgradePoints.appendChild(element);
			CharacterUtils.addAttribute(doc, element, "school", school.toString());
			CharacterUtils.addAttribute(doc, element, "points", String.valueOf(this.getSpellUpgradePoints(school)));
		}
		
		// Fetishes:
		Element characterFetishes = doc.createElement("fetishes");
		properties.appendChild(characterFetishes);
		for(Fetish f : this.getFetishes()){
			Element element = doc.createElement("fetish");
			characterFetishes.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", f.toString());
		}
		
		Element fetishDesire = doc.createElement("fetishDesire");
		properties.appendChild(fetishDesire);
		for(Entry<Fetish, FetishDesire> entry : this.getFetishDesireMap().entrySet()){
			Element fondenessEntry = doc.createElement("entry");
			fetishDesire.appendChild(fondenessEntry);
			
			CharacterUtils.addAttribute(doc, fondenessEntry, "fetish", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, fondenessEntry, "desire", entry.getValue().toString());
		}
		
		Element fetishExperience = doc.createElement("fetishExperience");
		properties.appendChild(fetishExperience);
		for(Entry<Fetish, Integer> entry : this.getFetishExperienceMap().entrySet()){
			Element expEntry = doc.createElement("entry");
			fetishExperience.appendChild(expEntry);
			
			CharacterUtils.addAttribute(doc, expEntry, "fetish", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, expEntry, "experience", String.valueOf(entry.getValue()));
		}
		
		
		// Status effects:
		Element characterStatusEffects = doc.createElement("statusEffects");
		properties.appendChild(characterStatusEffects);
		for(StatusEffect se : this.getStatusEffects()){
			Element element = doc.createElement("statusEffect");
			characterStatusEffects.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "type", se.toString());
			CharacterUtils.addAttribute(doc, element, "value", String.valueOf(this.getStatusEffectDuration(se)));
		}
		
		
		
		// ************** Relationships **************//
		
		Element characterRelationships = doc.createElement("characterRelationships");
		properties.appendChild(characterRelationships);
		for(Entry<String, Float> entry : this.getAffectionMap().entrySet()){
			Element relationship = doc.createElement("relationship");
			characterRelationships.appendChild(relationship);
			
			CharacterUtils.addAttribute(doc, relationship, "character", entry.getKey());
			CharacterUtils.addAttribute(doc, relationship, "value", String.valueOf(entry.getValue()));
		}
		
		
		
		// ************** Pregnancy **************//
		
		// Pregnancy:
		Element characterPregnancy = doc.createElement("pregnancy");
		properties.appendChild(characterPregnancy);
		CharacterUtils.addAttribute(doc, characterPregnancy, "timeProgressedToFinalPregnancyStage", String.valueOf(this.getTimeProgressedToFinalPregnancyStage()));
		
		Element characterPotentialPartnersAsMother = doc.createElement("potentialPartnersAsMother");
		characterPregnancy.appendChild(characterPotentialPartnersAsMother);
		for(PregnancyPossibility pregPoss : this.getPotentialPartnersAsMother()) {
			pregPoss.saveAsXML(characterPotentialPartnersAsMother, doc);
		}
		
		Element characterPotentialPartnersAsFather = doc.createElement("potentialPartnersAsFather");
		characterPregnancy.appendChild(characterPotentialPartnersAsFather);
		for(PregnancyPossibility pregPoss : this.getPotentialPartnersAsFather()) {
			pregPoss.saveAsXML(characterPotentialPartnersAsFather, doc);
		}
		
		Element characterPregnancyCurrentLitter = doc.createElement("pregnantLitter");
		characterPregnancy.appendChild(characterPregnancyCurrentLitter);
		if (this.getPregnantLitter() != null) {
			this.getPregnantLitter().saveAsXML(characterPregnancyCurrentLitter, doc);
		}
		
		Element characterPregnancyBirthedLitters = doc.createElement("birthedLitters");
		characterPregnancy.appendChild(characterPregnancyBirthedLitters);
		for(Litter litter : this.getLittersBirthed()) {
			litter.saveAsXML(characterPregnancyBirthedLitters, doc);
		}
		
		Element characterPregnancyLittersFathered = doc.createElement("littersFathered");
		characterPregnancy.appendChild(characterPregnancyLittersFathered);
		for(Litter litter : this.getLittersFathered()) {
			litter.saveAsXML(characterPregnancyLittersFathered, doc);
		}
		
		Element pregnancyReactionsElement = doc.createElement("pregnancyReactions");
		characterPregnancy.appendChild(pregnancyReactionsElement);
		for(String value : pregnancyReactions) {
			CharacterUtils.createXMLElementWithValue(doc, pregnancyReactionsElement, "id", value.toString());
		}
		
		
		
		// ************** Family **************//

		Element characterFamily = doc.createElement("family");
		properties.appendChild(characterFamily);
		
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "motherId", this.getMotherId());
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "fatherId", this.getFatherId());
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "yearOfConception", String.valueOf(this.getConceptionDate().getYear()));
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "monthOfConception", this.getConceptionDate().getMonth().toString());
		CharacterUtils.createXMLElementWithValue(doc, characterFamily, "dayOfConception", String.valueOf(this.getConceptionDate().getDayOfMonth()));
		
		
		
		// ************** Slavery **************//

		Element slaveryElement = doc.createElement("slavery");
		properties.appendChild(slaveryElement);
		
		Element slavesOwned = doc.createElement("slavesOwned");
		slaveryElement.appendChild(slavesOwned);
		for(String slave : this.getSlavesOwned()) {
			Element element = doc.createElement("slave");
			slavesOwned.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "id", slave);
		}
		
		CharacterUtils.createXMLElementWithValue(doc, slaveryElement, "owner", this.getOwner()==null?"":this.getOwner().getId());
		CharacterUtils.createXMLElementWithValue(doc, slaveryElement, "slaveJob", this.getSlaveJob().toString());
		
		Element slaveJobSettings = doc.createElement("slaveJobSettings");
		slaveryElement.appendChild(slaveJobSettings);
		for(SlaveJobSetting setting : this.getSlaveJobSettings()) {
			Element element = doc.createElement("setting");
			slaveJobSettings.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "value", setting.toString());
		}
		
		Element slavePermissionSettings = doc.createElement("slavePermissionSettings");
		slaveryElement.appendChild(slavePermissionSettings);
		for(Entry<SlavePermission, Set<SlavePermissionSetting>> entry : this.getSlavePermissionSettings().entrySet()) {
			Element element = doc.createElement("permission");
			slavePermissionSettings.appendChild(element);

			CharacterUtils.addAttribute(doc, element, "type", entry.getKey().toString());
			for(SlavePermissionSetting setting : entry.getValue()) {
				Element settingElement = doc.createElement("setting");
				element.appendChild(settingElement);
				CharacterUtils.addAttribute(doc, settingElement, "value", setting.toString());
			}
		}
		

		Element slaveWorkHours = doc.createElement("slaveWorkHours");
		slaveryElement.appendChild(slaveWorkHours);
		for(int i=0; i<workHours.length; i++) {
			CharacterUtils.addAttribute(doc, slaveWorkHours, "hour"+String.valueOf(i), String.valueOf(workHours[i]));
		}
		
		
		
		// ************** Companions **************//

		Element companonElement = doc.createElement("companions");
		properties.appendChild(companonElement);
		
		Element companionsFollowing = doc.createElement("companionsFollowing");
		companonElement.appendChild(companionsFollowing);
		for(String companion : this.getCompanionsId()) {
			Element element = doc.createElement("companion");
			companionsFollowing.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "id", companion);
		}
		
		CharacterUtils.createXMLElementWithValue(doc, companonElement, "partyLeader", this.getPartyLeader()==null?"":this.getPartyLeader().getId());
		CharacterUtils.createXMLElementWithValue(doc, companonElement, "maxCompanions", String.valueOf(this.getMaxCompanions()));
		
		
		
		// ************** Sex Stats **************//
		
		Element characterSexStats = doc.createElement("sexStats");
		properties.appendChild(characterSexStats);
		
		Element fluidsStoredMapElement = doc.createElement("fluidsStoredMap");
		characterSexStats.appendChild(fluidsStoredMapElement);
		for(Entry<SexAreaOrifice, List<FluidStored>> entry : fluidsStoredMap.entrySet()) {
			Element element = doc.createElement("entry");
			fluidsStoredMapElement.appendChild(element);

			CharacterUtils.addAttribute(doc, element, "orifice", entry.getKey().toString());
			for(FluidStored f : entry.getValue()) {
				f.saveAsXML(element, doc);
			}
		}

		
		CharacterUtils.createXMLElementWithValue(doc, characterSexStats, "daysOrgasmCount", String.valueOf(this.getDaysOrgasmCount()));
		CharacterUtils.createXMLElementWithValue(doc, characterSexStats, "daysOrgasmCountRecord", String.valueOf(this.getDaysOrgasmCountRecord()));
		CharacterUtils.createXMLElementWithValue(doc, characterSexStats, "totalOrgasmCount", String.valueOf(this.getTotalOrgasmCount()));
		
		Element sexPartnerCount = doc.createElement("sexPartnerCount");
		characterSexStats.appendChild(sexPartnerCount);
		for(Entry<String, SexCount> entry : sexCount.entrySet()){
			Element element = doc.createElement("sexPartner");
			sexPartnerCount.appendChild(element);

			CharacterUtils.addAttribute(doc, element, "partner", entry.getKey());
			CharacterUtils.addAttribute(doc, element, "sexConsensualCount", String.valueOf(entry.getValue().getSexConsensualCount()));
			CharacterUtils.addAttribute(doc, element, "sexAsSubCount", String.valueOf(entry.getValue().getSexAsSubCount()));
			CharacterUtils.addAttribute(doc, element, "sexAsDomCount", String.valueOf(entry.getValue().getSexAsDomCount()));
		}
		
		Element characterCumCount = doc.createElement("cumCounts");
		characterSexStats.appendChild(characterCumCount);
		for(SexParticipantType participant : SexParticipantType.values()) {
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					if(this.getCumCount(new SexType(participant, pt, ot)) > 0) {
						Element element = doc.createElement("cumCountGiving");
						characterCumCount.appendChild(element);

						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getCumCount(new SexType(participant, pt, ot))));
					}
					if(this.getCumCount(new SexType(participant, ot, pt)) > 0) {
						Element element = doc.createElement("cumCountReceiving");
						characterCumCount.appendChild(element);

						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getCumCount(new SexType(participant, ot, pt))));
					}
				}
			}
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(this.getCumCount(new SexType(participant, SexAreaPenetration.PENIS, pt)) > 0) {
					Element element = doc.createElement("cumCountGiving");
					characterCumCount.appendChild(element);

					CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
					CharacterUtils.addAttribute(doc, element, "penetrationType", SexAreaPenetration.PENIS.toString());
					CharacterUtils.addAttribute(doc, element, "orificeType", pt.toString());
					CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getCumCount(new SexType(participant, SexAreaPenetration.PENIS, pt))));
				}
				if(this.getCumCount(new SexType(participant, pt, SexAreaPenetration.PENIS)) > 0) {
					Element element = doc.createElement("cumCountReceiving");
					characterCumCount.appendChild(element);

					CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
					CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
					CharacterUtils.addAttribute(doc, element, "orificeType", SexAreaPenetration.PENIS.toString());
					CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getCumCount(new SexType(participant, pt, SexAreaPenetration.PENIS))));
				}
			}
		}

		Element characterSexCount = doc.createElement("sexCounts");
		characterSexStats.appendChild(characterSexCount);
		for(SexParticipantType participant : SexParticipantType.values()) {
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					if(this.getSexCount(new SexType(participant, pt, ot)) > 0) {
						Element element = doc.createElement("sexCountGiving");
						characterSexCount.appendChild(element);

						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getSexCount(new SexType(participant, pt, ot))));
					}
					if(this.getSexCount(new SexType(participant, ot, pt)) > 0) {
						Element element = doc.createElement("sexCountReceiving");
						characterSexCount.appendChild(element);

						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "count", String.valueOf(this.getSexCount(new SexType(participant, ot, pt))));
					}
				}
			}
		}

		Element characterVirginityTakenBy = doc.createElement("virginityTakenBy");
		characterSexStats.appendChild(characterVirginityTakenBy);
		for(SexParticipantType participant : SexParticipantType.values()) {
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					if(this.getVirginityLoss(new SexType(participant, pt, ot))!=null && !this.getVirginityLoss(new SexType(participant, pt, ot)).getKey().isEmpty()) {
						Element element = doc.createElement("penetrationTypeVirginity");
						characterVirginityTakenBy.appendChild(element);
	
						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "takenBy", this.getVirginityLoss(new SexType(participant, pt, ot)).getKey());
						CharacterUtils.addAttribute(doc, element, "takenDescription", this.getVirginityLoss(new SexType(participant, pt, ot)).getValue());
					}
					if(this.getVirginityLoss(new SexType(participant, ot, pt))!=null && !this.getVirginityLoss(new SexType(participant, ot, pt)).getKey().isEmpty()) {
						Element element = doc.createElement("orificeTypeVirginity");
						characterVirginityTakenBy.appendChild(element);
	
						CharacterUtils.addAttribute(doc, element, "participantType", participant.toString());
						CharacterUtils.addAttribute(doc, element, "penetrationType", pt.toString());
						CharacterUtils.addAttribute(doc, element, "orificeType", ot.toString());
						CharacterUtils.addAttribute(doc, element, "takenBy", this.getVirginityLoss(new SexType(participant, ot, pt)).getKey());
						CharacterUtils.addAttribute(doc, element, "takenDescription", this.getVirginityLoss(new SexType(participant, ot, pt)).getValue());
					}
				}
			}
		}
		
		Element sexPartnerMapElement = doc.createElement("sexPartnerMap");
		characterSexStats.appendChild(sexPartnerMapElement);
		for(String s : sexPartnerMap.keySet()) {
			Element element = doc.createElement("id");
			sexPartnerMapElement.appendChild(element);
			CharacterUtils.addAttribute(doc, element, "value", s);
			
			for(Entry<SexType, Integer> entry : sexPartnerMap.get(s).entrySet()) {
				Element entryElement = doc.createElement("entry");
				element.appendChild(entryElement);

				CharacterUtils.addAttribute(doc, entryElement, "participantType", entry.getKey().getAsParticipant().toString());
				CharacterUtils.addAttribute(doc, entryElement, "penetrationType", entry.getKey().getPerformingSexArea().toString());
				CharacterUtils.addAttribute(doc, entryElement, "orificeType", entry.getKey().getTargetedSexArea().toString());
				CharacterUtils.addAttribute(doc, entryElement, "count", String.valueOf(entry.getValue()));
			}
		}
		
		
		
		// ************** Fluids **************//
		
		
		Element characterAddictionsCore = doc.createElement("addictionsCore");
		properties.appendChild(characterAddictionsCore);

		CharacterUtils.addAttribute(doc, characterAddictionsCore, "alcoholLevel", String.valueOf(alcoholLevel));
		
		Element characterAddictions = doc.createElement("addictions");
		characterAddictionsCore.appendChild(characterAddictions);
		for(Addiction add : addictions) {
			add.saveAsXML(characterAddictions, doc);
		}
		
		
		Element psychoactives = doc.createElement("psychoactiveFluids");
		characterAddictionsCore.appendChild(psychoactives);
		for(FluidType ft : this.getPsychoactiveFluidsIngested()) {
			Element element = doc.createElement("fluid");
			psychoactives.appendChild(element);
			CharacterUtils.addAttribute(doc, element, "value", ft.toString());
		}



		// ************** Artwork overrides **************//

		if (hasArtwork()) {
			Element artworkOverride = doc.createElement("artwork");
			properties.appendChild(artworkOverride);

			int index = getArtworkIndex();
			if (index != getDefaultArtworkIndex()) {
				Element artistElement = doc.createElement("overrideArtist");
				artworkOverride.appendChild(artistElement);
				CharacterUtils.addAttribute(doc, artistElement, "index", String.valueOf(index));
			}

			index = getCurrentArtwork().getIndex();
			if (getCurrentArtwork().getIndex() != 0) {
				Element imageElement = doc.createElement("overrideImage");
				artworkOverride.appendChild(imageElement);
				CharacterUtils.addAttribute(doc, imageElement, "index", String.valueOf(index));
			}
		}

		return properties;
	}
	
	public static String getValueFromElementWithTagName(Element parentElement, String tagName) {
		return getValueFromElementWithTagName(parentElement, tagName, null);
	}
	
	public static String getValueFromElementWithTagName(Element parentElement, String tagName, String defaultValue) {
		Element x = (Element) parentElement.getElementsByTagName(tagName).item(0);
		return x != null ? x.getAttribute("value") : defaultValue;
	}
	
	public static void loadGameCharacterVariablesFromXML(GameCharacter character, StringBuilder log, Element parentElement, Document doc, CharacterImportSetting... settings) {

		boolean noPregnancy = Arrays.asList(settings).contains(CharacterImportSetting.NO_PREGNANCY);
		boolean noCompanions = Arrays.asList(settings).contains(CharacterImportSetting.NO_COMPANIONS);
		boolean noElemental = Arrays.asList(settings).contains(CharacterImportSetting.NO_ELEMENTAL);
		boolean noSlavery = Arrays.asList(settings).contains(CharacterImportSetting.CLEAR_SLAVERY);
		
		// ************** Core information **************//
		
		NodeList nodes = parentElement.getElementsByTagName("core");
		Element element = (Element) nodes.item(0);

		String version = getValueFromElementWithTagName(element, "version", "");
		String loadedCharacterId = getValueFromElementWithTagName(element, "id");
		if (loadedCharacterId != null) {
			character.setId(loadedCharacterId);
			CharacterUtils.appendToImportLog(log, "<br/>Set id: " + character.getId());
		}

		// Name:
		Element nameElement = (Element) element.getElementsByTagName("name").item(0);
		String nameElementValue = nameElement.getAttribute("value");
		if (!nameElementValue.isEmpty()) {
			character.setName(new NameTriplet(nameElementValue));
			CharacterUtils.appendToImportLog(log, "<br/>Set name: " + nameElementValue);
		} else {
			character.setName(new NameTriplet(
					nameElement.getAttribute("nameMasculine"),
					nameElement.getAttribute("nameAndrogynous"),
					nameElement.getAttribute("nameFeminine")));
		}
		
		// Surname:
		if(element.getElementsByTagName("surname")!=null && element.getElementsByTagName("surname").getLength()>0) {
			character.setSurname(((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
			CharacterUtils.appendToImportLog(log, "<br/>Set surname: " + ((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
		}

		// Surname:
		if(element.getElementsByTagName("genericName")!=null && element.getElementsByTagName("genericName").getLength()>0) {
			character.setGenericName(((Element)element.getElementsByTagName("genericName").item(0)).getAttribute("value"));
		}
		
		// Level:
		character.setLevel(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "<br/>Set level: " + Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
		
		// Age appearance difference:
		try {
			character.setAgeAppearanceDifference(Integer.valueOf(((Element)element.getElementsByTagName("ageAppearanceDifference").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set ageAppearanceDifference: " + Integer.valueOf(((Element)element.getElementsByTagName("ageAppearanceDifference").item(0)).getAttribute("value")));
		} catch(Exception ex) {
		}
		
		// Birthday:
		try {
			int day = Integer.valueOf(((Element)element.getElementsByTagName("dayOfBirth").item(0)).getAttribute("value"));
			Month month = Month.valueOf(((Element)element.getElementsByTagName("monthOfBirth").item(0)).getAttribute("value"));
			int year = Integer.valueOf(((Element)element.getElementsByTagName("yearOfBirth").item(0)).getAttribute("value"));
			
			character.setBirthday(LocalDateTime.of(year, month, day, 12, 0));
		} catch(Exception ex) {
		}
		
		// Sexual Orientation:
		if(element.getElementsByTagName("sexualOrientation").getLength()!=0) {
			if(!((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value").equals("null")) {
				character.setSexualOrientation(SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "<br/>Set Sexual Orientation: " + SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
			} else {
				character.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				CharacterUtils.appendToImportLog(log, "<br/>Set Sexual Orientation: " + SexualOrientation.AMBIPHILIC);
			}
		}

		if(element.getElementsByTagName("description").getLength()!=0) {
			character.setDescription(((Element)element.getElementsByTagName("description").item(0)).getAttribute("value"));
			CharacterUtils.appendToImportLog(log, "<br/>Set description");
		}
		if(element.getElementsByTagName("playerPetName").getLength()!=0) {
			String petName = ((Element)element.getElementsByTagName("playerPetName").item(0)).getAttribute("value");
			character.setPlayerPetName(petName);
			CharacterUtils.appendToImportLog(log, "<br/>Set playerPetName: "+petName);
		}
		if(element.getElementsByTagName("playerKnowsName").getLength()!=0) {
			character.setPlayerKnowsName(Boolean.valueOf(((Element)element.getElementsByTagName("playerKnowsName").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set playerKnowsName: "+character.isPlayerKnowsName());
		}
		if(element.getElementsByTagName("raceConcealed").getLength()!=0) {
			character.setRaceConcealed(Boolean.valueOf(((Element)element.getElementsByTagName("raceConcealed").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set raceConcealed: "+character.isRaceConcealed());
		}
		if(element.getElementsByTagName("history").getLength()!=0) {
			try {
				String history = ((Element)element.getElementsByTagName("history").item(0)).getAttribute("value");
				
				if(history.equals("OCCUPATION_UNEMPLOYED_NPC")) {
					history = "OCCUPATION_NPC_UNEMPLOYED";
				}
				
				character.setHistory(Occupation.valueOf(history));
				CharacterUtils.appendToImportLog(log, "<br/>Set history: "+character.getHistory());
			} catch(Exception ex) {
				character.setHistory(Occupation.STUDENT);
				CharacterUtils.appendToImportLog(log, "<br/>History import failed. Set history to: "+character.getHistory());
			}
		}
		if(!noElemental && element.getElementsByTagName("elemental").getLength()!=0) {
			character.setElementalID(((Element)element.getElementsByTagName("elemental").item(0)).getAttribute("value"));
		}
		
		if(element.getElementsByTagName("personality").getLength()!=0 && !Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.3.5")) {
			nodes = parentElement.getElementsByTagName("personality");
			Element personalityElement = (Element) nodes.item(0);
			if(personalityElement!=null) {
				NodeList personalityEntries = personalityElement.getElementsByTagName("personalityEntry");
				for(int i=0; i<personalityEntries.getLength(); i++){
					Element e = ((Element)personalityEntries.item(i));
					try {
						character.setPersonalityTrait(PersonalityTrait.valueOf(e.getAttribute("trait")), PersonalityWeight.valueOf(e.getAttribute("weight")));
						CharacterUtils.appendToImportLog(log, "<br/>Added personality: "+e.getAttribute("trait")+" "+e.getAttribute("weight"));
					}catch(IllegalArgumentException ex){
					}
				}
			}
		
		}
		
		if(element.getElementsByTagName("obedience").getLength()!=0) {
			character.setObedienceSilentlyFromSavefile(Float.valueOf(((Element)element.getElementsByTagName("obedience").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set obedience: "+character.getObedience());
		}
		
		boolean setGenderIdentity = false;
		if(element.getElementsByTagName("genderIdentity").getLength()!=0) {
			try {
				if(!((Element)element.getElementsByTagName("genderIdentity").item(0)).getAttribute("value").equals("null")) {
					character.setGenderIdentity(Gender.valueOf(((Element)element.getElementsByTagName("genderIdentity").item(0)).getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "<br/>Set genderIdentity: "+character.getGenderIdentity());
					setGenderIdentity = true;
				}
			} catch (Exception ex) {
			}
		}
		
		if(element.getElementsByTagName("foughtPlayerCount").getLength()!=0) {
			character.setFoughtPlayerCount(Integer.valueOf(((Element)element.getElementsByTagName("foughtPlayerCount").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set foughtPlayerCount: "+character.getFoughtPlayerCount());
		}
		
		if(element.getElementsByTagName("lostCombatCount").getLength()!=0) {
			character.setLostCombatCount(Integer.valueOf(((Element)element.getElementsByTagName("lostCombatCount").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set lostCombatCount: "+character.getLostCombatCount());
		}
		
		if(element.getElementsByTagName("wonCombatCount").getLength()!=0) {
			character.setWonCombatCount(Integer.valueOf(((Element)element.getElementsByTagName("wonCombatCount").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set wonCombatCount: "+character.getWonCombatCount());
		}
		
		int extraLevelUpPoints = 0;
		// If there is a version number, attributes should be working correctly:
		if(element.getElementsByTagName("version").item(0)!=null) {
			nodes = parentElement.getElementsByTagName("attributes");
			Element attElement = (Element) nodes.item(0);
			NodeList attributeList = attElement.getElementsByTagName("attribute");
			for(int i=0; i<attributeList.getLength(); i++){
				Element e = ((Element)attributeList.item(i));
				
				try {
					if(e.getAttribute("type").equals("CORRUPTION")) {
						character.setAttribute(Attribute.MAJOR_CORRUPTION, Float.valueOf(e.getAttribute("value")), false);
						
					} else if(e.getAttribute("type").equals("STRENGTH") || e.getAttribute("type").equals("MAJOR_STRENGTH")) {
						character.setAttribute(Attribute.MAJOR_PHYSIQUE, Float.valueOf(e.getAttribute("value")), false);
						
					} else {
						if(!version.isEmpty() && Main.isVersionOlderThan(version, "0.2.0")) {
							Attribute att = Attribute.valueOf(e.getAttribute("type"));
							switch(att) {
								case DAMAGE_FIRE: case DAMAGE_ICE: case DAMAGE_LUST: case DAMAGE_PHYSICAL: case DAMAGE_POISON: case DAMAGE_SPELLS:
									character.setAttribute(att, Float.valueOf(e.getAttribute("value"))-100, false);
									break;
								default:
									character.setAttribute(att, Float.valueOf(e.getAttribute("value")), false);
									break;
							}
						} else {
							character.setAttribute(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")), false);
						}
					}
					CharacterUtils.appendToImportLog(log, "<br/>Set Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
				}catch(IllegalArgumentException ex){
				}
			}
			
		} else {
			extraLevelUpPoints = (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5);
			CharacterUtils.appendToImportLog(log, "<br/>Old character version. Extra LevelUpPoints set to: "+(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5));
		}
		
		if(element.getElementsByTagName("health").getLength()!=0) {
			character.setHealth(Float.valueOf(((Element)element.getElementsByTagName("health").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set health: "+character.getHealth());
		}
		if(element.getElementsByTagName("mana").getLength()!=0) {
			character.setMana(Float.valueOf(((Element)element.getElementsByTagName("mana").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set mana: "+character.getMana());
		}

		// Knows area map:
		try {
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10")) {
				nodes = parentElement.getElementsByTagName("playerKnowsAreas");
				Element knowsElement = (Element) nodes.item(0);
				if(knowsElement!=null) {
					NodeList knownAreas = knowsElement.getElementsByTagName("area");
					for(int i=0; i<knownAreas.getLength(); i++){
						Element e = ((Element)knownAreas.item(i));

						CoverableArea ca = CoverableArea.valueOf(e.getAttribute("type"));
						try {
							String id = Main.game.getPlayer().getId();
							character.setAreaKnownByCharacter(ca, id, true);
							CharacterUtils.appendToImportLog(log, "<br/>Added character knows area: "+ca+", "+id);
						}catch(IllegalArgumentException ex){
						}
					}
				}
				
			} else {
				nodes = element.getElementsByTagName("areasKnownByCharacters");
				Element knowsElement = (Element) nodes.item(0);
				if(knowsElement!=null) {
					NodeList knownAreas = knowsElement.getElementsByTagName("area");
					for(int i=0; i<knownAreas.getLength(); i++){
						Element e = ((Element)knownAreas.item(i));
						
						CoverableArea ca = CoverableArea.valueOf(e.getAttribute("type"));
						
						NodeList characters = e.getElementsByTagName("character");
						for(int j=0; j<characters.getLength(); j++){
							Element characterIdElement = ((Element)characters.item(j));
							try {
								String id = characterIdElement.getAttribute("id");
								character.setAreaKnownByCharacter(ca, id, true);
								CharacterUtils.appendToImportLog(log, "<br/>Added character knows area: "+ca+", "+id);
							}catch(IllegalArgumentException ex){
							}
						}
					}
				}
			}
		} catch(Exception ex) {
		}
		
		
		nodes = parentElement.getElementsByTagName("playerCore");
		if(nodes.getLength()>0) { // Old version support:
			
			character.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")))-1);
			CharacterUtils.appendToImportLog(log, "<br/>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))-1));
			
			element = (Element) nodes.item(0);
	
			character.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")), false);
			CharacterUtils.appendToImportLog(log, "<br/>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
			
		} else {
			character.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")), false);
			CharacterUtils.appendToImportLog(log, "<br/>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
			
			try {
				character.setPerkPoints(Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "<br/>Set perkPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));
			} catch(Exception ex) {
			}
		}
		
		
		
		// ************** Location Information **************//
		
		nodes = parentElement.getElementsByTagName("locationInformation");
		element = (Element) nodes.item(0);
		if(element!=null) {
			
			WorldType worldType = WorldType.valueOf(((Element)element.getElementsByTagName("worldLocation").item(0)).getAttribute("value"));
			
			if((worldType==WorldType.DOMINION || worldType==WorldType.SUBMISSION || worldType==WorldType.HARPY_NEST) && Main.isVersionOlderThan(version, "0.2.1.5")) {
				PlaceType placeType = PlaceType.DOMINION_BACK_ALLEYS;
				
				if(character.isPlayer()) {
					if(worldType==WorldType.DOMINION) {
						placeType = PlaceType.DOMINION_AUNTS_HOME;
						
					} else if(worldType==WorldType.SUBMISSION) {
						placeType = PlaceType.SUBMISSION_ENTRANCE;
						
					} else {
						placeType = PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST;
					}
					
				} else {
					
					if(character instanceof DominionAlleywayAttacker) {
						placeType = PlaceType.DOMINION_BACK_ALLEYS;
						
					} else if(character instanceof DominionSuccubusAttacker) {
						placeType = PlaceType.DOMINION_DARK_ALLEYS;
						
					} else if(character instanceof Cultist) {
						placeType = PlaceType.DOMINION_STREET;
						
					} else if(character instanceof ReindeerOverseer) {
						placeType = PlaceType.DOMINION_STREET;
						
					} else if(character instanceof SubmissionAttacker) {
						placeType = PlaceType.SUBMISSION_TUNNELS;
						
					} else if(character instanceof HarpyNestsAttacker) {
						placeType = PlaceType.HARPY_NESTS_WALKWAYS;
						
					} else if(character instanceof HarpyBimbo || character instanceof HarpyBimboCompanion) {
						placeType = PlaceType.HARPY_NESTS_HARPY_NEST_YELLOW;
						
					} else if(character instanceof HarpyDominant || character instanceof HarpyDominantCompanion) {
						placeType = PlaceType.HARPY_NESTS_HARPY_NEST_RED;
						
					} else if(character instanceof HarpyNympho || character instanceof HarpyNymphoCompanion) {
						placeType = PlaceType.HARPY_NESTS_HARPY_NEST_PINK;
						
					} else if(character instanceof Scarlett || character instanceof Alexa) {
						placeType = PlaceType.HARPY_NESTS_ALEXAS_NEST;
						
					} else { // Catch if no location found:
						if(worldType==WorldType.DOMINION) {
							placeType = PlaceType.DOMINION_BACK_ALLEYS;
							
						} else if(worldType==WorldType.SUBMISSION) {
							placeType = PlaceType.SUBMISSION_TUNNELS;
							
						} else {
							placeType = PlaceType.HARPY_NESTS_WALKWAYS;
						}
					}
				}
				
//				if(Main.game.getWorlds().get(worldType).getRandomUnoccupiedCell(placeType) == null) {
//					System.out.println(worldType+", "+placeType);
//				}
				
				character.setLocation(
						worldType,
						Main.game.getWorlds().get(worldType).getRandomUnoccupiedCell(placeType).getLocation(),
						!character.isPlayer());
				
				if(character.isPlayer()) {
					Main.game.getWorlds().get(worldType).getCell(character.getLocation()).setDiscovered(true);
					if (character.getLocation().getY() < Main.game.getWorlds().get(worldType).WORLD_HEIGHT - 1) {
						Main.game.getWorlds().get(worldType).getCell(character.getLocation().getX(), character.getLocation().getY() + 1).setDiscovered(true);
					}
					if (character.getLocation().getY() != 0) {
						Main.game.getWorlds().get(worldType).getCell(character.getLocation().getX(), character.getLocation().getY() - 1).setDiscovered(true);
					}
					if (character.getLocation().getX() < Main.game.getWorlds().get(worldType).WORLD_WIDTH - 1) {
						Main.game.getWorlds().get(worldType).getCell(character.getLocation().getX() + 1, character.getLocation().getY()).setDiscovered(true);
					}
					if (character.getLocation().getX() != 0) {
						Main.game.getWorlds().get(worldType).getCell(character.getLocation().getX() - 1, character.getLocation().getY()).setDiscovered(true);	
					}
				}
				
			} else {
				character.setLocation(
						worldType,
						new Vector2i(
								Integer.valueOf(((Element)element.getElementsByTagName("location").item(0)).getAttribute("x")),
								Integer.valueOf(((Element)element.getElementsByTagName("location").item(0)).getAttribute("y"))),
						false);
				character.setHomeLocation(
						WorldType.valueOf(((Element)element.getElementsByTagName("homeWorldLocation").item(0)).getAttribute("value")),
						new Vector2i(
								Integer.valueOf(((Element)element.getElementsByTagName("homeLocation").item(0)).getAttribute("x")),
								Integer.valueOf(((Element)element.getElementsByTagName("homeLocation").item(0)).getAttribute("y"))));
			}
			
		} else {
			character.setLocation(new Vector2i(0, 0));
		}
		
		

		// ************** Body **************//
		
		character.body = Body.loadFromXML(log, (Element) parentElement.getElementsByTagName("body").item(0), doc);
		if(!setGenderIdentity) {
			character.setGenderIdentity(character.getGender());
		}
		
		
		
		// ************** Inventory **************//
		
		character.resetInventory(true);
		
		nodes = parentElement.getElementsByTagName("characterInventory");
		element = (Element) nodes.item(0);
		if(element!=null) {
			character.inventory = CharacterInventory.loadFromXML(element, doc);
			
			for(AbstractClothing clothing : character.getClothingCurrentlyEquipped()) {
				character.applyEquipClothingEffects(clothing);
			}
			
			if(character.getMainWeapon()!=null) {
				for (Entry<Attribute, Integer> e : character.getMainWeapon().getAttributeModifiers().entrySet()) {
					character.incrementBonusAttribute(e.getKey(), e.getValue());
				}
			}
			
			if(character.getOffhandWeapon()!=null) {
				for (Entry<Attribute, Integer> e : character.getOffhandWeapon().getAttributeModifiers().entrySet()) {
					character.incrementBonusAttribute(e.getKey(), e.getValue());
				}
			}
			
		} else {
			CharacterCreation.getDressed(character, false);
		}
		

		
		// ************** Markings **************//
		
		nodes = parentElement.getElementsByTagName("scars");
		Element scarsContainerElement = (Element) nodes.item(0);
		if(scarsContainerElement!=null) {
			NodeList scarEntries = scarsContainerElement.getElementsByTagName("scarEntry");
			for(int i=0; i<scarEntries.getLength(); i++){
				Element e = ((Element)scarEntries.item(i));
				
				character.setScar(InventorySlot.valueOf(e.getAttribute("slot")), Scar.loadFromXML((Element) e.getElementsByTagName("scar").item(0), doc));
			}
		}
		
		nodes = parentElement.getElementsByTagName("tattoos");
		Element tattoosContainerElement = (Element) nodes.item(0);
		if(tattoosContainerElement!=null) {
			NodeList tattooEntries = tattoosContainerElement.getElementsByTagName("tattooEntry");
			for(int i=0; i<tattooEntries.getLength(); i++){
				Element e = ((Element)tattooEntries.item(i));
				Tattoo tattoo = Tattoo.loadFromXML((Element) e.getElementsByTagName("tattoo").item(0), doc);
				
				if(tattoo!=null) {
					character.addTattoo(InventorySlot.valueOf(e.getAttribute("slot")), tattoo);
				}
			}
		}
		
		
		// ************** Attributes **************//
		
		// Core attributes are set in the first section.
		
		// Potion attributes:
		nodes = parentElement.getElementsByTagName("potionAttributes");
		Element attElement = (Element) nodes.item(0);
		if(attElement!=null) {
			NodeList potionAttributesList = attElement.getElementsByTagName("attribute");
			for(int i=0; i<potionAttributesList.getLength(); i++){
				Element e = ((Element)potionAttributesList.item(i));

				try {
					character.addPotionEffect(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "<br/>Set Potion Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		// Perks:
		nodes = parentElement.getElementsByTagName("traits");
		element = (Element) nodes.item(0);
		if(element!=null) {
			NodeList perkElements = element.getElementsByTagName("perk");
			for(int i=0; i<perkElements.getLength(); i++){
				Element e = ((Element)perkElements.item(i));
				Perk p = Perk.valueOf(e.getAttribute("type"));
				if(p.isEquippableTrait()) {
					character.addTrait(p);
				}
				CharacterUtils.appendToImportLog(log, "<br/>Added Equipped Perk: "+Perk.valueOf(e.getAttribute("type")).getName(character));
			}
		}
		
		nodes = parentElement.getElementsByTagName("perks");
		element = (Element) nodes.item(0);
		if(element!=null) {
			if(!version.isEmpty() && Main.isVersionOlderThan(version, "0.2.0.2")) {
				int points = character.getPerkPointsAtLevel(character.getLevel());
				character.setPerkPoints(points);
				character.clearTraits();
				CharacterUtils.appendToImportLog(log, "<br/>Added Perk Points: "+points);
			} else {
				NodeList perkElements = element.getElementsByTagName("perk");
				for(int i=0; i<perkElements.getLength(); i++){
					Element e = ((Element)perkElements.item(i));
					
					String type = e.getAttribute("type");
					type = type.replaceAll("STRENGTH_", "PHYSIQUE_");
					try {
						character.addPerk(Integer.valueOf(e.getAttribute("row")), Perk.valueOf(type));
						CharacterUtils.appendToImportLog(log, "<br/>Added Perk: "+Perk.valueOf(type).getName(character));
					} catch(Exception ex) {
					}
				}
			}
		}
		
		// Spells:
		nodes = parentElement.getElementsByTagName("knownSpells");
		element = (Element) nodes.item(0);
		try {
			NodeList spellElements = element.getElementsByTagName("spell");
			for(int i=0; i<spellElements.getLength(); i++){
				Element e = ((Element)spellElements.item(i));
				character.addSpell(Spell.valueOf(e.getAttribute("type")));
			}
		} catch(Exception ex) {
		}
		
		nodes = parentElement.getElementsByTagName("spellUpgrades");
		element = (Element) nodes.item(0);
		try {
			NodeList upgradeElements = element.getElementsByTagName("upgrade");
			for(int i=0; i<upgradeElements.getLength(); i++){
				Element e = ((Element)upgradeElements.item(i));
				character.addSpellUpgrade(SpellUpgrade.valueOf(e.getAttribute("type")));
			}
		} catch(Exception ex) {
		}
		
		nodes = parentElement.getElementsByTagName("spellUpgradePoints");
		element = (Element) nodes.item(0);
		try {
			NodeList upgradeEntryElements = element.getElementsByTagName("upgradeEntry");
			for(int i=0; i<upgradeEntryElements.getLength(); i++){
				Element e = ((Element)upgradeEntryElements.item(i));
				character.setSpellUpgradePoints(SpellSchool.valueOf(e.getAttribute("school")), Integer.valueOf(e.getAttribute("points")));
			}
		} catch(Exception ex) {
		}
		
		// Fetishes:
		nodes = parentElement.getElementsByTagName("fetishes");
		element = (Element) nodes.item(0);
		if(element!=null) {
			NodeList fetishElements = element.getElementsByTagName("fetish");
			if(fetishElements.item(0)!=null && !((Element)fetishElements.item(0)).getAttribute("value").isEmpty()) {
				for(int i=0; i<fetishElements.getLength(); i++){
					Element e = ((Element)fetishElements.item(i));
					
					try {
						if(e.getAttribute("type").equals("FETISH_NON_CON")) { // Support for old non-con fetish:
							character.incrementEssenceCount(TFEssence.ARCANE, 5, false);
							CharacterUtils.appendToImportLog(log, "<br/>Added refund for old non-con fetish. (+5 arcane essences)");
							
						} else if(Fetish.valueOf(e.getAttribute("type")) != null) {
							if(Boolean.valueOf(e.getAttribute("value"))) {
								character.addFetish(Fetish.valueOf(e.getAttribute("type")));
								CharacterUtils.appendToImportLog(log, "<br/>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(character));
							}
						}
					}catch(IllegalArgumentException ex){
					}
				}
				
			} else {
				for(int i=0; i<fetishElements.getLength(); i++){
					Element e = ((Element)fetishElements.item(i));
					
					try {
						if(e.getAttribute("type").equals("FETISH_NON_CON")) { // Support for old non-con fetish:
							character.incrementEssenceCount(TFEssence.ARCANE, 5, false);
							CharacterUtils.appendToImportLog(log, "<br/>Added refund for old non-con fetish. (+5 arcane essences)");
							
						} else if(Fetish.valueOf(e.getAttribute("type")) != null) {
							character.addFetish(Fetish.valueOf(e.getAttribute("type")));
							CharacterUtils.appendToImportLog(log, "<br/>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(character));
						}
					}catch(IllegalArgumentException ex){
					}
				}
			}
		}

		nodes = parentElement.getElementsByTagName("fetishDesire");
		element = (Element) nodes.item(0);
		if(element!=null) {
			NodeList fetishDesireEntries = element.getElementsByTagName("entry");
			for(int i=0; i<fetishDesireEntries.getLength(); i++){
				Element e = ((Element)fetishDesireEntries.item(i));

				try {
					character.setFetishDesire(Fetish.valueOf(e.getAttribute("fetish")), FetishDesire.valueOf(e.getAttribute("desire")));
					CharacterUtils.appendToImportLog(log, "<br/>Set fetish desire: "+e.getAttribute("fetish") +" , "+ e.getAttribute("desire"));
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		nodes = parentElement.getElementsByTagName("fetishExperience");
		element = (Element) nodes.item(0);
		if(element!=null) {
			NodeList fetishExperienceEntries = element.getElementsByTagName("entry");
			for(int i=0; i<fetishExperienceEntries.getLength(); i++){
				Element e = ((Element)fetishExperienceEntries.item(i));

				try {
					character.setFetishExperience(Fetish.valueOf(e.getAttribute("fetish")), Integer.valueOf(e.getAttribute("experience")));
					CharacterUtils.appendToImportLog(log, "<br/>Set fetish experience: "+e.getAttribute("fetish") +" , "+ e.getAttribute("experience"));
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		
		// Status Effects:
		nodes = parentElement.getElementsByTagName("statusEffects");
		element = (Element) nodes.item(0);
		NodeList statusEffectElements = element.getElementsByTagName("statusEffect");
		for(int i=0; i<statusEffectElements.getLength(); i++){
			Element e = ((Element)statusEffectElements.item(i));
			
			try {
				if(Integer.valueOf(e.getAttribute("value")) != -1) {
					StatusEffect effect = StatusEffect.valueOf(e.getAttribute("type"));
					if(!noPregnancy || (effect!=StatusEffect.PREGNANT_0 && effect!=StatusEffect.PREGNANT_1 && effect!=StatusEffect.PREGNANT_2 && effect!=StatusEffect.PREGNANT_3)) {
						character.addStatusEffect(effect, Integer.valueOf(e.getAttribute("value")));
						CharacterUtils.appendToImportLog(log, "<br/>Added Status Effect: "+StatusEffect.valueOf(e.getAttribute("type")).getName(character)+" ("+Integer.valueOf(e.getAttribute("value"))+" minutes)");
					}
				}
			}catch(IllegalArgumentException ex){
			}
		}
		
		
		
		// ************** Relationships **************//
		
		nodes = parentElement.getElementsByTagName("characterRelationships");
		element = (Element) nodes.item(0);
		if(element!=null) {
			NodeList relationshipElements = element.getElementsByTagName("relationship");
			for(int i=0; i<relationshipElements.getLength(); i++){
				Element e = ((Element)relationshipElements.item(i));
				
				if(!e.getAttribute("character").equals("NOT_SET")) {
					character.setAffection(e.getAttribute("character"), Float.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "<br/>Set Relationship: "+e.getAttribute("character") +" , "+ Float.valueOf(e.getAttribute("value")));
				}
			}
		}
		
		
		
		// ************** Pregnancy **************//
		
		if(!noPregnancy) {
			nodes = parentElement.getElementsByTagName("pregnancy");
			Element pregnancyElement = (Element) nodes.item(0);
			if(pregnancyElement!=null) {
				CharacterUtils.appendToImportLog(log, "<br/><br/>Pregnancies:");
				
				character.setTimeProgressedToFinalPregnancyStage(Integer.valueOf(pregnancyElement.getAttribute("timeProgressedToFinalPregnancyStage")));
				
				nodes = pregnancyElement.getElementsByTagName("potentialPartnersAsMother");
				element = (Element) nodes.item(0);
				if(element!=null) {
					NodeList motherPregPossibilities = element.getElementsByTagName("pregnancyPossibility");
					for(int i=0; i<motherPregPossibilities.getLength(); i++){
						Element e = ((Element)motherPregPossibilities.item(i));
						
						character.getPotentialPartnersAsMother().add(PregnancyPossibility.loadFromXML(e, doc));
						CharacterUtils.appendToImportLog(log, "<br/>Added Pregnancy Possibility as mother.");
					}
				}
				
				nodes = pregnancyElement.getElementsByTagName("potentialPartnersAsFather");
				element = (Element) nodes.item(0);
				if(element!=null) {
					NodeList fatherPregPossibilities = element.getElementsByTagName("pregnancyPossibility");
					for(int i=0; i<fatherPregPossibilities.getLength(); i++){
						Element e = ((Element)fatherPregPossibilities.item(i));
						
						character.getPotentialPartnersAsFather().add(PregnancyPossibility.loadFromXML(e, doc));
						CharacterUtils.appendToImportLog(log, "<br/>Added Pregnancy Possibility as father.");
					}
				}
				
				nodes = pregnancyElement.getElementsByTagName("pregnantLitter");
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("litter").item(0);
				if(element!=null) {
					character.setPregnantLitter(Litter.loadFromXML(element, doc));
					CharacterUtils.appendToImportLog(log, "<br/>Added Pregnant litter.");
				}
				
				nodes = pregnancyElement.getElementsByTagName("birthedLitters");
				element = (Element) nodes.item(0);
				if(element!=null) {
					NodeList litterElements = element.getElementsByTagName("litter");
					for(int i=0; i<litterElements.getLength(); i++){
						Element e = ((Element)litterElements.item(i));
						
						character.getLittersBirthed().add(Litter.loadFromXML(e, doc));
						CharacterUtils.appendToImportLog(log, "<br/>Added litter birthed.");
					}
				}
				
				nodes = pregnancyElement.getElementsByTagName("littersFathered");
				element = (Element) nodes.item(0);
				if(element!=null) {
					NodeList litterElements = element.getElementsByTagName("litter");
					for(int i=0; i<litterElements.getLength(); i++){
						Element e = ((Element)litterElements.item(i));
						
						character.getLittersFathered().add(Litter.loadFromXML(e, doc));
						CharacterUtils.appendToImportLog(log, "<br/>Added litter fathered.");
					}
				}
				
				try {
					nodes = ((Element) pregnancyElement.getElementsByTagName("pregnancyReactions").item(0)).getElementsByTagName("id");
					for(int i = 0; i < nodes.getLength(); i++){
						Element e = (Element) nodes.item(i);
						character.pregnancyReactions.add(e.getAttribute("value"));
					}
				} catch(Exception ex) {
				}
			}
		}
		
		
		// ************** Family **************//
		
		nodes = parentElement.getElementsByTagName("family");
		Element familyElement = (Element) nodes.item(0);
		if(familyElement!=null) {
			character.setMother(((Element)familyElement.getElementsByTagName("motherId").item(0)).getAttribute("value"));
			character.setFather(((Element)familyElement.getElementsByTagName("fatherId").item(0)).getAttribute("value"));

			try {
				int day = Integer.valueOf(((Element)familyElement.getElementsByTagName("dayOfConception").item(0)).getAttribute("value"));
				Month month = Month.valueOf(((Element)familyElement.getElementsByTagName("monthOfConception").item(0)).getAttribute("value"));
				int year = Integer.valueOf(((Element)familyElement.getElementsByTagName("yearOfConception").item(0)).getAttribute("value"));
				
				character.setConceptionDate(LocalDateTime.of(year, month, day, 12, 0));
				
			} catch(Exception ex) {
			}
		}
		
		
		
		// ************** Slavery **************//
		
		if(!noSlavery) {
			nodes = parentElement.getElementsByTagName("slavery");
			Element slaveryElement = (Element) nodes.item(0);
			if(slaveryElement!=null) {
				
				for(int i=0; i<((Element) slaveryElement.getElementsByTagName("slavesOwned").item(0)).getElementsByTagName("slave").getLength(); i++){
					Element e = ((Element)slaveryElement.getElementsByTagName("slave").item(i));
					
					if(!e.getAttribute("id").equals("NOT_SET")) {
						character.getSlavesOwned().add(e.getAttribute("id"));
						CharacterUtils.appendToImportLog(log, "<br/>Added owned slave: "+e.getAttribute("id"));
					}
				}
				
				
				character.setOwner(((Element)slaveryElement.getElementsByTagName("owner").item(0)).getAttribute("value"));
				CharacterUtils.appendToImportLog(log, "<br/>Set owner: "+character.getOwnerId());
				
				character.setSlaveJob(SlaveJob.valueOf(((Element)slaveryElement.getElementsByTagName("slaveJob").item(0)).getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "<br/>Set slave job: "+character.getSlaveJob());
				
				NodeList slaveJobSettingElements = ((Element) slaveryElement.getElementsByTagName("slaveJobSettings").item(0)).getElementsByTagName("setting");
				for(int i=0; i<slaveJobSettingElements.getLength(); i++){
					Element e = ((Element)slaveryElement.getElementsByTagName("setting").item(i));
					
					try {
						SlaveJobSetting setting = SlaveJobSetting.valueOf(e.getAttribute("value"));
						character.addSlaveJobSettings(setting);
						CharacterUtils.appendToImportLog(log, "<br/>Added slave job setting: "+setting);
					} catch(Exception ex) {
					}
				}
				
				// Clear settings first:
				for(SlavePermission key : character.getSlavePermissionSettings().keySet()) {
					if(!key.isMutuallyExclusiveSettings()) {
						character.getSlavePermissionSettings().get(key).clear();
					}
				}
				
				for(int i=0; i<((Element) slaveryElement.getElementsByTagName("slavePermissionSettings").item(0)).getElementsByTagName("permission").getLength(); i++){
					Element e = ((Element)slaveryElement.getElementsByTagName("permission").item(i));
					SlavePermission slavePermission =  SlavePermission.valueOf(e.getAttribute("type"));
					
					NodeList settingElements = e.getElementsByTagName("setting");
					for(int j=0; j<settingElements.getLength(); j++){
						Element e2 = ((Element)settingElements.item(j));
						
						SlavePermissionSetting setting = SlavePermissionSetting.valueOf(e2.getAttribute("value"));
						character.addSlavePermissionSetting(slavePermission, setting);
						CharacterUtils.appendToImportLog(log, "<br/>Added slave permission setting: "+slavePermission+", "+setting);
					}
				}
	
				Element workHourElement = ((Element)slaveryElement.getElementsByTagName("slaveWorkHours").item(0));
				for(int i=0; i<character.workHours.length; i++) {
					character.workHours[i] = Boolean.valueOf(workHourElement.getAttribute("hour"+String.valueOf(i)));
					CharacterUtils.appendToImportLog(log, "<br/>Set work hour: "+i+", "+character.workHours[i]);
				}
			}
		}
		
		
		
		// ************** Companions **************//
		
		if(!noCompanions) {
			nodes = parentElement.getElementsByTagName("companions");
			Element companionsElement = (Element) nodes.item(0);
			if(companionsElement!=null) {
				
				for(int i=0; i<((Element) companionsElement.getElementsByTagName("companionsFollowing").item(0)).getElementsByTagName("companion").getLength(); i++){
					Element e = ((Element)companionsElement.getElementsByTagName("companion").item(i));
					
					if(!e.getAttribute("id").equals("NOT_SET")) {
						character.getCompanionsId().add(e.getAttribute("id"));
						CharacterUtils.appendToImportLog(log, "<br/>Added companion: "+e.getAttribute("id"));
					}
				}
				
				character.setPartyLeader(((Element)companionsElement.getElementsByTagName("partyLeader").item(0)).getAttribute("value"));
				CharacterUtils.appendToImportLog(log, "<br/>Set party leader: "+((Element)companionsElement.getElementsByTagName("partyLeader").item(0)).getAttribute("value"));
				
				character.setMaxCompanions(Integer.valueOf(((Element)companionsElement.getElementsByTagName("maxCompanions").item(0)).getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "<br/>Set max companions: "+String.valueOf(character.getMaxCompanions()));
			}
		}
		
		
		
		// ************** Sex Stats **************//
		
		nodes = parentElement.getElementsByTagName("sexStats");
		Element sexStatsElement = (Element) nodes.item(0);
		
		
		
		if(sexStatsElement.getElementsByTagName("daysOrgasmCountRecord").getLength()!=0) {
			character.setDaysOrgasmCountRecord(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("daysOrgasmCountRecord").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set daysOrgasmCountRecord: "+character.getDaysOrgasmCountRecord());
		}
		
		if(sexStatsElement.getElementsByTagName("daysOrgasmCount").getLength()!=0) {
			character.setDaysOrgasmCount(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("daysOrgasmCount").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set daysOrgasmCount: "+character.getDaysOrgasmCount());
		}
		
		if(sexStatsElement.getElementsByTagName("totalOrgasmCount").getLength()!=0) {
			character.setTotalOrgasmCount(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("totalOrgasmCount").item(0)).getAttribute("value")));
			CharacterUtils.appendToImportLog(log, "<br/>Set totalOrgasmCount: "+character.getTotalOrgasmCount());
		}
		
//		if(sexStatsElement.getElementsByTagName("sexConsensualCount").getLength()!=0) {
//			character.setSexConsensualCount(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("sexConsensualCount").item(0)).getAttribute("value")));
//			CharacterUtils.appendToImportLog(log, "<br/>Set consensual sex count: "+character.getSexConsensualCount());
//		}
//
//		if(sexStatsElement.getElementsByTagName("sexAsSubCount").getLength()!=0) {
//			character.setSexAsSubCount(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("sexAsSubCount").item(0)).getAttribute("value")));
//			CharacterUtils.appendToImportLog(log, "<br/>Set sub sex count: "+character.getSexAsSubCount());
//		}
//
//		if(sexStatsElement.getElementsByTagName("sexAsDomCount").getLength()!=0) {
//			character.setSexAsDomCount(Integer.valueOf(((Element)sexStatsElement.getElementsByTagName("sexAsDomCount").item(0)).getAttribute("value")));
//			CharacterUtils.appendToImportLog(log, "<br/>Set dom sex count: "+character.getSexAsDomCount());
//		}
		
		// Partner sex count:
		element = (Element) (sexStatsElement).getElementsByTagName("sexPartnerCount").item(0);
		if(element!=null) {
			NodeList sexPartnerElements = element.getElementsByTagName("sexPartner");
			for(int i = 0; i < sexPartnerElements.getLength(); i++){
				Element e = (Element) sexPartnerElements.item(i);
				
				try {
					SexCount count = character.getSexCount(e.getAttribute("partner"));
					count.setSexConsensualCount(Integer.valueOf(e.getAttribute("sexConsensualCount")));
					count.setSexAsSubCount(Integer.valueOf(e.getAttribute("sexAsSubCount")));
					count.setSexAsDomCount(Integer.valueOf(e.getAttribute("sexAsDomCount")));
				}catch(Exception ex){
				}
			}
		}
		
		// Fluids stored:
		element = (Element) (sexStatsElement).getElementsByTagName("fluidsStoredMap").item(0);
		if(element!=null) {
			NodeList fluidStoredMapEntries = element.getElementsByTagName("entry");
			for(int i = 0; i < fluidStoredMapEntries.getLength(); i++){
				Element e = (Element) fluidStoredMapEntries.item(i);
				SexAreaOrifice ot = SexAreaOrifice.valueOf(e.getAttribute("orifice"));
				NodeList fluidStoredEntries = e.getElementsByTagName("fluidStored"); 
				for (int j = 0; j < fluidStoredEntries.getLength(); j++) {
					Element fluidStored = (Element) fluidStoredEntries.item(j);
					character.addFluidStored(ot, FluidStored.loadFromXML(log, fluidStored, doc));
				}
			}
		}
		
		
		// Cum counts:
		element = (Element) (sexStatsElement).getElementsByTagName("cumCounts").item(0);
		NodeList cumCountElements = element.getElementsByTagName("cumCountGiving");
		for(int i = 0; i < cumCountElements.getLength(); i++){
			Element e = (Element) cumCountElements.item(i);
			try {
				SexType sexType;
				try {
					sexType = new SexType(
							SexParticipantType.valueOf(e.getAttribute("participantType")),
							SexAreaPenetration.valueOf(e.getAttribute("penetrationType")),
							SexAreaOrifice.valueOf(e.getAttribute("orificeType")));
				} catch(Exception innerEx) {
					sexType = new SexType(
							SexParticipantType.valueOf(e.getAttribute("participantType")),
							SexAreaPenetration.valueOf(e.getAttribute("penetrationType")),
							SexAreaPenetration.valueOf(e.getAttribute("orificeType")));
				}
				
				int count = Integer.parseInt(e.getAttribute("count"));
				character.setCumCount(sexType, character.getCumCount(sexType) + count);
				CharacterUtils.appendToImportLog(log, "<br/>Added cum count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(Exception ex){
			}
		}
		cumCountElements = element.getElementsByTagName("cumCountReceiving");
		for(int i = 0; i < cumCountElements.getLength(); i++){
			Element e = (Element) cumCountElements.item(i);
			try {
				SexType sexType;
				try {
					sexType = new SexType(
							SexParticipantType.valueOf(e.getAttribute("participantType")),
							SexAreaPenetration.valueOf(e.getAttribute("penetrationType")),
							SexAreaOrifice.valueOf(e.getAttribute("orificeType")));
				} catch(Exception innerEx) {
					sexType = new SexType(
							SexParticipantType.valueOf(e.getAttribute("participantType")),
							SexAreaPenetration.valueOf(e.getAttribute("penetrationType")),
							SexAreaPenetration.valueOf(e.getAttribute("orificeType")));
				}
				
				int count = Integer.parseInt(e.getAttribute("count"));
				character.setCumCount(sexType, character.getCumCount(sexType) + count);
				CharacterUtils.appendToImportLog(log, "<br/>Added cum count:"+e.getAttribute("orificeType")+" "+e.getAttribute("penetrationType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(Exception ex){
			}
		}
		
		// Sex counts:
		element = (Element) (sexStatsElement).getElementsByTagName("sexCounts").item(0);
		NodeList sexCountElements = element.getElementsByTagName("sexCountGiving");
		for(int i = 0; i < sexCountElements.getLength(); i++){
			Element e = (Element) sexCountElements.item(i);
			
			try {
				SexType sexType = new SexType(SexParticipantType.valueOf(e.getAttribute("participantType")), SexAreaPenetration.valueOf(e.getAttribute("penetrationType")), SexAreaOrifice.valueOf(e.getAttribute("orificeType")));
				int count = Integer.parseInt(e.getAttribute("count"));
				character.setSexCount(sexType, character.getSexCount(sexType) + count);
				CharacterUtils.appendToImportLog(log, "<br/>Added sex count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(Exception ex){
			}
		}
		sexCountElements = element.getElementsByTagName("sexCountReceiving");
		for(int i = 0; i < sexCountElements.getLength(); i++){
			Element e = (Element) sexCountElements.item(i);
			
			try {
				SexType sexType = new SexType(SexParticipantType.valueOf(e.getAttribute("participantType")), SexAreaOrifice.valueOf(e.getAttribute("orificeType")), SexAreaPenetration.valueOf(e.getAttribute("penetrationType")));
				int count = Integer.parseInt(e.getAttribute("count"));
				character.setSexCount(sexType, character.getSexCount(sexType) + count);
				CharacterUtils.appendToImportLog(log, "<br/>Added sex count:"+e.getAttribute("orificeType")+" "+e.getAttribute("penetrationType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(Exception ex){
			}
		}
		
		// Virginity losses:
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.1")) {
			element = (Element) (sexStatsElement).getElementsByTagName("virginityTakenBy").item(0);
			NodeList virginityElements = element.getElementsByTagName("virginity");
			for(int i=0; i<virginityElements.getLength(); i++){
				Element e = ((Element)virginityElements.item(i));
				
				try {
					character.setVirginityLoss(
							new SexType(
								SexParticipantType.valueOf(e.getAttribute("participantType")),
								SexAreaPenetration.valueOf(e.getAttribute("penetrationType")),
								SexAreaOrifice.valueOf(e.getAttribute("orificeType"))),
							"",//Main.game.getUniqueNPCId(GenericAndrogynousNPC.class),
							e.getAttribute("takenBy"));
					
					CharacterUtils.appendToImportLog(log, "<br/>Added virginity loss:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" (taken by:"+e.getAttribute("takenBy")+")");
				}catch(Exception ex){
				}
			}
			
		} else {
			element = (Element) (sexStatsElement).getElementsByTagName("virginityTakenBy").item(0);
			NodeList virginityElements = element.getElementsByTagName("penetrationTypeVirginity");
			for(int i=0; i<virginityElements.getLength(); i++){
				Element e = ((Element)virginityElements.item(i));
				
				try {
					character.setVirginityLoss(
							new SexType(
								SexParticipantType.valueOf(e.getAttribute("participantType")),
								SexAreaPenetration.valueOf(e.getAttribute("penetrationType")),
								SexAreaOrifice.valueOf(e.getAttribute("orificeType"))),
							e.getAttribute("takenBy"),
							e.getAttribute("takenDescription"));
					
					CharacterUtils.appendToImportLog(log, "<br/>Added virginity loss:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" (taken by:"+e.getAttribute("takenBy")+")");
				}catch(Exception ex){
				}
			}
			element = (Element) (sexStatsElement).getElementsByTagName("virginityTakenBy").item(0);
			virginityElements = element.getElementsByTagName("orificeTypeVirginity");
			for(int i=0; i<virginityElements.getLength(); i++){
				Element e = ((Element)virginityElements.item(i));
				
				try {
					character.setVirginityLoss(
							new SexType(
								SexParticipantType.valueOf(e.getAttribute("participantType")),
								SexAreaOrifice.valueOf(e.getAttribute("orificeType")),
								SexAreaPenetration.valueOf(e.getAttribute("penetrationType"))),
							e.getAttribute("takenBy"),
							e.getAttribute("takenDescription"));
					
					CharacterUtils.appendToImportLog(log, "<br/>Added virginity loss:"+e.getAttribute("orificeType")+" "+e.getAttribute("penetrationType")+" (taken by:"+e.getAttribute("takenBy")+")");
				}catch(Exception ex){
				}
			}
		}
		
		
		element = (Element) (sexStatsElement).getElementsByTagName("sexPartnerMap").item(0);
		if(element!=null) {
			NodeList sexPartnerIds = element.getElementsByTagName("id");
			for(int i = 0; i < sexPartnerIds.getLength(); i++){
				Element e = ((Element)sexPartnerIds.item(i));
				
				if(!e.getAttribute("value").equals("NOT_SET")) { // Don't load in stats from unknown NPCs
					character.sexPartnerMap.put(e.getAttribute("value"), new HashMap<>());
					NodeList sexPartnerEntries = e.getElementsByTagName("entry");
					for(int j = 0; j < sexPartnerEntries.getLength(); j++){
						Element e2 = ((Element)sexPartnerEntries.item(j));
	
						try {
							character.sexPartnerMap.get(e.getAttribute("value")).put(
									new SexType(
											SexParticipantType.valueOf(e.getAttribute("participantType")),
											SexAreaPenetration.valueOf(e2.getAttribute("penetrationType")),
											SexAreaOrifice.valueOf(e2.getAttribute("orificeType"))),
											Integer.valueOf(e2.getAttribute("count")));
							
							CharacterUtils.appendToImportLog(log, "<br/>Added sex tracking: "+e.getAttribute("value")+" "+e2.getAttribute("penetrationType")+"/"+e2.getAttribute("orificeType")+" "+Integer.valueOf(e2.getAttribute("count")));
						}catch(Exception ex){
						}
					}
				}
			}
		}
		
		
		// ************** Addictions **************//

//		Element characterAddictions = doc.createElement("addictions");
//		properties.appendChild(characterAddictions);
//		for(Addiction add : addictions) {
//			add.saveAsXML(characterAddictions, doc);
//		}
//		
//		CharacterUtils.addAttribute(doc, characterAddictions, "alcoholLevel", String.valueOf(alcoholLevel));
//		
//		Element psychoactives = doc.createElement("psychoactiveFluids");
//		properties.appendChild(psychoactives);
//		for(FluidType ft : this.getPsychoactiveFluidsIngested()) {
//			Element element = doc.createElement("fluid");
//			psychoactives.appendChild(element);
//			CharacterUtils.addAttribute(doc, element, "value", ft.toString());
//		}
		
		nodes = parentElement.getElementsByTagName("addictionsCore");
		Element addictionsElement = (Element) nodes.item(0);
		
		if(addictionsElement!=null) {
			if(!addictionsElement.getAttribute("alcoholLevel").isEmpty()) {
				try {
					character.alcoholLevel = (Float.valueOf(addictionsElement.getAttribute("alcoholLevel")));
				} catch(Exception ex) {
				}
			}
			
			element = (Element) addictionsElement.getElementsByTagName("psychoactiveFluids").item(0);
			if(element!=null) {
				NodeList fluidElements = element.getElementsByTagName("fluid");
				for(int i=0; i<fluidElements.getLength(); i++){
					Element e = ((Element)fluidElements.item(i));
					
					character.addPsychoactiveFluidIngested(FluidType.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "<br/>Added psychoactive fluid:"+e.getAttribute("value"));
				}
			}
			
			// Old addiction support:
			element = (Element) addictionsElement.getElementsByTagName("addictionSatisfiedMap").item(0);
			if(element!=null) {
				NodeList addictionElements = element.getElementsByTagName("addiction");
				for(int i=0; i<addictionElements.getLength(); i++){
					Element e = ((Element)addictionElements.item(i));
					
					character.setLastTimeSatisfiedAddiction(FluidType.valueOf(e.getAttribute("type")), Long.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "<br/>Set satisfied time:"+e.getAttribute("type")+" "+e.getAttribute("value"));
				}
			}
			
			// New addiction:
			element = (Element) addictionsElement.getElementsByTagName("addictions").item(0);
			if(element!=null) {
				NodeList addictionElements = element.getElementsByTagName("addiction");
				for(int i=0; i<addictionElements.getLength(); i++){
					try {
						character.addAddiction(Addiction.loadFromXML(log, ((Element)addictionElements.item(i)), doc));
					} catch(Exception ex) {	
					}
				}
			}
		}


		// ************** Artwork **************//

		// Initialize artworks (name and femininity must be set at this point)
		character.loadImages();

		if (character.hasArtwork() && Main.getProperties().hasValue(PropertyValue.artwork)) {
			// Retrieve overrides for artist and image index
			nodes = parentElement.getElementsByTagName("artwork");
			int artistIndex = -1, imageIndex = -1;
			if (nodes.getLength() > 0) {
				Element artworkElement = (Element) nodes.item(0);
				nodes = artworkElement.getElementsByTagName("overrideArtist");
				if (nodes.getLength() > 0) {
					Element artistElement = (Element) nodes.item(0);
					artistIndex = Integer.valueOf(artistElement.getAttribute("index"));
				}

				nodes = artworkElement.getElementsByTagName("overrideImage");
				if (nodes.getLength() > 0) {
					Element artistElement = (Element) nodes.item(0);
					imageIndex = Integer.valueOf(artistElement.getAttribute("index"));
				}
			}

			// Apply override indices
			if (artistIndex > -1)
				character.setArtworkIndex(artistIndex);
			if (imageIndex > -1)
				character.getCurrentArtwork().setIndex(imageIndex);

			// Cache current image
			ImageCache.INSTANCE.requestCache(character.getCurrentArtwork().getCurrentImage());
		}


		// ************** Version Overrides **************//

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10") && !character.isPlayer()) {
			character.setPerkPoints(character.getPerkPointsAtLevel(character.getTrueLevel()));
			PerkManager.initialisePerks(character);

			// All non-unique characters are muggers or prostitutes as of version 0.2.10:
			if(!character.isUnique() && character.getHistory()!=Occupation.NPC_PROSTITUTE) {
				character.setHistory(Occupation.NPC_MUGGER);
			}
		}
	}

	/**
	 * Equivalent to {@link GameCharacter#loadImages(boolean)} without forcing a reload if the folder didn't change.
	 */
	public void loadImages() {
		loadImages(false);
	}

	/**
	 * Load or reload all artworks associated with the character. If the parameter is set to true, a reload will always
	 * happen. Otherwise, nothing will be done if the folder name didn't change.
	 * @param forceReload Always reload, even if the folder name didn't change
	 */
	public void loadImages(boolean forceReload) {
		String folder = getArtworkFolderName();
		
//		System.out.println(folder);
		
		if (folder.equals(artworkFolderName) && !forceReload) {
			// Nothing changed, abort loading
			return;
		} else {
			artworkList.clear();
			artworkFolderName = folder;
		}

		if(!folder.isEmpty()) {
			for(Artist artist : Artwork.allArtists) {
				File f = new File("res/images/characters/" + folder + "/" + artist.getFolderName());
				if(f.exists() && f.isDirectory()) {
					Artwork art = new Artwork(f, artist);
					// Cull empty artwork lists
					if (art.getTotalArtworkCount() > 0) {
						artworkList.add(art);
					}
				}
			}
		}
	}

	/**
	 * Copies a list of files into this character's image directory and forces a reload of the artwork list.
	 * @param imageFiles The list of files to import
	 */
	public void importImages(List<File> imageFiles) {
		try {
			// Copy files to the character's custom image folder
			Path destination = Paths.get("res", "images", "characters", getArtworkFolderName(), "custom");
			Files.createDirectories(destination);
			for (File source : imageFiles) {
				// Copy to temporary file and use atomic move to guarantee that the file is available
				Path tmp = destination.resolve(source.getName() + ".tmp");
				Files.copy(source.toPath(), tmp);
				Files.move(tmp, destination.resolve(source.getName()), StandardCopyOption.ATOMIC_MOVE);
			}

			// Reload the character's images
			loadImages(true);
			Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "[style.colourGood(Images imported)]",
					imageFiles.size() + (imageFiles.size() > 1 ? " images were" : " image was") + " added"), false);
		} catch (IOException e1) {
			e1.printStackTrace();
			Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "[style.colourBad(Image import failed)]",
					"See error.log for details"), false);
		}
	}
	
	public abstract boolean isUnique();
	
	public boolean isRaceConcealed() {
		return raceConcealed;
	}

	public void setRaceConcealed(boolean raceConcealed) {
		this.raceConcealed = raceConcealed;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getMapIcon() {
		if(isRaceConcealed()) {
			return SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown();
		} else {
			return getSubspecies().getSVGString(this);
		}
	}
	public String getHomeMapIcon() {
		if(isRaceConcealed()) {
			return SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown();
		} else {
			return getSubspecies().getSVGStringDesaturated(this);
		}
	}

	
	protected StringBuilder infoScreenSB = new StringBuilder();

	public Artwork getCurrentArtwork() {
		// Index is not set, use default
		if(artworkIndex == -1) {
			artworkIndex = getDefaultArtworkIndex();
		}

		return getArtworkList().get(artworkIndex);
	}

	protected int getDefaultArtworkIndex() {
		// Determine index by artist, default to 0 (if neither preferred nor custom art exists)
		int rv = 0, i = 0;
		for(Artwork art : getArtworkList()) {
			// Always override with custom art
			if(art.getArtist().getName().equals("Custom")) {
				rv = i;
				break;
			}

			// Otherwise, choose the preferred artist
			if(art.getArtist().getFolderName().equals(Main.getProperties().preferredArtist)) {
				rv = i;
			}

			++i;
		}

		return rv;
	}

	public boolean isImageRevealed() {
		return isPlayer() || getCurrentArtwork().isCurrentImageClothed() || getTotalTimesHadSex(Main.game.getPlayer()) > 0;
	}
	
	public String getCharacterInformationScreen() {
		infoScreenSB.setLength(0);

		if (Main.getProperties().hasValue(PropertyValue.artwork)) {
			if (hasArtwork()) {
				Artwork artwork = this.getCurrentArtwork();
				String imageString = "";
				int width = 200;
				int percentageWidth = 33;
				CachedImage image = ImageCache.INSTANCE.getImage(artwork.getCurrentImage());
				if (image != null) {
					imageString = image.getImageString();
					width = image.getWidth();
					percentageWidth = image.getPercentageWidth();
				}

				boolean revealed = isImageRevealed();

				infoScreenSB.append(
						"<div class='full-width-container' style='position:relative; float:right; width:"+percentageWidth+"%; max-width:"+width+"; object-fit:scale-down;'>"
								+ "<div class='full-width-container' style='width:100%; margin:0;'>"
								+ "<img id='CHARACTER_IMAGE' style='"+(revealed ? "" : "-webkit-filter: brightness(0%);")+" width:100%;' src='"+imageString+"'/>"//file:/
								+ "<div class='overlay no-pointer no-highlight' style='text-align:center;'>" // Add overlay div to stop javaFX's insane image drag+drop
								+ (revealed ? "" : "<p style='margin-top:50%; font-weight:bold; color:"+Colour.BASE_GREY.toWebHexString()+";'>Unlocked through sex!</p>")
								+ "</div>"
								+ "<div class='title-button' id='ARTWORK_ADD' style='background:transparent; left:auto; right:28px;'>"+SVGImages.SVG_IMAGE_PROVIDER.getAddIcon()+"</div>"
								+ "<div class='title-button' id='ARTWORK_INFO' style='background:transparent; left:auto; right:4px;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
								+ "</div>"
								+ "<div class='normal-button"+(artwork.getTotalArtworkCount()==1?" disabled":"")+"' id='ARTWORK_PREVIOUS' style='float:left; width:10%; margin:0 10%; padding:0; text-align:center;'>&lt;</div>"
								+ "<div class='full-width-container' style='float:left; width:40%; margin:0; text-align:center;'>"+(artwork.getIndex()+1)+"/"+artwork.getTotalArtworkCount()+"</div>"
								+ "<div class='normal-button"+(artwork.getTotalArtworkCount()==1?" disabled":"")+"' id='ARTWORK_NEXT' style='float:left; width:10%; margin:0 10%; padding:0; text-align:center;'>&gt;</div>"

								+ "<div class='normal-button"+(this.getArtworkList().size()==1?" disabled":"")+"' id='ARTWORK_ARTIST_PREVIOUS' style='float:left; width:10%; margin:0 10%; padding:0; text-align:center;'>&lt;</div>"
								+ "<div class='full-width-container' style='float:left; width:40%; margin:0; text-align:center;'>"+this.getArtworkList().get(artworkIndex).getArtist().getName()+"</div>"
								+ "<div class='normal-button"+(this.getArtworkList().size()==1?" disabled":"")+"' id='ARTWORK_ARTIST_NEXT' style='float:left; width:10%; margin:0 10%; padding:0; text-align:center;'>&gt;</div>"
								+ "</div>");
			} else {
				infoScreenSB.append("<div class='title-button' id='ARTWORK_ADD' style='position:relative; float:right; background:transparent; left:auto; right:4px;'>"+SVGImages.SVG_IMAGE_PROVIDER.getAddIcon()+"</div>");
			}
		}

		infoScreenSB.append("<h4>Background</h4>"
				+ "<p>"
					+ this.getDescription()
				+ "</p>");
		
		if(!this.isRaceConcealed()) {
			if(!this.isPlayer()) {
				infoScreenSB.append("<br/>"
						+ "<h4>Relationships</h4>"
						+ "<p>"
							+ (Main.game.getPlayer().hasCompanion(this)
									?UtilText.parse(this, "[style.boldCompanion(Companion:)]<br/>[npc.Name] is currently following you around as your companion.<br/><br/>")
									:"")
							+ "[style.boldAffection(Affection:)]<br/>"
							+ AffectionLevel.getDescription(this, Main.game.getPlayer(),
									AffectionLevel.getAffectionLevelFromValue(this.getAffection(Main.game.getPlayer())), true));
				
				for(Entry<String, Float> entry : this.getAffectionMap().entrySet()) {
					try {
						GameCharacter target = Main.game.getNPCById(entry.getKey());
						if(!target.isPlayer()) {
							infoScreenSB.append("<br/>" + AffectionLevel.getDescription(this, target, AffectionLevel.getAffectionLevelFromValue(this.getAffection(target)), true));
						}
					} catch (Exception e) {
						infoScreenSB.append("<br/>Unknown (id:"+entry.getKey()+")");
					}
				}
				
				infoScreenSB.append("<br/><br/>"
							+ "[style.boldObedience(Obedience:)]<br/>"
							+ UtilText.parse(this,
									(this.isSlave()
										?"[npc.Name] [style.boldArcane(is a slave)], owned by "+(this.getOwner().isPlayer()?"you!":this.getOwner().getName("a")+".")
										:"[npc.Name] [style.boldGood(is not a slave)]."))
							+ "<br/>"+ObedienceLevel.getDescription(this, ObedienceLevel.getObedienceLevelFromValue(this.getObedienceValue()), true, true));
				
				if(!this.getSlavesOwned().isEmpty()) {
					infoScreenSB.append("<br/><br/>"
							+ "[style.boldArcane(Slaves owned:)]");
					for(String id : this.getSlavesOwned()) {
						try {
							infoScreenSB.append(UtilText.parse(Main.game.getNPCById(id), "<br/>[npc.Name]"));
						} catch (Exception e) {
							infoScreenSB.append("<br/>Unknown (id:"+id+")");
						}
					}
				}
				
			} else {
				infoScreenSB.append("<p>"
						+ "[style.boldObedience(Obedience:)]<br/>"
						+ UtilText.parse(this,
								(this.isSlave()
									?"You [style.boldArcane(are a slave)], owned by "+(this.getOwner().isPlayer()?"you! (How did this happen?!)":this.getOwner().getName("a")+".")
									:"You [style.boldGood(are not a slave)]."))
						+ "<br/>"+ObedienceLevel.getDescription(this, ObedienceLevel.getObedienceLevelFromValue(this.getObedienceValue()), true, true));
			
			}
			
			infoScreenSB.append("<br/>"
					+ "<h4>Personality</h4>"
					+ "<p>");
			for(PersonalityTrait trait : PersonalityTrait.values()) {
				infoScreenSB.append("<b>"+trait.getName()+":</b> <i style='color:"+trait.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(trait.getNameFromWeight(this, this.getPersonality().get(trait)))+"</i><br/>"
						+trait.getDescriptionFromWeight(this, this.getPersonality().get(trait))+"<br/>");
			}
			infoScreenSB.append("</p>");
			
			infoScreenSB.append("</p>"
					+ "<br/>"
						+ "<h4>Appearance</h4>"
					+ "<p>"
						+ this.getBodyDescription()
					+ "</p>"
					+ PhoneDialogue.getBodyStatsPanel(this));
		}
		
		return infoScreenSB.toString();
	}

	public String getArtworkFolderName() {
		// Get folder by class name if unique, character name otherwise
		return this.isUnique() ? this.getClass().getSimpleName() : "generic/" + this.getNameIgnoresPlayerKnowledge();
	}

	public boolean hasArtwork() {
		return !getArtworkList().isEmpty();
	}

	public List<Artwork> getArtworkList() {
		return artworkList;
	}
	
	public int getArtworkIndex() {
		if(artworkIndex >= getArtworkList().size() || artworkIndex < 0) {
			artworkIndex = getDefaultArtworkIndex();
		}
		return artworkIndex;
	}

	public void setArtworkIndex(int artworkIndex) {
		artworkIndex = artworkIndex % getArtworkList().size();
		if(artworkIndex < 0) {
			artworkIndex = getArtworkList().size() + artworkIndex;
		}
		this.artworkIndex = artworkIndex;
	}

	public void incrementArtworkIndex(int increment) {
		setArtworkIndex(this.artworkIndex + increment);
	}

	public String speech(String text) {
		return UtilText.parseSpeech(text, this);
	}
	public String thought(String text) {
		return UtilText.parseThought(text, this);
	}
	public String getName(String determiner) {
		if (Character.isUpperCase(getName().charAt(0)) || determiner.isEmpty()) {
			return getName();
			
		} else {
			return (determiner.equalsIgnoreCase("a") || determiner.equalsIgnoreCase("an")
						?(Character.isUpperCase(determiner.charAt(0))
								?Util.capitaliseSentence(UtilText.generateSingularDeterminer(getName()))
								:UtilText.generateSingularDeterminer(getName()))
						:determiner)
					+ " " + getName();
		}
	}

	public boolean isPlayerKnowsName() {
		return playerKnowsName;
	}

	public void setPlayerKnowsName(boolean playerKnowsName) {
		this.playerKnowsName = playerKnowsName;
	}

	/**
	 * @param area
	 * @param target
	 * @return true if the target knows what this character's area looks like.
	 */
	public boolean isAreaKnownByCharacter(CoverableArea area, GameCharacter target) {
		if(target.equals(this) || Main.game.isConcealedSlotsReveal()) {
			return true;
		}
		return areasKnownByCharactersMap.get(area).contains(target.getId());
	}
	
	public void setAreaKnownByCharacter(CoverableArea area, String targetId, boolean known) {
		if(known) {
			areasKnownByCharactersMap.get(area).add(targetId);
		} else {
			areasKnownByCharactersMap.get(area).remove(targetId);
		}
	}
	
	public void setAreaKnownByCharacter(CoverableArea area, GameCharacter target, boolean known) {
		setAreaKnownByCharacter(area, target.getId(), known);
	}

	/**
	 * Removes all other character's knowledge of this area.
	 */
	public void resetAreaKnownByCharacters(CoverableArea area) {
		areasKnownByCharactersMap.get(area).clear();
	}
	
	/**
	 * @param area
	 * @param target
	 * @return true if this character knows what the target's area looks like.
	 */
	public boolean isKnowsCharacterArea(CoverableArea area, GameCharacter target) {
		if(target.equals(this)) {
			return true;
		}
		return target.isAreaKnownByCharacter(area, this);
	}

	public void setKnowsCharacterArea(CoverableArea area, String targetId, boolean known) {
		try {
			Main.game.getNPCById(targetId).setAreaKnownByCharacter(area, this, known);
		} catch(Exception ex) {
			System.err.println("Error in GameCharacter method: setKnowsCharacterArea() - No character with supplied Id '"+targetId+"' found!");
		}
	}

	public void setKnowsCharacterArea(CoverableArea area, GameCharacter target, boolean known) {
		target.setAreaKnownByCharacter(area, this, known);
	}

	public String getSpeechColour() {
		if(this.isPlayer()) {
			switch(Femininity.valueOf(getFemininityValue())) {
				case ANDROGYNOUS:
					return Colour.ANDROGYNOUS.toWebHexString();
				case FEMININE:
					return Colour.FEMININE.toWebHexString();
				case FEMININE_STRONG:
					return Colour.FEMININE_PLUS.toWebHexString();
				case MASCULINE:
					return Colour.MASCULINE.toWebHexString();
				case MASCULINE_STRONG:
					return Colour.MASCULINE_PLUS.toWebHexString();
			}
		} else {
			switch(Femininity.valueOf(getFemininityValue())) {
				case ANDROGYNOUS:
					return Colour.ANDROGYNOUS_NPC.toWebHexString();
				case FEMININE:
					return Colour.FEMININE_NPC.toWebHexString();
				case FEMININE_STRONG:
					return Colour.FEMININE_PLUS_NPC.toWebHexString();
				case MASCULINE:
					return Colour.MASCULINE_NPC.toWebHexString();
				case MASCULINE_STRONG:
					return Colour.MASCULINE_PLUS_NPC.toWebHexString();
			}
		}
		return null;
	}

	protected void updateAttributeListeners() {
		if (NPCAttributeChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : NPCAttributeChangeEventListeners)
				eventListener.onChange();
	}

	protected void updateLocationListeners() {
		if (NPCLocationChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : NPCLocationChangeEventListeners)
				eventListener.onChange();
	}

	public void updateInventoryListeners() {
		if (NPCInventoryChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : NPCInventoryChangeEventListeners)
				eventListener.onChange();
	}

	public static void addPlayerAttributeChangeEventListener(CharacterChangeEventListener eventListener) {
		playerAttributeChangeEventListeners.add(eventListener);
	}

	public static void addNPCAttributeChangeEventListener(CharacterChangeEventListener eventListener) {
		NPCAttributeChangeEventListeners.add(eventListener);
	}

	public static void addPlayerLocationChangeEventListener(CharacterChangeEventListener eventListener) {
		playerLocationChangeEventListeners.add(eventListener);
	}

	public static void addNPCLocationChangeEventListener(CharacterChangeEventListener eventListener) {
		NPCLocationChangeEventListeners.add(eventListener);
	}

	public static void addPlayerInventoryChangeEventListener(CharacterChangeEventListener eventListener) {
		playerInventoryChangeEventListeners.add(eventListener);
	}

	public static void addNPCInventoryChangeEventListener(CharacterChangeEventListener eventListener) {
		NPCInventoryChangeEventListeners.add(eventListener);
	}


	public String getBodyDescription() {
		return body.getDescription(this);
	}
	
	public void setBody(Gender startingGender, GameCharacter mother, GameCharacter father) {
		body = CharacterUtils.generateBody(startingGender, mother, father);

		postTransformationCalculation();
	}
	
	public void setBody(Gender startingGender, RacialBody startingBodyType, RaceStage stage) {
		body = CharacterUtils.generateBody(startingGender, startingBodyType, stage);
		
		postTransformationCalculation();
	}
	
	public void setBody(Gender startingGender, Subspecies startingSpeciesType, RaceStage stage) {
		body = CharacterUtils.generateBody(startingGender, startingSpeciesType, stage);

		postTransformationCalculation();
	}
	
	/**
	 * Sets this character's body based on the preferences stored in the properties.xml file.
	 * @param gender
	 * @param subspeciesMap
	 */
	public void setBodyFromSubspeciesPreference(Gender gender, Map<Subspecies, Integer> subspeciesMap) {
		double humanChance = 0;
		
		if(Main.getProperties().humanEncountersLevel==1) {
			humanChance = 0.05f;
			
		} else if(Main.getProperties().humanEncountersLevel==2) {
			humanChance = 0.25f;
			
		} else if(Main.getProperties().humanEncountersLevel==3) {
			humanChance = 0.5f;
			
		} else if(Main.getProperties().humanEncountersLevel==4) {
			humanChance = 0.75f;
		}
		
		if(gender.isFeminine()) {
			for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		} else {
			for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		}
		
		int total = 0;
		for(Integer i : subspeciesMap.values()) {
			total += i;
		}
		
		if(subspeciesMap.isEmpty() || total==0 || Math.random()<humanChance) {
			setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
			
		} else {
			Subspecies species = Util.getRandomObjectFromWeightedMap(subspeciesMap);
			
			if(gender.isFeminine()) {
				RaceStage stage = CharacterUtils.getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species), gender, species);
				setBody(gender, species, stage);
				
			} else {
				RaceStage stage = CharacterUtils.getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(species), gender, species);
				setBody(gender, species, stage);
			}

		}
	}
	
	protected void addToSubspeciesMap(int weight, Gender gender, Subspecies subspecies, Map<Subspecies, Integer> map) {
		if(gender.isFeminine()) {
			if(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN
					&& Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue()>0) {
				map.put(subspecies, weight*Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue());
			}
		} else {
			if(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN
					&& Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue()>0) {
				map.put(subspecies, weight*Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue());
			}
		}
	}

	public Gender getGenderIdentity() {
		return genderIdentity;
	}

	public void setGenderIdentity(Gender genderIdentity) {
		this.genderIdentity = genderIdentity;
	}

	public String getName() {
		if(this.isSlave()) {
			if(Main.game.isStarted() && ((this.getOwner() != null && this.getOwner().isPlayer()))) {
				this.setPlayerKnowsName(true);
			}
		}
		if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())) {
			this.setPlayerKnowsName(true);
		}
		if((nameTriplet==null || !playerKnowsName) && !isPlayer()) {
			
			if(this.getGenericName()!=null && !this.getGenericName().isEmpty()) {
				return this.getGenericName();
			}
			
			if(isFeminine()) {
				if(getRace() == Race.WOLF_MORPH && Main.game.isSillyModeEnabled()){
					return "awoo-girl";
				} else if(getSubspecies()==Subspecies.HUMAN){
					return "woman";
				} else{
					return getSubspecies().getSingularFemaleName(this);
				}
				
			} else {
				if(getRace() == Race.WOLF_MORPH && Main.game.isSillyModeEnabled()){
					return "awoo-boy";
				} else if(getSubspecies()==Subspecies.HUMAN){
					return "man";
				} else{
					return getSubspecies().getSingularMaleName(this);
				}
			}
			
		} else {
			return getNameIgnoresPlayerKnowledge();
		}
	}
	
	public void setName(NameTriplet nameTriplet) {
		this.nameTriplet = nameTriplet;
	}
	
	public String getNameIgnoresPlayerKnowledge() {
		if(getFemininityValue()>Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			return nameTriplet.getFeminine();
			
		} else if(getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity()) {
			return nameTriplet.getAndrogynous();
			
		} else {
			return nameTriplet.getMasculine();
		}
	}
	
	public NameTriplet getNameTriplet() {
		return nameTriplet;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * @return The name to be used when the player doesn't know this character's name. Leave blank for generic subspecies name.
	 */
	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getPlayerPetName() {
		if(playerPetName.isEmpty()) {
			if(Main.game.getPlayer()==null) {
				return "";
			}
			return Main.game.getPlayer().getName();
		} else {
			return playerPetName;
		}
	}
	
	public void setPlayerPetName(String playerPetName) {
		this.playerPetName = playerPetName;
	}

	public boolean isPlayer() {
		return false;
	}

	public String getDescription() {
		return UtilText.parse(this, description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
		
		if(this.isPlayer()) {
			if(this.getAgeValue()<=0) {
				this.birthday = (this.getBirthday().minusYears(2-this.getAgeValue())); //still not ideal, button can glitch out when clicking -	
			} else if(this.getAgeValue()>50) {
				this.birthday = (this.getBirthday().plusYears(this.getAgeValue()-50));
			}
		}
	}

	public AgeCategory getAppearsAsAge() {
		return AgeCategory.valueOf(getAppearsAsAgeValue());
	}
	
	public int getAppearsAsAgeValue() {
		return Math.max(5, getAgeValue() + ageAppearanceDifference);
	}

	public AgeCategory getAge() {
		return AgeCategory.valueOf(getAgeValue());
	}
	
	public int getAgeValue() {
		int age = (int) ChronoUnit.YEARS.between(birthday, Main.game.getDateNow());
		
		if(birthday.getYear()>=Main.game.getStartingDate().getYear()) {
			return 5 + age; //watch me 18
		}
		
		return Math.max(5, age); //watch me 1
	}
	
	public int getAgeAppearanceDifference() {
		return ageAppearanceDifference;
	}
	
	public void setAgeAppearanceDifference(int ageAppearanceDifference) {
		this.ageAppearanceDifference = ageAppearanceDifference;
	}
	
	public void incrementAgeAppearanceDifference(int increment) {
		setAgeAppearanceDifference(getAgeAppearanceDifference() + increment);
	}

	public void setAgeAppearanceDifferenceToAppearAsAge(int targetedAge) {
		ageAppearanceDifference = targetedAge - this.getAgeValue();
	}
	
	public Month getBirthMonth() {
		return birthday.getMonth();
	}
	
	public int getDayOfBirth() {
		return birthday.getDayOfMonth();
	}
	
	public Occupation getHistory() {
		if(!this.isPlayer() && this.isSlave()) {
			return Occupation.NPC_SLAVE;
		} else if(this instanceof Elemental) {
			return Occupation.ELEMENTAL;
		}
		return history;
	}

	/**
	 * Only player character gets job attribute bonuses.
	 */
	public void setHistory(Occupation history) {
		// Revert attributes from old History:
		if (this.history != null) {
			if(this.history.getAssociatedPerk()!=null && this.isPlayer()) {
				for (Attribute att : this.history.getAssociatedPerk().getAttributeModifiers().keySet()) {
					incrementBonusAttribute(att, -this.history.getAssociatedPerk().getAttributeModifiers().get(att));
				}
			}
			this.history.revertExtraEffects(this);
		}
		

		// Implement attributes from new History:
		if(history.getAssociatedPerk()!=null && this.isPlayer()) {
			for (Attribute att : history.getAssociatedPerk().getAttributeModifiers().keySet()) {
				incrementBonusAttribute(att, history.getAssociatedPerk().getAttributeModifiers().get(att));
			}
		}
		history.applyExtraEffects(this);
		
		this.history = history;

		updateAttributeListeners();
	}
	
	public Map<PersonalityTrait, PersonalityWeight> getPersonality() {
		return personality;
	}
	
	public void setPersonalityTrait(PersonalityTrait trait, PersonalityWeight weight) {
		getPersonality().put(trait, weight);
	}
	
	public void setPersonality(Map<PersonalityTrait, PersonalityWeight> personality) {
		this.personality = personality;
	}
	
	public boolean isCurious() {
		return personality.get(PersonalityTrait.ADVENTUROUSNESS)==PersonalityWeight.HIGH;
	}
	
	public boolean isCautious() {
		return personality.get(PersonalityTrait.ADVENTUROUSNESS)==PersonalityWeight.LOW;
	}
	
	public boolean isTrusting() {
		return personality.get(PersonalityTrait.AGREEABLENESS)==PersonalityWeight.HIGH;
	}
	
	public boolean isSelfish() {
		return personality.get(PersonalityTrait.AGREEABLENESS)==PersonalityWeight.LOW;
	}
	
	public boolean isVigilant() {
		return personality.get(PersonalityTrait.CONSCIENTIOUSNESS)==PersonalityWeight.HIGH;
	}
	
	public boolean isCareless() {
		return personality.get(PersonalityTrait.CONSCIENTIOUSNESS)==PersonalityWeight.LOW;
	}
	
	public boolean isExtroverted() {
		return personality.get(PersonalityTrait.EXTROVERSION)==PersonalityWeight.HIGH;
	}
	
	public boolean isIntroverted() {
		return personality.get(PersonalityTrait.EXTROVERSION)==PersonalityWeight.LOW;
	}
	
	public boolean isNeurotic() {
		return personality.get(PersonalityTrait.NEUROTICISM)==PersonalityWeight.HIGH;
	}
	
	public boolean isConfident() {
		return personality.get(PersonalityTrait.NEUROTICISM)==PersonalityWeight.LOW;
	}

	public SexualOrientation getSexualOrientation() {
		return sexualOrientation;
	}

	public void setSexualOrientation(SexualOrientation sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}

	public boolean isAttractedTo(GameCharacter character) {
		if(hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) { // If they're vulnerable to arcane storms, they will always be eager during a storm:
			return true;
		}
		
		if((getSexualOrientation()==SexualOrientation.ANDROPHILIC && character.isFeminine())
				|| (getSexualOrientation()==SexualOrientation.GYNEPHILIC && !character.isFeminine())
				) {
			return false;
		}
		
		if(this.isRelatedTo(character)) {
			if (!hasFetish(Fetish.FETISH_INCEST)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isAttractedTo(Gender gender) {
		if((getSexualOrientation()==SexualOrientation.ANDROPHILIC && gender.isFeminine())
				|| (getSexualOrientation()==SexualOrientation.GYNEPHILIC && !gender.isFeminine())
				) {
			return false;
		}
		
		return true;
	}
	
	// Obedience:
	
	public ObedienceLevel getObedience() {
		return ObedienceLevel.getObedienceLevelFromValue(obedience);
	}
	
	public float getObedienceValue() {
		return Math.round(obedience*100)/100f;
	}
	
	public void setObedienceSilentlyFromSavefile(float obedience) {
		this.obedience = Math.max(-100, Math.min(100, obedience));
	}

	public String setObedience(float obedience) {
		return setObedience(obedience, true);
	}
	
	public String setObedience(float obedience, boolean applyJobPerkGains) {
		return incrementObedience(obedience - this.getObedienceValue(), applyJobPerkGains);
	}

	public String incrementObedience(float increment) {
		return incrementObedience(increment, true);
	}
	
	public String incrementObedience(float increment, boolean applyJobPerkGains) {
		boolean teacherPerkGain = false;
		if(applyJobPerkGains && increment>0 && this.isSlave() && this.getOwner().hasTrait(Perk.JOB_TEACHER, true)) {
			increment *= 3;
			teacherPerkGain = true;
		}
		
		this.obedience = Math.max(-100, Math.min(100, obedience+increment));
		
		return UtilText.parse(this,
				"<p style='text-align:center'>"
						+ "[npc.Name] "+(increment>0?"[style.boldGrow(gains)]":"[style.boldShrink(loses)]")+" <b>"+Math.abs(increment)+"</b> [style.boldObedience(obedience)]!<br/>"
						+ "[npc.She] now has <b>"+(obedience>0?"+":"")+obedience+"</b> [style.boldObedience(obedience)].<br/>"
						+ ObedienceLevel.getDescription(this, ObedienceLevel.getObedienceLevelFromValue(obedience), true, false)
					+ "</p>"
					+ (teacherPerkGain
						?"<p style='text-align:center'>"
							+ "<i>Obedience gain was [style.colourExcellent(tripled)], as "+(this.getOwner().isPlayer()?"you have":this.getOwner().getName()+" has ")+" the '"+Perk.JOB_TEACHER.getName(this.getOwner())+"' trait.</i>"
						+ "</p>"
						:""));
	}
	
	public float getHourlyObedienceChange(int hour) {
		if(this.workHours[hour]) {
			if(this.getSlaveJob()==SlaveJob.IDLE) {
				return this.getHomeLocationPlace().getHourlyObedienceChange() * (this.isSlave() && this.getOwner().hasTrait(Perk.JOB_TEACHER, true)?3:1);
			}
			// To get rid of e.g. 2.3999999999999999999999:
			return (Math.round(this.getSlaveJob().getObedienceGain(this)*100)/100f) * (this.isSlave() && this.getOwner().hasTrait(Perk.JOB_TEACHER, true)?3:1);
		}
		
		// To get rid of e.g. 2.3999999999999999999999:
		return (Math.round(this.getHomeLocationPlace().getHourlyObedienceChange()*100)/100f) * (this.isSlave() && this.getOwner().hasTrait(Perk.JOB_TEACHER, true)?3:1);
	}
	
	public float getDailyObedienceChange() {
		float totalObedienceChange = 0;
		
		for (int workHour = 0; workHour < this.getTotalHoursWorked(); workHour++) {
			if(this.getSlaveJob()==SlaveJob.IDLE) {
				totalObedienceChange+=this.getHomeLocationPlace().getHourlyObedienceChange();
			}
			totalObedienceChange+=this.getSlaveJob().getObedienceGain(this);
			
		}
		
		for (int homeHour = 0; homeHour < 24-this.getTotalHoursWorked(); homeHour++) {
			totalObedienceChange+=this.getHomeLocationPlace().getHourlyObedienceChange();
		}
		// To get rid of e.g. 2.3999999999999999999999:
		return (Math.round(totalObedienceChange*100)/100f) * (this.isSlave() && this.getOwner().hasTrait(Perk.JOB_TEACHER, true)?3:1);
	}
	
	public int getSlavesWorkingJob(SlaveJob job) {
		int i=0;
			for(String id : this.getSlavesOwned()) {
				try {
					if(Main.game.getNPCById(id).getSlaveJob()==job) {
						i++;
					}
				} catch (Exception e) {
					System.err.println("Main.game.getNPCById("+id+") returning null in method: getSlavesWorkingJob()");
				}
			}
		return i;
	}
	
	public int getValueAsSlave() {
		int value = 10000;
		switch(this.getSubspecies()) {
			case ANGEL:
				value = 80000;
				break;
			case CAT_MORPH:
			case CAT_MORPH_CARACAL:
			case CAT_MORPH_CHEETAH:
			case CAT_MORPH_LEOPARD:
			case CAT_MORPH_LEOPARD_SNOW:
			case CAT_MORPH_LION:
			case CAT_MORPH_LYNX:
			case CAT_MORPH_TIGER:
				value = 12000;
				break;
			case DOG_MORPH:
			case DOG_MORPH_BORDER_COLLIE:
			case DOG_MORPH_DOBERMANN:
				value = 8000;
				break;
			case COW_MORPH:
				value = 15000;
				break;
			case HORSE_MORPH:
			case HORSE_MORPH_ZEBRA:
			case REINDEER_MORPH:
				value = 18000;
				break;
			case DEMON:
			case ELEMENTAL_AIR:
			case ELEMENTAL_ARCANE:
			case ELEMENTAL_EARTH:
			case ELEMENTAL_FIRE:
			case ELEMENTAL_WATER:
				value = 60000;
				break;
			case IMP:
			case IMP_ALPHA:
				value = 1000;
				break;
			case HARPY:
			case HARPY_BALD_EAGLE:
			case HARPY_RAVEN:
				value = 12000;
				break;
			case HUMAN:
				value = 4000;
				break;
			case SQUIRREL_MORPH:
				value = 6000;
				break;
			case ALLIGATOR_MORPH:
				value = 10000;
				break;
			case WOLF_MORPH:
				value = 10000;
				break;
			case FOX_MORPH:
			case FOX_MORPH_FENNEC:
				value = 8000;
				break;
			case FOX_ASCENDANT:
				value = 15000 * this.getTailCount();
				break;
			case FOX_ASCENDANT_FENNEC:
				value = 15000 * this.getTailCount();
				break;
			case BAT_MORPH:
				value = 10000;
				break;
			case RAT_MORPH:
				value = 6000;
				break;
			case RABBIT_MORPH:
			case RABBIT_MORPH_LOP:
				value = 12000;
				break;
			case SLIME:
				value = 10000;
				break;
		}
		
		value += (getFetishes().size()*50);
		
		value *= (100+(getObedienceValue()/2))/100f;
		
		return value;
	}
	
	public SlaveJob getSlaveJob() {
		return slaveJob;
	}

	public void setSlaveJob(SlaveJob slaveJob) {
		slaveJobSettings.clear();
		this.slaveJob = slaveJob;
		for(SlaveJobSetting jobSetting : slaveJob.getDefaultMutuallyExclusiveSettings()) {
			addSlaveJobSettings(jobSetting);
		}
	}
	
	public boolean addSlaveJobSettings(SlaveJobSetting setting) {
		if(slaveJobSettings.contains(setting)) {
			return false;
		}
		for(List<SlaveJobSetting> exSettingList : getSlaveJob().getMutuallyExclusiveSettings().values()) {
			if(exSettingList.contains(setting)) {
				for(SlaveJobSetting exSetting : exSettingList) {
					removeSlaveJobSettings(exSetting);
				}
			}
		}
		
		return slaveJobSettings.add(setting);
	}
	
	public boolean removeSlaveJobSettings(SlaveJobSetting setting) {
		return slaveJobSettings.remove(setting);
	}
	
	public List<SlaveJobSetting> getSlaveJobSettings() {
		return slaveJobSettings;
	}
	
	public boolean addSlavePermissionSetting(SlavePermission permission, SlavePermissionSetting setting) {
		if(permission.isMutuallyExclusiveSettings()) {
			slavePermissionSettings.get(permission).clear();
		}
		return slavePermissionSettings.get(permission).add(setting);
	}
	
	public boolean removeSlavePermissionSetting(SlavePermission permission, SlavePermissionSetting setting) {
		if(permission.isMutuallyExclusiveSettings()) {
			System.err.println("You cannot remove a setting from a mutually exclusive settings list!");
			return false;
		}
		return slavePermissionSettings.get(permission).remove(setting);
	}
	
	public boolean hasSlavePermissionSetting(SlavePermissionSetting setting) {
		for(SlavePermission permission : SlavePermission.values()) {
			if(slavePermissionSettings.get(permission).contains(setting)) {
				return true;
			}
		}
		return false;
	}
	
	public Map<SlavePermission, Set<SlavePermissionSetting>> getSlavePermissionSettings() {
		return slavePermissionSettings;
	}
	
	public void resetWorkHours() {
		for(int i = 0 ; i<workHours.length ; i++) {
			workHours[i] = false;
		}
	}
	
	public boolean[] getWorkHours() {
		return workHours;
	}
	
	public void setWorkHour(int i, boolean isWorking) {
		workHours[i] = isWorking;
	}
	
	public int getTotalHoursWorked() {
		if(this.getSlaveJob()==SlaveJob.IDLE) {
			return 24;
		}
		int count = 0;
		for(int i = 0 ; i<workHours.length ; i++) {
			if(workHours[i]) {
				count++;
			}
		}
		return count;
	}


	// Affection:
	/**
	 * Do not use this method to alter the map!
	 */
	public Map<String, Float> getAffectionMap() {
		return affectionMap;
	}
	
	public void clearAffectionMap() {
		affectionMap.clear();
	}
	
	public float getAffection(GameCharacter character) {
		affectionMap.putIfAbsent(character.getId(), 0f);
		
		return Math.round(affectionMap.get(character.getId())*100)/100f;
	}
	
	public AffectionLevel getAffectionLevel(GameCharacter character) {
		return AffectionLevel.getAffectionLevelFromValue(getAffection(character));
	}
	
	/**
	 * Sets this character's affection towards the supplied GameCharacter.
	 * 
	 * @param character
	 * @param affection
	 * @return
	 */
	public String setAffection(GameCharacter character, float affection) {
		affectionMap.put(character.getId(), Math.max(-100, Math.min(100, affection)));
		
		if(character.isPlayer()) {
			return UtilText.parse(this,
					"<p style='text-align:center'>"
						+ "[npc.Name] now has <b>"+(affection>0?"+":"")+affection+"</b> [style.boldAffection(affection)] towards you!<br/>"
						+ AffectionLevel.getDescription(this, character, AffectionLevel.getAffectionLevelFromValue(affection), true)
					+ "</p>");
			
		} else {
			return UtilText.parse(character,
					"<p style='text-align:center'>"
						+ getName("The")+" now has <b>"+(affection>0?"+":"")+affection+"</b> [style.boldAffection(affection)] towards [npc.name]!<br/>"
						+ AffectionLevel.getDescription(this, character, AffectionLevel.getAffectionLevelFromValue(affection), true)
					+ "</p>");
		}
	}
	
	public void setAffection(String id, float affection) {
		affectionMap.put(id, Math.max(-100, Math.min(100, affection)));
	}
	
	/**
	 * Increments this character's affection towards the supplied GameCharacter.
	 * 
	 * @param character
	 * @param affectionIncrement
	 * @return
	 */
	public String incrementAffection(GameCharacter character, float affectionIncrement) {
		setAffection(character, getAffection(character) + affectionIncrement);
		
		return UtilText.parse(this, character,
				"<p style='text-align:center'>"
						+ "[npc.Name] "+(affectionIncrement>0?"[style.boldGrow(gains)]":"[style.boldShrink(loses)]")+" <b>"+Math.abs(affectionIncrement)+"</b> [style.boldAffection(affection)] towards "+(character.isPlayer()?"you":"[npc2.name]")+"!"
					+ "</p>");
	}
	
	public String getGiftReaction(AbstractCoreItem gift, boolean applyEffects) {
		return null;
	}
	
	// Slavery:
	
	public DialogueNodeOld getEnslavementDialogue(AbstractClothing enslavementClothing) {
		SlaveDialogue.setEnslavementTarget(this);
		this.enslavementClothing = enslavementClothing;
		return enslavementDialogue;
	}
	
	public void setEnslavementDialogue(DialogueNodeOld enslavementDialogue) {
		this.enslavementDialogue = enslavementDialogue;
	}
	
	public AbstractClothing getEnslavementClothing() {
		return enslavementClothing;
	}

	public void applyEnslavementEffects(GameCharacter enslaver) {
		if(this instanceof NPC) {
			Main.game.setActiveNPC((NPC) this);
			this.setPlayerKnowsName(true);
		}
		this.setAffection(enslaver, -200);
		this.setObedience(-100);
	}
	
	public boolean isAbleToBeEnslaved() {
		return getEnslavementDialogue(enslavementClothing)!=null;// && this.getSubspecies()!=Subspecies.DEMON; TODO
	}
	
	public List<String> getSlavesOwned() {
		return slavesOwned;
	}
	
	public int getNumberOfSlavesIdle() {
		int i=0;
		for(String id : slavesOwned) {
			try {
				if(Main.game.getNPCById(id).getSlaveJob()==SlaveJob.IDLE) {
					i++;
				}
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in method: getNumberOfSlavesIdle()");
			}
		}
		return i;
	}
	
	public int getNumberOfSlavesInAdministration() {
		int i=0;
		for(String id : slavesOwned) {
			try {
				if(Main.game.getNPCById(id).getLocationPlace().getPlaceType() == PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION) {
					i++;
				}
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in method: getNumberOfSlavesInAdministration()");
			}
		}
		return i;
	}
	
	public int getSlaveryTotalDailyIncome() {
		int i=0;
		for(String id : slavesOwned) {
			try {
				i += Main.game.getNPCById(id).getSlaveJob().getFinalDailyIncomeAfterModifiers(Main.game.getNPCById(id));
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in method: getSlaveryTotalDailyIncome()");
			}
		}
		return i;
	}
	
	public int getSlaveryTotalDailyUpkeep() {
		int i=0;
		for(Cell c : OccupantManagementDialogue.getImportantCells()) {
			i += c.getPlace().getUpkeep();
		}
		return i;
	}
	
	public boolean addSlave(NPC slave) {
		boolean added = slavesOwned.add(slave.getId());
		Main.game.getPlayer().removeFriendlyOccupant(slave);
		
		if(added) {
			if(slave.isSlave()) {
				slave.getOwner().removeSlave(slave);
			}
			slave.setOwner(this);
			slave.setPendingClothingDressing(false);
		}
		
		return added;
	}
	
	public boolean removeSlave(GameCharacter slave) {
		boolean removed = slavesOwned.remove(slave.getId());
		
		if(removed) {
			slave.setOwner("");
		}
		
		return removed;
	}
	
	public void removeAllSlaves() {
		for(String id : slavesOwned) {
			if(Main.game.isCharacterExisting(id)) {
				try {
					Main.game.getNPCById(id).setOwner("");
				} catch (Exception e) {
					System.err.println("Main.game.getNPCById("+id+") returning null in method: removeAllSlaves()");
				}
			}
		}
		
		slavesOwned.clear();
	}
	
	public String getOwnerId() {
		return owner;
	}
	
	public GameCharacter getOwner() {
		if(owner==null || owner.isEmpty()) {
			return null;
		}
		try {
			return Main.game.getNPCById(owner);
		} catch (Exception e) {
			System.err.println("Main.game.getNPCById("+owner+") returning null in method: getOwner()");
			return null;
		}
	}

	/**<b>Do not call this method directly! Use the owner's addSlave() and removeSlave() methods!</b>*/
	protected void setOwner(GameCharacter owner) {
		this.owner = owner.getId();
	}

	/**<b>Do not call this method directly! Use the owner's addSlave() and removeSlave() methods!</b>*/
	protected void setOwner(String owner) {
		this.owner = owner;
	}
	
	public boolean isSlave() {
		return !getOwnerId().isEmpty();
	}
	
	// Companions:
	
	/**
	 * <b>Only to be called on character slave import! Don't use this method!!!</b>
	 */
	protected void clearAllCompanionVariables() {
		this.partyLeader="";
		this.getCompanionsId().clear();
		this.elementalID = "";
	}
	
	protected void setElementalID(String elementalID) {
		this.elementalID = elementalID;
	}
	
	private String getElementalID() {
		return elementalID;
	}
	
	public Elemental createElemental() {
		try {
			return (Elemental) Main.game.getNPCById(elementalID);
			
		} catch (Exception e) {
			if(elementalID!=null && !elementalID.isEmpty()) {
				System.err.println("Main.game.getNPCById("+elementalID+") returning null in method: createElemental()");
			}
			Elemental elemental = new Elemental(Gender.F_V_B_FEMALE, this, false);
			try {
				Main.game.addNPC(elemental, false);
			} catch (Exception e2) {
				e.printStackTrace();
			}
			this.elementalID = elemental.getId();
			return elemental;
		}
	}
	
	public Elemental getElemental() {
		try {
			return (Elemental) Main.game.getNPCById(elementalID);
		} catch(Exception e) {
			System.err.println("Main.game.getNPCById("+elementalID+") returning null in method: getElemental()");
			return null;
		}
	}
	
	public boolean isElementalSummoned() {
		return this.getCompanions().contains(this.getElemental());
	}
	
	/**
	 * Adds a companion character, if possible. Removes character from a previous party.<br/>
	 * Should be preceded by a canHaveMoreCompanions() check.
	 * @param npc
	 * @return
	 */
	public boolean addCompanion(GameCharacter character) {
		if(!character.isPlayer() && (getPartyLeader() == null || character instanceof Elemental)) {
			if(character.getPartyLeader() != null) {
				character.getPartyLeader().removeCompanion(character);
			}
			character.setPartyLeader(this.getId());
			character.setLocation(this.getWorldLocation(), this.getLocation(), false);
			return this.companions.add(character.getId());
			
		} else {
			return false;
		}
	}
	
	/**
	 * Removes a companion NPC 
	 */
	public void removeCompanion(GameCharacter character) {
		if(this.companions != null) {
			if(character.isElementalSummoned()) {
				character.removeCompanion(character.getElemental());
			}
			character.setPartyLeader("");
			this.companions.remove(character.getId());
		}
	}
	
	/**
	 * Returns true if the character is currently the character's companion.
	 */
	public boolean hasCompanion(GameCharacter character) {
		if(this.companions == null) {
			return false;
		}
		return this.companions.contains(character.getId());
	}
	
	/**
	 * Returns party leader or null if no party is led.
	 */
	public GameCharacter getPartyLeader() {
		if(this.partyLeader==null || this.partyLeader.isEmpty()) {
			return null;
		}
		try {
			return Main.game.getNPCById(partyLeader);
		} catch(Exception e) {
			System.err.println("Main.game.getNPCById("+partyLeader+") returning null in method: getPartyLeader()");
			return null;
		}
	}
	
	public int getMaxCompanions() {
		return maxCompanions;
	}
	
	/**
	 * Adjusts maximum companion amount. Set to -1 to avoid checking.
	 */
	public void setMaxCompanions(int maxCompanions) {
		this.maxCompanions = maxCompanions;
	}

	public List<String> getCompanionsId() {
		return this.companions;
	}
	
	/**<b>Do not call this method directly! Use the owner's addCompanion() and removeCompanion() methods!</b>*/
	protected void setPartyLeader(GameCharacter owner) {
		this.partyLeader = owner.getId();
	}

	/**<b>Do not call this method directly! Use the owner's addCompanion() and removeCompanion() methods!</b>*/
	public void setPartyLeader(String owner) {
		this.partyLeader = owner;
	}
	
	/**
	 * Gets the character's companion NPCs, if any.
	 */
	public List<GameCharacter> getCompanions() {
		List<GameCharacter> listToReturn = new ArrayList<>();
		if(this.companions != null) {
			for(String companionID : this.companions) {
				try {
					listToReturn.add(Main.game.getNPCById(companionID));
				} catch(Exception e) {
					System.err.println("Main.game.getNPCById("+companionID+") returning null in method: getCompanions()");
				}
			}
		}
		return listToReturn;
	}
	
	/**
	 * Gets the character's non-elemental companion NPCs, if any.
	 */
	public List<GameCharacter> getNonElementalCompanions() {
		List<GameCharacter> listToReturn = getCompanions();
		listToReturn.removeIf((com) -> com instanceof Elemental);
		return listToReturn;
	}
	
	/**
	 * @return true if there is space for more party members. Elemental companions do not take up a companion slot.
	 */
	public boolean canHaveMoreCompanions() {
		int elementals = 0;
		for(GameCharacter companion : getCompanions()) {
			if(companion instanceof Elemental) {
				elementals++;
			}
		}
		
		return this.maxCompanions != -1
				&& (companions.size()-elementals) < maxCompanions;
	}
	
	/**
	 * Called when the player does something in relation to the value and it's important to the NPC. Override for custom behavior.
	 * @param source
	 * @param moral
	 * @param power
	 */
	public void moralityCheck(GameCharacter source, MoralityValue moral, float power) {
		return;
	}
	
	public void companionshipCheck() {
		try {
			if(Main.game.isStarted() && !this.isCompanionAvailable(Main.game.getNPCById(partyLeader))) {
				String s = "";
				if(Main.game.getNPCById(partyLeader).statusEffectDescriptions.get(StatusEffect.COMPANIONS_LEAVING)!=null) {
					s = Main.game.getNPCById(partyLeader).statusEffectDescriptions.get(StatusEffect.COMPANIONS_LEAVING);
				}
				
				Main.game.getNPCById(partyLeader).getStatusEffectDescriptions().put(StatusEffect.COMPANIONS_LEAVING, s+getCompanionRejectionReason());
				Main.game.getNPCById(partyLeader).removeCompanion(this);
				this.returnToHome();
			}
		} catch(Exception e) {
			System.err.println("Main.game.getNPCById("+partyLeader+") returning null in method: companionshipCheck()");
		}
	}
	
	/**
	 * Override if needed. Returns true if this companion is available to that character. Is called during turn updates to make sure NPCs keep their companionship state updated. 
	 */
	public boolean isCompanionAvailable(GameCharacter partyLeader) {
		if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId()) && this.getHistory().isAtWork(Main.game.getHourOfDay()) && !this.getHistory().isLowlife()) {
			return false;
		}
		
		return (this.isSlave() && this.getOwner().equals(partyLeader))
				|| (Main.game.getPlayer().getFriendlyOccupants().contains(this.getId()))
				|| (this instanceof Elemental && ((Elemental)this).getSummoner().equals(partyLeader));
	}
	
	/**
	 * Added during turn update to the report to make sure player knows why NPCs leave them. By default will just make some generic excuse up.
	 */
	public String getCompanionRejectionReason() {
		if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId()) && this.getHistory().isAtWork(Main.game.getHourOfDay())) {
			return UtilText.parse(this,
					"<p>"
						+ "[npc.speech(Ah! I need to get to work! Sorry, [pc.name], I'll see you later! Bye!)] [npc.name] suddenly says, before turning and running off..."
					+ "</p>");
		}
		
		return this.getName()+" leaves your party for unknown reasons.";
	}
	
	public final boolean isCompanionAvailableForSex(boolean companionIsSub) {
		return getCompanionSexRejectionReason(companionIsSub) == null
				|| getCompanionSexRejectionReason(companionIsSub).isEmpty();
	}
	
	/**
	 * Override this to see if companion is willing to have sex with player
	 */
	public String getCompanionSexRejectionReason(boolean companionIsSub) {
		if(!Main.game.getSavedDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false))) {
			return "You're in the middle of something right now!";
		}
		if(this instanceof Elemental) {
			switch(this.getBodyMaterial()) {
				case AIR:
					if(((Elemental)this).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A) && companionIsSub) {
						return UtilText.parse(this, "As you have sworn subservience to the school of Air, while [npc.name] is bound in this form, [npc.she] refuses to let you act as the dominant partner in sex!");
					}
					break;
				case ARCANE:
					if(((Elemental)this).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A) && companionIsSub) {
						return UtilText.parse(this, "As you have sworn subservience to the school of Arcane, while [npc.name] is bound in this form, [npc.she] refuses to let you act as the dominant partner in sex!");
					}
					break;
				case FIRE:
					if(((Elemental)this).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A) && companionIsSub) {
						return UtilText.parse(this, "As you have sworn subservience to the school of Fire, while [npc.name] is bound in this form, [npc.she] refuses to let you act as the dominant partner in sex!");
					}
					break;
				case FLESH:
				case SLIME:
					break;
				case RUBBER:
				case STONE:
					if(((Elemental)this).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A) && companionIsSub) {
						return UtilText.parse(this, "As you have sworn subservience to the school of Earth, while [npc.name] is bound in this form, [npc.she] refuses to let you act as the dominant partner in sex!");
					}
					break;
				case ICE:
				case WATER:
					if(((Elemental)this).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A) && companionIsSub) {
						return UtilText.parse(this, "As you have sworn subservience to the school of Water, while [npc.name] is bound in this form, [npc.she] refuses to let you act as the dominant partner in sex!");
					}
					break;
			}
		}
		
		if(!this.isSlave()) {
			if(!this.isAttractedTo(getPartyLeader())) {
				return UtilText.parse(this, getPartyLeader(), "[npc.Name] doesn't want to have sex with [npc2.name]!");
			}
			if(!this.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive() && this.getLocationPlace().getPlaceType().isPopulated()) {
				return UtilText.parse(this, "As [npc.she] isn't an exhibitionist, [npc.name] is not going to agree to have sex in public!");
			}
		}
		
		switch(this.getWorldLocation()) {
			case ANGELS_KISS_FIRST_FLOOR:
			case ANGELS_KISS_GROUND_FLOOR:
			case BAT_CAVERNS:
			case DOMINION:
			case EMPTY:
			case SLAVER_ALLEY:
			case SUBMISSION:
			case HARPY_NEST:
			case JUNGLE:
			case LILAYAS_HOUSE_FIRST_FLOOR:
			case LILAYAS_HOUSE_GROUND_FLOOR:
			case NIGHTLIFE_CLUB:
				break;
				
			case ENFORCER_HQ:
				return "You can't have sex in the Enforcer HQ!";
			case SHOPPING_ARCADE:
				if(this.getLocationPlace().getPlaceType()!=PlaceType.SHOPPING_ARCADE_PATH) {
					return "This isn't a suitable place to be having sex with [npc.name]!";
				}
				break;
			case SUPPLIER_DEN:
			case SLIME_QUEENS_LAIR_GROUND_FLOOR:
			case SLIME_QUEENS_LAIR_FIRST_FLOOR:
			case GAMBLING_DEN:
			case IMP_FORTRESS_ALPHA:
			case IMP_FORTRESS_DEMON:
			case IMP_FORTRESS_FEMALES:
			case IMP_FORTRESS_MALES:
				return "This isn't a suitable place to be having sex with [npc.name]!";
			case ZARANIX_HOUSE_FIRST_FLOOR:
			case ZARANIX_HOUSE_GROUND_FLOOR:
				return "You can't have sex with [npc.name] in Zaranix's house!";
		}
		for(GameCharacter character : Main.game.getCharactersPresent()) {
			if(!character.isSlave() && !this.getPartyLeader().getCompanions().contains(character)) {
				return UtilText.parse(character, "You can't have sex in front of [npc.name]!");
			}
		}
		return "";
	}
	
	// Relationships:
	
	public Relationship getRelationship(GameCharacter character) {//TODO grandchildren, cousins, etc.
		// If this character is the character's parent:
		if((!character.getMotherId().isEmpty() && character.getMotherId().equals(getId()))
				|| (!character.getFatherId().isEmpty() && character.getFatherId().equals(getId()))) {
			return Relationship.PARENT;
		}
		// If this character is the character's child:
		if((!getMotherId().isEmpty() && getMotherId().equals(character.getId()))
				|| (!getFatherId().isEmpty() && getFatherId().equals(character.getId()))) {
			return Relationship.OFFSPRING;
		}
		// If this character is the character's sibling:
		if((!getMotherId().isEmpty() && getMotherId().equals(character.getMotherId()))
				|| (!getFatherId().isEmpty() && getFatherId().equals(character.getFatherId()))) {
			return Relationship.SIBLING;
		}
		
		return null;
	}
	
	public boolean isRelatedTo(GameCharacter character) {
		return getRelationship(character) !=null;
	}
	
	public GameCharacter getMother() {
		if(motherId==null || motherId.isEmpty()) {
			return null;
		}
		try {
			return Main.game.getNPCById(motherId);
		} catch(Exception e) {
			System.err.println("Main.game.getNPCById("+motherId+") returning null in method: getMother()");
			return null;
		}
	}
	
	public String getMotherId() {
		return motherId;
	}
	
	public void setMother(String motherId) {
		this.motherId = motherId;
	}

	public void setMother(GameCharacter mother) {
		motherId = mother.getId();
	}

	public GameCharacter getFather() {
		if(fatherId==null || fatherId.isEmpty()) {
			return null;
		}
		try {
			return Main.game.getNPCById(fatherId);
		} catch(Exception e) {
			System.err.println("Main.game.getNPCById("+fatherId+") returning null in method: getFather()");
			return null;
		}
	}

	public String getFatherId() {
		return fatherId;
	}
	
	public void setFather(String fatherId) {
		this.fatherId = fatherId;
	}
	
	public void setFather(GameCharacter father) {
		fatherId = father.getId();
	}

	public LocalDateTime getConceptionDate() {
		return conceptionDate;
	}

	public void setConceptionDate(LocalDateTime conceptionDate) {
		this.conceptionDate = conceptionDate;
	}

	public int getExperience() {
		return experience;
	}

	private int getExperienceNeededForNextLevel() {
		return getLevel() * 10;
	}
	
	public String incrementExperience(int increment, boolean withExtaModifiers) {
		if (getLevel() == LEVEL_CAP) {
			experience = 0;
			return "";
		}
		
		int xpIncrement = (int) Math.max(0, increment * (withExtaModifiers&&this.hasTrait(Perk.JOB_WRITER, true)?1.25f:1));
		
		experience += xpIncrement;
		
		if (experience >= getExperienceNeededForNextLevel()) {
			levelUp();
		}
		
		if(this.isPlayer()) {
			return "<div class='container-full-width' style='text-align:center;'>You [style.colourGood(gained)] <b style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + xpIncrement + " xp</b>!</div>";

		} else {
			return "<div class='container-full-width' style='text-align:center;'>"+UtilText.parse(this, "[npc.Name]")
				+" [style.colourGood(gained)] <b style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + xpIncrement + " xp</b>!</div>";
		}
	}

	private void levelUp() {
		// For handling health, mana and stamina changes as a result of an attribute being changed:
		float healthPercentage = getHealthPercentage();
		float manaPercentage = getManaPercentage();
		
		while (experience >= getExperienceNeededForNextLevel() && getLevel() < LEVEL_CAP) {
			experience -= getExperienceNeededForNextLevel();

			level++;

			perkPoints++;
			if(level%5==0) {
				perkPoints+=2;
			}
		}
		if (getLevel() == LEVEL_CAP) {
			experience = 0;
		}
		
		// Increment health, mana and stamina based on the change:
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * healthPercentage);
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * manaPercentage);
		
		if(isPlayer()) {
			Main.getProperties().setValue(PropertyValue.levelUpHightlight, true);
		} else {
			//TODO NPC level up
		}
	}
	
	public int getPerkPointsAtLevel(int level) {
		return level-1 + (level/5)*2;
	}
	
	public int getPerkPoints() {
		return perkPoints;
	}

	public void incrementPerkPoints(int increment) {
		setPerkPoints(perkPoints+increment);
	}
	
	public void setPerkPoints(int perkPoints) {
		this.perkPoints = perkPoints;
	}

	// Attributes:

	public CorruptionLevel getCorruptionLevel(){
		return CorruptionLevel.getCorruptionLevelFromValue(getAttributeValue(Attribute.MAJOR_CORRUPTION));
	}

	public float getBaseAttributeValue(Attribute attribute) {
		// Special case for health:
		if (attribute == Attribute.HEALTH_MAXIMUM) {
			return 10 + 2*getLevel() + getAttributeValue(Attribute.MAJOR_PHYSIQUE)*2;
		}

		// Special case for mana:
		if (attribute == Attribute.MANA_MAXIMUM) {
			return 10 + 2*getLevel() + getAttributeValue(Attribute.MAJOR_ARCANE)*2;
		}
		
		return Math.round(attributes.get(attribute)*100)/100f;
	}

	public float getBonusAttributeValue(Attribute att) {
		return Math.round(bonusAttributes.get(att)*100)/100f;
	}

	public float getAttributeValue(Attribute att) {
		if(!Main.game.isInNewWorld() && att == Attribute.MAJOR_ARCANE) {
			return 0;
		}
		
		float value = getBaseAttributeValue(att) + getBonusAttributeValue(att);
		
		if(att == Attribute.HEALTH_MAXIMUM
				&& (this.hasStatusEffect(StatusEffect.ELEMENTAL_EARTH_SERVANT_OF_EARTH)
						|| this.hasStatusEffect(StatusEffect.ELEMENTAL_WATER_SERVANT_OF_WATER)
						|| this.hasStatusEffect(StatusEffect.ELEMENTAL_AIR_SERVANT_OF_AIR)
						|| this.hasStatusEffect(StatusEffect.ELEMENTAL_FIRE_SERVANT_OF_FIRE)
						|| this.hasStatusEffect(StatusEffect.ELEMENTAL_ARCANE_SERVANT_OF_ARCANE))) {
			value /= 2;
		}
		
		if(value < att.getLowerLimit()) {
			return att.getLowerLimit();
			
		} else if(value > att.getUpperLimit()) {
			return att.getUpperLimit();
		}
		
		return Math.round(value * 100)/100f;
	}

	public String setAttribute(Attribute att, float value) {
		return setAttribute(att, value, true);
	}
	
	public String setAttribute(Attribute att, float value, boolean appendAttributeChangeText) {
		return incrementAttribute(att, value - attributes.get(att), appendAttributeChangeText);
	}

	public String incrementAttribute(Attribute att, float increment) {
		return incrementAttribute(att, increment, false);
	}
	
	public String incrementAttribute(Attribute att, float increment, boolean appendAttributeChangeText) {
		float value = attributes.get(att) + increment;

		// For handling health, mana and stamina changes as a result of an
		// attribute being changed:
		float healthPercentage = getHealthPercentage();
		float manaPercentage = getManaPercentage();
		
		if(value < att.getLowerLimit()) {
			value = att.getLowerLimit();
			
		} else if(value > att.getUpperLimit()) {
			value = att.getUpperLimit();
		}
		
		attributes.put(att, value);
		
		if(isPlayer() && appendAttributeChangeText) {
			Main.game.addEvent(new EventLogEntryAttributeChange(att, ((int)(increment * 100))/100f, true), !Main.game.isInSex());
		}

		// Increment health, mana and stamina based on the change:
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * healthPercentage);
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * manaPercentage);

		updateAttributeListeners();
		
		return att.getAttributeChangeText(this, ((int)(increment * 100))/100f);
	}


	public void incrementBonusAttribute(Attribute att, float increment) {
		float value = bonusAttributes.get(att) + increment;

		float healthPercentage = getHealthPercentage();
		float manaPercentage = getManaPercentage();

		bonusAttributes.put(att, value);

		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * healthPercentage);
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * manaPercentage);

		updateAttributeListeners();
	}
	
	public Map<Attribute, Float> getPotionAttributes() {
		return potionAttributes;
	}
	
	public void setPotionAttributes(Map<Attribute, Float> attributeMap) {
		potionAttributes = attributeMap;
	}
	
	public void setPotionAttribute(Attribute att, float value) {
		potionAttributes.put(att, value);
		
		for(float v : potionAttributes.values()) {
			if(v>0) {
				return;
			}
		}
		this.removeStatusEffect(StatusEffect.POTION_EFFECTS);
	}
	
	public String addPotionEffect(Attribute att, float value) {
		Map<Attribute, Float> savedPotionEffects = new EnumMap<>(Attribute.class);
		savedPotionEffects.putAll(getPotionAttributes());
		
		int potionTimeRemaining = 0;
		
		if(this.hasStatusEffect(StatusEffect.POTION_EFFECTS)) {
			potionTimeRemaining = getStatusEffectDuration(StatusEffect.POTION_EFFECTS);
			removeStatusEffect(StatusEffect.POTION_EFFECTS);
		}
		
		setPotionAttributes(savedPotionEffects);
		
		value *= this.isPlayer()&&this.hasTrait(Perk.JOB_CHEF, true)?2:1;
		
		if(potionAttributes.containsKey(att)) {
			setPotionAttribute(att, potionAttributes.get(att)+value);
		} else {
			setPotionAttribute(att, value);
		}
		
		if(potionAttributes.get(att)==0) {
			potionAttributes.remove(att);
		}
		
		potionTimeRemaining += 30 * (this.isPlayer()&&this.hasTrait(Perk.JOB_CHEF, true)?2:1);
		
		if(potionTimeRemaining>=12*60) {
			addStatusEffect(StatusEffect.POTION_EFFECTS, 12*60);
			
		} else if(potionTimeRemaining>60) {
			addStatusEffect(StatusEffect.POTION_EFFECTS, potionTimeRemaining);
			
		} else {
			addStatusEffect(StatusEffect.POTION_EFFECTS, 60);
			
		}
		
		if(isPlayer()) {
			if(potionAttributes.get(att)<0) {
				return "<p style='text-align:center;'>"
							+ "You now have [style.boldBad("+potionAttributes.get(att)+")] <b style='color:"+att.getColour().toWebHexString()+";'>"+att.getName()+"</b>"
							+(this.hasTrait(Perk.JOB_CHEF, true)?" ([style.boldExcellent(doubled)] from <b style='color:"+Perk.JOB_CHEF.getColour().toWebHexString()+";'>"+Perk.JOB_CHEF.getName(this)+"</b>)":"")
							+" for as long as you can maintain your potion effects!"
						+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
							+ "You now have [style.boldGood(+"+potionAttributes.get(att)+")] <b style='color:"+att.getColour().toWebHexString()+";'>"+att.getName()+"</b>"
							+(this.hasTrait(Perk.JOB_CHEF, true)?" ([style.boldExcellent(doubled)] from <b style='color:"+Perk.JOB_CHEF.getColour().toWebHexString()+";'>"+Perk.JOB_CHEF.getName(this)+"</b>)":"")
							+" for as long as you can maintain your potion effects!"
						+ "</p>";
			}
		} else {
			if(potionAttributes.get(att)<0) {
				return "<p style='text-align:center;'>"
							+ UtilText.parse(this,
							"[npc.Name] now has [style.boldBad("+potionAttributes.get(att)+")] <b style='color:"+att.getColour().toWebHexString()+";'>"+att.getName()+"</b>"
							+(this.hasTrait(Perk.JOB_CHEF, true)?" ([style.boldExcellent(doubled)] from <b style='color:"+Perk.JOB_CHEF.getColour().toWebHexString()+";'>"+Perk.JOB_CHEF.getName(this)+"</b>)":"")
							+" for as long as [npc.she] can maintain [npc.her] potion effects!")
						+ "</p>";
			} else {
				return "<p style='text-align:center;'>"
							+ UtilText.parse(this,
							"[npc.Name] now has [style.boldGood(+"+potionAttributes.get(att)+")] <b style='color:"+att.getColour().toWebHexString()+";'>"+att.getName()+"</b>"
							+(this.hasTrait(Perk.JOB_CHEF, true)?" ([style.boldExcellent(doubled)] from <b style='color:"+Perk.JOB_CHEF.getColour().toWebHexString()+";'>"+Perk.JOB_CHEF.getName(this)+"</b>)":"")
							+" for as long as [npc.she] can maintain [npc.her] potion effects!")
						+ "</p>";
			}
		}
	}
	
	public void clearPotionAttributes() {
		potionAttributes.clear();
	}
	

	// Perks:

	public List<Perk> getTraits() {
		return traits;
	}
	
	public boolean hasTraitActivated(Perk perk) {
		return traits.contains(perk);
	}

	public boolean removeTrait(Perk perk) {
		if(traits.remove(perk)) {
			applyPerkRemovalEffects(perk);
			return true;
		}
		return false;
	}
	
	public void clearTraits() {
		for(Perk p : traits) {
			applyPerkRemovalEffects(p);
		}
		traits.clear();
	}
	
	public boolean addTrait(Perk perk) {
		if(traits.contains(perk) || traits.size()>=MAX_TRAITS) {
			return false;
		}
		traits.add(perk);
		applyPerkGainEffects(perk);
		return true;
	}
	
	public void resetPerksMap() {
		HashMap<Integer, Set<Perk>> currentPerks = new HashMap<>(perks);
		
		for(Entry<Integer, Set<Perk>> entry : currentPerks.entrySet()) {
			Set<Perk> tooTiredToThink = new HashSet<>(entry.getValue());
			for(Perk p : tooTiredToThink) {
				this.removePerk(entry.getKey(), p);
			}
		}
		
		calculateSpecialAttacks();
		
		updateAttributeListeners();
		
		PerkManager.initialisePerks(this);
	}
	
	public Map<Integer, Set<Perk>> getPerksMap() {
		return perks;
	}
	
	public List<Perk> getMajorPerks() {
		List<Perk> tempPerkList = new ArrayList<>();
		for(Entry<Integer, Set<Perk>> entry : perks.entrySet()) {
			for(Perk p : entry.getValue()) {
				if(p.isEquippableTrait()) {
					tempPerkList.add(p);
				}
			}
		}
		tempPerkList.sort(Comparator.comparingInt(Perk::getRenderingPriority).reversed());
		return tempPerkList;
	}
	
	public boolean hasTrait(Perk p, boolean equipped) {
		if(p.isEquippableTrait()) {
			if((p.getPerkCategory()==PerkCategory.JOB)) {
				return getHistory().getAssociatedPerk()==p;
			} else if(equipped) {
				return traits.contains(p);
			} else {
				return hasPerkInTree(PerkManager.MANAGER.getPerkRow(this, p), p);
			}
		}
		return false;
	}
	
	public boolean hasPerkAnywhereInTree(Perk p) {
		for(Set<Perk> perkSet : perks.values()) {
			if(perkSet.contains(p)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasPerkInTree(int row, Perk p) {
		if(!perks.containsKey(row)) {
			return false;
		}
		return perks.get(row).contains(p);
	}

	public boolean addPerk(Perk perk) {
		return addPerk(PerkManager.MANAGER.getPerkRow(this, perk), perk);
	}
	
	public boolean addPerk(int row, Perk perk) {
		perks.putIfAbsent(row, new HashSet<>());
		
		if (perks.get(row).contains(perk)) {
			return false;
		}
		
		perks.get(row).add(perk);
		
		if(!perk.isEquippableTrait()) {
			applyPerkGainEffects(perk);
		}
		
		return true;
	}

	public boolean removePerk(int row, Perk perk) {
		if (!perks.containsKey(row)) {
			return false;
		}
		
		if (!perks.get(row).contains(perk)) {
			return false;
		}
		
		perks.get(row).remove(perk);

		if(!perk.isEquippableTrait()) {
			applyPerkRemovalEffects(perk);
		}
		
		return true;
	}
	
	private void applyPerkGainEffects(Perk perk) {
		// Increment bonus attributes from this perk:
		if (perk.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : perk.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
		}
		calculateSpecialAttacks();
		
		updateAttributeListeners();
	}
	
	private void applyPerkRemovalEffects(Perk perk) {
		
		// Reverse bonus attributes from this perk:
		if (perk.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : perk.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), -e.getValue());
			}
		}
		calculateSpecialAttacks();
		
		updateAttributeListeners();
	}
	
	
	// Fetishes:

	/**The returned list is ordered by rendering priority.*/
	public List<Fetish> getFetishes() {
		List<Fetish> tempFetishList = new ArrayList<>(fetishes);
		tempFetishList.sort(Comparator.comparingInt(Fetish::getRenderingPriority).reversed());
		return tempFetishList;
	}
	
	public boolean hasBaseFetish(Fetish f) {
		return fetishes.contains(f);
	}
	
	public boolean hasFetish(Fetish f) {
		return fetishes.contains(f) || fetishesFromClothing.contains(f);
	}
	
	public boolean hasTransformationFetish() {
		return hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) || hasFetish(Fetish.FETISH_KINK_GIVING);
	}
	
	public boolean addFetish(Fetish fetish) {
		if (fetishes.contains(fetish)) {
			return false;
		}
		
		fetishes.add(fetish);

		applyFetishGainEffects(fetish);

		return true;
	}
	
	private void applyFetishGainEffects(Fetish fetish) {
		// Increment bonus attributes from this fetish:
		if (fetish.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : fetish.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
		}
		
		calculateSpecialAttacks();
		updateAttributeListeners();
		calculateSpecialFetishes();
	}

	public boolean removeFetish(Fetish fetish) {
		if (!fetishes.contains(fetish)) {
			return false;
		}
		
		fetishes.remove(fetish);

		applyFetishLossEffects(fetish);
		
		return true;
	}
	
	public void clearFetishes() {
		for(Fetish fetish : this.getFetishes()) {
			removeFetish(fetish);
		}
	}
	
	private void applyFetishLossEffects(Fetish fetish) {
		// Reverse bonus attributes from this fetish:
		if (fetish.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : fetish.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), -e.getValue());
			}
		}
		
		calculateSpecialAttacks();
		updateAttributeListeners();
		calculateSpecialFetishes();
	}
	
	private void calculateSpecialFetishes() {
		for(Fetish f : Fetish.values()) {
			if(!f.getFetishesForAutomaticUnlock().isEmpty()) {
				boolean conditionsMet = true;
				for(Fetish fetishNeeded : f.getFetishesForAutomaticUnlock()) {
					if(!hasFetish(fetishNeeded)) {
						conditionsMet = false;
						break;
					}
				}
				if(conditionsMet) {
					addFetish(f);
				} else {
					removeFetish(f);
				}
			}
		}
	}
	
	public Map<Fetish, FetishDesire> getFetishDesireMap() {
		return fetishDesireMap;
	}
	
	public void clearFetishDesires() {
		fetishDesireMap.clear();
	}
	
	public boolean setFetishDesire(Fetish fetish, FetishDesire desire) {
		if(fetishDesireMap.get(fetish)==desire) {
			return false;
		}
		
		fetishDesireMap.put(fetish, desire);
		
		return true;
	}
	
	public FetishDesire getBaseFetishDesire(Fetish fetish) {
		
		if(!fetishDesireMap.containsKey(fetish)) {
			return FetishDesire.TWO_NEUTRAL;
		}
		return fetishDesireMap.get(fetish);
	}
	
	public FetishDesire getFetishDesire(Fetish fetish) {
		if(hasFetish(fetish)) {
			return FetishDesire.FOUR_LOVE;
		}
		
		FetishDesire baseDesire = getBaseFetishDesire(fetish);
		
		if(!hasFetish(fetish) && clothingFetishDesireModifiersMap.containsKey(fetish)) {
			return FetishDesire.getDesireFromValue(baseDesire.getValue() + clothingFetishDesireModifiersMap.get(fetish));
		}
		
		return baseDesire;
	}
	
	private Map<Fetish, Integer> getFetishExperienceMap() {
		return fetishExperienceMap;
	}
	
	public boolean setFetishExperience(Fetish fetish, int experience) {
		fetishExperienceMap.put(fetish, Math.max(0, Math.min(experience, FetishLevel.FOUR_MASTERFUL.getMaximumExperience())));
		return true;
	}
	
	public boolean incrementFetishExperience(Fetish fetish, int experienceIncrement) {
		fetishExperienceMap.putIfAbsent(fetish, 0);
		return setFetishExperience(fetish, Math.max(0, fetishExperienceMap.get(fetish)+experienceIncrement));
	}
	
	public int getFetishExperience(Fetish fetish) {
		if(!fetishExperienceMap.containsKey(fetish)) {
			return 0;
		}
		return fetishExperienceMap.get(fetish);
	}
	
	public FetishLevel getFetishLevel(Fetish fetish) {
		return FetishLevel.getFetishLevelFromValue(getFetishExperience(fetish));
	}
	

	// Status effects:

	public void calculateStatusEffects(int turnTime) {
		// Count down status effects:
		String s;
		List<StatusEffect> tempListStatusEffects = new ArrayList<>();
		for (StatusEffect se : getStatusEffects()) {
			if (!se.isCombatEffect()){
				s = se.applyEffect(this, turnTime);
				if(s.length()!=0) {
					statusEffectDescriptions.put(se, s);
				}
			}
			
			incrementStatusEffectDuration(se, -turnTime);
			
			if (statusEffects.get(se) < 0 && !se.isConditionsMet(this)) {
				tempListStatusEffects.add(se);
			}
		}

		// Remove all status effects that are no longer applicable:
		for(StatusEffect se : tempListStatusEffects) {
			removeStatusEffect(se);
		}
		
		if (!Main.game.isInCombat()) {
			clearCombatStatusEffects();
		}
		
		// Add all status effects that are applicable:
		for (StatusEffect se : StatusEffect.values()) {
			if (se.isConditionsMet(this)) {
				addStatusEffect(se, -1);
			}
		}
		
		// Clothing effects:
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			for(ItemEffect ie : c.getEffects()) {
				String clothingEffectDescription = ie.applyEffect(this, this, turnTime);
				if (this.isPlayer() && !clothingEffectDescription.isEmpty()) {
					statusEffectDescriptions.put(StatusEffect.CLOTHING_EFFECT, statusEffectDescriptions.computeIfAbsent(StatusEffect.CLOTHING_EFFECT, x -> "") + clothingEffectDescription);
				}
			}
		}
		
		// Tattoo effects:
		for(Tattoo tattoo : tattoos.values()) {
			for(ItemEffect ie : tattoo.getEffects()) {
				String tattooEffectDescription = ie.applyEffect(this, this, turnTime);
				if (this.isPlayer() && !tattooEffectDescription.isEmpty()) {
					statusEffectDescriptions.put(StatusEffect.CLOTHING_EFFECT, statusEffectDescriptions.computeIfAbsent(StatusEffect.CLOTHING_EFFECT, x -> "") + tattooEffectDescription);
				}
			}
		}
		
		updateAttributeListeners();
	}
	
	/**
	 * The returned list is ordered by rendering priority.
	 */

	public List<StatusEffect> getStatusEffects() {
		List<StatusEffect> tempListStatusEffects = new ArrayList<>(statusEffects.keySet());
		tempListStatusEffects.sort(Comparator.comparingInt(StatusEffect::getRenderingPriority).reversed());
		return tempListStatusEffects;
	}


	public boolean hasStatusEffect(StatusEffect se) {
		return statusEffects.containsKey(se);
	}


	public boolean addStatusEffect(StatusEffect statusEffect, int length) {
		if (hasStatusEffect(statusEffect)){
			// refresh the effect
			statusEffects.put(statusEffect, length);//(Main.game.isInCombat()&&statusEffect.isCombatEffect()&&statusEffect.isBeneficial())?length+1:length);
			return false;
		}
		
		statusEffects.put(statusEffect, length);//(Main.game.isInCombat()&&statusEffect.isCombatEffect()&&statusEffect.isBeneficial())?length+1:length);
		
		// Increment bonus attributes from this StatusEffect:
		if (statusEffect.getAttributeModifiers(this) != null) {
			for (Entry<Attribute, Float> e : statusEffect.getAttributeModifiers(this).entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
		}
		
		// Apply effect:
		if (!statusEffect.isCombatEffect()){
			String s = statusEffect.applyEffect(this, 0);
			if(s.length()!=0) {
				statusEffectDescriptions.put(statusEffect, s);
			}
		}
		
		updateAttributeListeners();

		return true;
	}

	public String removeStatusEffectCombat(StatusEffect se) {
		if (!statusEffects.containsKey(se)) {
			return "";
		}
		
		String s = se.applyRemoveStatusEffect(this);
		
		statusEffects.remove(se);
		
		s+=se.applyPostRemovalStatusEffect(this);
		
		updateAttributeListeners();
		
		return s;
	}

	public boolean removeStatusEffect(StatusEffect se) {
		if (!statusEffects.containsKey(se)) {
			return false;
		}
		
		String s = se.applyRemoveStatusEffect(this);
		if (!se.isCombatEffect()){
			if(s.length()!=0) {
				statusEffectDescriptions.put(se, s);
			}
		}
		
		statusEffects.remove(se);

		s+=se.applyPostRemovalStatusEffect(this);
		
		updateAttributeListeners();

		return true;
	}


	public int getStatusEffectDuration(StatusEffect se) {
		return statusEffects.get(se);
	}


	public boolean setStatusEffectDuration(StatusEffect se, int length) {
		if (!statusEffects.containsKey(se))
			return false;

		statusEffects.put(se, length);

		return true;
	}


	public boolean incrementStatusEffectDuration(StatusEffect se, int increment) {
		if (!statusEffects.containsKey(se) || statusEffects.get(se) == -1)
			return false;

//		if (statusEffects.get(se) + increment < 0) // Remove the status effect
//													// if it ticked past 0:
//			removeStatusEffect(se);
//		else // Otherwise, increment the status effect:
			
		statusEffects.put(se, statusEffects.get(se) + increment);

		return true;
	}


	public Map<StatusEffect, String> getStatusEffectDescriptions() {
		return statusEffectDescriptions;
	}


	public void clearCombatStatusEffects() {
		List<StatusEffect> removalList = new ArrayList<>();
		for (StatusEffect se : statusEffects.keySet()) {
			if (se.isCombatEffect())
				removalList.add(se);
		}
		for (StatusEffect se : removalList)
			removeStatusEffect(se);
	}

	
	// Stats:

	public Map<SexType, Integer> getSexPartnerStats(GameCharacter c) {
		return sexPartnerMap.get(c.getId());
	}
	
	public Map<String, Map<SexType, Integer>> getSexPartners() {
		return sexPartnerMap;
	}

	public void addSexPartner(GameCharacter partner) {
		this.sexPartnerMap.computeIfAbsent(partner.getId(), k -> new HashMap<>());
	}
	
	public void addSexPartner(GameCharacter partner, SexType sexType) {
		this.sexPartnerMap.computeIfAbsent(partner.getId(), k -> new HashMap<>());
		
		Map <SexType, Integer> sp = this.sexPartnerMap.get(partner.getId());
		sp.putIfAbsent(sexType, 0);
		
		sp.put(sexType, sp.get(sexType) + 1);
	}

	public int getFoughtPlayerCount() {
		return foughtPlayerCount;
	}
	public void setFoughtPlayerCount(int count) {
		foughtPlayerCount = count;
	}

	public int getLostCombatCount() {
		return lostCombatCount;
	}
	public void setLostCombatCount(int count) {
		lostCombatCount = count;
	}

	public int getWonCombatCount() {
		return wonCombatCount;
	}
	public void setWonCombatCount(int count) {
		wonCombatCount = count;
	}
	
	public String getMainAttackDescription(boolean isHit) {
		if(this.getMainWeapon()!=null) {
			return this.getMainWeapon().getWeaponType().getAttackDescription(this, Combat.getTargetedCombatant(this), isHit);
		} else {
			return AbstractWeaponType.genericMeleeAttackDescription(this, Combat.getTargetedCombatant(this), isHit);
		}
	}
	
	public String getOffhandAttackDescription(boolean isHit) {
		if(this.getOffhandWeapon()!=null) {
			return this.getOffhandWeapon().getWeaponType().getAttackDescription(this, Combat.getTargetedCombatant(this), isHit);
		} else {
			return AbstractWeaponType.genericMeleeAttackDescription(this, Combat.getTargetedCombatant(this), isHit);
		}
	}
	
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.parse(this,
						UtilText.returnStringAtRandom(
						"Letting out a wild scream, [npc.name] thrusts [npc.her] arm into mid air as [npc.she] casts a spell!",
						"Spitting curses, [npc.name] locks [npc.her] eyes onto yours, before casting a spell!",
						"With an angry curse, [npc.name] steps forwards and casts a spell!"))
			+ "</p>";
	}
	
	public String getSeductionDescription() {
		String description = "";
		if(this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION)
				|| this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)
				|| this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH)) {
			if(this.isFeminine()) {
				return UtilText.parse(this,
						UtilText.returnStringAtRandom(
								"[npc.Name] puts on a smouldering look, and as her eyes meet yours, you hear an extremely lewd moan echoing around in your head, [npc.thought(~Aaah!~ "
										+(this.hasVagina()
												?"You're making me so wet!"
												:this.hasPenis()
													?"You're getting me so hard!"
													:"You're turning me on so much!")+")]",
								"[npc.Name] locks her big, innocent-looking eyes with yours, and as she pouts, you hear an echoing moan deep within your mind, [npc.thought("+
										(this.hasVagina()
												?"~Mmm!~ Fuck me! ~Aaa!~ My pussy's wet and ready for you!"
												:this.hasPenis()
													?"~Mmm!~ I can't wait to fuck you! ~Aaa!~ You're going to love my cock!"
													:"~Mmm!~ Fuck me! ~Aaa!~ I need you so badly!")+")]",
								(this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)
										|| this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH)
										?"[npc.Name] pouts innocently at you, before blowing you a wet kiss. As she straightens back up, you feel a ghostly pair of wet lips press against your cheek."
										:"")));
			} else {
				return UtilText.parse(this,
						UtilText.returnStringAtRandom(
								"[npc.Name] puts on a confident look, and as his eyes meet yours, you hear an extremely lewd groan echoing around in your head, [npc.thought(~Mmm!~ "
										+(this.hasVagina()
												?"You're making me so wet!"
												:this.hasPenis()
													?"You're getting me so hard!"
													:"You're turning me on so much!")+")]",
								"[npc.Name] locks his eyes with yours, and as he throws you a charming smile, you hear an echoing groan deep within your mind, [npc.thought("+
										(this.hasVagina()
												?"~Mmm!~ Fuck me! ~Aaa!~ My pussy's wet and ready for you!"
												:this.hasPenis()
													?"~Mmm!~ I can't wait to fuck you! You're going to love my cock!"
													:"~Mmm!~ I can't wait to have some fun with you!")+")]",
								(this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)
										|| this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH)
										?"[npc.Name] throws you a charming smile, before winking at you and striking a heroic pose. As he straightens back up, you feel a ghostly pair of arms pulling you into a strong, confident embrace."
										:"")));
			}
		}
		
		if(this.isFeminine()) {
			if(Combat.getTargetedCombatant(this).isPlayer()) {
				description = UtilText.parse(this,
						UtilText.returnStringAtRandom(
						"[npc.Name] erotically runs [npc.her] hands down [npc.her] legs and bends forwards as [npc.she] teases you, "
								+ "[npc.speech(Come on baby, I can show you a good time!)]",
						"[npc.Name] pushes out [npc.her] chest and lets out an erotic moan, "
								+ "[npc.speech(Come play with me!)]",
						"[npc.Name] slowly runs [npc.her] hands down between [npc.her] thighs, "
								+ "[npc.speech(You know you want it!)]",
						"[npc.Name] blows a kiss at you, before winking suggestively in your direction.",
						"Biting [npc.her] lip and putting on [npc.her] most smouldering look, [npc.name] runs [npc.her] hands slowly up [npc.her] inner thighs.",
						"As [npc.name] gives you [npc.her] most innocent look, [npc.she] blows you a little kiss.",
						"Turning around, [npc.name] lets out a playful giggle as [npc.she] gives [npc.her] [npc.ass+] a slap.",
						"[npc.Name] slowly runs [npc.her] [npc.hands] up the length of [npc.her] body, before pouting at you."));
				
			} else {
				description = UtilText.parse(this, Combat.getTargetedCombatant(this),
						UtilText.returnStringAtRandom(
						"[npc.Name] blows a kiss at [npc2.name], before winking suggestively in [npc2.her] direction.",
						"Biting [npc.her] lip and putting on [npc.her] most smouldering look, [npc.name] runs [npc.her] hands slowly up [npc.her] inner thighs.",
						"As [npc.name] gives [npc2.name] [npc.her] most innocent look, [npc.she] blows [npc2.herHim] a little kiss.",
						"Turning around, [npc.name] lets out a playful giggle as [npc.she] gives [npc.her] [npc.ass+] a slap.",
						"[npc.Name] slowly runs [npc.her] [npc.hands] up the length of [npc.her] body, before pouting at [npc2.name]."));
			}
			
		} else {
			if(Combat.getTargetedCombatant(this).isPlayer()) {
				description = UtilText.parse(this,
						UtilText.returnStringAtRandom(
						"[npc.Name] winks at you and flexes [npc.his] muscles, "
								+ "[npc.speech(My body's aching for your touch!)]",
						"[npc.Name] strikes a heroic pose before blowing a kiss your way, "
								+ "[npc.speech(Come on, I can show you a good time!)]",
						"[npc.Name] grins at you as [npc.he] reaches down and grabs [npc.his] crotch, "
								+ "[npc.speech(You know you want a taste of this!)]",
						"[npc.Name] blows a kiss at you, before winking suggestively in your direction.",
						"Smiling confidently at you, [npc.name] slowly runs [npc.her] hands up [npc.her] inner thighs.",
						"As [npc.name] gives you [npc.her] most seductive look, [npc.she] blows you a kiss.",
						"Turning around, [npc.name] lets out a playful laugh as [npc.she] gives [npc.her] [npc.ass+] a slap.",
						"[npc.Name] tries to look as commanding as possible as [npc.she] smirks playfully at you."));
				
			} else {
				description = UtilText.parse(this, Combat.getTargetedCombatant(this),
						UtilText.returnStringAtRandom(
						"[npc.Name] blows a kiss at [npc2.name], before winking suggestively in [npc2.her] direction.",
						"Smiling confidently at [npc2.name], [npc.name] slowly runs [npc.her] hands up [npc.her] inner thighs.",
						"As [npc.name] gives [npc2.name] [npc.her] most seductive look, [npc.she] blows [npc2.herHim] a kiss.",
						"Turning around, [npc.name] lets out a playful laugh as [npc.she] gives [npc.her] [npc.ass+] a slap.",
						"[npc.Name] tries to look as commanding as possible as [npc.she] smirks playfully at [npc2.name]."));
			}
		}
		
		return "<p>"
				+ description
				+ "</p>";
	}
	
	
	// Sex stats:
	
	public SexCount getSexCount(GameCharacter partner) {
		sexCount.putIfAbsent(partner.getId(), new SexCount());
		return sexCount.get(partner.getId());
	}
	
	public SexCount getSexCount(String partnerID) {
		sexCount.putIfAbsent(partnerID, new SexCount());
		return sexCount.get(partnerID);
	}
	
	public int getSexConsensualCount(GameCharacter partner) {
		return getSexCount(partner).getSexConsensualCount();
	}
	
	public void setSexConsensualCount(GameCharacter partner, int sexConsensualCount) {
		getSexCount(partner).setSexConsensualCount(sexConsensualCount);
	}
	
	public int getSexAsSubCount(GameCharacter partner) {
		return getSexCount(partner).getSexAsSubCount();
	}
	
	public int getTotalSexAsSubCount() {
		int i=0;
		for(SexCount count : sexCount.values()) {
			i+=count.getSexAsSubCount();
		}
		return i;
	}
	
	public void setSexAsSubCount(GameCharacter partner, int sexAsSubCount) {
		getSexCount(partner).setSexAsSubCount(sexAsSubCount);
	}
	
	public int getSexAsDomCount(GameCharacter partner) {
		return getSexCount(partner).getSexAsDomCount();
	}
	
	public int getTotalSexAsDomCount() {
		int i=0;
		for(SexCount count : sexCount.values()) {
			i+=count.getSexAsDomCount();
		}
		return i;
	}
	
	public void setSexAsDomCount(GameCharacter partner, int sexAsDomCount) {
		getSexCount(partner).setSexAsDomCount(sexAsDomCount);
	}
	
	public int getTotalTimesHadSex(GameCharacter partner) {
		return getSexCount(partner).getTotalTimesHadSex();
	}
	
	// Sex:

	public String getLostVirginityDescriptor() {
		return this.getLocationPlace().getPlaceType().getVirgintyLossDescription();
	}
	
	public void incrementSexCount(SexType sexType) {
		sexCountMap.merge(sexType, 1, Integer::sum);
	}
	public void setSexCount(SexType sexType, int integer) {
		sexCountMap.put(sexType, integer);
	}
	public int getSexCount(SexType sexType) {
		sexCountMap.putIfAbsent(sexType, 0);
		return sexCountMap.get(sexType);
	}
	
	
	public int getDaysOrgasmCountRecord() {
		if(daysOrgasmCountRecord == 0) {
			return getDaysOrgasmCount();
		}
		return daysOrgasmCountRecord;
	}
	
	public void setDaysOrgasmCountRecord(int daysOrgasmCountRecord) {
		this.daysOrgasmCountRecord = daysOrgasmCountRecord;
	}
	
	public int getDaysOrgasmCount() {
		return daysOrgasmCount;
	}
	
	public void setDaysOrgasmCount(int daysOrgasmCount) {
		this.daysOrgasmCount = daysOrgasmCount;
	}
	
	public void incrementDaysOrgasmCount(int increment) {
		daysOrgasmCount += increment;
	}
	
	public void resetDaysOrgasmCount() {
		if(getDaysOrgasmCount() > daysOrgasmCountRecord) {
			setDaysOrgasmCountRecord(getDaysOrgasmCount());
		}
		daysOrgasmCount = 0;
	}
	
	public void incrementTotalOrgasmCount(int increment) {
		totalOrgasmCount += increment;
	}
	
	public int getTotalOrgasmCount() {
		return totalOrgasmCount;
	}

	public void setTotalOrgasmCount(int totalOrgasmCount) {
		this.totalOrgasmCount = totalOrgasmCount;
	}

	// Cum:
	public void incrementCumCount(SexType sexType) {
		cumCountMap.merge(sexType, 1, Integer::sum);
	}
	
	public void setCumCount(SexType sexType, int integer) {
		cumCountMap.put(sexType, integer);
	}
	
	public int getCumCount(SexType sexType) {
		cumCountMap.putIfAbsent(sexType, 0);
		return cumCountMap.get(sexType);
	}
	
	public int getTotalCumCountInArea(SexAreaInterface area, boolean includeGiven, boolean includeTaken) {
		int total = 0;
		for(Entry<SexType, Integer> count : cumCountMap.entrySet()) {
			if(count.getKey().getPerformingSexArea()==area) {
				if(includeTaken) {
					total+=count.getValue();
				}
			}
			if(count.getKey().getTargetedSexArea()==area) {
				if(includeGiven) {
					total+=count.getValue();
				}
			}
		}
		return total;
	}
	
	public int getTotalCumCount(boolean includeGiven, boolean includeTaken) {
		int total = 0;
		for(Entry<SexType, Integer> count : cumCountMap.entrySet()) {
			if(count.getKey().getPerformingSexArea()==SexAreaPenetration.PENIS) {
				if(includeGiven) {
					total+=count.getValue();
				}
			}
			if(count.getKey().getTargetedSexArea()==SexAreaPenetration.PENIS) {
				if(includeTaken) {
					total+=count.getValue();
				}
			}
		}
		return total;
	}

	// Virginity:
	
	public void setVirginityLoss(SexType sexType, GameCharacter characterTakingVirginity, String description) {
		virginityLossMap.put(sexType, new SimpleEntry<>(characterTakingVirginity.getId(), description));
	}
	
	public void setVirginityLoss(SexType sexType, String characterTakingVirginityId, String description) {
		virginityLossMap.put(sexType, new SimpleEntry<>(characterTakingVirginityId, description));
	}
	
	public void resetVirginityLoss(SexType sexType) {
		virginityLossMap.remove(sexType);
	}
	
	public Entry<String, String> getVirginityLoss(SexType sexType) {
		return virginityLossMap.get(sexType);
	}
	
	public Map<SexType, Entry<String, String>> getVirginityLossMap() {
		return virginityLossMap;
	}
	
	
	public String getVirginityLossDescription(SexType sexType) {
		
		if(this.getVirginityLoss(sexType)==null || this.getVirginityLoss(sexType).getKey()==null) {
			return "someone";
		}
		
		if(this.getVirginityLoss(sexType).getKey()!=null && this.getVirginityLoss(sexType).getKey().isEmpty()) { // Support for versions prior to 0.2.10
			return virginityLossMap.get(sexType).getValue();
			
		} else {
			try {
				return UtilText.parse(Main.game.getNPCById(this.getVirginityLoss(sexType).getKey()),
						"[npc.name] "+virginityLossMap.get(sexType).getValue());
			} catch(Exception e) {
				System.err.println("Main.game.getNPCById("+this.getVirginityLoss(sexType).getKey()+") returning null in method: getVirginityLossDescription()");
				return "someone";
			}
		}
	}
	
	// ****************** Sex & Dirty talk: ***************************
	
	public String getCondomEquipEffects(GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex() && !target.isPlayer()) {
			if(Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual()) {
				return "<p>"
						+ "Holding out a condom to [npc.name], you force [npc.herHim] to take it and put it on."
						+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] whines at you,"
						+ " [npc.speech(Do I really have to? It feels so much better without one...)]"
						+ "</p>";
			} else {
				return "<p>"
						+ "Holding out a condom to [npc.name], you let out a sigh of relief as [npc.she] reluctantly takes it."
						+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] growls at you,"
						+ " [npc.speech(You'd better be glad that I'm in a good mood!)]"
						+ "</p>";
			}
		}
		return AbstractClothingType.getEquipDescriptions(target, equipper, rough,
				"You tear open the packet and roll the condom down the length of your [pc.penis].",
				"You tear open the packet and roll the condom down the length of [npc.namePos] [npc.penis].",
				"You tear open the packet and forcefully roll the condom down the length [npc.namePos] [npc.penis].",
				"[npc.Name] tears open the packet and rolls the condom down the length of [npc.her] [npc.penis].",
				"[npc.Name] tears open the packet and rolls the condom down the length of your [pc.penis].",
				"[npc.Name] tears open the packet and forcefully rolls the condom down the length of your [pc.penis].", null, null);
	}
	
	/**
	 * @return A <b>formatted</b> piece of speech, reacting to any current penetration.
	 */
	public String getDirtyTalk() {
		if(!Main.game.isInSex()) {
			return "";
		
		} else {
			boolean isPlayerDom = Sex.isDom(Main.game.getPlayer());
			List<String> speech = new ArrayList<>();
			String s = "";
			GameCharacter target = Sex.getTargetedPartner(this);
			
			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				if(Sex.getCharacterContactingSexArea(this, orifice).contains(target)) {
					switch(orifice) {
						case ANUS:
							s = getDirtyTalkAssPenetrated(target, isPlayerDom);
							break;
						case ASS:
							s = null;
							break;
						case BREAST:
							s = getDirtyTalkBreastsPenetrated(target, isPlayerDom);
							break;
						case MOUTH:
							s = getDirtyTalkMouthPenetrated(target, isPlayerDom);
							break;
						case NIPPLE:
							s = getDirtyTalkNipplePenetrated(target, isPlayerDom);
							break;
						case THIGHS:
							s = null;
							break;
						case URETHRA_PENIS:
							s = null;
							break;
						case URETHRA_VAGINA:
							s = null;
							break;
						case VAGINA:
							s = getDirtyTalkVaginaPenetrated(target, isPlayerDom);
							break;
					}
				}
				if(s!=null && !s.isEmpty()) {
					if(!Sex.getCharacterContactingSexArea(this, orifice).contains(this)) {
						speech.add(s);
					}
				}
			}
		
			for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
				if(Sex.getCharacterContactingSexArea(this, penetration).contains(target)) {
					switch(penetration) {
						case FINGER:
							s = getDirtyTalkFingerPenetrating(target, isPlayerDom);
							break;
						case PENIS:
							s = getDirtyTalkPenisPenetrating(target, isPlayerDom);
							break;
						case TAIL:
							s = getDirtyTalkTailPenetrating(target, isPlayerDom);
							break;
						case TENTACLE:
							s = getDirtyTalkTentaclePenetrating(target, isPlayerDom);
							break;
						case TONGUE:
							s = getDirtyTalkTonguePenetrating(target, isPlayerDom);
							break;
						case CLIT:
							s = getDirtyTalkClitPenetrating(target, isPlayerDom);
							break;
						case FOOT:
							s = getDirtyTalkToesPenetrating(target, isPlayerDom);
							break;
					}
				}
				if(s!=null && !s.isEmpty()) {
					if(!Sex.getCharactersHavingOngoingActionWith(this, penetration).contains(this) || Sex.getCharactersHavingOngoingActionWith(this, penetration).size()>1) {
						speech.add(s);
					}
				}
			}
			
			
			// Choose a random line to say:
			if(!speech.isEmpty()){
				s = speech.get(Util.random.nextInt(speech.size())); // Prefer non-self penetrative speech.
				
			} else if(Sex.isCharacterEngagedInOngoingAction(this)) {
				if(Sex.getSexPace(this)==SexPace.SUB_RESISTING) {
					s = UtilText.returnStringAtRandom(
							"Stop, please!",
							"Leave me alone!",
							"Stop it! Leave me alone!");
					
				} else {
					s = UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!");
				}
			} else {
				s = getDirtyTalkNoPenetration(Sex.getTargetedPartner(this), isPlayerDom);
			}
			
			return UtilText.parseSpeech(s, this);
		}
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this NPCs speech related to no ongoing penetration.
	 */
	public String getDirtyTalkNoPenetration(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";
		
		switch(Sex.getSexPace(this)) {
			case DOM_GENTLE:
				returnedLine = UtilText.returnStringAtRandom(
						"I'll be gentle, don't worry!",
						"You're going to be a good [npc2.girl] now, aren't you?",
						"Let's have some fun!",
						"You're going to love this!");
				break;
			case DOM_NORMAL:
				returnedLine = UtilText.returnStringAtRandom(
						"This is going to be good!",
						"How best to use you, I wonder...",
						"You're going to be a good [npc2.girl]!",
						"Ready for some fun?");
				break;
			case DOM_ROUGH:
				returnedLine = UtilText.returnStringAtRandom(
						"You ready to get fucked, slut?",
						"I'm going to fuck you senseless!",
						"You're my bitch now, understand?!",
						"I'm going to use you however I want, you fucking slut!");
				break;
			case SUB_EAGER:
				if(this.isVaginaVirgin() && this.hasVagina()) {
					returnedLine = UtilText.returnStringAtRandom(
							"Come on, fuck me already! Take my virginity!",
							"I'm still a virgin! Please, break me in already!",
							"What are you waiting for?! Fuck my virgin pussy already!",
							"I want you be my first! Pop my cherry!",
							"Have my virginity! I beg you!",
							"I'm so horny! Please, fuck my pussy! Take my virginity!",
							Util.SplitR("I'm so horny! :Finally! :This is it! :I've been waiting for this for so long! ::", ":") + Util.SplitR("Come on, fuck me already!:What are you waiting for?!:Please, fuck my [this.vagina]!", ":") + " " + Util.SplitR("I'm still a virgin!:I want you be my first!:Have my virginity!:Break me in already!:Pop my cherry!", ":") );
				} else if(this.isPenisVirgin() && this.hasPenis()) {
					returnedLine = Util.SplitR("I'm so horny! :Finally! :This is it! :I've been waiting for this for so long! ::", ":") + Util.SplitR("Come on, fuck me already!:What are you waiting for?!:Please, fuck my [this.penis]!", ":") + " " + Util.SplitR("I'm still a virgin!:I want you be my first!:Have my virginity!:Take my v-card!:Pop my cherry!", ":");
				} else {
					returnedLine = UtilText.returnStringAtRandom(
							"Come on, fuck me already! Please!",
							"Fuck me! Please!",
							"What are you waiting for?! Come on, fuck me!",
							"I'm so horny! Please, fuck me!");
				}
				break;
			case SUB_NORMAL:
				if(this.isVaginaVirgin() && this.hasVagina() || this.isPenisVirgin() && this.hasPenis()) {
					returnedLine = UtilText.returnStringAtRandom(
							"I'll be a good [npc1.girl]! Just... I'm still a virgin, okay?",
							"I'll do whatever you want! I'm still a virgin though...",
							"Let's get started! But... I'm still a virgin...",
							"Let's have some fun! But... I'm still a virgin, okay?");
				} else {
					returnedLine = UtilText.returnStringAtRandom(
							"I'll be a good [npc.girl]!",
							"I'll do whatever you want!",
							"Let's get started!",
							"Let's have some fun!");
				}
				break;
			case SUB_RESISTING:
				if(this.isVaginaVirgin() && this.hasVagina() || this.isPenisVirgin() && this.hasPenis()) {
					returnedLine = UtilText.returnStringAtRandom(
							"Go away! I-I'm still a virgin! Leave me alone!",
							"Stop it! Just go away! I-I'm still a virgin!",
							"Please stop! I don't want to lose my virginity!",
							"Don't do this! I'm still a virgin!");
				} else {
					returnedLine = UtilText.returnStringAtRandom(
							"Go away! Leave me alone!",
							"Stop it! Just go away!",
							"Please stop! Don't do this!");
				}
				break;
			default:
				returnedLine = UtilText.returnStringAtRandom(
						"This is going to be good!",
						"Time for some fun!",
						"Let's get started!",
						"Let's have some fun!");
		}
		
		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their vagina used. Returns null if no vagina or penetration found.
	 */
	public String getDirtyTalkVaginaPenetrated(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";
		
		if(getVaginaType()!=VaginaType.NONE) {
			if(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.VAGINA) != null) {
				switch(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.VAGINA)) {
					case FINGER:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right, be a good [npc2.girl] now and push your [npc2.fingers] in deeper!",
										"Good [npc2.girl]! Keep those [npc2.fingers] of yours busy!",
										"What a good [npc2.girl]! My pussy loves the feeling of your [npc2.fingers]!");
								break;
							case DOM_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right, push your [npc2.fingers] in deep!",
										"Good [npc2.girl]! Get those [npc2.fingers] in deep!",
										"Keep going! Curl your [npc2.fingers] up a bit!");
								break;
							case DOM_ROUGH:
								returnedLine = UtilText.returnStringAtRandom(
										"Come on slut, you can get your [npc2.fingers] in deeper than that!",
										"Keep it up bitch! Get those [npc2.fingers] in deep!",
										"Keep going slut! Curl your [npc2.fingers] up and put in a little more effort!");
								break;
							case SUB_EAGER:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Keep fingering me!",
										"Keep going! My pussy loves your attention!",
										"Oh yes! I love being fingered!");
								break;
							case SUB_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Keep fingering me!",
										"Keep going! I love this!",
										"Oh yes!");
								break;
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Get your [npc2.fingers] out of me! Stop! Please!",
										"Stop fingering me! Please, no more!",
										"Stop it! Stop! Please!");
								break;
							default:
								returnedLine = UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!");
								break;
						}
						break;
					case PENIS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								returnedLine = UtilText.returnStringAtRandom(
										"My pussy loves your [npc2.cock]!",
										"Good [npc2.girl]! Keep sliding that delicious cock of yours in and out of me!",
										"What a good [npc2.girl]! Enjoy my pussy as your reward now!");
								break;
							case DOM_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"You like feeling my pussy gripping down on your cock?!",
										"Good [npc2.girl]! Push your [npc2.cock] in deep!",
										"Keep going! Get that [npc2.cock] in deep like a good [npc2.girl]!");
								break;
							case DOM_ROUGH:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right slut, you're my little fuck toy now!",
										"Come on bitch! You can get your worthless [npc2.cock] in deeper than that!",
										"Fucking slut, put some more effort in! My pussy deserves better than your worthless [npc2.cock]!");
								break;
							case SUB_EAGER:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Fuck me! Fuck me harder! Don't stop!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Fuck me! I love your [npc2.cock]!");
								break;
							case SUB_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!");
								break;
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Get out of me! Stop! Please!",
										"Please, no more! Take your cock out!",
										"Get out of me! Stop! Please!");
								break;
							default:
								returnedLine = UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!");
								break;
						}
						break;
					case TAIL:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								returnedLine = UtilText.returnStringAtRandom(
										"My pussy loves your [npc2.tail]! Keep going!",
										"Good [npc2.girl]! Keep fucking me with that [npc2.tail] of yours!",
										"What a good [npc2.girl]! Enjoy my pussy as your reward now!");
								break;
							case DOM_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Oh yes! Your [npc2.tail] feels so good!",
										"Good [npc2.girl]! Push your [npc2.tail] in deep!",
										"Keep going! Get that [npc2.tail] in deep like a good [npc2.girl]!");
								break;
							case DOM_ROUGH:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right slut, get that [npc2.tail] in deep like a good little fuck toy!",
										"Come on bitch! You can get your [npc2.tail] in deeper than that!",
										"Fucking bitch, put some more effort in! My pussy deserves better than some slut's [npc2.tail]!");
								break;
							case SUB_EAGER:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Fuck me! Fuck me harder! Don't stop!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Fuck me! I love your [npc2.tail]! Get it deeper!");
								break;
							case SUB_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!");
								break;
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Get out of me! Stop! Please!",
										"Please, no more! Take your tail out!",
										"Get out of me! Stop! Please!");
								break;
							default:
								returnedLine = UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!");
								break;
						}
						break;
					case TONGUE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right, keep eating me out!",
										"Good [npc2.girl]! Keep that [npc2.tongue] of yours busy!",
										"What a good [npc2.girl]! You love the taste of my pussy, don't you?!");
								break;
							case DOM_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Oh yes! Get that [npc2.tongue] in deep!",
										"Good [npc2.girl]! Get that [npc2.tongue] of yours in deep!",
										"Keep going! My pussy loves your [npc2.tongue]!");
								break;
							case DOM_ROUGH:
								returnedLine = UtilText.returnStringAtRandom(
										"That's right slut, keep eating me out like the worthless little fuck toy you are!",
										"Come on bitch! Get that [npc2.tongue] of yours in deeper!",
										"Fucking bitch, put some more effort in! You know how lucky you are, being allowed to taste my pussy like this?!");
								break;
							case SUB_EAGER:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! I love your [npc2.tongue]! Don't stop!",
										"Don't stop! Deeper! Eat me out! Yes, yes, yes!",
										"Oh yes! Taste my pussy! I love your [npc2.tongue]! Get it deeper!");
								break;
							case SUB_NORMAL:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Don't stop!",
										"Don't stop! I love your [npc2.tongue]!",
										"Oh yes! Eat me out!");
								break;
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Get out of me! Stop! Please!",
										"Please, no more! Take your tongue out!",
										"Get out of me! Stop! Please!");
								break;
							default:
								returnedLine = UtilText.returnStringAtRandom(
										"Yes! Get that tongue deeper!",
										"Oh yeah! Keep going!",
										"Deeper! Don't stop!");
						}
						break;
					default:
						switch(Sex.getSexPace(this)) {
							case SUB_RESISTING:
								returnedLine = UtilText.returnStringAtRandom(
										"Go away! Leave me alone!",
										"Stop it! Just go away!",
										"Please stop! Don't do this!");
								break;
							default:
								returnedLine =  UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!");
								break;
						}
				}
			}
		}
		
		if(returnedLine.isEmpty()) {
			return null;
		}
		
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their asshole used. Returns null if no penetration found.
	 */
	public String getDirtyTalkAssPenetrated(GameCharacter target,  boolean isPlayerDom){
		String returnedLine = "";
		
		if(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.ANUS) != null) {
			switch(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.ANUS)) {
				case FINGER:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, be a good [npc2.girl] now and push your [npc2.fingers] in deeper!",
									"Good [npc2.girl]! Keep those [npc2.fingers] of yours busy!",
									"What a good [npc2.girl]! Keep fingering my ass!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, push your [npc2.fingers] in deep!",
									"Good [npc2.girl]! Get those [npc2.fingers] in deep!",
									"Keep going! My ass loves the attention!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Come on slut, you can get your [npc2.fingers] in deeper than that!",
									"Keep it up bitch! Get those [npc2.fingers] in deep!",
									"Keep going slut! Get your [npc2.fingers] in deep and put in a little more effort!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Keep fingering my ass! Don't stop!",
									"Keep going! My ass loves the attention!",
									"Oh yes! My ass loves being fingered!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Keep fingering my ass!",
									"Keep going! I love this!",
									"Oh yes!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop fingering my ass! Stop! Please!",
									"Please, no more! Take your fingers out of my ass!",
									"Get out of my ass! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
					break;
				case PENIS:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My ass loves your [npc2.cock]!",
									"Good [npc2.girl]! Keep sliding that delicious cock of yours in and out of my ass!",
									"What a good [npc2.girl]! Enjoy my ass as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"You like feeling my slutty little asshole gripping down on your cock?!",
									"Good [npc2.girl]! Push your [npc2.cock] in deep!",
									"Keep going! Get that [npc2.cock] in deep like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, you're my little fuck toy now!",
									"Come on bitch! You can get your worthless [npc2.cock] in deeper than that!",
									"Fucking slut, put some more effort in! My ass deserves better than your worthless [npc2.cock]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck me! Fuck me harder! Don't stop!",
									"Don't stop! Harder! Fuck me! Yes, yes, yes!",
									"Oh yes! Fuck me! I love your [npc2.cock]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck me!",
									"Don't stop! Fuck me!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your cock out of my ass!",
									"Get out of my ass! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TAIL:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My ass loves your [npc2.tail]! Keep going!",
									"Good [npc2.girl]! Keep fucking my ass with that [npc2.tail] of yours!",
									"What a good [npc2.girl]! Enjoy my ass as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Your [npc2.tail] feels so good!",
									"Good [npc2.girl]! Push your [npc2.tail] in deep!",
									"Keep going! Get that [npc2.tail] in deep like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, get that [npc2.tail] in deep like a good little fuck toy!",
									"Come on bitch! You can get your [npc2.tail] in deeper than that!",
									"Fucking bitch, put some more effort in! My ass deserves better than some slut's [npc2.tail]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck me! Fuck me harder! Don't stop!",
									"Don't stop! Harder! Fuck me! Yes, yes, yes!",
									"Oh yes! Fuck me! I love your [npc2.tail]! Get it deeper!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck me!",
									"Don't stop! Fuck me!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tail out of my ass!",
									"Get out of my ass! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TONGUE:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, keep that rimjob going like a good [npc2.girl]!",
									"Good [npc2.girl]! Keep that [npc2.tongue] of yours busy in my ass!",
									"What a good [npc2.girl]! You love the taste of my ass, don't you?!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Get that [npc2.tongue] in deep!",
									"Good [npc2.girl]! Get that [npc2.tongue] of yours in deep!",
									"Keep going! My ass loves your [npc2.tongue]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, keep that rimjob going like the worthless little fuck toy you are!",
									"Come on bitch! Get that [npc2.tongue] of yours deeper into my ass!",
									"Fucking bitch, put some more effort in! You know how lucky you are, being allowed to lick my ass like this?!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! I love your [npc2.tongue]! Don't stop!",
									"Don't stop! Deeper! Get your [npc2.tongue] into my ass! Yes, yes, yes!",
									"Oh yes! Taste my ass! I love your [npc2.tongue]! Get it deeper!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Don't stop!",
									"Don't stop! I love your [npc2.tongue]!",
									"Oh yes! Taste my ass!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tongue out of my ass!",
									"Get your tongue out of my ass! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Get that tongue deeper!",
									"Oh yeah! Keep going!",
									"Deeper! Don't stop!");
							break;
					}
					break;
				default:
					switch(Sex.getSexPace(this)) {
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Go away! Leave me alone!",
									"Stop it! Just go away!",
									"Please stop! Don't do this!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their mouth used. Returns null if no penetration found.
	 */
	public String getDirtyTalkMouthPenetrated(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";

		if(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.MOUTH) != null) {
			switch(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.MOUTH)) {
				case FINGER:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"Your fingers taste good!",
									"Good [npc2.girl]! Your [npc2.fingers] taste so good!",
									"What a good [npc2.girl]!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Your fingers taste good!",
									"Good [npc2.girl]! Your [npc2.fingers] taste so good!",
									"What a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Fucking slut, let's get your [npc2.fingers] all nice and wet now!",
									"That's right bitch! Let's get your [npc2.fingers] nice and wet!",
									"Hold still slut! I need your [npc2.fingers] all nice and lubed up!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"I love the taste of your [npc2.fingers]! Don't stop!",
									"Keep going! I love sucking on your [npc2.fingers]!",
									"Oh yes! I love the taste of your [npc2.fingers]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"I love the taste of your [npc2.fingers]!",
									"I love sucking on your [npc2.fingers]!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your fingers out of my mouth!",
									"Get out of my mouth! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
					break;
				case PENIS:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"I love your [npc2.cock]!",
									"Good [npc2.girl]! Your delicious cock deserves this nice reward!",
									"What a good [npc2.girl]! I hope you're enjoying your reward!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Your cock tastes so good!",
									"I love sucking your [npc2.cock]!",
									"Oh yes! Your [npc2.cock] tastes so good!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Hold still slut, be a good little fuck toy and just be thankful that I love sucking cock!",
									"Stay still bitch! You'd better be happy that your worthless [npc2.cock] is the only thing for me to suck right now!",
									"Fucking slut, hold still! I need to practice my oral skills on your worthless [npc2.cock]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! I love your [npc2.cock]! I need it! Use my throat!",
									"Don't stop! Harder! Fuck my throat! Yes, yes, yes!",
									"Oh yes! I love your [npc2.cock]! You taste so good!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"I love your [npc2.cock]! Use my throat!",
									"Fuck my throat! Oh yes!",
									"I love your [npc2.cock]! You taste so good!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your cock out of my mouth!",
									"Get your cock away from me! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"I love sucking cock!",
									"Oh yeah! I love your cock!",
									"Don't stop!");
							break;
					}
					break;
				case TAIL:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"I love sucking your [npc2.tail]!",
									"Good [npc2.girl]! Get this delicious [npc2.tail] of yours deep down my throat!",
									"What a good [npc2.girl]! I hope you're enjoying your reward!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"I love sucking your [npc2.tail]!",
									"Good [npc2.girl]! Get this delicious [npc2.tail] of yours deep down my throat!",
									"What a good [npc2.girl]! I hope you're enjoying your reward!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Hold still slut, be a good little fuck toy and just be thankful that I decided to practice oral on your [npc2.tail]!",
									"Stay still bitch! You'd better be happy that I decided to practice my cock-sucking on your [npc2.tail]!",
									"Fucking slut, hold still! I need to practice my oral skills on your [npc2.tail]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! I love your [npc2.tail]! I need it! Use my throat!",
									"Don't stop! Harder! Fuck my throat! Yes, yes, yes!",
									"Oh yes! I love your [npc2.tail]! You taste so good!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"I love your [npc2.tail]! Use my throat!",
									"Fuck my throat! Oh yes!",
									"I love your [npc2.tail]! You taste so good!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tail out of my mouth!",
									"Get your tail away from me! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"I love sucking your [npc2.tail]!",
									"Oh yeah! I love your [npc2.tail]!",
									"Don't stop!");
							break;
					}
					break;
				case TONGUE:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good bitch!",
									"Good slut! I love your [npc2.lips]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Stop kissing me like this!",
									"Get away from me! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Good [npc2.girl]!",
									"Good [npc2.girl]! I love your [npc2.lips]!",
									"Don't stop!");
							break;
					}
					break;
				default:// Self penetration:
					switch(Sex.getSexPace(this)) {
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Go away! Leave me alone!",
									"Stop it! Just go away!",
									"Please stop! Don't do this!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their nipples used. Returns null if no penetration found.
	 */
	public String getDirtyTalkNipplePenetrated(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";
		
		if(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.NIPPLE) != null) {
			switch(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.NIPPLE)) {
				case FINGER:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, be a good [npc2.girl] now and push your [npc2.fingers] deeper into my nipple!",
									"Good [npc2.girl]! Keep those [npc2.fingers] of yours busy in my breast!",
									"What a good [npc2.girl]! My nipples love the feeling of your [npc2.fingers]!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, push your [npc2.fingers] deep into my breast!",
									"Good [npc2.girl]! Get those [npc2.fingers] deep into my nipple!",
									"Keep going! Curl your [npc2.fingers] up a bit!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Come on slut, you can get your [npc2.fingers] deeper into my breast than that!",
									"Keep it up bitch! Get those [npc2.fingers] deep into my nipple!",
									"Keep going slut! Curl your [npc2.fingers] up and put in a little more effort!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Keep fingering my nipple!",
									"Keep going! My nipples love your attention!",
									"Oh yes! I love getting my nipples fingered!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Keep fingering my nipple!",
									"Keep going! I love this!",
									"Oh yes!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your fingers out of my nipple!",
									"Get out of my nipple! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
					break;
				case PENIS:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My nipples love your [npc2.cock]!",
									"Good [npc2.girl]! Keep sliding that delicious cock of yours in and out of my tits!",
									"What a good [npc2.girl]! Enjoy my tits as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"You like feeling my nipple gripping down on your cock?!",
									"Good [npc2.girl]! Push your [npc2.cock] deep into my breast!",
									"Keep going! Get that [npc2.cock] in deep like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, my tits could use some attention from a little fuck toy like you!",
									"Come on bitch! You can get your worthless [npc2.cock] deeper into my tits than that!",
									"Fucking slut, put some more effort in! My breasts deserve better than your worthless [npc2.cock]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits! Fuck them harder! Don't stop!",
									"Don't stop! Harder! Fuck my nipples! Yes, yes, yes!",
									"Oh yes! Fuck my tits! I love your [npc2.cock]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits!",
									"Don't stop! Fuck my nipples!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your cock out of my breast!",
									"Get out of my nipple! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TAIL:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My tits love your [npc2.tail]! Keep going!",
									"Good [npc2.girl]! Keep fucking my nipples with that [npc2.tail] of yours!",
									"What a good [npc2.girl]! Enjoy my tits as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Your [npc2.tail] feels so good!",
									"Good [npc2.girl]! Push your [npc2.tail] deep into my breast!",
									"Keep going! Get that [npc2.tail] deep into my tits like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, get that [npc2.tail] deep into my nipple like a good little fuck toy!",
									"Come on bitch! You can get your [npc2.tail] deeper into my tits than that!",
									"Fucking bitch, put some more effort in! My breasts deserve better than some slut's [npc2.tail]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits! Fuck them harder! Don't stop!",
									"Don't stop! Harder! Fuck my nipples! Yes, yes, yes!",
									"Oh yes! Fuck my tits! I love your [npc2.tail]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits!",
									"Don't stop! Fuck my nipples!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tail out of my nipple!",
									"Get out of my nipple! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TONGUE:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, keep sucking on my nipples like a good [npc2.girl]!",
									"Good [npc2.girl]! Keep that [npc2.tongue] of yours busy!",
									"What a good [npc2.girl]! You love my tits, don't you?!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Keep sucking on my nipples!",
									"Good [npc2.girl]! Get that [npc2.tongue] of yours deep into my nipples!",
									"Keep going! My tits love the feel of your [npc2.tongue]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, keep sucking on my tits like the worthless little fuck toy you are!",
									"Come on bitch! Get that [npc2.tongue] of yours deeper into my nipples!",
									"Fucking bitch, put some more effort in! You know how lucky you are, being allowed to suck on my tits like this?!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! I love the feeling of your lips on my tits! Don't stop!",
									"Don't stop! Suck on my tits! Yes, yes, yes!",
									"Oh yes! Lick my nipples! I love your [npc2.tongue]! Get it deeper!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Don't stop!",
									"Don't stop! I love your [npc2.tongue]!",
									"Oh yes! Suck on my tits!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Take your tongue out of my nipple!",
									"Leave me alone! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Get that tongue deeper!",
									"Oh yeah! Keep going!",
									"Deeper! Don't stop!");
							break;
					}
					break;
				default:// Self penetration:
					switch(Sex.getSexPace(this)) {
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Go away! Leave me alone!",
									"Stop it! Just go away!",
									"Please stop! Don't do this!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to having their nipples used. Returns null if no penetration found.
	 */
	public String getDirtyTalkBreastsPenetrated(GameCharacter target, boolean isPlayerDom){
		String returnedLine = "";
		
		if(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.BREAST) != null) {
			switch(Sex.getFirstContactingSexAreaPenetration(this, SexAreaOrifice.BREAST)) {
				case FINGER:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, be a good [npc2.girl] and keep fondling my breasts!",
									"Good [npc2.girl]! Keep those [npc2.hands] of yours busy with my breasts!",
									"What a good [npc2.girl]! My tits love all of this attention!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, keep fondling my breasts!",
									"Good [npc2.girl]! Keep those [npc2.hands] of yours busy with my breasts!",
									"Keep going! My tits love all of this attention!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"Come on slut, keep fondling my breasts!",
									"Keep it up bitch! Keep those [npc2.hands] of yours busy with my breasts!",
									"Keep going slut! My tits need more attention!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Keep fondling my breasts!",
									"Keep going! My tits love all this attention!",
									"Oh yes! I love getting my breasts fondled!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Keep fondling my breasts!",
									"Keep going! I love this!",
									"Oh yes!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Get away from my breasts!",
									"Get away from my breasts! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
					break;
				case PENIS:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My tits love your [npc2.cock]!",
									"Good [npc2.girl]! Keep sliding that delicious cock of yours between my tits!",
									"What a good [npc2.girl]! Enjoy my tits as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"You like feeling my tits gripping down around your cock?!",
									"Good [npc2.girl]! Push your [npc2.cock] deep between my breasts!",
									"Keep going! Get that [npc2.cock] between my tits like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, my tits could use some attention from a little fuck toy like you!",
									"Come on bitch! You can get your worthless [npc2.cock] deeper between my tits than that!",
									"Fucking slut, put some more effort in! My breasts deserve better than your worthless [npc2.cock]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits! Fuck them harder! Don't stop!",
									"Don't stop! Harder! Fuck my tits! Yes, yes, yes!",
									"Oh yes! Fuck my tits! I love your [npc2.cock]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits!",
									"Don't stop! Fuck my breasts!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Get your cock away from my breasts!",
									"Get away from me! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TAIL:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"My tits love your [npc2.tail]!",
									"Good [npc2.girl]! Keep sliding that delicious tail of yours between my tits!",
									"What a good [npc2.girl]! Enjoy my tits as your reward now!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"You like feeling my tits gripping down around your tail?!",
									"Good [npc2.girl]! Push your [npc2.tail] deep between my breasts!",
									"Keep going! Get that [npc2.tail] between my tits like a good [npc2.girl]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, my tits could use some attention from a little fuck toy like you!",
									"Come on bitch! You can get your worthless [npc2.tail] deeper between my tits than that!",
									"Fucking slut, put some more effort in! My breasts deserve better than your worthless [npc2.tail]!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits! Fuck them harder! Don't stop!",
									"Don't stop! Harder! Fuck my tits! Yes, yes, yes!",
									"Oh yes! Fuck my tits! I love your [npc2.tail]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Fuck my tits!",
									"Don't stop! Fuck my breasts!",
									"Oh yes! Fuck me!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Get your tail away from my breasts!",
									"Get away from me! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck me! Yes! Harder!",
									"Oh yeah! Fuck me!",
									"Harder! Don't stop!");
							break;
					}
					break;
				case TONGUE:
					switch(Sex.getSexPace(this)) {
						case DOM_GENTLE:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right, keep sucking on my tits like a good [npc2.girl]!",
									"Good [npc2.girl]! Keep that [npc2.tongue] of yours busy on my tits!",
									"What a good [npc2.girl]! You love my breasts, don't you?!");
							break;
						case DOM_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Oh yes! Keep sucking on my tits!",
									"Good [npc2.girl]! Keep that [npc2.tongue] of yours busy on my tits!",
									"Keep going! My tits love the feel of your [npc2.tongue]!");
							break;
						case DOM_ROUGH:
							returnedLine = UtilText.returnStringAtRandom(
									"That's right slut, keep sucking on my tits like the worthless little fuck toy you are!",
									"Come on bitch! Keep that [npc2.tongue] of yours busy on my tits!",
									"Fucking bitch, put some more effort in! You know how lucky you are, being allowed to suck on my tits like this?!");
							break;
						case SUB_EAGER:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! I love the feeling of your lips on my tits! Don't stop!",
									"Don't stop! Suck on my tits! Yes, yes, yes!",
									"Oh yes! Lick my nipples! I love your [npc2.tongue]!");
							break;
						case SUB_NORMAL:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Don't stop!",
									"Don't stop! I love your [npc2.tongue]!",
									"Oh yes! Suck on my tits!");
							break;
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Stop it! Stop! Please!",
									"Please, no more! Get away from me!",
									"Leave me alone! Stop! Please!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Yes! Keep going!",
									"Oh yeah! Keep going!",
									"Deeper! Don't stop!");
							break;
					}
					break;
				default:// Self penetration:
					switch(Sex.getSexPace(this)) {
						case SUB_RESISTING:
							returnedLine = UtilText.returnStringAtRandom(
									"Go away! Leave me alone!",
									"Stop it! Just go away!",
									"Please stop! Don't do this!");
							break;
						default:
							returnedLine = UtilText.returnStringAtRandom(
									"Fuck!",
									"Yeah!",
									"Oh yeah!");
							break;
					}
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}
	
	// Dirty talk related to penetrating areas:
	
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their fingers. Returns null if no penetration found.
	 */
	public String getDirtyTalkFingerPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.FINGER, target).isEmpty()) {
			for(SexAreaOrifice orifice : Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.FINGER, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.fingers] deep in your ass, don't you?",
										"I love fingering cute little asses like yours!",
										"What a good [npc2.girl]! Your ass loves the feeling of my [npc1.fingers], doesn't it?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.fingers] deep in your ass, don't you?!",
										"I love fingering cute little asses like yours!",
										"You like it when I curl my [npc1.fingers] up inside your ass, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! I can feel your horny little ass clenching down on my [npc1.fingers]!",
										"You love this, don't you bitch?! Feeling my [npc1.fingers] pushing deep into your slutty little asshole!",
										"That's right slut! You love having my [npc1.fingers] stuffed deep in your slutty ass!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my fingers deep inside your ass!",
										"I love giving your ass the attention it deserves!",
										"I love fingering your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love fingering your ass!",
										"I love giving your ass the attention it deserves!",
										"I love fingering your ass!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love having your [npc2.breasts] fondled like this, don't you?",
										"I love your [npc2.breasts]!",
										"What a good [npc2.girl]! Your tits love the feeling of my [npc1.fingers], don't they?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of having your [npc2.breasts] fondled, don't you?!",
										"I love your [npc2.breasts+]!",
										"You like it when I press my [npc1.fingers] into your [npc2.breasts], like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! Moaning as I grope your [npc2.breasts+]!",
										"You love this, don't you bitch?! Having your [npc2.breasts] groped and fondled like <i>this</i>!",
										"That's right slut! Your [npc2.breasts+] are mine to use however I want!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love the feel of your [npc2.breasts]!",
										"I love giving your tits the attention they deserve!",
										"I love your [npc2.breasts]!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love the feel of your [npc2.breasts]!",
										"I love giving your tits the attention they deserve!",
										"I love your [npc2.breasts]!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking on my [npc1.fingers]!",
										"That's right, keep swirling your [npc2.tongue] around my [npc1.fingers]!",
										"What a good [npc2.girl]! You love sucking on my [npc1.fingers], don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love sucking on my [npc1.fingers], don't you?!",
										"That's right, keep sucking on my [npc1.fingers]!",
										"Keep sucking on my [npc1.fingers], just like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut! Suck on my [npc1.fingers] like you would on a nice thick cock!",
										"You love this, don't you bitch?! Having my [npc1.fingers] sliding in and out of your mouth!",
										"That's right slut! Suck on my [npc1.fingers] as I stuff them deep down your throat!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Suck on my [npc1.fingers]! Just like that!",
										"I love having my [npc1.fingers] sucked! Keep going!",
										"Keep sucking my [npc1.fingers]! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking on my [npc1.fingers]!",
										"I love having my [npc1.fingers] sucked!",
										"Keep sucking my [npc1.fingers]! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.fingers] deep in your nipples, don't you?",
										"I love fingering cute little nipples like yours!",
										"What a good [npc2.girl]! Your tits love the feeling of my [npc1.fingers], don't they?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.fingers] deep in your nipples, don't you?!",
										"I love fingering cute little nipples like yours!",
										"You like it when I curl my [npc1.fingers] up inside your tits, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! Moaning as I stuff my [npc1.fingers] deep into your nipples!",
										"You love this, don't you bitch?! Feeling my [npc1.fingers] pushing deep into your tits!",
										"That's right slut! You love having my [npc1.fingers] stuffed deep in your slutty nipples!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my fingers deep inside your nipples!",
										"I love giving your tits the attention they deserve!",
										"I love fingering your nipples!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love fingering your nipples!",
										"I love giving your tits the attention they deserve!",
										"I love fingering your nipples!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.fingers] deep in your pussy, don't you?",
										"I love fingering cute little things like you!",
										"What a good [npc2.girl]! Your pussy loves the feeling of my [npc1.fingers], doesn't it?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.fingers] deep in your pussy, don't you?!",
										"I love fingering cute [npc2.girl]s like you!",
										"You like it when I curl my [npc1.fingers] up inside you, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! I can feel your horny pussy clenching down on my [npc1.fingers]!",
										"You love this, don't you bitch?! Feeling my [npc1.fingers] pushing deep into your slutty cunt!",
										"That's right slut! You love having my [npc1.fingers] stuffed deep in your slutty pussy!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my fingers deep inside your little pussy!",
										"I love giving your pussy the attention it deserves!",
										"I love fingering you!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love fingering your pussy!",
										"I love giving your pussy the attention it deserves!",
										"I love fingering you!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their penis. Returns null if no penetration found.
	 */
	public String getDirtyTalkPenisPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target).isEmpty()) {
			for(SexAreaOrifice orifice : Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.cock] slide deep into your ass!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.cock] sliding deep into your hot ass!",
										"Your ass feels so good squeezing down around my [npc1.cock]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your ass feels so good!",
										"Oh yes! Take my cock! Take it deep!",
										"Your ass was made for my cock!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my cock! Your ass belongs to me!",
										"What a horny bitch! Take my cock you filthy little butt-slut!",
										"You feel that, fuck toy?! Do you feel my cock sinking deep into your slutty little ass?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my cock! I love your ass!",
										"Don't stop! Harder! Use my cock! Yes, yes, yes!",
										"Oh yes! Use me! I love your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						if(target.hasBreasts()) {
							switch(Sex.getSexPace(this)) {
								case DOM_GENTLE:
									availableLines.add(UtilText.returnStringAtRandom(
											"Good [npc2.girl]! Feel my cock slide up between your [npc2.breasts]!",
											"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.cock] sliding up between your [npc2.breasts+]!",
											"Your [npc2.breasts] feel so good squeezing down around my [npc1.cock]!"));
									break;
								case DOM_NORMAL:
									availableLines.add(UtilText.returnStringAtRandom(
											"Fuck! Your tits feel so good to fuck!",
											"Oh yes! Wrap your tits around my cock!",
											"Your tits were made for my cock!"));
									break;
								case DOM_ROUGH:
									availableLines.add(UtilText.returnStringAtRandom(
											"That's right slut, pleasure my cock! Push your tits together and make this good for me!",
											"What a horny bitch! Using your tits to please my cock like a desperate slut!",
											"You like this, fuck toy?! Squeezing your [npc2.breasts] around my cock and pleasing me like the slut you are?!"));
									break;
								case SUB_EAGER:
									availableLines.add(UtilText.returnStringAtRandom(
											"Yes! Use my cock! I love your tits!",
											"Don't stop! Harder! Fuck me! Yes, yes, yes!",
											"Oh yes! Use me! I love your tits!"));
									break;
								case SUB_NORMAL:
									availableLines.add(UtilText.returnStringAtRandom(
											"Yes! Fuck me!",
											"Don't stop! Fuck me!",
											"Oh yes! Fuck me!"));
									break;
								case SUB_RESISTING:
									availableLines.add(UtilText.returnStringAtRandom(
											"I don't want to do this! Please let me stop!",
											"Let me go! Get off my cock!",
											"Please! Stop! I don't want this!"));
									break;
								default:
									availableLines.add(UtilText.returnStringAtRandom(
											"Fuck me! Yes! Harder!",
											"Oh yeah! Fuck me!",
											"Harder! Don't stop!"));
									break;
							}
						} else {
							switch(Sex.getSexPace(this)) {
								case DOM_GENTLE:
									availableLines.add(UtilText.returnStringAtRandom(
											"Good [npc2.girl]! Feel my cock slide over your [npc2.breasts]!",
											"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.cock] sliding over your [npc2.breasts+]!",
											"Your [npc2.breasts] feel so good squeezing down around my [npc1.cock]!"));
									break;
								case DOM_NORMAL:
									availableLines.add(UtilText.returnStringAtRandom(
											"Fuck! Your [npc2.breasts] feel so good to fuck!",
											"Oh yes! Feel my [npc1.cock] sliding over your [npc2.breasts]!",
											"Your [npc2.breasts] were made for my cock!"));
									break;
								case DOM_ROUGH:
									availableLines.add(UtilText.returnStringAtRandom(
											"That's right slut, pleasure my cock! Push your [npc2.breasts] together and make this good for me!",
											"What a horny bitch! Using your [npc2.breasts] to please my cock like a desperate slut!",
											"You like this, fuck toy?! Squeezing your [npc2.breasts] around my cock and pleasing me like the slut you are?!"));
									break;
								case SUB_EAGER:
									availableLines.add(UtilText.returnStringAtRandom(
											"Yes! Use my cock! I love your [npc2.breasts]!",
											"Don't stop! Yes, yes, yes!",
											"Oh yes! Use me! I love your [npc2.breasts]!"));
									break;
								case SUB_NORMAL:
									availableLines.add(UtilText.returnStringAtRandom(
											"Yes! Let me fuck your [npc2.breasts]!",
											"Don't stop! Oh yes!",
											"Oh yes! I love your [npc2.breasts]!"));
									break;
								case SUB_RESISTING:
									availableLines.add(UtilText.returnStringAtRandom(
											"I don't want to do this! Please let me stop!",
											"Let me go! Get off my cock!",
											"Please! Stop! I don't want this!"));
									break;
								default:
									availableLines.add(UtilText.returnStringAtRandom(
											"Fuck me! Yes! Harder!",
											"Oh yeah! Fuck me!",
											"Harder! Don't stop!"));
									break;
							}
						}
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking my cock!",
										"That's right, use your [npc2.tongue] as well! You're good at sucking cock!",
										"What a good [npc2.girl]! You love sucking my cock, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You're good at sucking cock!",
										"Oh yeah! Keep sucking my cock!",
										"Use your tongue as well! Yeah, like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Come on you slut! You can suck cock better than that!",
										"That's right bitch! Take my cock deep down your throat!",
										"Put some effort into it slut! You can suck cock better than that!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my cock! Just like that!",
										"Oh yes! Wrap those lips of yours around my cock! Keep going!",
										"Keep sucking my cock! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my cock!",
										"Wrap those lips of yours around my cock! Keep going!",
										"Keep sucking my cock! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my cock!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my cock slide deep into your breast!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.cock] sliding deep into your cute little nipple!",
										"Your cute little nipple feels so good squeezing down around my [npc1.cock]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Take my cock! Take it deep!",
										"Your tits were made for my cock!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my cock! Feel it pushing deep into your nipple!",
										"What a horny bitch! Taking my cock deep into your tit like a slut!",
										"You feel that, fuck toy?! Do you feel my cock sinking deep into your slutty little nipple?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my cock! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my cock!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.cock] slide deep into your little pussy!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.cock] sliding deep into your cute little cunt!",
										"Your cute little cunt feels so good squeezing down around my [npc1.cock]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your pussy feels so good!",
										"Oh yes! Take my cock! Take it deep!",
										"Your pussy was made for my cock!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my cock! Your pussy belongs to me!",
										"What a horny bitch! Take my cock you slut!",
										"You feel that, fuck toy?! Do you feel my cock sinking deep into your slutty little cunt?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my cock! I love your pussy!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your pussy!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their tail. Returns null if no penetration found.
	 */
	public String getDirtyTalkTailPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TAIL, target).isEmpty()) {
			for(SexAreaOrifice orifice : Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TAIL, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tail] slide deep into your cute little ass!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tail] sliding deep into your cute little ass!",
										"Your cute little ass feels so good squeezing down around my [npc1.tail]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your ass feels so good!",
										"Oh yes! Take my [npc1.tail]! Take it deep into your ass!",
										"Your ass was made for a good tail-fucking!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right bitch, feel my [npc1.tail] pushing deep into your slutty ass!",
										"What a horny slut! Now moan for me as I fuck your ass with my tail!",
										"You feel that, fuck toy?! Do you feel my [npc1.tail] sinking deep into your slutty little ass?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tail]! I love your ass!",
										"Don't stop! Harder! Use my [npc1.tail]! Yes, yes, yes!",
										"Oh yes! Use me! I love your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tail] slide up between your [npc2.breasts]!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tail] sliding up between your [npc2.breasts+]!",
										"Your [npc2.breasts] feel so good squeezing down around my [npc1.tail]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Wrap your tits around my [npc1.tail]!",
										"Your tits were made for my [npc1.tail]!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, pleasure my [npc1.tail]! Push your tits together and make this good for me!",
										"What a horny bitch! Using your tits to please my [npc1.tail] like a desperate slut!",
										"You like this, fuck toy?! Squeezing your [npc2.breasts] around my [npc1.tail] and pleasing me like the slut you are?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tail]! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my [npc1.tail]!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking my [npc1.tail]!",
										"That's right, use your [npc2.tongue] as well! You're good at this!",
										"What a good [npc2.girl]! You love sucking my [npc1.tail], don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Take my [npc1.tail] deep down your throat!",
										"Oh yeah! Keep sucking my [npc1.tail]!",
										"Use your tongue as well! Yeah, like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Come on you slut! Take my [npc1.tail] deep down your throat!",
										"That's right bitch! Take my [npc1.tail] deep down your throat!",
										"Put some effort into it slut! You can suck my [npc1.tail] better than that!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my [npc1.tail]! Just like that!",
										"Oh yes! Wrap those lips of yours around my [npc1.tail]! Keep going!",
										"Keep sucking my [npc1.tail]! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my [npc1.tail]!",
										"Wrap those lips of yours around my [npc1.tail]! Keep going!",
										"Keep sucking my [npc1.tail]! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tail] slide deep into your breast!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tail] sliding deep into your cute little nipple!",
										"Your cute little nipple feels so good squeezing down around my [npc1.tail]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Take my [npc1.tail] deep into your nipple!",
										"Your tits were made for my [npc1.tail]!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my [npc1.tail]! Feel it pushing deep into your nipple!",
										"What a horny bitch! Taking my [npc1.tail] deep into your tit like a slut!",
										"You feel that, fuck toy?! Do you feel my [npc1.tail] sinking deep into your slutty little nipple?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tail]! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tail] slide deep into your little pussy!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tail] sliding deep into your cute little cunt!",
										"Your cute little cunt feels so good squeezing down around my [npc1.tail]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your pussy feels so good!",
										"Oh yes! Take my [npc1.tail]! Take it deep!",
										"Your pussy was made for a good tail-fucking!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, feel my [npc1.tail] pushing deep into your worthless little cunt! Your pussy belongs to me!",
										"What a horny bitch! Now moan for me as I fuck you with my tail!",
										"You feel that, fuck toy?! Do you feel my [npc1.tail] sinking deep into your slutty little cunt?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tail]! I love your pussy!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your pussy!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their tail. Returns null if no penetration found.
	 */
	public String getDirtyTalkTentaclePenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TENTACLE, target).isEmpty()) {
			for(SexAreaOrifice orifice : Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TENTACLE, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tentacle] slide deep into your cute little ass!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tentacle] sliding deep into your cute little ass!",
										"Your cute little ass feels so good squeezing down around my [npc1.tentacle]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your ass feels so good!",
										"Oh yes! Take my [npc1.tentacle]! Take it deep into your ass!",
										"Your ass was made for a good tentacle-fucking!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right bitch, feel my [npc1.tentacle] pushing deep into your slutty ass!",
										"What a horny slut! Now moan for me as I fuck your ass with my tentacle!",
										"You feel that, fuck toy?! Do you feel my [npc1.tentacle] sinking deep into your slutty little ass?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tentacle]! I love your ass!",
										"Don't stop! Harder! Use my [npc1.tentacle]! Yes, yes, yes!",
										"Oh yes! Use me! I love your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tentacle] slide up between your [npc2.breasts]!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tentacle] sliding up between your [npc2.breasts+]!",
										"Your [npc2.breasts] feel so good squeezing down around my [npc1.tentacle]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Wrap your tits around my [npc1.tentacle]!",
										"Your tits were made for my [npc1.tentacle]!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, pleasure my [npc1.tentacle]! Push your tits together and make this good for me!",
										"What a horny bitch! Using your tits to please my [npc1.tentacle] like a desperate slut!",
										"You like this, fuck toy?! Squeezing your [npc2.breasts] around my [npc1.tentacle] and pleasing me like the slut you are?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tentacle]! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my [npc1.tentacle]!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking my [npc1.tentacle]!",
										"That's right, use your [npc2.tongue] as well! You're good at this!",
										"What a good [npc2.girl]! You love sucking my [npc1.tentacle], don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Take my [npc1.tentacle] deep down your throat!",
										"Oh yeah! Keep sucking my [npc1.tentacle]!",
										"Use your tongue as well! Yeah, like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Come on you slut! Take my [npc1.tentacle] deep down your throat!",
										"That's right bitch! Take my [npc1.tentacle] deep down your throat!",
										"Put some effort into it slut! You can suck my [npc1.tentacle] better than that!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my [npc1.tentacle]! Just like that!",
										"Oh yes! Wrap those lips of yours around my [npc1.tentacle]! Keep going!",
										"Keep sucking my [npc1.tentacle]! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my [npc1.tentacle]!",
										"Wrap those lips of yours around my [npc1.tentacle]! Keep going!",
										"Keep sucking my [npc1.tentacle]! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tentacle] slide deep into your breast!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tentacle] sliding deep into your cute little nipple!",
										"Your cute little nipple feels so good squeezing down around my [npc1.tentacle]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Take my [npc1.tentacle] deep into your nipple!",
										"Your tits were made for my [npc1.tentacle]!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my [npc1.tentacle]! Feel it pushing deep into your nipple!",
										"What a horny bitch! Taking my [npc1.tentacle] deep into your tit like a slut!",
										"You feel that, fuck toy?! Do you feel my [npc1.tentacle] sinking deep into your slutty little nipple?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tentacle]! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.tentacle] slide deep into your little pussy!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.tentacle] sliding deep into your cute little cunt!",
										"Your cute little cunt feels so good squeezing down around my [npc1.tentacle]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your pussy feels so good!",
										"Oh yes! Take my [npc1.tentacle]! Take it deep!",
										"Your pussy was made for a good tentacle-fucking!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, feel my [npc1.tentacle] pushing deep into your worthless little cunt! Your pussy belongs to me!",
										"What a horny bitch! Now moan for me as I fuck you with my tentacle!",
										"You feel that, fuck toy?! Do you feel my [npc1.tentacle] sinking deep into your slutty little cunt?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my [npc1.tentacle]! I love your pussy!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your pussy!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	

	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their penis. Returns null if no penetration found.
	 */
	public String getDirtyTalkClitPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.CLIT, target).isEmpty()) {
			for(SexAreaOrifice orifice : Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.CLIT, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.clit] slide deep into your ass!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.clit] sliding deep into your hot ass!",
										"Your ass feels so good squeezing down around my [npc1.clit]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your ass feels so good!",
										"Oh yes! Take my clit! Take it deep!",
										"Your ass was made for my clit!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my clit! Your ass belongs to me!",
										"What a horny bitch! Take my clit you filthy little butt-slut!",
										"You feel that, fuck toy?! Do you feel my clit sinking deep into your slutty little ass?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my clit! I love your ass!",
										"Don't stop! Harder! Use my clit! Yes, yes, yes!",
										"Oh yes! Use me! I love your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my clit slide up between your [npc2.breasts]!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.clit] sliding up between your [npc2.breasts+]!",
										"Your [npc2.breasts] feel so good squeezing down around my [npc1.clit]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Wrap your tits around my clit!",
										"Your tits were made for my clit!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, pleasure my clit! Push your tits together and make this good for me!",
										"What a horny bitch! Using your tits to please my clit like a desperate slut!",
										"You like this, fuck toy?! Squeezing your [npc2.breasts] around my clit and pleasing me like the slut you are?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my clit! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my clit!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking my clit!",
										"That's right, use your [npc2.tongue] as well! You're good at sucking clit!",
										"What a good [npc2.girl]! You love sucking my clit, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You're good at sucking clit!",
										"Oh yeah! Keep sucking my clit!",
										"Use your tongue as well! Yeah, like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Come on you slut! You can suck clit better than that!",
										"That's right bitch! Take my clit deep down your throat!",
										"Put some effort into it slut! You can suck clit better than that!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my clit! Just like that!",
										"Oh yes! Wrap those lips of yours around my clit! Keep going!",
										"Keep sucking my clit! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking my clit!",
										"Wrap those lips of yours around my clit! Keep going!",
										"Keep sucking my clit! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my clit!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my clit slide deep into your breast!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.clit] sliding deep into your cute little nipple!",
										"Your cute little nipple feels so good squeezing down around my [npc1.clit]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your tits feel so good to fuck!",
										"Oh yes! Take my clit! Take it deep!",
										"Your tits were made for my clit!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my clit! Feel it pushing deep into your nipple!",
										"What a horny bitch! Taking my clit deep into your tit like a slut!",
										"You feel that, fuck toy?! Do you feel my clit sinking deep into your slutty little nipple?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my clit! I love your tits!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get off my clit!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl]! Feel my [npc1.clit] slide deep into your little pussy!",
										"That's right, be a good [npc2.girl] and moan for me! Feel my [npc1.clit] sliding deep into your cute little cunt!",
										"Your cute little cunt feels so good squeezing down around my [npc1.clit]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck! Your pussy feels so good!",
										"Oh yes! Take my clit! Take it deep!",
										"Your pussy was made for my clit!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut, take my clit! Your pussy belongs to me!",
										"What a horny bitch! Take my clit you slut!",
										"You feel that, fuck toy?! Do you feel my clit sinking deep into your slutty little cunt?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Use my clit! I love your pussy!",
										"Don't stop! Harder! Fuck me! Yes, yes, yes!",
										"Oh yes! Use me! I love your pussy!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Fuck me!",
										"Don't stop! Fuck me!",
										"Oh yes! Fuck me!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck me! Yes! Harder!",
										"Oh yeah! Fuck me!",
										"Harder! Don't stop!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their fingers. Returns null if no penetration found.
	 */
	public String getDirtyTalkToesPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.FOOT, target).isEmpty()) {
			for(SexAreaOrifice orifice : Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.FOOT, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.toes] deep in your ass, don't you?",
										"I love foot-fucking cute little asses like yours!",
										"What a good [npc2.girl]! Your ass loves the feeling of my [npc1.toes], doesn't it?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.toes] deep in your ass, don't you?!",
										"I love foot-fucking cute little asses like yours!",
										"You like it when I curl my [npc1.toes] up inside your ass, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! I can feel your horny little ass clenching down on my [npc1.toes]!",
										"You love this, don't you bitch?! Feeling my [npc1.toes] pushing deep into your slutty little asshole!",
										"That's right slut! You love having my [npc1.toes] stuffed deep in your slutty ass!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my toes deep inside your ass!",
										"I love giving your ass the attention it deserves!",
										"I love foot-fucking your ass!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love foot-fucking your ass!",
										"I love giving your ass the attention it deserves!",
										"I love foot-fucking your ass!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love having your [npc2.breasts] fondled like this, don't you?",
										"I love your [npc2.breasts]!",
										"What a good [npc2.girl]! Your tits love the feeling of my [npc1.toes], don't they?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of having your [npc2.breasts] fondled, don't you?!",
										"I love your [npc2.breasts+]!",
										"You like it when I press my [npc1.toes] into your [npc2.breasts], like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! Moaning as I grope your [npc2.breasts+]!",
										"You love this, don't you bitch?! Having your [npc2.breasts] groped and fondled like <i>this</i>!",
										"That's right slut! Your [npc2.breasts+] are mine to use however I want!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love the feel of your [npc2.breasts]!",
										"I love giving your tits the attention they deserve!",
										"I love your [npc2.breasts]!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love the feel of your [npc2.breasts]!",
										"I love giving your tits the attention they deserve!",
										"I love your [npc2.breasts]!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], keep sucking on my [npc1.toes]!",
										"That's right, keep swirling your [npc2.tongue] around my [npc1.toes]!",
										"What a good [npc2.girl]! You love sucking on my [npc1.toes], don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love sucking on my [npc1.toes], don't you?!",
										"That's right, keep sucking on my [npc1.toes]!",
										"Keep sucking on my [npc1.toes], just like that!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right slut! Suck on my [npc1.toes] like you would on a nice thick cock!",
										"You love this, don't you bitch?! Having my [npc1.toes] sliding in and out of your mouth!",
										"That's right slut! Suck on my [npc1.toes] as I stuff them deep down your throat!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Suck on my [npc1.toes]! Just like that!",
										"I love having my [npc1.toes] sucked! Keep going!",
										"Keep sucking my [npc1.toes]! Yes! Just like that!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep sucking on my [npc1.toes]!",
										"I love having my [npc1.toes] sucked!",
										"Keep sucking my [npc1.toes]! Yes!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.toes] deep in your nipples, don't you?",
										"I love foot-fucking cute little nipples like yours!",
										"What a good [npc2.girl]! Your tits love the feeling of my [npc1.toes], don't they?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.toes] deep in your nipples, don't you?!",
										"I love foot-fucking cute little nipples like yours!",
										"You like it when I curl my [npc1.toes] up inside your tits, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! Moaning as I stuff my [npc1.toes] deep into your nipples!",
										"You love this, don't you bitch?! Feeling my [npc1.toes] pushing deep into your tits!",
										"That's right slut! You love having my [npc1.toes] stuffed deep in your slutty nipples!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my toes deep inside your nipples!",
										"I love giving your tits the attention they deserve!",
										"I love foot-fucking your nipples!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love foot-fucking your nipples!",
										"I love giving your tits the attention they deserve!",
										"I love foot-fucking your nipples!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Good [npc2.girl], you love feeling my [npc1.toes] deep in your pussy, don't you?",
										"I love foot-fucking cute little things like you!",
										"What a good [npc2.girl]! Your pussy loves the feeling of my [npc1.toes], doesn't it?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"You love the feeling of my [npc1.toes] deep in your pussy, don't you?!",
										"I love foot-fucking cute [npc2.girl]s like you!",
										"You like it when I curl my [npc1.toes] up inside you, like <i>this</i>?!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"What a dirty slut! I can feel your horny pussy clenching down on my [npc1.toes]!",
										"You love this, don't you bitch?! Feeling my [npc1.toes] pushing deep into your slutty cunt!",
										"That's right slut! You love having my [npc1.toes] stuffed deep in your slutty pussy!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Let me get my toes deep inside your little pussy!",
										"I love giving your pussy the attention it deserves!",
										"I love foot-fucking you!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love foot-fucking your pussy!",
										"I love giving your pussy the attention it deserves!",
										"I love foot-fucking you!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Fuck!",
										"Yeah!",
										"Oh yeah!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this character's speech related to penetrating someone using their tongue. Returns null if no penetration found.
	 */
	public String getDirtyTalkTonguePenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TONGUE, target).isEmpty()) {
			for(SexAreaOrifice orifice : Sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TONGUE, target)) {
				switch(orifice) {
					case ANUS:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right, moan for me as I pleasure your ass!",
										"Good [npc2.girl]! I love licking cute little asses like yours!",
										"What a good [npc2.girl]! You love my tongue in your ass, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"That's right, moan for me as I lick your ass!",
										"Feel my tongue deep in your ass! Moan for me!",
										"You love my tongue in your ass, don't you?"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Keep still slut, I need to practice my oral skills on your worthless ass!",
										"Stay still bitch! Just keep moaning and enjoying this while it lasts!",
										"You'd better appreciate this bitch! You know how lucky you are, being used as oral practice?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love your ass! Feel my [npc1.tongue] pushing deep!",
										"Oh yes! Let me lick your ass! Yes, yes, yes!",
										"Oh yes! I love licking ass! Let me get my [npc1.tongue] nice and deep!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love licking your ass!",
										"Let me lick your ass! I love this!",
										"I love licking your ass!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! Get your ass away from my face!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Get that tongue deeper!",
										"Oh yeah! Keep going!",
										"Deeper! Don't stop!"));
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your tits taste so good!",
										"Good [npc2.girl]! I love the taste of your tits!",
										"What a good [npc2.girl]! You love having your tits kissed like this, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Oh yes! Your tits taste so good!",
										"You like this? Feeling my tongue running over your breasts?!",
										"I love the taste of your tits!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Keep still slut, and be thankful that I'm giving your tits some attention!",
										"Stay still bitch! Just keep moaning and enjoying this while it lasts!",
										"You'd better appreciate this bitch! You know how lucky you are, having me lick your tits?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love your tits! Let me suck on your nipples!",
										"Oh yes! Let me suck on your nipples! Yes, yes, yes!",
										"Oh yes! I love your tits!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love your tits! Let me suck on your nipples!",
										"Oh yes! Let me suck on your nipples!",
										"I love your tits!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Keep going!",
										"Oh yeah! Keep going!",
										"Don't stop!"));
								break;
						}
						break;
					case MOUTH:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your lips taste so good!",
										"Good [npc2.girl]! Don't stop!",
										"What a good [npc2.girl]!"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your lips taste so good!",
										"Good [npc2.girl]! Don't stop!",
										"What a good [npc2.girl]!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Come on slut, you can kiss better than that!",
										"Put some more effort into this bitch! Kiss me like you mean it!",
										"Fucking slut, you can kiss me better than this! Put some more effort into it!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Oh yes! Your lips taste so good! I need more!",
										"I love kissing you! Yes, yes, yes!",
										"Oh yes! Your lips taste so good!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your lips taste so good! I need more!",
										"I love kissing you!",
										"Your lips taste so good!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your lips taste so good!",
										"I love kissing you!",
										"Don't stop!"));
								break;
						}
						break;
					case NIPPLE:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your nipples taste so good!",
										"Good [npc2.girl]! I love the taste of your tits!",
										"What a good [npc2.girl]! You love having my tongue in your nipple, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Oh yes! Your tits taste so good!",
										"You like this? Feeling my tongue deep in your hot little nipple?!",
										"I love the taste of your tits!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Keep still slut, I need to practice my skills on fuckable nipples like yours!",
										"Stay still bitch! Just keep moaning and enjoying this while it lasts!",
										"You'd better appreciate this bitch! You know how lucky you are, having me lick your nipples?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love your tits! Let me suck on your nipples!",
										"Oh yes! Let me suck on your nipples! Yes, yes, yes!",
										"Oh yes! I love your nipples! Let me get my [npc1.tongue] nice and deep!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love your tits! Let me suck on your nipples!",
										"Oh yes! Let me suck on your nipples!",
										"I love your nipples! Let me get my [npc1.tongue] nice and deep!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please let me stop!",
										"Let me go! I don't want to do this!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Get that tongue deeper!",
										"Oh yeah! Keep going!",
										"Deeper! Don't stop!"));
								break;
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						switch(Sex.getSexPace(this)) {
							case DOM_GENTLE:
								availableLines.add(UtilText.returnStringAtRandom(
										"Your pussy tastes so good!",
										"Good [npc2.girl]! I love the taste of your pussy!",
										"What a good [npc2.girl]! You love my tongue in your pussy, don't you?"));
								break;
							case DOM_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"Oh yes! Your pussy tastes so good!",
										"You like this? Feeling my tongue deep in your hot little cunt?!",
										"I love the taste of your pussy!"));
								break;
							case DOM_ROUGH:
								availableLines.add(UtilText.returnStringAtRandom(
										"Keep still slut, I need to practice my oral skills on you!",
										"Stay still bitch! Just keep moaning and enjoying this while it lasts!",
										"You'd better appreciate this bitch! You know how lucky you are, being used as oral practice?!"));
								break;
							case SUB_EAGER:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! I love your pussy! You taste so good!",
										"Oh yes! Let me eat you out! Yes, yes, yes!",
										"Oh yes! I love the taste of your pussy! Let me get my [npc1.tongue] nice and deep!"));
								break;
							case SUB_NORMAL:
								availableLines.add(UtilText.returnStringAtRandom(
										"I love your pussy! You taste so good!",
										"Let me eat you out! You taste good!",
										"I love the taste of your pussy!"));
								break;
							case SUB_RESISTING:
								availableLines.add(UtilText.returnStringAtRandom(
										"I don't want to do this! Please stop!",
										"Let me go! Get your pussy away from my face!",
										"Please! Stop! I don't want this!"));
								break;
							default:
								availableLines.add(UtilText.returnStringAtRandom(
										"Yes! Get that tongue deeper!",
										"Oh yeah! Keep going!",
										"Deeper! Don't stop!"));
								break;
						}
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
	
	
	// Area reveals: TODO All reveals need to take in character being revealed

	public String getAssRevealDescription(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {

		SexPace pace = SexPace.DOM_NORMAL;
		if(Main.game.isInSex()) {
			pace = Sex.getSexPace(charactersReacting.get(0));
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(characterBeingRevealed.isPlayer()) {
			sb.append("<p>");
			switch(pace) {
				case DOM_GENTLE:
					sb.append(UtilText.parse(charactersReacting.get(0), "[npc.Name] lets out a soft [npc.moan] as your [pc.asshole+] is revealed."));
					break;
				case DOM_NORMAL:
					sb.append(UtilText.parse(charactersReacting.get(0), "[npc.Name] lets out [npc.a_moan+] as your [pc.asshole+] is revealed."));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.parse(charactersReacting.get(0), "[npc.Name] lets out a hungry growl as your [pc.asshole+] is revealed."));
					break;
				case SUB_EAGER:
					sb.append(UtilText.parse(charactersReacting.get(0), "[npc.Name] lets out [npc.a_moan+] as your [pc.asshole+] is revealed."));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.parse(charactersReacting.get(0), "[npc.Name] lets out [npc.a_moan] as your [pc.asshole+] is revealed."));
					break;
				case SUB_RESISTING:
					sb.append(UtilText.parse(charactersReacting.get(0), "[npc.Name] lets out [npc.a_sob+] as your [pc.asshole+] is revealed."));
					break;
				default:
					sb.append(UtilText.parse(charactersReacting.get(0), "[npc.Name] lets out [npc.a_moan] as your [pc.asshole+] is revealed."));
					break;
			}
			sb.append("</p>");
			
		}
		
		return sb.toString();
	}

	public String getBreastsRevealDescription(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		
		if(characterBeingRevealed.isPlayer()) {

			SexPace pace = SexPace.DOM_NORMAL;
			if(Main.game.isInSex()) {
				pace = Sex.getSexPace(charactersReacting.get(0));
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			
			GameCharacter npcReacting = charactersReacting.get(0);
			
			if(!Sex.isConsensual() && pace!=SexPace.SUB_RESISTING) {
				if(!Sex.isDom(this)) {
					// Feminine NPC:
					if(npcReacting.isFeminine()) {
						if (characterBeingRevealed.isFeminine()) {
							if (!characterBeingRevealed.hasBreasts()) {
								sb.append("[npc2.Name] struggles to stifle a mocking laugh as [npc.namePos] flat chest is revealed, "
										+ "[npc2.speech(Pfft-hahaha!)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								if(this.getBreastSize().getMeasurement() >= characterBeingRevealed.getBreastSize().getMeasurement()) {
									sb.append("[npc2.Name] [npc2.verb(put)] on a patronising smile as [npc.namePos] [pc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Aww... They're pretty cute!)]");
									
								} else {
									sb.append("[npc2.Name] [npc2.verb(shuffle)] about in embarrassment as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(They're so much bigger than mine...)]");
								}
								
							} else {
								sb.append("[npc2.NamePos] jaw drops as [npc.namePos] [npc.breastSize] breasts are revealed, "
										+ "[npc2.speech(How did you manage to get your tits to be that huge?!)]");
							}
							
						} else {
							if (!characterBeingRevealed.hasBreasts()) {
								sb.append("");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								sb.append("In a very patronising voice, [npc2.name] [npc2.verb(react)] to [npc.namePos] breasts being revealed, "
										+ "[npc2.speech(Aww, you trying to become a girl?)]");
			
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								if(this.getBreastSize().getMeasurement() >= characterBeingRevealed.getBreastSize().getMeasurement()) {
									sb.append("[npc2.Name] [npc2.verb(gasp)] in surprise as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Why would a guy have tits like that?)]");
									
								} else {
									sb.append("[npc2.Name] [npc2.verb(fail)] to contain [npc2.her] surprise as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(A <i>guy</i> has bigger tits than me?!)]");
								}
			
							} else {
								sb.append("[npc2.NamePos] jaw drops as [npc.namePos] [npc.breastSize] breasts are revealed, "
										+ "[npc2.speech(How did you manage to get your tits to be that huge?!)]");
							}
							
						}
						
					// Masculine NPC:
					} else {
						if (characterBeingRevealed.isFeminine()) {
							if (!characterBeingRevealed.hasBreasts()) {
								sb.append("[npc2.Name] [npc2.verb(struggle)] to stifle a mocking laugh as [npc.namePos] flat chest is revealed, "
										+ "[npc2.speech(Pfft-hahaha!)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(let)] out a disappointed hum as [npc.namePos] [npc.breastSize] breasts are revealed, "
										+ "[npc2.speech(Huh... They're pretty small you know...)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								sb.append("[npc2.NamePos] eyes light up as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Oh fuck yeah... Look at the size of those tits!)]");
								
							} else {
								sb.append("[npc2.NamePos] jaw drops as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(How did you manage to get your tits to be that huge?!)]");
							}
							
						} else {
							if (!characterBeingRevealed.hasBreasts()) {
								sb.append("");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								sb.append("In a mocking tone, [npc2.name] [npc2.verb(question)] [npc.name] as [npc.her] tiny breasts are revealed, "
											+ "[npc2.speech(Hah, you trying to become a girl?)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.FF.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(let)] out a surprised gasp as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Why would a guy have tits like that?)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(fail)] to contain [npc2.her] surprise as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(What's a <i>guy</i> doing with such massive tits?!)]");
								
							} else {
								sb.append("[npc2.NamePos] jaw drops as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(How did you manage to get your tits to be that huge?!)]");
							}
							
						}
					}
					
				} else {
					// Feminine NPC:
					if(this.isFeminine()) {
						if (characterBeingRevealed.isFeminine()) {
							if (!characterBeingRevealed.hasBreasts()) {
								sb.append("[npc2.Name] [npc2.verb(let)] out a mocking laugh as [npc.namePos] flat chest is revealed, "
										+ "[npc2.speech(Hahaha, I don't think I've ever seen a girl with a chest <i>that</i> flat before!)]");
			
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								if(this.getBreastSize().getMeasurement() >= characterBeingRevealed.getBreastSize().getMeasurement()) {
									sb.append("[npc2.Name] [npc2.verb(grin)] down at [npc.name] as [npc.her] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Aww, look at those tiny little things, how cute!)]");
									
								} else {
									sb.append("[npc2.Name] [npc2.verb(let)] out an annoyed huff as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Are you trying to put me to shame or something?!)]");
								}
			
							} else {
								sb.append("[npc2.NamePos] jaw drops as [npc.namePos] [npc.breastSize] breasts are revealed, "
										+ "[npc2.speech(How did you manage to get your tits to be that huge?!)]");
							}
			
						} else {
							if (!characterBeingRevealed.hasBreasts()) {
								sb.append("");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(grin)] at [npc.name] as [npc.her] [npc.breastSize] breasts are revealed, "
										+ "[npc2.speech(Aww, you trying to become a girl?)]");
			
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								if(this.getBreastSize().getMeasurement() >= characterBeingRevealed.getBreastSize().getMeasurement()) {
									sb.append("[npc2.Name] [npc2.verb(let)] out a surprised gasp as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Why would a guy have tits like that?)]");
									
								} else {
									sb.append("[npc2.Name] [npc2.verb(let)] out an annoyed huff as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Are you kidding me?! A <i>guy</i> has bigger tits than me?!)]");
								}
			
							} else {
								sb.append("[npc2.NamePos] jaw drops as [npc.namePos] [npc.breastSize] breasts are revealed, "
										+ "[npc2.speech(How did you manage to get your tits to be that huge?!)]");
							}
							
						}
						
					// Masculine NPC:
					} else {
						if (characterBeingRevealed.isFeminine()) {
							if (!characterBeingRevealed.hasBreasts()) {
								sb.append("[npc2.Name] [npc2.verb(let)] out a mocking laugh as [npc.namePos] flat chest is revealed, "
											+ "[npc2.speech(Hahaha, I don't think I've ever seen a girl with a chest <i>that</i> flat before!)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(growl)] at [npc.name] as [npc.her] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(I like my girls with bigger tits than that!)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.FF.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(grin)] as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Mmm yeah, those are some nice tits!)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(let)] out a delighted hum as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Oh fuck yeah! Look at the size of those things!)]");
								
							} else {
								sb.append("[npc2.NamePos] jaw drops as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(How did you manage to get [npc.namePos] tits to be that huge?! What a fucking tit-cow!)]");
							}
							
						} else {
							if (!characterBeingRevealed.hasBreasts()) {
								sb.append("");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.C.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(burst)] out laughing as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Hahaha, you trying to become a girl?!)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.FF.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(let)] out a surprised gasp as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Why would a guy have tits like that?!)]");
								
							} else if (characterBeingRevealed.getBreastRawSizeValue() <= CupSize.JJ.getMeasurement()) {
								sb.append("[npc2.Name] [npc2.verb(let)] out a mocking laugh as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(Are you kidding me?! Why does a <i>guy</i> have tits like that?!)]");
								
							} else {
								sb.append("[npc2.NamePos] jaw drops as [npc.namePos] [npc.breastSize] breasts are revealed, "
											+ "[npc2.speech(How did you manage to get your tits to be that huge?!)]");
							}
						}
					}
				}
				
			} else {
				switch(pace) {
					case DOM_GENTLE:
						sb.append("[npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.namePos] [npc.breasts+] are revealed.");
						break;
					case DOM_NORMAL:
						sb.append("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.breasts+] are revealed.");
						break;
					case DOM_ROUGH:
						sb.append("[npc2.Name] [npc2.verb(let)] out a hungry growl as [npc.namePos] [npc.breasts+] are revealed.");
						break;
					case SUB_EAGER:
						sb.append("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.breasts+] are revealed.");
						break;
					case SUB_NORMAL:
						sb.append("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan] as [npc.namePos] [npc.breasts+] are revealed.");
						break;
					case SUB_RESISTING:
						sb.append("[npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.namePos] [npc.breasts+] are revealed.");
						break;
				}
			}

			sb.append("</p>");
			return UtilText.parse(characterBeingRevealed, npcReacting, sb.toString());
			
		} else {
			return "";
		}
	}

	public String getPenisRevealDescription(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		
		SexPace pace = SexPace.DOM_NORMAL;
		if(Main.game.isInSex()) {
			pace = Sex.getSexPace(charactersReacting.get(0));
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
		
		if(characterBeingRevealed.isPlayer()) {
			if(pace!=SexPace.SUB_RESISTING) {
				if(characterBeingRevealed.getPenisType()==PenisType.DILDO) {
					sb.append("[npc2.Name] grins as [npc2.she] sees that you're wearing a strap-on. "
							+ "[npc2.speech(Looking to have a little extra fun, huh?)]"
						+ "</p>");
					
				} else {
					switch(characterBeingRevealed.getPenisSize()) {
						case NEGATIVE_UTILITY_VALUE:
						case ZERO_MICROSCOPIC:
						case ONE_TINY:
							if(this.isFeminine()) {
								if(this.isKnowsCharacterArea(CoverableArea.PENIS, characterBeingRevealed)) {
									sb.append(UtilText.returnStringAtRandom(
											"[npc2.Name] fails to suppress a mocking giggle your tiny [npc.cock] is revealed, ",
											"[npc2.Name] lets out a little giggle as your tiny [npc.cock] is revealed, ",
											"[npc2.Name] lets out a derisive sneer as your tiny [npc.cock] is revealed, "));
									sb.append(UtilText.returnStringAtRandom(
											"[npc2.speech(Your little clitty dick is as cute as ever!)]",
											"[npc2.speech(That's just so unbelievably pathetic!)]",
											"[npc2.speech(What a pathetic little cock! I mean, can I even call it a cock?! It's more like a little clit!)]"));
								} else {
									sb.append(UtilText.returnStringAtRandom(
											"[npc2.Name] fails to suppress a mocking giggle as [npc2.she] sees that you've got a tiny [npc.cock] between your [npc.legs], ",
											"[npc2.Name] lets out a surprised laugh as your tiny [npc.cock] is revealed, ",
											"[npc2.Name] lets out a derisive laugh as [npc2.she] sees your tiny [npc.cock], "));
									if(characterBeingRevealed.getAppearsAsGender()!=characterBeingRevealed.getGender()) {
										sb.append(UtilText.returnStringAtRandom(
												"[npc2.speech(Aww, that's so cute! I didn't realise you were [npc.a_gender]!)]",
												"[npc2.speech(Wait, you're [npc.a_gender]?! What a pathetic little clitty dick you've got!)]"));
									} else {
										sb.append(UtilText.returnStringAtRandom(
												"[npc2.speech(Aww, that's so cute! I love it when [npc.gender]s have little clitty dicks like that!)]",
												"[npc2.speech(Aww, that's such a cute little clitty dick, for such a cute little [npc.gender]!)]"));
									}
								}
								
							} else {
								if(this.isKnowsCharacterArea(CoverableArea.PENIS, characterBeingRevealed)) {
									sb.append(UtilText.returnStringAtRandom(
											"[npc2.Name] fails to suppress a booming, mocking laugh your tiny [npc.cock] is revealed, ",
											"[npc2.Name] lets out a grunt as your tiny [npc.cock] is revealed, ",
											"[npc2.Name] lets out a derisive grunt as your tiny [npc.cock] is revealed, "));
									sb.append(UtilText.returnStringAtRandom(
											"[npc2.speech(That thing's more like a clit than a real cock!)]",
											"[npc2.speech(That's just so unbelievably pathetic!)]",
											"[npc2.speech(Your cock is as pathetic as ever! Hah!)]"));
								} else {
									sb.append(UtilText.returnStringAtRandom(
											"[npc2.Name] lets out a booming, mocking laugh your tiny [npc.cock] is revealed, ",
											"[npc2.Name] lets out a surprised grunt as your tiny [npc.cock] is revealed, ",
											"[npc2.Name] lets out a derisive grunt as [npc2.she] sees that you've got a tiny [npc.cock] between your [npc.legs], "));
									if(characterBeingRevealed.getAppearsAsGender()!=characterBeingRevealed.getGender()) {
										sb.append(UtilText.returnStringAtRandom(
												"[npc2.speech(Is that pathetic little thing your cock?! I didn't realise you were [npc.a_gender]!)]",
												"[npc2.speech(Wait, you're [npc.a_gender]?! What a pathetic excuse for a cock you've got!)]"));
									} else {
										sb.append(UtilText.returnStringAtRandom(
												"[npc2.speech(Is that pathetic little thing your cock?! Well, I guess you <i>are</i> a [npc.gender]...)]",
												"[npc2.speech(Such a pathetic little clitty dick, for such a helpless little [npc.gender]!)]"));
									}
								}
							}
							break;
						case TWO_AVERAGE:
						case THREE_LARGE:
							if(this.isKnowsCharacterArea(CoverableArea.PENIS, characterBeingRevealed)) {
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.Name] lets out a hungry [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out a delighted [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out a happy [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, "));
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.speech(That's a nice cock you've got!)]",
										"[npc2.speech(Oh yeah, bring that over here!)]",
										"[npc2.speech(Come on, put that cock of yours to use!)]"));
							} else {
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.Name] fails to suppress a flustered [npc2.moan] as [npc2.she] sees that you've got a [npc.cockSize] [npc.cock] between your [npc.legs], ",
										"[npc2.Name] lets out a surprised [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out a startled [npc2.moan] as [npc2.she] sees your [npc.cockSize] [npc.cock], "));
								if(characterBeingRevealed.getAppearsAsGender()!=characterBeingRevealed.getGender()) {
									sb.append(UtilText.returnStringAtRandom(
											"[npc2.speech(Hey! I didn't realise you were [npc.a_gender]! Well, whatever...)]",
											"[npc2.speech(Wait, you're [npc.a_gender]?! Well, whatever...)]"));
								} else {
									sb.append(UtilText.returnStringAtRandom(
											"[npc2.speech(That's a nice cock... For [npc.a_gender], that is...)]",
											"[npc2.speech(Mmm, nice cock, I guess...)]"));
								}
							}
							break;
						case FOUR_HUGE:
						case FIVE_ENORMOUS:
							if(this.isKnowsCharacterArea(CoverableArea.PENIS, characterBeingRevealed)) {
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.Name] lets out a hungry [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out a delighted [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out a happy [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, "));
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.speech(Your cock's so huge! I'll never get tired of seeing it bounce free like that...)]",
										"[npc2.speech(Oh yeah, bring that huge cock of yours over to me!)]",
										"[npc2.speech(Oh yeah, your cock's just so huge, I'll never get tired of it!)]"));
							} else {
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.Name] fails to suppress [npc2.a_moan] as [npc2.she] sees that you've got a [npc.cockSize] [npc.cock] between your [npc.legs], ",
										"[npc2.Name] lets out [npc2.a_moan] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out [npc2.a_moan] as [npc2.she] sees your [npc.cockSize] [npc.cock], "));
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.speech(That thing's huge! I mean, I could tell it was from your bulge, but damn...)]",
										"[npc2.speech(Holy shit! I could see it was big from your bulge, but wow...)]"));
							}
							break;
						case SIX_GIGANTIC:
						case SEVEN_STALLION:
							if(this.isKnowsCharacterArea(CoverableArea.PENIS, characterBeingRevealed)) {
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.Name] lets out a hungry [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out a delighted [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out a happy [npc2.moan] as your [npc.cockSize] [npc.cock] is revealed, "));
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.speech(I can't believe how massive your cock is! I doubt anyone's got a bigger one than you...)]",
										"[npc2.speech(Oh yeah, bring that gigantic cock of yours over to me!)]",
										"[npc2.speech(Your cock's so huge! I don't think I'll ever get tired of it!)]"));
							} else {
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.Name] fails to suppress [npc2.a_moan+] as [npc2.she] sees that you've got a [npc.cockSize] [npc.cock] between your [npc.legs], ",
										"[npc2.Name] lets out [npc2.a_moan+] as your [npc.cockSize] [npc.cock] is revealed, ",
										"[npc2.Name] lets out [npc2.a_moan+] as [npc2.she] sees your [npc.cockSize] [npc.cock], "));
								sb.append(UtilText.returnStringAtRandom(
										"[npc2.speech(What?! Holy shit! Your cock's <i>huge</i>!)]",
										"[npc2.speech(Holy shit! I could see it was huge from your bulge, but... well... it's <i>massive</i>!)]"));
							}
							break;
					}
				}
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
								"[npc2.Name] lets out [npc2.a_sob+] as your [npc.penis+] is revealed.",
								"[npc2.speech(No! Please! Get away from me!)] [npc2.name] screams as your [npc.penis+] is revealed."));
			}
			
			
		} else {
			if(characterBeingRevealed.getPenisType()==PenisType.DILDO) {
				sb.append("[npc.Name] grins as [npc.she] reveals the fact that [npc.sheIs] wearing a strap-on. "
						+ "[npc.speech(Time for a little extra fun!)]");
			} else {
				if(this.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) || !isFeminine()) {
					switch(pace) {
						case DOM_GENTLE:
								sb.append("[npc.Name] lets out a soft [npc.moan] as [npc.her] [npc.cock+] is revealed.");
								break;
						case DOM_NORMAL:
							sb.append("[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.cock+].");
							break;
						case DOM_ROUGH:
							sb.append("[npc.Name] grins as [npc.she] sees you staring at [npc.her] [npc.cock+].");
							break;
						case SUB_EAGER:
							sb.append("[npc.Name] lets out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.cock+].");
							break;
						case SUB_NORMAL:
							sb.append("[npc.Name] lets out [npc.a_moan] as [npc.she] sees you staring at [npc.her] [npc.cock+].");
							break;
						case SUB_RESISTING:
							sb.append("[npc.Name] lets out [npc.a_sob] and tries to pull away from you as [npc.her] [npc.cock+] is revealed.");
							break;
					}
				
				} else {
					switch(pace) {
						case DOM_GENTLE:
							sb.append("[npc.Name] lets out a soft giggle as [npc.she] sees you staring at [npc.her] [npc.cock+],"
										+ " [npc.speech(Let's have some fun!)]");
							break;
						case DOM_NORMAL:
							sb.append("[npc.Name] lets out a playful giggle as [npc.she] sees you staring at [npc.her] [npc.cock+],"
										+ " [npc.speech(That look on your face is priceless! Now let's have some fun!)]");
							break;
						case DOM_ROUGH:
							sb.append("[npc.Name] lets out a laugh as [npc.she] sees you staring at [npc.her] [npc.cock+],"
										+ " [npc.speech(Hah! I bet you didn't expect this!)]");
							break;
						case SUB_EAGER:
							sb.append("[npc.Name] lets out a playful giggle as [npc.she] sees you staring at [npc.her] [npc.cock+],"
										+ " [npc.speech(That look on your face is priceless! Now let's have some fun!)]");
							break;
						case SUB_NORMAL:
							sb.append("[npc.Name] lets out [npc.a_moan] as [npc.she] sees you staring at [npc.her] [npc.cock+],"
										+ " [npc.speech(Let's have some fun!)]");
							break;
						case SUB_RESISTING:
							sb.append("[npc.Name] lets out [npc.a_sob] and tries to pull away from you as [npc.her] [npc.cock+] is revealed,"
										+ " [npc.speech(Leave me alone!)]");
							break;
					}
				}
			}
		}
		sb.append("</p>");
		
		return UtilText.parse(characterBeingRevealed, charactersReacting.get(0), sb.toString());
	}

	public String getVaginaRevealDescription(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {

		SexPace pace = SexPace.DOM_NORMAL;
		if(Main.game.isInSex()) {
			pace = Sex.getSexPace(charactersReacting.get(0));
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
		
		if(characterBeingRevealed.isPlayer()) {
			switch(pace) {
				case DOM_GENTLE:
					sb.append(UtilText.parse(characterBeingRevealed, charactersReacting.get(0),
								"[npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(see)] "
									+ (Sex.hasLubricationTypeFromAnyone(characterBeingRevealed, SexAreaOrifice.VAGINA, LubricationType.GIRLCUM)
											? "[npc.namePos] wet [npc.pussy] betraying [npc.her] arousal, "
											: "[npc.namePos] [npc.pussy+], ")
									+ (this.hasPenis()
											?"[npc2.speech(You're going to love this, I promise...)]"
											:"[npc2.speech(I'll make this feel good, I promise...)]")));
					break;
				case DOM_NORMAL:
					sb.append(UtilText.parse(characterBeingRevealed, charactersReacting.get(0),
							"[npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(see)] "
									+ (Sex.hasLubricationTypeFromAnyone(characterBeingRevealed, SexAreaOrifice.VAGINA, LubricationType.GIRLCUM)
											? "[npc.namePos] wet [npc.pussy] betraying [npc.her] arousal, "
											: "[npc.namePos] [npc.pussy+], ")
									+ (this.hasPenis()
											?"[npc2.speech(You're going to be a good fuck!)]"
											:"[npc2.speech(This is going to be fun!)]")));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.parse(characterBeingRevealed, charactersReacting.get(0),
							"[npc2.Name] [npc2.verb(smirk)] when [npc2.she] [npc2.verb(see)] "
									+ (Sex.hasLubricationTypeFromAnyone(characterBeingRevealed, SexAreaOrifice.VAGINA, LubricationType.GIRLCUM)
											? "[npc.namePos] wet [npc.pussy] betraying [npc.her] arousal, "
											: "[npc.namePos] [npc.pussy+], ")
									+ (this.hasPenis()
											?"[npc2.speech(Ready for a good hard fucking, slut?)]"
											:"[npc2.speech(Looking good, slut!)]")));
					break;
				case SUB_EAGER:
					sb.append(UtilText.parse(characterBeingRevealed, charactersReacting.get(0),
							"[npc2.NamePos] eyes light up when [npc2.she] [npc2.verb(see)] "
							+ (Sex.hasLubricationTypeFromAnyone(characterBeingRevealed, SexAreaOrifice.VAGINA, LubricationType.GIRLCUM)
									? "[npc.namePos] wet [npc.pussy] betraying [npc.her] arousal."
									: "[npc.namePos] [npc.pussy].")));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.parse(characterBeingRevealed, charactersReacting.get(0),
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan] as [npc.namePos] [npc.pussy+] is revealed."));
					break;
				case SUB_RESISTING:
					sb.append(UtilText.parse(characterBeingRevealed, charactersReacting.get(0),
							"[npc2.Name] [npc2.verb(try)] to pull away from [npc.name] as "
							+ (Sex.hasLubricationTypeFromAnyone(characterBeingRevealed, SexAreaOrifice.VAGINA, LubricationType.GIRLCUM)
									? "[npc.namePos] wet [npc.pussy] is revealed."
									: "[npc.namePos] [npc.pussy+] is revealed.")));
					break;
			}
			
		} else if(this.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) || !charactersReacting.contains(Main.game.getPlayer()) || isFeminine()) {
			switch(pace) {
				case DOM_GENTLE:
					sb.append(UtilText.parse(characterBeingRevealed, "[npc.Name] lets out a soft [npc.moan] as [npc.her] [npc.pussy+] is revealed."));
					break;
				case DOM_NORMAL:
					sb.append(UtilText.parse(characterBeingRevealed, "[npc.Name] lets out an excited [npc.moan] as [npc.her] [npc.pussy+] is revealed."));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.parse(characterBeingRevealed, "[npc.Name] grins as [npc.her] [npc.pussy+] is revealed."));
					break;
				case SUB_EAGER:
					sb.append(UtilText.parse(characterBeingRevealed, "[npc.Name] lets out an excited [npc.moan] as [npc.her] [npc.pussy+] is revealed."));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.parse(characterBeingRevealed, "[npc.Name] lets out [npc.a_moan] as [npc.her] [npc.pussy+] is revealed."));
					break;
				case SUB_RESISTING:
					sb.append(UtilText.parse(characterBeingRevealed, "[npc.Name] lets out [npc.a_sob] and tries to pull away from you as [npc.her] [npc.pussy+] is revealed."));
					break;
			}
			
		} else {
			switch(pace) {
				case DOM_GENTLE:
					sb.append(UtilText.parse(characterBeingRevealed,
							"[npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.her] [npc.pussy+] is revealed,"
								+ " [npc.speech(Hah! I bet you didn't expect this!)]"));
					break;
				case DOM_NORMAL:
					sb.append(UtilText.parse(characterBeingRevealed,
							"[npc.Name] [npc.verb(let)] out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
								+ " [npc.speech(Hah! I bet you didn't expect this!)]"));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.parse(characterBeingRevealed,
							"[npc.Name] [npc.verb(grin)] as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
								+ " [npc.speech(Hah! I bet you didn't expect this!)]"));
					break;
				case SUB_EAGER:
					sb.append(UtilText.parse(characterBeingRevealed,
							"[npc.Name] [npc.verb(let)] out an excited [npc.moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
								+ " [npc.speech(Hah! I bet you didn't expect this!)]"));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.parse(characterBeingRevealed,
							"[npc.Name] [npc.verb(let)] out [npc.a_moan] as [npc.she] sees you staring at [npc.her] [npc.pussy+],"
								+ " [npc.speech(Hah! I bet you didn't expect this!)]"));
					break;
				case SUB_RESISTING:
					sb.append(UtilText.parse(characterBeingRevealed,
							"[npc.Name] [npc.verb(let)] out [npc.a_sob] and tries to pull away from you as [npc.her] [npc.pussy+] is revealed,"
								+ " [npc.speech(Leave me alone!)]"));
					break;
			}
		}
		sb.append("</p>");
		return sb.toString();
	}

	public String getMoundRevealDescription(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		
		SexPace pace = SexPace.DOM_NORMAL;
		if(Main.game.isInSex()) {
			pace = Sex.getSexPace(charactersReacting.get(0));
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(characterBeingRevealed.isPlayer()) {
			sb.append("<p>");
			if(pace!=SexPace.SUB_RESISTING) {
				if(isFeminine()) {
					if (!Sex.isDom(this)) {
						sb.append("[npc2.Name] looks confused for a moment before letting out a patronising sigh, "
								+ "[npc2.speech(Awww... You're like a little doll down there! That's so cute!)]");
					} else {
						sb.append("[npc2.Name] looks confused for a moment before breaking out into a mocking laugh, "
								+ "[npc2.speech(Hahaha! You're like a little doll down there!)]");
					}
				// Masculine NPC:
				} else {
					if(!Sex.isDom(this)) {
						sb.append("[npc2.Name] looks confused for a moment before letting out a patronising sneer, "
								+ "[npc2.speech(Awww... You're like a little doll down there! That's so cute!)]");
					} else {
						sb.append("[npc2.Name] looks confused for a moment before breaking out into a mocking laugh, "
									+ "[npc2.speech(Hahaha! You're like a little doll down there!)]");
					}
				}
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
								"[npc2.Name] lets out [npc2.a_sob+] as your genderless mound is revealed.",
								"[npc2.speech(Stop! Please! Get away from me!)] [npc2.name] screams as you reveal your genderless mound."));
			}
			sb.append("</p>");
			
			return UtilText.parse(characterBeingRevealed, charactersReacting.get(0), sb.toString());
		}
		
		return "";
	}


	// Penetrations:
	
	private static String generateGenericPenetrationDescription(GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
		
		// Kissing:
		if(penetrationType == SexAreaPenetration.TONGUE && orifice == SexAreaOrifice.MOUTH) {
			if(characterPenetrating.isPlayer()) {
				switch(Sex.getSexPace(characterPenetrating)) {
					case DOM_GENTLE:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"Your soft [npc.moans] are muffled into [npc2.namePos] mouth as you continue kissing [npc2.herHim].",
								"You gently press your [npc.lips+] against [npc2.namePos] as you continue kissing [npc2.herHim].",
								"Gently pressing your [npc.lips+] against [npc2.namePos], you continue making out with [npc2.herHim]."));
					case DOM_NORMAL:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"Your [npc.moans+] are muffled into [npc2.namePos] mouth as you continue passionately kissing [npc2.herHim].",
								"You eagerly press your [npc.lips+] against [npc2.namePos] as you continue passionately kissing [npc2.herHim].",
								"Passionately pressing your [npc.lips+] against [npc2.namePos], you continue making out with [npc2.herHim]."));
					case DOM_ROUGH:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"Your [npc.moans+] are muffled into [npc2.namePos] mouth as you continue forcefully snogging [npc2.herHim].",
								"You roughly grind your [npc.lips+] against [npc2.namePos] as you continue forcefully snogging [npc2.herHim].",
								"Roughly grinding your [npc.lips+] against [npc2.namePos], you continue making out with [npc2.herHim]."));
					case SUB_EAGER:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"Your [npc.moans+] are muffled into [npc2.namePos] mouth as you continue passionately kissing [npc2.herHim].",
								"You eagerly press your [npc.lips+] against [npc2.namePos] as you continue passionately kissing [npc2.herHim].",
								"Passionately pressing your [npc.lips+] against [npc2.namePos], you continue making out with [npc2.herHim]."));
					case SUB_NORMAL:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"Your [npc.moans] are muffled into [npc2.namePos] mouth as you continue kissing [npc2.herHim].",
								"You press your [npc.lips+] against [npc2.namePos] as you continue kissing [npc2.herHim].",
								"Pressing your [npc.lips+] against [npc2.namePos], you continue making out with [npc2.herHim]."));
					case SUB_RESISTING:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"Your [npc.sobs+] are muffled into [npc2.namePos] mouth as you desperately try to push [npc2.herHim] away from you.",
								"You try to pull your [npc.lips+] away from [npc2.namePos] as you struggle against [npc2.herHim].",
								"Trying to pull your [npc.lips+] away from [npc2.namePos], you continue struggling against [npc2.her] unwanted kiss."));
				}
				
			} else if(characterPenetrated.isPlayer()) {
				switch(Sex.getSexPace(characterPenetrating)) {
					case DOM_GENTLE:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] soft [npc.moans] are muffled into your mouth as [npc.she] continues kissing you.",
								"[npc.Name] gently presses [npc.her] [npc.lips+] against yours as [npc.she] continues kissing you.",
								"Gently pressing [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you."));
					case DOM_NORMAL:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.moans+] are muffled into your mouth as [npc.she] continues passionately kissing you.",
								"[npc.Name] eagerly presses [npc.her] [npc.lips+] against yours as [npc.she] continues passionately kissing you.",
								"Passionately pressing [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you."));
					case DOM_ROUGH:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.moans+] are muffled into your mouth as [npc.she] continues forcefully snogging you.",
								"[npc.Name] roughly grinds [npc.her] [npc.lips+] against yours as [npc.she] continues forcefully snogging you.",
								"Roughly grinding [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you."));
					case SUB_EAGER:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.moans+] are muffled into your mouth as [npc.she] continues passionately kissing you.",
								"[npc.Name] eagerly presses [npc.her] [npc.lips+] against yours as [npc.she] continues passionately kissing you.",
								"Passionately pressing [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you."));
					case SUB_NORMAL:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.moans] are muffled into your mouth as [npc.she] continues kissing you.",
								"[npc.Name] presses [npc.her] [npc.lips+] against yours as [npc.she] continues kissing you.",
								"Pressing [npc.her] [npc.lips+] against yours, [npc.name] continues making out with you."));
					case SUB_RESISTING:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.sobs+] are muffled into your mouth as [npc.she] desperately tries to push you away from [npc.herHim].",
								"[npc.Name] tries to pull [npc.her] [npc.lips+] away from yours as [npc.she] struggles against you.",
								"Trying to pull [npc.her] [npc.lips+] away from yours, [npc.name] continues struggling against your unwanted kiss."));
				}
				
			} else {
				switch(Sex.getSexPace(characterPenetrating)) {
					case DOM_GENTLE:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] soft [npc.moans] are muffled into [npc2.namePos] mouth as [npc.she] continues kissing [npc2.herHim].",
								"[npc.Name] gently presses [npc.her] [npc.lips+] against [npc2.namePos] as [npc.she] continues kissing [npc2.herHim].",
								"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos], [npc.name] continues making out with [npc2.herHim]."));
					case DOM_NORMAL:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.moans+] are muffled into [npc2.namePos] mouth as [npc.she] continues passionately kissing [npc2.herHim].",
								"[npc.Name] eagerly presses [npc.her] [npc.lips+] against [npc2.namePos] as [npc.she] continues passionately kissing [npc2.herHim].",
								"Passionately pressing [npc.her] [npc.lips+] against [npc2.namePos], [npc.name] continues making out with [npc2.herHim]."));
					case DOM_ROUGH:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.moans+] are muffled into [npc2.namePos] mouth as [npc.she] continues forcefully snogging [npc2.herHim].",
								"[npc.Name] roughly grinds [npc.her] [npc.lips+] against [npc2.namePos] as [npc.she] continues forcefully snogging [npc2.herHim].",
								"Roughly grinding [npc.her] [npc.lips+] against [npc2.namePos], [npc.name] continues making out with [npc2.herHim]."));
					case SUB_EAGER:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.moans+] are muffled into [npc2.namePos] mouth as [npc.she] continues passionately kissing [npc2.herHim].",
								"[npc.Name] eagerly presses [npc.her] [npc.lips+] against [npc2.namePos] as [npc.she] continues passionately kissing [npc2.herHim].",
								"Passionately pressing [npc.her] [npc.lips+] against [npc2.namePos], [npc.name] continues making out with [npc2.herHim]."));
					case SUB_NORMAL:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.moans] are muffled into [npc2.namePos] mouth as [npc.she] continues kissing [npc2.herHim].",
								"[npc.Name] presses [npc.her] [npc.lips+] against [npc2.namePos] as [npc.she] continues kissing [npc2.herHim].",
								"Pressing [npc.her] [npc.lips+] against [npc2.namePos], [npc.name] continues making out with [npc2.herHim]."));
					case SUB_RESISTING:
						return UtilText.parse(characterPenetrating, characterPenetrated,
								UtilText.returnStringAtRandom(
								"[npc.NamePos] [npc.sobs+] are muffled into [npc2.namePos] mouth as [npc.she] desperately tries to push away from [npc2.herHim].",
								"[npc.Name] tries to pull [npc.her] [npc.lips+] away from [npc2.namePos] as [npc.she] struggles against [npc2.herHim].",
								"Trying to pull [npc.her] [npc.lips+] away from [npc2.namePos], [npc.name] continues struggling against the unwanted kiss."));
				}
			}
		}
		
		String orificeName="";
		String penetratorName="";
		
		switch(penetrationType) {
			case FINGER:
				penetratorName = "[npc.fingers]";
				if(orifice == SexAreaPenetration.PENIS) {
					penetratorName = UtilText.returnStringAtRandom("[npc.fingers]", "hand");
				}
				break;
			case PENIS:
				penetratorName = "[npc.penis+]";
				break;
			case TAIL:
				penetratorName = "[npc.tail+]";
				break;
			case TENTACLE:
				break;
			case TONGUE:
				penetratorName = "[npc.tongue]";
				break;
			case CLIT:
				penetratorName = "[npc.clit+]";
				break;
			case FOOT:
				penetratorName = "[npc.toes]";
				break;
		}
		
		if(orifice.isOrifice()) {
			switch((SexAreaOrifice)orifice) {
				case ANUS:
					orificeName = "[npc2.asshole+]";
					break;
				case ASS:
					orificeName = "[npc2.ass+]";
					break;
				case MOUTH:
					orificeName = "mouth";
					break;
				case NIPPLE:
					orificeName = "[npc2.nipple+]";
					break;
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					orificeName = "urethra";
					break;
				case VAGINA:
					orificeName = "[npc2.pussy+]";
					break;
				case THIGHS:
					orificeName = "thighs";
					break;
				case BREAST:
					orificeName = "[npc2.breasts+]";
					break;
			}
		} else {
			switch((SexAreaPenetration)orifice) {
				case CLIT:
					orificeName = "[npc2.clit+]";
					break;
				case FINGER:
					orificeName = "[npc2.fingers+]";
					break;
				case PENIS:
					orificeName = "[npc2.cock+]";
					break;
				case TAIL:
					orificeName = "[npc2.tail+]";
					break;
				case TENTACLE:
					orificeName = "[npc2.tentacle+]";
					break;
				case FOOT:
					if(Sex.getSexPositionSlot(characterPenetrating).isStanding()) {
						orificeName = "[npc2.foot+]";
					} else {
						orificeName = "[npc2.feet+]";
					}
					break;
				case TONGUE:
					orificeName = "[npc2.tongue+]";
					break;
			}
		}
		
		String penetratingQualifier = "";
		String penetratingAction = "";
		
		String penetratingPrefix = "";
		String penetratedPrefix = "";
		String penetratedPostfix = "";
		
		switch(Sex.getSexPace(characterPenetrating)) {
			case DOM_GENTLE:
				penetratingQualifier = UtilText.returnStringAtRandom("gently", "slowly", "steadily");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slide", "pump", "thrust")
						:UtilText.returnStringAtRandom("slides", "pumps", "thrusts");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] drifts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] drifts out from between [npc.namePos] [npc.lips+]");
			break;
			case DOM_NORMAL:
				penetratingQualifier = UtilText.returnStringAtRandom("eagerly", "enthusiastically", "readily", "happily");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slide", "push", "drive", "thrust", "pump")
						:UtilText.returnStringAtRandom("slides", "pushes", "drives", "thrusts", "pumps");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] bursts out from between [npc.namePos] [npc.lips+]");
			break;
			case DOM_ROUGH:
				penetratingQualifier = UtilText.returnStringAtRandom("roughly", "forcefully", "mercilessly");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slam", "hammer", "thrust", "pump", "piston")
						:UtilText.returnStringAtRandom("slams", "hammers", "thrusts", "pumps", "pistons");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] bursts out from between [npc.namePos] [npc.lips+]");
			break;
			case SUB_EAGER:
				penetratingQualifier = UtilText.returnStringAtRandom("desperately", "frantically", "eagerly", "enthusiastically");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slam", "hammer", "thrust", "pump")
						:UtilText.returnStringAtRandom("slams", "hammers", "thrusts", "pumps");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] bursts out from between [npc.namePos] [npc.lips+]");
			break;
			case SUB_NORMAL:
				penetratingQualifier = UtilText.returnStringAtRandom("happily", "willingly");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slide", "push", "drive", "thrust", "pump")
						:UtilText.returnStringAtRandom("slides", "pushes", "drives", "thrusts", "pumps");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] drifts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] drifts out from between [npc.namePos] [npc.lips+]");
			break;
			case SUB_RESISTING:
				penetratingQualifier = UtilText.returnStringAtRandom("reluctantly", "half-heartedly", "hesitantly");
				penetratingAction = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("slide", "push", "drive")
						:UtilText.returnStringAtRandom("slides", "pushes", "drives");
				penetratingPrefix = characterPenetrating.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc.Name] lets out [npc.a_moan+]", "[npc.A_moan+] bursts out from between [npc.namePos] [npc.lips+]");
			break;
		}
		
		switch(Sex.getSexPace(characterPenetrated)) {
			case DOM_GENTLE:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] drifts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("causing you to let out [pc.a_moan+]", "causing [pc.a_moan+] to drift out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("causing [npc2.herHim] to let out [npc2.a_moan+]", "causing [npc2.a_moan+] to drift out from between [npc2.her] [npc2.lips+]");
				break;
			case DOM_NORMAL:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("causing you to let out [pc.a_moan+]", "causing [pc.a_moan+] to burst out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("causing [npc2.herHim] to let out [npc2.a_moan+]", "causing [npc2.a_moan+] to burst out from between [npc2.her] [npc2.lips+]");
				break;
			case DOM_ROUGH:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("causing you to let out [pc.a_moan+]", "causing [pc.a_moan+] to burst out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("causing [npc2.herHim] to let out [npc2.a_moan+]", "causing [npc2.a_moan+] to burst out from between [npc2.her] [npc2.lips+]");
				break;
			case SUB_EAGER:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] bursts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("causing you to let out [pc.a_moan+]", "causing [pc.a_moan+] to burst out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("causing [npc2.herHim] to let out [npc2.a_moan+]", "causing [npc2.a_moan+] to burst out from between [npc2.her] [npc2.lips+]");
				break;
			case SUB_NORMAL:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You let out [pc.a_moan+]", "[pc.A_moan+] drifts out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("[npc2.Name] lets out [npc2.a_moan+]", "[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+]");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("causing you to let out [pc.a_moan+]", "causing [pc.a_moan+] to drift out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("causing [npc2.herHim] to let out [npc2.a_moan+]", "causing [npc2.a_moan+] to drift out from between [npc2.her] [npc2.lips+]");
				break;
			case SUB_RESISTING:
				penetratedPrefix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("You struggle and try to protest", "You attempt to push [npc2.name] away", "You let out a protesting whine")
						:UtilText.returnStringAtRandom("[npc2.Name] struggles and tries to protest", "[npc2.Name] attempts to push you away", "[npc2.Name] lets out a protesting whine");
				penetratedPostfix = characterPenetrated.isPlayer()
						?UtilText.returnStringAtRandom("causing you to let out [pc.a_moan+]", "causing [pc.a_moan+] to burst out from between your [pc.lips+]")
						:UtilText.returnStringAtRandom("causing [npc2.herHim] to let out [npc2.a_moan+]", "causing [npc2.a_moan+] to burst out from between [npc2.her] [npc2.lips+]");
				break;
		}
		
		String penetrationDescription = UtilText.returnStringAtRandom(
				"in and out of",
				"deep into");
		
		if(orifice.isOrifice()) {
			switch((SexAreaOrifice)orifice) {
				case ASS:
					penetrationDescription = UtilText.returnStringAtRandom(
							"between the cheeks of",
							"in and out between the cheeks of");
					break;
				case BREAST:
				case THIGHS:
					penetrationDescription = UtilText.returnStringAtRandom(
							"between",
							"in and out between");
					break;
				case ANUS:
				case MOUTH:
				case NIPPLE:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
				case VAGINA:
					break;
			}
		} else {
			switch((SexAreaPenetration)orifice) {
				case PENIS:
					if(penetrationType==SexAreaPenetration.FINGER) {
						penetrationDescription = UtilText.returnStringAtRandom(
								"up and down around",
								"up and down");
						
					} else if(penetrationType==SexAreaPenetration.FOOT) {
						if(Sex.getSexPositionSlot(characterPenetrating).isStanding()) {
							penetrationDescription = UtilText.returnStringAtRandom(
									"up and down against",
									"against");
						} else {
							penetrationDescription = UtilText.returnStringAtRandom(
									"up and down around");
						}
					}
					break;
				case FINGER:
				case CLIT:
				case TAIL:
				case TENTACLE:
				case FOOT:
				case TONGUE:
					penetrationDescription = UtilText.returnStringAtRandom(
							"over");
					break;
			}
		}
		
		// PC penetrating NPC:
		if(characterPenetrating.isPlayer() && !characterPenetrated.isPlayer()) {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratedPrefix+" as you "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" "+penetrationDescription+" [npc2.her] "+orificeName+".",
					penetratedPrefix+" as you "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" "+penetrationDescription+" [npc2.her] "+orificeName+".",
					"You "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" "+penetrationDescription+" [npc2.namePos] "+orificeName+", "+penetratedPostfix+".",
					"You "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" "+penetrationDescription+" [npc2.namePos] "+orificeName+", "+penetratedPostfix+"."));
			
		// NPC penetrating PC:
		} else if(!characterPenetrating.isPlayer() && characterPenetrated.isPlayer()) {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratingPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" your "+orificeName+".",
					penetratingPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" your "+orificeName+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" your "+orificeName+", "+penetratedPostfix+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" your "+orificeName+", "+penetratedPostfix+"."));
			
		// NPC penetrating other NPC:
		} else if(!characterPenetrating.isPlayer() && !characterPenetrated.isPlayer() && !characterPenetrating.equals(characterPenetrated)) {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratingPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" [npc2.namePos] "+orificeName+".",
					penetratingPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" [npc2.namePos] "+orificeName+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" [npc2.namePos] "+orificeName+", "+penetratedPostfix+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" [npc2.namePos] "+orificeName+", "+penetratedPostfix+"."));
			
		// PC self-penetrating:
		} else if(characterPenetrating.isPlayer() && characterPenetrating.equals(characterPenetrated)) {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratedPrefix+" as you "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" "+penetrationDescription+" your "+orificeName+".",
					penetratedPrefix+" as you "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" "+penetrationDescription+" your "+orificeName+".",
					"You "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" "+penetrationDescription+" your "+orificeName+", "+penetratedPostfix+".",
					"You "+penetratingQualifier+" "+penetratingAction+" your "+penetratorName+" "+penetrationDescription+" your "+orificeName+", "+penetratedPostfix+"."));
			
		// NPC self-penetrating:
		} else {
			return UtilText.parse(characterPenetrating, characterPenetrated,
					UtilText.returnStringAtRandom(
					penetratedPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" [npc.her] "+orificeName+".",
					penetratedPrefix+" as [npc.she] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" [npc.her] "+orificeName+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" [npc.her] "+orificeName+", "+penetratedPostfix+".",
					"[npc.Name] "+penetratingQualifier+" "+penetratingAction+" [npc.her] "+penetratorName+" "+penetrationDescription+" [npc.her] "+orificeName+", "+penetratedPostfix+"."));
		}
		
	}
	
	private String getGenericInitialPenetration(GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
		String penetrationVerb=" slides", penetrationAdverb="";
		
		switch(Sex.getSexPace(characterPenetrating)) {
			case DOM_GENTLE:
				penetrationAdverb = UtilText.returnStringAtRandom("slowly", "gently");
				penetrationVerb = UtilText.returnStringAtRandom("slide", "push", "glide");
				break;
			case DOM_NORMAL:
				penetrationAdverb = "";
				penetrationVerb = UtilText.returnStringAtRandom("push");
				break;
			case DOM_ROUGH:
				penetrationAdverb = UtilText.returnStringAtRandom("roughly", "violently", "forcefully");
				penetrationVerb = UtilText.returnStringAtRandom("slam", "grind");
				break;
			case SUB_EAGER:
				penetrationAdverb = UtilText.returnStringAtRandom("eagerly", "desperately", "enthusiastically");
				penetrationVerb = UtilText.returnStringAtRandom("slam", "grind");
				break;
			case SUB_NORMAL:
				penetrationAdverb = "";
				penetrationVerb = UtilText.returnStringAtRandom("push");
				break;
			case SUB_RESISTING:
				penetrationAdverb = UtilText.returnStringAtRandom("reluctantly", "hesitantly");
				penetrationVerb = UtilText.returnStringAtRandom("push");
				break;
		}
		
		String penetrationAdjective = "into";
		
		if(orifice.isOrifice()) {
			switch((SexAreaOrifice)orifice) {
				case ASS:
				case BREAST:
				case THIGHS:
					penetrationAdjective = "between";
					break;
				case ANUS:
				case MOUTH:
				case NIPPLE:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
				case VAGINA:
					break;
			}
		} else {
			switch((SexAreaPenetration)orifice) {
				case CLIT:
				case FINGER:
				case PENIS:
				case TAIL:
				case TENTACLE:
				case FOOT:
				case TONGUE:
					penetrationAdjective = "over";
					break;
			}
		}
		
		String ownerName = characterPenetrating.equals(characterPenetrated)?"[npc2.her]":"[npc2.namePos]";
		
		return UtilText.parse(characterPenetrating, characterPenetrated,
				"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb("+penetrationAdverb+" "+penetrationVerb+")] [npc.her] "
						+penetrationType.getName(characterPenetrating)+" "+penetrationAdjective+" "+ownerName+" "+orifice.getName(characterPenetrated)+".");
		
	}
	
	public String getPenetrationDescription(boolean initialPenetration, GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
		List<String> initialDescriptions = new ArrayList<>();
		StringBuilder penetrationSB = new StringBuilder();
		
		if(penetrationType == SexAreaPenetration.FINGER && orifice == SexAreaPenetration.PENIS) { //TODO
			if(initialPenetration) {
				return UtilText.parse(characterPenetrated, characterPenetrating,
						"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(wrap)] [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.cock+], before starting to give [npc.herHim] a handjob.");
				
			} else {
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			
		} else if(penetrationType == SexAreaPenetration.FOOT && orifice == SexAreaPenetration.PENIS) { //TODO
			if(initialPenetration) {
				if(Sex.getSexPositionSlot(characterPenetrating).isStanding()) {
					return UtilText.parse(characterPenetrated, characterPenetrating,
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.foot+] into [npc.namePos] groin, before starting to rub and press down on [npc.her] [npc.cock+].");
				} else {
					return UtilText.parse(characterPenetrated, characterPenetrating,
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(wrap)] [npc2.her] [npc2.feet+] around [npc.namePos] [npc.cock+], before starting to give [npc.herHim] a footjob.");
				}
				
			} else {
				return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			
		} else if(orifice == SexAreaOrifice.ASS) {

			if(initialPenetration) {
				return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
		} else if(orifice == SexAreaOrifice.BREAST) {

			if(initialPenetration) {
				return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
		} else if(orifice == SexAreaOrifice.ANUS) {
			if(initialPenetration) {
				switch(penetrationType) {
					case PENIS:
						initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock+] push into [npc2.her] [npc2.asshole+].");
						for(PenetrationModifier mod : characterPenetrating.getPenisModifiers()) {
							switch(mod) {
								case BARBED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the little barbs lining [npc.namePos] [npc.cock] rake the insides of [npc2.her] [npc2.asshole+].");
									break;
								case BLUNT:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the blunt head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.asshole+].");
									break;
								case FLARED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the flared head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.asshole+].");
									break;
								case KNOTTED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)]"
											+ " the fat knot at the base of [npc.namePos] [npc.cock] bump up against [npc2.her] [npc2.asshole+] as [npc.she] fully [npc.verb(penetrate)] [npc2.herHim] in one thrust.");
									break;
								case PREHENSILE:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] prehensile [npc.cock] exploring the insides of [npc2.her] [npc2.asshole+].");
									break;
								case RIBBED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] ribbed [npc.cock] push into [npc2.her] [npc2.asshole+].");
									break;
								case SHEATHED:
									break;
								case TAPERED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the tapered head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.asshole+].");
									break;
								case TENTACLED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)]"
											+ " the little tentacles lining [npc.namePos] [npc.cock] squirm and wriggle against the insides of [npc2.her] [npc2.asshole+].");
									break;
								case VEINY:
									break;
							}
						}
						penetrationSB.append(initialDescriptions.get(Util.random.nextInt(initialDescriptions.size())));
						
						initialDescriptions.clear();
						initialDescriptions.add(" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.namePos] [npc2.asshole+] squeezes down around [npc.her] [npc.cock+].");
						for(OrificeModifier mod : characterPenetrating.getVaginaOrificeModifiers()) {
							switch(mod) {
								case MUSCLE_CONTROL:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.asshole+],"
											+ " [npc.she] [npc.verb(feel)] a series of internal muscles instantly start to grip and squeeze down on [npc.her] throbbing length.");
									break;
								case PUFFY:
									break;
								case RIBBED:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.asshole+],"
											+ " [npc.she] [npc.verb(feel)] the ribbed interior bumping down against [npc.her] throbbing length.");
									break;
								case TENTACLED:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.asshole+],"
											+ " [npc.she] [npc.verb(feel)] a series of little writhing tentacles start to massage and stroke [npc.her] throbbing length.");
									break;
							}
						}
						penetrationSB.append(initialDescriptions.get(Util.random.nextInt(initialDescriptions.size())));
						
						return UtilText.parse(characterPenetrating, characterPenetrated, penetrationSB.toString());
					default:
						break;
				}
				return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
			
		} else if(orifice == SexAreaOrifice.VAGINA) {
			if(initialPenetration) {
				switch(penetrationType) {
					case PENIS:
						initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock+] push into [npc2.her] [npc2.vagina+].");
						for(PenetrationModifier mod : characterPenetrating.getPenisModifiers()) {
							switch(mod) {
								case BARBED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the little barbs lining [npc.namePos] [npc.cock] rake the insides of [npc2.her] [npc2.vagina+].");
									break;
								case BLUNT:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the blunt head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.vagina+].");
									break;
								case FLARED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the flared head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.vagina+].");
									break;
								case KNOTTED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)]"
											+ " the fat knot at the base of [npc.namePos] [npc.cock] bump up against [npc2.her] [npc2.vagina+] as [npc.she] fully [npc.verb(penetrate)] [npc2.herHim] in one thrust.");
									break;
								case PREHENSILE:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] prehensile [npc.cock] exploring the insides of [npc2.her] [npc2.vagina+].");
									break;
								case RIBBED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] ribbed [npc.cock] push into [npc2.her] [npc2.vagina+].");
									break;
								case SHEATHED:
									break;
								case TAPERED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the tapered head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.vagina+].");
									break;
								case TENTACLED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)]"
											+ " the little tentacles lining [npc.namePos] [npc.cock] squirm and wriggle against the insides of [npc2.her] [npc2.vagina+].");
									break;
								case VEINY:
									break;
							}
						}
						penetrationSB.append(initialDescriptions.get(Util.random.nextInt(initialDescriptions.size())));
						
						initialDescriptions.clear();
						initialDescriptions.add(" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.namePos] [npc2.vagina+] squeezes down around [npc.her] [npc.cock+].");
						for(OrificeModifier mod : characterPenetrating.getVaginaOrificeModifiers()) {
							switch(mod) {
								case MUSCLE_CONTROL:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.vagina+],"
											+ " [npc.she] [npc.verb(feel)] a series of internal muscles instantly start to grip and squeeze down on [npc.her] throbbing length.");
									break;
								case PUFFY:
									break;
								case RIBBED:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.vagina+],"
											+ " [npc.she] [npc.verb(feel)] the ribbed interior bumping down against [npc.her] throbbing length.");
									break;
								case TENTACLED:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.vagina+],"
											+ " [npc.she] [npc.verb(feel)] a series of little writhing tentacles start to massage and stroke [npc.her] throbbing length.");
									break;
							}
						}
						penetrationSB.append(initialDescriptions.get(Util.random.nextInt(initialDescriptions.size())));
						
						return UtilText.parse(characterPenetrating, characterPenetrated, penetrationSB.toString());
					default:
						break;
				}
				return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
		} else if(orifice == SexAreaOrifice.NIPPLE) {
			if(initialPenetration) {
				switch(penetrationType) {
					case PENIS:
						initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock+] push into [npc2.her] [npc2.nipple+].");
						for(PenetrationModifier mod : characterPenetrating.getPenisModifiers()) {
							switch(mod) {
								case BARBED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the little barbs lining [npc.namePos] [npc.cock] rake the insides of [npc2.her] [npc2.nipple+].");
									break;
								case BLUNT:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the blunt head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.nipple+].");
									break;
								case FLARED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the flared head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.nipple+].");
									break;
								case KNOTTED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)]"
											+ " the fat knot at the base of [npc.namePos] [npc.cock] bump up against [npc2.her] [npc2.nipple+] as [npc.she] fully [npc.verb(penetrate)] [npc2.herHim] in one thrust.");
									break;
								case PREHENSILE:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] prehensile [npc.cock] exploring the insides of [npc2.her] [npc2.nipple+].");
									break;
								case RIBBED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] ribbed [npc.cock] push into [npc2.her] [npc2.nipple+].");
									break;
								case SHEATHED:
									break;
								case TAPERED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the tapered head of [npc.namePos] [npc.cock] push inside [npc2.her] [npc2.nipple+].");
									break;
								case TENTACLED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)]"
											+ " the little tentacles lining [npc.namePos] [npc.cock] squirm and wriggle against the insides of [npc2.her] [npc2.nipple+].");
									break;
								case VEINY:
									break;
							}
						}
						penetrationSB.append(initialDescriptions.get(Util.random.nextInt(initialDescriptions.size())));
						
						initialDescriptions.clear();
						initialDescriptions.add(" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.namePos] [npc2.nipple+] squeezes down around [npc.her] [npc.cock+].");
						for(OrificeModifier mod : characterPenetrating.getVaginaOrificeModifiers()) {
							switch(mod) {
								case MUSCLE_CONTROL:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.nipple+],"
											+ " [npc.she] [npc.verb(feel)] a series of internal muscles instantly start to grip and squeeze down on [npc.her] throbbing length.");
									break;
								case PUFFY:
									break;
								case RIBBED:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.nipple+],"
											+ " [npc.she] [npc.verb(feel)] the ribbed interior bumping down against [npc.her] throbbing length.");
									break;
								case TENTACLED:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] [npc2.nipple+],"
											+ " [npc.she] [npc.verb(feel)] a series of little writhing tentacles start to massage and stroke [npc.her] throbbing length.");
									break;
							}
						}
						penetrationSB.append(initialDescriptions.get(Util.random.nextInt(initialDescriptions.size())));
						
						return UtilText.parse(characterPenetrating, characterPenetrated, penetrationSB.toString());
					default:
						break;
				}
				return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
		} else if(orifice == SexAreaOrifice.MOUTH) {
			if(initialPenetration) {
				switch(penetrationType) {
					case PENIS:
						initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock+] slide down into [npc2.her] throat.");
						for(PenetrationModifier mod : characterPenetrating.getPenisModifiers()) {
							switch(mod) {
								case BARBED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the little barbs lining [npc.namePos] [npc.cock] rake the insides of [npc2.her] throat.");
									break;
								case BLUNT:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the blunt head of [npc.namePos] [npc.cock] push deep down [npc2.her] throat.");
									break;
								case FLARED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the flared head of [npc.namePos] [npc.cock] push deep down [npc2.her] throat.");
									break;
								case KNOTTED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)]"
											+ " the fat knot at the base of [npc.namePos] [npc.cock] bump against [npc2.her] [npc2.lips+] as [npc.she] fully [npc.verb(hilt)] [npc.herself] down [npc2.her] throat in one thrust.");
									break;
								case PREHENSILE:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] prehensile [npc.cock] exploring the insides of [npc2.her] throat.");
									break;
								case RIBBED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] ribbed [npc.cock] push deep down [npc2.her] throat.");
									break;
								case SHEATHED:
									break;
								case TAPERED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)] the tapered head of [npc.namePos] [npc.cock] push deep down [npc2.her] throat.");
									break;
								case TENTACLED:
									initialDescriptions.add("[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(feel)]"
											+ " the little tentacles lining [npc.namePos] [npc.cock] squirm and wriggle against the insides of [npc2.her] throat.");
									break;
								case VEINY:
									break;
							}
						}
						penetrationSB.append(initialDescriptions.get(Util.random.nextInt(initialDescriptions.size())));
						
						initialDescriptions.clear();
						initialDescriptions.add(" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.namePos] throat squeezes down around [npc.her] [npc.cock+].");
						for(OrificeModifier mod : characterPenetrating.getVaginaOrificeModifiers()) {
							switch(mod) {
								case MUSCLE_CONTROL:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] throat,"
											+ " [npc.she] [npc.verb(feel)] a series of internal muscles instantly start to grip and squeeze down on [npc.her] throbbing length.");
									break;
								case PUFFY:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] throat,"
											+ " [npc.she] [npc.verb(feel)] [npc2.namePos] extra-puffy [npc2.lips] wrap around [npc.her] throbbing length.");
									break;
								case RIBBED:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] throat,"
											+ " [npc.she] [npc.verb(feel)] the ribbed interior bumping down against [npc.her] throbbing length.");
									break;
								case TENTACLED:
									initialDescriptions.add(" As the [npc.cockHead+] of [npc.namePos] [npc.cock+] pushes its way into [npc2.namePos] throat,"
											+ " [npc.she] [npc.verb(feel)] a series of little writhing tentacles start to massage and stroke [npc.her] throbbing length.");
									break;
							}
						}
						penetrationSB.append(initialDescriptions.get(Util.random.nextInt(initialDescriptions.size())));
						
						return UtilText.parse(characterPenetrating, characterPenetrated, penetrationSB.toString());
					default:
						break;
				}
				return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
		} else if(orifice == SexAreaOrifice.THIGHS) {

			if(initialPenetration) {
				return getGenericInitialPenetration(characterPenetrating, penetrationType, characterPenetrated, orifice);
			}
			
			return generateGenericPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
			
		}
		
		return "";
	}
	
	public String getStopPenetrationDescription(GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaOrifice orifice) {
		String orificeName = "", penetrationName = "";

		switch(penetrationType) {
			case FINGER:
				penetrationName = "[npc.fingers]";
				break;
			case PENIS:
				penetrationName = "[npc.cock+]";
				break;
			case TAIL:
				penetrationName = "[npc.tail+]";
				break;
			case TENTACLE:
				penetrationName = "tentacle";
				break;
			case TONGUE:
				penetrationName = "[npc.tongue+]";
				break;
			case CLIT:
				penetrationName = "[npc.clit+]";
				break;
			case FOOT:
				penetrationName = "[npc.toes]";
				break;
		}
		
		switch(orifice) {
			case ANUS:
				orificeName = "[npc2.asshole+]";
				break;
			case ASS:
				orificeName = "[npc2.ass+]";
				break;
			case MOUTH:
				orificeName = "mouth";
				break;
			case NIPPLE:
				orificeName = "[npc2.nipple+]";
				break;
			case URETHRA_PENIS:
			case URETHRA_VAGINA:
				orificeName = "urethra";
				break;
			case VAGINA:
				orificeName = "[npc2.pussy+]";
				break;
			case THIGHS:
				orificeName = "thighs";
				break;
			case BREAST:
				orificeName = "[npc2.breasts+]";
				break;
		}
		
		
		if(characterPenetrating.isPlayer()) {
			if(characterPenetrated.isPlayer()) {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"You slide your "+penetrationName+" out of your "+orificeName+".");
			} else {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"You slide your "+penetrationName+" out of [npc2.namePos] "+orificeName+".");
			}
			
		} else {
			if(characterPenetrated.isPlayer()) {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"[npc.Name] slides [npc.her] "+penetrationName+" out of your "+orificeName+".");
			} else if(characterPenetrating.equals(characterPenetrated)) {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"[npc.Name] slides [npc.her] "+penetrationName+" out of [npc.her] "+orificeName+".");
			} else {
				return UtilText.parse(characterPenetrating, characterPenetrated,
						"[npc.Name] slides [npc.her] "+penetrationName+" out of [npc2.namePos] "+orificeName+".");
			}
		}
	}
	
	
	public String getVirginityLossOrificeDescription(GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaOrifice orifice) {
		StringBuilder StringBuilderSB = new StringBuilder();
		
		switch(orifice) {
			case ANUS:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerAnalVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerAnalVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				}
				break;
			case MOUTH:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerMouthVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerMouthVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				}
				break;
			case NIPPLE:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerNippleVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerNippleVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				}
				break;
			case URETHRA_PENIS: case URETHRA_VAGINA:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerUrethraVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerUrethraVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				}
				break;
			case VAGINA:
				if(characterPenetrated.isPlayer()) {
					StringBuilderSB.append(getPlayerVaginaVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				} else {
					StringBuilderSB.append(getPartnerVaginaVirginityLossDescription(characterPenetrated, characterPenetrating, penetrationType));
				}
				break;
			case ASS: case BREAST: case THIGHS:
				// Don't have a virginity to lose.
				break;
		}
		return StringBuilderSB.toString();
	}
	
	public String getVirginityLossPenetrationDescription(GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaOrifice orifice) {
		StringBuilder StringBuilderSB = new StringBuilder();
		
		switch(penetrationType) {
			case PENIS:
				if(characterPenetrating.isPlayer()) {
					if(Main.game.getPlayer().isPenisVirgin()) {
						StringBuilderSB.append(getPlayerPenileVirginityLossDescription(characterPenetrated, characterPenetrating, orifice));
					}
				} else {
					if(this.isPenisVirgin()) {
						StringBuilderSB.append(getPartnerPenileVirginityLossDescription(characterPenetrated, characterPenetrating, orifice));
					}
				}
				break;
			case FINGER: case TAIL: case TENTACLE: case TONGUE: case FOOT: case CLIT:
				// Don't have a virginity to lose.
				break;
		}
		
		return StringBuilderSB.toString();
	}
	
	protected String formatVirginityLoss(String rawInput) {
		return UtilText.formatVirginityLoss(rawInput);
	}
	
	protected String losingPureVirginity(GameCharacter characterPenetrating, SexAreaPenetration penetrationType){
		if(characterPenetrating.isPlayer()) {
			return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
					+ "</p>"
					+ "<p>"
						+ "You can't quite believe what you're doing to yourself."
						+ " As your "+(Sex.getFirstContactingSexAreaPenetration(Main.game.getPlayer(), SexAreaOrifice.VAGINA).getName(characterPenetrating))
							+" takes your own virginity in a single thrust, you find yourself letting out a desperate gasp."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[pc.thought(W-What am I doing?!<br/>"
								+ "I-I'm just so... <i>horny</i>!<br/>"
								+ "I-I can't help myself!)]"
					+ "</p>"
					+ "<p>"
						+ "You don't quite know how to react to your own actions."
						+ " The virginity that you prized so highly is now gone, and a vacant gaze settles over your face as your [pc.pussy+] spreads lewdly around your own "+penetrationType.getName(characterPenetrating)+"."
					+ "</p>"
					+ "<p>"
						+ "While you were a virgin, you felt invincible."
						+ " As though you could overcome any obstacle that was placed in your way."
						+ " Now, however..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "[pc.thought(Now I'm just some regular old slut...<br/>"
							+ "So turned on that I choose to fuck myself...<br/>"
							+ "All I'm good for now is being a worthless fuck-toy...)]"
					+ "</p>"
					+ "<p>"
						+ "With a shuddering sigh, you decide to resign yourself to the fact that now you're nothing more than a <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
					+ "</p>");
			
		} else {
			return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
					+ "</p>"
					+ "<p>"
						+ "You can't believe what's happening."
						+ " As [npc.namePos] "+(Sex.getFirstContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).getName(characterPenetrating))
						+" takes your virginity in a single thrust, you find yourself letting out a desperate gasp."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[pc.thought(This is how I lose my virginity?!<br/>"
								+ "To... <i>[npc.a_race]</i>?!<br/>"
								+ "This can't be happening!)]"
					+ "</p>"
					+ "<p>"
						+ "You don't quite know how to react."
						+ " The virginity that you prized so highly has been suddenly taken from you, and a vacant gaze settles over your face as your [pc.pussy+] spreads lewdly around the intruding object."
					+ "</p>"
					+ "<p>"
						+ "While you were a virgin, you felt invincible."
						+ " As though you could overcome any obstacle that was placed in your way."
						+ " Now, however..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "[pc.thought(Now I'm just some regular old slut...<br/>"
							+ "Getting fucked by any random person I meet...<br/>"
							+ "All I'm good for now is being the next lucky guy's fuck-toy...)]"
					+ "</p>"
					+ "<p>"
					+ "You're vaguely aware of [npc.name] [npc.moaning] somewhere in the background, completely oblivious to how hard you've been hit by the loss of your virginity."
					+ " With a shuddering sigh, you decide to resign yourself to the fact that now you're nothing more than a <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
					+ "</p>");
		}
	}
	
	// Virginity loss:
	
	private String getPlayerAnalVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		StringBuilder StringBuilderSB = new StringBuilder();
		
		boolean isPenis = penetration == SexAreaPenetration.PENIS;
		boolean isTail = penetration == SexAreaPenetration.TAIL;
		
		if(characterPenetrating.isPlayer()) { // SELF-PENETRATION
			// Initial penetration:
			if(!Sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.ANUS)) {
				// Dry:
				StringBuilderSB.append(
						"<p>"
							+ "You let out a painful cry as you force your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" into your dry [pc.asshole]."
							+ " Squirming and shuffling in discomfort, your cries grow louder and louder as you start fucking your own [pc.ass]; the lack of lubrication turning your first anal experience into one of mind-numbing agony."
						+ "</p>");
				
			} else {
				 // Wet:
				StringBuilderSB.append(
						"<p>"
							+ "You let out a painful cry as you force your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" into your [pc.asshole+]."
							+ " Squirming and shuffling in discomfort, you continue letting out little whimpers as you start fucking your own [pc.ass]."
							+ " Thankfully, your [pc.asshole] was lubricated beforehand, and you dread to think of how painful your first anal experience would have been otherwise."
						+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The pain and discomfort at the feeling of losing your anal virginity is pure bliss, and you soon find yourself [pc.moaning] in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "With tears welling up in your [pc.eyes], you let out another painful wail as you draw"+(isTail?" your [pc.tail]":"")+" back, before thrusting deep inside yourself once again."
							+ " This time, the pain isn't as extreme as before, and you realise that you're starting to get used to the feeling of using your own ass."
						+ "</p>");
			}
			
			// Ending:
			StringBuilderSB.append(
					"<p>"
						+ "The throbbing, painful ache in your [pc.ass] slowly starts to fade away, and as your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")
							+" pushes into your [pc.asshole+] once again, you let out a little whimper of relief as you feel that you're quickly getting used to the penetration."
					+ "</p>");
			
		} else { // PARTNER PENETRATION
			// Initial penetration:
			if(!Sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.ANUS)) {
				// Dry:
				StringBuilderSB.append(
						"<p>"
							+ "You let out a painful cry as you feel [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" push into your dry [pc.asshole]."
							+ " Squirming and shuffling in discomfort, your cries grow louder and louder as [npc.name] starts fucking your [pc.ass]; the lack of lubrication turning your first anal experience into one of mind-numbing agony."
						+ "</p>");
				
			} else {
				 // Wet:
				StringBuilderSB.append(
						"<p>"
							+ "You let out a painful cry as you feel [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" push into your [pc.asshole+]."
							+ " Squirming and shuffling in discomfort, you continue letting out little whimpers as [npc.name] starts fucking your [pc.ass]."
							+ " Thankfully, your [pc.asshole] was lubricated beforehand, and you dread to think of how painful your first anal experience would have been otherwise."
						+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The pain and discomfort at the feeling of losing your anal virginity is pure bliss, and you soon find yourself [pc.moaning] in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			}
			
			// Partner sadistic reaction:
			if(this.hasFetish(Fetish.FETISH_SADIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "With tears welling up in your [pc.eyes], you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before violently thrusting deep inside you once again."
							+ " [npc.She] lets out an evil laugh as [npc.she] causes you to writhe about in pain, [npc.her] sadistic nature fuelling [npc.her] rough thrusts into your [pc.asshole] as [npc.she] ruthlessly fucks your [pc.ass]."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "With tears welling up in your [pc.eyes], you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before thrusting deep inside you once again."
							+ " This time, the pain isn't as extreme as before, and you realise that you're starting to get used to the feeling of being fucked in the ass."
						+ "</p>");
			}
			
			// Partner deflowering reaction:
			if(this.hasFetish(Fetish.FETISH_DEFLOWERING)) {
				StringBuilderSB.append(
						"<p>"
							+ "[npc.speech(Oh, yes!)] [npc.she] cries, [npc.speech(Good [pc.girl], saving your anal virginity for me!"
								+ " Remember this moment, remember that <i>my</i> "+(isPenis?"cock":"")+(isTail?"tail":"")+" was the the one that turned you into "+(Main.game.getPlayer().isFeminine()?"a horny buttslut":"a little fucktoy")+"!)]"
						+ "</p>");
			}
			
			// Ending:
			StringBuilderSB.append(
					"<p>"
						+ "The throbbing, painful ache in your [pc.ass] slowly starts to fade away, and as [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")
							+" pushes into your [pc.asshole+] once again, you let out a little whimper of relief as you feel that there's no accompanying stab of pain."
					+ "</p>");
		}
		
		
		StringBuilderSB.append(formatVirginityLoss("You'll always remember this moment as the time that you lost your anal virginity!"));
		
		return UtilText.parse(characterPenetrating, StringBuilderSB.toString());
	}
	
	
	
	protected String getPlayerVaginaVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		StringBuilder StringBuilderSB = new StringBuilder();
		
		boolean isPenis = penetration == SexAreaPenetration.PENIS;
		boolean isTail = penetration == SexAreaPenetration.TAIL;
		
		if(characterPenetrating.isPlayer()) { // SELF-PENETRATION
			// Initial penetration:
			if(!Sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) {
				// Dry:
				StringBuilderSB.append(
						"<p>"
							+ "As you drive your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" deep into your dry [pc.pussy], your vision suddenly explodes in stars, and a painful, high-pitched shriek escapes from between your lips."
							+ " Being penetrated without any form of lubrication would be uncomfortable at the best of times, but due to the fact that you're still a virgin, it's somewhat more than just a little discomfort,"
								+ " and your shriek turns into a shuddering cry as you shuffle about in pure agony."
						+ "</p>");
				
			} else {
				 // Wet:
				StringBuilderSB.append(
							"<p>"
								+ "As you drive your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" deep into your [pc.pussy+], your vision suddenly narrows down, and a painful, desperate wail escapes from between your lips."
								+ " Luckily, your pussy was lubricated before being penetrated, but due to the fact that you're still a virgin, it isn't enough to completely prevent the pain you now feel between your legs,"
									+ " and your wail turns into a shuddering moan as you shuffle about in discomfort."
							+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			}else {
				StringBuilderSB.append(
						"<p>"
							+ "Instinctively trying to clench your legs together, you let out another painful wail as you draw"+(isTail?" your [pc.tail]":"")+" back, before thrusting deep inside yourself once again."
							+ " This time, the pain isn't as extreme as before, and you realise that the initial hurt was due to your hymen being torn."
						+ "</p>");
			}
			
			// Ending:
			if (Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				StringBuilderSB.append(
						"<p>"
							+ "As the pain recedes into a dull, throbbing ache between your legs, you feel a little trickle of blood running out of your now-broken-in pussy, and you can't help but let out yet another whimpering cry."
							+ " The throbbing, painful ache in your groin slowly starts to fade away, and as you push your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")
								+" into your [pc.pussy+] once again, you let out a sigh of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "The throbbing, painful ache in your groin slowly starts to fade away, and as you push your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")
								+" into your [pc.pussy+] once again, you let out a sigh of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			}
			
		} else { // PARTNER PENETRATION
			// Initial penetration:
			if(!Sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) {
				// Dry:
				StringBuilderSB.append(
						"<p>"
							+ "As [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" drives deep into your dry [pc.pussy], your vision suddenly explodes in stars, and a painful, high-pitched shriek escapes from between your lips."
							+ " Being penetrated without any form of lubrication would be uncomfortable at the best of times, but due to the fact that you're still a virgin, it's somewhat more than just a little discomfort,"
								+ " and your shriek turns into a shuddering cry as you shuffle about in pure agony."
						+ "</p>");
				
			} else {
				 // Wet:
				StringBuilderSB.append(
							"<p>"
								+ "As [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" drives deep into your [pc.pussy+], your vision suddenly narrows down, and a painful, desperate wail escapes from between your lips."
								+ " Luckily, your pussy was lubricated before being penetrated, but due to the fact that you're still a virgin, it isn't enough to completely prevent the pain you now feel between your legs,"
									+ " and your wail turns into a shuddering moan as you shuffle about in discomfort."
							+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			}
			
			// Partner sadistic reaction:
			if(this.hasFetish(Fetish.FETISH_SADIST)) {
				StringBuilderSB.append(
						"<p>"
							+ "Trying desperately to clench your legs together, you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before violently thrusting deep inside you once again."
							+ " [npc.She] lets out an evil laugh as [npc.she] causes you to writhe about in pain, [npc.her] sadistic nature fuelling [npc.her] rough thrusts into your pussy as [npc.she] ruthlessly tears through your hymen."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "Trying desperately to clench your legs together, you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before thrusting deep inside you once again."
							+ " This time, the pain isn't as extreme as before, and you realise that the initial hurt was due to your hymen being torn."
						+ "</p>");
			}
			
			// Partner deflowering reaction:
			if(this.hasFetish(Fetish.FETISH_DEFLOWERING)) {
				StringBuilderSB.append(
						"<p>"
							+ "[npc.speech(Oh, yes!)] [npc.she] cries, [npc.speech(Good [pc.girl], saving your virginity for me!"
								+ " Remember this moment, remember that <i>my</i> "+(isPenis?"cock":"")+(isTail?"tail":"")+" was the the one that broke you in!)]"
						+ "</p>");
			}
			
			// Ending:
			if (Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				StringBuilderSB.append(
						"<p>"
							+ "As the pain recedes into a dull, throbbing ache between your legs, you feel a little trickle of blood running out of your now-broken-in pussy, and you can't help but let out yet another whimpering cry."
							+ " The throbbing, painful ache in your groin slowly starts to fade away, and as [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")
								+" pushes into your [pc.pussy+] once again, you let out a sigh of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			} else {
				StringBuilderSB.append(
						"<p>"
							+ "The throbbing, painful ache in your groin slowly starts to fade away, and as [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")
								+" pushes into your [pc.pussy+] once again, you let out a sigh of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			}
		}
		
		
		StringBuilderSB.append(formatVirginityLoss("Your hymen has been torn; you have lost your virginity!"));
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
			StringBuilderSB.append(losingPureVirginity(characterPenetrating, penetration));
		}
		
		return UtilText.parse(characterPenetrating, StringBuilderSB.toString());
	}
	
	private String getPlayerPenileVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaOrifice orifice){
		return formatVirginityLoss("You'll always remember this moment as the time that you lost your penile virginity!");
	}
	
	private String getPlayerNippleVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		return formatVirginityLoss("You'll always remember this moment as the time that you lost your nipple virginity!");
	}
	
	private String getPlayerUrethraVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		return formatVirginityLoss("You have lost your urethral virginity!");
	}
	
	private String getPlayerMouthVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		if(penetration == SexAreaPenetration.PENIS) {
			if(characterPenetrating.isPlayer()) {
				return formatVirginityLoss("You'll always remember this moment as the first time that you sucked your own cock!");
			} else {
				return formatVirginityLoss("You'll always remember this moment as the first time that you sucked a cock!");
			}
			
		} else if(penetration == SexAreaPenetration.TAIL) {
			if(characterPenetrating.isPlayer()) {
				return formatVirginityLoss("You'll always remember this moment as the first time that you sucked your tail!");
			} else {
				return formatVirginityLoss("You'll always remember this moment as the first time that you sucked someone's tail!");
			}
			
		} else {
			return formatVirginityLoss("You'll always remember this moment as the first time that you took something down your throat!");
		}
	}
	
	private String getPartnerPenileVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaOrifice orifice){
		return UtilText.parse(characterPenetrated, characterPenetrating,
				(characterPenetrated.equals(characterPenetrating)
						?formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own penile virginity!")
						:formatVirginityLoss("[npc.Name] [npc.has] taken [npc2.namePos] penile virginity!"))
				+(characterPenetrated.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;>"
							+ "[style.italicsArcane(Due to [npc.namePos] deflowering fetish, [npc.she] [npc.verb(gain)])]"
								+ " [style.italicsExperience("+Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrated)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}
	
	private String getPartnerAnalVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		return UtilText.parse(characterPenetrated, characterPenetrating,
				(characterPenetrated.equals(characterPenetrating)
						?formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own anal virginity!")
						:formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc.namePos] anal virginity!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}
	
	private String getPartnerVaginaVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		return UtilText.parse(characterPenetrated, characterPenetrating,
				(characterPenetrated.equals(characterPenetrating)
						?formatVirginityLoss("[npc.NamePos] hymen has been torn; [npc2.Name] [npc2.has] taken [npc2.her] own virginity!")
						:formatVirginityLoss("[npc.NamePos] hymen has been torn; [npc2.name] [npc2.has] taken [npc.her] virginity!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}
	
	private String getPartnerNippleVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		return UtilText.parse(characterPenetrated, characterPenetrating,
				(characterPenetrated.equals(characterPenetrating)
						?formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own nipple virginity!")
						:formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc.namePos] nipple virginity!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}
	
	private String getPartnerUrethraVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		return UtilText.parse(characterPenetrated, characterPenetrating,
				(characterPenetrated.equals(characterPenetrating)
						?formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own urethral virginity!")
						:formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc.namePos] urethral virginity!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}
	
	private String getPartnerMouthVirginityLossDescription(GameCharacter characterPenetrated, GameCharacter characterPenetrating, SexAreaPenetration penetration){
		return UtilText.parse(characterPenetrated, characterPenetrating,
				(characterPenetrated.equals(characterPenetrating)
						?formatVirginityLoss("[npc2.Name] [npc2.has] given [npc2.herself] [npc.her] first oral experience!")
						:formatVirginityLoss("[npc2.Name] [npc2.has] given [npc.name] [npc.her] first oral experience!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}
	
	
	// Stretching:
	
	protected String formatStretching(String rawInput) {
		return UtilText.formatStretching(rawInput);
	}

	public String getStretchingDescription(GameCharacter partner, SexAreaPenetration penetrationType, SexAreaOrifice orifice) {
		switch(orifice) {
			case ANUS:
				switch(penetrationType) {
				case FINGER: case PENIS: case TONGUE: case TAIL: case TENTACLE: case FOOT: case CLIT:
					return formatStretching(this.isPlayer()
							?"Your [pc.asshole+] is being stretched."
							:UtilText.parse(this, "[npc.NamePos] [npc.asshole+] is being stretched."));
				}
				break;
			case ASS:
				break;
			case BREAST:
				break;
			case MOUTH:
				switch(penetrationType) {
					case FINGER:
						if(partner.equals(this)) {
							return formatStretching(this.isPlayer()
									?UtilText.parse(partner, "You're struggling to fit your fingers down your throat.")
									:UtilText.parse(this, "[npc.Name] is struggling to fit [npc.her] fingers down [npc.her] throat."));
						}
						return formatStretching(this.isPlayer()
								?UtilText.parse(partner, "You're struggling to fit [npc.namePos] fingers down your throat.")
								:(partner.isPlayer()
										?UtilText.parse(this, "[npc.Name] is struggling to fit your fingers down [npc.her] throat.")
										:UtilText.parse(this, partner, "[npc.Name] is struggling to fit [npc2.namePos] fingers down [npc.her] throat.")));
					case PENIS:
						if(partner.equals(this)) {
							return formatStretching(this.isPlayer()
									?UtilText.parse(partner, "You're struggling to fit your [pc.cock+] down your throat.")
									:UtilText.parse(this, "[npc.Name] is struggling to fit [npc.her] [npc.cock+] down [npc.her] throat."));
						}
						return formatStretching(this.isPlayer()
								?UtilText.parse(partner, "You're struggling to fit [npc.namePos] [npc.cock+] down your throat.")
								:(partner.isPlayer()
										?UtilText.parse(this, "[npc.Name] is struggling to fit your [pc.cock+] down [npc.her] throat.")
										:UtilText.parse(this, partner, "[npc.Name] is struggling to fit [npc2.namePos] [npc2.cock+] down [npc.her] throat.")));
					case TAIL:
						if(partner.equals(this)) {
							return formatStretching(this.isPlayer()
									?UtilText.parse(partner, "You're struggling to fit your [pc.tail] down your throat.")
									:UtilText.parse(this, "[npc.Name] is struggling to fit [npc.her] [npc.tail] down [npc.her] throat."));
						}
						return formatStretching(this.isPlayer()
								?UtilText.parse(partner, "You're struggling to fit [npc.namePos] [npc.tail] down your throat.")
								:(partner.isPlayer()
										?UtilText.parse(this, "[npc.Name] is struggling to fit your [pc.tail] down [npc.her] throat.")
										:UtilText.parse(this, partner, "[npc.Name] is struggling to fit [npc2.namePos] [npc2.tail] down [npc.her] throat.")));
					default:
						return "Your throat is being stretched out.";
				}
			case NIPPLE:
				switch(penetrationType) {
					case FINGER: case PENIS: case TONGUE: case TAIL: case TENTACLE: case FOOT: case CLIT:
						return formatStretching(this.isPlayer()
								?"Your [pc.nipples+] are being stretched."
								:UtilText.parse(this, "[npc.NamePos] [npc.nipples+] are being stretched."));
				}
				break;
			case THIGHS:
				break;
			case URETHRA_PENIS: case URETHRA_VAGINA:
				switch(penetrationType) {
				case FINGER: case PENIS: case TONGUE: case TAIL: case TENTACLE: case FOOT: case CLIT:
					return formatStretching(this.isPlayer()
							?"Your urethra is being stretched."
							:UtilText.parse(this, "[npc.NamePos] urethra is being stretched."));
				}
				break;
			case VAGINA:
				switch(penetrationType) {
					case FINGER: case PENIS: case TONGUE: case TAIL: case TENTACLE: case FOOT: case CLIT:
						return formatStretching(this.isPlayer()
								?"Your [pc.pussy+] is being stretched."
								:UtilText.parse(this, "[npc.NamePos] [npc.pussy+] is being stretched."));
				}
		}
		return "";
	}

	public String getStretchingFinishedDescription(SexAreaOrifice orifice) {
		switch(orifice) {
			case ANUS:
				return formatStretching(UtilText.parse(this, "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.her] [npc.asshole+] finishes stretching out to a comfortable size."));
			case ASS:
				break;
			case BREAST:
				break;
			case MOUTH:
				return formatStretching(UtilText.parse(this, "[npc.Name] [npc.verb(let)] out a muffled [npc.moan] as [npc.her] throat finishes stretching out to a comfortable size."));
			case NIPPLE:
				return formatStretching(UtilText.parse(this, "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.her] [npc.nipples+] finish stretching out to a comfortable size."));
			case THIGHS:
				break;
			case URETHRA_PENIS: case URETHRA_VAGINA:
				return formatStretching(UtilText.parse(this, "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.her] urethra finishes stretching out to a comfortable size."));
			case VAGINA:
				return formatStretching(UtilText.parse(this, "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.her] [npc.pussy+] finishes stretching out to a comfortable size."));
		}
		return "";
	}

	
	// Too loose:
	
	protected String formatTooLoose(String rawInput) {
		return UtilText.formatTooLoose(rawInput);
	}
	
	
	public String getTooLooseDescription(SexAreaOrifice orifice) {
		switch(orifice) {
			case ANUS:
				return formatTooLoose(UtilText.parse(this, "[npc.Her] asshole is too loose to provide much pleasure..."));
			case ASS:
				break;
			case BREAST:
				break;
			case MOUTH:
				break;
			case NIPPLE:
				return formatTooLoose(UtilText.parse(this, "[npc.Her] nipples are too loose to provide much pleasure..."));
			case THIGHS:
				break;
			case URETHRA_PENIS: case URETHRA_VAGINA:
				return formatTooLoose(UtilText.parse(this, "[npc.Her] urethra is too loose to provide much pleasure..."));
			case VAGINA:
				return formatTooLoose(UtilText.parse(this, "[npc.Her] pussy is too loose to provide much pleasure..."));
		}
		
		return "";
		
	}
	
	// Addictions:
	
	/**
	 * @param fluid The FluidType to be ingested.
	 * @param orificeIngestedThrough Orifice through which the fluid is being ingested.
	 * @param addictive Is this fluid addictive or not.
	 * @return A <b>formatted paragraph</b> description of addiction increasing/satisfied, or an empty String if no addictive effects occur.
	 */
	public String ingestFluid(GameCharacter charactersFluid, FluidType fluid, SexAreaOrifice orificeIngestedThrough, int millilitres, List<FluidModifier> modifiers) {
		StringBuilder fluidIngestionSB = new StringBuilder();
		
		//TODO convert all instances of this method to just (GameCharacter charactersFluid, BodyPartInterface fluid, int millilitres)
		boolean found = false;
	
		if(fluid.getBaseType()==FluidTypeBase.CUM) {
			if(Main.game.isInSex()) {
				Sex.addLubrication(this, orificeIngestedThrough, charactersFluid, LubricationType.CUM);
			}
			
			if(fluidsStoredMap.containsKey(orificeIngestedThrough)) {
				for(FluidStored fluidStored : fluidsStoredMap.get(orificeIngestedThrough)) {
					if(fluidStored.getFluid().equals(charactersFluid.getCum())) {
						fluidStored.incrementMillilitres(millilitres);
						found = true;
						break;
					}
				}
			}
			if(!found) {
				this.addFluidStored(orificeIngestedThrough, new FluidStored(charactersFluid.getId(), charactersFluid.getCum(), millilitres));
			}
			
		} else if(fluid.getBaseType()==FluidTypeBase.MILK) {
			if(Main.game.isInSex()) {
				Sex.addLubrication(this, orificeIngestedThrough, charactersFluid, LubricationType.MILK);
			}
			
			for(FluidStored fluidStored : fluidsStoredMap.get(orificeIngestedThrough)) {
				if(fluidStored.getFluid().equals(charactersFluid.getMilk())) {
					fluidStored.incrementMillilitres(millilitres);
					found = true;
					break;
				}
			}
			if(!found) {
				this.addFluidStored(orificeIngestedThrough, new FluidStored(charactersFluid.getId(), charactersFluid.getMilk(), millilitres));
			}
			
		} else if(fluid.getBaseType()==FluidTypeBase.GIRLCUM) {
			if(Main.game.isInSex()) {
				Sex.addLubrication(this, orificeIngestedThrough, charactersFluid, LubricationType.GIRLCUM);
			}
			
			for(FluidStored fluidStored : fluidsStoredMap.get(orificeIngestedThrough)) {
				if(fluidStored.getFluid().equals(charactersFluid.getGirlcum())) {
					fluidStored.incrementMillilitres(millilitres);
					found = true;
					break;
				}
			}
			if(!found) {
				this.addFluidStored(orificeIngestedThrough, new FluidStored(charactersFluid.getId(), charactersFluid.getGirlcum(), millilitres));
			}
		}
		
		if((this.getBodyMaterial()==BodyMaterial.SLIME
				|| orificeIngestedThrough == SexAreaOrifice.VAGINA)
				&& fluid.getBaseType()==FluidTypeBase.CUM) {
			fluidIngestionSB.append(rollForPregnancy(charactersFluid, millilitres));
		}
		
		if(modifiers.contains(FluidModifier.ALCOHOLIC)) { //TODO factor in body size:
			fluidIngestionSB.append(this.incrementAlcoholLevel(millilitres * 0.001f));
		}
		
		if(modifiers.contains(FluidModifier.HALLUCINOGENIC)) {
			this.addPsychoactiveFluidIngested(fluid);
			this.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60);
			if(isPlayer()) {
				fluidIngestionSB.append("<p>"
							+ "Due to the psychoactive properties of "+(charactersFluid.isPlayer()?"your":charactersFluid.getName()+"'s")+" "+fluid.getName(charactersFluid)
								+", you start <span style='color:"+Colour.PSYCHOACTIVE.toWebHexString()+";'>tripping out</span>!"
						+ "</p>");
			} else {
				fluidIngestionSB.append(UtilText.parse(this,
						"<p>"
						+ "Due to the psychoactive properties of "+(charactersFluid.isPlayer()?"your":charactersFluid.getName()+"'s")+" "+fluid.getName(charactersFluid)
							+", [npc.name] starts <span style='color:"+Colour.PSYCHOACTIVE.toWebHexString()+";'>tripping out</span>!"
					+ "</p>"));
			}
		}
		
		if(modifiers.contains(FluidModifier.ADDICTIVE) && this.getAddiction(fluid) == null) {
			addAddiction(new Addiction(fluid, Main.game.getMinutesPassed(), charactersFluid.getId()));
			if(isPlayer()) {
				fluidIngestionSB.append("<p>"
							+ "Due to the addictive properties of "+(charactersFluid.isPlayer()?"your":charactersFluid.getName()+"'s")+" "+fluid.getName(charactersFluid)
								+", you find yourself [style.colourArcane(craving)] <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+"!"
						+ "</p>");
			} else {
				fluidIngestionSB.append(UtilText.parse(this,
						"<p>"
							+ "Due to the addictive properties of "+(charactersFluid.equals(this)?"[npc.her]":(charactersFluid.isPlayer()?"your":charactersFluid.getName()+"'s"))+" "+fluid.getName(charactersFluid)
								+", [npc.name] finds [npc.herself] [style.colourArcane(craving)] <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+"!"
						+ "</p>"));
			}
			
		} else if(this.getAddiction(fluid) != null) {
			boolean curedWithdrawal = Main.game.getMinutesPassed()-this.getAddiction(fluid).getLastTimeSatisfied()>=24*60;
			setLastTimeSatisfiedAddiction(fluid, Main.game.getMinutesPassed());
			if(isPlayer()) {
				fluidIngestionSB.append(UtilText.parse(charactersFluid,
						"<p>"
							+ "Your [style.colourArcane(craving)] for <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+" has been satisfied!"
							+ (curedWithdrawal
								?" You feel deeply grateful to [npc.name] for providing you with what you needed most..."
								:" You weren't suffering from withdrawal, but you still feel thankful to [npc.name] for feeding your addiction...")
						+ "</p>"));
			} else {
				if(charactersFluid.isPlayer()) {
					fluidIngestionSB.append(UtilText.parse(this,
							"<p>"
								+ "[npc.NamePos] [style.colourArcane(craving)] for <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+" has been satisfied!"
								+ (curedWithdrawal
									?" [npc.She] feels deeply grateful to you for providing [npc.herHim] with what [npc.she] needed most..."
											+ "</p>"
											+ this.incrementAffection(charactersFluid, 5)
											+ (this.isSlave()?this.incrementObedience(5):"")
									:" [npc.She] wasn't suffering from withdrawal, but [npc.she] still feels thankful to you for feeding [npc.her] addiction..."
											+ "</p>")));
				} else {
					fluidIngestionSB.append(UtilText.parse(this, charactersFluid,
							"<p>"
								+ "[npc.NamePos] [style.colourArcane(craving)] for <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(charactersFluid)+"</span> "+fluid.getName(charactersFluid)+" has been satisfied!"
								+ (curedWithdrawal
									?" [npc.She] feels deeply grateful to [npc2.name] for providing [npc.herHim] with what [npc.she] needed most..."
											+ "</p>"
//											+ this.incrementAffection(charactersFluid, 5)
											+ (this.isSlave()?this.incrementObedience(5):"")
									:" [npc.She] wasn't suffering from withdrawal, but [npc.she] still feels thankful to [npc2.name] for feeding [npc.her] addiction..."
											+ "</p>")));
				}
			}
		}
		
		return fluidIngestionSB.toString();
	}
	
	//TODO remove:
	@Deprecated
	public String ingestFluid(FluidType fluid, SexAreaOrifice orificeIngestedThrough, int millilitres, List<FluidModifier> modifiers) {
		StringBuilder fluidIngestionSB = new StringBuilder();
		if(modifiers.contains(FluidModifier.ALCOHOLIC)) { //TODO factor in body size:
			fluidIngestionSB.append(this.incrementAlcoholLevel(millilitres * 0.001f));
		}
		
		if(modifiers.contains(FluidModifier.HALLUCINOGENIC)) {
			this.addPsychoactiveFluidIngested(fluid);
			this.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60);
			if(isPlayer()) {
				fluidIngestionSB.append("<p>"
							+ "Due to the psychoactive properties of the "+fluid.getName(null)
								+", you start <span style='color:"+Colour.PSYCHOACTIVE.toWebHexString()+";'>tripping out</span>!"
						+ "</p>");
			} else {
				fluidIngestionSB.append(UtilText.parse(this,
						"<p>"
						+ "Due to the psychoactive properties of the "+fluid.getName(null)
							+", [npc.name] starts <span style='color:"+Colour.PSYCHOACTIVE.toWebHexString()+";'>tripping out</span>!"
					+ "</p>"));
			}
		}
		
		if(modifiers.contains(FluidModifier.ADDICTIVE) && this.getAddiction(fluid) == null) {
			addAddiction(new Addiction(fluid, Main.game.getMinutesPassed(), this.getId()));
			if(isPlayer()) {
				fluidIngestionSB.append("<p>"
							+ "Due to the addictive properties of the "+fluid.getName(null)
								+", you find yourself [style.colourArcane(craving)] <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(null)+"</span> "+fluid.getName(null)+"!"
						+ "</p>");
			} else {
				fluidIngestionSB.append(UtilText.parse(this,
						"<p>"
							+ "Due to the addictive properties of the "+fluid.getName(null)+", [npc.name] finds [npc.herself] [style.colourArcane(craving)]"
									+ " <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(null)+"</span> "+fluid.getName(null)+"!"
						+ "</p>"));
			}
			
		} else if(this.getAddiction(fluid) != null) {
			setLastTimeSatisfiedAddiction(fluid, Main.game.getMinutesPassed());
			if(isPlayer()) {
				fluidIngestionSB.append(
						"<p>"
							+ "Your [style.colourArcane(craving)] for <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(null)+"</span> "+fluid.getName(null)+" has been satisfied!"
						+ "</p>");
			} else {
				fluidIngestionSB.append(UtilText.parse(this,
						"<p>"
							+ "[npc.NamePos] [style.colourArcane(craving)] for <span style='color:"+fluid.getRace().getColour().toWebHexString()+";'>"+fluid.getDescriptor(null)+"</span> "+fluid.getName(null)+" has been satisfied!"
						+ "</p>"));
			}
		}
		
		if(fluidIngestionSB.length()==0) {
			if(isPlayer()) {
				fluidIngestionSB.append("<p style='text-align:center;'>"
							+ "<i>You drink the "+fluid.getName(null)+"!</i>"
						+ "</p>");
			} else {
				fluidIngestionSB.append(UtilText.parse(this,
						"<p style='text-align:center;'>"
							+ "<i>[npc.Name] drinks the "+fluid.getName(null)+"!</i>"
						+ "</p>"));
			}
		}
		
		return fluidIngestionSB.toString();
	}
	
	public AlcoholLevel getAlcoholLevel() {
		return AlcoholLevel.getAlcoholLevelFromValue(alcoholLevel);
	}
	
	public float getAlcoholLevelValue() {
		return alcoholLevel;
	}
	
	public String setAlcoholLevel(float alcoholLevel) {
		this.alcoholLevel = Math.max(0, Math.min(1, alcoholLevel));
		if(this.isPlayer()) {
			return "<p style='text-align:center;'>"
					+ "You start to feel "
						+(this.alcoholLevel>=0.75f
						?"immensely dizzy and light headed as the alcohol quickly enters your system."
						:this.alcoholLevel>=0.5f
						?"incredibly dizzy and light headed as the alcohol quickly enters your system."
						:this.alcoholLevel>=0.3f
						?"very dizzy and light headed as the alcohol quickly enters your system."
						:this.alcoholLevel>=0.15f?"dizzy and light headed as the alcohol quickly enters your system."
						:"a little dizzy and light headed as the alcohol quickly enters your system.")
						+"<br/>"
						+ "Your [style.boldAlcohol(intoxication level)] is now at "+((int)getIntoxicationPercentage())+"%"
					+ "</p>";
		} else {
			return "<p style='text-align:center;'>"
					+UtilText.parse(this,
					"[npc.Name] starts to feel "
						+(this.alcoholLevel>=0.75f
						?"immensely dizzy and light headed as the alcohol quickly enters [npc.her] system."
						:this.alcoholLevel>=0.5f
						?"incredibly dizzy and light headed as the alcohol quickly enters [npc.her] system."
						:this.alcoholLevel>=0.3f
						?"very dizzy and light headed as the alcohol quickly enters [npc.her] system."
						:this.alcoholLevel>=0.15f?"dizzy and light headed as the alcohol quickly enters [npc.her] system."
						:"a little dizzy and light headed as the alcohol quickly enters [npc.her] system.")
					+"<br/>"
					+ "[npc.NamePos] [style.boldAlcohol(intoxication level)] is now at "+((int)getIntoxicationPercentage())+"%")
				+ "</p>";
		}
	}
	
	public String incrementAlcoholLevel(float alcoholLevelIncrement) {
		return setAlcoholLevel(alcoholLevel + alcoholLevelIncrement);
	}
	
	public float getIntoxicationPercentage() {
		return getAlcoholLevelValue()*100;
	}
	
	public Addiction getAddiction(FluidType fluid) {
		for(Addiction add : addictions) {
			if(add.getFluid() == fluid) {
				return add;
			}
		}
		return null;
	}
	
	public List<Addiction> getAddictions() {
		return addictions;
	}
	
	public void clearAddictions() {
		addictions.clear();
	}
	
	public boolean addAddiction(Addiction addiction) {
		for(Addiction add : addictions) {
			if(add.getFluid() == addiction.getFluid()) {
				add.setLastTimeSatisfied(addiction.getLastTimeSatisfied());
				add.getProviderIDs().addAll(addiction.getProviderIDs());
				return true;
			}
		}
		return addictions.add(addiction);
	}
	
	public boolean removeAddiction(Addiction addiction) {
		return addictions.remove(addiction);
	}
	
	public void setLastTimeSatisfiedAddiction(FluidType fluid, long minutes) {
		for(Addiction add : addictions) {
			if(add.getFluid() == fluid) {
				add.setLastTimeSatisfied(minutes);
				return;
			}
		}
	}
	
	public long getLastTimeSatisfiedAddiction(FluidType fluid) {
		for(Addiction add : addictions) {
			if(add.getFluid() == fluid) {
				return add.getLastTimeSatisfied();
			}
		}
		return 0;
	}
	
	public Set<FluidType> getPsychoactiveFluidsIngested() {
		return psychoactiveFluidsIngested;
	}
	
	public void addPsychoactiveFluidIngested(FluidType fluidType) {
		psychoactiveFluidsIngested.add(fluidType);
	}
	
	public void removePsychoactiveEffects() {
		psychoactiveFluidsIngested.clear();
	}
	
	public boolean hasIngestedPsychoactiveFluidType(FluidTypeBase baseType) {
		for(FluidType type : getPsychoactiveFluidsIngested()) {
			if(type.getBaseType()==baseType) {
				return true;
			}
		}
		return false;
	}
	
	
	// Combat:

	public boolean isImmuneToDamageType(DamageType type) {
		return false;
	}
	
	public boolean isStunned() {
		for(StatusEffect se : this.getStatusEffects()) {
			if(se.isStun()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAbleToEscape() {
		return getUnableToEscapeDescription().isEmpty();
	}
	
	public String getUnableToEscapeDescription() {
		for(AbstractClothing clothing : this.getClothingCurrentlyEquipped()) {
			if(clothing.getClothingType().isHindersLegMovement() && !this.isAbleToFly()) {
				return "Escape is blocked due to your "+clothing.getName()+" hindering your movement!";
			}
		}
		return "";
	}
	
	/**
	 * The returned list is ordered by rendering priority.
	 */
	public List<SpecialAttack> getSpecialAttacks() {
		calculateSpecialAttacks();
		List<SpecialAttack> tempListSpecialAttacks = new ArrayList<>(specialAttacks);
		tempListSpecialAttacks.sort(Comparator.comparingInt(SpecialAttack::getRenderingPriority).reversed());
		return tempListSpecialAttacks;
	}


	public void calculateSpecialAttacks() {
		specialAttacks.clear();
		
		for (SpecialAttack sAttack : SpecialAttack.values()) {
			if (sAttack.isConditionsMet(this)) {
				specialAttacks.add(sAttack);
			}
		}
	}
	
	public List<Spell> getSpells() {
		return spells;
	}
	
	public boolean addSpell(Spell spell) {
		if(spells.contains(spell)) {
			return false;
		}
		return spells.add(spell);
	}
	
	public boolean hasSpell(Spell spell) {
		return spells.contains(spell);
	}
	

	public boolean hasAnySpellInSchool(SpellSchool school) {
		for(Spell s : getSpells()) {
			if(s.getSpellSchool()==school) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Hard reset of spells and spell upgrades, without refunding any points.
	 */
	public void resetSpells() {
		getSpells().clear();
		getSpellUpgrades().clear();
	}
	
	public void clearSpellUpgradePoints() {
		for(SpellSchool school : SpellSchool.values()) {
			this.spellUpgradePoints.put(school, 0);
		}
	}
	
	/** Spells from weapons. */
	public List<Spell> getExtraSpells() {
		List<Spell> tempListSpells = new ArrayList<>();
		
		if(getMainWeapon()!=null) {
			if(getMainWeapon().getSpells()!=null) {
				tempListSpells.addAll(getMainWeapon().getSpells());
			}
		}
		
		if(getOffhandWeapon()!=null) {
			if(getOffhandWeapon().getSpells()!=null) {
				tempListSpells.addAll(getOffhandWeapon().getSpells());
			}
		}
		
		return tempListSpells;
	}

	public List<Spell> getAllSpells() {
		List<Spell> tempListSpells = new ArrayList<>();

		tempListSpells.addAll(getSpells());
		tempListSpells.addAll(getExtraSpells());
		
		Set<Spell> spellSet = new HashSet<>(tempListSpells); // Remove duplicates
		
		tempListSpells.clear();
		tempListSpells.addAll(spellSet);
		tempListSpells.sort((s1, s2) -> s1.getSpellSchool().compareTo(s2.getSpellSchool()));
		
		return tempListSpells;
	}
	
	public boolean isSpellSchoolSpecialAbilityUnlocked(SpellSchool school) {
		int spellCount = 0;
		for(Spell s : this.getSpells()) {
			if(s.getSpellSchool()==school) {
				spellCount++;
			}
		}
		return spellCount>=3;
	}
	
	public Set<SpellUpgrade> getSpellUpgrades() {
		return spellUpgrades;
	}
	
	public boolean addSpellUpgrade(SpellUpgrade spellUpgrade) {
		return spellUpgrades.add(spellUpgrade);
	}
	
	public boolean hasSpellUpgrade(SpellUpgrade spellUpgrade) {
		return spellUpgrades.contains(spellUpgrade);
	}
	
	public void resetSpellUpgrades(SpellSchool school) {
		for(SpellUpgrade upgrade : getSpellUpgrades()) {
			if(upgrade.getSpellSchool()==school) {
				this.setSpellUpgradePoints(upgrade.getSpellSchool(), getSpellUpgradePoints(upgrade.getSpellSchool())+upgrade.getPointCost());
			}
		}
		getSpellUpgrades().removeIf((su) -> su.getSpellSchool()==school);
	}
	
	public int getSpellUpgradePoints(SpellSchool spellSchool) {
		spellUpgradePoints.putIfAbsent(spellSchool, 0);
		return spellUpgradePoints.get(spellSchool);
	}
	
	public void setSpellUpgradePoints(SpellSchool spellSchool, int points) {
		spellUpgradePoints.put(spellSchool, points);
	}

	public void incrementSpellUpgradePoints(SpellSchool spellSchool, int increment) {
		setSpellUpgradePoints(spellSchool, getSpellUpgradePoints(spellSchool) + increment);
	}
	
	public boolean isAbleToTeleport() {
		return this.hasSpell(Spell.TELEPORT) && (this.getCompanions().isEmpty() || this.hasSpellUpgrade(SpellUpgrade.TELEPORT_2));
	}
	
	public float getRegenerationRate() {
		if(this.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.AIR)) {
			return 0.2f;
		} else {
			return 0.1f;
		}
	}
	
	public float getHealth() {
		if(health>getAttributeValue(Attribute.HEALTH_MAXIMUM)) {
			health = getAttributeValue(Attribute.HEALTH_MAXIMUM);
		}
		return health;
	}

	public float getHealthPercentage() {
		return health / getAttributeValue(Attribute.HEALTH_MAXIMUM);
	}

	public String incrementHealth(float increment) {
		return incrementHealth(null, increment);
	}

	public String incrementHealth(GameCharacter attacker, float increment) {
		// Fetishes:
		if(Main.game.isInCombat()) {
			// Masochist:
			if (isMasochist() && increment < 0) {
				float manaLoss = (Math.round((increment*0.25f)*10))/10f;
				incrementLust(-manaLoss);
				setHealth(getHealth() + (increment*0.75f));
				
				this.incrementFetishExperience(Fetish.FETISH_MASOCHIST, 2);
				
				if (isPlayer()) {
					return ("<p>"
							+ "Due to your <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>masochist fetish</b>, incoming damage is reduced by 25%, but in turn, you take"
									+ " <b>"+(-manaLoss)+"</b> <b style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</b> as you struggle to control your arousal!"
							+ "</p>");
				} else {
					return (UtilText.parse(this,
							"<p>"
								+ "Due to [npc.namePos] <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>masochist fetish</b>, incoming damage is reduced by 25%, but in turn, [npc.she] takes"
								+ " <b>"+(-manaLoss)+"</b> <b style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</b> as [npc.she] struggles to control [npc.her] arousal!"
							+ "</p>"));
				}
			// Sadist:
			} else if (attacker!=null && attacker.hasFetish(Fetish.FETISH_SADIST) && increment < 0) {
				float manaLoss = (Math.round((increment*0.1f)*10))/10f;
				
				this.incrementFetishExperience(Fetish.FETISH_SADIST, 2);

				attacker.incrementLust(-manaLoss);
				setHealth(getHealth() + increment);
				
				if (!attacker.isPlayer()) {
					return (UtilText.parse(attacker,
							"<p>"
								+ "Due to [npc.her] <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>sadist fetish</b>, [npc.name] suffers <b>"+(-manaLoss)+"</b>"
										+ " <b style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</b> as [npc.she] gets aroused by inflicting damage!"
							+ "</p>"));
					
				} else {
					return ("<p>"
							+ "Due to your <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>sadist fetish</b>, you suffer <b>"+(-manaLoss)+"</b>"
									+ " <b style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</b> as you get aroused by inflicting damage!"
							+ "</p>");
				}
				
				
			} else {
				setHealth(getHealth() + increment);
				return "";
			}
			
		} else {
			setHealth(getHealth() + increment);
			return "";
		}
	}


	public void setHealth(float health) {
		if (health < 0)
			this.health = 0;
		else if (health > getAttributeValue(Attribute.HEALTH_MAXIMUM))
			this.health = getAttributeValue(Attribute.HEALTH_MAXIMUM);
		else
			this.health = health;

		updateAttributeListeners();
	}

	/**
	 * @param percentage Use value of 0 -> 1
	 */
	public void setHealthPercentage(float percentage) {
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM) * percentage);
	}

	public float getMana() {
		return mana;
	}


	public float getManaPercentage() {
		return mana / getAttributeValue(Attribute.MANA_MAXIMUM);
	}


	public void setMana(float mana) {
		if (mana < 0)
			this.mana = 0;
		else if (mana > getAttributeValue(Attribute.MANA_MAXIMUM))
			this.mana = getAttributeValue(Attribute.MANA_MAXIMUM);
		else
			this.mana = mana;

		updateAttributeListeners();
	}
	
	/**
	 * @param percentage Use value of 0 -> 1
	 */
	public void setManaPercentage(float percentage) {
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM) * percentage);
	}
	
	public void incrementMana(float increment) {
		setMana(getMana() + increment);
	}

	public float getArousal() {
		return getAttributeValue(Attribute.AROUSAL);
	}
	
	public void setArousal(float arousal) {
		if (arousal < 0) {
			setAttribute(Attribute.AROUSAL, 0, false);
			
		} else if (arousal > 100) {
			setAttribute(Attribute.AROUSAL, 100, false);
			
		} else {
			setAttribute(Attribute.AROUSAL, arousal, false);
		}

		updateAttributeListeners();
	}
	
	public void incrementArousal(float increment) {
		setArousal(getArousal() + increment);
	}
	
	public float getLust() {
		return getAttributeValue(Attribute.LUST);
	}
	
	public LustLevel getLustLevel() {
		return LustLevel.getLustLevelFromValue(getAttributeValue(Attribute.LUST));
	}
	
	public void alignLustToRestingLust(int minutes) {
		if(hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) {
			setLust(75);
		} else {
			if(getLust()>getRestingLust()) {
				incrementLust(-Math.min(getLust()-getRestingLust(), 0.5f*minutes));
			} else if((int)getLust()!=getRestingLust()) {
				incrementLust(Math.min(getRestingLust()-getLust(), 0.5f*minutes));
			}
		}
	}
	
	public int getRestingLust() {
		if(hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) {
			return 75;
		}
		return (int) Math.round(getAttributeValue(Attribute.MAJOR_CORRUPTION)/2);
	}
	
	public String setLust(float lust) {
		if (lust < 0) {
			setAttribute(Attribute.LUST, 0, false);
			
		} else if (lust > 100) {
			setAttribute(Attribute.LUST, 100, false);
			
		} else {
			setAttribute(Attribute.LUST, lust, false);
		}

		updateAttributeListeners();
		
		if(this.isPlayer()) {
			return "<p style='text-align:center;'>"
						+ "You are now feeling <b style='color:"+this.getLustLevel().getColour().toWebHexString()+";'>"+this.getLustLevel().getName()+"</b>."
					+ "</p>";
		} else {
			return "<p style='text-align:center;'>"
					+ UtilText.parse(this, "[npc.Name] is now feeling <b style='color:"+this.getLustLevel().getColour().toWebHexString()+";'>"+this.getLustLevel().getName()+"</b>.")
				+ "</p>";
		}
	}
	
	public String incrementLust(float increment) {
		return setLust(getLust() + increment);
	}
	
	public boolean isVulnerableToLustLoss() {
		return this.getAttributeValue(Attribute.MAJOR_ARCANE) < IntelligenceLevel.TWO_SMART.getMinimumValue();
	}
	
	
	public boolean isWearingCondom() {
//		if(Main.game.isInSex() && this == Sex.getPartner())
//			System.out.println("condom inner: "+(this.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot())!=null));
		return this.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot())!=null && this.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getClothingType().equals(ClothingType.PENIS_CONDOM);
	}
	
	public boolean isExhibitionist() {
		return hasFetish(Fetish.FETISH_EXHIBITIONIST);
	}
	
	public boolean isMasochist() {
		return hasFetish(Fetish.FETISH_MASOCHIST);
	}
	
	public boolean isSubmissive() {
		return hasFetish(Fetish.FETISH_SUBMISSIVE);
	}
	
	// Pregnancy:
	
	/**
	 * @return Whether the supplied character has reacted to this GameCharacter's pregnancy.
	 */
	public boolean isCharacterReactedToPregnancy(GameCharacter character) {
		return pregnancyReactions.contains(character.getId());
	}
	
	public void setCharacterReactedToPregnancy(GameCharacter pregnantCharacter, boolean reactedToPregnancy) {
		if(reactedToPregnancy) {
			pregnancyReactions.add(pregnantCharacter.getId());
		} else {
			pregnancyReactions.remove(pregnantCharacter.getId());
		}
	}
	
	public void resetAllPregnancyReactions() {
		pregnancyReactions.clear();
	}
	
	public static final String PREGNANCY_CALCULATION = "((Virility% * Cum Production Modifier) + Fertility%) / 3";

	public void performHourlyFluidsCheck() {
		for(SexAreaOrifice ot : SexAreaOrifice.values()) {
			if(this.fluidsStoredMap.get(ot)!=null) {
				for(FluidStored fs : this.fluidsStoredMap.get(ot)) {
					if(fs.getFluid().getFluidModifiers().contains(FluidModifier.ADDICTIVE)) {
						addAddiction(new Addiction(fs.getFluid().getType(), Main.game.getMinutesPassed(), fs.getCharactersFluidID()));
					}
					if(fs.getFluid().getFluidModifiers().contains(FluidModifier.HALLUCINOGENIC)) {
						this.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60);
					}
				}
			}
		}
			
		// Impregnation:
		performImpregnationCheck();
	}
	
	public void performImpregnationCheck() {
		if(this.fluidsStoredMap.get(SexAreaOrifice.VAGINA)!=null && !this.fluidsStoredMap.get(SexAreaOrifice.VAGINA).isEmpty()) {
			List<FluidStored> fluids = new ArrayList<>(this.fluidsStoredMap.get(SexAreaOrifice.VAGINA));
			Collections.shuffle(fluids);
			for(FluidStored fs : fluids) {
				if(fs.isCum()) {
					GameCharacter partner = null;
					if(fs.getCharactersFluidID().equals(Main.game.getPlayer().getId())) {
						partner = Main.game.getPlayer();
					} else {
						try {
							partner = Main.game.getNPCById(fs.getCharactersFluidID());
						} catch(Exception e) {
							System.err.println("Main.game.getNPCById("+fs.getCharactersFluidID()+") returning null in method: performImpregnationCheck()");
						}
					}
					if(partner!=null) {
						rollForPregnancy(partner, fs.getMillilitres());
					}
				}
			}
		}
	}
	
	public String rollForPregnancy(GameCharacter partner, int cumQuantity) {
		if(partner instanceof Elemental) {
			return PregnancyDescriptor.NO_CHANCE.getDescriptor(this, partner)
					+"<p style='text-align:center;'>[style.italicsMinorBad(Elementals cannot impregnate anyone!)]<br/>[style.italicsDisabled(I will add support for impregnating/being impregnated by elementals soon!)]</p>";
		}
		
		float pregnancyChance = 0;
		
		if((partner.hasPerkAnywhereInTree(Perk.FIRING_BLANKS) && partner.getAttributeValue(Attribute.VIRILITY)<=0)
				|| (this.hasPerkAnywhereInTree(Perk.BARREN) && this.getAttributeValue(Attribute.FERTILITY)<=0)) {
			pregnancyChance = 0;
			
		} else {
			pregnancyChance = 0;
			pregnancyChance += (Util.getModifiedDropoffValue(partner.getAttributeValue(Attribute.VIRILITY), Attribute.VIRILITY.getUpperLimit())/100f) * CumProduction.getCumProductionFromInt(cumQuantity).getPregnancyModifier();
			pregnancyChance += (Util.getModifiedDropoffValue(getAttributeValue(Attribute.FERTILITY), Attribute.FERTILITY.getUpperLimit())/100f);
			pregnancyChance /= 3;
		}
		
		if (!isAbleToBeImpregnated()) {
			pregnancyChance = 0;
		}
		
		if(pregnancyChance<0) {
			pregnancyChance=0;
		}
		
		if(pregnancyChance>1) {
			pregnancyChance=1;
		}
		
		String s;
		
		if(isVisiblyPregnant()) {
			s = PregnancyDescriptor.ALREADY_PREGNANT.getDescriptor(this, partner);
			
		} else {
			PregnancyPossibility pregPoss = new PregnancyPossibility(this.getId(), partner.getId(), pregnancyChance);
			
			this.addPotentialPartnerAsMother(pregPoss);
			partner.addPotentialPartnerAsFather(pregPoss);

			if (pregnancyChance <= 0) {
				s = PregnancyDescriptor.NO_CHANCE.getDescriptor(this, partner);
			} else if(pregnancyChance<=0.15f) {
				s = PregnancyDescriptor.LOW_CHANCE.getDescriptor(this, partner);
			} else if(pregnancyChance<=0.3f) {
				s = PregnancyDescriptor.AVERAGE_CHANCE.getDescriptor(this, partner);
			} else if(pregnancyChance<1) {
				s = PregnancyDescriptor.HIGH_CHANCE.getDescriptor(this, partner);
			} else {
				s = PregnancyDescriptor.CERTAINTY.getDescriptor(this, partner);
			}
		}
		
		if (!hasStatusEffect(StatusEffect.PREGNANT_0) && !isPregnant()) {
			addStatusEffect(StatusEffect.PREGNANT_0, 60 * (4 + Util.random.nextInt(5)));
		}
		
		// Now roll for pregnancy:
		if (!isPregnant()) {
			if (Math.random() <= pregnancyChance) {
				
				int minimumNumberOfChildren = 1;
				int maximumNumberOfChildren = 1;
				
				if(this.getBodyMaterial()==BodyMaterial.SLIME) {
					minimumNumberOfChildren = Race.SLIME.getNumberOfOffspringLow();
					maximumNumberOfChildren = Race.SLIME.getNumberOfOffspringHigh();
					
				} else {
					if(getVaginaType()==VaginaType.HUMAN) {
						if(partner.getPenisType().getRace()==null) {
							minimumNumberOfChildren = partner.getRace().getNumberOfOffspringLow();
							maximumNumberOfChildren = partner.getRace().getNumberOfOffspringHigh();
						} else {
							minimumNumberOfChildren = partner.getPenisType().getRace().getNumberOfOffspringLow();
							maximumNumberOfChildren = partner.getPenisType().getRace().getNumberOfOffspringHigh();
						}
						
					} else {
						if(getVaginaType().getRace()==null) {
							minimumNumberOfChildren = getRace().getNumberOfOffspringLow();
							maximumNumberOfChildren = getRace().getNumberOfOffspringHigh();
						} else {
							minimumNumberOfChildren = getVaginaType().getRace().getNumberOfOffspringLow();
							maximumNumberOfChildren = getVaginaType().getRace().getNumberOfOffspringHigh();
						}
					}
				}
				
				if(partner.hasTraitActivated(Perk.FETISH_SEEDER)) {
					maximumNumberOfChildren*=2;
				}
				if(hasTraitActivated(Perk.FETISH_BROODMOTHER)) {
					maximumNumberOfChildren*=2;
				}
				
				int numberOfChildren = minimumNumberOfChildren + Util.random.nextInt((maximumNumberOfChildren-minimumNumberOfChildren)+1);
				
				List<NPC> offspring = new ArrayList<>();
				for (int i = 0; i < numberOfChildren; i++) { // Add children here:
					NPC npc = new NPCOffspring(this, partner);
					offspring.add(npc);
					try {
						Main.game.addNPC(npc, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				pregnantLitter = new Litter(Main.game.getDateNow(), Main.game.getDateNow(), this, partner, offspring);
			}
		}
		
		return s;
	}

	public boolean isPregnant() {
		return pregnantLitter != null;
	}
	
	public boolean isHasAnyPregnancyEffects() {
		return hasStatusEffect(StatusEffect.PREGNANT_0) || hasStatusEffect(StatusEffect.PREGNANT_1) || hasStatusEffect(StatusEffect.PREGNANT_2) || hasStatusEffect(StatusEffect.PREGNANT_3);
	}
	
	public boolean isVisiblyPregnant() {
		return pregnantLitter != null && !hasStatusEffect(StatusEffect.PREGNANT_0);
	}
	
	public long getTimeProgressedToFinalPregnancyStage() {
		return timeProgressedToFinalPregnancyStage;
	}

	public void setTimeProgressedToFinalPregnancyStage(long timeProgressedToFinalPregnancyStage) {
		this.timeProgressedToFinalPregnancyStage = timeProgressedToFinalPregnancyStage;
	}

	/**
	 * Ends the character's pregnancy. If ended with birth, also handles litters
	 * added to the littersBirthed list.
	 * 
	 * @param withBirth
	 *            True if this pregnancy ends by giving birth.
	 */
	public void endPregnancy(boolean withBirth) {
		for(PregnancyPossibility pregPoss : potentialPartnersAsMother) {
			if(pregPoss.getFather()!=null) {
				pregPoss.getFather().getPotentialPartnersAsFather().remove(pregPoss);
			}
		}
		potentialPartnersAsMother.clear();
		
		if (withBirth) {
			Litter birthedLitter = pregnantLitter;
			
			if((birthedLitter.getFather()!=null && birthedLitter.getFather().isPlayer()) || (birthedLitter.getMother()!=null && birthedLitter.getMother().isPlayer())) {
				for(String id: birthedLitter.getOffspring()) {
					try {
						NPC npc = (NPC) Main.game.getNPCById(id);
						birthedLitter.setBirthDate(Main.game.getDateNow());
						npc.setConceptionDate(birthedLitter.getConceptionDate());
						npc.setBirthday(LocalDateTime.of(Main.game.getDateNow().getYear(), Main.game.getDateNow().getMonth(), Main.game.getDateNow().getDayOfMonth(), Main.game.getDateNow().getHour(), Main.game.getDateNow().getMinute()));
					} catch(Exception e) {
						System.err.println("Main.game.getNPCById("+id+") returning null in method: endPregnancy()");
					}
				}
			}
			
			littersBirthed.add(birthedLitter);
			
			if(birthedLitter.getFather()!=null) {
				birthedLitter.getFather().getLittersFathered().add(birthedLitter);
			}
		} else {
			if(pregnantLitter!=null) {
				for(String npc : pregnantLitter.getOffspring()) {
					Main.game.removeNPC(npc);
				}
			}
		}
		
		removeStatusEffect(StatusEffect.PREGNANT_1);
		removeStatusEffect(StatusEffect.PREGNANT_2);
		removeStatusEffect(StatusEffect.PREGNANT_3);

		pregnantLitter = null;
	}

	public List<Litter> getLittersBirthed() {
		return littersBirthed;
	}

	public Litter getLastLitterBirthed() {
		if(littersBirthed.isEmpty()) {
			return null;
		}
		return littersBirthed.get(littersBirthed.size() - 1);
	}
	
	public List<Litter> getLittersFathered() {
		return littersFathered;
	}
	
	public Litter getPregnantLitter() {
		return pregnantLitter;
	}
	
	public void setPregnantLitter(Litter pregnantLitter) {
		this.pregnantLitter = pregnantLitter;
	}
	
	public boolean isCharacterPossiblyMother(String id) {
		for(PregnancyPossibility pp : getPotentialPartnersAsMother()) {
			if(pp.getMotherId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public List<PregnancyPossibility> getPotentialPartnersAsMother() {
		return potentialPartnersAsMother;
	}
	
	public void addPotentialPartnerAsMother(PregnancyPossibility possibility) {
		boolean alreadyContainedPossibility = false;
		
		for(PregnancyPossibility currentPossibility : potentialPartnersAsMother) {
			if(currentPossibility.getMotherId().equals(possibility.getMotherId())
					&& currentPossibility.getFatherId().equals(possibility.getFatherId())) {
				currentPossibility.setProbability(1f-(1-currentPossibility.getProbability() * 1-possibility.getProbability()));
				alreadyContainedPossibility = true;
				break;
			}
		}
		
		if(!alreadyContainedPossibility) {
			potentialPartnersAsMother.add(possibility);
		}
	}
	
	public boolean isCharacterPossiblyFather(String id) {
		for(PregnancyPossibility pp : getPotentialPartnersAsFather()) {
			if(pp.getFatherId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public List<PregnancyPossibility> getPotentialPartnersAsFather() {
		return potentialPartnersAsFather;
	}
	
	public void addPotentialPartnerAsFather(PregnancyPossibility possibility) {
		boolean alreadyContainedPossibility = false;
		
		for(PregnancyPossibility currentPossibility : potentialPartnersAsFather) {
			if(currentPossibility.getMotherId().equals(possibility.getMotherId())
					&& currentPossibility.getFatherId().equals(possibility.getFatherId())) {
				currentPossibility.setProbability(1f-(1-currentPossibility.getProbability() * 1-possibility.getProbability()));
				alreadyContainedPossibility = true;
				break;
			}
		}
		
		if(!alreadyContainedPossibility) {
			potentialPartnersAsFather.add(possibility);
		}
	}
	
	// Cummed in areas:
	
	public List<FluidStored> getAllFluidsStored() {
		List<FluidStored> list = new ArrayList<>();
		for(List<FluidStored> stored : fluidsStoredMap.values()) {
			list.addAll(stored);
		}
		return list;
	}
	
	/**
	 * @return false If the area is not storing any fluids, or if the stored fluids are not only cum.
	 */
	public boolean isOnlyCumInArea(SexAreaOrifice area) {
		for(FluidStored f : fluidsStoredMap.get(area)) {
			if(!f.isCum()) {
				return false;
			}
		}
		return !fluidsStoredMap.get(area).isEmpty();
	}
	
	public int getTotalFluidInArea(SexAreaOrifice area) {
		int total = 0;
		fluidsStoredMap.putIfAbsent(area, new ArrayList<>());
		for(FluidStored f : fluidsStoredMap.get(area)) {
			total+=f.getMillilitres();
		}
		return total;
	}
	
	public void drainTotalFluidsStored(SexAreaOrifice area, int drain) {
		fluidsStoredMap.putIfAbsent(area, new ArrayList<>());
		int drained = 0;
		for(FluidStored f : fluidsStoredMap.get(area)) {
			if(drained>=Math.abs(drain)) {
				break;
			}
			
			int drainAmount = Math.min(Math.abs(drain), f.getMillilitres());
			f.incrementMillilitres(-drainAmount);
			drained+=drainAmount;
		}
		fluidsStoredMap.get(area).removeIf((fs) -> fs.getMillilitres()<=0);
	}
	
	public void incrementAllFluidsStored(SexAreaOrifice area, int increment) {
		fluidsStoredMap.putIfAbsent(area, new ArrayList<>());
		for(FluidStored f : fluidsStoredMap.get(area)) {
			f.incrementMillilitres(increment);
		}
		fluidsStoredMap.get(area).removeIf((fs) -> fs.getMillilitres()<=0);
	}
	
	public void addFluidStored(SexAreaOrifice area, FluidStored fluid) {
		fluidsStoredMap.putIfAbsent(area, new ArrayList<>());
		fluidsStoredMap.get(area).add(fluid);
	}
	
	public void clearFluidsStored(SexAreaOrifice area) {
		fluidsStoredMap.putIfAbsent(area, new ArrayList<>());
		fluidsStoredMap.get(area).clear();
	}
	
	public void resetFluidsStored() {
		for(List<FluidStored> f : fluidsStoredMap.values()) {
			f.clear();
		}
	}
	
	public void washAllOrifices() {
		for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
			switch(orifice) {
				case MOUTH:
					break;
				case ASS: case BREAST: case THIGHS:
					clearFluidsStored(orifice);
					break;
				case ANUS: case NIPPLE: case URETHRA_PENIS: case URETHRA_VAGINA: case VAGINA:
					drainTotalFluidsStored(orifice, 500);
					break;
			}
		}
	}

	// Other:
	
	public Vector2i getLocation() {
		return location;
	}

	public void setLocation(Vector2i location) {
		this.location = location;
		if(this.companions != null) {
			for(String companionID : this.companions) {
				try {
					Main.game.getNPCById(companionID).setLocation(getWorldLocation(), location, false);
				} catch(Exception e) {
					System.err.println("Main.game.getNPCById("+companionID+") returning null in method: setLocation()");
				}
			}
		}
		
		updateLocationListeners();
	}
	
	public Vector2i getHomeLocation() {
		return homeLocation;
	}
	
	public WorldType getWorldLocation() {
		return worldLocation;
	}

	public void setWorldLocation(WorldType worldLocation) {
		if(this.worldLocation != worldLocation && this.isPlayer()) {
			Main.game.setRequestAutosave(true);
		}
		this.worldLocation = worldLocation;
	}
	
	public WorldType getHomeWorldLocation() {
		return homeWorldLocation;
	}
	
	public GenericPlace getLocationPlace() {
		return Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getPlace();
	}
	
	public GenericPlace getHomeLocationPlace() {
		return Main.game.getWorlds().get(getHomeWorldLocation()).getCell(getHomeLocation()).getPlace();
	}
	
	public void setLocation(WorldType worldType, Vector2i location, boolean setAsHomeLocation) {
		setWorldLocation(worldType);
		setLocation(location);
		if(setAsHomeLocation) {
			setHomeLocation(worldType, location);
		}
//		if(this.isPlayer() && Main.game.isStarted() && Main.game.getCurrentDialogueNode().equals(Main.game.getDefaultDialogueNoEncounter())) {
//			Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(), true);
//		}
	}

	public void setRandomUnoccupiedLocation(WorldType worldType, PlaceType placeType, boolean setAsHomeLocation) {
		setLocation(worldType, Main.game.getWorlds().get(worldType).getRandomUnoccupiedCell(placeType).getLocation(), setAsHomeLocation);
	}

	/**
	 * Moves this character to a random unoccupied cell, of one of the supplied placeTypes. If none of the placeTypes have an unoccupied cell, this character is moved to a random occupied one instead.
	 */
	public void setRandomUnoccupiedLocation(WorldType worldType, boolean setAsHomeLocation, PlaceType... placeTypes) {
		List<Cell> unoccupiedCells = new ArrayList<>();
		List<Cell> occupiedCells = new ArrayList<>();
		
		for(PlaceType pt : placeTypes) {
			Cell c = Main.game.getWorlds().get(worldType).getRandomUnoccupiedCell(pt);
			if(Main.game.getCharactersPresent(c).isEmpty()) {
				unoccupiedCells.add(c);
			} else {
				occupiedCells.add(c);
			}
		}
		
		if(!unoccupiedCells.isEmpty()) {
			setLocation(worldType, Util.randomItemFrom(unoccupiedCells).getLocation(), setAsHomeLocation);
		} else {
			setLocation(worldType, Util.randomItemFrom(occupiedCells).getLocation(), setAsHomeLocation);
		}
	}
	
	public void setRandomLocation(WorldType worldType, PlaceType placeType, boolean setAsHomeLocation) {
		setLocation(worldType, Main.game.getWorlds().get(worldType).getRandomCell(placeType).getLocation(), setAsHomeLocation);
	}
	
	public void setNearestLocation(WorldType worldType, PlaceType placeType, boolean setAsHomeLocation) {
		setLocation(worldType, Main.game.getWorlds().get(worldType).getNearestCell(placeType, this.getLocation()).getLocation(), setAsHomeLocation);
	}
	
	public void setLocation(WorldType worldType, PlaceType placeType) {
		setLocation(worldType, placeType, false);
	}
	
	public void setLocation(WorldType worldType, PlaceType placeType, boolean setAsHomeLocation) {
		setLocation(worldType, Main.game.getWorlds().get(worldType).getClosestCell(this.getLocation(), placeType).getLocation(), setAsHomeLocation);
	}
	
	/**
	 * Moves this character to an adjoining Cell which shares the PlaceType of the Cell that the character is already in.
	 * @param additionalPlaceTypes Any additional PlaceTypes that should be allowed for movement.
	 * @return True if the character was moved.
	 */
	public boolean moveToAdjacentMatchingCellType(PlaceType... additionalPlaceTypes) {
		World world = Main.game.getWorlds().get(this.getWorldLocation());
		List<Vector2i> availableLocations = new ArrayList<>();
		
		List<PlaceType> acceptablePlaceTypes = new ArrayList<>();
		Collections.addAll(acceptablePlaceTypes, additionalPlaceTypes);
		acceptablePlaceTypes.add(getLocationPlace().getPlaceType());
		
		if(world.getCell(this.getLocation().getX()+1, this.getLocation().getY())!=null && acceptablePlaceTypes.contains(world.getCell(this.getLocation().getX()+1, this.getLocation().getY()).getPlace().getPlaceType())) {
			availableLocations.add(new Vector2i(this.getLocation().getX()+1, this.getLocation().getY()));
		}
		if(world.getCell(this.getLocation().getX()-1, this.getLocation().getY())!=null && acceptablePlaceTypes.contains(world.getCell(this.getLocation().getX()-1, this.getLocation().getY()).getPlace().getPlaceType())) {
			availableLocations.add(new Vector2i(this.getLocation().getX()-1, this.getLocation().getY()));
		}
		if(world.getCell(this.getLocation().getX(), this.getLocation().getY()+1)!=null && acceptablePlaceTypes.contains(world.getCell(this.getLocation().getX(), this.getLocation().getY()+1).getPlace().getPlaceType())) {
			availableLocations.add(new Vector2i(this.getLocation().getX(), this.getLocation().getY()+1));
		}
		if(world.getCell(this.getLocation().getX(), this.getLocation().getY()-1)!=null && acceptablePlaceTypes.contains(world.getCell(this.getLocation().getX(), this.getLocation().getY()-1).getPlace().getPlaceType())) {
			availableLocations.add(new Vector2i(this.getLocation().getX(), this.getLocation().getY()-1));
		}
		
		if(availableLocations.isEmpty()) {
			return false;
		} else {
			this.setLocation(availableLocations.get(Util.random.nextInt(availableLocations.size())));
			return true;
		}
	}
	
	public void setHomeLocation() {
		this.homeWorldLocation = this.getWorldLocation();
		this.homeLocation = this.getLocation();
	}
	
	public void setHomeLocation(WorldType homeWorldLocation, PlaceType placeType) {
		this.homeWorldLocation = homeWorldLocation;
		this.homeLocation = Main.game.getWorlds().get(homeWorldLocation).getCell(placeType).getLocation();
	}
	
	public void setHomeLocation(WorldType homeWorldLocation, Vector2i location) {
		this.homeWorldLocation = homeWorldLocation;
		this.homeLocation = location;
	}
	
	public void returnToHome() {
		setLocation(homeWorldLocation, homeLocation, true);
	}
	
	public boolean isAtHome() {
		return this.getLocation().equals(this.getHomeLocation()) && this.getWorldLocation().equals(this.getHomeWorldLocation());
	}
	
	private int getTrueLevel() {
		return level;
	}
	
	public int getLevel() {
		if(this.isPlayer()
				|| !Main.getProperties().difficultyLevel.isNPCLevelScaling()
				|| (this.isSlave() && this.getOwner().isPlayer())
				|| Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				|| (this.getPartyLeader()!=null && this.getPartyLeader().isPlayer())) {
			return level;
			
		} else {
			if(Main.getProperties().difficultyLevel == DifficultyLevel.HELL) {
				if(level < Main.game.getPlayer().getLevel() * 2) {
					return Main.game.getPlayer().getLevel() * 2;
				} else {
					return level;
				}
			} else if(level < Main.game.getPlayer().getLevel()) {
				return Main.game.getPlayer().getLevel();
			} else {
				return level;
			}
		}
	}

	public void setLevel(int level) {
		this.level = level;
	}

	// -------------------- Inventory -------------------- //

	public String droppedItemText(AbstractCoreItem item) {
		if(this.getLocationPlace().isItemsDisappear()) {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
							+ "You drop your " + item.getName() + " on the floor."
							+ "<br/>"
							+ "<span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>The " + item.getName() + " will be lost if you leave this location!</span>"
						+ "</p>";
			} else {
				return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "[npc.Name] drops [npc.her] " + item.getName() + " on the floor."
						+ "<br/>"
						+ "<span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>The " + item.getName() + " will be lost if you leave this location!</span>"
					+ "</p>");
			}
			
		} else {
			if(isPlayer()) {
				return "<p style='text-align:center;'>"
							+ "You drop your " + item.getName() + " on the floor."
							+ "<br/>"
							+ "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>The " + item.getName() + " will be stored safely in this location!</span>"
						+ "</p>";
			} else {
				return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "[npc.Name] drops [npc.her] " + item.getName() + " on the floor."
						+ "<br/>"
						+ "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>The " + item.getName() + " will be stored safely in this location!</span>"
					+ "</p>");
			}
		}
	}

	public String addedItemToInventoryText(AbstractCoreItem item) {
		String returnString = "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Item added to "+(this.isPlayer()?"":"[npc.namePos] ")+"inventory:</b> <b>" + item.getName() + "</b>";
		
		if(item instanceof AbstractItem) {
			returnString = "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Item added to "+(this.isPlayer()?"":"[npc.namePos] ")+"inventory:</b> <b>" + ((AbstractItem)item).getDisplayName(true) + "</b>";
			
		} else if(item instanceof AbstractClothing) {
			returnString = "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Clothing added to "+(this.isPlayer()?"":"[npc.namePos] ")+"inventory:</b> <b>" + ((AbstractClothing)item).getDisplayName(true) + "</b>";
			
		} else if(item instanceof AbstractWeapon) {
			returnString = "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Weapon added to "+(this.isPlayer()?"":"[npc.namePos] ")+"inventory:</b> <b>" + ((AbstractWeapon)item).getDisplayName(true) + "</b>";
		}
		
		return UtilText.parse(this, returnString);
	}

	public String addedItemToInventoryText(AbstractItemType item) {
		return "<p style='text-align:center;'>" + "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You add the " + item.getName(false) + " to your inventory.</span>" + "</p>";
	}

	public String inventoryFullText() {
		return "<p style='text-align:center;'>" + "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full!</b>" + "</p>";
	}
	
	/**
	 * First unequips all clothing into void, so that clothing effects are preserved.
	 */
	public void resetInventory(boolean includeWeapons){
		unequipAllClothingIntoVoid(includeWeapons);
		
		this.inventory = new CharacterInventory(0);
	}
	
	public void unequipAllClothingIntoVoid(boolean includeWeapons) {
		List<AbstractClothing> clothingEquipped = new ArrayList<>(this.getClothingCurrentlyEquipped());
		for(AbstractClothing clothing : clothingEquipped) {
			clothing.setSealed(false);
		}
		for(AbstractClothing clothing : clothingEquipped) {
			this.unequipClothingIntoVoid(clothing, true, this);
		}
		
		if(includeWeapons) {
			this.unequipMainWeaponIntoVoid();
			this.unequipOffhandWeaponIntoVoid();
		}
	}
	
	public int getMoney() {
		return inventory.getMoney();
	}

	public void setMoney(int money) {
		inventory.setMoney(money);
	}
	
	public String incrementMoney(int money) {
		int moneyLoss = Math.min(-money, this.getMoney());
		
		inventory.incrementMoney(money);
		
		if(this.isPlayer()) {
			if(money>0) {
				return "<div class='container-full-width' style='text-align:center;'>You [style.colourGood(gained)] " + UtilText.formatAsMoney(money) + "!</div>";
			} else {
				return "<div class='container-full-width' style='text-align:center;'>You [style.colourBad(lost)] " + UtilText.formatAsMoney(moneyLoss) + "!</div>";
			}
		} else {
			if(money>0) {
				return "<div class='container-full-width' style='text-align:center;'>"+UtilText.parse(this, "[npc.Name]")+" [style.colourGood(gained)] " + UtilText.formatAsMoney(money) + "!</div>";
			} else {
				return "<div class='container-full-width' style='text-align:center;'>"+UtilText.parse(this, "[npc.Name]")+" [style.colourBad(lost)] " + UtilText.formatAsMoney(moneyLoss) + "!</div>";
			}
		}
	}
	
	// Essences:
	
	public Map<TFEssence, Integer> getEssenceMap() {
		return inventory.getEssenceMap();
	}
	
	public int getEssenceCount(TFEssence essence) {
		return getEssenceMap().get(essence);
	}
	
	public void setEssenceCount(TFEssence essence, int amount) {
		getEssenceMap().put(essence, amount);
	}
	
	public String incrementEssenceCount(TFEssence essence, int increment, boolean withGainModifiers) {
		String additional = "";
		if(withGainModifiers && increment>0) {
			if(this.hasStatusEffect(StatusEffect.WEATHER_STORM) || this.hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) {
				increment *= 2;
				additional = "<br/>Essence gain was [style.boldExcellent(doubled)] due to the ongoing arcane storm!";
			}
		}
		
		if(getEssenceCount(essence)+increment < 0) {
			getEssenceMap().put(essence, 0);
		} else {
			getEssenceMap().put(essence, getEssenceCount(essence)+increment);
		}
		
		if(increment>0) {
			return "You gained "+UtilText.formatAsEssences(increment, "b", false)+" <b style='color:"+essence.getColour().toWebHexString()+";'>"+essence.getName()+" essence"+(increment>1?"s":"")+"</b>!"
						+ additional;
		} else {
			return "You lost "+UtilText.formatAsEssences(-increment, "b", false)+" <b style='color:"+essence.getColour().toWebHexString()+";'>"+essence.getName()+" essence"+(increment<-1?"s":"")+"</b>!";
		}
	}
	
	public boolean hasEssences() {
		for(Integer i : getEssenceMap().values()) {
			if(i>0) {
				return true;
			}
		}
		return false;
	}
	public boolean hasNonArcaneEssences() {
		for(Entry<TFEssence, Integer> entry : getEssenceMap().entrySet()) {
			if(entry.getKey()!=TFEssence.ARCANE) {
				if(entry.getValue()>0) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public void clearNonEquippedInventory(){
		inventory.clearNonEquippedInventory();
	}

	public boolean isInventoryFull() {
		return inventory.isInventoryFull();
	}

	public int getMaximumInventorySpace() {
		return inventory.getMaximumInventorySpace();
	}

	public void setMaximumInventorySpace(int maxInventorySpace) {
		inventory.setMaximumInventorySpace(maxInventorySpace);
	}

	public int getInventorySlotsTaken() {
		return inventory.getInventorySlotsTaken();
	}
	
	public Map<AbstractWeapon, Integer> getMapOfDuplicateWeapons() {
		return inventory.getMapOfDuplicateWeapons();
	}
	public int getUniqueWeaponCount() {
		return inventory.getUniqueWeaponCount();
	}
	public int getWeaponCount() {
		return inventory.getWeaponCount();
	}
	public int getWeaponCount(AbstractWeapon weapon) {
		return inventory.getWeaponCount(weapon);
	}

	public Map<AbstractClothing, Integer> getMapOfDuplicateClothing() {
		return inventory.getMapOfDuplicateClothing();
	}
	public int getUniqueClothingCount() {
		return inventory.getUniqueClothingCount();
	}
	public int getClothingCount() {
		return inventory.getClothingCount();
	}
	public int getClothingCount(AbstractClothing clothing) {
		return inventory.getClothingCount(clothing);
	}
	
	public Map<AbstractItem, Integer> getMapOfDuplicateItems() {
		return inventory.getMapOfDuplicateItems();
	}
	public int getUniqueItemCount() {
		return inventory.getUniqueItemCount();
	}
	public int getItemCount() {
		return inventory.getItemCount();
	}
	public int getItemCount(AbstractItem item) {
		return inventory.getItemCount(item);
	}
	
	
	// -------------------- Items -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public List<AbstractItem> getAllItemsInInventory() {
		return inventory.getAllItemsInInventory();
	}
	
	public AbstractItem getItem(int index) {
		return inventory.getItem(index);
	}

	public String addItem(AbstractItem item, boolean removingFromFloor) {
		return addItem(item, removingFromFloor, false);
	}
	/**
	 * Add an item to this character's inventory. If the inventory is full, the item is dropped in the character's current location.
	 * 
	 * @param removingFromFloor true if this item should be removed from the floor of the area the character is currently in on a successful pick up.
	 * @return Description of what happened.
	 */
	public String addItem(AbstractItem item, boolean removingFromFloor, boolean appendTextToEventLog) {
		if (removingFromFloor) {
			if (inventory.addItem(item)) {
				updateInventoryListeners();
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().removeItem(item);
				if(appendTextToEventLog) {
					Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Item Added", item.getName()), false);
				}
				return "<p style='text-align:center;'>"+ addedItemToInventoryText(item)+"</p>";
			} else {
				return inventoryFullText() + droppedItemText(item);
			}
			
		} else {
			if (inventory.addItem(item)) {
				updateInventoryListeners();
				if(appendTextToEventLog) {
					Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Item Added", item.getName()), false);
				}
				return "<p style='text-align:center;'>"+ addedItemToInventoryText(item)+"</p>";
			} else {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().addItem(item);
				return inventoryFullText() + droppedItemText(item);
			}
		}
	}
	
	public void removeItem(AbstractItem item) {
		inventory.removeItem(item);
	}
	
	public boolean hasItem(AbstractItem item) {
		return inventory.hasItem(item);
	}
	
	/**
	 * @return true if one of the items in this inventory has the same type as the Item provided.
	 */
	public boolean hasItemType(AbstractItemType item) {
		return inventory.hasItemType(item);
	}
	
	/**
	 * Drops the item in the cell this character is currently in.
	 * @return Description of what happened.
	 */
	public String dropItem(AbstractItem item) {
		if (inventory.dropItem(item, location)) {
			return droppedItemText(item);
			
		} else {
			return "";
		}
	}
	
	
	public String useItem(AbstractItem item, GameCharacter target, boolean removingFromFloor) {
		return useItem(item, target, removingFromFloor, false);
	}
	
	/**
	 * Uses the specified item on the specified target. If the item returns true on isConsumedOnUse() call, this item is removed from the character's inventory.
	 * 
	 * @param removingFromFloor true if an instance of this item should be consumed from the floor of the area the character is currently in. If item isConsumedOnUse() returns false, an item will not be removed from the floor.
	 * @return Description of what happened.
	 */

	public String useItem(AbstractItem item, GameCharacter target, boolean removingFromFloor, boolean onlyReturnEffects) {
		if(ItemType.getAllItems().contains(item.getItemType()) && isPlayer()) {
			if(Main.getProperties().addItemDiscovered(item.getItemType())) {
				Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(item.getItemType().getName(false), item.getRarity().getColour()), true);
			}
		}
		
		if (item.getItemType().isConsumedOnUse()) {
			if(removingFromFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().removeItem(item);
			} else {
				removeItem(item);
			}
		}
		
		if(onlyReturnEffects) {
			return item.applyEffect(this, target);
		} else {
			return item.getUseDescription(this, target) + item.applyEffect(this, target);
		}
		
	}

	
	// -------------------- Weapons -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public List<AbstractWeapon> getAllWeaponsInInventory() {
		return inventory.getAllWeaponsInInventory();
	}
	
	public AbstractWeapon getWeapon(int index) {
		return inventory.getWeapon(index);
	}
	
	public String addWeapon(AbstractWeapon weapon, boolean removingFromFloor) {
		if (inventory.addWeapon(weapon)) {
			if (removingFromFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().removeWeapon(weapon);
			}
			return "<p style='text-align:center;'>" + addedItemToInventoryText(weapon)+"</p>";
			
		} else {
			if(!removingFromFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().addWeapon(weapon);
			}
			return inventoryFullText() + "<br/>" + droppedItemText(weapon);
		}
	}
	
	public boolean removeWeapon(AbstractWeapon weapon) {
		return inventory.removeWeapon(weapon);
	}
	
	public boolean hasWeapon(AbstractWeapon weapon) {
		return inventory.hasWeapon(weapon);
	}
	
	public boolean hasWeaponEquipped(AbstractWeapon weapon) {
		return (getMainWeapon()!=null && getMainWeapon().equals(weapon))
				|| (getOffhandWeapon()!=null && getOffhandWeapon().equals(weapon));
	}
	
	public String dropWeapon(AbstractWeapon weapon) {
		if (inventory.dropWeapon(weapon, location)) {
			return droppedItemText(weapon);
			
		} else
			return "";
	}
	
	public AbstractWeapon getMainWeapon() {
		return inventory.getMainWeapon();
	}

	
	/** @return Description of equipping this weapon. */
	public String equipMainWeaponFromInventory(AbstractWeapon weapon, GameCharacter fromCharactersInventory) {
		fromCharactersInventory.removeWeapon(weapon);
		return equipMainWeapon(weapon);
	}

	/** @return Description of equipping this weapon. */
	public String equipMainWeaponFromFloor(AbstractWeapon weapon) {
		Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().removeWeapon(weapon);
		return equipMainWeapon(weapon);
	}

	/** @return Description of equipping this weapon. */
	public String equipMainWeaponFromNowhere(AbstractWeapon weapon) {
		return equipMainWeapon(weapon);
	}
	
	private String equipMainWeapon(AbstractWeapon weapon) {
		if (weapon == null) {
			throw new NullPointerException("null weapon was passed.");
		}
		
		StringBuilder s = new StringBuilder();
		
		if (getMainWeapon() != null) {
			s.append(unequipMainWeapon(false));
		}
		if (weapon.getWeaponType().isTwoHanded() && getOffhandWeapon() != null) {
			s.append(unequipOffhandWeapon(false));
		}

		s.append(weapon.onEquip(this));
		// Apply its attribute bonuses:
		if (weapon.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : weapon.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
		}
		
		inventory.equipMainWeapon(weapon);
		updateInventoryListeners();
		
		return s.toString();
	}
	
	public String unequipMainWeapon(boolean dropToFloor) {
		if (getMainWeapon() != null) {
			// If weapon is unequipped, revert its attribute bonuses:
			if (getMainWeapon().getAttributeModifiers() != null) {
				for (Entry<Attribute, Integer> e : getMainWeapon().getAttributeModifiers().entrySet()) {
					incrementBonusAttribute(e.getKey(), -e.getValue());
				}
			}
			
			boolean mustDropToFloor = isInventoryFull() && !hasWeapon(getMainWeapon());
			String s;
			if (mustDropToFloor || dropToFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().addWeapon(getMainWeapon());
				s = getMainWeapon().getWeaponType().unequipText(this) + (mustDropToFloor && !dropToFloor ? inventoryFullText() : "") + droppedItemText(getMainWeapon());
			} else {
				addWeapon(getMainWeapon(), false);
				s = getMainWeapon().getWeaponType().unequipText(this) + "<p style='text-align:center;'>" + addedItemToInventoryText(getMainWeapon())+"</p>";
			}
			inventory.unequipMainWeapon();
			updateInventoryListeners();
			
			return s;
			
		} else {
			return "";
		}
	}
	
	public void unequipMainWeaponIntoVoid() {
		if (getMainWeapon() != null) {
			// If weapon is unequipped, revert its attribute bonuses:
			if (getMainWeapon().getAttributeModifiers() != null) {
				for (Entry<Attribute, Integer> e : getMainWeapon().getAttributeModifiers().entrySet()) {
					incrementBonusAttribute(e.getKey(), -e.getValue());
				}
			}
			
			inventory.unequipMainWeapon();
			updateInventoryListeners();
		}
	}
	
	public AbstractWeapon getOffhandWeapon() {
		return inventory.getOffhandWeapon();
	}
	
	
	/** @return Description of equipping this weapon. */
	public String equipOffhandWeaponFromInventory(AbstractWeapon weapon, GameCharacter fromCharactersInventory) {
		String s = equipOffhandWeapon(weapon);
		fromCharactersInventory.removeWeapon(weapon);
		return s;
	}

	/** @return Description of equipping this weapon. */
	public String equipOffhandWeaponFromFloor(AbstractWeapon weapon) {
		String s = equipOffhandWeapon(weapon);
		Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().removeWeapon(weapon);
		return s;
	}

	/** @return Description of equipping this weapon. */
	public String equipOffhandWeaponFromNowhere(AbstractWeapon weapon) {
		return equipOffhandWeapon(weapon);
	}
	
	public String equipOffhandWeapon(AbstractWeapon weapon) {
		if (weapon == null) {
			throw new NullPointerException("null weapon was passed.");
		}
		
		StringBuilder s = new StringBuilder();
		
		if (getOffhandWeapon() != null) {
			s.append(unequipOffhandWeapon(false));
		}
		if (getMainWeapon() != null && getMainWeapon().getWeaponType().isTwoHanded()) {
			s.append(unequipMainWeapon(false));
		}
		
		s.append(weapon.onEquip(this));
		// Apply its attribute bonuses:
		if (weapon.getAttributeModifiers() != null) {
			for (Entry<Attribute, Integer> e : weapon.getAttributeModifiers().entrySet()) {
				incrementBonusAttribute(e.getKey(), e.getValue());
			}
		}
		
		inventory.equipOffhandWeapon(weapon);
		updateInventoryListeners();
		
		return s.toString();
	}
	
	public String unequipOffhandWeapon(boolean dropToFloor) {
		if (getOffhandWeapon() != null) {
			// If weapon is unequipped, revert it's attribute bonuses:
			if (getOffhandWeapon().getAttributeModifiers() != null)
				for (Entry<Attribute, Integer> e : getOffhandWeapon().getAttributeModifiers().entrySet())
					incrementBonusAttribute(e.getKey(), -e.getValue());
			
			boolean mustDropToFloor = isInventoryFull() && !hasWeapon(getOffhandWeapon());
			String s;
			if (mustDropToFloor || dropToFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().addWeapon(getOffhandWeapon());
				s = getOffhandWeapon().getWeaponType().unequipText(this) + (mustDropToFloor && !dropToFloor ? inventoryFullText() : "") + droppedItemText(getOffhandWeapon());
			} else {
				addWeapon(getOffhandWeapon(), false);
				s = getOffhandWeapon().getWeaponType().unequipText(this) + "<p style='text-align:center;'>" + addedItemToInventoryText(getOffhandWeapon())+"</p>";
			}

			inventory.unequipOffhandWeapon();
			updateInventoryListeners();
			
			return s;
		} else
			return "";
	}
	
	public void unequipOffhandWeaponIntoVoid() {
		if (getOffhandWeapon() != null) {
			// If weapon is unequipped, revert its attribute bonuses:
			if (getOffhandWeapon().getAttributeModifiers() != null) {
				for (Entry<Attribute, Integer> e : getOffhandWeapon().getAttributeModifiers().entrySet()) {
					incrementBonusAttribute(e.getKey(), -e.getValue());
				}
			}
			
			inventory.unequipOffhandWeapon();
			updateInventoryListeners();
		}
	}
	
	
	// -------------------- Clothing -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public List<AbstractClothing> getAllClothingInInventory() {
		return inventory.getAllClothingInInventory();
	}
	
	public AbstractClothing getClothing(int index) {
		return inventory.getClothing(index);
	}
	
	public void cleanAllClothing() {
		inventory.cleanAllClothing();
	}

	public String addClothing(AbstractClothing clothing, boolean removingFromFloor) {
		if (inventory.addClothing(clothing)) {
			updateInventoryListeners();
			if (removingFromFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().removeClothing(clothing);
			}
			return "<p style='text-align:center;'>" + addedItemToInventoryText(clothing)+"</p>";
		} else {
			if (!removingFromFloor) {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().addClothing(clothing);
			}
			return inventoryFullText() + droppedItemText(clothing);
		}
	}


	public boolean removeClothing(AbstractClothing clothing) {
		return inventory.removeClothing(clothing);
	}


	public String dropClothing(AbstractClothing clothing) {
		if (inventory.dropClothing(clothing, location)) {
			updateInventoryListeners();

			return droppedItemText(clothing);
		} else
			return "";
	}
	
	public boolean hasClothing(AbstractClothing clothing) {
		return inventory.hasClothing(clothing);
	}


	public List<AbstractClothing> getClothingCurrentlyEquipped() {
		return inventory.getClothingCurrentlyEquipped();
	}
	
	public Map<InventorySlot, List<AbstractClothing>> getInventorySlotsConcealed() {
		
		Map<InventorySlot, List<AbstractClothing>> concealedMap = new HashMap<>(inventory.getInventorySlotsConcealed());
		
		if(Main.game.isConcealedSlotsReveal()) {
			return new HashMap<>();
		}
		
		if(Main.game.isInSex()) {
			for(InventorySlot slot : Sex.getSexManager().getSlotsConcealed(this)) {
				concealedMap.put(slot, new ArrayList<>());
			}
		}
		
		return concealedMap;
	}


	public AbstractClothing getClothingInSlot(InventorySlot invSlot) {
		return inventory.getClothingInSlot(invSlot);
	}


	public int getClothingSetCount(ClothingSet clothingSet) {
		return inventory.getClothingSetCount(clothingSet);
	}
	
	public boolean isSlotIncompatible(InventorySlot slot) {
		return inventory.isSlotIncompatible(slot);
	}
	
	public void deleteAllEquippedClothing() {
		List<AbstractClothing> equippedClothing = new ArrayList<>();
		for(AbstractClothing clothing : getClothingCurrentlyEquipped()) {
			equippedClothing.add(clothing);
			clothing.setSealed(false);
		}
		for(AbstractClothing clothing : equippedClothing) {
			unequipClothingIntoVoid(clothing, true, this);
		}
		equippedClothing.clear();
	}

	private void applyEquipClothingEffects(AbstractClothing newClothing) {
		incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, newClothing.getClothingType().getPhysicalResistance());
		for (Entry<Attribute, Integer> e : newClothing.getAttributeModifiers().entrySet()) {
			incrementBonusAttribute(e.getKey(), e.getValue());
		}
		
		for(ItemEffect ie : newClothing.getEffects()) {
			if(ie.getSecondaryModifier()!=null && ie.getSecondaryModifier().getFetish()!=null) {
				Fetish associatedFetish = ie.getSecondaryModifier().getFetish();
				switch(ie.getPotency()) {
					case MINOR_BOOST:
						clothingFetishDesireModifiersMap.putIfAbsent(ie.getSecondaryModifier().getFetish(), 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 1);
						break;
					case BOOST:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 2);
						break;
					case MAJOR_BOOST:
						fetishesFromClothing.add(associatedFetish);
						applyFetishGainEffects(associatedFetish);
						break;
					case MINOR_DRAIN:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 1);
						break;
					case DRAIN:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 2);
						break;
					case MAJOR_DRAIN:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 999);
						break;
				}
			}
		}
	}
	
	private void applyUnequipClothingEffects(AbstractClothing clothing) {
		if(Main.game.isInSex() && Sex.getAllParticipants().contains(this)) {
			if(clothing.getItemTags().contains(ItemTag.DILDO_AVERAGE)
					|| clothing.getItemTags().contains(ItemTag.DILDO_ENORMOUS)
					|| clothing.getItemTags().contains(ItemTag.DILDO_GIGANTIC)
					|| clothing.getItemTags().contains(ItemTag.DILDO_HUGE)
					|| clothing.getItemTags().contains(ItemTag.DILDO_LARGE)
					|| clothing.getItemTags().contains(ItemTag.DILDO_STALLION)
					|| clothing.getItemTags().contains(ItemTag.DILDO_TINY)) {
				for(GameCharacter character : Sex.getCharacterContactingSexArea(this, SexAreaPenetration.PENIS)) {
					Sex.stopAllOngoingActions(this, SexAreaPenetration.PENIS, character, true);
				}
			}
		}
		
		incrementBonusAttribute(Attribute.RESISTANCE_PHYSICAL, -clothing.getClothingType().getPhysicalResistance());
		for (Entry<Attribute, Integer> e : clothing.getAttributeModifiers().entrySet()) {
			incrementBonusAttribute(e.getKey(), -e.getValue());
		}
		
		for(ItemEffect ie : clothing.getEffects()) {
			if(ie.getSecondaryModifier()!=null && ie.getSecondaryModifier().getFetish()!=null) {
				Fetish associatedFetish = ie.getSecondaryModifier().getFetish();
				switch(ie.getPotency()) {
					case MINOR_BOOST:
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 1);
						break;
					case BOOST:
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 2);
						break;
					case MAJOR_BOOST:
						fetishesFromClothing.remove(associatedFetish);
						applyFetishLossEffects(associatedFetish);
						break;
					case MINOR_DRAIN:
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 1);
						break;
					case DRAIN:
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 2);
						break;
					case MAJOR_DRAIN:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 999);
						break;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param newClothing The clothing to equip to this character.
	 * @param automaticClothingManagement Whether clothing should automatically be shifted/removed in order to equip this clothing.
	 * @param characterClothingEquipper The character who is equipping the clothing to this character.
	 * @param fromCharactersInventory The character who has this clothing in their inventory.
	 * @return Equip description
	 */
	public String equipClothingFromInventory(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper, GameCharacter fromCharactersInventory) {
		boolean wasAbleToEquip = inventory.isAbleToEquip(newClothing, true, automaticClothingManagement, this, characterClothingEquipper);

		// If this item was able to be equipped, and it was equipped, apply its attribute bonuses:
		if (wasAbleToEquip) {
			applyEquipClothingEffects(newClothing);
			
			fromCharactersInventory.removeClothing(newClothing);

			newClothing.setEnchantmentKnown(true);

			updateInventoryListeners();

			if (isPlayer() && Main.game.isInNewWorld()) {
				if (Main.getProperties().addClothingDiscovered(newClothing.getClothingType())) {
					Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(newClothing.getClothingType().getName(), newClothing.getRarity().getColour()), true);
				}
			}
		}

		return inventory.getEquipDescription();
	}

	/**
	 * Overrides all clothing equip checks, making sure that this piece of clothing is equipped, no matter what. Should only be used in exceptional circumstances.
	 * @param newClothing
	 */
	public void equipClothingOverride(AbstractClothing newClothing, boolean removeFromInventoryOrFloor) {
		inventory.getClothingCurrentlyEquipped().add(newClothing);
		
		applyEquipClothingEffects(newClothing);
		
		this.removeClothing(newClothing);
		Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().removeClothing(newClothing);

		updateInventoryListeners();
	}

	public String equipClothingFromNowhere(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper) {
		boolean wasAbleToEquip = inventory.isAbleToEquip(newClothing, true, automaticClothingManagement, this, characterClothingEquipper);

		// If this item was able to be equipped, and it was equipped, apply it's
		// attribute bonuses:
		if (wasAbleToEquip) {
			applyEquipClothingEffects(newClothing);
			
			newClothing.setEnchantmentKnown(true);

			updateInventoryListeners();

			if (isPlayer() && Main.game.isInNewWorld()) {
				if (Main.getProperties().addClothingDiscovered(newClothing.getClothingType())) {
					Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(newClothing.getClothingType().getName(), newClothing.getRarity().getColour()), true);
				}
			}
		}

		return inventory.getEquipDescription();
	}


	public String equipClothingFromGround(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper) {
		boolean wasAbleToEquip = inventory.isAbleToEquip(newClothing, true, automaticClothingManagement, this, characterClothingEquipper);

		// If this item was able to be equipped, and it was equipped, apply it's
		// attribute bonuses:
		if (wasAbleToEquip) {
			applyEquipClothingEffects(newClothing);

			Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().removeClothing(newClothing);

			newClothing.setEnchantmentKnown(true);

			updateInventoryListeners();

			if (isPlayer() && Main.game.isInNewWorld()) {
				if (Main.getProperties().addClothingDiscovered(newClothing.getClothingType())) {
					Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(newClothing.getClothingType().getName(), newClothing.getRarity().getColour()), true);
				}
			}
		}

		return inventory.getEquipDescription();
	}


	public boolean isAbleToEquip(AbstractClothing newClothing, boolean automaticClothingManagement, GameCharacter characterClothingEquipper) {
		return inventory.isAbleToEquip(newClothing, false, automaticClothingManagement, this, characterClothingEquipper);
	}

	public String getEquipDescription() {
		return inventory.getEquipDescription();
	}

	public String unequipClothingIntoUnequippersInventory(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingUnequipper) {
		boolean unknownPenis = !this.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.PENIS);
		boolean unknownBreasts = !this.isAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.BREASTS);
		boolean unknownVagina = !this.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.VAGINA);
		boolean unknownAss = !this.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.ANUS);
		
		boolean wasAbleToUnequip = inventory.isAbleToUnequip(clothing, true, automaticClothingManagement, this, characterClothingUnequipper);

		// If this item was able to be unequipped, and it was unequipped, revert
		// it's attribute bonuses:
		if (!wasAbleToUnequip) {
			return "<p style='text-align:center;'>"
					+inventory.getEquipDescription()
					+ "</p>";
			
		} else {
			applyUnequipClothingEffects(clothing);
			
			boolean fitsIntoInventory = !characterClothingUnequipper.isInventoryFull() || characterClothingUnequipper.hasClothing(clothing);

			// Place the clothing into inventory:
			if (fitsIntoInventory) {
				characterClothingUnequipper.addClothing(clothing, false);
			} else {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().addClothing(clothing);
			}
			
			updateInventoryListeners();
			
			if(!this.isPlayer() && Main.game.getCharactersPresent().contains(this)) {
				for(CoverableArea ca : CoverableArea.values()) {
					if(this.isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
						this.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
					}
				}
			}
			
			return "<p style='text-align:center;'>"
				+inventory.getEquipDescription()
				+"<br/>"
				+ (!fitsIntoInventory
						? droppedItemText(clothing)
						: addedItemToInventoryText(clothing))
				+ "</p>"
				+(this.isPlayer() || Main.game.isInSex()
						?""
						:((unknownBreasts && this.isCoverableAreaExposed(CoverableArea.BREASTS)
									?"<p>"
									+ UtilText.parse(this, this.getBreastDescription())
								+ "</p>"
								+ characterClothingUnequipper.getBreastsRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:"")
						+ (unknownAss && this.isCoverableAreaExposed(CoverableArea.ANUS)
								?"<p>"
									+ UtilText.parse(this, this.getAssDescription())
								+ "</p>"
								+ characterClothingUnequipper.getAssRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:"")
						+ (unknownPenis && this.isCoverableAreaExposed(CoverableArea.PENIS) && this.hasPenis()
								?"<p>"
									+ UtilText.parse(this, this.getPenisDescription())
								+ "</p>"
								+ characterClothingUnequipper.getPenisRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:"")
						+ (unknownVagina && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.hasVagina()
								?"<p>"
									+ UtilText.parse(this, this.getVaginaDescription())
								+ "</p>"
								+ characterClothingUnequipper.getVaginaRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:"")
						+ ((unknownPenis || unknownVagina) && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.isCoverableAreaExposed(CoverableArea.PENIS) && !this.hasVagina()&& !this.hasPenis()
								?"<p>"
								+ UtilText.parse(this, this.getMoundDescription())
							+ "</p>"
							+ characterClothingUnequipper.getMoundRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
							:"")));
			
		}
	}

	public String unequipClothingIntoInventory(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingUnequipper) {
		boolean unknownPenis = !this.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.PENIS);
		boolean unknownBreasts = !this.isAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.BREASTS);
		boolean unknownVagina = !this.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.VAGINA);
		boolean unknownAss = !this.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.ANUS);
		
		boolean wasAbleToUnequip = inventory.isAbleToUnequip(clothing, true, automaticClothingManagement, this, characterClothingUnequipper);

		// If this item was able to be unequipped, and it was unequipped, revert
		// it's attribute bonuses:
		if (!wasAbleToUnequip) {
			return "<p style='text-align:center;'>"
					+inventory.getEquipDescription()
					+ "</p>";
			
		} else {
			applyUnequipClothingEffects(clothing);
			
			boolean fitsIntoInventory = !isInventoryFull() || hasClothing(clothing);

			// Place the clothing into inventory:
			if (fitsIntoInventory) {
				addClothing(clothing, false);
			} else {
				Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().addClothing(clothing);
			}
			
			updateInventoryListeners();

			if(!this.isPlayer() && (Main.game.getCharactersPresent().contains(this) || (this.isSlave() && this.getOwner().isPlayer()))) {
				for(CoverableArea ca : CoverableArea.values()) {
					if(this.isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
						this.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
					}
				}
			}
			
			return "<p style='text-align:center;'>"
				+inventory.getEquipDescription()
				+"<br/>"
				+ (!fitsIntoInventory
						? droppedItemText(clothing)
						: addedItemToInventoryText(clothing))
				+ "</p>"
				+(this.isPlayer() || Main.game.isInSex()
						?""
						:((unknownBreasts && this.isCoverableAreaExposed(CoverableArea.BREASTS)
									?"<p>"
									+ UtilText.parse(this, this.getBreastDescription())
								+ "</p>"
								+ characterClothingUnequipper.getBreastsRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:"")
						+ (unknownAss && this.isCoverableAreaExposed(CoverableArea.ANUS)
								?"<p>"
									+ UtilText.parse(this, this.getAssDescription())
								+ "</p>"
								+ characterClothingUnequipper.getAssRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:"")
						+ (unknownPenis && this.isCoverableAreaExposed(CoverableArea.PENIS) && this.hasPenis()
								?"<p>"
									+ UtilText.parse(this, this.getPenisDescription())
								+ "</p>"
								+ characterClothingUnequipper.getPenisRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:"")
						+ (unknownVagina && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.hasVagina()
								?"<p>"
									+ UtilText.parse(this, this.getVaginaDescription())
								+ "</p>"
								+ characterClothingUnequipper.getVaginaRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:"")
						+ ((unknownPenis || unknownVagina) && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.isCoverableAreaExposed(CoverableArea.PENIS) && !this.hasVagina()&& !this.hasPenis()
								?"<p>"
								+ UtilText.parse(this, this.getMoundDescription())
							+ "</p>"
							+ characterClothingUnequipper.getMoundRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
							:"")));
			
		}
	}


	public String unequipClothingOntoFloor(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingUnequipper) { // TODO it's saying "added to inventory"
		boolean unknownPenis = !this.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.PENIS);
		boolean unknownBreasts = !this.isAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.BREASTS);
		boolean unknownVagina = !this.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.VAGINA);
		boolean unknownAss = !this.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.ANUS);
		
		boolean wasAbleToUnequip = inventory.isAbleToUnequip(clothing, true, automaticClothingManagement, this, characterClothingUnequipper);

		// If this item was able to be unequipped, and it was unequipped, revert
		// it's attribute bonuses:
		if (wasAbleToUnequip) {
			applyUnequipClothingEffects(clothing);

			// Place the clothing on the floor:
			Main.game.getWorlds().get(getWorldLocation()).getCell(getLocation()).getInventory().addClothing(clothing);

			updateInventoryListeners();
			
		} else {
			return "<p style='text-align:center;'>"
					+inventory.getEquipDescription()
					+ "</p>";
		}

		if(this.isPlayer()) {
			return "<p style='text-align:center;'>"
						+ inventory.getEquipDescription()
					+"</p>"
					+ droppedItemText(clothing);
		}

		if(!this.isPlayer() && (Main.game.getCharactersPresent().contains(this) || (this.isSlave() && this.getOwner().isPlayer()))) {
			for(CoverableArea ca : CoverableArea.values()) {
				if(this.isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
					this.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
				}
			}
		}
		
		return "<p style='text-align:center;'>"
					+ inventory.getEquipDescription()
				+"</p>"
				+ droppedItemText(clothing)
				+(this.isPlayer() || Main.game.isInSex()
						?""
						:(unknownBreasts && this.isCoverableAreaExposed(CoverableArea.BREASTS)
									?"<p>"
										+ UtilText.parse(this, this.getBreastDescription())
									+ "</p>"
									+ characterClothingUnequipper.getBreastsRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
									:"")
							+ (unknownAss && this.isCoverableAreaExposed(CoverableArea.ANUS)
									?"<p>"
										+ UtilText.parse(this, this.getAssDescription())
									+ "</p>"
									+ characterClothingUnequipper.getAssRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
									:"")
							+ (unknownPenis && this.isCoverableAreaExposed(CoverableArea.PENIS) && this.hasPenis()
									?"<p>"
										+ UtilText.parse(this, this.getPenisDescription())
									+ "</p>"
									+ characterClothingUnequipper.getPenisRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
									:"")
							+ (unknownVagina && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.hasVagina()
									?"<p>"
										+ UtilText.parse(this, this.getVaginaDescription())
									+ "</p>"
									+ characterClothingUnequipper.getVaginaRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
									:"")
							+ ((unknownPenis || unknownVagina) && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.isCoverableAreaExposed(CoverableArea.PENIS) && !this.hasVagina()&& !this.hasPenis()
									?"<p>"
									+ UtilText.parse(this, this.getMoundDescription())
								+ "</p>"
								+ characterClothingUnequipper.getMoundRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:""));
	}
	
	public void forceUnequipClothingIntoVoid(GameCharacter characterRemovingClothing, AbstractClothing clothing) {
		applyUnequipClothingEffects(clothing);
		inventory.forceUnequipClothingIntoVoid(this, characterRemovingClothing, clothing);
	}
	
	public String unequipClothingIntoVoid(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingUnequipper) { // TODO it's saying "added to inventory"
		boolean unknownPenis = !this.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.PENIS);
		boolean unknownBreasts = !this.isAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.BREASTS);
		boolean unknownVagina = !this.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.VAGINA);
		boolean unknownAss = !this.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.ANUS);

		boolean wasAbleToUnequip = inventory.isAbleToUnequip(clothing, true, automaticClothingManagement, this, characterClothingUnequipper);

		// If this item was able to be unequipped, and it was unequipped, revert
		// it's attribute bonuses:
		if (wasAbleToUnequip) {
			applyUnequipClothingEffects(clothing);
			
			updateInventoryListeners();
		}
		
		if(this.isPlayer()) {
			return "<p style='text-align:center;'>"
					+ inventory.getEquipDescription()
				+"</p>";
		}

		if(!this.isPlayer() && (Main.game.getCharactersPresent().contains(this) || (this.isSlave() && this.getOwner()!=null && this.getOwner().isPlayer()))) {
			for(CoverableArea ca : CoverableArea.values()) {
				if(this.isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
					this.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
				}
			}
		}
		
		return "<p style='text-align:center;'>"
					+ inventory.getEquipDescription()
				+"</p>"
				+ (this.isPlayer() || Main.game.isInSex()
						?""
						:(unknownBreasts && this.isCoverableAreaExposed(CoverableArea.BREASTS)
									?"<p>"
										+ UtilText.parse(this, this.getBreastDescription())
									+ "</p>"
									+ characterClothingUnequipper.getBreastsRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
									:"")
							+ (unknownAss && this.isCoverableAreaExposed(CoverableArea.ANUS)
									?"<p>"
										+ UtilText.parse(this, this.getAssDescription())
									+ "</p>"
									+ characterClothingUnequipper.getAssRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
									:"")
							+ (unknownPenis && this.isCoverableAreaExposed(CoverableArea.PENIS) && this.hasPenis()
									?"<p>"
										+ UtilText.parse(this, this.getPenisDescription())
									+ "</p>"
									+ characterClothingUnequipper.getPenisRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
									:"")
							+ (unknownVagina && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.hasVagina()
									?"<p>"
										+ UtilText.parse(this, this.getVaginaDescription())
									+ "</p>"
									+ characterClothingUnequipper.getVaginaRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
									:"")
							+ ((unknownPenis || unknownVagina) && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.isCoverableAreaExposed(CoverableArea.PENIS) && !this.hasVagina()&& !this.hasPenis()
									?"<p>"
									+ UtilText.parse(this, this.getMoundDescription())
								+ "</p>"
								+ characterClothingUnequipper.getMoundRevealDescription(this, Util.newArrayListOfValues(characterClothingUnequipper))
								:""));
	}


	public boolean isAbleToUnequip(AbstractClothing clothing, boolean automaticClothingManagement, GameCharacter characterClothingRemover) {
		return inventory.isAbleToUnequip(clothing, false, automaticClothingManagement, this, characterClothingRemover);
	}


	public String getUnequipDescription() {
		return inventory.getEquipDescription();
	}


	/**
	 * <b>Warning:</b> Passing in a DisplacementType that isn't present for the passed in clothing *might* throw a null pointer exception...
	 * 
	 * @param clothing The clothing that is to be checked for displacement.
	 * @param dt The type of displacement to perform upon this clothing.
	 * @param displaceIfAble If true, the clothing will be displaced (if it's able to be displaced). If false, this method can just be used as a boolean check to see if it's able to be displaced without actually displacing it.
	 * @param automaticClothingManagement If true, other clothing will be moved aside to get access to the clothing that needs to be displaced. If false, then if other clothing is blocking this clothing from being displaced, this method will return false.
	 * @param characterClothingDisplacer The character who is performing the displacement action.
	 * @return True if the clothing can be displaced, false if it can't/
	 */
	public boolean isAbleToBeDisplaced(AbstractClothing clothing, DisplacementType dt, boolean displaceIfAble, boolean automaticClothingManagement, GameCharacter characterClothingDisplacer) {
		boolean unknownPenis = !this.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.PENIS);
		boolean unknownBreasts = !this.isAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.BREASTS);
		boolean unknownVagina = !this.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.VAGINA);
		boolean unknownAss = !this.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer()) && !this.isCoverableAreaExposed(CoverableArea.ANUS);
		
		boolean wasAbleToDisplace = inventory.isAbleToBeDisplaced(clothing, dt, displaceIfAble, automaticClothingManagement, this, characterClothingDisplacer);

		// If this item was able to be displaced, and it was displaced, apply
		// exposed effects:
		if (wasAbleToDisplace && displaceIfAble) {
			updateInventoryListeners();
		}
		
		if(!this.isPlayer() && (Main.game.getCharactersPresent().contains(this) || (this.isSlave() && this.getOwner().isPlayer()))) {
			for(CoverableArea ca : CoverableArea.values()) {
				if(this.isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
					this.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
				}
			}
			
			if(!Main.game.isInSex()) {
				inventory.appendToDisplaceDescription(
					(unknownBreasts && this.isCoverableAreaExposed(CoverableArea.BREASTS)
							?"<p>"
								+ UtilText.parse(this, this.getBreastDescription())
							+ "</p>"
							+ characterClothingDisplacer.getBreastsRevealDescription(this, Util.newArrayListOfValues(characterClothingDisplacer))
							:"")
					+ (unknownAss && this.isCoverableAreaExposed(CoverableArea.ANUS)
							?"<p>"
								+ UtilText.parse(this, this.getAssDescription())
							+ "</p>"
							+ characterClothingDisplacer.getAssRevealDescription(this, Util.newArrayListOfValues(characterClothingDisplacer))
							:"")
					+ (unknownPenis && this.isCoverableAreaExposed(CoverableArea.PENIS) && this.hasPenis()
							?"<p>"
								+ UtilText.parse(this, this.getPenisDescription())
							+ "</p>"
							+ characterClothingDisplacer.getPenisRevealDescription(this, Util.newArrayListOfValues(characterClothingDisplacer))
							:"")
					+ (unknownVagina && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.hasVagina()
							?"<p>"
								+ UtilText.parse(this, this.getVaginaDescription())
							+ "</p>"
							+ characterClothingDisplacer.getVaginaRevealDescription(this, Util.newArrayListOfValues(characterClothingDisplacer))
							:"")
					+ ((unknownPenis || unknownVagina) && this.isCoverableAreaExposed(CoverableArea.VAGINA) && this.isCoverableAreaExposed(CoverableArea.PENIS) && !this.hasVagina()&& !this.hasPenis()
							?"<p>"
							+ UtilText.parse(this, this.getMoundDescription())
						+ "</p>"
						+ characterClothingDisplacer.getMoundRevealDescription(this, Util.newArrayListOfValues(characterClothingDisplacer))
						:""));
			}
		}
		
		return wasAbleToDisplace;
	}


	public String getDisplaceDescription() {
		return inventory.getDisplaceDescription();
	}


	public boolean isAbleToBeReplaced(AbstractClothing clothing, DisplacementType dt, boolean replaceIfAble, boolean automaticClothingManagement, GameCharacter characterClothingDisplacer) {
		boolean wasAbleToReplace = inventory.isAbleToBeReplaced(clothing, dt, replaceIfAble, automaticClothingManagement, this, characterClothingDisplacer);

		// If this item was able to be displaced, and it was displaced, apply
		// exposed effects:
		if (wasAbleToReplace && replaceIfAble) {
			updateInventoryListeners();
		}

		return wasAbleToReplace;
	}

	public void replaceAllClothing() {
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			c.clearDisplacementList();
		}
	}
	

	public String getReplaceDescription() {
		return inventory.getReplaceDescription();
	}
	
	public AbstractClothing getBlockingClothing() {
		return inventory.getBlockingClothing();
	}

	public SimpleEntry<AbstractClothing, DisplacementType> getNextClothingToRemoveForCoverableAreaAccess(CoverableArea coverableArea) {
		return inventory.getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
	}
	
	public void displaceClothingForAccess(CoverableArea coverableArea) {
		if(isAbleToAccessCoverableArea(coverableArea, true)) {
			SimpleEntry<AbstractClothing, DisplacementType> entry = getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
			while(entry != null) {
				this.isAbleToBeDisplaced(entry.getKey(), entry.getValue(), true, true, this);
				entry = getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
			}
		}
	}

	public boolean isAbleToAccessCoverableArea(CoverableArea area, boolean byRemovingClothing) {
		return inventory.isAbleToAccessCoverableArea(area, byRemovingClothing);
	}
	
	public AbstractClothing getClothingBlockingCoverableAreaAccess(CoverableArea area, boolean byRemovingClothing) {
		return inventory.getClothingBlockingCoverableAreaAccess(area, byRemovingClothing);
	}
	
	public boolean isCoverableAreaExposed(CoverableArea area) {
		if(area == CoverableArea.VAGINA
				|| area == CoverableArea.ANUS
				|| area == CoverableArea.NIPPLES) {
			for(AbstractClothing clothing : this.getClothingCurrentlyEquipped()) {
				if(area == CoverableArea.VAGINA && clothing.getItemTags().contains(ItemTag.PLUGS_VAGINA)) {
					return false;
				}
				if(area == CoverableArea.ANUS && clothing.getItemTags().contains(ItemTag.PLUGS_ANUS)) {
					return false;
				}
				if(area == CoverableArea.NIPPLES && clothing.getItemTags().contains(ItemTag.PLUGS_NIPPLES)) {
					return false;
				}
			}
		}
		return inventory.isCoverableAreaExposed(area);
	}
	
	public boolean isSexAreaExposed(SexAreaInterface sArea) {
		if(sArea!=null) {
			if(sArea.isPenetration()) {
				return isPenetrationTypeExposed((SexAreaPenetration) sArea);
			}
			if(sArea.isOrifice()) {
				return isOrificeTypeExposed((SexAreaOrifice) sArea);
			}
		}
		return false;
	}
	
	public boolean isPenetrationTypeExposed(SexAreaPenetration pt) {
		switch(pt) {
			case FINGER:
				return isCoverableAreaExposed(CoverableArea.HANDS);
			case PENIS:
				return isCoverableAreaExposed(CoverableArea.PENIS);
			case TAIL:
				return true;
			case TENTACLE:
				return true;
			case TONGUE:
				return isCoverableAreaExposed(CoverableArea.MOUTH);
			case CLIT:
				return isCoverableAreaExposed(CoverableArea.VAGINA);
			case FOOT:
				return isCoverableAreaExposed(CoverableArea.FEET);
		}
		return false;
	}
	
	public boolean isOrificeTypeExposed(SexAreaOrifice ot) {
		switch(ot) {
			case ANUS:
				return isCoverableAreaExposed(CoverableArea.ANUS);
			case ASS:
				return isCoverableAreaExposed(CoverableArea.ASS);
			case MOUTH:
				return isCoverableAreaExposed(CoverableArea.MOUTH);
			case NIPPLE:
				return isCoverableAreaExposed(CoverableArea.NIPPLES);
			case URETHRA_PENIS:
				return isCoverableAreaExposed(CoverableArea.PENIS);
			case URETHRA_VAGINA:
				return isCoverableAreaExposed(CoverableArea.VAGINA);
			case VAGINA:
				return isCoverableAreaExposed(CoverableArea.VAGINA);
			case THIGHS:
				return isCoverableAreaExposed(CoverableArea.THIGHS);
			case BREAST:
				return isCoverableAreaExposed(CoverableArea.BREASTS);
		}
		return false;
	}

	public boolean isOrificePlugged(SexAreaOrifice ot) {
		HashMap<SexAreaOrifice, ItemTag> plugMap = new HashMap<>();
		plugMap.put(SexAreaOrifice.ANUS, ItemTag.PLUGS_ANUS);
		plugMap.put(SexAreaOrifice.VAGINA, ItemTag.PLUGS_VAGINA);
		plugMap.put(SexAreaOrifice.NIPPLE, ItemTag.PLUGS_NIPPLES);
		ItemTag lookingFor = plugMap.get(ot);
		
		return lookingFor != null 
				&& getClothingCurrentlyEquipped().stream().anyMatch(c -> c.getItemTags().contains(lookingFor));
	}
	
	public int getClothingAverageFemininity() {
		return inventory.getClothingAverageFemininity();
	}

	public AbstractClothing getLowestZLayerCoverableArea(CoverableArea area) {
		return inventory.getLowestZLayerCoverableArea(area);
	}

	public AbstractClothing getHighestZLayerCoverableArea(CoverableArea area) {
		return inventory.getHighestZLayerCoverableArea(area);
	}
	
	public Set<InventorySlot> getDirtySlots() {
		return inventory.getDirtySlots();
	}
	
	public boolean isDirtySlot(InventorySlot slot) {
		return inventory.isDirtySlot(slot);
	}
	
	public boolean addDirtySlot(InventorySlot slot) {
		return inventory.addDirtySlot(slot);
	}
	
	public boolean removeDirtySlot(InventorySlot slot) {
		return inventory.removeDirtySlot(slot);
	}

	public void cleanAllDirtySlots() {
		inventory.cleanAllDirtySlots();
	}

	// Body:

	/** <b>Please use accessor methods instead! Accessing and modifying body parts directly will cause problems! >:(</b> */
	public Body getBody() {
		return body;
	}
	
	public List<BodyPartInterface> getAllBodyParts() {
		return body.getAllBodyParts();
	}
	
	private class GenderAppearance {
		public String description;
		public Gender gender;
		public GenderAppearance(String description, Gender gender) {
			this.description = description;
			this.gender = gender;
		}
	}
	
	public Gender getAppearsAsGender() {
		return calculateGenderAppearance(false).gender;
	}

	public String getAppearsAsGenderDescription(boolean colouredGender) {
		return UtilText.parse(this, calculateGenderAppearance(colouredGender).description);
	}
	
	public boolean isPenisBulgeVisible() {
		return hasPenis()
				&& getGenitalArrangement() != GenitalArrangement.CLOACA
				&& (hasPenisModifier(PenetrationModifier.SHEATHED)
					? getPenisRawSizeValue()>=PenisSize.FOUR_HUGE.getMaximumValue()
					: getPenisRawSizeValue()>=PenisSize.TWO_AVERAGE.getMaximumValue());
	}
	
	public boolean isTesticleBulgeVisible() {
		return hasPenis()
				&& getGenitalArrangement() != GenitalArrangement.CLOACA
				&& (isInternalTesticles()
						? false
						: getTesticleSize().getValue()>=TesticleSize.FOUR_HUGE.getValue());
	}
	
	private GenderAppearance calculateGenderAppearance(boolean colouredGender) {
		boolean visibleVagina = isCoverableAreaExposed(CoverableArea.VAGINA) && hasVagina();
		boolean visiblePenis = isCoverableAreaExposed(CoverableArea.PENIS) && hasPenis();
		
		if(this.getFemininityValue()>=Femininity.FEMININE.getMinimumFemininity()) {
			if(hasBreasts()) {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, as well as the fact that you have [pc.breastSize] breasts, everyone can tell that you're [pc.a_gender("+colouredGender+")] on first glance."
							:"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, as well as the fact that [npc.she] has [npc.breastSize] breasts, everyone can tell that [npc.sheIs] [npc.a_gender("+colouredGender+")] on first glance.",
							Gender.F_P_V_B_FUTANARI);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.F_P_V_B_FUTANARI);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.F_P_V_B_FUTANARI);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, and the fact that your [pc.penis] remains concealed, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.F_V_B_FEMALE);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, everyone correctly assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.F_V_B_FEMALE);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis] and [pc.breastSize] breasts, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
							:"Due to [npc.her] exposed [npc.penis] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
							Gender.F_P_B_SHEMALE);
					
				} else {
					// Obvious bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your feminine appearance and [pc.breastSize] breasts, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.F_P_B_SHEMALE);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your feminine appearance and [pc.breastSize] breasts, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.F_P_B_SHEMALE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your feminine appearance and [pc.breastSize] breasts, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] feminine appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.F_V_B_FEMALE);
						
					} else if(hasVagina()) {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Your feminine appearance and [pc.breastSize] breasts leads everyone to correctly assume that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"Due to [npc.her] feminine appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.F_V_B_FEMALE);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your feminine appearance and [pc.breastSize] breasts, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] genderless mound being exposed, combined with [npc.her] feminine appearance and [npc.breastSize] breasts, everyone can tell that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.F_B_DOLL);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your feminine appearance and [pc.breastSize] breasts, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] feminine appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.F_V_B_FEMALE);
						}
					}
				}
				
			// No breasts:
			} else {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, everyone can tell that you're [pc.a_gender("+colouredGender+")] on first glance."
							:"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, everyone can tell that [npc.sheIs] [npc.a_gender("+colouredGender+")] on first glance.",
							Gender.F_P_V_FUTANARI);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.F_P_V_FUTANARI);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.F_P_V_FUTANARI);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], and the fact that your [pc.penis] remains concealed, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.F_V_FEMALE);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], everyone correctly assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.F_V_FEMALE);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis], everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
							:"Due to [npc.her] exposed [npc.penis], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
							Gender.F_P_TRAP);
					
				} else {
					// Obvious bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your feminine appearance, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.F_P_TRAP);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your feminine appearance, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] feminine appearance, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.F_P_TRAP);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your feminine appearance, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] feminine appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.F_V_FEMALE);
						
					} else if(hasVagina()) {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Your feminine appearance leads everyone to correctly assume that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"Due to [npc.her] feminine appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.F_V_FEMALE);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your feminine appearance, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] genderless mound being exposed, combined with [npc.her] feminine appearance, everyone can tell that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.F_DOLL);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your feminine appearance, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] feminine appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.F_V_FEMALE);
						}
					}
				}
			}
			
		// Androgynous:
		} else if(this.getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity()) {
			if(hasBreasts()) {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, as well as the fact that you have [pc.breastSize] breasts, everyone can tell that you're [pc.a_gender("+colouredGender+")] on first glance."
							:"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, as well as the fact that [npc.she] has [npc.breastSize] breasts, everyone can tell that [npc.sheIs] [npc.a_gender("+colouredGender+")] on first glance.",
							Gender.N_P_V_B_HERMAPHRODITE);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.N_P_V_B_HERMAPHRODITE);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.N_P_V_B_HERMAPHRODITE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, and the fact that your [pc.penis] remains concealed, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.N_V_B_TOMBOY);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, everyone correctly assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.N_V_B_TOMBOY);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis] and [pc.breastSize] breasts, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
							:"Due to [npc.her] exposed [npc.penis] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
							Gender.N_P_B_SHEMALE);
					
				} else {
					// Obvious bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your androgynous appearance and [pc.breastSize] breasts, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] androgynous appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.N_P_B_SHEMALE);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your androgynous appearance and [pc.breastSize] breasts, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] androgynous appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.N_P_B_SHEMALE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your androgynous appearance and [pc.breastSize] breasts, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] androgynous appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.N_V_B_TOMBOY);
						
					} else if(hasVagina()) {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Your androgynous appearance and [pc.breastSize] breasts leads everyone to correctly assume that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"Due to [npc.her] androgynous appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.N_V_B_TOMBOY);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your androgynous appearance and [pc.breastSize] breasts, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] genderless mound being exposed, combined with [npc.her] androgynous appearance and [npc.breastSize] breasts, everyone can tell that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.N_B_DOLL);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your androgynous appearance and [pc.breastSize] breasts, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] androgynous appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.N_V_B_TOMBOY);
						}
					}
				}
				
			// No breasts:
			} else {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, everyone can tell that you're [pc.a_gender("+colouredGender+")] on first glance."
							:"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, everyone can tell that [npc.sheIs] [npc.a_gender("+colouredGender+")] on first glance.",
							Gender.N_P_V_HERMAPHRODITE);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.N_P_V_HERMAPHRODITE);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.N_P_V_HERMAPHRODITE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], and the fact that your [pc.penis] remains concealed, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.N_V_TOMBOY);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], everyone correctly assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.N_V_TOMBOY);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis], everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
							:"Due to [npc.her] exposed [npc.penis], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
							Gender.N_P_TRAP);
					
				} else {
					// Obvious bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your androgynous appearance, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] androgynous appearance, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.N_P_TRAP);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your androgynous appearance, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] androgynous appearance, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.N_P_TRAP);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your androgynous appearance, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] androgynous appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.N_V_TOMBOY);
						
					} else if(hasVagina()) {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Your androgynous appearance leads everyone to correctly assume that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"Due to [npc.her] androgynous appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.N_V_TOMBOY);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your androgynous appearance, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]"
									:	"Due to [npc.her] genderless mound being exposed, combined with [npc.her] androgynous appearance, everyone can tell that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.N_NEUTER);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your androgynous appearance, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] androgynous appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.N_V_TOMBOY);
						}
					}
				}
			}
			
		// Masculine:
		} else {
			if(hasBreasts()) {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, as well as the fact that you have [pc.breastSize] breasts, everyone can tell that you're [pc.a_gender("+colouredGender+")] on first glance."
							:"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, as well as the fact that [npc.she] has [npc.breastSize] breasts, everyone can tell that [npc.sheIs] [npc.a_gender("+colouredGender+")] on first glance.",
							Gender.M_P_V_B_HERMAPHRODITE);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.M_P_V_B_HERMAPHRODITE);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina] and [pc.breastSize] breasts, reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.M_P_V_B_HERMAPHRODITE);
					}
					
					if(hasPenis()) {
						// Assume female, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, and the fact that your [pc.penis] remains concealed, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.M_V_B_BUTCH);
						
					} else {
						// Correctly assume female:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina] and [pc.breastSize] breasts, everyone correctly assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.M_V_B_BUTCH);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis] and [pc.breastSize] breasts, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
							:"Due to [npc.her] exposed [npc.penis] and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
							Gender.M_P_B_BUSTYBOY);
					
				} else {
					// Obvious bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your masculine appearance and [pc.breastSize] breasts, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.M_P_B_BUSTYBOY);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your masculine appearance and [pc.breastSize] breasts, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance and [npc.breastSize] breasts, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.M_P_B_BUSTYBOY);
					}
					
					if(hasPenis()) {
						// Correctly assume busty boy:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your masculine appearance and [pc.breastSize] breasts, everyone correctly assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] masculine appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.M_P_B_BUSTYBOY);
						
					} else if(hasVagina()) {
						// Assume bustyboy:
						return new GenderAppearance(
								isPlayer()
								?"Your masculine appearance and [pc.breastSize] breasts leads everyone to assume that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"Due to [npc.her] masculine appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.M_P_B_BUSTYBOY);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your masculine appearance and [pc.breastSize] breasts, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] genderless mound being exposed, combined with [npc.her] masculine appearance and [npc.breastSize] breasts, everyone can tell that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.M_B_MANNEQUIN);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your masculine appearance and [pc.breastSize] breasts, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] masculine appearance and [npc.breastSize] breasts, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.M_V_B_BUTCH);
						}
					}
				}
				
			// No breasts:
			} else {
				if(visibleVagina && visiblePenis) {
					// Exposed penis and vagina:
					return new GenderAppearance(
							isPlayer()
							?"Due to the fact that both your [pc.vagina] and [pc.penis] are exposed, everyone can tell that you're [pc.a_gender("+colouredGender+")] on first glance."
							:"Due to the fact that both [npc.her] [npc.vagina] and [npc.penis] are exposed, everyone can tell that [npc.sheIs] [npc.a_gender("+colouredGender+")] on first glance.",
							Gender.M_P_V_HERMAPHRODITE);
						
				} else if(visibleVagina) {
					// Exposed vagina and obvious penis bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.M_P_V_HERMAPHRODITE);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your exposed [pc.vagina], reveals to everyone that you're [pc.a_gender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] exposed [npc.vagina], reveals to everyone that [npc.sheIs] [npc.a_gender("+colouredGender+")].",
								Gender.M_P_V_HERMAPHRODITE);
					}
					
					if(hasPenis()) {
						// Assume cuntboy, as penis is not visible:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], and the fact that your [pc.penis] remains concealed, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.M_V_CUNTBOY);
						
					} else {
						// Correctly assume cuntboy:
						return new GenderAppearance(
								isPlayer()
								?"Due to your exposed [pc.vagina], everyone correctly assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] exposed [npc.vagina], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.M_V_CUNTBOY);
					}
					
				} else if(visiblePenis) {
					// Exposed penis:
					return new GenderAppearance(
							isPlayer()
							?"Due to your exposed [pc.penis], everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
							:"Due to [npc.her] exposed [npc.penis], everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
							Gender.M_P_MALE);
					
				} else {
					// Obvious bulge:
					if(isPenisBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.cockSize] bulge between your legs, combined with your masculine appearance, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.cockSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.M_P_MALE);
						
					} else if (isTesticleBulgeVisible()) {
						return new GenderAppearance(
								isPlayer()
								?"The [pc.ballSize] bulge of your [pc.balls] between your legs, combined with your masculine appearance, leads everyone to believe that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"The [npc.ballSize] bulge between [npc.her] legs, combined with [npc.her] masculine appearance, leads everyone to believe that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.M_P_MALE);
					}
					
					if(hasPenis()) {
						// Assume male:
						return new GenderAppearance(
								isPlayer()
								?"Your [pc.penis] is concealed, so, due to your masculine appearance, everyone assumes that you're [pc.a_appearsAsGender("+colouredGender+")] on first glance."
								:"Due to [npc.her] masculine appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")] on first glance.",
								Gender.M_P_MALE);
						
					} else if(hasVagina()) {
						// Assume male:
						return new GenderAppearance(
								isPlayer()
								?"Your masculine appearance leads everyone to assume that you're [pc.a_appearsAsGender("+colouredGender+")]."
								:"Due to [npc.her] masculine appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
								Gender.M_P_MALE);
						
					} else {
						if(isCoverableAreaExposed(CoverableArea.VAGINA) && isCoverableAreaExposed(CoverableArea.PENIS)) {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is exposed, so, due to your masculine appearance, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] genderless mound being exposed, combined with [npc.her] masculine appearance, everyone can tell that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.M_MANNEQUIN);
							
						} else {
							return new GenderAppearance(
									isPlayer()
									?"Your genderless mound is concealed, so, due to your masculine appearance, strangers treat you as [pc.a_appearsAsGender("+colouredGender+")]."
									:	"Due to [npc.her] masculine appearance, everyone assumes that [npc.sheIs] [npc.a_appearsAsGender("+colouredGender+")].",
									Gender.M_P_MALE);
						}
					}
				}
			}
			
		}
	}


	public Gender getGender() {
		return body.getGender();
	}
	
	//TODO Improve isFeminine method to take into account knowledge of the character's gender.
	public boolean isFeminine() {
		boolean isFeminine = body.isFeminine();
		
		if(Femininity.valueOf(getFemininityValue()) == Femininity.ANDROGYNOUS) {
			switch(Main.getProperties().androgynousIdentification){
				case FEMININE:
					isFeminine = true;
					break;
				case CLOTHING_FEMININE:
					isFeminine = getClothingAverageFemininity() >= 50;
					break;
				case CLOTHING_MASCULINE:
					isFeminine = getClothingAverageFemininity() > 50;
					break;
				case MASCULINE:
					isFeminine = false;
					break;
				default:
					break;
			}
		}
//		else {
//			return body.getGender().isFeminine();
//		}
		return isFeminine;
	}


	public Race getRace() {
		return getSubspecies().getRace();
	}
	
	public Subspecies getSubspecies() {
		return body.getSubspecies();
	}
	
	public boolean isAbleToSelfTransform() {
		return this instanceof Elemental
				|| this.getRace()==Race.DEMON
				|| this.getRace()==Race.SLIME;
	}
	
	public Race getAntennaRace() {
		return body.getAntenna().getType().getRace();
	}
	
	public Race getArmRace() {
		return body.getArm().getType().getRace();
	}

	public Race getAssRace() {
		return body.getAss().getType().getRace();
	}

	public Race getBreastRace() {
		return body.getBreast().getType().getRace();
	}

	public Race getFaceRace() {
		return body.getFace().getType().getRace();
	}

	public Race getEyeRace() {
		return body.getEye().getType().getRace();
	}

	public Race getEarRace() {
		return body.getEar().getType().getRace();
	}

	public Race getHairRace() {
		return body.getHair().getType().getRace();
	}

	public Race getLegRace() {
		return body.getLeg().getType().getRace();
	}

	public Race getSkinRace() {
		return body.getSkin().getType().getRace();
	}

	public Race getHornRace() {
		return body.getHorn().getType().getRace();
	}

	public Race getPenisRace() {
		return getCurrentPenis().getType().getRace();
	}

	public Race getTailRace() {
		return body.getTail().getType().getRace();
	}
	
	public Race getTongueRace() {
		return body.getFace().getTongue().getType().getRace();
	}

	public Race getVaginaRace() {
		return body.getVagina().getType().getRace();
	}

	public Race getWingRace() {
		return body.getWing().getType().getRace();
	}

	public String postTransformationCalculation() {
		return postTransformationCalculation(true);
	}
	public String postTransformationCalculation(boolean displayColourDiscovered) {
		StringBuilder postTFSB = new StringBuilder();
		// If this is the first time getting this covering type:
		for(BodyPartInterface bp : body.getAllBodyParts()) {
			BodyCoveringType bct = bp.getType().getBodyCoveringType(this);
			if(!body.getBodyCoveringTypesDiscovered().contains(bct)) {
				if(bct!=null) {
					body.getBodyCoveringTypesDiscovered().add(bct);
					
					String bctName = bct.getName(this);
					if(bct == BodyCoveringType.HORN) {
						bctName = "horn";
					}
					if(bct == BodyCoveringType.ANTLER_REINDEER) {
						bctName = "antler";
					}
					
					if(displayColourDiscovered) {
						if(isPlayer()) {
							postTFSB.append(
									"<b>You have discovered that your natural "+bctName+" colour is "+getCovering(bct).getColourDescriptor(this, true, false)+"!</b>");
						} else {
							postTFSB.append(UtilText.parse(this,
									"<b>[npc.Name] has discovered that [npc.her] natural "+bctName+" colour is "+getCovering(bct).getColourDescriptor(this, true, false)+"!</b>"));
						}
					}
				}
			}
		}
		
		body.calculateRace();
		calculateSpecialAttacks();

		postTFSB.append(inventory.calculateClothingPostTransformation(this));
		
		updateInventoryListeners();
		updateAttributeListeners();
		
		return postTFSB.toString();
	}

	public Set<BodyCoveringType> getBodyCoveringTypesDiscovered(){
		return body.getBodyCoveringTypesDiscovered();
	}
	
	public BodyCoveringType getBodyHairCoveringType(Race race) {
		return Body.getBodyHairCoveringType(race);
	}
	
	public BodyCoveringType getBodyHairCoveringType() {
		return getBodyHairCoveringType(getRace());
	}

	public RaceStage getRaceStage() {
		return body.getRaceStage();
	}

	// Tattoos and markings:
	
	public void setScar(InventorySlot invSlot, Scar scar) {
		scars.put(invSlot, scar);
	}
	
	public Scar getScarInSlot(InventorySlot invSlot) {
		if(scars.containsKey(invSlot)) {
			return scars.get(invSlot);
		}
		return null;
	}
	
	public void addTattoo(InventorySlot invSlot, Tattoo tattoo) {
		removeTattoo(invSlot);
		tattoos.put(invSlot, tattoo);
		applyEquipTattooEffects(tattoo);
	}
	
	public void removeTattoo(InventorySlot invSlot) {
		Tattoo tattoo = tattoos.get(invSlot);
		if(tattoo!=null) {
			applyUnequipTattooEffects(tattoo);
			tattoos.remove(invSlot);
		}
	}
	
	public Tattoo getTattooInSlot(InventorySlot invSlot) {
		if(tattoos.containsKey(invSlot)) {
			return tattoos.get(invSlot);
		}
		return null;
	}
	
	private void applyEquipTattooEffects(Tattoo tattoo) {
		for (Entry<Attribute, Integer> e : tattoo.getAttributeModifiers().entrySet()) {
			incrementBonusAttribute(e.getKey(), e.getValue());
		}
		
		for(ItemEffect ie : tattoo.getEffects()) {
			if(ie.getSecondaryModifier()!=null && ie.getSecondaryModifier().getFetish()!=null) {
				Fetish associatedFetish = ie.getSecondaryModifier().getFetish();
				switch(ie.getPotency()) {
					case MINOR_BOOST:
						clothingFetishDesireModifiersMap.putIfAbsent(ie.getSecondaryModifier().getFetish(), 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 1);
						break;
					case BOOST:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 2);
						break;
					case MAJOR_BOOST:
						fetishesFromClothing.add(associatedFetish);
						applyFetishGainEffects(associatedFetish);
						break;
					case MINOR_DRAIN:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 1);
						break;
					case DRAIN:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 2);
						break;
					case MAJOR_DRAIN:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 999);
						break;
				}
			}
		}
	}
	
	private void applyUnequipTattooEffects(Tattoo tattoo) {
		for (Entry<Attribute, Integer> e : tattoo.getAttributeModifiers().entrySet()) {
			incrementBonusAttribute(e.getKey(), -e.getValue());
		}
		
		for(ItemEffect ie : tattoo.getEffects()) {
			if(ie.getSecondaryModifier()!=null && ie.getSecondaryModifier().getFetish()!=null) {
				Fetish associatedFetish = ie.getSecondaryModifier().getFetish();
				switch(ie.getPotency()) {
					case MINOR_BOOST:
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 1);
						break;
					case BOOST:
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) - 2);
						break;
					case MAJOR_BOOST:
						fetishesFromClothing.remove(associatedFetish);
						applyFetishLossEffects(associatedFetish);
						break;
					case MINOR_DRAIN:
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 1);
						break;
					case DRAIN:
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 2);
						break;
					case MAJOR_DRAIN:
						clothingFetishDesireModifiersMap.putIfAbsent(associatedFetish, 0);
						clothingFetishDesireModifiersMap.put(associatedFetish, clothingFetishDesireModifiersMap.get(associatedFetish) + 999);
						break;
				}
			}
		}
	}

	// Femininity:

	public Femininity getFemininity() {
		return Femininity.valueOf(body.getFemininity());
	}
	
	public int getFemininityValue() {
		return body.getFemininity();
	}
	
	public String setFemininity(int femininity) {
		
		String beardLoss = "";
		
		if(femininity>=Femininity.ANDROGYNOUS.getMinimumFemininity() && this.getFacialHair()!=BodyHair.ZERO_NONE && !Main.getProperties().hasValue(PropertyValue.feminineBeardsContent)) {
			if(isPlayer()) {
				beardLoss = "<p>"
								+ "As your body shifts into a more feminine form, you feel your facial hair falling out; evidence that you're no longer able to grow a beard."
							+ "</p>";
			} else {
				beardLoss = "<p>"
								+ "As [npc.her] body shifts into a more feminine form, [npc.her] facial hair falls out; evidence that [npc.sheIs] no longer able to grow a beard."
							+ "</p>";
			}
		}
		
		if (body.getFemininity() < femininity) {
			if (body.setFemininity(femininity)) {
				loadImages();
				if(isPlayer()) {
						return "<p>"
									+ "You feel your body subtly shifting to become <b style='color:" + Colour.FEMININE.toWebHexString() + ";'>more feminine</b>.<br/>"
									+ "You now have <b style='color:"+ Femininity.valueOf(getFemininityValue()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininityValue(), true) + "</b> body."
							+ "</p>"
							+beardLoss;
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.NamePos] body subtly shifts to become <b style='color:" + Colour.FEMININE.toWebHexString() + ";'>more feminine</b>.<br/>"
								+ "[npc.She] now has <b style='color:"+ Femininity.valueOf(getFemininityValue()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininityValue(), true) + "</b> body."
							+ "</p>"
							+beardLoss);
				}
			}
		} else {
			if (body.setFemininity(femininity)) {
				loadImages();
				if(isPlayer()) {
					return "<p>"
								+ "You feel your body subtly shifting to become <b style='color:" + Colour.MASCULINE.toWebHexString() + ";'>more masculine</b>.<br/>"
								+ "You have <b style='color:"+ Femininity.valueOf(getFemininityValue()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininityValue(), true) + "</b> body."
							+ "</p>"
							+beardLoss;
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.NamePos] body subtly shifts to become <b style='color:" + Colour.MASCULINE.toWebHexString() + ";'>more masculine</b>.<br/>"
								+ "[npc.She] now has <b style='color:"+ Femininity.valueOf(getFemininityValue()).getColour().toWebHexString() + ";'>" + Femininity.getFemininityName(getFemininityValue(), true) + "</b> body."
							+ "</p>"
							+beardLoss);
				}
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Your femininity doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>[npc.NamePos] femininity doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementFemininity(int increment) {
		return setFemininity(getFemininityValue() + increment);
	}

	// Body size:
	
	public BodySize getBodySize() {
		return BodySize.valueOf(body.getBodySize());
	}
	
	public int getBodySizeValue() {
		return body.getBodySize();
	}

	public String setBodySize(int bodySize) {
		if (body.getBodySize() < bodySize) {
			if (body.setBodySize(bodySize)) {
				if(isPlayer()) {
					return "<p class='center'>"
								+ "You feel your body shifting and expanding as <b style='color:" + Colour.BODY_SIZE_THREE.toWebHexString() + ";'>you grow larger</b>.<br/>"
								+ "You now have <b style='color:"+ BodySize.valueOf(getBodySizeValue()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySizeValue()).getName(true)+ "</b>, "
									+ Muscle.valueOf(getMuscleValue()).getName(false) + " body, giving you "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p class='center'>"
								+ "[npc.NamePos] body shifts and expands as <b style='color:" + Colour.BODY_SIZE_THREE.toWebHexString() + ";'>[npc.she] grows larger</b>.<br/>"
								+ "[npc.She] now has <b style='color:"+ BodySize.valueOf(getBodySizeValue()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySizeValue()).getName(true) + "</b>, "
										+ Muscle.valueOf(getMuscleValue()).getName(false) + " body, giving [npc.herHim] "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>");
				}
			}
		} else {
			if (body.setBodySize(bodySize)) {
				if(isPlayer()) {
					return "<p class='center'>"
							+ "You feel your body shifting and narrowing down as <b style='color:" + Colour.BODY_SIZE_ONE.toWebHexString() + ";'>you get slimmer</b>.<br/>"
							+ "You now have <b style='color:"+ BodySize.valueOf(getBodySizeValue()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySizeValue()).getName(true) + "</b>, "
									+ Muscle.valueOf(getMuscleValue()).getName(false) + " body, giving you "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
						+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p class='center'>"
								+ "[npc.NamePos] body shifts and narrows down as <b style='color:" + Colour.BODY_SIZE_ONE.toWebHexString() + ";'>[npc.she] gets slimmer</b>.<br/>"
								+ "[npc.She] now has <b style='color:"+ BodySize.valueOf(getBodySizeValue()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySizeValue()).getName(true) + "</b>, "
										+ Muscle.valueOf(getMuscleValue()).getName(false) + " body, giving [npc.herHim] "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>");
				}
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Your body's size doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>[npc.NamePos] body doesn't change...</span>")
				+ "</p>";
		}
	}

	public String incrementBodySize(int increment) {
		return setBodySize(getBodySizeValue() + increment);
	}
	
	// Muscle:
	
	public Muscle getMuscle() {
		return Muscle.valueOf(body.getMuscle());
	}
	
	public int getMuscleValue() {
		return body.getMuscle();
	}

	public String setMuscle(int muscle) {
		if (body.getMuscle() < muscle) {
			if (body.setMuscle(muscle)) {
				if(isPlayer()) {
					return "<p class='center'>"
								+ "You feel your body shifting as <b style='color:" + Colour.MUSCLE_THREE.toWebHexString() + ";'>you get more muscular</b>.<br/>"
								+ "You now have <b style='color:"+ Muscle.valueOf(getMuscleValue()).getColour().toWebHexString() + ";'>" + Muscle.valueOf(getMuscleValue()).getName(true) + "</b>, "
										+ BodySize.valueOf(getBodySizeValue()).getName(false) + " body, giving you "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p class='center'>"
								+ "[npc.NamePos] body shifts as <b style='color:" + Colour.MUSCLE_THREE.toWebHexString() + ";'>[npc.she] gets more muscular</b>.<br/>"
								+ "[npc.She] now has <b style='color:"+ Muscle.valueOf(getMuscleValue()).getColour().toWebHexString() + ";'>" + Muscle.valueOf(getMuscleValue()).getName(true) + "</b>, "
										+ BodySize.valueOf(getBodySizeValue()).getName(false) + " body, giving [npc.her] "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>");
				}
			}
		} else {
			if (body.setMuscle(muscle)) {
				if(isPlayer()) {
					return "<p class='center'>"
							+ "You feel your body shifting as <b style='color:" + Colour.MUSCLE_ONE.toWebHexString() + ";'>you lose some of your muscle</b>.<br/>"
							+ "You now have <b style='color:"+ Muscle.valueOf(getMuscleValue()).getColour().toWebHexString() + ";'>" + Muscle.valueOf(getMuscleValue()).getName(true) + "</b>, "
									+ BodySize.valueOf(getBodySizeValue()).getName(false) + " body, giving you "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
						+ "</p>";
				} else {
					return UtilText.parse(this,
							"<p class='center'>"
								+ "[npc.NamePos] body shifts as <b style='color:" + Colour.MUSCLE_ONE.toWebHexString() + ";'>[npc.she] loses some of [npc.her] muscle</b>.<br/>"
								+ "[npc.She] now has <b style='color:"+ Muscle.valueOf(getMuscleValue()).getColour().toWebHexString() + ";'>" + Muscle.valueOf(getMuscleValue()).getName(true) + "</b>, "
										+ BodySize.valueOf(getBodySizeValue()).getName(false) + " body, giving [npc.her] "+BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue())).getName(true)+" appearance."
							+ "</p>");
				}
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Your body's muscles don't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>[npc.NamePos] muscles don't change...</span>")
				+ "</p>";
		}
	}

	public String incrementMuscle(int increment) {
		return setMuscle(getMuscleValue() + increment);
	}
	

	public BodyShape getBodyShape() {
		return BodyShape.valueOf(Muscle.valueOf(getMuscleValue()), BodySize.valueOf(getBodySizeValue()));
	}

	/**
	 * @return The character's height in cm.
	 */
	public int getHeightValue() {
		return body.getHeightValue();
	}
	
	public Height getHeight() {
		return body.getHeight();
	}

	public boolean isShortStature() {
		return this.getHeightValue()<Height.getShortStatureCutOff();
	}
	
	public int getMinimumHeight() {
		return Height.NEGATIVE_TWO_MIMIMUM.getMinimumValue();
	}
	
	public int getMaximumHeight() {
		return Height.SEVEN_COLOSSAL.getMaximumValue();
	}
	
	/**
	 * @return Formatted description of height change.
	 */
	public String setHeight(int height) {
		height = Math.min(Height.SEVEN_COLOSSAL.getMaximumValue(), Math.max(Height.NEGATIVE_TWO_MIMIMUM.getMinimumValue(), height));
		
		if (body.getHeightValue() < height) {
			if (body.setHeight(height)) {
				return isPlayer()
						? "<p class='center'>"
							+ "The world around you seems slightly further away than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>grown taller</b>."
							+ "<br/>"
							+ "You are now <b>" + getHeightValue() + "cm</b> tall."
						+ "</p>"
						: UtilText.parse(this,
							"<p class='center'>"
								+ "[npc.She] sways from side to side a little, [npc.her] balance suddenly thrown off by the fact that [npc.sheIs] just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>grown taller</b>."
							+ "</p>");
			}
			
		} else if (body.getHeightValue() > height) {
			if (body.setHeight(height)) {
				return isPlayer()
						? "<p class='center'>"
							+ "The world around you suddenly seems slightly closer than it used to be, but after a moment you realise that you've just <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>become shorter</b>."
							+ "<br/>"
							+ "You are now <b>" + getHeightValue() + "cm</b> tall."
						+ "</p>"
						: UtilText.parse(this,
							"<p class='center'>"
								+ "[npc.She] shrinks down, <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>becoming noticeably shorter</b>."
							+ "</p>");
			}
		}
		
		if(isPlayer()) {
			return "<p class='center'>"
					+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Your height doesn't change...</span>"
				+ "</p>";
		} else {
			return "<p class='center'>"
					+ UtilText.parse(this, "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>[npc.NamePos] height doesn't change...</span>")
				+ "</p>";
		}
	}
	
	/**
	 * @return Formatted description of height change.
	 */
	public String incrementHeight(int increment) {
		return setHeight(getHeightValue() + increment);
	}

	/**
	 * <b>THIS IS NOT A FINISHED METHOD.</b> TODO
	 * 
	 * @return The character's weight. Weight is calculated from height and
	 *         takes into consideration special race modifiers.
	 */
	public int getWeight() {
		return body.getCalculatedWeight();
	}
	
	public boolean isAbleToFlyFromArms() {
		if(body.isAbleToFlyFromArms()) {
			for(AbstractClothing clothing : this.getClothingCurrentlyEquipped()) {
				if(clothing.getClothingType().isHindersArmMovement()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean isAbleToFly() {
		return isAbleToFlyFromArms() || body.isAbleToFlyFromWings();
	}
	
	// Pubic Hair:
	public BodyHair getPubicHair() {
		return body.getPubicHair();
	}
	public Covering getPubicHairType() {
		if(this.hasPenis()) {
			return getCovering(getBodyHairCoveringType(getPenisType().getRace()));
		} else if(this.hasVagina()) {
			return getCovering(getBodyHairCoveringType(getVaginaType().getRace()));
		}
		
		return getCovering(getBodyHairCoveringType(getSkinType().getRace()));
	}
	public String setPubicHair(BodyHair pubicHair) {
		if(getPubicHair() == pubicHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			switch(pubicHair) {
				case ZERO_NONE:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>There is no longer any trace of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>There is no longer any trace of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case ONE_STUBBLE:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a stubbly patch of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a stubbly patch of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case TWO_MANICURED:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a well-manicured patch of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a well-manicured patch of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case THREE_TRIMMED:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a trimmed patch of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a trimmed patch of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case FOUR_NATURAL:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a natural bush of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a natural bush of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case FIVE_UNKEMPT:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have an unkempt mass of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has an unkempt mass of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case SIX_BUSHY:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a thick mass of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a thick mass of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
				case SEVEN_WILD:
					if(this.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a wild, bushy mass of "+getPubicHairType().getFullDescription(this, true)+" in your pubic region.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(this, "<p>[npc.Name] now has a wild, bushy mass of "+getPubicHairType().getFullDescription(this, true)+" in [npc.her] pubic region.</p>"));
					}
					break;
			}
		}
		
		body.setPubicHair(pubicHair);
		
		postTransformationCalculation();
		
		return UtilText.transformationContentSB.toString();
	}
	public String setPubicHair(int value) {
		return setPubicHair(BodyHair.getBodyHairFromValue(value));
	}
	public String incrementPubicHair(int increment) {
		int value = getPubicHair().getValue() + increment;
		if(value < 0) {
			value = 0;
		} else if(value > BodyHair.SEVEN_WILD.getValue()) {
			value = BodyHair.SEVEN_WILD.getValue();
		}
		return setPubicHair(BodyHair.getBodyHairFromValue(value));
	}
	
	// Piercings:
	
	// Navel/Stomach (I wasn't very consistent with the names...):
	public boolean isPiercedNavel() {
		return body.isPiercedStomach();
	}
	/**
	 * @return Formatted description of piercing change.
	 */
	public String setPiercedNavel(boolean pierced) {
		if (!isPiercedNavel() && pierced) {
			body.setPiercedStomach(true);
			if(isPlayer()) {
				return "<p>"
							+ "Your navel is now <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>pierced</b>."
						+ "</p>";
			}else {
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Her] navel is now <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>pierced</b>."
						+ "</p>");
			}
			
		} else if (isPiercedNavel() && !pierced) {
			body.setPiercedStomach(false);
			
			AbstractClothing c = this.getClothingInSlot(InventorySlot.PIERCING_STOMACH);
			String piercingUnequip = "";
			if(c!=null) {
				this.forceUnequipClothingIntoVoid(this, c);
				piercingUnequip = this.addClothing(c, false);
			}
			
			if(isPlayer()) {
				return "<p>"
							+ "Your navel is <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>no longer pierced</b>."
						+ "</p>"
						+piercingUnequip;
			}else {
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Her] navel is <b style='color:" + Colour.TRANSFORMATION_GENERIC.toWebHexString() + ";'>no longer pierced</b>."
						+ "</p>"
						+piercingUnequip);
			}
			
		} else {
			return "<p>"
						+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Nothing seems to happen.</span>"
					+ "</p>";
		}
	}

	// Body parts in alphabetical order:
	
	
	
	// ------------------------------ Antennae: ------------------------------ //
	
	// Type:
	public AntennaType getAntennaType() {
		return body.getAntenna().getType();
	}
	public String setAntennaType(AntennaType hornType) {
		return body.getAntenna().setType(this, hornType);
	}
	// Misc.:
	public boolean hasAntennas() {
		return body.getAntenna().getType() != AntennaType.NONE;
	}
	// Names:
	public String getAntennaName() {
		return body.getAntenna().getName(this);
	}
	public String getAntennaNameSingular() {
		return body.getAntenna().getNameSingular(this);
	}
	public String getAntennaName(boolean withDescriptor) {
		return body.getAntenna().getName(this, withDescriptor);
	}
	public String getAntennaDescriptor() {
		return body.getAntenna().getDescriptor(this);
	}
	public String getAntennaDeterminer() {
		return body.getAntenna().getDeterminer(this);
	}
	public String getAntennaPronoun() {
		return body.getAntenna().getType().getPronoun();
	}
	// Rows:
	public int getAntennaRows() {
		return body.getAntenna().getAntennaRows();
	}
	public String setAntennaRows(int rows) {
		return body.getAntenna().setAntennaRows(this, rows);
	}
	public String incrementAntennaRows(int increment) {
		return body.getAntenna().setAntennaRows(this, getAntennaRows()+increment);
	}
	
	
	
	// ------------------------------ Arms: ------------------------------ //
	
	// Type:
	public ArmType getArmType() {
		return body.getArm().getType();
	}
	public String setArmType(ArmType type) {
		return body.getArm().setType(this, type);
	}
	// Names:
	public String getArmName() {
		return body.getArm().getName(this);
	}
	public String getArmNameSingular() {
		return body.getArm().getNameSingular(this);
	}
	public String getArmName(boolean withDescriptor) {
		return body.getArm().getName(this, withDescriptor);
	}
	public String getArmDescriptor() {
		return body.getArm().getDescriptor(this);
	}
	public String getArmDeterminer() {
		return body.getArm().getDeterminer(this);
	}
	public String getArmPronoun() {
		return body.getArm().getType().getPronoun();
	}
	// Rows:
	public int getArmRows() {
		return body.getArm().getArmRows();
	}
	public String setArmRows(int armRows) {
		return body.getArm().setArmRows(this, armRows);
	}
	public String incrementArmRows(int increment) {
		return setArmRows(getArmRows() + increment);
	}
	// Nail polish:
	public Covering getHandNailPolish() {
		return getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS);
	}
	public String setHandNailPolish(Covering nailPolish) {
		body.getCoverings().put(nailPolish.getType(), nailPolish);
		postTransformationCalculation();
		
		if(isPlayer()) {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any nail polish."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(this, true, false)+" nail polish."
						+ "</p>";
			}
			
		} else {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any nail polish."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(this, true, false)+" nail polish."
						+ "</p>");
			}
		}
	}
	// Underarm hair:
	public BodyHair getUnderarmHair() {
		return body.getArm().getUnderarmHair();
	}
	public Covering getUnderarmHairType() {
		body.getBodyCoveringTypesDiscovered().add(getBodyHairCoveringType(getArmType().getRace()));
		return getCovering(getBodyHairCoveringType(getArmType().getRace()));
	}
	public String setUnderarmHair(BodyHair underarmHair) {
		return body.getArm().setUnderarmHair(this, underarmHair);
	}
	public String setUnderarmHair(int value) {
		return body.getArm().setUnderarmHair(this, BodyHair.getBodyHairFromValue(value));
	}
	public String incrementUnderarmHair(int increment) {
		int value = getUnderarmHair().getValue() + increment;
		if(value < 0) {
			value = 0;
		} else if(value > BodyHair.SEVEN_WILD.getValue()) {
			value = BodyHair.SEVEN_WILD.getValue();
		}
		return body.getArm().setUnderarmHair(this, BodyHair.getBodyHairFromValue(value));
	}
	
	
	
	// ------------------------------ Ass: ------------------------------ //
	
	// Type:
	public AssType getAssType() {
		return body.getAss().getType();
	}
	public String setAssType(AssType type) {
		return body.getAss().setType(this, type);
	}
	// Names:
	public String getAssName() {
		return body.getAss().getName(this);
	}
	public String getAssNameSingular() {
		return body.getAss().getNameSingular(this);
	}
	public String getAssName(boolean withDescriptor) {
		return body.getAss().getName(this, withDescriptor);
	}
	public String getAssDescriptor() {
		return body.getAss().getDescriptor(this);
	}
	public String getAssDeterminer() {
		return body.getAss().getDeterminer(this);
	}
	public String getAssPronoun() {
		return body.getAss().getType().getPronoun();
	}
	public String getAssDescription() {
		return body.getAssDescription(this);
	}
	// Names:
	public String getAnusName() {
		return body.getAss().getAnus().getName(this);
	}
	public String getAnusNameSingular() {
		return body.getAss().getAnus().getNameSingular(this);
	}
	public String getAnusName(boolean withDescriptor) {
		return body.getAss().getAnus().getName(this, withDescriptor);
	}
	public String getAnusDescriptor() {
		return body.getAss().getAnus().getDescriptor(this);
	}
	public String getAnusDeterminer() {
		return body.getAss().getAnus().getDeterminer(this);
	}
	public String getAnusPronoun() {
		return body.getAss().getAnus().getType().getPronoun();
	}
	// Size:
	public AssSize getAssSize() {
		return body.getAss().getAssSize();
	}
	public String setAssSize(int assSize) {
		return body.getAss().setAssSize(this, assSize);
	}
	public String setAssSize(AssSize assSize) {
		return body.getAss().setAssSize(this, assSize.getValue());
	}
	public String incrementAssSize(int assSize) {
		return body.getAss().setAssSize(this, getAssSize().getValue() + assSize);
	}
	// Hip size:
	public HipSize getHipSize() {
		return body.getAss().getHipSize();
	}
	public String setHipSize(int hipSize) {
		return body.getAss().setHipSize(this, hipSize);
	}
	public String setHipSize(HipSize hipSize) {
		return body.getAss().setHipSize(this, hipSize.getValue());
	}
	public String incrementHipSize(int hipSize) {
		return body.getAss().setHipSize(this, getHipSize().getValue() + hipSize);
	}
	// Ass hair:
	public BodyHair getAssHair() {
		return body.getAss().getAnus().getAssHair();
	}
	public Covering getAssHairType() {
		body.getBodyCoveringTypesDiscovered().add(body.getAss().getAnus().getAssHairType(this).getType());
		return body.getAss().getAnus().getAssHairType(this);
	}
	public String setAssHair(BodyHair assHair) {
		return body.getAss().getAnus().setAssHair(this, assHair);
	}
	public String setAssHair(int value) {
		return body.getAss().getAnus().setAssHair(this, BodyHair.getBodyHairFromValue(value));
	}
	public String incrementAssHair(int increment) {
		int value = getAssHair().getValue() + increment;
		if(value < 0) {
			value = 0;
		} else if(value > BodyHair.SEVEN_WILD.getValue()) {
			value = BodyHair.SEVEN_WILD.getValue();
		}
		return body.getAss().getAnus().setAssHair(this, BodyHair.getBodyHairFromValue(value));
	}
	// Orifice stats:
	// Wetness:
	public Wetness getAssWetness() {
		return body.getAss().getAnus().getOrificeAnus().getWetness(this);
	}
	public String setAssWetness(int wetness) {
		return body.getAss().getAnus().getOrificeAnus().setWetness(this, wetness);
	}
	public String setAssWetness(Wetness wetness) {
		return body.getAss().getAnus().getOrificeAnus().setWetness(this, wetness.getValue());
	}
	public String incrementAssWetness(int increment) {
		return body.getAss().getAnus().getOrificeAnus().setWetness(this, getAssWetness().getValue() + increment);
	}
	// Capacity:
	public Capacity getAssCapacity() {
		return body.getAss().getAnus().getOrificeAnus().getCapacity();
	}
	public float getAssRawCapacityValue() {
		return body.getAss().getAnus().getOrificeAnus().getRawCapacityValue();
	}
	public float getAssStretchedCapacity() {
		return body.getAss().getAnus().getOrificeAnus().getStretchedCapacity();
	}
	public void setAssStretchedCapacity(float capacity){
		body.getAss().getAnus().getOrificeAnus().setStretchedCapacity(capacity);
	}
	public void incrementAssStretchedCapacity(float increment){
		body.getAss().getAnus().getOrificeAnus().setStretchedCapacity(getAssStretchedCapacity() + increment);
	}
	public String setAssCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getAss().getAnus().getOrificeAnus().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String setAssCapacity(Capacity capacity, boolean setStretchedValueToNewValue) {
		return body.getAss().getAnus().getOrificeAnus().setCapacity(this, capacity.getMedianValue(), setStretchedValueToNewValue);
	}
	public String incrementAssCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setAssCapacity(getAssRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	// Elasticity:
	public OrificeElasticity getAssElasticity() {
		return body.getAss().getAnus().getOrificeAnus().getElasticity();
	}
	public String setAssElasticity(int elasticity) {
		return body.getAss().getAnus().getOrificeAnus().setElasticity(this, elasticity);
	}
	public String incrementAssElasticity(int increment) {
		return setAssElasticity(getAssElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getAssPlasticity() {
		return body.getAss().getAnus().getOrificeAnus().getPlasticity();
	}
	public String setAssPlasticity(int plasticity) {
		return body.getAss().getAnus().getOrificeAnus().setPlasticity(this, plasticity);
	}
	public String incrementAssPlasticity(int increment) {
		return setAssPlasticity(getAssPlasticity().getValue() + increment);
	}
	// Virginity:
	public boolean isAssVirgin() {
		return body.getAss().getAnus().getOrificeAnus().isVirgin();
	}
	public void setAssVirgin(boolean virgin) {
		body.getAss().getAnus().getOrificeAnus().setVirgin(virgin);
	}
	// Bleaching:
	public boolean isAssBleached() {
		return body.getAss().getAnus().isBleached();
	}
	public void setAssBleached(boolean bleached) {
		body.getAss().getAnus().setAssBleached(this, bleached);
	}
	// Modifiers:
	public Set<OrificeModifier> getAssOrificeModifiers() {
		return body.getAss().getAnus().getOrificeAnus().getOrificeModifiers();
	}
	public boolean hasAssOrificeModifier(OrificeModifier modifier) {
		return body.getAss().getAnus().getOrificeAnus().hasOrificeModifier(modifier);
	}
	public String addAssOrificeModifier(OrificeModifier modifier) {
		return body.getAss().getAnus().getOrificeAnus().addOrificeModifier(this, modifier);
	}
	public String removeAssOrificeModifier(OrificeModifier modifier) {
		return body.getAss().getAnus().getOrificeAnus().removeOrificeModifier(this, modifier);
	}
	
	
	
	// ------------------------------ Body Material: ------------------------------ //
	
	// Type:
	public BodyMaterial getBodyMaterial() {
		return body.getBodyMaterial();
	}
	public String setBodyMaterial(BodyMaterial type) {
		
		String tfDescription = "";
		
		if(this.getBodyMaterial()==type) {
			return "<p>"
						+ "[style.colourDisabled(Nothing happens...)]"
					+ "</p>";
		}
		
		//TODO other material types
		
		if(type == BodyMaterial.SLIME) {
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				switch(bct) {
					case MAKEUP_BLUSHER:
					case MAKEUP_EYE_LINER:
					case MAKEUP_EYE_SHADOW:
					case MAKEUP_LIPSTICK:
					case MAKEUP_NAIL_POLISH_FEET:
					case MAKEUP_NAIL_POLISH_HANDS:
						 // Slimes can't wear makeup:
						body.getCoverings().put(bct, new Covering(bct, CoveringPattern.NONE, CoveringModifier.SMOOTH, Colour.COVERING_NONE, false, Colour.COVERING_NONE, false));
						break;
					case SLIME:
					case SLIME_EYE:
					case SLIME_PUPILS:
					case SLIME_SCLERA:
					case SLIME_ANUS:
					case SLIME_HAIR:
					case SLIME_MOUTH:
					case SLIME_NIPPLES:
					case SLIME_VAGINA:
					case SLIME_PENIS:
						this.getBodyCoveringTypesDiscovered().add(bct);
						break;
					default:
						break;
				}
			}
			
			this.setVaginaWetness(Wetness.SEVEN_DROOLING.getValue());			
			this.setAssWetness(Wetness.SEVEN_DROOLING.getValue());
			
			String colourBasic = this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().getName();
			try {
				if(this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().isRainbow()) {
					colourBasic = "rainbow-coloured";
				} else {
					colourBasic = this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().getName().split(" ")[1];
				}
			} catch(Exception ex) {
			}
			
			if(this.isPlayer()) {
				tfDescription = "<p>"
							+ "Despite the fact that there's no sudden change in the weather, you feel as though the air around you is rapidly getting warmer and warmer,"
								+ " and within the space of just a few seconds, it's as though you're standing in the middle of a sauna."
							+ " Droplets of sweat quickly begin to bead on your [pc.skin], forming little little rivulets of cool, "
								+this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().getName()+" liquid, which quickly run down over your burning body to drip onto the floor beneath you."
						+ "</p>"
						+ "<p>"
							+ "Despite your body's best efforts at cooling you down, you still find yourself getting hotter and hotter, and, with a heavy sigh, you feel your [pc.legs] collapse out from under you as the heavy heat beats you down."
							+ " Lifting [pc.a_hand] to your face to wipe the sweat from your [pc.eyes], your heat-addled mind suddenly realises that something's very wrong,"
							+ " [pc.thought(Wait... Why is my sweat "+colourBasic+"?!"
									+ " And why is there so much of it?!)]"
						+ "</p>"
						+ "<p>"
							+ "You open your mouth to scream, only to discover that your throat is rapidly being filled with liquid, and all you can manage is a garbled cry,"
							+ " [pc.speechNoEffects(~Bllgh!~ ~Blllgggh!~)]"
						+ "</p>"
						+ "<p>"
							+ "Thrashing around in a frenzied state of panic, your efforts to escape this mysterious goo prove to be completely fruitless, and within a matter of seconds your entire body is covered in slime."
							+ " What's more, you suddenly realise that you've gotten a lot smaller, and, looking down, you see that your [pc.legs] have completely melted away to form more of the goo that's quickly overtaking you."
							+ " Your struggles only seem to speed this alarming process up, and after just a minute more, your [pc.arms] have suffered the same fate as your [pc.legs], having melted away into yet more of the "
								+this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().getName()+" liquid."
						+ "</p>"
						+ "<p>"
							+ "As the rest of your body proceeds to turn into slime, the intense heat that started this whole process starts to fade away, quickly being replaced by the sense of a still, calm coolness all around you."
							+ " Sinking down into the ever-increasing quantity of slime that's enveloping you, you're aware of the fact that the final solid parts of your body have now condensed down into a small sphere,"
								+ " which is what's now housing your senses and consciousness."
							+ " As this final stage of your transformation presents itself, you find yourself remarkably relaxed, considering that your entire being is now just a little core floating around in a sea of "
								+colourBasic+"."
						+ "</p>"
						+ "<p>"
							+ "The calm coolness that's surrounding you steadily starts to come under your control, and, having now undergone your complete transformation into a slime core,"
								+ " you find that you can manipulate the liquid surrounding you in any way you like."
							+ " Quickly reforming a slimy version of your old body around yourself, you discover that you can project your senses into the areas where they used to reside."
							+ " Your vision travels up out of your core and into your slimy eyes, finally allowing you escape the world of "
								+this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().getName()+" goo and see clearly out into your surroundings once again."
							+ " Similarly, you restore your senses of hearing, taste, touch, and smell to their original homes, leaving you as very much the person you were before this alarming transformation, albeit now being composed entirely of slime."
						+ "</p>"
						+ "<p>"
							+ "Your entire being is now condensed into a [style.boldSlime(slime core)]!<br/><i>"
							+ "- You have complete control over all of the slime which surrounds you, allowing you to morph your body parts at will!<br/>"
							+ "- The wetness of your pussy and asshole can never be anything less than "+Wetness.SEVEN_DROOLING.getDescriptor()+"!<br/>"
							+ "- You are unable to apply any makeup to your slimy body!<br/>"
							+ "- You can now be impregnated through any orifice, even if you lack a vagina!</i>"
						+ "</p>";
				
			} else {
				tfDescription = UtilText.parse(this,
						"<p>"
							+ "[npc.NamePos] cheeks instantly flush, and [npc.she] starts panting and sighing as though [npc.sheIs] suffering from an intense heat stroke."
							+ " Droplets of sweat quickly begin to bead on [npc.her] [npc.skin], forming little little rivulets of cool, "
								+this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().getName()+" liquid, which quickly run down over [npc.her] burning body to drip onto the floor beneath [npc.herHim]."
						+ "</p>"
						+ "<p>"
							+ "Despite [npc.her] body's best efforts at cooling [npc.her] down, [npc.she] lets out a heavy sigh, and [npc.her] [npc.legs] collapse out from under [npc.herHim] as [npc.sheIs] beaten down by the intense heat [npc.sheIs] feeling."
							+ " Lifting [npc.a_hand] to [npc.her] face to wipe the sweat from [npc.her] [npc.eyes], [npc.her] heat-addled mind suddenly realises that something's very wrong, and [npc.she] exclaims,"
							+ " [npc.speech(Wait... Why is my sweat "+colourBasic+"?!"
									+ " What's happening?!)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.She] tries to scream, but [npc.her] throat is rapidly being filled with liquid, and all [npc.she] can manage is a garbled cry,"
							+ " [npc.speechNoEffects(~Bllgh!~ ~Blllgggh!~)]"
						+ "</p>"
						+ "<p>"
							+ "Thrashing around in a frenzied state of panic, [npc.her] efforts to escape this mysterious goo prove to be completely fruitless, and within a matter of seconds [npc.her] entire body is covered in slime."
							+ " What's more, [npc.she] suddenly realises that [npc.sheIs] gotten a lot smaller, and, looking down,"
								+ " [npc.she] sees that [npc.her] [npc.legs] have completely melted away to form more of the goo that's quickly overtaking [npc.herHim]."
							+ " [npc.Her] struggles only seem to speed this alarming process up, and after just a minute more, [npc.her] [npc.arms] have suffered the same fate as [npc.her] [npc.legs], having melted away into yet more of the "
								+this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().getName()+" liquid."
						+ "</p>"
						+ "<p>"
							+ "As [npc.name] proceeds to turn into a slime, the final solid parts of [npc.her] body condense down into a small sphere, which is what now houses [npc.her] senses and consciousness."
							+ " Transformed into a little core that's now floating around in a sea of " +colourBasic+", [npc.name] soon finds that [npc.she] can manipulate the liquid surrounding [npc.herHim] in any way [npc.she] likes."
						+"</p>"
						+ "<p>"
							+ "Quickly reforming a slimy version of [npc.her] old body around [npc.herself], [npc.she] discovers that [npc.she] can project [npc.her] senses into the areas where they used to reside."
							+ " [npc.Her] slimy eyes slowly blink as [npc.she] escapes the world of "+this.getCovering(BodyCoveringType.SLIME).getPrimaryColour().getName()+" goo and sees clearly out into [npc.her] surroundings once again."
							+ " Similarly, [npc.she] restores [npc.her] senses of hearing, taste, touch, and smell to their original homes, leaving [npc.herHim] as very much the person [npc.she] was before this alarming transformation,"
								+ " albeit now being composed entirely of slime."
						+ "</p>"
						+ "<p>"
							+ "[npc.NamePos] entire being is now condensed into a [style.boldSlime(slime core)]!<br/><i>"
							+ "- [npc.She] has complete control over all of the slime which surrounds [npc.herHim], allowing [npc.herHim] to morph [npc.her] body parts at will!<br/>"
							+ "- The wetness of [npc.her] pussy and asshole can never be anything less than "+Wetness.SEVEN_DROOLING.getDescriptor()+"!<br/>"
							+ "- [npc.She] is unable to apply any makeup to [npc.her] slimy body!<br/>"
							+ "- [npc.She] can now be impregnated through any orifice, even if [npc.she] lacks a vagina!</i>"
						+ "</p>");
			}
		}
		
		if(type==BodyMaterial.FLESH) {
			if(this.isPlayer()) {
				tfDescription = "<p>"
									+ "Your slimy body starts to tingle all over, and as you look down at your [pc.arms], you see the slime that they're made up of starting to get more and more opaque."
									+ " As your slime starts to solidify, you feel the little glowing core in the centre of your body start to break up and disperse throughout your torso."
								+ "</p>"
								+ "<p>"
									+ "With a sharp gasp, you feel the transformation speed up, and within just a few moments, your entire body has reverted to being made out of flesh and blood."
								+ "</p>"
								+ "<p>"
									+ "Your body is now made out of [style.boldTfGeneric(flesh)]!"
								+ "</p>";
				
			} else {
				tfDescription = UtilText.parse(this,
						"<p>"
							+ "[npc.NamePos] slimy body starts to tingle all over, and as [npc.she] looks down at [npc.her] [npc.arms], [npc.she] sees the slime that they're made up of starting to get more and more opaque."
							+ " As her slime starts to solidify, the little glowing core in the place where [npc.her] heart should be starts to break up and disperse throughout [npc.her] torso."
						+ "</p>"
						+ "<p>"
							+ "With a sharp gasp, [npc.she] feels the transformation speed up, and within just a few moments, [npc.her] entire body has reverted to being made out of flesh and blood."
						+ "</p>"
						+ "<p>"
							+ "[npc.NamePos] body is now made out of [style.boldTfGeneric(flesh)]!"
						+ "</p>");
			}
		}

		body.setBodyMaterial(type);
		postTransformationCalculation(false);
		
		return tfDescription;
	}
	
	
	
	// ------------------------------ Breasts: ------------------------------ //
	
	// Misc:
	public boolean hasBreasts() {
		return body.getBreast().hasBreasts();
	}
	public boolean isBreastFuckableNipplePenetration() {
		return body.getBreast().isFuckable();
	}
	public boolean isBreastFuckablePaizuri() {
		return body.getBreast().getRawSizeValue() >= CupSize.C.getMeasurement();
	}
	// Type:
	public BreastType getBreastType() {
		return body.getBreast().getType();
	}
	public String setBreastType(BreastType type) {
		return body.getBreast().setType(this, type);
	}
	// Shape:
	public BreastShape getBreastShape() {
		return body.getBreast().getShape();
	}
	public String setBreastShape(BreastShape shape) {
		return body.getBreast().setShape(this, shape);
	}
	// Names:
	public String getBreastName() {
		return body.getBreast().getName(this);
	}
	public String getBreastNameSingular() {
		return body.getBreast().getNameSingular(this);
	}
	public String getBreastName(boolean withDescriptor) {
		return body.getBreast().getName(this, withDescriptor);
	}
	public String getBreastDescriptor() {
		return body.getBreast().getDescriptor(this);
	}
	public String getBreastPronoun() {
		return body.getBreast().getType().getPronoun();
	}
	public String getBreastDescription() {
		return body.getBreastDescription(this);
	}
	// Breast rows:
	public int getBreastRows() {
		return body.getBreast().getRows();
	}
	public String setBreastRows(int rows) {
		return body.getBreast().setRows(this, rows);
	}
	public String incrementBreastRows(int increment) {
		return setBreastRows(getBreastRows() + increment);
	}
	// Lactation:
	public Lactation getBreastMilkStorage() {
		if(!Main.getProperties().hasValue(PropertyValue.lactationContent)) {
			return Lactation.ZERO_NONE;
		}
		return body.getBreast().getMilkStorage();
	}
	public int getBreastRawMilkStorageValue() {
		if(!Main.getProperties().hasValue(PropertyValue.lactationContent)) {
			return 0;
		}
		return body.getBreast().getRawMilkStorageValue();
	}
	public String setBreastMilkStorage(int lactation) {
		return body.getBreast().setMilkStorage(this, lactation);
	}
	public String incrementBreastMilkStorage(int increment) {
		return setBreastMilkStorage(getBreastRawMilkStorageValue() + increment);
	}
	// Current milk:
	public Lactation getBreastStoredMilk() {
		if(!Main.getProperties().hasValue(PropertyValue.lactationContent)) {
			return Lactation.ZERO_NONE;
		}
		return body.getBreast().getStoredMilk();
	}
	public float getBreastRawStoredMilkValue() {
		if(!Main.getProperties().hasValue(PropertyValue.lactationContent)) {
			return 0;
		}
		return body.getBreast().getRawStoredMilkValue();
	}
	public String setBreastStoredMilk(float lactation) {
		return body.getBreast().setStoredMilk(this, lactation);
	}
	public String incrementBreastStoredMilk(float increment) {
		return setBreastStoredMilk(getBreastRawStoredMilkValue() + increment);
	}
	// Regen:
	public FluidRegeneration getBreastLactationRegeneration() {
		return body.getBreast().getLactationRegeneration();
	}
	public int getBreastRawLactationRegenerationValue() {
		return body.getBreast().getRawLactationRegenerationValue();
	}
	public String setBreastLactationRegeneration(int regenerationValue) {
		return body.getBreast().setLactationRegeneration(this, regenerationValue);
	}
	public String incrementBreastLactationRegeneration(int increment) {
		return setBreastLactationRegeneration(getBreastRawLactationRegenerationValue() + increment);
	}
	// Breast size:
	public CupSize getBreastSize() {
		return body.getBreast().getSize();
	}
	public int getBreastRawSizeValue() {
		return body.getBreast().getRawSizeValue();
	}
	public String setBreastSize(int size) {
		return body.getBreast().setSize(this, size);
	}
	public String incrementBreastSize(int increment) {
		return setBreastSize(getBreastRawSizeValue() + increment);
	}
	
	// Nipples:
	
	// Type:
	/**NippleType is automatically changed when BreastType is set.*/
	public NippleType getNippleType() {
		return body.getBreast().getNipples().getType();
	}
	// Names:
	public String getNippleName() {
		return body.getBreast().getNipples().getName(this);
	}
	public String getNippleNameSingular() {
		return body.getBreast().getNipples().getNameSingular(this, false);
	}
	public String getNippleName(boolean withDescriptor) {
		return body.getBreast().getNipples().getName(this, withDescriptor);
	}
	public String getNippleDescriptor() {
		return body.getBreast().getNipples().getDescriptor(this);
	}
	// Count:
	public int getNippleCountPerBreast() {
		return body.getBreast().getNippleCountPerBreast();
	}
	public String setNippleCountPerBreast(int count) {
		return body.getBreast().setNippleCountPerBreast(this, count);
	}
	public String incrementNippleCountPerBreast(int increment) {
		return body.getBreast().setNippleCountPerBreast(this, getNippleCountPerBreast() + increment);
	}
	// Nipple Shape:
	public NippleShape getNippleShape() {
		return body.getBreast().getNipples().getNippleShape();
	}
	public String setNippleShape(NippleShape nippleShape) {
		return body.getBreast().getNipples().setNippleShape(this, nippleShape);
	}
	// Nipple size:
	public NippleSize getNippleSize() {
		return body.getBreast().getNipples().getNippleSize();
	}
	public String setNippleSize(int nippleSize) {
		return body.getBreast().getNipples().setNippleSize(this, nippleSize);
	}
	public String setNippleSize(NippleSize nippleSize) {
		return body.getBreast().getNipples().setNippleSize(this, nippleSize.getValue());
	}
	public String incrementNippleSize(int increment) {
		return body.getBreast().getNipples().setNippleSize(this, getNippleSize().getValue() + increment);
	}
	// Areolae size:
	public AreolaeSize getAreolaeSize() {
		return body.getBreast().getNipples().getAreolaeSize();
	}
	public String setAreolaeSize(int areolaeSize) {
		return body.getBreast().getNipples().setAreolaeSize(this, areolaeSize);
	}
	public String setAreolaeSize(AreolaeSize areolaeSize) {
		return body.getBreast().getNipples().setAreolaeSize(this, areolaeSize.getValue());
	}
	public String incrementAreolaeSize(int increment) {
		return body.getBreast().getNipples().setAreolaeSize(this, getAreolaeSize().getValue() + increment);
	}
	// Areolae Shape:
	public AreolaeShape getAreolaeShape() {
		return body.getBreast().getNipples().getAreolaeShape();
	}
	public String setAreolaeShape(AreolaeShape areolaeShape) {
		return body.getBreast().getNipples().setAreolaeShape(this, areolaeShape);
	}
	// Piercing:
	public boolean isPiercedNipple() {
		return body.getBreast().getNipples().isPierced();
	}
	public String setPiercedNipples(boolean pierced) {
		return body.getBreast().getNipples().setPierced(this, pierced);
	}
	// Orifice stats:
	// Wetness:
	public Wetness getNippleWetness() {
		return body.getBreast().getNipples().getOrificeNipples().getWetness(this);
	}
	// Capacity:
	public Capacity getNippleCapacity() {
		return body.getBreast().getNipples().getOrificeNipples().getCapacity();
	}
	public float getNippleRawCapacityValue() {
		return body.getBreast().getNipples().getOrificeNipples().getRawCapacityValue();
	}
	public float getNippleStretchedCapacity() {
		return body.getBreast().getNipples().getOrificeNipples().getStretchedCapacity();
	}
	public void setNippleStretchedCapacity(float capacity){
		body.getBreast().getNipples().getOrificeNipples().setStretchedCapacity(capacity);
	}
	public void incrementNippleStretchedCapacity(float increment){
		body.getBreast().getNipples().getOrificeNipples().setStretchedCapacity(getNippleStretchedCapacity() + increment);
	}
	public String setNippleCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getBreast().getNipples().getOrificeNipples().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementNippleCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setNippleCapacity(getNippleRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	// Elasticity:
	public OrificeElasticity getNippleElasticity() {
		return body.getBreast().getNipples().getOrificeNipples().getElasticity();
	}
	public String setNippleElasticity(int elasticity) {
		return body.getBreast().getNipples().getOrificeNipples().setElasticity(this, elasticity);
	}
	public String incrementNippleElasticity(int increment) {
		return setNippleElasticity(getNippleElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getNipplePlasticity() {
		return body.getBreast().getNipples().getOrificeNipples().getPlasticity();
	}
	public String setNipplePlasticity(int plasticity) {
		return body.getBreast().getNipples().getOrificeNipples().setPlasticity(this, plasticity);
	}
	public String incrementNipplePlasticity(int increment) {
		return setNipplePlasticity(getNipplePlasticity().getValue() + increment);
	}
	// Virginity:
	public boolean isNippleVirgin() {
		return body.getBreast().getNipples().getOrificeNipples().isVirgin();
	}
	public void setNippleVirgin(boolean virgin) {
		body.getBreast().getNipples().getOrificeNipples().setVirgin(virgin);
	}
	// Modifiers:
	public Set<OrificeModifier> getNippleOrificeModifiers() {
		return body.getBreast().getNipples().getOrificeNipples().getOrificeModifiers();
	}
	public boolean hasNippleOrificeModifier(OrificeModifier modifier) {
		return body.getBreast().getNipples().getOrificeNipples().hasOrificeModifier(modifier);
	}
	public String addNippleOrificeModifier(OrificeModifier modifier) {
		return body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(this, modifier);
	}
	public String removeNippleOrificeModifier(OrificeModifier modifier) {
		return body.getBreast().getNipples().getOrificeNipples().removeOrificeModifier(this, modifier);
	}
	
	// Milk:
	public FluidMilk getMilk() {
		return body.getBreast().getMilk();
	}
	public FluidType getMilkType() {
		return body.getBreast().getMilk().getType();
	}
	public String getMilkName() {
		return body.getBreast().getMilk().getName(this);
	}
	// Flavour:
	public FluidFlavour getMilkFlavour() {
		return body.getBreast().getMilk().getFlavour();
	}
	public String setMilkFlavour(FluidFlavour flavour) {
		return body.getBreast().getMilk().setFlavour(this, flavour);
	}
	// Modifiers:
	public boolean hasMilkModifier(FluidModifier fluidModifier) {
		return body.getBreast().getMilk().hasFluidModifier(fluidModifier);
	}
	public String addMilkModifier(FluidModifier fluidModifier) {
		return body.getBreast().getMilk().addFluidModifier(this, fluidModifier);
	}
	public String removeMilkModifier(FluidModifier fluidModifier) {
		return body.getBreast().getMilk().removeFluidModifier(this, fluidModifier);
	}
	// Transformations:
	public List<ItemEffect> getMilkTransformativeEffects() {
		return body.getBreast().getMilk().getTransformativeEffects();
	}
	
	
	
	// ------------------------------ Ears: ------------------------------ //
	
	// Type:
	public EarType getEarType() {
		return body.getEar().getType();
	}
	public String setEarType(EarType type) {
		return body.getEar().setType(this, type);
	}
	// Names:
	public String getEarName() {
		return body.getEar().getName(this);
	}
	public String getEarNameSingular() {
		return body.getEar().getNameSingular(this);
	}
	public String getEarName(boolean withDescriptor) {
		return body.getEar().getName(this, withDescriptor);
	}
	public String getEarDescriptor() {
		return body.getEar().getDescriptor(this);
	}
	public String getEarDeterminer() {
		return body.getEar().getDeterminer(this);
	}
	public String getEarPronoun() {
		return body.getEar().getType().getPronoun();
	}
	//Piercing:
	public boolean isPiercedEar() {
		return body.getEar().isPierced();
	}
	public String setPiercedEar(boolean pierced) {
		return body.getEar().setPierced(this, pierced);
	}
	
	

	// ------------------------------ Eyes: ------------------------------ //
	
	// Type:
	public EyeType getEyeType() {
		return body.getEye().getType();
	}
	public String setEyeType(EyeType type) {
		return body.getEye().setType(this, type);
	}
	// Names:
	public String getEyeName() {
		return body.getEye().getName(this);
	}
	public String getEyeNameSingular() {
		return body.getEye().getNameSingular(this);
	}
	public String getEyeName(boolean withDescriptor) {
		return body.getEye().getName(this, withDescriptor);
	}
	public String getEyeDescriptor() {
		return body.getEye().getDescriptor(this);
	}
	public String getEyeDeterminer() {
		return body.getEye().getDeterminer(this);
	}
	public String getEyePronoun() {
		return body.getEye().getType().getPronoun();
	}
	// Coverings:
	public String setEyeCovering(Covering covering) {
		String description = body.getEye().setEyeCovering(this, covering);
		postTransformationCalculation();
		return description;
	}
	// Eye makeup:
	public Covering getEyeLiner() {
		return getCovering(BodyCoveringType.MAKEUP_EYE_LINER);
	}
	public String setEyeLiner(Covering eyeLiner) {
		body.getCoverings().put(eyeLiner.getType(), eyeLiner);
		postTransformationCalculation();
		
		if(isPlayer()) {
			if(eyeLiner.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any eye liner."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+eyeLiner.getColourDescriptor(this, true, false)+" eye liner."
						+ "</p>";
			}
			
		} else {
			if(eyeLiner.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any eye liner."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+eyeLiner.getColourDescriptor(this, true, false)+" eye liner."
						+ "</p>");
			}
		}
	}
	public Covering getEyeShadow() {
		return getCovering(BodyCoveringType.MAKEUP_EYE_SHADOW);
	}
	public String setEyeShadow(Covering eyeShadow) {
		body.getCoverings().put(eyeShadow.getType(), eyeShadow);
		postTransformationCalculation();
		
		if(isPlayer()) {
			if(eyeShadow.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any eye shadow."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+eyeShadow.getColourDescriptor(this, true, false)+" eye shadow."
						+ "</p>";
			}
			
		} else {
			if(eyeShadow.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any eye shadow."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+eyeShadow.getColourDescriptor(this, true, false)+" eye shadow."
						+ "</p>");
			}
		}
	}
	// Pairs Count:
	public int getEyePairs() {
		return body.getEye().getEyePairs();
	}
	public String setEyePairs(int eyePairs) {
		return body.getEye().setEyePairs(this, eyePairs);
	}
	public String incrementEyePairs(int increment) {
		return body.getEye().setEyePairs(this, getEyePairs() + increment);
	}
	// Shapes:
	public EyeShape getIrisShape() {
		return body.getEye().getIrisShape();
	}
	public String setIrisShape(EyeShape eyeShape) {
		return body.getEye().setIrisShape(this, eyeShape);
	}
	public EyeShape getPupilShape() {
		return body.getEye().getPupilShape();
	}
	public String setPupilShape(EyeShape eyeShape) {
		return body.getEye().setPupilShape(this, eyeShape);
	}
	
	

	// ------------------------------ Face: ------------------------------ //
	
	// Type:
	public FaceType getFaceType() {
		return body.getFace().getType();
	}
	public String setFaceType(FaceType type) {
		return body.getFace().setType(this, type);
	}
	// Names:
	public String getFaceName() {
		return body.getFace().getName(this);
	}
	public String getFaceNameSingular() {
		return body.getFace().getNameSingular(this);
	}
	public String getFaceName(boolean withDescriptor) {
		return body.getFace().getName(this, withDescriptor);
	}
	public String getFaceDescriptor() {
		return body.getFace().getDescriptor(this);
	}
	public String getFaceDeterminer() {
		return body.getFace().getDeterminer(this);
	}
	public String getFacePronoun() {
		return body.getFace().getType().getPronoun();
	}
	// Nose Names:
	public String getNoseNameSingular() {
		return body.getFace().getNoseNameSingular(this);
	}
	public String getNoseNamePlural() {
		return body.getFace().getNoseNamePlural(this);
	}
	public String getNoseDescriptor() {
		return body.getFace().getNoseDescriptor(this);
	}
	// Lip Names:
	public String getLipsNameSingular() {
		return body.getFace().getMouth().getLipsNameSingular(this);
	}
	public String getLipsNamePlural() {
		return body.getFace().getMouth().getLipsNamePlural(this);
	}
	public String getLipsDescriptor() {
		return body.getFace().getMouth().getLipsDescriptor(this);
	}
	// Nose Piercing:
	public boolean isPiercedNose() {
		return body.getFace().isPiercedNose();
	}
	public String setPiercedNose(boolean piercedNose) {
		return body.getFace().setPiercedNose(this, piercedNose);
	}
	// Lip Size:
	public LipSize getLipSize() {
		return body.getFace().getMouth().getLipSize();
	}
	public int getLipSizeValue() {
		return body.getFace().getMouth().getLipSizeValue();
	}
	public String setLipSize(LipSize lipSize) {
		return body.getFace().getMouth().setLipSize(this, lipSize.getValue());
	}
	public String setLipSize(int lipSize) {
		return body.getFace().getMouth().setLipSize(this, lipSize);
	}
	public String incrementLipSize(int lipSize) {
		return body.getFace().getMouth().setLipSize(this, getLipSize().getValue() + lipSize);
	}
	// Piercing:
	public boolean isPiercedLip() {
		return body.getFace().getMouth().isPiercedLip();
	}
	public String setPiercedLip(boolean pierced) {
		return body.getFace().getMouth().setPiercedLip(this, pierced);
	}
	// Makeup:
	public Covering getLipstick() {
		return getCovering(BodyCoveringType.MAKEUP_LIPSTICK);
	}
	public String setLipstick(Covering lipstick) {
		body.getCoverings().put(lipstick.getType(), lipstick);
		postTransformationCalculation();
		
		if(isPlayer()) {
			if(lipstick.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any lipstick."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+lipstick.getColourDescriptor(this, true, false)+" lipstick."
						+ "</p>";
			}
			
		} else {
			if(lipstick.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any lipstick."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+lipstick.getColourDescriptor(this, true, false)+" lipstick."
						+ "</p>");
			}
		}
	}
	public Covering getBlusher() {
		return getCovering(BodyCoveringType.MAKEUP_BLUSHER);
	}
	public String setBlusher(Covering blusher) {
		body.getCoverings().put(blusher.getType(), blusher);
		postTransformationCalculation();
		
		if(isPlayer()) {
			if(blusher.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any blusher."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+blusher.getColourDescriptor(this, true, false)+" blusher."
						+ "</p>";
			}
			
		} else {
			if(blusher.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any blusher."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+blusher.getColourDescriptor(this, true, false)+" blusher."
						+ "</p>");
			}
		}
	}
	// Facial hair:
	public BodyHair getFacialHair() {
		if(this.getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity() && !Main.getProperties().hasValue(PropertyValue.feminineBeardsContent)) {
			setFacialHair(BodyHair.ZERO_NONE);
		}
		return body.getFace().getFacialHair();
	}
	public Covering getFacialHairType() {
		body.getBodyCoveringTypesDiscovered().add(body.getFace().getFacialHairType(this).getType());
		return body.getFace().getFacialHairType(this);
	}
	public String setFacialHair(BodyHair facialHair) {
		return body.getFace().setFacialHair(this, facialHair);
	}
	public String setFacialHair(int value) {
		return body.getFace().setFacialHair(this, BodyHair.getBodyHairFromValue(value));
	}
	public String incrementFacialHair(int increment) {
		int value = getFacialHair().getValue() + increment;
		if(value < 0) {
			value = 0;
		} else if(value > BodyHair.SEVEN_WILD.getValue()) {
			value = BodyHair.SEVEN_WILD.getValue();
		}
		return body.getFace().setFacialHair(this, BodyHair.getBodyHairFromValue(value));
	}
	// Orifice stats:
	// Wetness:
	public Wetness getFaceWetness() {
		return body.getFace().getMouth().getOrificeMouth().getWetness(this);
	}
	public String setFaceWetness(int wetness) {
		return body.getFace().getMouth().getOrificeMouth().setWetness(this, wetness);
	}
	public String incrementFaceWetness(int increment) {
		return body.getFace().getMouth().getOrificeMouth().setWetness(this, getFaceWetness().getValue() + increment);
	}
	// Capacity:
	public Capacity getFaceCapacity() {
		return body.getFace().getMouth().getOrificeMouth().getCapacity();
	}
	public float getFaceRawCapacityValue() {
		return body.getFace().getMouth().getOrificeMouth().getRawCapacityValue();
	}
	public float getFaceStretchedCapacity() {
		return body.getFace().getMouth().getOrificeMouth().getStretchedCapacity();
	}
	public void setFaceStretchedCapacity(float capacity){
		body.getFace().getMouth().getOrificeMouth().setStretchedCapacity(capacity);
	}
	public void incrementFaceStretchedCapacity(float increment){
		body.getFace().getMouth().getOrificeMouth().setStretchedCapacity(getFaceStretchedCapacity() + increment);
	}
	public String setFaceCapacity(Capacity capacity, boolean setStretchedValueToNewValue) {
		return body.getFace().getMouth().getOrificeMouth().setCapacity(this, capacity.getMedianValue(), setStretchedValueToNewValue);
	}
	public String setFaceCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getFace().getMouth().getOrificeMouth().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementFaceCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setFaceCapacity(getFaceRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	// Elasticity:
	public OrificeElasticity getFaceElasticity() {
		return body.getFace().getMouth().getOrificeMouth().getElasticity();
	}
	public String setFaceElasticity(int elasticity) {
		return body.getFace().getMouth().getOrificeMouth().setElasticity(this, elasticity);
	}
	public String setFaceElasticity(OrificeElasticity elasticity) {
		return body.getFace().getMouth().getOrificeMouth().setElasticity(this, elasticity.getValue());
	}
	public String incrementFaceElasticity(int increment) {
		return setFaceElasticity(getFaceElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getFacePlasticity() {
		return body.getFace().getMouth().getOrificeMouth().getPlasticity();
	}
	public String setFacePlasticity(int plasticity) {
		return body.getFace().getMouth().getOrificeMouth().setPlasticity(this, plasticity);
	}
	public String incrementFacePlasticity(int increment) {
		return setFacePlasticity(getFacePlasticity().getValue() + increment);
	}
	// Virginity:
	public boolean isFaceVirgin() {
		return body.getFace().getMouth().getOrificeMouth().isVirgin();
	}
	public void setFaceVirgin(boolean virgin) {
		body.getFace().getMouth().getOrificeMouth().setVirgin(virgin);
	}
	// Modifiers:
	public Set<OrificeModifier> getFaceOrificeModifiers() {
		return body.getFace().getMouth().getOrificeMouth().getOrificeModifiers();
	}
	public boolean hasFaceOrificeModifier(OrificeModifier modifier) {
		return body.getFace().getMouth().getOrificeMouth().hasOrificeModifier(modifier);
	}
	public String addFaceOrificeModifier(OrificeModifier modifier) {
		return body.getFace().getMouth().getOrificeMouth().addOrificeModifier(this, modifier);
	}
	public String removeFaceOrificeModifier(OrificeModifier modifier) {
		return body.getFace().getMouth().getOrificeMouth().removeOrificeModifier(this, modifier);
	}
	
	
	
	// ------------------------------ Genital arrangement: ------------------------------ //
	
	// Type:
	public GenitalArrangement getGenitalArrangement() {
		return body.getGenitalArrangement();
	}
	public String setGenitalArrangement(GenitalArrangement type) {
		body.setGenitalArrangement(type);
		
		return ""; // TODO
	}
	
	
	
	// ------------------------------ Hair: ------------------------------ //
	
	// Type:
	public HairType getHairType() {
		return body.getHair().getType();
	}
	public String setHairType(HairType type) {
		return body.getHair().setType(this, type);
	}
	// Names:
	public String getHairName() {
		return body.getHair().getName(this);
	}
	public String getHairName(boolean withDescriptor) {
		return body.getHair().getName(this, withDescriptor);
	}
	public String getHairDescriptor() {
		return body.getHair().getDescriptor(this);
	}
	public String getHairDeterminer() {
		return body.getHair().getDeterminer(this);
	}
	public String getHairPronoun() {
		return body.getHair().getType().getPronoun();
	}
	// Length:
	public HairLength getHairLength() {
		return body.getHair().getLength();
	}
	public int getHairRawLengthValue() {
		return body.getHair().getRawLengthValue();
	}
	public String setHairLength(int length) {
		return body.getHair().setLength(this, length);
	}
	public String setHairLength(HairLength length) {
		return body.getHair().setLength(this, length.getMedianValue());
	}
	public String incrementHairLength(int increment) {
		return setHairLength(getHairRawLengthValue() + increment);
	}
	// Style:
	public HairStyle getHairStyle() {
		return body.getHair().getStyle();
	}
	public String setHairStyle(HairStyle hairStyle) {
		return body.getHair().setStyle(this, hairStyle);
	}
	// Covering:
	public String setHairCovering(Covering covering, boolean updateBodyHair) {
		if(!getCovering(getHairType().getBodyCoveringType(this)).equals(covering)) {
			body.getCoverings().put(covering.getType(), covering);

			body.updateCoverings(false, false, updateBodyHair, false);
			
			if (isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as your [pc.hair] "+(getHairType().isDefaultPlural()?"change":"changes")+" colour.<br/>"
							+ "You now have [style.boldTfGeneric([pc.hairFullDescription])]."
						+ "</p>"
						+ postTransformationCalculation();
			} else {
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.her] [npc.hair] "+(getHairType().isDefaultPlural()?"change":"changes")+" colour.<br/>"
							+ "[npc.She] now has [style.boldTfGeneric([npc.hairFullDescription])]."
						+ "</p>"
						+ postTransformationCalculation());
			}
		}

		body.updateCoverings(false, false, updateBodyHair, false);
		
		return "<p>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Nothing seems to happen.</span>" + "</p>";
	}
	
	
	
	// ------------------------------ Horns: ------------------------------ //
	
	// Type:
	public HornType getHornType() {
		return body.getHorn().getType();
	}
	public String setHornType(HornType hornType) {
		return body.getHorn().setType(this, hornType);
	}
	// Misc.:
	public boolean hasHorns() {
		return body.getHorn().getType() != HornType.NONE;
	}
	// Names:
	public String getHornName() {
		return body.getHorn().getName(this);
	}
	public String getHornNameSingular() {
		return body.getHorn().getNameSingular(this);
	}
	public String getHornName(boolean withDescriptor) {
		return body.getHorn().getName(this, withDescriptor);
	}
	public String getHornDescriptor() {
		return body.getHorn().getDescriptor(this);
	}
	public String getHornDeterminer() {
		return body.getHorn().getDeterminer(this);
	}
	public String getHornPronoun() {
		return body.getHorn().getType().getPronoun();
	}
	// Length:
	public int getHornLength() {
		return body.getHorn().getHornLengthValue();
	}
	public String setHornLength(int length) {
		return body.getHorn().setHornLength(this, length);
	}
	public String incrementHornLength(int increment) {
		return body.getHorn().setHornLength(this, getHornLength() + increment);
	}
	// Rows:
	public int getHornRows() {
		return body.getHorn().getHornRows();
	}
	public String setHornRows(int rows) {
		return body.getHorn().setHornRows(this, rows);
	}
	public String incrementHornRows(int increment) {
		return body.getHorn().setHornRows(this, getHornRows() + increment);
	}
	// Horns per row:
	public int getHornsPerRow() {
		return body.getHorn().getHornsPerRow();
	}
	public String setHornsPerRow(int hornsPerRow) {
		return body.getHorn().setHornsPerRow(this, hornsPerRow);
	}
	public String incrementHornsPerRow(int increment) {
		return body.getHorn().setHornsPerRow(this, getHornsPerRow() + increment);
	}
	
	
	
	// ------------------------------ Legs: ------------------------------ //
	
	// Type:
	public LegType getLegType() {
		return body.getLeg().getType();
	}
	public String setLegType(LegType type) {
		return body.getLeg().setType(this, type);
	}
	// Foot Structure:
	public FootStructure getFootStructure() {
		return body.getLeg().getFootStructure();
	}
	public String setFootStructure(FootStructure footStructure) {
		return body.getLeg().setFootStructure(footStructure);
	}
	// Name:
	public String getLegName() {
		return body.getLeg().getName(this);
	}
	public String getLegName(boolean withDescriptor) {
		return body.getLeg().getName(this, withDescriptor);
	}
	public String getLegDescriptor() {
		return body.getLeg().getDescriptor(this);
	}
	public String getLegDeterminer() {
		return body.getLeg().getDeterminer(this);
	}
	public String getLegPronoun() {
		return body.getLeg().getType().getPronoun();
	}
	// Nail polish:
	public Covering getFootNailPolish() {
		return getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET);
	}
	public String setFootNailPolish(Covering nailPolish) {
		body.getCoverings().put(nailPolish.getType(), nailPolish);
		postTransformationCalculation();
		
		if(isPlayer()) {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return "<p>"
							+ "You are [style.boldShrink(not wearing)] any toenail polish."
						+ "</p>";
			} else {
				return "<p>"
							+ "You are [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(this, true, false)+" toenail polish."
						+ "</p>";
			}
			
		} else {
			if(nailPolish.getPattern()==CoveringPattern.NONE) {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldShrink(not wearing)] any toenail polish."
						+ "</p>");
			} else {
				return UtilText.parse(this,"<p>"
							+ "[npc.Name] is [style.boldGrow(now wearing)] "+nailPolish.getColourDescriptor(this, true, false)+" toenail polish."
						+ "</p>");
			}
		}
	}
	
	
	
	// ------------------------------ Mound: ------------------------------ //
	
	public String getMoundDescription() {
		return body.getMoundDescription(this);
	}
	
	
	
	// ------------------------------ Tongue: ------------------------------ //
	
	// Type:
	public TongueType getTongueType() {
		return body.getFace().getTongue().getType();
	}
	// Names:
	public String getTongueName() {
		return body.getFace().getTongue().getName(this);
	}
	public String getTongueName(boolean withDescriptor) {
		return body.getFace().getTongue().getName(this, withDescriptor);
	}
	public String getTongueDescriptor() {
		return body.getFace().getTongue().getDescriptor(this);
	}
	public String getTongueDeterminer() {
		return body.getFace().getTongue().getDeterminer(this);
	}
	public String getTonguePronoun() {
		return body.getFace().getTongue().getType().getPronoun();
	}
	// Length:
	public TongueLength getTongueLength() {
		return body.getFace().getTongue().getTongueLength();
	}
	public int getTongueLengthValue() {
		return body.getFace().getTongue().getTongueLengthValue();
	}
	public String setTongueLength(int tongueLength) {
		return body.getFace().getTongue().setTongueLength(this, tongueLength);
	}
	public String incrementTongueLength(int increment) {
		return body.getFace().getTongue().setTongueLength(this, getTongueLengthValue() + increment);
	}
	// Pierced:
	public boolean isPiercedTongue() {
		return body.getFace().getTongue().isPierced();
	}
	public String setPiercedTongue(boolean pierced) {
		return body.getFace().getTongue().setPierced(this, pierced);
	}
	// Tongue Modifiers:
	public boolean hasTongueModifier(TongueModifier modifier) {
		return body.getFace().getTongue().hasTongueModifier(modifier);
	}
	public String addTongueModifier(TongueModifier modifier) {
		return body.getFace().getTongue().addTongueModifier(this, modifier);
	}
	public String removeTongueModifier(TongueModifier modifier) {
		return body.getFace().getTongue().removeTongueModifier(this, modifier);
	}
	
	
	
	// ------------------------------ Penis: ------------------------------ //
	
	public Penis getCurrentPenis() {
		if(body.getPenis().getType()==PenisType.NONE) {
			for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
				if(c.getItemTags().contains(ItemTag.DILDO_TINY)) {
					this.body.getCoverings().put(BodyCoveringType.DILDO, new Covering(BodyCoveringType.DILDO, CoveringPattern.NONE, c.getColour(), false, c.getColour(), false));
					return Dildo.dildoTiny;
					
				} else if(c.getItemTags().contains(ItemTag.DILDO_AVERAGE)) {
					this.body.getCoverings().put(BodyCoveringType.DILDO, new Covering(BodyCoveringType.DILDO, CoveringPattern.NONE, c.getColour(), false, c.getColour(), false));
					return Dildo.dildoAverage;
					
				} else if(c.getItemTags().contains(ItemTag.DILDO_LARGE)) {
					this.body.getCoverings().put(BodyCoveringType.DILDO, new Covering(BodyCoveringType.DILDO, CoveringPattern.NONE, c.getColour(), false, c.getColour(), false));
					return Dildo.dildoLarge;
					
				} else if(c.getItemTags().contains(ItemTag.DILDO_HUGE)) {
					this.body.getCoverings().put(BodyCoveringType.DILDO, new Covering(BodyCoveringType.DILDO, CoveringPattern.NONE, c.getColour(), false, c.getColour(), false));
					return Dildo.dildoHuge;
					
				} else if(c.getItemTags().contains(ItemTag.DILDO_ENORMOUS)) {
					this.body.getCoverings().put(BodyCoveringType.DILDO, new Covering(BodyCoveringType.DILDO, CoveringPattern.NONE, c.getColour(), false, c.getColour(), false));
					return Dildo.dildoEnormous;
					
				} else if(c.getItemTags().contains(ItemTag.DILDO_GIGANTIC)) {
					this.body.getCoverings().put(BodyCoveringType.DILDO, new Covering(BodyCoveringType.DILDO, CoveringPattern.NONE, c.getColour(), false, c.getColour(), false));
					return Dildo.dildoGigantic;
					
				} else if(c.getItemTags().contains(ItemTag.DILDO_STALLION)) {
					this.body.getCoverings().put(BodyCoveringType.DILDO, new Covering(BodyCoveringType.DILDO, CoveringPattern.NONE, c.getColour(), false, c.getColour(), false));
					return Dildo.dildoStallion;
					
				}
			}
		}
		
		return body.getPenis();
	}
	
	// Type:
	public PenisType getPenisType() {
		return getCurrentPenis().getType();
	}
	public String setPenisType(PenisType type) {
		String s = body.getPenis().setType(this, type);
		
		StringBuilder clothingRemovalSB = new StringBuilder();
		List<AbstractClothing> clothingToRemove = new ArrayList<>();
		
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			if((c.getItemTags().contains(ItemTag.REQUIRES_PENIS) && type==PenisType.NONE)
					|| (c.getItemTags().contains(ItemTag.REQUIRES_NO_PENIS) && type!=PenisType.NONE)) {
				clothingToRemove.add(c);
			}
		}
		
		for(AbstractClothing c : clothingToRemove) {
			this.forceUnequipClothingIntoVoid(this, c);
			clothingRemovalSB.append(this.addClothing(c, false));
		}
		
		return s + clothingRemovalSB.toString();
	}
	// Misc.:
	public boolean hasPenisIgnoreDildo() {
		return this.getBody().getPenis().getType() != PenisType.NONE
				&& this.getBody().getPenis().getType() != PenisType.DILDO;
	}
	public boolean hasPenis() {
		return getCurrentPenis().getType() != PenisType.NONE;
	}
	public boolean isPenisVirgin() {
		return getCurrentPenis().isVirgin();
	}
	public void setPenisVirgin(boolean virgin) {
		getCurrentPenis().setVirgin(virgin);
	}
	// Names:
	public String getPenisName() {
		return getCurrentPenis().getName(this);
	}
	public String getPenisName(boolean withDescriptor) {
		return getCurrentPenis().getName(this, withDescriptor);
	}
	public String getPenisDescriptor() {
		return getCurrentPenis().getDescriptor(this);
	}
	public String getPenisDeterminer() {
		return getCurrentPenis().getDeterminer(this);
	}
	public String getPenisPronoun() {
		return getCurrentPenis().getType().getPronoun();
	}
	public String getPenisDescription() {
		return body.getPenisDescription(this);
	}
	// Penis girth:
	public PenisGirth getPenisGirth() {
		return getCurrentPenis().getGirth();
	}
	public int getPenisRawGirthValue() {
		return getCurrentPenis().getRawGirthValue();
	}
	public String setPenisGirth(int size) {
		return getCurrentPenis().setPenisGirth(this, size);
	}
	public String setPenisGirth(PenisGirth size) {
		return getCurrentPenis().setPenisGirth(this, size.getValue());
	}
	public String incrementPenisGirth(int increment) {
		return setPenisGirth(getPenisRawGirthValue() + increment);
	}
	// Penis size:
	public PenisSize getPenisSize() {
		return getCurrentPenis().getSize();
	}
	public int getPenisRawSizeValue() {
		return getCurrentPenis().getRawSizeValue();
	}
	public String setPenisSize(int size) {
		return getCurrentPenis().setPenisSize(this, size);
	}
	public String setPenisSize(PenisSize size) {
		return getCurrentPenis().setPenisSize(this, size.getMedianValue());
	}
	public String incrementPenisSize(int increment) {
		return setPenisSize(getPenisRawSizeValue() + increment);
	}
	// Pierced:
	public boolean isPiercedPenis() {
		return getCurrentPenis().isPierced();
	}
	public String setPiercedPenis(boolean pierced) {
		return getCurrentPenis().setPierced(this, pierced);
	}
	// Modifiers:
	public List<PenetrationModifier> getPenisModifiers() {
		List<PenetrationModifier> list = new ArrayList<>();
		list.addAll(getCurrentPenis().getPenisModifiers());
		return list;
	}
	public boolean hasPenisModifier(PenetrationModifier modifier) {
		return getCurrentPenis().hasPenisModifier(modifier);
	}
	public String addPenisModifier(PenetrationModifier modifier) {
		return getCurrentPenis().addPenisModifier(this, modifier);
	}
	public String removePenisModifier(PenetrationModifier modifier) {
		return getCurrentPenis().removePenisModifier(this, modifier);
	}
	
	
	// Urethra:

	public String getPenisUrethraDescriptor() {
		return getCurrentPenis().getUrethraDescriptor(this);
	}
	public boolean isUrethraFuckable() {
		return getCurrentPenis().getOrificeUrethra().getRawCapacityValue()>0;
	}
	// Capacity:
	public Capacity getPenisCapacity() {
		return getCurrentPenis().getOrificeUrethra().getCapacity();
	}
	public String setPenisCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return getCurrentPenis().getOrificeUrethra().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementPenisCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setPenisCapacity(getPenisRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	public float getPenisRawCapacityValue() {
		return getCurrentPenis().getOrificeUrethra().getRawCapacityValue();
	}
	public float getPenisStretchedCapacity() {
		return getCurrentPenis().getOrificeUrethra().getStretchedCapacity();
	}
	public void setPenisStretchedCapacity(float capacity){
		getCurrentPenis().getOrificeUrethra().setStretchedCapacity(capacity);
	}
	public void incrementPenisStretchedCapacity(float increment){
		getCurrentPenis().getOrificeUrethra().setStretchedCapacity(getPenisStretchedCapacity() + increment);
	}
	// Elasticity:
	public OrificeElasticity getUrethraElasticity() {
		return getCurrentPenis().getOrificeUrethra().getElasticity();
	}
	public String setUrethraElasticity(int elasticity) {
		return getCurrentPenis().getOrificeUrethra().setElasticity(this, elasticity);
	}
	public String incrementUrethraElasticity(int increment) {
		return setUrethraElasticity(getUrethraElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getUrethraPlasticity() {
		return getCurrentPenis().getOrificeUrethra().getPlasticity();
	}
	public String setUrethraPlasticity(int plasticity) {
		return getCurrentPenis().getOrificeUrethra().setPlasticity(this, plasticity);
	}
	public String incrementUrethraPlasticity(int increment) {
		return setUrethraPlasticity(getUrethraPlasticity().getValue() + increment);
	}
	// Virgin:
	public boolean isUrethraVirgin() {
		return getCurrentPenis().getOrificeUrethra().isVirgin();
	}
	public void setUrethraVirgin(boolean virgin) {
		getCurrentPenis().getOrificeUrethra().setVirgin(virgin);
	}
	// Modifiers:
	public boolean hasUrethraOrificeModifier(OrificeModifier modifier) {
		return getCurrentPenis().getOrificeUrethra().hasOrificeModifier(modifier);
	}
	public String addUrethraOrificeModifier(OrificeModifier modifier) {
		return getCurrentPenis().getOrificeUrethra().addOrificeModifier(this, modifier);
	}
	public String removeUrethraOrificeModifier(OrificeModifier modifier) {
		return getCurrentPenis().getOrificeUrethra().removeOrificeModifier(this, modifier);
	}
	
	// ------------------------------ Second Penis: ------------------------------ //
	
	// Type:
	public PenisType getSecondPenisType() {
		return body.getSecondPenis().getType();
	}
	public String setSecondPenisType(PenisType type) {
		return body.getSecondPenis().setType(this, type);
	}
	// Misc.:
	public boolean hasSecondPenis() {
		return body.getSecondPenis().getType() != PenisType.NONE;
	}
	// Names:
	public String getSecondPenisName() {
		return body.getSecondPenis().getName(this);
	}
	public String getSecondPenisName(boolean withDescriptor) {
		return body.getSecondPenis().getName(this, withDescriptor);
	}
	public String getSecondPenisDescriptor() {
		return body.getSecondPenis().getDescriptor(this);
	}
	public String getSecondPenisDeterminer() {
		return body.getSecondPenis().getDeterminer(this);
	}
	public String getSecondPenisPronoun() {
		return body.getSecondPenis().getType().getPronoun();
	}
	// Penis size:
	public PenisSize getSecondPenisSize() {
		return body.getSecondPenis().getSize();
	}
	public int getSecondPenisRawSizeValue() {
		return body.getSecondPenis().getRawSizeValue();
	}
	public String setSecondPenisSize(int size) {
		return body.getSecondPenis().setPenisSize(this, size);
	}
	public String incrementSecondPenisSize(int increment) {
		return setSecondPenisSize(getSecondPenisRawSizeValue() + increment);
	}
	// Pierced:
	public boolean isPiercedSecondPenis() {
		return body.getSecondPenis().isPierced();
	}
	public String setPiercedSecondPenis(boolean pierced) {
		return body.getSecondPenis().setPierced(this, pierced);
	}
	// Modifiers:
	public boolean hasSecondPenisModifier(PenetrationModifier modifier) {
		return body.getSecondPenis().hasPenisModifier(modifier);
	}
	public String addSecondPenisModifier(PenetrationModifier modifier) {
		return body.getSecondPenis().addPenisModifier(this, modifier);
	}
	public String removeSecondPenisModifier(PenetrationModifier modifier) {
		return body.getSecondPenis().removePenisModifier(this, modifier);
	}
	// Urethra:
	// Capacity:
	public Capacity getSecondPenisCapacity() {
		return body.getSecondPenis().getOrificeUrethra().getCapacity();
	}
	public String setSecondPenisCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getSecondPenis().getOrificeUrethra().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementSecondPenisCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setSecondPenisCapacity(getSecondPenisRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	public float getSecondPenisRawCapacityValue() {
		return body.getSecondPenis().getOrificeUrethra().getRawCapacityValue();
	}
	public float getSecondPenisStretchedCapacity() {
		return body.getSecondPenis().getOrificeUrethra().getStretchedCapacity();
	}
	public void setSecondPenisStretchedCapacity(float capacity){
		body.getSecondPenis().getOrificeUrethra().setStretchedCapacity(capacity);
	}
	public void incrementSecondPenisStretchedCapacity(float increment){
		body.getSecondPenis().getOrificeUrethra().setStretchedCapacity(getSecondPenisStretchedCapacity() + increment);
	}
	// Elasticity:
	public OrificeElasticity getSecondUrethraElasticity() {
		return body.getSecondPenis().getOrificeUrethra().getElasticity();
	}
	public String setSecondUrethraElasticity(int elasticity) {
		return body.getSecondPenis().getOrificeUrethra().setElasticity(this, elasticity);
	}
	public String incrementSecondUrethraElasticity(int increment) {
		return setUrethraElasticity(getUrethraElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getSecondUrethraPlasticity() {
		return body.getSecondPenis().getOrificeUrethra().getPlasticity();
	}
	public String setSecondUrethraPlasticity(int plasticity) {
		return body.getSecondPenis().getOrificeUrethra().setPlasticity(this, plasticity);
	}
	public String incrementSecondUrethraPlasticity(int increment) {
		return setUrethraPlasticity(getUrethraPlasticity().getValue() + increment);
	}
	// Virgin:
	public boolean isSecondUrethraVirgin() {
		return body.getSecondPenis().getOrificeUrethra().isVirgin();
	}
	public void setSecondUrethraVirgin(boolean virgin) {
		body.getSecondPenis().getOrificeUrethra().setVirgin(virgin);
	}
	// Modifiers:
	public boolean hasSecondUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getSecondPenis().getOrificeUrethra().hasOrificeModifier(modifier);
	}
	public String addSecondUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getSecondPenis().getOrificeUrethra().addOrificeModifier(this, modifier);
	}
	public String removeSecondUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getSecondPenis().getOrificeUrethra().removeOrificeModifier(this, modifier);
	}

	// ------------------------------ Testicles: ------------------------------ //
	
	// Count:
	public int getPenisNumberOfTesticles() {
		return getCurrentPenis().getTesticle().getTesticleCount();
	}
	public String setPenisNumberOfTesticles(int testicleCount) {
		return getCurrentPenis().getTesticle().setTesticleCount(this, testicleCount);
	}
	// Cum Storage:
	public CumProduction getPenisCumStorage() {
		return body.getPenis().getTesticle().getCumStorage();
	}
	public int getPenisRawCumStorageValue() {
		return body.getPenis().getTesticle().getRawCumStorageValue();
	}
	public String setPenisCumStorage(int cumProduction) {
		return body.getPenis().getTesticle().setCumStorage(this, cumProduction);
	}
	public String incrementPenisCumStorage(int increment) {
		return setPenisCumStorage(getPenisRawCumStorageValue() + increment);
	}
	// Current cum:
	public void fillCumToMaxStorage() {
		setPenisStoredCum(getPenisRawCumStorageValue());
	}
	public CumProduction getPenisStoredCum() {
		if(!Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)) {
			return getPenisCumStorage();
		}
		
		return body.getPenis().getTesticle().getStoredCum();
	}
	public float getPenisRawStoredCumValue() {
		if(!Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)) {
			return getPenisRawCumStorageValue();
		}
		
		return body.getPenis().getTesticle().getRawStoredCumValue();
	}
	public String setPenisStoredCum(float cum) {
		return body.getPenis().getTesticle().setStoredCum(this, cum);
	}
	public String incrementPenisStoredCum(float increment) {
		return setPenisStoredCum(getPenisRawStoredCumValue() + increment);
	}
	// Orgasm cum amount:
	public FluidExpulsion getPenisCumExpulsion() {
		if (!Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)) {
			return FluidExpulsion.FOUR_HUGE;
		}
		return body.getPenis().getTesticle().getCumExpulsion();
	}
	/** As a percentage from 0 -> 100. */
	public int getPenisRawCumExpulsionValue() {
		if (!Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)) {
			return FluidExpulsion.FOUR_HUGE.getMaximumValue();
		}
		return body.getPenis().getTesticle().getRawCumExpulsionValue();
	}
	public String setPenisCumExpulsion(int percentage) {
		return body.getPenis().getTesticle().setCumExpulsion(this, percentage);
	}
	public String incrementPenisCumExpulsion(int percentage) {
		return setPenisCumExpulsion(getPenisRawCumExpulsionValue() + percentage);
	}
	public CumProduction getPenisOrgasmCumQuantity() {
		return CumProduction.getCumProductionFromInt(getPenisRawOrgasmCumQuantity());
	}
	public int getPenisRawOrgasmCumQuantity() {
		if (!Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)) {
			return body.getPenis().getTesticle().getRawCumStorageValue();
		}
		if(body.getPenis().getTesticle().getRawStoredCumValue() <= Testicle.MINIMUM_VALUE_FOR_ALL_CUM_TO_BE_EXPELLED) {
			return (int) body.getPenis().getTesticle().getRawStoredCumValue();
		}
		return (int) (body.getPenis().getTesticle().getRawStoredCumValue() * (getPenisRawCumExpulsionValue()/100f));
	}
	public void applyOrgasmCumEffect() {
		if(Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)) {
			this.incrementPenisStoredCum(-getPenisRawOrgasmCumQuantity());
		}
	}
	// Regen:
	public FluidRegeneration getPenisCumProductionRegeneration() {
		return body.getPenis().getTesticle().getCumProductionRegeneration();
	}
	public int getPenisRawCumProductionRegenerationValue() {
		return body.getPenis().getTesticle().getRawCumProductionRegenerationValue();
	}
	public String setPenisCumProductionRegeneration(int regenerationValue) {
		return body.getPenis().getTesticle().setCumProductionRegeneration(this, regenerationValue);
	}
	public String incrementPenisCumProductionRegeneration(int increment) {
		return setPenisCumProductionRegeneration(getPenisRawCumProductionRegenerationValue() + increment);
	}
	// Testicle size:
	public TesticleSize getTesticleSize() {
		return getCurrentPenis().getTesticle().getTesticleSize();
	}
	public String setTesticleSize(int size) {
		return getCurrentPenis().getTesticle().setTesticleSize(this, size);
	}
	public String setTesticleSize(TesticleSize size) {
		return getCurrentPenis().getTesticle().setTesticleSize(this, size.getValue());
	}
	public String incrementTesticleSize(int increment) {
		return setTesticleSize(getTesticleSize().getValue() + increment);
	}
	// Testicle count:
	public int getTesticleCount() {
		return getCurrentPenis().getTesticle().getTesticleCount();
	}
	public String setTesticleCount(int count) {
		return getCurrentPenis().getTesticle().setTesticleCount(this, count);
	}
	public String incrementTesticleCount(int increment) {
		return getCurrentPenis().getTesticle().setTesticleCount(this, getTesticleCount() + increment);
	}
	// Internal:
	public boolean isInternalTesticles() {
		return getCurrentPenis().getTesticle().isInternal();
	}
	public String setInternalTesticles(boolean internal) {
		return getCurrentPenis().getTesticle().setInternal(this, internal);
	}
	
	// Cum:
	public FluidCum getCum() {
		return getCurrentPenis().getTesticle().getCum();
	}
	public FluidType getCumType() {
		return getCurrentPenis().getTesticle().getCum().getType();
	}
	public String getCumName() {
		return getCurrentPenis().getTesticle().getCum().getName(this);
	}
	// Flavour:
	public FluidFlavour getCumFlavour() {
		return getCurrentPenis().getTesticle().getCum().getFlavour();
	}
	public String setCumFlavour(FluidFlavour flavour) {
		return getCurrentPenis().getTesticle().getCum().setFlavour(this, flavour);
	}
	// Modifiers:
	public List<FluidModifier> getCumModifiers() {
		List<FluidModifier> list = new ArrayList<>();
		list.addAll(getCurrentPenis().getTesticle().getCum().getFluidModifiers());
		return list;
	}
	public boolean hasCumModifier(FluidModifier fluidModifier) {
		return getCurrentPenis().getTesticle().getCum().hasFluidModifier(fluidModifier);
	}
	public String addCumModifier(FluidModifier fluidModifier) {
		return getCurrentPenis().getTesticle().getCum().addFluidModifier(this, fluidModifier);
	}
	public String removeCumModifier(FluidModifier fluidModifier) {
		return getCurrentPenis().getTesticle().getCum().removeFluidModifier(this, fluidModifier);
	}
	// Transformations:
	public List<ItemEffect> getCumTransformativeEffects() {
		return getCurrentPenis().getTesticle().getCum().getTransformativeEffects();
	}
	
	
	
	// ------------------------------ Skin: ------------------------------ //
	
	// Type:
	public SkinType getSkinType() {
		return body.getSkin().getType();
	}
	public String setSkinType(SkinType type) {
		return body.getSkin().setType(this, type);
	}
	// Names:
	public String getSkinName() {
		return body.getSkin().getName(this);
	}
	public String getSkinName(boolean withDescriptor) {
		return body.getSkin().getName(this, withDescriptor);
	}
	public String getSkinDescriptor() {
		return body.getSkin().getDescriptor(this);
	}
	public String getSkinDeterminer() {
		return body.getSkin().getDeterminer(this);
	}
	public String getSkinPronoun() {
		return body.getSkin().getType().getPronoun();
	}
	public Covering getCovering(BodyCoveringType bodyCoveringType) {
		
		switch(bodyCoveringType) {
			case MAKEUP_BLUSHER:
			case MAKEUP_EYE_LINER:
			case MAKEUP_EYE_SHADOW:
			case MAKEUP_LIPSTICK:
			case MAKEUP_NAIL_POLISH_FEET:
			case MAKEUP_NAIL_POLISH_HANDS:
				return body.getCoverings().get(bodyCoveringType);
			default:
				break;
		}
		
		switch(this.getBodyMaterial()) {
			case AIR:
				if(bodyCoveringType==BodyCoveringType.HAIR_DEMON) {
					return body.getCoverings().get(BodyCoveringType.AIR_HAIR);
				}
				return body.getCoverings().get(BodyCoveringType.AIR);
			case ARCANE:
				if(bodyCoveringType==BodyCoveringType.HAIR_DEMON) {
					return body.getCoverings().get(BodyCoveringType.ARCANE_HAIR);
				}
				return body.getCoverings().get(BodyCoveringType.ARCANE);
			case FIRE:
				if(bodyCoveringType==BodyCoveringType.HAIR_DEMON) {
					return body.getCoverings().get(BodyCoveringType.FIRE_HAIR);
				}
				return body.getCoverings().get(BodyCoveringType.FIRE);
			case FLESH:
				break;
			case ICE:
				if(bodyCoveringType==BodyCoveringType.HAIR_DEMON) {
					return body.getCoverings().get(BodyCoveringType.ICE_HAIR);
				}
				return body.getCoverings().get(BodyCoveringType.ICE);
			case RUBBER:
				if(bodyCoveringType==BodyCoveringType.HAIR_DEMON) {
					return body.getCoverings().get(BodyCoveringType.RUBBER_HAIR);
				}
				return body.getCoverings().get(BodyCoveringType.RUBBER);
			case SLIME:
				break;
			case STONE:
				if(bodyCoveringType==BodyCoveringType.HAIR_DEMON) {
					return body.getCoverings().get(BodyCoveringType.STONE_HAIR);
				}
				return body.getCoverings().get(BodyCoveringType.STONE);
			case WATER:
				if(bodyCoveringType==BodyCoveringType.HAIR_DEMON) {
					return body.getCoverings().get(BodyCoveringType.WATER_HAIR);
				}
				return body.getCoverings().get(BodyCoveringType.WATER);
		}
		
		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
			switch(bodyCoveringType) {
				case MAKEUP_BLUSHER:
				case MAKEUP_EYE_LINER:
				case MAKEUP_EYE_SHADOW:
				case MAKEUP_LIPSTICK:
				case MAKEUP_NAIL_POLISH_FEET:
				case MAKEUP_NAIL_POLISH_HANDS:
					break;
				case EYE_PUPILS: case SLIME_PUPILS:
					return body.getCoverings().get(BodyCoveringType.SLIME_PUPILS);
				case EYE_SCLERA: case SLIME_SCLERA:
					return body.getCoverings().get(BodyCoveringType.SLIME_SCLERA);

				case EYE_ALLIGATOR_MORPH:
				case EYE_ANGEL:
				case EYE_BAT:
				case EYE_COW_MORPH:
				case EYE_DEMON_COMMON:
				case EYE_DOG_MORPH:
				case EYE_FELINE:
				case EYE_HARPY:
				case EYE_HORSE_MORPH:
				case EYE_HUMAN:
				case EYE_LYCAN:
				case EYE_RABBIT:
				case EYE_RAT:
				case EYE_REINDEER_MORPH:
				case EYE_SQUIRREL:
				case SLIME_EYE:
					return body.getCoverings().get(BodyCoveringType.SLIME_EYE);
					
				case HAIR_ANGEL:
				case HAIR_BOVINE_FUR:
				case HAIR_CANINE_FUR:
				case HAIR_DEMON:
				case HAIR_FELINE_FUR:
				case HAIR_HARPY:
				case HAIR_HORSE_HAIR:
				case HAIR_HUMAN:
				case HAIR_LYCAN_FUR:
				case HAIR_REINDEER_FUR:
				case HAIR_SCALES_ALLIGATOR:
				case HAIR_SQUIRREL_FUR:
				case SLIME_HAIR:
					return body.getCoverings().get(BodyCoveringType.SLIME_HAIR);
				case ANUS:
				case SLIME_ANUS:
					return body.getCoverings().get(BodyCoveringType.SLIME_ANUS);
				case NIPPLES:
				case SLIME_NIPPLES:
					return body.getCoverings().get(BodyCoveringType.SLIME_NIPPLES);
				case MOUTH:
				case SLIME_MOUTH:
					return body.getCoverings().get(BodyCoveringType.SLIME_MOUTH);
				case VAGINA:
				case SLIME_VAGINA:
					return body.getCoverings().get(BodyCoveringType.SLIME_VAGINA);
				case PENIS:
				case SLIME_PENIS:
					return body.getCoverings().get(BodyCoveringType.SLIME_PENIS);
				default:
					return body.getCoverings().get(BodyCoveringType.SLIME);
			}
		}
		return body.getCoverings().get(bodyCoveringType);
	}

	/**
	 * @return Formatted description of skin colour change.
	 */
	public String setSkinCovering(Covering covering, boolean updateAllSkinColours) {
		
		if(!this.getBodyMaterial().isAbleToWearMakeup()) {
			switch(covering.getType()) {
				case MAKEUP_BLUSHER:
				case MAKEUP_EYE_LINER:
				case MAKEUP_EYE_SHADOW:
				case MAKEUP_LIPSTICK:
				case MAKEUP_NAIL_POLISH_FEET:
				case MAKEUP_NAIL_POLISH_HANDS:
					return "<p>"
								+ "[style.colourDisabled(Makeup cannot be applied to "+this.getBodyMaterial().getName()+"...)]"
							+ "</p>";
				default:
					break;
			}
		}
		
		if(!getCovering(getSkinType().getBodyCoveringType(this)).equals(covering)) {

			BodyCoveringType coveringType = covering.getType();
			
			body.getCoverings().put(coveringType, covering);
			
			body.updateCoverings(false, false, false, updateAllSkinColours);
			
			List<String> affectedParts = new ArrayList<>();
			for (BodyPartInterface part : body.getAllBodyParts()) {
				if (part.getType().getBodyCoveringType(this) == coveringType) {
					affectedParts.add(part.getName(this));
				}
			}
			
			if (!affectedParts.isEmpty()) {
				if (isPlayer()) {
					return "<p>"
								+ "The " + coveringType.getName(this) + " on your " + Util.stringsToStringList(affectedParts, false) + " suddenly start" + (coveringType.isDefaultPlural() ? "" : "s") + " to itch, and you let out a startled cry as "
								+ (coveringType.isDefaultPlural() ? "they begin" : "it begins") + " to change colour.<br/>"
								+ "You now have "+covering.getFullDescription(this, true)+"."
							+ "</p>"
							+ postTransformationCalculation();
				} else {
					return UtilText.parse(this,
							"<p>"
								+ "[npc.Name] feels the " + coveringType.getName(this) + " on [npc.her] " + Util.stringsToStringList(affectedParts, false) + " suddenly start to itch, and [npc.she] lets out a startled cry as "
								+ (coveringType.isDefaultPlural() ? "they begin" : "it begins") + " to change colour.<br/>"
								+ "[npc.She] now has "+covering.getFullDescription(this, true)+"."
							+ "</p>"
							+ postTransformationCalculation());
				}
			} else {
				postTransformationCalculation();
			}
			
		} else {
			body.updateCoverings(false, false, false, updateAllSkinColours);
		}

		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	
	
	// ------------------------------ Tail: ------------------------------ //

	public boolean hasTail() {
		return getTailType() != TailType.NONE;
	}
	// Type:
	public TailType getTailType() {
		return body.getTail().getType();
	}
	public String setTailType(TailType type) {
		return body.getTail().setType(this, type);
	}
	// Misc.:
	public boolean isTailPrehensile(){
		return body.getTail().getType().isPrehensile();
	}
	// Names:
	public String getTailName() {
		return body.getTail().getName(this);
	}
	public String getTailName(boolean withDescriptor) {
		return body.getTail().getName(this, withDescriptor);
	}
	public String getTailDescriptor() {
		return body.getTail().getDescriptor(this);
	}
	public String getTailDeterminer() {
		return body.getTail().getDeterminer(this);
	}
	public String getTailPronoun() {
		return body.getTail().getType().getPronoun();
	}
	// Count:
	public int getTailCount() {
		return body.getTail().getTailCount();
	}
	public String setTailCount(int tailCount) {
		return body.getTail().setTailCount(this, tailCount);
	}
	public String incrementTailCount(int increment) {
		return body.getTail().setTailCount(this, getTailCount() + increment);
	}
	
	
	
	// ------------------------------ Tentacle: ------------------------------ //
	
	// Type:
	public TentacleType getTentacleType() {
		return body.getTentacle().getType();
	}
	public String setTentacleType(TentacleType type) {
		return body.getTentacle().setType(this, type);
	}
	// Names:
	public String getTentacleName() {
		return body.getTentacle().getName(this);
	}
	public String getTentacleName(boolean withDescriptor) {
		return body.getTentacle().getName(this, withDescriptor);
	}
	public String getTentacleDescriptor() {
		return body.getTentacle().getDescriptor(this);
	}
	public String getTentacleDeterminer() {
		return body.getTentacle().getDeterminer(this);
	}
	public String getTentaclePronoun() {
		return body.getTentacle().getType().getPronoun();
	}
	// Count:
	public int getTentacleCount() {
		return body.getTentacle().getTentacleCount();
	}
	public String setTentacleCount(int tailCount) {
		return body.getTentacle().setTentacleCount(this, tailCount);
	}
	public String incrementTentacleCount(int increment) {
		return body.getTentacle().setTentacleCount(this, getTentacleCount() + increment);
	}
	
	

	// ------------------------------ Vagina: ------------------------------ //
	
	// Type:
	public VaginaType getVaginaType() {
		return body.getVagina().getType();
	}
	public String setVaginaType(VaginaType type, boolean overridePregnancyPrevention) {
		return body.getVagina().setType(this, type, overridePregnancyPrevention);
	}
	public String setVaginaType(VaginaType type) {
		return body.getVagina().setType(this, type);
	}
	// Misc.:
	public boolean hasVagina() {
		return body.getVagina().getType() != VaginaType.NONE;
	}
	// Labia size:
	public LabiaSize getVaginaLabiaSize() {
		return body.getVagina().getLabiaSize();
	}
	public int getVaginaRawLabiaSizeValue() {
		return body.getVagina().getRawLabiaSizeValue();
	}
	public String setVaginaLabiaSize(int labiaSize) {
		return body.getVagina().setLabiaSize(this, labiaSize);
	}
	public String setVaginaLabiaSize(LabiaSize labiaSize) {
		return body.getVagina().setLabiaSize(this, labiaSize.getValue());
	}
	public String incrementVaginaLabiaSize(int increment) {
		return setVaginaLabiaSize(getVaginaRawLabiaSizeValue() + increment);
	}
	// Names:
	public String getVaginaName(boolean withDescriptor) {
		return body.getVagina().getName(this, withDescriptor);
	}
	public String getVaginaDescriptor() {
		return body.getVagina().getDescriptor(this);
	}
	public String getVaginaDeterminer() {
		return body.getVagina().getDeterminer(this);
	}
	public String getVaginaPronoun() {
		return body.getVagina().getType().getPronoun();
	}
	public String getVaginaDescription() {
		return body.getVaginaDescription(this);
	}
	// Piercing:
	public boolean isPiercedVagina() {
		return body.getVagina().isPierced();
	}
	public String setPiercedVagina(boolean pierced) {
		return body.getVagina().setPierced(this, pierced);
	}
	// Orifice stats:
	// Wetness:
	public Wetness getVaginaWetness() {
		return body.getVagina().getOrificeVagina().getWetness(this);
	}
	public String setVaginaWetness(int wetness) {
		return body.getVagina().getOrificeVagina().setWetness(this, wetness);
	}
	public String setVaginaWetness(Wetness wetness) {
		return body.getVagina().getOrificeVagina().setWetness(this, wetness.getValue());
	}
	public String incrementVaginaWetness(int increment) {
		return body.getVagina().getOrificeVagina().setWetness(this, getVaginaWetness().getValue() + increment);
	}
	// Capacity:
	public Capacity getVaginaCapacity() {
		return body.getVagina().getOrificeVagina().getCapacity();
	}
	public float getVaginaRawCapacityValue() {
		return body.getVagina().getOrificeVagina().getRawCapacityValue();
	}
	public float getVaginaStretchedCapacity() {
		return body.getVagina().getOrificeVagina().getStretchedCapacity();
	}
	public void setVaginaStretchedCapacity(float capacity){
		body.getVagina().getOrificeVagina().setStretchedCapacity(capacity);
	}
	public void incrementVaginaStretchedCapacity(float increment){
		body.getVagina().getOrificeVagina().setStretchedCapacity(getVaginaStretchedCapacity() + increment);
	}
	public String setVaginaCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getVagina().getOrificeVagina().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String setVaginaCapacity(Capacity capacity, boolean setStretchedValueToNewValue) {
		return body.getVagina().getOrificeVagina().setCapacity(this, capacity.getMedianValue(), setStretchedValueToNewValue);
	}
	public String incrementVaginaCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setVaginaCapacity(getVaginaRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	// Elasticity:
	public OrificeElasticity getVaginaElasticity() {
		return body.getVagina().getOrificeVagina().getElasticity();
	}
	public String setVaginaElasticity(int elasticity) {
		return body.getVagina().getOrificeVagina().setElasticity(this, elasticity);
	}
	public String incrementVaginaElasticity(int increment) {
		return setVaginaElasticity(getVaginaElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getVaginaPlasticity() {
		return body.getVagina().getOrificeVagina().getPlasticity();
	}
	public String setVaginaPlasticity(int plasticity) {
		return body.getVagina().getOrificeVagina().setPlasticity(this, plasticity);
	}
	public String incrementVaginaPlasticity(int increment) {
		return setVaginaPlasticity(getVaginaPlasticity().getValue() + increment);
	}
	// Virginity:
	public boolean isVaginaVirgin() {
		return body.getVagina().getOrificeVagina().isVirgin();
	}
	public void setVaginaVirgin(boolean virgin) {
		body.getVagina().getOrificeVagina().setVirgin(virgin);
	}
	// Squirter:
	public boolean isVaginaSquirter() {
		return body.getVagina().getOrificeVagina().isSquirter();
	}
	public String setVaginaSquirter(boolean squirter) {
		return body.getVagina().getOrificeVagina().setSquirter(this, squirter);
	}
	// Modifiers:
	public Set<OrificeModifier> getVaginaOrificeModifiers() {
		return body.getVagina().getOrificeVagina().getOrificeModifiers();
	}
	public boolean hasVaginaOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeVagina().hasOrificeModifier(modifier);
	}
	public String addVaginaOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeVagina().addOrificeModifier(this, modifier);
	}
	public String removeVaginaOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeVagina().removeOrificeModifier(this, modifier);
	}
	
	//Clitoris:
	// Clitoris size:
	public ClitorisSize getVaginaClitorisSize() {
		return body.getVagina().getClitoris().getClitorisSize();
	}
	public int getVaginaRawClitorisSizeValue() {
		return body.getVagina().getClitoris().getRawClitorisSizeValue();
	}
	public String setVaginaClitorisSize(int clitSize) {
		return body.getVagina().getClitoris().setClitorisSize(this, clitSize);
	}
	public String setVaginaClitorisSize(ClitorisSize clitSize) {
		return body.getVagina().getClitoris().setClitorisSize(this, clitSize.getMedianValue());
	}
	public String incrementVaginaClitorisSize(int increment) {
		return setVaginaClitorisSize(getVaginaRawClitorisSizeValue() + increment);
	}
	// Clitoris girth:
	public PenisGirth getClitorisGirth() {
		return body.getVagina().getClitoris().getGirth();
	}
	public int getClitorisRawGirthValue() {
		return body.getVagina().getClitoris().getRawGirthValue();
	}
	public String setClitorisGirth(int size) {
		return body.getVagina().getClitoris().setGirth(this, size);
	}
	public String incrementClitorisGirth(int increment) {
		return setClitorisGirth(getClitorisRawGirthValue() + increment);
	}
	// Clitoris Modifiers:
	public List<PenetrationModifier> getClitorisModifiers() {
		List<PenetrationModifier> list = new ArrayList<>();
		list.addAll(body.getVagina().getClitoris().getClitorisModifiers());
		return list;
	}
	public boolean hasClitorisModifier(PenetrationModifier modifier) {
		return body.getVagina().getClitoris().hasClitorisModifier(modifier);
	}
	public String addClitorisModifier(PenetrationModifier modifier) {
		return body.getVagina().getClitoris().addClitorisModifier(this, modifier);
	}
	public String removeClitorisModifier(PenetrationModifier modifier) {
		return body.getVagina().getClitoris().removeClitorisModifier(this, modifier);
	}
	
	// Girlcum:
	public FluidGirlCum getGirlcum() {
		return body.getVagina().getGirlcum();
	}
	public FluidType getGirlcumType() {
		return body.getVagina().getGirlcum().getType();
	}
	public String getGirlcumName() {
		return body.getVagina().getGirlcum().getName(this);
	}
	// Flavour:
	public FluidFlavour getGirlcumFlavour() {
		return body.getVagina().getGirlcum().getFlavour();
	}
	public String setGirlcumFlavour(FluidFlavour flavour) {
		return body.getVagina().getGirlcum().setFlavour(this, flavour);
	}
	// Modifiers:
	public boolean hasGirlcumModifier(FluidModifier fluidModifier) {
		return body.getVagina().getGirlcum().hasFluidModifier(fluidModifier);
	}
	public String addGirlcumModifier(FluidModifier fluidModifier) {
		return body.getVagina().getGirlcum().addFluidModifier(this, fluidModifier);
	}
	public String removeGirlcumModifier(FluidModifier fluidModifier) {
		return body.getVagina().getGirlcum().removeFluidModifier(this, fluidModifier);
	}
	// Transformations:
	public List<ItemEffect> getGirlcumTransformativeEffects() {
		return body.getVagina().getGirlcum().getTransformativeEffects();
	}
	
	// Urethra:

	public String getVaginaUrethraDescriptor() {
		return body.getVagina().getUrethraDescriptor(this);
	}
	public boolean isVaginaUrethraFuckable() {
		return body.getVagina().getOrificeUrethra().getRawCapacityValue()>0;
	}
	// Capacity:
	public Capacity getVaginaUrethraCapacity() {
		return body.getVagina().getOrificeUrethra().getCapacity();
	}
	public String setVaginaUrethraCapacity(float capacity, boolean setStretchedValueToNewValue) {
		return body.getVagina().getOrificeUrethra().setCapacity(this, capacity, setStretchedValueToNewValue);
	}
	public String incrementVaginaUrethraCapacity(float increment, boolean setStretchedValueToNewValue) {
		return setVaginaUrethraCapacity(getVaginaUrethraRawCapacityValue() + increment, setStretchedValueToNewValue);
	}
	public float getVaginaUrethraRawCapacityValue() {
		return body.getVagina().getOrificeUrethra().getRawCapacityValue();
	}
	public float getVaginaUrethraStretchedCapacity() {
		return body.getVagina().getOrificeUrethra().getStretchedCapacity();
	}
	public void setVaginaUrethraStretchedCapacity(float capacity){
		body.getVagina().getOrificeUrethra().setStretchedCapacity(capacity);
	}
	public void incrementVaginaUrethraStretchedCapacity(float increment){
		body.getVagina().getOrificeUrethra().setStretchedCapacity(getVaginaUrethraStretchedCapacity() + increment);
	}
	// Elasticity:
	public OrificeElasticity getVaginaUrethraElasticity() {
		return body.getVagina().getOrificeUrethra().getElasticity();
	}
	public String setVaginaUrethraElasticity(int elasticity) {
		return body.getVagina().getOrificeUrethra().setElasticity(this, elasticity);
	}
	public String incrementVaginaUrethraElasticity(int increment) {
		return setUrethraElasticity(getVaginaUrethraElasticity().getValue() + increment);
	}
	// Plasticity:
	public OrificePlasticity getVaginaUrethraPlasticity() {
		return body.getVagina().getOrificeUrethra().getPlasticity();
	}
	public String setVaginaUrethraPlasticity(int plasticity) {
		return body.getVagina().getOrificeUrethra().setPlasticity(this, plasticity);
	}
	public String incrementVaginaUrethraPlasticity(int increment) {
		return setUrethraPlasticity(getVaginaUrethraPlasticity().getValue() + increment);
	}
	// Virgin:
	public boolean isVaginaUrethraVirgin() {
		return body.getVagina().getOrificeUrethra().isVirgin();
	}
	public void setVaginaUrethraVirgin(boolean virgin) {
		body.getVagina().getOrificeUrethra().setVirgin(virgin);
	}
	// Modifiers:
	public boolean hasVaginaUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeUrethra().hasOrificeModifier(modifier);
	}
	public String addVaginaUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeUrethra().addOrificeModifier(this, modifier);
	}
	public String removeVaginaUrethraOrificeModifier(OrificeModifier modifier) {
		return body.getVagina().getOrificeUrethra().removeOrificeModifier(this, modifier);
	}
		
	
	// ------------------------------ Wings: ------------------------------ //
	
	// Type:
	public WingType getWingType() {
		return body.getWing().getType();
	}
	public String setWingType(WingType type) {
		return body.getWing().setType(this, type);
	}
	// Size:
	public WingSize getWingSize() {
		return body.getWing().getSize();
	}
	public int getWingSizeValue() {
		return body.getWing().getSizeValue();
	}
	public String setWingSize(int size) {
		return body.getWing().setSize(this, size);
	}
	public String incrementWingSize(int increment) {
		return body.getWing().setSize(this, getWingSizeValue()+increment);
	}
	// Names:
	public String getWingName() {
		return body.getWing().getName(this);
	}
	public String getWingName(boolean withDescriptor) {
		return body.getWing().getName(this, withDescriptor);
	}
	public String getWingDescriptor() {
		return body.getWing().getDescriptor(this);
	}
	public String getWingDeterminer() {
		return body.getWing().getDeterminer(this);
	}
	public String getWingPronoun() {
		return body.getWing().getType().getPronoun();
	}
	
	public abstract boolean isAbleToBeImpregnated();

}