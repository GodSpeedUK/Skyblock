package com.minecoremc.skyblockcore.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.configuration.Configuration;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public enum Message implements Configuration, me.dan.pluginapi.message.Message {

    PREFIX("prefix", "&3&lMINE&b&lCORE &8» &3"),
    PLAYER_NOT_FOUND("prefix", "{prefix}Could not find {player}!"),
    ISLAND_NO_ISLAND("island.no-island", "{prefix}You must have an island to do this!"),
    ISLAND_CREATE_ALREADY_HAVE_ISLAND("island.create.already-have-island", "{prefix}You already have an island!"),
    ISLAND_CREATE_CREATED("island.create.created", "{prefix}You have created an Island! Use: /island tp"),
    ALREADY_INVITED_TO_ISLAND("island.invite.already-been-invited", "{prefix}This player has already been invited!"),
    ISLAND_MEMBER_INVITE("island.invite.sent", "{prefix}{target}has been invited to your island!"),

    ISLAND_INVITE_RECEIVED("island.invite.received", "{prefix}{player} has invited you to there island!"),
    ISLAND_NOT_FOUND("island.not-found", "{prefix}That island does not exist!"),
    ISLAND_JOIN_ALREADY_IN_ISLAND("island.join.already-in-island", "{prefix}You are already a member of an island!"),
    ISLAND_JOIN_NOT_INVITED("island.join.not-invited", "{prefix}You need an invite to join this island!"),
    ISLAND_JOIN_JOINED("island.join.joined", "{prefix}You have joined an Island!"),
    ISLAND_INFO_ONLINE("island.info.online", "&a"),
    ISLAND_INFO_OFFLINE("island.info.offline", "&c"),
    ISLAND_INFO_SEPARATOR("island.info.separator", "&7, "),
    ISLAND_INFO("island.info.list", Arrays.asList(
            "&8&l==========================",
            " {prefix}Island Info:",
            " ",
            "&3ID: &b{id}",
            "&3Owner: &b{owner}",
            "&3Level: &b{level}",
            "&3Members: ",
            "{members}",
            " ",
            "&8&l=========================="
    )),

    CURRENCY_TO_USER("currency.deposited", "&a(!) {amount} {type} &ahas been added to {player} balance"),

    PET_NAME("pets.name", "{petName} PET"),

    PET_ENABLED("pets.enabled", "&a(!) You have activated your pet!"),

    PET_DISABLED("pets.disabled", "&c(!) You have disabled your pet!"),

    PET_LEVELED_UP("pets.leveled-up", "&a(!) Your &l{petName}&a has leveled up to level: &f{petLevel}"),

    PET_GAINED_EXP("pets.gained-exp", "&a(!) Your &l{petName}&a has gained {amount} exp");

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
