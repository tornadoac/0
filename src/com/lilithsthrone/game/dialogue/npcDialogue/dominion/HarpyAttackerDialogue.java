package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class HarpyAttackerDialogue {

	private static NPC getHarpy() {
		return Main.game.getActiveNPC();
	}
	
	private static boolean isWantsToFight() {
		return getHarpy().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_ONE_FRIENDLY.getMinimumValue();
	}
	
	private static boolean isAffectionHighEnoughToInviteHome() {
		return getHarpy().getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue();
	}
	
	private static void applyPregnancyReactions() {
		if(getHarpy().isVisiblyPregnant()){
			getHarpy().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getHarpy(), true);
		}
	}
	
	private static String getStatus() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'><i>");
		AffectionLevel al = getHarpy().getAffectionLevel(Main.game.getPlayer());
		switch(al) {
			case NEGATIVE_FIVE_LOATHE:
			case NEGATIVE_FOUR_HATE:
			case NEGATIVE_THREE_STRONG_DISLIKE:
			case NEGATIVE_TWO_DISLIKE:
			case NEGATIVE_ONE_ANNOYED:
			case ZERO_NEUTRAL:
				break;
			case POSITIVE_ONE_FRIENDLY:
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] is acting in a <i style='color:"+al.getColour().toWebHexString()+";'>friendly, flirtatious</i> manner towards you.");
				} else {
					sb.append("[npc.Name] is acting in a <i style='color:"+al.getColour().toWebHexString()+";'>friendly</i> manner towards you.");
				}
				break;
			case POSITIVE_TWO_LIKE:
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>likes you</i>, and sees you as more than just a friend.");
				} else {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>likes you</i>, and sees you as a close friend.");
				}
				break;
			case POSITIVE_THREE_CARING:
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>cares about you a lot</i>, and is deeply attracted towards you.");
				} else {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>cares about you a lot</i>, and considers you to be [npc.her] best friend.");
				}
				break;
			case POSITIVE_FOUR_LOVE:
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					sb.append("You can tell from the way that [npc.she] looks at you that [npc.name] <i style='color:"+al.getColour().toWebHexString()+";'>loves you</i>.");
				} else {
					sb.append("You can tell that [npc.name] <i style='color:"+al.getColour().toWebHexString()+";'>loves you</i> in a purely platonic manner.");
				}
				break;
			case POSITIVE_FIVE_WORSHIP:
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] <i style='color:"+al.getColour().toWebHexString()+";'>worships you</i>, and is head-over-heels in love with you.");
				} else {
					sb.append("[npc.Name] <i style='color:"+al.getColour().toWebHexString()+";'>worships you</i>, and would do almost anything you asked of [npc.herHim].");
				}
				break;
		}
		sb.append("</i></p>");
		
		return UtilText.parse(getHarpy(), sb.toString());
	}
	
	public static final DialogueNode HARPY_ATTACKS = new DialogueNode("Assaulted!", "An angry harpy swoops down on you!", true) {
		
		@Override
		public String getLabel(){
			if(Main.game.getActiveNPC().isVisiblyPregnant()) {
				return "Pregnant harpy";
			} else {
				return "Angry harpy";
			}
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean pregnancyReaction = false;
			
			if(getHarpy().getLastTimeEncountered() != -1) {
				
				if(isWantsToFight()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_REPEAT_INTRO", getHarpy()));
					
					if(getHarpy().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getHarpy().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_REPEAT_PREGNANCY_REVEAL", getHarpy()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_REPEAT_STILL_PREGNANT", getHarpy()));
						}
					}

					if(Main.game.getPlayer().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!Main.game.getPlayer().isCharacterReactedToPregnancy(getHarpy())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_REPEAT_PLAYER_PREGNANCY", getHarpy()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_REPEAT_CONTINUED_PLAYER_PREGNANCY", getHarpy()));
						}
					}
					
					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_REPEAT", getHarpy()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_REPEAT_END", getHarpy()));
					
				
				} else { // The mugger doesn't want to attack the player:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL_INTRO", getHarpy()));
					
					if(getHarpy().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getHarpy().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL_PREGNANCY_REVEAL", getHarpy()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL_STILL_PREGNANT", getHarpy()));
						}
					}

					if(Main.game.getPlayer().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!Main.game.getPlayer().isCharacterReactedToPregnancy(getHarpy())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL_PLAYER_PREGNANCY", getHarpy()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL_CONTINUED_PLAYER_PREGNANCY", getHarpy()));
						}
					}
					
					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL", getHarpy()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL_END", getHarpy()));

					UtilText.nodeContentSB.append(getStatus());
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_INTRO", getHarpy()));
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK", getHarpy()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", getHarpy()) {
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<250) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"Offer to pay [npc.name] 250 flames to leave you alone.", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PAY_OFF", getHarpy()));
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-250));
							}
						};
					}
					
				} else if (index == 3) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null) {
									@Override
									public boolean isPlayerAbleToStopSex() {
										return false;
									}
								},
								AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_OFFER_BODY", getHarpy())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
					}
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Talk", "Talk to [npc.name] for a while in order to get to know [npc.herHim] a little better.", HARPY_NEST_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 10));
							
							if(isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<250) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"You don't have enough money to offer [npc.name] any.", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"Offer [npc.name] some money to help [npc.herHim] buy food and clothing.", HARPY_NEST_PEACEFUL_OFFER_MONEY) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-250));
								Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 15));

								if(isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
								}
							}
						};
					}
					
				} else if (index == 3) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (dom)", "Take the dominant role and have sex with [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getHarpy()),
								null,
								null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL_SEX_AS_DOM", getHarpy())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (dom)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} else if (index == 4) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (sub)", "Offer your body to [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PEACEFUL_SEX_AS_SUB", getHarpy())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (sub)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !isAffectionHighEnoughToInviteHome()) {
						return new Response("Offer room",
								"You feel as though it would be best to spend some more time getting to know [npc.name] before inviting [npc.herHim] back to Lilaya's mansion...<br/>"
								+ "[style.italics(Requires [npc.name] to have at least "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+" affection towards you.)]",
								null);
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						return new Response("Offer room",
								"You'll need to get Lilaya's permission before inviting [npc.name] back to her mansion...",
								null);
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						return new Response("Offer room",
								"You don't have a suitable room prepared for [npc.name] to move in to. Upgrade one of the empty rooms in Lilaya's house to a 'Guest Room' first.",
								null);
						
					}else {
						return new Response("Offer room", "Ask [npc.name] if [npc.she] would like a room in Lilaya's mansion.", HARPY_NEST_PEACEFUL_OFFER_ROOM) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								getHarpy().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
							}
						};
					}
					
				} else if (index==10) {
					return new Response("Attack",
							"Betray [npc.namePos] trust and attack [npc.herHim]!<br/>"
									+ "[style.italicsBad(This will devastate [npc.name], causing [npc.herHim] to leave Dominion in despair. (Removing [npc.herHim] from the game.))]",
							HARPY_NEST_PEACEFUL_ATTACK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), -200));
							getHarpy().addFlag(NPCFlagValue.genericNPCBetrayedByPlayer);
							Main.game.getPlayer().incrementKarma(-50); // Why would you make friends with them and then attack them? ;_;
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you're in a rush to be somewhere else, before continuing on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_PEACEFUL_TALK = new DialogueNode("Talk", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_TALK", getHarpy()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_CAN_INVITE_HOME", getHarpy()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getHarpy()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_PEACEFUL_OFFER_MONEY = new DialogueNode("Offer money", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_OFFER_MONEY", getHarpy()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_CAN_INVITE_HOME", getHarpy()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getHarpy()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go and buy food.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_PEACEFUL_OFFER_ROOM = new DialogueNode("Offer room", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_OFFER_ROOM", getHarpy());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Show to room", "Take [npc.name] to [npc.her] new room.", HARPY_NEST_PEACEFUL_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						getHarpy().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(getHarpy());
						Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNode("New Room", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_OFFER_ROOM_BACK_HOME", getHarpy());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Give [npc.name] some time to get settled in [npc.her] new room. You can come back at any time to talk with [npc.herHim].", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HARPY_NEST_PEACEFUL_ATTACK = new DialogueNode("Attack", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_NEST_PEACEFUL_ATTACK", getHarpy());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Start fighting [npc.name]!", getHarpy());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL = new DialogueNode("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(getHarpy())>0) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL", getHarpy());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_NO_ORGASM", getHarpy());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode STORM_ATTACK = new DialogueNode("Attacked!", "A figure jumps out of a nearby doorway!", true) {
		
		@Override
		public String getLabel(){
			return "Assaulted!";
		}

		@Override
		public String getContent() {
			// Storm attackers are different from alley attackers. They are not saved as persistent NPCs, so don't worry about giving any repeat-encounter descriptions.
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "STORM_ATTACK", getHarpy());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Defend yourself against the unwanted advances of [npc.name]!", getHarpy());
				
			} else if (index == 2) {
				return new Response("Offer money",
						"Due to the ongoing arcane storm, [npc.name] isn't interested in your money, and only wants to have sex! You'll have to either fight [npc.herHim] or give [npc.herHim] what [npc.she] wants!",
						null);
				
			} else if (index == 3) {
				return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
						null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(getHarpy()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
							@Override
							public boolean isPlayerAbleToStopSex() {
								return false;
							}
						},
						AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "STORM_ATTACK_OFFER_BODY", getHarpy()));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if((getHarpy().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getHarpy());
				
			} else {
				if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_BETRAYED", getHarpy());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getHarpy());
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if((getHarpy().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Continue", "Leave [npc.name] be and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
								Main.game.banishNPC(getHarpy());
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
							null,
							null),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_SEX", getHarpy()));
					
				} else if (index == 3) {
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getHarpy()));
					
				} else if (index == 4) {
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getHarpy()));
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now... Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getHarpy()));
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getHarpy(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 7 && getHarpy().getLocationPlace().getPlaceType().getEncounterType()!=Encounter.DOMINION_STREET) {
					if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
						return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
						
					} else {
						return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
							@Override
							public void effects() {
								getHarpy().setPlayerKnowsName(true);
								Main.game.getTextEndStringBuilder().append(getHarpy().setAffection(Main.game.getPlayer(), 10));
							}
						};
					}
					
				} else if (index == 8 && getHarpy().isAbleToSelfTransform()) {
					return new Response("Transform [npc.herHim]",
							"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getHarpy());
						}
					};
					
				} else if (index == 10 && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getHarpy()));
							Main.game.banishNPC(getHarpy());
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
								Main.game.banishNPC(getHarpy());
							}
						}
					};
					
				} else if (index == 2) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
							null,
							null),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE", getHarpy()));
					
				} else if (index == 3) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Gentle Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getHarpy()));
					
				} else if (index == 4) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Rough Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH), AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getHarpy()));
					
				} else if (index == 5) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getHarpy(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 7) {
					if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
						return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
						
					} else {
						return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
							@Override
							public void effects() {
								getHarpy().setPlayerKnowsName(true);
								Main.game.getTextEndStringBuilder().append(getHarpy().setAffection(Main.game.getPlayer(), 10));
							}
						};
					}
					
				} else if (index == 8 && getHarpy().isAbleToSelfTransform()) {
					return new Response("Transform [npc.herHim]",
							"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getHarpy());
						}
					};
					
				} else if (index == 10 && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getHarpy()));
							Main.game.banishNPC(getHarpy());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY_TALK = new DialogueNode("Talk", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_TALK", getHarpy());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"Let [npc.name] go.",
						Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		
		Value<String, AbstractItem> potion = null;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {

			if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_BETRAYED", getHarpy());
			}
			
			if(getHarpy().hasTransformationFetish() && getHarpy().isWillingToRape(Main.game.getPlayer()) ) {
				potion = getHarpy().getTransformativePotion(Main.game.getPlayer(), true);
				
//				System.out.println("Potion Check 1"); 
//				System.out.println(potion); 
//				System.out.println(potion.getValue().getName()); 
				
				if(potion == null) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF_FINISHED", getHarpy());
					
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF", getHarpy());
				}
			}
				
			if(getHarpy().isAttractedTo(Main.game.getPlayer()) && getHarpy().isWillingToRape(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_NO_TF_ATTRACTED", getHarpy());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_NO_TF_NOT_ATTRACTED", getHarpy());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
				}
				return null;
			}
			
			if(getHarpy().hasTransformationFetish()
					&& potion != null
					&& getHarpy().isWillingToRape(Main.game.getPlayer())) {
				
//				System.out.println("Potion Check 2"); 
//				System.out.println(potion); 
//				System.out.println(potion.getValue()); 
				
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION_REFUSED);
					}
					
				} else if (index == 2) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					if(potion.getValue().getItemType() == ItemType.FETISH_REFINED) {
						applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_KINK_RECEIVING);
						applicableCorruptionLevel = Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel();
					}
					
					return new Response("Swallow", "Do as you're told and swallow the strange potion.", AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null){
						@Override
						public void effects(){
							Util.Value<String, AbstractItem> potion = getHarpy().getTransformativePotion(Main.game.getPlayer());
							
//							System.out.println("Potion Check 3"); 
//							System.out.println(potion.getValue().getName()); 
//							System.out.println(potion); 
							
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "[npc.Name] steps back, grinning down at you as you obediently swallow the strange liquid."
										+ " [npc.speech(Good [pc.girl]! "+potion.getKey()+")]"
									+ "</p>"
									+ "<p>"
										+getHarpy().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
									+"</p>");
						}
					};
					
				}
				
			} else if(getHarpy().isAttractedTo(Main.game.getPlayer()) && getHarpy().isWillingToRape(Main.game.getPlayer())) {
				
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX", getHarpy()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getHarpy()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getHarpy()));
					
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				}
			}
			
			return null;
			
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION_REFUSED = new DialogueNode("Avoided Transformation", "", true) {

		@Override
		public String getContent() {
			if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_TRANSFORMATION_REFUSED_ATTRACTED", getHarpy());
			
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_TRANSFORMATION_REFUSED_NOT_ATTRACTED", getHarpy());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getHarpy()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getHarpy()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getHarpy()));
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION = new DialogueNode("Transformed", "", true) {

		@Override
		public String getContent() {
			if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_TRANSFORMATION_ATTRACTED", getHarpy());
			
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_TRANSFORMATION_NOT_ATTRACTED", getHarpy());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX", getHarpy()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getHarpy()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getHarpy()));
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if((getHarpy().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY", getHarpy());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getHarpy());
				}
				
			} else {
				if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_RAPE_BETRAYED", getHarpy());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_RAPE", getHarpy());
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getHarpy());
						}
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getHarpy(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getHarpy());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Collapse", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_DEFEAT", getHarpy());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
