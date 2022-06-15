package com.minecoremc.skyblockcore;

import com.minecoremc.skyblockcore.block.ValuableBlock;
import com.minecoremc.skyblockcore.block.ValuableBlockList;
import com.minecoremc.skyblockcore.command.IslandCommand;
import com.minecoremc.skyblockcore.configuration.Config;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.island.IslandManager;
import com.minecoremc.skyblockcore.pets.*;
import com.minecoremc.skyblockcore.pets.commands.*;
import com.minecoremc.skyblockcore.pets.listeners.*;
import com.minecoremc.skyblockcore.pets.manager.*;
import com.minecoremc.skyblockcore.scoreboard.*;
import lombok.Getter;
import me.dan.pluginapi.configuration.Configuration;
import me.dan.pluginapi.configuration.Serialization;
import me.dan.pluginapi.file.YamlFile;
import me.dan.pluginapi.plugin.CustomPlugin;
import net.milkbowl.vault.economy.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

@Getter
public final class SkyblockCore extends CustomPlugin {

    @Getter
    private static SkyblockCore instance;

    private IslandManager islandManager;
    private PetManager petManager;
    private Economy econ = null;
    private ScoreboardManager scoreboardManager;

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
        this.islandManager = new IslandManager();
        registerCommands(new IslandCommand(), new PetCommand());
        this.scoreboardManager = new ScoreboardManager(new Scoreboard());
        this.petManager = new PetManager();
        registerEvents(scoreboardManager, new PetPlaceListener(), new MoneyPetListener());
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
