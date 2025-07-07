import java.util.ArrayList;
import java.util.Scanner;


public class MainApp implements ConsoleColors{

    public static BankUser currentUser = null;
    private static final ArrayList<BankUser> userList = new ArrayList<>();


    public static void main(String[] args) {

        // start terminal program
        try {
            Scanner sc = new Scanner(System.in);

            // intro to the bank
            System.out.println(GREEN_BOLD + "-------------------------" + RESET);
            System.out.println(GREEN_BOLD_BRIGHT + "| WELCOME TO THE BANK™ |" + RESET);
            System.out.println(GREEN_BOLD + "------------------------" + RESET);
            System.out.println(GREEN + "Login with 'login <username> <pass>'" + RESET);
            System.out.println(GREEN + "See all commands with 'help'" + RESET);

            boolean exit = false;
            while(!exit) {
                System.out.print(">> ");
                String[] command = sc.nextLine().split(" +");
                switch (command[0]) {

                    case "help":
                        System.out.println(GREEN_ULINE + "List of Commands:" + RESET);
                        System.out.println("'login <username> <pass>' - login to account");
                        System.out.println("'logout' - logout from account");
                        System.out.println("'register <username> <pass>' - register a new user account");
                        System.out.println("'current' - view who is currently logged in");
                        System.out.println("'create' - create a new bank account for the logged-in user");
                        System.out.println("'delete <id>' - delete a bank account of the logged-in user");
                        System.out.println("'exit' - exit the app");

                        break;

                    case "login":
                        if (currentUser != null) {
                            System.out.println(YELLOW + "A user is already logged in" + RESET);
                            break;
                        } else if (command.length < 3) {
                            System.out.println("command format - 'login <username> <pass>'");
                            break;
                        } else {
                            currentUser = loginUser(command[1], command[2]);
                            break;
                        }

                    case "logout":
                        if (currentUser == null) {
                            System.out.println("Not logged in");
                        } else {
                            currentUser = null;
                            System.out.println(GREEN + "Logged out" + RESET);
                        }
                        break;

                    case "register":
                        //checking correctness
                        if (command.length < 3) {
                            System.out.println("command format - 'register <username> <pass>'");
                            break;
                        }

                        // create account
                        BankUser account = new BankUser(command[1], command[2]);
                        userList.add(account);


                        // if -l option added, login the user as well
                        if (command.length == 4 && command[3].equals("-l")) {
                            currentUser = account;
                            System.out.println(GREEN +"Successfully created account and logged in" + RESET);
                            break;
                        }

                        System.out.println(GREEN +"Successfully registered a new account" + RESET);
                        break;

                    case "create":
                        if (currentUser == null) {
                            System.out.println(YELLOW + "No-one is logged in" + RESET);
                            System.out.println(YELLOW + "Login using 'login <username> <password>'" + RESET);
                        } else {
                            currentUser.createBankAccount();
                        }
                        break;
                    case "delete":
                        if (command.length < 2) {
                            System.out.println(YELLOW + "command format - 'delete <id>'" + RESET);
                        } else if (currentUser == null) {
                            System.out.println(YELLOW + "No-one is logged in" + RESET);
                            System.out.println(YELLOW + "Login using 'login <username> <password>'" + RESET);
                        } else {
                            currentUser.deleteBankAccount(Integer.parseInt(command[1]));
                        }
                        break;

                    case "current":
                        if (currentUser != null) {
                            System.out.println(GREEN + "Current User: " + GREEN_BOLD_BRIGHT + currentUser.getUsername() + RESET);
                        } else {
                            System.out.println(YELLOW + "No user logged-in" + RESET);
                        }
                        break;

                    case "list":
                        if (currentUser.getUserBankAccounts().isEmpty()) {
                            System.out.println(YELLOW + "User has no bank accounts" + RESET);
                        } else {
                            int i = 0;
                            System.out.println(GREEN_ULINE + "User's Bank Accounts:" + RESET);
                            for (BankAccount acc : currentUser.getUserBankAccounts()) {
                                System.out.println("no." + (i+1) + " - id: " + acc.getId() );
                                i += 1;
                            }
                        }
                        break;

                    case "exit":
                        exit = true;
                        sc.close();
                        System.out.println(GREEN_BOLD_BRIGHT + "See You Next Time!" + RESET);
                        break;

                    default:
                        System.out.println(":( Command unknown (Type 'help' for all commands)");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // login method
    public static BankUser loginUser(String username, String password) {

        if (userList.isEmpty()) {
            System.out.println(RED_BRIGHT + ":( no such username (also there are no accounts)" + RESET);
            return null;
        }

        //loop through user list
        for (BankUser user : userList) {
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

    public static ArrayList<BankUser> getUserList() {
        return userList;
    }
}
