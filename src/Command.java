import java.util.Scanner;

interface Command {
    String name();
    boolean run(Scanner sc);
}