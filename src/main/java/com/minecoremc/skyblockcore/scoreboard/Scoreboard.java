package com.minecoremc.skyblockcore.scoreboard;

import java.util.*;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.*;
import net.milkbowl.vault.chat.*;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class Scoreboard implements ScoreboardEntrySupplier {
	
	public SkyblockCore plugin;

	public String getScoreboardTitle() {
		return ChatColor.translateAlternateColorCodes('&', Config.SCOREBOARD_TITLE.getString());
	}

	public List<String> getScoreboardEntries(Player player) {
		List<String> lines = new ArrayList();
		for (final String scoreboard : Config.SCOREBOARD_BOARD.getStringList()) {
			lines.add(ChatColor.translateAlternateColorCodes('&', scoreboard.replace("{player}", player.getName())));
		}

		return lines;
	}
}