package com.lilithsthrone.game.sex.managers.dominion;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class SMStallFaceToWall extends SexManagerDefault {

	public SMStallFaceToWall(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionBipeds.FACING_WALL_STALL,
				dominants,
				submissives);

	}

}
