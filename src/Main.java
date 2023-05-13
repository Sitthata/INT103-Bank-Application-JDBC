import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        bank_application(sc);
    }

    public static void bank_application(Scanner sc) {

        boolean running = true;
        Bank bank = new Bank("KMUTT BANKING");

        while(running) {
            System.out.println("Welcome to " + bank.getName());
            System.out.println("1. Open Account");
            System.out.println("2. Close Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. List Accounts");
            System.out.println("6. Account Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    // Open Account
                    long number = generateUniqueID();
                    System.out.print("Enter account name: ");
                    String name = sc.next();
                    System.out.print("Enter account balance: ");
                    double balance = sc.nextDouble();
                    Account account = new Account(number, name, balance);
                    bank.openAccount(account);
                    System.out.println();
                }
                case 2 -> {
                    // Close Account
                    System.out.print("Enter account number: ");
                    int accountNumber = sc.nextInt();
                    bank.closeAccount(accountNumber);
                    System.out.println();
                }
                case 3 -> {
                    // Deposit Money
                    System.out.print("Enter account number: ");
                    int accountNumber = sc.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double amount = sc.nextDouble();
                    bank.depositMoney(accountNumber, amount);
                    System.out.println();
                }
                case 4 -> {
                    // Withdraw Money
                    System.out.print("Enter account number: ");
                    int accountNumber = sc.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double amount = sc.nextDouble();
                    bank.withdrawMoney(accountNumber, amount);
                    System.out.println();
                }
                case 5 -> {
                    bank.listAccount();
                    System.out.println();
                }
                case 6 -> {
                    // Account Details
                    System.out.print("Enter account number: ");
                    long accountNumber = sc.nextLong();
                    if(!bank.isAccountExist(accountNumber)) {
                        System.out.println("Account does not exist\n");
                        break;
                    }
                    Account currentAccount = bank.getAccount(accountNumber);
                    if (currentAccount == null) {
                        System.out.println("Account does not exist\n");
                        break;
                    }

                    System.out.println("Account Number: " + currentAccount.getNumber());
                    System.out.println("Account Name: " + currentAccount.getName());
                    System.out.println("Account Balance: " + currentAccount.getBalance());
                    System.out.println();
                }
                case 7 -> running = false;
                default -> System.out.println("Invalid input");
            }
        }
    }

    public static long generateUniqueID() {
        long lowerBound = 1_000_000_000L; // 10 digits
        long upperBound = 9_999_999_999L; // 10 digits

        // Generate a random number between lowerBound and upperBound
        return ThreadLocalRandom.current().nextLong(lowerBound, upperBound);
    }

}