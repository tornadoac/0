package com.lilithsthrone.game.character.markings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.ColorListPresets;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class AbstractTattooType extends AbstractCoreType {
	
	private static List<InventorySlot> standardInventorySlots = new ArrayList<>(InventorySlot.getClothingSlots());
	
	private boolean isMod;
	
	private int value;
	
	private int enchantmentLimit;
	
	private List<InventorySlot> slotAvailability;

	private String name;
	private String description;

	private List<Color> availablePrimaryColors;
	private List<Color> availableSecondaryColors;
	private List<Color> availableTertiaryColors;
	
	private String pathName;
	private Map<Color, Map<Color, Map<Color, String>>> SVGStringMap;
	
	public AbstractTattooType(
			String pathName,
			String name,
			String description,
			List<Color> availablePrimaryColors,
			List<Color> availableSecondaryColors,
			List<Color> availableTertiaryColors,
			List<InventorySlot> slotAvailability) {

		this.isMod = false;
		
		value = 500;
		enchantmentLimit = 5;
		
		this.pathName = pathName;
		this.name = name;
		this.description = description;
		
		this.availablePrimaryColors = new ArrayList<>();
		if (availablePrimaryColors == null) {
			this.availablePrimaryColors.add(Color.CLOTHING_BLACK);
		} else {
			this.availablePrimaryColors.addAll(availablePrimaryColors);
		}

		this.availableSecondaryColors = new ArrayList<>();
		if (availableSecondaryColors != null) {
			this.availableSecondaryColors.addAll(availableSecondaryColors);
		}

		this.availableTertiaryColors = new ArrayList<>();
		if (availableTertiaryColors != null) {
			this.availableTertiaryColors.addAll(availableTertiaryColors);
		}
		
		SVGStringMap = new HashMap<>();
		
		if(slotAvailability==null) {
			this.slotAvailability = standardInventorySlots;
		} else {
			this.slotAvailability = slotAvailability;
		}
	}
	

	public AbstractTattooType(File tattooXMLFile) {

		if (tattooXMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(tattooXMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element clothingElement = (Element) doc.getElementsByTagName("tattoo").item(0);
				
				Element coreAttributes;
				if(clothingElement.getElementsByTagName("coreAtributes").getLength()>0) {
					coreAttributes = (Element) clothingElement.getElementsByTagName("coreAtributes").item(0); // Support for old versions
				} else {
					coreAttributes = (Element) clothingElement.getElementsByTagName("coreAttributes").item(0); // Fix typo
				}
				
				List<InventorySlot> slotAvailability = new ArrayList<>();
				NodeList slotAvailabilityNodeList = ((Element)coreAttributes.getElementsByTagName("slotAvailability").item(0)).getElementsByTagName("slot");
				try {
					for(int i = 0; i < slotAvailabilityNodeList.getLength(); i++){
						Element e = ((Element)slotAvailabilityNodeList.item(i));
						slotAvailability.add(InventorySlot.valueOf(e.getTextContent()));
					}
					if (slotAvailability.isEmpty()) {
						slotAvailability = standardInventorySlots;
					}
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'slotAvailability' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}
				this.slotAvailability = slotAvailability;
				
				this.isMod = true;
				
				this.value = Integer.valueOf(coreAttributes.getElementsByTagName("value").item(0).getTextContent());
				this.pathName = tattooXMLFile.getParentFile().getAbsolutePath() + "/" + coreAttributes.getElementsByTagName("imageName").item(0).getTextContent();
				this.name = coreAttributes.getElementsByTagName("name").item(0).getTextContent();
				this.description = coreAttributes.getElementsByTagName("description").item(0).getTextContent();
				
				try {
					enchantmentLimit = Integer.valueOf(coreAttributes.getElementsByTagName("enchantmentLimit").item(0).getTextContent());
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'enchantmentLimit' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}
				
				List<Color> importedPrimaryColors = new ArrayList<>();
				try {
					importedPrimaryColors = readColorsFromElement(coreAttributes, "primaryColors");
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'primaryColors' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}

				List<Color> importedSecondaryColors = new ArrayList<>();
				try {
					importedSecondaryColors = readColorsFromElement(coreAttributes, "secondaryColors");
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'secondaryColors' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}

				List<Color> importedTertiaryColors = new ArrayList<>();
				try {
					importedTertiaryColors = readColorsFromElement(coreAttributes, "tertiaryColors");
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'tertiaryColors' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}
				
				this.availablePrimaryColors = new ArrayList<>(importedPrimaryColors);

				this.availableSecondaryColors = new ArrayList<>(importedSecondaryColors);

				this.availableTertiaryColors = new ArrayList<>(importedTertiaryColors);
				
				SVGStringMap = new HashMap<>();

			} catch(Exception ex) {
				System.err.println("TattooType was unable to be loaded from file! (" + tattooXMLFile.getName() + ")\n" + ex);
			}
		}
	}


	private List<Color> readColorsFromElement(Element coreAttributes, String elementTagName) {
		Element colorsElement = ((Element)coreAttributes.getElementsByTagName("primaryColors").item(0));
		if(colorsElement.getAttribute("values").isEmpty()) {
			NodeList colorsNodeList = colorsElement.getElementsByTagName("color");
			List<Color> result = new ArrayList<>(colorsNodeList.getLength());
			for(int i = 0; i < colorsNodeList.getLength(); i++){
				result.add(Color.valueOf(((Element)colorsNodeList.item(i)).getTextContent()));
			}
			return result;
		}
		return ColorListPresets.valueOf(colorsElement.getAttribute("values")).getPresetColorList();
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractTattooType)
					&& ((AbstractTattooType)o).isMod()==isMod
					&& ((AbstractTattooType)o).getSlotAvailability().equals(this.getSlotAvailability())
					&& ((AbstractTattooType)o).getName().equals(this.getName())
					&& ((AbstractTattooType)o).getDescription().equals(this.getDescription())
					&& ((AbstractTattooType)o).getAvailablePrimaryColors().equals(this.getAvailablePrimaryColors())
					&& ((AbstractTattooType)o).getAvailableSecondaryColors().equals(this.getAvailableSecondaryColors())
					&& ((AbstractTattooType)o).getAvailableTertiaryColors().equals(this.getAvailableTertiaryColors())
					&& ((AbstractTattooType)o).getPathName().equals(this.getPathName());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (this.isMod() ? 1 : 0);
		
		result = 31 * result + getSlotAvailability().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getDescription().hashCode();
		
		result = 31 * result + getAvailablePrimaryColors().hashCode();
		result = 31 * result + getAvailableSecondaryColors().hashCode();
		result = 31 * result + getAvailableTertiaryColors().hashCode();
		
		result = 31 * result + getPathName().hashCode();
		
		return result;
	}
	
	public boolean isMod() {
		return isMod;
	}
	
	public int getValue() {
		return value;
	}


	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Color> getAvailablePrimaryColors() {
		return availablePrimaryColors;
	}

	public List<Color> getAvailableSecondaryColors() {
		return availableSecondaryColors;
	}

	public List<Color> getAvailableTertiaryColors() {
		return availableTertiaryColors;
	}

	public List<InventorySlot> getSlotAvailability() {
		return slotAvailability;
	}
	
	public String getId() {
		return TattooType.getIdFromTattooType(this);
	}
	
	public int getEnchantmentLimit() {
		return enchantmentLimit;
	}
	
	public AbstractItemEffectType getEnchantmentEffect() {
		return ItemEffectType.TATTOO;
	}
	
	private void addSVGStringMapping(Color color, Color colorSecondary, Color colorTertiary, String s) {
		if(SVGStringMap.get(color)==null) {
			SVGStringMap.put(color, new HashMap<>());
			SVGStringMap.get(color).put(colorSecondary, new HashMap<>());
			
		} else if(SVGStringMap.get(color).get(colorSecondary)==null) {
			SVGStringMap.get(color).put(colorSecondary, new HashMap<>());
		}
		
		SVGStringMap.get(color).get(colorSecondary).put(colorTertiary, s);
	}
	
	private String getSVGStringFromMap(Color color, Color colorSecondary, Color colorTertiary) {
		if(SVGStringMap.get(color)==null) {
			return null;
		} else {
			if(SVGStringMap.get(color).get(colorSecondary)==null) {
				return null;
			} else {
				return SVGStringMap.get(color).get(colorSecondary).get(colorTertiary);
			}
		}
	}
	
	public String getSVGImage(GameCharacter character, Color color, Color colorSecondary, Color colorTertiary) {
		if (!availablePrimaryColors.contains(color) || pathName==null || pathName.isEmpty()) {
			return "";
		}
		
		String stringFromMap = getSVGStringFromMap(color, colorSecondary, colorTertiary);
		if (stringFromMap!=null) {
			return stringFromMap;
			
		} else {
			if (availablePrimaryColors.contains(color)) {
				try {
					InputStream is;
					String s;
					if(isMod) {
						List<String> lines = Files.readAllLines(Paths.get(pathName));
						StringBuilder sb = new StringBuilder();
						for(String line : lines) {
							sb.append(line);
						}
						s = sb.toString();
						
					} else {
						is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/tattoos/" + pathName + ".svg");
						s = Util.inputStreamToString(is);
						is.close();
					}
					
					s = SvgUtil.colorReplacement(this.getId(), color, colorSecondary, colorTertiary, s);
					
					addSVGStringMapping(color, colorSecondary, colorTertiary, s);
					
					return s;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		return "";
	}

	public String getPathName() {
		return pathName;
	}
}
