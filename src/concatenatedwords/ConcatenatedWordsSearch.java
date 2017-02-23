package concatenatedwords;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.Callable;


public class ConcatenatedWordsSearch implements Callable<ArrayList<String>> {

    private ArrayList<String> concatenatedWordsList = new ArrayList<>();
    private ArrayList<String> wordsList;
    private int beginList;
    private int endList;


    public ConcatenatedWordsSearch(ArrayList<String> wordsList, int beginList, int endList) {
        this.wordsList = wordsList;
        this.beginList = beginList;
        this.endList = endList;

    }

    @Override
    public ArrayList<String> call() {

        for (int i = beginList; i < endList; i++) {
            System.out.println(i);
            ArrayList<String> containsWords = new ArrayList<>();
            String conWord = wordsList.get(i);
            for (int j = 0; j < wordsList.size(); j++) {
                if (!wordsList.get(j).equals(conWord) && conWord.contains(wordsList.get(j)) && (conWord.length() - wordsList.get(j).length()) != 1) {
                    containsWords.add(wordsList.get(j));
                }
            }

            ArrayList<String> tempList = new ArrayList<>(containsWords);
            tempList.sort(((o1, o2) -> o2.length() - o1.length()));
            if (search(i, conWord, tempList)) continue;

            tempList = new ArrayList<>(containsWords);
            tempList.sort(((o1, o2) -> o1.length() - o2.length()));
            if (search(i, conWord, tempList)) continue;

            tempList = new ArrayList<>(containsWords);
            tempList.sort((Comparator.naturalOrder()));
            if (search(i, conWord, tempList)) continue;

            tempList = new ArrayList<>(containsWords);
            tempList.sort((Comparator.reverseOrder()));
            if (search(i, conWord, tempList)) continue;

            tempList = new ArrayList<>(containsWords);
            tempList.sort(((o1, o2) -> {
                if (o1.length() > o2.length()) {
                    return -1;
                }
                if (o2.length() > o1.length()) {
                    return 1;
                }
                return o2.compareTo(o1);
            }));
            if (search(i, conWord, tempList)) continue;

            tempList = new ArrayList<>(containsWords);
            tempList.sort(((o1, o2) -> {
                if (o1.length() > o2.length()) {
                    return 1;
                }
                if (o2.length() > o1.length()) {
                    return -1;
                }
                return o2.compareTo(o1);
            }));
            if (search(i, conWord, tempList)) continue;

        }
        return concatenatedWordsList;
    }

    private boolean search(int i, String conWord, ArrayList<String> tempList) {
        for (int j = 0; j < tempList.size(); j++) {
            for(int k = 0; k < tempList.size(); k++){
                conWord = conWord.replaceAll(tempList.get(k), "*");
            }

            if (!conWord.matches("(?i).*[a-zа-я].*")) {
                concatenatedWordsList.add(wordsList.get(i));
                return true;
            } else {
                String a = tempList.get(0);
                tempList.remove(0);
                tempList.add(a);
                conWord = wordsList.get(i);
            }
        }
        return false;
    }
}