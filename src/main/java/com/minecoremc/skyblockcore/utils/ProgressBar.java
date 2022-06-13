package com.minecoremc.skyblockcore.utils;

import com.google.common.base.*;
import org.bukkit.*;

public class ProgressBar {

    public static String getProgressBar(int currentBar, int maxBar, int totalBar, char symbol, ChatColor finishedColor, ChatColor unfinishedColor) {
        float percent = (float) currentBar / maxBar;
        int progress = (int)  (totalBar * percent);

        return Strings.repeat("" + finishedColor + symbol, progress) + Strings.repeat("" + unfinishedColor + symbol, totalBar - progress);
    }
}