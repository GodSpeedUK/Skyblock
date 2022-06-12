package com.minecoremc.skyblockcore.command.subcommand;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

public class JoinCommand extends AbstractSubCommand {

    public JoinCommand() {
        super("/island join (Player)", Collections.singletonList("join"));
        setPlayer(true); 
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        String[] args = commandContext.getArgs();

        if (args.length == 2) {
            Player p = Bukkit.getPlayer(args[1]);

            if (p == null) return;
            if (!args[0].equalsIgnoreCase("join")) return;

            SkyblockCore.getInstance().getIslandManager().joinIsland(p, player);
        }
    }
}
