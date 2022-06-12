package com.minecoremc.skyblockcore.user;

import com.minecoremc.skyblockcore.island.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.user.data.UserData;

import java.util.*;

@Getter
@Setter
public class SkyblockUserData extends UserData {

    private int island = 0;

    public SkyblockUserData(UUID uuid) {
        super(uuid);
    }

    public boolean hasIsland() {
        return island > 0;
    }

}
