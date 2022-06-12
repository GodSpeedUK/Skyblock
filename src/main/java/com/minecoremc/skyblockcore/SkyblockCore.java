package com.minecoremc.skyblockcore;

import com.minecoremc.skyblockcore.block.ValuableBlock;
import com.minecoremc.skyblockcore.block.ValuableBlockList;
import com.minecoremc.skyblockcore.command.IslandCommand;
import com.minecoremc.skyblockcore.configuration.Config;
import com.minecoremc.skyblockcore.configuration.Message;
import com.minecoremc.skyblockcore.island.IslandManager;
import lombok.Getter;
import me.dan.pluginapi.configuration.Configuration;
import me.dan.pluginapi.configuration.Serialization;
import me.dan.pluginapi.file.YamlFile;
import me.dan.pluginapi.plugin.CustomPlugin;

@Getter
public final class SkyblockCore extends CustomPlugin {

    @Getter
    private static SkyblockCore instance;

    private IslandManager islandManager;

    @Override
    public void enable() {
        instance = this;
        Configuration.loadConfig(new YamlFile("config.yml", this.getDataFolder().getAbsolutePath(), null, this), Config.values());
        Configuration.loadConfig(new YamlFile("messages.yml", this.getDataFolder().getAbsolutePath(), null, this), Message.values());
        Serialization.register(ValuableBlock.class);
        Serialization.register(ValuableBlockList.class);
        this.islandManager = new IslandManager();
        registerCommands(new IslandCommand());
    }

    @Override
    public void disable() {
        islandManager.runSaveTask();
        instance = null;
    }
}
