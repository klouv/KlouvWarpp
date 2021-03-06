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
            sender.sendMessage("you need a args. warp [warpName]");
            return  true;
        }

        Player player = (Player) sender;
        String name = args[0];
        Location loc = player.getLocation();

        Location location = map.get(name);
        player.sendMessage("kemerleri bağla gidiyoruzzzzz");
        //todo bekleme ekle 2 saniye
        player.teleport(location);

        return true;
    }
}
