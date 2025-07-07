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
}
