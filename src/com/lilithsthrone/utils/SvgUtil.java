package com.lilithsthrone.utils;

/**
 * Put any static util methods related to .svg files in here.
 *
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public class SvgUtil {

	public static String colorReplacementPattern(String gradientReplacementID, Color color, Color colorSecondary, Color colorTertiary, String inputString) {
		String s = inputString;

		s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
				gradientReplacementID + color.toString() + (colorSecondary!=null?colorSecondary.toString():"") + (colorTertiary!=null?colorTertiary.toString():"") + "$0");

		if(color!=null) {
			s = s.replaceAll("#f4d7d7", color.getShades()[0]);
			s = s.replaceAll("#e9afaf", color.getShades()[1]);
			s = s.replaceAll("#de8787", color.getShades()[2]);
			s = s.replaceAll("#d35f5f", color.getShades()[3]);
			s = s.replaceAll("#c83737", color.getShades()[4]);
		}

		if(colorSecondary!=null) {
			s = s.replaceAll("#f4e3d7", colorSecondary.getShades()[0]);
			s = s.replaceAll("#e9c6af", colorSecondary.getShades()[1]);
			s = s.replaceAll("#deaa87", colorSecondary.getShades()[2]);
			s = s.replaceAll("#d38d5f", colorSecondary.getShades()[3]);
			s = s.replaceAll("#c87137", colorSecondary.getShades()[4]);
		}

		if(colorTertiary!=null) {
			s = s.replaceAll("#f4eed7", colorTertiary.getShades()[0]);
			s = s.replaceAll("#e9ddaf", colorTertiary.getShades()[1]);
			s = s.replaceAll("#decd87", colorTertiary.getShades()[2]);
			s = s.replaceAll("#d3bc5f", colorTertiary.getShades()[3]);
			s = s.replaceAll("#c8ab37", colorTertiary.getShades()[4]);
		}

		return s;
	}

	public static String colorReplacement(String gradientReplacementID, Color color, String inputString) {
		return colorReplacement(gradientReplacementID, color, null, null, inputString);
	}

	public static String colorReplacement(String gradientReplacementID, Color color, Color colorSecondary, Color colorTertiary, String inputString) {
		String s = inputString;

		if(gradientReplacementID!=null) {
			s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
					gradientReplacementID + color.toString() + (colorSecondary!=null?colorSecondary.toString():"") + (colorTertiary!=null?colorTertiary.toString():"") + "$0");
		}

		// Fixes issue of SVG icons overflowing:
		s = s.replaceFirst("width=\"100%\"\\R   height=\"100%\"", "");

		if(color!=null) {
			s = s.replaceAll("#ff2a2a", color.getShades()[0]);
			s = s.replaceAll("#ff5555|#f55(?!\\d)", color.getShades()[1]);
			s = s.replaceAll("#ff8080", color.getShades()[2]);
			s = s.replaceAll("#ffaaaa|#faa(?!\\d)", color.getShades()[3]);
			s = s.replaceAll("#ffd5d5", color.getShades()[4]);
		}

		if(colorSecondary!=null) {
			s = s.replaceAll("#ff7f2a", colorSecondary.getShades()[0]);
			s = s.replaceAll("#ff9955|#f95(?!\\d)", colorSecondary.getShades()[1]);
			s = s.replaceAll("#ffb380", colorSecondary.getShades()[2]);
			s = s.replaceAll("#ffccaa|#fca(?!\\d)", colorSecondary.getShades()[3]);
			s = s.replaceAll("#ffe6d5", colorSecondary.getShades()[4]);
		}

		if(colorTertiary!=null) {
			s = s.replaceAll("#ffd42a", colorTertiary.getShades()[0]);
			s = s.replaceAll("#ffdd55|#fd5(?!\\d)", colorTertiary.getShades()[1]);
			s = s.replaceAll("#ffe680", colorTertiary.getShades()[2]);
			s = s.replaceAll("#ffeeaa|#fea(?!\\d)", colorTertiary.getShades()[3]);
			s = s.replaceAll("#fff6d5", colorTertiary.getShades()[4]);
		}

		return s;
	}

	public static String colorReplacement(String gradientReplacementID, BaseColor color, String inputString) {
		return colorReplacement(gradientReplacementID, color, null, null, inputString);
	}

	public static String colorReplacement(String gradientReplacementID, BaseColor color, BaseColor colorSecondary, BaseColor colorTertiary, String inputString) {
		String s = inputString;

		if(gradientReplacementID!=null) {
			s = s.replaceAll("linearGradient\\d|innoGrad\\d|radialGradient\\d",
					gradientReplacementID + color.toString() + (colorSecondary!=null?colorSecondary.toString():"") + (colorTertiary!=null?colorTertiary.toString():"") + "$0");
		}

		// Fixes issue of SVG icons overflowing:
		s = s.replaceFirst("width=\"100%\"\\R   height=\"100%\"", "");

		if(color!=null) {
			s = s.replaceAll("#ff2a2a", color.getShades()[0]);
			s = s.replaceAll("#ff5555|#f55(?!\\d)", color.getShades()[1]);
			s = s.replaceAll("#ff8080", color.getShades()[2]);
			s = s.replaceAll("#ffaaaa|#faa(?!\\d)", color.getShades()[3]);
			s = s.replaceAll("#ffd5d5", color.getShades()[4]);
		}

		if(colorSecondary!=null) {
			s = s.replaceAll("#ff7f2a", colorSecondary.getShades()[0]);
			s = s.replaceAll("#ff9955|#f95(?!\\d)", colorSecondary.getShades()[1]);
			s = s.replaceAll("#ffb380", colorSecondary.getShades()[2]);
			s = s.replaceAll("#ffccaa|#fca(?!\\d)", colorSecondary.getShades()[3]);
			s = s.replaceAll("#ffe6d5", colorSecondary.getShades()[4]);
		}

		if(colorTertiary!=null) {
			s = s.replaceAll("#ffd42a", colorTertiary.getShades()[0]);
			s = s.replaceAll("#ffdd55|#fd5(?!\\d)", colorTertiary.getShades()[1]);
			s = s.replaceAll("#ffe680", colorTertiary.getShades()[2]);
			s = s.replaceAll("#ffeeaa|#fea(?!\\d)", colorTertiary.getShades()[3]);
			s = s.replaceAll("#fff6d5", colorTertiary.getShades()[4]);
		}

		return s;
	}
}
