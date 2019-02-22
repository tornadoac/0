package com.lilithsthrone.game.character.markings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.ColorListPresets;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class TattooWriting implements XMLSaving {
	
	private String text;
	private Color color;
	private boolean glow;
	private List<TattooWritingStyle> styles;
	
	public TattooWriting(String text, Color color, boolean glow, TattooWritingStyle... styles) {
		this.text = text;
		this.color = color;
		this.glow = glow;
		this.styles = new ArrayList<>();
		Collections.addAll(this.styles, styles);
	}
	
	public static List<Color> getAvailableColors() {
		return ColorListPresets.ALL.getPresetColorList();
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof TattooWriting)
					&& ((TattooWriting)o).getText().equals(this.getText())
					&& ((TattooWriting)o).getColor().equals(this.getColor())
					&& ((TattooWriting)o).isGlow()==glow
					&& ((TattooWriting)o).getStyles().equals(this.getStyles());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getText().hashCode();
		result = 31 * result + getColor().hashCode();
		result = 31 * result + (isGlow() ? 1 : 0);
		result = 31 * result + getStyles().hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("tattooWriting");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "color", this.getColor().toString());
		CharacterUtils.addAttribute(doc, element, "glow", String.valueOf(this.isGlow()));
		
		element.appendChild(doc.createCDATASection(this.getText().trim()));
		
		Element innerElement = doc.createElement("styles");
		element.appendChild(innerElement);
		for(TattooWritingStyle style : this.getStyles()) {
			Element styleElement = doc.createElement("style");
			innerElement.appendChild(styleElement);
			CharacterUtils.addAttribute(doc, styleElement, "value", style.toString());
		}
		
		return element;
	}
	
	public static TattooWriting loadFromXML(Element parentElement, Document doc) {
		try {
			List<TattooWritingStyle> importedStyles = new ArrayList<>();
			try {
				NodeList stylesList = ((Element) parentElement.getElementsByTagName("styles").item(0)).getElementsByTagName("style");
				for(int i=0; i<stylesList.getLength(); i++){
					Element e = ((Element)stylesList.item(i));
					
					TattooWritingStyle style = TattooWritingStyle.valueOf(e.getAttribute("value"));
					importedStyles.add(style);
				}
			} catch(Exception ex) {
			}
			
			String text = parentElement.getTextContent();
			
			TattooWriting tw = new TattooWriting(text.trim(),
					Color.valueOf(parentElement.getAttribute("color")),
					Boolean.valueOf(parentElement.getAttribute("glow")));
			
			tw.styles = importedStyles;
			
			return tw;
			
		} catch(Exception ex) {
			System.err.println("Warning: An instance of TattooWriting was unable to be imported!");
			return null;
		}
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean isGlow() {
		return glow;
	}
	
	public List<TattooWritingStyle> getStyles() {
		return styles;
	}

	public void addStyle(TattooWritingStyle style) {
		this.styles.add(style);
	}

	public void removeStyle(TattooWritingStyle style) {
		this.styles.remove(style);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void setGlow(boolean glow) {
		this.glow = glow;
	}

}
