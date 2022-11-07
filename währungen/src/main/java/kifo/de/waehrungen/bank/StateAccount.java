package kifo.de.waehrungen.bank;

import java.util.UUID;

public class StateAccount {

    private int balance = 0;

    public StateAccount() {
        this.balance = 100;
    }

    public StateAccount(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void changeBalance(int amount) {
        balance = balance + amount;
    }
}
