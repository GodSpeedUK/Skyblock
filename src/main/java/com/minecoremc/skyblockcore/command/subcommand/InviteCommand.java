package com.minecoremc.skyblockcore.command.subcommand;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.command.*;
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

        if(args.length == 2) {
            Player p = Bukkit.getPlayer(args[1]);

            if (p == null) return;
            if (!args[0].equalsIgnoreCase("invite")) return;

            SkyblockCore.getInstance().getIslandManager().invite(player, p);
        }
    }
}
