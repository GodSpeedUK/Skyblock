package com.minecoremc.skyblockcore.scoreboard;

import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class PlayerScoreboard {
	public static String[] TEAM_NAMES = new String[15];
	private Scoreboard scoreboard;
	private Objective objective;
	private Player player;
	private ScoreboardEntrySupplier supplier;

	static {
		for (int i = 0; i < 15; ++i) {
			TEAM_NAMES[i] = ChatColor.RESET.toString() + ChatColor.values()[i] + ChatColor.RESET.toString();
		}

	}

	protected PlayerScoreboard(Player player, ScoreboardEntrySupplier supplier) {
		this.player = player;
		this.supplier = supplier;
		Scoreboard scoreboard = player.getScoreboard();
		if (scoreboard == Bukkit.getScoreboardManager().getMainScoreboard()) {
			scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		}

		player.setScoreboard(this.scoreboard = scoreboard);
		Objective objective = scoreboard.getObjective("sidebar");
		if (objective == null) {
			objective = scoreboard.registerNewObjective("sidebar", "dummy");
		}

		objective.setDisplayName(supplier.getScoreboardTitle());
		(this.objective = objective).setDisplaySlot(DisplaySlot.SIDEBAR);
	}

	public void send() {
		List<String> entries = this.supplier.getScoreboardEntries(this.player);
		int index = 15;
		if (entries.size() != this.scoreboard.getEntries().size()) {
			this.scoreboard.getEntries().forEach((x) -> {
				this.scoreboard.resetScores(x);
			});
		}

		Iterator var4 = entries.iterator();

		while (var4.hasNext()) {
			String entry = (String) var4.next();
			if (index >= 1) {
				String left = "";
				String right = "";
				if (entry.length() <= 16) {
					left = entry;
				} else {
					String first = entry.substring(0, 16);
					String second = entry.substring(16, entry.length());
					if (first.endsWith("�")) {
						first = first.substring(0, first.length() - 1);
						second = "�" + second;
					}

					second = ChatColor.getLastColors(first) + second;
					left = first;
					right = second.substring(0, Math.min(second.length(), 16));
					if (right.endsWith("�")) {
						right = right.substring(0, right.length() - 1);
					}
				}

				Team team = this.scoreboard.getTeam(TEAM_NAMES[15 - index]);
				if (team == null) {
					team = this.scoreboard.registerNewTeam(TEAM_NAMES[15 - index]);
				}

				if (!team.hasEntry(TEAM_NAMES[15 - index])) {
					team.addEntry(TEAM_NAMES[15 - index]);
				}

				team.setPrefix(left);
				team.setSuffix(right);
				this.objective.getScore(TEAM_NAMES[15 - index]).setScore(index--);
			}
		}

	}

	public void clean() {
		if (this.player.isOnline()) {
			this.player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}

		this.scoreboard.clearSlot(DisplaySlot.SIDEBAR);
		this.scoreboard.getTeams().forEach((x) -> {
			x.unregister();
		});
		this.scoreboard.getObjectives().forEach((x) -> {
			x.unregister();
		});
		this.player = null;
		this.objective = null;
		this.scoreboard = null;
	}
}