package com.minecoremc.skyblockcore.harvesterhoe.command.subcommands;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.message.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

public class GiveHoeCommand extends AbstractSubCommand {

    public GiveHoeCommand() {
        super("/harvesterHoe give (player)", Collections.singletonList("give"));
        setPermission("skyblock.harvesterhoe.admin");
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        String[] args = commandContext.getArgs();

        OfflinePlayer p = Bukkit.getPlayer(args[1]);

        if (p == null) {
            Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", args[1]));
            return;
        }

        p.getPlayer().getInventory().addItem(SkyblockCore.getInstance().getHoeManager().makeHoe(p.getPlayer()));
    }
}
