package com.minecoremc.skyblockcore.pets;

import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import lombok.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

@Getter @Setter
public class Pet {

    private ItemStack body;

    private ChatColor color;
    private String name;
    private String bar;
    private String type;
    private List<String> description;

    public Pet(ChatColor color, ItemStack body, String type, String name, String bar,  List<String> description) {
        this.color = color;
        this.body = body;
        this.type = type;

        this.name = name;
        this.bar = bar;

        this.description = description;
    }

    public ItemStack createPet(Player player) {
        ItemStack item = new ItemStack(body);

        ItemMeta meta = item.getItemMeta();

        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', description.toString()));

        lore.add(ChatUtil.chat(color + "&lLeveling"));
        lore.add(ChatUtil.chat(color + " ● &lLevel: &f" + uld.getLevels().getOrDefault(type, 1) + "&7/&f5"));
        lore.add(ChatUtil.chat(color + " ● &lExp: &f" + uld.getXps().getOrDefault(type, 0) + "&7/&f100"));
        lore.add(ChatUtil.chat(color + " ● &8[" + bar + "&8]"));


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Message.PET_NAME.getString())
                .replace("{color}", ChatColor.getLastColors(color.name())).replace("{petName}", type));
        meta.setLore(lore);

        return item;
    }
}
