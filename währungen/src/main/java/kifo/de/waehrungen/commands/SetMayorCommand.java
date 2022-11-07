package kifo.de.waehrungen.commands;

import kifo.de.waehrungen.Main;
import kifo.de.waehrungen.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMayorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length  != 1) {
            player.sendMessage(ChatColor.GRAY + "Verwendung" + ChatColor.DARK_GRAY + ":" + ChatColor.AQUA + " /setMayor Spieler");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);

        if(targetPlayer == null) {
            player.sendMessage(ChatColor.RED + "Spieler nicht gefunden.");
            return true;
        }

        if(!player.isOp()) {
            return true;
        }

        player.sendMessage(ChatColor.WHITE + "Du hast " + targetPlayer.getName() + " zum Bürgermeister ernannt.");
        targetPlayer.sendMessage(ChatColor.GREEN + "Du wurdest zum Bürgermeister ernannt.");

        Main.getInstance().getMayor().setMayor(targetPlayer.getUniqueId());

        return false;
    }
}
