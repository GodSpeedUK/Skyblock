package com.minecoremc.skyblockcore.pets.commands.subcommnds;

import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.pets.*;
import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import dev.dbassett.skullcreator.*;
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

        Pet moneyPet = new Pet(ChatColor.GREEN, getPetBody(), PetType.MONEY.getName(), PetType.MONEY.getName(),
                ProgressBar.getProgressBar(uld.getXps().get(PetType.MONEY.getName()), uld.getMaxBar(), uld.getTotalBar(), '|', ChatColor.GREEN, ChatColor.RED),
                Message.PET_MONEY_DESCRIPTION.getStringList());

        if (args.length != 2) return;

        Player p = Bukkit.getPlayer(args[1]);

        if (p == null) {
            Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", args[1]));
            return;
        }

        if(args[2].equalsIgnoreCase("MONEY")) {
            player.getInventory().addItem(moneyPet.createPet(player));
        }

    }


    private ItemStack getPetBody() {
        // Got this base64 string from minecraft-heads.com
        String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmU" +
                "vNGQyNjc2MWUwZWYyNjJjY2VkMWI4OTZhYTExNjEyNjk2ZjExZTZkNzVkMGFjOWI0YTBiNzk0NjZjNzhiZTFmYiJ9fX0";

        return SkullCreator.itemFromBase64(base64);
    }
}
