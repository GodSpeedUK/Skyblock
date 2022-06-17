package com.minecoremc.skyblockcore.configuration;

import com.minecoremc.skyblockcore.block.ValuableBlock;
import com.minecoremc.skyblockcore.block.ValuableBlockList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.configuration.Configuration;
import me.dan.pluginapi.item.*;
import me.dan.pluginapi.menu.*;
import org.bukkit.Material;
import org.bukkit.inventory.*;

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
    )),

    HARVESTER_HOE_MAIN_GUI("harvester-hoe.guis.main", new Menu()
            .setName("&7Hoe Menu")
            .setSize(27)
            .addItems(
                    new MenuItem()
                            .setFill(true)
                            .setItem(Item.builder().name("light_blue_stained_glass_pane").name("")
                                    .itemFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES)).build()),

                    new MenuItem()
                            .setFill(false)
                            .setKey("hoe-stats")
                            .setSlots(11)
                            .setItem(Item.builder().material("BOOK").amount(1).name("&b&lStatistics").lore(
                                    Arrays.asList("&7Here you can view your hoe's", "&7statistics",
                                            "", "&e&lCrops Harvested",
                                            "&e ● &bSugar Cane &f{sugarCane}",
                                            "&e ● &bCarrots &f{carrots}",
                                            "&e ● &bPotatoes &f{potatoes")).itemFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES)).build()),

                    new MenuItem()
                            .setFill(false)
                            .setKey("hoe-abilities")
                            .setSlots(14)
                            .setItem(Item.builder().material("DIAMOND_HOE").amount(1).name("&b&lAbilities").lore(Arrays.asList(
                                    "&7(( Click to purchase abilities. ))")).itemFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES)).build()),

                    new MenuItem()
                            .setFill(false)
                            .setKey("hoe-settings")
                            .setSlots(17)
                            .setItem(Item.builder().material("COMPASS").amount(1).name("&b&lSettings &7(Soon...)").lore(Arrays.asList(
                                    "&7(( Click to change settings. ))")).itemFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES)).build())
            )
    ),

    HARVESTER_HOE_ENCHANTS_GUI("harvester-hoe.guis.enchants", new Menu()
            .setName("&7Abilities Menu")
            .setSize(36)
            .addItems(
                    new MenuItem()
                            .setFill(true)
                            .setItem(Item.builder().name("light_blue_stained_glass_pane").name("")
                                    .itemFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES)).build()),

                    new MenuItem()
                            .setFill(false)
                            .setKey("hoeEnchant-merchant")
                            .setSlots(11)
                            .setItem(Item.builder().name("&a&lMerchant").amount(1).material("SEEDS").lore(Arrays.asList(
                                    "&7(( Chance to double crops while harvesting )) ", "", "&e● &bPrice: &f{price}", "",
                                    "&e● &bLevel: &f{level}", "&e● &bMax Level: 5", "",
                                    "&7&o(( &f&oLeft-Click &7&oto purchase enchant! ))")).itemFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES)).build())
                    )
            );

    @Getter
    private final String path;

    @Getter
    @Setter
    private Object value;

}
