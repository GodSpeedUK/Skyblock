package com.minecoremc.skyblockcore.harvesterhoe;

import lombok.*;
import org.bukkit.*;

@Getter @Setter
public class HarvesterHoe {

    private final String name;
    private final Material material;

    public HarvesterHoe() {
        this.name = "&b&lHarvester &e&lHoe";

        this.material = Material.DIAMOND_HOE;
    }
}
