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

public class GiveCommand extends AbstractSubCommand {

    public GiveCommand() {
        super("/harvesterHoe give (player)", Collections.singletonList("hhoe"));
        setPermission("skyblock.harvesterhoe.admin");
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        String[] args = commandContext.getArgs();


        if (args.length == 1) {

            Player p = Bukkit.getPlayer(args[1]);

            if (p == null) {
                Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", args[1]));
                return;
            }

            player.getInventory().addItem(SkyblockCore.getInstance().getHoeManager().makeHoe(player));
        }
    }
}
