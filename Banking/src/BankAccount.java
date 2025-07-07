import java.io.*;
import java.util.ArrayList;

public class BankAccount {
    private double balance;
    private final int id;
    private final BankUser connectedUser;

    // Constructors
    public BankAccount() {
        this.balance = 0;
        this.id = createId();
        this.connectedUser = MainApp.currentUser;
    }

    public BankAccount(double balance) {
        this.balance = balance;
        this.id = createId();
        this.connectedUser = MainApp.currentUser;
    }

    public BankAccount(double balance, int id, BankUser user) {
        this.balance = balance;
        this.id = id;
        this.connectedUser = user;
    }

    //Getters, setters
    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double newBal) {
        this.balance = newBal;
    }

    public int getId() {
        return this.id;
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

    // create and validate id
    private int createId() {

        boolean idIsGood = false;
        int newId = 0;

        while (!idIsGood) {
            newId = (int) (Math.random()*100);
            idIsGood = validateId(newId);
        }
        return newId;
    }

    private boolean validateId(int newId) {

        ArrayList<BankUser> userList = MainApp.getUserList();


        for (BankUser user : userList) {
            if (user.getUserBankAccounts() == null) return true;
            for (BankAccount acc : user.getUserBankAccounts()) {
                if (acc.getId() == newId) {
                    return false;
                }
            }
        }
        return true;
    }

    // storage and reading of bank accounts
    public static void storeAll() {

        //define bank account file
        File file = new File("src/storage/bankAccounts.csv");

        // write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if (MainApp.getUserList().isEmpty()) return;
            for (BankUser user : MainApp.getUserList()) {
                if (user.getUserBankAccounts().isEmpty()) continue;
                for (BankAccount acc : user.getUserBankAccounts()) {
                    String accString = String.format("%s,%s,%.2f\n", user.getUsername(), acc.getId(), acc.getBalance());
                    writer.write(accString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void readAll() {

        //define bank account file
        File file = new File("src/storage/bankAccounts.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();
            while (line != null) {

                String[] arr = line.split(",");
                System.out.println(line);
                BankUser user = MainApp.findUser(arr[0]);
                BankAccount bAccount = new BankAccount(Double.parseDouble(arr[2]), Integer.parseInt(arr[1]), user);

                assert user != null;
                user.getUserBankAccounts().add(bAccount);

                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}