package concatenatedwords;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class ConcatenatedWordsSearch implements Runnable {

    private Map<Integer, String> concatenatedWordsMap = new ConcurrentHashMap<>();
    private ArrayList<String> wordsList;
    private int beginList;
    private int endList;
    private Thread thr;

    public ConcatenatedWordsSearch(ArrayList<String> wordsList, int beginList, int endList) {
        this.wordsList = wordsList;
        this.beginList = beginList;
        this.endList = endList;
        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {

        for (int i = beginList; i < endList; i++) {
            HashMap<Integer, String> tempHashMap = new HashMap<>();
            for (int j = 0; j < wordsList.size(); j++) {
                if (!wordsList.get(j).equals(wordsList.get(i)) && wordsList.get(j).contains(wordsList.get(i))) {
                    tempHashMap.put(j, wordsList.get(j).replaceAll(wordsList.get(i), "*"));
                }
            }
            for (String wordFromList : wordsList) {
                for (Map.Entry<Integer, String> entry : tempHashMap.entrySet()) {
                    if (entry.getValue().contains(wordFromList)) {
                        tempHashMap.put(entry.getKey(), entry.getValue().replaceAll(wordFromList, "*"));
                    }
                }
            }
            for (Map.Entry<Integer, String> entry : tempHashMap.entrySet()) {
                if (concatenatedWordsMap.get(entry.getKey()) == null && !entry.getValue().matches("(?i).*[a-zа-я].*")) {
                    concatenatedWordsMap.put(entry.getKey(), wordsList.get(entry.getKey()));
                }
            }
            System.out.println(i);
        }
    }

    public Map<Integer, String> getConcatenatedWordsMap() {
        return concatenatedWordsMap;
    }

    public Thread getThr() {
        return thr;
    }
}
