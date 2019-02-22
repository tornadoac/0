package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.2.1
 * @author Innoxia
 */
public class Covering  {
	
	protected BodyCoveringType type;
	protected CoveringPattern pattern;
	protected CoveringModifier modifier;
	
	protected Color primaryColor, secondaryColor;
	protected boolean primaryGlowing, secondaryGlowing;

	/**
	 * Constructor.<br/>
	 * Initializes CoveringPattern pattern to a random value, and boolean glowing to false.
	 * @param type The BodyCoveringType to set this skin to.
	 */
	public Covering(BodyCoveringType type) {
		this(type, type.getNaturalPatterns().get(Util.random.nextInt(type.getNaturalPatterns().size())),
				type.getNaturalColorsPrimary().get(Util.random.nextInt(type.getNaturalColorsPrimary().size())), false,
				(type.getNaturalColorsSecondary().isEmpty()
						?type.getNaturalColorsPrimary().get(Util.random.nextInt(type.getNaturalColorsPrimary().size()))
						:type.getNaturalColorsSecondary().get(Util.random.nextInt(type.getNaturalColorsSecondary().size()))), false);
	}
	
	/**
	 * Constructor.<br/>
	 * Initializes CoveringPattern pattern to the first value, boolean glowing to false, and secondaryColor to same as primaryColor (where available).
	 * @param type
	 * @param primaryColor
	 */
	public Covering(BodyCoveringType type, Color primaryColor) {
		this(type, type.getNaturalPatterns().get(0),
				primaryColor, false,
				(type.getNaturalColorsSecondary().contains(primaryColor) || type.getNaturalColorsSecondary().isEmpty()
						?primaryColor
						:type.getNaturalColorsSecondary().get(Util.random.nextInt(type.getNaturalColorsSecondary().size()))), false);
	}
	
	/**
	 * Constructor.
	 * @param type The BodyCoveringType to set this skin to.
	 * @param pattern The CoveringPattern to set this skin to.
	 * @param glowing Whether this skin is glowing or not.
	 */
	public Covering(BodyCoveringType type, CoveringPattern pattern, Color primaryColor, boolean primaryGlowing, Color secondaryColor, boolean secondaryGlowing) {
		this(type, pattern, type.getNaturalModifiers().get(0), primaryColor, primaryGlowing, secondaryColor, secondaryGlowing);
	}
	
	public Covering(BodyCoveringType type, CoveringPattern pattern, CoveringModifier modifier, Color primaryColor, boolean primaryGlowing, Color secondaryColor, boolean secondaryGlowing) {
		this.type = type;
		this.pattern = pattern;
		this.modifier = modifier;
		this.primaryColor = primaryColor;
		this.primaryGlowing = primaryGlowing;
		this.secondaryColor = secondaryColor;
		this.secondaryGlowing = secondaryGlowing;
	}
	
	public Covering(Covering coveringToClone) {
		this.type = coveringToClone.getType();
		this.pattern = coveringToClone.getPattern();
		this.modifier = coveringToClone.getModifier();
		this.primaryColor = coveringToClone.getPrimaryColor();
		this.primaryGlowing = coveringToClone.isPrimaryGlowing();
		this.secondaryColor = coveringToClone.getSecondaryColor();
		this.secondaryGlowing = coveringToClone.isSecondaryGlowing();
	}
	
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
//	public String getName(GameCharacter gc, boolean withDescriptor) {
//		return (getDescriptor(gc).length() > 0 ? getDescriptor(gc) + " " : "") + getName(gc);
//	}

	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	public String getNamePlural(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	public String getDescriptor(GameCharacter gc) {
		return modifier.getName();
	}
	
	public String getColorDescriptor(GameCharacter gc, boolean colored, boolean capitalized) {
		String primaryColorName = capitalized?Util.capitalizeSentence(primaryColor.getName()):primaryColor.getName();
		String secondaryColorName = capitalized?Util.capitalizeSentence(secondaryColor.getName()):secondaryColor.getName();
//		if(gc.getRace()==Race.SLIME) {
//			if(this.getType()!=BodyCoveringType.SLIME) {
//				return gc.getCovering(BodyCoveringType.SLIME).getColorDescriptor(gc, colored, capitalized);
//			}
//		}
		// Hopefully nobody ever discovers this appalling mess... BlobSweats
		if(colored) {
			switch(pattern) {
				case HIGHLIGHTS:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-highlighted</span>";
				case OMBRE:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"fading into <span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"</span>";
				case MOTTLED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-mottled</span>";
				case FRECKLED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-freckled</span>";
				case SPOTTED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-spotted</span>";
				case MARKED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-marked</span>";
				case STRIPED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-striped</span>";
				case ORIFICE_ANUS:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"-rimmed</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-interiored</span>";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-interiored</span>";
				case ORIFICE_VAGINA:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"-lipped</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-interiored</span>";
				case ORIFICE_MOUTH:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"-lipped</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"-interiored</span>";
				case NONE: case FLUID:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>";
				case EYE_IRISES:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>";
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"</span>";
				case EYE_PUPILS:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>";
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"</span>";
				case EYE_SCLERA:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>";
				case EYE_SCLERA_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColorName+"</span>";
			}
			return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColorName+"</span>";
		
		} else {
			switch(pattern) {
				case HIGHLIGHTS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName+"-highlighted";
				case OMBRE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+", fading into "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName+"";
				case MOTTLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName+"-mottled";
				case FRECKLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName+"-freckled";
				case SPOTTED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName+"-spotted";
				case MARKED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName+"-marked";
				case STRIPED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName+"-striped";
				case ORIFICE_ANUS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+"-rimmed";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName;
				case ORIFICE_VAGINA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+"-lipped";
				case ORIFICE_MOUTH:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+" lips";
				case NONE: case FLUID:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName;
				case EYE_IRISES:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName;
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName;
				case EYE_PUPILS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName;
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName;
				case EYE_SCLERA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName;
				case EYE_SCLERA_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColorName;
			}
			return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColorName;
		}
	}
	
	public String getPrimaryColorDescriptor(boolean colored) {
		if(colored) {
			return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>";
		
		} else {
			return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName();
		}
	}
	
	public String getSecondaryColorDescriptor(boolean colored) {
		if(colored) {
			return (secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+"</span>";
		
		} else {
			return (secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName();
		}
	}
	
	/**
	 * @return A full description of this covering. e.g. "glowing black, wolf-like fur, with luminescent purple stripes" or "black, shaggy fur"
	 */
	public String getFullDescription(GameCharacter gc, boolean colored) {
		//text-shadow: 0px 0px 4px #FF0000;
		String descriptor = modifier.getName();
		String name = type.getName(gc);
		
//		if(gc.getRace()==Race.SLIME) {
//			if(this.getType()!=BodyCoveringType.SLIME) {
//				return gc.getCovering(BodyCoveringType.SLIME).getFullDescription(gc, colored);
//			}
//		}
		if(colored) {
			switch(pattern) {
				case HIGHLIGHTS:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" highlights</span>";
				case OMBRE:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", which gradually "+(type.isDefaultPlural()?"fade":"fades")+" into "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+"</span>";
				case MOTTLED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" mottling</span>";
				case FRECKLED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" freckles</span>";
				case SPOTTED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" spots</span>";
				case MARKED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" markings</span>";
				case STRIPED:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" stripes</span>";
				case ORIFICE_ANUS:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"-rimmed anus</span>, with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" internal walls</span>";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+" nipples</span>, with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" internal walls</span>";
				case ORIFICE_VAGINA:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"-lipped pussy</span>, with "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" internal walls</span>";
				case ORIFICE_MOUTH:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+" lips</span>, with a "
							+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+" throat</span>";
				case NONE: case FLUID:
					if(primaryColor==Color.COVERING_NONE) {
						return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>";
					}
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name;
				case EYE_IRISES:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span> irises";
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+"</span> irises";
				case EYE_PUPILS:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span> pupils";
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+"</span> pupils";
				case EYE_SCLERA:
					return (primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span> sclerae";
				case EYE_SCLERA_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColor.toWebHexString()+";'>")+primaryColor.getName()+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColor)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColor.toWebHexString()+";'>")+secondaryColor.getName()+"</span> sclerae";
			
			}
			
		} else {
			switch(pattern) {
				case HIGHLIGHTS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" highlights";
				case OMBRE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", which gradually "+(type.isDefaultPlural()?"fade":"fades")+" into "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName();
				case MOTTLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" mottling";
				case FRECKLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" freckles";
				case SPOTTED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" spots";
				case MARKED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" markings";
				case STRIPED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" stripes";
				case ORIFICE_ANUS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+"-rimmed anus, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" internal walls";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+" nipples, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" internal walls";
				case ORIFICE_VAGINA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+"-lipped pussy, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" internal walls";
				case ORIFICE_MOUTH:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+" lips, with a "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" throat";
				case NONE: case FLUID:
					if(primaryColor==Color.COVERING_NONE) {
						return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName();
					}
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name;
				case EYE_IRISES:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+" irises";
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" irises";
				case EYE_PUPILS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+" pupils";
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" sclerae";
				case EYE_SCLERA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+" pupils";
				case EYE_SCLERA_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColor.getName()+" sclerae";
			}
		}
		return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColor.getName();
	}
	
	private String spanStartGlowing(Color color) {
		return "<span style='color:"+color.toWebHexString()+"; text-shadow: 0px 0px 4px "+color.getShades()[4]+";'>";
	}
	
	private String getGlowingDescriptor() {
		return UtilText.returnStringAtRandom("glowing", "luminescent", "luminous", "fluorescent");
	}
	
	@Override
	public boolean equals(Object o) {
//		if(super.equals(o)){
			if(o instanceof Covering){
				if(((Covering)o).getType() == type
						&& ((Covering)o).getPattern() == pattern
						&& ((Covering)o).getModifier() == modifier
						&& ((Covering)o).getPrimaryColor() == primaryColor
						&& ((Covering)o).isPrimaryGlowing() == primaryGlowing
						&& ((Covering)o).getSecondaryColor() == secondaryColor
						&& ((Covering)o).isSecondaryGlowing() == secondaryGlowing){
					return true;
				}
			}
//		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + pattern.hashCode();
		result = 31 * result + modifier.hashCode();
		result = 31 * result + primaryColor.hashCode();
		result = 31 * result + (primaryGlowing ? 1 : 0);
		result = 31 * result + secondaryColor.hashCode();
		result = 31 * result + (secondaryGlowing ? 1 : 0);
		return result;
	}

	public BodyCoveringType getType() {
		return type;
	}

	public void setType(BodyCoveringType type) {
		this.type = type;
	}

	public CoveringPattern getPattern() {
		return pattern;
	}

	public void setPattern(CoveringPattern pattern) {
		this.pattern = pattern;
	}

	public CoveringModifier getModifier() {
		return modifier;
	}

	public void setModifier(CoveringModifier modifier) {
		this.modifier = modifier;
	}

	public Color getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(Color primaryColor) {
		this.primaryColor = primaryColor;
	}

	public Color getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(Color secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public boolean isPrimaryGlowing() {
		return primaryGlowing;
	}

	public void setPrimaryGlowing(boolean primaryGlowing) {
		this.primaryGlowing = primaryGlowing;
	}

	public boolean isSecondaryGlowing() {
		return secondaryGlowing;
	}

	public void setSecondaryGlowing(boolean secondaryGlowing) {
		this.secondaryGlowing = secondaryGlowing;
	}
}
