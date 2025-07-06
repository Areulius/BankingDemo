import java.util.ArrayList;

public class BankUser {
    private final String username;
    private final String password;
    ArrayList<BankAccount> UserAccounts;


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

//    public createBankAccount(String name) {
//
//    }
//
//    public deleteBankAccount(String name) {
//
//    }
}
