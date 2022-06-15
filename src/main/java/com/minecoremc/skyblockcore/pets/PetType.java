package com.minecoremc.skyblockcore.pets;

import com.minecoremc.skyblockcore.pets.manager.*;
import lombok.*;
import org.bukkit.*;

import java.util.*;
import java.util.stream.*;

@Getter
public enum PetType {

    MONEY(
            new XpBlocks(Material.SUGAR_CANE_BLOCK, 3),
            new XpBlocks(Material.CARROT, 2),
            new XpBlocks(Material.POTATO, 5)
    ),MONEY1;

    private final HashMap<Material, Integer> xpValues;

    PetType(XpBlocks... values) {
        xpValues = Arrays.stream(values)
                .collect(Collectors.toMap(XpBlocks::nameable, XpBlocks::xpValue, (prev, next) -> next, HashMap::new));
    }

    public int getXpFor(Material keyed) {
        return xpValues.getOrDefault(keyed, 0);
    }
}
