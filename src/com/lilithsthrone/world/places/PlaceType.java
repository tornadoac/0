package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.JunglePlaces;
import com.lilithsthrone.game.dialogue.places.dominion.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestAlexa;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestBimbo;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestDominant;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestNympho;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestsDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ArcaneArts;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.DreamLover;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.PixsPlayground;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SupplierDepot;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloorRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.places.submission.BatCaverns;
import com.lilithsthrone.game.dialogue.places.submission.GamblingDenDialogue;
import com.lilithsthrone.game.dialogue.places.submission.LyssiethPalaceDialogue;
import com.lilithsthrone.game.dialogue.places.submission.SlimeQueensLair;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColor;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public enum PlaceType {
	
	// Generic holding map:
	
	GENERIC_IMPASSABLE("Impassable Tile", null, BaseColor.GREY, Color.MAP_BACKGROUND, null, null, false, false, true, ""),
	
	GENERIC_EMPTY_TILE("Empty", "dominion/slaverAlleyIcon", BaseColor.CRIMSON, Color.MAP_BACKGROUND, null, null, false, false, true, ""),

	GENERIC_HOLDING_CELL("Unknown", "dominion/slaverAlleyIcon", BaseColor.GREY, Color.MAP_BACKGROUND, null, null, false, false, true, ""),
	
	GENERIC_MUSEUM("Museum", "dominion/slaverAlleyIcon", BaseColor.TAN, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum"),
	
	// World map:

	WORLD_MAP_THICK_JUNGLE("thick jungle", "The further into the jungle one travels, the thicker the vegetation becomes, which allows particularly wild and dangerous predators to conceal themselves...", "#6b8f7e", null, true, true, true, ""),
	WORLD_MAP_JUNGLE("jungle", "Sparse, tropical foliage is home to many different jungle animal-morphs, not all of which are friendly.", "#8fbfa8", null, true, true, true, ""),
	WORLD_MAP_JUNGLE_CITY("Itza'aak", "A sprawling, Mayan-like city on the edge of the jungle, Itza'aak is the last bastion of civilization in the north of Dominion.", "#b377b0", null, true, true, true, ""),

	WORLD_MAP_FOOTHILLS("foothills", "A steady increase in elevation leads to the rolling hills at the base of the mountains of the moon.", Color.BASE_GREY_DARK.getShades()[0], null, true, true, true, ""),
	WORLD_MAP_MOUNTAINS("mountains", "The mountain range west of Dominion is known as the 'mountains of the moon', and is home to many alpine animal-morphs.", Color.BASE_GREY_DARK.getShades()[2], null, true, true, true, ""),
	WORLD_MAP_SNOWY_MOUNTAINS("mountain peaks", "The highest peaks of the mountains of the moon are capped in snow, and are home to several wild and aggressive races...", Color.BASE_GREY_DARK.getShades()[3], null, true, true, true, ""),

	WORLD_MAP_SNOWY_VALLEY("snowstorm valley", "This sheltered valley sees regular, heavy snowfall, and is home to numerous arctic races.", /*R*/"#eeeeee", null, true, true, true, ""),
	WORLD_MAP_GLACIAL_LAKE("selkie lake", "On the western side of snowstorm valley, there can be found a huge, partially-frozen lake.", "#bbf0f1", null, true, true, true, ""),

	WORLD_MAP_DOMINION("Dominion", "The capital city of the land bearing the same name, Dominion is Lilith's seat of power.", Color.BASE_PURPLE.getShades()[2], null, false, false, true, ""),

	WORLD_MAP_GRASSLANDS("grassland", "The grassland wilderness is home to many different races, the vast majority of which are just as wild and untamed as the land they inhabit.", "#beeac9", null, true, true, true, ""),
	WORLD_MAP_FIELDS("Foloi fields", "The farmland surrounding Dominion is known as the 'Foloi fields', and is primarily inhabited by farmyard animal-morphs.", "#e5f8de", null, false, true, true, ""),
	WORLD_MAP_FOREST("forest", "The thick forests scattered throughout the land are particularly dangerous, as they are home to the wild, predatory morphs of wolves, foxes, and bears.", "#8dbb7d", null, true, true, true, ""),
	WORLD_MAP_FIELDS_CITY("Elis", "The largest and most prosperous of all settlements in the Foloi fields, Elis acts as a trading hub for both the youko and the races inhabiting the mountains.", "#d544ae", null, false, true, true, ""),

	WORLD_MAP_YOUKO_FOREST("shinrin highlands", "The Shinrin highlands are a range of low, forest-covered hills, which steadily increase in elevation the further west you go. The elusive youko live here.", "#6ccc74", null, true, true, true, ""),

	WORLD_MAP_WILD_RIVER("river Hubur (wild)", "Far from Dominion, the river Hubur is a dangerous place in which to swim, as it is home to many wild freshwater races.", "#c1f1ee", null, true, true, true, ""),
	WORLD_MAP_RIVER("river Hubur", "The river Huber runs from the west, through Dominion, and flows out into the endless sea. Those parts of it which border the Foloi fields are considered safe.", "#ccfffc", null, false, true, true, ""),

	WORLD_MAP_SEA("endless sea", "The aquatic races inhabiting Dominion do not like to stray too far from shore, and so to them, the sea is considered to be endless.", Color.BASE_BLUE_DARK.getShades()[2], null, true, true, true, ""),
	WORLD_MAP_SEA_CITY("Lyonesse", "The underwater city of Lyonesse is situated off the eastern coast of Dominion, and, unsurprisingly, is particularly difficult for non-aquatic races to visit.", "#8264b0", null, true, true, true, ""),

	WORLD_MAP_ARID_GRASSLAND("arid grassland", "To the south, the wild grassland starts to dry out, and is the preferred home for morphs such as lions, leopard, and zebras.", Color.BASE_YELLOW_LIGHT.getShades()[2], null, true, true, true, ""),
	WORLD_MAP_ARID_SAVANNAH("savannah", "Sparse, open-canopy woodlands are scattered across the arid grasslands, and are inhabited by the same races.", Color.BASE_TAN.getShades()[2], null, true, true, true, ""),

	WORLD_MAP_DESERT("desert", "To the south of the arid grassland, all vegetation dies out, creating a hot, barren wasteland.", "#ffe7a7", null, true, true, true, ""),
	WORLD_MAP_SAND_DUNES("sand dunes", "At the southern edge of the desert, there lies a huge range of sand dunes, which are home to many dangerous races.", "#ffdb7a", null, true, true, true, ""),
	WORLD_MAP_DESERT_CITY("Thinis", "A city resembling those of ancient Egypt, Thinis is the southern-most of Dominion's settlements, and is well known for its prestigious arcane university.", "#d5445e", null, true, true, true, ""),

	WORLD_MAP_VOLCANO("dragon's breath volcano", "A huge volcano, perpetually oozing red-hot lava. Despite its name, dragons are no more common here than they are elsewhere in Dominion.", Color.BASE_ORANGE.getShades()[1], null, true, true, true, ""),
	WORLD_MAP_LAVA_FLOWS("lava flows", "The lava which pours forth from the volcano slowly runs off in a southern direction.", Color.BASE_GREY_DARK.getShades()[0], null, true, true, true, ""),
	
	// Museum:
	
	MUSEUM_ENTRANCE("Entrance", "prologue/exit",  BaseColor.RED, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.PEOPLE, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},
	MUSEUM_CROWDS("Crowds", "prologue/crowd",  BaseColor.YELLOW, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},
	MUSEUM_OFFICE("Office", "prologue/office",  BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum"),
	MUSEUM_STAGE("Stage", "prologue/stage",  BaseColor.ORANGE, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},
	MUSEUM_ROOM("Exhibit Room", "prologue/room",  BaseColor.TAN, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum"),
	MUSEUM_STAIRS("Stairs", "prologue/stairsUp",  BaseColor.GREEN, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum"),
	MUSEUM_LOBBY("Lobby", null,  BaseColor.TAN, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.PEOPLE, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},
	MUSEUM_CORRIDOR("Corridor", null,  BaseColor.TAN, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum"),
	MUSEUM_MIRROR("Mirror Room", "prologue/mirror",  BaseColor.PINK, Color.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum"),
	
	// Dominion:
	
	DOMINION_PLAZA("Lilith's Plaza", "dominion/statue",  BaseColor.PINK_DEEP, Color.MAP_BACKGROUND_PINK, CityPlaces.DOMINION_PLAZA, null, false, false, true, "in Dominion's central plaza") {
		@Override
		public Population getPopulation() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return new Population(PopulationType.ENFORCERS, PopulationDensity.SEVERAL, Subspecies.getDominionStormImmuneSpecies());
			} else {
				return new Population(PopulationType.CROWDS, PopulationDensity.DENSE, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
			}
		}
	},
	
	DOMINION_STREET("Dominion Streets", null, BaseColor.GREY, Color.MAP_BACKGROUND, CityPlaces.STREET, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return null;
			} else {
				return new Population(PopulationType.CROWDS, PopulationDensity.DENSE, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
			}
		}
	},
	
	DOMINION_BOULEVARD("Dominion Boulevard", null, BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND_PINK, CityPlaces.BOULEVARD, Encounter.DOMINION_BOULEVARD, false, false, true, "in the streets of Dominion") {

		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	},
	
	DOMINION_LILITHS_TOWER("Lilith's Tower", "dominion/lilithsTowerIcon", BaseColor.PURPLE, Color.MAP_BACKGROUND_PINK, LilithsTower.OUTSIDE, null, false, false, true, "in the streets of Dominion") {

		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	},
	
	DOMINION_ENFORCER_HQ("Enforcer HQ", "dominion/enforcerHQIcon", BaseColor.BLUE, Color.MAP_BACKGROUND, EnforcerHQDialogue.EXTERIOR, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},
	
	DOMINION_DEMON_HOME_GATE("Demon Home Gates", "dominion/gate", BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND_PINK, DemonHome.DEMON_HOME_GATE, null, false, false, true, "in the streets of Demon Home") {
		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	},
	
	DOMINION_DEMON_HOME("Demon Home", null, BaseColor.PINK, Color.MAP_BACKGROUND_PINK, DemonHome.DEMON_HOME_STREET, null, false, false, true, "in the streets of Demon Home") {
		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	},
	
	DOMINION_DEMON_HOME_ARTHUR("Demon Home", "dominion/demonHomeIcon", BaseColor.PINK, Color.MAP_BACKGROUND_PINK, DemonHome.DEMON_HOME_STREET_ARTHUR, null, false, false, true, "in the streets of Demon Home") {
		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	},
	
	DOMINION_SHOPPING_ARCADE("Shopping Arcade", "dominion/shoppingArcadeIcon", BaseColor.GOLD, Color.MAP_BACKGROUND, ShoppingArcadeDialogue.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},
	
	DOMINION_STREET_HARPY_NESTS("Dominion Streets", null, BaseColor.GREY, Color.MAP_BACKGROUND_DARK, CityPlaces.STREET_SHADED, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},
	
	DOMINION_HARPY_NESTS_ENTRANCE("Harpy Nests Entrance", "dominion/harpyNestIcon", BaseColor.MAGENTA, Color.MAP_BACKGROUND_DARK, HarpyNestsDialogue.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},
	
	DOMINION_NIGHTLIFE_DISTRICT("Nightlife District", "dominion/nightlifeIcon", BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND, NightlifeDistrict.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},
	
	DOMINION_CITY_HALL("City Hall", "dominion/townHallIcon",  BaseColor.LILAC, Color.MAP_BACKGROUND, CityHall.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},
	
	DOMINION_AUNTS_HOME("Lilaya's Home", "dominion/homeIcon", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, LilayaHomeGeneric.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},
	
	DOMINION_SLAVER_ALLEY("Slaver Alley", "dominion/slaverAlleyIcon",  BaseColor.CRIMSON, Color.MAP_BACKGROUND, SlaverAlleyDialogue.OUTSIDE, null, false, false, true, "in the alleyways near Slaver's Alley") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},

	DOMINION_RED_LIGHT_DISTRICT("Red Light District", "dominion/brothel", BaseColor.MAGENTA, Color.MAP_BACKGROUND_DARK, RedLightDistrict.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},

	DOMINION_PARK("Park", "dominion/park", BaseColor.GREEN, Color.MAP_BACKGROUND, CityPlaces.PARK, Encounter.DOMINION_STREET, false, false, true, "in one of Dominion's parks") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	},
	
	// Alleyways:
	
	DOMINION_BACK_ALLEYS("Dominion Alleyways", "dominion/alleysIcon",  BaseColor.BLACK, Color.MAP_BACKGROUND, CityPlaces.BACK_ALLEYS, Encounter.DOMINION_ALLEY, true, false, true, "in one of Dominion's backalleys"),

	DOMINION_DARK_ALLEYS("Dark Alleyways", "dominion/alleysDarkIcon",  BaseColor.PURPLE, Color.MAP_BACKGROUND, CityPlaces.DARK_ALLEYS, Encounter.DOMINION_DARK_ALLEY, true, false, true, "in one of Dominion's dark alleyways"),
	
	DOMINION_ALLEYS_CANAL_CROSSING("Canal Crossing", "dominion/bridge",  BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, CityPlaces.BACK_ALLEYS_CANAL, Encounter.DOMINION_ALLEY, true, false, true, "in one of Dominion's backalleys"),
	
	// Canals:
	
	DOMINION_CANAL("Dominion Canal", "dominion/canalIcon",  BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, CityPlaces.CANAL, Encounter.DOMINION_CANAL, true, false, true, "beside one of Dominion's canals"),
	
	DOMINION_CANAL_END("Dominion Canal", "dominion/canalEndIcon",  BaseColor.BLUE, Color.MAP_BACKGROUND, CityPlaces.CANAL_END, Encounter.DOMINION_CANAL, true, false, true, "beside one of Dominion's canals"),
	
	// Exits & entrances:
	
	DOMINION_EXIT_TO_SUBMISSION("Submission Entrance", "dominion/submissionExit",  BaseColor.TEAL, Color.MAP_BACKGROUND, CityPlaces.CITY_EXIT_SEWERS, null, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	},
	
	DOMINION_EXIT_TO_JUNGLE("Dominion Exit", "dominion/JungleExit",  BaseColor.RED, Color.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT, null, false, false, true, "in the streets of Dominion") {
		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.NORTH;
		}
	},
	
	DOMINION_EXIT_TO_FIELDS("Dominion Exit", "dominion/fieldsExit",  BaseColor.RED, Color.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT, null, false, false, true, "in the streets of Dominion") {
		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.WEST;
		}
	},
	
	DOMINION_EXIT_TO_SEA("Dominion Exit", "dominion/endlessSeaExit",  BaseColor.RED, Color.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT, null, false, false, true, "in the streets of Dominion") {
		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.EAST;
		}
	},
	
	DOMINION_EXIT_TO_DESERT("Dominion Exit", "dominion/desertExit", BaseColor.RED, Color.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT, null, false, false, true, "in the streets of Dominion") {
		@Override
		public Population getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.SOUTH;
		}
	},
	
	ENFORCER_HQ_CORRIDOR("Corridor", null, BaseColor.BLACK, Color.MAP_BACKGROUND, EnforcerHQDialogue.CORRIDOR, null, false, true, true, "in the Enforcer HQ"),

	ENFORCER_HQ_WAITING_AREA("Waiting area", "dominion/enforcerHQ/waitingRoom", BaseColor.BROWN, Color.MAP_BACKGROUND, EnforcerHQDialogue.WAITING_AREA, null, false, true, true, "in the Enforcer HQ"),
	
	ENFORCER_HQ_RECEPTION_DESK("Reception desk", "dominion/enforcerHQ/receptionDesk", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, EnforcerHQDialogue.RECEPTION_DESK, null, false, true, true, "in Candi's office"),
	
	ENFORCER_HQ_GUARDED_DOOR("Guarded door", "dominion/enforcerHQ/guardedDoor", BaseColor.CRIMSON, Color.MAP_BACKGROUND, EnforcerHQDialogue.GUARDED_DOOR, null, false, true, true, "in the Enforcer HQ"),
	
	ENFORCER_HQ_BRAXS_OFFICE("Brax's Office", "dominion/enforcerHQ/braxsOffice", BaseColor.BLUE_STEEL, Color.MAP_BACKGROUND, EnforcerHQDialogue.INTERIOR_BRAX, null, false, true, true, "in his office") {
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.braxEncountered)) {
				return EnforcerHQDialogue.INTERIOR_BRAX_REPEAT;
				
			} else {
				return EnforcerHQDialogue.INTERIOR_BRAX;
			}
		}
	},

	ENFORCER_HQ_ENTRANCE("Entranceway", "dominion/enforcerHQ/exit", BaseColor.RED, Color.MAP_BACKGROUND, EnforcerHQDialogue.ENTRANCE, null, false, true, true, ""),
	
	// Standard tiles:
	HARPY_NESTS_WALKWAYS("Walkway", null, BaseColor.BLACK, Color.MAP_BACKGROUND, HarpyNestsDialogue.WALKWAY, Encounter.HARPY_NEST_WALKWAYS, true, false, true, "in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return super.getPopulation();
			} else {
				return new Population(PopulationType.HARPIES, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies().get(WorldType.HARPY_NEST));
			}
		}
	},
	
	HARPY_NESTS_WALKWAYS_BRIDGE("Walkway Bridge", "dominion/harpyNests/bridge", BaseColor.GREY, Color.MAP_BACKGROUND, HarpyNestsDialogue.WALKWAY_BRIDGE, Encounter.HARPY_NEST_WALKWAYS, true, false, true, "in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public Population getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	},

	// Places:
	HARPY_NESTS_ENTRANCE_ENFORCER_POST("Enforcer post", "dominion/harpyNests/exit", BaseColor.RED, Color.MAP_BACKGROUND, HarpyNestsDialogue.ENTRANCE_ENFORCER_POST, null, false, true, true, "in the Harpy Nests") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.ENFORCERS, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
		}
	},
	
	HARPY_NESTS_ALEXAS_NEST("Alexa's nest", "dominion/harpyNests/nestAlexa", BaseColor.GOLD, Color.MAP_BACKGROUND, HarpyNestAlexa.ALEXAS_NEST_EXTERIOR, null, false, false, true, "in Alexa's nest"){
		@Override
		public Population getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	},
	
	HARPY_NESTS_HARPY_NEST_RED("Harpy nest", "dominion/harpyNests/nestRed", BaseColor.CRIMSON, Color.MAP_BACKGROUND, HarpyNestDominant.HARPY_NEST_DOMINANT, null, false, false, true, "in Diana's nest"){
		@Override
		public Population getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	},
	
	HARPY_NESTS_HARPY_NEST_PINK("Harpy nest", "dominion/harpyNests/nestPink", BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND, HarpyNestNympho.HARPY_NEST_NYMPHO, null, false, false, true, "in Lexi's nest"){
		@Override
		public Population getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	},
	
	HARPY_NESTS_HARPY_NEST_YELLOW("Harpy nest", "dominion/harpyNests/nestYellow", BaseColor.YELLOW_LIGHT, Color.MAP_BACKGROUND, HarpyNestBimbo.HARPY_NEST_BIMBO, null, false, false, true, "in Brittany's nest"){
		@Override
		public Population getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	},
		
	// Standard tiles:
	JUNGLE_PATH("Jungle Path", null, BaseColor.GREEN, Color.MAP_BACKGROUND, JunglePlaces.PATH, null, false, false, true, "in the jungle"),
	
	JUNGLE_DENSE_JUNGLE("Dense Jungle", null, BaseColor.GREEN, Color.MAP_BACKGROUND, JunglePlaces.DENSE_JUNGLE, null, true, false, true, "in the jungle"),

	// Safe places:
	JUNGLE_CLUB("Club", null, BaseColor.GREEN, Color.MAP_BACKGROUND, JunglePlaces.CLUB, null, false, false, true, "in the jungle"),
	
	JUNGLE_BROTHEL("Brothel", null, BaseColor.GREEN, Color.MAP_BACKGROUND, JunglePlaces.BROTHEL, null, false, false, true, "in the jungle"),

	// Dangerous places:
	JUNGLE_TENTACLE_QUEENS_LAIR("Tentacle Queen's Lair", null, BaseColor.GREEN, Color.MAP_BACKGROUND, JunglePlaces.TENTACLE_QUEENS_LAIR, null, false, false, true, "in the jungle"),

	// Exits & entrances:
	JUNGLE_ENTRANCE("Jungle Entrance", null, BaseColor.GREEN, Color.MAP_BACKGROUND, JunglePlaces.JUNGLE_ENTRANCE, null, false, false, true, "in the jungle"){
//		@Override
//		public WorldType getParentWorldType() {
//			return WorldType.DOMINION;
//		}
//		@Override
//		public PlaceType getParentPlaceType() {
//			return PlaceType.DOMINION_EXIT_TO_JUNGLE;
//		}
//		@Override
//		public EntranceType getParentAlignment() {
//			return EntranceType.ALIGNED_FLIP_VERTICAL;
//		}
	},
	
	// Ground floor:
	
	LILAYA_HOME_CORRIDOR("Corridor", null, BaseColor.GREY, Color.MAP_BACKGROUND, LilayaHomeGeneric.CORRIDOR, Encounter.LILAYAS_HOME_CORRIDOR, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR("Room", "dominion/lilayasHome/room", BaseColor.GREY, Color.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW, null, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " G-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColor.GREY, Color.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR, null, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " GG-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR("Room", "dominion/lilayasHome/room", BaseColor.GREY, Color.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW, null, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " F-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColor.GREY, Color.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN, null, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " FG-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ARTHUR_ROOM("Arthur's Room", "dominion/lilayasHome/roomArthur", BaseColor.BLUE_STEEL, Color.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_ARTHUR, null, false, true, false, "in Arthur's Room"),
	
	LILAYA_HOME_BIRTHING_ROOM("Room", "dominion/lilayasHome/roomBirthing", BaseColor.PINK, Color.MAP_BACKGROUND, LilayaHomeGeneric.BIRTHING_ROOM, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_KITCHEN("Kitchen", "dominion/lilayasHome/kitchen", BaseColor.TAN, Color.MAP_BACKGROUND, LilayaHomeGeneric.KITCHEN, null, false, true, false, "in Lilaya's kitchen"),
	
	LILAYA_HOME_LIBRARY("Library", "dominion/lilayasHome/library", BaseColor.TEAL, Color.MAP_BACKGROUND, Library.LIBRARY, null, false, true, false, "in Lilaya's library") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addItem(AbstractItemType.generateItem(ItemType.getLoreBook(Subspecies.HALF_DEMON)));
		}
	},
	
	LILAYA_HOME_STAIR_UP("Staircase", "dominion/lilayasHome/stairsUp", BaseColor.GREEN_LIGHT, Color.MAP_BACKGROUND, LilayaHomeGeneric.STAIRCASE_UP, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ENTRANCE_HALL("Entrance Hall", "dominion/lilayasHome/entranceHall", BaseColor.RED, Color.MAP_BACKGROUND, LilayaHomeGeneric.ENTRANCE_HALL, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_LAB("Lilaya's Lab", "dominion/lilayasHome/lab", BaseColor.ORANGE, Color.MAP_BACKGROUND, Lab.LAB, null, false, true, false, "in Lilaya's lab") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addClothing(AbstractClothingType.generateClothing(ClothingType.SCIENTIST_EYES_SAFETY_GOGGLES, Color.CLOTHING_BLACK, false));
		}
	},
	
	LILAYA_HOME_GARDEN("Garden", "dominion/lilayasHome/garden", BaseColor.GREEN, Color.MAP_BACKGROUND, LilayaHomeGeneric.GARDEN, null, false, false, false, "in Lilaya's garden"),
	
	LILAYA_HOME_FOUNTAIN("Fountain", "dominion/lilayasHome/fountain", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, LilayaHomeGeneric.FOUNTAIN, null, false, false, false, "in Lilaya's garden"),
	

	// First floor:

	LILAYA_HOME_ROOM_LILAYA("Lilaya's Room", "dominion/lilayasHome/roomLilaya", BaseColor.CRIMSON, Color.MAP_BACKGROUND, LilayasRoom.ROOM_LILAYA, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_ROSE("Rose's Room", "dominion/lilayasHome/roomRose", BaseColor.PINK, Color.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_ROSE, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_PLAYER("Your Room", "dominion/lilayasHome/roomPlayer", BaseColor.AQUA, Color.MAP_BACKGROUND, RoomPlayer.ROOM, null, false, true, false, "in your room"),
	
	LILAYA_HOME_STAIR_DOWN("Staircase", "dominion/lilayasHome/stairsDown", BaseColor.RED, Color.MAP_BACKGROUND, LilayaHomeGeneric.STAIRCASE_DOWN, null, false, true, false, "in Lilaya's Home"),
	
	// Zaranix:
	// Ground floor:
	
	ZARANIX_GF_CORRIDOR("Corridor", null, BaseColor.GREY, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.CORRIDOR, null, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR;
			}
		}
	},
	
	ZARANIX_GF_STAIRS("Staircase", "dominion/zaranixHome/stairsDown", BaseColor.GREEN_LIGHT, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.STAIRS, null, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeGroundFloor.STAIRS;
			}
		}
	},
	
	ZARANIX_GF_ENTRANCE("Entrance", "dominion/zaranixHome/entranceHall", BaseColor.RED, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.ENTRANCE, null, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ENTRANCE;
				
			} else {
				return ZaranixHomeGroundFloor.ENTRANCE;
			}
		}
	},
	
	ZARANIX_GF_LOUNGE("Lounge", "dominion/zaranixHome/lounge", BaseColor.ORANGE, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.LOUNGE, null, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.LOUNGE;
				
			} else {
				return ZaranixHomeGroundFloor.LOUNGE;
			}
		}
	},
	
	ZARANIX_GF_ROOM("Room", "dominion/zaranixHome/room", BaseColor.GREY, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.ROOM, null, false, true, false, "in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.ROOM;
			}
		}
	},
	
	ZARANIX_GF_MAID("Corridor", null, BaseColor.GREY, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.CORRIDOR_MAID, null, true, true, false, "in Zaranix's Home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	},
	
	ZARANIX_GF_GARDEN_ROOM("Room", "dominion/zaranixHome/room", BaseColor.GREY, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.GARDEN_ROOM, null, false, true, false, "in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ROOM;
			}
		}
	},
	
	ZARANIX_GF_GARDEN("Garden", "dominion/zaranixHome/garden", BaseColor.GREEN, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.GARDEN, null, false, true, false, "in Zaranix's garden"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN;
			}
		}
	},
	
	ZARANIX_GF_GARDEN_ENTRY("Garden", "dominion/zaranixHome/entranceHall", BaseColor.GREEN, Color.MAP_BACKGROUND, ZaranixHomeGroundFloor.GARDEN_ENTRY, null, false, true, false, "in Zaranix's garden"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ENTRY;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ENTRY;
			}
		}
	},
	
	// First floor:
	
	ZARANIX_FF_CORRIDOR("Corridor", null, BaseColor.GREY, Color.MAP_BACKGROUND, ZaranixHomeFirstFloor.CORRIDOR, null, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR;
			}
		}
	},
	
	ZARANIX_FF_STAIRS("Staircase", "dominion/zaranixHome/stairsDown", BaseColor.RED, Color.MAP_BACKGROUND, ZaranixHomeFirstFloor.STAIRS, null, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeFirstFloor.STAIRS;
			}
		}
	},
	
	ZARANIX_FF_OFFICE("Zaranix's Room", "dominion/zaranixHome/roomZaranix", BaseColor.PINK_DEEP, Color.MAP_BACKGROUND, ZaranixHomeFirstFloor.ZARANIX_ROOM, null, true, true, false, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ZARANIX_ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ZARANIX_ROOM;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	},
	
	ZARANIX_FF_ROOM("Room", "dominion/zaranixHome/room", BaseColor.GREY, Color.MAP_BACKGROUND, ZaranixHomeFirstFloor.ROOM, null, false, true, false, "in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ROOM;
			}
		}
	},
	
	ZARANIX_FF_MAID("Corridor", null, BaseColor.RED, Color.MAP_BACKGROUND, ZaranixHomeFirstFloor.CORRIDOR_MAID, null, true, true, false, "in Zaranix's Home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	},
	
	// Angel's Kiss:

	ANGELS_KISS_CORRIDOR("Corridor", null, BaseColor.GREY, Color.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_CORRIDOR, null, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_ENTRANCE("Entrance Hall", "dominion/angelsKiss/entrance", BaseColor.RED, Color.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_ENTRANCE, null, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_STAIRCASE_UP("Entrance Hall", "dominion/angelsKiss/stairsUp", BaseColor.GREEN_LIGHT, Color.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_STAIRS_UP, null, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_STAIRCASE_DOWN("Entrance Hall", "dominion/angelsKiss/stairsDown", BaseColor.RED, Color.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_STAIRS_DOWN, null, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_BEDROOM("Bedroom", "dominion/angelsKiss/bedroom", BaseColor.PINK, Color.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_BEDROOM, null, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_BEDROOM_BUNNY("Bunny's Bedroom", "dominion/angelsKiss/bedroomBunny", BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_BEDROOM_BUNNY, null, false, true, false, "in Bunny's Bedroom"),
	ANGELS_KISS_BEDROOM_LOPPY("Loppy's Bedroom", "dominion/angelsKiss/bedroomLoppy", BaseColor.PURPLE, Color.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_BEDROOM_LOPPY, null, false, true, false, "in Loppy's Bedroom"),
	ANGELS_KISS_OFFICE("Angel's Office", "dominion/angelsKiss/office", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_OFFICE, null, false, true, false, "in Angel's Office"),
	
	// Shopping arcade:
	
	SHOPPING_ARCADE_PATH("Arcade", null, BaseColor.BLACK, Color.MAP_BACKGROUND, ShoppingArcadeDialogue.ARCADE, null, false, true, true, "in the Shopping Arcade") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWDS, PopulationDensity.DENSE, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
		}
	},

	SHOPPING_ARCADE_GENERIC_SHOP("Shop", "dominion/shoppingArcade/genericShop", BaseColor.BLACK, Color.MAP_BACKGROUND, ShoppingArcadeDialogue.GENERIC_SHOP, null, false, true, true, "in the Shopping Arcade") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.SHOPPERS, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
		}
	},
	
	SHOPPING_ARCADE_RALPHS_SHOP("Ralph's Snacks", "dominion/shoppingArcade/ralphShop", BaseColor.TEAL, Color.MAP_BACKGROUND, RalphsSnacks.EXTERIOR, null, false, true, true, "in his store"),
	
	SHOPPING_ARCADE_NYANS_SHOP("Nyan's Clothing Emporium", "dominion/shoppingArcade/nyanShop", BaseColor.ROSE, Color.MAP_BACKGROUND, ClothingEmporium.EXTERIOR, null, false, true, true, "in her store"),
	
	SHOPPING_ARCADE_VICKYS_SHOP("Arcane Arts", "dominion/shoppingArcade/vickyShop", BaseColor.MAGENTA, Color.MAP_BACKGROUND, ArcaneArts.EXTERIOR, null, false, true, true, "in her store"),

	SHOPPING_ARCADE_KATES_SHOP("Succubi's Secrets", "dominion/shoppingArcade/kateShop", BaseColor.PINK, Color.MAP_BACKGROUND, SuccubisSecrets.EXTERIOR, null, false, true, true, "in her beauty salon"),

	SHOPPING_ARCADE_ASHLEYS_SHOP("Dream Lover", "dominion/shoppingArcade/ashleyShop", BaseColor.LILAC_LIGHT, Color.MAP_BACKGROUND, DreamLover.EXTERIOR, null, false, true, true, "in their store"),
	
	SHOPPING_ARCADE_SUPPLIER_DEPOT("Supplier Depot", "dominion/shoppingArcade/supplierDepot", BaseColor.CRIMSON, Color.MAP_BACKGROUND, SupplierDepot.EXTERIOR, null, false, true, true, "in the supplier depot") {
		@Override
		public BaseColor getColor() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return BaseColor.GREEN;
			} else {
				return BaseColor.CRIMSON;
			}
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return PlaceType.getSVGOverride(this.toString(), "dominion/shoppingArcade/supplierDepot", Color.BASE_GREEN);
			} else {
				return SVGString;
			}
		}
	},
	
	SHOPPING_ARCADE_PIXS_GYM("Pix's Playground", "dominion/shoppingArcade/gym", BaseColor.GOLD, Color.MAP_BACKGROUND, PixsPlayground.GYM_EXTERIOR, null, false, true, true, "in her gym"),

	// Exits & entrances:
	SHOPPING_ARCADE_ENTRANCE("Exit", "dominion/shoppingArcade/exit", BaseColor.RED, Color.MAP_BACKGROUND, ShoppingArcadeDialogue.ENTRY, null, false, true, true, "in the Shopping Arcade"),
	
	// Supplier Depot:
	
	SUPPLIER_DEPOT_CORRIDOR("Corridor", null, BaseColor.BLACK, Color.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_CORRIDOR, null, false, true, false, "in the supplier depot"),
	
	SUPPLIER_DEPOT_ENTRANCE("Reception Area", "dominion/shoppingArcade/exit", BaseColor.GREY, Color.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_RECEPTION, null, false, true, false, "in the supplier depot") {
		@Override
		public boolean isPopulated() {
			return Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return PlaceType.getSVGOverride(this.toString(), "dominion/shoppingArcade/exit", Color.BASE_GREEN);
			} else {
				return SVGString;
			}
		}
	},
	
	SUPPLIER_DEPOT_STORAGE_ROOM("Storage Room", "dominion/shoppingArcade/supplierStorageRoom", BaseColor.ORANGE, Color.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_STORAGE_ROOM, null, false, true, false, "in the supplier depot"),
	
	SUPPLIER_DEPOT_OFFICE("Office", "dominion/shoppingArcade/supplierOffice", BaseColor.CRIMSON, Color.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_OFFICE, null, true, true, false, "in the supplier depot") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP);
		}
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.suppliersEncountered)) {
				return SupplierDepot.SUPPLIER_DEPOT_OFFICE_REPEAT;
				
			} else {
				return SupplierDepot.SUPPLIER_DEPOT_OFFICE;
			}
		}
	},
	
	// Slaver Alley:
	
	SLAVER_ALLEY_PATH("Alleyway", null, BaseColor.BLACK, Color.MAP_BACKGROUND, SlaverAlleyDialogue.ALLEYWAY, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWDS, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
		}
	},

	SLAVER_ALLEY_STALL_FEMALES("A Woman's Touch", "dominion/slaverAlley/marketStallFemale", BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_FEMALE, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},

	SLAVER_ALLEY_STALL_MALES("Iron & Steel", "dominion/slaverAlley/marketStallMale", BaseColor.BLUE_STEEL, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_MALE, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},

	SLAVER_ALLEY_STALL_ANAL("The Rear Entrance", "dominion/slaverAlley/marketStallAnal", BaseColor.ORANGE, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_ANAL, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},

	SLAVER_ALLEY_STALL_VAGINAL("White Lilies", "dominion/slaverAlley/marketStallVaginal", BaseColor.PINK, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_VAGINAL, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},

	SLAVER_ALLEY_STALL_ORAL("Viva Voce", "dominion/slaverAlley/marketStallOral", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_ORAL, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},

	SLAVER_ALLEY_STATUE("Statue of the Fallen Angel", "dominion/slaverAlley/marketStallStatue", BaseColor.BLACK, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_STATUE, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},

	SLAVER_ALLEY_MARKET_STALL_EXCLUSIVE("Zaibatsu Exchange", "dominion/slaverAlley/marketStallExclusive", BaseColor.GREY, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_EXCLUSIVE, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},

	SLAVER_ALLEY_MARKET_STALL_BULK("Royal Dominion Company", "dominion/slaverAlley/marketStallBulk", BaseColor.BLUE, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_BULK, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},
	
	SLAVER_ALLEY_CAFE("Cafe", "dominion/slaverAlley/marketStallCafe", BaseColor.BROWN, Color.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL_CAFE, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	},
	
	SLAVER_ALLEY_AUCTIONING_BLOCK("Auctioning Block", "dominion/slaverAlley/auctionBlock", BaseColor.GOLD, Color.MAP_BACKGROUND, SlaverAlleyDialogue.AUCTION_BLOCK, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
		}
	},

	SLAVER_ALLEY_PUBLIC_STOCKS("Public Stocks", "dominion/slaverAlley/stocks", BaseColor.TAN, Color.MAP_BACKGROUND, SlaverAlleyDialogue.PUBLIC_STOCKS, null, false, true, true, "in the stocks at Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
		}
	},

	SLAVER_ALLEY_SLAVERY_ADMINISTRATION("Slavery Administration", "dominion/slaverAlley/slaveryAdministration", BaseColor.PURPLE, Color.MAP_BACKGROUND, SlaverAlleyDialogue.SLAVERY_ADMINISTRATION_EXTERIOR, null, false, true, true, "in Slaver's Alley"){
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS);
		}
	},
	
	SLAVER_ALLEY_SCARLETTS_SHOP("Scarlett's Shop", "dominion/slaverAlley/scarlettsStall", BaseColor.CRIMSON, Color.MAP_BACKGROUND, ScarlettsShop.SCARLETTS_SHOP_EXTERIOR, null, false, true, true, "in Slaver's Alley"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) { // Scarlett owns the shop:
				return ScarlettsShop.SCARLETTS_SHOP_EXTERIOR;
				
			} else { // Alexa owns the shop:
				return ScarlettsShop.ALEXAS_SHOP_EXTERIOR;
			}
		}	
	},
	
	SLAVER_ALLEY_ENTRANCE("Gateway", "dominion/slaverAlley/exit", BaseColor.RED, Color.MAP_BACKGROUND, SlaverAlleyDialogue.GATEWAY, null, false, true, true, "in Slaver's Alley") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.DOMINION));
		}
	},
	
	// Nightlife:

	WATERING_HOLE_ENTRANCE("Entrance", "dominion/nightLife/exit", BaseColor.RED, Color.MAP_BACKGROUND, NightlifeDistrict.WATERING_HOLE_ENTRANCE, null, false, true, true, "in 'The Watering Hole'") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies().get(WorldType.NIGHTLIFE_CLUB));
		}
	},

	WATERING_HOLE_MAIN_AREA("The Watering Hole", null, BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, NightlifeDistrict.WATERING_HOLE_MAIN, null, false, true, true, "in 'The Watering Hole'") {
		@Override
		public Population getPopulation() {
			return WATERING_HOLE_ENTRANCE.getPopulation();
		}
	},

	WATERING_HOLE_SEATING_AREA("Seating Area", "dominion/nightLife/seatingArea", BaseColor.BLUE, Color.MAP_BACKGROUND, NightlifeDistrict.WATERING_HOLE_SEATING, null, false, true, true, "in 'The Watering Hole'") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.NIGHTLIFE_CLUB));
		}
	},

	WATERING_HOLE_VIP_AREA("VIP Area", "dominion/nightLife/vipArea", BaseColor.PURPLE, Color.MAP_BACKGROUND, NightlifeDistrict.WATERING_HOLE_VIP, null, false, true, true, "in 'The Watering Hole'") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.VIPS, PopulationDensity.SEVERAL,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.CAT_MORPH_LION, SubspeciesSpawnRarity.FOUR_COMMON),
							new Value<>(Subspecies.HORSE_MORPH_ZEBRA, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},

	WATERING_HOLE_BAR("Bar", "dominion/nightLife/bar", BaseColor.ORANGE, Color.MAP_BACKGROUND, NightlifeDistrict.WATERING_HOLE_BAR, null, false, true, true, "in 'The Watering Hole'") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies().get(WorldType.NIGHTLIFE_CLUB));
		}
	},

	WATERING_HOLE_DANCE_FLOOR("Dance Floor", "dominion/nightLife/danceFloor", BaseColor.PINK_DEEP, Color.MAP_BACKGROUND, NightlifeDistrict.WATERING_HOLE_DANCE_FLOOR, null, false, true, true, "in 'The Watering Hole'") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies().get(WorldType.NIGHTLIFE_CLUB));
		}
	},

	WATERING_HOLE_TOILETS("Toilets", "dominion/nightLife/toilets", BaseColor.GREEN_DARK, Color.MAP_BACKGROUND, NightlifeDistrict.WATERING_HOLE_TOILETS, null, false, true, true, "in the toilets of 'The Watering Hole'") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.PEOPLE, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies().get(WorldType.NIGHTLIFE_CLUB));
		}
	},
	
	// Submission:
	
	SUBMISSION_WALKWAYS("Walkways", null, BaseColor.BLACK, Color.MAP_BACKGROUND, SubmissionGenericPlaces.WALKWAYS, null, false, true, true, "in Submission") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWDS, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.SUBMISSION));
		}
	},

	SUBMISSION_TUNNELS("Tunnels", "submission/tunnelsIcon", BaseColor.BLACK, Color.MAP_BACKGROUND, SubmissionGenericPlaces.TUNNEL, Encounter.SUBMISSION_TUNNELS, true, true, true, "in Submission"),

	SUBMISSION_BAT_CAVERNS("Bat Caverns", "submission/batCaverns", BaseColor.BLUE, Color.MAP_BACKGROUND, SubmissionGenericPlaces.BAT_CAVERNS, null, false, true, true, "in Submission"), // Insert batman reference here.
	
	SUBMISSION_RAT_WARREN("Rat Warren", "submission/ratWarren", BaseColor.BROWN_DARK, Color.MAP_BACKGROUND, SubmissionGenericPlaces.RAT_WARREN, null, false, true, true, "in Submission"),
	
	SUBMISSION_GAMBLING_DEN("Gambling Den", "submission/gamblingDen", BaseColor.GOLD, Color.MAP_BACKGROUND, SubmissionGenericPlaces.GAMBLING_DEN, null, false, true, true, "in Submission"),
	
	SUBMISSION_LILIN_PALACE("Lyssieth's Palace", "submission/lilinPalace", BaseColor.PURPLE, Color.MAP_BACKGROUND_DARK, SubmissionGenericPlaces.LILIN_PALACE, null, false, true, true, "in Submission"),
	SUBMISSION_LILIN_PALACE_GATE("Lyssieth's Palace Gate", "submission/gate", BaseColor.PURPLE_LIGHT, Color.MAP_BACKGROUND_DARK, SubmissionGenericPlaces.LILIN_PALACE_GATE, null, false, true, true, "in Submission") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.GUARDS, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},
	SUBMISSION_LILIN_PALACE_CAVERN("Cavern", null, BaseColor.GREY, Color.MAP_BACKGROUND_DARK, SubmissionGenericPlaces.LILIN_PALACE_CAVERN, null, false, true, true, "in Submission"),

	/**This fortress is <b>Alpha Imps</b>.*/
	SUBMISSION_IMP_FORTRESS_ALPHA("Imp Fortress", "submission/impFortress1", BaseColor.CRIMSON, Color.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_ALPHA, null, true, true, true, "in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				return PlaceType.getSVGOverride(this.toString(), "submission/impFortress1", Color.BASE_GREEN_LIGHT);
			}
			return PlaceType.getSVGOverride(this.toString(), "submission/impFortress1", Color.BASE_CRIMSON);
		}
	},
	/**Associated fortress is <b>Alpha Imps</b>.*/
	SUBMISSION_IMP_TUNNELS_ALPHA("Imp Tunnels", "submission/impTunnels1Icon", BaseColor.RED, Color.MAP_BACKGROUND, SubmissionGenericPlaces.TUNNEL, Encounter.SUBMISSION_TUNNELS, true, true, true, "in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				return PlaceType.getSVGOverride(this.toString(), "submission/impTunnels1Icon", Color.BASE_GREY);
			}
			return PlaceType.getSVGOverride(this.toString(), "submission/impTunnels1Icon", Color.BASE_RED);
		}
	},

	FORTRESS_ALPHA_ENTRANCE("Gateway", "submission/impFortress/entrance", BaseColor.RED, Color.MAP_BACKGROUND, ImpFortressDialogue.ENTRANCE, null, false, true, false, "in the Alpha Imp Fortress"),
	FORTRESS_ALPHA_COURTYARD("Courtyard", null, BaseColor.BLACK, Color.MAP_BACKGROUND, ImpFortressDialogue.COURTYARD, null, false, true, false, "in the Alpha Imp Fortress"),
	FORTRESS_ALPHA_KEEP("Keep", "submission/impFortress/keep", BaseColor.CRIMSON, Color.MAP_BACKGROUND, ImpFortressDialogue.KEEP, null, true, true, true, "in the Alpha Imp Fortress"),

	SUBMISSION_IMP_FORTRESS_DEMON("Imp Citadel", "submission/impFortress2", BaseColor.PURPLE, Color.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_DEMON, null, true, true, true, "in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return PlaceType.getSVGOverride(this.toString(), "submission/impFortress2", Color.BASE_GREEN_LIGHT);
			}
			return PlaceType.getSVGOverride(this.toString(), "submission/impFortress2", Color.BASE_PURPLE_DARK);
		}
	},
	SUBMISSION_IMP_TUNNELS_DEMON("Imp Tunnels", "submission/impTunnels2Icon", BaseColor.PURPLE, Color.MAP_BACKGROUND, SubmissionGenericPlaces.TUNNEL, Encounter.SUBMISSION_TUNNELS, true, true, true, "in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return PlaceType.getSVGOverride(this.toString(), "submission/impTunnels2Icon", Color.BASE_GREY);
			}
			return PlaceType.getSVGOverride(this.toString(), "submission/impTunnels2Icon", Color.BASE_PURPLE);
		}
	},
	
	FORTRESS_DEMON_ENTRANCE("Gateway", "submission/impFortress/entrance", BaseColor.RED, Color.MAP_BACKGROUND, ImpCitadelDialogue.ENTRANCE, null, false, true, false, "in the Dark Siren's citadel") {
		@Override
		public Population getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return null;
			}
			return new Population(PopulationType.GUARDS, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.TWO_RARE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},
	FORTRESS_DEMON_COURTYARD("Courtyard", null, BaseColor.BLACK, Color.MAP_BACKGROUND, ImpCitadelDialogue.COURTYARD, null, false, true, false, "in the Dark Siren's citadel") {
		@Override
		public Population getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
	},
	FORTRESS_DEMON_WELL("Well", "submission/impFortress/well", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, ImpCitadelDialogue.WELL, null, false, true, false, "in the Dark Siren's citadel") {
		@Override
		public Population getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
	},
	FORTRESS_DEMON_KEEP("Keep", "submission/impFortress/keep", BaseColor.PURPLE, Color.MAP_BACKGROUND, ImpCitadelDialogue.KEEP, null, true, true, false, "in the Dark Siren's citadel") {
		@Override
		public boolean isDangerous() {
			return Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
		}
	},
	FORTRESS_DEMON_CELLS("Cells", "submission/impFortress/cells", BaseColor.TEAL, Color.MAP_BACKGROUND, ImpCitadelDialogue.CELLS, null, false, true, false, "in the Dark Siren's citadel") {
		@Override
		public Population getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return null;
			}
			return new Population(PopulationType.GUARDS, PopulationDensity.FEW,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.TWO_RARE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},
	FORTRESS_LAB("Laboratory", "submission/impFortress/laboratory", BaseColor.ORANGE, Color.MAP_BACKGROUND, ImpCitadelDialogue.LABORATORY, null, false, true, false, "in the Dark Siren's citadel") {
		@Override
		public Population getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
	},
	FORTRESS_DEMON_TREASURY("Treasury", "submission/impFortress/treasury", BaseColor.GOLD, Color.MAP_BACKGROUND, ImpCitadelDialogue.TREASURY, null, false, true, false, "in the Dark Siren's citadel") {
		@Override
		public Population getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
	},
	
	/**This fortress is <b>Female Imp Seducers</b>.*/
	SUBMISSION_IMP_FORTRESS_FEMALES("Imp Fortress", "submission/impFortress3", BaseColor.PINK, Color.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_FEMALES, null, true, true, true, "in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				return PlaceType.getSVGOverride(this.toString(), "submission/impFortress3", Color.BASE_GREEN_LIGHT);
			}
			return PlaceType.getSVGOverride(this.toString(), "submission/impFortress3", Color.BASE_PINK);
		}
	},
	/**Associated fortress is <b>Female Imp Seducers</b>.*/
	SUBMISSION_IMP_TUNNELS_FEMALES("Imp Tunnels", "submission/impTunnels3Icon", BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND, SubmissionGenericPlaces.TUNNEL, Encounter.SUBMISSION_TUNNELS, true, true, true, "in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				return PlaceType.getSVGOverride(this.toString(), "submission/impTunnels3Icon", Color.BASE_GREY);
			}
			return PlaceType.getSVGOverride(this.toString(), "submission/impTunnels3Icon", Color.BASE_PINK_LIGHT);
		}
	},

	FORTRESS_FEMALES_ENTRANCE("Gateway", "submission/impFortress/entrance", BaseColor.RED, Color.MAP_BACKGROUND, ImpFortressDialogue.ENTRANCE, null, false, true, false, "in the Female Imp Fortress"),
	FORTRESS_FEMALES_COURTYARD("Courtyard", null, BaseColor.BLACK, Color.MAP_BACKGROUND, ImpFortressDialogue.COURTYARD, null, false, true, false, "in the Female Imp Fortress"),
	FORTRESS_FEMALES_KEEP("Keep", "submission/impFortress/keep", BaseColor.PINK, Color.MAP_BACKGROUND, ImpFortressDialogue.KEEP, null, true, true, true, "in the Female Imp Fortress"),

	/**This fortress is <b>Male Imp Brawlers</b>.*/
	SUBMISSION_IMP_FORTRESS_MALES("Imp Fortress", "submission/impFortress4", BaseColor.BLUE, Color.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_MALES, null, true, true, true, "in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				return PlaceType.getSVGOverride(this.toString(), "submission/impFortress4", Color.BASE_GREEN_LIGHT);
			}
			return PlaceType.getSVGOverride(this.toString(), "submission/impFortress4", Color.BASE_BLUE);
		}
	},
	/**Associated fortress is <b>Male Imp Brawlers</b>.*/
	SUBMISSION_IMP_TUNNELS_MALES("Imp Tunnels", "submission/impTunnels4Icon", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, SubmissionGenericPlaces.TUNNEL, Encounter.SUBMISSION_TUNNELS, true, true, true, "in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				return PlaceType.getSVGOverride(this.toString(), "submission/impTunnels4Icon", Color.BASE_GREY);
			}
			return PlaceType.getSVGOverride(this.toString(), "submission/impTunnels4Icon", Color.BASE_BLUE_LIGHT);
		}
	},

	FORTRESS_MALES_ENTRANCE("Gateway", "submission/impFortress/entrance", BaseColor.RED, Color.MAP_BACKGROUND, ImpFortressDialogue.ENTRANCE, null, false, true, false, "in the Male Imp Fortress"),
	FORTRESS_MALES_COURTYARD("Courtyard", null, BaseColor.BLACK, Color.MAP_BACKGROUND, ImpFortressDialogue.COURTYARD, null, false, true, false, "in the Male Imp Fortress"),
	FORTRESS_MALES_KEEP("Keep", "submission/impFortress/keep", BaseColor.BLUE, Color.MAP_BACKGROUND, ImpFortressDialogue.KEEP, null, true, true, true, "in the Male Imp Fortress"),
	

	SUBMISSION_ENTRANCE("Enforcer Checkpoint", "submission/submissionExit", BaseColor.BROWN, Color.MAP_BACKGROUND, SubmissionGenericPlaces.SEWER_ENTRANCE, null, false, true, true, "in Submission") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.ENFORCERS, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies().get(WorldType.SUBMISSION));
		}
	},
	
	// Lyssieth palace:
	
	LYSSIETH_PALACE_CORRIDOR("Corridor", null, BaseColor.GREY, Color.MAP_BACKGROUND, LyssiethPalaceDialogue.CORRIDOR, null, false, true, false, "in Lyssieth's Palace") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.MAIDS, PopulationDensity.COUPLE,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TWO_RARE),
							new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.FOUR_COMMON)));
		}
	},
	LYSSIETH_PALACE_WINDOWS("Windows", null, BaseColor.GREY_DARK, Color.MAP_BACKGROUND_DARK, LyssiethPalaceDialogue.WINDOWS, null, false, true, false, "in Lyssieth's Palace") {
		@Override
		public Population getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	},
	LYSSIETH_PALACE_ENTRANCE("Entrance", "submission/lyssiethsPalace/entrance", BaseColor.RED, Color.MAP_BACKGROUND, LyssiethPalaceDialogue.ENTRANCE, null, false, true, false, "in Lyssieth's Palace") {
		@Override
		public Population getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	},
	LYSSIETH_PALACE_ROOM("Room", "submission/lyssiethsPalace/lounge", BaseColor.PINK, Color.MAP_BACKGROUND, LyssiethPalaceDialogue.ROOM, null, false, true, false, "in Lyssieth's Palace"),
	LYSSIETH_PALACE_HALL("Hall", "submission/lyssiethsPalace/throneRoom", BaseColor.ORANGE, Color.MAP_BACKGROUND, LyssiethPalaceDialogue.HALL, null, false, true, false, "in Lyssieth's Palace"),
	LYSSIETH_PALACE_OFFICE("Lyssieth's Office", "submission/lyssiethsPalace/office", BaseColor.GOLD, Color.MAP_BACKGROUND, LyssiethPalaceDialogue.LYSSIETH_OFFICE_ENTER, null, false, true, false, "in Lyssieth's Palace"),
	LYSSIETH_PALACE_SIREN_OFFICE("Meraxis's Office", "submission/lyssiethsPalace/officeSiren", BaseColor.CRIMSON, Color.MAP_BACKGROUND, LyssiethPalaceDialogue.SIREN_OFFICE, null, false, true, false, "in Lyssieth's Palace"),
	
	LYSSIETH_PALACE_STAIRS_1("Staircase", "submission/lyssiethsPalace/staircase", BaseColor.GREEN, Color.MAP_BACKGROUND, LyssiethPalaceDialogue.STAIRCASE, null, false, true, false, "in Lyssieth's Palace") {
		@Override
		public Population getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	},
	LYSSIETH_PALACE_STAIRS_2("Staircase", "submission/lyssiethsPalace/staircase", BaseColor.GREEN, Color.MAP_BACKGROUND, LyssiethPalaceDialogue.STAIRCASE, null, false, true, false, "in Lyssieth's Palace") {
		@Override
		public Population getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	},
	
	// Bat caverns:

	BAT_CAVERN_ENTRANCE("Winding Staircase", "submission/batCaverns/cavernStaircase", BaseColor.GREEN, Color.MAP_BACKGROUND, BatCaverns.STAIRCASE, null, false, true, true, "in the Bat Caverns"),
	BAT_CAVERN_DARK("Dark Cavern", null, BaseColor.GREY, Color.MAP_BACKGROUND, BatCaverns.CAVERN_DARK, Encounter.BAT_CAVERN, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_LIGHT("Bioluminescent Cavern", "submission/batCaverns/cavernBioluminescent", BaseColor.AQUA, Color.MAP_BACKGROUND, BatCaverns.CAVERN_LIGHT, Encounter.BAT_CAVERN, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_RIVER("Underground River", "submission/batCaverns/cavernRiver", BaseColor.BLUE, Color.MAP_BACKGROUND, BatCaverns.RIVER, Encounter.BAT_CAVERN, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_RIVER_CROSSING("Mushroom Bridge", "submission/batCaverns/cavernBridge", BaseColor.TEAL, Color.MAP_BACKGROUND, BatCaverns.RIVER_BRIDGE, Encounter.BAT_CAVERN, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_RIVER_END("Underground River End", "submission/batCaverns/cavernRiverEnd", BaseColor.BLUE_DARK, Color.MAP_BACKGROUND, BatCaverns.RIVER_END, Encounter.BAT_CAVERN, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_SLIME_QUEEN_LAIR("Slime Lake", "submission/batCaverns/cavernLake", BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND, BatCaverns.SLIME_LAKE, Encounter.BAT_CAVERN, true, true, true, "beside Slime Lake"),
	

	SLIME_QUEENS_LAIR_CORRIDOR("Corridor", null, BaseColor.GREY, Color.MAP_BACKGROUND, SlimeQueensLair.CORRIDOR, null, false, true, true, "in the Slime Queen's tower"),
	SLIME_QUEENS_LAIR_ENTRANCE("Entrance Hall", "submission/slimeQueensLair/entranceHall", BaseColor.RED, Color.MAP_BACKGROUND, SlimeQueensLair.ENTRANCE, null, false, true, true, "in the Slime Queen's tower"),
	SLIME_QUEENS_LAIR_STAIRS_UP("Spiral Staircase", "submission/slimeQueensLair/staircase", BaseColor.GREEN, Color.MAP_BACKGROUND, SlimeQueensLair.STAIRCASE_UP, null, false, true, true, "in the Slime Queen's tower"),
	SLIME_QUEENS_LAIR_STAIRS_DOWN("Spiral Staircase", "submission/slimeQueensLair/staircase", BaseColor.RED, Color.MAP_BACKGROUND, SlimeQueensLair.STAIRCASE_DOWN, null, false, true, true, "in the Slime Queen's tower"),
	SLIME_QUEENS_LAIR_ROOM("Bedroom", "submission/slimeQueensLair/room", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, SlimeQueensLair.ROOM, null, false, true, true, "in the Slime Queen's tower"),
	SLIME_QUEENS_LAIR_STORAGE_VATS("Distillery", "submission/slimeQueensLair/storageVats", BaseColor.ORANGE, Color.MAP_BACKGROUND, SlimeQueensLair.STORAGE_VATS, null, false, true, false, "in the Slime Queen's tower") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			for(int i=0; i<15; i++) {
				inventory.addItem(AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_SLIME_QUENCHER));
			}
			for(int i=0; i<5; i++) {
				inventory.addItem(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_SLIME));
			}
		}
	},
	SLIME_QUEENS_LAIR_ENTRANCE_GUARDS("Guard Post", "submission/slimeQueensLair/guards", BaseColor.RED, Color.MAP_BACKGROUND, SlimeQueensLair.GUARD_POST, null, true, true, true, "in the Slime Queen's tower"),
	SLIME_QUEENS_LAIR_ROYAL_GUARD("Royal Guard Post", "submission/slimeQueensLair/royalGuards", BaseColor.PURPLE, Color.MAP_BACKGROUND, SlimeQueensLair.ROYAL_GUARD_POST, null, true, true, true, "in the Slime Queen's tower"),
	SLIME_QUEENS_LAIR_SLIME_QUEEN("Bed Chamber", "submission/slimeQueensLair/bedChamber", BaseColor.PINK, Color.MAP_BACKGROUND, SlimeQueensLair.BED_CHAMBER, null, false, true, true, "in the Slime Queen's tower"),
	
	// Gambling Den:
	
	GAMBLING_DEN_CORRIDOR("Gambling Den", null, BaseColor.BLACK, Color.MAP_BACKGROUND, GamblingDenDialogue.CORRIDOR, null, false, true, true, "in the Gambling Den") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.SUBMISSION));
		}
	},

	GAMBLING_DEN_ENTRANCE("Entrance", "submission/gamblingDen/entrance", BaseColor.GREEN, Color.MAP_BACKGROUND, GamblingDenDialogue.ENTRANCE, null, false, true, true, "in the Gambling Den") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.SUBMISSION));
		}
	},
	
	GAMBLING_DEN_TRADER("Trader", "submission/gamblingDen/trader", BaseColor.TEAL, Color.MAP_BACKGROUND, GamblingDenDialogue.TRADER, null, false, true, true, "in the Gambling Den"),
	GAMBLING_DEN_GAMBLING("Dice Poker Tables", "submission/gamblingDen/gambling", BaseColor.GOLD, Color.MAP_BACKGROUND, GamblingDenDialogue.GAMBLING, null, false, true, true, "in the Gambling Den") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.SUBMISSION));
		}
	},
	GAMBLING_DEN_PREGNANCY_ROULETTE("Pregnancy Roulette", "submission/gamblingDen/referee", BaseColor.PINK, Color.MAP_BACKGROUND, GamblingDenDialogue.PREGNANCY_ROULETTE, null, false, true, true, "in the Gambling Den") {
		@Override
		public Population getPopulation() {
			return new Population(PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies().get(WorldType.SUBMISSION));
		}
	},
	GAMBLING_DEN_PREGNANCY("Breeding Stalls", "submission/gamblingDen/normalPregnancy", BaseColor.BLUE_LIGHT, Color.MAP_BACKGROUND, GamblingDenDialogue.PREGNANCY_ROULETTE_MALE_STALLS, null, false, true, true, "in the Gambling Den"),
	GAMBLING_DEN_FUTA_PREGNANCY("Futa Breeding Stalls", "submission/gamblingDen/futaPregnancy", BaseColor.PINK_LIGHT, Color.MAP_BACKGROUND, GamblingDenDialogue.PREGNANCY_ROULETTE_FUTA_STALLS, null, false, true, true, "in the Gambling Den"),
	
	;

	private String name;
	private String worldPlaceDescription;
	protected String SVGString;
	private String colorString;
	private BaseColor color;
	private Color backgroundColor;
	protected DialogueNode dialogue;
	private Encounter encounterType;
	private boolean dangerous, stormImmune, itemsDisappear;
	private String virginityLossDescription;

	private static Map<String, String> SVGOverrides = new HashMap<>(); 
	
	/**
	 * Only to be used for world map places.
	 */
	private PlaceType(String name,
			String worldPlaceDescription,
			String color,
			Encounter encounterType,
			boolean dangerous,
			boolean stormImmune,
			boolean itemsDisappear,
			String virginityLossDescription) {
		
		this.name = name;
		this.colorString = color;
		this.worldPlaceDescription = worldPlaceDescription;
		this.encounterType = encounterType;
		this.dangerous = dangerous;
		this.stormImmune = stormImmune;
		this.itemsDisappear = itemsDisappear;
		this.virginityLossDescription = virginityLossDescription;
		SVGString = null;
	}
	
	private PlaceType(String name,
			String SVGPath,
			BaseColor color,
			Color backgroundColor,
			DialogueNode dialogue,
			Encounter encounterType,
			boolean dangerous,
			boolean stormImmune,
			boolean itemsDisappear,
			String virginityLossDescription) {
		
		this.name = name;
		this.color = color;
		this.backgroundColor = backgroundColor;
		this.dialogue = dialogue;
		this.encounterType = encounterType;
		this.dangerous = dangerous;
		this.stormImmune = stormImmune;
		this.itemsDisappear = itemsDisappear;
		this.virginityLossDescription = virginityLossDescription;
		
		if(SVGPath!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + SVGPath + ".svg");
				if(is==null) {
					System.err.println("Error! PlaceType icon file does not exist (Trying to read from '"+SVGPath+"')! (Code 1)");
				}
				String s = Util.inputStreamToString(is);
				
				s = SvgUtil.colorReplacement(this.toString(), color, s);
				
				SVGString = s;
	
				is.close();
	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			SVGString = null;
		}
	}

	public String getName() {
		return name;
	}

	public String getWorldPlaceDescription() {
		return worldPlaceDescription;
	}

	public BaseColor getColor() {
		return color;
	}

	public String getColorString() {
		return colorString;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Encounter getEncounterType() {
		return encounterType;
	}

	public DialogueNode getDialogue(boolean withRandomEncounter) {
		return getDialogue(withRandomEncounter, false);
	}
	
	public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
		if (getEncounterType() != null && withRandomEncounter) {
			DialogueNode dn = getEncounterType().getRandomEncounter(forceEncounter);
			if (dn != null) {
				return dn;
			}
		}

		return dialogue;
	}
	
	public Population getPopulation() {
		return null;
	}
	
	public boolean isPopulated() {
		return getPopulation()!=null && !getPopulation().getSpecies().isEmpty();
	}

	public boolean isLand() {
		return true;
	}
	
	public boolean isDangerous() {
		return dangerous;
	}

	public boolean isStormImmune() {
		return stormImmune;
	}
	
	public boolean isItemsDisappear() {
		return itemsDisappear;
	}
	
	private static String getSVGOverride(String id, String pathName, Color color) {
		if(!SVGOverrides.keySet().contains(pathName+color)) {
			try {
				InputStream is = color.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + pathName + ".svg");
				if(is==null) {
					System.err.println("Error! PlaceType icon file does not exist (Trying to read from '"+pathName+"')! (Code 2)");
				}
				String s = Util.inputStreamToString(is);

				s = SvgUtil.colorReplacement(id, color, s);
				
				SVGOverrides.put(pathName+color, s);
	
				is.close();
	
			} catch (Exception e1) {
				System.err.println("Eeeeeek! PlaceType.getSVGOverride()");
				e1.printStackTrace();
				return "";
			}
		}
		
		return SVGOverrides.get(pathName+color);
	}
	
	public String getSVGString(Set<PlaceUpgrade> upgrades) {
		return SVGString;
	}
	
	public void applyInventoryInit(CharacterInventory inventory) {
		
	}
	
	// For determining where this place should be placed:
	
	public Bearing getBearing() {
		return null;
	}
	
	public WorldType getParentWorldType() {
		return null;
	}
	
	public PlaceType getParentPlaceType() {
		return null;
	}
	
	public EntranceType getParentAlignment() {
		return null;
	}
	
	public String getPlaceNameAppendFormat(int count) {
		return "";
	}
	
	public boolean isAbleToBeUpgraded() {
		return false;
	}
	
	public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
		return new ArrayList<>();
	}
	
	public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
		return new ArrayList<>();
	}

	public static ArrayList<PlaceUpgrade> getAvailableLilayaRoomPlaceUpgrades(Set<PlaceUpgrade> upgrades) {
		if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			return PlaceUpgrade.getGuestRoomUpgrades();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
			return PlaceUpgrade.getSlaveQuartersUpgradesSingle();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
			return PlaceUpgrade.getSlaveQuartersUpgradesDouble();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
			return PlaceUpgrade.getMilkingUpgrades();
		}
		
		return PlaceUpgrade.getCoreRoomUpgrades();
	}
	
	public String getLilayaRoomSVGString(Set<PlaceUpgrade> upgrades) {
		if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			return PlaceType.getSVGOverride(this.toString(), "dominion/lilayasHome/roomGuest", Color.BASE_GREEN_LIGHT);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
			return PlaceType.getSVGOverride(this.toString(), "dominion/lilayasHome/roomSlave", Color.BASE_CRIMSON);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
			return PlaceType.getSVGOverride(this.toString(), "dominion/lilayasHome/roomMilking", Color.BASE_ORANGE);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
			return PlaceType.getSVGOverride(this.toString(), "dominion/lilayasHome/roomSlaveDouble", Color.BASE_MAGENTA);
			
		} else {
			return SVGString;
		}
	}

	public String getVirginityLossDescription() {
		return virginityLossDescription;
	}
}
