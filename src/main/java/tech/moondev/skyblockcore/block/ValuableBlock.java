package tech.moondev.skyblockcore.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.dan.pluginapi.configuration.Serializable;
import me.dan.pluginapi.file.YamlFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Builder
public class ValuableBlock extends Serializable {

    private final Material material;
    private final double value;

    @Override
    public Map<String, Object> serialize() {

        Map<String, Object> map = new HashMap<>();

        map.put("material", material.name());
        map.put("value", value);

        return map;
    }

    public static ValuableBlock deserialize(YamlFile yamlFile, String path) {
        YamlConfiguration c = yamlFile.getConfig();
        Material material = Material.getMaterial(c.getString(path + ".material"));
        if (material == null) {
            return null;
        }

        double value = c.getDouble(path + ".value");

        return ValuableBlock.builder()
                .material(material)
                .value(value)
                .build();
    }

}
