package com.minecoremc.skyblockcore.harvesterhoe.listeners;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.harvesterhoe.*;
import com.minecoremc.skyblockcore.user.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

public class HarvesterHoeListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        HarvesterHoeManager hoeManager = SkyblockCore.getInstance().getHoeManager();
        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);

        if (!player.getItemInHand().equals(hoeManager.makeHoe(player))) return;

        switch (block.getType()) {
            case SUGAR_CANE_BLOCK:
            case CARROT:
            case POTATO:
                uld.setCarrotsMined(uld.getCarrotsMined() + 1);
                uld.setPotatoMined(uld.getPotatoMined() + 1);
                uld.setSugarCaneMined(uld.getSugarCaneMined() + 1);
                break;
            default:
                break;
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory open = event.getClickedInventory();
        HarvesterHoeManager hoeManager = SkyblockCore.getInstance().getHoeManager();

        if (!(open.getHolder() == hoeManager.mainGUI(player).getHolder())) return;
        event.setCancelled(true);

        if (event.getSlot() == 13) {
            player.openInventory(hoeManager.abilitiesGUI(player));
            return;
        }
    }
}
