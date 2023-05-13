import java.util.Random;
import java.util.UUID;

public class Account {

    private long number;
    private String name;
    private double balance;

    public Account(long number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public long getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

}
