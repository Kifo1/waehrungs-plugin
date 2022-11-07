package kifo.de.waehrungen.commands;

import kifo.de.waehrungen.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StateAccountCommand implements CommandExecutor {

    private int amount = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(ChatColor.WHITE + "Auf dem Staatskonto befinden sich: " + Main.getInstance().getAccountManager().getStateAccount().getBalance() + " Münzen");
        }
        else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("einzahlen") || args[0].equalsIgnoreCase("auszahlen")) {

                try {
                    amount = Integer.parseInt(args[1]);
                } catch(NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Dein Parameter 2 muss eine Zahl sein.");
                    return true;
                }

                if(!(amount >= 1)) {
                    player.sendMessage(ChatColor.RED + "Der Betrag muss >= 1 sein.");
                    return true;
                }

                if(args[0].equalsIgnoreCase("einzahlen")) {
                    if(player.getUniqueId().equals(Main.getInstance().getMayor().getMayor())) {

                        if(Main.getInstance().getAccountManager().getAccount(player.getUniqueId()).getBalance() >= amount) {
                            Main.getInstance().getAccountManager().getAccount(player.getUniqueId()).changeBalance(-amount);
                            Main.getInstance().getAccountManager().getStateAccount().changeBalance(amount);

                            for (Player p: Bukkit.getOnlinePlayers()) {
                                p.sendMessage(ChatColor.WHITE + player.getName() + " hat " + amount + " Münzen auf das Staatskonto eingezahlt.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Du hast nicht genug Münzen.");
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "Nur der Bürgermeister kann auf das Staatskonto einzahlen.");
                        return true;
                    }
                }

                if(args[0].equalsIgnoreCase("auszahlen")) {
                    if(player.getUniqueId().equals(Main.getInstance().getMayor().getMayor())) {

                        if(Main.getInstance().getAccountManager().getStateAccount().getBalance() >= amount) {
                            Main.getInstance().getAccountManager().getStateAccount().changeBalance(-amount);
                            Main.getInstance().getAccountManager().getAccount(player.getUniqueId()).changeBalance(amount);

                            for (Player p: Bukkit.getOnlinePlayers()) {
                                p.sendMessage(ChatColor.WHITE + player.getName() + " hat " + amount + " Münzen vom Staatskonto abgebucht.");
                            }

                        } else {
                            player.sendMessage(ChatColor.RED + "Auf dem Staatskonto sind nicht genug Münzen.");
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "Nur der Bürgermeister kann vom Staatskonto abbuchen.");
                        return true;
                    }
                }

            } else {
                player.sendMessage(ChatColor.GRAY + "Verwendung" + ChatColor.DARK_GRAY + ":" + ChatColor.AQUA + " /Staatskonto, /Staatskonto einzahlen <Betrag>, /Staatskonto auszahlen <Betrag>");
                return true;
            }

        }
        else {
            player.sendMessage(ChatColor.GRAY + "Verwendung" + ChatColor.DARK_GRAY + ":" + ChatColor.AQUA + " /Staatskonto, /Staatskonto einzahlen <Betrag>, /Staatskonto auszahlen <Betrag>");
        }

        return false;
    }
}
