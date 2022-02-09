import java.util.Scanner;

public class Launcher {

    static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Input :");
            String input = sc.nextLine();
            switch (input) {
                case "quit":
                    return;
                case "fibo":
                    System.out.println("Nombre :");
                    int val = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Suite de fibo : " + fibonacci(val));
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }

    }
}