package kifo.de.waehrungen;

import kifo.de.waehrungen.bank.AccountManager;
import kifo.de.waehrungen.commands.*;
import kifo.de.waehrungen.mayor.Mayor;
import kifo.de.waehrungen.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    private static Main instance;
    private Config config;
    private AccountManager accountManager;
    private Mayor mayor;

    @Override
    public void onLoad() {
        instance = this;
        config = new Config();
    }

    @Override
    public void onEnable() {
        getLogger().info("Währungs-Plugin wurde gestartet.");
        getCommand("kontostand").setExecutor(new BalanceCommand());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("staatskonto").setExecutor(new StateAccountCommand());
        getCommand("setMayor").setExecutor(new SetMayorCommand());
        getCommand("getMayor").setExecutor(new getMayorCommand());

        mayor = new Mayor();
        accountManager = new AccountManager();
    }

    @Override
    public void onDisable() {
        getLogger().info("Währungs-Plugin wurde beendet.");
        accountManager.save();
        mayor.save();
        config.save();
    }

    public static Main getInstance() {
        return instance;
    }

    public Config getConfiguration() {
        return config;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public Mayor getMayor() {
        return mayor;
    }
}
