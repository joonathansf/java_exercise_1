import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Predict implements Command {
    @Override
    public String name() {
        return "predict";
    }
    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Please enter the path of the file");
        String strFilePath = "";
        try {
            strFilePath = scanner.nextLine();
            String a = Files.readString(Paths.get(strFilePath));
            String[] words = a.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split(" ");
            Map<String, Map<String, Integer>> firstWordhashMap = new HashMap<>();

            for (int i = 0; i < words.length - 1; i++) {
                String firstWord = words[i];
                String followingWord = words[i+1];
                Map<String, Integer> followingWordHashMap = firstWordhashMap.get(firstWord);
                if(followingWordHashMap == null) {
                    followingWordHashMap = new HashMap<>();
                    firstWordhashMap.put(firstWord, followingWordHashMap);
                    followingWordHashMap.put(followingWord, 1);
                    continue;
                }
                Integer j = followingWordHashMap.get(followingWord);
                if (j == null)
                    followingWordHashMap.put(followingWord, 1);
                else
                    followingWordHashMap.replace(followingWord, j+1);
            }
            System.out.println("Enter a word : ");
            strFilePath = scanner.nextLine();
            strFilePath = strFilePath.toLowerCase();
            if (!firstWordhashMap.containsKey(strFilePath)) {
                System.out.println("This word isn't in the text, exiting the command !");
                return false;
            }
            String followingWord = strFilePath;
            StringBuilder stringBuilder = new StringBuilder();
            int length = 1;
            while(firstWordhashMap.containsKey(followingWord) && length++ < 20) {
                Map<String, Integer> val = firstWordhashMap.get(followingWord);
                Integer value = 0;
                stringBuilder.append(" " + followingWord);
                for(Entry<String, Integer> e : val.entrySet()) {
                    if(e.getValue() > value) {
                        followingWord = e.getKey();
                        value = e.getValue();
                    }
                }
            }
            stringBuilder.append(' ' + followingWord);
            System.out.println(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println("Unreadable file : " + e.getClass() + " " + e.getMessage());
        }
        return true;
    }
}