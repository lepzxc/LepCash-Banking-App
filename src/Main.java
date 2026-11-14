import model.Transaction;
import model.User;
import service.BankService;
import util.InputUtil;

import java.util.Scanner;

public class Main {
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputUtil input = new InputUtil(scanner);
        BankService bank = new BankService();

        System.out.println("==================================");
        System.out.println("             LepCash");
        System.out.println("==================================");
        System.out.println("DEMO ACCOUNTS:");
        System.out.println("Mobile: 09171234567 | PIN: 1234");
        System.out.println("Mobile: 09981234567 | PIN: 5678");

        User loggedInUser = authenticate(input, bank);
        if (loggedInUser == null) {
            System.out.println("Too many failed attempts. Application closed.");
            scanner.close();
            return;
        }

        System.out.println("\nWelcome, " + loggedInUser.getFullName() + "!");
        boolean running = true;

        while (running) {
            showMenu(loggedInUser);
            int choice = input.readMenuChoice();

            switch (choice) {
                case 1:
                    showBalance(loggedInUser);
                    break;
                case 2:
                    performCashIn(input, bank, loggedInUser);
                    break;
                case 3:
                    performTransfer(input, bank, loggedInUser);
                    break;
                case 4:
                    showTransactions(loggedInUser);
                    break;
                case 5:
                    System.out.println("Thank you for using LepCash. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1 to 5.");
            }
        }
        scanner.close();
    }

    private static User authenticate(InputUtil input, BankService bank) {
        for (int attempt = 1; attempt <= MAX_LOGIN_ATTEMPTS; attempt++) {
            System.out.println("\n--- LOGIN ---");
            String mobile = input.readText("Mobile number: ");
            String pin = input.readText("PIN: ");

            User user = bank.login(mobile, pin);
            if (user != null) return user;

            int remaining = MAX_LOGIN_ATTEMPTS - attempt;
            if (remaining > 0) {
                System.out.println("Incorrect mobile number or PIN. Attempts remaining: " + remaining);
            }
        }
        return null;
    }

    private static void showMenu(User user) {
        System.out.println("\n----------------------------------");
        System.out.println("LepCash MAIN MENU");
        System.out.println("Account: " + user.getFullName());
        System.out.println("1. Check Balance");
        System.out.println("2. Cash In");
        System.out.println("3. Transfer Money");
        System.out.println("4. Transaction History");
        System.out.println("5. Logout / Exit");
        System.out.println("----------------------------------");
    }

    private static void showBalance(User user) {
        System.out.printf("Current balance: PHP %.2f%n", user.getBalance());
    }

    private static void performCashIn(InputUtil input, BankService bank, User user) {
        double amount = input.readAmount("Enter cash-in amount: PHP ");
        if (bank.cashIn(user, amount)) {
            System.out.println("Cash-in successful!");
            showBalance(user);
        } else {
            System.out.println("Cash-in failed.");
        }
    }

    private static void performTransfer(InputUtil input, BankService bank, User user) {
        String receiver = input.readText("Enter receiver mobile number: ");
        double amount = input.readAmount("Enter amount to transfer: PHP ");
        System.out.println(bank.transfer(user, receiver, amount));
        showBalance(user);
    }

    private static void showTransactions(User user) {
        System.out.println("\n--- TRANSACTION HISTORY ---");
        if (user.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        // Polymorphism: each subclass provides its own getSummary() implementation.
        for (Transaction transaction : user.getTransactions()) {
            System.out.println(transaction.getSummary());
        }
    }
}
