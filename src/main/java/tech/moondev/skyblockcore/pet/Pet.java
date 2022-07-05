package tech.moondev.skyblockcore.pet;

import tech.moondev.skyblockcore.pet.manager.XpBlock;
import lombok.*;
import lombok.Builder;
import me.dan.pluginapi.file.YamlFile;
import me.dan.pluginapi.item.Item;
import me.dan.pluginapi.message.Placeholder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import java.util.*;

@Getter
@Setter
@Builder
public class Pet {

    private Item item;
    private String pet;

    private List<XpBlock> xpBlocks;

    private Pet() {

    }


    public ItemStack createPet(Player player) {
        return item.toItemStack(new Placeholder("{pet}", pet), new Placeholder("{player}", player.getName()));
    }

    public Map<String, Object> serialize() {
        Map<String, Object> map = item.serialize();
        map.put("pet", pet);
        int i = 0;
        for (XpBlock xpBlock : xpBlocks) {
            Map<String, Object> serialized = xpBlock.serialize();
            for (String key : serialized.keySet()) {
                map.put("xp-blocks." + i + "." + key, serialized.get(key));
            }
        }
        return map;
    }

    public static Pet deserialize(YamlFile yamlFile, String path) {
        YamlConfiguration c = yamlFile.getConfig();
        Item item = Item.deserialize(yamlFile, path);
        String pet = c.getString(path + ".pet");
        List<XpBlock> xpBlockList = new ArrayList<>();
        for (String key : c.getConfigurationSection(path + ".xp-blocks").getKeys(false)) {
            int id;
            try {
                id = Integer.parseInt(key);
            } catch (NumberFormatException e) {
                continue;
            }

            XpBlock xpBlock = XpBlock.deserialize(yamlFile, path + ".xp-blocks." + key);
            xpBlockList.set(id, xpBlock);
        }
        return Pet.builder().item(item).pet(pet).xpBlocks(xpBlockList).build();
    }

    /*


    ItemStack item = new ItemStack(SkullAPI.getSkull(body));

        SkullMeta meta = (SkullMeta) item.getItemMeta();

        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
        List<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(ChatUtil.chat(color + "&lAbility"));
        lore.add(ChatUtil.chat("&7This pet allows you to earn"));
        lore.add(ChatUtil.chat("&7" + description));
        lore.add("");
        lore.add(ChatUtil.chat(color + "&lLeveling"));
        lore.add(ChatUtil.chat(color + " ● &lLevel: &f" + uld.getLevels().getOrDefault(type, 1) + "&7/&f5"));
        lore.add(ChatUtil.chat(color + " ● &lExp: &f" + uld.getXps().getOrDefault(type, 0) + "&7/&f100"));
        lore.add(ChatUtil.chat(color + " ● &8[" + bar + "&8]"));


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', color + "&l" + Message.PET_NAME.getString()).replace("{petName}", type.name()));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

     */
}
