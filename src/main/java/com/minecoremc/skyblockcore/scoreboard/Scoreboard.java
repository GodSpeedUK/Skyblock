package com.minecoremc.skyblockcore.scoreboard;

import java.util.*;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.user.*;
import net.milkbowl.vault.chat.*;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class Scoreboard implements ScoreboardEntrySupplier {

	public String getScoreboardTitle() {
		return ChatColor.translateAlternateColorCodes('&', Config.SCOREBOARD_TITLE.getString());
	}

	public List<String> getScoreboardEntries(Player player) {
		List<String> lines = new ArrayList();

		SkyblockUserData sud = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);

		String souls = String.valueOf(sud.getSouls());
		String crystals = String.valueOf(sud.getCrystals());

		for (final String scoreboard : Config.SCOREBOARD_BOARD.getStringList()) {
			lines.add(ChatColor.translateAlternateColorCodes('&',
					scoreboard.replace("{player}", player.getName())
							.replace("{souls}", souls)
							.replace("{crystals}", crystals)));
		}

		return lines;
	}
}