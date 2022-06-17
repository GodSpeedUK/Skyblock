package com.minecoremc.skyblockcore.harvesterhoe.listeners;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.configuration.*;
import me.dan.pluginapi.menu.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

public class HoeMenuPerform extends MenuPerform {

    @Override
    public boolean perform(MenuItem menuItem, User user) {
        System.out.println(116711);
        if (menuItem.getKey() == null) {
            return true;
        }

        System.out.println(1111);

        if (!menuItem.getKey().toLowerCase().startsWith("hoe- ")) {
            return true;
        }

        System.out.println(2222);
        Player player = user.getPlayer();

        if (menuItem.getKey().equalsIgnoreCase("hoe- abilities")) {
            System.out.println(3333);
            player.closeInventory();

            System.out.println(4444);
            SkyblockCore.getInstance().getHoeManager().abilitiesGUI(player);

            System.out.println(5555);
            return true;
        }

        return true;
    }

    @Override
    public void onClose(User user, Inventory inventory) {

    }
}
