import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Input :");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (!input.equals("quit"))
        {
            System.out.println("Unknown command");
        }
    }
}