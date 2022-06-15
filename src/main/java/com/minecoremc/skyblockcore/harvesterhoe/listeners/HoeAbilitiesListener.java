package com.minecoremc.skyblockcore.harvesterhoe.listeners;

import com.minecoremc.skyblockcore.*;
import com.minecoremc.skyblockcore.harvesterhoe.*;
import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class HoeAbilitiesListener implements Listener{


    @EventHandler
    public void clickAbilitiesMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory open = event.getClickedInventory();
        HarvesterHoeManager hoeManager = SkyblockCore.getInstance().getHoeManager();

        SkyblockUserData sud = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);
        UserEnchantData ued = User.get(player.getUniqueId()).getUserData(UserEnchantData.class);

        if(!(open.getHolder() == hoeManager.abilitiesGUI(player).getHolder())) return;
        event.setCancelled(true);

        if (event.getSlot() == 18) {
            player.openInventory(hoeManager.mainGUI(player));
        }

        if (event.getSlot() == 13 && event.getCurrentItem().getType() == Material.SEEDS) {
            ItemStack item = player.getInventory().getItemInHand();
            if (item == null) return;
            if (!item.hasItemMeta()) return;
            if (!item.getItemMeta().hasLore()) return;
            if (!item.getItemMeta().getDisplayName().equals(hoeManager.makeHoe(player).getItemMeta().getDisplayName())) return;
            if (item.getItemMeta().getLore().contains(ChatUtil.chat("&3 ● &a&l" + "Merchant: &f1&7/&f3"))) return;
            if (sud.getCrystals() != 4000 + 4000 * ued.getMerchantLVL() / 40) return;

            sud.setCrystals(sud.getCrystals() - 4000 + 4000 * ued.getMerchantLVL() / 40);

            ItemMeta meta = item.getItemMeta();
            List<String> updatedLore = item.getItemMeta().getLore();

            updatedLore.add(ChatUtil.chat("&3 ● &a&l" + "Merchant: &f1&7/&f3"));

            meta.setLore(updatedLore);
            item.setItemMeta(meta);
            player.updateInventory();
            return;
        }
    }
}
