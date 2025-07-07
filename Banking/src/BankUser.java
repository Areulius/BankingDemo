import java.io.*;
import java.util.ArrayList;

public class BankUser implements ConsoleColors{
    private final String username;
    private final String password;
    private final ArrayList<BankAccount> userBankAccounts = new ArrayList<>();


    public BankUser (String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void createBankAccount() {
        BankAccount newAccount = new BankAccount();
        userBankAccounts.add(newAccount);
        System.out.println(GREEN + "Created bank account with id: " + this.userBankAccounts.getLast().getId() + RESET);
    }

    public void deleteBankAccount(int id) {
        if (userBankAccounts.isEmpty()) {
            System.out.println("There are no accounts to delete");
        } else {
            for (BankAccount acc : userBankAccounts) {
                if (id == acc.getId()) {
                    userBankAccounts.remove(acc);
                    System.out.println(GREEN + "Deleted bank account with id: " + id + RESET);
                    return;
                }
            }
            System.out.println(YELLOW + "Found no account of such id" + RESET);
            return;
        }

    }


    public ArrayList<BankAccount> getUserBankAccounts() {
        return this.userBankAccounts;
    }

    // login method
    public static BankUser loginUser(String username, String password) {

        if (MainApp.getUserList().isEmpty()) {
            System.out.println(RED_BRIGHT + ":( no such username (also there are no accounts)" + RESET);
            return null;
        }

        //loop through user list
        for (BankUser user : MainApp.getUserList()) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    System.out.println(GREEN +"Successfully logged in" + RESET);
                    return user;
                } else {
                    System.out.println(RED_BRIGHT + ":( wrong password" + RESET);
                    return null;
                }
            } else {
                System.out.println(RED_BRIGHT + ":( no such username" + RESET);
                return null;
            }
        }
        return null;
    }

    // storage in file------------
    public static void storeAll() {
        //define user file
        File file = new File("src/storage/users.csv");

        // write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if (MainApp.getUserList().isEmpty()) return;
            for (BankUser user : MainApp.getUserList()) {
                String userString = String.format("%s,%s", user.getUsername(), user.getPassword());
                for (BankAccount acc : user.getUserBankAccounts()) {
                    userString = userString.concat("," + acc.getId());
                }
                writer.write(userString + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readAll() {
        //define user file
        File file = new File("src/storage/users.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(",");
                BankUser account = new BankUser(arr[0], arr[1]);
                MainApp.getUserList().add(account);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
