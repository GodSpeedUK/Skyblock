package com.minecoremc.skyblockcore.user;

import lombok.*;
import me.dan.pluginapi.user.data.*;

import java.util.*;

@Getter
public class UserEnchantData extends UserData {

    private int merchantLVL = 0;

    public UserEnchantData(UUID uuid) {
        super(uuid);
    }
}
