package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class PartnerPenisUrethraPenis {
	
	public static final SexAction PARTNER_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Fuck [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.cock+] into [pc.name]'s [pc.penisUrethra+] and start fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over yours, [npc.name] lets out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards,"
									+ " sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against yours, and with a slow, steady pressure, [npc.she] gently sinks [npc.her] [npc.cock+] into your [pc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over yours, [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
									+ " greedily sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against yours, and with a determined thrust, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] over yours, [npc.name] lets out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards,"
									+ " forcing [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against yours, and with a forceful thrust, [npc.she] roughly slams [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over yours, [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
									+ " greedily sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against yours, and with a determined thrust, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.cockHead+] of [npc.her] [npc.cock] over yours, [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against yours, and with a thrust of [npc.her] [npc.hips], [npc.she] sinks [npc.her] [npc.cock+] into your [pc.penisUrethra+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as [npc.she] enters you, gently bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+].",
							" With a soft [pc.moan], you start gently bucking your [pc.hips] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+].",
							" With [pc.a_moan+], you start eagerly bucking your [pc.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, violently thrusting your [pc.hips] back against [npc.herHim] as you roughly force [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+].",
							" With [pc.a_moan+], you start violently bucking your [pc.hips] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+].",
							" With [pc.a_moan+], you start eagerly bucking your [pc.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+].",
							" With [pc.a_moan+], you start bucking your [pc.hips] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into your [pc.penisUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] enters you, and, with tears running down your [pc.face], you beg for [npc.herHim] to pull out as you weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you try, in vain, to pull away from [npc.name]'s unwanted penetration, tears running down your [pc.face] as [npc.her] unwelcome [npc.cock] pushes deep into your [pc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [pc.name]'s [pc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] starts bucking [npc.her] [npc.hips] into you, softly pressing [npc.her] groin against yours with every thrust as [npc.she] slowly fucks you.",
					"Slowly pushing [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] softly thrusts [npc.her] [npc.hips] against you, letting out a little [npc.moan] as [npc.she] gently fucks you.",
					"Sliding [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] lets out a little [npc.moan] as [npc.she] starts to gently buck [npc.her] [npc.hips], breathing in your [pc.scent] as [npc.she] slowly fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.name],"
									+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.penisUrethra+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.penisUrethra+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.penisUrethra+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [pc.name]'s [pc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] starts eagerly bucking [npc.her] [npc.hips] into you, slamming into your groin with every thrust as [npc.she] enthusiastically fucks you.",
					"Eagerly pushing [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] greedily thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] continues fucking you.",
					"Enthusiastically thrusting [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bucking [npc.her] [npc.hips],"
							+ " breathing in your [pc.scent] as [npc.she] eagerly fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.name],"
									+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.penisUrethra+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.penisUrethra+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.penisUrethra+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [pc.name]'s [pc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.cock+] deep into your [pc.penisUrethra+], [npc.name] starts violently pumping [npc.her] [npc.hips], grinding into your groin with every thrust as [npc.she] brutally fucks you.",
					"Violently thrusting [npc.her] [npc.cock+] deep into your [pc.penisUrethra+], [npc.name] starts slamming [npc.her] [npc.hips] into you, letting out [npc.a_moan+] as [npc.she] roughly fucks you.",
					"Ruthlessly thrusting [npc.her] [npc.cock+] deep into your [pc.penisUrethra+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts violently thrusting [npc.her] [pc.hips] back and forth,"
							+ " breathing in your [pc.scent] as [npc.she] roughly fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.name],"
									+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to stop abusing your [pc.penisUrethra+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to stop abusing your [pc.penisUrethra+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.penisUrethra+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [pc.name]'s [pc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] starts bucking [npc.her] [npc.hips] into you, slamming into your groin with every thrust as [npc.she] fucks you.",
					"Pushing [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] continues fucking you.",
					"Thrusting [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts bucking [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as [npc.she] fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly buck your [pc.hips] in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fucking you.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you slowly grind yourself against [npc.name], softly [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently buck your [pc.hips] in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, roughly thrusting your [pc.hips] out against [npc.name]'s groin, you order [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you roughly grind yourself against [npc.name], ordering [npc.herHim] to continue as your aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You buck your [pc.hips] in response, letting out [pc.a_moan+] as you start imploring [npc.name] to continue fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your [pc.hips] out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you grind yourself against [npc.name], [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [pc.name]'s [pc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] starts eagerly bucking [npc.her] [npc.hips] into you, slamming into your groin with every thrust as [npc.she] enthusiastically fucks you.",
					"Eagerly pushing [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] continues desperately fucking you.",
					"Eagerly thrusting [npc.her] [npc.cock+] into your [pc.penisUrethra+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bucking [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as [npc.she] fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly buck your [pc.hips] in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fucking you.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you slowly grind yourself against [npc.name], softly [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently buck your [pc.hips] in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, roughly thrusting your [pc.hips] out against [npc.name]'s groin, you order [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you roughly grind yourself against [npc.name], ordering [npc.herHim] to continue as your aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You buck your [pc.hips] in response, letting out [pc.a_moan+] as you start imploring [npc.name] to continue fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your [pc.hips] out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you grind yourself against [npc.name], [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] away from [pc.name]'s [pc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull [npc.her] [npc.cock] free from your [pc.penisUrethra+], [npc.name] lets out [npc.a_sob+], pushing against you as [npc.she] weakly begs to be released.",
					" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, pleading for you to take your [pc.penisUrethra+] off [npc.her] [npc.cock].",
					" [npc.Sobbing] in distress, [npc.name] weakly struggles against you, pleading for you to let go of [npc.her] [npc.cock]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you slowly thrust your [pc.hips] out against [npc.name], letting out a soft [pc.moan] as you continue gently fucking yourself on [npc.her] [npc.cock+].",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests,"
									+ " you gently push your [pc.hips] out against [npc.her] groin, before continuing to fuck yourself on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, slowly grinding yourself against [npc.herHim] and softly [pc.moaning] as you sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you roughly slam your [pc.hips] out against [npc.name], letting out [pc.a_moan+] as you continue violently fucking yourself on [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests,"
									+ " you forcefully thrust your [pc.hips] out against [npc.her] groin, before continuing to roughly fuck yourself on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, roughly grinding yourself against [npc.herHim] and [pc.moaning+] out loud as you violently force [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you eagerly thrust your [pc.hips] out against [npc.name], letting out [pc.a_moan+] as you continue happily fucking yourself on [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests,"
									+ " you eagerly push your [pc.hips] out against [npc.her] groin, before continuing to energetically fuck yourself on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, eagerly grinding yourself against [npc.herHim] and [pc.moaning+] out loud as you force [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop fucking [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [pc.name]'s [pc.penisUrethra+] and stop fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of your [pc.penisUrethra+],"
									+ " [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down between your folds one last time before pulling [npc.her] [npc.hips] back.",
							"Thrusting deep inside of you one last time, [npc.name] then yanks [npc.her] [npc.cock+] back out of your [pc.penisUrethra+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of your [pc.penisUrethra+], [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down between your folds one last time before pulling [npc.her] [npc.hips] back.",
							"Pushing deep inside of you one last time, [npc.name] then slides [npc.her] [npc.cock+] back out of your [pc.penisUrethra+], putting an end to your fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You can't help but let out [pc.sob+] as [npc.name] pulls out of your [pc.penisUrethra], and you continue crying and protesting as you continue to weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.name], tears streaming down your [pc.face] as [npc.she] withdraws from your [pc.penisUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.cock+] out of your [pc.penisUrethra+], eager for more of [npc.her] attention.",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Player can only start fucking themselves on the partner's cock in consensual sex or if they're the dom.
			// You can't penetrate if you're already fucking your partner, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			
			if(Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.PENIS)) {
				return (Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()));
			} else {
				return false; //(Sex.isConsensual() || Sex.isDom(Main.game.getPlayer())) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS).contains(OrificeType.URETHRA_PENIS);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Cock Urethra Penetrated";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to sink [npc.her] [npc.cock+] into your penis's [pc.penisUrethra+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you slowly slide [npc.her] [npc.cock+] over the [pc.penisHead] of your [pc.cock+],"
									+ " letting out a little [pc.moan] before gently bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you eagerly guide [npc.her] [npc.cock+] up to the [pc.penisHead] of your [pc.cock+],"
									+ " letting out [pc.a_moan+] before desperately bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding yourself against [npc.name], you guide [npc.her] [npc.cock+] up to the [pc.penisHead] of your [pc.cock+],"
									+ " letting out [pc.a_moan+] before roughly slamming your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you eagerly guide [npc.her] [npc.cock+] up to the [pc.penisHead] of your [pc.cock+],"
									+ " letting out [pc.a_moan+] before desperately bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you guide [npc.her] [npc.cock+] up to the [pc.penisHead] of your [pc.cock+],"
									+ " letting out [pc.a_moan+] before bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a soft [npc.moan] as [npc.she] enters you, gently bucking [npc.her] [npc.hips] as [npc.she] starts to fuck your [pc.penisUrethra+].",
							" With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] into your groin, sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+] as [npc.she] starts fucking you."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your [pc.penisUrethra+].",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] into your groin, sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+] as [npc.she] starts energetically fucking you."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, and, seeking to remind you who's in charge,"
									+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck your [pc.penisUrethra+].",
							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] into your groin, seeking to remind you who's in charge as [npc.she] starts ruthlessly fucking your [pc.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your [pc.penisUrethra+].",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] into your groin, sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+] as [npc.she] starts energetically fucking you."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, bucking [npc.her] [npc.hips] into your groin as [npc.she] starts fucking your [pc.penisUrethra+].",
							" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] into your groin, sinking [npc.her] [npc.cock+] into your [pc.penisUrethra+] as [npc.she] starts fucking you."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.cock] inside of you, and, struggling against you, [npc.she] desperately tries to pull [npc.her] [npc.cock+] free from your [pc.penisUrethra+].",
							" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.cock] deep into your [pc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck yourself on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"Gently pushing your [pc.hips] out against [npc.name]'s groin, you let out a soft [pc.moan] as you help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
					"With a soft [pc.moan], you gently start gyrating your [pc.hips], forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.penisUrethra+].",
					"Slowly thrusting your [pc.hips] against [npc.name], a soft [pc.moan] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].");
			
		}
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Fuck yourself on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.hips], forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.penisUrethra+].",
					"Enthusiastically thrusting your [pc.hips] against [npc.name], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].");
			
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck yourself on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"Violently slamming your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you roughly force [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
					"With [pc.a_moan+], you aggressively start gyrating your [pc.hips] against [npc.name], forcing [npc.her] [npc.cock+] ever deeper into your [pc.penisUrethra+].",
					"Roughly thrusting your [pc.hips] against [npc.name], [pc.a_moan+] bursts out from between your [pc.lips+] as your forceful movements drive [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].");
			
		}

	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Buck your hips against [npc.name] as [npc.her] [npc.cock] thrusts into your [pc.penisUrethra].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
					"With [pc.a_moan+], you start gyrating your [pc.hips], forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.penisUrethra+].",
					"Thrusting your [pc.hips] against [npc.name], [pc.a_moan+] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eager urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [npc.name] as [npc.her] [npc.cock] thrusts into your [pc.penisUrethra].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep into your [pc.penisUrethra+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.hips], forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.penisUrethra+].",
					"Enthusiastically thrusting your [pc.hips] against [npc.name], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.penisUrethra+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [pc.penisUrethra+] away from [npc.name]'s [npc.cock+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.penisUrethra+] as [npc.she] continues gently fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues slowly sliding in and out of your [pc.penisUrethra+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues gently sliding deep into your [pc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.penisUrethra+] as [npc.she] continues eagerly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues eagerly sliding in and out of your [pc.penisUrethra+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues eagerly sliding deep into your [pc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.penisUrethra+] as [npc.she] continues roughly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues roughly slamming in and out of your [pc.penisUrethra+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues roughly slamming deep into your [pc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop cock urethral";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out of your cock's [pc.penisUrethra+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.name]'s [npc.cock] out of your [pc.penisUrethra+], you let out a menacing growl as you command [npc.herHim] to stop fucking you.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.cock] out of your [pc.penisUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.cock] out of your [pc.penisUrethra+], you let out [pc.a_moan+] as you tell [npc.herHim] to stop fucking you.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out of your [pc.penisUrethra+]."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that you haven't finished with [npc.herHim] just yet.",
							" With [npc.a_sob+], [npc.name] continues to protest and struggle against you as you hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you stop [npc.herHim] from fucking your [pc.penisUrethra+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your [pc.penisUrethra+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
