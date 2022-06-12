package com.minecoremc.skyblockcore.island;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.file.gson.GsonUtil;

import java.util.UUID;
@Getter
public class Island {


    private final int id;

    @Setter
    private UUID owner;

    public Island(int id, UUID owner) {
        this.id = id;
        this.owner = owner;
    }

}
