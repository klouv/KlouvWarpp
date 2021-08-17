package klouvWarp.command;

import klouvWarp.KlouvWarp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class PlayerSetWarpCommand implements CommandExecutor {


    Map<UUID, Map<String, Location>> playerMap;
    Map<String, Location> valueMap;

    public PlayerSetWarpCommand(KlouvWarp plugin) {
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
            sender.sendMessage("you need a args. playersetwarp [warpName]");
            return true;
        }

        Player player = (Player) sender;
        Location loc = player.getLocation();
        String name = args[0];

        Map s = (Map) valueMap.put(name, loc);
        playerMap.put(player.getUniqueId(), s);
        //buradan emin değilim intelij ekledi
        player.sendMessage("warp name: " + name + " created");

        return true;
    }
}
