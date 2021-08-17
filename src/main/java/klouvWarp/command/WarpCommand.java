package klouvWarp.command;

import klouvWarp.KlouvWarp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class WarpCommand implements CommandExecutor {

    private Map<String, Location> map;

    public WarpCommand(KlouvWarp plugin) {
        this.map = plugin.map;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("you not use the command");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("you need a args. set(del)warp [warpName], warp [warpName]");
            return  true;
        }

        if (!(label.equals("warp"))) {
            if (!(sender.hasPermission("KlouvWarpAdmin"))) {
                sender.sendMessage("this command for admins");
                return true;
            }
        }

        Player player = (Player) sender;
        String name = args[0];
        Location loc = player.getLocation();

        if (label.equals("setwarp")) {
            map.put(name, loc);
            player.sendMessage("warp name: " + name + " created");
            player.sendMessage("warp kaydedildi");
        }

        else if (label.equals("delwarp")) {
            map.remove(name);
            player.sendMessage("warp name: " + name + " deleted" );
            player.sendMessage("warp silindi");
        }

        else if (label.equals("warp")) {
            Location location = map.get(name);
            player.sendMessage("kemerleri bağla gidiyoruzzzzz");
            //todo buraya 2 saniye bekletme ekle
            player.teleport(location);
        }
        return true;
    }
}
