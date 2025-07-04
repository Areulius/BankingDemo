import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainApp {
    public static void main(String[] args) {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

            boolean exit = false;
            while(!exit) {
                String[] command = r.readLine().split(" +");
                switch (command[0]) {
                    case "help":
                        System.out.println("help command wip");
                        break;
                    case "hello":
                        System.out.println("Hello, user");
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
