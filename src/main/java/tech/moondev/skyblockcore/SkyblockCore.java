package tech.moondev.skyblockcore;

import com.minecoremc.skyblockcore.command.*;
import com.minecoremc.skyblockcore.command.currency.*;
import tech.moondev.skyblockcore.command.IslandCommand;
import tech.moondev.skyblockcore.configuration.Config;
import tech.moondev.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.harvesterhoe.*;
import com.minecoremc.skyblockcore.harvesterhoe.command.*;
import com.minecoremc.skyblockcore.harvesterhoe.listeners.*;
import tech.moondev.skyblockcore.island.IslandManager;
import com.minecoremc.skyblockcore.menus.*;
import com.minecoremc.skyblockcore.pets.*;
import com.minecoremc.skyblockcore.pets.commands.*;
import com.minecoremc.skyblockcore.pets.listeners.*;
import com.minecoremc.skyblockcore.pets.manager.*;
import com.minecoremc.skyblockcore.scoreboard.*;
import lombok.Getter;
import me.dan.pluginapi.*;
import me.dan.pluginapi.configuration.Configuration;
import me.dan.pluginapi.file.YamlFile;
import me.dan.pluginapi.plugin.CustomPlugin;
import net.milkbowl.vault.economy.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import tech.moondev.skyblockcore.command.CrystalShopCommand;
import tech.moondev.skyblockcore.command.currency.CurrencyCommand;
import tech.moondev.skyblockcore.menu.CrystalShopMenuPerform;
import tech.moondev.skyblockcore.pet.commands.PetCommand;
import tech.moondev.skyblockcore.pet.listeners.MoneyPetListener;
import tech.moondev.skyblockcore.pet.listeners.PetPlaceListener;
import tech.moondev.skyblockcore.pet.manager.PetManager;
import tech.moondev.skyblockcore.scoreboard.Scoreboard;
import tech.moondev.skyblockcore.scoreboard.ScoreboardManager;

@Getter
public final class SkyblockCore extends CustomPlugin {

    @Getter
    private static SkyblockCore instance;

    private IslandManager islandManager;
    private Economy econ = null;
    private ScoreboardManager scoreboardManager;


    private PetManager petManager;

    @Override
    public void enable() {
        instance = this;
        if (!setupEconomy()) {
            Bukkit.getConsoleSender().sendMessage("[%s] - Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        //Serialization.register(ValuableBlockList.class);
        //Serialization.register(ValuableBlock.class);
        Configuration.loadConfig(new YamlFile("config.yml", this.getDataFolder().getAbsolutePath(), null, this), Config.values());
        Configuration.loadConfig(new YamlFile("messages.yml", this.getDataFolder().getAbsolutePath(), null, this), Message.values());
        registerCommands(new IslandCommand(), new PetCommand(), new CurrencyCommand(), new CrystalShopCommand());
        this.islandManager = new IslandManager();
        this.scoreboardManager = new ScoreboardManager(new Scoreboard());
        registerEvents(scoreboardManager, new PetPlaceListener(), new MoneyPetListener());

        this.petManager = new PetManager();
        //

        PluginAPI.getInstance().getMenuManager().registerPerformMethod(Config.CRYSTAL_SHOP_GUI.getMenu(), new CrystalShopMenuPerform());
    }

    @Override
    public void disable() {
        islandManager.runSaveTask();
        instance = null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
