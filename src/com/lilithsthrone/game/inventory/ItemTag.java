package com.lilithsthrone.game.inventory;

import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.1
 * @version 0.3.1
 * @author Innoxia
 */
public enum ItemTag {

	REMOVE_FROM_DEBUG_SPAWNER(false),

	NOT_FOR_SALE(false),

	REINDEER_GIFT(false), // Can be found in the presents that the reindeer sell (who appear in DOminion during winter months).
	SOLD_BY_RALPH(false), // Will also be used for any future consumable and miscellaneous item vendors.
	SOLD_BY_NYAN(false), // Clothing
	SOLD_BY_KATE(false), // Jewelry
	SOLD_BY_FINCH(false), // BDSM and sex-related stuff
	SOLD_BY_VICKY(false), // Weapons

	SPELL_BOOK(false),
	SPELL_SCROLL(false),
	ESSENCE(false),
	ATTRIBUTE_TF_ITEM(false),
	RACIAL_TF_ITEM(false),
	MISC_TF_ITEM(false),
	BOOK(false),
	GIFT(false),
	DOMINION_ALLEYWAY_SPAWN(false),
	SUBMISSION_TUNNEL_SPAWN(false),
	BAT_CAVERNS_SPAWN(false),

	//-------------- WEAPONS & CLOTHING --------------//

	REVEALS_CONCEALABLE_SLOT(false), // If a piece of clothing has this tag, it will always be visible, even if another item of clothing is concealing its slot. (Used for spreader bar.)

	TRANSPARENT( // This item of clothing does not conceal any areas. Used for chastity cages & condoms (so penis is still visible). Could also be used for sheer clothing material.
			Util.newArrayListOfValues(
					"[style.colorSex(Cannot conceal any body parts)]"),
			false),

	WEAPON_BLADE(false), // Should be added to all weapons that use an arcane blade

	DRESS(false), // For helping to generate clothing in CharacterUtils

	SPREADS_FEET( // Prevents double foot actions, like wrap-around footjobs
			Util.newArrayListOfValues(
					"[style.colorBad(Restricts sex actions)]"),
			false),

	MUFFLES_SPEECH( // Causes the wearer to not be able to talk. E.g. Ball gags
			Util.newArrayListOfValues(
					"[style.colorBad(Muffles speech)]"),
			false),

	HINDERS_ARM_MOVEMENT( // Hinders the ability of the wearer to use their arms. E.g. Hand cuffs
			Util.newArrayListOfValues(
					"[style.colorBad(Hinders arm movement)]",
					"[style.colorTerrible(Blocks arm-wing flight)]"),
			false),

	HINDERS_LEG_MOVEMENT(  // Hinders the ability of the wearer to run or use their legs properly. E.g. Spreader bar
			Util.newArrayListOfValues(
					"[style.colorBad(Hinders leg movement)]",
					"[style.colorTerrible(Blocks non-flight combat escape)]"),
			false),

	DISCARDED_WHEN_UNEQUIPPED( //  Makes the clothing be thrown away when unequipped. E.g. Condoms
			Util.newArrayListOfValues(
					"[style.colorMinorBad(Discarded when unequipped)]"),
			false),

	ENABLE_SEX_EQUIP( // Allows this clothing to be equipped during sex. E.g. Condoms or strapons
			Util.newArrayListOfValues(
					"[style.colorSex(Sex-equip enabled)]"),
			false),

	// Self-explanatory requirements in order to equip this clothing:
	REQUIRES_PENIS(
			Util.newArrayListOfValues(
					"[style.colorSex(Requires penis)]"),
			false),
	REQUIRES_NO_PENIS(
			Util.newArrayListOfValues(
					"[style.colorSex(Requires no penis)]"),
			false),
	REQUIRES_VAGINA(
			Util.newArrayListOfValues(
					"[style.colorSex(Requires vagina)]"),
			false),
	REQUIRES_NO_VAGINA(
			Util.newArrayListOfValues(
					"[style.colorSex(Requires no vagina)]"),
			false),
	REQUIRES_FUCKABLE_NIPPLES(
			Util.newArrayListOfValues(
					"[style.colorSex(Requires fuckable nipples)]"),
			false),

	// These 'FITS' tags are used to check for whether clothing is suitable for certain body parts. They should be pretty self-explanatory.
	FITS_HOOFS(
			Util.newArrayListOfValues(
					"[style.colorBestial(Only fits hoofs)]"),
			false),
	FITS_TALONS(
			Util.newArrayListOfValues(
					"[style.colorBestial(Only fits talons)]"),
			false),
	FITS_HARPY_WINGS(
			Util.newArrayListOfValues(
					"[style.colorBestial(Only fits arm-wings)]"),
			false),
	FITS_NON_BIPED_BODY_HUMANOID(
			Util.newArrayListOfValues(
					"[style.colorHuman(Fits humanoid parts of non-biped bodies)]"),
			false),
	FITS_TAUR_BODY(
			Util.newArrayListOfValues(
					"[style.colorBestial(Only fits taur bodies)]"),
			false),
	FITS_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colorBestial(Only fits long-tailed bodies)]"),false), //lamia, eels
	FITS_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colorBestial(Only fits tailed bodies)]"),false), //mermaids
	FITS_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colorBestial(Only fits arachnid bodies)]"),false), //spiders and scorpions
	FITS_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colorBestial(Only fits cephalopod bodies)]"),false), //octopuses and squids

	PREVENTS_ERECTION_PHYSICAL( // Prevents the wearer from getting an erection during sex, by means of physically limiting the space into which the erection could take shape (i.e. chastity cages). As of 0.3.1, only affects descriptors.
			Util.newArrayListOfValues(
					"[style.colorTerrible(Prevents erection)]"),
			false),
	PREVENTS_ERECTION_OTHER( // Prevents the wearer from getting an erection during sex, by means other than physical limitations. As of 0.3.1, only affects descriptors.
			Util.newArrayListOfValues(
					"[style.colorTerrible(Prevents erection)]"),
			false),

	// Sex-related clothing:

	/**<b>IMPORTANT</b> This tag should only ever be given to clothing going into the PENIS InventorySlot, as otherwise it will throw errors.*/
	CONDOM(true), // Gives this clothing condom behavior

	// To detect whether creampies should leak out or not:

	PLUGS_ANUS( // Counts as being inserted into the wearer's anus. E.g. butt plugs or anal beads
			Util.newArrayListOfValues(
					"[style.colorSex(Plugs asshole)]"),
			true),
	SEALS_ANUS( // Counts as sealing(false), but not inserted into(false), the wearer's anus. E.g. Tape
			Util.newArrayListOfValues(
					"[style.colorSex(Seals asshole)]"),
			true),

	PLUGS_VAGINA( // Counts as being inserted into the wearer's vagina. E.g. insertable dildo
			Util.newArrayListOfValues(
					"[style.colorSex(Plugs pussy)]"),
			true),
	SEALS_VAGINA( // Counts as sealing, but not inserted into, the wearer's vagina. E.g. Tape
			Util.newArrayListOfValues(
					"[style.colorSex(Seals pussy)]"),
			true),

	PLUGS_NIPPLES( // Counts as being inserted into the wearer's nipples. E.g. insertable nipple-dildos
			Util.newArrayListOfValues(
					"[style.colorSex(Plugs nipples)]"),
			true),
	SEALS_NIPPLES( // Counts as sealing, but not inserted into, the wearer's nipples. E.g. Pasties
			Util.newArrayListOfValues(
					"[style.colorSex(Seals nipples)]"),
			true),

	DILDO_TINY( // 3 inches
			Util.newArrayListOfValues(
					"[style.colorSex(3-inch dildo)]"),
			true),
	DILDO_AVERAGE( // 6 inches
			Util.newArrayListOfValues(
					"[style.colorSex(6-inch dildo)]"),
			true),
	DILDO_LARGE( // 10 inches
			Util.newArrayListOfValues(
					"[style.colorSex(10-inch dildo)]"),
			true),
	DILDO_HUGE( // 14 inches
			Util.newArrayListOfValues(
					"[style.colorSex(14-inch dildo)]"),
			true),
	DILDO_ENORMOUS( // 18 inches
			Util.newArrayListOfValues(
					"[style.colorSex(18-inch dildo)]"),
			true),
	DILDO_GIGANTIC( // 22 inches
			Util.newArrayListOfValues(
					"[style.colorSex(22-inch dildo)]"),
			true),
	DILDO_STALLION( // 32 inches
			Util.newArrayListOfValues(
					"[style.colorSex(32-inch dildo)]"),
			true),
	;

	private List<String> clothingTooltipAdditions;
	private boolean sexToy;

	/**
	 * @param clothingTooltipAdditions The descriptions that should be appended to clothing tooltips for when this ItemTag is present on clothing.
	 * @param sexToy If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex.
	 */
	private ItemTag(List<String> clothingTooltipAdditions, boolean sexToy) {
		this.clothingTooltipAdditions = clothingTooltipAdditions;
		this.sexToy = sexToy;
	}
	/**
	 * @param sexToy If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex.
	 */
	private ItemTag(boolean sexToy) {
		this.clothingTooltipAdditions = null;
		this.sexToy = sexToy;
	}

	public List<String> getClothingTooltipAdditions() {
		return clothingTooltipAdditions;
	}

	public boolean isSexToy() {
		return sexToy;
	}

}
