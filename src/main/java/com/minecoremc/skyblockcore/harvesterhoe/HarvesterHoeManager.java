package com.minecoremc.skyblockcore.harvesterhoe;

import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class HarvesterHoeManager {

    public HarvesterHoeManager() {

    }

    public ItemStack makeHoe(Player player) {
        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
        HarvesterHoe harvesterHoe = new HarvesterHoe();


        ItemStack itemStack = new ItemStack(harvesterHoe.getMaterial());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatUtil.chat(harvesterHoe.getName()));
        List<String> lore = new ArrayList<>();

        lore.add(ChatUtil.chat(""));
        lore.add(ChatUtil.chat("&7Using a harvester hoe, gives you a major"));
        lore.add(ChatUtil.chat("&7advantage when harvesting crops!"));
        lore.add(ChatUtil.chat(""));
        lore.add(ChatUtil.chat("&3&lLeveling"));
        lore.add(ChatUtil.chat("&b ● &3Level: &f" + uld.getHoeLevel() + "&7/&f5"));
        lore.add(ChatUtil.chat("&b ● &3Exp: &f" + uld.getHoeExp() + "&7/&f75"));
        lore.add(ChatUtil.chat(""));
        lore.add(ChatUtil.chat("&b&lAbilities"));

        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public Inventory mainGUI(Player player) {
        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);

        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtil.chat("&7Harvester Hoe"));

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, (short) 3));
        }

        ItemStack stats = Builder.nameItem(Material.BOOK, "&b&l(!) &3&lStatistics", (short) 1, 1,
                Arrays.asList("&7Here you can view your hoe's", "&7statistics",
                        "", "&b&lCrops Harvested",
                        "&3 ● &bSugar Cane &f" + uld.getSugarCaneMined(),
                        "&3 ● &bCarrots &f" + uld.getCarrotsMined(),
                        "&3 ● &bPotatoes &f" + uld.getPotatoMined()));

        ItemStack abilities = Builder.nameItem(Material.FEATHER, "&b&l(!) &3&lAbilities", (short) 1, 1,
                Arrays.asList("&7(( Click to purchase abilities."));

        ItemStack settings = Builder.nameItem(Material.COMPASS, "&b&l(!) &3&lSettings &7(Soon...)", (short) 1, 1,
                Arrays.asList("&7(( Click to customise your settings ))"));


        inventory.setItem(10, stats);
        inventory.setItem(13, abilities);
        inventory.setItem(16, settings);

        return inventory;
    }

    public Inventory abilitiesGUI(Player player) {
        UserEnchantData ued = User.get(player.getUniqueId()).getUserData(UserEnchantData.class);

        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtil.chat("&7Hoe Abilities"));

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, (short) 3));
        }

        ItemStack back = Builder.nameItem(Material.BARRIER, "&c&lBACK", (short) 1, 1,
                Arrays.asList());

        ItemStack merchant = Builder.nameItem(Material.SEEDS, "&a&lMerchant", (short) 1, 1,
                Arrays.asList("&7&oThe &f&nMerchant&7&o enchant allows you to", "&7double the amount of crops you receive while farming", "",
                        "&3● &bLevel: &f" + ued.getMerchantLVL() + "&7/&f3", "&3● &bPrice: &b"+ 4000 + 4000 * ued.getMerchantLVL() / 40 +" crystals", "", "&7&o(( &f&oLeft Click &7&oto purchase enchant! ))"));


        inventory.setItem(18, back);

        inventory.setItem(13, merchant);


        return inventory;
    }
}
