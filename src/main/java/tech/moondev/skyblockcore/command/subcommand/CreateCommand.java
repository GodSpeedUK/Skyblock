package tech.moondev.skyblockcore.command.subcommand;

import tech.moondev.skyblockcore.SkyblockCore;
import tech.moondev.skyblockcore.configuration.Message;
import tech.moondev.skyblockcore.user.SkyblockUserData;
import me.dan.pluginapi.command.AbstractSubCommand;
import me.dan.pluginapi.command.CommandContext;
import me.dan.pluginapi.user.User;
import org.bukkit.entity.Player;

import java.util.Collections;

public class CreateCommand extends AbstractSubCommand {

    public CreateCommand() {
        super("/island create", Collections.singletonList("create"));
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
        Player player = (Player) commandContext.getCommandSender();
        SkyblockUserData userData = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);

        if (userData.getIsland() > 0) {
            Message.ISLAND_CREATE_ALREADY_HAVE_ISLAND.send(player);
            return;
        }

        SkyblockCore.getInstance().getIslandManager().createIsland(player);

        Message.ISLAND_CREATE_CREATED.send(player);
    }
}
