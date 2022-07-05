package tech.moondev.skyblockcore.user;

import lombok.*;
import me.dan.pluginapi.user.data.*;

import java.util.*;

@Getter @Setter
public class UserEnchantData extends UserData {

    private int merchantLVL = 0;

    public UserEnchantData(UUID uuid) {
        super(uuid);
    }
}
