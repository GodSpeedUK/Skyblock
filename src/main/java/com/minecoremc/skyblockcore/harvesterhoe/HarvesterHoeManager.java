package com.minecoremc.skyblockcore.harvesterhoe;

import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import me.dan.pluginapi.*;
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

    public void mainGUI(Player player) {
        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);

        PluginAPI.getInstance().getMenuManager().setMenu(User.get(player.getUniqueId()), Config.HARVESTER_HOE_MAIN_GUI.getMenu(),
                new Placeholder("{sugarCane}", String.valueOf(uld.getSugarCaneMined())),
                new Placeholder("{carrots}", String.valueOf(uld.getCarrotsMined())),
                new Placeholder("{potatoes}", String.valueOf(uld.getPotatoMined())));
    }

    public void abilitiesGUI(Player player) {
        UserEnchantData ued = User.get(player.getUniqueId()).getUserData(UserEnchantData.class);

        int price = 2250 * ued.getMerchantLVL();

        int sum = price + price / 20 * 2;

        int finalPrice = price + sum;

        PluginAPI.getInstance().getMenuManager().setMenu(User.get(player.getUniqueId()), Config.HARVESTER_HOE_ENCHANTS_GUI.getMenu(),
                new Placeholder("{level}", String.valueOf(ued.getMerchantLVL())),
                new Placeholder("{price}", String.valueOf(finalPrice)));
    }

    /*
    Builder.nameItem(Material.POTATO_ITEM, "&a&lMerchant", (short) 1, 1,
                Arrays.asList("&7&oThe &f&nMerchant&7&o enchant allows you to", "&7double the amount of crops you receive while farming", "",
                        "&e● &bLevel: &f" + ued.getMerchantLVL() + "&7/&f3", "&e● &bPrice: &f"+ 2250 * ued.getMerchantLVL() +"&b crystals", "", "&7&o(( &f&oLeft Click &7&oto purchase enchant! ))"));
     */
}
