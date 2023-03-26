import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ATMinterface {
    private static HashMap<Integer, Integer> accounts = new HashMap<Integer, Integer>();
    private static HashMap<Integer, ArrayList<String>> transactionHistories = new HashMap<Integer, ArrayList<String>>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean loggedIn = false;
    private static int userId = 0;

    public static void main(String[] args) {
        accounts.put(123456789, 1234);
        accounts.put(987654321, 5678);
        accounts.put(987654322, 5679);
        accounts.put(987654323, 5670);
        accounts.put(987654324, 5671);
        accounts.put(987654325, 5672);
        accounts.put(987654326, 5673);
        accounts.put(987654327, 5674);
        accounts.put(987654328, 5675);

        while (true) {
            if (!loggedIn) {
                login();
            } else {
                System.out.println("\nEnter 1 for balance inquiry, 2 for deposit, 3 for withdrawal, 4 for transfer, 5 for transaction history, or 6 to logout:");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        viewTransactionHistory();
                        break;
                    case 6:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            }
        }
    }

    private static void login() {
        System.out.println("\nEnter user ID:");
        int inputUserId = scanner.nextInt();
        System.out.println("Enter user PIN:");
        int pin = scanner.nextInt();

        if (accounts.containsKey(inputUserId) && accounts.get(inputUserId) == pin) {
            loggedIn = true;
            userId = inputUserId;
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed! Please try again.");
        }
    }

    private static void viewBalance() {
        System.out.println("\nYour current balance is $" + accounts.get(userId));
    }

    private static void deposit() {
        System.out.println("\nEnter deposit amount:");
        int depositAmount = scanner.nextInt();
        accounts.put(userId, accounts.get(userId) + depositAmount);
        System.out.println("$" + depositAmount + " deposited successfully!");

        ArrayList<String> transactionHistory = transactionHistories.getOrDefault(userId, new ArrayList<String>());
        transactionHistory.add("Deposit: +" + depositAmount);
        transactionHistories.put(userId, transactionHistory);
    }

    private static void withdraw() {
        System.out.println("\nEnter withdrawal amount:");
        int withdrawalAmount = scanner.nextInt();

        if (withdrawalAmount > accounts.get(userId)) {
            System.out.println("Insufficient funds!");
        } else {
            accounts.put(userId, accounts.get(userId) - withdrawalAmount);
            System.out.println("$" + withdrawalAmount + " withdrawn successfully!");

            ArrayList<String> transactionHistory = transactionHistories.getOrDefault(userId, new ArrayList<String>());
            transactionHistory.add("Withdrawal: -" + withdrawalAmount);
            transactionHistories.put(userId, transactionHistory);
        }
    }

    private static void transfer() {
        System.out.println("\nEnter transfer amount:");
        int transferAmount = scanner.nextInt();
        System.out.println("Enter recipient user ID:");
        int recipientUserId = scanner.nextInt();

        if (!accounts.containsKey(recipientUserId)) {
            System.out.println("Invalid recipient user ID!");
        } else if (transferAmount > accounts.get(userId)) {
            System.out.println("Insufficient funds!");
        } else {
            accounts.put(userId, accounts.get(userId) - transferAmount);
            accounts.put(recipientUserId, accounts.get(recipientUserId) + transferAmount);
            System.out.println("$" + transferAmount + " transferred successfully to user " + recipientUserId + "!");
            ArrayList<String> senderTransactionHistory = transactionHistories.getOrDefault(userId, new ArrayList<String>());
            senderTransactionHistory.add("Transfer to " + recipientUserId + ": -" + transferAmount);
            transactionHistories.put(userId, senderTransactionHistory);
    
            ArrayList<String> recipientTransactionHistory = transactionHistories.getOrDefault(recipientUserId, new ArrayList<String>());
            recipientTransactionHistory.add("Transfer from " + userId + ": +" + transferAmount);
            transactionHistories.put(recipientUserId, recipientTransactionHistory);
        }
    }
    
    private static void viewTransactionHistory() {
        ArrayList<String> transactionHistory = transactionHistories.getOrDefault(userId, new ArrayList<String>());
    
        if (transactionHistory.isEmpty()) {
            System.out.println("\nNo transaction history found.");
        } else {
            System.out.println("\nTransaction history:");
            for (String transaction : transactionHistory) {
                System.out.println("- " + transaction);
            }
        }
    }
    
    private static void logout() {
        loggedIn = false;
        userId = 0;
        System.out.println("\nLogout successful!");
    }
}    

