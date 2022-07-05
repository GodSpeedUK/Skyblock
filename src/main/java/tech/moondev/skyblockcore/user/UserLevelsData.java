package tech.moondev.skyblockcore.user;

import lombok.*;
import me.dan.pluginapi.user.data.*;

import java.util.*;

@Getter
@Setter
public class UserLevelsData extends UserData {

    // This class deals with the majority of features that have levels related to them


    private HashMap<String, Integer> levels;
    private HashMap<String, Integer> xps;

    private HashMap<UUID, PetType> petActive;

    private int sugarCaneMined = 0;
    private int carrotsMined = 0;
    private int potatoMined = 0;

    private int hoeLevel = 0;
    private int hoeExp = 0;

    private int maxBar = 100;
    private int totalBar = 40;

    public UserLevelsData(UUID uuid) {
        super(uuid);

        this.petActive = new HashMap<>();
        this.levels = new HashMap<>();
        this.xps = new HashMap<>();
        for (PetType type : PetType.values()) {
            String name = type.name();
            levels.put(name, 1);
            xps.put(name, 0);
        }
    }
}
