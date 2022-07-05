package tech.moondev.skyblockcore.pet.commands.subcommnds;

import me.dan.pluginapi.command.*;

import java.util.*;

public class GiveCommand extends AbstractSubCommand {

    public GiveCommand() {
        super("/pets give (player) (type)", Collections.singletonList("give"));
        setPlayer(true);
    }

    @Override
    public void perform(CommandContext commandContext) {
   /*     Player player = (Player) commandContext.getCommandSender();
        java.lang.String[] args = commandContext.getArgs();

        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);

        Pet moneyPet = new Pet(ChatColor.GREEN, "iCodeThings", PetType.MONEY, tech.moondev.skyblockcore.configuration.Message.PET_NAME.getString(),
                ProgressBar.getProgressBar(uld.getXps().get(PetType.MONEY), uld.getMaxBar(), uld.getTotalBar(), '|', ChatColor.GREEN, ChatColor.RED),
                "more money when selling items");

        if (!(args.length > 2)) return;

        Player p = Bukkit.getPlayer(args[1]);

        if (p == null) {
            Message.PLAYER_NOT_FOUND.send(player, new Placeholder("{player}", args[1]));
            return;
        }

        if(args[2].equalsIgnoreCase("MONEY")) {
            player.getInventory().addItem(moneyPet.createPet(player));
        }
*/
    }

}
