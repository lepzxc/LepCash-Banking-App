package util;

import java.util.Scanner;

public class InputUtil {
    private final Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public double readAmount(String prompt) {
        while (true) {
            String value = readText(prompt);
            try {
                double amount = Double.parseDouble(value);
                if (Double.isFinite(amount) && amount > 0) return amount;
                System.out.println("Amount must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Example: 500 or 250.50");
            }
        }
    }

    public int readMenuChoice() {
        String value = readText("Choose an option: ");
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
