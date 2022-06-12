package com.minecoremc.skyblockcore.command;

import com.minecoremc.skyblockcore.command.subcommand.CreateCommand;
import me.dan.pluginapi.command.AbstractCommand;
import me.dan.pluginapi.command.CommandContext;

import java.util.Collections;

public class IslandCommand extends AbstractCommand {

    public IslandCommand() {
        super("island");
        setAliases(Collections.singletonList("is"));
        setUsage("/island");
        addSubCommands(new CreateCommand());
    }

    @Override
    public void perform(CommandContext commandContext) {

    }
}
