package klouvWarp.command;

import klouvWarp.KlouvWarp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class PlayerDelWarpCommand implements CommandExecutor {


    Map<UUID, Map<String, Location>> playerMap;
    Map<String, Location> valueMap;

    public PlayerDelWarpCommand(KlouvWarp plugin) {
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
            sender.sendMessage("you need a args. playerdelwarp [warpName]");
            return true;
        }

        Player player = (Player) sender;

        Location loc = player.getLocation();
        String name = args[0];

        playerMap.get(player.getUniqueId()).remove(name);
        player.sendMessage("warp name: " + name + " deleted");

        return true;
    }
}
