package com.lilithsthrone.rendering;

import java.util.List;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class Artist {
	
	private String name;
	private Color color;
	private String folderName;
	private List<ArtistWebsite> websites;
	
	public Artist(String name, Color color, String folderName, List<ArtistWebsite> websites) {
		this.name = name;
		this.color = color;
		this.folderName = folderName;
		this.websites = websites;
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}

	public String getFolderName() {
		return folderName;
	}
	
	public List<ArtistWebsite> getWebsites() {
		return websites;
	}
	
}
