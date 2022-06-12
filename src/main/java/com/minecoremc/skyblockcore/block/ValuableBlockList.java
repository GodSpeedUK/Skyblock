package com.minecoremc.skyblockcore.block;

import lombok.Builder;
import lombok.Getter;
import me.dan.pluginapi.configuration.Serializable;
import me.dan.pluginapi.file.YamlFile;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
public class ValuableBlockList extends Serializable {

    @Getter
    private final List<ValuableBlock> valuableBlockList;

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        if (valuableBlockList == null) {
            return map;
        }

        int i = 0;

        for (ValuableBlock valuableBlock : valuableBlockList) {
            Map<String, Object> serialized = valuableBlock.serialize();
            for (String key : serialized.keySet()) {
                map.put(i + "." + key, serialized.get(key));
            }

            i++;
        }

        return map;
    }

    public static ValuableBlockList deserialize(YamlFile yamlFile, String path) {

        YamlConfiguration c = yamlFile.getConfig();

        List<ValuableBlock> valuableBlockList = new ArrayList<>();

        for (String key : c.getConfigurationSection(path).getKeys(false)) {
            ValuableBlock valuableBlock = ValuableBlock.deserialize(yamlFile, path + "." + key);
            valuableBlockList.add(valuableBlock);
        }

        return ValuableBlockList.builder().valuableBlockList(valuableBlockList).build();

    }

}
