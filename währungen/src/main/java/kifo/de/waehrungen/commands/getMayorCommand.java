package kifo.de.waehrungen.commands;

import kifo.de.waehrungen.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getMayorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            if(Main.getInstance().getMayor().getMayor() != null) {
                player.sendMessage(ChatColor.WHITE + "" + Bukkit.getOfflinePlayer(Main.getInstance().getMayor().getMayor()).getName() + " ist der Bürgermeister.");
            } else {
                player.sendMessage(ChatColor.WHITE + "Es wurde noch kein Bürgermeister gewählt.");
            }
        }
        return false;
    }
}
