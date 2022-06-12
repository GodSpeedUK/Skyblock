package com.minecoremc.skyblockcore.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.configuration.Configuration;
import me.dan.pluginapi.message.Message;

import java.util.List;

@AllArgsConstructor
public enum Messages implements Configuration, Message {

    PREFIX("prefix", "&3&lMINE&b&lCORE &8» &3"),
    ISLAND_CREATE_ALREADY_HAVE_ISLAND("island.create.already-have-island", "{prefix}You already have an island!"),
    ISLAND_CREATE_CREATED("island.create.created", "{prefix}You have created an Island! Use: /island tp");

    @Getter
    private final String path;
    @Getter
    @Setter
    private Object value;

    @Override
    public String getPrefix() {
        return PREFIX.getString();
    }

    @Override
    public List<String> getStringList() {
        return Configuration.super.getStringList();
    }

    @Override
    public String getString() {
        return Configuration.super.getString();
    }
}
