package tech.moondev.skyblockcore.menu;

import com.minecoremc.skyblockcore.user.*;
import com.minecoremc.skyblockcore.utils.*;
import me.dan.pluginapi.menu.*;
import me.dan.pluginapi.user.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import tech.moondev.skyblockcore.user.SkyblockUserData;
import tech.moondev.skyblockcore.util.ChatUtil;

import java.util.*;

public class CrystalShopMenuPerform extends MenuPerform {


    @Override
    public boolean perform(MenuItem menuItem, User user) {
        if (menuItem.getKey() == null) {
            return true;
        }
        if (!menuItem.getKey().toLowerCase().startsWith("crystalshop")) {
            return true;
        }

        Player player = user.getPlayer();
        SkyblockUserData sud = User.get(player.getUniqueId()).getUserData(SkyblockUserData.class);

        if (menuItem.getKey().equalsIgnoreCase("crystalshop-2igs")) {
            if (!(sud.getCrystals() >= 1250)) {
                player.sendMessage(ChatUtil.chat("&cNot enough money"));
                return true;
            }

            sud.setCrystals(sud.getCrystals() - 1250);

            ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, 2);
            BlockStateMeta bsm = (BlockStateMeta) spawner.getItemMeta();
            bsm.setDisplayName(ChatUtil.chat("&e&lIron Golem Spawner"));
            CreatureSpawner cs = (CreatureSpawner) bsm.getBlockState();

            cs.setSpawnedType(EntityType.IRON_GOLEM);
            bsm.setBlockState(cs);
            spawner.setItemMeta(bsm);

            player.getInventory().addItem(spawner);
            player.sendMessage(ChatUtil.chat("&a(!) You have just purchased &e&l2x &eIron Golem Spawners"));
        }

        if (menuItem.getKey().equalsIgnoreCase("crystalshop-flight")) {
            if (!(sud.getCrystals() >= 460)) {
                player.sendMessage(ChatUtil.chat("&cNot enough money"));
                return true;
            }

            sud.setCrystals(sud.getCrystals() - 460);


            addPermission(player.getUniqueId(), "essentials.fly");

            player.sendMessage(ChatUtil.chat("&a(!) You have just purchased &alTHE POWER OF FLIGHT"));
        }

        if (menuItem.getKey().equalsIgnoreCase("crystalshop-rank")) {
            // luck perm code
        }


        return true;
    }

    @Override
    public void onClose(User user, Inventory inventory) {

    }

    public void addPermission(UUID user, String permission) {
        // Add the permission
    }
}
