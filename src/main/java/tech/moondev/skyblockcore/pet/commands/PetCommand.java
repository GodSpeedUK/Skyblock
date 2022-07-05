package tech.moondev.skyblockcore.pet.commands;

import me.dan.pluginapi.command.*;
import tech.moondev.skyblockcore.pet.commands.subcommnds.GiveCommand;

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
