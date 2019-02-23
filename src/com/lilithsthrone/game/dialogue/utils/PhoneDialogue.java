package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.Litter;
import com.lilithsthrone.game.character.PregnancyPossibility;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.ClothingRarityComparator;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.ItemRarityComparator;
import com.lilithsthrone.utils.TreeNode;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.WeaponRarityComparator;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia, tukaima
 */
public class PhoneDialogue {

	private static List<GameCharacter> charactersEncountered;

	private static StringBuilder journalSB;
	public static final DialogueNode MENU = new DialogueNode("Phone home screen", "Phone", true) {

		@Override
		public String getContent() {
			return RenderingEngine.ENGINE.getFullMap(Main.game.getPlayer().getWorldLocation(), true)
					+"<p>You pull out your phone and tap in the unlock code.</p>"
					+ (Main.game.isInNewWorld()
							?"<p>"
								+"Using your powerful aura, you've managed to figure out a way to channel the arcane into charging the battery of your phone, although considering that it's the only one in this world,"
									+ " it's not much use for calling anyone."
								+ " Instead, you're using it as a way to store information about things you've discovered in this strange new world."
							+ "</p>"
							:"");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						(Main.game.getPlayer().isMainQuestUpdated() || Main.game.getPlayer().isSideQuestUpdated() || Main.game.getPlayer().isRelationshipQuestUpdated())
							?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Quests</span>"
							:"Quests",
						"Open your planner to view your current quests.", PLANNER_MAIN){
					@Override
					public void effects() {
						Main.game.getPlayer().setMainQuestUpdated(false);
					}
				};

			} else if (index == 2) {
				return new Response(
						Main.getProperties().hasValue(PropertyValue.levelUpHightlight)
							? "<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Perks</span>"
							:"Perks",
						"View your character page.", CHARACTER_LEVEL_UP) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
					}
				};

			} else if (index == 3) {
				return new Response("Spells", "View your spells page.", CHARACTER_SPELLS_EARTH);

			} else if (index == 4) {
				return new Response("Fetishes", "View your fetishes page.", CHARACTER_FETISHES);

			} else if (index == 5) {
				return new Response("Stats", "Take a detailed look at your stats.", CHARACTER_STATS);

			} else if (index == 6) {
				return new Response("Selfie", "Take a selfie to get a good view of yourself.", CHARACTER_APPEARANCE);

			} else if (index == 7) {
				if(Main.game.getPlayer().getCharactersEncountered().isEmpty()) {
					return new Response("Contacts", "You haven't met anyone yet!", null);
				} else {
					return new Response("Contacts", "Even though you can't call anyone, on account of there being no phones in this world, you've still kept a record of all the people you've come into contact with.", CONTACTS) {
						@Override
						public void effects() {
							Main.game.getPlayer().sortCharactersEncountered();
							charactersEncountered = Main.game.getPlayer().getCharactersEncounteredAsGameCharacters();
						}
					};
				}

			} else if (index == 8) {
				return new Response(
						(Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
								|| Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
								|| Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
								|| Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))
							? "<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Encyclopedia</span>"
							:"Encyclopedia",
						"Have a look at all the different items and races you've discovered so far.", ENCYCLOPEDIA){
					@Override
					public void effects() {
						resetContentForRaces();
					}
				};

			} else if (index == 9) {
				if(Main.game.getPlayer().isAbleToSelfTransform()) {
					return new Response("Transform",
							"Transform your body.",
							BodyChanging.BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer());
						}
					};
				} else {
					return new Response("Transform", Main.game.getPlayer().getUnableToTransformDescription(), null);
				}

			} else if (index == 10) {
				return new Response("Maps", "Take a look at maps of all the places you've visited.", MAP) {
					@Override
					public void effects() {
						worldTypeMap = Main.game.getPlayer().getWorldLocation();
					}
				};

			} else if (index == 0){
				return new ResponseEffectsOnly("Back", "Put your phone away."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode PLANNER_MAIN = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			// Main Quests:
			journalSB.append("<details open>"
					+ "<summary class='quest-title' style='color:" + QuestType.MAIN.getColor().toWebHexString() + ";'>" + QuestLine.MAIN.getName() + "</summary>");

			TreeNode<Quest> currentNode = QuestLine.MAIN.getQuestTree().getFirstNodeWithData(Main.game.getPlayer().getQuest(QuestLine.MAIN));

			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.MAIN)) {
				journalSB.append(getQuestBoxDiv(currentNode.getData(), false));
				currentNode = currentNode.getParent();
			}

			while(currentNode!=null) {
				journalSB.append(getQuestBoxDiv(currentNode.getData(), true));
				currentNode = currentNode.getParent();
			}
			journalSB.append("</details>");

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", null);

			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isSideQuestUpdated()
						?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
						:"Side quests"), "View your side quests.", PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};

			} else if (index == 3) {
				return new Response((Main.game.getPlayer().isRelationshipQuestUpdated()
						?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Relationship quests</span>"
						:"Relationship quests"), "View your relationship quests.", PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_SIDE = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean sideQuestsFound = false;

			// Side Quests:
			for (QuestLine questLine : Main.game.getPlayer().getQuests().keySet()) {
				if(questLine.getType()==QuestType.SIDE) {
					sideQuestsFound = true;

					TreeNode<Quest> currentNode = questLine.getQuestTree().getFirstNodeWithData(Main.game.getPlayer().getQuest(questLine));

					if (Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColor().getShades()[1] + ";'>"
									+ "Completed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(currentNode.getData(), true));

					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColor().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(currentNode.getData(), false));
					}

					currentNode = currentNode.getParent();

					while(currentNode!=null) {
						journalSB.append(getQuestBoxDiv(currentNode.getData(), true));
						currentNode = currentNode.getParent();
					}

					journalSB.append("</details>");
				}
			}

			if(!sideQuestsFound) {
				journalSB.append("<div class='subTitle'>You haven't got any side quests!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response("Side quests", "View your side quests.", null);
			} else if (index == 3) {
				return new Response((Main.game.getPlayer().isRelationshipQuestUpdated()
						?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Relationship quests</span>"
						:"Relationship quests"), "View your relationship quests.", PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_RELATIONSHIP = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean relationshipQuestFound = false;

			// Side Quests:
			for (QuestLine questLine : Main.game.getPlayer().getQuests().keySet()) {
				if(questLine.getType()==QuestType.RELATIONSHIP) {
					relationshipQuestFound = true;

					TreeNode<Quest> currentNode = questLine.getQuestTree().getFirstNodeWithData(Main.game.getPlayer().getQuest(questLine));

					if (Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColor().getShades()[1] + ";'>"
									+ "Completed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(currentNode.getData(), true));

					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColor().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(currentNode.getData(), false));
					}

					currentNode = currentNode.getParent();

					while(currentNode!=null) {
						journalSB.append(getQuestBoxDiv(currentNode.getData(), true));
						currentNode = currentNode.getParent();
					}

					journalSB.append("</details>");
				}
			}

			if(!relationshipQuestFound) {
				journalSB.append("<div class='subTitle'>You haven't got any relationship quests!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isSideQuestUpdated()
						?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
						:"Side quests"), "View your side quests.", PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
			} else if (index == 3) {
				return new Response("Relationship quests", "View your relationship quests.", null);
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getQuestBoxDiv(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "<div class='quest-box'>"
					+ "<h6 style='color:" + q.getQuestType().getColor().getShades()[1] + ";text-align:center;'>"
							+ "<b>Completed - "+ q.getName() + "</b>"
					+ "</h6>"
				+ "</div>";
		}

		if(completed) {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColor().getShades()[1] + ";text-align:center;'>"
							+ "<b>Completed - "+ q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='color:" + Color.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
						+ q.getCompletedDescription()
					+ "</p>"
				+ "</div>";
		} else {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColor().toWebHexString()+ "; text-align:center;'>"
						+ "<b>" + q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='text-align:center;'>"
						+ q.getDescription()
					+ "</p>"
				+ "</div>";
		}
	}

	private static String getLevelAndExperienceHTML(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "";
		}

		if (!completed) {
			if(q.getLevel() <= Main.game.getPlayer().getLevel() - 3) {
				return "<b class='quest-extra level' style='color:"+  Color.GENERIC_GOOD.toWebHexString() + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Color.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";

			} else if (q.getLevel() >= Main.game.getPlayer().getLevel() + 3) {
				return "<b class='quest-extra level' style='color:"+  Color.GENERIC_BAD.toWebHexString() + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Color.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";

			} else {
				return "<b class='quest-extra level'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Color.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
			}

		} else {
			return "<b class='quest-extra level' style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>Level " + q.getLevel() + "</b>"
					+ "<b class='quest-extra experience' style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
		}
	}


	public static final DialogueNode CHARACTER_APPEARANCE = new DialogueNode("Selfie picture", "Take a selfie", true) {

		@Override
		public String getContent() {
//			return Main.game.getPlayer().getBodyDescription();
			return Main.game.getPlayer().getCharacterInformationScreen();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_STATS = new DialogueNode("Character Stats", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(

				"<details>"
				+ "<summary>[style.boldExcellent(Stats Mechanics)]</summary>"
					+ "<p style='text-align:center;padding:margin:0;'>"
						+ "All derived stats start to have diminishing returns past the half-way point!<br/>"
						+ "<b>For example:</b><br/>"
						+ "<b>25</b> <b style='color:"+Color.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(25, 100)+"% damage</i><br/>"
						+ "<b>50</b> <b style='color:"+Color.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(50, 100)+"% damage</i><br/>"
						+ "<i>Past this point, there are diminishing returns.</i><br/>"
						+ "<b>60</b> <b style='color:"+Color.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(60, 100)+"% damage</i><br/>"
						+ "<b>80</b> <b style='color:"+Color.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(80, 100)+"% damage</i><br/>"
						+ "<b>100</b> <b style='color:"+Color.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(100, 100)+"% damage</i><br/>"
					+ "</p>"
				+ "</details>"

				+ "<div class='container-full-width'>"
					+ "<h4 style='color:"+Color.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Core Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_PHYSIQUE, "")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_ARCANE, "")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_CORRUPTION, "")

				+"</div>"
				+"<div class='container-full-width'>"

					+ "<h4 style='color:"+Color.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Misc. Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.FERTILITY,
							"Pregnancy Chance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.FERTILITY), Attribute.FERTILITY.getUpperLimit())+"%</b>")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.VIRILITY,
							"Impregnation Chance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.VIRILITY), Attribute.VIRILITY.getUpperLimit())+"%</b>")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER,
							"Spell Cost:<br/>"
							+ "<b>-"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.SPELL_COST_MODIFIER), Attribute.SPELL_COST_MODIFIER.getUpperLimit())+"%</b>")

					+ "<div class='container-full-width' style='text-align:center; background:"+Color.BACKGROUND_ALT.toWebHexString()+";'>"
						+ "<b style='color:"+Color.BASE_PINK_LIGHT.toWebHexString()+";'>Pregnancy calculation:</b> <i>"+GameCharacter.PREGNANCY_CALCULATION+"</i>"
					+ "</div>"

				+"</div>"
				+"<div class='container-full-width'>"

					+ "<h4 style='color:"+Color.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Combat Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_CHANCE,
							"Critical Hit Chance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_CHANCE), Attribute.CRITICAL_CHANCE.getUpperLimit())+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE,
							"Critical Hit Damage:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_DAMAGE), Attribute.CRITICAL_DAMAGE.getUpperLimit())+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DODGE_CHANCE,
							"Dodge Chance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DODGE_CHANCE), Attribute.DODGE_CHANCE.getUpperLimit())+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MISS_CHANCE,
							"Miss Chance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.MISS_CHANCE), Attribute.MISS_CHANCE.getUpperLimit())+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_UNARMED,
							"Unarmed Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED), Attribute.DAMAGE_UNARMED.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS,
							"Spell Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_SPELLS), Attribute.DAMAGE_SPELLS.getUpperLimit()))+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_MELEE_WEAPON,
							"Melee Weapon Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_MELEE_WEAPON), Attribute.DAMAGE_MELEE_WEAPON.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_RANGED_WEAPON,
							"Ranged Weapon Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_RANGED_WEAPON), Attribute.DAMAGE_RANGED_WEAPON.getUpperLimit()))+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL,
							"Physical Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_PHYSICAL), Attribute.DAMAGE_PHYSICAL.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL,
							"Physical Resistance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_PHYSICAL), Attribute.RESISTANCE_PHYSICAL.getUpperLimit())+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_FIRE,
							"Fire Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_FIRE), Attribute.DAMAGE_FIRE.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE,
							"Fire Resistance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_FIRE), Attribute.RESISTANCE_FIRE.getUpperLimit())+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ICE,
							"Ice Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ICE), Attribute.DAMAGE_ICE.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_ICE,
							"Ice Resistance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_ICE), Attribute.RESISTANCE_ICE.getUpperLimit())+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_POISON,
							"Poison Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_POISON), Attribute.DAMAGE_POISON.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_POISON,
							"Poison Resistance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_POISON), Attribute.RESISTANCE_POISON.getUpperLimit())+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LUST,
							"Lust Damage:<br/>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST), Attribute.DAMAGE_LUST.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_LUST,
							"Lust Resistance:<br/>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_LUST), Attribute.RESISTANCE_LUST.getUpperLimit())+"%</b>",
							true)

				+"</div>"
				+"<div class='container-full-width'>"
					+ "<h6 style='color:"+Color.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Racial values</h6>");

			List<Attribute> encounteredAttributes = new ArrayList<>();
			for(Subspecies subspecies : Subspecies.values()) {
				Attribute damageModifier = subspecies.getDamageMultiplier();
				if(!encounteredAttributes.contains(damageModifier)) {
					Attribute resistanceModifier = subspecies.getResistanceMultiplier();
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), damageModifier,
									Util.capitalizeSentence(damageModifier.getName())+":<br/>"
									+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(damageModifier), damageModifier.getUpperLimit()))+"%</b>",
									true)
							+ getAttributeBox(Main.game.getPlayer(), resistanceModifier,
									Util.capitalizeSentence(resistanceModifier.getName())+":<br/>"
									+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(resistanceModifier), resistanceModifier.getUpperLimit())+"%</b>",
									true));
					encounteredAttributes.add(damageModifier);
				}
			}

			UtilText.nodeContentSB.append("</div>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", null);

			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);

			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);

			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);

			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static String getBodyStatsPanel(GameCharacter character) {
		boolean knowsNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer());
		boolean knowsCrotchNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer());
		boolean knowsPenis = character.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer());
		boolean knowsVagina = character.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer());
		boolean knowsAnus = character.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer());

		return "<div class='container-full-width'>"
				+ "<h6 style='color:"+Color.TRANSFORMATION_GENERIC.toWebHexString()+"; text-align:center;'>Core Attributes</h6>"
				+ statHeader()
				+ statRow(Color.ANDROGYNOUS, "Femininity",
						Color.TEXT, String.valueOf(character.getFemininityValue()),
						character.getFemininity().getColor(), Util.capitalizeSentence(character.getFemininity().getName(false)),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Height (cm)",
						Color.TEXT, String.valueOf(character.getHeightValue()),
						character.getHeight().getColor(), Util.capitalizeSentence(character.getHeight().getDescriptor()),
						false)
				+ statRow(Color.MUSCLE_THREE, "Muscle Definition",
						Color.TEXT, String.valueOf(character.getMuscleValue()),
						character.getMuscle().getColor(), Util.capitalizeSentence(character.getMuscle().getName(false)),
						true)
				+ statRow(Color.BODY_SIZE_THREE, "Body Size",
						Color.TEXT, String.valueOf(character.getBodySizeValue()),
						character.getBodySize().getColor(), Util.capitalizeSentence(character.getBodySize().getName(false)),
						false)
				+ statRow(character.getBodyShape().toWebHexStringColor(), "Body Shape",
						Color.TEXT,
						"<b style='color:"+character.getMuscle().getColor().toWebHexString()+";'>"+character.getMuscleValue()+"</b>"
								+ " <b>|</b> <b style='color:"+character.getBodySize().getColor().toWebHexString()+";'>"+character.getBodySizeValue()+"</b>",
						character.getBodyShape().toWebHexStringColor(), Util.capitalizeSentence(character.getBodyShape().getName(false)),
						true)

				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Color.TRANSFORMATION_GREATER.toWebHexString()+"; text-align:center;'>Head & Throat Attributes</h6>"
//				+ statHeader()
				+ statRow(Color.TRANSFORMATION_GENERIC, "Hair Length (inches)",
						Color.TEXT, String.valueOf(character.getHairRawLengthValue()),
						character.getHairLength().getColor(), Util.capitalizeSentence(character.getHairLength().getDescriptor()),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Tongue length (inches)",
						Color.TEXT, String.valueOf(character.getTongueLengthValue()),
						Color.TRANSFORMATION_GENERIC, Util.capitalizeSentence(character.getTongueLength().getDescriptor()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Throat Wetness",
						Color.TEXT, String.valueOf(character.getFaceWetness().getValue()),
						Color.GENERIC_SEX, Util.capitalizeSentence(character.getFaceWetness().getDescriptor()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Throat Capacity (inches)",
						Color.TEXT, String.valueOf(character.getFaceRawCapacityValue()),
						Color.GENERIC_SEX, Util.capitalizeSentence(character.getFaceCapacity().getDescriptor()),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Throat Elasticity",
						Color.TEXT, String.valueOf(character.getFaceElasticity().getValue()),
						Color.GENERIC_SEX, Util.capitalizeSentence(character.getFaceElasticity().getDescriptor()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Throat Plasticity",
						Color.TEXT, String.valueOf(character.getFacePlasticity().getValue()),
						Color.GENERIC_SEX, Util.capitalizeSentence(character.getFacePlasticity().getDescriptor()),
						true)

				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Color.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Breast Attributes</h6>"
//				+ statHeader()
				+ statRow(Color.TRANSFORMATION_GENERIC, "Cup Size",
						Color.TEXT, String.valueOf(character.getBreastRawSizeValue()),
						Color.GENERIC_SEX, Util.capitalizeSentence(character.getBreastSize().getCupSizeName()),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Count",
						Color.TEXT, String.valueOf(character.getBreastRows()),
						Color.GENERIC_SEX, Util.capitalizeSentence(Util.capitalizeSentence(Util.intToString(character.getBreastRows()))+" pair"+(character.getBreastRows()==1?"":"s")),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Milk Storage (mL)",
						Color.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getBreastRawMilkStorageValue()),
						Color.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitalizeSentence(character.getBreastMilkStorage().getDescriptor()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Milk Regeneration (%/minute)",
						Color.TEXT, !knowsNipples?"Unknown":String.valueOf(Math.round((character.getBreastLactationRegeneration().getPercentageRegen()*100)*100)/100f),
						Color.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitalizeSentence(character.getBreastLactationRegeneration().getName()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Capacity (inches)",
						Color.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNippleRawCapacityValue()),
						Color.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitalizeSentence(character.getNippleCapacity().getDescriptor()),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Elasticity",
						Color.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNippleElasticity().getValue()),
						Color.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitalizeSentence(character.getNippleElasticity().getDescriptor()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Plasticity",
						Color.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNipplePlasticity().getValue()),
						Color.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitalizeSentence(character.getNipplePlasticity().getDescriptor()),
						true)

				+ (character.hasBreastsCrotch()
						?"<span style='height:16px;width:100%;float:left;'></span>"
							+ "<h6 style='color:"+Color.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>"+(character.getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs")+" Attributes</h6>"
							+ statRow(Color.TRANSFORMATION_GENERIC, "Size",
									Color.TEXT, !character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRawSizeValue()),
									Color.GENERIC_SEX,
									!character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples
										?"Unknown"
										:(character.getBreastCrotchShape()==BreastShape.UDDERS
											?Util.capitalizeSentence(character.getBreastCrotchSize().getDescriptor())
											:Util.capitalizeSentence(character.getBreastCrotchSize().getCupSizeName())),
									true)
							+ statRow(Color.TRANSFORMATION_GENERIC, "Count",
									Color.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRows()),
									Color.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitalizeSentence(Util.intToString(character.getBreastCrotchRows()))+" pair"+(character.getBreastCrotchRows()==1?"":"s"),
									true)
							+ statRow(Color.TRANSFORMATION_GENERIC, "Milk Storage (mL)",
									Color.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRawMilkStorageValue()),
									Color.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitalizeSentence(character.getBreastCrotchMilkStorage().getDescriptor()),
									false)
							+ statRow(Color.TRANSFORMATION_GENERIC, "Milk Regeneration (%/minute)",
									Color.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(Math.round((character.getBreastCrotchLactationRegeneration().getPercentageRegen()*100)*100)/100f),
									Color.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitalizeSentence(character.getBreastCrotchLactationRegeneration().getName()),
									false)
							+ statRow(Color.TRANSFORMATION_GENERIC, "Capacity (inches)",
									Color.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchRawCapacityValue()),
									Color.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitalizeSentence(character.getNippleCrotchCapacity().getDescriptor()),
									true)
							+ statRow(Color.TRANSFORMATION_GENERIC, "Elasticity",
									Color.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchElasticity().getValue()),
									Color.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitalizeSentence(character.getNippleCrotchElasticity().getDescriptor()),
									false)
							+ statRow(Color.TRANSFORMATION_GENERIC, "Plasticity",
									Color.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchPlasticity().getValue()),
									Color.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitalizeSentence(character.getNippleCrotchPlasticity().getDescriptor()),
									true)
						:"")

				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Color.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Penis Attributes</h6>"
//				+ statHeader()
				+ statRow(Color.TRANSFORMATION_GENERIC, "Penis Size (inches)",
						Color.TEXT, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisRawSizeValue())),
						Color.GENERIC_SEX, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitalizeSentence(character.getPenisSize().getDescriptor())),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Testicle Size",
						Color.TEXT, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getTesticleSize().getValue())),
						Color.GENERIC_SEX, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitalizeSentence(character.getTesticleSize().getDescriptor())),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Cum Storage (mL)",
						Color.TEXT, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisRawCumStorageValue())),
						Color.GENERIC_SEX, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitalizeSentence(character.getPenisCumStorage().getDescriptor())),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Cum Production Pregnancy Modifier",
						Color.TEXT, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisCumStorage().getPregnancyModifier())),
						Color.GENERIC_SEX, !knowsPenis?"Unknown":"N/A",
						true)
				+ (Main.getProperties().hasValue(PropertyValue.cumRegenerationContent) ? statRow(Color.TRANSFORMATION_GENERIC, "Cum Regeneration (%/minute)",
						Color.TEXT, !knowsPenis?"Unknown":String.valueOf(Math.round((character.getPenisCumProductionRegeneration().getPercentageRegen()*100)*100)/100f),
						Color.GENERIC_SEX, !knowsPenis?"Unknown":Util.capitalizeSentence(character.getPenisCumProductionRegeneration().getName()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Cum Expulsion (% of stored cum)",
						Color.TEXT, !knowsPenis?"Unknown":String.valueOf(character.getPenisRawCumExpulsionValue()),
						Color.GENERIC_SEX, !knowsPenis?"Unknown":Util.capitalizeSentence(character.getPenisCumExpulsion().getDescriptor()),
						false) : "")

				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Color.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Vagina Attributes</h6>"
//				+ statHeader()
				+ statRow(Color.TRANSFORMATION_GENERIC, "Clitoris Size (inches)",
						Color.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaRawClitorisSizeValue())),
						Color.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitalizeSentence(character.getVaginaClitorisSize().getDescriptor())),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Wetness",
						Color.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue())),
						Color.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitalizeSentence(character.getVaginaWetness().getDescriptor())),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Capacity (inches)",
						Color.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaRawCapacityValue())),
						Color.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitalizeSentence(character.getVaginaCapacity().getDescriptor())),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Elasticity",
						Color.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaElasticity().getValue())),
						Color.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitalizeSentence(character.getVaginaElasticity().getDescriptor())),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Plasticity",
						Color.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaPlasticity().getValue())),
						Color.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitalizeSentence(character.getVaginaPlasticity().getDescriptor())),
						true)

				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Color.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Anus Attributes</h6>"
//				+ statHeader()
				+ statRow(Color.TRANSFORMATION_GENERIC, "Wetness",
						Color.TEXT, !knowsAnus?"Unknown":String.valueOf(character.getAssWetness().getValue()),
						Color.GENERIC_SEX, !knowsAnus?"Unknown":Util.capitalizeSentence(character.getAssWetness().getDescriptor()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Capacity (inches)",
						Color.TEXT, !knowsAnus?"Unknown":String.valueOf(character.getAssRawCapacityValue()),
						Color.GENERIC_SEX, !knowsAnus?"Unknown":Util.capitalizeSentence(character.getAssCapacity().getDescriptor()),
						true)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Elasticity",
						Color.TEXT, !knowsAnus?"Unknown":String.valueOf(character.getAssElasticity().getValue()),
						Color.GENERIC_SEX, !knowsAnus?"Unknown":Util.capitalizeSentence(character.getAssElasticity().getDescriptor()),
						false)
				+ statRow(Color.TRANSFORMATION_GENERIC, "Plasticity",
						Color.TEXT, !knowsAnus?"Unknown":String.valueOf(character.getAssPlasticity().getValue()),
						Color.GENERIC_SEX, !knowsAnus?"Unknown":Util.capitalizeSentence(character.getAssPlasticity().getDescriptor()),
						true)

				+"</div>";
	}

	public static final DialogueNode CHARACTER_STATS_BODY = new DialogueNode("Body Stats", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary style='color:"+Color.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Orifice Mechanics</summary>"

						+ "[style.boldSex(Capacity:)] An orifice's capacity determines the size of objects that can be comfortably inserted."
							+ " <b>Higher capacity values mean that the orifice can take larger insertions without stretching</b>."
							+ "<br/>Capacity values range from 0 (extremely tight) to 40 (gaping wide)."

						+ "<br/><br/>"

						+ "[style.boldSex(Elasticity:)] An orifice's elasticity determines how quickly it stretches out."
							+ " If a partner's penis is too large for your orifice's capacity value, then your orifice will stretch out each turn during sex, with <b>higher elasticity values meaning that it stretches out quicker</b>."
							+ "<br/>Elasticity values range from 0 (extremely resistant to being stretched out) to 7 (instantly stretching out)."

							+ "<br/><br/>"

						+ "[style.boldSex(Plasticity:)] An orifice's plasticity determines how quickly it recovers after being stretched out."
							+ " If your orifice has been stretched out during sex, <b>higher plasticity values mean that it will recover slower, with very high values meaning that it will never recover all of its original tightness</b>."
							+ "<br/>Plasticity values range from 0 (instantly returns to starting size after sex) to 7 (recovers none of its original size after sex)."
				+ "</details>"

				+ getBodyStatsPanel(Main.game.getPlayer()));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);

			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", null);

			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);

			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);

			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_STATS_SEX = new DialogueNode("Sex Stats", "", true) {

		@Override
		public String getContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "You have orgasmed [style.boldSex("+Main.game.getPlayer().getDaysOrgasmCount()+")] time"+(Main.game.getPlayer().getDaysOrgasmCount()==1?"":"s")
							+" today, bringing your total orgasm count to [style.boldSex("+Main.game.getPlayer().getTotalOrgasmCount()+")].<br/>"
						+ "Your record for most orgasms in one day is currently [style.boldArcane("+Main.game.getPlayer().getDaysOrgasmCountRecord()+")]."
					+ "</div>"

					+ sexStatHeader()

					+ sexStatRow(Color.AROUSAL_STAGE_TWO, "Fingering",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
							-1)

					+ sexStatRow(Color.AROUSAL_STAGE_TWO, "Anal Fingering",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
							-1)

					+ sexStatRow(Color.AROUSAL_STAGE_TWO, "Blowjobs",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)))

					+ sexStatRow(Color.AROUSAL_STAGE_TWO, "Cunnilingus",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
							-1)

					+ sexStatRow(Color.AROUSAL_STAGE_TWO, "Anilingus",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
							-1)

					+ sexStatRow(Color.AROUSAL_STAGE_FIVE, "Vaginal sex",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)))

					+ sexStatRow(Color.AROUSAL_STAGE_FIVE, "Anal sex",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)))

					+ sexStatRow(Color.AROUSAL_STAGE_FIVE, "Nipple penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)))

					+ sexStatRow(Color.AROUSAL_STAGE_FIVE, "Penis Urethra penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)))

					+ sexStatRow(Color.AROUSAL_STAGE_FIVE, "Vagina Urethra penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);

			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);

			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", null);

			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);

			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_STATS_PREGNANCY = new DialogueNode("Pregnancy Stats", "", true) {

		private void OffspringHeaderDisplay(StringBuilder output, String word_one, String word_two, String color, int count) {
			output.append("<div class='extraAttribute-quarter'>");
			output.append(word_one);
			output.append("<br/>");
			output.append("<b style='color:").append(color).append(";'>");
			output.append(word_two);
			output.append("</b>");
			output.append("<br/>");
			output.append(count);
			output.append("</div>");
		}

		private boolean ChildMet(NPC npc) {
			return Main.game.getPlayer().getCharactersEncountered().contains(npc.getId());
		}

		private void OffspringTableLine(StringBuilder output, NPC npc) {
			boolean female = npc.isFeminine();
			String color = female ? Color.FEMININE.toWebHexString() : Color.MASCULINE.toWebHexString();
			String child_name = ChildMet(npc) ? npc.getName() : "Unknown";
			String race_color = npc.getRace().getColor().toWebHexString();
			String species_name = female
								? npc.getSubspecies().getSingularFemaleName(npc)
								: npc.getSubspecies().getSingularMaleName(npc);
			String mother = npc.getMother() == null ? "???" : (npc.getMother().isPlayer() ? "[style.colorExcellent(You)]" : npc.getMother().getName());
			String father = npc.getFather() == null ? "???" : (npc.getFather().isPlayer() ? "[style.colorExcellent(You)]" : npc.getFather().getName());
			String extraRelationships = Main.game.getPlayer().getRelationshipStrTo(npc, Relationship.Parent);

			output.append("<tr>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b style='color:").append(color).append(";'>");
						output.append(child_name);
					output.append("</b>");
				output.append("</td>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b style='color:").append(race_color).append(";'>");
						output.append(species_name);
					output.append("</b>");
				output.append("</td>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b>");
						output.append(mother);
					output.append("</b>");
				output.append("</td>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b>");
						output.append(father);
					output.append("</b>");
				output.append("</td>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b>");
						output.append(
								extraRelationships.isEmpty()
									?"[style.italicsDisabled(None)]"
									:extraRelationships);
					output.append("</b>");
				output.append("</td>");
			output.append("</tr>");
		}

		@Override
		public String getContent() {

			int sonsBirthed=0;
			int daughtersBirthed=0;
			int sonsFathered=0;
			int daughtersFathered=0;
			int childrenMet = 0;

			for (Litter litter : Main.game.getPlayer().getLittersBirthed()){
				sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
				daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
			}
			for (Litter litter : Main.game.getPlayer().getLittersFathered()){
				sonsFathered+=(litter.isSelfImpregnation()?0:litter.getSonsFromMother()+litter.getSonsFromFather());
				daughtersFathered+=(litter.isSelfImpregnation()?0:litter.getDaughtersFromMother()+litter.getDaughtersFromFather());
			}

			UtilText.nodeContentSB.setLength(0);

			OffspringHeaderDisplay(UtilText.nodeContentSB, "Mothered", "Sons", Color.MASCULINE.toWebHexString(), sonsBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Mothered", "Daughters", Color.FEMININE.toWebHexString(), daughtersBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Fathered", "Sons", Color.MASCULINE.toWebHexString(), sonsFathered);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Fathered", "Daughters", Color.FEMININE.toWebHexString(), daughtersFathered);

			for (NPC npc : Main.game.getOffspring()) {
				childrenMet += ChildMet(npc) ? 1 : 0;
			}
			int totalChildren = (sonsBirthed+daughtersBirthed+sonsFathered+daughtersFathered);
			int percentageMet = totalChildren == 0 ? 100 : (100 * childrenMet / totalChildren);

			UtilText.nodeContentSB.append(
					"<div class='subTitle'>Total offspring: "+ totalChildren+" (Children met: "+ percentageMet +"%)</div>"

					+ "<span style='height:16px;width:100%;float:left;'></span>"

					+ pregnancyDetails()

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+"<div class='subTitle'>Offspring list</div>"
					+ "<div class='container-full-width' style='text-align:center;'>"

					+ "<table align='center'>"
					+ "<tr><th>Name</th><th>Race</th><th>Mother</th><th>Father</th><th>Your additional relationships</th></tr>"
					+ "<tr style='height:8px;'></tr>");

			for(NPC npc : Main.game.getOffspring()) {
				OffspringTableLine(UtilText.nodeContentSB, npc);
			}

			UtilText.nodeContentSB.append("</table></div>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);

			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);

			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);

			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", null);

			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String sexStatHeader() {
		return "<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; font-weight:bold; text-align:center;'>"
					+ "<div class='container-full-width' style='width:calc(33.3% - 16px); padding:0;'>"
						+ "Type"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ "Given"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ "Cum Given"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ "Taken"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ "Cum Taken"
					+ "</div>"
				+ "</div>";
	}

	private static String sexStatRow(Color color, String name, int given, int loadsGiven, int received, int loadsReceived) {
		return "<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; text-align:center;'>"
					+ "<div class='container-full-width' style='width:calc(33.3% - 16px); padding:0;'>"
						+ "<span style='color:" + color.toWebHexString() + ";'>" + name + "</span>"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ given
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ (loadsGiven < 0 ? "<span class='option-disabled'>-</span>" : loadsGiven)
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ received
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ (loadsReceived < 0 ? "<span class='option-disabled'>-</span>" : loadsReceived)
					+ "</div>"
				+ "</div>";
	}

	private static String pregnancyDetails() {
		StringBuilder contentSB = new StringBuilder();

		// Mothered children:

		boolean noPregnancies=true;

		contentSB.append("<div class='subTitle'>Mothered children</div>");

		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)){

			contentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "[style.boldBad(Ongoing pregnancy)]"
					+ "<br/>"
					+ "[style.bold(Possible partners:)]");

			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsMother()){
				if(pp.getFather()!=null) {
					contentSB.append(UtilText.parse(pp.getFather(),
							"<br/><b>[npc.Name(A)] (</b>"
								+ (!pp.getFather().getRaceStage().getName().isEmpty()
										?"<b style='color:"+pp.getFather().getRaceStage().getColor().toWebHexString()+";'>" + Util.capitalizeSentence(pp.getFather().getRaceStage().getName())+"</b> "
										:"")
								+ "<b style='color:"+pp.getFather().getRace().getColor().toWebHexString()+";'>"
								+ (pp.getFather().getGender().isFeminine()
										?Util.capitalizeSentence(pp.getFather().getSubspecies().getSingularFemaleName(pp.getFather()))
										:Util.capitalizeSentence(pp.getFather().getSubspecies().getSingularMaleName(pp.getFather())))
								+ "</b><b>) Probability: "));

					if (pp.getProbability() <= 0) {
						contentSB.append("None");
					} else if(pp.getProbability()<=0.15f) {
						contentSB.append("Low");
					} else if(pp.getProbability()<=0.3f) {
						contentSB.append("Average");
					} else if(pp.getProbability()<1) {
						contentSB.append("High");
					} else {
						contentSB.append("Certainty");
					}

					contentSB.append("</b>");
				}
			}

			contentSB.append("</div>");

			noPregnancies=false;

		}

		if (!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				if(litter.getFather()!=null) {
					contentSB.append(UtilText.parse(litter.getFather(),
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "<br/>"
								+ "Conceived with [npc.name(a)] on " + Util.getStringOfLocalDateTime(litter.getConceptionDate()) + ", delivered on " + Util.getStringOfLocalDateTime(litter.getBirthDate()) + "."
								+ "<br/>"
								+ "You gave birth to "+ litter.getBirthedDescription()+ "."
							+ "</div>"));
				} else {
					contentSB.append(
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "<br/>"
								+ "Conceived with someone you can't remember on " + Util.getStringOfLocalDateTime(litter.getConceptionDate()) + ", delivered on " + Util.getStringOfLocalDateTime(litter.getBirthDate()) + "."
								+ "<br/>"
								+ "You gave birth to "+ litter.getBirthedDescription()+ "."
							+ "</div>");
				}
			}
			noPregnancies=false;
		}

		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:"+Color.TEXT_GREY.toWebHexString()+";'>You have never been pregnant!</span>"
					+ "</div>");
		}

		// Fathered children:

		noPregnancies=true;

		contentSB.append("<span style='height:16px;width:100%;float:left;'></span>"
				+ "<div class='subTitle'>Fathered children</div>");

		for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsFather()){
			if(pp.getMother()!=null) {
				contentSB.append(UtilText.parse(pp.getMother(),
						"<div class='container-full-width' style='text-align:center;'>"
						+ "[style.boldBad(Ongoing pregnancy)]"
						+ "<br/>"
						+"<b>[npc.Name(A)] (</b>"
							+ (!pp.getMother().getRaceStage().getName().isEmpty()
									?"<b style='color:"+pp.getMother().getRaceStage().getColor().toWebHexString()+";'>" + Util.capitalizeSentence(pp.getMother().getRaceStage().getName())+"</b> "
									:"")
							+ "<b style='color:"+pp.getMother().getRace().getColor().toWebHexString()+";'>"
							+ (pp.getMother().getGender().isFeminine()
									?Util.capitalizeSentence(pp.getMother().getSubspecies().getSingularFemaleName(pp.getMother()))
									:Util.capitalizeSentence(pp.getMother().getSubspecies().getSingularMaleName(pp.getMother())))
							+ "</b><b>)</b>"));

				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					contentSB.append("<br/>Probability of impregnation: ");
					if (pp.getProbability() <= 0) {
						contentSB.append("None");
					} else if(pp.getProbability()<=0.15f) {
						contentSB.append("Low");
					} else if(pp.getProbability()<=0.3f) {
						contentSB.append("Average");
					} else if(pp.getProbability()<1) {
						contentSB.append("High");
					} else {
						contentSB.append("Certainty");
					}
				} else {
					if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_1)) {
						contentSB.append("<br/>Pregnancy stage: [style.boldSex("+Util.capitalizeSentence(StatusEffect.PREGNANT_1.getName(pp.getMother()))+")]");

					} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_2)) {
						contentSB.append("<br/>Pregnancy stage: [style.boldSex("+Util.capitalizeSentence(StatusEffect.PREGNANT_2.getName(pp.getMother()))+")]");

					} else {
						contentSB.append("<br/>Pregnancy stage: [style.boldSex("+Util.capitalizeSentence(StatusEffect.PREGNANT_3.getName(pp.getMother()))+")]");

					}
				}

				contentSB.append("</b><br/>");
				contentSB.append("</div>");
			}
			noPregnancies=false;
		}

		if (!Main.game.getPlayer().getLittersFathered().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersFathered()) {
				if(litter.getMother()!=null) {
					contentSB.append(UtilText.parse(litter.getMother(),
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "<br/>"
								+ "Conceived with [npc.name(a)] on " + Util.getStringOfLocalDateTime(litter.getConceptionDate()) + ", delivered on " + Util.getStringOfLocalDateTime(litter.getBirthDate()) + "."
								+ "<br/>"
								+ "[npc.She] gave birth to "+ litter.getBirthedDescription()+ "."
							+ "</div>"));

				} else {
					contentSB.append(
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "<br/>"
								+ "Conceived with someone you can't remember on " + Util.getStringOfLocalDateTime(litter.getConceptionDate()) + ", delivered on " + Util.getStringOfLocalDateTime(litter.getBirthDate()) + "."
								+ "<br/>"
								+ "They gave birth to "+ litter.getBirthedDescription()+ "."
							+ "</div>");
				}
			}
			noPregnancies=false;
		}

		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:"+Color.TEXT_GREY.toWebHexString()+";'>You have never got anyone pregnant!</span>"
					+ "</div>");
		}


		return contentSB.toString();
	}

	private static String statHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Attribute"
					+ "</div>"
					+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Value"
					+ "</div>"
					+ "<div style='float:left; width:30%; font-weight:bold; margin:0; padding:0;'>"
						+ "Description"
					+"</div>"
				+ "</div>";
	}

	private static String statRow(String colorLeft, String left, Color colorCenter, String center, String colorRight, String right, boolean light) {
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(light?"background:"+Color.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
				+ "<div style='color:"+colorLeft+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ left
				+ "</div>"
				+ "<div style='color:"+colorCenter.toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ center
				+ "</div>"
				+ "<div style='color:"+colorRight+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ right
				+ "</div>"
			+ "</div>";
	}

	private static String statRow(Color colorLeft, String left, Color colorCenter, String center, Color colorRight, String right, boolean light) {
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(light?"background:"+Color.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='color:"+colorLeft.toWebHexString()+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ left
					+ "</div>"
					+ "<div style='color:"+(center.equalsIgnoreCase("unknown")?Color.TEXT_GREY:colorCenter).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ center
					+ "</div>"
					+ "<div style='color:"+(right.equalsIgnoreCase("unknown")?Color.TEXT_GREY:colorRight).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ right
					+ "</div>"
				+ "</div>";
	}

	private static String getAttributeBox(GameCharacter owner, Attribute att, String effect) {
		return getAttributeBox(owner, att, effect, false);
	}

	private static String getAttributeBox(GameCharacter owner, Attribute att, String effect, boolean half) {
		float width = (Math.abs(owner.getAttributeValue(att))/(att.getUpperLimit()-att.getLowerLimit())) * 100;
		Color color = (owner.getAttributeValue(att)==0
							?Color.TEXT
							:(owner.getAttributeValue(att)==att.getUpperLimit()
								?Color.GENERIC_EXCELLENT
								:(owner.getAttributeValue(att)==att.getUpperLimit()
									?Color.GENERIC_TERRIBLE
									:(owner.getAttributeValue(att)>0
										?Color.GENERIC_GOOD
										:Color.GENERIC_BAD))));

		return "<div class='container-half-width' style='"+(half?"width:calc(50% - 16px);":"width:calc(33% - 16px);")+" margin-bottom:0; background:"+Color.BACKGROUND_ALT.toWebHexString()+";'>"
					+ "<div class='container-half-width' style='width:66.6%;margin:0;background:"+Color.BACKGROUND_ALT.toWebHexString()+";'>"
						+ "<b style='color:" + att.getColor().toWebHexString() + ";'>"+Util.capitalizeSentence(att.getName())+"</b>"
					+ "</div>"
					+ "<div class='container-half-width' style='width:33.3%;margin:0;background:"+Color.BACKGROUND_ALT.toWebHexString()+";text-align:center;'>"
						+ "<b style='color:"+color.toWebHexString()+";'>"+owner.getAttributeValue(att)+"</b>"
					+ "</div>"
					+ "<div class='container-full-width' style='height:6px;padding:0;border-radius: 2px;'>"
						+ "<div class='container-full-width' style='width:" + width + "%; padding:0;"
								+ " margin:0 0 0 "
									+(att.getLowerLimit()>=0
										?0
										:(owner.getAttributeValue(att)>0
											?"50%"
											:(50-width)+"%"))+";"
								+ " height:100%; background:" + (owner.getAttributeValue(att)>0?att.getColor().toWebHexString():att.getColor().getShades()[1]) + "; float:left; border-radius: 2px;'></div>"
					+ "</div>"
					+ "<div class='container-half-width' style='margin:0;background:"+Color.BACKGROUND_ALT.toWebHexString()+"; padding:0; text-align:center;'>"
							+ "Base: "+(owner.getBaseAttributeValue(att) > 0
								? "<b style='color:" + Color.GENERIC_GOOD.getShades()[1] + ";"
										: (owner.getBaseAttributeValue(att) < 0
												? "<b style='color:" + Color.GENERIC_BAD.getShades()[1] + ";"
												: "<b style='color:" + Color.TEXT_GREY.toWebHexString() + ";"))+"'>"
												+owner.getBaseAttributeValue(att)
												+"</b>"
					+ "</div>"
					+ "<div class='container-half-width' style='margin:0;background:"+Color.BACKGROUND_ALT.toWebHexString()+"; padding:0; text-align:center;'>"
							+ "Bonus: "
							+ (owner.getBonusAttributeValue(att) > 0
									? "<b style='color:" + Color.GENERIC_GOOD.getShades()[1] + ";"
											: (owner.getBonusAttributeValue(att) < 0
													? "<b style='color:" + Color.GENERIC_BAD.getShades()[1] + ";"
													: "<b style='color:" + Color.TEXT_GREY.toWebHexString() + ";"))+"'>"
													+owner.getBonusAttributeValue(att)
													+"</b>"
					+ "</div>"
					+ (effect.length()>0
							?"<div class='container-full-width' style='margin:0;background:"+Color.BACKGROUND_ALT.toWebHexString()+"; padding:0; text-align:center;'>"
								+"<hr/>"
								+ "<i>"+effect+"</i>"
							+ "</div>"
							:"")
				+ "</div>";
	}

	private static String getContactEntry(GameCharacter contact) {
		boolean isOffspring = contact.getMotherId().equals(Main.game.getPlayer().getId()) || contact.getFatherId().equals(Main.game.getPlayer().getId());

		return UtilText.parse(contact, "<b style='color:"+contact.getFemininity().getColor().toWebHexString()+";'>[npc.Name]</b>"+(isOffspring
				?(contact.isFeminine()
						?", your [style.colorFeminine(daughter)]"
						:", your [style.colorMasculine(son)]")
				:"")
				+". [npc.She] is "
				+ (contact.getRaceStage()==RaceStage.HUMAN || contact.isRaceConcealed()
					?"[npc.a_race(true)]"
					:"[npc.a_raceStage(true)] [npc.race(true)]")
				+", whose current location is: <i>"+contact.getWorldLocation().getName()+", "+contact.getLocationPlace().getPlaceType().getName()+"</i>.");
	}

	public static final DialogueNode CONTACTS = new DialogueNode("Contacts", "Look at your contacts.", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			for (int i = 0; i < charactersEncountered.size(); i++) {
				GameCharacter npc = charactersEncountered.get(i);

				UtilText.nodeContentSB.append("<p>"
												+ getContactEntry(npc)
											+ "</p>");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else if (index <= charactersEncountered.size()) {
				GameCharacter npc = charactersEncountered.get(index-1);
				boolean isOffspring = npc.getMotherId().equals(Main.game.getPlayer().getId()) || npc.getFatherId().equals(Main.game.getPlayer().getId());
				return new Response(
						UtilText.parse(npc, isOffspring
								?(npc.isFeminine()?"[style.colorFeminine([npc.Name])]":"[style.colorMasculine([npc.Name])]")
								:"[npc.Name]"),
						getContactEntry(npc),
						CONTACTS_CHARACTER){
					@Override
					public void effects() {
						CharactersPresentDialogue.resetContent(npc);
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CONTACTS_CHARACTER = new DialogueNode("Contacts", "Look at your contacts.", true) {

		@Override
		public String getLabel() {
			return CharactersPresentDialogue.characterViewed.getName();
		}

		@Override
		public String getContent() {
			return CharactersPresentDialogue.menuContent;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else if (index <= Main.game.getPlayer().getCharactersEncountered().size()) {
				try {
					GameCharacter npc = Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return new Response(Util.capitalizeSentence(npc.getName()),
							"Take a detailed look at what " + npc.getName("the") + " looks like.",
							CONTACTS_CHARACTER){
						@Override
						public void effects() {
							CharactersPresentDialogue.resetContent(npc);

						}
					};
				} catch (Exception e) {
					Util.logGetNpcByIdError("CONTACTS_CHARACTER.getResponse()", Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return null;
				}

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode ENCYCLOPEDIA = new DialogueNode("Encyclopedia", "", true) {

		@Override
		public String getContent() {
			return "<p>"
					+ "You have collected information on all the races, weapons, clothing, and items that you've encountered in your travels."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Races</span>":"Races",
						"Have a look at all the different races that you've encountered in your travels.", RACES){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
					}
				};

			} else if (index == 2) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered))?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Weapons</span>":"Weapons",
						"Have a look at all the different weapons that you've encountered in your travels.", WEAPON_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
					}
				};

			} else if (index == 3) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newClothingDiscovered))?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Clothing</span>":"Clothing",
						"Have a look at all the different clothing that you've encountered in your travels.", CLOTHING_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
					}
				};

			} else if (index == 4) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newItemDiscovered))?"<span style='color:" + Color.GENERIC_EXCELLENT.toWebHexString() + ";'>Items</span>":"Items",
						"Have a look at all the different items that you've encountered in your travels.", ITEM_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<AbstractItemType> itemsDiscoveredList = new ArrayList<>();
	private static List<AbstractClothingType> clothingDiscoveredList = new ArrayList<>();
	private static List<AbstractWeaponType> weaponsDiscoveredList = new ArrayList<>();

	static {

		itemsDiscoveredList.addAll(ItemType.getAllItems());
		itemsDiscoveredList.sort(new ItemRarityComparator());

		weaponsDiscoveredList.addAll(WeaponType.getAllweapons());
		weaponsDiscoveredList.sort(new WeaponRarityComparator());

		clothingDiscoveredList.addAll(ClothingType.getAllClothing());
		clothingDiscoveredList.sort(new ClothingRarityComparator());
	}
	public static final DialogueNode WEAPON_CATALOGUE = new DialogueNode("Discovered Weapons", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			journalSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Hover over the colored icons to see preview pictures of each weapon.</i>"
					+ "</div>");

			for (AbstractWeaponType weapon : weaponsDiscoveredList) {
				if (Main.getProperties().isWeaponDiscovered(weapon)) {
					journalSB.append(
							"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div class='container-full-width' style='width:calc(60% - 16px)'>"
									+ "<b style='color:" + weapon.getRarity().getColor().toWebHexString() + ";'>" + Util.capitalizeSentence(weapon.getName()) + "</b> ("+(weapon.isMelee()?"Melee":"Ranged")+")"
							+ "</div>"
							+ "<div class='container-full-width' style='width:calc(40% - 16px)'>");

					for (DamageType dt : weapon.getAvailableDamageTypes()) {
						journalSB.append("<div class='phone-item-color' id='" + (weapon.hashCode() + "_" + dt.toString()) + "' style='background-color:" + dt.getMultiplierAttribute().getColor().toWebHexString() + ";'></div>");
					}

					journalSB.append("</div>"
							+ "</div>");

				} else {
					journalSB.append(
						"<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
								+ "[style.boldDisabled(Undiscovered)]"
						+ "</div>");
				}
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode CLOTHING_CATALOGUE = new DialogueNode("Discovered Clothing", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			journalSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Hover over the colored icons to see preview pictures of each item of clothing.</i>"
					+ "</div>");

			for (AbstractClothingType clothing : clothingDiscoveredList) {
				if (Main.getProperties().isClothingDiscovered(clothing)) {
					journalSB.append(
							"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div class='container-full-width' style='width:calc(40% - 16px)'>"
									+ "<b style='color:" + clothing.getRarity().getColor().toWebHexString() + ";'>" + Util.capitalizeSentence(clothing.getName()) + "</b> ("+Util.capitalizeSentence(clothing.getSlot().getName())+")"
							+ "</div>"
							+ "<div class='container-full-width' style='width:calc(60% - 16px)'>");

					for (Color c : clothing.getAllAvailablePrimaryColors()) {
						journalSB.append("<div class='phone-item-color' id='" + (clothing.hashCode() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");
					}

					journalSB.append("</div>"
							+ "</div>");

				} else {
					journalSB.append(
						"<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
								+ "[style.boldDisabled(Undiscovered ("+Util.capitalizeSentence(clothing.getSlot().getName())+"))]"
						+ "</div>");
				}
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode ITEM_CATALOGUE = new DialogueNode("Discovered items", "View discovered items", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			journalSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Hover over the 'i' icons to see preview pictures of each item.</i>"
					+ "</div>");

			for (AbstractItemType item : itemsDiscoveredList) {
				if (Main.getProperties().isItemDiscovered(item)) {
					journalSB.append(
							"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div class='container-full-width' style='width:calc(40% - 16px)'>"
									+ "<div class='title-button' id='"+ItemType.getItemToIdMap().get(item)+"' style='background:transparent; position:relative; top:0; left:0; float:left; margin:0 8px 0 0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
									+ " <b style='color:" + item.getRarity().getColor().toWebHexString() + ";'>" + Util.capitalizeSentence(item.getName(false)) + "</b>"
							+ "</div>"
							+ "<div class='container-full-width' style='width:calc(60% - 16px)'>");

					if (item.getEffects().isEmpty()) {
						journalSB.append("-");
					} else {
						int i=1;
						for(ItemEffect ie : item.getEffects()) {
							for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
								journalSB.append(s);
								if(i != ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size())
									journalSB.append("<br/>");
								i++;
							}
						}
					}

					journalSB.append("</div>"
							+ "</div>");

				} else {
					journalSB.append(
						"<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
								+ "[style.boldDisabled(Undiscovered)]"
						+ "</div>");
				}
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<Race> racesDiscovered = new ArrayList<>();
	private static List<Subspecies> subspeciesDiscovered = new ArrayList<>();
	private static Race raceSelected;
	private static Subspecies subspeciesSelected;
	private static StringBuilder subspeciesSB = new StringBuilder();

	public static void resetContentForRaces() {

		subspeciesDiscovered.clear();

		for (Subspecies subspecies : Subspecies.values()) {
			if(Main.getProperties().isRaceDiscovered(subspecies)) {
				Race race = subspecies.getRace();
				if(!racesDiscovered.contains(race)) {
					racesDiscovered.add(race);
				}
				subspeciesDiscovered.add(subspecies);
			}
		}

		racesDiscovered.sort((a, b) -> a.getName(false).compareTo(b.getName(false)));
		subspeciesDiscovered.sort((a, b) -> a.getName(null).compareTo(b.getName(null)));

	}

	public static final DialogueNode RACES = new DialogueNode("Discovered races", "View discovered races", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
						+ "You have encountered the following races in your travels:<br/>"
						+ "(Discovered races are [style.boldGood(highlighted)], while undiscovered races are [style.colorDisabled(greyed out)].)"
					+ "</p>");
			List<Race> sortedRaces = new ArrayList<>();
			Collections.addAll(sortedRaces, Race.values());
			sortedRaces.remove(Race.NONE);
			sortedRaces.sort((r1, r2) -> r1.getName(false).compareTo(r2.getName(false)));
			for(Race race : sortedRaces) {
				UtilText.nodeContentSB.append("<div style='box-sizing: border-box; text-align:center; width:50%; padding:8px; margin:0; float:left;'>");
				if(racesDiscovered.contains(race)) {
					UtilText.nodeContentSB.append("<b style='color:"+race.getColor().toWebHexString()+";'>" + Util.capitalizeSentence(race.getName(false)) + "</b>");
				} else {
					UtilText.nodeContentSB.append("[style.colorDisabled(" + Util.capitalizeSentence(race.getName(false)) + ")]");
				}
				UtilText.nodeContentSB.append("</div>");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);

			} else if (index <= racesDiscovered.size()) {
				return new Response(Util.capitalizeSentence(racesDiscovered.get(index - 1).getName(false)),
						"Take a look at all the subspecies of the race: '" + racesDiscovered.get(index - 1).getName(false) + "'",
						SUBSPECIES){
					@Override
					public void effects() {
						raceSelected = racesDiscovered.get(index - 1);
						subspeciesSelected = Subspecies.getMainSubspeciesOfRace(raceSelected);
						if(!subspeciesDiscovered.contains(subspeciesSelected)) {
							for(Subspecies sub : subspeciesDiscovered) {
								if(sub.getRace()==raceSelected) {
									subspeciesSelected = sub;
									break;
								}
							}
						}
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode SUBSPECIES = new DialogueNode("Discovered races", "View discovered races", true) {

		@Override
		public String getLabel() {
			return Util.capitalizeSentence(subspeciesSelected.getName(null));
		}

		@Override
		public String getContent() {
			subspeciesSB.setLength(0);

			Body femaleBody = CharacterUtils.generateBody(null, Gender.F_V_B_FEMALE, subspeciesSelected, RaceStage.GREATER);
			Body maleBody = CharacterUtils.generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);

			subspeciesSB.append(
				"<div class='container-full-width' style='width:calc(40% - 16px); float:right;'>"
					+ "<p style='width:100%; text-align:center;'><b style='color:"+subspeciesSelected.getColor(null).toWebHexString()+";'>"+Util.capitalizeSentence(subspeciesSelected.getName(null))+"</b><br/>"
							+ "Average stats</p>"
					+ "<table align='center'>"
						+ "<tr>"
							+ "<td>Height (cm)</td>"
							+ "<td>"+femaleBody.getHeightValue()+"</td>"
							+ "<td>"+maleBody.getHeightValue()+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Femininity</td>"
							+ "<td>"+femaleBody.getFemininity()+"</td>"
							+ "<td>"+maleBody.getFemininity()+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Breast size</td>"
							+ "<td>"+(femaleBody.getBreast().getRawSizeValue()==0
										?"Flat"
										:femaleBody.getBreast().getSize().getCupSizeName()+"-cup")+"</td>"
							+ "<td>"+(maleBody.getBreast().getRawSizeValue()==0
										?"Flat"
										:maleBody.getBreast().getSize().getCupSizeName()+"-cup")+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Penis size (inches)</td>"
							+ "<td>-</td>"
							+ "<td>"+maleBody.getPenis().getRawSizeValue()+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Vagina capacity</td>"
							+ "<td>"+Util.capitalizeSentence(femaleBody.getVagina().getOrificeVagina().getCapacity().getDescriptor())+"</td>"
							+ "<td>-</td>"
						+ "</tr>"
					+ "</table>"
				+ "</div>"

				+"<p>"
					+ "<b style='color:"+subspeciesSelected.getColor(null).toWebHexString()+";'>"+Util.capitalizeSentence(subspeciesSelected.getName(null))+"</b>"
					+ (Subspecies.getMainSubspeciesOfRace(raceSelected)==subspeciesSelected
							?" ([style.colorGood(Core)] subspecies of <span style='color:"+raceSelected.getColor().toWebHexString()+";'>"+Util.capitalizeSentence(raceSelected.getName(false))+"</span>)"
							:" (Subspecies of <span style='color:"+raceSelected.getColor().toWebHexString()+";'>"+Util.capitalizeSentence(raceSelected.getName(false))+"</span>)")
					+ "<br/>"
					+ "Masculine: <span style='color:"+Femininity.valueOf(maleBody.getFemininity()).getColor().toWebHexString()+";'>"+Util.capitalizeSentence(subspeciesSelected.getSingularMaleName(null))+"</span>"
					+ "<br/>"
					+ "Feminine: <span style='color:"+Femininity.valueOf(femaleBody.getFemininity()).getColor().toWebHexString()+";'>"+Util.capitalizeSentence(subspeciesSelected.getSingularFemaleName(null))+"</span>"
					+ "<br/><br/>"
					+ "<i>"+subspeciesSelected.getDescription(null)+"</i>"
				+ "</p>"

				+"<h6>"+Util.capitalizeSentence(raceSelected.getName(false))+" Lore</h6>"
					+subspeciesSelected.getBasicDescription(null)
					+ (Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspeciesSelected)
						?subspeciesSelected.getAdvancedDescription(null)
						:"<p style='color:"+Color.TEXT_GREY.toWebHexString()+";'>"
							+ "Further information can be discovered in books!"
						+ "</p>"));

			return subspeciesSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Subspecies> raceSubspecies = Subspecies.getSubspeciesOfRace(raceSelected);

			if (index == 0) {
				return new Response("Back", "Return to the race selection screen.", RACES);

			} else if (index <= raceSubspecies.size()) {
				Subspecies indexSubspecies = raceSubspecies.get(index - 1);
				if(!subspeciesDiscovered.contains(indexSubspecies)) {
					return new Response(Util.capitalizeSentence(indexSubspecies.getName(null)),
							"You haven't discovered this subspecies yet!",
							null);
				}
				return new Response(Util.capitalizeSentence(indexSubspecies.getName(null)),
						"Take a detailed look at what " + indexSubspecies.getNamePlural(null) + " are like."
						+ (Subspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies
							?"<br/>This is the [style.colorGood(core)] "+raceSelected.getName(false)+" subspecies."
							:""),
						SUBSPECIES){
					@Override
					public Color getHighlightColor() {
						if(Subspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies) {
							return Color.GENERIC_GOOD;
						}
						return super.getHighlightColor();
					}
					@Override
					public void effects() {
						subspeciesSelected = indexSubspecies;
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_LEVEL_UP = new DialogueNode("Perks", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary>[style.boldPerk(Perk & Trait Information)]</summary>"
							+ "[style.colorPerk(Perks)] (circular icons) apply permanent boosts to your attributes.<br/>"
							+ "[style.colorPerk(Traits)] (square icons) provide unique effects for your character."
								+ " Unlike perks, <b>traits will have no effect on your character until they're slotted into your 'Active Traits' bar</b>.<br/>"
							+ "Perks require perk points to unlock. You earn one perk point each time you level up, and earn an extra two perk points every five levels.<br/><br/>"
							+ "In addition to the perks that can be purchased via perk points, there are also several special, hidden perks that are unlocked via special events."
							+ " There are currently [style.boldPerk("+Perk.getHiddenPerks().size()+")] special perks in the game."
					+ "</details>"

					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
					+ "<h6 style='text-align:center;'>Active Traits</h6>");

			UtilText.nodeContentSB.append(
					"<div id='OCCUPATION_" + Main.game.getPlayer().getHistory().getAssociatedPerk()+ "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + Color.TRAIT.toWebHexString() + ";'>"
						+ "<div class='square-button-content'>"+Main.game.getPlayer().getHistory().getAssociatedPerk().getSVGString()+"</div>"
					+ "</div>");

			for(int i=0;i<GameCharacter.MAX_TRAITS;i++) {
				Perk p = null;
				if(i<Main.game.getPlayer().getTraits().size()) {
					p = Main.game.getPlayer().getTraits().get(i);
				}
				if(p!=null) {
					UtilText.nodeContentSB.append("<div id='TRAIT_" + p + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + Color.TRAIT.toWebHexString() + ";'>"
							+ "<div class='square-button-content'>"+p.getSVGString()+"</div>"
							+ "</div>");

				} else {
					UtilText.nodeContentSB.append("<div id='TRAIT_" + i + "' class='square-button small' style='display:inline-block; float:none;'></div>");

				}
			}

			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>Special Perks</h6>");

			for(Perk hiddenPerk : Perk.getHiddenPerks()) {
//				if(Main.game.getPlayer().getSpecialPerks().contains(hiddenPerk)) {
					UtilText.nodeContentSB.append("<div id='HIDDEN_PERK_" + hiddenPerk + "' class='square-button round small' style='width:6%; display:inline-block; float:none; border:1% solid " + Color.TRAIT.toWebHexString() + ";'>"
							+ "<div class='square-button-content'>"+hiddenPerk.getSVGString()+"</div>"
							+ (Main.game.getPlayer().getSpecialPerks().contains(hiddenPerk)
									?""
									:"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.95; border-radius:50%;'></div>")
							+ "</div>");

//				} else {
//					UtilText.nodeContentSB.append("<div id='HIDDEN_PERK_" + hiddenPerk + "' class='square-button round small' style='width:6%; display:inline-block; float:none; border:1% solid " + Color.BASE_GREY.toWebHexString() + ";'>"
//							+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown()+"</div>"
//							+ "</div>");
//				}
			}

			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(Main.game.getPlayer()));

			UtilText.nodeContentSB.append("</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
						+ "[style.italicsBad(Please note that this perk tree is a work-in-progress. This is not the final version, and is just a proof of concept!)]"
					+ "</div>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reset", "Reset all perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)", CHARACTER_LEVEL_UP) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetPerksMap();
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_SPELLS_ARCANE = new DialogueNode("Arcane Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.ARCANE, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.ARCANE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldArcane(School of Arcane ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)
									?"[style.colorDisabled("+SpellSchool.ARCANE.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Arcane school spells to unlock.)"
									:"[style.colorGood("+SpellSchool.ARCANE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "View your spells and upgrades in the school of Earth.", CHARACTER_SPELLS_EARTH);

			} else if(index==2) {
				return new Response("Water", "View your spells and upgrades in the school of Water.", CHARACTER_SPELLS_WATER);

			} else if(index==3) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);

			} else if(index==4) {
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);

			} else if(index==5) {
				return new Response("Arcane", "You are already viewing your Arcane spells!", null);

			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_ARCANE)) {
					if(!Main.game.getSavedDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false))) {
						return new Response("Arcane Elemental", "You can only summon your elemental in combat, or in a neutral scene!", null);

					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Arcane Elemental", "You need at least <b>"+Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);

					} else {
						return new Response("Arcane Elemental",
								"Summon your elemental by binding it to the school of Arcane! This will cost <b>"+Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_ARCANE) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Spell.ELEMENTAL_ARCANE.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
							}
						};
					}

				} else {
					return new Response("Arcane Elemental", "You don't know how to bind your elemental to the school of Arcane! (Requires spell: '"+Spell.ELEMENTAL_ARCANE.getName()+"')", null);
				}

			} else if(index==11) {
				return new Response("Reset Arcane", "Reset your Arcane upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_ARCANE) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.ARCANE);
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_SPELLS_EARTH = new DialogueNode("Earth Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.EARTH, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.EARTH.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldEarth(School of Earth ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
									?"[style.colorDisabled("+SpellSchool.EARTH.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Earth school spells to unlock.)"
									:"[style.colorGood("+SpellSchool.EARTH.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "You are already viewing your Earth spells.", null);

			} else if(index==2) {
				return new Response("Water", "View your spells and upgrades in the school of Water.", CHARACTER_SPELLS_WATER);

			} else if(index==3) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);

			} else if(index==4) {
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);

			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);

			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_EARTH)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						if(Main.game.isInCombat()) {
							return new Response("Earth Elemental", "While in combat, use the combat spells menu to summon your elemental!", null);
						} else {
							return new Response("Earth Elemental", "You can only summon your elemental in a neutral scene!", null);
						}

					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Earth Elemental", "You need at least <b>"+Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);

					} else {
						return new Response("Earth Elemental",
								"Summon your elemental by binding it to the school of Earth! This will cost <b>"+Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_EARTH) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Spell.ELEMENTAL_EARTH.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
							}
						};
					}

				} else {
					return new Response("Earth Elemental", "You don't know how to bind your elemental to the school of Earth! (Requires spell: '"+Spell.ELEMENTAL_EARTH.getName()+"')", null);
				}

			}  else if(index==11) {
				return new Response("Reset Earth", "Reset your Earth upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.EARTH);
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_SPELLS_WATER = new DialogueNode("Water Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.WATER, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.WATER.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldWater(School of Water ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
									?"[style.colorDisabled("+SpellSchool.WATER.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Water school spells to unlock.)"
									:"[style.colorGood("+SpellSchool.WATER.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "View your spells and upgrades in the school of Earth.", CHARACTER_SPELLS_EARTH);

			} else if(index==2) {
				return new Response("Water", "You are already viewing your Water spells!", null);

			} else if(index==3) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);

			} else if(index==4) {
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);

			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);

			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_WATER)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						if(Main.game.isInCombat()) {
							return new Response("Water Elemental", "While in combat, use the combat spells menu to summon your elemental!", null);
						} else {
							return new Response("Water Elemental", "You can only summon your elemental in a neutral scene!", null);
						}

					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Water Elemental", "You need at least <b>"+Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);

					} else {
						return new Response("Water Elemental",
								"Summon your elemental by binding it to the school of Water! This will cost <b>"+Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_WATER) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Spell.ELEMENTAL_WATER.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
							}
						};
					}

				} else {
					return new Response("Water Elemental", "You don't know how to bind your elemental to the school of Water! (Requires spell: '"+Spell.ELEMENTAL_WATER.getName()+"')", null);
				}

			} else if(index==11) {
				return new Response("Reset Water", "Reset your Water upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_WATER) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.WATER);
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_SPELLS_AIR = new DialogueNode("Air Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.AIR, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.AIR.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldAir(School of Air ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.AIR)
									?"[style.colorDisabled("+SpellSchool.AIR.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Air school spells to unlock.)"
									:"[style.colorGood("+SpellSchool.AIR.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "View your spells and upgrades in the school of Earth.", CHARACTER_SPELLS_EARTH);

			} else if(index==2) {
				return new Response("Water", "View your spells and upgrades in the school of Water.", CHARACTER_SPELLS_WATER);

			} else if(index==3) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);

			} else if(index==4) {
				return new Response("Air", "You are already viewing your Air spells!", null);

			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);

			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_AIR)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						if(Main.game.isInCombat()) {
							return new Response("Air Elemental", "While in combat, use the combat spells menu to summon your elemental!", null);
						} else {
							return new Response("Air Elemental", "You can only summon your elemental in a neutral scene!", null);
						}

					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Air Elemental", "You need at least <b>"+Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);

					} else {
						return new Response("Air Elemental",
								"Summon your elemental by binding it to the school of Air! This will cost <b>"+Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_AIR) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Spell.ELEMENTAL_AIR.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
							}
						};
					}

				} else {
					return new Response("Air Elemental", "You don't know how to bind your elemental to the school of Air! (Requires spell: '"+Spell.ELEMENTAL_AIR.getName()+"')", null);
				}

			}  else if(index==11) {
				return new Response("Reset Air", "Reset your Air upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_AIR) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.AIR);
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_SPELLS_FIRE = new DialogueNode("Fire Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.FIRE, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.FIRE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldFire(School of Fire ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)
									?"[style.colorDisabled("+SpellSchool.FIRE.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Fire school spells to unlock.)"
									:"[style.colorGood("+SpellSchool.FIRE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "View your spells and upgrades in the school of Earth.", CHARACTER_SPELLS_EARTH);

			} else if(index==2) {
				return new Response("Water", "View your spells and upgrades in the school of Water.", CHARACTER_SPELLS_WATER);

			} else if(index==3) {
				return new Response("Fire", "You are already viewing your Fire spells!", null);

			} else if(index==4) {
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);

			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);

			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_FIRE)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						if(Main.game.isInCombat()) {
							return new Response("Fire Elemental", "While in combat, use the combat spells menu to summon your elemental!", null);
						} else {
							return new Response("Fire Elemental", "You can only summon your elemental in a neutral scene!", null);
						}

					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Fire Elemental", "You need at least <b>"+Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);

					} else {
						return new Response("Fire Elemental",
								"Summon your elemental by binding it to the school of Fire! This will cost <b>"+Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_FIRE) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Spell.ELEMENTAL_FIRE.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
							}
						};
					}

				} else {
					return new Response("Fire Elemental", "You don't know how to bind your elemental to the school of Fire! (Requires spell: '"+Spell.ELEMENTAL_FIRE.getName()+"')", null);
				}

			} else if(index==11) {
				return new Response("Reset Fire", "Reset your Fire upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_FIRE) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.FIRE);
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

//	private static boolean confirmReset = false;
	public static final DialogueNode CHARACTER_FETISHES = new DialogueNode("Desires & Fetishes", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder(
					"<details>"
						+ "<summary>[style.boldFetish(Fetish Information)]</summary>"
							+ "You can select your [style.colorLust(desire)] for each fetish [style.colorArcane(for free)],"
							+ " or choose to take the associated [style.colorFetish(fetish)] for [style.colorArcane("+Fetish.FETISH_ANAL_GIVING.getCost()+" Arcane Essences)].<br/><br/>"
							+ "Choosing a desire will affect bonus lust gains in sex, while taking a fetish will permanently lock your desire to 'love', and also give you special bonuses."
							+ " Fetishes can only be removed through enchanted potions.<br/><br/>"
							+ "Your currently selected desire has a "+Color.FETISH.getName()+" border, but your true desire (indicated by the colored desire icon) may be modified by enchanted clothes or other items.<br/><br/>"
							+ "You earn experience for each fetish through performing related actions in sex."
							+ " Experience is earned regardless of whether or not you have the associated fetish."
							+ " Higher level fetishes will cause both you and your partner to gain more arousal from related sex actions, as well as increase the fetish's bonuses.<br/><br/>"
							+ "Finally, derived fetishes cannot be directly unlocked, but are instead automatically applied when you meet their requirements."
					+ "</details>");

			// Normal fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>Fetishes</h6></div>");
			journalSB.append(getFetishEntry(Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE));
			journalSB.append(getFetishEntry(Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_PENIS_GIVING, Fetish.FETISH_PENIS_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF));
			journalSB.append(getFetishEntry(Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF));
			journalSB.append(getFetishEntry(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			journalSB.append(getFetishEntry(Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT));
			journalSB.append(getFetishEntry(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN));
			journalSB.append(getFetishEntry(Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY));
			journalSB.append(getFetishEntry(Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_KINK_GIVING, Fetish.FETISH_KINK_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_SADIST, Fetish.FETISH_MASOCHIST));
			journalSB.append(getFetishEntry(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_NON_CON_SUB));

//			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Individual Fetishes</h6></div>");
			journalSB.append(getFetishEntry(Fetish.FETISH_DENIAL, Fetish.FETISH_DENIAL_SELF));
			journalSB.append(getFetishEntry(Fetish.FETISH_VOYEURIST, Fetish.FETISH_EXHIBITIONIST));
			journalSB.append(getFetishEntry(Fetish.FETISH_BIMBO, Fetish.FETISH_CROSS_DRESSER));
			journalSB.append(getFetishEntry(Fetish.FETISH_MASTURBATION, Fetish.FETISH_INCEST));

			// Derived fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Derived Fetishes</h6></div>");
			journalSB.append("<div class='fetish-container'>");

			for(Fetish fetish : Fetish.values()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					journalSB.append(
							"<div id='fetishUnlock" + fetish + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:2px solid " + Color.FETISH.getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " +  Color.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + Color.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString()+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
									? ""
									: (fetish.isAvailable(Main.game.getPlayer())
											? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
											: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
							+ "</div>");
				}
			}

			// Free Fetishes:

			journalSB.append("</div>");

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getFetishEntry(Fetish othersFetish, Fetish selfFetish) {
		return "<div class='container-full-width' style='background:transparent; margin:2px 0; width:100%;'>"
					+ getIndividualFetishEntry(othersFetish)
					+ getIndividualFetishEntry(selfFetish)
				+ "</div>";
	}

	private static String getIndividualFetishEntry(Fetish fetish) {
		FetishLevel level = FetishLevel.getFetishLevelFromValue(Main.game.getPlayer().getFetishExperience(fetish));
		float experiencePercentage = ((Main.game.getPlayer().getFetishExperience(fetish)) / (float)(level.getMaximumExperience()))*100;

		return "<div class='container-half-width' style='margin:0 8px;'>"
					+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
						+ (Main.game.getPlayer().hasFetish(fetish)
								?"[style.colorPink("+Util.capitalizeSentence(fetish.getName(Main.game.getPlayer()))+" "+level.getNumeral()+")]"
								:Util.capitalizeSentence(fetish.getName(Main.game.getPlayer()))+" "+level.getNumeral())
						+"<div class='container-full-width' style='margin:2px 0; padding:0; width:100%;'></div>" // Spacer
						+getFetishDesireEntry(fetish, FetishDesire.ZERO_HATE)
						+getFetishDesireEntry(fetish, FetishDesire.ONE_DISLIKE)
						+getFetishDesireEntry(fetish, FetishDesire.TWO_NEUTRAL)
						+getFetishDesireEntry(fetish, FetishDesire.THREE_LIKE)
						+getFetishDesireEntry(fetish, FetishDesire.FOUR_LOVE)
					+ "</div>"
					+"<div class='container-full-width' style='margin:0 8px; width: calc(22% - 16px);'>"
						+ "<div id='fetishUnlock" + fetish + "' class='fetish-icon full" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:2px solid " + Color.FETISH.toWebHexString() + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " + Color.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + Color.TEXT_GREY.toWebHexString() + ";'>"))
										+ "<div class='fetish-icon-content'>"+fetish.getSVGString()+"</div>"
										+ "<div style='width:40%;height:40%;position:absolute;top:0;right:4px;'>"+level.getSVGImageOverlay()+"</div>"
										+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
											? ""
											: (fetish.isAvailable(Main.game.getPlayer())
													? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
													: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width' style='margin:0; padding:0; width:100%;'>"
						+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
							+"<div class='container-full-width' style='margin:4px 0; padding:2px; width:100%; background:#222;'>"
								+ "<div class='container-full-width' style='margin:0; padding:2px; width:" + experiencePercentage + "%; background:"+level.getColor().toWebHexString()+";'></div>"
							+ "</div>"
						+ "</div>"
						+"<div class='container-full-width' style='text-align:center; margin:0 8px; width: calc(22% - 16px);'>"
							+ "<span style='color:"+level.getColor().toWebHexString()+";'>"+Main.game.getPlayer().getFetishExperience(fetish)+" xp</span>"
						+ "</div>"
//						+ "<div class='overlay no-pointer' id='"+fetish+"_EXPERIENCE'></div>"
					+ "</div>"
				+ "</div>";
	}

	private static String getFetishDesireEntry(Fetish fetish, FetishDesire desire) {
		boolean disabled = desire!=FetishDesire.FOUR_LOVE && Main.game.getPlayer().hasFetish(fetish);

		return "<div class='square-button"+(disabled?" disabled":"")+"' id='"+fetish+"_"+desire+"'"
					+ " style='"+(Main.game.getPlayer().getBaseFetishDesire(fetish)==desire
								?"border:2px solid "+Color.FETISH.getShades()[1]+";"
								:"")+"width:10%; margin:0 5%; float:left; cursor:pointer;'>"
				+ "<div class='square-button-content'>"+(Main.game.getPlayer().getFetishDesire(fetish)==desire?desire.getSVGImage():desire.getSVGImageDesaturated())+"</div>"
				+ (Main.game.getPlayer().hasFetish(fetish) && Main.game.getPlayer().getFetishDesire(fetish)!=desire
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; border-radius:5px;'></div>"
					:Main.game.getPlayer().getFetishDesire(fetish)!=desire
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:5px;'></div>"
						:"")
			+ "</div>";
	}

	public static WorldType worldTypeMap = WorldType.DOMINION;
	public static final DialogueNode MAP = new DialogueNode("Maps", "", true) {

		@Override
		public String getContent() {
			if(worldTypeMap==WorldType.WORLD_MAP) {
				return RenderingEngine.ENGINE.getFullWorldMap();
			} else {
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int i=2;
			for(WorldType world : WorldType.values()) {
				if(world != WorldType.WORLD_MAP
						&& world != WorldType.EMPTY
						&& world != WorldType.MUSEUM
						&& world != WorldType.MUSEUM_LOST) {
					if(index==i) {
						if(worldTypeMap==world) {
							return new Response(Util.capitalizeSentence(world.getName()), "You are already viewing the map of "+world.getName()+".", null);

						} else if(Main.game.getPlayer().getWorldsVisited().contains(world)) {
							return new Response(Util.capitalizeSentence(world.getName()), "View the map of "+world.getName()+".", MAP) {
								@Override
								public void effects() {
									worldTypeMap = world;
								}
							};

						} else {
							return new Response("???", "You haven't discovered this area yet.", null);
						}
					}
					i++;
				}
			}
			if (index == 1) {
				if(worldTypeMap==WorldType.WORLD_MAP) {
					return new Response("World map", "You are already viewing the world map.", null);

				} else if(Main.game.getPlayer().isDiscoveredWorldMap()) {
					return new Response("World map", "Take a look at the world map.", MAP) {
						@Override
						public void effects() {
							worldTypeMap = WorldType.WORLD_MAP;
						}
					};
				} else {
					return new Response("World map", "You haven't discovered the world map yet!", null);
				}

			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
}
