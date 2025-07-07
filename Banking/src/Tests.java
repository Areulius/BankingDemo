import jdk.javadoc.doclet.Taglet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tests {
    public static void main(String[] args) throws IOException {
        BankAccount ba1 = new BankAccount();
        BankAccount ba2 = new BankAccount(1000);

        System.out.println("Balance of account 1:");
        ba1.printBal();

        System.out.println("Balance of account 2:");
        ba2.printBal();

        System.out.println();
        ba2.transferFunds(ba1, 499);
        System.out.println();

        System.out.println("Balance of account 2:");
        ba2.printBal();

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String s = reader.readLine();

//        System.out.println("read line = " + s);

        File f = new File(System.getProperty("user.dir"));
        System.out.println(f.getPath());
        
        String[] pathnames = f.list();

        for (String pathname : pathnames) {
            // Print the names of files and directories
            System.out.println(pathname);
        }


    }
}