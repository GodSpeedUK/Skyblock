package com.minecoremc.skyblockcore.island;

import com.minecoremc.skyblockcore.SkyblockCore;
import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.user.SkyblockUserData;
import me.dan.pluginapi.file.gson.GsonUtil;
import me.dan.pluginapi.manager.Manager;
import me.dan.pluginapi.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class IslandManager extends Manager<Integer, Island> {

    private static final File DIRECTORY = new File(SkyblockCore.getInstance().getDataFolder(), "islands");

    private final List<Integer> pendingSave;
    private final HashMap<UUID, Island> members;
    private final List<UUID> invited;

    public IslandManager() {
        loadAll();
        this.pendingSave = new ArrayList<>();
        this.members = new HashMap<>();
        this.invited = new ArrayList<>();
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

    public void invite(Player inviter, Player target) {
        if(invited.contains(target.getUniqueId())) {
            Messages.ALREADY_INVITED_TO_ISLAND.send(inviter);
            return;
        }
        invited.add(target.getUniqueId());
        Messages.ISLAND_MEMBER_INVITE.send(inviter);
    }

    public void joinIsland(Player target, Player player) {
        UUID uuid = target.getUniqueId();
        User user = User.get(uuid);
        SkyblockUserData skyblockUserData = user.getUserData(SkyblockUserData.class);

        for (File file : DIRECTORY.listFiles()) {
            Island island = GsonUtil.read(DIRECTORY, file.getName(), Island.class);

            if (island.getOwner() != uuid) return;
            if (!invited.contains(player.getUniqueId())) return;
            if (members.size() >= Config.ISLAND_MAX_MEMBERS.getInt()) return;

            Messages.ISLAND_JOINED.send(player);
            members.put(player.getUniqueId(), island);
        }
    }

    public void islandMembers(Player islandID, Player player) {
        UUID uuid = islandID.getUniqueId();

        for (File file : DIRECTORY.listFiles()) {
            Island island = GsonUtil.read(DIRECTORY, file.getName(), Island.class);
            if (island == null) return;
            if (island.getOwner() != uuid) return;

            for (UUID memberUUID : members.keySet()) {
                String memberName = memberUUID.toString();
                String island2 = members.get(memberUUID).toString();
                player.sendMessage(memberName + " " + island2);
            }
        }
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
