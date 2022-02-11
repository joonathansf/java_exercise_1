import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command {

    static void readFile(String file, String word) {
        try {
            Path filePath = Paths.get(file);
            String content = Files.readString(filePath);
            String[] words = content.split(" ");
            String result = word;
            for (int i = 0; i < 19; i++) {
                ArrayList<String> occurences = new ArrayList<String>();
                for (int j = 0; j < words.length; j++) {
                    if (words[j].equals(word)) {
                        occurences.add(words[j + 1]);
                    }
                }
                Collections.sort(occurences);
                int max = 1;
                String current = occurences.get(occurences.size() - 1) ;
                int count = 1;
                for (int k = 1; k < occurences.size(); k++){
                    if (occurences.get(k).equals(occurences.get(k - 1))){
                        count += 1;
                    }
                    else{
                        if (count > max) {
                            current = occurences.get(k - 1);
                            max = count;
                        }
                        count = 1;
                    }
                }
                if (count > max) {
                    current = occurences.get(occurences.size() - 1);
                    max = count;
                }
                result += " " + current;
                word = current;
            }
            System.out.println(result.toLowerCase());
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
        String word = sc.nextLine();
        readFile(file, word.toLowerCase());
        return true;
    }
}
