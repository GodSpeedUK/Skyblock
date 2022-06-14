package com.minecoremc.skyblockcore.pets.manager;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.pets.*;
import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import me.dan.pluginapi.message.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class PetManager {

    public PetManager() {

    }

    public void increaseLVL(Player player, PetType type, int amount) {
        UserLevelsData target = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
        if (target.getLevels().containsKey(target)) {
            target.getLevels().put(type.getName(), target.getLevels().get(type) + amount);
        } else target.getLevels().put(type.getName(), amount);

        Message.PET_LEVELED_UP.send(player,
                new Placeholder("{petLevel}", String.valueOf(target.getLevels().getOrDefault(type, 1))),
                new Placeholder("{petName}",  Message.PET_NAME.getString()));
    }

    public void increaseEXP(Player player, PetType type, int amount) {
        UserLevelsData target = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
        if (target.getLevels().containsKey(type)) {
            target.getXps().put(type.getName(), target.getXps().get(type) + amount);
        } else target.getXps().put(type.getName(), amount);

        // notifications blocker code

        Message.PET_GAINED_EXP.send(player,
                new Placeholder("{amount}", String.valueOf(amount)),
                new Placeholder("{petName}",  Message.PET_NAME.getString()));
    }

    public void refreshPet(Player player, PetType type, ChatColor color) {
        Inventory i = player.getInventory();

        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
        for (ItemStack item : i.getContents()) {
            if (item == null) continue;
            if (!item.hasItemMeta()) continue;
            if (!item.getItemMeta().hasLore()) continue;
            if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(Message.PET_NAME.getString())) continue;

            ItemMeta itemMeta = item.getItemMeta();
            List<String> updatedLore = item.getItemMeta().getLore();



            updatedLore.set(6, ChatUtil.chat(color + "  ● &lLevel: &f" + uld.getLevels().getOrDefault(type, 1)));
            updatedLore.set(7, ChatUtil.chat(color + "  ● &lExp: &f" + uld.getXps().getOrDefault(type, 0) + "&7/&f" + "100"));
            updatedLore.set(8, ChatUtil.chat(color + "  ● &8[" +
                    ProgressBar.getProgressBar(Math.toIntExact(uld.getXps().get(type)), uld.getMaxBar(), uld.getTotalBar(), '|', ChatColor.GREEN, ChatColor.RED) + "&8]"));

            itemMeta.setLore(updatedLore);
            item.setItemMeta(itemMeta);
        }
    }
}
