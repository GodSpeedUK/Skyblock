package com.minecoremc.skyblockcore.command.subcommand;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.island.Island;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.configurable.Messages;
import me.dan.pluginapi.message.Placeholder;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

public class JoinCommand extends AbstractSubCommand {

    public JoinCommand() {
        super("/island join (Player)", Collections.singletonList("Join"));
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        String[] args = commandContext.getArgs();

        if (args.length < 2) {
            Messages.USAGE.send(player, new Placeholder("{usage}", getUsage()));
            return;
        }

        SkyblockUserData playerUD = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);

        if (playerUD.hasIsland()) {
            Message.ISLAND_JOIN_ALREADY_IN_ISLAND.send(player);
            return;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(commandContext.getArgs()[1]);

        if (!target.hasPlayedBefore()) {
            Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", commandContext.getArgs()[1]));
            return;
        }

        User user = User.get(target.getUniqueId());

        SkyblockUserData skyblockUserData = user.getUserData(SkyblockUserData.class);

        if (!skyblockUserData.hasIsland()) {
            Message.ISLAND_NOT_FOUND.send(player);
            return;
        }

        Island island = SkyblockCore.getInstance().getIslandManager().get(skyblockUserData.getIsland());

        if (!island.isInvited(player) && !island.isOpen()) {
            Message.ISLAND_JOIN_NOT_INVITED.send(player);
            return;
        }

        island.addMember(player);

        island.removeInvite(player);

        playerUD.setIsland(island.getId());

        Message.ISLAND_JOIN_JOINED.send(player);
    }
}
