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

public class InviteCommand extends AbstractSubCommand {

    public InviteCommand() {
        super("/island invite (player)", Collections.singletonList("invite"));
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
        Player p = Bukkit.getPlayer(args[1]);

        if (p == null) {
            Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", args[1]));
            return;
        }

        User user = User.get(player.getUniqueId());

        SkyblockUserData ud = user.getUserData(SkyblockUserData.class);

        int islandId = ud.getIsland();

        if (islandId < 1) {
            Message.ISLAND_NO_ISLAND.send(player);
            return;
        }

        Island island = SkyblockCore.getInstance().getIslandManager().get(islandId);

        island.invite(player, p);
    }
}
