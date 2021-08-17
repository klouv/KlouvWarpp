package klouvWarp.command;

import klouvWarp.KlouvWarp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class PlayerWarpCommand implements CommandExecutor {

    Map<UUID, Map<String, Location>> map;
    Map<String, Location> vMap;

    public PlayerWarpCommand(KlouvWarp plugin) {
        this.map = plugin.pMap;
        this.vMap = plugin.vMap;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("you not use the command");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("you need a args. set(del)playerwarp [warpName], playerwarp [warpName]");
            return true;
        }

        Player player = (Player) sender;

        Location loc = player.getLocation();
        String name = args[0];

        if (label.equals("playerwarp")) {
            Location location = map.get(player.getUniqueId()).get(name);
            player.teleport(location);
            player.sendMessage("kemerleri bağlaa uçuyoruzzz");
        }

        if (label.equals("setplayerwarp")) {
            map.put(player.getUniqueId(), (Map<String, Location>) vMap.put(name, loc));
            //buradan emin değilim intelij ekledi
            player.sendMessage("warp name: " + name + " created");
        }

        if (label.equals("delplayerwarp")) {
            map.get(player.getUniqueId()).remove(name);
            player.sendMessage("warp name: " + name + " deleted");
        }

        return true;
    }
}
