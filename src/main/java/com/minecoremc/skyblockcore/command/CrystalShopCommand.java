package com.minecoremc.skyblockcore.command;

import com.minecoremc.skyblockcore.configuration.*;
import me.dan.pluginapi.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.item.Item;
import me.dan.pluginapi.menu.*;
import me.dan.pluginapi.message.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import java.util.*;

public class CrystalShopCommand extends AbstractCommand {

    public CrystalShopCommand() {
        super("crystalshop");
        setUsage("/island");
        setRequiresPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();

        PluginAPI.getInstance().getMenuManager().setMenu(User.get(player.getUniqueId()), Config.CRYSTAL_SHOP_GUI.getMenu());
    }
}
