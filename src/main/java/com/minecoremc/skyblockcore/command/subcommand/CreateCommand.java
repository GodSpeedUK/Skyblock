package com.minecoremc.skyblockcore.command.subcommand;

import com.minecoremc.skyblockcore.SkyblockCore;
import com.minecoremc.skyblockcore.configuration.Messages;
import com.minecoremc.skyblockcore.user.SkyblockUserData;
import me.dan.pluginapi.command.AbstractSubCommand;
import me.dan.pluginapi.command.CommandContext;
import me.dan.pluginapi.user.User;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class CreateCommand extends AbstractSubCommand {

    public CreateCommand() {
        super("/island create", Collections.singletonList("create"));
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        SkyblockUserData userData = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);

        if (userData.getIsland() > 0) {
            Messages.ISLAND_CREATE_ALREADY_HAVE_ISLAND.send(player);
            return;
        }

        SkyblockCore.getInstance().getIslandManager().createIsland(player);

        Messages.ISLAND_CREATE_CREATED.send(player);
    }
}
