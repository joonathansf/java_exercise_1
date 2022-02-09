import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Input :");
            String input = sc.nextLine();
            if (input.equals("quit"))
            {
                break;
            }
            System.out.println("Unknown command");
        }

    }
}