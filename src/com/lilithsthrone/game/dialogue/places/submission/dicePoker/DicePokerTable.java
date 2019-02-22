package com.lilithsthrone.game.dialogue.places.submission.dicePoker;

import com.lilithsthrone.utils.Color;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum DicePokerTable {
	
	COPPER("copper", 500, 250, Color.CLOTHING_COPPER),
	SILVER("silver", 2500, 1000, Color.CLOTHING_SILVER),
	GOLD("gold", 10000, 5000, Color.CLOTHING_GOLD);
	
	private String name;
	private Color color;
	private int initialBet;
	private int raiseAmount;
	
	private DicePokerTable(String name, int initialBet, int raiseAmount, Color color) {
		this.name = name;
		this.color = color;
		this.initialBet = initialBet;
		this.raiseAmount = raiseAmount;
	}
	
	public String getName() {
		return name;
	}
	
	public int getInitialBet() {
		return initialBet;
	}

	public int getRaiseAmount() {
		return raiseAmount;
	}

	public Color getColor() {
		return color;
	}
}
