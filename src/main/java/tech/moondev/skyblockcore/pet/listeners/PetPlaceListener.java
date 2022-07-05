package tech.moondev.skyblockcore.pet.listeners;

import com.minecoremc.skyblockcore.utils.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import tech.moondev.skyblockcore.util.ChatUtil;

public class PetPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        if (event.getBlock().getType() == Material.SKULL || event.getBlock().getType() == Material.SKULL_ITEM) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatUtil.chat("&c(!) You cannot place your Pets!"));
        }
    }
}
