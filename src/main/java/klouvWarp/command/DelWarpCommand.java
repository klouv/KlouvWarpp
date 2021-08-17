package klouvWarp.command;

import klouvWarp.KlouvWarp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class DelWarpCommand implements CommandExecutor {

    private Map<String, Location> map;

    public DelWarpCommand(KlouvWarp plugin) {
        this.map = plugin.map;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage("you not use the command");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("you need a args. delwarp [warpName]");
            return  true;
        }

        if (!(sender.hasPermission("KlouvWarpAdmin"))) {
            sender.sendMessage("this command for admins");
            return true;
        }

        Player player = (Player) sender;
        String name = args[0];
        Location loc = player.getLocation();

        map.remove(name);
        player.sendMessage("warp name: " + name + " deleted" );
        player.sendMessage("warp silindi");

        return true;
    }
}
