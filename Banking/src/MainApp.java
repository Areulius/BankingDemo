import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainApp implements ConsoleColors{
    public static void main(String[] args) {

        // create list of users
        ArrayList<BankUser> UserList = new ArrayList<>();

        // start terminal program
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(GREEN_BOLD + "------------------------" + RESET);
            System.out.println(GREEN_BOLD_BRIGHT + "| WELCOME TO THE BANK™ |" + RESET);
            System.out.println(GREEN_BOLD + "------------------------" + RESET);
            System.out.println(GREEN + "Login with 'login <username>'" + RESET);
            System.out.println(GREEN + "See all commands with 'help'" + RESET);

            boolean exit = false;
            while(!exit) {
                System.out.print(">> ");
                String[] command = r.readLine().split(" +");
                switch (command[0]) {
                    case "help":
                        System.out.println("help command wip");
                        break;
                    case "login":
                        System.out.println("login functionality wip");
                        break;
                    case "exit":
                        exit = true;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println(":( Command unknown (Type 'help' for all commands)");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
