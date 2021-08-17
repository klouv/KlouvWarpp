package klouvWarp;

import klouvWarp.command.*;
import klouvWarp.file.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KlouvWarp extends JavaPlugin implements Listener {

    public Map<String, Location> map = new HashMap<>(); // warp map
    public Map<UUID, Map<String, Location>> playerMap = new HashMap<>(); //player warp map
    public Map<String, Location> valueMap = new HashMap<>(); // player warp map'in value map'i

    private ConfigFile config; //warp
    private ConfigFile playerConfig; //playerwarp

    @Override
    public void onEnable() {

        config = new ConfigFile(this, "config");
        playerConfig = new ConfigFile(this, "playerConfig");

        //-------------------------------PlayerWarpConfig-----------------------------
        for (String uuid : playerConfig.getKeys(false) ) {
            java.util.UUID uniqueID = UUID.fromString(uuid);
            ConfigurationSection section = playerConfig.getConfigurationSection(uuid);
            ConfigurationSection name = section.getConfigurationSection("name");

            String warpName = name.getName();
            World world = Bukkit.getWorld(name.getString("world"));
            double x = name.getInt("x");
            double y = name.getInt("y");
            double z = name.getInt("z");

            Location loc = new Location(world, x, y, z);

            playerMap.put(uniqueID, (Map<String, Location>) valueMap.put(warpName, loc));
        }
        //-----------------------------------------------------------------------------
        //-------------------------------WarpConfig------------------------------------
        for (String Key : config.getKeys(false)) {
            map.put(Key, (Location) config.get(Key));
        }
        //-----------------------------------------------------------------------------
        getCommand("playerwarp").setExecutor(new PlayerWarpCommand(this));
        getCommand("setplayerwarp").setExecutor(new PlayerSetWarpCommand(this));
        getCommand("delplayerwarp").setExecutor(new PlayerDelWarpCommand(this));
        //----------------------PLAYER WARP--------------------------------
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("delwarp").setExecutor(new DelWarpCommand(this));

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    //---------------------------------------------------------------------------
        map.forEach((k, v) -> {
            config.set(k, v);
        });
        config.save();
    //---------------------------------------------------------------------------

        playerMap.forEach((k, v) -> {
            ConfigurationSection section = playerConfig.createSection(k.toString());
            v.forEach((f, g) -> {
                ConfigurationSection nameSeciton = section.createSection(f);

                String world = g.getWorld().toString();
                int x = (int) g.getX();
                int y = (int) g.getY();
                int z = (int) g.getZ();

                nameSeciton.set("world", world);
                nameSeciton.set("x", x);
                nameSeciton.set("y", y);
                nameSeciton.set("z", z);
            });
        });
        playerConfig.save();
    //---------------------------------------------------------------------------

    }
}
