package com.minecoremc.skyblockcore.command.currency.subcommands;

import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.message.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

public class CrystalsCommand extends AbstractSubCommand {

    public CrystalsCommand() {
        super("/currency crystals (player) (amount)", Collections.singletonList("crystals"));
        setPlayer(true);
    }


    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        String[] args = commandContext.getArgs();

        SkyblockUserData sud = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);

        Player p = Bukkit.getPlayer(args[1]);

        if (p == null) {
            Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", args[1]));
            return;
        }

        int amount = Integer.parseInt(args[2]);

        sud.setCrystals(sud.getCrystals() + amount);

        Message.CURRENCY_TO_USER.send(player,
                new Placeholder("{amount}", String.valueOf(amount)),
                new Placeholder("{type}", "crystals"),
                new Placeholder("{player}", args[1]));
    }
}
