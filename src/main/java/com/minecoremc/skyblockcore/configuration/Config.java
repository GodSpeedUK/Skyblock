package com.minecoremc.skyblockcore.configuration;

import com.minecoremc.skyblockcore.block.ValuableBlock;
import com.minecoremc.skyblockcore.block.ValuableBlockList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.configuration.Configuration;
import org.bukkit.Material;

import java.util.Arrays;

@AllArgsConstructor
public enum Config implements Configuration {

    SAVE_INTERVAL("save-interval", 300),
    /*VALUABLE_BLOCKS("valuable-blocks", ValuableBlockList.builder()
            .valuableBlockList(Arrays.asList(
                    ValuableBlock.builder()
                            .material(Material.EMERALD_BLOCK)
                            .value(5).build(),
                    ValuableBlock.builder()
                            .material(Material.DIAMOND_BLOCK)
                            .value(4).build(),
                    ValuableBlock.builder()
                            .material(Material.GOLD_BLOCK)
                            .value(3)
                            .build()
            )))*/
    ISLAND_MAX_MEMBERS("island_max_members", 5),

    SCOREBOARD_TITLE("scoreboard.title", "&3&lMINE&e&lCORE"),
    SCOREBOARD_BOARD("scoreboard.board", Arrays.asList(
            "&7&m-----------------------",
            "",
            "&b{player}",
            " &e●&7 Money: &a{money}",
            " &e●&7 Souls: &c{souls}",
            " &e●&7 Crystals: &b{crystals}",
            "",
            "&b{island}",
            " &e●&7 Leader: &b{islandLeader}",
            " &e●&7 Value: &b{islandValue}",
            "&7&m-----------------------"
    ));

    @Getter
    private final String path;

    @Getter
    @Setter
    private Object value;

}
