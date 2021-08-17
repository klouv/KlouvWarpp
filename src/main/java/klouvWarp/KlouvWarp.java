package klouvWarp;

import klouvWarp.command.WarpCommand;
import klouvWarp.command.PlayerWarpCommand;
import klouvWarp.file.ConfigFile;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KlouvWarp extends JavaPlugin implements Listener {

    public Map<String, Location> map = new HashMap<>(); // warp map
    public Map<UUID, Map<String, Location>> pMap = new HashMap<>(); //player warp map
    public Map<String, Location> vMap = new HashMap<>(); // player warp map'in value map'i

    private ConfigFile config; //warp
    private ConfigFile Pconfig; //playerwarp

    @Override
    public void onEnable() {

        config = new ConfigFile(this, "config");
        Pconfig = new ConfigFile(this, "playerConfig");

        //-------------------------------PlayerWarpConfig-----------------------------
        for (String Key : Pconfig.getKeys(false) ) {
            ConfigurationSection section = Pconfig.getConfigurationSection(Key);
            //todo yapamadım aq
        }

        //-----------------------------------------------------------------------------
        //-------------------------------WarpConfig------------------------------------
        for (String Key : config.getKeys(false)) {
            map.put(Key, (Location) config.get(Key));
        }
        //-----------------------------------------------------------------------------
        getCommand("playerwarp").setExecutor(new PlayerWarpCommand(this));
        getCommand("setplayerwarp").setExecutor(new PlayerWarpCommand(this));
        getCommand("delplayerwarp").setExecutor(new PlayerWarpCommand(this));
        //----------------------PLAYER WARP--------------------------------
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("setwarp").setExecutor(new WarpCommand(this));
        getCommand("delwarp").setExecutor(new WarpCommand(this));

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

        map.forEach((k, v) -> {
            config.set(k, v);
        });
        config.save();

        pMap.forEach((k,v) -> {
            Pconfig.createSection(String.valueOf(k), v);
        });
        Pconfig.save();
    }
}
