package com.lilithsthrone.utils;

import javafx.scene.paint.Color;

/**
 * @since 0.1.69
 * @version 0.2.7
 * @author Innoxia
 */
public enum BaseColor {

	WHITE(Util.newColor(0xFFFFFF), Util.newColor(0x636363)),
	SILVER(Util.newColor(0xF3F3F3), Util.newColor(0x636363)),
	PLATINUM(Util.newColor(0xE4E5E2), Util.newColor(0xE4E5E2)),

	ROSE(Util.newColor(0xEBC2FF), Util.newColor(0xB800E6)),
	LILAC(Util.newColor(0x978AFF), Util.newColor(0x8170FF)),
	LILAC_LIGHT(Util.newColor(0xC2BDFF), Util.newColor(0x8E3DFF)),
	PURPLE_DARK(Util.newColor(0x740AFF), Util.newColor(0x6000D6)),
	PURPLE(Util.newColor(0xB980FF), Util.newColor(0x943DFF)),
	PURPLE_LIGHT(Util.newColor(0xDA8FFF), Util.newColor(0xC552FF)),

	VIOLET(Util.newColor(0xFF99C9), Util.newColor(0xFF57A5)),
	PINK(Util.newColor(0xFF6BDA), Util.newColor(0xFF0FC3)),
	PINK_LIGHT(Util.newColor(0xF5A8FF), Util.newColor(0xCF26BE)),
	PINK_DEEP(Util.newColor(0xFF33CC), Util.newColor(0xFF33CC)),

	MAGENTA(Util.newColor(0xFF1472), Util.newColor(0xFF1472)),
	CRIMSON(Util.newColor(0xFF385D), Util.newColor(0xFF385D)),
	RED(Util.newColor(0xEA5D76), Util.newColor(0xDD1D40)),
	RED_DARK(Util.newColor(0xA61641), Util.newColor(0x960012)),
	RED_LIGHT(Util.newColor(0xEE95A6), Util.newColor(0xE9536F)),
	ROSE_GOLD(Util.newColor(0xE7C1BB), Util.newColor(0xE7C1BB)),

	TAN(Util.newColor(0xEDC491), Util.newColor(0xDC8D2E)),
	BROWN(Util.newColor(0xD0A38B), Util.newColor(0xB5714A)),
	BROWN_DARK(Util.newColor(0x9F775B), Util.newColor(0x785945)),
	AUBURN(Util.newColor(0xCF6654), Util.newColor(0xBB4935)),
	ORANGE(Util.newColor(0xFF9970), Util.newColor(0xFA4700)),
	AMBER(Util.newColor(0xFFC552), Util.newColor(0xBD7E00)),
	GINGER(Util.newColor(0xFF9147), Util.newColor(0xF06000)),
	COPPER(Util.newColor(0xD46F2B), Util.newColor(0xB96227)),

	GOLD(Util.newColor(0xFFCC00), Util.newColor(0xCCA300)),
	YELLOW(Util.newColor(0xECEC5B), Util.newColor(0xC4C700)),
	YELLOW_LIGHT(Util.newColor(0xF8F8B9), Util.newColor(0xC1A42F)),

	GREEN_LIME(Util.newColor(0xB4D987), Util.newColor(0x83BE3C)),
	GREEN_LIGHT(Util.newColor(0x8fefbf), Util.newColor(0x1DB96B)),
	GREEN(Util.newColor(0x57DB7E), Util.newColor(0x0D683B)),
	GREEN_DARK(Util.newColor(0x209746), Util.newColor(0x209746)),

	AQUA(Util.newColor(0x61FFFF), Util.newColor(0x009999)), // Ne, Kazuma...
	TEAL(Util.newColor(0x6CBCB1), Util.newColor(0x439389)),
	PERIWINKLE(Util.newColor(0xCCCCFF), Util.newColor(0xCCCCFF)),
	BLUE_LIGHT(Util.newColor(0x99EBFF), Util.newColor(0x00A7D1)),
	BLUE(Util.newColor(0x05CDFF), Util.newColor(0x00627A)),
	BLUE_DARK(Util.newColor(0x0090BF), Util.newColor(0x002E43)),
	BLUE_STEEL(Util.newColor(0xA7B7D2), Util.newColor(0x5671A4)),

	GREY(Util.newColor(0xB3B3B3), Util.newColor(0x777777)),
	GREY_DARK(Util.newColor(0x999999), Util.newColor(0x515151)),
	PITCH_BLACK(Util.newColor(0x222222), Util.newColor(0x222222)),
	BLACK(Util.newColor(0x777777), Util.newColor(0x1F1F1F));

	private Color color, lightColor;

	private BaseColor(Color color, Color lightColor) {
		this.color = color;
		this.lightColor = lightColor;
	}

	public Color getColor() {
		return color;
	}

	public Color getLightColor() {
		return lightColor;
	}

	public String toWebHexString() {
		return getColor().toString().substring(2, 8);
	}

	/**
	 * Shades goes from dark to light. So shades[0] is the darkest.
	 * length 5
	 */
	public String[] getShades() {
		String[] shadesString = new String[5];
		float luminosity = -0.3f;
		int red = Integer.parseInt(color.toString().substring(2, 4), 16);
		int gre = Integer.parseInt(color.toString().substring(4, 6), 16);
		int blu = Integer.parseInt(color.toString().substring(6, 8), 16);
		int r, g, b;

		for (int i = 0; i < 5; i++) {
			r = red + (int)(red * (i * 0.15f + luminosity));
			r = Math.max(Math.min(r, 255), 0);

			g = gre + (int)(gre * (i * 0.15f + luminosity));
			g = Math.max(Math.min(g, 255), 0);

			b = blu + (int)(blu * (i * 0.15f + luminosity));
			b = Math.max(Math.min(b, 255), 0);

			shadesString[i] = String.format("#%02X%02X%02X", r, g, b);
		}

		return shadesString;
	}
}
