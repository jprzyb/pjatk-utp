/**
 *
 *  @author Wyrzykowski Tadeusz S17315
 *
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {

    private List<Purchase> purchaseList = new ArrayList<>();

    public void readFile(String fname) {
        purchaseList = new ArrayList<>();

        try {
            Files.readAllLines(Paths.get(fname)).forEach(line -> {
                String[] data = line.split(";");

                Purchase purchase = new Purchase(line);
                purchase.setClientId(data[0]);

                String[] fullName = data[1].split(" ");
                purchase.setName(fullName[1]);
                purchase.setSurname(fullName[0]);

                purchase.setProduct(data[2]);
                purchase.setQuantity(Double.parseDouble(data[3]));
                purchase.setUnitPrice(Double.parseDouble(data[4]));

                purchaseList.add(purchase);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String sortBy) {
        System.out.println(sortBy);

        if (sortBy.equalsIgnoreCase("Nazwiska")) {
            purchaseList.stream()
                    .sorted((o1, o2) -> {
                        int surnameCompare = o1.getSurname().compareToIgnoreCase(o2.getSurname());

                        if (surnameCompare == 0) {
                            return o1.getClientId().compareToIgnoreCase(o2.getClientId());
                        } else return surnameCompare;
                    })
                    .forEach(purchase -> System.out.println(purchase.getFullLine()));
        } else if (sortBy.equalsIgnoreCase("Koszty")) {
            purchaseList.stream()
                    .sorted((o1, o2) -> {
                        int priceCompare = Double.compare(
                                (o1.getQuantity() * o1.getUnitPrice() * -1)
                                , (o2.getQuantity() * o2.getUnitPrice() * -1)
                        );

                        if (priceCompare == 0) {
                            return o1.getClientId().compareToIgnoreCase(o2.getClientId());
                        } else return priceCompare;
                    })
                    .forEach(purchase -> {
                        double price = purchase.getUnitPrice() * purchase.getQuantity();
                        System.out.println(purchase.getFullLine() + " (koszt: " + price + ")");
                    });
        } else {
            purchaseList
                    .forEach(purchase -> System.out.println(purchase.getFullLine()));
        }

        System.out.println();
    }

    public void showPurchaseFor(String clientId) {
        System.out.println("Klient " + clientId);
        purchaseList.stream()
                .filter(purchase -> purchase.getClientId().equalsIgnoreCase(clientId))
                .forEach(purchase -> System.out.println(purchase.getFullLine()));
        System.out.println();
    }
}
