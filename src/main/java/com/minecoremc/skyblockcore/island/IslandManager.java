package com.minecoremc.skyblockcore.island;

import com.minecoremc.skyblockcore.SkyblockCore;
import com.minecoremc.skyblockcore.configuration.Config;
import com.minecoremc.skyblockcore.user.SkyblockUserData;
import me.dan.pluginapi.file.gson.GsonUtil;
import me.dan.pluginapi.manager.Manager;
import me.dan.pluginapi.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IslandManager extends Manager<Integer, Island> {

    private static final File DIRECTORY = new File(SkyblockCore.getInstance().getDataFolder(), "islands");

    private final List<Integer> pendingSave;

    public IslandManager() {
        loadAll();
        this.pendingSave = new ArrayList<>();
        long duration = Config.SAVE_INTERVAL.getInt() * 20L;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyblockCore.getInstance(), () -> Bukkit.getScheduler().runTaskAsynchronously(SkyblockCore.getInstance(), this::runSaveTask), duration, duration);
    }

    public void loadAll() {
        if (DIRECTORY == null || !DIRECTORY.exists()) {
            DIRECTORY.mkdirs();
            return;
        }

        if (DIRECTORY.listFiles() == null) {
            return;
        }

        for (File file : DIRECTORY.listFiles()) {
            Island island = GsonUtil.read(DIRECTORY, file.getName(), Island.class);
            insert(island.getId(), island);
        }

    }

    public void createIsland(Player owner) {
        UUID uuid = owner.getUniqueId();
        User user = User.get(uuid);
        SkyblockUserData skyblockUserData = user.getUserData(SkyblockUserData.class);

        int id = getNextFreeId();

        skyblockUserData.setIsland(id);

        Island island = new Island(id, uuid);

        insert(id, island);
    }

    public void saveIsland(Island island) {
        GsonUtil.save(DIRECTORY, island.getId() + "", Island.class);
    }

    public void runSaveTask() {
        for (int islandId : pendingSave) {
            saveIsland(get(islandId));
        }
        pendingSave.clear();

    }


}
