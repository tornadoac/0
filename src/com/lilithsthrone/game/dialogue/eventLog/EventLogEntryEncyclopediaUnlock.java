package com.lilithsthrone.game.dialogue.eventLog;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColor;
import com.lilithsthrone.utils.Color;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntryEncyclopediaUnlock extends EventLogEntry {

	public EventLogEntryEncyclopediaUnlock(String description, Color highlightDescriptionColor) {
		super(Main.game.getMinutesPassed(), "Encyclopedia",  "<span style='color:"+highlightDescriptionColor.toWebHexString()+";'>"+Util.capitalizeSentence(description)+"</span>");
	}

	public EventLogEntryEncyclopediaUnlock(String description, BaseColor highlightDescriptionColor) {
		super(Main.game.getMinutesPassed(), "Encyclopedia",  "<span style='color:"+highlightDescriptionColor.toWebHexString()+";'>"+Util.capitalizeSentence(description)+"</span>");
	}

	@Override
	public String getFormattedEntry() {
		return "<span style='color:"+Color.GENERIC_EXCELLENT.toWebHexString()+";'>Encyclopedia</span>: "+description;
	}

	@Override
	public String getMainDialogueDescription() {
		return "<p style='text-align:center;'>"
				+ "<b style='color:"+Color.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia</b>"
				+ "<br/>"
				+ description
			+ "</p>";
	}

}
