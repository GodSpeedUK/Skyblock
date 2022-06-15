package com.minecoremc.skyblockcore.harvesterhoe.command;

import com.minecoremc.skyblockcore.harvesterhoe.command.subcommands.*;
import me.dan.pluginapi.command.*;

import java.util.*;

public class HarvesterHoeCommand extends AbstractCommand {

    public HarvesterHoeCommand() {
        super("harvesterHoe");
        setAliases(Collections.singletonList("hhoe"));
        setUsage("/harvesterHoe give (player)");
        setPermission("skyblock.harvesterhoe.admin");
        addSubCommands(new GiveCommand());
    }

    @Override
    public void perform(CommandContext commandContext) {

    }
}
