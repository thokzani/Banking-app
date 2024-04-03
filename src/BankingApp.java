import java.util.Scanner;

// Main class for the banking app
public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter account information
        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.nextLine();

        System.out.print("Enter initial balance: $");
        double initialBalance = scanner.nextDouble();

        // Create a bank account using the entered information
        BankAccount account = new BankAccount(accountHolder, initialBalance);

        int choice;
        do {
            // Display menu options
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("0. Exit");

            // Prompt the user to enter their choice
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Perform actions based on user's choice
            switch (choice) {
                case 1:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.displayBalance();
                    break;
                case 0:
                    System.out.println("Exiting the banking app. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        // Close the Scanner to avoid resource leaks
        scanner.close();
    }
}
