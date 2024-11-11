/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Anagrams {
    private final List<String> wordList;
    //  CONSTRUCTOR
    public Anagrams(String filePath) {
        this.wordList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = br.readLine()) != null) {
                this.wordList.addAll(Arrays.asList(line.split(" ")));
            }
            br.close();
        } catch (Exception e) {
            System.err.println("program could nto find files in "+filePath);
        }
    }
    //  METHODS
    private boolean areAnagrams(String w1, String w2) {
        TreeSet<String> t1 = new TreeSet<>(Arrays.asList(w1.split("")));
        TreeSet<String> t2 = new TreeSet<>(Arrays.asList(w2.split("")));
        return t1.equals(t2);
    }
    public List<ArrayList<String>> getSortedByAnQty() {
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        for (String word : this.wordList) {
            boolean found = false;
            for (ArrayList<String> list: out) {
                String listWord = list.get(0);

                if (this.areAnagrams(listWord, word)) {
                    list.add(word);
                    found = true;
                    break;
                }
            }
            if (!found) {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(word);
                out.add(newList);
            }
        }
        return out.stream()
                .sorted((e1, e2) -> { //    LAMBDA

                    int difference = e2.size() - e1.size();
                    if (difference == 0) {
                        return e1.get(0).compareTo(e2.get(0));
                    }
                    return difference;

                }).collect(Collectors.toList());
    }
    public String getAnagramsFor(String w) {
        ArrayList<String> temp = this.getSortedByAnQty()
                .stream()
                .filter((e) -> this.areAnagrams(w, e.get(0)))
                .findAny()
                .orElse(null);

        if (temp == null) {
            return w + ": null";
        }

        List<String> list = temp
                .stream()
                .filter(el -> !el.equals(w))
                .collect(Collectors.toList());
        return w + ": " + list;
    }
}
