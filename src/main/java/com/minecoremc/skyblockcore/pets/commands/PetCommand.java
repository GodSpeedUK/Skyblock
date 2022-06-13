package com.minecoremc.skyblockcore.pets.commands;

import me.dan.pluginapi.command.*;

import java.util.*;

public class PetCommand extends AbstractCommand {

    public PetCommand() {
        super("pets");
        setAliases(Collections.singletonList("pet"));
        setUsage("/pets");
        setPermission("skyblock.pets.administrator");
        addSubCommands();
    }

    @Override
    public void perform(CommandContext commandContext) {

    }
}
