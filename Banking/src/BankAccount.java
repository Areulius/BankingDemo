public class BankAccount {
    private double balance;

    // Constructors
    public BankAccount() {
        this.balance = 0;
    }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    //Getters, setters
    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double newBal) {
        this.balance = newBal;
    }

    //Other Methods
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("Deposited %f to the account\n", amount);
        } else {
            System.out.println(":( You can only deposit a positive amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            this.balance -= amount;
            System.out.printf("Withdrew %f from the account\n", amount);
        } else {
            System.out.println(":( You can only withdraw a positive amount");
        }
    }

    public void printBal() {
        System.out.printf("| Account's Balance: %.2f |\n", this.balance);
    }

    public void transferFunds(BankAccount toAcc, double amount) {
        if (amount > 0) {
            this.balance -= amount;
            toAcc.balance += amount;
            System.out.printf("Successfully transferred %.2f!\n", amount);
        } else {
            System.out.println(":( You can only transfer a positive amount");
        }
    }
}