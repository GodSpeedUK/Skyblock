package tech.moondev.skyblockcore.command;

import com.minecoremc.skyblockcore.command.subcommand.*;
import me.dan.pluginapi.command.AbstractCommand;
import me.dan.pluginapi.command.CommandContext;
import tech.moondev.skyblockcore.command.subcommand.CreateCommand;
import tech.moondev.skyblockcore.command.subcommand.InfoCommand;
import tech.moondev.skyblockcore.command.subcommand.InviteCommand;
import tech.moondev.skyblockcore.command.subcommand.JoinCommand;

import java.util.Collections;

public class IslandCommand extends AbstractCommand {

    public IslandCommand() {
        super("island");
        setAliases(Collections.singletonList("is"));
        setUsage("/island");
        addSubCommands(new CreateCommand(), new InviteCommand(), new InfoCommand(), new JoinCommand());
    }

    @Override
    public void perform(CommandContext commandContext) {

    }
}
