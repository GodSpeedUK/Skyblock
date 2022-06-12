package com.minecoremc.skyblockcore.island;

import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.user.SkyblockUserData;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.math.NumberUtil;
import me.dan.pluginapi.message.Placeholder;
import me.dan.pluginapi.user.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class Island {


    private final int id;

    private boolean open = false;

    private int level = 0;

    @Setter
    private UUID owner;

    private final List<UUID> members;
    private final List<UUID> invited;

    public Island(int id, UUID owner) {
        this.id = id;
        this.owner = owner;
        this.members = new ArrayList<>();
        this.invited = new ArrayList<>();
    }

    public void addMember(Player player) {
        UUID uuid = player.getUniqueId();
        members.add(uuid);
        SkyblockUserData userData = User.get(uuid).getUserData(SkyblockUserData.class);
        userData.setIsland(id);
    }

    private String getMemberList() {
        List<String> list = new ArrayList<>();
        for (UUID member : members) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(member);
            if (offlinePlayer.isOnline()) {
                list.add(Message.ISLAND_INFO_ONLINE.getString() + offlinePlayer.getName());
            } else {
                list.add(Message.ISLAND_INFO_OFFLINE.getString() + offlinePlayer.getName());
            }
        }
        Collections.sort(list);

        StringBuilder stringBuilder = new StringBuilder();

        int i = 1;

        for (String member : list) {
            stringBuilder.append(member);
            if (i != list.size()) {
                stringBuilder.append(Message.ISLAND_INFO_SEPARATOR.getString());
            }
        }

        return stringBuilder.toString();
    }

    public void sendInfoMessage(CommandSender commandSender) {
        Message.ISLAND_INFO.send(commandSender,
                new Placeholder("{owner}", Bukkit.getOfflinePlayer(owner).getName()),
                new Placeholder("{id}", id + ""),
                new Placeholder("{members}", getMemberList()),
                new Placeholder("{level}", NumberUtil.formatBigDecimal(BigDecimal.valueOf(level)))
        );
    }

    public void invite(Player inviter, Player target) {
        if (invited.contains(target.getUniqueId())) {
            Message.ALREADY_INVITED_TO_ISLAND.send(inviter);
            return;
        }
        invited.add(target.getUniqueId());
        Message.ISLAND_MEMBER_INVITE.send(inviter);
    }

    public boolean isInvited(Player player) {
        return invited.contains(player.getUniqueId());
    }

    public void removeInvite(OfflinePlayer offlinePlayer) {
        invited.remove(offlinePlayer.getUniqueId());
    }

    public void invite(OfflinePlayer offlinePlayer) {
        invited.add(offlinePlayer.getUniqueId());
    }

}
