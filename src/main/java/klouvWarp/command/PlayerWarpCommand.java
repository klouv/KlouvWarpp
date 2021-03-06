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

    Map<UUID, Map<String, Location>> playerMap;
    Map<String, Location> valueMap;

    public PlayerWarpCommand(KlouvWarp plugin) {
        this.playerMap = plugin.playerMap;
        this.valueMap = plugin.valueMap;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("you not use the command");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("you need a args. playerwarp [warpName]");
            return true;
        }

        Player player = (Player) sender;

        Location loc = player.getLocation();
        String name = args[0];

        if (label.equals("playerwarp")) {
            Location location = playerMap.get(player.getUniqueId()).get(name);
            player.teleport(location);
            player.sendMessage("kemerleri bağlaa uçuyoruzzz");
        }
        return true;
    }
}
