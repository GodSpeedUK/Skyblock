package com.minecoremc.skyblockcore.user;

import com.minecoremc.skyblockcore.pets.*;
import lombok.*;
import me.dan.pluginapi.user.data.*;

import java.util.*;

@Getter
public class UserLevelsData extends UserData {

    // This class deals with the majority of features that have levels related to them


    private HashMap<PetType, Integer> levels;
    private HashMap<PetType, Integer> xps;

    private int maxBar = 100;
    private int totalBar = 40;

    public UserLevelsData(UUID uuid) {
        super(uuid);

        this.levels = new HashMap<>();
        this.xps = new HashMap<>();
        for (PetType type : PetType.values()) {
            levels.put(type, 1);
            xps.put(type, 0);
        }
    }
}
