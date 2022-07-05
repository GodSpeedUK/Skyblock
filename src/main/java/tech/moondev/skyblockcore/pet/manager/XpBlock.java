package tech.moondev.skyblockcore.pet.manager;

import lombok.Builder;
import me.dan.pluginapi.configuration.Serializable;
import me.dan.pluginapi.file.YamlFile;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

@Builder
public class XpBlock extends Serializable {

    private String block;
    private int xpValue;

    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("block", block);
        map.put("xp", xpValue);
        return map;
    }

    public static XpBlock deserialize(YamlFile yamlFile, String path) {
        YamlConfiguration c = yamlFile.getConfig();
        XpBlockBuilder xpBlocksBuilder = XpBlock.builder();
        xpBlocksBuilder.block(c.getString(path + ".block"));
        xpBlocksBuilder.xpValue(c.getInt(path + ".xp"));
        return xpBlocksBuilder.build();
    }

}
