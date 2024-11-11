/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {
    public void readFile(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] atr = line.split(";");
            new Purchase(atr[0], atr[1].split(" ")[0], atr[1].split(" ")[1], atr[2],Double.parseDouble(atr[3]), Double.parseDouble(atr[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showSortedBy(String sortType){
        List<Purchase> purchases = new ArrayList<>();
        purchases.addAll(Purchase.getList());

        if (sortType.equals("Nazwiska")){
            System.out.println("Nazwiska");
            Collections.sort(purchases, Comparator.comparing(Purchase::getId));
            Collections.sort(purchases, Comparator.comparing(Purchase::getLastName));
        }
        else{
            System.out.println("Koszty");
            Collections.sort(purchases, Comparator.comparing(Purchase::getId));
            Collections.sort(purchases, Comparator.comparing(Purchase::getPrice));
        }

        for (Purchase p : purchases) {
            System.out.println(p);
        }
        System.out.println();
    }
    public void showPurchaseFor(String id) {
        List<Purchase> purchases = new ArrayList<>();
        purchases.addAll(Purchase.getList());
        System.out.println("Klient "+id);
        for(Purchase p : purchases){
            if(p.getId().equals(id)) System.out.println(p);
        }
        System.out.println();
    }
}
