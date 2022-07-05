package tech.moondev.skyblockcore.command.currency;

import com.minecoremc.skyblockcore.command.currency.subcommands.*;
import me.dan.pluginapi.command.*;
import tech.moondev.skyblockcore.command.currency.subcommands.CrystalsCommand;
import tech.moondev.skyblockcore.command.currency.subcommands.SoulsCommand;

import java.util.*;

public class CurrencyCommand extends AbstractCommand {

    public CurrencyCommand() {
        super("currency");
        setAliases(Collections.singletonList(""));
        setUsage("/currency (type) (player) (amount)");
        setPermission("skyblock.currency.admin");
        addSubCommands(new CrystalsCommand(), new SoulsCommand());
    }

    @Override
    public void perform(CommandContext commandContext) {

    }
}
