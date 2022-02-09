import java.util.*;

public class Launcher {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Command> actions = new ArrayList<Command>();
        actions.add(new Freq());
        actions.add(new Fibo());
        actions.add(new Quit());
        boolean finish = false;
        while (!finish) {
            boolean actionFound = false;
            System.out.println("Input :");
            String input = sc.nextLine();
            for (int i = 0; i < actions.size(); i++) {
                if (actions.get(i).name().equals(input)) {
                    actionFound = true;
                    finish = actions.get(i).run(sc);
                }
            }
            if (!actionFound) System.out.println("Unknown command");
        }
        sc.close();
    }
}