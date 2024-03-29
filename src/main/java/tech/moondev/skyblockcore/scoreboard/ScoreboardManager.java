package tech.moondev.skyblockcore.scoreboard;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import tech.moondev.skyblockcore.SkyblockCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.scheduler.BukkitTask;

public class ScoreboardManager implements Listener {
    private final ScoreboardEntrySupplier supplier;
    private boolean registered = true;
    private final BukkitTask task;
    private final Map<UUID, PlayerScoreboard> scoreboards;

    public ScoreboardManager(ScoreboardEntrySupplier supplier) {
        this.supplier = supplier;
        this.scoreboards = new ConcurrentHashMap();
        Bukkit.getOnlinePlayers().forEach((x) -> {
            this.scoreboards.put(x.getUniqueId(), new PlayerScoreboard(x, supplier));
        });
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(SkyblockCore.getInstance(), () -> {
            this.scoreboards.forEach((id, scoreboard) -> {
                scoreboard.send();
            });
        }, 15L, 15L);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.scoreboards.put(player.getUniqueId(), new PlayerScoreboard(player, this.supplier));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerScoreboard scoreboard = (PlayerScoreboard) this.scoreboards.remove(player.getUniqueId());
        if (scoreboard != null) {
            scoreboard.clean();
        }

    }

    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        if (event.getPlugin() == SkyblockCore.getInstance()) {
            this.unregister();
        }

    }

    public void unregister() {
        if (this.registered) {
            this.registered = false;
            HandlerList.unregisterAll(this);
            if (this.task != null && Bukkit.getScheduler().isCurrentlyRunning(this.task.getTaskId())) {
                this.task.cancel();
            }

            this.scoreboards.forEach((id, scoreboard) -> {
                scoreboard.clean();
            });
            this.scoreboards.clear();
        }

    }
}