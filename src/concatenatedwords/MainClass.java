package concatenatedwords;

import java.util.ArrayList;


public class MainClass {
    public static void main(String[] args) {

        ArrayList<String> wordsFromFile = ReadFile.readToList("words.txt");
        ArrayList<String> concatenatedWordsList = MultiThreadSearch.searchConcatenatedWords(wordsFromFile, 16);

        System.out.println("The 1st longest concatenated word --> " + concatenatedWordsList.get(0));
        System.out.println("The 2nd longest concatenated word --> " + concatenatedWordsList.get(1));
        System.out.println("The total count of all the concatenated words in the file --> " + concatenatedWordsList.size());

    }
}
