package kifo.de.waehrungen.commands;

import kifo.de.waehrungen.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    private int amount = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 2) {
            player.sendMessage(ChatColor.GRAY + "Nutzung" + ChatColor.DARK_GRAY + ":" + ChatColor.AQUA + " /pay Spieler <Betrag>");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if(targetPlayer == null) {
            player.sendMessage(ChatColor.RED + "Spieler nicht gefunden.");
            return true;
        }

        try {
            amount = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Dein Parameter 2 muss eine Zahl sein.");
            return true;
        }

        if(amount >= 1 && Main.getInstance().getAccountManager().getAccount(player.getUniqueId()).getBalance() >= amount) {
            Main.getInstance().getAccountManager().getAccount(player.getUniqueId()).changeBalance(-amount);
            player.sendMessage(ChatColor.GRAY + "Du hast " + targetPlayer.getName() + " " + amount + " Münzen gegeben.");
            Main.getInstance().getAccountManager().getAccount(targetPlayer.getUniqueId()).changeBalance(amount);
            targetPlayer.sendMessage(ChatColor.GREEN + "Du hast " + amount + " Münzen von " + player.getName() + " bekommen.");
        } else {
            player.sendMessage(ChatColor.RED + "Du hast nicht genug Münzen.");
        }

        return false;
    }
}
