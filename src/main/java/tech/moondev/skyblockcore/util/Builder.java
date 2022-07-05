package tech.moondev.skyblockcore.util;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class Builder {
    public static List<String> colorList(List<String> list) {
        ArrayList<String> newList = new ArrayList<String>();
        for (String string : list) {
            newList.add(ChatUtil.chat(string));
        }
        return newList;
    }

    public static ItemStack nameItem(ItemStack item, String name, short durability, int amount, List<String> lores) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtil.chat(name));
        meta.setLore(Builder.colorList(lores));
        item.setItemMeta(meta);
        item.setAmount(amount);
        item.setDurability(durability);
        return item;
    }

    public static ItemStack nameItem(Material item, String name, short durability, int amount, List<String> lores) {
        return Builder.nameItem(new ItemStack(item), name, durability, amount, lores);
    }
}