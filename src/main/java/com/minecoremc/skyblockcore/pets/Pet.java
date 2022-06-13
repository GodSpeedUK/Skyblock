package com.minecoremc.skyblockcore.pets;

import com.minecoremc.skyblockcore.configuration.*;
import com.minecoremc.skyblockcore.user.*;
import lombok.*;
import me.dan.pluginapi.user.*;
import net.milkbowl.vault.chat.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

@Getter @Setter
public class Pet {

    private ItemStack petBody;
    private ChatColor color;

    private String name;
    private String bar;
    private String petType;
    private List<String> description;

    public Pet(String petType, String name, ItemStack petBody, String bar, List<String> description) {
        this.petType = petType;
        this.name = name;
        this.bar = bar;

        this.petBody = petBody;
        this.description = description;
    }

    public ItemStack createPet(Player player) {
        ItemStack item = new ItemStack(petBody);
        ItemMeta meta = item.getItemMeta();

        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
        List<String> lore = new ArrayList<>();

        String petLeveling = String.valueOf(Message.PET_LEVELING.getStringList());
        String petLevel = String.valueOf(uld.getLevels().getOrDefault(petType, 1));
        String petEXP = String.valueOf(uld.getXps().getOrDefault(petType, 0));

        lore.add(ChatColor.translateAlternateColorCodes('&', description.toString()));
        lore.add(ChatColor.translateAlternateColorCodes('&', petLeveling.replace
                ("{color}", ChatColor.getLastColors(color.name())).replace
                ("{petLevel}", petLevel).replace
                ("{petEXP}", petEXP).replace
                ("{progressbar}", bar)));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Message.PET_NAME.getString())
                .replace("{color}", ChatColor.getLastColors(color.name())).replace("{petName}", petType));
        meta.setLore(lore);

        return item;
    }
}
