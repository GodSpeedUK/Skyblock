package tech.moondev.skyblockcore.scoreboard;

import java.util.List;
import org.bukkit.entity.Player;

public interface ScoreboardEntrySupplier {
	String getScoreboardTitle();

	List<String> getScoreboardEntries(Player var1);
}