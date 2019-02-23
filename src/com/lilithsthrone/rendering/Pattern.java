package com.lilithsthrone.rendering;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.SvgUtil;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Irbynx
 */
public class Pattern {

	private static Map<String, Pattern> allPatterns;

	private String name;

	private boolean primaryRecolorAvailable;
	private boolean secondaryRecolorAvailable;
	private boolean tertiaryRecolorAvailable;

	private String baseSVGString;
	private Map<Color, Map<Color, Map<Color, String>>> SVGStringMap;

	static {
		allPatterns = new TreeMap<>();

		File dir = new File("res/patterns");

		allPatterns.put("none", new Pattern("none")); // Adding empty pattern

		if(dir.exists()) {
			FilenameFilter textFilter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					String lowercaseName = name.toLowerCase();
					if (lowercaseName.endsWith(".svg")) {
						return true;
					} else {
						return false;
					}
				}
			};

			for(File subFile : dir.listFiles(textFilter)) {
				if (subFile.exists()) {
					try {
						String newPatternName = subFile.getName().substring(0, subFile.getName().indexOf(".svg"));
						allPatterns.put(newPatternName, new Pattern(newPatternName));
						//System.out.println("Added Pattern: " + newPatternName);

					} catch(Exception ex) {
					}
				}
			}
		}
	}

	public Pattern(String name) {
		this.name = name;
		SVGStringMap = new HashMap<>();

		baseSVGString = "";
		if(!name.equals("none")) {
			try {
				File patternFile = new File(System.getProperty("user.dir")+"/res/patterns/" + this.getName().toLowerCase() + ".svg");
				List<String> lines = Files.readAllLines(patternFile.toPath());
				StringBuilder sb = new StringBuilder();
				for(String line : lines) {
					sb.append(line);
				}
				baseSVGString = sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}

			primaryRecolorAvailable =
					baseSVGString.contains("#f4d7d7")
					|| baseSVGString.contains("#e9afaf")
					|| baseSVGString.contains("#de8787")
					|| baseSVGString.contains("#d35f5f")
					|| baseSVGString.contains("#c83737");

			secondaryRecolorAvailable =
					baseSVGString.contains("#f4e3d7")
					|| baseSVGString.contains("#e9c6af")
					|| baseSVGString.contains("#deaa87")
					|| baseSVGString.contains("#d38d5f")
					|| baseSVGString.contains("#c87137");

			tertiaryRecolorAvailable =
					baseSVGString.contains("#f4eed7")
					|| baseSVGString.contains("#e9ddaf")
					|| baseSVGString.contains("#decd87")
					|| baseSVGString.contains("#d3bc5f")
					|| baseSVGString.contains("#c8ab37");
		}

	}

	/**
	 * Checks all found patterns and returns one if available.
	 */
	public static Pattern getPattern(String name) {
		return allPatterns.get(name);
	}

	/**
	 * Returns all available patterns
	 */
	public static Map<String, Pattern> getAllPatterns() {
		return allPatterns;
	}

	/**
	 * Returns name of the pattern
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns human readable name.
	 * @return
	 */
	public String getNiceName() {
		return this.name.replace('_', ' ');
	}

	public boolean isPrimaryRecolorAvailable() {
		return primaryRecolorAvailable;
	}

	public boolean isSecondaryRecolorAvailable() {
		return secondaryRecolorAvailable;
	}

	public boolean isTertiaryRecolorAvailable() {
		return tertiaryRecolorAvailable;
	}

	public String getSVGString(Color color, Color colorSecondary, Color colorTertiary) {
		if(!SVGStringMap.containsKey(color) || !SVGStringMap.get(color).containsKey(colorSecondary) || !SVGStringMap.get(color).get(colorSecondary).containsKey(colorTertiary)) {
			generateSVGImage(color, colorSecondary, colorTertiary);
		}
		return SVGStringMap.get(color).get(colorSecondary).get(colorTertiary);
	}

	private void generateSVGImage(Color color, Color colorSecondary, Color colorTertiary) {
		addSVGStringMapping(color, colorSecondary, colorTertiary, SvgUtil.colorReplacementPattern(this.getName(), color, colorSecondary, colorTertiary, baseSVGString));
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
}


// Can't find a good place to put the list for the future, so I'll put it here:
// Items supporting patterns as of the creation of the system:
//
// Bikini Bottoms
// Backless Panties
// Boxers
// Boyshorts
// Thigh highs
// Work boots
// Swimsuit
// Bikini
// Briefs
// Crotchless briefs
// Crotchless panties
// Crotchless thong
// Panties
// Vstring
// Elbow Length gloves
// Fingerless gloves
// Gloves
// Cargo trousers
// Thigh high socks
// Knee high socks
// Tshirt
