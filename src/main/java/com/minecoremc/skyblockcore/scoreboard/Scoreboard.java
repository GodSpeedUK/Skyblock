package com.minecoremc.skyblockcore.scoreboard;

import java.util.*;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.Message;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class Scoreboard implements ScoreboardEntrySupplier {
	
	public SkyblockCore plugin;

	public String getScoreboardTitle() {
		return ChatColor.translateAlternateColorCodes('&', Message.SCOREBOARD_TITLE.getString());
	}

	public List<String> getScoreboardEntries(Player player) {
		List<String> lines = new ArrayList();
		for (final String scoreboard : Message.SCOREBOARD_BOARD.getStringList()) {
			lines.add(Message.SCOREBOARD_BOARD.getString().replace("{player}", player.getName()));
		}

		return lines;
	}
}