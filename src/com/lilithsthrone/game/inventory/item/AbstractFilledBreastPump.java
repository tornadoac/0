package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.1
 * @version 0.2.1
 * @author Innoxia
 */
public class AbstractFilledBreastPump extends AbstractItem implements XMLSaving {

	private String milkProvidor;
	private FluidMilk milk;
	private int millilitersStored;

	public AbstractFilledBreastPump(AbstractItemType itemType, Color color, GameCharacter milkProvidor, FluidMilk milk, int millilitersStored) {
		super(itemType);

		this.milkProvidor = milkProvidor.getId();
		this.milk = new FluidMilk(milk.getType());
		this.milk.setFlavor(milkProvidor, milk.getFlavor());
		for(FluidModifier fm : milk.getFluidModifiers()) {
			this.milk.addFluidModifier(milkProvidor, fm);
		}
		this.colorShade = color;
		SVGString = getSVGString(itemType.getPathName(), color);
		this.millilitersStored = millilitersStored;
	}

	public AbstractFilledBreastPump(AbstractItemType itemType, Color color, String milkProvidorId, FluidMilk milk, int millilitersStored) {
		super(itemType);

		this.milkProvidor = milkProvidorId;
		this.milk = new FluidMilk(milk.getType());
		this.milk.setFlavor(null, milk.getFlavor());
		for(FluidModifier fm : milk.getFluidModifiers()) {
			this.milk.addFluidModifier(null, fm);
		}
		this.colorShade = color;
		SVGString = getSVGString(itemType.getPathName(), color);
		this.millilitersStored = millilitersStored;
	}

	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractFilledBreastPump)
					&& ((AbstractFilledBreastPump)o).getMilkProvidorId().equals(this.getMilkProvidorId())
					&& ((AbstractFilledBreastPump)o).getMilk().equals(milk);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + milkProvidor.hashCode();
		result = 31 * result + milk.hashCode();
		return result;
	}

	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("item");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "id", this.getItemType().getId());
		CharacterUtils.addAttribute(doc, element, "color", String.valueOf(this.getColor()));
		CharacterUtils.addAttribute(doc, element, "milkProvidor", this.getMilkProvidorId());
		CharacterUtils.addAttribute(doc, element, "millilitersStored", String.valueOf(this.getMillilitersStored()));

		Element innerElement = doc.createElement("itemEffects");
		element.appendChild(innerElement);
		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}

		innerElement = doc.createElement("milk");
		element.appendChild(innerElement);
		this.getMilk().saveAsXML(innerElement, doc);

		return element;
	}

	public static AbstractFilledBreastPump loadFromXML(Element parentElement, Document doc) {
		return new AbstractFilledBreastPump(
				ItemType.getIdToItemMap().get(parentElement.getAttribute("id")),
				Color.valueOf(parentElement.getAttribute("color")),
				parentElement.getAttribute("milkProvidor"),
				((Element) parentElement.getElementsByTagName("milk").item(0)==null
					?new FluidMilk(FluidType.MILK_HUMAN)
					:FluidMilk.loadFromXML((Element) parentElement.getElementsByTagName("milk").item(0), doc)),
				(parentElement.getAttribute("millilitersStored").isEmpty()
					?25
					:Integer.valueOf(parentElement.getAttribute("millilitersStored"))));
	}

	private String getSVGString(String pathName, Color color) {
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/" + pathName + ".svg");
			String s = Util.inputStreamToString(is);

			s = SvgUtil.colorReplacement(String.valueOf(this.hashCode()), color, s);

			is.close();

			return s;

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return "";
	}

	@Override
	public String applyEffect(GameCharacter user, GameCharacter target) {
		return target.ingestFluid(getMilkProvidor(), milk, SexAreaOrifice.MOUTH, millilitersStored)
				+ target.addItem(AbstractItemType.generateItem(ItemType.MOO_MILKER_EMPTY), false);
	}

	public String getMilkProvidorId() {
		return milkProvidor;
	}

	public GameCharacter getMilkProvidor() {
		try {
			return Main.game.getNPCById(milkProvidor);
		} catch (Exception e) {
			Util.logGetNpcByIdError("getMilkProvidor()", milkProvidor);
			return null;
		}
	}

	public FluidMilk getMilk() {
		return milk;
	}

	public int getMillilitersStored() {
		return millilitersStored;
	}

	public void setMillilitersStored(int millilitersStored) {
		this.millilitersStored = millilitersStored;
	}

}
