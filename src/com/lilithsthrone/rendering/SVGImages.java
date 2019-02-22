package com.lilithsthrone.rendering;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum SVGImages {
	SVG_IMAGE_PROVIDER;

	private String

			displacedIcon, concealedIcon, cummedInIcon, feminineWarningIcon, masculineWarningIcon, jinxedIcon, tattooSwitchTattoo, tattooSwitchClothing, scarIcon,

			menuIcon,
			inventoryIcon, inventoryIconDisabled,
			questInventoryIcon, questInventoryIconDisabled,
			journalIcon, peopleIcon, zoomInIcon, zoomOutIcon, copyIcon, exportIcon, calendarIcon, informationIcon, addIcon,

			diskSave, diskSaveDisabled, diskSaveConfirm, diskOverwrite, diskLoad, diskLoadConfirm, diskLoadDisabled, diskDelete, diskDeleteConfirm,

			itemsOnFloorIcon,

			drinkSmall, drink,

			dice1, dice2, dice3, dice4, dice5, dice6, diceGlow,

			playerMapIconMasculine,
			playerMapIconAndrogynous,
			playerMapIconFeminine,
			playerMapDangerousIcon,

			raceBackground, raceBackgroundHalf, raceBackgroundSlime, raceBackgroundDemon, raceUnknown, raceDobermann, raceDobermannDesaturated,

			perkTreeArrow, spellOverlay,

			weatherDayClear, weatherDayCloud, weatherDayRain, weatherDaySnow, weatherDayStormIncoming, weatherDayStorm, weatherDayStormProtected,
			weatherNightClear, weatherNightCloud, weatherNightRain, weatherNightSnow, weatherNightStormIncoming, weatherNightStorm, weatherNightStormProtected,

			womensWatchHourHand, womensWatchMinuteHand, mensWatchHourHand, mensWatchMinuteHand,

			protectionEnabled, protectionDisabled, tattoo,

			responseCombat, responseSex, responseLocked, responseUnlocked, responseUnlockedDisabled, responseOption, responseOptionDisabled, responseCorruptionBypass,
			responseSubResist, responseSubNormal, responseSubEager,
			responseDomGentle, responseDomNormal, responseDomRough,

			NPCWarningMale, NPCWarningFemale, NPCWarningDemon,

			stopwatch,

			counterZero, counterOne, counterTwo, counterThree, counterFour, counterFive, counterFivePlus,
			counterZeroDisabled, counterOneDisabled, counterTwoDisabled, counterThreeDisabled, counterFourDisabled, counterFiveDisabled, counterFivePlusDisabled,

			scaleZero, scaleOne, scaleTwo, scaleThree, scaleFour,
			scaleZeroDisabled, scaleOneDisabled, scaleTwoDisabled, scaleThreeDisabled, scaleFourDisabled,

			slaveBuy, slaveBuyDisabled, slaveSell, slaveSellDisabled, slaveInspect, slaveInspectDisabled, slaveJob, slaveJobDisabled,
			slavePermissionsDisabled, slavePermissions, slaveTransfer, slaveTransferDisabled, slaveCosmetics, slaveCosmeticsDisabled,

			transactionBuy, transactionBuyDisabled, transactionBid, transactionBidDisabled, transactionSell, transactionSellDisabled,

			// Effects:
			creampie, creampieMasochist,
			fluidIngested, fluidIngestedMasochist,

			// Items:

			hypnoWatchBase, hypnoWatchGynephilic, hypnoWatchAmbiphilic, hypnoWatchAndrophilic,

			// Sex:
			coverableAreaMouth,
			coverableAreaAnus, coverableAreaAss,
			coverableAreaBreasts, coverableAreaBreastsFlat, coverableAreaNipple,
			coverableAreaBreastsCrotch, coverableAreaUdders, coverableAreaNippleCrotch,
			coverableAreaVagina, coverableAreaUrethraVagina,
			coverableAreaMound,
			coverableAreaThighs,
			coverableAreaUrethraPenis,

			penetrationTypeFinger, penetrationTypePenis, penetrationTypeTail, penetrationTypeTongue, penetrationTypeFoot,
			combinationStretching, combinationTooLoose, combinationWet, combinationDry,
			stretching, holeTooBig;

	private Map<Integer, String> youkoTailsMap = new HashMap<>();
	private Map<Integer, String> youkoTailsDesaturatedMap = new HashMap<>();


	private Map<Color, String> refinedBackgroundMap = new EnumMap<>(Color.class);
	private Map<Color, String> refinedSwirlsMap = new EnumMap<>(Color.class);

	@SuppressWarnings("resource")
	private SVGImages() {

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/displacedWarningIcon.svg");
			displacedIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/concealed.svg");
			concealedIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/cummedInWarningIcon.svg");
			cummedInIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/feminineWarningIcon.svg");
			feminineWarningIcon = Util.inputStreamToString(is);
			feminineWarningIcon = setColor(feminineWarningIcon, Color.FEMININE);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/masculineWarningIcon.svg");
			masculineWarningIcon = Util.inputStreamToString(is);
			masculineWarningIcon = setColor(masculineWarningIcon, Color.BASE_BLUE_LIGHT);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/jinxed.svg");
			jinxedIcon = Util.inputStreamToString(is);
			jinxedIcon = setColor(jinxedIcon, Color.ATTRIBUTE_CORRUPTION);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/tattooSwitchTattoo.svg");
			tattooSwitchTattoo = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/tattooSwitchClothing.svg");
			tattooSwitchClothing = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/scar.svg");
			scarIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/menu.svg");
			menuIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/inventory.svg");
			inventoryIcon = Util.inputStreamToString(is);
			inventoryIcon = setColor(inventoryIcon, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/inventory.svg");
			inventoryIconDisabled = Util.inputStreamToString(is);
			inventoryIconDisabled = setColor(inventoryIconDisabled, Color.BASE_PITCH_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/questInventory.svg");
			questInventoryIcon = Util.inputStreamToString(is);
			questInventoryIcon = setColor(questInventoryIcon, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/questInventory.svg");
			questInventoryIconDisabled = Util.inputStreamToString(is);
			questInventoryIconDisabled = setColor(questInventoryIconDisabled, Color.BASE_PITCH_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/itemsOnFloor.svg");
			itemsOnFloorIcon = Util.inputStreamToString(is);
			itemsOnFloorIcon = setColor(itemsOnFloorIcon, Color.BASE_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/drink_small.svg");
			drinkSmall = Util.inputStreamToString(is);
			drinkSmall = setColor(drinkSmall, Color.BASE_WHITE);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/drink.svg");
			drink = Util.inputStreamToString(is);
			drink = setColor(drink, Color.BASE_WHITE);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice1.svg");
			dice1 = Util.inputStreamToString(is);
			dice1 = setColor(dice1, Color.BASE_WHITE);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice2.svg");
			dice2 = Util.inputStreamToString(is);
			dice2 = setColor(dice2, Color.BASE_WHITE);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice3.svg");
			dice3 = Util.inputStreamToString(is);
			dice3 = setColor(dice3, Color.BASE_WHITE);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice4.svg");
			dice4 = Util.inputStreamToString(is);
			dice4 = setColor(dice4, Color.BASE_WHITE);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice5.svg");
			dice5 = Util.inputStreamToString(is);
			dice5 = setColor(dice5, Color.BASE_WHITE);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice6.svg");
			dice6 = Util.inputStreamToString(is);
			dice6 = setColor(dice6, Color.BASE_WHITE);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/glow.svg");
			diceGlow = Util.inputStreamToString(is);
			diceGlow = setColor(diceGlow, Color.BASE_GOLD);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/journal.svg");
			journalIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/people.svg");
			peopleIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/zoomIn.svg");
			zoomInIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/zoomOut.svg");
			zoomOutIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/copy.svg");
			copyIcon = Util.inputStreamToString(is);
			copyIcon = setColor(copyIcon, Color.BASE_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/export.svg");
			exportIcon = Util.inputStreamToString(is);
			exportIcon = setColor(exportIcon, Color.BASE_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/information.svg");
			informationIcon = Util.inputStreamToString(is);
			informationIcon = setColor(informationIcon, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/add.svg");
			addIcon = Util.inputStreamToString(is);
			addIcon = setColor(addIcon, Color.BASE_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/calendar.svg");
			calendarIcon = Util.inputStreamToString(is);
			calendarIcon = setColor(calendarIcon, Color.BASE_CRIMSON);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
			diskSave = Util.inputStreamToString(is);
			diskSave = setColor(diskSave, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
			diskSaveDisabled = Util.inputStreamToString(is);
			diskSaveDisabled = setColor(diskSaveDisabled, Color.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
			diskOverwrite = Util.inputStreamToString(is);
			diskOverwrite = setColor(diskOverwrite, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
			diskSaveConfirm = Util.inputStreamToString(is);
			diskSaveConfirm = setColor(diskSaveConfirm, Color.GENERIC_EXCELLENT);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
			diskLoad = Util.inputStreamToString(is);
			diskLoad = setColor(diskLoad, Color.BASE_BLUE_LIGHT);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
			diskLoadConfirm = Util.inputStreamToString(is);
			diskLoadConfirm = setColor(diskLoadConfirm, Color.GENERIC_EXCELLENT);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
			diskLoadDisabled = Util.inputStreamToString(is);
			diskLoadDisabled = setColor(diskLoadDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskDelete.svg");
			diskDelete = Util.inputStreamToString(is);
			diskDelete = setColor(diskDelete, Color.BASE_CRIMSON);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskDelete.svg");
			diskDeleteConfirm = Util.inputStreamToString(is);
			diskDeleteConfirm = setColor(diskDeleteConfirm, Color.GENERIC_EXCELLENT);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
			playerMapIconMasculine = Util.inputStreamToString(is);
			playerMapIconMasculine = setColor(playerMapIconMasculine, Color.MASCULINE_PLUS);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
			playerMapIconAndrogynous = Util.inputStreamToString(is);
			playerMapIconAndrogynous = setColor(playerMapIconAndrogynous, Color.ANDROGYNOUS);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
			playerMapIconFeminine = Util.inputStreamToString(is);
			playerMapIconFeminine = setColor(playerMapIconFeminine, Color.FEMININE_PLUS);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
			playerMapDangerousIcon = Util.inputStreamToString(is);
			playerMapDangerousIcon = setColor(playerMapDangerousIcon, Color.GENERIC_BAD);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackground.svg");
			raceBackground = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundHalf.svg");
			raceBackgroundHalf = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundSlime.svg");
			raceBackgroundSlime = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundDemon.svg");
			raceBackgroundDemon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceUnknown.svg");
			raceUnknown = Util.inputStreamToString(is);
			raceUnknown = setColor(raceUnknown, Color.RACE_UNKNOWN);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceDogMorphDobermann.svg");
			raceDobermann = Util.inputStreamToString(is);
			raceDobermann = setColor(raceDobermann, Color.RACE_DOG_MORPH);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceDogMorphDobermann.svg");
			raceDobermannDesaturated = Util.inputStreamToString(is);
			raceDobermannDesaturated = setColor(raceDobermannDesaturated, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/perkTreeArrow.svg");
			perkTreeArrow = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/spell_overlay.svg");
			spellOverlay = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/wrist_womens_watch_hourhand.svg");
			womensWatchHourHand = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/wrist_womens_watch_minutehand.svg");
			womensWatchMinuteHand = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/wrist_mens_watch_hourhand.svg");
			mensWatchHourHand = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/wrist_mens_watch_minutehand.svg");
			mensWatchMinuteHand = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayClear.svg");
			weatherDayClear = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayCloudy.svg");
			weatherDayCloud = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayRain.svg");
			weatherDayRain = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDaySnow.svg");
			weatherDaySnow = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayStormIncoming.svg");
			weatherDayStormIncoming = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayStorm.svg");
			weatherDayStorm = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayStormProtected.svg");
			weatherDayStormProtected = Util.inputStreamToString(is);
			weatherDayStormProtected = setColor(weatherDayStormProtected, Color.CLOTHING_BLUE_LIGHT);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightClear.svg");
			weatherNightClear = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightCloudy.svg");
			weatherNightCloud = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightRain.svg");
			weatherNightRain = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightSnow.svg");
			weatherNightSnow = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightStormIncoming.svg");
			weatherNightStormIncoming = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightStorm.svg");
			weatherNightStorm = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightStormProtected.svg");
			weatherNightStormProtected = Util.inputStreamToString(is);
			weatherNightStormProtected = setColor(weatherNightStormProtected, Color.CLOTHING_BLUE_LIGHT);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/protection.svg");
			protectionDisabled = Util.inputStreamToString(is);
			protectionDisabled = setColor(protectionDisabled, Color.CLOTHING_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/protection.svg");
			protectionEnabled = Util.inputStreamToString(is);
			protectionEnabled = setColor(protectionEnabled, Color.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/tattoo.svg");
			tattoo = Util.inputStreamToString(is);
			tattoo = setColor(tattoo, Color.CLOTHING_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseCombat.svg");
			responseCombat = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSex.svg");
			responseSex = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseLocked.svg");
			responseLocked = Util.inputStreamToString(is);
			responseLocked = setColor(responseLocked, Color.GENERIC_BAD);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseUnlocked.svg");
			responseUnlocked = Util.inputStreamToString(is);
			responseUnlocked = setColor(responseUnlocked, Color.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseUnlocked.svg");
			responseUnlockedDisabled = Util.inputStreamToString(is);
			responseUnlockedDisabled = setColor(responseUnlockedDisabled, Color.BASE_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseOption.svg");
			responseOption = Util.inputStreamToString(is);
			responseOption = setColor(responseOption, Color.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseOption.svg");
			responseOptionDisabled = Util.inputStreamToString(is);
			responseOptionDisabled = setColor(responseOptionDisabled, Color.BASE_BLACK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseCorruptionBypass.svg");
			responseCorruptionBypass = Util.inputStreamToString(is);
			responseCorruptionBypass = setColor(responseCorruptionBypass, Color.GENERIC_ARCANE);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSubResist.svg");
			responseSubResist = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSubNormal.svg");
			responseSubNormal = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSubEager.svg");
			responseSubEager = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseDomGentle.svg");
			responseDomGentle = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseDomNormal.svg");
			responseDomNormal = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseDomRough.svg");
			responseDomRough = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
			NPCWarningMale = Util.inputStreamToString(is);
			NPCWarningMale = setColor(NPCWarningMale, Color.MASCULINE_PLUS);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
			NPCWarningFemale = Util.inputStreamToString(is);
			NPCWarningFemale = setColor(NPCWarningFemale, Color.FEMININE_PLUS);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
			NPCWarningDemon = Util.inputStreamToString(is);
			NPCWarningDemon = setColor(NPCWarningDemon, Color.GENERIC_ARCANE);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/stopwatch.svg");
			stopwatch = Util.inputStreamToString(is);
			stopwatch = setColor(stopwatch, Color.BASE_GREY);

			// scales:

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay0.svg");
			counterZero = Util.inputStreamToString(is);
			counterZero = setColor(counterZero, Color.BASE_PINK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay1.svg");
			counterOne = Util.inputStreamToString(is);
			counterOne = setColor(counterOne, Color.BASE_PINK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay2.svg");
			counterTwo = Util.inputStreamToString(is);
			counterTwo = setColor(counterTwo, Color.BASE_PINK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay3.svg");
			counterThree = Util.inputStreamToString(is);
			counterThree = setColor(counterThree, Color.BASE_PINK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay4.svg");
			counterFour = Util.inputStreamToString(is);
			counterFour = setColor(counterFour, Color.BASE_PINK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay5.svg");
			counterFive = Util.inputStreamToString(is);
			counterFive = setColor(counterFive, Color.BASE_PINK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay5Plus.svg");
			counterFivePlus = Util.inputStreamToString(is);
			counterFivePlus = setColor(counterFivePlus, Color.BASE_PINK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay0.svg");
			counterZeroDisabled = Util.inputStreamToString(is);
			counterZeroDisabled = setColor(counterZeroDisabled, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay1.svg");
			counterOneDisabled = Util.inputStreamToString(is);
			counterOneDisabled = setColor(counterOneDisabled, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay2.svg");
			counterTwoDisabled = Util.inputStreamToString(is);
			counterTwoDisabled = setColor(counterTwoDisabled, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay3.svg");
			counterThreeDisabled = Util.inputStreamToString(is);
			counterThreeDisabled = setColor(counterThreeDisabled, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay4.svg");
			counterFourDisabled = Util.inputStreamToString(is);
			counterFourDisabled = setColor(counterFourDisabled, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay5.svg");
			counterFiveDisabled = Util.inputStreamToString(is);
			counterFiveDisabled = setColor(counterFiveDisabled, Color.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay5Plus.svg");
			counterFivePlusDisabled = Util.inputStreamToString(is);
			counterFivePlusDisabled = setColor(counterFivePlusDisabled, Color.BASE_BLACK);


			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_zero.svg");
			scaleZero = Util.inputStreamToString(is);
			scaleZero = setColor(scaleZero, Color.BASE_MAGENTA);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_one.svg");
			scaleOne = Util.inputStreamToString(is);
			scaleOne = setColor(scaleOne, Color.BASE_GREEN);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_two.svg");
			scaleTwo = Util.inputStreamToString(is);
			scaleTwo = setColor(scaleTwo, Color.BASE_GREEN);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_three.svg");
			scaleThree = Util.inputStreamToString(is);
			scaleThree = setColor(scaleThree, Color.BASE_GREEN);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_four.svg");
			scaleFour = Util.inputStreamToString(is);
			scaleFour = setColor(scaleFour, Color.BASE_GREEN);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_zero.svg");
			scaleZeroDisabled = Util.inputStreamToString(is);
			scaleZeroDisabled = setColor(scaleZeroDisabled, Color.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_one.svg");
			scaleOneDisabled = Util.inputStreamToString(is);
			scaleOneDisabled = setColor(scaleOneDisabled, Color.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_two.svg");
			scaleTwoDisabled = Util.inputStreamToString(is);
			scaleTwoDisabled = setColor(scaleTwoDisabled, Color.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_three.svg");
			scaleThreeDisabled = Util.inputStreamToString(is);
			scaleThreeDisabled = setColor(scaleThreeDisabled, Color.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_four.svg");
			scaleFourDisabled = Util.inputStreamToString(is);
			scaleFourDisabled = setColor(scaleFourDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveBuy.svg");
			slaveBuy = Util.inputStreamToString(is);
			slaveBuy = setColor(slaveBuy, Color.GENERIC_BAD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveBuyDisabled.svg");
			slaveBuyDisabled = Util.inputStreamToString(is);
			slaveBuyDisabled = setColor(slaveBuyDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveSell.svg");
			slaveSell = Util.inputStreamToString(is);
			slaveSell = setColor(slaveSell, Color.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveSellDisabled.svg");
			slaveSellDisabled = Util.inputStreamToString(is);
			slaveSellDisabled = setColor(slaveSellDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveInspect.svg");
			slaveInspect = Util.inputStreamToString(is);
			slaveInspect = setColor(slaveInspect, Color.BASE_BLUE_STEEL);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveInspect.svg");
			slaveInspectDisabled = Util.inputStreamToString(is);
			slaveInspectDisabled = setColor(slaveInspectDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveJob.svg");
			slaveJob = Util.inputStreamToString(is);
			slaveJob = setColor(slaveJob, Color.BASE_BROWN_DARK);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveJob.svg");
			slaveJobDisabled = Util.inputStreamToString(is);
			slaveJobDisabled = setColor(slaveJobDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slavePermissions.svg");
			slavePermissions = Util.inputStreamToString(is);
			slavePermissions = setColor(slavePermissions, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slavePermissionsDisabled.svg");
			slavePermissionsDisabled = Util.inputStreamToString(is);
			slavePermissionsDisabled = setColor(slavePermissionsDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveTransfer.svg");
			slaveTransfer = Util.inputStreamToString(is);
			slaveTransfer = setColor(slaveTransfer, Color.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveTransfer.svg");
			slaveTransferDisabled = Util.inputStreamToString(is);
			slaveTransferDisabled = setColor(slaveTransferDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveCosmetics.svg");
			slaveCosmetics = Util.inputStreamToString(is);
			slaveCosmetics = setColor(slaveCosmetics, Color.BASE_CRIMSON);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveCosmetics.svg");
			slaveCosmeticsDisabled  = Util.inputStreamToString(is);
			slaveCosmeticsDisabled = setColor(slaveCosmeticsDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBuy.svg");
			transactionBuy = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBuyDisabled.svg");
			transactionBuyDisabled = Util.inputStreamToString(is);
			transactionBuyDisabled = setColor(transactionBuyDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBid.svg");
			transactionBid = Util.inputStreamToString(is);
			transactionBid = setColor(transactionBid, Color.BASE_BROWN);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBid.svg");
			transactionBidDisabled = Util.inputStreamToString(is);
			transactionBidDisabled = setColor(transactionBidDisabled, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionSell.svg");
			transactionSell = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionSellDisabled.svg");
			transactionSellDisabled = Util.inputStreamToString(is);
			transactionSellDisabled = setColor(transactionSellDisabled, Color.BASE_GREY);

			for(int i=1; i<=9; i++) {
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceFoxTail"+i+".svg");
				String svg = Util.inputStreamToString(is);
				svg = SvgUtil.colorReplacement("foxTail"+i,
						Color.RACE_FOX_MORPH,
						Color.RACE_FOX_MORPH,
						Color.RACE_FOX_MORPH,
						svg);

				youkoTailsMap.put(i, svg);


				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceFoxTail"+i+".svg");
				svg = Util.inputStreamToString(is);
				svg = SvgUtil.colorReplacement("foxTail"+i,
						Color.BASE_GREY,
						Color.BASE_GREY,
						Color.BASE_GREY,
						svg);

				youkoTailsDesaturatedMap.put(i, svg);
			}


			// Effects:

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/creampie.svg");
			creampie = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/creampieMasochist.svg");
			creampieMasochist = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/fluidIngested.svg");
			fluidIngested = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/fluidIngestedMasochist.svg");
			fluidIngestedMasochist = Util.inputStreamToString(is);

			// Items:

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockBase.svg");
			hypnoWatchBase = Util.inputStreamToString(is);
			hypnoWatchBase = setColor(hypnoWatchBase, Color.BASE_GREY);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockGyne.svg");
			hypnoWatchGynephilic = Util.inputStreamToString(is);
			hypnoWatchGynephilic = setColor(hypnoWatchGynephilic, Color.FEMININE_PLUS);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockAmbi.svg");
			hypnoWatchAmbiphilic = Util.inputStreamToString(is);
			hypnoWatchAmbiphilic = setColor(hypnoWatchAmbiphilic, Color.ANDROGYNOUS);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockAndro.svg");
			hypnoWatchAndrophilic = Util.inputStreamToString(is);
			hypnoWatchAndrophilic = setColor(hypnoWatchAndrophilic, Color.MASCULINE_PLUS);

			// Sex:

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaAnus.svg");
			coverableAreaAnus = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaAss.svg");
			coverableAreaAss = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaMouth.svg");
			coverableAreaMouth = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaBreasts.svg");
			coverableAreaBreasts = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaBreastsFlat.svg");
			coverableAreaBreastsFlat = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaNipple.svg");
			coverableAreaNipple = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaVagina.svg");
			coverableAreaVagina = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaMound.svg");
			coverableAreaMound = Util.inputStreamToString(is);


			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaBreastsCrotch.svg");
			coverableAreaBreastsCrotch = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaUdders.svg");
			coverableAreaUdders = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaNippleCrotch.svg");
			coverableAreaNippleCrotch = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaUrethraVagina.svg");
			coverableAreaUrethraVagina = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaUrethraPenis.svg");
			coverableAreaUrethraPenis = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaThighs.svg");
			coverableAreaThighs = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeFinger.svg");
			penetrationTypeFinger = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypePenis.svg");
			penetrationTypePenis = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeTail.svg");
			penetrationTypeTail = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeTongue.svg");
			penetrationTypeTongue = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeFoot.svg");
			penetrationTypeFoot = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationStretching.svg");
			combinationStretching = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationTooLoose.svg");
			combinationTooLoose = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationWet.svg");
			combinationWet = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationDry.svg");
			combinationDry = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/stretching.svg");
			stretching = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/holeTooBig.svg");
			holeTooBig = Util.inputStreamToString(is);

			String tempString = "";
			for(AbstractItemEffectType effect : ItemEffectType.getAllEffectTypes()) {
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/refined_background.svg");
				tempString = Util.inputStreamToString(is);
				tempString = setColor(tempString, effect.getColor());

				refinedBackgroundMap.put(effect.getColor(), tempString);
			}

			tempString = "";
			for(TFModifier secondaryModifier : TFModifier.values()) {
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/refined_swirls.svg");
				tempString = Util.inputStreamToString(is);
				tempString = setColor(tempString, secondaryModifier.getColor());

				refinedSwirlsMap.put(secondaryModifier.getColor(), tempString);
			}

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String setColor(String stringSVG, Color colorShade) {
		String s = stringSVG;
		s = SvgUtil.colorReplacement(null, colorShade, s);
		return s;
	}

	public String getDisplacedIcon() {
		return displacedIcon;
	}

	public String getConcealedIcon() {
		return concealedIcon;
	}

	public String getCummedInIcon() {
		return cummedInIcon;
	}

	public String getFeminineWarningIcon() {
		return feminineWarningIcon;
	}

	public String getMasculineWarningIcon() {
		return masculineWarningIcon;
	}

	public String getJinxedIcon() {
		return jinxedIcon;
	}

	public String getTattooSwitchTattoo() {
		return tattooSwitchTattoo;
	}

	public String getTattooSwitchClothing() {
		return tattooSwitchClothing;
	}

	public String getScarIcon() {
		return scarIcon;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public String getInventoryIcon() {
		return inventoryIcon;
	}

	public String getInventoryIconDisabled() {
		return inventoryIconDisabled;
	}

	public String getQuestInventoryIcon() {
		return questInventoryIcon;
	}

	public String getQuestInventoryIconDisabled() {
		return questInventoryIconDisabled;
	}

	public String getJournalIcon() {
		return journalIcon;
	}

	public String getPeopleIcon() {
		return peopleIcon;
	}

	public String getZoomInIcon() {
		return zoomInIcon;
	}

	public String getZoomOutIcon() {
		return zoomOutIcon;
	}

	public String getCopyIcon() {
		return copyIcon;
	}

	public String getExportIcon() {
		return exportIcon;
	}

	public String getInformationIcon() {
		return informationIcon;
	}

	public String getAddIcon() {
		return addIcon;
	}

	public String getCalendarIcon() {
		return calendarIcon;
	}

	public String getDiskSave() {
		return diskSave;
	}
	public String getDiskSaveDisabled() {
		return diskSaveDisabled;
	}

	public String getDiskSaveConfirm() {
		return diskSaveConfirm;
	}

	public String getDiskOverwrite() {
		return diskOverwrite;
	}

	public String getDiskLoad() {
		return diskLoad;
	}

	public String getDiskLoadConfirm() {
		return diskLoadConfirm;
	}

	public String getDiskLoadDisabled() {
		return diskLoadDisabled;
	}

	public String getDiskDelete() {
		return diskDelete;
	}

	public String getDiskDeleteConfirm() {
		return diskDeleteConfirm;
	}

	public String getItemsOnFloorIcon() {
		return itemsOnFloorIcon;
	}

	public String getPlayerMapIconMasculine() {
		return playerMapIconMasculine;
	}

	public String getPlayerMapIconAndrogynous() {
		return playerMapIconAndrogynous;
	}

	public String getPlayerMapIconFeminine() {
		return playerMapIconFeminine;
	}

	public String getPlayerMapDangerousIcon() {
		return playerMapDangerousIcon;
	}

	public String getPerkTreeArrow() {
		return perkTreeArrow;
	}

	public String getSpellOverlay() {
		return spellOverlay;
	}

	public String getWomensWatchHourHand() {
		return womensWatchHourHand;
	}

	public String getWomensWatchMinuteHand() {
		return womensWatchMinuteHand;
	}

	public String getMensWatchHourHand() {
		return mensWatchHourHand;
	}

	public String getMensWatchMinuteHand() {
		return mensWatchMinuteHand;
	}

	public String getWeatherDayClear() {
		return weatherDayClear;
	}

	public String getWeatherDayCloud() {
		return weatherDayCloud;
	}

	public String getWeatherDayRain() {
		return weatherDayRain;
	}

	public String getWeatherDaySnow() {
		return weatherDaySnow;
	}

	public String getWeatherDayStormIncoming() {
		return weatherDayStormIncoming;
	}

	public String getWeatherDayStormProtected() {
		return weatherDayStormProtected;
	}

	public String getWeatherDayStorm() {
		return weatherDayStorm;
	}

	public String getWeatherNightClear() {
		return weatherNightClear;
	}

	public String getWeatherNightCloud() {
		return weatherNightCloud;
	}

	public String getWeatherNightRain() {
		return weatherNightRain;
	}

	public String getWeatherNightSnow() {
		return weatherNightSnow;
	}

	public String getWeatherNightStormIncoming() {
		return weatherNightStormIncoming;
	}

	public String getWeatherNightStorm() {
		return weatherNightStorm;
	}

	public String getWeatherNightStormProtected() {
		return weatherNightStormProtected;
	}

	public String getProtectionEnabled() {
		return protectionEnabled;
	}

	public String getProtectionDisabled() {
		return protectionDisabled;
	}

	public String getTattoo() {
		return tattoo;
	}

	public String getResponseCombat() {
		return responseCombat;
	}

	public String getResponseSex() {
		return responseSex;
	}

	public String getResponseLocked() {
		return responseLocked;
	}

	public String getResponseUnlocked() {
		return responseUnlocked;
	}

	public String getResponseUnlockedDisabled() {
		return responseUnlockedDisabled;
	}

	public String getResponseCorruptionBypass() {
		return responseCorruptionBypass;
	}

	public String getResponseSubResist() {
		return responseSubResist;
	}

	public String getResponseSubNormal() {
		return responseSubNormal;
	}

	public String getResponseSubEager() {
		return responseSubEager;
	}

	public String getResponseDomGentle() {
		return responseDomGentle;
	}

	public String getResponseDomNormal() {
		return responseDomNormal;
	}

	public String getResponseDomRough() {
		return responseDomRough;
	}

	public String getNPCWarningMale() {
		return NPCWarningMale;
	}

	public String getNPCWarningFemale() {
		return NPCWarningFemale;
	}

	public String getNPCWarningDemon() {
		return NPCWarningDemon;
	}

	public String getCoverableAreaMouth() {
		return coverableAreaMouth;
	}

	public String getCoverableAreaAnus() {
		return coverableAreaAnus;
	}

	public String getCoverableAreaAss() {
		return coverableAreaAss;
	}

	public String getCoverableAreaNipple() {
		return coverableAreaNipple;
	}

	public String getCoverableAreaBreasts() {
		return coverableAreaBreasts;
	}

	public String getCoverableAreaBreastsFlat() {
		return coverableAreaBreastsFlat;
	}

	public String getCoverableAreaBreastsCrotch() {
		return coverableAreaBreastsCrotch;
	}

	public String getCoverableAreaUdders() {
		return coverableAreaUdders;
	}

	public String getCoverableAreaNippleCrotch() {
		return coverableAreaNippleCrotch;
	}

	public String getCoverableAreaVagina() {
		return coverableAreaVagina;
	}

	public String getCoverableAreaMound() {
		return coverableAreaMound;
	}

	public String getCoverableAreaUrethraVagina() {
		return coverableAreaUrethraVagina;
	}

	public String getCoverableAreaUrethraPenis() {
		return coverableAreaUrethraPenis;
	}

	public String getCoverableAreaThighs() {
		return coverableAreaThighs;
	}

	public String getPenetrationTypeFinger() {
		return penetrationTypeFinger;
	}

	public String getPenetrationTypeTail() {
		return penetrationTypeTail;
	}

	public String getPenetrationTypeTongue() {
		return penetrationTypeTongue;
	}

	public String getPenetrationTypePenis() {
		return penetrationTypePenis;
	}

	public String getPenetrationTypeFoot() {
		return penetrationTypeFoot;
	}

	public String getCombinationStretching() {
		return combinationStretching;
	}

	public String getCombinationTooLoose() {
		return combinationTooLoose;
	}

	public String getCombinationWet() {
		return combinationWet;
	}

	public String getCombinationDry() {
		return combinationDry;
	}

	public String getStretching() {
		return stretching;
	}

	public String getHoleTooBig() {
		return holeTooBig;
	}

	public Map<Color, String> getRefinedBackgroundMap() {
		return refinedBackgroundMap;
	}

	public Map<Color, String> getRefinedSwirlsMap() {
		return refinedSwirlsMap;
	}

	public String getHypnoWatchBase() {
		return hypnoWatchBase;
	}

	public String getHypnoWatchGynephilic() {
		return hypnoWatchGynephilic;
	}

	public String getHypnoWatchAmbiphilic() {
		return hypnoWatchAmbiphilic;
	}

	public String getHypnoWatchAndrophilic() {
		return hypnoWatchAndrophilic;
	}

	public String getScaleZero() {
		return scaleZero;
	}

	public String getScaleOne() {
		return scaleOne;
	}

	public String getScaleTwo() {
		return scaleTwo;
	}

	public String getScaleThree() {
		return scaleThree;
	}

	public String getScaleFour() {
		return scaleFour;
	}

	public String getScaleZeroDisabled() {
		return scaleZeroDisabled;
	}

	public String getScaleOneDisabled() {
		return scaleOneDisabled;
	}

	public String getScaleTwoDisabled() {
		return scaleTwoDisabled;
	}

	public String getScaleThreeDisabled() {
		return scaleThreeDisabled;
	}

	public String getScaleFourDisabled() {
		return scaleFourDisabled;
	}

	public String getSlaveBuy() {
		return slaveBuy;
	}

	public String getSlaveSell() {
		return slaveSell;
	}

	public String getSlaveInspect() {
		return slaveInspect;
	}

	public String getSlaveInspectDisabled() {
		return slaveInspectDisabled;
	}

	public String getSlaveJob() {
		return slaveJob;
	}

	public String getSlaveJobDisabled() {
		return slaveJobDisabled;
	}

	public String getSlavePermissions() {
		return slavePermissions;
	}

	public String getSlavePermissionsDisabled() {
		return slavePermissionsDisabled;
	}

	public String getSlaveTransfer() {
		return slaveTransfer;
	}

	public String getSlaveBuyDisabled() {
		return slaveBuyDisabled;
	}

	public String getSlaveSellDisabled() {
		return slaveSellDisabled;
	}

	public String getSlaveTransferDisabled() {
		return slaveTransferDisabled;
	}

	public String getSlaveCosmetics() {
		return slaveCosmetics;
	}

	public String getSlaveCosmeticsDisabled() {
		return slaveCosmeticsDisabled;
	}

	public String getTransactionBuy() {
		return transactionBuy;
	}

	public String getTransactionBuyDisabled() {
		return transactionBuyDisabled;
	}

	public String getTransactionBid() {
		return transactionBid;
	}

	public String getTransactionBidDisabled() {
		return transactionBidDisabled;
	}

	public String getTransactionSell() {
		return transactionSell;
	}

	public String getTransactionSellDisabled() {
		return transactionSellDisabled;
	}

	public String getResponseOption() {
		return responseOption;
	}

	public String getResponseOptionDisabled() {
		return responseOptionDisabled;
	}

	public String getCreampie() {
		return creampie;
	}

	public String getCreampieMasochist() {
		return creampieMasochist;
	}

	public String getRaceBackground() {
		return raceBackground;
	}

	public String getRaceBackgroundHalf() {
		return raceBackgroundHalf;
	}

	public String getRaceBackgroundSlime() {
		return raceBackgroundSlime;
	}

	public String getRaceBackgroundDemon() {
		return raceBackgroundDemon;
	}

	public String getRaceUnknown() {
		return raceUnknown;
	}

	public String getRaceDobermann() {
		return raceDobermann;
	}

	public String getRaceDobermannDesaturated() {
		return raceDobermannDesaturated;
	}

	public String getCounterZero() {
		return counterZero;
	}

	public String getCounterOne() {
		return counterOne;
	}

	public String getCounterTwo() {
		return counterTwo;
	}

	public String getCounterThree() {
		return counterThree;
	}

	public String getCounterFour() {
		return counterFour;
	}

	public String getCounterFive() {
		return counterFive;
	}

	public String getCounterFivePlus() {
		return counterFivePlus;
	}

	public String getCounterZeroDisabled() {
		return counterZeroDisabled;
	}

	public String getCounterOneDisabled() {
		return counterOneDisabled;
	}

	public String getCounterTwoDisabled() {
		return counterTwoDisabled;
	}

	public String getCounterThreeDisabled() {
		return counterThreeDisabled;
	}

	public String getCounterFourDisabled() {
		return counterFourDisabled;
	}

	public String getCounterFiveDisabled() {
		return counterFiveDisabled;
	}

	public String getCounterFivePlusDisabled() {
		return counterFivePlusDisabled;
	}

	public String getStopwatch() {
		return stopwatch;
	}

	public String getDrinkSmall() {
		return drinkSmall;
	}

	public String getDrink() {
		return drink;
	}

	public String getDice1() {
		return dice1;
	}

	public String getDice2() {
		return dice2;
	}

	public String getDice3() {
		return dice3;
	}

	public String getDice4() {
		return dice4;
	}

	public String getDice5() {
		return dice5;
	}

	public String getDice6() {
		return dice6;
	}

	public String getDiceGlow() {
		return diceGlow;
	}

	public String getFluidIngested() {
		return fluidIngested;
	}

	public String getFluidIngestedMasochist() {
		return fluidIngestedMasochist;
	}

	public String getFoxTail(int i) {
		return youkoTailsMap.get(i);
	}

	public String getFoxTailDesaturated(int i) {
		return youkoTailsDesaturatedMap.get(i);
	}

}
