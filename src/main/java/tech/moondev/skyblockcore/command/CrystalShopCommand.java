package tech.moondev.skyblockcore.command;

import com.minecoremc.skyblockcore.configuration.*;
import me.dan.pluginapi.*;
import me.dan.pluginapi.command.*;
import me.dan.pluginapi.user.*;
import org.bukkit.entity.*;
import tech.moondev.skyblockcore.configuration.Config;

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
