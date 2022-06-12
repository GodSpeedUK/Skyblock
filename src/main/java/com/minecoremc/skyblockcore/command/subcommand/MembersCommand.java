package com.minecoremc.skyblockcore.command.subcommand;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

public class MembersCommand extends AbstractSubCommand {

    public MembersCommand() {
        super("/island members (Player)", Collections.singletonList("members"));
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        String[] args = commandContext.getArgs();
        SkyblockUserData userData = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);

        if (args.length == 2) {
            Player p = Bukkit.getPlayer(args[1]);

            if (p == null) return;

            if (!args[0].equalsIgnoreCase("members")) return;

            SkyblockCore.getInstance().getIslandManager().islandMembers(p, player);
        }
    }
}
