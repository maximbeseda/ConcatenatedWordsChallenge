package concatenatedwords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadFile {

    public static ArrayList<String> readToList (String fileName){
        ArrayList<String> wordsListFromFile = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (br.ready()){
                wordsListFromFile.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsListFromFile;
    }

}
