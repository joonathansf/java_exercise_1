import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Predict implements Command {

    static void readFile(String file, String mot) {
        try {
            Path filePath = Paths.get(file);
            String content = Files.readString(filePath);
            String[] lines = content.split(" ");
            boolean founded = false;
            for (String word : lines)
            {
                if (word.equals(mot)) {
                    founded = true;
                }
            }
            if (!founded) {
                System.out.println("Mot non trouvé");
            }
            else {
                for (String word : lines)
                {
                    System.out.print(word + " ");
                }
                System.out.println("");
            }
        } catch(Exception e) {
            System.out.println("Unreadable file: " + e.getClass() + " " +  e.getMessage());
        }
    }

    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner sc) {
        System.out.println("Fichier :");
        String file = sc.nextLine();
        System.out.println("Mot :");
        String mot = sc.nextLine();
        readFile(file, mot);
        return true;
    }
}
