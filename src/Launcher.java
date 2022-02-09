import java.nio.file.Files;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Launcher {

    static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static void readFile(String file) {
        try {
            Path filePath = Paths.get(file);
            String content = Files.readString(filePath);
            String[] lines = content.split("\n");
            for (String line : lines) {
                String[] words = line.split(" ");
                HashMap<String, Integer> occurences = new HashMap<>();
                boolean isLineEmpty = false;
                for (String word : words) {
                    int value = 0;
                    if (word.isBlank()) {
                        isLineEmpty = true;
                        break;
                    }
                    if (occurences.containsKey(word))
                        value = occurences.get(word);
                    occurences.put(word, value + 1);
                }
                int limit = 0;
                while (limit < 3 || occurences.size() < 0) {
                    String indice = "";
                    int max = -1;
                    for (Map.Entry<String, Integer> entry : occurences.entrySet()) {
                        String key = entry.getKey();
                        int value = entry.getValue();
                        if (value > max) {
                            indice = key;
                            max = value;
                        }
                    }
                    if (!isLineEmpty) System.out.print(indice + " ");
                    occurences.remove(indice);
                    limit += 1;
                }
                if (!isLineEmpty) System.out.print("\n");
            }
        } catch(Exception e) {
            System.out.println("Unreadable file: " + e.getClass() + " " +  e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Input :");
            String input = sc.nextLine();
            switch (input) {
                case "quit":
                    sc.close();
                    return;
                case "fibo":
                    System.out.println("Nombre :");
                    int val = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Résultat : " + fibonacci(val));
                    break;
                case "freq":
                    System.out.println("Fichier :");
                    String file = sc.nextLine();
                    readFile(file);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
}