package com.minecoremc.skyblockcore.command.subcommand;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.island.Island;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.message.Placeholder;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

public class InfoCommand extends AbstractSubCommand {

    public InfoCommand() {
        super("/island info (Player)", Collections.singletonList("info"));
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        String[] args = commandContext.getArgs();

        Optional<Island> targetIsland;

        if (args.length < 2) {
            SkyblockUserData ud = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);
            if (!ud.hasIsland()) {
                Message.ISLAND_NO_ISLAND.send(player);
                return;
            }
            targetIsland = getIsland(player);
        } else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(commandContext.getArgs()[1]);
            if (!target.hasPlayedBefore()) {
                Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", target.getName()));
                return;
            }

            targetIsland = getIsland(target);
        }

        if (!targetIsland.isPresent()) {
            Message.ISLAND_NOT_FOUND.send(player);
            return;
        }

        Island island = targetIsland.get();

        island.sendInfoMessage(player);
    }

    private Optional<Island> getIsland(OfflinePlayer offlinePlayer) {

        SkyblockUserData ud = User.get(offlinePlayer.getUniqueId()).getUserData(SkyblockUserData.class);
        if (!ud.hasIsland()) {
            return Optional.empty();
        }

        return Optional.ofNullable(SkyblockCore.getInstance().getIslandManager().get(ud.getIsland()));
    }
}
