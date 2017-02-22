package concatenatedwords;

import java.util.ArrayList;


public class MainClass {
    public static void main(String[] args) {

        ArrayList<String> wordsFromFile = FileReaderAndWriter.readToList("words.txt");

        ArrayList<String> concatenatedWordsList = MultiThreadSearch.searchConcatenatedWords(wordsFromFile, 8);
        FileReaderAndWriter.writeListToFile(concatenatedWordsList, "result.txt");
        System.out.println("The total count of all the concatenated words in the file --> " + concatenatedWordsList.size());


    }
}
