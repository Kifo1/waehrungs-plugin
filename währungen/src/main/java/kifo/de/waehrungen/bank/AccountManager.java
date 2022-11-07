package kifo.de.waehrungen.bank;

import kifo.de.waehrungen.Main;
import kifo.de.waehrungen.utils.Config;

import java.util.*;

public class AccountManager {

    private final Map<UUID, Account> map;
    private StateAccount stateAccount;

    public AccountManager() {
        map = new HashMap<>();

        load();
    }

    public StateAccount getStateAccount() {
        if(stateAccount == null) {
            stateAccount = new StateAccount();
        }
        return stateAccount;
    }

    public Account getAccount(UUID uuid) {

        if(map.containsKey(uuid)) {
            return map.get(uuid);
        }

        Account account = new Account(uuid);
        map.put(uuid, account);
        return account;
    }

    public void setAccount(UUID uuid, Account account) {
        map.put(uuid, account);
    }

    private void load() {
        Config config = Main.getInstance().getConfiguration();

        List<String> uuids = config.getConfig().getStringList("accounts");

        uuids.forEach(s -> {
            UUID uuid = UUID.fromString(s);

            int balance = config.getConfig().getInt("account." + s);

            map.put(uuid, new Account(uuid, balance));
        });

        if(config.getConfig().contains("stateAccount")) {
            stateAccount = new StateAccount(config.getConfig().getInt("stateAccount"));
        }
    }

    public void save() {
        Config config = Main.getInstance().getConfiguration();

        List<String> uuids = new ArrayList<>();

        for(UUID uuid : map.keySet()) {
            uuids.add(uuid.toString());
        }

        config.getConfig().set("accounts", uuids);
        map.forEach((uuid, account) -> config.getConfig().set("account." + uuid.toString(), account.getBalance()));

        config.getConfig().set("stateAccount", stateAccount.getBalance());
    }
}
