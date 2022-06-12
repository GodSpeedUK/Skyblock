package com.minecoremc.skyblockcore.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.user.data.UserData;

import java.util.UUID;

@Getter
@Setter
public class SkyblockUserData extends UserData {

    private int island = 0;

    public SkyblockUserData(UUID uuid) {
        super(uuid);
    }
}
