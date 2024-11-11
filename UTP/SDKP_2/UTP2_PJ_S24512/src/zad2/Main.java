/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad2;


/*<-- niezbędne importy */

import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream().filter(s -> s.toString().startsWith("WAW")).map( //filter
            s -> s.toString().replace("WAW" ,"to")).map(s -> s.toString().replace(s.substring(s.lastIndexOf(" ") , s.length()) , (" - price in PLN:\t"+Double.valueOf(s.substring(s.lastIndexOf(" ") , s.length()))*ratePLNvsEUR))).map(s -> s.toString().replace(".0" ,"")).toList();
    for (String r : result) System.out.println(r);
  }
}