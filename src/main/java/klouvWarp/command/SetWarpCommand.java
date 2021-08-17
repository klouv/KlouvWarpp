package klouvWarp.command;

import klouvWarp.KlouvWarp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class SetWarpCommand implements CommandExecutor {

    private Map<String, Location> map;

    public SetWarpCommand(KlouvWarp plugin) {
        this.map = plugin.map;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("you not use the command");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("---------Warps--------");
            String content;
            map.keySet().forEach((k) -> {

            });

            return  true;
        }

        if (!(sender.hasPermission("KlouvWarpAdmin"))) {
            sender.sendMessage("this command for admins");
            return true;
        }

        String name = args[0];
        Location loc = player.getLocation();

        map.put(name, loc);
        player.sendMessage("warp name: " + name + " created");
        player.sendMessage("warp kaydedildi");

        return true;
    }
}
