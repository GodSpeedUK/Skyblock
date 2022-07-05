package tech.moondev.skyblockcore.scoreboard;

import java.util.*;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import tech.moondev.skyblockcore.SkyblockCore;
import tech.moondev.skyblockcore.configuration.Config;
import tech.moondev.skyblockcore.user.SkyblockUserData;

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
							.replace("{crystals}", crystals)
							.replace("{money}", String.valueOf(SkyblockCore.getInstance().getEcon().getBalance(player)))));
		}

		return lines;
	}
}