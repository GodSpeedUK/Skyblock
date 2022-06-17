package com.minecoremc.skyblockcore.harvesterhoe.listeners;

import com.minecoremc.skyblockcore.*;
import me.dan.pluginapi.menu.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

public class HoeMenuPerform extends MenuPerform {

    @Override
    public boolean perform(MenuItem menuItem, User user) {

        if (menuItem.getKey() == null) {
            return true;
        }

        if (!menuItem.getKey().startsWith("hoe-")) {
            return true;
        }
        Player player = user.getPlayer();

        if (menuItem.getItem().equals(Material.DIAMOND_HOE)) {
            SkyblockCore.getInstance().getHoeManager().abilitiesGUI(player);
        }

        return true;
    }

    @Override
    public void onClose(User user, Inventory inventory) {

    }
}
