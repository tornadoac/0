package com.lilithsthrone.controller.eventListeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;

/**
 * Shows the tooltip at the given element's position.
 *
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public class InventoryTooltipEventListener implements EventListener {
	private GameCharacter owner, equippedToCharacter;
	private AbstractCoreItem coreItem;
	private AbstractItem item;
	private Tattoo tattoo;
	private AbstractItemType genericItem;
	private AbstractWeapon weapon;
	private AbstractWeaponType genericWeapon;
	private DamageType dt;
	private AbstractClothing clothing;
	private Color color;
	private Color secondaryColor;
	private Color tertiaryColor;
	private Pattern pattern;
	private AbstractClothingType genericClothing;
	private AbstractClothing dyeClothing;
	private AbstractWeapon dyeWeapon;
	private InventorySlot invSlot;
	private TFModifier enchantmentModifier;
	private TFPotency potency;
	private TFEssence essence;
	private static StringBuilder tooltipSB = new StringBuilder();

	private static final int LINE_HEIGHT = 14;
	private static final int TOOLTIP_WIDTH = 400;

	@Override
	public void handleEvent(Event event) {
		if (item != null || (coreItem instanceof AbstractItem)) {
			if(coreItem != null) {
				item = (AbstractItem) coreItem;
			}
			itemTooltip(item);

		} else if (weapon != null || (coreItem instanceof AbstractWeapon)) {
			if(coreItem != null) {
				weapon = (AbstractWeapon) coreItem;
			}
			weaponTooltip(weapon);

		} else if (clothing != null || (coreItem instanceof AbstractClothing)) {
			if(coreItem != null) {
				clothing = (AbstractClothing) coreItem;
			}
			clothingTooltip(clothing);

		} else if(tattoo!=null) {
			tattooTooltip(tattoo);

		} else if (dyeClothing != null) {

			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 446);

			Color subtitleColor = dyeClothing.isEnchantmentKnown()?dyeClothing.getRarity().getColor():Color.RARITY_UNKNOWN;

			tooltipSB.setLength(0);
			if(color!=null) {
				tooltipSB.append("<div class='title' style='color:" + subtitleColor.toWebHexString() + ";'>" + Util.capitalizeSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitalizeSentence(color.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>"
						+ dyeClothing.getClothingType().getSVGImage(
								color, InventoryDialogue.dyePreviewSecondary, InventoryDialogue.dyePreviewTertiary,
								InventoryDialogue.dyePreviewPattern,
								InventoryDialogue.dyePreviewPatternPrimary, InventoryDialogue.dyePreviewPatternSecondary, InventoryDialogue.dyePreviewPatternTertiary)
						+ "</div>");

			} else if(secondaryColor!=null) {
				tooltipSB.append("<div class='title' style='color:" + subtitleColor.toWebHexString() + ";'>" + Util.capitalizeSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitalizeSentence(secondaryColor.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>"
						+ dyeClothing.getClothingType().getSVGImage(
								InventoryDialogue.dyePreviewPrimary, secondaryColor, InventoryDialogue.dyePreviewTertiary,
								InventoryDialogue.dyePreviewPattern,
								InventoryDialogue.dyePreviewPatternPrimary, InventoryDialogue.dyePreviewPatternSecondary, InventoryDialogue.dyePreviewPatternTertiary)
						+ "</div>");

			} else if(tertiaryColor!=null) {
				tooltipSB.append("<div class='title' style='color:" + subtitleColor.toWebHexString() + ";'>" + Util.capitalizeSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitalizeSentence(tertiaryColor.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>"
						+ dyeClothing.getClothingType().getSVGImage(
								InventoryDialogue.dyePreviewPrimary, InventoryDialogue.dyePreviewSecondary, tertiaryColor,
								InventoryDialogue.dyePreviewPattern,
								InventoryDialogue.dyePreviewPatternPrimary, InventoryDialogue.dyePreviewPatternSecondary, InventoryDialogue.dyePreviewPatternTertiary)
						+ "</div>");

			} else if(pattern!=null) {
				tooltipSB.append("<div class='title' style='color:" + subtitleColor.toWebHexString() + ";'>" + Util.capitalizeSentence(dyeClothing.getName()) + "</div>"

						+ "<div class='subTitle'>" + Util.capitalizeSentence(pattern.getNiceName()) + "</div>"

						+ "<div class='picture full' style='position:relative;'>" + dyeClothing.getClothingType().getSVGImage(
								InventoryDialogue.dyePreviewPrimary, InventoryDialogue.dyePreviewSecondary, InventoryDialogue.dyePreviewTertiary,
								pattern.getName(),
								InventoryDialogue.dyePreviewPatternPrimary, InventoryDialogue.dyePreviewPatternSecondary, InventoryDialogue.dyePreviewPatternTertiary)
						+ "</div>");

			}

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (dyeWeapon != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 446);

			tooltipSB.setLength(0);

			if(color!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeWeapon.getRarity().getColor().toWebHexString() + ";'>" + Util.capitalizeSentence(dyeWeapon.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitalizeSentence(color.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>" + dyeWeapon.getWeaponType().getSVGImage(dyeWeapon.getDamageType(), color, InventoryDialogue.dyePreviewSecondary) + "</div>");

			} else if(secondaryColor!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeWeapon.getRarity().getColor().toWebHexString() + ";'>" + Util.capitalizeSentence(dyeWeapon.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitalizeSentence(secondaryColor.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>" + dyeWeapon.getWeaponType().getSVGImage(dyeWeapon.getDamageType(), InventoryDialogue.dyePreviewPrimary, secondaryColor) + "</div>");

			}

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericItem != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 416);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitalizeSentence(genericItem.getName(true)) + "</div>"

					+ "<div class='picture full' style='position:relative;'>" + genericItem.getSVGString() + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericClothing != null) {

			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 446);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + genericClothing.getRarity().getColor().toWebHexString() + ";'>" + Util.capitalizeSentence(genericClothing.getName()) + "</div>"

					+ "<div class='subTitle'>" + Util.capitalizeSentence(color.getName()) + "</div>"

					+ "<div class='picture full' style='position:relative;'>" + genericClothing.getSVGImage(color,
							genericClothing.getAvailableSecondaryColors().isEmpty()?null:genericClothing.getAvailableSecondaryColors().get(0),
							genericClothing.getAvailableTertiaryColors().isEmpty()?null:genericClothing.getAvailableTertiaryColors().get(0),
							null, null, null, null) + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericWeapon != null) {

			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 446);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + genericWeapon.getRarity().getColor().toWebHexString() + ";'>" + Util.capitalizeSentence(genericWeapon.getName()) + "</div>"

					+ "<div class='subTitle'>" + Util.capitalizeSentence(dt.getName()) + "</div>"

					+ "<div class='picture full'>" + genericWeapon.getSVGImage(dt, null, null) + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (invSlot != null) {
			if (invSlot == InventorySlot.WEAPON_MAIN) {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getMainWeapon() == null) {
						Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
						Main.mainController.setTooltipContent("<div class='title'>Primary Weapon</div>");

					} else {
						weaponTooltip(equippedToCharacter.getMainWeapon());
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Primary Weapon</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_OFFHAND) {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getOffhandWeapon() == null) {
						Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
						Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon</div>");

					} else {
						weaponTooltip(equippedToCharacter.getOffhandWeapon());
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon</div>");
				}

			} else {
				if (equippedToCharacter != null) {

					boolean renderingTattoos = false;

					if((equippedToCharacter.isPlayer() && RenderingEngine.ENGINE.isRenderingTattoosLeft())
							|| (!equippedToCharacter.isPlayer() && RenderingEngine.ENGINE.isRenderingTattoosRight())) {
						renderingTattoos = true;
					}

					if ((!renderingTattoos && equippedToCharacter.getClothingInSlot(invSlot)==null)
							|| (renderingTattoos && equippedToCharacter.getTattooInSlot(invSlot)==null)) {

						List<String> clothingBlockingThisSlot = new ArrayList<>();
						for (AbstractClothing c : equippedToCharacter.getClothingCurrentlyEquipped()) {
							if (c.getClothingType().getIncompatibleSlots(equippedToCharacter).contains(invSlot)) {
								clothingBlockingThisSlot.add(c.getName());
							}
						}

						BodyPartClothingBlock block = invSlot.getBodyPartClothingBlock(equippedToCharacter);

						if (!renderingTattoos && !clothingBlockingThisSlot.isEmpty()) {
							setBlockedTooltipContent(UtilText.parse(equippedToCharacter, "This slot is currently <b style='color:" + Color.SEALED.toWebHexString() + ";'>blocked</b> by [npc.namePos] ")
									+ Util.stringsToStringList(clothingBlockingThisSlot, false) + ".");

						} else if (!renderingTattoos && block != null) {
							setBlockedTooltipContent("<span style='color:" + Color.GENERIC_MINOR_BAD.toWebHexString() + ";'>Restricted!</span>", UtilText.parse(equippedToCharacter, block.getDescription()));

						} else {

							boolean piercingBlocked=false;
							boolean bypassesPiercing = !equippedToCharacter.getBody().getBodyMaterial().isRequiresPiercing();

							switch(invSlot){
								case PIERCING_VAGINA:
									if(equippedToCharacter.getVaginaType()==VaginaType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"You don't have a vagina.",
													(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
														?"[npc.Name] doesn't have a vagina."
														:"You don't know if [npc.name] has a vagina.")));
										piercingBlocked=true;

									} else if(!bypassesPiercing && !equippedToCharacter.isPiercedVagina()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"Your vagina has not been pierced.",
												(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
														?"[npc.NamePos] vagina has not been pierced."
														:"You don't know if [npc.name] has a vagina.")));
										piercingBlocked=true;
									}
									break;

								case PIERCING_EAR:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedEar()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your ears have not been pierced.",
													"[npc.NamePos] ears have not been pierced."));
											piercingBlocked=true;
										}
									}
									break;

								case PIERCING_LIP:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedLip()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your lips have not been pierced.",
													"[npc.NamePos] lips have not been pierced."));
											piercingBlocked=true;
										}
									}
									break;

								case PIERCING_NIPPLE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNipple()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your nipples have not been pierced.",
													(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer())
															?"[npc.NamePos] nipples have not been pierced."
															:"You don't know if [npc.namePos] nipples have been pierced.")));
											piercingBlocked=true;
										}
									}
									break;

								case PIERCING_NOSE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNose()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your nose has not been pierced.",
													"[npc.NamePos] nose has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;

								case PIERCING_PENIS:
									if(equippedToCharacter.getPenisType()==PenisType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a penis.",
												(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
														?"[npc.Name] doesn't have a penis."
														:"You don't know if [npc.name] has a penis.")));
										piercingBlocked=true;

									} else if(!bypassesPiercing && !equippedToCharacter.isPiercedPenis()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"Your penis has not been pierced.",
												(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
														?"[npc.NamePos] penis has not been pierced."
														:"You don't know if [npc.name] has a penis.")));
										piercingBlocked=true;
									}
									break;

								case PIERCING_STOMACH:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNavel()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your navel has not been pierced.",
													"[npc.NamePos] navel has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;

								case PIERCING_TONGUE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedTongue()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your tongue has not been pierced.",
													"[npc.NamePos] tongue has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;

								case HORNS:
									if(equippedToCharacter.getHornType()==HornType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have any horns.",
												"[npc.Name] doesn't have any horns."));
										piercingBlocked=true;
									}
									break;

								case PENIS:
									if(!equippedToCharacter.hasPenisIgnoreDildo()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a penis.",
												"[npc.Name] doesn't have a penis."));
										piercingBlocked=true;
									}
									break;

								case TAIL:
									if(equippedToCharacter.getTailType()==TailType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a tail.",
												"[npc.Name] doesn't have a tail."));
										piercingBlocked=true;
									}
									break;

								case VAGINA:
									if(!equippedToCharacter.hasVagina()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a vagina.",
												"[npc.Name] doesn't have a vagina."));
										piercingBlocked=true;
									}
									break;

								case WINGS:
									if(equippedToCharacter.getWingType()==WingType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have any wings.",
												"[npc.Name] doesn't have any wings."));
										piercingBlocked=true;
									}
									break;

								default:
									break;
							}

							if(!piercingBlocked){
								if(renderingTattoos) {
									scarTooltip(equippedToCharacter.getScarInSlot(invSlot));

								} else {
									setEmptyInventorySlotTooltipContent();
								}
							}
						}

					} else {
						if(renderingTattoos) {
							tattooTooltip(equippedToCharacter.getTattooInSlot(invSlot));
						} else {
							clothingTooltip(equippedToCharacter.getClothingInSlot(invSlot));
						}
					}

				} else {
					setEmptyInventorySlotTooltipContent();
				}
			}

		} else if (enchantmentModifier != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 152);
			Main.mainController.setTooltipContent(UtilText.parse(
					"<div class='title' style='color:"+enchantmentModifier.getRarity().getColor().toWebHexString()+";'>"
							+ Util.capitalizeSentence(enchantmentModifier.getName())
					+ "</div>"
					+ "<div class='description' style='height:48px'>"
					+ UtilText.parse(enchantmentModifier.getDescription())
					+ "</div>"
					+ "<div class='subTitle'>"
					+(EnchantmentDialogue.getIngredient() instanceof Tattoo
							? UtilText.formatAsMoney(enchantmentModifier.getValue()*EnchantingUtils.FLAME_COST_MODIFER, "b")+" cost"
							: UtilText.formatAsEssences(enchantmentModifier.getValue(), "b", false)+" essence cost")
					+ "</div>"));

		} else if(potency!=null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>Set potency to <b style='color:"+potency.getColor().toWebHexString()+";'>" + Util.capitalizeSentence(potency.getName()) + "</b></div>"));

		} else if (essence != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'><b style='color:"+essence.getColor().toWebHexString()+";'>" + Util.capitalizeSentence(essence.getName()) + "</b> essence</div>"));

		}  else {
			return;
		}

		TooltipUpdateThread.updateToolTip(-1,-1);
		// Main.mainController.getTooltip().show(Main.primaryStage);
	}


	private void setBlockedTooltipContent(String description){
		setBlockedTooltipContent("<span style='color:" + Color.GENERIC_BAD.toWebHexString() + ";'>Blocked!</span>", description);
	}
	private void setBlockedTooltipContent(String title, String description){
		boolean dirty = equippedToCharacter.isDirtySlot(invSlot);
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 144);
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter,
				"<div class='title'>"
						+ Util.capitalizeSentence(invSlot.getName())+ ": "+title
				+ "</div>"
				+"<div class='description' style='height:72px; text-align:center;'>"
					+ (dirty
						?"[npc.NamePos] "+invSlot.getName()+" "+(invSlot.isPlural()?"are":"is")
								+ " <span style='color:"+Color.CUM.toWebHexString()+";'>dirty</span>!<br/>"
						:"")
					 + UtilText.parse(description)
				 +"</div>"));
	}

	private void setEmptyInventorySlotTooltipContent(){
		boolean dirty = equippedToCharacter.isDirtySlot(invSlot);
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60+(dirty?56:0));
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter,
				"<div class='title'>"
						+ Util.capitalizeSentence(invSlot.getName())
				+ "</div>"
				+ (dirty
					?"<div class='description' style='height:48px; text-align:center;'>"
							+ "[npc.NamePos] "+invSlot.getName()+" "+(invSlot.isPlural()?"have":"has")
							+ " been <span style='color:"+Color.CUM.toWebHexString()+";'>dirtied</span> by sexual fluids!"
						+ "</div>"
					:"")));
	}

	public InventoryTooltipEventListener setCoreItem(AbstractCoreItem coreItem, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.coreItem = coreItem;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}

	public InventoryTooltipEventListener setItem(AbstractItem item, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.item = item;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}

	public InventoryTooltipEventListener setTattoo(InventorySlot invSlot, Tattoo tattoo, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.invSlot = invSlot;
		this.tattoo = tattoo;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}

	public InventoryTooltipEventListener setGenericItem(AbstractItemType genericItem) {
		resetVariables();
		this.genericItem = genericItem;
		return this;
	}

	public InventoryTooltipEventListener setClothing(AbstractClothing clothing, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.clothing = clothing;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}

	public InventoryTooltipEventListener setDyeClothingPrimary(AbstractClothing dyeClothing, Color color) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.color = color;
		return this;
	}

	public InventoryTooltipEventListener setDyeClothingSecondary(AbstractClothing dyeClothing, Color secondaryColor) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.secondaryColor = secondaryColor;
		return this;
	}

	public InventoryTooltipEventListener setDyeClothingTertiary(AbstractClothing dyeClothing, Color tertiaryColor) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.tertiaryColor = tertiaryColor;
		return this;
	}

	public InventoryTooltipEventListener setDyeWeaponPrimary(AbstractWeapon dyeWeapon, Color color) {
		resetVariables();
		this.dyeWeapon = dyeWeapon;
		this.color = color;
		return this;
	}

	public InventoryTooltipEventListener setDyeWeaponSecondary(AbstractWeapon dyeWeapon, Color secondaryColor) {
		resetVariables();
		this.dyeWeapon = dyeWeapon;
		this.secondaryColor = secondaryColor;
		return this;
	}

	public InventoryTooltipEventListener setDyeClothingPattern(AbstractClothing dyeClothing, Pattern pattern) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.pattern = pattern;
		return this;
	}

	public InventoryTooltipEventListener setGenericClothing(AbstractClothingType genericClothing, Color color) {
		resetVariables();
		this.genericClothing = genericClothing;
		this.color = color;
		return this;
	}

	public InventoryTooltipEventListener setGenericWeapon(AbstractWeaponType genericWeapon, DamageType dt) {
		resetVariables();
		this.genericWeapon = genericWeapon;
		this.dt = dt;
		return this;
	}

	public InventoryTooltipEventListener setWeapon(AbstractWeapon weapon, GameCharacter owner, boolean isEquipped) {
		resetVariables();
		this.weapon = weapon;
		if(isEquipped) {
			this.equippedToCharacter = owner;
		}
		this.owner = owner;
		return this;
	}

	public InventoryTooltipEventListener setInventorySlot(InventorySlot invSlot, GameCharacter equippedToCharacter) {
		resetVariables();
		this.invSlot = invSlot;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = equippedToCharacter;
		return this;
	}

	public InventoryTooltipEventListener setTFModifier(TFModifier enchantmentModifier) {
		resetVariables();
		this.enchantmentModifier = enchantmentModifier;
		return this;
	}

	public InventoryTooltipEventListener setTFPotency(TFPotency potency) {
		resetVariables();
		this.potency = potency;
		return this;
	}

	public InventoryTooltipEventListener setEssence(TFEssence essence) {
		resetVariables();
		this.essence = essence;
		return this;
	}

	private void resetVariables() {
		owner = null;
		equippedToCharacter = null;
		coreItem = null;
		item = null;
		tattoo = null;
		genericItem = null;
		weapon = null;
		genericWeapon = null;
		dt = null;
		clothing = null;
		color = null;
		dyeClothing = null;
		dyeWeapon = null;
		secondaryColor = null;
		tertiaryColor = null;
		pattern = null;
		genericClothing = null;
		invSlot = null;
		enchantmentModifier = null;
		potency = null;
		essence = null;
	}

	private void itemTooltip(AbstractItem absItem) {

		int yIncrease = 0;
		int listIncrease = 0;

		if(!absItem.getEffects().isEmpty()) {
			listIncrease+=1;
			for(ItemEffect ie : absItem.getEffects()) {
				listIncrease += ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size();
			}
		}

		yIncrease += Math.max(0, listIncrease-3);

		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitalizeSentence(absItem.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-full-width titular'>"
				+ (absItem.isConsumedOnUse()
						? "<span style='color:" + Color.GENERIC_BAD.toWebHexString() + ";'>Consumed on use</span>"
						: "<span style='color:" + Color.GENERIC_GOOD.toWebHexString() + ";'>Infinite uses</span>")
				+ "</div>"
//				+ "<div class='container-half-width titular'>"
//					+ "<span style='color:" + absItem.getRarity().getColor().toWebHexString() + ";'>"+Util.capitalizeSentence(absItem.getDisplayRarity())+"</span>"
//				+ "</div>"
					);

		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>"
				+ "<span style='color:" + absItem.getRarity().getColor().toWebHexString() + ";'>"+Util.capitalizeSentence(absItem.getDisplayRarity())+"</span>"
				);

		for(ItemEffect ie : absItem.getEffects()) {
			for(int i=0; i<ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size(); i++) {
				tooltipSB.append("</br>"+ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).get(i));
			}
		}

		tooltipSB.append("</div>");

		// Picture:
		tooltipSB.append("<div class='item-image'>"
								+ "<div class='item-image-content'>"
									+(absItem.getSVGString())
								+ "</div>"
							+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>"
						+ absItem.getDescription()
					+ "</div>");

		// Value:

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absItem)) {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+UtilText.formatAsMoney(absItem.getValue())
										+" | "
										+ InventoryDialogue.getInventoryNPC().getName("The") + " offers: " + UtilText.formatAsMoney(absItem.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+UtilText.formatAsMoney(absItem.getValue())
										+" | "
										+ "<span style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+UtilText.formatAsMoney(absItem.getValue())
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney( + getBuybackPriceFor(absItem))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+UtilText.formatAsMoney(absItem.getValue())
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absItem.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='container-full-width titular'>" + "Value: "+UtilText.formatAsMoney(absItem.getValue()) + "</div>");
		}

		tooltipSB.append("</body>");

		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 364 + (yIncrease * LINE_HEIGHT));
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

	}

	private void weaponTooltip(AbstractWeapon absWep) {

		int yIncrease = 0;
		int listIncrease = 2 + absWep.getAttributeModifiers().size();
		listIncrease += absWep.getSpells().size();

		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitalizeSentence(absWep.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular' style='color:"+absWep.getDamageType().getMultiplierAttribute().getColor().toWebHexString()+";'>" + Util.capitalizeSentence(absWep.getDamageType().getName()) + " damage</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (absWep.getWeaponType().getClothingSet() == null
								? "<span style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>"
								: "<span style='color:" + Color.RARITY_EPIC.toWebHexString() + ";'>"+absWep.getWeaponType().getClothingSet().getName() + " set</span>")
						+ "</div>");

		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>"
				+ "<span style='color:" + absWep.getRarity().getColor().toWebHexString() + ";'>"+Util.capitalizeSentence(absWep.getDisplayRarity())+"</span></br>"
				+ (absWep.getWeaponType().isTwoHanded()? "Two-handed" : "One-handed")+"</br>"
				);

		int cost = absWep.getWeaponType().getArcaneCost();
		if(cost>0) {
			listIncrease++;
			tooltipSB.append("Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] "+(absWep.getWeaponType().isMelee()?"per attack":"to fire")+"<br/>");
		}

		if (equippedToCharacter != null) {
			tooltipSB.append("<b>"
								+ Attack.getMinimumDamage(equippedToCharacter, null, Attack.MAIN, absWep) + " - " + Attack.getMaximumDamage(equippedToCharacter, null, Attack.MAIN, absWep)
							+ "</b>"
							+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColor().toWebHexString() + ";'>Damage</b>");
		} else {
			if(owner!=null && !owner.isPlayer()) {
				listIncrease++;
				tooltipSB.append(UtilText.parse(owner, "[npc.Name]: ")
					+"<b>"
						+ Attack.getMinimumDamage(owner, null, Attack.MAIN, absWep) + " - " + Attack.getMaximumDamage(owner, null, Attack.MAIN, absWep)
					+ "</b>"
					+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColor().toWebHexString() + ";'>Damage</b><br/>"
					+ "You: ");
			}
			tooltipSB.append("<b>"
								+ Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWep) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWep)
							+ "</b>"
							+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColor().toWebHexString() + ";'>Damage</b>");
		}

//		if (absWep.getEffects().size() != 0) { TODO enchanting effects
//			for (ItemEffect e : absWep.getEffects()) {
//				for(String s : e.getEffectsDescription(owner, owner)) {
//					tooltipSB.append("<br/>"+ s);
//				}
//			}
			for(Entry<Attribute, Integer> entry : absWep.getAttributeModifiers().entrySet()) {
				tooltipSB.append("<br/>"+
						(entry.getValue()<0
								?"[style.boldBad("+entry.getValue()+")] "
								:"[style.boldGood(+"+entry.getValue()+")] ")
						+ "<b style='color:"+entry.getKey().getColor().toWebHexString()+";'>"+Util.capitalizeSentence(entry.getKey().getName())+"</b>");
			}
//		} else {
//			tooltipSB.append("<br/>[style.colorDisabled(No bonuses)]");
//		}

			for(Spell s : absWep.getSpells()) {
				tooltipSB.append("<br/><b style='color:"+Color.DAMAGE_TYPE_SPELL.toWebHexString()+";'>Grants Spell</b><b>:</b> <b style='color:"+s.getSpellSchool().getColor().toWebHexString()+";'>"+Util.capitalizeSentence(s.getName())+"</b>");
			}

		tooltipSB.append("</div>");

		// Picture:
		tooltipSB.append("<div class='item-image'>"
							+ "<div class='item-image-content'>"
								+ (owner!=null && (absWep.equals(owner.getMainWeapon()) || absWep.equals(owner.getOffhandWeapon()))?absWep.getSVGEquippedString(owner):absWep.getSVGString())
							+ "</div>"
						+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>"
						+ absWep.getWeaponType().getDescription()
					+ "</div>");

		// Value:

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absWep)) {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
										+" | "
										+ InventoryDialogue.getInventoryNPC().getName("The") + " offers: " + UtilText.formatAsMoney(absWep.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
										+" | "
										+ "<span style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney( + getBuybackPriceFor(absWep))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absWep.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='container-full-width titular'>" + "Value: "+UtilText.formatAsMoney(absWep.getValue()) + "</div>");
		}

		tooltipSB.append("</body>");

		yIncrease += Math.max(0, listIncrease-3);
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 364 + (yIncrease * LINE_HEIGHT));
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

	}

	private void clothingTooltip(AbstractClothing absClothing) {
		int yIncrease = 0;

		int listIncrease = 1 + absClothing.getAttributeModifiers().size();

		yIncrease += absClothing.getExtraDescriptions(equippedToCharacter).size();

		for(ItemEffect ie : absClothing.getEffects()) {
			if(ie.getPrimaryModifier()==TFModifier.CLOTHING_ENSLAVEMENT
					|| ie.getPrimaryModifier()==TFModifier.CLOTHING_SEALING) {
				listIncrease+=1;
			} else if(ie.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && ie.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				listIncrease+=2;
			}
		}
		yIncrease += Math.max(0, listIncrease-4);

		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitalizeSentence(absClothing.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular'>" + Util.capitalizeSentence(absClothing.getClothingType().getSlot().getName()) + "</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (absClothing.getClothingType().getClothingSet() == null
								? "<span style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>"
								: "<span style='color:" + Color.RARITY_EPIC.toWebHexString() + ";'>"+absClothing.getClothingType().getClothingSet().getName() + " set</span>")
						+ "</div>");

		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>");
		int res = absClothing.getClothingType().getPhysicalResistance();
		tooltipSB.append(
				"<span style='color:" + absClothing.getRarity().getColor().toWebHexString() + ";'>"+Util.capitalizeSentence(absClothing.getDisplayRarity())+"</span></br>"
				+ (res>0
					?"[style.boldGood(+"+absClothing.getClothingType().getPhysicalResistance()+")]"
					:"[style.boldDisabled(0)]")
				+" [style.boldResPhysical("+Util.capitalizeSentence(Attribute.RESISTANCE_PHYSICAL.getName())+")]");

		if (!absClothing.getEffects().isEmpty()) {
			if (!absClothing.isEnchantmentKnown()) {
				tooltipSB.append("<br/>[style.colorDisabled(Unidentified effects!)]");
			} else {
				for (ItemEffect e : absClothing.getEffects()) {
					if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && e.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
						for(String s : e.getEffectsDescription(owner, owner)) {
							tooltipSB.append("<br/>"+ s);
						}
					}
				}
				for(Entry<Attribute, Integer> entry : absClothing.getAttributeModifiers().entrySet()) {
					tooltipSB.append("<br/>"+
							(entry.getValue()<0
									?"[style.boldBad("+entry.getValue()+")] "
									:"[style.boldGood(+"+entry.getValue()+")] ")
							+ "<b style='color:"+entry.getKey().getColor().toWebHexString()+";'>"+Util.capitalizeSentence(entry.getKey().getName())+"</b>");
				}
			}

		} else {
			tooltipSB.append("<br/>[style.colorDisabled(No bonuses)]");
		}

		tooltipSB.append("</div>");

		// Picture:
		tooltipSB.append("<div class='item-image'>"
							+ "<div class='item-image-content'>"
								+ (owner!=null && owner.getClothingCurrentlyEquipped().contains(absClothing)?absClothing.getSVGEquippedString(owner):absClothing.getSVGString())
							+ "</div>"
						+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>"
						+ absClothing.getTypeDescription()
					+ "</div>");

		tooltipSB.append("<div class='container-full-width titular'>");
		if (absClothing.getExtraDescriptions(equippedToCharacter).isEmpty()) {
			tooltipSB.append("<span style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>No Status</span>");
		} else {
			tooltipSB.append("<b>Status</b>");
			for (String s : absClothing.getExtraDescriptions(equippedToCharacter)) {
				tooltipSB.append("<br/>" + s);
			}
		}
		tooltipSB.append("</div>");

		// Value:

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absClothing)) {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
										+" | "
										+ InventoryDialogue.getInventoryNPC().getName("The") + " offers " + UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
										+" | "
										+ "<span style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney( + getBuybackPriceFor(absClothing))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='container-full-width titular'>Value: "+ (absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b")) + "</div>");
		}

		tooltipSB.append("</body>");

		int specialIncrease = 0;
		if(absClothing.getDisplayName(false).length()>40) {
			specialIncrease = 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 400 + (yIncrease * LINE_HEIGHT) + specialIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

	}

	private void scarTooltip(Scar scar) {
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>No tattoo</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular'>" + Util.capitalizeSentence(invSlot.getTattooSlotName()) + "</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (scar==null
									? "<span style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>No scars</span>"
									: "<span style='color:" + Color.SCAR.toWebHexString() + ";'>"+Util.capitalizeSentence(owner.getScarInSlot(invSlot).getName())+"</span>")
						+ "</div>");

		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 88);
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
	}

	private void tattooTooltip(Tattoo tattoo) {
		int yIncrease = 0;

		if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
			yIncrease++;
		}
		if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
			yIncrease++;
		}
		int lSize=0;
		for (ItemEffect e : tattoo.getEffects()) {
			if(e.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE
					|| e.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| e.getPrimaryModifier()==TFModifier.TF_MOD_FETISH_BEHAVIOR
					|| e.getPrimaryModifier()==TFModifier.TF_MOD_FETISH_BODY_PART) {
				lSize++;
			} else {
				lSize+=2;
			}
		}
		lSize-=4;
		if(lSize<0) {
			lSize=0;
		}

		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitalizeSentence(tattoo.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular'>" + (invSlot.getTattooSlotName()==null?"[style.colorDisabled(Cannot be tattooed)]":Util.capitalizeSentence(invSlot.getTattooSlotName())) + "</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (owner.getScarInSlot(invSlot)==null
									? "<span style='color:" + Color.TEXT_GREY.toWebHexString() + ";'>No scars</span>"
									: "<span style='color:" + Color.SCAR.toWebHexString() + ";'>"+Util.capitalizeSentence(owner.getScarInSlot(invSlot).getName())+"</span>")
						+ "</div>");

		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>");

		if (!tattoo.getEffects().isEmpty()) {
			int i=0;
			for (ItemEffect e : tattoo.getEffects()) {
				if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && e.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
					for(String s : e.getEffectsDescription(owner, owner)) {
						tooltipSB.append((i>0?"<br/>"+s:s));
					}
					i++;
				}
			}
			for(Entry<Attribute, Integer> entry : tattoo.getAttributeModifiers().entrySet()) {
				tooltipSB.append((i>0?"<br/>":"")
						+ (entry.getValue()<0
								?"[style.boldBad("+entry.getValue()+")] "
								:"[style.boldGood(+"+entry.getValue()+")] ")
						+ "<b style='color:"+entry.getKey().getColor().toWebHexString()+";'>"+Util.capitalizeSentence(entry.getKey().getName())+"</b>");
				i++;
			}

		} else {
			tooltipSB.append("[style.colorDisabled(No bonuses)]");
		}

		tooltipSB.append("</div>");

		// Picture:
		tooltipSB.append("<div class='container-half-width' style='width:calc(33.3% - 16px);'>"
						+ tattoo.getSVGImage(equippedToCharacter)
					+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>"
						+ tattoo.getType().getDescription()
						+"</div>");

			if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
					tooltipSB.append("<div class='container-full-width' style='padding:8px; height:54px; text-align:center;'>");
					if(tattoo.getWriting().getStyles().isEmpty()) {
						tooltipSB.append("Normal,");
					} else {
						int i=0;
						for(TattooWritingStyle style : tattoo.getWriting().getStyles()) {
							tooltipSB.append(i==0?Util.capitalizeSentence(style.getName()):", "+style.getName());
							i++;
						}
					}
					tooltipSB.append(" "+tattoo.getWriting().getColor().getName()+" text reads:<br/>");
					tooltipSB.append(tattoo.getFormattedWritingOutput()
							+ "</div>");
			} else {
				tooltipSB.append(
						"<div class='container-full-width' style='padding:8px; height:28px; text-align:center;'>"
							+"[style.colorDisabled(This tattoo doesn't have any writing.)]"
						+ "</div>");
			}

			if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
				tooltipSB.append("<div class='container-full-width' style='padding:8px; height:68px; text-align:center;'>"
									+ "An enchanted, "+tattoo.getCounter().getColor().getName()+" "+tattoo.getCounter().getType().getName()+" counter reads:<br/>"
										+ "<span style='color:"+tattoo.getCounter().getColor().toWebHexString()+";'>"
												+tattoo.getFormattedCounterOutput(equippedToCharacter)
										+"</span>"
								+ "</div>");
			} else {
				tooltipSB.append(
						"<div class='container-full-width' style='padding:8px; height:28px; text-align:center;'>"
							+"[style.colorDisabled(This tattoo doesn't have a counter.)]"
						+ "</div>");
			}

		tooltipSB.append("</div>");

		tooltipSB.append("</body>");

		int specialIncrease = 0;
		if(tattoo.getDisplayName(false).length()>40) {
			specialIncrease = 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 410 + ((lSize+yIncrease) * LINE_HEIGHT) + yIncrease*8 + specialIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
	}

	private int getBuybackPriceFor(AbstractCoreItem item) {
		for (ShopTransaction s : Main.game.getPlayer().getBuybackStack()) {
			if (s.getAbstractItemSold() == item) {
				return s.getPrice();
			}
		}
		throw new IllegalArgumentException("That's not a buyback item");
	}

	private static String getTooltipText(GameCharacter character, String playerText, String NPCText) {
		if(character.isPlayer()) {
			return playerText;
		} else {
			return UtilText.parse(character, NPCText);
		}
	}
}
