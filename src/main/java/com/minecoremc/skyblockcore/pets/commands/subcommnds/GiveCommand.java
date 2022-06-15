package com.minecoremc.skyblockcore.pets.commands.subcommnds;

import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.pets.*;
import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.message.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import java.util.*;

public class GiveCommand extends AbstractSubCommand {

    public GiveCommand() {
        super("/pets give (player) (type)", Collections.singletonList("give"));
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        String[] args = commandContext.getArgs();

        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);

        Pet moneyPet = new Pet(ChatColor.GREEN, "Bindfps", PetType.MONEY, Message.PET_NAME.getString(),
                ProgressBar.getProgressBar(uld.getXps().get(PetType.MONEY), uld.getMaxBar(), uld.getTotalBar(), '|', ChatColor.GREEN, ChatColor.RED),
                "more money when selling items");

        System.out.println(2);
        if (!(args.length > 2)) return;

        Player p = Bukkit.getPlayer(args[1]);

        System.out.println(3);
        if (p == null) {
            Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", args[1]));
            return;
        }

        if(args[2].equalsIgnoreCase("MONEY")) {
            System.out.println(5);
            player.getInventory().addItem(moneyPet.createPet(player));
        }

    }

}
