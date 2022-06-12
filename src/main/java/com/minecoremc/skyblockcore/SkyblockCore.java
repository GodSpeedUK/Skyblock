package com.minecoremc.skyblockcore;

import com.minecoremc.skyblockcore.block.ValuableBlock;
import com.minecoremc.skyblockcore.block.ValuableBlockList;
import com.minecoremc.skyblockcore.command.IslandCommand;
import com.minecoremc.skyblockcore.configuration.Config;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.island.IslandManager;
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
    private Economy econ = null;

    @Override
    public void enable() {
        if (!setupEconomy() ) {
            Bukkit.getConsoleSender().sendMessage("[%s] - Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        instance = this;
        Configuration.loadConfig(new YamlFile("config.yml", this.getDataFolder().getAbsolutePath(), null, this), Config.values());
        Configuration.loadConfig(new YamlFile("messages.yml", this.getDataFolder().getAbsolutePath(), null, this), Message.values());
        Serialization.register(ValuableBlock.class);
        Serialization.register(ValuableBlockList.class);
        this.islandManager = new IslandManager();
        registerCommands(new IslandCommand());

        new ScoreboardManager(this, new Scoreboard());
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
