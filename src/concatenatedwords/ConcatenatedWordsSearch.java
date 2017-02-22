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
            ArrayList<String> tempList = new ArrayList<>();
            String conWord = wordsList.get(i);
            for (int j = 0; j < wordsList.size(); j++) {
                if (!wordsList.get(j).equals(conWord) && conWord.contains(wordsList.get(j)) && (conWord.length() - wordsList.get(j).length()) != 1) {
                    tempList.add(wordsList.get(j));
                }
            }

            tempList.sort(((o1, o2) -> o2.length() - o1.length()));

            for (int j = 0; j < tempList.size(); j++) {
                conWord = conWord.replaceAll(tempList.get(j), "*");
            }

            if (!conWord.matches("(?i).*[a-zа-я].*")) {
                concatenatedWordsList.add(wordsList.get(i));
                continue;
            }
            conWord = wordsList.get(i);

            tempList.sort(((o1, o2) -> o1.length() - o2.length()));

            for (int j = 0; j < tempList.size(); j++) {
                conWord = conWord.replaceAll(tempList.get(j), "*");
            }

            if (!conWord.matches("(?i).*[a-zа-я].*")) {
                concatenatedWordsList.add(wordsList.get(i));
                continue;
            }
            conWord = wordsList.get(i);

            tempList.sort((Comparator.naturalOrder()));

            for (int j = 0; j < tempList.size(); j++) {
                conWord = conWord.replaceAll(tempList.get(j), "*");
            }

            if (!conWord.matches("(?i).*[a-zа-я].*")) {
                concatenatedWordsList.add(wordsList.get(i));
                continue;
            }
            conWord = wordsList.get(i);

            tempList.sort((Comparator.reverseOrder()));

            for (int j = 0; j < tempList.size(); j++) {
                conWord = conWord.replaceAll(tempList.get(j), "*");
            }

            if (!conWord.matches("(?i).*[a-zа-я].*")) {
                concatenatedWordsList.add(wordsList.get(i));
                continue;
            }
            conWord = wordsList.get(i);

            tempList.sort(((o1, o2) -> {
                if (o1.length() > o2.length()) {
                    return -1;
                }
                if (o2.length() > o1.length()) {
                    return 1;
                }
                return o2.compareTo(o1);
            }));

            for (int j = 0; j < tempList.size(); j++) {
                conWord = conWord.replaceAll(tempList.get(j), "*");
            }

            if (!conWord.matches("(?i).*[a-zа-я].*")) {
                concatenatedWordsList.add(wordsList.get(i));
                continue;
            }
            conWord = wordsList.get(i);

            tempList.sort(((o1, o2) -> {
                if (o1.length() > o2.length()) {
                    return 1;
                }
                if (o2.length() > o1.length()) {
                    return -1;
                }
                return o2.compareTo(o1);
            }));

            for (int j = 0; j < tempList.size(); j++) {
                conWord = conWord.replaceAll(tempList.get(j), "*");
            }

            if (!conWord.matches("(?i).*[a-zа-я].*")) {
                concatenatedWordsList.add(wordsList.get(i));
            }
        }
        return concatenatedWordsList;
    }
}