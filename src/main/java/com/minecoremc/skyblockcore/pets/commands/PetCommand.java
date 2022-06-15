package com.minecoremc.skyblockcore.pets.commands;

import com.minecoremc.skyblockcore.pets.commands.subcommnds.*;
import me.dan.pluginapi.command.*;

import java.util.*;

public class PetCommand extends AbstractCommand {

    public PetCommand() {
        super("pets");
        setAliases(Collections.singletonList("pet"));
        setUsage("/pets give (player) (type)");
        setPermission("skyblock.pets.administrator");
        addSubCommands(new GiveCommand());
    }

    @Override
    public void perform(CommandContext commandContext) {

    }
}
