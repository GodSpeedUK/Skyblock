package com.minecoremc.skyblockcore.harvesterhoe;

import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import me.dan.pluginapi.item.Item;
import me.dan.pluginapi.menu.*;
import me.dan.pluginapi.message.*;
import me.dan.pluginapi.nbt.*;
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

        lore.add(ChatUtil.chat("&7Using a harvester hoe, gives you a major"));
        lore.add(ChatUtil.chat("&7advantage when harvesting crops!"));
        lore.add(ChatUtil.chat(""));
        lore.add(ChatUtil.chat("&e&lLeveling"));
        lore.add(ChatUtil.chat("&e ● &bLevel: &f" + uld.getHoeLevel() + "&7/&f5"));
        lore.add(ChatUtil.chat("&e ● &bExp: &f" + uld.getHoeExp() + "&7/&f75"));
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
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE,  1, (short) 3));
        }

        ItemStack stats = Item.builder().material("BOOK").name("&b&lStatistics").lore(
                Arrays.asList("&7Here you can view your hoe's", "&7statistics",
                        "", "&e&lCrops Harvested",
                        "&e ● &bSugar Cane &f" + uld.getSugarCaneMined(),
                        "&e ● &bCarrots &f" + uld.getCarrotsMined(),
                        "&e ● &bPotatoes &f" + uld.getPotatoMined())).build().toItemStack();

        ItemStack abilities = Item.builder().material("DIAMOND_HOE").name("&b&lAbilities").lore(Arrays.asList(
                "&7(( Click to purchase abilities. ))")).build().toItemStack();

        ItemStack settings = Item.builder().material("COMPASS").name("&b&lSettings &7(Soon...)").lore(Arrays.asList()).build().toItemStack();

        Builder.nameItem(Material.NAME_TAG, "&e&l(!) &b&lSettings &7(Soon...)", (short) 1, 1,
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
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE,  1, (short) 3));
        }

        ItemStack back = Builder.nameItem(Material.BARRIER, "&c&lBACK", (short) 1, 1, Arrays.asList(""));

        ItemStack merchant = Builder.nameItem(Material.SEEDS, "&a&lMerchant", (short) 1, 1, Arrays.asList
                ("&7(( Chance to double crops while harvesting )) ", "", "&e● &bPrice: &f" + 2250 * ued.getMerchantLVL(),
                        "&e● &bLevel: &f" + ued.getMerchantLVL(), "&e● &bMax Level: 5", "",
                        "&7&o(( &f&oLeft-Click &7&oto purchase enchant! ))"));


        inventory.setItem(18, back);

        inventory.setItem(13, merchant);


        return inventory;
    }

    /*
    Builder.nameItem(Material.POTATO_ITEM, "&a&lMerchant", (short) 1, 1,
                Arrays.asList("&7&oThe &f&nMerchant&7&o enchant allows you to", "&7double the amount of crops you receive while farming", "",
                        "&e● &bLevel: &f" + ued.getMerchantLVL() + "&7/&f3", "&e● &bPrice: &f"+ 2250 * ued.getMerchantLVL() +"&b crystals", "", "&7&o(( &f&oLeft Click &7&oto purchase enchant! ))"));
     */
}
