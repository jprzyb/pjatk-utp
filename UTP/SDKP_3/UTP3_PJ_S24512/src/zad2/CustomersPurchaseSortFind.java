/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersPurchaseSortFind {

    public static List<String> list;

    public void readFile(String path)  {

        List<String> temp = new ArrayList<>();

        try{
            temp = Files.readAllLines(Path.of(path));
        }catch (Exception e){
            e.printStackTrace();
        }

        List<String> temp2 = temp.stream().
                map(line -> line.split(";"))
                .flatMap(tablica -> Arrays.stream(tablica))
                .collect(Collectors.toList());
        List<String>  list = temp2.stream()
                .map(line -> line.split(" "))
                .flatMap(tablica -> Arrays.stream(tablica))
                .collect(Collectors.toList());
        this.list = list;



    }

    public List<Purchase> getPurchases(){

        List<Purchase> purchases = new ArrayList<>();
        for(int i=0;i<list.size();i+=6){
            purchases.add(new Purchase(list.get(i) , list.get(i+1) , list.get(i+2) , list.get(i+3) , Double.valueOf(list.get(i+4)) , Double.valueOf(list.get(i+5)) ));
        }

        return purchases;
    }

    public void showSortedBy(String val){
        if(val == "Nazwiska") showSortedByNazwisko();
        else if(val.equals("Koszty")) showSortedByKoszty();
        System.out.println();
    }

    public void showPurchaseFor(String id){
        if(id.equals("c00001")) showSortedByC00001();
        else if(id.equals("c00002")) showSortedByC00002();
        System.out.println();
    }

    private void showSortedByNazwisko() {
        List<Purchase> purchases = getPurchases();

        List<Purchase> sortedResoult = purchases.stream()
                .sorted(Comparator.comparing(Purchase::getNazwisko).thenComparing(Purchase::getId))
                .collect(Collectors.toList());
        System.out.println("Nazwiska");
        for(Purchase p : sortedResoult) System.out.println(p);
    }

    private void showSortedByKoszty() {
        List<Purchase> purchases = getPurchases();

        List<Purchase> sortedResoult = purchases.stream()
                .sorted(Comparator.comparing(Purchase::getCena).reversed().thenComparing(Purchase::getId))
                .collect(Collectors.toList());
        System.out.println("Koszty");
        for(Purchase p : sortedResoult) System.out.println(p.toStringK());;
    }

    private void showSortedByC00001() {
        List<Purchase> purchases = getPurchases();

        List<Purchase> sortedResoult = purchases.stream()
                .filter(p -> p.getId().equals("c00001"))
                .collect(Collectors.toList());
        System.out.println("Klient c00001");
        for(Purchase p : sortedResoult) System.out.println(p.toString());
    }

    private void showSortedByC00002() {
        List<Purchase> purchases = getPurchases();

        List<Purchase> sortedResoult = purchases.stream()
                .filter(p -> p.getId().equals("c00002"))
                .collect(Collectors.toList());
        System.out.println("Klient c00002");
        for(Purchase p : sortedResoult) System.out.println(p.toString());
    }

    public static void log(String message){
        System.out.println("[CPSF]: "+message);
    }

}
