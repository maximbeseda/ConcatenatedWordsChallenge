package concatenatedwords;

import java.io.*;
import java.util.ArrayList;


public class FileReaderAndWriter {

    public static ArrayList<String> readToList (String fileName){
        ArrayList<String> wordsListFromFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            while (br.ready()){
                wordsListFromFile.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsListFromFile;
    }

    public static void writeListToFile (ArrayList<String> wordsList, String fileName){
        try (PrintWriter pw = new PrintWriter(fileName)){
            for (int i = 0; i < wordsList.size(); i++) {
                pw.println(wordsList.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
