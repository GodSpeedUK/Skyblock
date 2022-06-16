package com.minecoremc.skyblockcore.harvesterhoe;

import lombok.*;
import org.bukkit.*;

@Getter @Setter
public class HarvesterHoe {

    private final String name;
    private final Material material;

    public HarvesterHoe() {
        this.name = "&e&l(!) &b&lHarvester Hoe &e&l(!)";

        this.material = Material.DIAMOND_HOE;
    }
}
