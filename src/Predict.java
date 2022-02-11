import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command {

    static void readFile(String file, String word) {
        try {
            Path filePath = Paths.get(file);
            String content = Files.readString(filePath);
            String[] word_list = content.split(" ");
            String phrase = word;
            for (int i = 0; i < 19; i++) {
                ArrayList<String> mot_apres = new ArrayList<String>();
                //on met tout les mots 'apres' le mot acutel dans une liste
                for (int j = 0; j < word_list.length; j++) {
                    if (word_list[j].equals(word)) {
                        //System.out.println("j'ajoute : " + word_list[j + 1]);
                        mot_apres.add(word_list[j + 1]);
                    }
                }
                Collections.sort(mot_apres);
                int maxi = 1;
                String mot_max = mot_apres.get(mot_apres.size() - 1) ;
                int counte = 1;
                for (int w = 1; w < mot_apres.size(); w++){
                    if (mot_apres.get(w).equals(mot_apres.get(w - 1))){
                        counte += 1;
                    }
                    else{
                        if (counte > maxi) {
                            mot_max = mot_apres.get(w - 1);
                            maxi = counte;
                        }
                        counte = 1;
                    }
                }
                if (counte > maxi) {
                    mot_max = mot_apres.get(mot_apres.size() - 1);
                    maxi = counte;
                }
                //System.out.println("j'ai hoisis le mot : " + mot_max);
                phrase += " " + mot_max;
                word = mot_max;
            }
            System.out.println(phrase);
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
