package com.minecoremc.skyblockcore.command.currency;

import com.minecoremc.skyblockcore.command.currency.subcommands.*;
import me.dan.pluginapi.command.*;

import java.util.*;

public class CurrencyCommand extends AbstractCommand {

    public CurrencyCommand() {
        super("currency");
        setAliases(Collections.singletonList(""));
        setUsage("/currency (type) (player) (amount)");
        setPermission("skyblock.currency.admin");
        addSubCommands(new CrystalsCommand());
    }

    @Override
    public void perform(CommandContext commandContext) {

    }
}
