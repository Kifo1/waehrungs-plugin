package kifo.de.waehrungen.bank;

import java.util.UUID;

public class Account {

    private final UUID uuid;
    private int balance = 100;

    public Account(UUID uuid) {
        this.uuid = uuid;
    }

    public Account(UUID uuid, int balance) {
        this.uuid = uuid;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void changeBalance(int amount) {
        balance = balance + amount;
    }
}
