/**
 *
 *  @author Wyrzykowski Tadeusz S17315
 *
 */

package zad4;


import zad3.Anagrams;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Main {
  public static void main(String[] args) {
    Anagrams an = new Anagrams("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");

    int max = an.getSortedByAnQty().iterator().next().size();

    StreamSupport.stream(an.getSortedByAnQty().spliterator(), false)
            .filter(wlist -> wlist.size() == max)
            .forEach(wlist -> System.out.println(wlist.get(0) + " "
                    + wlist.stream().filter(w->!w.equalsIgnoreCase(wlist.get(0))).collect(Collectors.joining(" "))));
  }
}
