package tech.moondev.skyblockcore.user;

import com.minecoremc.skyblockcore.island.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.user.data.UserData;

import javax.swing.text.*;
import java.util.*;

@Getter
@Setter
public class SkyblockUserData extends UserData {

    private int island = 0;

    private long souls = 0;
    private long crystals = 0;

    private long darkSouls = 0;
    private long lightSouls = 0;

    private List<String> canfly = new ArrayList<>();
    
    public SkyblockUserData(UUID uuid) {
        super(uuid);
    }

    public boolean hasIsland() {
        return island > 0;
    }

}
