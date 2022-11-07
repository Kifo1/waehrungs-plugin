package kifo.de.waehrungen.commands;

import kifo.de.waehrungen.Main;
import kifo.de.waehrungen.bank.Account;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        Account account = Main.getInstance().getAccountManager().getAccount(player.getUniqueId());

        player.sendMessage("Dein Kontostand beträgt: " + account.getBalance() + " Münzen");

        return false;
    }
}
