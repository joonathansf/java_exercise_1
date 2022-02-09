import java.util.Scanner;

public class Fibo implements Command {

    static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner sc) {
        System.out.println("Nombre :");
        int val = sc.nextInt();
        sc.nextLine();
        System.out.println("Résultat : " + fibonacci(val));
        return true;
    }
}
