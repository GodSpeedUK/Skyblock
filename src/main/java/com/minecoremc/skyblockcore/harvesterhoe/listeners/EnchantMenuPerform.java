package com.minecoremc.skyblockcore.harvesterhoe.listeners;

import me.dan.pluginapi.menu.*;
import me.dan.pluginapi.user.*;
import org.bukkit.inventory.*;

public class EnchantMenuPerform extends MenuPerform {

    @Override
    public boolean perform(MenuItem menuItem, User user) {
        if (menuItem.getKey() == null) {
            return true;
        }

        if (!menuItem.getKey().startsWith("hoeEnchant-")) {
            return true;
        }




        return true;
    }

    @Override
    public void onClose(User user, Inventory inventory) {

    }
}
