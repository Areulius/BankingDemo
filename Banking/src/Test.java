public class Test {
    public static void main(String[] args) {
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
    }
}