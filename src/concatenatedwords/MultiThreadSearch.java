package concatenatedwords;

import java.util.*;


public class MultiThreadSearch {

    public static ArrayList<String> searchConcatenatedWords(ArrayList<String> wordsList, int countThread) {

        ConcatenatedWordsSearch[] threadArray = new ConcatenatedWordsSearch[countThread];
        for (int i = 0; i < threadArray.length; i++) {
            int sizePartWordsList = wordsList.size() / countThread;
            int beginPartWordsList = sizePartWordsList * i;
            int endPartWordsList = ((i + 1) * sizePartWordsList);
            if ((wordsList.size() - endPartWordsList) < sizePartWordsList) {
                endPartWordsList = wordsList.size();
            }
            threadArray[i] = new ConcatenatedWordsSearch(wordsList, beginPartWordsList, endPartWordsList);
        }
        for (int i = 0; i < threadArray.length; i++) {
            try {
                threadArray[i].getThr().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> concatenatedWordsList = mergePartWordsList(threadArray);
        Collections.sort(concatenatedWordsList, ((o1, o2) -> {
            if (o1.length() > o2.length()) {
                return -1;
            }
            if (o2.length() > o1.length()) {
                return 1;
            }
            return 0;
        }));
        return concatenatedWordsList;
    }

    private static ArrayList<String> mergePartWordsList(ConcatenatedWordsSearch[] threadArray) {
        Set<String> concatenatedWordsSet = new TreeSet<>();
        for (int i = 0; i < threadArray.length; i++) {
            concatenatedWordsSet.addAll(threadArray[i].getConcatenatedWordsMap().values());
        }
        return new ArrayList<>(concatenatedWordsSet);
    }
}
