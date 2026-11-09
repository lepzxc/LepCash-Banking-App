package service;

import model.CashInTransaction;
import model.TransferTransaction;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final List<User> users = new ArrayList<>();

    public BankService() {
        // Sample accounts for testing. Data resets when the program closes.
        users.add(new User(1001L, "09171234567", "1234", "Alex Santos", 5000.00));
        users.add(new User(1002L, "09981234567", "5678", "Jamie Reyes", 2500.00));
    }

    public User findUserByMobile(String mobileNumber) {
        for (User user : users) {
            if (user.getMobileNumber().equals(mobileNumber)) {
                return user;
            }
        }
        return null;
    }

    public User login(String mobileNumber, String pin) {
        User user = findUserByMobile(mobileNumber);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public boolean cashIn(User user, double amount) {
        if (!isValidAmount(amount)) return false;
        user.setBalance(user.getBalance() + amount);
        user.addTransaction(new CashInTransaction(amount));
        return true;
    }

    public String transfer(User sender, String receiverMobile, double amount) {
        if (!isValidAmount(amount)) return "Enter a valid amount greater than zero.";
        User receiver = findUserByMobile(receiverMobile);

        if (receiver == null) return "Receiver account was not found.";
        if (receiver == sender) return "You cannot transfer money to your own account.";
        if (sender.getBalance() < amount) return "Insufficient balance.";

        // Both balance changes and both logs are handled together.
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        sender.addTransaction(new TransferTransaction(amount, receiverMobile, true));
        receiver.addTransaction(new TransferTransaction(amount, sender.getMobileNumber(), false));
        return "Transfer successful!";
    }

    private boolean isValidAmount(double amount) {
        return Double.isFinite(amount) && amount > 0;
    }
}
