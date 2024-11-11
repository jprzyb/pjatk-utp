/**
 * @author Wyrzykowski Tadeusz S17315
 */

package zad2;

import java.util.*;

public class Main {

    static List<String> getPricesInPLN(List<String> destinations, double xrate) {
        return ListCreator.collectFrom(destinations)
                .when(s -> s.startsWith("WAW") && s.split(" ").length == 3)
                .mapEvery(m -> {
                    String[] data = m.split(" ");
                    String to = data[1];
                    int price = (int) (Double.parseDouble(data[2]) * xrate);
                    return "to " + to + " - price in PLN:  " + price;
                });
    }

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
        List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
        for (String r : result) System.out.println(r);
    }
}