/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) throws IOException {

      URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
      Map<String, String> anagrams = Stream.of(url.openStream())
              .flatMap(in -> new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines())
              .map(String::toLowerCase)
              .collect(Collectors.groupingBy(Main::sort, Collectors.joining(" "))) ;

      int maxCount = anagrams.values().stream()
              .mapToInt(anagamsCollection -> anagamsCollection.split("\\s+").length)
              .max().orElse(0);

      anagrams.entrySet().stream()
              .filter(entry -> entry.getValue().split("\\s+").length == maxCount)
              .sorted(Map.Entry.comparingByValue())
              .forEach(entry -> System.out.println(entry.getValue()));
  }

    private static String sort(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

}
