import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Freq implements Command {

    static void readFile(String file) {
        try {
            Path filePath = Paths.get(file);
            String content = Files.readString(filePath);
            String[] lines = content.split("\n");
            for (String line : lines) {
                String[] words = line.split(" ");
                LinkedHashMap<String, Integer> occurences = new LinkedHashMap<>();
                boolean isLineEmpty = false;
                for (String word : words) {
                    int value = 0;
                    if (word.isBlank()) {
                        isLineEmpty = true;
                        break;
                    }
                    if (occurences.containsKey(word.toLowerCase()))
                        value = occurences.get(word.toLowerCase());
                    occurences.put(word.toLowerCase(), value + 1);
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

    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner sc) {
        System.out.println("Fichier :");
        String file = sc.nextLine();
        readFile(file);
        return true;
    }
}
