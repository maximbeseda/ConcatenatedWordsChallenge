package concatenatedwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MultiThreadSearch {

    public static ArrayList<String> searchConcatenatedWords(ArrayList<String> wordsList, int countThread) {

        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<ArrayList<String>>> results = new ArrayList<>();
        ArrayList<String> threadArray = new ArrayList<>();
        for (int i = 0; i < countThread; i++) {
            int sizePartWordsList = wordsList.size() / countThread;
            int beginPartWordsList = sizePartWordsList * i;
            int endPartWordsList = ((i + 1) * sizePartWordsList);
            if ((wordsList.size() - endPartWordsList) < sizePartWordsList) {
                endPartWordsList = wordsList.size();
            }

            results.add(exec.submit(new ConcatenatedWordsSearch(wordsList, beginPartWordsList, endPartWordsList)));
        }
        for (Future<ArrayList<String>> fs : results) {
            try {
                threadArray.addAll(fs.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
        }

        Collections.sort(threadArray, ((o1, o2) -> {
            if (o1.length() > o2.length()) {
                return -1;
            }
            if (o2.length() > o1.length()) {
                return 1;
            }
            return 0;
        }));
        return threadArray;
    }

}